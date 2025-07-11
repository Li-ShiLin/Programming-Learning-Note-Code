package com.hmdp.service.impl;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;
import com.hmdp.mapper.ShopMapper;
import com.hmdp.service.IShopService;
import com.hmdp.utils.CacheClient;
import com.hmdp.utils.RedisData;
import com.hmdp.utils.SystemConstants;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.domain.geo.GeoReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.*;

/**
 * <p>
 * 服务实现类
 * </p>
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private CacheClient cacheClient;

    @Override
    public Result queryById(Long id) {
/*      // 调用queryWithPassThrough(id)解决商品查询的缓存穿透问题
        Shop shop = queryWithPassThrough(id);*/


/*      // 互斥锁解决缓存击穿
        Shop shop = queryWithMutex(id);*/

/*        // 逻辑过期解决缓存击穿问题
        Shop shop = queryWithLogicalExpire(id);*/

/*
        // 利用封装的Redis工具类解决缓存穿透问题
        Shop shop = cacheClient.queryWithPassThrough(CACHE_SHOP_KEY,id,Shop.class,id2->getById(id2),CACHE_SHOP_TTL,TimeUnit.MINUTES);
*/

        // 利用封装的Redis工具类解决缓存击穿问题
        Shop shop = cacheClient.queryWithLogicalExpire(
                CACHE_SHOP_KEY, id, Shop.class, this::getById, CACHE_SHOP_TTL, TimeUnit.MINUTES);

        if (shop == null) {
            return Result.fail("店铺不存在！");

/*  原代码的不足之处：
            如果报“店铺不存在”，但是数据库中有数据，可以在单元测试中通过saveShop2Redis方法进行缓存预热
            也可以在queryWithLogicalExpire改进缓存重建部分代码，返回数据库中的店铺信息
            也可以直接在此处查询数据库
               */
        }

        // 7.返回成功信息
        return Result.ok(shop);
    }

    // 解决商品查询的缓存穿透问题
    private Shop queryWithPassThrough(Long id) {
        // 1.从redis查询商铺缓存
        // 最好用hash结构，此处用字符串演示
        String key = CACHE_SHOP_KEY + id;
        String shopJson = stringRedisTemplate.opsForValue().get(key);

        // 2.判断是否存在
        if (StrUtil.isNotBlank(shopJson)) {
            // 3.存在，直接返回
            Shop shop = JSONUtil.toBean(shopJson, Shop.class);
            return shop;
        }

        // 判断命中的是否是空值
        if (shopJson != null) {
            // 返回错误信息
            return null;
        }

        // 4.不存在，根据id查询数据库
        Shop shop = getById(id);

        // 5.数据库中不存在，返回错误
        if (shop == null) {
            // 将空值写入redis（解决商品查询的缓存穿透问题）
            stringRedisTemplate.opsForValue().set(key, "", CACHE_NULL_TTL, TimeUnit.MINUTES);
            // 不存在，返回错误
            return null;
        }

        // 6.数据库中存在，写入redis
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(shop), CACHE_SHOP_TTL, TimeUnit.MINUTES);

        // 7.返回成功信息
        return shop;
    }


    // 获取锁
    private boolean tryLock(String key) {
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", 10, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(flag);
    }


    // 释放锁
    private void unlock(String key) {
        stringRedisTemplate.delete(key);
    }


    // 互斥锁解决商品查询的缓存击穿问题
    private Shop queryWithMutex(Long id) {
        // 1.从redis查询商铺缓存
        // 最好用hash结构，此处用字符串演示
        String key = CACHE_SHOP_KEY + id;
        String shopJson = stringRedisTemplate.opsForValue().get(key);

        // 2.判断是否存在
        if (StrUtil.isNotBlank(shopJson)) {
            // 3.存在，直接返回
            Shop shop = JSONUtil.toBean(shopJson, Shop.class);
            return shop;
        }

        // 判断命中的是否是空值
        if (shopJson != null) {
            // 返回错误信息
            return null;
        }

        // 4.实现缓存重建
        // 4.1 获取互斥锁
        String lockKey = "lock:shop" + id;
        Shop shop = null;
        try {
            boolean isLock = tryLock(lockKey);
            // 4.2 判断获取是否成功
            if (!isLock) {
                // 4.3 互斥锁获取失败，则休眠并重试
                Thread.sleep(50);
                return queryWithMutex(id);

            }

            // 4.4 获取互斥锁成功，根据id查询数据库
            shop = getById(id);
            // 模拟重建缓存的延时
            Thread.sleep(200);

            // 5.数据库中不存在，返回错误
            if (shop == null) {
                // 将空值写入redis（解决商品查询的缓存穿透问题）
                stringRedisTemplate.opsForValue().set(key, "", CACHE_NULL_TTL, TimeUnit.MINUTES);
                // 不存在，返回错误
                return null;
            }

            // 6.数据库中存在，写入redis
            stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(shop), CACHE_SHOP_TTL, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 7.释放互斥锁
            unlock(lockKey);
        }

        // 8.返回成功信息
        return shop;
    }


    // 缓存预热：活动前将店铺添加到redis -> 添加操作在单元测试中
    public void saveShop2Redis(Long id, long expireSeconds) {
        // 1.查询店铺数据
        Shop shop = getById(id);
        // 2.封装逻辑过期时间
        RedisData redisData = new RedisData();
        redisData.setData(shop);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(expireSeconds));

        // 3.写入redis
        stringRedisTemplate.opsForValue().set(CACHE_SHOP_KEY + id, JSONUtil.toJsonStr(redisData));
    }

    // 逻辑过期解决缓存击穿问题
    private Shop queryWithLogicalExpire(Long id) {
        // 1.从redis查询商铺缓存
        // 最好用hash结构，此处用字符串演示
        String key = CACHE_SHOP_KEY + id;
        String shopJson = stringRedisTemplate.opsForValue().get(key);

        // 2.判断是否存在
        if (StrUtil.isBlank(shopJson)) {
            // 3.不存在，直接返回
            return null;
        }

        // 4.命中，需要把json反序列化为对象
        RedisData redisData = JSONUtil.toBean(shopJson, RedisData.class);
        Shop shop = JSONUtil.toBean((JSONObject) redisData.getData(), Shop.class);
        LocalDateTime expireTime = redisData.getExpireTime();
        // 5.判断是否过期
        if (expireTime.isAfter(LocalDateTime.now())) {
            // 5.1 未过期，直接返回店铺信息
            return shop;
        }

        // 5.2 已过期，需要缓存重建
        // 6.缓存重建
        // 6.1 获取互斥锁
        String lockKey = LOCK_SHOP_KEY + id;
        boolean isLock = tryLock(lockKey);
        // 6.2 判断是否获取锁成功
        if (isLock) {
            // 6.3 成功.开启独立线程，实现缓存重建
            // 视频补充，注意：获取锁成功应该再次检测redis缓存是否过期，做DoubleCheck,如果存在则无需重建缓存
            CACHE_REBUILD_EXECUTOR.submit(() -> {
                try {
                    // 重建缓存——>查数据库后再写入redis
                    this.saveShop2Redis(id, 20L);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    // 释放锁
                    unlock(lockKey);
                }
            });
        }
        // 6.4 失败.返回过期的店铺信息
        return shop;
    }

    // 缓存重建线程池
    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);


    // 缓存
    @Override
    @Transactional
    public Result update(Shop shop) {
        Long id = shop.getId();
        if (id == null) {
            return Result.fail("店铺id不能为空");
        }
        // 1.更新数据库
        updateById(shop);
        // 2.删除缓存
        stringRedisTemplate.delete(CACHE_SHOP_KEY + id);
        return null;
    }

    @Override
    public Result queryShopByType(Integer typeId, Integer current, Double x, Double y) {
        // 1.判断是否需要根据坐标查询
        if (x == null || y == null) {
            // 不需要坐标查询，按数据库查询
            Page<Shop> page = query().eq("type_id", typeId)
                    .page(new Page<>(current, SystemConstants.DEFAULT_PAGE_SIZE));
            return Result.ok(page.getRecords());
        }
        // 2.计算分页参数
        int from = (current - 1) * SystemConstants.DEFAULT_PAGE_SIZE;
        int end = current * SystemConstants.DEFAULT_PAGE_SIZE;

        // 3.查询redis,按照距离排序、分页,查询结果：shopId、distance
        // GEOSEARCH BYLONLAT x y BYRADIUS 10 WITHDISTANCE
        String key = SHOP_GEO_KEY + typeId;
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = stringRedisTemplate.opsForGeo()
                .search(
                        key,
                        GeoReference.fromCoordinate(x, y),
                        new Distance(5000),
                        RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().limit(end)
                );
        // 4.解析出id
        if (results == null) {
            return Result.ok(Collections.emptyList());
        }
        List<GeoResult<RedisGeoCommands.GeoLocation<String>>> list = results.getContent();
        if (list.size() <= from) {
            // 没有下一页了，结束
            return Result.ok(Collections.emptyList());
        }
        // 4.1 截取from ~ end 的部分
        ArrayList<Long> ids = new ArrayList<>(list.size());
        Map<String, Distance> distanceMap = new HashMap<>(list.size());
        list.stream().skip(from).forEach(result -> {
            // 4.2 获取店铺id
            String shopIdStr = result.getContent().getName();
            // 4.3 获取距离
            ids.add(Long.valueOf(shopIdStr));
            Distance distance = result.getDistance();
            distanceMap.put(shopIdStr, distance);
        });

        // 5.根据id查询Shop
        String idStr = StrUtil.join(",", ids);
        List<Shop> shops = query().in("id", ids).last("ORDER BY FIELD(id," + idStr + ")").list();
        for (Shop shop : shops) {
            shop.setDistance(distanceMap.get(shop.getId().toString()).getValue());
        }
        // 6.返回
        return Result.ok(shops);
    }
}
