<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1.Spring Cache](#1spring-cache)
  - [1.1 spring cache 概述](#11-spring-cache-%E6%A6%82%E8%BF%B0)
  - [1.2 整合spring cache](#12-%E6%95%B4%E5%90%88spring-cache)
  - [1.3 注解@Cacheable & 自定义缓存配置](#13-%E6%B3%A8%E8%A7%A3cacheable--%E8%87%AA%E5%AE%9A%E4%B9%89%E7%BC%93%E5%AD%98%E9%85%8D%E7%BD%AE)
  - [1.4 注解@CacheEvict](#14-%E6%B3%A8%E8%A7%A3cacheevict)
  - [1.5 注解@Caching](#15-%E6%B3%A8%E8%A7%A3caching)
  - [1.6 spring cache总结](#16-spring-cache%E6%80%BB%E7%BB%93)
- [2.检索服务](#2%E6%A3%80%E7%B4%A2%E6%9C%8D%E5%8A%A1)
  - [2.1 搭建检索服务首页](#21-%E6%90%AD%E5%BB%BA%E6%A3%80%E7%B4%A2%E6%9C%8D%E5%8A%A1%E9%A6%96%E9%A1%B5)
  - [2.2 搜索页跳转实现](#22-%E6%90%9C%E7%B4%A2%E9%A1%B5%E8%B7%B3%E8%BD%AC%E5%AE%9E%E7%8E%B0)
  - [2.3 检索业务分析](#23-%E6%A3%80%E7%B4%A2%E4%B8%9A%E5%8A%A1%E5%88%86%E6%9E%90)
      - [2.3.1 封装商品检索条件](#231-%E5%B0%81%E8%A3%85%E5%95%86%E5%93%81%E6%A3%80%E7%B4%A2%E6%9D%A1%E4%BB%B6)
      - [2.3.2 封装检索返回的结果](#232-%E5%B0%81%E8%A3%85%E6%A3%80%E7%B4%A2%E8%BF%94%E5%9B%9E%E7%9A%84%E7%BB%93%E6%9E%9C)
  - [2.4 检索DSL语句构建](#24-%E6%A3%80%E7%B4%A2dsl%E8%AF%AD%E5%8F%A5%E6%9E%84%E5%BB%BA)
      - [2.4.1 数据准备](#241-%E6%95%B0%E6%8D%AE%E5%87%86%E5%A4%87)
      - [2.4.2 关键字检索](#242-%E5%85%B3%E9%94%AE%E5%AD%97%E6%A3%80%E7%B4%A2)
      - [2.4.3 通过三级分类检索](#243-%E9%80%9A%E8%BF%87%E4%B8%89%E7%BA%A7%E5%88%86%E7%B1%BB%E6%A3%80%E7%B4%A2)
      - [2.4.4 通过品牌进行检索](#244-%E9%80%9A%E8%BF%87%E5%93%81%E7%89%8C%E8%BF%9B%E8%A1%8C%E6%A3%80%E7%B4%A2)
      - [2.4.5 通过属性进行检索](#245-%E9%80%9A%E8%BF%87%E5%B1%9E%E6%80%A7%E8%BF%9B%E8%A1%8C%E6%A3%80%E7%B4%A2)
      - [2.4.6 对检索进行排序](#246-%E5%AF%B9%E6%A3%80%E7%B4%A2%E8%BF%9B%E8%A1%8C%E6%8E%92%E5%BA%8F)
      - [2.4.7 查询有库存的记录](#247-%E6%9F%A5%E8%AF%A2%E6%9C%89%E5%BA%93%E5%AD%98%E7%9A%84%E8%AE%B0%E5%BD%95)
      - [2.4.8 按照价格区间进行查询](#248-%E6%8C%89%E7%85%A7%E4%BB%B7%E6%A0%BC%E5%8C%BA%E9%97%B4%E8%BF%9B%E8%A1%8C%E6%9F%A5%E8%AF%A2)
      - [2.4.9 查询指定页码的数据](#249-%E6%9F%A5%E8%AF%A2%E6%8C%87%E5%AE%9A%E9%A1%B5%E7%A0%81%E7%9A%84%E6%95%B0%E6%8D%AE)
      - [2.4.10 对查询结果进行高亮显示](#2410-%E5%AF%B9%E6%9F%A5%E8%AF%A2%E7%BB%93%E6%9E%9C%E8%BF%9B%E8%A1%8C%E9%AB%98%E4%BA%AE%E6%98%BE%E7%A4%BA)
      - [2.4 11 聚合查询](#24-11-%E8%81%9A%E5%90%88%E6%9F%A5%E8%AF%A2)
      - [2.4.12 字聚合](#2412-%E5%AD%97%E8%81%9A%E5%90%88)
      - [2.4.13 嵌入式类型的聚合查询](#2413-%E5%B5%8C%E5%85%A5%E5%BC%8F%E7%B1%BB%E5%9E%8B%E7%9A%84%E8%81%9A%E5%90%88%E6%9F%A5%E8%AF%A2)
      - [2.4 14 完整的DSL语句](#24-14-%E5%AE%8C%E6%95%B4%E7%9A%84dsl%E8%AF%AD%E5%8F%A5)
  - [2.5 检索服务实现](#25-%E6%A3%80%E7%B4%A2%E6%9C%8D%E5%8A%A1%E5%AE%9E%E7%8E%B0)
      - [2.5.1 SearchController\MallSearchService\MallSearchServiceImpl](#251-searchcontroller%5Cmallsearchservice%5Cmallsearchserviceimpl)
      - [2.5.2  构建查询的DSL语句](#252--%E6%9E%84%E5%BB%BA%E6%9F%A5%E8%AF%A2%E7%9A%84dsl%E8%AF%AD%E5%8F%A5)
      - [2.5.3 检索结果的封装](#253-%E6%A3%80%E7%B4%A2%E7%BB%93%E6%9E%9C%E7%9A%84%E5%B0%81%E8%A3%85)
      - [2.5.4 页面数据渲染](#254-%E9%A1%B5%E9%9D%A2%E6%95%B0%E6%8D%AE%E6%B8%B2%E6%9F%93)
        - [2.5.4.1 显示商品信息](#2541-%E6%98%BE%E7%A4%BA%E5%95%86%E5%93%81%E4%BF%A1%E6%81%AF)
        - [2.5.4.2 显示品牌、分类和属性筛选条件](#2542-%E6%98%BE%E7%A4%BA%E5%93%81%E7%89%8C%E5%88%86%E7%B1%BB%E5%92%8C%E5%B1%9E%E6%80%A7%E7%AD%9B%E9%80%89%E6%9D%A1%E4%BB%B6)
        - [2.5.4.3 页面关键字搜索效果渲染](#2543-%E9%A1%B5%E9%9D%A2%E5%85%B3%E9%94%AE%E5%AD%97%E6%90%9C%E7%B4%A2%E6%95%88%E6%9E%9C%E6%B8%B2%E6%9F%93)
        - [2.5.4.4 页面分页效果渲染](#2544-%E9%A1%B5%E9%9D%A2%E5%88%86%E9%A1%B5%E6%95%88%E6%9E%9C%E6%B8%B2%E6%9F%93)
        - [2.5.4.5 页面排序功能](#2545-%E9%A1%B5%E9%9D%A2%E6%8E%92%E5%BA%8F%E5%8A%9F%E8%83%BD)
        - [2.5.4.6 页面价格区间搜索](#2546-%E9%A1%B5%E9%9D%A2%E4%BB%B7%E6%A0%BC%E5%8C%BA%E9%97%B4%E6%90%9C%E7%B4%A2)
        - [2.5.4.7 按照有货无货进行检索](#2547-%E6%8C%89%E7%85%A7%E6%9C%89%E8%B4%A7%E6%97%A0%E8%B4%A7%E8%BF%9B%E8%A1%8C%E6%A3%80%E7%B4%A2)
      - [2.5.5 构建面包屑导航功能](#255-%E6%9E%84%E5%BB%BA%E9%9D%A2%E5%8C%85%E5%B1%91%E5%AF%BC%E8%88%AA%E5%8A%9F%E8%83%BD)
        - [2.5.5.1 面包屑导航](#2551-%E9%9D%A2%E5%8C%85%E5%B1%91%E5%AF%BC%E8%88%AA)
        - [2.5.5.2 面包屑导航参数 & 商品服务调用](#2552-%E9%9D%A2%E5%8C%85%E5%B1%91%E5%AF%BC%E8%88%AA%E5%8F%82%E6%95%B0--%E5%95%86%E5%93%81%E6%9C%8D%E5%8A%A1%E8%B0%83%E7%94%A8)
        - [2.5.5.3 构建面包屑导航实现](#2553-%E6%9E%84%E5%BB%BA%E9%9D%A2%E5%8C%85%E5%B1%91%E5%AF%BC%E8%88%AA%E5%AE%9E%E7%8E%B0)
        - [2.5.5.4 面包屑导航页面渲染](#2554-%E9%9D%A2%E5%8C%85%E5%B1%91%E5%AF%BC%E8%88%AA%E9%A1%B5%E9%9D%A2%E6%B8%B2%E6%9F%93)
- [3.异步&线程池](#3%E5%BC%82%E6%AD%A5%E7%BA%BF%E7%A8%8B%E6%B1%A0)
  - [3.1初始化线程的4种方式](#31%E5%88%9D%E5%A7%8B%E5%8C%96%E7%BA%BF%E7%A8%8B%E7%9A%844%E7%A7%8D%E6%96%B9%E5%BC%8F)
      - [3.1.1  继承 Thread](#311--%E7%BB%A7%E6%89%BF-thread)
      - [3.1.2 实现 Runnable接口](#312-%E5%AE%9E%E7%8E%B0-runnable%E6%8E%A5%E5%8F%A3)
      - [3.1.3 实现Callable接口+ FutureTask](#313-%E5%AE%9E%E7%8E%B0callable%E6%8E%A5%E5%8F%A3-futuretask)
      - [3.1.4 线程池](#314-%E7%BA%BF%E7%A8%8B%E6%B1%A0)
  - [3.2 线程池的七大参数](#32-%E7%BA%BF%E7%A8%8B%E6%B1%A0%E7%9A%84%E4%B8%83%E5%A4%A7%E5%8F%82%E6%95%B0)
      - [3.2.1 线程池的创建](#321-%E7%BA%BF%E7%A8%8B%E6%B1%A0%E7%9A%84%E5%88%9B%E5%BB%BA)
      - [3.2.2 线程池的七大参数](#322-%E7%BA%BF%E7%A8%8B%E6%B1%A0%E7%9A%84%E4%B8%83%E5%A4%A7%E5%8F%82%E6%95%B0)
      - [3.2.3 线程池工作顺序](#323-%E7%BA%BF%E7%A8%8B%E6%B1%A0%E5%B7%A5%E4%BD%9C%E9%A1%BA%E5%BA%8F)
  - [3.3 Executors方式常见的4种线程池](#33-executors%E6%96%B9%E5%BC%8F%E5%B8%B8%E8%A7%81%E7%9A%844%E7%A7%8D%E7%BA%BF%E7%A8%8B%E6%B1%A0)
  - [3.4 开发中为什么使用线程](#34-%E5%BC%80%E5%8F%91%E4%B8%AD%E4%B8%BA%E4%BB%80%E4%B9%88%E4%BD%BF%E7%94%A8%E7%BA%BF%E7%A8%8B)
  - [3.5 CompletableFuture异步](#35-completablefuture%E5%BC%82%E6%AD%A5)
      - [3.5.1  CompletableFuture+简介](#351--completablefuture%E7%AE%80%E4%BB%8B)
      - [3.5.2 创建异步对象](#352-%E5%88%9B%E5%BB%BA%E5%BC%82%E6%AD%A5%E5%AF%B9%E8%B1%A1)
      - [3.5.3 计算完成时回调方法](#353-%E8%AE%A1%E7%AE%97%E5%AE%8C%E6%88%90%E6%97%B6%E5%9B%9E%E8%B0%83%E6%96%B9%E6%B3%95)
      - [3.5.4 handle方法](#354-handle%E6%96%B9%E6%B3%95)
      - [3.5.5 线程串行化方法](#355-%E7%BA%BF%E7%A8%8B%E4%B8%B2%E8%A1%8C%E5%8C%96%E6%96%B9%E6%B3%95)
      - [3.5.6  两任务组合–都要完成](#356--%E4%B8%A4%E4%BB%BB%E5%8A%A1%E7%BB%84%E5%90%88%E9%83%BD%E8%A6%81%E5%AE%8C%E6%88%90)
      - [3.5.7 两任务组合-一个完成](#357-%E4%B8%A4%E4%BB%BB%E5%8A%A1%E7%BB%84%E5%90%88-%E4%B8%80%E4%B8%AA%E5%AE%8C%E6%88%90)
      - [3.5.8 多任务组合](#358-%E5%A4%9A%E4%BB%BB%E5%8A%A1%E7%BB%84%E5%90%88)
- [4.商品详情](#4%E5%95%86%E5%93%81%E8%AF%A6%E6%83%85)
  - [4.1 搭建页面跳转环境](#41-%E6%90%AD%E5%BB%BA%E9%A1%B5%E9%9D%A2%E8%B7%B3%E8%BD%AC%E7%8E%AF%E5%A2%83)
  - [4.2 封装商品详情页数据](#42-%E5%B0%81%E8%A3%85%E5%95%86%E5%93%81%E8%AF%A6%E6%83%85%E9%A1%B5%E6%95%B0%E6%8D%AE)
  - [4.3 商品详情页数据获取](#43-%E5%95%86%E5%93%81%E8%AF%A6%E6%83%85%E9%A1%B5%E6%95%B0%E6%8D%AE%E8%8E%B7%E5%8F%96)
      - [4.3.1 整体思路 & 代码框架](#431-%E6%95%B4%E4%BD%93%E6%80%9D%E8%B7%AF--%E4%BB%A3%E7%A0%81%E6%A1%86%E6%9E%B6)
      - [4.3.2 获取sku的图片信息](#432-%E8%8E%B7%E5%8F%96sku%E7%9A%84%E5%9B%BE%E7%89%87%E4%BF%A1%E6%81%AF)
      - [4.3.3 获取spu的销售属性组合](#433-%E8%8E%B7%E5%8F%96spu%E7%9A%84%E9%94%80%E5%94%AE%E5%B1%9E%E6%80%A7%E7%BB%84%E5%90%88)
      - [4.3.4 获取当前spu的规格参数信息](#434-%E8%8E%B7%E5%8F%96%E5%BD%93%E5%89%8Dspu%E7%9A%84%E8%A7%84%E6%A0%BC%E5%8F%82%E6%95%B0%E4%BF%A1%E6%81%AF)
  - [4.4 详情页基本信息渲染](#44-%E8%AF%A6%E6%83%85%E9%A1%B5%E5%9F%BA%E6%9C%AC%E4%BF%A1%E6%81%AF%E6%B8%B2%E6%9F%93)
  - [4.5 详情页销售属性渲染](#45-%E8%AF%A6%E6%83%85%E9%A1%B5%E9%94%80%E5%94%AE%E5%B1%9E%E6%80%A7%E6%B8%B2%E6%9F%93)
  - [4.6 异步编排优化](#46-%E5%BC%82%E6%AD%A5%E7%BC%96%E6%8E%92%E4%BC%98%E5%8C%96)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

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
@EnableFeignClients(basePackages = "com.atguigu.gulimall.product.feign")
@EnableDiscoveryClient
@MapperScan("com.atguigu.gulimall.product.dao")
@SpringBootApplication
public class gulimallProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(gulimallProductApplication.class, args);
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
    @GetMapping({"/", "/list.html"})
    public String listPage() {
        return "list";
    }
}
```

2.实现：更改`list.html`中的搜索函数

![image-20230622230528957](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306270618760.png)

###  2.3 检索业务分析

#####  2.3.1 封装商品检索条件

商品检索的搜索条件: 在`gulimall-product 商品服务`页面上，点击三级分类、在检索框搜索关键字、选择筛选条件进行查询，这三种操作都可以跳转到`gulimall-search检索服务`进行检索。所以我们要把页面传过来的所有搜索条件封装成一个对象，通过这个对象的具体参数信息来决定查询哪些信息

1.通过三级选择具体分类进入商品检索服务进行查询：

- 需要三级分类id来进行检索，检索条件为`三级分类id` ：`catalog3Id`

![image-20230705021736981](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307050217008.png)

2.输入检索关键字进行检索：

- 检索条件为`页面传递过来的全文匹配关键字 `:  `keyword`

![image-20230705021708343](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307050217404.png)

3.勾选筛选条件来进行检索，涉及到的检索条件有

- 排序条件`sort`，有三种排序条件：
  - 按销量: `sort=saleCount_asc/desc`
  - 按价格:`sort=skuPrice_asc/desc`
  - 按热度分:`sort=hotScore_asc/desc`

- 是否只显示有货`hasStock`:   0（无库存）1（有库存）

- 价格区间查询`skuPrice`: `1_500` 、 `_500` 、`500_`
- 按照品牌进行查询,可以多选：`List<Long> brandId`
- 按照属性进行筛选,可以多选:`List<String> attrs`

![image-20230705021757609](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307050217661.png)

4.其他查询条件：

- 页码：`pageNum`
- 原生的所有查询条件:`String _queryString`

5.检索条件分析汇总

- 用于全文检索的参数: `skuTitle`、`keyword`
- 用于检索排序的参数`sort`：`sort`的取值有三种: `saleCount(销量)`、`hotScore (热度分)`、`skuPrice (价格)`
- 用于检索过滤的参数: `hasStock`、`skuPrice区间`、`brandld`、`catalog3ld`、`attrs`
- 用于检索聚合: `attrs`

6.所有搜索条件封装成`SearchParam`对象:

```java
/**
 * 封装页面所有可能传递过来的查询条件
 * catalog3Id=225&keyword=小米&sort=saleCount_asc&hasStock=0/1&brandId=1&brandId=2
 * &attrs=1_5寸:8寸&attrs=2_16G:8G
 */
@Data
public class SearchParam {
    /**
     * 页面传递过来的全文匹配关键字  v
     */
    private String keyword;
    /**
     * 三级分类id   v
     */
    private Long catalog3Id;
    /**
     * 排序条件 v
     * 排序条件的几种可能：
     * sort=saleCount_asc/desc（按销量）、
     * sort=skuPrice_asc/desc（按价格）、
     * sort=hotScore_asc/desc（按热度分）
     */
    private String sort;

    /**
     * 是否只显示有货 0（无库存）1（有库存） hasStock=0/1
     */
    private Integer hasStock;
    /**
     * 价格区间查询  skuPrice=1_500/_500/500_
     */
    private String skuPrice;
    /**
     * 按照品牌进行查询，可以多选  brandId=1
     */
    private List<Long> brandId;
    /**
     * 按照属性进行筛选 可以多选  attrs=2_5存:6寸
     */
    private List<String> attrs;
    /**
     * 页码
     */
    private Integer pageNum = 1;
    /**
     * 原生的所有查询条件
     */
    private String _queryString;
}
```

#####  2.3.2 封装检索返回的结果

将返回给页面的所有信息封装为`SearchResult`对象

```java
/**
 * 将返回给页面的所有信息封装为对象
 */
@Data
public class SearchResult {
    /**
     * 查询到的所有商品信息
     */
    private List<SkuEsModel> products;
    /**
     * 当前页码 (分页信息)
     */
    private Integer pageNum;
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 总页码
     */
    private Integer totalPages;
    private List<Integer> pageNavs;
    /**
     * 当前查询到的结果，所有涉及到的品牌
     */
    private List<BrandVo> brands;
    /**
     * 当前查询到的结果，所有涉及到的所有分类
     */
    private List<CatalogVo> catalogs;
    /**
     * 当前查询到的结果，所有涉及到的所有属性
     */
    private List<AttrVo> attrs;
    /**
     * 面包屑导航数据
     */
    private List<NavVo> navs = new ArrayList<>();
    private List<Long> attrIds = new ArrayList<>();
    @Data
    public static class NavVo {
        private String navName;
        private String navValue;
        private String link;
    }
    @Data
    public static class BrandVo {
        private Long brandId;
        private String brandName;
        private String brandImg;
    }
    @Data
    public static class CatalogVo {
        private Long catalogId;
        private String catalogName;
    }
    @Data
    public static class AttrVo {
        private Long attrId;
        private String attrName;
        private List<String> attrValue;
    }
}
```

###  2.4 检索DSL语句构建

#####  2.4.1 数据准备

在课程资料中找到最终的sql语句文件,执行`gulimall_pms.sql`将商品服务的数据重新生成，然后在后台将点击`商品上架`,就可以把数据导入到`product`索引中，导入后在`kibana`中可以查到26条数据

```json
# 查到26个商品
GET product/_search
```

#####  2.4.2 关键字检索

模拟在搜索框中搜索"华为"来进行关键字检索

```json
# 模拟在搜索框中搜索"华为"
GET product/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "skuTitle": "华为"
          }
        }
      ]
    }
  }
}
```

#####  2.4.3 通过三级分类检索

模拟通过三级分类来进行检索

```json
# 模拟通过三级分类来进行检索
# 约定：全文匹配条件都写在must的match中，非全文匹配条件都写在filter中
# 文匹配写在must里的term中也是可以的，但是把非全文匹配写在和must同级别的filter中的话，非全文匹配字段就不需要参与评分，查询效率就会更高
GET product/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "skuTitle": "华为"
          }
        }
      ],
      "filter": [
        {
          "term": {
            "catalogId": "225"
          }
        }
      ]
    }
  }
}
```

#####  2.4.4 通过品牌进行检索

通过品牌进行检索，可以选择多个品牌

```json
# 选择多个品牌进行检索
GET product/_search
{
  "query": {
    "bool": {
      "filter": {
        "terms": {
          "brandId": [
            "1",
            "2",
            "9"
          ]
        }
      }
    }
  }
}
```

#####  2.4.5 通过属性进行检索

如果是嵌入式的属性,查询，聚合，分析都应该用嵌入式的：属性attr是数组类型，我们将其声明成nested,嵌入式类型在查询的时候就需要遵循嵌入式的查询规则

错误的检索方式

```json
# 错误的检索方式
# 依据属性进行检索(查不到数据，原因是属性attr是数组类型，我们将其声明成nested,嵌入式类型在查询的时候就需要遵循嵌入式的查询规则)
GET product/_search
{
  "query": {
    "bool": {
      "filter": {
        "term": {
          "attrs.attrId": "15"
        }
      }
    }
  }
}
```

正确的检索方式

```json
# 正确的检索方式
# 依据属性进行检索(属性attr是数组类型，我们将其声明成nested,嵌入式类型的查询就需要遵循嵌入式的查询规则)
GET product/_search
{
  "query": {
    "bool": {
      "filter": {
        "nested": {
          "path": "attrs",
          "query": {
            "bool": {
              "must": [
                {
                  "term": {
                    "attrs.attrId": {
                      "value": "15"
                    }
                  }
                }
              ]
            }
          }
        }
      }
    }
  }
}
```

依据`属性id`和`属性名`进行检索

```json
# 依据属性进行检索
GET product/_search
{
  "query": {
    "bool": {
      "filter": {
        "nested": {
          "path": "attrs",
          "query": {
            "bool": {
              "must": [
                {
                  "term": {
                    "attrs.attrId": {
                      "value": "15"
                    }
                  }
                },
                {
                  "terms": {
                    "attrs.attrValue": [
                      "海思（Hisilicon）",
                      "HUAWEI Kirin 970"
                    ]
                  }
                }
              ]
            }
          }
        }
      }
    }
  }
}
```

#####  2.4.6 对检索进行排序

依据价格进行排序`skuPrice`

```json
# 模拟对检索进行排序
# 依据价格进行排序skuPrice
GET product/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "skuTitle": "华为"
          }
        }
      ]
    }
  },
  "sort": [
    {
      "skuPrice": {
        "order": "desc"
      }
    }
  ]
}
```

#####  2.4.7 查询有库存的记录

```json
# 查询有库存的记录
GET product/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "skuTitle": "华为"
          }
        }
      ],
      "filter": [
        {
          "term": {
            "hasStock": {
              "value": "true"
            }
          }
        }
      ]
    }
  }
}
```

##### 2.4.8 按照价格区间进行查询

```json
# 价格区间的查询(skuPrice)
GET product/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "skuTitle": "华为"
          }
        }
      ],
      "filter": [
        {
          "range": {
            "skuPrice": {
              "gte": 0,
              "lte": 6000
            }
          }
        }
      ]
    }
  }
}
```

#####  2.4.9 查询指定页码的数据

```json
# 按照页码进行查询 from:起始页 size:页数
GET product/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "skuTitle": "华为"
          }
        }
      ],
      "filter": [
        {
          "range": {
            "skuPrice": {
              "gte": 0,
              "lte": 5800
            }
          }
        }
      ]
    }
  },
  "from":0,
  "size":2
}
```

#####  2.4.10 对查询结果进行高亮显示

```json
# 高亮显示
# pre_tags   前置标签
# post_tags  后置标签
# fields     指定要高亮的字段
GET product/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "skuTitle": "华为"
          }
        }
      ]
    }
  },
  "highlight": {
    "fields": {
      "skuTitle": {}
    },
    "pre_tags": "<b style='color:red'>",
    "post_tags": "</b>"
  }
}
```

#####  2.4 11 聚合查询

```json
# aggs：      对结果进行聚合
# brand_agg： 聚合条件的别名,对品牌进行聚合
# field:      聚合的字段
GET product/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "skuTitle": "华为"
          }
        }
      ]
    }
  },
  "aggs": {
    "brand_agg": {
      "terms": {
        "field": "brandId",
        "size": 10
      }
    },
    "catalog_agg": {
      "terms": {
        "field": "catalogId",
        "size": 10
      }
    }
  }
}
```

#####  2.4.12 字聚合

子聚合：对聚合后的结果做进一步聚合

```json
# 字聚合：对聚合后的结果做进一步聚合
# 此聚合查询报错
# 原因：product的映射中将attrName的做了如下定义： 
#        "attrName" : {
#        "type" : "keyword",
#        "index" : false,
#        "doc_values" : false
#        }
# "index" : false, "doc_values" : false 导致字段不能被搜索、不能用来聚合
# 解决：所以要重新修改映射
GET product/_search
{
  "query": {
    "match_all": {}
  },
  "aggs": {
    "brand_agg": {
      "terms": {
        "field": "brandId",
        "size": 10
      },
      "aggs": {
        "brand_name_agg": {
          "terms": {
            "field": "brandName",
            "size": 10
          }
        },
        "brand_img_agg": {
          "terms": {
            "field": "brandImg",
            "size": 10
          }
        }
      }
    }
  }
}
```

新建索引并指定映射

```json
# 新建索引并指定映射
PUT gulimall_product
{
  "mappings": {
    "properties": {
      "attrs": {
        "type": "nested",
        "properties": {
          "attrId": {
            "type": "long"
          },
          "attrName": {
            "type": "keyword"
          },
          "attrValue": {
            "type": "keyword"
          }
        }
      },
      "brandId": {
        "type": "long"
      },
      "brandImg": {
        "type": "keyword"
      },
      "brandName": {
        "type": "keyword"
      },
      "catalogId": {
        "type": "long"
      },
      "catalogName": {
        "type": "keyword"
      },
      "hasStock": {
        "type": "boolean"
      },
      "hotScore": {
        "type": "long"
      },
      "saleCount": {
        "type": "long"
      },
      "skuId": {
        "type": "long"
      },
      "skuImg": {
        "type": "keyword"
      },
      "skuPrice": {
        "type": "keyword"
      },
      "skuTitle": {
        "type": "text",
        "analyzer": "ik_smart"
      },
      "spuId": {
        "type": "keyword"
      }
    }
  }
}
```

查询映射

```
# 查询映射
GET gulimall_product
```

数据迁移

```json
# 数据迁移
POST _reindex
{
  "source": {
    "index": "product"
  },
  "dest": {
    "index": "gulimall_product"
  }
}
```

查询数据

```
# 查询数据
GET gulimall_product/_search
```

#####  2.4.13 嵌入式类型的聚合查询

```json
# 如果是嵌入式的属性,查询，聚合，分析都应该用嵌入式的
# 嵌入式的聚合
GET gulimall_product/_search
{
  "query": {
    "match_all": {}
  },
  "aggs": {
    "attr_agg": {
      "nested": {
        "path": "attrs"
      },
      "aggs": {
        "attr_id_agg": {
          "terms": {
            "field": "attrs.attrId",
            "size": 10
          },
          "aggs": {
            "attr_name_agg": {
              "terms": {
                "field": "attrs.attrName",
                "size": 10
              }
            },
            "attr_value_agg":{
              "terms": {
                "field": "attrs.attrValue",
                "size": 10
              }
            }
          }
        }
      }
    }
  }
}
```

##### 2.4 14 完整的DSL语句

```json
# 模糊匹配，过滤（按照属性，分类，品牌，价格区间，库存），排序，分页，高亮
# 完整的检索条件
GET gulimall_product/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "skuTitle": "华为"
          }
        }
      ],
      "filter": [
        {
          "term": {
            "catalogId": "225"
          }
        },
        {
          "terms": {
            "brandId": [
              "1",
              "2",
              "9"
            ]
          }
        },
        {
          "nested": {
            "path": "attrs",
            "query": {
              "bool": {
                "must": [
                  {
                    "term": {
                      "attrs.attrId": {
                        "value": "15"
                      }
                    }
                  },
                  {
                    "terms": {
                      "attrs.attrValue": [
                        "海思（Hisilicon）",
                        "以官网信息为准"
                      ]
                    }
                  }
                ]
              }
            }
          }
        },
        {
          "term": {
            "hasStock": {
              "value": "true"
            }
          }
        },
        {
          "range": {
            "skuPrice": {
              "gte": 0,
              "lte": 6000
            }
          }
        }
      ]
    }
  },
  "sort": [
    {
      "skuPrice": {
        "order": "desc"
      }
    }
  ],
  "from": 0,
  "size": 1,
  "highlight": {
    "fields": {
      "skuTitle": {}
    },
    "pre_tags": "<b style='color:red'>",
    "post_tags": "</b>"
  },
  "aggs": {
    "brand_agg": {
      "terms": {
        "field": "brandId",
        "size": 10
      },
      "aggs": {
        "brand_name_agg": {
          "terms": {
            "field": "brandName",
            "size": 10
          }
        },
        "brand_img_agg": {
          "terms": {
            "field": "brandImg",
            "size": 10
          }
        }
      }
    },
    "catalog_agg": {
      "terms": {
        "field": "catalogId",
        "size": 10
      },
      "aggs": {
        "catalog_name_agg": {
          "terms": {
            "field": "catalogName",
            "size": 10
          }
        }
      }
    },
    "attr_agg": {
      "nested": {
        "path": "attrs"
      },
      "aggs": {
        "attr_id_agg": {
          "terms": {
            "field": "attrs.attrId",
            "size": 10
          },
          "aggs": {
            "attr_name_agg": {
              "terms": {
                "field": "attrs.attrName",
                "size": 1
              }
            },
            "attr_value_agg": {
              "terms": {
                "field": "attrs.attrValue",
                "size": 10
              }
            }
          }
        }
      }
    }
  }
}
```



###  2.5 检索服务实现

#####  2.5.1 SearchController\MallSearchService\MallSearchServiceImpl

`SearchController`:接收用户传来的检索条件，依据检索条件进行检索，将检索到的结果传到页面，让页面进行渲染展示

```java
@Controller
public class SearchController {
    @Autowired
    private MallSearchService mallSearchService;
    /**
     * param: 自动将页面提交过来的所有请求查询参数封装成指定的对象
     * model : 因为所有查到的数据是要返回给页面，所以参数中加一个Model
     */
    @GetMapping({"/", "/list.html"})
    public String listPage(SearchParam param, Model model, HttpServletRequest request) {
        param.set_queryString(request.getQueryString());
        //1、根据传递来的页面的查询参数，去es中检索商品
        SearchResult result = mallSearchService.search(param);
        // 将检索到的结果传到页面进行渲染
        model.addAttribute("result", result);
        return "list";
    }
}
```

`MallSearchService`: 向`elasticsearch`发起检索请求，返回检索的结果, 检索结果中包含页面需要的所有信息

```java
public interface MallSearchService {
    /**
     * @param param  检索的所有参数
     * @return 返回检索的结果,里面包含页面需要的所有信息
     */
    SearchResult search(SearchParam param);
}
```

`MallSearchServiceImpl`:根据前端页面传来的请求参数，动态构建出查询需要的DSL语句，向`elasticsearch`发起请求，将响应数据封装成具体的对象实例

```java
@Service
public class MallSearchServiceImpl implements MallSearchService {
    @Autowired
    private RestHighLevelClient client;
    //去es进行检索
    @Override
    public SearchResult search(SearchParam param) {
        //1、动态构建出查询需要的DSL语句
        SearchResult result = null;
        //1、准备检索请求
        SearchRequest searchRequest = buildSearchRequrest(param);
        try {
            //2、执行检索请求
            SearchResponse response = client.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);
            //3、分析响应数据并封装成我们需要的格式
            result = buildSearchResult(response, param);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 准备检索请求
     * 模糊匹配，过滤（按照属性，分类，品牌，价格区间，库存），排序，分页，高亮，聚合分析
     */
    private SearchRequest buildSearchRequrest(SearchParam param) {
        return null;
    }
    /**
     * 构建结果数据
     */
    private SearchResult buildSearchResult(SearchResponse response, SearchParam param) {
        return null;
    }
}
```

#####  2.5.2  构建查询的DSL语句

`buildSearchRequrest`：构建DSL语句，向`es`发生查询请求，获取查询结果

- 模糊匹配，过滤（按照属性，分类，品牌，价格区间，库存），排序，分页，高亮，聚合分析
- `buildSearchRequrest`实现：

```java

    /**
     * 准备检索请求
     * 模糊匹配，过滤（按照属性，分类，品牌，价格区间，库存），排序，分页，高亮，聚合分析
     */
    private SearchRequest buildSearchRequrest(SearchParam param) {
        // SearchSourceBuilder是用来构建DSL语句的
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        /**
         * 查询条件: 模糊匹配，过滤（按照属性，分类，品牌，价格区间，库存）
         */
        // 1、构建Bool-query
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        // 1.1 must - 模糊匹配
        if (!StringUtils.isEmpty(param.getKeyword())) {
            boolQuery.must(QueryBuilders.matchQuery("skuTitle", param.getKeyword()));
        }
        // 1.2 bool - filter - 按照三级分类id查询
        if (param.getCatalog3Id() != null) {
            boolQuery.filter(QueryBuilders.termQuery("catalogId", param.getCatalog3Id()));
        }
        // 1.2 bool - filter - 按照品牌id查询
        if (param.getBrandId() != null && param.getBrandId().size() > 0) {
            boolQuery.filter(QueryBuilders.termQuery("brandId", param.getBrandId()));
        }

        // 1.3 bool - filter - 按照是否有库存进行查询
        boolQuery.filter(QueryBuilders.termQuery("hasStock", param.getHasStock() == 1));

        // 1.4 bool - filter - 按照所有指定的属性进行查询
        if (param.getAttrs() != null && param.getAttrs().size() > 0) {
            // attrs=1_5寸:8寸&attrs=2_16G:8G
            for (String attrStr : param.getAttrs()) {
                BoolQueryBuilder nestedBoolQuery = QueryBuilders.boolQuery();
                // attrs=1_5寸:8寸
                String[] s = attrStr.split("_");
                String attrId = s[0]; // 检索的属性id
                String[] attrValues = s[1].split(":"); // 检索的属性值
                nestedBoolQuery.must(QueryBuilders.termQuery("attrs.attrId", attrId));
                nestedBoolQuery.must(QueryBuilders.termQuery("attrs.attrValue", attrValues));
                // 每一个都必须生成一个nested查询
                NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("attrs", nestedBoolQuery, ScoreMode.None);
                boolQuery.filter(nestedQuery);
            }
        }

        // 1.5 bool - filter - 按照价格区间进行查询
        if (!StringUtils.isEmpty(param.getSkuPrice())) {
            //1_500/_500/500_
            /**
             * "range": {
             *             "skuPrice": {
             *               "gte": 0,
             *               "lte": 6000
             *             }
             *           }
             */
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("skuPrice");
            String[] arrs = param.getSkuPrice().split("_");
            if (arrs.length == 2) {
                // 区间
                rangeQuery.gte(arrs[0]).lte(arrs[1]);
            } else if (arrs.length == 1) {
                if (param.getSkuPrice().startsWith("_")) {
                    rangeQuery.lte(arrs[0]);
                }
                if (param.getSkuPrice().endsWith("_")) {
                    rangeQuery.gte(arrs[0]);
                }
            }
            boolQuery.filter(rangeQuery);
        }
        // 把以前的所有条件都拿来进行封装
        sourceBuilder.query(boolQuery);

        /**
         * 排序，分页，高亮
         */
        // 2.1 排序
        if (!StringUtils.isEmpty(param.getSort())) {
            String sort = param.getSort();
            // sort=hotScore_asc/desc
            String[] s = sort.split("_");
            SortOrder order = s[1].equalsIgnoreCase("asc") ? SortOrder.ASC : SortOrder.DESC;
            sourceBuilder.sort(s[0], order);
        }
        // 2.2 分页
        //  pageSize=5 pageNum:1  from:0  size:5  [0,1,2,3,4]
        //  pageNum:2  from:5   size:5
        //  from = (pageNum-1)*size
        sourceBuilder.from((param.getPageNum() - 1) * EsConstant.PRODUCT_PAGESIZE);
        sourceBuilder.size(EsConstant.PRODUCT_PAGESIZE);

        // 2.3 高亮（对查询到的检索关键字进行高亮）
        if (!StringUtils.isEmpty(param.getKeyword())) {
            HighlightBuilder builder = new HighlightBuilder();
            builder.field("skuTitle");
            builder.preTags("<b style='color:red'>");
            builder.postTags("</b>");
            sourceBuilder.highlighter(builder);
        }

        /**
         * 聚合分析
         */
        //1、品牌聚合
        TermsAggregationBuilder brand_agg = AggregationBuilders.terms("brand_agg");
        brand_agg.field("brandId").size(50);
        //品牌聚合的子聚合
        brand_agg.subAggregation(AggregationBuilders.terms("brand_name_agg").field("brandName").size(1));
        brand_agg.subAggregation(AggregationBuilders.terms("brand_img_agg").field("brandImg").size(1));
        //TODO 1、聚合brand
        sourceBuilder.aggregation(brand_agg);
        //2、分类聚合 catalog_agg
        TermsAggregationBuilder catalog_agg = AggregationBuilders.terms("catalog_agg").field("catalogId").size(20);
        catalog_agg.subAggregation(AggregationBuilders.terms("catalog_name_agg").field("catalogName").size(1));
        //TODO 2、聚合catalog
        sourceBuilder.aggregation(catalog_agg);
        //3、属性聚合 attr_agg
        NestedAggregationBuilder attr_agg = AggregationBuilders.nested("attr_agg", "attrs");

        //聚合出当前所有的attrId
        TermsAggregationBuilder attr_id_agg = AggregationBuilders.terms("attr_id_agg").field("attrs.attrId");
        //聚合分析出当前attr_id对应的名字
        attr_id_agg.subAggregation(AggregationBuilders.terms("attr_name_agg").field("attrs.attrName").size(1));
        //聚合分析出当前attr_id对应的所有可能的属性值attrValue
        attr_id_agg.subAggregation(AggregationBuilders.terms("attr_value_agg").field("attrs.attrValue").size(50));
        attr_agg.subAggregation(attr_id_agg);
        //TODO 3、聚合attr
        sourceBuilder.aggregation(attr_agg);

        // 将上面构建的DSL语句转成字符串进行分析，获取DSL语句
        String str = sourceBuilder.toString();
        System.out.println("构建的DSL语句：" + str);


        SearchRequest searchRequest = new SearchRequest(new String[]{EsConstant.PRODUCT_INDEX}, sourceBuilder);
        return searchRequest;
    }
```

- 查询条件: 模糊匹配，过滤（按照属性，分类，品牌，价格区间，库存）。`DSL查询语句`和`java代码`对应：

![image-20230708224102707](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307082241778.png)



- 查询条件: 模糊匹配，过滤。`nested`嵌套类型的`DSL查询语句`和`java代码`对应：

![image-20230708224832427](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307082248477.png)



- 聚合分析。`DSL查询语句`和`java代码`对应：



![image-20230709042616767](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307090426842.png)



- 初步测试：在`postman`中添加请求参数，会自动生成完整的访问链接`http://127.0.0.1:12000/list.html?keyword=华为&catalog3Id=225&hasStock=1&attrs=15_海思（Hisilicon）&attrs=16_HUAWEI Kirin 970&skuPrice=6000_`。发送请求此请求，可以通过代码`String str = sourceBuilder.toString();  System.out.println("构建的DSL语句：" + str);`将构建的`DSL查询语句`打印在控制台，将其拷贝到`kibana`控制台进行查询测试

![image-20230709025401196](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307090254250.png)

#####   2.5.3 检索结果的封装

`buildSearchResult`:构建结果数据，接收查询结果，将查询结果中的信息提取出来，封装成对象并返回给前端页面进行渲染

```java
    /**
     * 构建结果数据
     */
    private SearchResult buildSearchResult(SearchResponse response, SearchParam param) {
        SearchResult result = new SearchResult();
        SearchHits hits = response.getHits();
        // 1、返回的所有查询到的商品信息
        List<SkuEsModel> esModels = new ArrayList<>();
        if (hits.getHits() != null && hits.getHits().length > 0) {
            for (SearchHit hit : hits.getHits()) {
                String sourceAsString = hit.getSourceAsString();
                SkuEsModel esModel = JSON.parseObject(sourceAsString, SkuEsModel.class);
                if (!StringUtils.isEmpty(param.getKeyword())) {
                    HighlightField skuTitle = hit.getHighlightFields().get("skuTitle");
                    String string = skuTitle.getFragments()[0].string();
                    esModel.setSkuTitle(string);
                }
                esModels.add(esModel);
            }
            ;
        }
        result.setProducts(esModels);

        // 2、当前所有商品涉及到的所有属性信息
        List<SearchResult.AttrVo> attrVos = new ArrayList<>();
        ParsedNested attr_agg = response.getAggregations().get("attr_agg");
        ParsedLongTerms attr_id_agg = attr_agg.getAggregations().get("attr_id_agg");
        for (Terms.Bucket bucket : attr_id_agg.getBuckets()) {
            SearchResult.AttrVo attrVo = new SearchResult.AttrVo();
            //1、得到属性的id
            long attrId = bucket.getKeyAsNumber().longValue();
            //2、得到属性的名字
            String attrName = ((ParsedStringTerms) bucket.getAggregations().get("attr_name_agg")).getBuckets().get(0).getKeyAsString();

            //3、得到属性的所有值
            List<String> attrValues = ((ParsedStringTerms) bucket.getAggregations().get("attr_value_agg")).getBuckets().stream().map(item -> {
                String keyAsString = ((Terms.Bucket) item).getKeyAsString();
                return keyAsString;
            }).collect(Collectors.toList());

            attrVo.setAttrId(attrId);
            attrVo.setAttrName(attrName);
            attrVo.setAttrValue(attrValues);

            attrVos.add(attrVo);
        }


        // 3、当前所有商品涉及到的所有品牌信息
        List<SearchResult.BrandVo> brandVos = new ArrayList<>();
        ParsedLongTerms brand_agg = response.getAggregations().get("brand_agg");
        for (Terms.Bucket bucket : brand_agg.getBuckets()) {
            SearchResult.BrandVo brandVo = new SearchResult.BrandVo();
            //1、得到品牌的id
            long brandId = bucket.getKeyAsNumber().longValue();
            //2、得到品牌的名
            String brandName = ((ParsedStringTerms) bucket.getAggregations().get("brand_name_agg")).getBuckets().get(0).getKeyAsString();
            //3、得到品牌的图片
            String brandImg = ((ParsedStringTerms) bucket.getAggregations().get("brand_img_agg")).getBuckets().get(0).getKeyAsString();
            brandVo.setBrandId(brandId);
            brandVo.setBrandName(brandName);
            brandVo.setBrandImg(brandImg);
            brandVos.add(brandVo);
        }
        result.setBrands(brandVos);


        // 4、当前所有商品涉及到的所有分类信息
        ParsedLongTerms catalog_agg = response.getAggregations().get("catalog_agg");
        List<SearchResult.CatalogVo> catalogVos = new ArrayList<>();
        List<? extends Terms.Bucket> buckets = catalog_agg.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            SearchResult.CatalogVo catalogVo = new SearchResult.CatalogVo();
            //得到分类id
            String keyAsString = bucket.getKeyAsString();
            catalogVo.setCatalogId(Long.parseLong(keyAsString));

            //得到分类名
            ParsedStringTerms catalog_name_agg = bucket.getAggregations().get("catalog_name_agg");
            String catalog_name = catalog_name_agg.getBuckets().get(0).getKeyAsString();
            catalogVo.setCatalogName(catalog_name);
            catalogVos.add(catalogVo);
        }
        result.setCatalogs(catalogVos);

        // 5.1、分页信息-页码
        result.setPageNum(param.getPageNum());
        // 5.2、分页信息-总记录数
        long total = hits.getTotalHits().value;
        result.setTotal(total);
        // 5.3、分页信息-总页码数(计算得到) 11/2 = 5 ... 1
        int totalPages = (int) total % EsConstant.PRODUCT_PAGESIZE == 0 ? (int) total / EsConstant.PRODUCT_PAGESIZE : ((int) total / EsConstant.PRODUCT_PAGESIZE + 1);
        result.setTotalPages(totalPages);


        return result;
    }
```



1.返回的所有查询到的商品信息。`查询结果`和`提取信息的java代码`对照：



![image-20230709133748128](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307091337186.png)



2.当前所有商品涉及到的所有属性信息。`查询结果`和`提取信息的java代码`对照：



![image-20230709135034267](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307091350325.png)



3.当前所有商品涉及到的所有品牌信息。`查询结果`和`提取信息的java代码`对照：



![image-20230709135413973](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307091354018.png)





4.当前所有商品涉及到的所有分类信息。`查询结果`和`提取信息的java代码`对照：



![image-20230709135712574](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307091357611.png)

#####  2.5.4 页面数据渲染

######  2.5.4.1 显示商品信息

需求：显示检索到的商品

修改默认分页数据`pageSize`为16个

```java
package com.atguigu.gulimall.search.constant;
public class EsConstant {
    public static final String PRODUCT_INDEX = "gulimall_product"; //sku数据在es中的索引
    public static final Integer PRODUCT_PAGESIZE = 16; //sku数据在es中的索引
}
```

修改`gulimall-search`的`templates/list.html`，渲染搜索页面。显示商品信息：

```xml
    <!--商品信息显示-->
    <div class="rig_tab">
        <div th:each="product:${result.getProducts()}">
            <div class="ico">
                <i class="iconfont icon-weiguanzhu"></i>
                <a href="/static/search/#">关注</a>
            </div>
            <p class="da">
                <a th:href="|http://item.gulimall.com/${product.skuId}.html|">
                    <img th:src="${product.skuImg}" class="dim">
                </a>
            </p>
            <ul class="tab_im">
                <li><a href="/static/search/#" title="黑色">
                    <img th:src="${product.skuImg}"></a></li>
            </ul>
            <p class="tab_R">
                <span th:text="'¥'+${product.skuPrice}">¥5199.00</span>
            </p>
            <p class="tab_JE">
                <a href="/static/search/#" th:utext="${product.skuTitle}">
                    Apple iPhone 7 Plus (A1661) 32G 黑色 移动联通电信4G手机
                </a>
            </p>
        </div>
    </div>
```

注意：`th:text`标签是将一切内容都识别为纯文本，而`th:utext`标签可以识别html文本，并使其生效

![image-20230711091554264](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307110916335.png)

测试：访问`http://search.gulimall.com/list.html?catalog3Id=225`可以搜索到`手机`商品的信息，访问`http://search.gulimall.com/list.html?catalog3Id=225&keyword=华为`可搜索到`华为`相关商品，且商品简介中`华为`二字被高亮显示

######  2.5.4.2 显示品牌、分类和属性筛选条件

需求：显示查询到的商品的品牌、分类和属性信息，当点击这些`品牌\分类\属性`时，就会在原来的基础上增加筛选条件做进一步的查询:

![image-20230712002230402](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307120022487.png)

修改`gulimall-search`的`templates/list.html`，渲染搜索页面：

```xml
    <div class="JD_nav_logo" th:with="brandid= ${param.brandId}">
        <!--品牌-->
        <div th:if="${#strings.isEmpty(brandid)}" class="JD_nav_wrap">

            <div class="sl_key">
                <span><b>品牌：</b></span>
            </div>
            <div class="sl_value">
                <div class="sl_value_logo">
                    <ul>
                        <li th:each="brand:${result.brands}">
                            <a href="/static/search/#"
                               th:href="${'javascript:searchProducts(&quot;brandId&quot;,'+brand.brandId+')'}">
                                <img th:src="${brand.brandImg}" alt="">
                                <div th:text="${brand.brandName}">
                                    华为(HUAWEI)
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="sl_ext">
                <a href="/static/search/#">
                    更多
                    <i style='background: url("image/search.ele.png")no-repeat 3px 7px'></i>
                    <b style='background: url("image/search.ele.png")no-repeat 3px -44px'></b>
                </a>
                <a href="/static/search/#">
                    多选
                    <i>+</i>
                    <span>+</span>
                </a>
            </div>
        </div>
        <!--分类-->
        <div class="JD_pre">
            <div class="sl_key">
                <span><b>分类：</b></span>
            </div>
            <div class="sl_value">
                <ul>
                    <li th:each="catalog:${result.catalogs}">
                        <a href="/static/search/#"
                           th:href="${'javascript:searchProducts(&quot;catalog3Id&quot;,'+catalog.catalogId+')'}"
                           th:text="${catalog.catalogName}">5.56英寸及以上</a>
                    </li>
                </ul>
            </div>
            <div class="sl_ext">
                <a href="/static/search/#">
                    更多
                    <i style='background: url("image/search.ele.png")no-repeat 3px 7px'></i>
                    <b style='background: url("image/search.ele.png")no-repeat 3px -44px'></b>
                </a>
                <a href="/static/search/#">
                    多选
                    <i>+</i>
                    <span>+</span>
                </a>
            </div>
        </div>

        <!--其他的所有需要展示的属性-->
        <div class="JD_pre" th:each="attr:${result.attrs}"
             th:if="${!#lists.contains(result.attrIds, attr.attrId)}">
            <div class="sl_key">
                <span th:text="${attr.attrName}">屏幕尺寸：</span>
            </div>
            <div class="sl_value">
                <ul>
                    <li th:each="val:${attr.attrValue}">
                        <a href="/static/search/#"
                           th:href="${'javascript:searchProducts(&quot;attrs&quot;,&quot;'+attr.attrId+'_'+val+'&quot;)'}"
                           th:text="${val}">5.56英寸及以上</a></li>
                </ul>
            </div>
        </div>
    </div>
```

点击`品牌\分类\属性`时触发的程序逻辑：`replaceAndAddParamVal`中`forceAdd`为`true`时就要强制在路径上添加参数，负责才考虑替换

```js
    function searchProducts(name, value) {
        //原来的页面
        location.href = replaceAndAddParamVal(location.href, name, value, true)
    }
    function replaceAndAddParamVal(url, paramName, replaceVal, forceAdd) {
        var oUrl = url.toString();
        //1、如果没有就添加，有就替换；
        if (oUrl.indexOf(paramName) != -1) {
            if (forceAdd) {
                var nUrl = "";
                if (oUrl.indexOf("?") != -1) {
                    nUrl = oUrl + "&" + paramName + '=' + replaceVal;
                } else {
                    nUrl = oUrl + "?" + paramName + '=' + replaceVal;
                }
                return nUrl;
            } else {
                var re = eval('/(' + paramName + '=)([^&]*)/gi');
                var nUrl = oUrl.replace(re, paramName + '=' + replaceVal);
                return nUrl;
            }
        } else {
            var nUrl = "";
            if (oUrl.indexOf("?") != -1) {
                nUrl = oUrl + "&" + paramName + '=' + replaceVal;
            } else {
                nUrl = oUrl + "?" + paramName + '=' + replaceVal;
            }
            return nUrl;
        }
    }
```

###### 2.5.4.3 页面关键字搜索效果渲染

在搜索框中输入关键字，点击搜索后进行相应检索

![image-20230712030619334](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307120357521.png)



```xml
<!--搜索导航-->
<div class="header_sous">
    <div class="logo">
        <a href="http://gulimall.com"><img src="/static/search/./image/logo1.jpg" alt=""></a>
    </div>
    <div class="header_form">
        <input id="keyword_input" type="text" placeholder="手机" th:value="${param.keyword}"/>
        <a href="javascript:searchByKeyword();">搜索</a>
    </div>
</div>
```

输入关键字并点击`搜索`触发的逻辑：

```js
    function searchByKeyword() {
        searchProducts("keyword", $("#keyword_input").val());
    }
    function searchProducts(name, value) {
        //原来的页面
        location.href = replaceAndAddParamVal(location.href, name, value, true)
    }
    function replaceAndAddParamVal(url, paramName, replaceVal, forceAdd) {
        var oUrl = url.toString();
        //1、如果没有就添加，有就替换；
        if (oUrl.indexOf(paramName) != -1) {
            if (forceAdd) {
                var nUrl = "";
                if (oUrl.indexOf("?") != -1) {
                    nUrl = oUrl + "&" + paramName + '=' + replaceVal;
                } else {
                    nUrl = oUrl + "?" + paramName + '=' + replaceVal;
                }
                return nUrl;
            } else {
                var re = eval('/(' + paramName + '=)([^&]*)/gi');
                var nUrl = oUrl.replace(re, paramName + '=' + replaceVal);
                return nUrl;
            }
        } else {
            var nUrl = "";
            if (oUrl.indexOf("?") != -1) {
                nUrl = oUrl + "&" + paramName + '=' + replaceVal;
            } else {
                nUrl = oUrl + "?" + paramName + '=' + replaceVal;
            }
            return nUrl;
        }
    }
```

######  2.5.4.4 页面分页效果渲染

![image-20230712014658385](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307120146430.png)

```xml
<!--分页-->
<div class="filter_page">
    <div class="page_wrap">
        <span class="page_span1">
            <a class="page_a" th:attr="pn=${result.pageNum - 1}"
               th:if="${result.pageNum>1}">
                < 上一页
            </a>
            <a class="page_a"
               th:attr="pn=${nav},style=${nav == result.pageNum?'border: 0;color:#ee2222;background: #fff':''}"
               th:each="nav:${result.pageNavs}">[[${nav}]]</a>
            <a class="page_a" th:attr="pn=${result.pageNum + 1}"
               th:if="${result.pageNum<result.totalPages}">
                下一页 >
            </a>
        </span>
        <span class="page_span2">
            <em>共<b>[[${result.totalPages}]]</b>页&nbsp;&nbsp;到第</em>
            <input type="number" value="1">
            <em>页</em>
            <a class="page_submit">确定</a>
        </span>
    </div>
</div>
```

点击分页页码时触发的逻辑：

```js
// 点击分页页码时触发的逻辑
$(".page_a").click(function () {
    var pn = $(this).attr("pn");
    var href = location.href;
    location.href = replaceAndAddParamVal(href, "pageNum", pn, false);
    return false;
});

function replaceAndAddParamVal(url, paramName, replaceVal, forceAdd) {
    var oUrl = url.toString();
    //1、如果没有就添加，有就替换；
    if (oUrl.indexOf(paramName) != -1) {
        if (forceAdd) {
            var nUrl = "";
            if (oUrl.indexOf("?") != -1) {
                nUrl = oUrl + "&" + paramName + '=' + replaceVal;
            } else {
                nUrl = oUrl + "?" + paramName + '=' + replaceVal;
            }
            return nUrl;
        } else {
            var re = eval('/(' + paramName + '=)([^&]*)/gi');
            var nUrl = oUrl.replace(re, paramName + '=' + replaceVal);
            return nUrl;
        }
    } else {
        var nUrl = "";
        if (oUrl.indexOf("?") != -1) {
            nUrl = oUrl + "&" + paramName + '=' + replaceVal;
        } else {
            nUrl = oUrl + "?" + paramName + '=' + replaceVal;
        }
        return nUrl;
    }
}
```

######  2.5.4.5 页面排序功能

需求：对检索结果进行排序展示



![image-20230712030816973](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307120308011.png)

显示内容和回显内容

```xml
    <!--综合排序
    1、sort=saleCount_asc/desc skuPrice_asc/desc
    2、
    -->
    <div class="filter_top">
        <div class="filter_top_left" th:with="p = ${param.sort},priceRange = ${param.skuPrice}">
            <a th:class="${(!#strings.isEmpty(p) && #strings.startsWith(p,'hotScore') && #strings.endsWith(p,'desc'))?'sort_a desc':'sort_a'}"
               th:attr="style=${(#strings.isEmpty(p) || #strings.startsWith(p,'hotScore'))?'color: #FFF;border-color:#e4393c;background: #e4393c':'color: #333;border-color:#CCC;background: #fff'}"
               sort="hotScore" href="/static/search/#"> 综合排序 [[${(!#strings.isEmpty(p) &&
                #strings.startsWith(p,'hotScore') && #strings.endsWith(p,'desc'))?'↓':'↑'}]] </a>
            <a th:class="${(!#strings.isEmpty(p) && #strings.startsWith(p,'saleCount') && #strings.endsWith(p,'desc'))?'sort_a desc':'sort_a'}"
               th:attr="style=${(!#strings.isEmpty(p) && #strings.startsWith(p,'saleCount'))?'color: #FFF;border-color:#e4393c;background: #e4393c':'color: #333;border-color:#CCC;background: #fff'}"
               sort="saleCount" href="/static/search/#">销量 [[${(!#strings.isEmpty(p) &&
                #strings.startsWith(p,'saleCount') && #strings.endsWith(p,'desc'))?'↓':'↑'}]]</a>
            <a th:class="${(!#strings.isEmpty(p) && #strings.startsWith(p,'skuPrice')&& #strings.endsWith(p,'desc'))?'sort_a desc':'sort_a'}"
               th:attr="style=${(!#strings.isEmpty(p) && #strings.startsWith(p,'skuPrice'))?'color: #FFF;border-color:#e4393c;background: #e4393c':'color: #333;border-color:#CCC;background: #fff'}"
               sort="skuPrice" href="/static/search/#">价格 [[${(!#strings.isEmpty(p) &&
                #strings.startsWith(p,'skuPrice')&& #strings.endsWith(p,'desc'))?'↓':'↑'}]]</a>

            <a href="/static/search/#">评论分</a>
            <a href="/static/search/#">上架时间</a>
            <!--                            300_500 _500 500_      #strings.substringAfter(priceRange,'-')-->


            <input id="skuPriceFrom" type="number" style="width: 100px;margin-left: 30px;"
                   th:value="${#strings.isEmpty(priceRange)?'':#strings.substringBefore(priceRange,'_')}">
            -
            <input id="skuPriceTo" type="number" style="width: 100px;"
                   th:value="${#strings.isEmpty(priceRange)?'':#strings.substringAfter(priceRange,'_')}">
            <button id="skuPriceSearchBtn">确定</button>
        </div>
    </div>
```

点击排序触发的逻辑：

```js
    $(".sort_a").click(function () {
        //1、当前被点击的元素变为选中状态
        //    color: #FFF; border-color: #e4393c; background: #e4393c;
        //改变当前元素以及兄弟元素的样式
        // changeStyle(this);
        $(this).toggleClass("desc");
        //2、跳转到指定位置 sort=skuPrice_asc/desc
        var sort = $(this).attr("sort");
        sort = $(this).hasClass("desc") ? sort + "_desc" : sort + "_asc";
        location.href = replaceAndAddParamVal(location.href, "sort", sort);
        //禁用默认行为
        return false;
    });
```



###### 2.5.4.6 页面价格区间搜索

需求：输入价格区间进行搜索

![image-20230712034445372](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307120344401.png)

```xml
<!--价格区间-->
<!--                            300_500 _500 500_      #strings.substringAfter(priceRange,'-')-->
<input id="skuPriceFrom" type="number" style="width: 100px;margin-left: 30px;"
       th:value="${#strings.isEmpty(priceRange)?'':#strings.substringBefore(priceRange,'_')}">
-
<input id="skuPriceTo" type="number" style="width: 100px;"
       th:value="${#strings.isEmpty(priceRange)?'':#strings.substringAfter(priceRange,'_')}">
<button id="skuPriceSearchBtn">确定</button>
```

输入价格区间并点击确实触发的逻辑：

```js
$("#skuPriceSearchBtn").click(function () {
    //1、拼上价格区间的查询条件
    var from = $("#skuPriceFrom").val();
    var to = $("#skuPriceTo").val();

    var query = from + "_" + to;

    location.href = replaceAndAddParamVal(location.href, "skuPrice", query);
});
```

######  2.5.4.7 按照有货无货进行检索



![image-20230712040139781](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307120401828.png)

```xml
<a href="#" th:with="check = ${param.hasStock}">
    <input id="showHasStock" type="checkbox"
           th:checked="${#strings.equals(check,'1')}">
    仅显示有货
</a>
```

触发的逻辑：

```js
<script>
   $("#showHasStock").change(function () {
        if ($(this).prop('checked')) {
            location.href = replaceAndAddParamVal(location.href, "hasStock", 1);
        } else {
            //没选中
            var re = eval('/(hasStock=)([^&]*)/gi');
            location.href = (location.href + "").replace(re, '');
        }
        return false;
    })
</script>
```

#####  2.5.5 构建面包屑导航功能

######  2.5.5.1 面包屑导航

面包屑导航需求：选中`商品属性`或`品牌信息`来对商品进行筛选，选中的属性被添加到`面包屑导航`中，`面包屑导航栏`展示了当前所有的筛选属性。删除`面包屑导航`中某个属性时，要重新根据新的`面包屑导航`属性来对商品进行筛选。面包屑导航一般由前端实现，但是此处我们采用的是`服务端渲染技术`，页面渲染由后端进行



![image-20230719050512213](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307190505314.png)

###### 2.5.5.2 面包屑导航参数 & 商品服务调用

1.在`SearchResult`中添加面包屑导航参数为页面提供`面包屑导航数据`

1.1面包屑导航参数`NavVo`要记录选中属性的名字`navName`,属性对应的值`navValue`,以及取消当前属性后要跳转的链接`link`。`attrIds`表示`面包屑导航数据中的属性id`,`attrIds`中的属性添加到面包屑之后就不用在属性部分显示

```java
/**
 * 将返回给页面的所有信息封装为对象
 */
@Data
public class SearchResult {
    
    // 其他字段省略
    
    /**
     * 面包屑导航数据
     */
    private List<NavVo> navs = new ArrayList<>();
    
    @Data
    public static class NavVo {
        // 导航的名字
        private String navName;
        // 导航的值
        private String navValue;
        // 取消后要跳转的链接
        private String link;
    }
    
   /**
     * 面包屑导航数据中的属性id
     */
    private List<Long> attrIds = new ArrayList<>();
}
```

1.2要跳转的链接`link`是当前页面的请求路径删除要取消的属性生成的。所以检索的请求参数需要再传递一个参数`_queryString`来表示`原生的所有查询条件`

```java
@Data
public class SearchParam {
    // 其他参数省略
    /**
     * 原生的所有查询条件
     */
    private String _queryString;
}
```

2.因为页面传过来的是`属性id`,所以还需要根据这个id到`商品服务gulimall-product`中获取属性的名字

2.1 导入`springcloud`的`openfeign`依赖管理：

```xml
    <properties>
        <java.version>1.8</java.version>
        <elasticsearch.version>7.4.2</elasticsearch.version>
        <spring-cloud.version>Greenwich.SR3</spring-cloud.version>
    </properties>

     <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

2.2 注解`@EnableFeignClients`开启`feign`的远程调用功能（开启远程调用的前提，调用方和服务提供方都注册到注册中心）:

```java
@EnableFeignClients //开启远程调用
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class GulimallSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(GulimallSearchApplication.class, args);
    }
}
```

2.3 在检索服务`gulimall-search`下声明`feign`远程调用接口：

```java
@FeignClient("gulimall-product")
public interface ProductFeignService {
    @GetMapping("/product/attr/info/{attrId}")
    public R attrInfo(@PathVariable("attrId") Long attrId);
    @GetMapping("/product/brand/infos")
    public R brandsInfo(@RequestParam("brandIds") List<Long> brandIds);
}
```

2.4 商品服务`gulimall-product`的`/product/attr/info/{attrId}`接口和`/product/brand/infos`接口：

查询属性信息：

```java
// 接口/product/attr/info/{attrId}
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;
    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId) {
        AttrRespVo respVo = attrService.getAttrInfo(attrId);
        return R.ok().put("attr", respVo);
    }
}
```

```java
public interface AttrService extends IService<AttrEntity> {
    AttrRespVo getAttrInfo(Long attrId);
}
```

```java
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {
    @Autowired
    private CategoryService categoryService;

    @Cacheable(value = "attr",key = "'attrinfo:'+#root.args[0]")
    @Override
    public AttrRespVo getAttrInfo(Long attrId) {
        AttrRespVo respVo = new AttrRespVo();
        AttrEntity attrEntity = this.getById(attrId);
        BeanUtils.copyProperties(attrEntity, respVo);

        if (attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            // 1.设置分组信息
            AttrAttrgroupRelationEntity attrgroupRelation = relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrId));
            if (attrgroupRelation != null) {
                respVo.setAttrGroupId(attrgroupRelation.getAttrGroupId());
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrgroupRelation.getAttrGroupId());
                if (attrGroupEntity != null) {
                    respVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }
        }
        // 2、设置分类信息
        Long catelogId = attrEntity.getCatelogId();
        Long[] catelogPath = categoryService.findCatelogPath(catelogId);
        respVo.setCatelogPath(catelogPath);

        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);
        if (categoryEntity != null && categoryEntity.getName() != null) {
            respVo.setCatelogName(categoryEntity.getName());
        }
        return respVo;
    }
}
```

查询品牌信息：

```java
// 接口/product/brand/infos
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;
    /**
     * 查询品牌信息
     */
    @GetMapping("/infos")
    public R info(@RequestParam("brandIds") List<Long> brandIds) {
        List<BrandEntity> brand = brandService.getBrandsByIds(brandIds);

        return R.ok().put("brand", brand);
    }

}
```

```java
public interface BrandService extends IService<BrandEntity> {
    List<BrandEntity> getBrandsByIds(List<Long> brandIds);
}
```

```java
@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;
    @Override
    public List<BrandEntity> getBrandsByIds(List<Long> brandIds) {
        return baseMapper.selectList(new QueryWrapper<BrandEntity>().in("brand_id",brandIds));
    }
}
```

2.5 远程服务`商品服务gulimall-product`的`/product/attr/info/{attrId}`接口响应数据在两个类中(`AttrRespVo`和`AttrVo`)，我们需要将重新在检索服务`gulimall-search`中重新封装一个类`AttrResponseVo`来包含这些属性。同时将 `/product/brand/infos`接口的响应数据也封装为`BrandVo`

```java
// 商品服务gulimall-product中的数据
// AttrRespVo定义：
@Data
public class AttrRespVo extends AttrVo {
    /**
     * 			"catelogName": "手机/数码/手机", //所属分类名字
     * 			"groupName": "主体", //所属分组名字
     */
    // 所属分类
    private String catelogName;
    // 所属分组
    private String groupName;
    private Long[] catelogPath;
}

// AttrVo定义：
@Data
public class AttrVo {
    /**
     * 属性id
     */
    private Long attrId;
    /**
     * 属性名
     */
    private String attrName;
    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    private Integer searchType;
    /**
     * 值类型[0-为单个值，1-可以选择多个值]
     */
    private Integer valueType;
    /**
     * 属性图标
     */
    private String icon;
    /**
     * 可选值列表[用逗号分隔]
     */
    private String valueSelect;
    /**
     * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
     */
    private Integer attrType;
    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    private Long enable;
    /**
     * 所属分类
     */
    private Long catelogId;
    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    private Integer showDesc;

    private Long attrGroupId;
}
```

```java
//  gulimall-search中的AttrResponseVo
@Data
public class AttrResponseVo {
    /**
     * 属性id
     */
    private Long attrId;
    /**
     * 属性名
     */
    private String attrName;
    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    private Integer searchType;
    /**
     * 值类型[0-为单个值，1-可以选择多个值]
     */
    private Integer valueType;
    /**
     * 属性图标
     */
    private String icon;
    /**
     * 可选值列表[用逗号分隔]
     */
    private String valueSelect;
    /**
     * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
     */
    private Integer attrType;
    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    private Long enable;
    /**
     * 所属分类
     */
    private Long catelogId;
    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    private Integer showDesc;

    private Long attrGroupId;

    private String catelogName;
    private String groupName;

    private Long[] catelogPath;
}

//  gulimall-search中的BrandVo
@Data
public class BrandVo {
    private Long brandId;
    private String  brandName;
}
```



2.6 注意到商品服务`AttrController`中放入映射`R`中的返回值的键值是`attr`，而原本默认的键值是`data`,所以重载一下公共返回对象`R`的`getData`方法：

```java
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    //利用fastjson进行逆转
    public <T> T getData(String key, TypeReference<T> typeReference){
        Object data = get(key);//默认是map
        String s = JSON.toJSONString(data);
        T t = JSON.parseObject(s, typeReference);
        return t;
    }
    
    //利用fastjson进行逆转
    public <T> T getData(TypeReference<T> typeReference){
        Object data = get("data");//默认是map
        String s = JSON.toJSONString(data);
        T t = JSON.parseObject(s, typeReference);
        return t;
    }
    
    // 省略其他内部方法...
}
```

###### 2.5.5.3 构建面包屑导航实现

`SearchController`:

```java
@Controller
public class SearchController {
    @Autowired
    private MallSearchService mallSearchService;
    /**
     * param: 自动将页面提交过来的所有请求查询参数封装成指定的对象
     * model : 因为所有查到的数据是要返回给页面，所以参数中加一个Model
     */
    @GetMapping({"/", "/list.html"})
    public String listPage(SearchParam param, Model model, HttpServletRequest request) {
        param.set_queryString(request.getQueryString());
        //1、根据传递来的页面的查询参数，去es中检索商品
        SearchResult result = mallSearchService.search(param);
        // 将检索到的结果传到页面进行渲染
        model.addAttribute("result", result);
        return "list";
    }
}
```

`MallSearchService`:

```java
public interface MallSearchService {
    /**
     *
     * @param param  检索的所有参数
     * @return 返回检索的结果,里面包含页面需要的所有信息
     */
    SearchResult search(SearchParam param);
}
```

`MallSearchServiceImpl`:主要关注`buildSearchResult`方法

```java
@Service
public class MallSearchServiceImpl implements MallSearchService {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ProductFeignService productFeignService;
    
    //去es进行检索
    @Override
    public SearchResult search(SearchParam param) {
        //1、动态构建出查询需要的DSL语句
        SearchResult result = null;
        //1、准备检索请求
        SearchRequest searchRequest = buildSearchRequrest(param);
        try {
            //2、执行检索请求
            SearchResponse response = client.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);
            //3、分析响应数据并封装成我们需要的格式
            result = buildSearchResult(response, param);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    /**
     * 构建结果数据
     */
    private SearchResult buildSearchResult(SearchResponse response, SearchParam param) {
        // 省略其他属性的构建

        //6、构建面包屑导航功能：属性
        if (param.getAttrs() != null && param.getAttrs().size() > 0) {
            List<SearchResult.NavVo> collect = param.getAttrs().stream().map(attr -> {
                //1、分析每个attrs传过来的查询参数值。
                SearchResult.NavVo navVo = new SearchResult.NavVo();
                // attrs=2_5存:6寸
                String[] s = attr.split("_");
                navVo.setNavValue(s[1]);
                // 根据属性id从商品服务查询对应的属性名称
                R r = productFeignService.attrInfo(Long.parseLong(s[0]));
                result.getAttrIds().add(Long.parseLong(s[0]));
                if (r.getCode() == 0) {
                    AttrResponseVo data = r.getData("attr", new TypeReference<AttrResponseVo>() {
                    });
                    navVo.setNavName(data.getAttrName());
                } else {
                    //  如果调用失败，可以设置一个默认值
                    navVo.setNavName(s[0]);
                }
//
                //2、取消了这个面包屑以后，我们要跳转到那个地方.将请求地址的url里面的当前置空
                //拿到所有的查询条件，去掉当前。
                //attrs=  15_海思（Hisilicon）
                String replace = replaceQueryString(param, attr, "attrs");
                navVo.setLink("http://search.gulimall.com/list.html" + ("".equals(replace) ? "" : "?" + replace));

                return navVo;
            }).collect(Collectors.toList());
            result.setNavs(collect);
        }

        // 7、构建面包屑导航功能：品牌
        if (param.getBrandId() != null && param.getBrandId().size() > 0) {
            List<SearchResult.NavVo> navs = result.getNavs();
            SearchResult.NavVo navVo = new SearchResult.NavVo();

            navVo.setNavName("品牌");
            // 远程查询所有品牌
            R r = productFeignService.brandsInfo(param.getBrandId());
            if (r.getCode() == 0) {
                List<BrandVo> brand = r.getData("brand", new TypeReference<List<BrandVo>>() {
                });
                StringBuffer buffer = new StringBuffer();
                String replace = "";
                for (BrandVo brandVo : brand) {
                    buffer.append(brandVo.getBrandName() + ";");
                    replace = replaceQueryString(param, brandVo.getBrandId() + "", "brandId");
                }
                navVo.setNavValue(buffer.toString());
                navVo.setLink("http://search.gulimall.com/list.html" + ("".equals(replace) ? "" : "?" + replace));
            }

            navs.add(navVo);
        }

        //TODO 构建分类面包屑导航功能（注意：分类不需要导航取消）

        System.out.println("查询结果封装result:" + result);
        return result;
    }

    /**
     * 取消了这个面包屑以后，我们要跳转到那个地方.将请求地址的url里面的当前置空
     */
    private String replaceQueryString(SearchParam param, String value, String key) {
        String encode = null;
        try {
            encode = URLEncoder.encode(value, "UTF-8");
            encode = encode.replace("+", "%20");//浏览器对空格编码和java不一样（）
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!param.get_queryString().contains("&")) {  // 如果只有一个查询参数（?号后紧跟参数，不包含& ）
            return param.get_queryString().replace(key + "=" + encode, "");
        }
        return param.get_queryString().replace("&" + key + "=" + encode, "");
    }

}
```

######  2.5.5.4 面包屑导航页面渲染

展示面包屑导航，并且将面包屑导航中选中的属性从属性展示中去除

```js
        <div class="JD_ipone_one c">
            <!--            遍历面包屑功能-->
            <a th:href="${nav.link}" th:each="nav:${result.navs}"><span th:text="${nav.navName}"></span>：<span
                    th:text="${nav.navValue}"></span> ×</a>
        </div>

    <!--其他的所有需要展示的属性-->
    <!--`attrIds`表示`面包屑导航数据中的属性id`,`attrIds`中的属性添加到面包屑之后就不用在属性部分显示-->
    <div class="JD_pre" th:each="attr:${result.attrs}"
         th:if="${!#lists.contains(result.attrIds, attr.attrId)}">
        <div class="sl_key">
            <span th:text="${attr.attrName}">屏幕尺寸：</span>
        </div>
        <div class="sl_value">
            <ul>
                <li th:each="val:${attr.attrValue}">
                    <a href="/static/search/#"
                       th:href="${'javascript:searchProducts(&quot;attrs&quot;,&quot;'+attr.attrId+'_'+val+'&quot;)'}"
                       th:text="${val}">5.56英寸及以上</a></li>
            </ul>
        </div>
    </div>
```



## 3.异步&线程池

### 3.1初始化线程的4种方式

##### 3.1.1  继承 Thread

```java
public class ThreadTest {
    public static void main(String[] args) {
        System.out.println("main.....start");
        Thread01 thread01 = new Thread01();
        thread01.start(); // 启动线程
        System.out.println("main.....end");
    }
    /*
     * 1)、继承Thread
     *     Thread01 thread = new Thread01();
     *     thread.start();//启动线程
     */
    public static class Thread01 extends Thread{
        @Override
        public void run() {
            System.out.println("当前线程："+Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果："+i);
        }
                /*
                main.....start
                main.....end
                当前线程：20
                运行结果：5
                 */
    }
}
```

#####  3.1.2 实现 Runnable接口

```java
public class ThreadTest {
    public static void main(String[] args) {
        System.out.println("main.....start");
        // 2)、实现Runnable接口
        Runable01 runable01 = new Runable01();
        new Thread(runable01).start();
        System.out.println("main.....end");
    }
    /*
     * 2)、实现Runnable接口
     *     Runable01 runable01 = new Runable01();
     *     new Thread(runable01).start();
     */
    public static class Runable01 implements Runnable{
        @Override
        public void run() {
            System.out.println("当前线程："+Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果："+i);
        }
            /*
            main.....start
            main.....end
            当前线程：22
            运行结果：5        
             */
    }
}
```

#####  3.1.3 实现Callable接口+ FutureTask

```java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main.....start");
        // 3)、实现Callable接口 + FutureTask （可以拿到返回结果，可以处理异常）
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable01());
        new Thread(futureTask).start();
          // 阻塞等待整个线程执行完成，获取返回结果
         Integer integer = futureTask.get();
        System.out.println("main.....end");
    }
    /*
     * 3)、实现Callable接口 + FutureTask （可以拿到返回结果，可以处理异常）
     *      FutureTask<Integer> futureTask = new FutureTask<>(new Callable01());
     *      new Thread(futureTask).start();
     *      //阻塞等待整个线程执行完成，获取返回结果
     *      Integer integer = futureTask.get();
     */
    public static class Callable01 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("当前线程："+Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果："+i);
            return i;
        }
            /*
            main.....start
            当前线程：22
            运行结果：5
            main.....end
             */
    }

}
```

#####  3.1.4 线程池

以上3种方式在执行程序的时候都要通过`new Thread()`创建线程，在大量请求的场景下，每个请求都创建新的线程，可能会导致服务器资源耗尽，不利于控制服务器中的线程资源。通常的做法是创建线程池，然后直接给线程池提交任务

开发中要保证整个系统中的线程池数量不变，通常全局地设置一到两个线程池。要执行异步任务时直接将其提交给线程池执行即可

通过线程池执行线程性能稳定，也可以获取执行结果，并捕获异常。但是，在业务复杂情况下，一个异步调用可能会依赖于另一个异步调用的执行结果

两种初始化线程池的方式：

```java
Executors.newFiexedThreadPool()
//或者
new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime TimeUnit unit,workQueue, threadFactory, handler);
```

提交线程到线程池执行：`excute`方法和的`submit`方法：

![image-20230701200832553](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307231824671.png)

以下是对这三种接口类型的区别的详细说明：

1. `submit(Runnable task)`：提交一个实现了 `Runnable` 接口的任务到线程池。此方法不返回任何结果，所以在任务执行完毕后无法获取任务的返回值。
2. `submit(Callable<T> task)`：提交一个实现了 `Callable<T>` 接口的任务到线程池。此方法返回一个 `Future<T>` 对象，可以通过该对象来获取任务的返回值。`Callable<T>` 接口的 `call` 方法可以返回一个类型为 `T` 的结果。
3. `execute(Runnable command)`：执行一个实现了 `Runnable` 接口的任务。与 `submit(Runnable task)` 方法类似，但是不返回任何结果，因此无法获取任务的返回值

**线程池代码示例**：

```java
public class ThreadTest {

    public static ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main.....start");
        // 4)、线程池
        service.execute(new Runable01());
        System.out.println("main.....end");
    }

    public static class Runable01 implements Runnable {

        @Override
        public void run() {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
        }
            /*
            main.....start
            main.....end
            当前线程：22
            运行结果：5
             */
    }
}
```

总结：

```
1、四种创建线程方式的区别;
       1) 1、2不能得到返回值。3可以获取返回值
       2) 1、2、3都不能控制资源
       3) 4可以控制资源，性能稳定
2、线程池[ExecutorService]
       1)、创建线程池：
               1）、Executors.newFiexedThreadPool()
               2）、new ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler)
       2)、给线程池直接提交任务
               service.execute(new Runable01());
               Future:可以获取到异步结果    
```

###  3.2 线程池的七大参数

##### 3.2.1 线程池的创建

```java
一、线程池的创建：
    1)、Executors.newFiexedThreadPool()
    2)、new ThreadPoolExecutor(int corePoolSize,
                                      int maximumPoolSize,
                                      long keepAliveTime,
                                      TimeUnit unit,
                                      BlockingQueue<Runnable> workQueue,
                                      ThreadFactory threadFactory,
                                      RejectedExecutionHandler handler)
```

#####  3.2.2 线程池的七大参数

```
二、线程池的七大参数:
1) corePoolSize: 核心线程数
         线程池创建好以后就准备就绪的线程数量，就等待来接受异步任务去执行
         核心线程数一直存在，除非线程池被销毁或者设置了allowCoreThreadTimeOut属性
2) maximumPoolSize: 最大线程数量。控制资源
3) keepAliveTime: 存活时间
         如果当前的线程数量大于core数量(maximumPoolSize > corePoolSize)
         那些空闲时间超过存活时间的非核心线程就会被释放
         只要非核心线程的线程空闲时间大于指定的keepAliveTime，就会被释放
4) unit:时间单位
5) BlockingQueue<Runnable> workQueue:阻塞队列
          如果任务有很多，就会将目前多的任务放在队列里面
          只要有线程空闲，就会去队列里面取出新的任务继续执行。
          new LinkedBlockingDeque<>()：默认是Integer的最大值，一般要不了这么多，内存不够
6) threadFactory: 线程的创建工厂
7) RejectedExecutionHandler handler:
          如果队列满了，按照我们指定的拒绝策略拒绝执行任务
          系统会默认使用 AbortPolicy 拒绝策略
          AbortPolicy 是 RejectedExecutionHandler 接口的一个实现类
          AbortPolicy会在任务被拒绝执行时抛出一个 RejectedExecutionException 异常
          如果不想抛弃还要执行：CallerRunsPolicy
```

#####  3.2.3 线程池工作顺序

```
三、线程池工作顺序：
1)、线程池创建，准备好core数量的核心线程，准备接受任务
     1.1、core满了，就将再进来的任务放入阻塞队列中。空闲的core就会自己去阻塞队列获取任务执行
     1.2、阻塞队列满了，就直接开新线程执行，最大只能开到max指定的数量
     1.3、max满了就用RejectedExecutionHandler拒绝任务
     1.4、max都执行完成，有很多空闲.在指定的时间keepAliveTime以后，释放max-core这些线程

2) 面试: 一个线程池core 7:max 20 , queue: 50。100个并发进来怎么分配的?
        先有7个能直接得到执行，接下来50个进入队列排队，在多开13个继续执行
        现在70个被安排上了。剩下30个默认拒绝策略
```

###  3.3 Executors方式常见的4种线程池

- `Executors.newCachedThreadPool()` 

  - core是0，所有都可回收

  - 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程

- `Executors.newFixedThreadPool()`

  - 固定大小，core=max；都不可回收
  - 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待

- `Executors.newScheduledThreadPool()`
  - 用作定时任务的线程池
  - 创建一个定长线程池，支持定时及周期性任务执行

- `Executors.newSingleThreadExecutor()`
  - core=max=1,单线程的线程池，后台从队列里面获取任务，挨个执行
  - 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序 (FIFO、LIFO、优先级) 执行

###  3.4 开发中为什么使用线程

- 降低资源的消耗
  - 通过重复利用已经创建好的线程降低线程的创建和销毁带来的损耗
- 提高响应速度
  - 因为线程池中的线程数没有超过线程池的最大上限时,有的线程处于等待分配任务的状态，当任务来时无需创建新的线程就能执行
- 提高线程的可管理性
  - 线程池会根据当前系统特点对池内的线程进行优化处理,减少创建和销毁线程带来的系统开销
  - 无限的创建和销毁线程不仅消耗系统资源，还降低系统的稳定性，使用线程池进行统一分配

###  3.5 CompletableFuture异步

#####  3.5.1  CompletableFuture+简介

业务场景: 查询商品详情页的逻辑比较复杂，有些数据还需要远程调用，必然需要花费更多的时间

![image-20230702044049873](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307231824249.png)



假如商品详情页的每个查询，需要如下标注的时间才能完成
那么，用户需要5.5s后才能看到商品详情页的内容。很显然是不能接受的。如果有多个线程同时完成这6步操作，也许只需要1.5s即可完成响应

#####  3.5.2 创建异步对象

CompletableFuture提供了四个静态方法来创建一个异步操作

![image-20230702184805536](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307231824042.png)

1.`runXxxx`都是没有返回结果的，`supplyXxox`都是可以获取返回结果的

2.可以传入自定义的线程池，否则就用默认的线程池

- **runAsync方法**使用示例：

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class CompletableFutureTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        System.out.println("main.....start...");
        CompletableFuture.runAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
        }, executor);
        System.out.println("main.....end...");
            /*
            main.....start...
            main.....end...
            当前线程：22
            运行结果：5
             */
    }
}
```

**supplyAsync方法**使用：

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class CompletableFutureTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main.....start...");
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }, executor);
        System.out.println("supplyAsync返回结果：" + future.get());
        System.out.println("main.....end...");
                /*
                main.....start...
                当前线程：22
                运行结果：5
                supplyAsync返回结果：5
                main.....end...
                 */
    }
}
```

#####   3.5.3 计算完成时回调方法

![image-20230702191040357](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307231824177.png)

- `whenComplete` 可以处理正常和异常的计算结果
- `exceptionally` 处理异常情况

- `whenComplete` 和 `whenCompleteAsync` 的区别：
  - `whenComplete`：是执行当前任务的线程执行继续执行 `whenComplete` 的任务
  - `whenCompleteAsync`：是执行把 `whenCompleteAsync` 这个任务继续提交给线程池来进行执行
  - `whenComplete` 方法是同步的，使用当前线程执行后续操作，而 `whenCompleteAsync` 方法是异步的，使用特定的线程池或 Executor 来执行后续操作
- 方法不以 `Async` 结尾，意味着 `Action` 使用相同的线程执行(同步执行)，而 `Async`(异步执行) 可能会创建一个新的线程来执行 (如果是使用相同的线程池，也可能会被同一个线程选中执行)

**whenComplete**代码示例：

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class CompletableFutureTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main.....start...");
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 0;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).whenComplete((result, exception) -> {
            System.out.println("异步任务成功完成了...结果是:" + result + "  异常是:" + exception);
        });
        System.out.println("main.....end...");
            /*
            main.....start...
            当前线程：22
            异步任务成功完成了...结果是:null  异常是:java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
            main.....end...
             */
    }
}
```

**exceptionally使用**代码示例：

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class CompletableFutureTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main.....start...");
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 0;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).whenComplete((result, exception) -> {
            // whenComplete虽然能够得到异常信息，但是无法修改返回数据
            System.out.println("异步任务成功完成了...结果是:" + result + "  异常是:" + exception);
        }).exceptionally(throwable -> {
            // exceptionally可以感知异常并且返回默认值
            return 10;
        });
        System.out.println("结果是：" + future.get());
        System.out.println("main.....end...");
            /*
            main.....start...
            当前线程：22
            异步任务成功完成了...结果是:null  异常是:java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
            结果是：10
            main.....end...
             */
    }
}
```

#####  3.5.4 handle方法

![image-20230702205640436](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307231824709.png)

和complete一样，可对结果做最后的处理（可处理异常），可改变返回值

`whenComplete`主要是感知异常和接收返回结果，不能修改返回结果。如果需要对返回的数据进一步处理，通常使用`handle`方法来实现

`handle`既能感知异常和返回结果，也可以对返回结果进行修改。`handle`作用：方法执行完以后的处理，无论是成功还是出现异常，都可以处理

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class CompletableFutureTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main.....start...");
        // handle方法示例代码：
        // handle: 方法执行完以后的处理
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 10;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).handle((result, exception) -> {
            if (result != null) {
                return result * 2;
            }
            if (exception != null) {
                return 0;
            }
            return 0;
        });
        //     R apply(T t, U u);
        System.out.println("结果是：" + future.get());
        System.out.println("main.....end...");
                /*
                main.....start...
                当前线程：22
                运行结果：1
                结果是：2
                main.....end...
                 */
    }
}
```

#####  3.5.5 线程串行化方法

业务场景：假如有两个异步任务A和B，B任务要等待A任务完全执行完并返回结果以后才能执行。就可以使用线程串行化方法来将A任务和B任务串联起来

![image-20230702205819382](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307231824174.png)

- `thenApply`方法:
  - 当一个线程依赖另一个线程时，获取上一个任务返回的结果，并返回当前任务的返回值

- `thenAccept`方法: 
  - 消费处理结果。接收任务的处理结果，并消费处理，无返回结果
- `thenRun`方法:
  - 只要上面的任务执行完成，就开始执行thenRun，只是处理完任务后，执行thenRun的后续操作
- 不带`Async`后缀的方法是同步执行的，带有`Async`默认是异步执行的。以上都要前置任务成功完成后才能执行



**thenRunAsync**方法使用示例(`thenRunAsync`方法不能获取到上一步的执行结果，且无返回值)：

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 线程串行化
 * 1）thenRun：不能获取到上一步的执行结果，无返回值
 *  .thenRunAsync(() -> {
 *             System.out.println("任务2启动了...");
 *         }, executor);
 */
public class CompletableFutureSeriesTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main.....start...");

        // thenRunAsync方法的使用：
        // thenRunAsync没有返回值且相对于前一个线程是异步执行的
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 10;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).thenRunAsync(() -> {
            System.out.println("任务2启动了");
        }, executor);
        System.out.println("main.....end...");
                /*
                main.....start...
                当前线程：22
                运行结果：1
                main.....end...
                任务2启动了
                 */
    }
}
```

**thenAcceptAsync**方法使用示例(能接收上一步结果，但是无返回值)：

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 线程串行化
 * 2）、thenAcceptAsync;能接受上一步结果，但是无返回值
 */
public class CompletableFutureSeriesTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main.....start...");
        // thenAcceptAsync方法的使用：
        // thenAcceptAsync方法: 能接收上一步结果，但是无返回值
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 5;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).thenAcceptAsync(result->{
            System.out.println("任务2启动了");
            System.out.println("上一步的运行结果是：" + result);
        },executor);

        // void accept(T t);
        System.out.println("main.....end...");
                /*
                main.....start...
                当前线程：22
                运行结果：2
                main.....end...
                任务2启动了
                上一步的运行结果是：2
                 */
    }
}
```

**thenApplyAsync**方法使用示例：

既可以能接收上一步的运行结果，也有有自己的返回值

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 线程串行化
 * 3）、thenApplyAsync：;能接受上一步结果，有返回值
 */
public class CompletableFutureSeriesTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main.....start...");
        // thenApplyAsync方法的使用：
        // thenApplyAsync方法: 既可以能接收上一步的运行结果，也有有自己的返回值
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 5;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).thenApplyAsync(res -> {
            System.out.println("任务2启动了");
            System.out.println("上一步的运行结果是：" + res);
            return "返回结果为" + res;
        }, executor);
        //  R apply(T t);
        // 打印返回值：
        System.out.println(future.get());  // future.get()是一个阻塞方法
        System.out.println("main.....end...");
                /*
                main.....start...
                当前线程：22
                运行结果：2
                任务2启动了
                上一步的运行结果是：2
                返回结果为2
                main.....end...        
                 */
    }
}
```

#####  3.5.6  两任务组合–都要完成

![image-20230702222501674](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307231824159.png)

- 两个任务必须都完成，才会触发第三个任务
- `thenCombine`:
  - 组合两个 future，获取两个future的返回结果，并返回当前任务的返回值
- `thenAcceptBoth`:
  - 组合两个future，获取两个future任务的返回结果，然后处理任务，没有返回值
- `runAfterBoth`:
  - 组合两个future，不需要获取future的结果，只需两个future处理完任务后处理该任务



**runAfterBothAsync**方法使用示例：

不能感知前两个的运行结果

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class CompletableFutureTwoTaskTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        /**
         * 两个都完成
         */
        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程...启动了" + Thread.currentThread().getId());
            int i = 10 / 5;
            System.out.println("任务1线程...结束");
            return i;
        }, executor);

        CompletableFuture<String> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程...启动了" + Thread.currentThread().getId());
            System.out.println("任务2线程...结束");
            return "hello";
        }, executor);

        // 1） runAfterBothAsync： 不能感知前两个的运行结果
        future01.runAfterBothAsync(future02, () -> {
            System.out.println("任务3开始");
        }, executor);
        System.out.println("main.....end...");
                    /*
                    任务1线程...启动了22
                    任务1线程...结束
                    任务2线程...启动了23
                    任务2线程...结束
                    main.....end...
                    任务3开始
                     */
    }
}
```



**thenAcceptBothAsync**方法：

可以感知到前两个任务的结果

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class CompletableFutureTwoTaskTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        /**
         * 两个都完成
         */
        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程...启动了" + Thread.currentThread().getId());
            int i = 10 / 5;
            System.out.println("任务1线程...结束");
            return i;
        }, executor);

        CompletableFuture<String> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程...启动了" + Thread.currentThread().getId());
            System.out.println("任务2线程...结束");
            return "hello";
        }, executor);
        // 2) thenAcceptBothAsync: 可以感知到前两个任务的结果
        future01.thenAcceptBothAsync(future02, (f1, f2) -> {
            System.out.println("任务3开始");
            System.out.println("得到之前的结果：future01的结果： " + f1 + "  future02的结果:" + f2);
        }, executor);
                /*
                任务1线程...启动了20
                任务1线程...结束
                任务2线程...启动了21
                任务2线程...结束
                main.....end...
                任务3开始
                得到之前的结果：future01的结果： 2  future02的结果:hello
                 */
        System.out.println("main.....end...");
    }
}
```



**thenAcceptBothAsync**示例代码:

可以感知到前两个任务的结果,并且可以有自己的返回值

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class CompletableFutureTwoTaskTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 两个都完成
         */
        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程...启动了" + Thread.currentThread().getId());
            int i = 10 / 5;
            System.out.println("任务1线程...结束");
            return i;
        }, executor);

        CompletableFuture<String> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程...启动了" + Thread.currentThread().getId());
            System.out.println("任务2线程...结束");
            return "hello";
        }, executor);
        // 2) thenAcceptBothAsync: 可以感知到前两个任务的结果
        // R apply(T t, U u);
        CompletableFuture<String> future = future01.thenCombineAsync(future02, (f1, f2) -> {
            return "future01:" + f1 + " future02:" + f2;
        }, executor);
        System.out.println("thenAcceptBothAsync示例代码返回值：" + future.get());
                /*
                任务1线程...启动了22
                任务1线程...结束
                任务2线程...启动了23
                任务2线程...结束
                thenAcceptBothAsync示例代码返回值：future01:2 future02:hello
                main.....end...        
                 */
        System.out.println("main.....end...");
    }
}
```

##### 3.5.7 两任务组合-一个完成

![image-20230702232316051](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307231824534.png)



- 两个任务只要有一个完成，就会触发第三个任务
- 两个任务中,任意一个future任务完成的时候,执行第三个任务
- `applyToEither`:
  - 两个任务有一个执行完成，获取它的返回值，处理任务并有新的返回值
- `acceptEither`:
  - 两个任务有一个执行完成，获取它的返回值，处理任务，没有新的返回值
- `runAfterEither`:
  - 两个任务有一个执行完成，不需要获取future的结果，处理任务，也没有返回值





`runAfterEitherAsync`方法使用示例代码：

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class CompletableFutureOneOfTaskTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 两个都完成
         */
        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程...启动了" + Thread.currentThread().getId());
            int i = 10 / 5;
            System.out.println("任务1线程...结束");
            return i;
        }, executor);
        CompletableFuture<String> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程...启动了" + Thread.currentThread().getId());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务2线程...结束");
            return "hello";
        }, executor);

        /**
         * 两个任务只要有一个完成，就可以执行任务三
         */
        // 1) runAfterEitherAsync: 不感知前面两个任务的结果，自己也没有返回值
        CompletableFuture<Void> future = future01.runAfterEitherAsync(future02, () -> {
            System.out.println("任务3开始");
        }, executor);
                    /*
                    任务1线程...启动了22
                    任务1线程...结束
                    任务2线程...启动了23
                    main.....end...
                    任务3开始
                    任务2线程...结束
                     */
        System.out.println("main.....end...");
    }
}
```

**acceptEitherAsync**方法示例代码：

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class CompletableFutureOneOfTaskTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 两个都完成
         */
        CompletableFuture<Object> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程...启动了" + Thread.currentThread().getId());
            int i = 10 / 5;
            System.out.println("任务1线程...结束");
            return i;
        }, executor);

        CompletableFuture<Object> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程...启动了" + Thread.currentThread().getId());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务2线程...结束");
            return "hello";
        }, executor);

        /**
         * 两个任务只要有一个完成，就可以执行任务三
         */
        // 2) acceptEitherAsync: 感知前面两个任务的结果，自己有返回值
        CompletableFuture<Void> future = future01.acceptEitherAsync(future02, (res) -> {
            System.out.println("任务3开始,之前的结果是：" + res);
        }, executor);
        //     void accept(T t);
        System.out.println("main.....end...");
                    /*
                    任务1线程...启动了22
                    任务1线程...结束
                    任务2线程...启动了23
                    main.....end...
                    任务3开始,之前的结果是：2
                    任务2线程...结束        
                     */
    }
}
```



**applyToEitherAsync**方法使用示例代码：

applyToEitherAsync: 能感知前面两个任务的结果且自己有返回值

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class CompletableFutureOneOfTaskTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 两个都完成
         */
        CompletableFuture<Object> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程...启动了" + Thread.currentThread().getId());
            int i = 10 / 5;
            System.out.println("任务1线程...结束");
            return i;
        }, executor);

        CompletableFuture<Object> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程...启动了" + Thread.currentThread().getId());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务2线程...结束");
            return "hello";
        }, executor);

        /**
         * 两个任务只要有一个完成，就可以执行任务三
         */
        // 3) applyToEitherAsync: 能感知前面两个任务的结果且自己有返回值
        CompletableFuture<String> future = future01.applyToEitherAsync(future02, (res) -> {
            System.out.println("任务3开始...之前的结果：" + res);
            return res.toString() + "-> 哈哈";
        }, executor);
        System.out.println("任务三的返回结果：" + future.get());
        System.out.println("main.....end...");
                    /*
                    任务1线程...启动了22
                    任务2线程...启动了23
                    任务1线程...结束
                    任务3开始...之前的结果：2
                    任务三的返回结果：2-> 哈哈
                    main.....end...
                     */
    }
}
```

#####  3.5.8 多任务组合

![image-20230702235635433](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307231824253.png)

`allOf`:等待所有任务完成

`anyof`:只要有一个任务完成

```java
package com.atguigu.gulimall.search.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureMultiTaskTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main.....start");
        /**
         * 多任务组合
         */
        CompletableFuture<String> futureImg = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品图片信息");
            return "hello.jpg";
        }, executor);

        CompletableFuture<String> futureAttr = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品属性");
            return "黑色+256G";
        }, executor);

        CompletableFuture<String> futureDesc = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("查询商品的介绍");
            return "华为";
        }, executor);

//        // 1) allOf使用
//        CompletableFuture<Void> allOf = CompletableFuture.allOf(futureImg, futureAttr, futureDesc);
//        allOf.get(); //等待所有结果完成
//        System.out.println(futureImg.get() + "  " + futureAttr.get() + "  " + futureDesc.get());
//                    /*
//                    main.....start
//                    查询商品图片信息
//                    查询商品属性
//                    查询商品的介绍
//                    hello.jpg  黑色+256G  华为
//                    main.....end...
//                     */

        // 2) anyOf
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(futureImg, futureAttr, futureDesc);
        anyOf.get();
        System.out.println(anyOf.get());
                /*
                main.....start
                查询商品图片信息
                查询商品属性
                hello.jpg
                main.....end...
                查询商品的介绍
                 */
        
        System.out.println("main.....end...");

    }
}
```



##  4.商品详情

###   4.1 搭建页面跳转环境

1.通过`switchhost`创建新的域名映射，`192.168.56.10 item.gulimall.com`。查看 `/mydata/nginx/conf/conf.d`目录下`gulimall.conf`的配置，发现已经可以匹配新的域名，所有配置可以不做修改

![image-20230720030730677](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307200307720.png)

2.修改`application.yml`，将域名为`item.gulimall.com`的请求转发到商品服务`gulimall-product`

```yaml
spring:
  cloud:
    gateway:
      routes:
## 将nginx过来的请求转发到商品服务
        - id: gulimall_host_route
          uri: lb://gulimall-product
          predicates:
            - Host=gulimall.com,item.gulimall.com
```

3.将商品详情页的`html`页面拷贝到`gulimall-product`的`template`目录下并重命名为`item.html`

![ ](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307200332651.png)

4.在`/mydata/nginx/html/static`目录下创建`item`目录，将详情页的静态资源上传到`item`目录

![image-20230720044555308](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307200445351.png)

5.将`item.html`中的图片和链接地址加上前缀`/static/item`

![image-20230720033917212](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307200339247.png)

6.修改检索服务`gulimall-search`的`list.html`。检索到商品以后，点击商品图片就会调转到`http://item.gulimall.com/${product.skuId}.html`

```js
<!--商品信息显示-->
<div class="rig_tab">
    <div th:each="product:${result.getProducts()}">
        <p class="da">
            <a th:href="|http://item.gulimall.com/${product.skuId}.html|">
                <img th:src="${product.skuImg}" class="dim">
            </a>
        </p>
    </div>
</div>
```

7.在商品服务`gulimall-product`中加上如下`ItemController`。在检索服务中点击商品图片，就会访问`http://item.gulimall.com/${product.skuId}.html`   并跳转到商品详情页`item.html`

```java
public class ItemController {
    /**
     * 展示当前sku的详情
     * @param skuId
     * @return
     */
    @GetMapping("/{skuId}.html")
    public String skuItem(@PathVariable("skuId") Long skuId) {
        log.info("准备查询{}的详情", skuId);
        return "item";
    }
}
```

8.测试：点击`检索服务`的商品图片，跳转到`商品服务`的商品详情页面



![image-20230720050605062](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307200506120.png)

###  4.2 封装商品详情页数据

`SkuItemVo`类：将商品详情页页面需要的数据封装为`SkuItemVo`类

```java
@Data
public class SkuItemVo {
    //1、sku基本信息获取  pms_sku_info
    SkuInfoEntity info;

    boolean hasStock = true;

    //2、sku的图片信息  pms_sku_images
    List<SkuImagesEntity> images;

    //3、获取spu的销售属性组合。
    List<SkuItemSaleAttrVo> saleAttr;

    //4、获取spu的介绍
    SpuInfoDescEntity desp;

    //5、获取spu的规格参数信息。
    List<SpuItemAttrGroupVo> groupAttrs;

    SeckillInfoVo seckillInfo;//当前商品的秒杀优惠信息

}
```

`SkuImagesEntity`：sku的图片信息 

```java
@Data
@TableName("pms_sku_images")
public class SkuImagesEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * sku_id
	 */
	private Long skuId;
	/**
	 * 图片地址
	 */
	private String imgUrl;
	/**
	 * 排序
	 */
	private Integer imgSort;
	/**
	 * 默认图[0 - 不是默认图，1 - 是默认图]
	 */
	private Integer defaultImg;
}
```

`SkuItemSaleAttrVo`：spu的销售属性组合

```java
@ToString
@Data
public class SkuItemSaleAttrVo {
    private Long attrId;
    private String attrName;
    private List<AttrValueWithSkuIdVo> attrValues;
}
```

`SpuInfoDescEntity`：spu的介绍

```java
@Data
@TableName("pms_spu_info_desc")
public class SpuInfoDescEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 商品id
     */
    @TableId(type = IdType.INPUT)
    private Long spuId;
    /**
     * 商品介绍
     */
    private String decript;
}
```

`SpuItemAttrGroupVo`：spu的规格参数信息

```java
@ToString
@Data
public class SpuItemAttrGroupVo {
    private String groupName;
    private List<Attr> attrs;
}
```

`SeckillInfoVo`: 当前商品的秒杀优惠信息

```java
@Data
public class SeckillInfoVo {

    /**
     * 活动id
     */
    private Long promotionId;

    /**
     * 活动场次id
     */
    private Long promotionSessionId;
    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 商品秒杀随机码
     */
    private String randomCode;
    /**
     * 秒杀价格
     */
    private BigDecimal seckillPrice;
    /**
     * 秒杀总量
     */
    private Integer seckillCount;
    /**
     * 每人限购数量
     */
    private Integer seckillLimit;
    /**
     * 排序
     */
    private Integer seckillSort;
    //当前商品秒杀的开始时间
    private Long startTime;
    //当前商品秒杀的结束时间
    private Long endTime;
}
```

###  4.3 商品详情页数据获取

#####  4.3.1 整体思路 & 代码框架

根据`skuId`获取页面需要的数据。页面需要的信息：

- 1.sku基本信息
- 2.sku的图片信息
- 3.spu的销售属性组合
- 4.spu的介绍
- 5.当前spu的规格参数信息

`ItemController`类：根据`skuId`获取页面需要的数据返回给页面`item.html`来进行渲染

```java
@Controller
public class ItemController {
    @Autowired
    SkuInfoService skuInfoService;
    /**
     * 展示当前sku的详情
     * @param skuId
     * @return
     */
    @GetMapping("/{skuId}.html")
    public String skuItem(@PathVariable("skuId") Long skuId, Model model) throws ExecutionException, InterruptedException {
        System.out.println("准备查询"+skuId+"详情");
        SkuItemVo vo = skuInfoService.item(skuId);
        model.addAttribute("item",vo);
        return "item";
    }
}
```

`SkuInfoService`：根据`skuId`获取页面需要的数据

```java
public interface SkuInfoService extends IService<SkuInfoEntity> {
    SkuItemVo item(Long skuId);
}
```

`SkuInfoServiceImpl`实现类：根据`skuId`获取页面需要的数据

```java
@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {
    @Autowired
    private SkuImagesService skuImagesService;

    @Autowired
    private SpuInfoDescService spuInfoDescService;

    @Autowired
    private AttrGroupService attrGroupService;
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Override
    public SkuItemVo item(Long skuId) {
        SkuItemVo skuItemVo = new SkuItemVo();
        // 1.sku基本信息获取  pms_sku_info表
        SkuInfoEntity info = getById(skuId);
        skuItemVo.setInfo(info);
        Long catalogId = info.getCatalogId();
        Long spuId = info.getSpuId();

        // 2.sku的图片信息获取  pms_sku_images表
        List<SkuImagesEntity> images = skuImagesService.getImagesBySkuId(skuId);
        skuItemVo.setImages(images);

        // 3.获取spu的销售属性组合
        List<SkuItemSaleAttrVo> saleAttrVos = skuSaleAttrValueService.getSaleAttrsBySpuId(spuId);
        skuItemVo.setSaleAttr(saleAttrVos);


        // 4.获取spu的介绍  pms_spu_info_desc表
        SpuInfoDescEntity spuInfoDescEntity = spuInfoDescService.getById(spuId);
        skuItemVo.setDesp(spuInfoDescEntity);

        // 5.获取当前spu的规格参数信息
        List<SpuItemAttrGroupVo> attrGroupVos = attrGroupService.getAttrGroupWithAttrsBySpuId(spuId, catalogId);
        skuItemVo.setGroupAttrs(attrGroupVos);

        return skuItemVo;
    }

}
```

#####  4.3.2 获取sku的图片信息

`SkuImagesService `类：

```java
public interface SkuImagesService extends IService<SkuImagesEntity> {
    List<SkuImagesEntity> getImagesBySkuId(Long skuId);
}
```

`SkuImagesServiceImpl`类：

```java
@Service("skuImagesService")
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesDao, SkuImagesEntity> implements SkuImagesService {
    @Override
    public List<SkuImagesEntity> getImagesBySkuId(Long skuId) {
        SkuImagesDao imagesDao = this.baseMapper;
        List<SkuImagesEntity> imagesEntities = imagesDao.selectList(new QueryWrapper<SkuImagesEntity>().eq("sku_id", skuId));
        return imagesEntities;
    }
}
```

#####  4.3.3 获取spu的销售属性组合

`SkuSaleAttrValueService`类：

```java
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {
    List<SkuItemSaleAttrVo> getSaleAttrsBySpuId(Long spuId);
}
```

`SkuSaleAttrValueServiceImpl`类：

```java
@Service("skuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValueEntity> implements SkuSaleAttrValueService {
    @Override
    public List<SkuItemSaleAttrVo> getSaleAttrsBySpuId(Long spuId) {
        SkuSaleAttrValueDao dao = this.baseMapper;
        List<SkuItemSaleAttrVo> saleAttrVos = dao.getSaleAttrsBySpuId(spuId);
        return saleAttrVos;
    }
}
```

`SkuSaleAttrValueDao`，mapper接口：`getSaleAttrsBySpuId`方法——分析当前spu有多少个sku，所有sku涉及到的属性组合

```java
@Mapper
public interface SkuSaleAttrValueDao extends BaseMapper<SkuSaleAttrValueEntity> {
    // 分析当前spu有多少个sku，所有sku涉及到的属性组合
    List<SkuItemSaleAttrVo> getSaleAttrsBySpuId(@Param("spuId") Long spuId);
}
```

`SkuSaleAttrValueDao.xml` sql查询语句:`getSaleAttrsBySpuId`方法——分析当前spu有多少个sku，所有sku涉及到的属性组合

```sql
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.atguigu.gulimall.product.dao.SkuSaleAttrValueDao">

    <!-- 可根据自己的需求，是否要使用 -->
    <resultMap type="com.atguigu.gulimall.product.entity.SkuSaleAttrValueEntity" id="skuSaleAttrValueMap">
        <result property="id" column="id"/>
        <result property="skuId" column="sku_id"/>
        <result property="attrId" column="attr_id"/>
        <result property="attrName" column="attr_name"/>
        <result property="attrValue" column="attr_value"/>
        <result property="attrSort" column="attr_sort"/>
    </resultMap>


    <resultMap id="SkuItemSaleAttrVo" type="com.atguigu.gulimall.product.vo.SkuItemSaleAttrVo">
        <result column="attr_id" property="attrId"></result>
        <result column="attr_name" property="attrName"></result>
        <collection property="attrValues" ofType="com.atguigu.gulimall.product.vo.AttrValueWithSkuIdVo">
            <result column="attr_value" property="attrValue"></result>
            <result column="sku_ids" property="skuIds"></result>
        </collection>
    </resultMap>

    <!--  分析当前spu有多少个sku，所有sku涉及到的属性组合  -->
    <select id="getSaleAttrsBySpuId" resultMap="SkuItemSaleAttrVo">
        SELECT ssav.`attr_id`                       attr_id,
               ssav.`attr_name`                     attr_name,
               ssav.`attr_value`,
               GROUP_CONCAT(DISTINCT info.`sku_id`) sku_ids
        FROM `pms_sku_info` info
                 LEFT JOIN `pms_sku_sale_attr_value` ssav ON ssav.`sku_id` = info.`sku_id`
        WHERE info.`spu_id` = #{spuId}
        GROUP BY ssav.`attr_id`, ssav.`attr_name`, ssav.`attr_value`
    </select>
</mapper>
```

`kuSaleAttrValueDao`查询测试：

```java
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AttrGroupDaoTest {
    @Autowired
    SkuSaleAttrValueDao skuSaleAttrValueDao;
    @Test
    public void test02() {
        List<SkuItemSaleAttrVo> saleAttrsBySpuId = skuSaleAttrValueDao.getSaleAttrsBySpuId(13L);
        System.out.println(saleAttrsBySpuId);
// [SkuItemSaleAttrVo(attrId=9, attrName=颜色, attrValues=[AttrValueWithSkuIdVo(attrValue=白色, skuIds=12,13,14), AttrValueWithSkuIdVo(attrValue=紫色, skuIds=24,25,26), AttrValueWithSkuIdVo(attrValue=红色, skuIds=21,22,23), AttrValueWithSkuIdVo(attrValue=绿色, skuIds=15,16,17), AttrValueWithSkuIdVo(attrValue=黄色, skuIds=18,19,20), AttrValueWithSkuIdVo(attrValue=黑色, skuIds=9,10,11)]), SkuItemSaleAttrVo(attrId=12, attrName=版本, attrValues=[AttrValueWithSkuIdVo(attrValue=128GB , skuIds=9,12,15,18,21,24), AttrValueWithSkuIdVo(attrValue=256GB, skuIds=10,13,16,19,22,25), AttrValueWithSkuIdVo(attrValue=64GB, skuIds=11,14,17,20,23,26)])] 
    }
}
```

#####  4.3.4 获取当前spu的规格参数信息

`AttrGroupService`类：

```java
public interface AttrGroupService extends IService<AttrGroupEntity> {
    List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId);
}
```

`AttrGroupServiceImpl`实现类：

```java
@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {
    @Override
    public List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId) {
        // 1.查出当前spu对应的所有属性的分组信息以及当前分组下的所有属性对应的值
        AttrGroupDao baseMapper = this.getBaseMapper();
        List<SpuItemAttrGroupVo> vos = baseMapper.getAttrGroupWithAttrsBySpuId(spuId, catalogId);
        return vos;
    }
}
```

`AttrGroupDao`接口：

```java
@Mapper
public interface AttrGroupDao extends BaseMapper<AttrGroupEntity> {
    List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(@Param("spuId") Long spuId, @Param("catalogId") Long catalogId);
}
```

`AttrGroupDao.xml`映射文件：

```sql
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.atguigu.gulimall.product.dao.AttrGroupDao">

	<!-- 可根据自己的需求，是否要使用 -->
    <resultMap type="com.atguigu.gulimall.product.entity.AttrGroupEntity" id="attrGroupMap">
        <result property="attrGroupId" column="attr_group_id"/>
        <result property="attrGroupName" column="attr_group_name"/>
        <result property="sort" column="sort"/>
        <result property="descript" column="descript"/>
        <result property="icon" column="icon"/>
        <result property="catelogId" column="catelog_id"/>
    </resultMap>

    <!--    resultType 返回集合里面元素的类型，只要有嵌套属性就要封装自定义结果-->
    <resultMap id="spuItemAttrGroupVo" type="com.atguigu.gulimall.product.vo.SpuItemAttrGroupVo">
        <!--        spu_id  attr_group_name  attr_group_id  attr_id  attr_name             attr_value  -->
        <result property="groupName" column="attr_group_name"></result>
        <collection property="attrs" ofType="com.atguigu.gulimall.product.vo.Attr">
            <result column="attr_name" property="attrName"></result>
            <result column="attr_value" property="attrValue"></result>
        </collection>
    </resultMap>


    <select id="getAttrGroupWithAttrsBySpuId"
            resultMap="spuItemAttrGroupVo">
        SELECT
            pav.`spu_id`,
            ag.`attr_group_name`,
            ag.`attr_group_id`,
            aar.`attr_id`,
            attr.`attr_name`,
            pav.`attr_value`
        FROM `pms_attr_group` ag
                 LEFT JOIN `pms_attr_attrgroup_relation` aar ON aar.`attr_group_id` = ag.`attr_group_id`
                 LEFT JOIN `pms_attr` attr ON attr.`attr_id` = aar.`attr_id`
                 LEFT JOIN `pms_product_attr_value` pav ON pav.`attr_id` = attr.`attr_id`
        WHERE ag.catelog_id=#{catalogId} AND pav.`spu_id`=#{spuId}
    </select>
</mapper>
```

`AttrGroupDao`查询测试：

```java
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AttrGroupDaoTest {
    @Autowired
    AttrGroupDao attrGroupDao;
    @Test
    public void test() {
        List<SpuItemAttrGroupVo> attrGroupWithAttrsBySpuId = attrGroupDao.getAttrGroupWithAttrsBySpuId(13L, 225L);
        System.out.println(attrGroupWithAttrsBySpuId);
// [SpuItemAttrGroupVo(groupName=主体, attrs=[Attr(attrId=null, attrName=入网型号, attrValue=A2217), Attr(attrId=null, attrName=上市年份, attrValue=2018)]), SpuItemAttrGroupVo(groupName=基本信息, attrs=[Attr(attrId=null, attrName=机身长度（mm）, attrValue=158.3), Attr(attrId=null, attrName=机身材质工艺, attrValue=以官网信息为准)]), SpuItemAttrGroupVo(groupName=主芯片, attrs=[Attr(attrId=null, attrName=CPU品牌, attrValue=以官网信息为准), Attr(attrId=null, attrName=CPU型号, attrValue=A13仿生)])]

    }
}
```

###  4.4 详情页基本信息渲染

1.商品标题、商品价格渲染：

```js
<div class="box-two">
    <div class="box-name" th:text="${item.info.skuTitle}">
        华为 HUAWEI Mate 10 6GB+128GB 亮黑色 移动联通电信4G手机 双卡双待
    </div>
    <div class="box-hide" th:text="${item.info.skuSubtitle}">预订用户预计11月30日左右陆续发货！麒麟970芯片！AI智能拍照！
        <a href="/static/item/"><u>华为 HUAWEI Mate 10 Pro 10:08 限时限量抢！</u></a>
    </div>
    <div class="box-yuyue">
        <div class="yuyue-one">
            <img src="/static/item/img/7270ffc3baecdd448958f9f5e69cf60f.png" alt=""/> 预约抢购
        </div>
    </div>
    <div class="box-summary clear">
        <ul>
            <li>
                <span>￥</span>
                <span th:text="${#numbers.formatDecimal(item.info.price,3,2)}">4499.00</span>
            </li>
        </ul>
    </div>
```

2.商品大图渲染：

```js
<div class="imgbox">
    <div class="probox">
        <img class="img1" alt="" th:src="${item.info.skuDefaultImg}">
        <div class="hoverbox"></div>
    </div>
    <div class="showbox">
        <img class="img1" alt="" th:src="${item.info.skuDefaultImg}">
    </div>
</div>
```

3.商品有货无货渲染：

```js
<li>
    <span th:text="${item.hasStock?'有货':'无货'}">无货</span>， 此商品暂时售完
</li>
```

4.商品图片渲染：

```js
<div class="box-lh-one">
    <ul>
        <li th:each="img:${item.images}" th:if="${!#strings.isEmpty(img.imgUrl)}"><img th:src="${img.imgUrl}" /></li>
    </ul>
</div>
```

5.商品属性渲染：

```js
<div class="box-attr-3">
    <div class="box-attr clear" th:each="attr:${item.saleAttr}">
        <dl>
            <dt>选择[[${attr.attrName}]]</dt>
            <dd th:each="vals:${attr.attrValues}"
            >
                <a class="sku_attr_value"
                   th:attr="skus=${vals.skuIds},class=${#lists.contains(#strings.listSplit(vals.skuIds,','), item.info.skuId.toString())?'sku_attr_value checked':'sku_attr_value'}"  >
                    [[${vals.attrValue}]]
                    <!--											<img src="/static/item/img/59ddfcb1Nc3edb8f1.jpg" /> -->
                </a>
            </dd>
        </dl>
    </div>
</div>
```

7.商品详情，规格与包装渲染：

```js
<!--商品详情-->
<div class="huawei">
    <ul class="xuanxiangka">
        <li class="jieshoa actives" id="li1">
            <div class="shanpinsssss">
                <img class="xiaoguo" th:src="${descp}"
                     th:each="descp:${#strings.listSplit(item.desp.decript,',')}"/>
                <div class="guiGebox guiGebox1">
                    <div class="guiGe" th:each="guige:${item.groupAttrs}">
                        <h3 th:text="${guige.groupName}">主体</h3>
                        <dl>
                            <dt>品牌</dt>
                            <dd>华为(HUAWEI)</dd>
                            <dt>型号</dt>
                            <dd class="Ptable-tips">
                                <a href="/static/item/#"><i>？</i></a>
                            </dd>
                            <dd>ALP-AL00</dd>
                            <dt>入网型号</dt>
                            <dd class="Ptable-tips">
                                <a href="/static/item/#"><i>？</i></a>
                            </dd>
                            <dd>ALP-AL00</dd>
                            <dt>上市年份</dt>
                            <dd>2017年</dd>
                            <dt>上市时间</dt>
                            <dd>10月</dd>
                        </dl>
                    </div>
                    <div class="package-list">
                        <h3>包装清单</h3>
                        <p>手机（含内置电池） X 1、5A大电流华为SuperCharge充电器X 1、5A USB数据线 X 1、半入耳式线控耳机
                            X 1、快速指南X 1、三包凭证 X 1、取卡针 X 1、保护壳 X 1</p>
                    </div>
                </div>
            </div>
        </li>
        <li class="baozhuang actives" id="li2">
            <div class="guiGebox">
                <div class="guiGe" th:each="group:${item.groupAttrs}">
                    <h3 th:text="${group.groupName}">主体</h3>
                    <dl>
                        <div th:each="attr:${group.attrs}">
                            <dt th:text="${attr.attrName}">品牌</dt>
                            <dd th:text="${attr.attrValue}">华为(HUAWEI)</dd>
                        </div>
                    </dl>
                </div>
            </div>
        </li>
</div>
```

###  4.5 详情页销售属性渲染

需求：

- 销售属性渲染：渲染当前商品销售属性，属于当前商品的具体销售属性`标红`
- sku组合切换：点击具体属性时商品切换到具体细分的商品详情页

![image-20230723172546822](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307231725925.png)

销售属性渲染:

```js
<div class="box-attr-3">
    <div class="box-attr clear" th:each="attr:${item.saleAttr}">
        <dl>
            <dt>选择[[${attr.attrName}]]</dt>
            <dd th:each="vals:${attr.attrValues}"
                >
                <a class="sku_attr_value"
                   th:attr="skus=${vals.skuIds},class=${#lists.contains(#strings.listSplit(vals.skuIds,','), item.info.skuId.toString())?'sku_attr_value checked':'sku_attr_value'}"  >
                        [[${vals.attrValue}]]
<!--											<img src="/static/item/img/59ddfcb1Nc3edb8f1.jpg" /> -->
                </a>
            </dd>
        </dl>
    </div>
</div>
```

sku组合切换：

```js
<script>
	$(".sku_attr_value").click(function(){
		//1、点击的元素先添加上自定义的属性。为了识别我们是刚才被点击的
		var skus = new Array();
		$(this).addClass("clicked");
		var curr = $(this).attr("skus").split(",");
		//当前被点击的所有sku组合数组放进去
		skus.push(curr);
		//去掉同一行的所有的checked
		$(this).parent().parent().find(".sku_attr_value").removeClass("checked");

		$("a[class='sku_attr_value checked']").each(function(){
			skus.push($(this).attr("skus").split(","));
		});
		console.log(skus);

		//2、取出他们的交集，得到skuId
		var filterEle = skus[0];
		for(var i = 1;i<skus.length;i++){
			filterEle = $(filterEle).filter(skus[i]);
		}

		console.log(filterEle[0]);
		location.href = "http://item.gulimall.com/"+filterEle[0]+".html";

		//4、跳转
	});
	$(function(){
		$(".sku_attr_value").parent().css({"border":"solid 1px #CCC"});
		$("a[class='sku_attr_value checked']").parent().css({"border":"solid 1px red"});
	})
</script>
```

###  4.6 异步编排优化

异步编排优化:将`sku基本信息获取`、`sku的图片信息获取`、`获取spu的销售属性组合`、`获取spu的介绍 `、`获取spu的规格参数信息`几个操作进行异步编排，提高系统的并发能力

1.在商品服务`gulimall-product`中进行线程池配置：

` MyThreadConfig `:

```java
@Configuration
public class MyThreadConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolConfigProperties pool){
       return new ThreadPoolExecutor(pool.getCoreSize(),
                pool.getMaxSize(),pool.getKeepAliveTime(),
                TimeUnit.SECONDS,new LinkedBlockingDeque<>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

    }
}
```

`ThreadPoolConfigProperties`:

```java
@ConfigurationProperties(prefix = "gulimall.thread")
@Component
@Data
public class ThreadPoolConfigProperties {
    private Integer coreSize;
    private Integer maxSize;
    private Integer keepAliveTime;
}
```

`application.properties`配置文件：

```properties
gulimall.thread.core-size=20
gulimall.thread.max-size=200
gulimall.thread.keep-alive-time=10
```

2.异步编排优化实现：

`SkuInfoServiceImpl`:

```java
@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {
    @Autowired
    private SkuImagesService skuImagesService;

    @Autowired
    private SpuInfoDescService spuInfoDescService;

    @Autowired
    private AttrGroupService attrGroupService;
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    // 注入配置好的线程池
    @Autowired
    ThreadPoolExecutor executor;

    // 异步编排优化后
    @Override
    public SkuItemVo item(Long skuId) throws ExecutionException, InterruptedException {
        SkuItemVo skuItemVo = new SkuItemVo();

        CompletableFuture<SkuInfoEntity> infoFuture = CompletableFuture.supplyAsync(() -> {
            // 1.sku基本信息获取  pms_sku_info表
            SkuInfoEntity info = getById(skuId);
            skuItemVo.setInfo(info);
            return info;
        }, executor);

        CompletableFuture<Void> saleAttrFuture = infoFuture.thenAcceptAsync((res) -> {
            //3、获取spu的销售属性组合。
            List<SkuItemSaleAttrVo> saleAttrVos = skuSaleAttrValueService.getSaleAttrsBySpuId(res.getSpuId());
            skuItemVo.setSaleAttr(saleAttrVos);
        }, executor);


        CompletableFuture<Void> descFuture = infoFuture.thenAcceptAsync(res -> {
            //4、获取spu的介绍  pms_spu_info_desc
            SpuInfoDescEntity spuInfoDescEntity = spuInfoDescService.getById(res.getSpuId());
            skuItemVo.setDesp(spuInfoDescEntity);
        }, executor);


        CompletableFuture<Void> baseAttrFuture = infoFuture.thenAcceptAsync(res -> {
            //5、获取spu的规格参数信息。
            List<SpuItemAttrGroupVo> attrGroupVos = attrGroupService.getAttrGroupWithAttrsBySpuId(res.getSpuId(), res.getCatalogId());
            skuItemVo.setGroupAttrs(attrGroupVos);
        }, executor);


        //2、sku的图片信息  pms_sku_images
        CompletableFuture<Void> imageFuture = CompletableFuture.runAsync(() -> {
            List<SkuImagesEntity> images = skuImagesService.getImagesBySkuId(skuId);
            skuItemVo.setImages(images);
        }, executor);

        // 等到所有任务都完成
        CompletableFuture.allOf(saleAttrFuture,descFuture,baseAttrFuture,imageFuture).get();

        return skuItemVo;

    }

}
```

