<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1.项目简介](#1%E9%A1%B9%E7%9B%AE%E7%AE%80%E4%BB%8B)
  - [1.1 电商模式](#11-%E7%94%B5%E5%95%86%E6%A8%A1%E5%BC%8F)
  - [1.2 项目技术 & 特色 & 项目架构图](#12-%E9%A1%B9%E7%9B%AE%E6%8A%80%E6%9C%AF--%E7%89%B9%E8%89%B2--%E9%A1%B9%E7%9B%AE%E6%9E%B6%E6%9E%84%E5%9B%BE)
- [2.分布式基础概念](#2%E5%88%86%E5%B8%83%E5%BC%8F%E5%9F%BA%E7%A1%80%E6%A6%82%E5%BF%B5)
    - [2.1 微服务](#21-%E5%BE%AE%E6%9C%8D%E5%8A%A1)
    - [2.2 集群&分布式&节点](#22-%E9%9B%86%E7%BE%A4%E5%88%86%E5%B8%83%E5%BC%8F%E8%8A%82%E7%82%B9)
    - [2.3 远程调用](#23-%E8%BF%9C%E7%A8%8B%E8%B0%83%E7%94%A8)
    - [2.4 负载均衡](#24-%E8%B4%9F%E8%BD%BD%E5%9D%87%E8%A1%A1)
    - [2.5 服务注册/发现 & 注册中心](#25-%E6%9C%8D%E5%8A%A1%E6%B3%A8%E5%86%8C%E5%8F%91%E7%8E%B0--%E6%B3%A8%E5%86%8C%E4%B8%AD%E5%BF%83)
    - [2.6 配置中心](#26-%E9%85%8D%E7%BD%AE%E4%B8%AD%E5%BF%83)
    - [2.7 服务熔断 & 服务降级](#27-%E6%9C%8D%E5%8A%A1%E7%86%94%E6%96%AD--%E6%9C%8D%E5%8A%A1%E9%99%8D%E7%BA%A7)
    - [2.8 API网关](#28-api%E7%BD%91%E5%85%B3)
- [3.目录导航](#3%E7%9B%AE%E5%BD%95%E5%AF%BC%E8%88%AA)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## 1.项目简介

谷粒商城: 谷粒商城是个B2C模式的电商平台，销售自营商品给客户

### 1.1 电商模式

- 市面上有5种常见的电商模式B2B、B2C、C2B、C2C、020
- B2B模式
    - B2B (Business to Business)，是指商家与商家建立的商业关系。如:阿里巴巴
- B2C模式
    - B2C(Business to Consumer)，就是我们经常看到的供应商直接把商品卖给用户，即“商对客"
      模式，也就是通常说的商业零售，直接面向消费者销售产品和服务。如:苏宁易购、京东、天猫、小米商城

- C2B模式
    - C2B (Customer to Business)，即消费者对企业。先有消费者需求产生而后有企业生产，即先有消费者提出需求，后有生产企业按需求组织生产
- C2C模式
    - C2C (Customer to Consumer)，客户之间自己把东西放上网去卖，如:淘宝，闲鱼
- O2O模式
    - O2O即 online To
      Offline，也即将线下商务的机会与互联网结合在了一起，让互联网成为线下交易的前台。线上快速支付，线下优质服务。如:
      饿了么，美团，淘票票，京东到家

### 1.2 项目技术 & 特色 & 项目架构图

- 前后分离开发，并开发基于vue的后台管理系统
- SpringCloud全新的解决方案
- 应用监控、限流、网关、熔断降级等分布式方案全方位
- 涉及透彻讲解分布式事多分布式锁等分布式系统的难点
- 分析高并发场景的编码方式，线程池，异步编排等使用
- 压力测试与性能优化
- 各种集群技术的区别以及使用
- CI/CD使用
- 项目微服务架构图：

![image-20230301230237903](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303012337356.jpg)

微服务划分图：

![image-20230301233404279](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303012337644.png)

## 2.分布式基础概念

#### 2.1 微服务

      微服务架构风格，就像是把一个单独的应用程序开发为一套小服务，每个小服务运行在自己的进程中，并使用轻量级机制通信，通常是HTTP API。这些服务围绕业务能力来构建，并通过完全白动化部署机制来独立部署。这些服务使用不同的编程语言书写,以及不同数据存储技术，并保持最低限度的集中式管理

简而言之: 拒绝大型单体应用，基于业务边界进行服务细化坼分，各个服务独立部署运行

#### 2.2 集群&分布式&节点

- 集群是个物理形态，分布式是个工作方式。

- 只要是一堆机器，就可以叫集群，他们是不是一起协作着干活，这个谁也不知道;

- 《分布式系统原理与范型》定义:
    - “分布式系统是若干独立计算机的集合，这些计算机对于用户来说就像单个相关系统”.分布式系统( distributed
      system）是建立在网络之上的软件系统
    - 分布式是指将不同的业务分布在不同的地方
    - 集群指的是将几台服务器集中在一起，实现同一业务
    - 例如:京东是一个分布式系统，众多业务运行在不同的机器，所有业务构成一个大型的业务集群。每一个小的业务，比如用户系统，访问压力大的时候一台服务器是不够的。我们就应该将用户系统部署到多个服务器，也就是每一个业务系统也可以做集群化
- 分布式中的每一个节点，都可以做集群。而集群并不一定就是分布式的。
- 节点: 集群中的一个服务器

#### 2.3 远程调用

在分布式系统中，各个服务可能处于不同主机，但是服务之间不可避免的需要互相调用，我们称为远程调用。

SpringCloud,.中使用HTTP+JSON的方式完成远程调用

![image-20230301224928509](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303012337554.png)

#### 2.4 负载均衡

![image-20230301225021067](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303012337599.png)

- 分布式系统中，A服务需要调用B服务，B服务在多台机器中都存在，A调用任意一个服务器均可完成功能
- 为了使每一个服务器都不要太忙或者太闲，我们可以负载均衡的调用每一个服务器，提升网站的健壮性
- 常见的负载均衡算法:
    - 轮询:为第一个请求选择健康池中的第一个后端服务器，然后按顺序往后依次选择，直到最后一个，然后循环
    - 最小连接:优先选择连接数最少，也就是压力最小的后端服务器，在会话较长的情况下可以考虑采取这种方式
    - 散列。根据请求源的 IP的散列(hash)来选择要转发的服务器。这种方式可以一定程度上保证特定用户能连接到相同的服务器。如果你的应用需要处理状态而要求用户能连接到和之前相同的服务器，可以考虑采取这种方式

#### 2.5 服务注册/发现 & 注册中心

A服务调用B服务，A服务并不知道B服落当前在哪几台服务器有，哪些正常的，哪些服务已经下线。解决这个问题可以引入注册中心;

![image-20230301225358474](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303012338364.png)

如果某些服务下线，我们其他人可以实时的感知到其他服务的状态，从而避免调用不可用的服务

#### 2.6 配置中心

![image-20230301225517252](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303012338265.png)

每一个服务最终都有大量的配置，并且每个服务都可能部署在多台机器上。我们经常需要变更配置，我们可以让每个服务在配置中心获取自己的配置

配置中心用来集中管理微服务的配置信息

#### 2.7 服务熔断 & 服务降级

- 在微服务架构中，微服条之间通过网络讲行通信,存在相互依赖，当其中一个服务不可用时，有可能会造成雪崩效应。要防止这样的情况，必须要有容错机制来保护服务。

![image-20230301225822585](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303012338057.png)

- 服务熔断
    - 设置服务的超时，当被调用的服务经常失败，到达某个阙值，我们可以开启断路保护机制，后来的请求不再去调用这个服务。本地直接返回默认的数据
- 服务降级
    - 在运维期间，当系统处于高峰期，系统资源紧张，我们可以让非核心业务降级运行。降级:
      某些服务不处理,或者简单处理【抛异常、返回NULL、调用Mock数据、调用Fallback 处理逻辑】

#### 2.8 API网关

- 在微服务架构中，APl Gateway作为整体架构的重要组件，它抽象了微服务中都需要的公共功能，同时提供了客户端负载均衡，服务自动熔断，灰度发布，统一认证，限流流控，日志统计等丰富的功能，帮助我们解决很多API管理难题



## 3.目录导航

[一、环境，微服务项目搭建](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/001_gulimail/README.md)

- [1.安装linux虚拟机](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/001_gulimail/README.md)
- [2.安装Docker](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/001_gulimail/README.md#2docker)
- [3.docker安装mysql](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/001_gulimail/README.md#3dockermysql)
- [4.docker安装redis](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/001_gulimail/README.md#4dockerredis)
- [5.开发环境统一](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/001_gulimail/README.md#5)
- [6.创建项目微服务 | 逆向工程搭建](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/001_gulimail/README.md#6)

[二、分布式组件](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/002_gulimall/README.md)

- [1.Spring Cloud Alibaba简介](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/002_gulimall/README.md#1spring-cloud-alibaba)
- [2. Spring Cloud Alibaba Nacos 服务注册发现](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/002_gulimall/README.md#2-spring-cloud-alibaba-nacos-)
- [3. Spring Cloud Feign远程调用](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/002_gulimall/README.md#3-spring-cloud-feign-)
- [4. Spring  Cloud Alibaba-Nacos 配置中心](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/002_gulimall/README.md#4-cloud-alibaba-nacos-)
- [5. Spring Cloud Gateway网关](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/002_gulimall/README.md#5-spring-cloud-gateway)

[三、前端开发基础知识&快速入门](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/003_ES6/README.md)

- [1.ES6核心：变量、解构、函数优化、对象优化、map和reduce、Promise异步编排、模块化](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/003_ES6/README.md#1es6)
- [2.vue：MVVM思想、vue基本语法、vue指令、组件化、生命周期钩子函数](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/004_vue2/README.md)
- [3.vue模块化开发、vue整合ElementUI](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/005_dev-demo/README.md)

[四、商品服务](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/006_gulimall/README.md)

- [1.商品三级分类：树形结构、网关配置跨域、三级分类CRUD](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/006_gulimall/README.md#1)
- [2.三级分类前端编写：树形展示、拖拽功能、节点批量删除](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/006_renren-fast-vue/README.md#2-)
- [3.品牌管理：SpringCloud Alibaba-OSS使用、文件上传功能、后端校验实现、统一异常处理](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/006_renren-fast-vue/README.md#3-)
- [4.属性分组：SPU和SKU、规格参数、分页实现、销售属性、属性分组和属性关联](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/007_gulimall/README.md)
- [5.商品服务页面：整合thymeleaf、渲染一级、二级、三级分类数据](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/008_gulimall/README.md#8)

[五、库存服务](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/007_gulimall/README.md#6-)

- [获取库存列表、查询商品库存服务、spu管理](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/007_gulimall/README.md#6-)

[六、Elasticsearch](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/008_gulimall/README.md)

- [1.简介](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/008_gulimall/README.md#1)
- [2.安装elasticsearch、kibana](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/008_gulimall/README.md#3elasticsearchkibana)
- [3.初步检索：索引文档、查询文档、更新文档、删除文档、bulk批量API](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/008_gulimall/README.md#4)
- [4.进阶检索：SearchAPI、Query DSL、Mapping 映射、分词](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/008_gulimall/README.md#5-)
- [5.Elasticsearch-Rest-Client：整合elasticsearch-rest-high-level-client、保存或更新数据、检索数据](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/008_gulimall/README.md#6-elasticsearch-rest-client)

[七、商品上架](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/008_gulimall/README.md#7)

- [sku和spu在es中存储、es检索、es数据模型、nested数据类型、填充ES属性模型并发送给ES保存](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/008_gulimall/README.md#7)

[八、搭建域名访问环境](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/008_gulimall/README.md#9)

- [反向代理配置、nginx配置、配置nginx将请求转发到网关](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/008_gulimall/README.md#9)

[九、压力测试 | 性能监控](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/009_gulimall/README.md#1%E5%8E%8B%E5%8A%9B%E6%B5%8B%E8%AF%95)

- [JMeter压力测试、jvm内存模型、jconsole 与jvisualvn、nginx动静分离、三级分类数据获取优化](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/tree/master/007_E-Commerce-Guide/009_gulimall#1%E5%8E%8B%E5%8A%9B%E6%B5%8B%E8%AF%95)

[十、缓存与分布式锁](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/009_gulimall/README.md#3-%E7%BC%93%E5%AD%98%E4%B8%8E%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81)

- [1.缓存使用、整合redis、使用redis缓存优化三级分类数据获取](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/009_gulimall/README.md#31-%E7%BC%93%E5%AD%98%E4%BD%BF%E7%94%A8)
- [2.高并发下缓存失效问题：缓存穿透、缓存雪崩、缓存击穿](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/009_gulimall/README.md#34-%E9%AB%98%E5%B9%B6%E5%8F%91%E4%B8%8B%E7%BC%93%E5%AD%98%E5%A4%B1%E6%95%88%E9%97%AE%E9%A2%98)
- [3.加锁解决缓存击穿问题-采用本地锁](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/009_gulimall/README.md#35-%E5%8A%A0%E9%94%81%E8%A7%A3%E5%86%B3%E7%BC%93%E5%AD%98%E5%87%BB%E7%A9%BF%E9%97%AE%E9%A2%98-%E9%87%87%E7%94%A8%E6%9C%AC%E5%9C%B0%E9%94%81)
- [4.加锁解决缓存击穿问题-分布式锁](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/009_gulimall/README.md#36-%E5%8A%A0%E9%94%81%E8%A7%A3%E5%86%B3%E7%BC%93%E5%AD%98%E5%87%BB%E7%A9%BF%E9%97%AE%E9%A2%98-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81)
- [5.分布式锁演进](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/009_gulimall/README.md#37--%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E6%BC%94%E8%BF%9B%E9%80%90%E6%AD%A5%E6%8E%A8%E5%AF%BC363%E4%B8%AD%E7%9A%84%E6%9C%80%E7%BB%88%E5%AE%9E%E7%8E%B0)
- [6.redission分布式锁](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/009_gulimall/README.md#4redission%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81)
- [7.缓存数据一致性：双写模式、失效模式、Canal解决缓存数据一致性](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/009_gulimall/README.md#5%E7%BC%93%E5%AD%98%E6%95%B0%E6%8D%AE%E4%B8%80%E8%87%B4%E6%80%A7)
- [8.Spring Cache：整合spring cache、注解@Cacheable、自定义缓存配置、注解@CacheEvict、注解@Caching](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/010_gulimall/README.md#1spring-cache)

[十一、检索服务](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/010_gulimall/README.md#2%E6%A3%80%E7%B4%A2%E6%9C%8D%E5%8A%A1)

- [检索业务分析、检索DSL语句构建、嵌入式类型的聚合查询、页面数据渲染、构建面包屑导航功能](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/010_gulimall/README.md)

[十二、线程池](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/010_gulimall/README.md)

- [初始化线程4种方式、线程池的七大参数](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/010_gulimall/README.md)
- [CompletableFuture异步：计算完成时回调方法、handle方法、线程串行化方法、两任务组合、多任务组合](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/010_gulimall/README.md#3%E5%BC%82%E6%AD%A5%E7%BA%BF%E7%A8%8B%E6%B1%A0)

[十三、商品详情](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/010_gulimall/README.md)

- [搭建页面跳转环境、商品详情页数据获取、详情页数据渲染、异步编排优化](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/010_gulimall/README.md)

[十四、认证服务](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/011_gulimall/README.md)

- [认证服务环境搭建、页面跳转实现、用户注册功能、springMVC视图映射、阿里云短信验证码](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/011_gulimall/README.md)
- [注册信息保存、MD5盐值加密、注册功能实现](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/011_gulimall/README.md)
- [用户登录、社交登录、OAuth2.0、微博登陆、spring session解决session共享问题、单点登录](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/011_gulimall/README.md)

[十五、购物车功能](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/011_gulimall/README.md)

- [购物车需求、数据存储封装、离线在线购物车、ThreadLocal的使用、RedirectAttributes防刷新](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/011_gulimall/README.md)

[十六、消息中间件—RabbitMQ](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/011_gulimall/README.md)

- [RabbitMQ核心概念、RabbitMQ安装、整合使用案例、消息确认机制-可靠抵达、延时队列(实现定时任务）](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/011_gulimall/README.md)
- [RabbitMQ延时队列：延时队列定时关单、延时队列实现库存的自动解锁，实现最终一致性、定时关单、消息丢失、积压、重复的解决方案](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md#8rabbitmq%E5%BB%B6%E6%97%B6%E9%98%9F%E5%88%97)

[十七、订单服务](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md)

- [订单中心、订单构成、订单状态、订单流程](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md)
- [整合springsession、配置线程池、页面跳转环境、订单登录拦截](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md)
- [订单确认页实现、调用会员服务、调用仓储服务、调用会员服务、调用商品服务、Feign远程调用丢失请求头问题](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md)
- [提交订单、订单提交流程、获取spu信息、锁库存、提交订单实现](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md#5%E6%8F%90%E4%BA%A4%E8%AE%A2%E5%8D%95)

[十八、接口幂等性讨论](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md)

- [幂等性概念、幂等解决方案、token机制(令牌机制）、各种锁机制、各种唯一约束、全局请求唯一id](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md)

[十九、分布式事务](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md)

- [本地事务与分布式事务：事务的基本性质、事务的隔离级别、事务的传播行为、SpringBoot事务关键点](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md)

- [分布式事务：为什么有分布式事务、CAP定理与BASE理论、CAP定理、CAP面临的问题、BASE理论、强一致性、弱一致性、最终一致性](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md)
- [分布式事务几种方案：2PC模式、柔性事务-TCC事务补偿型方案、柔性事务-最大努力通知型方案、柔性事务-可靠消息+最终一致性方案（异步确保型）](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md)
- [分布式事务组件-seata：分布式事务场景、seata解决方案、Dubbo+SEATA提供支持的示例、订单服务整合seata(AT模式)解决分布式事务](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md)

[二十、支付宝支付](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md#9%E6%94%AF%E4%BB%98%E5%AE%9D%E6%94%AF%E4%BB%98)

- [支付流程中的加密：对称加密、非对称加密、公钥、私钥、加密、签名和验签、支付宝加密流程、内网穿透](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md#92-%E6%94%AF%E4%BB%98%E6%B5%81%E7%A8%8B%E4%B8%AD%E7%9A%84%E5%8A%A0%E5%AF%86)
- [整合支付宝、支付宝信息配置、订单服务实现订单支付、支付宝异步回调通知、支付过程中的一些问题](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md#93-%E6%95%B4%E5%90%88%E6%94%AF%E4%BB%98%E5%AE%9D)

[二十一、秒杀](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md)

- [秒杀限流、上架流程、秒杀流程、秒杀相关问题](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md)
- [cron表达式、后台添加秒杀商品、秒杀商品定时上架、缓存秒杀活动及商品信息、查询秒杀商品](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md)
- [整合springsession、登录拦截、整合rabbitMQ、秒杀流程及最终实现、订单处理、秒杀页面渲染](https://github.com/Li-ShiLin/Programming-Learning-Note-Code/blob/master/007_E-Commerce-Guide/012_gulimall/README.md)




---

笔记整理自尚硅谷B站谷粒商城项目课程[>>项目课程](https://www.bilibili.com/video/BV1np4y1C7Yf)

---

