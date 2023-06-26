package com.atguigu.gulimail.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atguigu.gulimail.product.dao.CategoryDao;
import com.atguigu.gulimail.product.entity.CategoryEntity;
import com.atguigu.gulimail.product.service.CategoryBrandRelationService;
import com.atguigu.gulimail.product.service.CategoryService;
import com.atguigu.gulimail.product.vo.Catelog2Vo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Autowired
    private RedissonClient redissonClient;

//    @Override
//    public PageUtils queryPage(Map<String, Object> params) {
//        IPage<CategoryEntity> page = this.page(
//                new Query<CategoryEntity>().getPage(params),
//                new QueryWrapper<CategoryEntity>()
//        );
//
//        return new PageUtils(page);
//    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 1、查出所有分类 (查询条件nul表示查询所有数据)
        List<CategoryEntity> entities = baseMapper.selectList(null);

        // 2、组装成父子的树形结构
        // 2.1 找到所有的一级分类
        List<CategoryEntity> levelOneMenus = entities
                .stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == 0)  // 找到所有的一级分类
                .map((memu) -> {
                    // 查找当前分类的子分类
                    memu.setChildren(getChildrens(memu, entities));
                    return memu;
                }).sorted((menu1, menu2) -> {
                    // 注意要防止空指针异常
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                })
                .collect(Collectors.toList());

        return levelOneMenus;
    }


    // 递归查找所有菜单的子菜单
    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> childrens = all
                .stream()
                .filter(categoryEntity -> {
                    return categoryEntity.getParentCid() == root.getCatId();
                })
                .map(categoryEntity -> {
                    // 1、找到子菜单
                    categoryEntity.setChildren(getChildrens(categoryEntity, all));
                    return categoryEntity;
                })
                .sorted((menu1, menu2) -> {
                    // 2、菜单的排序
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                }).collect(Collectors.toList());
        return childrens;
    }

    /**
     * 找到catelogId的完整路径
     *
     * @param catelogId
     * @return
     */
    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> paths = new ArrayList<>();

        List<Long> parentPath = findParentPath(catelogId, paths);

        Collections.reverse(parentPath);

        return parentPath.toArray(new Long[parentPath.size()]);
    }


    private List<Long> findParentPath(Long catelogId, List<Long> paths) {
        // 1.收集当前节点id
        paths.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if (byId.getParentCid() != 0) {
            findParentPath(byId.getParentCid(), paths);
        }
        return paths;
    }


    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO 检查当前删除的菜单是否被别的地方引用
        // 逻辑删除
        baseMapper.deleteBatchIds(asList);
    }


    /**
     * 级联更新所有关联的数据
     *
     * @CacheEvict注解 : 失效模式
     * 同时进行多种缓存操作的方法：
     * 1、使用@Caching注解：
     *     @Caching(evict = {
     *             @CacheEvict(value = "category", key = "'getLevel1Categorys'"),
     *             @CacheEvict(value = "category", key = "'getCatalogJson'")
     *     })
     * 2.1 、指定删除某个分区下的所有数据 @CacheEvict(value = "category",allEntries = true)
     * 2.2 、存储同一类型的数据，都可以指定成同一个分区。分区名默认就是缓存的前缀
     */
    // @CacheEvict 只要执行此方法修改了数据库中的三级分类数据，就把缓存中的旧数据删除。当执行查询语句时重新更新缓存
//    @CacheEvict(value = "category", key = "'getLevel1Categorys'")

    //
//    @CacheEvict(value = "category",allEntries = true) //失效模式
    @Caching(evict = {
            @CacheEvict(value = "category", key = "'getLevel1Categorys'"),
            @CacheEvict(value = "category", key = "'getCatalogJson'")
    })
    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());

        //同时修改缓存中的数据
        //redis.del("catalogJSON");等待下次主动查询进行更新
    }


    /**
     * 获取一级分类列表
     *
     * @Cacheable 注解：
     * 代表当前方法的结果需要缓存，如果缓存中有，方法不用调用。如果缓存中没有，会调用方法，最后将方法的结果放入缓存
     * 可以给每一个需要缓存的数据指定要放到哪个名字的缓存分区。缓存的分区一般按照业务类型分,一份数据可以放到多个分区
     * 如果要将方法返回的数据放到category和product分区，就可以在方法上加注解@Cacheable({"category","product"})
     * 1、每一个需要缓存的数据我们都来指定要放到那个名字的缓存。【缓存的分区(按照业务类型分)】
     * 2、 @Cacheable({"category"})
     * 代表当前方法的结果需要缓存，如果缓存中有，方法不用调用。
     * 如果缓存中没有，会调用方法，最后将方法的结果放入缓存
     * 3、默认行为
     * 1）、如果缓存中有，方法不用调用。
     * 2）、key默认自动生成；缓存的名字::SimpleKey [](自主生成的key值)
     * 3）、缓存的value的值。默认使用jdk序列化机制，将序列化后的数据存到redis
     * 4）、默认ttl时间 -1；
     * <p>
     * 自定义：
     * 1）、指定生成的缓存使用的key：  key属性指定，接受一个SpEL
     * SpEL的详细https://docs.spring.io/spring/docs/5.1.12.RELEASE/spring-framework-reference/integration.html#cache-spel-context
     * 2）、指定缓存的数据的存活时间： 配置文件中修改ttl
     * 3）、将数据保存为json格式:
     * 自定义RedisCacheConfiguration即可
     * 4、Spring-Cache的不足；
     * 1）、读模式：
     * 缓存穿透：查询一个null数据。解决：缓存空数据；ache-null-values=true
     * 缓存击穿：大量并发进来同时查询一个正好过期的数据。解决：加锁；？默认是无加锁的;sync = true（加锁，解决击穿）
     * 缓存雪崩：大量的key同时过期。解决：加随机时间。加上过期时间。：spring.cache.redis.time-to-live=3600000
     * 2）、写模式：（缓存与数据库一致）
     * 1）、读写加锁。
     * 2）、引入Canal，感知到MySQL的更新去更新数据库
     * 3）、读多写多，直接去数据库查询就行
     * 总结：
     * 常规数据（读多写少，即时性，一致性要求不高的数据）；完全可以使用Spring-Cache；写模式（只要缓存的数据有过期时间就足够了）
     * 特殊数据：特殊设计
     * <p>
     * 原理：
     * CacheManager(RedisCacheManager)->Cache(RedisCache)->Cache负责缓存的读写
     */
//    @Cacheable(value = {"category"}, key ="'getLevel1Categorys'")
    @Cacheable(value = {"category"},key = "#root.method.name",sync = true)
    @Override
    public List<CategoryEntity> getLevel1Categorys() {
        List<CategoryEntity> categoryEntities = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
        return categoryEntities;
    }

//
//    /**
//     * 三级分类数据获取性能优化一：将数据库的多次查询变为一次
//     */
//    @Override
//    public Map<String, List<Catelog2Vo>> getCatalogJson() {
//
//        /**
//         *  三级分类数据获取性能优化： 1.将数据库的多次查询变为一次
//         */
//        // 查到所有数据
//        List<CategoryEntity> selectList = baseMapper.selectList(null);
//
//
//        // 1.查出所有分类1级分类
//        List<CategoryEntity> level1Categorys = getParentCid(selectList, 0L);
//
//
//        // 2.封装数据
//        Map<String, List<Catelog2Vo>> parent_cid = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
//            // 每一个的一级分类，查到这个一级分类的二级分类
//            List<CategoryEntity> categoryEntities = getParentCid(selectList, v.getCatId());
//
//            // 3.封装上面的结果
//            List<Catelog2Vo> catelog2Vos = null;
//            if (categoryEntities != null) {
//                catelog2Vos = categoryEntities.stream().map(l2 -> {
//                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
//                    // 4、找当前二级分类的三级分类封装成vo
//                    List<CategoryEntity> level3Catelog = getParentCid(selectList, l2.getCatId());
//                    if (level3Catelog != null) {
//                        List<Catelog2Vo.Catelog3Vo> collect = level3Catelog.stream().map(l3 -> {
//                            // 封装成指定格式
//                            Catelog2Vo.Catelog3Vo catelog3Vo = new Catelog2Vo.Catelog3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
//
//                            return catelog3Vo;
//                        }).collect(Collectors.toList());
//                        catelog2Vo.setCatalog3List(collect);
//
//                    }
//                    return catelog2Vo;
//                }).collect(Collectors.toList());
//            }
//            return catelog2Vos;
//        }));
//
//        return parent_cid;
//    }
//
//    private List<CategoryEntity> getParentCid(List<CategoryEntity> selectList, Long parentCid) {
//        List<CategoryEntity> collect = selectList.stream().filter(item -> item.getParentCid() == parentCid).collect(Collectors.toList());
//        return collect;
//    }
//


//    //TODO 产生堆外内存溢出：OutOfDirectMemoryError
//    //1）、springboot2.0以后默认使用lettuce作为操作redis的客户端。它使用netty进行网络通信。
//    //2）、lettuce的bug导致netty堆外内存溢出 由于JVM设置了内存-Xmx300m；netty如果没有指定堆外内存，默认使用-Xmx300m作为堆外内存
//    //   可以通过-Dio.netty.maxDirectMemory进行设置
//    //解决方案：不能只使用-Dio.netty.maxDirectMemory去调大堆外内存。
//    //  1）、升级lettuce客户端。   2）、切换使用jedis
//    //  redisTemplate：
//    //  lettuce和jedis都是操作redis的底层客户端。Spring再次封装lettuce和jedis后得到redisTemplate；
//
//    /**
//     * 三级分类数据获取性能优化一：使用redis缓存优化三级分类数据获取
//     */
//    @Override
//    public Map<String, List<Catelog2Vo>> getCatalogJson() {
//        // 给缓存中放json字符串，拿出的json字符串， 还用逆转为能用的对象类型; [序列化与反序列化]
//
//        // 1、加入缓存逻辑
//        String catalogJSON = stringRedisTemplate.opsForValue().get("catalogJSON");
//        if (StringUtils.isEmpty(catalogJSON)) {
//            // 2、如果缓存中不存在，则到数据库中查询
//            // 缓存中数据都要存成JSON字符串。存成JSON的好处：JSON格式的数据是跨平台、跨语言兼容的
//            Map<String, List<Catelog2Vo>> catalogJsonFromDb = getCatalogJsonFromDb();
//
//
//            // 3、将查到的数据放到缓存，将对象转为JSON后存到redis缓存中
//            String jsonString = JSON.toJSONString(catalogJsonFromDb);
//            stringRedisTemplate.opsForValue().set("catalogJSON", jsonString);
//
//            // 返回数据
//            return catalogJsonFromDb;
//
//        }
//
//        // 将redis中取出来的json数据转成我们指定的对象
//        Map<String, List<Catelog2Vo>> result = JSON.parseObject(catalogJSON, new TypeReference<Map<String, List<Catelog2Vo>>>() {
//        });
//
//        return result;
//    }
//
//
//    // 从数据库查询并封装分类数据
//    public Map<String, List<Catelog2Vo>> getCatalogJsonFromDb() {
//
//        /**
//         *  三级分类数据获取性能优化： 1.将数据库的多次查询变为一次
//         */
//        // 查到所有数据
//        List<CategoryEntity> selectList = baseMapper.selectList(null);
//
//
//        // 1.查出所有分类1级分类
//        List<CategoryEntity> level1Categorys = getParentCid(selectList, 0L);
//
//
//        // 2.封装数据
//        Map<String, List<Catelog2Vo>> parent_cid = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
//            // 每一个的一级分类，查到这个一级分类的二级分类
//            List<CategoryEntity> categoryEntities = getParentCid(selectList, v.getCatId());
//
//            // 3.封装上面的结果
//            List<Catelog2Vo> catelog2Vos = null;
//            if (categoryEntities != null) {
//                catelog2Vos = categoryEntities.stream().map(l2 -> {
//                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
//                    // 4、找当前二级分类的三级分类封装成vo
//                    List<CategoryEntity> level3Catelog = getParentCid(selectList, l2.getCatId());
//                    if (level3Catelog != null) {
//                        List<Catelog2Vo.Catelog3Vo> collect = level3Catelog.stream().map(l3 -> {
//                            // 封装成指定格式
//                            Catelog2Vo.Catelog3Vo catelog3Vo = new Catelog2Vo.Catelog3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
//
//                            return catelog3Vo;
//                        }).collect(Collectors.toList());
//                        catelog2Vo.setCatalog3List(collect);
//
//                    }
//                    return catelog2Vo;
//                }).collect(Collectors.toList());
//            }
//            return catelog2Vos;
//        }));
//
//        return parent_cid;
//    }
//
//    private List<CategoryEntity> getParentCid(List<CategoryEntity> selectList, Long parentCid) {
//        List<CategoryEntity> collect = selectList.stream().filter(item -> item.getParentCid() == parentCid).collect(Collectors.toList());
//        return collect;
//    }


    //TODO 产生堆外内存溢出：OutOfDirectMemoryError
    //1）、springboot2.0以后默认使用lettuce作为操作redis的客户端。它使用netty进行网络通信。
    //2）、lettuce的bug导致netty堆外内存溢出 由于JVM设置了内存-Xmx300m；netty如果没有指定堆外内存，默认使用-Xmx300m作为堆外内存
    //   可以通过-Dio.netty.maxDirectMemory进行设置
    //解决方案：不能只使用-Dio.netty.maxDirectMemory去调大堆外内存。
    //  1）、升级lettuce客户端。   2）、切换使用jedis
    //  redisTemplate：
    //  lettuce和jedis都是操作redis的底层客户端。Spring再次封装lettuce和jedis后得到redisTemplate；


    @Cacheable(value = "category",key = "#root.methodName")
    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        log.info("缓存没命中，查询了数据库...");
        // 查到所有数据
        List<CategoryEntity> selectList = baseMapper.selectList(null);

        /**
         *  三级分类数据获取性能优化： 1.将数据库的多次查询变为一次
         */

        // 1.查出所有分类1级分类
        List<CategoryEntity> level1Categorys = getParentCid(selectList, 0L);


        // 2.封装数据
        Map<String, List<Catelog2Vo>> parent_cid = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            // 每一个的一级分类，查到这个一级分类的二级分类
            List<CategoryEntity> categoryEntities = getParentCid(selectList, v.getCatId());

            // 3.封装上面的结果
            List<Catelog2Vo> catelog2Vos = null;
            if (categoryEntities != null) {
                catelog2Vos = categoryEntities.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
                    // 4、找当前二级分类的三级分类封装成vo
                    List<CategoryEntity> level3Catelog = getParentCid(selectList, l2.getCatId());
                    if (level3Catelog != null) {
                        List<Catelog2Vo.Catelog3Vo> collect = level3Catelog.stream().map(l3 -> {
                            // 封装成指定格式
                            Catelog2Vo.Catelog3Vo catelog3Vo = new Catelog2Vo.Catelog3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());

                            return catelog3Vo;
                        }).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(collect);

                    }
                    return catelog2Vo;
                }).collect(Collectors.toList());
            }
            return catelog2Vos;
        }));
        return parent_cid;
    }


    /**
     * 三级分类数据获取性能优化二：加锁解决缓存击穿问题
     */
//    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson2() {
        // 给缓存中放json字符串，拿出的json字符串， 还用逆转为能用的对象类型; [序列化与反序列化]

        // 1、加入缓存逻辑
        String catalogJSON = stringRedisTemplate.opsForValue().get("catalogJSON");
        if (StringUtils.isEmpty(catalogJSON)) {
            log.info("缓存不命中...将要查询数据库");
            // 2、如果缓存中不存在，则到数据库中查询
            // 缓存中数据都要存成JSON字符串。存成JSON的好处：JSON格式的数据是跨平台、跨语言兼容的
            Map<String, List<Catelog2Vo>> catalogJsonFromDb = getCatalogJsonFromDbWithRedisLock();

            // 返回数据
            return catalogJsonFromDb;

        }
        log.info("缓存命中，直接返回数据");
        // 将redis中取出来的json数据转成我们指定的对象
        Map<String, List<Catelog2Vo>> result = JSON.parseObject(catalogJSON, new TypeReference<Map<String, List<Catelog2Vo>>>() {
        });

        return result;
    }

    // 从数据库查询并封装分类数据（使用redis缓存解决缓存击穿问题）
    public Map<String, List<Catelog2Vo>> getCatalogJsonFromDbWithRedisLock() {
        // 1、占分布式锁。去redis占坑
        String uuid = UUID.randomUUID().toString();
        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent("lock", uuid, 300, TimeUnit.SECONDS);
        if (lock) {
            log.info("获取分布式锁成功...");
            // 加锁成功...执行业务
            // 设置过期时间,防止执行业务代码时出错而导致锁无法释放，锁没有释放就会造成死锁，加上过期时间后即便业务出错，也会在指定时间内释放锁
            // stringRedisTemplate.expire("lock", 30, TimeUnit.SECONDS);
            Map<String, List<Catelog2Vo>> dateFromDB;
            try {
                dateFromDB = getDateFromDB();
            } finally {
                //            // 执行完业务要把锁释放,将锁删除
//            // 获取锁的值进行对比 + 对比成功后删除锁 = 原子操作
//            String lockValue = stringRedisTemplate.opsForValue().get("lock");
//            if (uuid.equals(lockValue)) {
//                // 如果是自己的锁才能删除：防止误删其他线程的锁
//                stringRedisTemplate.delete("lock");
//            }

                // 执行完业务要把锁释放,将锁删除
                // 获取锁的值进行对比 + 对比成功后删除锁 = 原子操作
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                // 删除锁
                Long lock1 = stringRedisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class)
                        , Arrays.asList("lock"), uuid);
            }

            return dateFromDB;
        } else {
            // 加锁失败...重试。
            log.info("获取分布式锁失败...等待重试....");
            // 本地锁的synchronized ()自带监听功能，以自旋的方式一直重试，当其他线程释放锁时尝试获取锁
            // 此处redis缓存获取锁，我们自己通过递归调用，继续尝试获取锁（自旋的方式）
            // 为限制重试频率，让线程休眠一段时间
            try {
                Thread.sleep(200L);
            } catch (Exception e) {
//                e.printStackTrace();
            }
            return getCatalogJsonFromDbWithRedisLock();
        }

    }


    /**
     * 从数据库查询并封装分类数据（使用redission缓存解决缓存击穿问题）
     * 缓存数据一致性问题： 缓存里面的数据如何与数据库保持一致
     * 数据一致性的两种模式：
     * 1）、双写模式：
     * 2）、失效模式：
     */
    public Map<String, List<Catelog2Vo>> getCatalogJsonFromDbWithRedissionLock() {

        // 锁的粒度越细，程序效率越高。
        // 锁的名字相同就代表同一把锁，锁的名字尽量具体一些，具体点的名字就可以保证锁的粒度
        // 最佳实践：缓存某一个具体的数据，如缓存11号商品防止该商品的缓存击穿问题，就应该将锁的名字设为product-11-lock,缓存13号商品,就应该将锁的名字设为product-13-lock
        // 而不应该将所有商品的锁笼统地设为product-lock
        RLock lock = redissonClient.getLock("catalogJson-lock");
        lock.lock(); // 阻塞式等待


        Map<String, List<Catelog2Vo>> dateFromDB;
        try {
            dateFromDB = getDateFromDB();
        } finally {
            lock.unlock();
        }

        return dateFromDB;

    }

    private Map<String, List<Catelog2Vo>> getDateFromDB() {
        // 得到锁以后，我们应该再去缓存中再确定一次，如果没有才继续查询
        String catalogJSON = stringRedisTemplate.opsForValue().get("catalogJSON");
        // 如果缓存命中就直接返回
        if (!StringUtils.isEmpty(catalogJSON)) {
            // 将redis中取出来的json数据转成我们指定的对象
            Map<String, List<Catelog2Vo>> result = JSON.parseObject(catalogJSON, new TypeReference<Map<String, List<Catelog2Vo>>>() {
            });
            return result;

        }
        log.info("缓存没命中，查询了数据库...");
        // 查到所有数据
        List<CategoryEntity> selectList = baseMapper.selectList(null);

        /**
         *  三级分类数据获取性能优化： 1.将数据库的多次查询变为一次
         */

        // 1.查出所有分类1级分类
        List<CategoryEntity> level1Categorys = getParentCid(selectList, 0L);


        // 2.封装数据
        Map<String, List<Catelog2Vo>> parent_cid = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            // 每一个的一级分类，查到这个一级分类的二级分类
            List<CategoryEntity> categoryEntities = getParentCid(selectList, v.getCatId());

            // 3.封装上面的结果
            List<Catelog2Vo> catelog2Vos = null;
            if (categoryEntities != null) {
                catelog2Vos = categoryEntities.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
                    // 4、找当前二级分类的三级分类封装成vo
                    List<CategoryEntity> level3Catelog = getParentCid(selectList, l2.getCatId());
                    if (level3Catelog != null) {
                        List<Catelog2Vo.Catelog3Vo> collect = level3Catelog.stream().map(l3 -> {
                            // 封装成指定格式
                            Catelog2Vo.Catelog3Vo catelog3Vo = new Catelog2Vo.Catelog3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());

                            return catelog3Vo;
                        }).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(collect);

                    }
                    return catelog2Vo;
                }).collect(Collectors.toList());
            }
            return catelog2Vos;
        }));

        // 3、将查到的数据放到缓存，将对象转为JSON后存到redis缓存中
        String jsonString = JSON.toJSONString(parent_cid);
        stringRedisTemplate.opsForValue().set("catalogJSON", jsonString);
        return parent_cid;
    }


    // 从数据库查询并封装分类数据(使用本地锁解决缓存击穿问题)
    public Map<String, List<Catelog2Vo>> getCatalogJsonFromDbWithLocalLock() {

        // 只要是同一把锁、就能锁住需要这个锁的所有线程
        // 因为springboot的所有组件在容器中都是单例的，所以利用此处的this即可实现上锁
        synchronized (this) {
            // 得到锁以后，我们应该再去缓存中再确定一次，如果没有才继续查询
            return getDateFromDB();
        }

    }

    private List<CategoryEntity> getParentCid(List<CategoryEntity> selectList, Long parentCid) {
        List<CategoryEntity> collect = selectList.stream().filter(item -> item.getParentCid() == parentCid).collect(Collectors.toList());
        return collect;
    }


}