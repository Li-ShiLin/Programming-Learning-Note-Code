## 1.Spring Cache

###  1.1 spring cache 概述

- Spring 从 3.1开始定义了`org.springframework.cache.Cache`和`org.springframework.cache.CacheManager`接口来统一不同的缓存技术;并支持使用`JCache (JSR-107）`注解简化我们开发

- Cache接口为缓存的组件规范定义，包含缓存的各种操作集合;Cache接口下Spring 提供了各种`xxaCache` 的实现;`RedisCacheEhCacheCache ,ConcurrentMapCache`等

- 每次调用需要缓存功能的方法时,Spring 会检查指定参数的指定的目标方法是否已经被调用过;如果有就直接从缓存中获取方法调用后的结果,如果没有就调用方法并缓存结果后返回给用户。下次调用直接从缓存中获取

- 使用 Spring缓存抽象时我们需要关注以下两点:
  - 1、确定方法需要被缓存以及他们的缓存策略
  - 2、从缓存中读取之前缓存存储的数据

- 缓存管理器：

  - `CacheManager`:缓存管理器，用来管理各种各样的缓存。应用中要使用spring的缓存功能，就要配置一个或多个缓存管理器`CacheManager`

  - 缓存的名字主要用来给缓存管理器里面的缓存划分区域，缓存的名字通常被用作业务逻辑的标识

  - 缓存管理器的两个接口：

  - ```java
    public interface CacheManager {
        @Nullable
        Cache getCache(String var1); // 按照名字获取缓存
    
        Collection<String> getCacheNames(); // 获取缓存管理器中使用缓存的名字集合
    }
    ```

  -  spring支持`CacheManager`的多种实现：如`ConcurrentHashMap`和`CacheManager ( Redis）`

  - 缓存管理器概念图：


![image-20230610114824067](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306270616303.png)



###  1.2 整合spring cache

1、引入`spring cache `场景依赖

```xml
        <!--引入spring cache 依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>
```

2、在`application.properties`添加配置

```properties
spring.cache.type=redis
```

3、开启缓存功能：在启动类上添加注解`@EnableCaching`

```java
@EnableCaching
@EnableFeignClients(basePackages = "com.atguigu.gulimail.product.feign")
@EnableDiscoveryClient
@MapperScan("com.atguigu.gulimail.product.dao")
@SpringBootApplication
public class GulimailProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(GulimailProductApplication.class, args);
    }

}
```

4、使用缓存注解完成缓存操作。主要的注解有

- @Cacheable: Triggers cache population.：触发将数据保存到缓存的操作
- @CacheEvict: Triggers cache eviction.：触发将数据从缓存删除的操作
- @CachePut: 
  - Updates the cache without interfering with the method execution.：不影响方法执行更新缓存
  -  双写模式 如果某一个修改方法存在返回数据，在该方法上添加` @CachePut `注解，那修改后的值会被放到缓存

- @Caching: Regroups multiple cache operations to be applied on a method.：组合以上多个操作
- @CacheConfig: Shares some common cache-related settings at class-level.：在类级别共享缓存的相同配置



此处测试`@Cacheable`注解：在`CategoryServiceImpl`实现类的`getLevel1Categorys`添加注解

```java
@Slf4j
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    /**
     * @Cacheable 注解：
     * 代表当前方法的结果需要缓存，如果缓存中有，方法不用调用。如果缓存中没有，会调用方法，最后将方法的结果放入缓存
     * 可以给每一个需要缓存的数据指定要放到哪个名字的缓存分区。缓存的分区一般按照业务类型分,一份数据可以放到多个分区
     * 如果要将方法返回的数据放到category和product分区，就可以在方法上加注解@Cacheable({"category","product"})
     */
    @Cacheable({"category", "product"})
    @Override
    public List<CategoryEntity> getLevel1Categorys() {
        List<CategoryEntity> categoryEntities = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
        return categoryEntities;
    }

}
```

测试：访问首页`http://localhost:10001/index.html`，redis中出现了两条数据，分区为`category`和`product`,键对应的值是`categoryEntities`对象经过jdk序列化后的结果



![image-20230610213550757](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306270616102.png)



 * @Cacheable 注解总结：
   * 代表当前方法的结果需要缓存，如果缓存中有，方法不用调用。如果缓存中没有，会调用方法，最后将方法的结果放入缓存
   * 可以给每一个需要缓存的数据指定要放到哪个名字的缓存分区。缓存的分区一般按照业务类型分,一份数据可以放到多个分区
   * 如果要将方法返回的数据放到`category`和`product`分区，就可以在方法上加注解`@Cacheable({"category","product"})`
   * 键值key默认自动生成：`缓存的名字::SimpleKey [](自主生成的key值)` 。key对应的value默认值是对象经过jdk序列化后的结果
   * 默认的ttl是-1 ，也就是永不过期

###  1.3 注解@Cacheable & 自定义缓存配置

上面的简单配置中`spring cache`会默认一些配置，下面展示自定义缓存配置使用：

- 自定义生成的缓存使用的key：使用key属性指定，key属性接收一个SpEL表达式
  - SpEL的详细语法`https://docs.spring.io/spring/docs/5.1.12.RELEASE/spring-framework-reference/integration.html#cache-spel-context`
  - ` @Cacheable(value = {"category"}, key ="'level1Categorys'")`:SpEL表达式中常规字符串要加上单引号
- 自定义缓存数据的存活时间\过期时间：在配置文件中修改ttl,配置项`spring.cache.redis.time-to-live`
- 自定义缓存数据的value保存为JSON格式:自定义缓存管理器的配置
- 自定义缓存配置原理：缓存自动配置类`CacheAutoConfiguration`帮我们导入redis配置类`RedisCacheConfiguration` ，`RedisCacheConfiguration` 帮我们自动配置了redis缓存管理器`RedisCacheManager`,`RedisCacheManager`会初始化所有的缓存，如果`redisCacheConfiguration`有就用已有的，没有就用默认配置。如果想改缓存的配置，只需要给容器中放一个`RedisCacheConfiguration`，相应的配置就会应用到当前RedisCacheManager管理的所有缓存分区中

1、在`application.properties`中设置key值、过期时间、是否使用前缀、是否缓存空值

```properties
# 指定缓存类型为redis
spring.cache.type=redis
# 指定缓存存活时间，时间单位是毫秒
spring.cache.redis.time-to-live=3600000
# 指定缓存名字的前缀，如果指定了前缀就用我们指定的前缀，如果没有则默认使用缓存的名字作为前缀
#spring.cache.redis.key-prefix=CACHE_
# 指定是否使用缓存的前缀
spring.cache.redis.use-key-prefix=true
# 是否缓存空值（设为true并指定过期时间，则可以解决缓存穿透问题）
spring.cache.redis.cache-null-values=true
```

2、`MyCacheConfig`配置类：指定键和值的序列化方式，将值的格式设为JSON格式

```java
@EnableConfigurationProperties(CacheProperties.class)
@Configuration
@EnableCaching
public class MyCacheConfig {
//    @Autowired
//    CacheProperties cacheProperties;

    /**
     * 配置文件中的东西没有用上；
     * 1、原来和配置文件绑定的配置类是这样子的
     *       @ConfigurationProperties(prefix = "spring.cache")
     *       public class CacheProperties
     * 2、要让他生效
     *       @EnableConfigurationProperties(CacheProperties.class)
     */
    @Bean
    RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {


        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
//        config = config.entryTtl();

        // 指定key的序列化方式
        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        // 指定value的序列化方式
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));


        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        //让配置文件中的所有配置也都生效
        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixKeysWith(redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }
        return config;
    }
}
```

3、`CategoryServiceImpl`实现类：key属性接收一个SpEL表达式，把方法的名字当作key值。SpEL的详细语法和其他取值`https://docs.spring.io/spring/docs/5.1.12.RELEASE/spring-framework-reference/integration.html#cache-spel-context`

```java
@Slf4j
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    //    @Cacheable(value = {"category"}, key ="'level1Categorys'")
    @Cacheable(value = {"category"}, key ="#root.method.name")
    @Override
    public List<CategoryEntity> getLevel1Categorys() {
        List<CategoryEntity> categoryEntities = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
        return categoryEntities;
    }

}
```

测试：访问`http://localhost:10001/index.html`,redis中出现key键`category::getLevel1Categorys`,对应的value值为已经序列化为JSON格式，过期时间为3600秒



![image-20230611080356448](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306270616222.png)



###   1.4 注解@CacheEvict

- `@Cacheable` ：读模式使用缓存，将方法的返回结果存入缓存，如果缓存中有数据，就不需要执行方法，缓存为空时才执行方法
- 在写模式下，对数据有修改，使用双写模式或者失效模式
  - ` @CachePut`：支持双写模式的注解。可以帮我们更新缓存，不论缓存为不为空，都会将方法返回的结果放到缓存中
  - `@CacheEvict`: 可以从缓存中删除数据，对应失效模式的使用

- `@CacheEvict` 注解使用：
  - 在方法上标注`@CacheEvict`注解，只要执行此方法，就把缓存中特定的旧数据删除。当执行查询语句时重新更新缓存
  - `@CacheEvict(value = "category",allEntries = true)` : 删除`category`分区下所有的缓存数据

- `@CacheEvict` 注解使用示例：



`CategoryServiceImpl`实现类：

```java
@Slf4j
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 级联更新所有关联的数据
     * @CacheEvict注解 : 失效模式
     */
    // @CacheEvict 只要执行此方法修改了数据库中的三级分类数据，就把缓存中的旧数据删除。当执行查询语句时重新更新缓存
    @CacheEvict(value = "category", key = "'getLevel1Categorys'")
    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());

        //同时修改缓存中的数据
        //redis.del("catalogJSON");等待下次主动查询进行更新
    }

}
```

测试：分别启动`renren-fast`后端项目、`renren-fast`前端项目、`gulimall-product` 、`gulimall-gateway` ，访问`http://localhost:10001/index.html`保证redis中存在key为`category::getLevel1Categorys`的数据，再到前端页面上修改三级分类数据，修改成功后重新查缓存，发现key为`category::getLevel1Categorys`的缓存数据已经被删除



![image-20230612015320938](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306270616121.png)

###  1.5 注解@Caching

- 注解@Caching：组合多种缓存操作，使得执行一个方法后可以执行多种缓存操作
- 注解@Caching使用示例：
  - 我们调用`updateCascade`方法修改了菜单以后，数据库发生了变化，通过`getLevel1Categorys`方法和`getCatalogJson`方法获取到的数据库中的数据都有可能发生变化，之前通过`getLevel1Categorys`方法和`getCatalogJson`方法获取到的老数据已经被保存到缓存中，为了保证缓存数据一致性。就需要将这两个方法添加的缓存删除

```java
@Slf4j
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    /**
     * @Caching注解：同时进行多种缓存操作 
     * 1、指定删除某个分区下的所有数据 @CacheEvict(value = "category",allEntries = true)
     * 2、存储同一类型的数据，都可以指定成同一个分区。分区名默认就是缓存的前缀
     */
    // @CacheEvict 只要执行此方法修改了数据库中的三级分类数据，就把缓存中的旧数据删除。当执行查询语句时重新更新缓存
//    @CacheEvict(value = "category", key = "'getLevel1Categorys'")
    @Caching(evict = {
            @CacheEvict(value = "category", key = "'getLevel1Categorys'"),
            @CacheEvict(value = "category", key = "'getCatalogJson'")
    })
    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
    }

//    @Cacheable(value = {"category"}, key ="'getLevel1Categorys'")
    @Cacheable(value = {"category"}, key = "#root.method.name")
    @Override
    public List<CategoryEntity> getLevel1Categorys() {
        List<CategoryEntity> categoryEntities = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
        return categoryEntities;
    }

    @Cacheable(value = "category",key = "#root.methodName")
    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        // 具体实现省略
    }
}
```

测试: 访问`http://gulimall.com/`,保证上述两个方法在查询数据库中数据的同时将查到的数据更新到缓存中。登录`renren-fast`前端项目，修改三级分类后，重新查询redis,发现刚才存储的两个键值被删除

![image-20230613033940294](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306270617971.png)



###  1.6 spring cache总结

```java
1.整合SpringCache简化缓存开发
     1）、引入依赖
          spring-boot-starter-cache、spring-boot-starter-data-redis
     2）、写配置
          （1）、自动配置了哪些
                  CacheAuroConfiguration会自动导入 RedisCacheConfiguration；
                  RedisCacheConfiguration自动配好了缓存管理器RedisCacheManager
          （2）、我们要配哪些：配置使用那种缓存（此处配置redis作为缓存）
                  spring.cache.type=redis
     3）、开启缓存功能 @EnableCaching
     4）、只需要使用注解就能完成缓存操作
     5）、测试使用缓存，主要使用下面的几个注解来使用缓存：
              @Cacheable: Triggers cache population.：触发将数据保存到缓存的操作
              @CacheEvict: Triggers cache eviction.：触发将数据从缓存删除的操作
              @CachePut: Updates the cache without interfering with the method execution.：不影响方法执行更新缓存
              @Caching: Regroups multiple cache operations to be applied on a method.：组合以上多个操作
              @CacheConfig: Shares some common cache-related settings at class-level.：在类级别共享缓存的相同配置
              
      6）、原理：
              CacheAutoConfiguration ->  RedisCacheConfiguration ->
               自动配置了RedisCacheManager->初始化所有的缓存->每个缓存决定使用什么配置
               ->如果redisCacheConfiguration有就用已有的，没有就用默认配置
               ->想改缓存的配置，只需要给容器中放一个RedisCacheConfiguration即可
               ->就会应用到当前RedisCacheManager管理的所有缓存分区中

2、Spring-Cache的总结
    1）、读模式：
              缓存穿透：查询一个null数据。解决：缓存空数据；ache-null-values=true
              缓存击穿：大量并发进来同时查询一个正好过期的数据。解决：加锁。默认是无加锁的;sync = true（加锁，解决击穿）`    @Cacheable(value = {"category"},key = "#root.method.name",sync = true)` 将sync属性设置为true,让方法变成同步方法，相当于加了本地锁，虽然不是分布式锁，但在一定程度上解决缓存击穿
              缓存雪崩：大量的key同时过期。解决：加随机时间,加上过期时间：spring.cache.redis.time-to-live=3600000
     2）、写模式：（缓存与数据库一致）
                1）、读写加锁
                2）、引入Canal，感知到MySQL的更新去更新数据库
                3）、读多写多，直接去数据库查询就行
    原理：
                  CacheManager(RedisCacheManager)->Cache(RedisCache)->Cache负责缓存的读写
           spring cache中的缓存管理器CacheManager(此处整合了redis,缓存管理器就是RedisCacheManager) 可以帮我们造出很多缓存组件Cache(此处整合了redis,缓存组件就是RedisCache)， 缓存组件Cache负责缓存的读写  

    总结：
               常规数据（读多写少，即时性，一致性要求不高的数据）；完全可以使用Spring-Cache；写模式（只要缓存的数据有过期时间就足够了）
               实时性要求高的特殊数据则需要特殊设计
```



##  2.检索服务

检索服务较完整笔记推荐：`https://blog.csdn.net/qq_40991313/article/details/129825496`

###  2.1 搭建检索服务首页

在`gulimall-search`服务的配置文件中加入`thymeleaf`依赖：

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```

找到资料中的检索页相关html文件，将`index.html`复制到检索服务的`templates`页面下，并改名为`list.html`，接着为在html中添加`thymeleaf`名称空间



![image-20230617060230495](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306270617374.png)



因为要将静态资源都放到`nginx`下，所以要修改`index.html`中的图片和链接路径

![image-20230619001718848](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306270617265.png)



利用xshell将静态文件传到`/mydata/nginx/html/static/search`目录下(静态文件备份在`src/main/resources/nginxStatic`目录下)

![image-20230619011752376](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306270617341.png)



利用`switchHosts`添加域名：`192.168.56.10 search.gulimall.com`



![image-20230619012830324](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306270617230.png)



更改nginx的`gulimall.conf`配置，使域名能够得到正确路由

```sh
cd /mydata/nginx/conf/conf.d
sudo vim gulimall.conf
sudo docker restart nginx
```

![image-20230622180346121](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306270617537.png)

在`gulimall-gateway`网关服务   `application.yml`文件修改如下：

```yaml
spring:
  cloud:
    gateway:
      routes:
## 将nginx过来的请求转发到检索服务
        - id: gulimall_search_route
          uri: lb://gulimall-search
          predicates:
            - Host=search.gulimall.com
```

当前`nginx`转发效果图

![image-20230619021328767](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306270617425.png)

测试：浏览器访问`http://search.gulimall.com/`,正确访问到检索服务的首页

### 2.2 搜索页跳转实现

在`gulimall-search`检索服务中添加热启动依赖“

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
```

在配置文件中关闭`thymeleaf`缓存

```properties
spring.thymeleaf.cache=false
```

1.效果：点击检索服务首页的“谷粒商城首页”或者下方的”谷粒商城“图片，跳转到商品服务首页

![image-20230622200450727](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306270618567.png)

1.实现：更改检索服务首页文件`src/main/resources/templates/list.html`的跳转链接

![image-20230622194449226](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306270618487.png)

2.效果：在商品服务的首页，点击“手机”或者在搜索框中搜索，能够跳转到检索服务的首页

![image-20230622211537026](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306270618343.png)

2.实现：增加跳转逻辑

在`gulimall-search`检索服务中添加`SearchController`实现首页跳转

```java
@Controller
public class SearchController {

    @GetMapping("/list.html")
    public String listPage(){
        return "list";
    }

}
```

2.实现：更改`list.html`中的搜索函数

![image-20230622230528957](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306270618760.png)





