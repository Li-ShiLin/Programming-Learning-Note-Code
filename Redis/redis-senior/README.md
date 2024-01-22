<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1.springboot整合redis](#1springboot%E6%95%B4%E5%90%88redis)
  - [1.1 集成Jedis](#11-%E9%9B%86%E6%88%90jedis)
  - [1.2 集成lettuce](#12-%E9%9B%86%E6%88%90lettuce)
  - [1.3集成RedisTemplate-推荐使用](#13%E9%9B%86%E6%88%90redistemplate-%E6%8E%A8%E8%8D%90%E4%BD%BF%E7%94%A8)
      - [1.3.1 连接单机](#131-%E8%BF%9E%E6%8E%A5%E5%8D%95%E6%9C%BA)
      - [1.3.2 连接集群](#132-%E8%BF%9E%E6%8E%A5%E9%9B%86%E7%BE%A4)
      - [1.3.3 redis序列化问题](#133-redis%E5%BA%8F%E5%88%97%E5%8C%96%E9%97%AE%E9%A2%98)
- [2.Redis单线程vS多线程(入门篇)](#2redis%E5%8D%95%E7%BA%BF%E7%A8%8Bvs%E5%A4%9A%E7%BA%BF%E7%A8%8B%E5%85%A5%E9%97%A8%E7%AF%87)
  - [2.1 redis单线程](#21-redis%E5%8D%95%E7%BA%BF%E7%A8%8B)
      - [2.1.1  redis是单线程还是多线程](#211--redis%E6%98%AF%E5%8D%95%E7%BA%BF%E7%A8%8B%E8%BF%98%E6%98%AF%E5%A4%9A%E7%BA%BF%E7%A8%8B)
      - [2.1.2 reids单线程的缺陷 | lazyfree机制](#212-reids%E5%8D%95%E7%BA%BF%E7%A8%8B%E7%9A%84%E7%BC%BA%E9%99%B7--lazyfree%E6%9C%BA%E5%88%B6)
      - [2.1.3 redis单线程的含义](#213-redis%E5%8D%95%E7%BA%BF%E7%A8%8B%E7%9A%84%E5%90%AB%E4%B9%89)
  - [2.2 redis6/7的多线程特性和lO多路复用](#22-redis67%E7%9A%84%E5%A4%9A%E7%BA%BF%E7%A8%8B%E7%89%B9%E6%80%A7%E5%92%8Clo%E5%A4%9A%E8%B7%AF%E5%A4%8D%E7%94%A8)
      - [2.2 1  Redis性能瓶颈：内存或者网络lO](#22-1--redis%E6%80%A7%E8%83%BD%E7%93%B6%E9%A2%88%E5%86%85%E5%AD%98%E6%88%96%E8%80%85%E7%BD%91%E7%BB%9Clo)
      - [2.2.2 IO多路复用简介 | epoll | IO多路复用模型](#222-io%E5%A4%9A%E8%B7%AF%E5%A4%8D%E7%94%A8%E7%AE%80%E4%BB%8B--epoll--io%E5%A4%9A%E8%B7%AF%E5%A4%8D%E7%94%A8%E6%A8%A1%E5%9E%8B)
      - [2.2.3  IO多路复用过程简介](#223--io%E5%A4%9A%E8%B7%AF%E5%A4%8D%E7%94%A8%E8%BF%87%E7%A8%8B%E7%AE%80%E4%BB%8B)
      - [2.2.4 IO多路复用流程精讲](#224-io%E5%A4%9A%E8%B7%AF%E5%A4%8D%E7%94%A8%E6%B5%81%E7%A8%8B%E7%B2%BE%E8%AE%B2)
      - [2.2.5 开启redis多线程](#225-%E5%BC%80%E5%90%AFredis%E5%A4%9A%E7%BA%BF%E7%A8%8B)
  - [2.3 总结](#23-%E6%80%BB%E7%BB%93)
- [3. bigKey](#3-bigkey)
  - [3.1 面试题概览](#31-%E9%9D%A2%E8%AF%95%E9%A2%98%E6%A6%82%E8%A7%88)
  - [3.2 moreKey 案例](#32-morekey-%E6%A1%88%E4%BE%8B)
      - [3.2.1  生产上限制`keys */flushdb/flushall`的使用](#321--%E7%94%9F%E4%BA%A7%E4%B8%8A%E9%99%90%E5%88%B6keys-flushdbflushall%E7%9A%84%E4%BD%BF%E7%94%A8)
      - [3.2.2  使用Scan命令替代 keys *](#322--%E4%BD%BF%E7%94%A8scan%E5%91%BD%E4%BB%A4%E6%9B%BF%E4%BB%A3-keys-)
  - [3.3 BigKey判定 | bigKey的危害 | 产生原因](#33-bigkey%E5%88%A4%E5%AE%9A--bigkey%E7%9A%84%E5%8D%B1%E5%AE%B3--%E4%BA%A7%E7%94%9F%E5%8E%9F%E5%9B%A0)
  - [3.4  bigkey的发现和排查](#34--bigkey%E7%9A%84%E5%8F%91%E7%8E%B0%E5%92%8C%E6%8E%92%E6%9F%A5)
  - [3.5  bigKey的删除](#35--bigkey%E7%9A%84%E5%88%A0%E9%99%A4)
      - [3.5.1 案例开发规范](#351-%E6%A1%88%E4%BE%8B%E5%BC%80%E5%8F%91%E8%A7%84%E8%8C%83)
      - [3.5.2  String类型删除](#352--string%E7%B1%BB%E5%9E%8B%E5%88%A0%E9%99%A4)
      - [3.5.3 hash类型删除](#353-hash%E7%B1%BB%E5%9E%8B%E5%88%A0%E9%99%A4)
      - [3.5.4 list类型删除](#354-list%E7%B1%BB%E5%9E%8B%E5%88%A0%E9%99%A4)
      - [3.5.5 set类型删除](#355-set%E7%B1%BB%E5%9E%8B%E5%88%A0%E9%99%A4)
      - [3.5.6 zset类型删除](#356-zset%E7%B1%BB%E5%9E%8B%E5%88%A0%E9%99%A4)
  - [3.6  BigKey生产调优](#36--bigkey%E7%94%9F%E4%BA%A7%E8%B0%83%E4%BC%98)
      - [3.6.1 阻塞和非阻塞删除命令](#361-%E9%98%BB%E5%A1%9E%E5%92%8C%E9%9D%9E%E9%98%BB%E5%A1%9E%E5%88%A0%E9%99%A4%E5%91%BD%E4%BB%A4)
      - [3.6.2 优化配置](#362-%E4%BC%98%E5%8C%96%E9%85%8D%E7%BD%AE)
- [4.缓存双写一致性之更新策略](#4%E7%BC%93%E5%AD%98%E5%8F%8C%E5%86%99%E4%B8%80%E8%87%B4%E6%80%A7%E4%B9%8B%E6%9B%B4%E6%96%B0%E7%AD%96%E7%95%A5)
  - [4.1 双写一致性面试题](#41-%E5%8F%8C%E5%86%99%E4%B8%80%E8%87%B4%E6%80%A7%E9%9D%A2%E8%AF%95%E9%A2%98)
  - [4.2 缓存双写一致性](#42-%E7%BC%93%E5%AD%98%E5%8F%8C%E5%86%99%E4%B8%80%E8%87%B4%E6%80%A7)
  - [4.3 双检加锁策略](#43-%E5%8F%8C%E6%A3%80%E5%8A%A0%E9%94%81%E7%AD%96%E7%95%A5)
  - [4.4 缓存—致性的几种更新策略](#44-%E7%BC%93%E5%AD%98%E8%87%B4%E6%80%A7%E7%9A%84%E5%87%A0%E7%A7%8D%E6%9B%B4%E6%96%B0%E7%AD%96%E7%95%A5)
      - [4.4.1  先更新数据库，再更新缓存](#441--%E5%85%88%E6%9B%B4%E6%96%B0%E6%95%B0%E6%8D%AE%E5%BA%93%E5%86%8D%E6%9B%B4%E6%96%B0%E7%BC%93%E5%AD%98)
      - [4.4.2 先更新缓存，再更新数据库](#442-%E5%85%88%E6%9B%B4%E6%96%B0%E7%BC%93%E5%AD%98%E5%86%8D%E6%9B%B4%E6%96%B0%E6%95%B0%E6%8D%AE%E5%BA%93)
      - [4.4.3 先删除缓存，再更新数据库](#443-%E5%85%88%E5%88%A0%E9%99%A4%E7%BC%93%E5%AD%98%E5%86%8D%E6%9B%B4%E6%96%B0%E6%95%B0%E6%8D%AE%E5%BA%93)
      - [4.4.4 先更新数据库，再删除缓存](#444-%E5%85%88%E6%9B%B4%E6%96%B0%E6%95%B0%E6%8D%AE%E5%BA%93%E5%86%8D%E5%88%A0%E9%99%A4%E7%BC%93%E5%AD%98)
  - [4.5 总结](#45-%E6%80%BB%E7%BB%93)
- [5.Redis与MySQL数据双写一致性工程落地案例](#5redis%E4%B8%8Emysql%E6%95%B0%E6%8D%AE%E5%8F%8C%E5%86%99%E4%B8%80%E8%87%B4%E6%80%A7%E5%B7%A5%E7%A8%8B%E8%90%BD%E5%9C%B0%E6%A1%88%E4%BE%8B)
  - [5.1 缓存一致性策略回顾](#51-%E7%BC%93%E5%AD%98%E4%B8%80%E8%87%B4%E6%80%A7%E7%AD%96%E7%95%A5%E5%9B%9E%E9%A1%BE)
  - [5.2 canal简介 & canal工作原理](#52-canal%E7%AE%80%E4%BB%8B--canal%E5%B7%A5%E4%BD%9C%E5%8E%9F%E7%90%86)
      - [5.2.1 canal简介](#521-canal%E7%AE%80%E4%BB%8B)
      - [5.2.2 GPT关于canal的介绍](#522-gpt%E5%85%B3%E4%BA%8Ecanal%E7%9A%84%E4%BB%8B%E7%BB%8D)
      - [5.2.3 canal工作原理](#523-canal%E5%B7%A5%E4%BD%9C%E5%8E%9F%E7%90%86)
  - [5.3  整合canal实现双写一致性](#53--%E6%95%B4%E5%90%88canal%E5%AE%9E%E7%8E%B0%E5%8F%8C%E5%86%99%E4%B8%80%E8%87%B4%E6%80%A7)
      - [5.3.1 mysql配置](#531-mysql%E9%85%8D%E7%BD%AE)
      - [5.3.2  canal服务端安装配置](#532--canal%E6%9C%8D%E5%8A%A1%E7%AB%AF%E5%AE%89%E8%A3%85%E9%85%8D%E7%BD%AE)
      - [5.3.3 canal客户端(Java编写业务程序)](#533-canal%E5%AE%A2%E6%88%B7%E7%AB%AFjava%E7%BC%96%E5%86%99%E4%B8%9A%E5%8A%A1%E7%A8%8B%E5%BA%8F)
      - [5.3.4 补充说明](#534-%E8%A1%A5%E5%85%85%E8%AF%B4%E6%98%8E)
- [6.bitmap | hyperloglog | GEO案例实战](#6bitmap--hyperloglog--geo%E6%A1%88%E4%BE%8B%E5%AE%9E%E6%88%98)
  - [6.1 面试题](#61-%E9%9D%A2%E8%AF%95%E9%A2%98)
  - [6.2 常见的四种统计](#62-%E5%B8%B8%E8%A7%81%E7%9A%84%E5%9B%9B%E7%A7%8D%E7%BB%9F%E8%AE%A1)
  - [6.2 hyperloglog 基数统计](#62-hyperloglog-%E5%9F%BA%E6%95%B0%E7%BB%9F%E8%AE%A1)
      - [6.2.1  术语 | 应用场景 | hyperloglog命令](#621--%E6%9C%AF%E8%AF%AD--%E5%BA%94%E7%94%A8%E5%9C%BA%E6%99%AF--hyperloglog%E5%91%BD%E4%BB%A4)
      - [6.2.2  HyPerLogLog  演化 | 原理](#622--hyperloglog--%E6%BC%94%E5%8C%96--%E5%8E%9F%E7%90%86)
      - [6.2.3 淘宝网站首页亿级UV的Redis统计方案](#623-%E6%B7%98%E5%AE%9D%E7%BD%91%E7%AB%99%E9%A6%96%E9%A1%B5%E4%BA%BF%E7%BA%A7uv%E7%9A%84redis%E7%BB%9F%E8%AE%A1%E6%96%B9%E6%A1%88)
  - [6.3 GEO 地理坐标](#63-geo-%E5%9C%B0%E7%90%86%E5%9D%90%E6%A0%87)
      - [6.3.1 简介 | 经纬度](#631-%E7%AE%80%E4%BB%8B--%E7%BB%8F%E7%BA%AC%E5%BA%A6)
      - [6.3.2 GEO命令](#632-geo%E5%91%BD%E4%BB%A4)
      - [6.3.3 实现附近地点的推送](#633-%E5%AE%9E%E7%8E%B0%E9%99%84%E8%BF%91%E5%9C%B0%E7%82%B9%E7%9A%84%E6%8E%A8%E9%80%81)
  - [6.4 bitmap](#64-bitmap)
      - [6.4.1 应用场景 | 底层原理](#641-%E5%BA%94%E7%94%A8%E5%9C%BA%E6%99%AF--%E5%BA%95%E5%B1%82%E5%8E%9F%E7%90%86)
      - [6.4.2 bitmap命令](#642-bitmap%E5%91%BD%E4%BB%A4)
      - [6.4.3 bitmap实现签到统计](#643-bitmap%E5%AE%9E%E7%8E%B0%E7%AD%BE%E5%88%B0%E7%BB%9F%E8%AE%A1)
- [7.布隆过滤器BloomFilter](#7%E5%B8%83%E9%9A%86%E8%BF%87%E6%BB%A4%E5%99%A8bloomfilter)
  - [7.1 简介 | 应用场景](#71-%E7%AE%80%E4%BB%8B--%E5%BA%94%E7%94%A8%E5%9C%BA%E6%99%AF)
  - [7.2  布隆过滤器原理](#72--%E5%B8%83%E9%9A%86%E8%BF%87%E6%BB%A4%E5%99%A8%E5%8E%9F%E7%90%86)
  - [7.3 布隆过滤器解决缓存穿透](#73-%E5%B8%83%E9%9A%86%E8%BF%87%E6%BB%A4%E5%99%A8%E8%A7%A3%E5%86%B3%E7%BC%93%E5%AD%98%E7%A9%BF%E9%80%8F)
      - [7.3.1  bitmap实现布隆过滤器思路](#731--bitmap%E5%AE%9E%E7%8E%B0%E5%B8%83%E9%9A%86%E8%BF%87%E6%BB%A4%E5%99%A8%E6%80%9D%E8%B7%AF)
      - [7.3.2  布隆过滤器解决缓存穿透思路](#732--%E5%B8%83%E9%9A%86%E8%BF%87%E6%BB%A4%E5%99%A8%E8%A7%A3%E5%86%B3%E7%BC%93%E5%AD%98%E7%A9%BF%E9%80%8F%E6%80%9D%E8%B7%AF)
      - [7.3.3 mybatis generator代码生成](#733-mybatis-generator%E4%BB%A3%E7%A0%81%E7%94%9F%E6%88%90)
      - [7.5.5  布隆过滤器解决缓存穿透](#755--%E5%B8%83%E9%9A%86%E8%BF%87%E6%BB%A4%E5%99%A8%E8%A7%A3%E5%86%B3%E7%BC%93%E5%AD%98%E7%A9%BF%E9%80%8F)
  - [7.4 总结](#74-%E6%80%BB%E7%BB%93)
- [8.缓存预热＋缓存雪崩＋缓存击穿＋缓存穿透](#8%E7%BC%93%E5%AD%98%E9%A2%84%E7%83%AD%EF%BC%8B%E7%BC%93%E5%AD%98%E9%9B%AA%E5%B4%A9%EF%BC%8B%E7%BC%93%E5%AD%98%E5%87%BB%E7%A9%BF%EF%BC%8B%E7%BC%93%E5%AD%98%E7%A9%BF%E9%80%8F)
  - [8.1 相关面试题](#81-%E7%9B%B8%E5%85%B3%E9%9D%A2%E8%AF%95%E9%A2%98)
  - [8.2 缓存预热](#82-%E7%BC%93%E5%AD%98%E9%A2%84%E7%83%AD)
  - [8.3  缓存雪崩](#83--%E7%BC%93%E5%AD%98%E9%9B%AA%E5%B4%A9)
  - [8.4  缓存穿透](#84--%E7%BC%93%E5%AD%98%E7%A9%BF%E9%80%8F)
      - [8.4.1 缓存穿透简介](#841-%E7%BC%93%E5%AD%98%E7%A9%BF%E9%80%8F%E7%AE%80%E4%BB%8B)
      - [8.4.2   解决缓存穿透：缓存空对象or缺省值](#842---%E8%A7%A3%E5%86%B3%E7%BC%93%E5%AD%98%E7%A9%BF%E9%80%8F%E7%BC%93%E5%AD%98%E7%A9%BA%E5%AF%B9%E8%B1%A1or%E7%BC%BA%E7%9C%81%E5%80%BC)
      - [8.4.3   解决缓存穿透：Google布隆过滤器](#843---%E8%A7%A3%E5%86%B3%E7%BC%93%E5%AD%98%E7%A9%BF%E9%80%8Fgoogle%E5%B8%83%E9%9A%86%E8%BF%87%E6%BB%A4%E5%99%A8)
      - [8.4.4 布隆过滤器实现白名单,黑名单](#844-%E5%B8%83%E9%9A%86%E8%BF%87%E6%BB%A4%E5%99%A8%E5%AE%9E%E7%8E%B0%E7%99%BD%E5%90%8D%E5%8D%95%E9%BB%91%E5%90%8D%E5%8D%95)
  - [8.6  缓存击穿](#86--%E7%BC%93%E5%AD%98%E5%87%BB%E7%A9%BF)
      - [8.6.1 简介 | 双检加锁策略](#861-%E7%AE%80%E4%BB%8B--%E5%8F%8C%E6%A3%80%E5%8A%A0%E9%94%81%E7%AD%96%E7%95%A5)
      - [8.6.2 缓存击穿案例](#862-%E7%BC%93%E5%AD%98%E5%87%BB%E7%A9%BF%E6%A1%88%E4%BE%8B)
      - [8.6.3  缓存击穿和缓存穿透对比](#863--%E7%BC%93%E5%AD%98%E5%87%BB%E7%A9%BF%E5%92%8C%E7%BC%93%E5%AD%98%E7%A9%BF%E9%80%8F%E5%AF%B9%E6%AF%94)
  - [8.6 缓存问题总结](#86-%E7%BC%93%E5%AD%98%E9%97%AE%E9%A2%98%E6%80%BB%E7%BB%93)
- [9.redis 分布式锁](#9redis-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81)
  - [9.1 分布式锁简介 | 分布式锁命令](#91-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E7%AE%80%E4%BB%8B--%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E5%91%BD%E4%BB%A4)
  - [9.2 锁的单机实现](#92-%E9%94%81%E7%9A%84%E5%8D%95%E6%9C%BA%E5%AE%9E%E7%8E%B0)
  - [9.3 nginx分布式微服务架构](#93-nginx%E5%88%86%E5%B8%83%E5%BC%8F%E5%BE%AE%E6%9C%8D%E5%8A%A1%E6%9E%B6%E6%9E%84)
  - [9.4 分布式锁一：set nx | 递归重试](#94-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E4%B8%80set-nx--%E9%80%92%E5%BD%92%E9%87%8D%E8%AF%95)
  - [9.5 分布式锁二：自旋替代递归重试](#95-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E4%BA%8C%E8%87%AA%E6%97%8B%E6%9B%BF%E4%BB%A3%E9%80%92%E5%BD%92%E9%87%8D%E8%AF%95)
  - [9.6 redis分布式锁三：添加过期时间](#96-redis%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E4%B8%89%E6%B7%BB%E5%8A%A0%E8%BF%87%E6%9C%9F%E6%97%B6%E9%97%B4)
  - [9.7 redis分布式锁四：保证加锁和过期时间设置的原子性](#97-redis%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E5%9B%9B%E4%BF%9D%E8%AF%81%E5%8A%A0%E9%94%81%E5%92%8C%E8%BF%87%E6%9C%9F%E6%97%B6%E9%97%B4%E8%AE%BE%E7%BD%AE%E7%9A%84%E5%8E%9F%E5%AD%90%E6%80%A7)
  - [9.8 redis分布式锁五：防止锁的误删](#98-redis%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E4%BA%94%E9%98%B2%E6%AD%A2%E9%94%81%E7%9A%84%E8%AF%AF%E5%88%A0)
  - [9.9  redis分布式锁六：Lua保证解锁原子性](#99--redis%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E5%85%ADlua%E4%BF%9D%E8%AF%81%E8%A7%A3%E9%94%81%E5%8E%9F%E5%AD%90%E6%80%A7)
      - [9.9.1 Lua脚本入门](#991-lua%E8%84%9A%E6%9C%AC%E5%85%A5%E9%97%A8)
      - [9.9.3 lua脚本保证原子性](#993-lua%E8%84%9A%E6%9C%AC%E4%BF%9D%E8%AF%81%E5%8E%9F%E5%AD%90%E6%80%A7)
  - [9.10 redis分布式锁七：可重入锁+设计模式](#910-redis%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E4%B8%83%E5%8F%AF%E9%87%8D%E5%85%A5%E9%94%81%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F)
      - [9.10.1 可重入锁](#9101-%E5%8F%AF%E9%87%8D%E5%85%A5%E9%94%81)
      - [9.10.2 ReentrantLock源码分析：可重入性原理](#9102-reentrantlock%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90%E5%8F%AF%E9%87%8D%E5%85%A5%E6%80%A7%E5%8E%9F%E7%90%86)
      - [9.10.3  hset实现分布式锁的可重入性](#9103--hset%E5%AE%9E%E7%8E%B0%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E7%9A%84%E5%8F%AF%E9%87%8D%E5%85%A5%E6%80%A7)
      - [9.10.4 lua脚本实现lock](#9104-lua%E8%84%9A%E6%9C%AC%E5%AE%9E%E7%8E%B0lock)
      - [9.10.5  lua脚本实现unlock](#9105--lua%E8%84%9A%E6%9C%AC%E5%AE%9E%E7%8E%B0unlock)
      - [9.10.6 实现可重入的分布式锁](#9106-%E5%AE%9E%E7%8E%B0%E5%8F%AF%E9%87%8D%E5%85%A5%E7%9A%84%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81)
      - [9.10.7  工厂模式提高分布式锁的通用性](#9107--%E5%B7%A5%E5%8E%82%E6%A8%A1%E5%BC%8F%E6%8F%90%E9%AB%98%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E7%9A%84%E9%80%9A%E7%94%A8%E6%80%A7)
      - [9.10.8 可重入性测试 | 工厂模式bug修复](#9108-%E5%8F%AF%E9%87%8D%E5%85%A5%E6%80%A7%E6%B5%8B%E8%AF%95--%E5%B7%A5%E5%8E%82%E6%A8%A1%E5%BC%8Fbug%E4%BF%AE%E5%A4%8D)
  - [9.11 redis分布式锁八：自动续期](#911-redis%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E5%85%AB%E8%87%AA%E5%8A%A8%E7%BB%AD%E6%9C%9F)
      - [9.11.1 分布式锁自动续期](#9111-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E8%87%AA%E5%8A%A8%E7%BB%AD%E6%9C%9F)
      - [9.11.2 分布式系统CAP理论](#9112-%E5%88%86%E5%B8%83%E5%BC%8F%E7%B3%BB%E7%BB%9Fcap%E7%90%86%E8%AE%BA)
  - [9.12 总结](#912-%E6%80%BB%E7%BB%93)
- [10. Redlock算法和底层源码分析](#10-redlock%E7%AE%97%E6%B3%95%E5%92%8C%E5%BA%95%E5%B1%82%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90)
  - [10.1  Redlock红锁算法](#101--redlock%E7%BA%A2%E9%94%81%E7%AE%97%E6%B3%95)
  - [10.2  使用Redisson进行编码改造V9.0](#102--%E4%BD%BF%E7%94%A8redisson%E8%BF%9B%E8%A1%8C%E7%BC%96%E7%A0%81%E6%94%B9%E9%80%A0v90)
  - [10.3 Redisson源码解析](#103-redisson%E6%BA%90%E7%A0%81%E8%A7%A3%E6%9E%90)
  - [10.4 多机案例](#104-%E5%A4%9A%E6%9C%BA%E6%A1%88%E4%BE%8B)
- [11. Redis的缓存过期淘汰策略](#11-redis%E7%9A%84%E7%BC%93%E5%AD%98%E8%BF%87%E6%9C%9F%E6%B7%98%E6%B1%B0%E7%AD%96%E7%95%A5)
  - [11.1 最大内存](#111-%E6%9C%80%E5%A4%A7%E5%86%85%E5%AD%98)
  - [11.2 过期键删除策略](#112-%E8%BF%87%E6%9C%9F%E9%94%AE%E5%88%A0%E9%99%A4%E7%AD%96%E7%95%A5)
  - [11.3  redis缓存淘汰策略](#113--redis%E7%BC%93%E5%AD%98%E6%B7%98%E6%B1%B0%E7%AD%96%E7%95%A5)
- [12. Redis经典五大类型源码及底层实现](#12-redis%E7%BB%8F%E5%85%B8%E4%BA%94%E5%A4%A7%E7%B1%BB%E5%9E%8B%E6%BA%90%E7%A0%81%E5%8F%8A%E5%BA%95%E5%B1%82%E5%AE%9E%E7%8E%B0)
  - [12.1 redis源码总览](#121-redis%E6%BA%90%E7%A0%81%E6%80%BB%E8%A7%88)
  - [12.2 10大数据类型](#122-10%E5%A4%A7%E6%95%B0%E6%8D%AE%E7%B1%BB%E5%9E%8B)
  - [12.3 redis对象redisObject](#123-redis%E5%AF%B9%E8%B1%A1redisobject)
      - [12.3.1 C语言结构体 | typedef关键字 | void关键字](#1231-c%E8%AF%AD%E8%A8%80%E7%BB%93%E6%9E%84%E4%BD%93--typedef%E5%85%B3%E9%94%AE%E5%AD%97--void%E5%85%B3%E9%94%AE%E5%AD%97)
      - [12.3.2  dictEntry结构 | redisObject结构](#1232--dictentry%E7%BB%93%E6%9E%84--redisobject%E7%BB%93%E6%9E%84)
  - [12.4 五大数据结构总览](#124-%E4%BA%94%E5%A4%A7%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E6%80%BB%E8%A7%88)
  - [12.5  底层编码类型映射 | DEBUG OBJECT key命令](#125--%E5%BA%95%E5%B1%82%E7%BC%96%E7%A0%81%E7%B1%BB%E5%9E%8B%E6%98%A0%E5%B0%84--debug-object-key%E5%91%BD%E4%BB%A4)
  - [12.6 String数据结构介绍](#126-string%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%BB%8B%E7%BB%8D)
      - [12.6.1  string底层对应的3大物理编码](#1261--string%E5%BA%95%E5%B1%82%E5%AF%B9%E5%BA%94%E7%9A%843%E5%A4%A7%E7%89%A9%E7%90%86%E7%BC%96%E7%A0%81)
      - [12.6.2  SDS简单动态字符串](#1262--sds%E7%AE%80%E5%8D%95%E5%8A%A8%E6%80%81%E5%AD%97%E7%AC%A6%E4%B8%B2)
      - [12.6.3 set命令源码分析](#1263-set%E5%91%BD%E4%BB%A4%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90)
      - [12.6.4  INT编码格式源码分析](#1264--int%E7%BC%96%E7%A0%81%E6%A0%BC%E5%BC%8F%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90)
      - [12.6.5  EMBSTR编码格式源码分析](#1265--embstr%E7%BC%96%E7%A0%81%E6%A0%BC%E5%BC%8F%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90)
      - [12.6.6 RAW编码格式源码分析](#1266-raw%E7%BC%96%E7%A0%81%E6%A0%BC%E5%BC%8F%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90)
      - [12.6.7 低于阈值但编码格式是raw](#1267-%E4%BD%8E%E4%BA%8E%E9%98%88%E5%80%BC%E4%BD%86%E7%BC%96%E7%A0%81%E6%A0%BC%E5%BC%8F%E6%98%AFraw)
      - [12.6.8 编码格式总结](#1268-%E7%BC%96%E7%A0%81%E6%A0%BC%E5%BC%8F%E6%80%BB%E7%BB%93)
  - [12.5  Hash数据结构介绍](#125--hash%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%BB%8B%E7%BB%8D)
      - [12.5.1 redis6 Hash类型底层实现](#1251-redis6-hash%E7%B1%BB%E5%9E%8B%E5%BA%95%E5%B1%82%E5%AE%9E%E7%8E%B0)
      - [12.5.2  redis6 hashtable源码分析](#1252--redis6-hashtable%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90)
      - [12.5.3  redis6  ziplist 压缩列表源码分析](#1253--redis6--ziplist-%E5%8E%8B%E7%BC%A9%E5%88%97%E8%A1%A8%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90)
      - [12.5.4 redis7  Hash类型底层实现](#1254-redis7--hash%E7%B1%BB%E5%9E%8B%E5%BA%95%E5%B1%82%E5%AE%9E%E7%8E%B0)
      - [12.5.5  redis7 listpack紧凑列表源码分析](#1255--redis7-listpack%E7%B4%A7%E5%87%91%E5%88%97%E8%A1%A8%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90)
  - [12.6  List数据结构介绍](#126--list%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%BB%8B%E7%BB%8D)
      - [12.6.1 redis6 List类型源码](#1261-redis6-list%E7%B1%BB%E5%9E%8B%E6%BA%90%E7%A0%81)
      - [12.6.2 redis7 List类型源码](#1262-redis7-list%E7%B1%BB%E5%9E%8B%E6%BA%90%E7%A0%81)
  - [12.7  set数据结构介绍](#127--set%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%BB%8B%E7%BB%8D)
  - [12.8  ZSet数据结构介绍](#128--zset%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E4%BB%8B%E7%BB%8D)
      - [12.8.1 redis6 zset源码分析](#1281-redis6-zset%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90)
      - [12.8.2  redis7 zset源码分析](#1282--redis7-zset%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90)
      - [12.8.3  skiplist跳表](#1283--skiplist%E8%B7%B3%E8%A1%A8)
  - [12.9 五大数据结构源码总结](#129-%E4%BA%94%E5%A4%A7%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E6%BA%90%E7%A0%81%E6%80%BB%E7%BB%93)
- [13. epoll和IO多路复用深度解析](#13-epoll%E5%92%8Cio%E5%A4%9A%E8%B7%AF%E5%A4%8D%E7%94%A8%E6%B7%B1%E5%BA%A6%E8%A7%A3%E6%9E%90)
  - [13.1 IO多路复用 | 同步 | 异步 | 阻塞 | 非阻塞](#131-io%E5%A4%9A%E8%B7%AF%E5%A4%8D%E7%94%A8--%E5%90%8C%E6%AD%A5--%E5%BC%82%E6%AD%A5--%E9%98%BB%E5%A1%9E--%E9%9D%9E%E9%98%BB%E5%A1%9E)
  - [13.2 BIO(阻塞式I/O)](#132-bio%E9%98%BB%E5%A1%9E%E5%BC%8Fio)
      - [13.2.1 BIO简介](#1321-bio%E7%AE%80%E4%BB%8B)
      - [13.2.2 accept监听程序演示](#1322-accept%E7%9B%91%E5%90%AC%E7%A8%8B%E5%BA%8F%E6%BC%94%E7%A4%BA)
      - [13.2.3 read读取程序演示](#1323-read%E8%AF%BB%E5%8F%96%E7%A8%8B%E5%BA%8F%E6%BC%94%E7%A4%BA)
      - [13.2.4 多线程模式优化](#1324-%E5%A4%9A%E7%BA%BF%E7%A8%8B%E6%A8%A1%E5%BC%8F%E4%BC%98%E5%8C%96)
  - [13.3 NIO(非阻塞式IO)](#133-nio%E9%9D%9E%E9%98%BB%E5%A1%9E%E5%BC%8Fio)
  - [13.4  IO Multiplexing (IO多路复用)](#134--io-multiplexing-io%E5%A4%9A%E8%B7%AF%E5%A4%8D%E7%94%A8)
      - [13.4.1 文件描述符](#1341-%E6%96%87%E4%BB%B6%E6%8F%8F%E8%BF%B0%E7%AC%A6)
      - [13.4.2  IO多路复用模型](#1342--io%E5%A4%9A%E8%B7%AF%E5%A4%8D%E7%94%A8%E6%A8%A1%E5%9E%8B)
      - [13.4.3 Redis的IO多路复用](#1343-redis%E7%9A%84io%E5%A4%9A%E8%B7%AF%E5%A4%8D%E7%94%A8)
      - [13.4.4  Reactor模式](#1344--reactor%E6%A8%A1%E5%BC%8F)
  - [13.5 select方法](#135-select%E6%96%B9%E6%B3%95)
  - [13.6  poll方法](#136--poll%E6%96%B9%E6%B3%95)
  - [13.7  epoll方法](#137--epoll%E6%96%B9%E6%B3%95)
  - [13.8  select、poll、epoll](#138--selectpollepoll)
  - [13.8  五种I/O模型总结：](#138--%E4%BA%94%E7%A7%8Dio%E6%A8%A1%E5%9E%8B%E6%80%BB%E7%BB%93)
- [14. 微信抢红包案例实现](#14-%E5%BE%AE%E4%BF%A1%E6%8A%A2%E7%BA%A2%E5%8C%85%E6%A1%88%E4%BE%8B%E5%AE%9E%E7%8E%B0)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

##  1.springboot整合redis

###  1.1 集成Jedis

Jedis Client是Redis官网推荐的一个面向java客户端，库文件实现了对各类API进行封装调用。创建`redis7_study`项目模块，并引入`jedis`依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.atguigu.redis7</groupId>
    <artifactId>redis7_study</artifactId>
    <version>1.0-SNAPSHOT</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.6.10</version>
        <relativePath/>
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <junit.version>4.12</junit.version>
        <log4j.version>1.2.17</log4j.version>
        <lombok.version>1.16.18</lombok.version>
    </properties>

    <dependencies>
        <!--SpringBoot通用依赖模块-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--jedis-->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>4.3.1</version>
        </dependency>
        <!--通用基础配置-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${junit.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>${log4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <optional>true</optional>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

`application.properties`配置文件：

```properties
server.port=7777
spring.application.name=redis7_study
```

主启动类：

```java
package com.atguigu.redis7;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Redis7Study7777
{
    public static void main(String[] args)
    {
        SpringApplication.run(Redis7Study7777.class,args);
    }
}
```

jedis入门案例：

```java
package com.atguigu.redis7.demo;
import redis.clients.jedis.Jedis;
import java.util.List;
import java.util.Set;
public class JedisDemo {
    public static void main(String[] args) {
        //1 connection获得，通过指定ip和端口号
        Jedis jedis = new Jedis("192.168.56.10", 6379);
        //2 指定访问服务器的密码
        jedis.auth("123456");
        //3 获得了jedis客户端，可以像jdbc一样，访问我们的redis
        System.out.println(jedis.ping());

        //keys
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);

        //string
        jedis.set("k3", "hello-jedis");
        System.out.println(jedis.get("k3"));
        System.out.println(jedis.ttl("k3"));
        jedis.expire("k3", 20L);

        //list
        jedis.lpush("list", "11", "12", "13");
        List<String> list = jedis.lrange("list", 0, -1);
        for (String element : list) {
            System.out.println(element);
        }

    }
}
```

jedis操作redis的五大数据类型

```java
package com.atguigu.redis7.demo;
import redis.clients.jedis.Jedis;
import java.util.*;
public class JedisDataStruct {
    public static void main(String[] args) {
        //连接本地的 Redis 服务，自己的ip和端口和密码
        Jedis jedis = new Jedis("192.168.56.10", 6379);
        // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
//        jedis.auth("111111");

        //key
        Set<String> keys = jedis.keys("*");
        for (Iterator iterator = keys.iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            System.out.println(key);
        }
        System.out.println("jedis.exists====>" + jedis.exists("k2"));
        System.out.println(jedis.ttl("k1"));
        //String
        //jedis.append("k1","myreids");
        System.out.println(jedis.get("k1"));
        jedis.set("k4", "k4_redis");
        System.out.println("----------------------------------------");
        jedis.mset("str1", "v1", "str2", "v2", "str3", "v3");
        System.out.println(jedis.mget("str1", "str2", "str3"));
        //list
        System.out.println("----------------------------------------");
        //jedis.lpush("mylist","v1","v2","v3","v4","v5");
        List<String> list = jedis.lrange("mylist", 0, -1);
        for (String element : list) {
            System.out.println(element);
        }
        
        //set
        jedis.sadd("orders", "jd001");
        jedis.sadd("orders", "jd002");
        jedis.sadd("orders", "jd003");
        Set<String> set1 = jedis.smembers("orders");
        for (Iterator iterator = set1.iterator(); iterator.hasNext(); ) {
            String string = (String) iterator.next();
            System.out.println(string);
        }
        jedis.srem("orders", "jd002");
        System.out.println(jedis.smembers("orders").size());
        
        
        //hash
        jedis.hset("hash1", "userName", "lisi");
        System.out.println(jedis.hget("hash1", "userName"));
        Map<String, String> map = new HashMap<String, String>();
        map.put("telphone", "138xxxxxxxx");
        map.put("address", "atguigu");
        map.put("email", "zzyybs@126.com");
        jedis.hmset("hash2", map);
        List<String> result = jedis.hmget("hash2", "telphone", "email");
        for (String element : result) {
            System.out.println(element);
        }

        //zset
        jedis.zadd("zset01", 60d, "v1");
        jedis.zadd("zset01", 70d, "v2");
        jedis.zadd("zset01", 80d, "v3");
        jedis.zadd("zset01", 90d, "v4");

        List<String> zset01 = jedis.zrange("zset01", 0, -1);
        zset01.forEach(System.out::println);
    }
}
```

###  1.2 集成lettuce

Lettuce是一个Redis的Java驱动包

补充：Jedis和Lettuce的区别

> jedis和Lettuce都是Redis的客户端，它们都可以连接Redis服务器，但是在SpringBoot2.0之后默认都是使用的L ettuce这个客户端连接Redis服务器。因为当使用Jedis客户端连接Redis服务器的时候，每个线程都要拿自己创建的Jedis实例去连接Redis客户端，当有很多个线程的时候，不仅开销大需要反复的创建关闭一个Jedis连接，而且也是线程不安全的，一个线程通过Jedis实例更改Redis服务器中的数据之后会影响另一个线程
>
> 但是如果使用Lettuce这个客户端连接Redis服务器的时候，就不会出现上面的情况,Lettuce底层使用的是Netty,当有多个线程都需要连接Redis服务器的时候，可以保证只创建一个Lettuce连接，使所有的线程共享这一个Lettuce连接，这样可以减少创建关闭一个Lettuce连接时候的开销;而且这种方式也是线程安全的，不会出现一个线程通过Lettuce更改Redis服务器中的数据之后而影响另一个线程的情况

在`pom.xml`文件中添加`lettuce`依赖：

```xml
<!--lettuce-->
        <dependency>
            <groupId>io.lettuce</groupId>
            <artifactId>lettuce-core</artifactId>
            <version>6.2.1.RELEASE</version>
        </dependency>
```

`lettuce`操作redis：

```java
package com.atguigu.redis7.demo;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import java.util.List;
import java.util.stream.Stream;

public class LettuceDemo
{
    public static void main(String[] args)
    {
        // 1 使用构建器链式编程来builder我们RedisURI
        RedisURI uri = RedisURI.builder()
                .redis("192.168.111.185")
                .withPort(6379)
                .withAuthentication("default","111111")
                .build();

        //2 创建连接客户端
        RedisClient redisClient = RedisClient.create(uri);
        StatefulRedisConnection conn = redisClient.connect();

        //3 通过conn创建操作的command
        RedisCommands commands = conn.sync();

        //========biz====================
        //keys
        List keys = commands.keys("*");
        System.out.println("***********"+keys);

        //string
        commands.set("k5","hello-lettuce");
        System.out.println("***********"+commands.get("k5"));
        //....

        //========biz====================

        //4 各种关闭释放资源
        conn.close();
        redisClient.shutdown();
    }
}
```

`lettuce`操作redis五大数据类型：

```java
package com.atguigu.redis7.demo;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SortArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Slf4j
public class LettuceDataStructDemo {
    public static void main(String[] args) {
        //使用构建器 RedisURI.builder
        RedisURI uri = RedisURI.builder().redis("192.168.56.10").withPort(6379).withAuthentication("default", "111111").build();
        //创建连接客户端
        RedisClient client = RedisClient.create(uri);
        StatefulRedisConnection conn = client.connect();
        //操作命令api
        RedisCommands<String, String> commands = conn.sync();

        //keys
        List<String> list = commands.keys("*");
        for (String s : list) {
            log.info("key:{}", s);
        }
        //String
        commands.set("k1", "1111");
        String s1 = commands.get("k1");
        System.out.println("String s ===" + s1);

        //list
        commands.lpush("myList2", "v1", "v2", "v3");
        List<String> list2 = commands.lrange("myList2", 0, -1);
        for (String s : list2) {
            System.out.println("list ssss===" + s);
        }
        //set
        commands.sadd("mySet2", "v1", "v2", "v3");
        Set<String> set = commands.smembers("mySet2");
        for (String s : set) {
            System.out.println("set ssss===" + s);
        }
        //hash
        Map<String, String> map = new HashMap<>();
        map.put("k1", "138xxxxxxxx");
        map.put("k2", "atguigu");
        map.put("k3", "zzyybs@126.com");

        commands.hmset("myHash2", map);
        Map<String, String> retMap = commands.hgetall("myHash2");
        for (String k : retMap.keySet()) {
            System.out.println("hash  k=" + k + " , v==" + retMap.get(k));
        }

        //zset
        commands.zadd("myZset2", 100.0, "s1", 110.0, "s2", 90.0, "s3");
        List<String> list3 = commands.zrange("myZset2", 0, 10);
        for (String s : list3) {
            System.out.println("zset ssss===" + s);
        }

        //sort
        SortArgs sortArgs = new SortArgs();
        sortArgs.alpha();
        sortArgs.desc();

        List<String> list4 = commands.sort("myList2", sortArgs);
        for (String s : list4) {
            System.out.println("sort ssss===" + s);
        }

        //关闭
        conn.close();
        client.shutdown();
    }
}
```

###  1.3集成RedisTemplate-推荐使用

#####  1.3.1 连接单机

引入`spring-boot-starter-data-redis`依赖：`RedisTemplate`默认整合了`lettuce`客户端，可以将之前的`lettuce`依赖删除

```xml
        <!--SpringBoot与Redis整合依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
      <!--lettuce-->
        <!--<dependency>
            <groupId>io.lettuce</groupId>
            <artifactId>lettuce-core</artifactId>
            <version>6.2.1.RELEASE</version>
        </dependency>-->
```

`application.properties`：添加redis配置

```properties
# ========================redis单机配置=====================
spring.redis.database=0
# 修改为自己的ip
spring.redis.host=192.168.56.10
spring.redis.port=6379
spring.redis.password=123456
spring.redis.lettuce.pool.max-active=8
spring.redis.lettuce.pool.max-wait=-1ms
spring.redis.lettuce.pool.max-idle=8
spring.redis.lettuce.pool.min-idle=0
```

`RedisTemplate`配置：通过配置修改`RedisTemplate`默认的序列化方式

```java
package com.atguigu.redis7.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

// 使用`RedisTemplate`，并通过配置修改默认的序列化方式
@Configuration
public class RedisConfig {
    /**
     * redis序列化的工具配置类，下面这个请一定开启配置
     * 127.0.0.1:6379> keys *
     * 1) "ord:102"  序列化过
     * 2) "\xac\xed\x00\x05t\x00\aord:102"   野生，没有序列化过
     * this.redisTemplate.opsForValue(); //提供了操作string类型的所有方法
     * this.redisTemplate.opsForList(); // 提供了操作list类型的所有方法
     * this.redisTemplate.opsForSet(); //提供了操作set的所有方法
     * this.redisTemplate.opsForHash(); //提供了操作hash表的所有方法
     * this.redisTemplate.opsForZSet(); //提供了操作zset的所有方法
     *
     * @param lettuceConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        //设置key序列化方式string
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化方式json，使用GenericJackson2JsonRedisSerializer替换默认序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
```

编写订单服务接口，利用`RedisTemplate`操作redis进行订单信息的保存和查询

```java
package com.atguigu.redis7.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
@Service
@Slf4j
public class OrderService {

    public static final String ORDER_KEY = "ord:";

    @Resource
    private RedisTemplate redisTemplate;
    //@Resource private StringRedisTemplate StringRedisTemplate;

    public void addOrder() {
        int keyId = ThreadLocalRandom.current().nextInt(1000) + 1;
        String serialNo = UUID.randomUUID().toString();

        String key = ORDER_KEY + keyId;
        String value = "京东订单" + serialNo;

        redisTemplate.opsForValue().set(key, value);


        log.info("***key:{}", key);
        log.info("***value:{}", value);
    }

    public String getOrderById(Integer keyId) {
        return (String) redisTemplate.opsForValue().get(ORDER_KEY + keyId);
        //return StringRedisTemplate.opsForValue().get(ORDER_KEY + keyId);
    }

}
```

测试：

```java
package com.atguigu.redis7.controller;
import com.atguigu.redis7.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
@RestController
@Slf4j
@Api(tags = "订单接口")
public class OrderController {
    @Resource
    private OrderService orderService;

    // 访问http://127.0.0.1:7777/order/add
    @ApiOperation("新增订单")
    @RequestMapping(value = "/order/add", method = RequestMethod.POST)
    public void addOrder() {
        orderService.addOrder();
    }


    // 访问http://127.0.0.1:7777/order/73
    @ApiOperation("按照keyIdc查询订单")
    @RequestMapping(value = "/order/{keyId}", method = RequestMethod.GET)
    public String getOrderById(@PathVariable Integer keyId) {
        return orderService.getOrderById(keyId);
    }
    // 京东订单486467a8-235f-49df-87b6-3a158a94292a
}
```



#####  1.3.2 连接集群

1.启动redis集群6台实例

2.修改`application.properties`：redis集群配置

```properties
# ========================redis集群配置=====================
spring.redis.password=111111
# 获取失败 最大重定向次数
spring.redis.cluster.max-redirects=3
spring.redis.lettuce.pool.max-active=8
spring.redis.lettuce.pool.max-wait=-1ms
spring.redis.lettuce.pool.max-idle=8
spring.redis.lettuce.pool.min-idle=0
spring.redis.cluster.nodes=192.168.111.175:6381,192.168.111.175:6382,192.168.111.172:6383,192.168.111.172:6384,192.168.111.174:6385,192.168.111.174:6386
```



#####  1.3.3 redis序列化问题

序列化问题：

![image-20231203050749707](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220413626.png)

原因：

JDK 序列化方式 （默认）`org.springframework.data.redis.serializer.JdkSerializationRedisSerializer` 。默认情况下，RedisTemplate 使用该数据列化方式，来看下源码 `RedisTemplate#afterPropertiesSet()`：

![image-20231203050604082](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220413971.png)

方案一：使用`StringRedisTemplate`

方案二：使用`RedisTemplate`，并通过配置修改默认的序列化方式

```java
package com.atguigu.redis7.controller.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

// 使用`RedisTemplate`，并通过配置修改默认的序列化方式
@Configuration
public class RedisConfig {
    /**
     * redis序列化的工具配置类，下面这个请一定开启配置
     * 127.0.0.1:6379> keys *
     * 1) "ord:102"  序列化过
     * 2) "\xac\xed\x00\x05t\x00\aord:102"   野生，没有序列化过
     * this.redisTemplate.opsForValue(); //提供了操作string类型的所有方法
     * this.redisTemplate.opsForList(); // 提供了操作list类型的所有方法
     * this.redisTemplate.opsForSet(); //提供了操作set的所有方法
     * this.redisTemplate.opsForHash(); //提供了操作hash表的所有方法
     * this.redisTemplate.opsForZSet(); //提供了操作zset的所有方法
     *
     * @param lettuceConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        //设置key序列化方式string
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化方式json，使用GenericJackson2JsonRedisSerializer替换默认序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
```



##  2.Redis单线程vS多线程(入门篇)

###  2.1 redis单线程

#####  2.1.1  redis是单线程还是多线程

**不限定版本问是否单线程也不太严谨**。Redis的版本很多，如3.x、4.x、6.x，版本不同架构也是不同的。版本3.x ，最早版本，也就是大家口口相传的redis是单线程。版本4.x，严格意义来说也不是单线程，而是负责处理客户端请求的线程是单线程，但是开始加了点多线程的东西(异步删除)。2020年5月版本的6.0.x后及2022年出的7.0版本后，告别了大家印象中的单线程，用一种全新的多线程来解决问题。redis的几个里程碑式的重要版本：

![image-20231208051320138](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220413483.png)

Redis是基于内存操作的，他的瓶颈可能是机器的内存或者网络带宽而并非 CPU，既然 CPU 不是瓶颈，那么自然就采用单线程的解决方案了，况且使用多线程比较麻烦。但是**在 Redis 4.0 中开始支持多线程了，例如后台删除、备份等功能**。官网关于redis单线程和多线程的介绍：

```
Redis是单线程的。如何利用多个CPU/内核?

CPU并不是您使用Redis的瓶颈，因为通常Redis要么受内存限制，要么受网络限制。例如，使用在平均Linux系统上运行的流水线Redis每秒可以发送一百万个请求，因此，如果您的应用程序主要使用O (N)或o(log (N)）命令，则几乎不会使用过多的CPU

为了最大程度地利用CPU，可以在同一框中启动多个Redis实例，并将它们视为不同的服务器。在某个时候，单个盒子可能还不够，因此，如果您要使用多个CPU，则可以开始考虑更早地进行分片的某种方法

Redis 4.0开始使Redis具有更多线程，Redis 4.0仅限于在后台删除对象，以及阻止通过Redis模块实现的命令。对于将来的版本，计划是使Redis越来越线程化
```



#####  2.1.2 reids单线程的缺陷 | lazyfree机制

既然单线程这么好，为什么逐渐又加入了多线程特性?

单线程的缺陷举例：redis是单线程原子命令操作，当需要删除一个很大的数据时就会导致 Redis 服务卡顿。于是在 Redis 4.0 中就新增了多线程的模块，当然此版本中的多线程主要是为了解决删除数据效率比较低的问题的。正常情况下使用 del 指令可以很快的删除数据，而当被删除的 key 是一个非常大的对象时，例如包含了成千上万个元素的 hash 集合时，那么 del 指令就会造成 Redis 主线程卡顿。这就是redis3.x单线程时代最经典的故障，大key删除的头疼问题。由于redis是单线程的，del bigKey等待很久这个线程才会释放，类似加了一个synchronized锁，你可以想象高并发下，程序堵成什么样子？



如何解决单线程下删除bigKey的问题：lazyfree机制可以有效的避免 Redis卡顿的问题。redis重度使用患者应该都遇到过使用 DEL 命令删除体积较大的键， 又或者在使用 FLUSHDB 和 FLUSHALL 删除包含大量键的数据库时，造成redis阻塞的情况；另外redis在清理过期数据和淘汰内存超限的数据时，如果碰巧撞到了大体积的键也会造成服务器阻塞。为了解决以上问题， redis 4.0 引入了lazyfree的机制，它可以将删除键或数据库的操作放在后台线程里执行， 从而尽可能地避免服务器阻塞



lazyfree机制：lazyfree的原理不难想象，就是在删除对象时只是进行逻辑删除，然后把对象丢给后台，让后台线程去执行真正的destruct，避免由于对象体积过大而造成阻塞。redis的lazyfree实现即是如此，下面我们由几个命令来介绍下lazyfree的实现

```shell
# 把删除工作交给了后台的小弟（子线程）异步来删除数据了
unlink key
flushdb async
flushall async
```

因为Redis是单个主线程处理，redis之父antirez一直强调"Lazy Redis is better Redis"。而lazy free的本质就是把某些cost(主要时间复杂度，占用主线程cpu时间片)较高的删除操作从redis主线程剥离让bio子线程来处理，极大地减少主线阻塞时间。从而减少删除导致性能和稳定性问题。在Redis 4.0引入了多个线程来实现数据的异步惰性删除等功能，但是其处理读写请求的仍然只有一个线程，所以仍然算是狭义上的单线程

#####  2.1.3 redis单线程的含义

Redis是单线程主要是指Redis的网络IO和键值对读写是由一个线程来完成的，Redis在处理客户端的请求时包括获取 (socket 读)、解析、执行、内容返回 (socket 写) 等都由一个顺序串行的主线程处理，这就是所谓的“单线程”。这也是Redis对外提供键值存储服务的主要流程。但Redis的其他功能，比如持久化RDB、AOF、异步删除、集群数据同步等等，其实是由额外的线程执行的。Redis命令工作线程是单线程的，但是整个Redis来说，是多线程的。Redis工作线程是单线程的，但是对于整个Redis来说是多线程的

![image-20231208051538006](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220413677.png)

`Redis3.x`单线程时代但性能依旧很快的主要原因：

```sh
基于内存操作: Redis的所有数据都存在内存中，因此所有的运算都是内存级别的，所以他的性能比较高

数据结构简单:Redis的数据结构是专门设计的，而这些简单的数据结构的查找和操作的时间大部分复杂度都是0(1)，因此性能比较高

多路复用和非阻塞V/O:Reds使用I/O多路复用功能来监听多个socket连接客户端，这样就可以使用一个线程连接来处理多个请求，减少线程切换带来的开销，同时也避免了V/O阻塞操作

避兔免上下文切换:因为是单线程模型，因此就避免了不必要的上下文切换和多线程竞争，这就省去了多线程切换带来的时间和性能上的消耗，而且单线程不会导致死锁问题的发生
```

###  2.2 redis6/7的多线程特性和lO多路复用

#####  2.2 1  Redis性能瓶颈：内存或者网络lO

**Redis主要的性能瓶颈是内存或者网络lO而并非CPU**：在Redis6/7中，非常受关注的第一个新特性就是多线程。这是因为，Redis一直被大家熟知的就是它的单线程架构，虽然有些命令操作可以用后台线程或子进程执行（比如数据删除、快照生成、AOF重写）。但是，从网络IO处理到实际的读写命令处理，都是由单个线程完成的。 随着网络硬件的性能提升，Redis的性能瓶颈有时会出现在网络IO的处理上，也就是说，单个主线程处理网络请求的速度跟不上底层网络硬件的速度

 

**Redis 6/7采用多个IO线程来处理网络请求，提高网络请求处理的并行度**。Redis的多IO线程只是用来处理网络请求的，对于读写操作命令Redis仍然使用单线程来处理。这是因为，Redis处理请求时，网络处理经常是瓶颈，通过多个IO线程并行处理网络操作，可以提升实例的整体处理性能。而继续使用单线程执行命令操作，就不用为了保证Lua脚本、事务的原子性，额外开发多线程互斥加锁机制了(不管加锁操作处理)。这样一来，Redis线程模型的实现就简单了



#####  2.2.2 IO多路复用简介 | epoll | IO多路复用模型

**IO多路复用相关概念** ：

I/O：网络I/O，尤其在操作系统层面指数据在内核态和用户态之间的读写操作

多路：多个客户端连接（连接就是套接字描述符，即socket或者channel)

复用：复用一个或几个线程

文件句柄(File descriptor)：又称文件描述符，简称FD，是计算机科学中的一个术语，是一个用于表述指向文件的引用的抽象化概念。文件描述符在形式上是一个非负整数。实际上，它是一个索引值，指向内核为每一个进程所维护的该进程打开文件的记录表。当程序打开一个现有文件或者创建一个新文件时，内核向进程返回一个文件描述符。在程序设计中，文件描述符这一概念往往只适用于UNIX、Linux这样的操作系统

IO多路复用：一种同步的IO模型，实现一个线程监视多个文件句柄，一旦某个文件句柄就绪就能够通知到对应应用程序进行相应的读写操作，没有文件句柄就绪时就会阻塞应用程序，从而释放CPU资源。IO多路复用下，—个服务端进程同时处理多个套接字描述符。也就是说一个或一组线程处理多个TCP连接，使用单进程就能够实现同时处理多个客户端的连接，无需创建或者维护过多的进程/线程

实现IO多路复用的模型有3种：select、poll、epoll

**epoll场景类别**：

模拟一个tcp服务器处理30个客户socket

假设你是一个监考老师，让30个学生解答一道竞赛考题，然后负责验收学生答卷，你有下面几个选择：

第一种选择(轮询)：按顺序逐个验收，先验收A，然后是B，之后是C、D。。。这中间如果有一个学生卡住，全班都会被耽误,你用循环挨个处理socket，根本不具有并发能力

第二种选择(来一个new一个，1对1服务)：你创建30个分身线程，每个分身线程检查一个学生的答案是否正确。 这种类似于为每一个用户创建一个进程或者线程处理连接

第三种选择(响应式处理，1对多服务)，你站在讲台上等，谁解答完谁举手。这时C、D举手，表示他们解答问题完毕，你下去依次检查C、D的答案，然后继续回到讲台上等。此时E、A又举手，然后去处理E和A。。。这种就是IO复用模型。Linux下的select、poll和epoll就是干这个的



**IO多路复用模型**：

将用户socket对应的文件描述符(FileDescriptor)注册进epoll，然后epoll帮你监听哪些socket上有消息到达，这样就避免了大量的无用操作。此时的socket应该采用非阻塞模式。这样，整个过程只在调用select、poll、epoll这些调用的时候才会阻塞，收发客户消息是不会阻塞的，整个进程或者线程就被充分利用起来，这就是事件驱动，所谓的reactor反应模式

![image-20231209103309500](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220413463.png)

在单个线程通过记录跟踪每一个Socket(I/O流)的状态来同时管理多个I/O流. 一个服务端进程可以同时处理多个套接字描述符。目的是尽量多的提高服务器的吞吐能力。大家都用过nginx，nginx使用epoll接收请求，ngnix会有很多链接进来， epoll会把他们都监视起来，然后像拨开关一样，谁有数据就拨向谁，然后调用相应的代码处理。redis类似同理，这就是IO多路复用原理，有请求就响应，没请求不打扰

IO多路复用原理总结：只使用一个服务端进程可以同时处理多个套接字描述符连接

面试题: redis为什么这么快？IO多路复用+epoll函数使用，才是redis为什么这么快的直接原因，而不是仅仅单线程命令+redis安装在内存中



#####  2.2.3  IO多路复用过程简介

 Unix网络编程中的五种IO模型：

```
Blocking lO-阻塞IO
NoneBlocking lO -非阻塞lO
lOmultiplexing - IO多路复用
signal driven lO–信号驱动lO
asynchronous lO-异步IO
```

IO多路复用(主线程和IO线程协作完成请求处理)流程：服务端和客户端建立Socket连接并分配处理线程、I0线程读取并解析请求、主线程执行请求操作、IO线程回写Socket和主线程清空全局队列

![image-20231209174917780](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220413996.png)

阶段一：服务端和客户端建立Socket连接，并分配处理线程

> 首先，主线程负责接收建立连接请求。当有客户端请求和实例建立Socket连接时，主线程会创建和客户端的连接，并把Socket放入全局等待队列中。紧接着，主线程通过轮询方法把Socket连接分配给IO线程

阶段二：I0线程读取并解析请求

> 主线程一旦把Socket分配给I0线程，就会进入阻塞状态，等待IO线程完成客户端请求读取和解析。因为有多个IO线程在并行处理，所以，这个过程很快就可以完成

阶段三：主线程执行请求操作

> 等到IO线程解析完请求，主线程还是会以单线程的方式执行这些命令操作

阶段四：IO线程回写Socket和主线程清空全局队列

> 当主线程执行完请求操作后，会把需要返回的结果写入缓冲区，然后，主线程会阻塞等待I0线程，把这些结果回写到Socket中，并返回给客户端。和lO线程读取和解析请求一样，I0线程回写Socket时，也是有多个线程在并发执行，所以回写Socket的速度也很快。等到O线程回写Socket完毕，主线程会清空全局队列，等待客户端的后续请求



#####  2.2.4 IO多路复用流程精讲

主线程和IO线程是怎么协作完成请求处理的：I/O 的读和写本身是堵塞的，比如当 socket 中有数据时，Redis 会通过调用先将数据从内核态空间拷贝到用户态空间，再交给 Redis 调用，而这个拷贝的过程就是阻塞的，当数据量越大时拷贝所需要的时间就越多，而这些操作都是基于单线程完成的

![image-20231209104631152](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220413211.png)

从Redis6开始，就新增了多线程的功能来提高 I/O 的读写性能，他的主要实现思路是将主线程的 IO 读写任务拆分给一组独立的线程去执行，这样就可以使多个 socket 的读写可以并行化了，采用多路 I/O 复用技术可以让单个线程高效的处理多个连接请求（尽量减少网络IO的时间消耗），将最耗时的Socket的读取、请求解析、写入单独外包出去，剩下的命令执行仍然由主线程串行执行并和内存的数据交互

![image-20231209104811058](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220430430.png)



结合上图可知，网络IO操作就变成多线程化了，其他核心部分仍然是线程安全的，是个不错的折中办法。Redis67将网络数据读写、请求协议解析通过多个IO线程的来处理 ，对于真正的命令执行来说，仍然使用主线程操作

![image-20231209105031013](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220413867.png)



#####  2.2.5 开启redis多线程

在实际应用中如果发现Redis实例的CPU开销不大但吞吐量却没有提升，可以考虑使用Redis7的多线程机制，加速网络处理，进而提升实例的吞吐量。Redis7将所有数据放在内存中，内存的响应时长大约为100纳秒，对于小数据包，Redis服务器可以处理8W到10W的QPS，这也是Redis处理的极限了，对于80%的公司来说，单线程的Redis已经足够使用了。 在Redis6.0及7后，多线程机制默认是关闭的，如果需要使用多线程功能，需要在redis.conf中完成两个设置

![image-20231209122207963](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220413614.png)



1.设置io-thread-do-reads配置项为yes，表示启动多线程

2.设置线程个数。关于线程数的设置，官方的建议是如果为 4 核的 CPU，建议线程数设置为 2 或 3，如果为 8 核 CPU 建议线程数设置为 6，线程数一定要小于机器核数，线程数并不是越大越好

### 2.3 总结

Redis自身出道就是优秀，基于内存操作、数据结构简单、多路复用和非阻塞 I/O、避免了不必要的线程上下文切换等特性，在单线程的环境下依然很快

但对于大数据的 key 删除还是卡顿厉害，因此在 Redis 4.0 引入了多线程unlink key/flushall async 等命令，主要用于 Redis 数据的异步删除

而在 Redis6/7中引入了 I/O 多线程的读写，这样就可以更加高效的处理更多的任务了，Redis 只是将 I/O 读写变成了多线程，而命令的执行依旧是由主线程串行执行的，因此在多线程下操作 Redis 不会出现线程安全的问题

Redis 无论是当初的单线程设计，还是如今与当初设计相背的多线程，目的只有一个：让 Redis 变得越来越快

 

##  3. bigKey

###  3.1 面试题概览

```shell
阿里广告平台：     海量数据里如何查询某一固定前缀的key
小红书：          你如何生产上限制keys */flushdb/flushall等危险命令以防止误删误用?
美团：            MEMORY USAGE命令你用过吗?
Morekey问题：     生产上redis数据库有1000W记录，你如何遍历? key*可以吗?
BigKey问题：      多大算big?你如何发现?如何删除?如何处理?BigKey你做过调优吗?惰性释放lazyfree了解过吗?
```

###  3.2 moreKey 案例

#####  3.2.1  生产上限制`keys */flushdb/flushall`的使用

```
大批量往redis里面插入2000W测试数据key
Linux Bash下面执行，插入100W
通过redis提供的管道--pipe命令插入100W大批量数据
# 生成100W条redis批量设置kv的语句(key=kn,value=vn)写入到/tmp目录下的redisTest.txt文件中
for((i=1;i<=100*10000;i++)); do echo "set k$i v$i" >> /tmp/redisTest.txt ;done;
```

![image-20231209191439346](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220430737.png)

100w的数据需要花费多少秒遍历查询？结论：当key的数量规模非常大时，利用`key *`命令遍历redis中的键值会非常耗时，可能会导致Redis服务卡顿，所有读写Redis的其它的指令都会被延后甚至会超时报错，可能会引起缓存雪崩甚至数据库宕机

![image-20231209191910455](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220430854.png)

某快递巨头真实生产事故案例新闻：

![image-20231211233217961](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220414428.png)



**`key *` 这个指令有致命的弊端，在实际环境中最好不要使用**

> 这个指令没有offset、limit参数，是要一次性吐出所有满足条件的 key，由于redis是单线程的，其所有操作都是原子的，而keys,算法是遍历算法，复杂度是O(n)，如果实例中有千万级以上的key，这个指令就会导致Redis服务卡顿，所有读写Redis的其它的指令都会被延后甚至会超时报错，可能会引起缓存雪崩甚至数据库宕机

**生产上如何限制`keys */flushdb/flushall`等危险命令以防止误删误用?**  通过配置禁用这些命令，`redis.conf`在`SECURITY`这一项中，配置完成后执行这些危险命令就会失败，从而保证redis使用的规范及安全性

![image-20231209192221005](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220414470.png)

#####  3.2.2  使用Scan命令替代 keys *

不用`keys *`避免卡顿，那该用什么？应该使用scan命令来遍历键值key，而不是使用`key *`这种危险的命令

scan命令：

- 作用：Scan命令用于迭代redis数据库中的数据库键。类似mysql的limit但不完全相同

- 官网说明：`https://redis.io/commands/scan/`  
- 官网说明：`https://redis.com.cn/commands/scan.html`

scan命令语法：`SCAN cursor [MATCH pattern][COUNT count]`

```shell
SCAN cursor [MATCH pattern][COUNT count]
    cursor    游标
    pattern   匹配的模式
    count     指定从数据集里返回多少元素，默认值为10

基于游标的迭代器，需要基于上一次的游标延续之前的迭代过程
以0作为游标开始一次新的迭代，直到命令返回游标0完成一次遍历
不保证每次执行都返回某个给定数量的元素，支持模糊查询
一次返回的数量不可控，只能是大概率符合count参数
```

scan命令特点：

```
SCAN 命令是一个基于游标的迭代器，每次被调用之后， 都会向用户返回一个新的游标
用户在下次迭代时需要使用这个新游标作为 SCAN 命令的游标参数， 以此来延续之前的迭代过程
SCAN返回一个包含两个元素的数组
第一个元素是用于进行下一次迭代的新游标,第二个元素则是一个数组,这个数组中包含了所有被迭代的元素
如果新游标返回零表示迭代已结束
SCAN的遍历顺序非常特别，它不是从第一维数组的第零位一直遍历到末尾，而是采用了高位进位加法来遍历
之所以使用这样特殊的方式进行遍历，是考虑到字典的扩容和缩容时避免槽位的遍历重复和遗漏
```

scan命令使用：

![image-20231212001710239](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220414938.png)

![image-20231213064533398](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220414483.png)



###  3.3 BigKey判定 | bigKey的危害 | 产生原因

阿里云Redis开发规范原文对bigKey的判定：

```shell
阿里云Redis开发规范原文
【强制】拒绝bigkey(防止网卡流量、慢查询)
string类型控制在10KB以内，hash、list、set、zset元素个数不要超过5000
反例:一个包含200万个元素的list
非字符串的bigkey，不要使用del删除,使用hscan、sscan、zscan方式渐进式删除
同时要注意防止bigkey过期时间自动删除问题(例如一个200万的zset设置1小时过期，会触发del操作，造成阻塞，而且该操作不会出现在慢查询中(latency可查))
```

BigKey的判定：

```
1.一般情况下，大的内容不是key本身，而是它对应的value
2.如果value是string类型，最大512MB但是≥10KB就是bigkey
3.list、hash、set和zset，个数超过5000就是bigkey
        list： 一个列表最多可以包含2的32次方-1个元素(4294967295,每个列表超过40亿个元素)
        hash： Redis中每个hash可以存储2的32次方-1键值对(40多亿)
        set：  集合中最大的成员数为2的32次方-1(4294967295,每个集合可存储40多亿个成员)
```

bigKey的危害

```shell
内存不均，集群迁移困难
超时删除，大key删除作梗
网络流量阻塞
```

bigKey的产生原因

```
明星粉丝列表：  粉丝逐步递增
汇总统计：     某个报表，月日年经年累月的积累
```

###  3.4  bigkey的发现和排查

**方法一**：

- 排查方法：`redis-cli --bigkeys`

- 好处：给出每种数据结构Top 1 bigkey，同时给出每种数据类型的键值个数+平均大小
- 不足：想查询大于10kb的所有key，--bigkeys参数就无能为力了，需要用到memory usage来计算每个键值的字节数
- 使用案例：

```shell
redis-cli --bigkeys -a 111111
redis-cli -h 127.0.0.1 -p 6379 -a 111111 --bigkeys

# 每隔 100 条 scan 指令就会休眠 0.1s，ops 就不会剧烈抬升，但是扫描的时间会变长
redis-cli -h 127.0.0.1 -p 7001 –-bigkeys -i 0.1
```

![image-20231213070652376](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220414800.png)



**方法二**：

- 排查方法：`MEMORY USAGE 键`   (作用：计算每个键值的字节数)
- 官网：`https://redis.com.cn/commands/memory-usage.html`
- 说明：MEMORY USAGE命令给出一个key和它的值在RAM中所占用的字节数。返回的结果是key的值以及为管理该key分配的内存总字节数。对于嵌套数据类型，可以使用选项SAMPLES，其中count表示抽样的元素个数，默认值为5。当需要抽样所有元素时，使用SAMPLES 0
- 语法：`MEMORY USAGE key [SAMPLES count]` ，redis MEMORY USAGE命令基本语法如下:

```
redis 127.0.0.1:6379>MEMORY USAGE key [SAMPLES count]
```

###  3.5  bigKey的删除

官网：`https://redis.io/commands/scan/`

#####  3.5.1 案例开发规范

阿里云Redis开发规范原文：

```
【强制】:拒绝bigkey(防止网卡流量、慢查询)
string类型控制在10KB以内，hash、list、set、zset元素个数不要超过5000
反例:一个包含200万个元素的list
非字符串的bigkey，不要使用del删除,使用hscan、sscan、zscan方式渐进式删除
同时要注意防止bigkey过期时间自动删除问题(例如一个200万的zset设置1小时过期，会触发del操作，造成阻塞，而且该操作不会出现在慢查询中(latency可查)
```

#####  3.5.2  String类型删除

String类型删除：一般用del，如果过于庞大unlink

#####  3.5.3 hash类型删除

hash类型删除：使用hscan每次获取少量field-value，再使用hdel删除每个field

命令：Redis HSCAN命令用于迭代哈希表中的键值对。redis HSCAN命令基本语法如下：
```
HSCAN key cursor [MATCH pattern] [COUNT count]
        cursor       -游标
        pattern      -匹配的模式
        count        -指定从数据集里返回多少元素，默认值为10。可用版本

命令返回值：
返回的每个元素都是一个元组，每一个元组元素由一个字段(field)和值(value)组成
```

阿里手册：

![image-20231211223923154](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220414423.png)

#####  3.5.4 list类型删除

list类型删除：使用ltrim渐进式逐步删除，直到全部删除完成

命令：Redis Ltrim对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。下标0表示列表的第一个元素，以1表示列表的第二个元素，以此类推。也可以使用负数下标，以-1表示列表的最后一个元素，-2表示列表的倒数第二个元素，以此类推

语法：redis Ltrim命令基本语法如下:

```
redis 127.8.0.1:6379> LTRIM KEY_NAME START STOP
```

命令执行成功时，返回ok

![image-20231211224447465](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220414948.png)

阿里手册：

![image-20231211224522272](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220430057.png)

#####  3.5.5 set类型删除

使用sscan每次获取部分元素，再使用srem命令删除每个元素

命令：

![image-20231211225439869](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220414741.png)

阿里手册

![image-20231211225514906](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220414746.png)

#####  3.5.6 zset类型删除

使用zscan每次获取部分元素，再使用ZREMRANGEBYRANK命令删除每个元素

命令：

![image-20231213071936499](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220430448.png)

阿里手册：

![image-20231211225943109](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220414198.png)



###  3.6  BigKey生产调优

通过redis.conf配置文件中的`LAZY FREEING`配置进行调优

#####  3.6.1 阻塞和非阻塞删除命令

阻塞和非阻塞删除命令

![image-20231211230244717](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220414205.png)

Redis有两个原语来删除键。一种称为DEL，是对象的阻塞删除。这意味着服务器停止处理新命令，以便以同步方式回收与对象关联的所有内存。如果删除的键与一个小对象相关联，则执行DEL命令所需的时间非常短，可与大多数Redis 中其他O(1)或 O(log_N)的命令相媲美。但是，如果键与包含数百万个元素的聚合值相关联，则服务器可能会阻塞很长时间(甚至几秒钟)才能完成操作

基于上述原因，Redis还提供了非阻塞删除原语，例如UNLINK(非阻塞DEL)以及FLUSHALL和FLUSHDB命令的 ASYNC选项，以便在后台回收内存。这些命令在恒定时间内执行。另一个线程将尽可能快地逐步释放后台中的对象

FLUSHALL和FLUSHDB的DEL、UNLINK和ASYNC选项是用户控制的。这取决于应用程序的设计，以了解何时使用其中一个是个好主意。然而，作为其他操作的副作用，Redis服务器有时不得不删除键或刷新整个数据库。具体而言，Redis在以下场景中独立于用户调用删除对象

#####  3.6.2 优化配置



![image-20231211230621720](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220414651.png)



## 4.缓存双写一致性之更新策略

### 4.1 双写一致性面试题

```shell
只要用缓存，就可能会涉及到redis缓存与数据库双存储双写，只要是双写，就一定会有数据一致性的问题，那么如何解决一致性问题?
双写一致性，先动缓存redis还是数据库mysql? why?
延时双删做过吗?会有哪些问题?
有这么一种情况，微服务查询redis无mysql有，为保证数据双写一致性回写redis需要注意什么?双检加锁策略了解过吗?
如何尽量避免缓存击穿?
redis和mysql双写100%会出纰漏，做不到强一致性，你如何保证最终一致性?
```



![image-20231213073236298](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220414163.png)

问题，上面业务逻辑你用java代码如何写？



###  4.2 缓存双写一致性

```
如果redis中有数据,需要和数据库中的值相同。如果redis中无数据，数据库中的值要是最新值，且准备回写redis

缓存按照操作来分，细分2种：只读缓存、读写缓存
读写缓存的策略有：同步直写策略、异步缓写策略

同步直写策略：
            写数据库后也同步写redis缓存，缓存和数据库中的数据一致
            对于读写缓存来说，要想保证缓存和数据库中的数据一致，就要采用同步直写策略
异步缓写策略：
          正常业务运行中，mysql数据变动了，但是可以在业务上容许出现一定时间后才作用于redis，比如仓库、物流系统
          异常情况出现了，不得不将失败的动作重新修补，有可能需要借助kafka或者RabbitMQ等消息中间件，实现重试重写

```

—图代码教你如何写：

![image-20231213074557665](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220415504.png)

###  4.3 双检加锁策略

采用双检加锁策略：

```
多个线程同时去查询数据库的这条数据，那么可以在第一个查询数据的请求上使用一个互斥锁来锁住它
其他的线程走到这一步拿不到锁就等着，等第一个线程查询到了数据，然后做缓存
后面的线程进来发现已经有缓存了，就直接走缓存
```

![image-20231213074847799](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220415834.png)

代码：

```java
package com.atguigu.redis.service;
import com.atguigu.redis.entities.User;
import com.atguigu.redis.mapper.UserMapper;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
@Service
@Slf4j
public class UserService {
    public static final String CACHE_KEY_USER = "user:";
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 业务逻辑没有写错，对于小厂中厂(QPS《=1000)可以使用，但是大厂不行
     * @param id
     * @return
     */
    public User findUserById(Integer id)
    {
        User user = null;
        String key = CACHE_KEY_USER+id;

        //1 先从redis里面查询，如果有直接返回结果，如果没有再去查询mysql
        user = (User) redisTemplate.opsForValue().get(key);

        if(user == null)
        {
            //2 redis里面无，继续查询mysql
            user = userMapper.selectByPrimaryKey(id);
            if(user == null)
            {
                //3.1 redis+mysql 都无数据
                //你具体细化，防止多次穿透，我们业务规定，记录下导致穿透的这个key回写redis
                return user;
            }else{
                //3.2 mysql有，需要将数据写回redis，保证下一次的缓存命中率
                redisTemplate.opsForValue().set(key,user);
            }
        }
        return user;
    }


    /**
     * 加强补充，避免突然key失效了，打爆mysql，做一下预防，尽量不出现击穿的情况。
     * @param id
     * @return
     */
    public User findUserById2(Integer id)
    {
        User user = null;
        String key = CACHE_KEY_USER+id;

        //1 先从redis里面查询，如果有直接返回结果，如果没有再去查询mysql，
        // 第1次查询redis，加锁前
        user = (User) redisTemplate.opsForValue().get(key);
        if(user == null) {
            //2 大厂用，对于高QPS的优化，进来就先加锁，保证一个请求操作，让外面的redis等待一下，避免击穿mysql
            synchronized (UserService.class){
                //第2次查询redis，加锁后
                user = (User) redisTemplate.opsForValue().get(key);
                //3 二次查redis还是null，可以去查mysql了(mysql默认有数据)
                if (user == null) {
                    //4 查询mysql拿数据(mysql默认有数据)
                    user = userMapper.selectByPrimaryKey(id);
                    if (user == null) {
                        return null;
                    }else{
                        //5 mysql里面有数据的，需要回写redis，完成数据一致性的同步工作
                        redisTemplate.opsForValue().setIfAbsent(key,user,7L,TimeUnit.DAYS);
                    }
                }
            }
        }
        return user;
    }
}
```



###  4.4 缓存—致性的几种更新策略

数据库和缓存—致性的几种更新策略：总之，要达到最终—致性

```
给缓存设置过期时间，定期清理缓存并回写，是保证最终一致性的解决方案

可以对存入缓存的数据设置过期时间，所有的写操作以数据库为准，对缓存操作只是尽最大努力即可
也就是说如果数据库写成功，缓存更新失败，那么只要到达过期时间，则后面的读请求自然会从数据库中读取新值然后回填缓存，达到一致性，切记，要以mysql的数据库写入库为准

上述方案和后续落地案例是调研后的主流+成熟的做法，但是考虑到各个公司业务系统的差距
不是100%绝对正确，不保证绝对适配全部情况，请自行酌情选择打法，合适的最好
```

可以停机的情况：

```
挂牌报错，凌晨升级，温磬提示，服务降级
单线程，这样重量级的数据操作最好不要多线程
```

4种更新策略

#####  4.4.1  先更新数据库，再更新缓存

异常问题1：

```
1.先更新mysql的某商品的库存，当前商品的库存是100，更新为99个
2.先更新mysql修改为99成功，然后更新redis
3.此时假设异常出现，更新redis失败了，这导致mysql里面的库存是99而redis里面的还是100
4.上述发生，会让数据库里面和缓存redis里面数据不一致，读到redis脏数据
```

异常问题2：

```
【先更新数据库，再更新缓存】A、B两个线程发起调用
【正常逻辑】
1 A update mysql 100
2 A update redis 100
3 B update mysql 80
4 B update redis 80
=============================
【异常逻辑】多线程环境下，A、B两个线程有快有慢，有前有后有并行
1 A update mysql 100
3 B update mysql 80
4 B update redis 80
2 A update redis 100
===========================
最终结果，mysql和redis数据不一致
mysql80,redis100
```

#####  4.4.2 先更新缓存，再更新数据库

不太推荐：业务上一般把mysql作为底单数据库，保证最后解释

异常情况：

```
【先更新缓存，再更新数据库】，A、B两个线程发起调用
【正常逻辑】
1 A update redis 100
2 A update mysql 100
3 B update redis 80
4 B update mysql 80
====================================
【异常逻辑】多线程环境下，A、B两个线程有快有慢有并行
A update redis  100
B update redis  80
B update mysql 80
A update mysql 100
----mysql100,redis80
```

#####  4.4.3 先删除缓存，再更新数据库

异常问题：

步骤分析1，先删除缓存，再更新数据库

```
阳哥自己这里写20秒，是自己故意乱写的，表示更新数据库可能失败，实际中不可能
1.A线程先成功删除了redis里面的数据，然后去更新mysql，此时mysql正在更新中，还没有结束（比如网络延时）, B突然出现要来读取缓存数据
```

![image-20231214063919746](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220415043.png)

步骤分析2，先删除缓存，再更新数据库

```
2.此时redis里面的数据是空的，B线程来读取，先去读redis里数据(已经被A线程delete掉了)，此处出来2个问题：
  2.1   B从mysql获得了旧值
        B线程发现redis里没有(缓存缺失)马上去mysql里面读取，从数据库里面读取来的是旧值
  2.2   B会把获得的旧值写回redis 
        获得旧值数据后返回前台并回写进redis(刚被A线程删除的旧数据有极大可能又被写回了)
```



![image-20231214064229975](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220415650.png)

步骤分析3，先删除缓存，再更新数据库

```
3.A线程更新完mysql，发现redis里面的缓存是脏数据，A线程直接懵逼了，
两个并发操作，一个是更新操作，另一个是查询操作，
A删除缓存后，B查询操作没有命中缓存，B先把老数据读出来后放到缓存中，然后A更新操作更新了数据库
于是，在缓存中的数据还是老的数据，导致缓存中的数据是脏的，而且还一直这样脏下去了
```

上面3步骤串讲梳理：

4 总结流程：

```\
1.请求A进行写操作，删除redis缓存后，工作正在进行中，更新mysql,A还么有彻底更新完mysql，还没commit
2.请求B查询，查询redis发现缓存不存在(被A从redis中删除了)
3.请求B继续，去数据库查询得到了mysql中的旧值(A还没有更新完)
4.请求B将旧值写回redis缓存
5.请求A将新值写入mysql数据库 
```

上述情况就会导致不一致的情形出现

| 时间 | 线程A                                                | 线程B                                                        | 出现的问题                                                   |
| ---- | ---------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| t1   | 请求A进行写操作，删除缓存成功后，工作正在mysql进行中 |                                                              |                                                              |
| t2   |                                                      | 1.缓存中读取不到，立刻读mysql，由于A还没有对mysql更新完，读到的是旧值 2.还把从mysql读取的旧值，写回了redis | 1.A还没有更新完mysql，导致B读到了旧值 2.线程B遵守回写机制，把旧值写回redis，导致其它请求读取的还是旧值，A白干了 |
| t3   | A更新完mysql数据库的值，over                         |                                                              | redis是被B写回的旧值，mysql是被A更新的新值。出现了，数据不一致问题 |

总结一下：先删除缓存，再更新数据库

```
如果数据库更新失败或超时或返回不及时，导致B线程请求访问缓存时发现redis里面没数据
缓存缺失，B再去读取mysql时，从数据库中读取到旧值，还写回redis，导致A白干了
```

解决方案：采用延时双删策略

```
加上sleep的这段时间，就是为了让线程B能够先从数据库读取数据，再把缺失的数据写入缓存
然后，线程A再进行删除。所以，线程A sleep的时间，就需要大于线程B读取数据再写入缓存的时间
这样一来，其它线程读取数据时，会发现缓存缺失，所以会从数据库中读取最新值
因为这个方案会在第一次删除缓存值后，延迟一段时间再次进行删除，所以我们也把它叫做“延迟双删
```



![image-20231214070414292](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220415410.png)

双删方案面试题：

这个删除该休眠多久呢？

```
线程A sleep的时间，就需要大于线程B读取数据再写入缓存的时间
这个时间怎么确定呢？

第一种方法：
在业务程序运行的时候，统计下线程读数据和写缓存的操作时间，自行评估自己的项目的读数据业务逻辑的耗时
以此为基础来进行估算然后写数据的休眠时间则在读数据业务逻辑的耗时基础上加百毫秒即可
这么做的目的，就是确保读请求结束，写请求可以删除读请求造成的缓存脏数据

第二种方法：
新启动一个后台监控程序，比如后面要讲解的WatchDog监控程序，会加时
```

这种同步淘汰策略，吞吐量降低怎么办?

![image-20231215041241340](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220415646.png)

后续看门狗WatchDog源码分析？

#####  4.4.4 先更新数据库，再删除缓存

异常问题：假如缓存删除失败或者来不及，导致请求再次访问redis时缓存命中，读取到的是缓存旧值

| 时间 | 线程A                  | 线程B                                   | 出现的问题                                         |
| ---- | ---------------------- | --------------------------------------- | -------------------------------------------------- |
| t1   | 更新数据库中的值...... |                                         |                                                    |
| t2   |                        | 缓存中立刻命中，此时B读取的是缓存旧值。 | A还没有来得及删除缓存的值，导致B缓存命中读到旧值。 |
| t3   | 更新缓存的数据，over   |                                         |                                                    |

业务指导思想：

微软云：`https://learn.microsoft.com/en-us/azure/architecture/patterns/cache-aside`

阿里巴巴canal也是类似的思想：上述的订阅binlog程序在mysql中有现成的中间件叫canal，可以完成订阅binlog日志的功能

解决方案：

```
流程如下图所示:
(1）更新数据库数据
(2）数据库会将操作信息写入binlog日志当中
(3）订阅程序提取出所需要的数据以及key
(4）另起一段非业务代码，获得该信息
(5）尝试删除缓存操作，发现删除失败
(6）将这些信息发送至消息队列
(7）重新从消息队列中获得该数据，重试操作
```



![image-20231215041935864](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220430901.png)

```
1.可以把要删除的缓存值或者是要更新的数据库值暂存到消息队列中（例如使用Kafka/RabbitMQ等)
2.当程序没有能够成功地删除缓存值或者是更新数据库值时，可以从消息队列中重新读取这些值，然后再次进行删除或更新
3.如果能够成功地删除或更新，就要把这些值从消息队列中去除，以免重复操作，此时也可以保证数据库和缓存的数据一致了，否则还需要再次进行重试
4.如果重试超过的一定次数后还是没有成功，就需要向业务层发送报错信息了，通知运维人员
```

类似经典的分布式事务问题，只有一个权威答案。最终一致性：

```
流量充值，先下发短信实际充值可能滞后5分钟，可以接受
电商发货，短信下发但是物流明天见
```



###  4.5 总结

如何选择方案？利弊如何

```
在大多数业务场景下， 阳哥建议是(仅代表我个人，不权威)优先使用先更新数据库，再删除缓存的方案(先更库→后删存)。理由如下：
1.先删除缓存值再更新数据库，有可能导致请求因缓存缺失而访问数据库，给数据库带来压力导致打满mysql。
2.如果业务应用中读取数据库和写缓存的时间不好估算，那么，延迟双删中的等待时间就不好设置。

多补充一句：如果使用先更新数据库，再删除缓存的方案
如果业务层要求必须读取一致性的数据，那么就需要在更新数据库时，先在Redis缓存客户端暂停并发读请求，等数据库更新完、缓存值删除后，再读取数据
从而保证数据一致性，这是理论可以达到的效果，但实际，不推荐，因为真实生产环境中，分布式下很难做到实时一致性，一般都是最终一致性，请大家参考
```

总结：

| 策略                             | 是否高并发多线程 | 问题                                         | 现象                                                         | 解决方案                                          |
| -------------------------------- | ---------------- | -------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------- |
| 先删除redis缓存，再更新mysql     | 否               | 缓存删除成功但数据库更新失败                 | Java程序从数据库中读到旧值                                   | 再次更新数据库，重试                              |
|                                  | 是               | 缓存删除成功但数据库更新中......有并发读请求 | 并发请求从数据库读到旧值并回写到redis，导致后续都是从redis读取到旧值 | 延迟双删                                          |
| **先更新mysql，再删除redis缓存** | 否               | 数据库更新成功，但缓存删除失败               | Java程序从redis中读到旧值                                    | 再次删除缓存，重试                                |
|                                  | 是               | 数据库更新成功但缓存删除中......有并发读请求 | 并发请求从缓存读到旧值                                       | 等待redis删除完成，这段时间有数据不一致，短暂存在 |



 ##  5.Redis与MySQL数据双写一致性工程落地案例

###  5.1 缓存一致性策略回顾

实现缓存一致性较优秀做法：先更新数据库，再删除缓存

```
实现缓存一致性一般采用如下策略：
    思路：先动mysql，再动redis，两害相衡取其轻，避免redis业务key突然消失后导致多线程请求打满mysql
    实现：先更新数据库，再删除缓存。尝试使用双检加锁机制lock住mysql，只让一个请求线程回写redis，完成数据一致性
```

双检加锁策略回顾：

```
多个线程同时去查询数据库的某条数据
可以在第一个查询数据的请求上使用一个互斥锁来锁住它
其他的线程走到这一步拿不到锁就等着
等第一个线程查询到了数据，然后做缓存
后面的线程进来发现已经有缓存了，就直接走缓存
```

![image-20231215061149209](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220415777.png)

###  5.2 canal简介 & canal工作原理

#####  5.2.1 canal简介

> 面试题提问：mysql有记录改动了(有增删改写操作)，如何立刻同步反应到redis？怎么知道mysql有改动？

canal简介：canal [kə'næl]，中文翻译为 水道/管道/沟渠/运河，主要用途是用于MySQL数据库增量日志数据的订阅、消费和解析，提供增量数据订阅和消费，是阿里巴巴开发并开源的，采用Java语言开发；历史背景是早期阿里巴巴因为杭州和美国双机房部署，存在跨机房数据同步的业务需求，实现方式主要是基于业务 trigger（触发器） 获取增量变更。从2010年开始，阿里巴巴逐步尝试采用解析数据库日志获取增量变更进行同步，由此衍生出了canal项目

canal官网地址：`https://github.com/alibaba/canal/wiki`

canal作用：数据库镜像数据库实时备份、索引构建和实时维护(拆分异构索引、倒排索引等)、业务cache刷新、带业务逻辑的增量数据处理

下载地址：`https://github.com/alibaba/canal/releases/tag/canal-1.1.6`

#####  5.2.2 GPT关于canal的介绍

阿里巴巴的 Canal 是一款开源的数据库同步工具，旨在提供高性能、高可靠性的数据库增量订阅与消费服务。它支持从 MySQL、PostgreSQL 等数据库实例中捕获数据变更，并将这些变更传输到下游存储、消息系统或数据仓库中。Canal 的设计目标是实时的增量数据同步和订阅，使得数据在不同数据存储之间能够高效地流通和更新

Canal 支持多种应用场景，包括实时数据备份、数据同步、数据订阅和数据消费。它能够保证数据的一致性，并提供了易用且高效的数据订阅方案。通过 Canal，用户可以以简单的方式实现数据库之间、数据库与消息系统之间的实时数据同步，并且能对数据进行灵活的加工处理，满足不同场景下的数据需求

Canal 的工作原理主要分为三个步骤：首先是通过数据库的 binlog 获取增量数据变更信息；然后将这些变更信息解析成实际的数据；最后通过网络协议将数据传输到下游消费者中。这一设计使得 Canal 能够在保证高性能、高稳定性的同时，提供了对不同数据库类型的广泛支持

在使用 Canal 时，用户可以根据自身需求进行灵活的配置和部署，以满足不同的数据同步和消费场景。Canal 提供了友好的管理界面和丰富的监控指标，帮助用户实时查看数据同步状态、监控性能指标，保证数据同步的安全可靠

总的来说，阿里巴巴的 Canal 作为一款开源数据库同步工具，具有高性能、高可靠性，并且提供了广泛的数据库支持和丰富的功能，能够满足用户在不同数据同步场景下的需求，为用户提供了可靠而高效的数据同步解决方案

#####  5.2.3 canal工作原理

MySQL的主从复制将经过如下步骤：
           1.当 master主服务器上的数据发生改变时，则将其改变写入二进制事件日志文件中
           2.salve从服务器会在一定时间间隔内对master主服务器上的二进制日志进行探测，探测其是否发生过改变
           3.如果探测到master主服务器的二进制事件日志发生了改变，则开始一个I/O Thread请求master二进制事件日志
           4.同时 master 主服务器为每个 I/O Thread 启动一个dump  Thread，用于向其发送二进制事件日志
           5.slave从服务器将接收到的二进制事件日志保存至自己本地的中继日志文件中
           6.salve从服务器将启动 SQL Thread 从中继日志中读取二进制日志，在本地重放，使得其数据和主服务器保持一致
           7.最后 I/O Thread 和 SQL Thread 将进入睡眠状态，等待下一次被唤醒



![image-20231215062038608](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220415555.png)



canal工作原理：`canal`模拟`MySQL slave` 的交互协议，伪装自己为`MySQL slave`，向`MySQL master`发送`dump`协议 `MySQL master` 收到`dump`请求，开始推送`binary log`给`slave`(即`canal`)，`canal`解析`binary log`对象(原始为byte流)



![image-20231215062457743](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220415808.png)



###  5.3  整合canal实现双写一致性

整合canal案例官方文档：`https://github.com/alibaba/canal/wiki/ClientExample`

#####  5.3.1 mysql配置

查看mysql版本

```sql
SELECT VERSION();
# mysql5.7.28
```

查看当前的主机二进制日志：

```sql
show master status;
```

查看是否启用二进制日志记录

```sql
# 'log_bin' 用于控制是否启用二进制日志记录，这对于数据库的备份和复制非常重要
SHOW VARIABLES LIKE 'log_bin';
```

在配置文件(window下为`my.ini`，linux下为`my.cnf`)中开启MySQL的binlog写入功能：

 ```ini
 log-bin=mysql-bin   #开启binlog
 binlog-format=ROW   #选择ROW模式
 server_id=1         #配置MySQL replaction需要定义，不要和canal的slaveId重复
 ```

配置说明：

- **ROW模式**：除了记录sql语句之外，还会记录每个字段的变化情况，能够清楚的记录每行数据的变化历史，但会占用较多的空间
- **STATEMENT模式**：只记录了sql语句，但是没有记录上下文信息，在进行数据恢复的时候可能会导致数据的丢失情况
- **MIX模式**：比较灵活的记录，理论上说当遇到了表结构变更的时候，就会记录为statement模式。当遇到了数据更新或者删除情况下就会变为row模式

```ini
[mysqld]
log-bin=mysql-bin     #开启binlog
binlog-format=ROW     #选择ROW模式
server_id=1           #配置MySQL replaction需要定义，不要和canal的 slaveId重复
#设置3306端口
port = 3306
#设置mysql的安装目录
basedir=D:\devSoft\mysql\mysql5.7.28    
#设置mysql数据库的数据的存放目录
datadir=D:\devSoft\mysql\mysq15.7.28\data
#允许最大连接数
max_connections=200
#服务端使用的字符集默认为8比特编码的latin1字符集
character-set-server=utf8
#创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
```

重启mysql，再次查看是否启用二进制日志记录

```sql
SHOW VARIABLES LIKE 'log_bin';
```

授权canal连接MySQL账号：

```sql
# 查看mysql用户，mysql默认的用户在mysql库的user表里
SELECT * FROM mysql.`user`;
# 默认没有canal账户，此处新建+授权
DROP USER IF EXISTS 'canal'@'%';
CREATE USER 'canal'@'%' IDENTIFIED BY 'canal';  
GRANT ALL PRIVILEGES ON *.* TO 'canal'@'%' IDENTIFIED BY 'canal';  
# 刷新权限
FLUSH PRIVILEGES;
SELECT * FROM mysql.user;
```

#####  5.3.2  canal服务端安装配置

1.下载：地址`https://github.com/alibaba/canal/releases/tag/canal-1.1.6`，下载Linux版本`canal.deployer-1.1.6.tar.gz`

2.解压：`解压后整体放入/mycanal路径下`

![image-20231215065206818](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220415909.png)

3.配置：修改`/mycanal/conf/example`路径下`instance.properties`文件。`instance.properties`：换成自己的mysql主机master的IP地址、换成自己的在mysql新建的canal账户

![image-20231215065654214](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220415499.png)

4.启动：`/opt/mycanal/bin`路径下执行`./startup.sh`（注意：linux环境下要安装java8才能使用canal）

5.判断canal是否启动成功：查看server日志

![image-20231215065951751](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220415294.png)

查看样例example的日志：

![image-20231215070031938](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220415190.png)

#####   5.3.3 canal客户端(Java编写业务程序)

整合canal案例官方文档：`https://github.com/alibaba/canal/wiki/ClientExample`

1.随便选个数据库，以具体情况为主，本例bigdata，按照下面建表

 ```sql
 CREATE TABLE `t_user` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `userName` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
 ```

2.新建项目模块`canal_demo02`：引入`canal`依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.atguigu.canal</groupId>
    <artifactId>canal_demo02</artifactId>
    <version>1.0-SNAPSHOT</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.14</version>
        <relativePath/>
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <junit.version>4.12</junit.version>
        <log4j.version>1.2.17</log4j.version>
        <lombok.version>1.16.18</lombok.version>
        <mysql.version>5.1.47</mysql.version>
        <druid.version>1.1.16</druid.version>
        <mapper.version>4.1.5</mapper.version>
        <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
    </properties>

    <dependencies>
        <!--canal-->
        <dependency>
            <groupId>com.alibaba.otter</groupId>
            <artifactId>canal.client</artifactId>
            <version>1.1.0</version>
        </dependency>
        <!--SpringBoot通用依赖模块-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--swagger2-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>
        <!--SpringBoot与Redis整合依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>
        <!--SpringBoot与AOP-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
        </dependency>
        <!--Mysql数据库驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>
        <!--SpringBoot集成druid连接池-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>${druid.version}</version>
        </dependency>
        <!--mybatis和springboot整合-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>${mybatis.spring.boot.version}</version>
        </dependency>
        <!--通用基础配置junit/devtools/test/log4j/lombok/hutool-->
        <!--hutool-->
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.2.3</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${junit.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>${log4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <optional>true</optional>
        </dependency>
        <!--persistence-->
        <dependency>
            <groupId>javax.persistence</groupId>
            <artifactId>persistence-api</artifactId>
            <version>1.0.2</version>
        </dependency>
        <!--通用Mapper-->
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper</artifactId>
            <version>${mapper.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure</artifactId>
        </dependency>
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>3.8.0</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

3.修改配置文件

```properties
server.port=5555
# ========================alibaba.druid=====================
#主启动类：
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/bigdata?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.druid.test-while-idle=false
```

4.启动类

```java
package com.atguigu.canal;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class CanalDemo02App
{
     //本例不要启动CanalDemo02App实例
}
```

5.`RedisUtils`类：静态代码块中创建redis连接池，`getJedis`方法获取reids连接

```java
package com.atguigu.canal.util;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
public class RedisUtils
{
    public static final String  REDIS_IP_ADDR = "192.168.111.185";
    public static final String  REDIS_pwd = "111111";
    public static JedisPool jedisPool;

    static {
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPool=new JedisPool(jedisPoolConfig,REDIS_IP_ADDR,6379,10000,REDIS_pwd);
    }

    public static Jedis getJedis() throws Exception {
        if(null!=jedisPool){
            return jedisPool.getResource();
        }
        throw new Exception("Jedispool is not ok");
    }

}
```

6.`RedisCanalClientExample`类：通过canal监听mysql的数据变化并同步到redis，mysql发生数据的变更，redis的数据也会跟着变化

```java
package com.atguigu.canal.biz;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import com.atguigu.canal.utils.RedisUtils;
import redis.clients.jedis.Jedis;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RedisCanalClientExample {
    public static final Integer _60SECONDS = 60;
    public static final String REDIS_IP_ADDR = "192.168.111.185";

    private static void redisInsert(List<Column> columns) {
        JSONObject jsonObject = new JSONObject();
        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
            jsonObject.put(column.getName(), column.getValue());
        }
        if (columns.size() > 0) {
            try (Jedis jedis = RedisUtils.getJedis()) {
                jedis.set(columns.get(0).getValue(), jsonObject.toJSONString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void redisDelete(List<Column> columns) {
        JSONObject jsonObject = new JSONObject();
        for (Column column : columns) {
            jsonObject.put(column.getName(), column.getValue());
        }
        if (columns.size() > 0) {
            try (Jedis jedis = RedisUtils.getJedis()) {
                jedis.del(columns.get(0).getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void redisUpdate(List<Column> columns) {
        JSONObject jsonObject = new JSONObject();
        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
            jsonObject.put(column.getName(), column.getValue());
        }
        if (columns.size() > 0) {
            try (Jedis jedis = RedisUtils.getJedis()) {
                jedis.set(columns.get(0).getValue(), jsonObject.toJSONString());
                System.out.println("---------update after: " + jedis.get(columns.get(0).getValue()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    public static void printEntry(List<Entry> entrys) {
        for (Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }

            RowChange rowChage = null;
            try {
                //获取变更的row数据
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error,data:" + entry.toString(), e);
            }
            //获取变动类型
            EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================&gt; binlog[%s:%s] , name[%s,%s] , eventType : %s", entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(), entry.getHeader().getSchemaName(), entry.getHeader().getTableName(), eventType));

            for (RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == EventType.INSERT) {
                    redisInsert(rowData.getAfterColumnsList());
                } else if (eventType == EventType.DELETE) {
                    redisDelete(rowData.getBeforeColumnsList());
                } else {//EventType.UPDATE
                    redisUpdate(rowData.getAfterColumnsList());
                }
            }
        }
    }



    public static void main(String[] args) {
        System.out.println("---------initCanal() main方法-----------");

        //=================================
        // 创建链接canal服务端，密码可以不写，不写的时候回去读canal的配置文件
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(REDIS_IP_ADDR, 11111), "example", "", "");
        int batchSize = 1000;
        //空闲空转计数器
        int emptyCount = 0;
        System.out.println("---------------------canal init OK，开始监听mysql变化------");
        try {
            connector.connect();
            // connector.subscribe(".*\\..*");表示监控所有库的所有表
            //connector.subscribe(".*\\..*");
            // 最好是订阅具体的数据库，否则性能不好
            connector.subscribe("bigdata.t_user");
            connector.rollback();
            // 逻辑：监控10分钟发现mysql没有数据变动的话就结束程序，否则重置计数器emptyCount
            int totalEmptyCount = 10 * _60SECONDS;
            while (emptyCount < totalEmptyCount) {
                System.out.println("我是canal，每秒一次正在监听:" + UUID.randomUUID().toString());
                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                long batchId = message.getId();
                int size = message.getEntries().size();
                // batchId == -1或者 size == 0 表示mysql没有变动，没有进行增删操作
                if (batchId == -1 || size == 0) {
                    emptyCount++;
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    //计数器重新置零
                    emptyCount = 0;
                    printEntry(message.getEntries());
                }
                connector.ack(batchId); // 提交确认
                // connector.rollback(batchId); // 处理失败, 回滚数据
            }
            System.out.println("已经监听了" + totalEmptyCount + "秒，无任何消息，请重启重试......");
        } finally {
            connector.disconnect();
        }
    }
}
```

#####  5.3.4 补充说明

java程序下`connector.subscribe`配置的过滤正则

![image-20231215071104277](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220416554.png)

关闭资源代码简写，try-with-resources释放资源

```
try-with-resources
jdk1.7后增加了try-with-resources，它是一个声明一个或多个资源的try语句
一个资源作为一个对象，必须在程序结束之后关闭,try -with-resources语句确保在语句的最后每个资源都被关闭
任何实现了java.lang.AutoCloseable和java.io.Closeable的对象都可以使用try-with-resource来实现异常处理和关闭资源
```



##  6.bitmap | hyperloglog | GEO案例实战

###  6.1 面试题

1.抖音电商直播，主播介绍的商品有评论，1个商品对应了1系列的评论。如何实现**排序+展现+取前10条记录**

2.用户在手机App上的签到打卡信息，1天对应1系列用户的签到记录，新浪微博、钉钉打卡签到，**签到情况如何统计**?

3.应用网站上的网页访问信息，1个网页对应1系列的访问点击，淘宝网首页，每天**有多少人浏览首页**?

4.你们公司系统上线后，说一下**UV、PV、DAU**分别是多少?

5.如何**统计每天的新增用户数和第2天的留存用户数**

6.在电商网站的商品评论中，如何**统计评论列表中的最新评论**

7.在签到打卡中，需要**统计一个月内连续打卡的用户数**

8.在网页访问记录中，需要**统计独立访客（Unique Visitor，UV）量**

9.类似今日头条、抖音、淘宝这样用户访问级别都是亿级的，请问如何处理？如何进行亿级数据的收集+清洗+统计+展现

###  6.2 常见的四种统计

**聚合统计**：统计多个集合元素的聚合结果，就是前面讲解过的交差并等集合统计

- 集合运算。交并差集和聚合函数的应用
- A集合：abc12 、B集合：123ax
- 集合的差集运算A - B，属于A但不属于B的元素构成的集合   `SDIFF key [key ...]`
- 集合的并集运算A U B，属于A或者属于B的元素合并后的集合`SUNION key [key ...]`
- 集合的交集运算A ∩ B，属于A同时也属于B的元素构成的集合`SINTER key [key ...]`、   `SINTERCARD numkeys key [key ...][LIMIT limit]`

**排序统计**：抖音短视频的场景，请你设计—个展现列表。考察你的数据结构和设计思路。设计案例和回答思路：以抖音vcr最新的留言评价为案例，所有评论需要两个功能，按照时间排序(正序、反序)+分页显示。 能够排序+分页显示的redis数据结构是什么合适？ 答案：zset最合适。 在面对需要展示最新列表、排行榜等场景时，如果数据更新频繁或者需要分页显示，建议使用ZSet。获取最新评论留言实现思路：添加评论时加当前时间戳作为zset的score进行存储，获取数据时按照时间戳排序即可

```shell
127.0.0.1:6379> ZADD itemz 1621058594271 v1 1621058594272 v2 1621058594277 v3
(integer)3
127.0.0.1:6379> ZRANGE itemz 0 2
1)"v1"
2)"v2"
3)"v3"
127.0.0.1:6379> ZREVRANGE itemz 0 2
1)"v3"
2)"v2"
3)"v1"
127.0.0.1:6379> ZRANGEBYSCORE itemz 1621058594271 1621058594280
1)"v1"
2)"v2"
3)"v3"
127.0.0.1:6379> ZRANGEBYSCORE itemz 1621058594271 1621058594280 limit 0 5
1)"v1"
2)"v2"
3)"v3"
```

**二值统计**：集合元素的取值就只有0和1两种。在钉钉上班签到打卡的场景中，只需要记录有签到(1)或没签到(O)。见bitmap

**基数统计**：指统计一个集合中不重复的元素个数。见hyperloglog

###  6.2 hyperloglog 基数统计

#####  6.2.1  术语 | 应用场景 | hyperloglog命令

**概念术语**：

- **UV **（Unique Visitor)：独立访客，一般理解为客户端IP。需要去重考虑
- **PV**（Page View):  页面浏览量。不用去重
- **DAU**  (Daily Active User)：日活跃用户量。登录或者使用了某个产品的用户数(除去重复登录的用户)。常用于反映网站、互联网应用或者网络游戏的运营情况
- **MAU**：Monthly Active User，月活跃用户量
- **基数**：是一种数据集，去重复后的真实个数.去重复统计功能的基数估计算法就是HyperLogLog
- **基数统计**：用于统计一个集合中不重复的元素个数，就是对集合去重复后剩余元素的计算

![image-20231221211657933](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220416406.png)



 **应用场景**：

- 应用：很多计数类场景，比如 每日注册 IP 数、每日访问 IP 数、页面实时访问数 PV、访问用户数 UV等。因为主要的目标高效、巨量地进行计数，所以对存储的数据的内容并不太关心。也就是说它只能用于统计巨量数量，不太涉及具体的统计对象的内容和精准性
- 具体应用举例：
  - 统计单日一个页面的访问量(PV)，单次访问就算一次
  - 统计单日一个页面的用户访问量(UV)，即按照用户为维度计算，单个用户一天内多次访问也只算一次
  - 多个key的合并统计，某个门户网站的所有模块的PV聚合统计就是整个网站的总PV

**hyperloglog基本命令**：

| 命令                          | 作用                    |
| ----------------------------- | ----------------------- |
| pfadd key element ...         | 将所有元素添加到key中   |
| pfcount key                   | 统计key的估算值(不精确) |
| pgmerge new_key key1 key2 ... | 合并key至新key          |

```
# 命令说明：
The API is constituted of three new commands :
PFADD var element element ... element
PFCOUNT var
PFMERGE dst src src src .. src
The commands prefix is“PF”in honor of Philippe Flajolet [4].
[4]http: / /en.wikipedia.org/wiki/Philippe_Flajolet
```

```shell
# 命令使用案例：
127.0.0.1:6379> pfadd hllo1 1 3 4 5 7 9
1
127.0.0.1:6379> pfadd hllo2 2 4 4 4 6 8 9
1
127.0.0.1:6379> PFCOUNT hllo2
5
127.0.0.1:6379> PFMERGE distResult hllo1 hllo2
oK
127.0.0.1:6379> PFCOUNT distResult
9
```

#####  6.2.2  HyPerLogLog  演化 | 原理

**可以实现去重统计的方法**：HashSet、bitmap

**bitmap存在的问题**：数据量较大的亿级统计,使用bitmaps同样会占用过多的内存。bitmap是通过用位bit数组来表示各元素是否出现，每个元素对应一位，所需的总内存为N个bit。基数计数则将每一个元素对应到bit数组中的其中一位，比如bit数组010010101(按照从零开始下标，有的就是1、4、6、8)。新进入的元素只需要将已经有的bit数组和新加入的元素进行按位或计算就行。这个方式能大大减少内存占用且位操作迅速，But，假设一个样本案例就是一亿个基数位值数据，一个样本就是一亿。如果要统计1亿个数据的基数位值,大约需要内存100000000/8/1024/1024约等于12M,内存减少占用的效果显著，这样得到统计一个对象样本的基数值需要12M。如果统计10000个对象样本(1w个亿级),就需要117.1875G将近120G，可见使用bitmaps还是不适用大数据量下(亿级)的基数计数场景，但是bitmaps方法是精确计算的。样本元素越多内存消耗急剧增大，难以管控+各种慢，对于亿级统计不太合适，大数据害死人，量变引起质变

**hyperloglog的统计原理**：hyperloglog概率算法。通过牺牲准确率来换取空间，对于不要求绝对准确率的场景下可以使用，因为概率算法不直接存储数据本身，通过一定的概率统计方法预估基数值，同时保证误差在一定范围内，由于又不储存数据故此可以大大节约内存。HyperLogLog就是一种概率算法的实现

**Hyperloglog原理说明**：**Hyperloglog只是进行不重复的基数统计，不是集合也不保存数据，只记录数量而不是具体内容**。Hyperloglog提供不精确的去重计数方案,在统计中存在误差，牺牲准确率来换取空间，误差仅仅只是0.81%左右。这个误差如何来的?论文地址和出处：`http://antirez.com/news/75`

#####  6.2.3 淘宝网站首页亿级UV的Redis统计方案

**需求**：UV的统计需要去重，一个用户一天内的多次访问只能算作一次。淘宝、天猫首页的UV，平均每天是1~1.5个亿左右。每天存1.5个亿的IP，访问者来了后先去查是否存在，不存在加入

**方案讨论**：

方案一：mysql过于笨重，不能用mysql进行统计

方案二：用redis的hash结构存储：`redis——hash = <keyDay,<ip,1>>` 。按照ipv4的结构来说明，每个ipv4的地址最多是15个字节(ip = "192.168.111.1"，最多xxx.xxx.xxx.xxx)
某一天的1.5亿 * 15个字节= 2G，一个月60G。内存消耗过大

```
127.0.0.1:6379>lhset 20210518uv 192.168.111.1 1
(integer)1
127.0.0.1:6379>lhget 20210518uv 192.168.111.1
"1"
127.0.0.1:6379> hset 20210518uv 192.168.111.2 1
(integer)1
127.0.0.1:6379> hset 20210518uv 192.168.111.3 1
( integer) 1
127.0.0.1:6379> hset 20210518uv 192.168.111.4 1
(integer) 1
127.0.0.1:6379> hset 20210518uv 192.168.111.5 1
(integer) 1
127.0.0.1:6379> hgetall 20210518uv
1)"192.168.111.1"
2)"1"
3)"192.168.111.2"
4) "1"
5)"192.168.111.3"
6)"1"
7) "192.168.111.4"
8)"1"
```

方案三：使用`Redis HyperLogLog`进行统计

Redis 在2.8.9版本添加了HyperLogLog结构。Redis HtyperLogLog是用来做基数统计的算法，HyperLogLog 的优点是，在输入元素的数量或者体积非常非常大时，计算基数所需的空间总是固定的、并且是很小的。在Redis里面，每个HyperLogLog键只需要花费12KB内存，就可以计算接近2^64个不同元素的基数。这和计算基数时，元素越多，内存就越多的集合形成鲜明对比。但是，因为HyperLogLog只会根据输入元素来计算基数，而不会储存输入元素本身，所以HyperLogLog 不能像集合那样，返回输入的各个元素

为什么是只需要花费12Kb?    Redis使用了214=16384个桶，按照上面的标准差，误差为0.81%，精度相当高。Redis使用一个long型哈希值的前14个比特用来确定桶编号，剩下的50个比特用来做基数估计。而26=64,所以只需要用6个比特表示下标值，在一般情况下，一个HLL数据结构占用内存的大小为16384*6/8= 12kB，Redis将这种情况称为密集(dense）存储。

![image-20231221214106046](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220416219.png)

**HyperLogLogService**：模拟后台有用户点击首页，每个用户来自不同ip地址。将ip地址添加到HyperLogLog 进行基数统计

```java
package com.atguigu.redis.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;
@Service
@Slf4j
public class HyperLogLogService
{
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 模拟后台有用户点击首页，每个用户来自不同ip地址
     */
    @PostConstruct
    public void init()
    {
        log.info("------模拟后台有用户点击首页，每个用户来自不同ip地址");
        new Thread(() -> {
            String ip = null;
            for (int i = 1; i <=200; i++) {
                Random r = new Random();
                ip = r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);

                Long hll = redisTemplate.opsForHyperLogLog().add("hll", ip);
                log.info("ip={},该ip地址访问首页的次数={}",ip,hll);
                //暂停几秒钟线程
                try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        },"t1").start();
    }

}
```

**HyperLogLogController**：查询统计量

```java
package com.atguigu.redis.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@Api(description = "淘宝亿级UV的Redis统计方案")
@RestController
@Slf4j
public class HyperLogLogController
{
    @Resource
    private RedisTemplate redisTemplate;

    @ApiOperation("获得IP去重后的首页访问量")
    @RequestMapping(value = "/uv",method = RequestMethod.GET)
    public long uv()
    {
        //pfcount
        return redisTemplate.opsForHyperLogLog().size("hll");
    }
}
```

###  6.3 GEO 地理坐标

#####  6.3.1 简介 | 经纬度

GEO应用：移动互联网时代LBS应用越来越多，交友软件中附近的小姐姐、外卖软件中附近的美食店铺、打车软件附近的车辆等等。那这种附近各种形形色色的XXX地址位置选择就可以通过GEO进行实现

利用mysql存储地理坐标存在的问题：

- 查询性能问题，如果并发高，数据量大这种查询是要搞垮mysql数据库的
- 一般mysql查询的是一个平面矩形访问，而叫车服务要以我为中心N公里为半径的圆形覆盖
- 精准度的问题，我们知道地球不是平面坐标系，而是一个圆球，这种矩形计算在长距离计算时会有很大误差，mysql不合适

经纬度：经度与纬度的合称组成一个坐标系统又称为地理坐标系统，是一种利用三度空间的球面来定义地球上的空间的球面坐标系统，能够标示地球上的任何一个位置。经线和纬线是人们为了在地球上确定位置和方向的，在地球仪和地图上画出来的并线。和经线相垂直的线叫做纬线(纬线指示东西方向)。纬线是一条条长度不等的圆圈。最长的纬线就是赤道。因为经线指示南北方向，所以经线又叫子午线。 国际上规定，把通过英国格林尼治天文台原址的经线叫做0°所以经线也叫本初子午线。在地球上经线指示南北方向，纬线指示东西方向。东西半球分界线：东经160° 西经20°。经度和维度：经度(longitude)：东经为正数，西经为负数，东西经。纬度(latitude)：北纬为正数，南纬为负数，南北纬。如何获得某个地址的经纬度：`http://api.map.baidu.com/lbsapi/getpoint/`

#####  6.3.2 GEO命令

**GEOADD（添加经纬度坐标）**： 

- 作用：添加经纬度坐标。geoadd用于存储指定的地理空间位置，可以将一个或多个经度(longitude)、纬度(latitude)、位置名称(member)添加到指定的key中
- geoadd语法格式：`GEOADD key longitude latitude member [longitude latitude member ...]`
- 官方文档：`http://www.redis.cn/commands/geoadd.html`
- 使用示例：

```
GEOADD city 116.403963 39.915119 "天安门" 116.403414 39.924091 "故宫" 116.024067 40.362639 "长城"
127.0.0.1:6379>ZRANGE city 0  -1
1)"\xe5\xa4\xa9\xe5\xae\x89\xe9\x97\xa8"
2)"\xe6\x95\x85\xe5\xae\xab"
3)"\xe9\x95\xbf\xe5\x9f\x8e"

# 中文乱码如何处理:
root@zzyy ~]#redis- cli -- raw
127.0.0.1:6379>ZRANGE city 0 -1
天安门
故宫
长城
```

**GEOPOS（返回经纬度）**：

- 作用：geopos用于从给定的key里返回所有指定名称(member)的位置(经度和纬度)，不存在的返回nil
- geopos语法格式：`GEOPOS key member [member ...]`
- 命令示例：

```
127.0.0.1:6379>GEOPOS city 天安门 故宫
116.40396326780319214
39.91511970338637383
116.40341609716415405
39.92409008156928252
```

**GEOHASH（返回坐标的geohash表示）**：

- 作用：geohash用于获取一个或多个位置元素的geohash值
- geohash语法格式: `GEOHASH key member [ member ...]`
- 说明：geohash算法生成的base32编码值。3维变2维变1维：将三维的地球变为二维的坐标、再将二维的坐标转换为一维的点块、最后将一维的点块转换为二进制再通过base32编码
- 命令示例：

```
127.0.0.1:6379> GEOHASH city 天安门 故宫 长城
wx4gof6f2vo
wX4gogfqsjo
wx4t85y1kto
```

**GEODIST（获取两个位置之间距离）**：

- 作用：geodist用于返回两个给定位置之间的距离
- geodist语法格式：`GEODIST key member1 member2 [m|km|ft|mi]` 。后面参数是距离单位：m米、km千米、ft英尺、mi英里
- 使用示例：

```
127.0.0.1:6379> GEODIST city 天安门 长城 km 
59.3390
127.0.0.1:6379> GEODIST city天安门 长城 m
59338.9814
127.0.0.1:6379> GEODIST city 天安门 长城m [m|km|ft|mi]
```

**GEORADIUS（以半径为中心，查找附近的XXX）**：

- 作用：以给定的经纬度为中心， 返回键包含的位置元素当中，与中心的距离不超过给定最大距离的所有位置元素
- georadius语法格式：`GEORADIUS key longitude latitude radius m|km|ft|mi [WITHCOORD] [WITHDIST] [WITHHASH] [COUNT count] [ASC|DESC] [STORE key] [STOREDIST key]`
- 参数说明：
  - WITHDIST: 在返回位置元素的同时，将位置元素与中心之间的距离也一并返回。 距离的单位和用户给定的范围单位保持一致
  - WITHCOORD: 将位置元素的经度和维度也一并返回
  - WITHHASH: 以52位有符号整数的形式，返回位置元素经过原始 geohash 编码的有序集合分值。这个选项主要用于底层应用或者调试， 实际中的作用并不大
  - COUNT：限定返回的记录数
- 命令示例：

```shell
GEORADIUS city 116.418017 39.914402 10 km withdist withcoord count 10 withhash desc
GEORADIUS city 116.418017 39.914402 10 km withdist withcoord count 10 desc
# 王府井经纬度(116.418017 39.914402)
127.0.0.1:6379> GEORADIUS city 116.418017 39.914402 10 km withdist withcoord count 10
天安门
1.2016
116.40396326780319214
39.91511970338637383
故宫
1.6470
116.4034160971641540
539.92409008156928252
127.0.0.1:6379> GEORADIUs city 116.418017_39.91440210 km withdist withcoord count 10 desc
故宫
1.6470
116.40341609716415405
39.92409008156928252
天安门
1.2016
116.40396326780319214
39.91511970338637383
```

**GEORADIUSBYMEMBER（找出位于指定范围内的元素）**：

- 作用：找出位于指定范围内的元素， 但是 `georadiusbymember` 的中心点是由给定的位置元素决定的， 而不是使用经度和纬度来决定中心点
- 使用示例：

```
127.0.0.1:6379>GEORADIUSbymember city 天安门 10 km withdist withcoord count 10 withhash
天安门
0.0000
4069885555089531
116.40396326780319214
39.91511970338637383
故宫
0.9988
4069885568908290
116.40341609716415405
39.92409008156928252
```

#####  6.3.3 实现附近地点的推送

需求分析：美团app附近的酒店。高德地图附近的人或者一公里以内的各种营业厅、加油站、理发店、超市。找个单车

关键命令：GEORADIUS（以给定的经纬度为中心，找出菜—半径内的元素）

思路：只需结合具体需求，将上面的GEO命令用java代码实现即可

`GeoController`：

```java
package com.atguigu.redis7.controller;
import com.atguigu.redis7.service.GeoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Api(tags = "美团地图位置附近的酒店推送GEO")
@RestController
@Slf4j
public class GeoController
{
    @Resource
    private GeoService geoService;

    @ApiOperation("添加坐标geoadd")
    @RequestMapping(value = "/geoadd",method = RequestMethod.GET)
    public String geoAdd()
    {
        return geoService.geoAdd();
    }

    @ApiOperation("获取经纬度坐标geopos")
    @RequestMapping(value = "/geopos",method = RequestMethod.GET)
    public Point position(String member)
    {
        return geoService.position(member);
    }

    @ApiOperation("获取经纬度生成的base32编码值geohash")
    @RequestMapping(value = "/geohash",method = RequestMethod.GET)
    public String hash(String member)
    {
        return geoService.hash(member);
    }

    @ApiOperation("获取两个给定位置之间的距离")
    @RequestMapping(value = "/geodist",method = RequestMethod.GET)
    public Distance distance(String member1, String member2)
    {
        return geoService.distance(member1,member2);
    }

    @ApiOperation("通过经度纬度查找北京王府井附近的")
    @RequestMapping(value = "/georadius",method = RequestMethod.GET)
    public GeoResults radiusByxy()
    {
        return geoService.radiusByxy();
    }

    @ApiOperation("通过地方查找附近,本例写死天安门作为地址")
    @RequestMapping(value = "/georadiusByMember",method = RequestMethod.GET)
    public GeoResults radiusByMember()
    {
        return geoService.radiusByMember();
    }
}
```

`GeoService`：

```java
package com.atguigu.redis7.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Circle;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Slf4j
public class GeoService
{
    public static final String CITY ="city";

    @Autowired
    private RedisTemplate redisTemplate;

    public String geoAdd()
    {
        Map<String, Point> map= new HashMap<>();
        map.put("天安门",new Point(116.403963,39.915119));
        map.put("故宫",new Point(116.403414 ,39.924091));
        map.put("长城" ,new Point(116.024067,40.362639));

        redisTemplate.opsForGeo().add(CITY,map);

        return map.toString();
    }

    public Point position(String member) {
        //获取经纬度坐标
        List<Point> list= this.redisTemplate.opsForGeo().position(CITY,member);
        return list.get(0);
    }

    public String hash(String member) {
        //geohash算法生成的base32编码值
        List<String> list= this.redisTemplate.opsForGeo().hash(CITY,member);
        return list.get(0);
    }

    public Distance distance(String member1, String member2) {
        //获取两个给定位置之间的距离
        Distance distance= this.redisTemplate.opsForGeo().distance(CITY,member1,member2, RedisGeoCommands.DistanceUnit.KILOMETERS);
        return distance;
    }

    public GeoResults radiusByxy() {
        //通过经度，纬度查找附近的,北京王府井位置116.418017,39.914402
        Circle circle = new Circle(116.418017, 39.914402, Metrics.KILOMETERS.getMultiplier());
        //返回50条
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(50);
        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults= this.redisTemplate.opsForGeo().radius(CITY,circle, args);
        return geoResults;
    }

    public GeoResults radiusByMember() {
        //通过地方查找附近
        String member="天安门";
        //返回50条
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(50);
        //半径10公里内
        Distance distance=new Distance(10, Metrics.KILOMETERS);
        GeoResults<RedisGeoCommands.GeoLocation<String>> geoResults= this.redisTemplate.opsForGeo().radius(CITY,member, distance,args);
        return geoResults;
    }
}
```

###  6.4 bitmap

#####  6.4.1 应用场景 | 底层原理

**应用场景**：

- bitmap用于状态统计。可以实现如下一些需求：用户是否登陆过、比如京东每日签到送京豆、电影、广告是否被点击播放过、钉钉打卡上下班，签到统计

- 日活统计连续签到打卡
- 最近一周的活跃用户
- 统计指定用户一年之中的登陆天数
- 某用户按照一年365天，哪几天登陆过？哪几天没有登陆？
- 全年中登录的天数共计多少?

**底层原理**：bitmap是由0和1状态表现的二进制位的bit数组，是用String类型作为底层数据结构实现的一种统计二值状态的数据类型。位图本质是数组，它是基于String数据类型的按位的操作。该数组由多个二进制位组成，每个二进制位都对应一个偏移量(我们可以称之为一个索引或者位格)。Bitmap支持的最大位数是2^32位，它可以极大的节约存储空间，使用512M内存就可以存储多大42.9亿的字节信息(2^32 = 4294967296)



![image-20231221230334506](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220416190.png)

bitmap的底层编码实质是二进制的ascii编码对应。ascii码：

![image-20231221232733956](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220416440.png)

通过如下setbit 和 gitbit命令演示可以看出**bitmap的底层编码实质是二进制的ascii编码对应**：

```shell
127.0.O.1:6379> setbit k1 1 1
1
127.o.0.1:6379> setbit k1 7 1
1
127.0.0.1:6379>
# 01000001是上面命令的二进制表示
127.0.0.1:6379> get k1
A
```

两个setbit命令对k1进行设置后，对应的二进制串就是0100 0001，二进制串0100 0001对应的10进制就是65

![image-20231224055224216](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220416333.png)

#####  6.4.2 bitmap命令

| 命令                        | 作用                                                | 时间复杂度 |
| --------------------------- | --------------------------------------------------- | ---------- |
| setbit key offset val       | 给指定key的值的第offset赋值val                      | O(1)       |
| getbit key offset           | 获取指定key的第offset位                             | O(1)       |
| bitcount key start end      | 返回指定key中[startend]中为1的数量                  | o(n)       |
| bitop operation destkey key | 对不同的二进制存储数据进行位运算(AND、OR、NOT、XOR) | o(n)       |

**setbit和getbit命令使用**： 

```shell
127.0.0.1:6379> setbit sign:u1:202106 0 1
1
127.0.0.1:6379> setbit sign:u1:202106 1 1
1
127.O.0.1:6379> setbit sign:u1:202106 2 1
1
127.0.0.1:6379> setbit sign:u1:202106 3 1
0
127.0.0.1:6379> setbit sign:u1:202106 6 1
1
127.0.0.1:6379> getbit sign:u1:202106 3
1
127.0.0.1:6379> getbit sign:u1:202106 30
0
127.0.0.1:6379>BITCOUNT sign:u1:202106 0 30
(integer) 6
```

**strlen命令**：不是字符串长度而是占据几个字节，超过8位后自己按照8位一组一byte再扩容

```shell
127.0.0.1:6379> setbit k2 0 1
0
127.0.0.1:6379> setbit k2 7 1
0
127.0.0.1:6379> STRLEN k2
1
127.0.0.1:6379> setbit k2 8 1
127.o.0.1:6379> STRLEN k2
2
```

**bitcount命令**：全部键里面含有1的有多少个

```shell
127.0.0.1:6379> setbit k2 0 1
0
127.0.0.1:6379> setbit k2 1 1
0
127.0.0.1:6379> setbit k2 15 1
0
127.0.0.1:6379> bitcount k2
3
```

**BITCOUNT命令**：

```shell
# —年365天，全年天天登陆占用多少字节
127.0.0.1:6379>setbit k2 0 1
0
127.0.0.1:6379> setbit k2 1 1
0
127.0.0.1:6379> setbit k2 15 1
0
127.0.0.1:6379> bitcount k2
3
127.0.0.1:6379> setbit k2 364 1
0
127.0.0.1:6379> BITCOUNT k2
4
127.0.0.1: 6379> STRLEN k2
46
```

**bitop命令**：

```shell
# 连续2天都签到的用户
# 加入某个网站或者系统，它的用户有1000W，做个用户id和位置的映射
# 比如0号位对应用户id：uid-092iok-lkj
# 比如1号位对应用户id：uid-7388c-xxx
127.0.0.1:6379> setbit 20200808 0 1
(integer)0
127.0.0.1:6379> setbit 20200808 1 1
(integer)0
127.0.0.1:6379> setbit 20200808 2 1
(integer)0
127.0.0.1:6379> setbit 20200808 3 1
(integer)0
127.0.0.1:6379> setbit 20200809 0 1
(integer) 0
127.0.0.1:6379> setbit 20200809 2 1
(integer) 0
127.0.0.1:6379> BITCOUNT 20200808
(integer)4
127.0.0.1:6379> BITCOUNT 20200809
(integer) 2
127.0.0.1:6379> BITOP and destkey 20200808 20200809 
(integer) 1
127.0.0.1:6379> BITCOUNT destkey
2
```

#####  6.4.3 bitmap实现签到统计

京东签到领取京豆：签到日历仅展示当月签到数据签到日历需展示最近连续签到天数假设当前日期是20210618，且20210616未签到若20210617已签到且0618未签到，则连续签到天数为1若20210617已签到且0618已签到，则连续签到天数为2连续签到天数越多，奖励越大所有用户均可签到截至2020年3月31日的12个月，京东年度活跃用户数3.87亿，同比增长24.8%，环比增长超2500万，此外，2020年3月移动端日均活跃用户数同比增长46%假设10%左右的用户参与签到，签到用户也高达3千万

 小厂方法，使用传统mysql方式实现签到：

```sql
CREATE TABLE user_sign
(
  keyid BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_key VARCHAR(200),#京东用户ID
  sign_date DATETIME,#签到日期(20210618)
  sign_count INT #连续签到天数
)
 
INSERT INTO user_sign(user_key,sign_date,sign_count)
VALUES ('20210618-xxxx-xxxx-xxxx-xxxxxxxxxxxx','2020-06-18 15:11:12',1);
 
SELECT
    sign_count
FROM
    user_sign
WHERE
    user_key = '20210618-xxxx-xxxx-xxxx-xxxxxxxxxxxx'
    AND sign_date BETWEEN '2020-06-17 00:00:00' AND '2020-06-18 23:59:59'
ORDER BY
    sign_date DESC
    LIMIT 1;
```

传统mysql方式实现签到存在的问题：方法正确但是难以落地实现。签到用户量较小时这么设计能行，但京东这个体量的用户（估算3000W签到用户，一天一条数据，一个月就是9亿数据）。对于京东这样的体量，如果一条签到记录对应着当日用记录，那会很恐怖

大厂方法，基于Redis的Bitmaps实现签到日历：建表-按位-redis bitmap。在签到统计时，每个用户一天的签到用1个bit位就能表示。一个月（假设是31天）的签到情况用31个bit位就可以，一年的签到也只需要用365个bit位，根本不用太复杂的集合类型。按年去存储一个用户的签到情况,365天只需要365/8≈46 Byte，1000W用户量一年也只需要44MB就足够了。假如是亿级的系统，每天使用1个1亿位的Bitmap约占12MB的内存（10^8/8/1024/1024），10天的Bitmap的内存开销约为120MB，内存压力不算太高。在实际使用时，最好对Bitmap设置过期时间，让Redis自动删除不再需要的签到记录以节省内存开销

案例实战见下一章， bitmap类型签到+结合布隆过滤器，案例升级

##  7.布隆过滤器BloomFilter

###  7.1 简介 | 应用场景

**布隆过滤器简介**：

- 布隆过滤器（英语：Bloom Filter）是 1970 年由布隆提出的。它实际上是一个很长的二进制数组(00000000)+一系列随机hash算法映射函数，主要用于判断一个元素是否在集合中。通常我们会遇到很多要判断一个元素是否在某个集合中的业务场景，一般想到的是将集合中所有元素保存起来，然后通过比较确定。链表、树、哈希表等等数据结构都是这种思路。但是随着集合中元素的增加，我们需要的存储空间也会呈现线性增长，最终达到瓶颈。同时检索速度也越来越慢，上述三种结构的检索时间复杂度分别O(n),O(logn),O(1)。这个时候，布隆过滤器（Bloom Filter）就应运而生。布隆过滤器是一种类似set的数据结构，只是统计结果在巨量数据下有点小瑕疵，不够完美。简而言之，布隆过滤器BloomFilter是由一个初值都为零的bit数组和多个哈希函数构成，可以用来快速判断集合中是否存在某个元素
- 作用：布隆过滤器的功能就是判断具体数据是否存在于一个大的集合中
- 设计思想：不保存数据信息，只是在内存中做一个是否存在的标记flag，这样做可以减少内存占用
- 特点：**一个元素如果判断结果为存在时，元素不一定存在，但是判断结果为不存在时，则一定不存在**
- 优点：可以高效地插入和杳询，占用空间少，但是返回的结果是不确定性+不够完美
- 缺点：布隆过滤器可以添加元素，但是不能删除元素，由于涉及hashcode判断依据，删掉元素会导致误判率增加

**应用场景**：

- 现有50亿个电话号码，现有10万个电话号码，如何要快速准确的判断这些电话号码是否已经存在?
- 安全连接网址，全球数10亿的网址判断黑名单校验，识别垃圾邮件。白名单校验，识别出合法用户进行后续处理
-  使用黑名单校验，识别垃圾邮件：发现存在黑名单中的，就执行特定操作。比如：识别垃圾邮件，只要是邮箱在黑名单中的邮件，就识别为垃圾邮件。假设黑名单的数量是数以亿计的，存放起来就是非常耗费存储空间的，布隆过滤器则是一个较好的解决方案。把所有黑名单都放在布隆过滤器中，在收到邮件时，判断邮件地址是否在布隆过滤器中即可
- 安全连接网址，全球上10亿的网址判断



###  7.2  布隆过滤器原理

**布隆过滤器数据结构和实现原理**：布隆过滤器(Bloom Filter) 是一种专门用来解决去重问题的高级数据结构，实质就是一个大型位数组和几个不同的无偏hash函数(无偏表示分布均匀)。由一个初值都为零的bit数组和多个个哈希函数构成，用来快速判断某个数据是否存在，但是跟HyperLogLog一样，它也一样有那么一点点不精确，也存在一定的误判概率

**布隆过滤器添加key，查询key的过程**：添加key时使用多个hash函数对key进行hash运算得到一个整数索引值，对位数组长度进行取模运算得到一个位置，每个hash函数都会得到一个不同的位置，将这几个位置都置1就完成了add操作。查询key时只要有其中一位是零就表示这个key不存在，但如果都是1，则不一定存在对应的key。结论：有，是可能有。无，是肯定无

![image-20231222200427358](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220416316.png)

hash冲突导致数据不精准：当有变量被加入集合时，通过N个映射函数将这个变量映射成位图中的N个点，把它们置为 1（假定有两个变量都通过 3 个映射函数）

**布隆过滤器hash冲突导致数据不精准**：哈希函数将任意大小的输入数据转换成特定大小的输出数据的函数，转换后的数据称为哈希值或哈希编码，也叫散列值。如果两个散列值是不相同的（根据同一函数）那么这两个散列值的原始输入也是不相同的。这个特性是散列函数具有确定性的结果，具有这种性质的散列函数称为单向散列函数。散列函数的输入和输出不是唯一对应关系的，如果两个散列值相同，两个输入值很可能是相同的，但也可能不同，这种情况称为“散列碰撞（collision）”。用 hash表存储大数据量时，空间效率还是很低，当只有一个 hash 函数时，还很容易发生哈希碰撞

![image-20231222200543449](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220416941.png)

Java中hash冲突java案例：

```java
public class HashCodeConflictDemo
{
    public static void main(String[] args)
    {
        Set<Integer> hashCodeSet = new HashSet<>();

        for (int i = 0; i <200000; i++) {
            int hashCode = new Object().hashCode();
            if(hashCodeSet.contains(hashCode)) {
                System.out.println("出现了重复的hashcode: "+hashCode+"\t 运行到"+i);
                break;
            }
            hashCodeSet.add(hashCode);
        }
            // 运行到第: 102990 次出现hash冲突,hashcode: 2134400190
            // 运行到第: 108437 次出现hash冲突,hashcode: 651156501
            // 运行到第: 142621 次出现hash冲突,hashcode: 2038112324
            // 运行到第: 143554 次出现hash冲突,hashcode: 1164664992
            // 运行到第: 149534 次出现hash冲突,hashcode: 273791087
            // 运行到第: 178146 次出现hash冲突,hashcode: 996371445
            // 运行到第: 181459 次出现hash冲突,hashcode: 254720071
            // 运行到第: 184087 次出现hash冲突,hashcode: 1872358815
        System.out.println("Aa".hashCode());
        System.out.println("BB".hashCode());
        System.out.println("柳柴".hashCode());
        System.out.println("柴柕".hashCode());
        // 2112
        // 2112
        // 851553
        // 851553
    }
}
```

### 7.3 布隆过滤器解决缓存穿透

#####  7.3.1  bitmap实现布隆过滤器思路

**步骤一：初始化bitmap**。布隆过滤器本质 是由长度为 m 的位向量或位列表（仅包含 0 或 1 位值的列表）组成，最初所有的值均设置为 0

**步骤二：添加占坑位**。向布隆过滤器中添加数据时，为了尽量地址不冲突，会使用多个 hash 函数对 key 进行运算，算得一个下标索引值，然后对位数组长度进行取模运算得到一个位置，每个 hash 函数都会算得一个不同的位置。再把位数组的这几个位置都置为 1 就完成了 add 操作。例如，我们添加一个字符串wmyskxz，对字符串进行多次hash(key) → 取模运行→ 得到坑位

![image-20231222202353468](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220416322.png)

**步骤三：判断是否存在**。向布隆过滤器查询某个key是否存在时，先把这个 key 通过相同的多个 hash 函数进行运算，查看对应的位置是否都为 1，只要有一个位为零，那么说明布隆过滤器中这个 key 不存在；如果这几个位置全都是 1，那么说明极有可能存在；因为这些位置的 1 可能是因为其他的 key 存在导致的，也就是前面说过的hash冲突。比如在 add 了字符串wmyskxz数据之后，很明显下面1/3/5 这几个位置的 1 是因为第一次添加的 wmyskxz 而导致的；此时我们查询一个没添加过的不存在的字符串inexistent-key，它有可能计算后坑位也是1/3/5 ，这就是误判了

![image-20231222202623031](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220416404.png)

**布隆过滤器误判起因**：布隆过滤器的误判是指多个输入经过哈希之后在相同的bit位置1了，这样就无法判断究竟是哪个输入产生的，因此误判的根源在于相同的 bit 位被多次映射且置 1

**布隆过滤器不能删除元素**：因为布隆过滤器的每一个 bit 并不是独占的，很有可能多个元素共享了某一位。如果直接删除这一位的话，会影响其他的元素。布隆过滤器可以添加元素，但是不能删除元素。因为删掉元素会导致误判率增加。使用时最好不要让实际元素数量远大于初始化数量，一次给够避免扩容。当实际元素数量超过初始化数量时，应该对布隆过滤器进行重建，重新分配一个size更大的过滤器，再将所有的历史元素批量add进行

#####  7.3.2  布隆过滤器解决缓存穿透思路

**缓存穿透**：一般情况下，先查询缓存redis是否有该条数据，缓存中没有时，再查询数据库。当数据库也不存在该条数据时，每次查询都要访问数据库，这就是缓存穿透。缓存透带来的问题是，当有大量请求查询数据库不存在的数据时，就会给数据库带来压力，甚至会拖垮数据库

 **布隆过滤器解决缓存穿透的问题**：把已存在数据的key存到布隆过滤器中，相当于redis前面挡着一个布隆过滤器。当有新的请求时，先到布隆过滤器中查询是否存在：如果布隆过滤器中不存在该条数据则直接返回；如果布隆过滤器中已存在，才去查询缓存redis，如果redis里没查询到则再查询Mysql数据库

![image-20231222203732480](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220416718.png)



**整体架构设计**：

![image-20231222204051534](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220416553.png)

#####  7.3.3 mybatis generator代码生成

本节使用MyBatis通用Mapper4来生成代码：

- mybatis-generator官方文档`http://mybatis.org/generator/`

- MyBatis通用Mapper4官网：`https://github.com/abel533/Mapper`


步骤一：建立数据库表

```sql
# t_customer用户表SQL
CREATE TABLE `t_customer` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `cname` varchar(50) NOT NULL,
  `age` int(10) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `sex` tinyint(4) NOT NULL,
  `birth` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_cname` (`cname`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4
```

步骤二：建springboot的Module：mybatis_generator，并引入依赖。`pom.xml`如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.atguigu.redis7</groupId>
    <artifactId>mybatis_generator</artifactId>
    <version>1.0-SNAPSHOT</version>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.6.10</version>
        <relativePath/>
    </parent>
    <properties>
        <!--  依赖版本号 -->
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <java.version>1.8</java.version>
        <hutool.version>5.5.8</hutool.version>
        <druid.version>1.1.18</druid.version>
        <mapper.version>4.1.5</mapper.version>
        <pagehelper.version>5.1.4</pagehelper.version>
        <mysql.version>5.1.39</mysql.version>
        <swagger2.version>2.9.2</swagger2.version>
        <swagger-ui.version>2.9.2</swagger-ui.version>
        <mybatis.spring.version>2.1.3</mybatis.spring.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--Mybatis 通用mapper tk单独使用，自己带着版本号-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.6</version>
        </dependency>
        <!--mybatis-spring-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>${mybatis.spring.version}</version>
        </dependency>
        <!-- Mybatis Generator -->
        <dependency>
            <groupId>org.mybatis.generator</groupId>
            <artifactId>mybatis-generator-core</artifactId>
            <version>1.4.0</version>
            <scope>compile</scope>
            <optional>true</optional>
        </dependency>
        <!--通用Mapper-->
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper</artifactId>
            <version>${mapper.version}</version>
        </dependency>
        <!--persistence-->
        <dependency>
            <groupId>javax.persistence</groupId>
            <artifactId>persistence-api</artifactId>
            <version>1.0.2</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

    <build>
        <resources>
            <resource>
                <directory>${basedir}/src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
            </resource>
            <resource>
                <directory>${basedir}/src/main/resources</directory>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.6</version>
                <configuration>
                    <configurationFile>${basedir}/src/main/resources/generatorConfig.xml</configurationFile>
                    <overwrite>true</overwrite>
                    <verbose>true</verbose>
                </configuration>
                <dependencies>
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>${mysql.version}</version>
                    </dependency>
                    <dependency>
                        <groupId>tk.mybatis</groupId>
                        <artifactId>mapper</artifactId>
                        <version>${mapper.version}</version>
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </build>
</project>
```

步骤三：在`src\mainresources`路径下新建2个配置文件`config.properties`和`generatorConfig.xml`

`config.properties`：

```properties
#t_customer表包名
package.name=com.atguigu.redis7
jdbc.driverClass=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/bigdata
jdbc.user=root
jdbc.password=123456
```

`generatorConfig.xml`：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <properties resource="config.properties"/>

    <context id="Mysql" targetRuntime="MyBatis3Simple" defaultModelType="flat">
        <property name="beginningDelimiter" value="`"/>
        <property name="endingDelimiter" value="`"/>

        <plugin type="tk.mybatis.mapper.generator.MapperPlugin">
            <property name="mappers" value="tk.mybatis.mapper.common.Mapper"/>
            <property name="caseSensitive" value="true"/>
        </plugin>

        <jdbcConnection driverClass="${jdbc.driverClass}"
                        connectionURL="${jdbc.url}"
                        userId="${jdbc.user}"
                        password="${jdbc.password}">
        </jdbcConnection>

        <javaModelGenerator targetPackage="${package.name}.entities" targetProject="src/main/java"/>

        <sqlMapGenerator targetPackage="${package.name}.mapper" targetProject="src/main/java"/>

        <javaClientGenerator targetPackage="${package.name}.mapper" targetProject="src/main/java" type="XMLMAPPER"/>

        <table tableName="t_customer" domainObjectName="Customer">
            <generatedKey column="id" sqlStatement="JDBC"/>
        </table>
    </context>
</generatorConfiguration>
```

步骤四：一键生成。双击插件mybatis-generator:gererate，一键生成生成entity+mapper接口+xml实现SQL

![image-20231223063801367](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220417983.png)



步骤五：在`redis7_study`模块中引入依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.atguigu.redis7</groupId>
    <artifactId>redis7_study</artifactId>
    <version>1.0-SNAPSHOT</version>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.6.10</version>
        <relativePath/>
    </parent>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <junit.version>4.12</junit.version>
        <log4j.version>1.2.17</log4j.version>
        <lombok.version>1.16.18</lombok.version>
    </properties>

    <dependencies>
        <!--SpringBoot通用依赖模块-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--jedis-->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>4.3.1</version>
        </dependency>
        <!--lettuce-->
        <!--<dependency>
            <groupId>io.lettuce</groupId>
            <artifactId>lettuce-core</artifactId>
            <version>6.2.1.RELEASE</version>
        </dependency>-->
        <!--SpringBoot与Redis整合依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>
        <!--swagger2-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>
        <!--Mysql数据库驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>
        <!--SpringBoot集成druid连接池-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.16</version>
        </dependency>
        <!--mybatis和springboot整合-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.0</version>
        </dependency>
        <!--hutool-->
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.2.3</version>
        </dependency>
        <!--persistence-->
        <dependency>
            <groupId>javax.persistence</groupId>
            <artifactId>persistence-api</artifactId>
            <version>1.0.2</version>
        </dependency>
        <!--通用Mapper-->
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper</artifactId>
            <version>4.1.5</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure</artifactId>
        </dependency>
        <!--通用基础配置junit/devtools/test/log4j/lombok/-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${junit.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>${log4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <optional>true</optional>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

步骤六：`\src\main\resources\`目录下新建mapper文件夹并拷贝`CustomerMapper.xml`

```properties
server.port=7777

spring.application.name=redis7_study

# ========================logging=====================
logging.level.root=info
logging.level.com.atguigu.redis7=info
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger- %msg%n 

logging.file.name=D:/mylogs2023/redis7_study.log
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger- %msg%n

# ========================swagger=====================
spring.swagger2.enabled=true
#在springboot2.6.X结合swagger2.9.X会提示documentationPluginsBootstrapper空指针异常，
#原因是在springboot2.6.X中将SpringMVC默认路径匹配策略从AntPathMatcher更改为PathPatternParser，
# 导致出错，解决办法是matching-strategy切换回之前ant_path_matcher
spring.mvc.pathmatch.matching-strategy=ant_path_matcher

# ========================redis单机=====================
spring.redis.database=0
# 修改为自己真实IP
spring.redis.host=192.168.111.185
spring.redis.port=6379
spring.redis.password=111111
spring.redis.lettuce.pool.max-active=8
spring.redis.lettuce.pool.max-wait=-1ms
spring.redis.lettuce.pool.max-idle=8
spring.redis.lettuce.pool.min-idle=0

# ========================alibaba.druid=====================
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/bigdata?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.druid.test-while-idle=false

# ========================mybatis===================
mybatis.mapper-locations=classpath:mapper/*.xml
mybatis.type-aliases-package=com.atguigu.redis7.entities

# ========================redis集群=====================
#spring.redis.password=111111
## 获取失败 最大重定向次数
#spring.redis.cluster.max-redirects=3
#spring.redis.lettuce.pool.max-active=8
#spring.redis.lettuce.pool.max-wait=-1ms
#spring.redis.lettuce.pool.max-idle=8
#spring.redis.lettuce.pool.min-idle=0
##支持集群拓扑动态感应刷新,自适应拓扑刷新是否使用所有可用的更新，默认false关闭
#spring.redis.lettuce.cluster.refresh.adaptive=true
##定时刷新
#spring.redis.lettuce.cluster.refresh.period=2000
#spring.redis.cluster.nodes=192.168.111.185:6381,192.168.111.185:6382,192.168.111.172:6383,192.168.111.172:6384,192.168.111.184:6385,192.168.111.184:6386
```

步骤七：主启动类上添加`@MapperScan`注解

```java
package com.atguigu.redis7;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
@MapperScan("com.atguigu.redis7.mapper") //import tk.mybatis.spring.annotation.MapperScan;
public class Redis7Study7777
{
    public static void main(String[] args)
    {
        SpringApplication.run(Redis7Study7777.class,args);
    }
}
```

步骤八：添加实体类Customer，直接拷贝之前自动生成的实体类Customer

```java
package com.atguigu.redis7.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_customer")
public class Customer implements Serializable
{
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String cname;

    private Integer age;

    private String phone;

    private Byte sex;

    private Date birth;

    public Customer()
    {
    }

    public Customer(Integer id, String cname)
    {
        this.id = id;
        this.cname = cname;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return cname
     */
    public String getCname() {
        return cname;
    }

    /**
     * @param cname
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }
    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return sex
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * @return birth
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * @param birth
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString()
    {
        return "Customer{" +
                "id=" + id +
                ", cname='" + cname + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", birth=" + birth +
                '}';
    }
}
```

步骤九：添加mapper接口

```java
package com.atguigu.redis7.mapper;
import com.atguigu.redis7.entities.Customer;
import tk.mybatis.mapper.common.Mapper;
public interface CustomerMapper extends Mapper<Customer> {
}
```

步骤十：添加`mapper.xml`文件并编写sql

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.atguigu.redis7.mapper.CustomerMapper">
  <resultMap id="BaseResultMap" type="com.atguigu.redis7.entities.Customer">
    <!--
      WARNING - @mbg.generated
    -->
    <id column="id" jdbcType="INTEGER" property="id" />
    <result column="cname" jdbcType="VARCHAR" property="cname" />
    <result column="age" jdbcType="INTEGER" property="age" />
    <result column="phone" jdbcType="VARCHAR" property="phone" />
    <result column="sex" jdbcType="TINYINT" property="sex" />
    <result column="birth" jdbcType="TIMESTAMP" property="birth" />
  </resultMap>
</mapper>
```

`controller`类：

```java
package com.atguigu.redis7.controller;
import com.atguigu.redis7.entities.Customer;
import com.atguigu.redis7.service.CustomerSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.Date;
import java.util.concurrent.ExecutionException;
@Api(tags = "客户Customer接口+布隆过滤器讲解")
@RestController
@Slf4j
public class CustomerController{
    @Resource private CustomerSerivce customerSerivce;

    @ApiOperation("数据库初始化2条Customer数据")
    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    public void addCustomer() {
        for (int i = 0; i < 2; i++) {
            Customer customer = new Customer();
            customer.setCname("customer"+i);
            customer.setAge(new Random().nextInt(30)+1);
            customer.setPhone("1381111xxxx");
            customer.setSex((byte) new Random().nextInt(2));
            customer.setBirth(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            customerSerivce.addCustomer(customer);
        }
    }
    @ApiOperation("单个用户查询，按customerid查用户信息")
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public Customer findCustomerById(@PathVariable int id) {
        return customerSerivce.findCustomerById(id);
    }
}
```

添加service类：

```java
package com.atguigu.redis7.service;
import com.atguigu.redis7.entities.Customer;
import com.atguigu.redis7.mapper.CustomerMapper;
import com.atguigu.redis7.utils.CheckUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
@Service
@Slf4j
public class CustomerSerivce
{
    public static final String CACHE_KEY_CUSTOMER = "customer:";

    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private RedisTemplate redisTemplate;

    public void addCustomer(Customer customer){
        int i = customerMapper.insertSelective(customer);

        if(i > 0)
        {
            //到数据库里面，重新捞出新数据出来，做缓存
            customer=customerMapper.selectByPrimaryKey(customer.getId());
            //缓存key
            String key=CACHE_KEY_CUSTOMER+customer.getId();
            //往mysql里面插入成功随后再从mysql查询出来，再插入redis
            redisTemplate.opsForValue().set(key,customer);
        }
    }

    public Customer findCustomerById(Integer customerId){
        Customer customer = null;
        //缓存key的名称
        String key=CACHE_KEY_CUSTOMER+customerId;
        //1 查询redis
        customer = (Customer) redisTemplate.opsForValue().get(key);
        //redis无，进一步查询mysql
        if(customer==null){
            //2 从mysql查出来customer
            customer=customerMapper.selectByPrimaryKey(customerId);
            // mysql有，redis无
            if (customer != null) {
                //3 把mysql捞到的数据写入redis，方便下次查询能redis命中。
                redisTemplate.opsForValue().set(key,customer);
            }
        }
        return customer;
    }

}
```

启动测试Swagger是否OK：

```
http://localhost:具体微服务端口/swagger-ui.html#/
http://localhost:7777/swagger-ui.html
```

#####  7.5.5  布隆过滤器解决缓存穿透

思路总结：

- setBit构建布隆过滤器过程：@PostConstruct初始化白名单数据，添加的时候先计算元素的hash值，通过hash值算出对应的二进制数组的坑位，将对应坑位的值的修改为数字1，表示存在
- getBit查询是否存在：计算元素的hash值，通过上一步hash值算出对应的二进制数组的坑位，返回对应坑位的值，零表示无，1表示存在

`BloomFilterInit`：初始化白名单数据

```java
package com.atguigu.redis7.filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
/**
 * 布隆过滤器白名单初始化工具类，一开始就设置一部分数据为白名单所有，
 * 白名单业务默认规定：布隆过滤器有，redis也有。
 */
@Component
@Slf4j
public class BloomFilterInit
{
    @Resource
    private RedisTemplate redisTemplate;
    
    //初始化白名单数据，故意差异化数据演示效果
    @PostConstruct
    public void init()
    {
        //白名单客户预加载到布隆过滤器
        String uid = "customer:12";
        //1 计算hashcode，由于可能有负数，直接取绝对值
        int hashValue = Math.abs(uid.hashCode());
        //2 通过hashValue和2的32次方取余后，获得对应的下标坑位
        long index = (long) (hashValue % Math.pow(2, 32));
        log.info(uid+" 对应------坑位index:{}",index);
        //3 设置redis里面bitmap对应坑位，该有值设置为1
        redisTemplate.opsForValue().setBit("whitelistCustomer",index,true);
    }
}
```

CheckUtils：检查键值在redis中是否存在

```java
package com.atguigu.redis7.utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
@Component
@Slf4j
public class CheckUtils
{
    @Resource
    private RedisTemplate redisTemplate;

    public boolean checkWithBloomFilter(String checkItem,String key)
    {
        int hashValue = Math.abs(key.hashCode());
        long index = (long) (hashValue % Math.pow(2, 32));
        boolean existOK = redisTemplate.opsForValue().getBit(checkItem, index);
        log.info("----->key:"+key+"\t对应坑位index:"+index+"\t是否存在:"+existOK);
        return existOK;
    }
}
```

`CustomerController`：

```java
package com.atguigu.redis7.controller;
import com.atguigu.redis7.entities.Customer;
import com.atguigu.redis7.service.CustomerSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.Date;
import java.util.concurrent.ExecutionException;
@Api(tags = "客户Customer接口+布隆过滤器讲解")
@RestController
@Slf4j
public class CustomerController
{
    @Resource private CustomerSerivce customerSerivce;

    @ApiOperation("数据库初始化2条Customer数据")
    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    public void addCustomer() {
        for (int i = 0; i < 2; i++) {
            Customer customer = new Customer();

            customer.setCname("customer"+i);
            customer.setAge(new Random().nextInt(30)+1);
            customer.setPhone("1381111xxxx");
            customer.setSex((byte) new Random().nextInt(2));
            customer.setBirth(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

            customerSerivce.addCustomer(customer);
        }
    }

    @ApiOperation("单个用户查询，按customerid查用户信息")
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public Customer findCustomerById(@PathVariable int id) {
        return customerSerivce.findCustomerById(id);
    }

    @ApiOperation("BloomFilter案例讲解")
    @RequestMapping(value = "/customerbloomfilter/{id}", method = RequestMethod.GET)
    public Customer findCustomerByIdWithBloomFilter(@PathVariable int id) throws ExecutionException, InterruptedException
    {
        return customerSerivce.findCustomerByIdWithBloomFilter(id);
    }
}
```

`CustomerSerivce`：

```java
package com.atguigu.redis7.service;
import com.atguigu.redis7.entities.Customer;
import com.atguigu.redis7.mapper.CustomerMapper;
import com.atguigu.redis7.utils.CheckUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
@Service
@Slf4j
public class CustomerSerivce
{
    public static final String CACHE_KEY_CUSTOMER = "customer:";

    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CheckUtils checkUtils;

    public void addCustomer(Customer customer){
        int i = customerMapper.insertSelective(customer);

        if(i > 0)
        {
            //到数据库里面，重新捞出新数据出来，做缓存
            customer=customerMapper.selectByPrimaryKey(customer.getId());
            //缓存key
            String key=CACHE_KEY_CUSTOMER+customer.getId();
            //往mysql里面插入成功随后再从mysql查询出来，再插入redis
            redisTemplate.opsForValue().set(key,customer);
        }
    }

    public Customer findCustomerById(Integer customerId){
        Customer customer = null;

        //缓存key的名称
        String key=CACHE_KEY_CUSTOMER+customerId;

        //1 查询redis
        customer = (Customer) redisTemplate.opsForValue().get(key);

        //redis无，进一步查询mysql
        if(customer==null)
        {
            //2 从mysql查出来customer
            customer=customerMapper.selectByPrimaryKey(customerId);
            // mysql有，redis无
            if (customer != null) {
                //3 把mysql捞到的数据写入redis，方便下次查询能redis命中。
                redisTemplate.opsForValue().set(key,customer);
            }
        }
        return customer;
    }

    /**
     * BloomFilter → redis → mysql
     * 白名单：whitelistCustomer
     * @param customerId
     * @return
     */

    @Resource
    private CheckUtils checkUtils;
    public Customer findCustomerByIdWithBloomFilter (Integer customerId)
    {
        Customer customer = null;

        //缓存key的名称
        String key = CACHE_KEY_CUSTOMER + customerId;

        //布隆过滤器check，无是绝对无，有是可能有
        //===============================================
        if(!checkUtils.checkWithBloomFilter("whitelistCustomer",key))
        {
            log.info("白名单无此顾客信息:{}",key);
            return null;
        }
        //===============================================

        //1 查询redis
        customer = (Customer) redisTemplate.opsForValue().get(key);
        //redis无，进一步查询mysql
        if (customer == null) {
            //2 从mysql查出来customer
            customer = customerMapper.selectByPrimaryKey(customerId);
            // mysql有，redis无
            if (customer != null) {
                //3 把mysql捞到的数据写入redis，方便下次查询能redis命中。
                redisTemplate.opsForValue().set(key, customer);
            }
        }
        return customer;
    }
}
```

测试：布隆过滤器无，直接返回，不再继续走下去。布隆过滤器有，redis有。布隆过滤器有，redis无

![image-20231223073016866](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220417153.png)

redis的setbit/getbit：

```shell
# redis自带的二进制数组bitmap，布隆过滤器就是whitelistCustomer
127.0.0.1:6379> keys *
hll
whitelistcustomer
k1
customer:10
k2
taobao:ip
list
customer:11
127.0.0.1:6379> type whitelistcustomer
string
127.0.0.1:6379> GETBIT whitelistcustomer 1
0
# 在1772098690这个位置有1表示有customer:11这个用户存在
# key:customer:11 对应坑位 index:1772098756
127.0.0.1:6379> GETBIT whitelistcustomer 1772098690
1
```

###  7.4 总结

布隆过滤器优点：高效地插入和查询，内存占用bit空间少

布隆过滤器缺点：不能删除元素。因为删掉元素会导致误判率增加，因为hash冲突同一个位置可能存的东西是多个共有的，你删除─个元素的同时可能也把其它的删除了

布隆过滤特点：存在误判,不能精准过滤：有，是很可能有。无，是肯定无，100%无

布谷鸟过滤器(了解)：为了解决布隆过滤器不能删除元素的问题，布谷鸟过滤器横空出世。论文《Cuckoo Filter：Better Than Bloom 论文网址：`https://www.cs.cmu.edu/~binfan/papers/conext14_cuckoofilter.pdf#:~:text=Cuckoo%20%EF%AC%81lters%20support%20adding%20and%20removing%20items%20dynamically,have%20lower%20space%20overhead%20than%20space-optimized%20Bloom%20%EF%AC%81lters`。论文作者将布谷鸟过滤器和布隆过滤器进行了深入的对比。按照企业调研，目前用的比较多比较成熟的就是布隆过滤器



##  8.缓存预热＋缓存雪崩＋缓存击穿＋缓存穿透

###  8.1 相关面试题

```
缓存预热、雪崩、穿透、击穿分别是什么?你遇到过那几个情况?
缓存预热你是怎么做的?
如何避免或者减少缓存雪崩?
穿透和击穿有什么区别?他两是一个意思还是截然不同?
穿透和击穿你有什么解决方案?如何避免?
假如出现了缓存不—致，你有哪些修补方案?
```

###  8.2 缓存预热

- 缓存预热就是系统上线时，提前将相关的缓存数据直接加载到缓存系统。避免在用户请求的时候，先查询数据库，然后再将数据缓存。数据直接加载后用户可以直接查询事先被预热的缓存数据

- 缓存预热案例：`@PostConstruct`初始化白名单数据

###  8.3  缓存雪崩

产生缓存雪崩的原因：

- redis主机挂了， Redis全盘崩溃，偏硬件运维
- redis中有大量key同时过期大面积失效，偏软件开发

预防及解决：

- redis中key设置为永不过期or过期时间错开
- redis缓存集群实现高可用：
  - 主从+哨兵
  - Redis Cluster
  - 开启Redis持久化机制aof/rdb，尽快恢复缓存集群

- 多缓存结合预防雪崩：ehcache本地缓存+ redis缓存

- 服务降级：Hystrix或者阿里sentinel限流&降级
- 阿里云-云数据库Redis版：`https:/www.aliyun.com/product/kvstore?spm=5176.54432._3207526240.15.2a3818a5iG19IE`

###  8.4  缓存穿透

#####  8.4.1 缓存穿透简介

缓存穿透：请求去查询—条记录，先查redis无，后查mysql无，都查询不到该条记录，但是请求每次都会打到数据库上面去，导致后台数据库压力暴增。这种现象称为缓存穿透，这时候redis变成了—个摆设。简单说就是本来无—物，两库都没有。既不在Redis缓存库，也不在mysql，数据库存在被多次暴击风险

CHATGPT关于缓存穿透的回答：Redis 缓存穿透是指恶意的或非法的查询导致缓存未命中，从而绕过缓存直接查询数据库，这可能导致数据库负载增加。通常缓存穿透会导致数据库频繁被访问，从而引起性能问题

针对 Redis 缓存穿透，可以考虑以下几种解决方案： 

- **Bloom Filter（布隆过滤器）**：在请求到来时首先使用布隆过滤器进行检查，如果发现请求的数据不存在，则直接拒绝该请求
- **空对象缓存**：如果数据库中不存在某个查询所需的数据，可以将空对象也缓存起来，但需要设置一个较短的过期时间，以避免缓存过多无效数据，这可以有效防止同一查询反复穿透到数据库
- **快速失败机制**：在发现某个查询在缓存中不存在后，立即返回错误或预先设定的默认值，避免直接访问数据库
- **限流措施**：通过限制在特定时间段内对某一数据的请求次数，避免极端情况下的频繁数据库访问



![image-20231224202504545](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220417726.png)



##### 8.4.2   解决缓存穿透：缓存空对象or缺省值

**防止缓存穿透方案一**：缓存空对象或者缺省值，回写增强。如果发生了缓存穿透，可以针对要查询的数据，在Redis里存一个和业务部门商量后确定的缺省值(比如，零、负数、defaultNull等)，比如，键uid:abcdxxx，值defaultNull作为案例的key和value。先去redis查键uid:abcdxxx没有，再去mysql查没有获得 ，这就发生了一次穿透现象。但是，可以增强回写机制，mysql也查不到的话也让redis存入刚刚查不到的key并保护mysql。第一次来查询uid:abcdxxx，redis和mysql都没有，返回null给调用者，但是增强回写后第二次来查uid:abcdxxx，此时redis就有值了。可以直接从Redis中读取default缺省值返回给业务应用程序，避免了把大量请求发送给mysql处理，打爆mysql。但是，此方法架不住黑客的恶意攻击，有缺陷......，只能解决key相同的情况。黑客会对你的系统进行攻击，拿一个不存在的id去查询数据，会产生大量的请求到数据库去查询。可能会导致你的数据库由于压力过大而宕掉。key相同时攻击系统：第一次打到mysql，空对象缓存后第二次就返回defaultNull缺省值，避兔mysql被攻击，不用再到数据库中去走一圈了。key不同时攻击系统：由于存在空对象缓存和缓存回写(看自己业务不限死)。redis中的无关紧要的key也会越写越多(记得设置redis过期时间)  ，可以看出缓存空对象的方案存在弊端，会导致过多无关的key出现

#####  8.4.3   解决缓存穿透：Google布隆过滤器

- Guava中布隆过滤器的实现算是比较权威的，所以实际项目中我们可以直接使用Guava布隆过滤器。谷歌的Guava库中的布隆过滤器实现提供了一个**可配置的误判率**。误判率可以通过几个因素来控制，包括预期的元素数量、预期的误判率和使用的哈希函数数量。在 Guava 的布隆过滤器实现中，你可以通过选择适当的参数来控制误判率，以满足你的应用需求。一般来说，误判率会随着哈希函数的数量增加而减小，同时也会受到预期元素数量和布隆过滤器的大小等因素的影响
- Guava's BloomFilter源码出处：`https://github.com/google/guava/blob/master/guava/src/com/google/common/hash/BloomFilter.java`
- 案例：白名单过滤器
- 白名单过滤器架构说明：让布隆过滤器作为白名单使用，白名单里面有的才让通过，没有直接返回。但是存在误判,由于误判率很小，1%的打到mysql，可以接受。使用注意：所有key都需要往redis和bloomfilter里面放入，全部合法的key都需要放入Guava版布隆过滤器+redis里面，不然数据就是返回null。误判问题，但是概率小可以接受，缺点就是布隆过滤器不能删除元素
  

![image-20231224203313310](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220417668.png)

#####  8.4.4 布隆过滤器实现白名单,黑名单

白名单过滤器代码实现：建Module，修改`redis7_study`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.atguigu.redis7</groupId>
    <artifactId>redis7_study</artifactId>
    <version>1.0-SNAPSHOT</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.6.10</version>
        <relativePath/>
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <junit.version>4.12</junit.version>
        <log4j.version>1.2.17</log4j.version>
        <lombok.version>1.16.18</lombok.version>
    </properties>

    <dependencies>
        <!--guava Google 开源的 Guava 中自带的布隆过滤器-->
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>23.0</version>
        </dependency>
        <!--SpringBoot通用依赖模块-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--jedis-->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>4.3.1</version>
        </dependency>
        <!--lettuce-->
        <!--<dependency>
            <groupId>io.lettuce</groupId>
            <artifactId>lettuce-core</artifactId>
            <version>6.2.1.RELEASE</version>
        </dependency>-->
        <!--SpringBoot与Redis整合依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>
        <!--swagger2-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>
        <!--Mysql数据库驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>
        <!--SpringBoot集成druid连接池-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.16</version>
        </dependency>
        <!--mybatis和springboot整合-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.0</version>
        </dependency>
        <!--hutool-->
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.2.3</version>
        </dependency>
        <!--persistence-->
        <dependency>
            <groupId>javax.persistence</groupId>
            <artifactId>persistence-api</artifactId>
            <version>1.0.2</version>
        </dependency>
        <!--通用Mapper-->
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper</artifactId>
            <version>4.1.5</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure</artifactId>
        </dependency>
        <!--通用基础配置junit/devtools/test/log4j/lombok/-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${junit.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>${log4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <optional>true</optional>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

配置文件：

```shell
server.port=7777

spring.application.name=redis7_study

# ========================logging=====================
logging.level.root=info
logging.level.com.atguigu.redis7=info
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger- %msg%n 

logging.file.name=D:/mylogs2023/redis7_study.log
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger- %msg%n

# ========================swagger=====================
spring.swagger2.enabled=true
#在springboot2.6.X结合swagger2.9.X会提示documentationPluginsBootstrapper空指针异常，
#原因是在springboot2.6.X中将SpringMVC默认路径匹配策略从AntPathMatcher更改为PathPatternParser，
# 导致出错，解决办法是matching-strategy切换回之前ant_path_matcher
spring.mvc.pathmatch.matching-strategy=ant_path_matcher

# ========================redis单机=====================
spring.redis.database=0
# 修改为自己真实IP
spring.redis.host=192.168.111.185
spring.redis.port=6379
spring.redis.password=111111
spring.redis.lettuce.pool.max-active=8
spring.redis.lettuce.pool.max-wait=-1ms
spring.redis.lettuce.pool.max-idle=8
spring.redis.lettuce.pool.min-idle=0

# ========================alibaba.druid=====================
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/bigdata?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.druid.test-while-idle=false

# ========================mybatis===================
mybatis.mapper-locations=classpath:mapper/*.xml
mybatis.type-aliases-package=com.atguigu.redis7.entities

# ========================redis集群=====================
#spring.redis.password=111111
## 获取失败 最大重定向次数
#spring.redis.cluster.max-redirects=3
#spring.redis.lettuce.pool.max-active=8
#spring.redis.lettuce.pool.max-wait=-1ms
#spring.redis.lettuce.pool.max-idle=8
#spring.redis.lettuce.pool.min-idle=0
##支持集群拓扑动态感应刷新,自适应拓扑刷新是否使用所有可用的更新，默认false关闭
#spring.redis.lettuce.cluster.refresh.adaptive=true
##定时刷新
#spring.redis.lettuce.cluster.refresh.period=2000
#spring.redis.cluster.nodes=192.168.111.185:6381,192.168.111.185:6382,192.168.111.172:6383,192.168.111.172:6384,192.168.111.184:6385,192.168.111.184:6386
```

主启动类：

```java
package com.atguigu.redis7;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
@MapperScan("com.atguigu.redis7.mapper") //import tk.mybatis.spring.annotation.MapperScan;
public class Redis7Study7777
{
    public static void main(String[] args)
    {
        SpringApplication.run(Redis7Study7777.class,args);
    }
}
```

新建测试案例， hello入门：

```java
  @Test
    public void testGuavaWithBloomFilter()
    {
        // 创建布隆过滤器对象
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), 100);
        // 判断指定元素是否存在
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
        // 将元素添加进布隆过滤器
        filter.put(1);
        filter.put(2);
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
    }
```

`GuavaBloomFilterController`：

```java
package com.atguigu.redis7.controller;
import com.atguigu.redis7.service.GuavaBloomFilterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
@Api(tags = "google工具Guava处理布隆过滤器")
@RestController
@Slf4j
public class GuavaBloomFilterController
{
    @Resource
    private GuavaBloomFilterService guavaBloomFilterService;

    @ApiOperation("guava布隆过滤器插入100万样本数据并额外10W测试是否存在")
    @RequestMapping(value = "/guavafilter",method = RequestMethod.GET)
    public void guavaBloomFilter()
    {
        guavaBloomFilterService.guavaBloomFilter();
    }
}
```

`GuavaBloomFilterService`：

```java
package com.atguigu.redis7.service;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class GuavaBloomFilterService{
    public static final int _1W = 10000;
    //布隆过滤器里预计要插入多少数据
    public static int size = 100 * _1W;
    //误判率,它越小误判的个数也就越少(思考，是不是可以设置的无限小，没有误判岂不更好)
    //fpp the desired false positive probability
    public static double fpp = 0.03;
    // 构建布隆过滤器
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size,fpp);
    public void guavaBloomFilter(){
        //1 先往布隆过滤器里面插入100万的样本数据
        for (int i = 1; i <=size; i++) {
            bloomFilter.put(i);
        }
        //故意取10万个不在过滤器里的值，看看有多少个会被认为在过滤器里
        List<Integer> list = new ArrayList<>(10 * _1W);
        for (int i = size+1; i <= size + (10 *_1W); i++) {
            if (bloomFilter.mightContain(i)) {
                log.info("被误判了:{}",i);
                list.add(i);
            }
        }
        log.info("误判的总数量：:{}",list.size());
    }
}
```

测试谷歌Guava布隆过滤器的误判率：

- 取样本100w数据，查询不在100W范围内的其它10w个数据是否存在
- 测试发现有10万数据是不存在的，误判了3033次
- 原始样本：100W 、 不存在数据:1000001W—>1100000W   、计算误判率为0.03033
- 结论：误判率为0.03033，和程序中设置的误判率fpp接近。说明谷歌的Guava库中的布隆过滤器提供了一个**可配置的误判率**，可以自行设定布隆过滤器的误判率，误判率确定以后，hash函数的个数和hash空间都会随之变化

debug源码分析下，看看hash函数。布隆过滤器说明

![image-20231224204435215](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220417224.png)



家庭作业思考题：让布隆过滤器作黑名单使用。抖音防止推荐重复视频、饿了么防止推荐重复优惠券都可以通过布隆过滤器的黑名单实现。推荐过的尽量别在重复推荐，推荐时先去布隆过滤器判断,存在则说明在黑名单里面，已经推荐过不再重复推荐;不存在则说明是新视频，推荐给用户并更新进布隆过滤器，防止下次重复推荐

![image-20231224204541907](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220417133.png)



###  8.6  缓存击穿

#####  8.6.1 简介 | 双检加锁策略

缓存击穿：大量的请求同时查询一个key 时，此时这个key正好失效了，就会导致大量的请求都打到数据库上面去。简单说就是**热点key突然失效，暴打mysql**。注意：穿透和击穿，截然不同

危害：会造成某—时刻数据库请求量过大，压力剧增。一般技术部门需要知道热点key是那些个?做到心里有数防止击穿

缓存击穿：热点key失效。解决方法：互斥更新、随机退避、差异失效时间

热点key失效的情况：

- 过期时间到了自然清除但还被访问到
- delete掉的key，刚巧又被访问

**chatgpt关于缓存击穿的回答**：

- Redis缓存击穿指的是当某个热点数据过期之后，同时有大量并发请求访问该数据，导致这些请求直接落到数据库上，而不经过缓存。这会导致数据库压力急剧增加，可能造成数据库宕机或性能下降
- 对于缓存击穿问题，可以采取以下措施进行缓解：
  - **加锁**：使用互斥锁或分布式锁，确保只有一个线程能够去查询数据库，其他线程需要等待结果，避免并发访问数据库
  - **热点数据预加载**：提前将可能会成为热点的数据加载到缓存中，避免因为并发请求导致缓存失效时引起的击穿问题
  - **设置短期随机过期时间**：设置短期的随机过期时间避免缓存同时失效，分散请求落到数据库上，控制瞬时并发请求
  - **限流**：对并发请求进行限流，以减轻数据库压力
  - **使用哨兵模式或集群模式**：通过Redis的哨兵模式或集群模式，提高Redis的可用性和容错能力，避免单点故障造成的缓存失效问题

防止缓存击穿的方案：

- 差异失效时间，对于访问频繁的热点key，干脆就不设置过期时间

- 互斥更新，采用**双检加锁策略**：多个线程同时去查询数据库的这条数据，那么可以在第一个查询数据的请求上使用一个互斥锁来锁住它

  ，其他的线程走到这一步拿不到锁就等着，等第一个线程查询到了数据，然后做缓存。后面的线程进来发现已经有缓存了，就直接走缓存



![image-20231224205454119](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220417648.png)

#####  8.6.2 缓存击穿案例

缓存击穿案例：

- 模拟高并发的天猫聚划算商品首页功能。实现天猫聚划算功能+防止缓存击穿。高并发+定时任务+分页显示
- 潜在问题：高并发时热点key突然失效导致了缓存击穿
- 技术方案分析：

```
1.100%高并发，绝对不可以用mysql实现
2.先把mysql里面参加活动的数据抽取进redis，一般采用定时器扫描来决定上线活动还是下线取消
3.支持分页功能，一页20条记录
```

 redis数据类型选型：由于要实现分页功能，所以此处采用redsi的list数据类型

```shell
# 采用redsi的list数据类型，实现分页功能
127.0.0.1:6379>LPUSH jhs1 p1 p2 p3 p4 p5 p6 p7 p8 p9 p10
(integer)10
127.0.0.1:6379> LRANGE jhs1 0 5
1)"p10"
2)"p9"
3)"p8
4)"p7"
5)"p6"
6)"p5"
127.0.0.1:6379> LRANGE jhs1 6 10
1)"p4"
2)"p3"
3)"p2"
4)"p1"
```

补充说明：

- list和zset都可以进行分页，但是当前的需求场景使用list会更好一些

- list实现分页：`LRANGE key start stop`    返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定
- zset实现分页：`ZRANGE key start stop [WITHSCORES]`    通过索引区间返回有序集合指定区间内的成员
- list类型和zset类型的使用场景比较：

| 类型            | 使用场景                                                     |
| --------------- | ------------------------------------------------------------ |
| List (列表)     | 1. 实时消息队列：存储消息并支持先入先出（FIFO）的处理        |
|                 | 2. 最新消息列表：可用于存储最新的消息或通知                  |
|                 | 3. 任务队列：适合用于处理异步任务，比如将需要延迟处理的任务存储在列表中 |
|                 | 4. 消息发布与订阅：可以将消息发布到列表中，然后其他服务或应用程序可以订阅并处理这些消息 |
| Zset (有序集合) | 1. 排行榜：适用于存储和获取排名数据，比如用户得分排行榜、文章点击排行榜等 |
|                 | 2. 范围查找：存储且需要按照某些条件排序的数据，比如存储会员的积分，可以根据分数范围来查找会员 |
|                 | 3. 唯一性数据集合：适用于一些需要保证唯一性的数据，并且需要按照一定规则排序的场景，比如学生考试成绩 |
|                 | 4. 时间序列数据：存储时间序列数据，并支持按照时间顺序进行范围查询，比如存储传感器数据、股票价格等 |

springboot+redis实现高并发的聚划算业务V2：

建Module_修改`redis7_study`：

实体类：

```java
package com.atguigu.redis7.entities;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "聚划算活动producet信息")
public class Product
{
    //产品ID
    private Long id;
    //产品名称
    private String name;
    //产品价格
    private Integer price;
    //产品详情
    private String detail;
}
```

`JHSTaskService`：采用定时器将参与聚划算活动的特价商品新增进入redis中

```java
package com.atguigu.redis7.service;

import cn.hutool.core.date.DateUtil;
import com.atguigu.redis7.entities.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
@Service
@Slf4j
public class JHSTaskService
{
    public  static final String JHS_KEY="jhs";
    public  static final String JHS_KEY_A="jhs:a";
    public  static final String JHS_KEY_B="jhs:b";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 偷个懒不加mybatis了，模拟从数据库读取100件特价商品，用于加载到聚划算的页面中
     * @return
     */
    private List<Product> getProductsFromMysql() {
        List<Product> list=new ArrayList<>();
        for (int i = 1; i <=20; i++) {
            Random rand = new Random();
            int id= rand.nextInt(10000);
            Product obj=new Product((long) id,"product"+i,i,"detail");
            list.add(obj);
        }
        return list;
    }

    @PostConstruct
    public void initJHS(){
        log.info("启动定时器淘宝聚划算功能模拟.........."+ DateUtil.now());
        new Thread(() -> {
            //模拟定时器一个后台任务，定时把数据库的特价商品，刷新到redis中
            while (true){
                //模拟从数据库读取100件特价商品，用于加载到聚划算的页面中
                List<Product> list=this.getProductsFromMysql();
                //采用redis list数据结构的lpush来实现存储
                this.redisTemplate.delete(JHS_KEY);
                //lpush命令
                this.redisTemplate.opsForList().leftPushAll(JHS_KEY,list);
                //间隔一分钟 执行一遍，模拟聚划算每3天刷新一批次参加活动
                try { TimeUnit.MINUTES.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

                log.info("runJhs定时刷新..............");
            }
        },"t1").start();
    }
}
```

`JHSProductController`：

```java
package com.atguigu.redis7.controller;
import com.atguigu.redis7.entities.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@Slf4j
@Api(tags = "聚划算商品列表接口")
public class JHSProductController
{
    public  static final String JHS_KEY="jhs";
    public  static final String JHS_KEY_A="jhs:a";
    public  static final String JHS_KEY_B="jhs:b";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 分页查询：在高并发的情况下，只能走redis查询，走db的话必定会把db打垮
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/pruduct/find",method = RequestMethod.GET)
    @ApiOperation("按照分页和每页显示容量，点击查看")
    public List<Product> find(int page, int size) {
        List<Product> list=null;

        long start = (page - 1) * size;
        long end = start + size - 1;

        try {
            //采用redis list数据结构的lrange命令实现分页查询
            list = this.redisTemplate.opsForList().range(JHS_KEY, start, end);
            if (CollectionUtils.isEmpty(list)) {
                //TODO 走DB查询
            }
            log.info("查询结果：{}", list);
        } catch (Exception ex) {
            //这里的异常，一般是redis瘫痪 ，或 redis网络timeout
            log.error("exception:", ex);
            //TODO 走DB查询
        }

        return list;
    }
}
```

至此步骤，上述聚划算的功能算是完成，请思考在高并发下有什么经典生产问题?

Bug和隐患说明：热点key突然失效导致可怕的缓存击穿。delete命令执行的—瞬间有空隙，其它请求线程继续找Redis为null打到了mysql

![image-20231224210844694](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220417863.png)



最终目的：2条命令原子性还是其次，主要是防止热key突然失效暴击mysql打爆系统

**解决缓存击穿方案一**：采用双检加锁策略。多个线程同时去查询数据库的这条数据，那么我们可以在第一个查询数据的请求上使用一个 互斥锁来锁住它。其他的线程走到这一步拿不到锁就等着，等第一个线程查询到了数据，然后做缓存。后面的线程进来发现已经有缓存了，就直接走缓存

![image-20231224211505794](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220417849.png)

**解决缓存击穿方案二**：分级缓存，使用AB双缓存架构来保证失效时间存在差异。开辟两块缓存，主A从B，先更新B再更新A，严格照这个顺序，并且保证A、B的过期时间不同。查询的时候先查询主缓存A，如果A没有(消失或者失效了)再查询从缓存B。聚划算中旧的商品还没有完全下架，新的商品还没有完全上架，在新旧数据交替的时候，用户还会频繁查询旧数据，这时候保证主从缓存的失效时间存在差异，就会更好地防止缓存击穿。AB双缓存主要是避免缓存同时失效，另外这种方案可能会造成额外的缓存空间浪费

![image-20231224211719404](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220417447.png)



AB双缓存实现聚划算商品加载预热，保证过期时间存在差异，从而防止redis缓存击穿。核心代码：

![image-20240101021014189](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220417101.png)

`JHSTaskService`：

```java
package com.atguigu.redis7.service;
import cn.hutool.core.date.DateUtil;
import com.atguigu.redis7.entities.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
@Service
@Slf4j
public class JHSTaskService
{
    public  static final String JHS_KEY="jhs";
    public  static final String JHS_KEY_A="jhs:a";
    public  static final String JHS_KEY_B="jhs:b";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 偷个懒不加mybatis了，模拟从数据库读取100件特价商品，用于加载到聚划算的页面中
     * @return
     */
    private List<Product> getProductsFromMysql() {
        List<Product> list=new ArrayList<>();
        for (int i = 1; i <=20; i++) {
            Random rand = new Random();
            int id= rand.nextInt(10000);
            Product obj=new Product((long) id,"product"+i,i,"detail");
            list.add(obj);
        }
        return list;
    }

    //@PostConstruct
    public void initJHS(){
        log.info("启动定时器淘宝聚划算功能模拟.........."+ DateUtil.now());
        new Thread(() -> {
            //模拟定时器，定时把数据库的特价商品，刷新到redis中
            while (true){
                //模拟从数据库读取100件特价商品，用于加载到聚划算的页面中
                List<Product> list=this.getProductsFromMysql();
                //采用redis list数据结构的lpush来实现存储
                this.redisTemplate.delete(JHS_KEY);
                //lpush命令
                this.redisTemplate.opsForList().leftPushAll(JHS_KEY,list);
                //间隔一分钟 执行一遍
                try { TimeUnit.MINUTES.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

                log.info("runJhs定时刷新..............");
            }
        },"t1").start();
    }

    @PostConstruct
    public void initJHSAB(){
        log.info("启动AB定时器计划任务淘宝聚划算功能模拟.........."+DateUtil.now());
        new Thread(() -> {
            //模拟定时器，定时把数据库的特价商品，刷新到redis中
            while (true){
                //模拟从数据库读取100件特价商品，用于加载到聚划算的页面中
                List<Product> list=this.getProductsFromMysql();
                //先更新B缓存
                this.redisTemplate.delete(JHS_KEY_B);
                this.redisTemplate.opsForList().leftPushAll(JHS_KEY_B,list);
                this.redisTemplate.expire(JHS_KEY_B,20L,TimeUnit.DAYS);
                //再更新A缓存
                this.redisTemplate.delete(JHS_KEY_A);
                this.redisTemplate.opsForList().leftPushAll(JHS_KEY_A,list);
                this.redisTemplate.expire(JHS_KEY_A,15L,TimeUnit.DAYS);
                //间隔一分钟 执行一遍
                try { TimeUnit.MINUTES.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

                log.info("runJhs定时刷新双缓存AB两层..............");
            }
        },"t1").start();
    }
}
```

`JHSProductController`：

```java
package com.atguigu.redis7.controller;
import com.atguigu.redis7.entities.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@Slf4j
@Api(tags = "聚划算商品列表接口")
public class JHSProductController
{
    public  static final String JHS_KEY="jhs";
    public  static final String JHS_KEY_A="jhs:a";
    public  static final String JHS_KEY_B="jhs:b";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 分页查询：在高并发的情况下，只能走redis查询，走db的话必定会把db打垮
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/pruduct/find",method = RequestMethod.GET)
    @ApiOperation("按照分页和每页显示容量，点击查看")
    public List<Product> find(int page, int size) {
        List<Product> list=null;

        long start = (page - 1) * size;
        long end = start + size - 1;

        try {
            //采用redis list数据结构的lrange命令实现分页查询
            list = this.redisTemplate.opsForList().range(JHS_KEY, start, end);
            if (CollectionUtils.isEmpty(list)) {
                //TODO 走DB查询
            }
            log.info("查询结果：{}", list);
        } catch (Exception ex) {
            //这里的异常，一般是redis瘫痪 ，或 redis网络timeout
            log.error("exception:", ex);
            //TODO 走DB查询
        }

        return list;
    }

    @RequestMapping(value = "/pruduct/findab",method = RequestMethod.GET)
    @ApiOperation("防止热点key突然失效，AB双缓存架构")
    public List<Product> findAB(int page, int size) {
        List<Product> list=null;
        long start = (page - 1) * size;
        long end = start + size - 1;
        try {
            //采用redis list数据结构的lrange命令实现分页查询
            list = this.redisTemplate.opsForList().range(JHS_KEY_A, start, end);
            if (CollectionUtils.isEmpty(list)) {
                log.info("=========A缓存已经失效了，记得人工修补，B缓存自动延续5天");
                //用户先查询缓存A(上面的代码)，如果缓存A查询不到（例如，更新缓存的时候删除了），再查询缓存B
                this.redisTemplate.opsForList().range(JHS_KEY_B, start, end);
                //TODO 走DB查询
            }
            log.info("查询结果：{}", list);
        } catch (Exception ex) {
            //这里的异常，一般是redis瘫痪 ，或 redis网络timeout
            log.error("exception:", ex);
            //TODO 走DB查询
        }
        return list;
    }
}
```



#####  8.6.3  缓存击穿和缓存穿透对比

缓存穿透和缓存击穿比较：

![image-20231225001647853](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220417843.png)



Redis缓存击穿和缓存穿透是两种与缓存相关的常见问题，它们有着不同的特点和解决方法：

**缓存击穿**：

- **定义**：在缓存击穿中，是指针对某一热点数据，当该数据突然失效或被大量并发请求访问时，未命中缓存直接访问数据库，导致对数据库的压力剧增
- **原因**：通常是由于并发请求导致热点数据失效，而此时大量请求同时落到数据库上，引起数据库压力突增
- **解决方法**：缓存击穿通常可以通过加锁、热点数据预加载、设置短期随机过期时间、限流以及使用哨兵模式或集群模式等方法来解决

**缓存穿透**：

- **定义**：缓存穿透是指针对数据库中不存在的数据，请求经过缓存直接访问数据库，无法命中缓存，这可能是由于恶意攻击、非法输入或者错误的业务逻辑导致的
- **原因**：通常是由于查询条件不合法或者恶意攻击，导致请求无法在缓存中命中而直接访问数据库
- **解决方法**：缓存穿透问题通常可以通过合理使用布隆过滤器、空对象缓存、对请求参数进行校验等方法来解决

总的来说，缓存击穿是指对热点数据的并发访问导致缓存失效，而缓存穿透则是指请求无法在缓存中命中导致直接访问数据库。针对这两种问题，需要针对性地采取不同的解决方法来保障系统的稳定性和性能

###  8.6 缓存问题总结

| 缓存问题     | 产生原因               | 解决方案                               |
| ------------ | ---------------------- | -------------------------------------- |
| 缓存更新方式 | 数据变更、缓存时效性   | 同步更新、失效更新、异步更新、定时更新 |
| 缓存不一致   | 同步更新失败、异步更新 | 增加重试、补偿任务、最终一致           |
| 缓存穿透     | 恶意攻击               | 空对象缓存、bloomfilter过滤器          |
| 缓存击穿     | 热点key失效            | 互斥更新、随机退避、差异失效时间       |
| 缓存雪崩     | 缓存挂掉               | 快速失败熔断、主从模式、集群模式       |



##  9.redis 分布式锁

###  9.1 分布式锁简介 | 分布式锁命令

**redis分布式锁官方文档**：`https://redis.io/docs/reference/patterns/distributed-locks/`

**锁的种类**：单体架构中synchronized或者Lock锁只会作用在同一个JVM虚拟机内。微服务架构下，多个服务存在多个不同VM虚拟机，单机的线程锁机制不再起作用，资源类在不同的服务器之间共享

![image-20231225235515753](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220418014.png)

**分布式锁需要具备的条件**：

- 独占性：OnlyOne，任何时刻只能有且仅有一个线程持有
- 高可用：在redis集群环境下，不能因为某一个节点挂了而出现获取锁和释放锁失败的情况。高并发请求下，依旧性能OK好使
- 杜绝死锁：必须有超时控制机制或者撤销操作，有个兜底终止跳出方案
- 不乱抢：防止张冠李戴，不能私下unlock别人的锁，只能自己加锁自己释放，自己的锁要自己解
- 重入性：同一个节点的同一个线程如果获得锁之后，它也可以再次获取这个锁

**面试题**：

- Redis做分布式锁的时候有什么需要注意的问题？
- 你们公司自己实现的分布式锁是否用的setnx命令实现？这个是最合适的吗? 如何考虑分布式锁的可重入问题？
- 如果Redis是单点部署的，会带来什么问题？怎么解决单点问题呢?
- Redis集群模式下，比如主从模式，CAP方面有没有什么问题呢？
- 介绍一下Redlock 和 redisson
- Redis分布式锁如何续期？看门狗知道吗？

**Redis应用场景总结**：

- 数据共享，分布式Session分布式锁
- 全局ID
- 计算器、点赞位统计
- 购物车
- 轻量级消息队列：list、stream
- 抽奖
- 点赞、签到、打卡
- 差集交集并集，用户关注、可能认识的人，推荐模型热点新闻、热搜排行榜

**redis分布式锁两个核心命令**：

- `setnx key value`： 如果键 key 已经存在并且值是一个字符串，返回 0。 如果键 key 不存在，则把 key 设为 value，并返回 1。`SETNX` 命令本身并不支持直接指定过期时间。`SETNX` 的作用是只在键不存在时设置键的值，因此并不包含设置过期时间的选项。如果需要在设置键的同时指定过期时间，可以考虑将 `SETNX` 和 `EXPIRE` 命令结合使用。首先使用 `SETNX` 设置键的值，然后使用 `EXPIRE` 来为这个键设置过期时间

- `set key value [EX seconds] [PX milliseconds] [NX|XX]`
  - EX：key在多少秒之后过期
  
  - PX：key在多少毫秒之后过期
  
  - NX：当key不存在的时候，才创建key，效果等同于setnx
  
  - XX：当key存在的时候，覆盖key
  
- 命令使用案例：

```shell
# 注意: setnx命令不能加过期时间，一般不推荐使用
# setnx+expire不安全，两条命令非原子性的
# setnx key value 使用案例：
127.0.0.1:6379> setnx k1 true
1
127.0.O.1:6379> setnx k1 true
0
127.0.0.1:6379> EXPIRE k1 60
1

# set key value [EX seconds] [PX milliseconds][NX|XX] 使用案例：
127.0.0.1:6379> set lock pay ex 10 NX
OK
127.0.0.1:6379> get lock
"pay"
127.0.0.1:6379> set lock order ex 10NX
OK
127.0.0.1:6379> set lock pay ex 10 NX
(nil)
127.0.0.1:6379>get lock
(nil)
```

###  9.2 锁的单机实现

使用synchronized或者Lock进行实现扣减库存的逻辑。建立两个Module：`redis_distributed_lock2`  、 `redis_distributed_lock3`，在`pom.xml`中引入依赖：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.atguigu.redislock</groupId>
    <artifactId>redis_distributed_lock2</artifactId>
    <version>1.0-SNAPSHOT</version>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.6.12</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <lombok.version>1.16.18</lombok.version>
    </properties>
    <dependencies>
        <!--SpringBoot通用依赖模块-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--SpringBoot与Redis整合依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>
        <!--swagger2-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>
        <!--通用基础配置boottest/lombok/hutool-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.8.8</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>
```

配置文件中添加如下配置：

```properties
server.port=7777
spring.application.name=redis_distributed_lock
# ========================swagger2=====================
# http://localhost:7777/swagger-ui.html
swagger2.enabled=true
spring.mvc.pathmatch.matching-strategy=ant_path_matcher
# ========================redis单机=====================
spring.redis.database=0
spring.redis.host=192.168.111.185
spring.redis.port=6379
spring.redis.password=111111
spring.redis.lettuce.pool.max-active=8
spring.redis.lettuce.pool.max-wait=-1ms
spring.redis.lettuce.pool.max-idle=8
spring.redis.lettuce.pool.min-idle=0
```

主启动类：

```java
package com.atguigu.redislock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class RedisDistributedLockApp7777
{
    public static void main(String[] args)
    {
        SpringApplication.run(RedisDistributedLockApp7777.class,args);
    }
}
```

`Swagger2Config` ：Swagger配置类

```java
package com.atguigu.redislock.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
@Configuration
@EnableSwagger2
public class Swagger2Config
{
    @Value("${swagger2.enabled}")
    private Boolean enabled;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.atguigu.redislock")) //你自己的package
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot利用swagger2构建api接口文档 "+"\t"+ DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now()))
                .description("springboot+redis整合")
                .version("1.0")
                .termsOfServiceUrl("https://www.baidu.com/")
                .build();
    }

}
```

redis配置类：RedisConfig

```java
package com.atguigu.redislock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
@Configuration
public class RedisConfig
{
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory)
    {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        //设置key序列化方式string
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化方式json
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
```

lnventoryService：扣减库存的逻辑（使用synchronized或者Lock进行实现，没有使用分布式锁）

```java
package com.atguigu.redislock.service;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@Service
@Slf4j
public class InventoryService
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String port;

    private Lock lock = new ReentrantLock();

    public String sale()
    {
        String retMessage = "";
        lock.lock();
        try
        {
            //1 查询库存信息
            String result = stringRedisTemplate.opsForValue().get("inventory001");
            //2 判断库存是否足够
            Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
            //3 扣减库存
            if(inventoryNumber > 0) {
                stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber;
                System.out.println(retMessage);
            }else{
                retMessage = "商品卖完了，o(╥﹏╥)o";
            }
        }finally {
            lock.unlock();
        }
        return retMessage+"\t"+"服务端口号："+port;
    }
}
```

lnventoryController：

```java
package com.atguigu.redislock.controller;
import cn.hutool.core.util.IdUtil;
import com.atguigu.redislock.service.InventoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicInteger;
@RestController
@Api(tags = "redis分布式锁测试")
public class InventoryController
{
    @Autowired
    private InventoryService inventoryService;

    @ApiOperation("扣减库存，一次卖一个")
    @GetMapping(value = "/inventory/sale")
    public String sale()
    {
        return inventoryService.sale();
    }
}
```

测试：swagger访问`http://localhost:7777/swagger-ui.html#/`   

结论：单机应用时使用synchronized或Lock也可以保证锁的正确性，只要正确加锁和解锁并保证代码逻辑，就不会出现超卖等并发问题

###    9.3 nginx分布式微服务架构

利用nginx实现反向代理+负载均衡，将上面的单体程序进行分布式部署，改成分布式应用。一边一个，默认轮询

![image-20231225014733249](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220418877.png)

Nginx路径\启动方式：

```
nginx命令路径：/usr/local/nginx/sbin
nginx配置路径：/usr/local/nginx/conf
nginx启动：   /usr/local/nginx/sbin    ./nginx  (成功启动Nginx浏览器可看到nginx欢迎welcome页面)
指定配置启动： /usr/local/nginx/sbin路径下执行下面的命令   ./nginx -c /usr/local/nginx/conf/nginx.conf
nginx关闭:   /usr/local/nginx/sbin      ./nginx -s stop
nginx重启:         /usr/local/nginx/sbin         ./nginx -s reload
```

Nginx负载均衡配置：`/usr/local/nginx/conf`目录下修改配置文件`nginx.conf`新增反向代理和负载均衡配置

![image-20231225015140528](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220418212.png)



启动两个微服务：

`InventoryService`

```java
package com.atguigu.redislock.service;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@Service
@Slf4j
public class InventoryService
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String port;

    private Lock lock = new ReentrantLock();

    public String sale()
    {
        String retMessage = "";
        lock.lock();
        try
        {
            //1 查询库存信息
            String result = stringRedisTemplate.opsForValue().get("inventory001");
            //2 判断库存是否足够
            Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
            //3 扣减库存
            if(inventoryNumber > 0) {
                stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber;
                System.out.println(retMessage);
            }else{
                retMessage = "商品卖完了，o(╥﹏╥)o";
            }
        }finally {
            lock.unlock();
        }
        return retMessage+"\t"+"服务端口号："+port;
    }
}
```

**测试**：

- 通过Nginx访问Linux服务器地址IP，`http://192.168.111.185/inventory/sale`  (`192.168.111.185` 是安装Linux的服务器地址)
- 利用Jmeter模拟高并发http请求，76号商品被卖出2次，出现超卖故障现象，为什么加了synchronized或者Lock还是没有控制住？

**java锁失效，需要使用分布式锁**：在单机环境下，可以使用synchronized或Lock来实现，但是在分布式系统中，因为竞争的线程可能不在同一个节点上（同一个jvm中），所以需要一个让所有进程都能访问到的锁来实现(比如redis或者zookeeper来构建)，不同进程jvm层面的锁就不管用了，那么可以利用第三方的一个组件，来获取锁，未获取到锁，则阻塞当前想要运行的线程。分布式场景下，多个服务间要保证同一时刻同一时间段内同一用户只能有一个请求(防止关键业务出现并发攻击)，这时候就需要用到分布式锁

###  9.4 分布式锁一：set nx | 递归重试

上一节的代码分布式部署后，单机锁还是出现超卖现象。要保证锁在分布式场景下的可见性，就需要改用分布式锁。下面利用redis的set nx命令实现分布式锁，并通过递归重试的方式不断地尝试获取锁

```shell
# redis分布式锁官网：https://redis.io/commands/set
# 借助Redis的SET命令即可实现加锁处理
SET命令参数：
   EX seconds：        Set the specified expire time, in seconds.
   PX milliseconds：   Set the specified expire time, in milliseconds
   NX：                Only set the key if it does not already exist
   XX：                Only set the key if it already exist
```

`InventoryService`：

```java
package com.atguigu.redislock.service;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@Service
@Slf4j
public class InventoryService
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String port;

    private Lock lock = new ReentrantLock();

    public String sale()
    {
        String retMessage = "";
        String key = "zzyyRedisLock";
        String uuidValue = IdUtil.simpleUUID()+":"+Thread.currentThread().getId();

        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, uuidValue);
        if(!flag){
            //暂停20毫秒后递归调用
            try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
            sale();
        }else{
            try{
                //1 查询库存信息
                String result = stringRedisTemplate.opsForValue().get("inventory001");
                //2 判断库存是否足够
                Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
                //3 扣减库存
                if(inventoryNumber > 0) {
                    stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                    retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber;
                    System.out.println(retMessage);
                }else{
                    retMessage = "商品卖完了，o(╥﹏╥)o";
                }
            }finally {
                stringRedisTemplate.delete(key);
            }
        }
        return retMessage+"\t"+"服务端口号："+port;
    }
}
```

测试：将库存设为5000，进行Jmeter压测，发现程序正确执行，且不会出现超卖现象

```shell
# 将库存设为5000
127.0.0.1:6379> set inventory001 5000
OK
127.0.0.1:6379> get inventory001
"5000"
```

当前程序不足之处：递归是一种思想没错，但是容易导致StackOverflowError，不太推荐。另外，高并发唤醒后推荐用while判断而不是if

### 9.5 分布式锁二：自旋替代递归重试

优化：用自旋替代递归重试调用，高并发唤醒后推荐用while判断而不是if

```java
package com.atguigu.redislock.service;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@Service
@Slf4j
public class InventoryService
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String port;

    private Lock lock = new ReentrantLock();

    public String sale()
    {
        String retMessage = "";
        String key = "zzyyRedisLock";
        String uuidValue = IdUtil.simpleUUID()+":"+Thread.currentThread().getId();
        while(!stringRedisTemplate.opsForValue().setIfAbsent(key, uuidValue)){
            //暂停20毫秒，类似CAS自旋
            try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        try
        {
            //1 查询库存信息
            String result = stringRedisTemplate.opsForValue().get("inventory001");
            //2 判断库存是否足够
            Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
            //3 扣减库存
            if(inventoryNumber > 0) {
                stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber;
                System.out.println(retMessage);
            }else{
                retMessage = "商品卖完了，o(╥﹏╥)o";
            }
        }finally {
            stringRedisTemplate.delete(key);
        }
        return retMessage+"\t"+"服务端口号："+port;
    }
}
```

**当前程序不足之处**：假如部署了微服务的Java程序机器挂了，代码层面根本没有走到finally这块，没办法保证解锁(无过期时间该key一直存在)，这个key没有被删除的话容易导致死锁的出现。最好给分布式锁key设置一个过期时间

###   9.6 redis分布式锁三：添加过期时间

优化：假如部署了微服务的Java程序机器挂了，代码层面根本没有走到finally这块，没办法保证解锁(无过期时间该key一直存在)，这个key没有被删除的话容易导致死锁的出现。最好给分布式锁key设置一个过期时间，防止死锁

```java
while(!stringRedisTemplate.opsForValue().setIfAbsent(key, uuidValue))
{
    //暂停20毫秒，进行递归重试.....
    try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
}

stringRedisTemplate.expire(key,30L,TimeUnit.SECONDS);
```

问题：设置key+过期时间分开了，必须要合并成—行保证原子性

###  9.7 redis分布式锁四：保证加锁和过期时间设置的原子性

优化：保证`设置key+设置key的过期时间`是一个原子操作

```java
package com.atguigu.redislock.service;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@Service
@Slf4j
public class InventoryService
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String port;

    private Lock lock = new ReentrantLock();

    public String sale()
    {
        String retMessage = "";
        String key = "zzyyRedisLock";
        String uuidValue = IdUtil.simpleUUID()+":"+Thread.currentThread().getId();

        while(!stringRedisTemplate.opsForValue().setIfAbsent(key, uuidValue,30L,TimeUnit.SECONDS))
        {
            //暂停毫秒
            try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        try
        {
            //1 查询库存信息
            String result = stringRedisTemplate.opsForValue().get("inventory001");
            //2 判断库存是否足够
            Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
            //3 扣减库存
            if(inventoryNumber > 0) {
                stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber;
                System.out.println(retMessage);
            }else{
                retMessage = "商品卖完了，o(╥﹏╥)o";
            }
        }finally {
            stringRedisTemplate.delete(key);
        }
        return retMessage+"\t"+"服务端口号："+port;
    }
}
```

**当前程序存在的不足**：特殊情况下会导致锁的误删。存在潜在的特殊情况，A线程抢到锁以后设置了分布式锁key对应的value，同时设置过期时间为30秒，假如A处理业务的时间超过了30秒，分布式锁过期后被删除。这个时候B线程抢到锁，同时设置了锁对应的值和过期时间，随后B线程进行业务逻辑的处理，在B还没有处理完业务逻辑时，A执行完业务逻辑之后去删除锁，此时删除的锁是B线程加的锁。这样就出现了A线程误删B线程设置的锁的特殊情况



![image-20231225022002518](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220418854.png)

###  9.8 redis分布式锁五：防止锁的误删

上一节程序不足：实际业务处理时间如果超过了默认设置key的过期时间，可能会导致线程A误删线程B的锁

优化：每个线程只能自己删除自己添加的锁，不能删除其他线程加的锁。所以每个线程在加锁时要为这个锁添加`UUID+线程id`作为标记，要删除的时候先判断是不是当前线程加的锁，是的话才能删除

```java
package com.atguigu.redislock.service;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@Service
@Slf4j
public class InventoryService
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String port;

    private Lock lock = new ReentrantLock();

    public String sale()
    {
        String retMessage = "";
        String key = "zzyyRedisLock";
        String uuidValue = IdUtil.simpleUUID()+":"+Thread.currentThread().getId();

        while(!stringRedisTemplate.opsForValue().setIfAbsent(key, uuidValue,30L,TimeUnit.SECONDS))
        {
            //暂停毫秒
            try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        try
        {
            //1 查询库存信息
            String result = stringRedisTemplate.opsForValue().get("inventory001");
            //2 判断库存是否足够
            Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
            //3 扣减库存
            if(inventoryNumber > 0) {
                stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber+"\t"+uuidValue;
                System.out.println(retMessage);
            }else{
                retMessage = "商品卖完了，o(╥﹏╥)o";
            }
        }finally {
            // v5.0判断加锁与解锁是不是同一个客户端，同一个才行，自己只能删除自己的锁，不误删他人的
            if(stringRedisTemplate.opsForValue().get(key).equalsIgnoreCase(uuidValue)){
                stringRedisTemplate.delete(key);
            }
        }
        return retMessage+"\t"+"服务端口号："+port;
    }
}
```

**当前程序不足之处**：finally块中`当前线程是否存在锁的判断+del删除操作`不是原子性的，假如在`判断`和`删除`的间隙出现了异常，就会导致删锁失败

优化：启用lua脚本编写redis分布式锁判断+删除判断代码

###  9.9  redis分布式锁六：Lua保证解锁原子性

本节优化：使用lua脚本编写`判断`+`删除`的逻辑，保证finally块中`当前线程是否存在锁的判断+del删除操作`是原子性的

#####  9.9.1 Lua脚本入门

lua脚本：Lua是巴西里约热内卢天主教大学(Pontifical Ccatholic University of Rio de Janeiro)里的一个研究小组于1993年开发的，该小组成员有:Roberto lerusalimschy、waldemar Celes和Luiz Henrique de Figueiredo。Lua是一种轻量小巧的脚本语言，用标准C语言编写并以源代码形式开放，其设计目的是为了嵌入应用程序中，从而为应用程序提供灵活的扩展和定制功能。Lua脚本的设计目的是为了嵌入到应用程序中，从而为应用程序提供灵活的扩展和定制功能

redis分布式锁官方文档：`https://redis.io/docs/manual/patterns/distributed-locks/`

Lua是一种轻量级的、高效的、可嵌入的脚本语言，具有以下特性：

1. **轻量级：** Lua设计简洁，核心库相对精简，使其成为嵌入其他应用程序的理想选择
2. **可移植性：** Lua在各种平台上都有高度的可移植性，因此非常适合用于跨平台的开发
3. **动态类型：** Lua是一种动态类型语言，不需要在编写代码时指定变量的类型
4. **垃圾回收：** Lua拥有自动垃圾回收，可以管理内存，使程序员无需担心手动分配和释放内存
5. **表驱动：** Lua将表（类似于字典或关联数组）作为主要的数据结构，提供了丰富而灵活的用法
6. **函数式编程风格：** Lua支持匿名函数和闭包，使其能够以函数式编程的风格来编写代码
7. **自由扩展：** 可以通过C语言扩展Lua的功能，这使得Lua能够与其他语言和库进行轻松整合

总的来说，Lua以其简洁、灵活和高效而闻名，适合于游戏开发、嵌入式系统、脚本编程等领域



**redis的eval命令**：**Redis通过eval命令调用Lua脚本**保证代码执行的原子性，直接用return返回脚本执行后的结果值

eval命令语法：`eval luascript numkeys [key [key ...]] [arg [arg ...]`

`redis` 的 `EVAL` 命令用于在服务器端执行 Lua 脚本。其语法如下：

```plaintext
EVAL script numkeys key [key ...] arg [arg ...]
```

- `script`: 要执行的 Lua 脚本
- `numkeys`: 传递给 Lua 脚本的键的数量
- `key [key ...]`: Lua 脚本中所需的键
- `arg [arg ...]`: 传递给 Lua 脚本的参数

```shell
# Redis调用Lua脚本
127.0.0.1:6379> EVAL "return 'hello lua'" 0
"hello lua"
127.0.0.1:6379> set k1 v1
0K
127.0.0.1:6379> get k1
"v1"
# Redis调用Lua脚本实现set、get操作
127.0.0.1:6379> EVAL "redis.call('set','k1','v1') return redis.call('get','k1')" 0
"v1"
127.0.0.1:6379> get k1
"v1"

# Redis调用Lua脚本实现mset操作
127.0.0.1:6379> EVAL "return redis.call('mset',KEYS[1],ARGV[1],KEYS[2]，ARGV[ 2])" 2 k1 k2 11 12
OK
127.0.0.1:6379> get k1
"11"
127.0.0.1:6379> get k2
"12"
```

lua条件判断：

```lua
# 条件判断语法
if(布尔条件) then
业务代码
elseif(布尔条件) then
业务代码
elseif(布尔条件) then
业务代码
else
业务代码
end
```

条件判断案例：

![image-20231225025441078](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220418035.png)

EVAL命令使用案例：

```shell
# 简单示例
# 返回字符串
EVAL "return 'Hello World'" 0

# 在 Lua 脚本中使用传入的键和参数
# 如何获取传入键的值
EVAL "return redis.call('GET', KEYS[1])" 1 "mykey"

# 传递多个键和参数
# 如何使用多个键和参数进行计算
EVAL "local sum = 0 for i, key in ipairs(KEYS) do sum = sum + tonumber(redis.call('GET', key)) end return sum" 3 "key1" "key2" "key3"
```

lua脚本保证`判断`+`删除`的原子性：

```shell
# Redis分布式锁
127.0.0.1:6379> set zzvvRedisLock 1111-2222-3333
OK
127.0.0.1:6379> get zzyyRedisLock
"1111-2222-3333"
127.0.0.1:6379> eval "if redis.call('get, KEYS[1]== ARGV[1] then return redis.call('del' ,KEYS[1]) else return 0 end" 1 zzyiedisLock 111-2222-3333
(integer) 1
127.0.0.1:6379> eval "if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('del' , KEYS[1]) else return 0 end" 1 zzyyRedisLock 1 
111-2222-3333
```

##### 9.9.3 lua脚本保证原子性

lua脚本保证`判断`+`删除`的原子性：

```lua
if redis.call('get, KEYS[1]== ARGV[1] then return redis.call('del' ,KEYS[1]) else return 0 end
```

优化：使用lua脚本保证`判断`+`删除`的原子性

`InventoryService`：

```java
package com.atguigu.redislock.service;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@Service
@Slf4j
public class InventoryService
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String port;

    private Lock lock = new ReentrantLock();

    public String sale()
    {
        String retMessage = "";
        String key = "zzyyRedisLock";
        String uuidValue = IdUtil.simpleUUID()+":"+Thread.currentThread().getId();

        while(!stringRedisTemplate.opsForValue().setIfAbsent(key, uuidValue,30L,TimeUnit.SECONDS))
        {
            //暂停毫秒
                     try { TimeUnit.MILLISECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        try
        {
            //1 查询库存信息
            String result = stringRedisTemplate.opsForValue().get("inventory001");
            //2 判断库存是否足够
            Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
            //3 扣减库存
            if(inventoryNumber > 0) {
                stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber+"\t"+uuidValue;
                System.out.println(retMessage);
            }else{
                retMessage = "商品卖完了，o(╥﹏╥)o";
            }
        }finally {
            //V6.0 将判断+删除自己的合并为lua脚本保证原子性
            String luaScript =
                    "if (redis.call('get',KEYS[1]) == ARGV[1]) then " +
                        "return redis.call('del',KEYS[1]) " +
                    "else " +
                        "return 0 " +
                    "end";
            stringRedisTemplate.execute(new DefaultRedisScript<>(luaScript, Boolean.class), Arrays.asList(key), uuidValue);
        }
        return retMessage+"\t"+"服务端口号："+port;
    }
}
```

补充说明：LUA脚本的构造方法

**LUA脚本的构造方法**：`new DefaultRedisScript<>(script)` 和 `new DefaultRedisScript<>(script, Long.class)` 是 Spring Data Redis 中用于定义 Redis Lua 脚本的类。 `new DefaultRedisScript<>(script)` 和 `new DefaultRedisScript<>(script, Long.class)` 对比：

- `new DefaultRedisScript<>(script)` 用于定义不返回任何结果的 Lua 脚本。这意味着脚本执行后不需要返回值，比如在进行一些写操作时可以使用这种方式
- `new DefaultRedisScript<>(script, Long.class)` 用于定义返回结果的 Lua 脚本。在这种情况下，开发者可以指定返回值的类型。在这个例子中，`Long.class` 表示脚本执行后将返回一个 Long 类型的值

尽量使用带返回值的构造方法，不然可能会报如下错误：

![image-20231225025643543](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220418650.png)

###  9.10 redis分布式锁七：可重入锁+设计模式

当前代码已经实现的优化：while判断并自旋重试获取锁+setnx含自然过期时间+Lua脚本删锁

一个靠谱分布式锁需要具备的条件和刚需：独占性、高可用、防死锁、不乱抢、重入性

继续优化的方向：兼顾锁的可重入性

##### 9.10.1 可重入锁

**可重入锁**：可重入锁又名递归锁，是指在同一个线程在外层方法获取锁的时候，再进入该线程的内层方法会自动获取锁(前提，锁对象得是同一个对象)，不会因为之前已经获取过还没释放而阻塞。如果是某个有 synchronized 修饰的递归调用方法，程序第二次进入被自己阻塞了岂不是天大的笑话，出现了作茧自缚，所以Java中ReentrantLock和synchronized都是可重入锁，可重入锁的一个优点是可一定程度避免死锁。可重入锁可以保证一个线程中的多个流程可以获取同一把锁，持有这把同步锁可以再次进入，自己可以获取自己的内部锁。可重入锁指的是可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁，这样的锁就叫做可重入锁。与可重入锁相反，不可重入锁不可递归调用，递归调用就发生死锁

![image-20231229081747712](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220418531.png)



**可重入锁中之隐式锁synchronized**：隐式锁(即synchronized关键字使用的锁)默认是可重入锁，简单的来说就是在一个synchronized修饰的方法或代码块的内部调用本类的其他synchronized修饰的方法或代码块时，是永远可以得到锁的。`synchronized`具有可重入性，`Synchronized`可重入的实现机理：每个锁对象拥有一个锁计数器和一个指向持有该锁的线程的指针。当执行monitorenter时，如果目标锁对象的计数器为零，那么说明它没有被其他线程所持有，Java虚拟机会将该锁对象的持有线程设置为当前线程，并且将其计数器加1。在目标锁对象的计数器不为零的情况下，如果锁对象的持有线程是当前线程，那么 Java 虚拟机可以将其计数器加1，否则需要等待，直至持有线程释放该锁。当执行monitorexit时，Java虚拟机则需将锁对象的计数器减1。计数器为零代表锁已被释放

 `synchronized` 修饰的同步代码块：满足可重入性，外层获取锁之后，内层仍然可以获取这个锁

```java
package com.atguigu.juc.senior.prepare;
/**
 * 在一个Synchronized修饰的方法或代码块的内部调用本类的其他Synchronized修饰的方法或代码块时，是永远可以得到锁的
 */
public class ReEntryLockDemo
{
    public static void main(String[] args)
    {
        final Object objectLockA = new Object();

        new Thread(() -> {
            synchronized (objectLockA)
            {
                System.out.println("-----外层调用");
                synchronized (objectLockA)
                {
                    System.out.println("-----中层调用");
                    synchronized (objectLockA)
                    {
                        System.out.println("-----内层调用");
                    }
                }
            }
        },"t1").start();
        // t1 外层调用
        // t1 中层调用
        // t1 内层调用
    }
}
```

 `synchronized` 修饰的同步方法：满足可重入性，外层获取锁之后，内层仍然可以获取这个锁

```java
package com.atguigu.juc.senior.prepare;
/**
 * 在一个Synchronized修饰的方法或代码块的内部调用本类的其他Synchronized修饰的方法或代码块时，是永远可以得到锁的
 */
public class ReEntryLockDemo
{
    public synchronized void m1()
    {
        System.out.println("-----m1");
        m2();
    }
    public synchronized void m2()
    {
        System.out.println("-----m2");
        m3();
    }
    public synchronized void m3()
    {
        System.out.println("-----m3");
    }

    public static void main(String[] args)
    {
        ReEntryLockDemo reEntryLockDemo = new ReEntryLockDemo();

        reEntryLockDemo.m1();
    }
}
```

**可重入锁中之显式锁Lock**：显式锁(即Lock)也有ReentrantLock这样的可重入锁

```java
package com.atguigu.juc.senior.prepare;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class ReEntryLockDemo
{
    static Lock lock = new ReentrantLock();

    public static void main(String[] args)
    {
        new Thread(() -> {
            lock.lock();
            try
            {
                System.out.println("----外层调用lock");
                lock.lock();
                try
                {
                    System.out.println("----内层调用lock");
                }finally {
                    // 这里故意注释，实现加锁次数和释放次数不一样
                    // 由于加锁次数和释放次数不一样，第二个线程始终无法获取到锁，导致一直在等待。
                    lock.unlock(); // 正常情况，加锁几次就要解锁几次
                }
            }finally {
                lock.unlock();
            }
        },"a").start();

        new Thread(() -> {
            lock.lock();
            try
            {
                System.out.println("b thread----外层调用lock");
            }finally {
                lock.unlock();
            }
        },"b").start();

    }
}
```

#####  9.10.2 ReentrantLock源码分析：可重入性原理

ReentrantLock类是Java.util.concurrent包中的一部分，它提供了可重入性的锁。下面简要分析一下ReentrantLock的部分源码，并重点讲解可重入性的实现

在ReentrantLock的实现中，**关键在于内部的同步状态和线程持有计数的维护**。首先，ReentrantLock内部使用一个Sync抽象类来实现锁的基本功能。在Sync中，通过继承AQS（AbstractQueuedSynchronizer），实现了一系列与锁相关的方法，包括加锁、释放锁等。在ReentrantLock中，通过继承Sync，实现了公平锁和非公平锁两种不同的锁。在Sync中，通过一个int类型的state来表示当前持有锁的线程数，而state的值大于0表示有线程持有锁，等于0则表示没有线程持有锁。可重入性的实现主要在Sync的tryAcquire和tryRelease方法中。当一个线程尝试获取锁时，会首先判断当前线程是否已经持有该锁，如果是，则将锁的状态加1，表示重入一次。而在释放锁时，会将锁的状态减1。通过这种方式，ReentrantLock保证了同一个线程可以多次获取同一把锁而不被阻塞，从而实现了可重入性。因为ReentrantLock的源码比较复杂，实现原理包含了AQS等底层机制，具体的实现细节需要深入源码中进行分析。但总的来说，ReentrantLock的**可重入性是通过状态维护和线程持有计数来实现的**

```java
        /**
         * Fair version of tryAcquire.  Don't grant access unless
         * recursive call or no waiters or is first.
         */
        protected final boolean tryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int c = getState();
            if (c == 0) {
                if (!hasQueuedPredecessors() &&
                    compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
            else if (current == getExclusiveOwnerThread()) {
                int nextc = c + acquires;
                if (nextc < 0)
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);
                return true;
            }
            return false;
        }
    }



    protected final boolean tryRelease(int releases) {
        int c = getState() - releases;
        if (Thread.currentThread() != getExclusiveOwnerThread())
            throw new IllegalMonitorStateException();
        boolean free = false;
        if (c == 0) {
            free = true;
            setExclusiveOwnerThread(null);
        }
        setState(c);
        return free;
    }
```

总结：要实现一个满足可重入性的锁，就需要让其满足AQS中对lock的规范，并维护锁的状态、对获取锁和释放锁的次数进行计数，lock 了几次就要unlock几次

#####  9.10.3  hset实现分布式锁的可重入性

实现redis分布式锁的可重入性思路：维护锁的状态，对获取锁的次数、释放锁的次数进行计数，保证获取几次就释放几次。采用redis中hash数据类型的hset实现可重入锁的计数

hset命令设计：`hset  redis锁名字(zzyyRedisLock)  某个请求线程的UUID+ThreadID   加锁的次数`

```shell
#  hset命令语法: hset key field value
#  hset         key                           field                        value
#  hset  redis锁名字(zzyyRedisLock)     某个请求线程的UUID+ThreadID          加锁的次数
127.0.0.1:6379> hset zzyyRedisLock oc90d37cb6ec42268861b3d739f8b3a8:1 11
(integer) 1
1270.0.1:6379> type zzyyRedisLock
hash

# Map<String,Map<0bject,object>>
```

案例命令：利用HSET进行锁的计数

```shell
127.0.0.1:6379> HEXISTS zzyyRedisLock 1111-2222
(integer) 0
127.0.0.1:6379> HSET zzyyRedisLock 1111-2222 1
(integer) 1
127.0.0.1:6379> HINCRBY zzyyRedisLock 1111-2222 1
(integer) 2
127.0.0.1:6379> HINCRBY zzyyRedisLock 1111-2222 1
(integer)3
127.0.0.1:6379> HGET zzyyRedisLock 1111-2222
"3"
127.0.0.1:6379> HINCRBY zzyyRedisLock 1111-2222-1
(integer) 2
127.0.0.1:6379> HINCRBY zzyyRedisLock 1111-2222-1
(integer) 1
127.0.0.1:6379> HINCRBY zzyyRedisLock 1111-2222-1
(inteqer) 0
127.0.0.1:6379> hget zzyyRedisLock 1111-2222
"0"
```

#####  9.10.4 lua脚本实现lock

**加锁lua脚本lock**：先利用`EXISTS key`命令判断redis 分布式锁这个key是否存在，返回零说明不存在。hset新建当前线程属于自己的锁BY UUID:ThreadID。返回1说明已经有锁，需进—步判断是不是当前线程自己的

```shell
# 如果 key 存在，则返回 1；如果 key 不存在，则返回 0
127.0.0.1:6379> EXISTS zzyyRedisLock
(integer) 0
# HSET命令返回1说明已经有锁，需进一步判断是不是当前线程自己的
# HSET  zzyyRedisLock  0c90d37cb6ec42268861b3d739f8b3a8:1      1
# HSET       key                value=UUID:ThreadID            次数
127.0.0.1:6379> HSET zzyyRedisLock oc9od37cb6ec42268861b3d739f8b3a8:1 (integer) 1

# HEXISTS是Redis中用于检查哈希表中指定字段是否存在的命令
# 返回0说明不是自己的,返回1说明是自己的锁，自增1次表示重入
HEXISTS key uuid:ThreadID

# 返回0说明不是自己的,返回1说明是自己的锁，自增1次表示重入
127.0.0.1:6379> HEXISTS zzyyRedisLock oc9od37cb6ec42268861b3d739f8b3a8:1
(integer)1
127.0.0.1:6379> HINCRBY zzyyRedisLock oc9od37cb6ec42268861b3d739f8b3a8:1 1
(integer)2
127.0.0.1:6379> HINCRBY zzyyRedisLock oc9od37cb6ec42268861b3d739f8b3a8:1 1
(integer) 3

# HINCRBY是Redis中用于为哈希表中的指定字段增加指定的整数值的命令
# 如果指定字段字段不存在，它会被创建，并且初始值为increment
HINCRBY key field increment
HINCRBY zzyyRedisLock 0c90d37cb6ec42268861b3d739f8b3a8:1 1
```

lua脚本实现lock版本一：

```lua
# Lua脚本实现分布式锁加锁，对标lock方法
if redis.call('exists','key') == 0 then
 redis.call('hset','key','uuid:threadid',1)
 redis.call('expire','key',30)
 return 1
elseif redis.call('hexists','key','uuid:threadid') == 1 then
 redis.call('hincrby','key','uuid:threadid',1)
 redis.call('expire','key',30)
 return 1
else
 return 0
end
# 待改进：相同部分是否可以替换处理？hincrby命令可否替代hset命令
```

lua脚本实现lock版本二：使用HINCRBY命令时，如果指定的key不存在，Redis 会自动创建这个 key，并将对应的字段的值设置为指定的增量值。如果key不存在，那么这个字段的值将被初始化为增量值

```lua
# 合并相同的代码,用hincrby替代hset，精简代码
if redis.call('exists','key') == 0 or redis.call('hexists','key','uuid:threadid') == 1 then
  redis.call('hincrby','key','uuid:threadid',1)
  redis.call('expire','key',30)
  return 1
else
  return 0
end
```

lua脚本实现lock版本三：

```shell
if redis.call('exists',KEYS[1]) == 0 or redis.call('hexists',KEYS[1],ARGV[1]) == 1 then 
 redis.call('hincrby',KEYS[1],ARGV[1],1) 
 redis.call('expire',KEYS[1],ARGV[2]) 
 return 1 
else
 return 0
end
```



| 含义                | 参数    | 具体参数取值                       |
| ------------------- | ------- | ---------------------------------- |
| key(分布式锁)       | KEYS[1] | zzyyRedisLock                      |
| value(具体线程的锁) | ARGV[1] | 2f586ae740a94736894ab9d51880ed9d:1 |
| 过期时间值          | ARGV[2] | 30  秒                             |

lua加锁脚本测试：

```shell
# 加锁
EVAL "if redis.call('exists',KEYS[1]) == 0 or redis.call('hexists',KEYS[1],ARGV[1]) == 1 then redis.call('hincrby',KEYS[1],ARGV[1],1) redis.call('expire',KEYS[1],ARGV[2]) return 1 else return 0 end" 1 zzyyRedisLock 0c90d37cb6ec42268861b3d739f8b3a8:1 30

# 查看加锁情况
HGET zzyyRedisLock 0c90d37cb6ec42268861b3d739f8b3a8:1
```

#####  9.10.5  lua脚本实现unlock

lua脚本实现分布式锁**unlock解锁设计思路**：

  1.判断是不是当前线程的锁，只解锁当前线程的锁，防止误删其他线程的锁，保证有锁且是自己的锁。判断命令：`HEXISTS key uuid:ThreadlD`。返回0，说明根本没有锁。程序块返回nil而不是0，说明有锁且是自己的锁

  2.直接调用`HINCRBY -1`表示每次减个1，解锁一次

  3.直到它变为0表示可以删除该锁Key，del锁key

```shell
# 1.有锁且还是自己的锁: HEXISTS key uuid:ThreadlD
# 2.返回0，说明根本没有锁，程序块返回nil而不是0，说明有锁且是自己的锁
# 3.直接调用HINCRBY负一表示每次减个1，解锁一次
# 4.直到它变为零表示可以删除该锁Key,del锁key
127.0.0.1:6379> HEXISTS zzyyRedisLock oc9od37cb6ec42268861b3d739f8b3a8:1
(integer) 0
127.0.0.1:6379> HEXISTS zzyyRedisLock 2f586ae740a94736894ab9d51880ed9d:1
(inteaer) 1
127.0.0.1:6379> HGET zzyyRedisLock 2f586ae740a94736894ab9d51880ed9d:1
"3"
127.0.0.1:6379> HINCRBY zzyyRedisLock 2f586ae740a94736894ab9d51880ed9d:1 -1
(integer)2
127.0.0.1:6379> HINCRBY zzyyRedisLock 2f586ae740a94736894ab9d51880ed9d:1 -1
(integer)1
127.0.0.1:6379> HINCRBY zzyyRedisLock 2f586ae740a94736894ab9d51880ed9d:1 -1
(integer)0
127.0.0.1: 6379> del zzyyRedisLock
(integer)1
```

实现unlock解锁的Lua脚本伪代码：

```lua
if redis.call('HEXISTS',lock,uuid:threadID) == 0 then
 return nil
elseif redis.call('HINCRBY',lock,uuid:threadID,-1) == 0 then
 return redis.call('del',lock)
else 
 return 0
end
```

实现unlock解锁的Lua脚本：

```shell
if redis.call('HEXISTS',KEYS[1],ARGV[1]) == 0 then
 return nil
elseif redis.call('HINCRBY',KEYS[1],ARGV[1],-1) == 0 then
 return redis.call('del',KEYS[1])
else
 return 0
end
```

使用redis的eval命令调用unlock解锁的Lua脚本：

```shell

eval "if redis.call('HEXISTS',KEYS[1],ARGV[1]) == 0 then return nil elseif redis.call('HINCRBY',KEYS[1],ARGV[1],-1) == 0 then return redis.call('del',KEYS[1]) else return 0 end" 1 zzyyRedisLock 2f586ae740a94736894ab9d51880ed9d:1
```

测试lock加锁、unlock解锁的全套流程：

![image-20231225224933848](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220418694.png)

#####  9.10.6 实现可重入的分布式锁

复原程序为初始无锁版

```java
package com.atguigu.redislock.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class InventoryService
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String port;

    public String sale()
    {
        String retMessage = "";
        //1 查询库存信息
        String result = stringRedisTemplate.opsForValue().get("inventory001");
        //2 判断库存是否足够
        Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
        //3 扣减库存
        if(inventoryNumber > 0) {
            stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
            retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber+"\t";
            System.out.println(retMessage);
        }else{
            retMessage = "商品卖完了，o(╥﹏╥)o";
        }
        return retMessage+"\t"+"服务端口号："+port;
    }
}
```

lock加锁lua脚本：

```lua
if redis.call('exists',KEYS[1]) == 0 or redis.call('hexists',KEYS[1],ARGV[1]) == 1 then 
  redis.call('hincrby',KEYS[1],ARGV[1],1) 
  redis.call('expire',KEYS[1],ARGV[2]) 
  return 1 
else
  return 0
end
```

unlock解锁lua脚本：

```lua
if redis.call('HEXISTS',KEYS[1],ARGV[1]) == 0 then 
  return nil 
elseif redis.call('HINCRBY',KEYS[1],ARGV[1],-1) == 0 then 
  return redis.call('del',KEYS[1]) 
else 
  return 0
end
```

 下面将lua脚本整合进入微服务Java程序，新建RedisDistributedLock类并实现JUC里面的Lock接口，使其满足JUC里面AQS对Lock锁的接口规范定义，并结合设计模式开发属于自己的Redis分布式锁工具类

`RedisDistributedLock`：通过实现JUC里面的Lock接口，实现Redis分布式锁RedisDistributedLock

```java
package com.atguigu.redislock.mylock;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.support.collections.DefaultRedisList;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
//@Component 引入DistributedLockFactory工厂模式，从工厂获得而不再从spring拿到
public class RedisDistributedLock implements Lock
{
    private StringRedisTemplate stringRedisTemplate;

    private String lockName;//KEYS[1]
    private String uuidValue;//ARGV[1]
    private long   expireTime;//ARGV[2]
    public RedisDistributedLock(StringRedisTemplate stringRedisTemplate, String lockName)
    {
        this.stringRedisTemplate = stringRedisTemplate;
        this.lockName = lockName;
        this.uuidValue = IdUtil.simpleUUID()+":"+Thread.currentThread().getId();//UUID:ThreadID
        this.expireTime = 30L;
    }
    @Override
    public void lock()
    {
        tryLock();
    }
    @Override
    public boolean tryLock()
    {
        try {tryLock(-1L,TimeUnit.SECONDS);} catch (InterruptedException e) {e.printStackTrace();}
        return false;
    }

    /**
     * 干活的，实现加锁功能，实现这一个干活的就OK，全盘通用
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException{
        if(time != -1L){
            this.expireTime = unit.toSeconds(time);
        }
        String script =
                "if redis.call('exists',KEYS[1]) == 0 or redis.call('hexists',KEYS[1],ARGV[1]) == 1 then " +
                        "redis.call('hincrby',KEYS[1],ARGV[1],1) " +
                        "redis.call('expire',KEYS[1],ARGV[2]) " +
                        "return 1 " +
                "else " +
                        "return 0 " +
                "end";

        System.out.println("script: "+script);
        System.out.println("lockName: "+lockName);
        System.out.println("uuidValue: "+uuidValue);
        System.out.println("expireTime: "+expireTime);

        while (!stringRedisTemplate.execute(new DefaultRedisScript<>(script,Boolean.class), Arrays.asList(lockName),uuidValue,String.valueOf(expireTime))) {
            TimeUnit.MILLISECONDS.sleep(50);
        }
        return true;
    }

    /**
     *干活的，实现解锁功能
     */
    @Override
    public void unlock()
    {
        String script =
                "if redis.call('HEXISTS',KEYS[1],ARGV[1]) == 0 then " +
                "   return nil " +
                "elseif redis.call('HINCRBY',KEYS[1],ARGV[1],-1) == 0 then " +
                "   return redis.call('del',KEYS[1]) " +
                "else " +
                "   return 0 " +
                "end";
        // nil = false 1 = true 0 = false
        System.out.println("lockName: "+lockName);
        System.out.println("uuidValue: "+uuidValue);
        System.out.println("expireTime: "+expireTime);
        Long flag = stringRedisTemplate.execute(new DefaultRedisScript<>(script, Long.class), Arrays.asList(lockName),uuidValue,String.valueOf(expireTime));
        if(flag == null)
        {
            throw new RuntimeException("This lock doesn't EXIST");
        }

    }

    //===下面的redis分布式锁暂时用不到=======================================
    //===下面的redis分布式锁暂时用不到=======================================
    //===下面的redis分布式锁暂时用不到=======================================
    @Override
    public void lockInterruptibly() throws InterruptedException
    {

    }

    @Override
    public Condition newCondition()
    {
        return null;
    }
}
```

#####  9.10.7  工厂模式提高分布式锁的通用性

**结合设计模式开发Redis分布式锁工具类**：lnventoryService中锁库存的逻辑直接使用redis分布式锁进行构造的话，分布式锁就被写死了。分布式锁不仅有redis分布式锁，可能还会有mysql分布式锁、zookeeper分布式锁，为了保证分布式锁的通用性，引入工厂模式进行优化

![image-20231230222227798](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220418164.png)

程序中提高通用性的一些方法：继承、多态、工厂模式

```
增加接口或类的通用性:
1.缺少什么new什么:   Dog dog= new Dog();
2.多态:            Animal a = new 鸡鸭猫狗鱼();
3.多态 + 动态:      提交给spring容器管理+池化技术
4.设计模式:         可以通过工厂设计模式，直接通过传递参数从工厂获得
```

`DistributedLockFactory`：引入工厂模式进行优化

```java
package com.atguigu.redislock.mylock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.locks.Lock;
@Component
public class DistributedLockFactory
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private String lockName;

    public Lock getDistributedLock(String lockType)
    {
        if(lockType == null) return null;

        if(lockType.equalsIgnoreCase("REDIS")){
            lockName = "zzyyRedisLock";
            return new RedisDistributedLock(stringRedisTemplate,lockName);
        } else if(lockType.equalsIgnoreCase("ZOOKEEPER")){
            //TODO zookeeper版本的分布式锁实现
            return new ZookeeperDistributedLock();
        } else if(lockType.equalsIgnoreCase("MYSQL")){
            //TODO mysql版本的分布式锁实现
            return null;
        }

        return null;
    }
}
```

`RedisDistributedLock`：

```java
package com.atguigu.redislock.mylock;
import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.support.collections.DefaultRedisList;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
//@Component 引入DistributedLockFactory工厂模式，从工厂获得而不再从spring拿到
public class RedisDistributedLock implements Lock
{
    private StringRedisTemplate stringRedisTemplate;

    private String lockName;//KEYS[1]
    private String uuidValue;//ARGV[1]
    private long   expireTime;//ARGV[2]

    public RedisDistributedLock(StringRedisTemplate stringRedisTemplate, String lockName){
        this.stringRedisTemplate = stringRedisTemplate;
        this.lockName = lockName;
        this.uuidValue = IdUtil.simpleUUID()+":"+Thread.currentThread().getId();//UUID:ThreadID
        this.expireTime = 30L;
    }
    @Override
    public void lock(){
        tryLock();
    }
    @Override
    public boolean tryLock(){
        try {tryLock(-1L,TimeUnit.SECONDS);} catch (InterruptedException e) {e.printStackTrace();}
        return false;
    }

    /**
     * 干活的，实现加锁功能，实现这一个干活的就OK，全盘通用
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException{
        if(time != -1L){
            this.expireTime = unit.toSeconds(time);
        }
        String script =
                "if redis.call('exists',KEYS[1]) == 0 or redis.call('hexists',KEYS[1],ARGV[1]) == 1 then " +
                        "redis.call('hincrby',KEYS[1],ARGV[1],1) " +
                        "redis.call('expire',KEYS[1],ARGV[2]) " +
                        "return 1 " +
                "else " +
                        "return 0 " +
                "end";
        System.out.println("script: "+script);
        System.out.println("lockName: "+lockName);
        System.out.println("uuidValue: "+uuidValue);
        System.out.println("expireTime: "+expireTime);
        while (!stringRedisTemplate.execute(new DefaultRedisScript<>(script,Boolean.class), Arrays.asList(lockName),uuidValue,String.valueOf(expireTime))) {
            TimeUnit.MILLISECONDS.sleep(50);
        }
        return true;
    }

    /**
     *干活的，实现解锁功能
     */
    @Override
    public void unlock()
    {
        String script =
                "if redis.call('HEXISTS',KEYS[1],ARGV[1]) == 0 then " +
                "   return nil " +
                "elseif redis.call('HINCRBY',KEYS[1],ARGV[1],-1) == 0 then " +
                "   return redis.call('del',KEYS[1]) " +
                "else " +
                "   return 0 " +
                "end";
        // nil = false 1 = true 0 = false
        System.out.println("lockName: "+lockName);
        System.out.println("uuidValue: "+uuidValue);
        System.out.println("expireTime: "+expireTime);
        Long flag = stringRedisTemplate.execute(new DefaultRedisScript<>(script, Long.class), Arrays.asList(lockName),uuidValue,String.valueOf(expireTime));
        if(flag == null)
        {
            throw new RuntimeException("This lock doesn't EXIST");
        }

    }

    //===下面的redis分布式锁暂时用不到=======================================
    //===下面的redis分布式锁暂时用不到=======================================
    //===下面的redis分布式锁暂时用不到=======================================
    @Override
    public void lockInterruptibly() throws InterruptedException
    {

    }

    @Override
    public Condition newCondition()
    {
        return null;
    }
}
```

lnventoryService使用工厂模式版：

```java
package com.atguigu.redislock.service;
import ch.qos.logback.core.joran.conditional.ThenAction;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.atguigu.redislock.mylock.DistributedLockFactory;
import com.atguigu.redislock.mylock.RedisDistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.omg.IOP.TAG_RMI_CUSTOM_MAX_STREAM_FORMAT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@Service
@Slf4j
public class InventoryService
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String port;
    @Autowired
    private DistributedLockFactory distributedLockFactory;
    
    public String sale()
    {

        String retMessage = "";

        Lock redisLock = distributedLockFactory.getDistributedLock("redis");
        redisLock.lock();
        try
        {
            //1 查询库存信息
            String result = stringRedisTemplate.opsForValue().get("inventory001");
            //2 判断库存是否足够
            Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
            //3 扣减库存
            if(inventoryNumber > 0)
            {
                inventoryNumber = inventoryNumber - 1;
                stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(inventoryNumber));
                retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber+"\t服务端口:" +port;
                System.out.println(retMessage);
                return retMessage;
            }
            retMessage = "商品卖完了，o(╥﹏╥)o"+"\t服务端口:" +port;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            redisLock.unlock();
        }
        return retMessage;
    }
}
```

单机+并发测试通过：`http://localhost:7777/inventory/sale`

#####  9.10.8 可重入性测试 | 工厂模式bug修复

可重入测试? lnventoryService类新增可重入测试方法`testReEnter`

```java
package com.atguigu.redislock.service;

import com.atguigu.redislock.mylock.DistributedLockFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.concurrent.locks.Lock;
@Service
@Slf4j
public class InventoryService
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String port;
    @Autowired
    private DistributedLockFactory distributedLockFactory;

    public String sale()
    {
        String retMessage = "";
        Lock redisLock = distributedLockFactory.getDistributedLock("redis");
        redisLock.lock();
        try
        {
            //1 查询库存信息
            String result = stringRedisTemplate.opsForValue().get("inventory001");
            //2 判断库存是否足够
            Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
            //3 扣减库存
            if(inventoryNumber > 0) {
                stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber+"\t";
                System.out.println(retMessage);
                testReEnter();
            }else{
                retMessage = "商品卖完了，o(╥﹏╥)o";
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            redisLock.unlock();
        }
        return retMessage+"\t"+"服务端口号："+port;
    }

    private void testReEnter()
    {
        Lock redisLock = distributedLockFactory.getDistributedLock("redis");
        redisLock.lock();
        try
        {
            System.out.println("################测试可重入锁#######");
        }finally {
            redisLock.unlock();
        }
    }
}

/**

 //1 查询库存信息
 String result = stringRedisTemplate.opsForValue().get("inventory001");
 //2 判断库存是否足够
 Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
 //3 扣减库存
 if(inventoryNumber > 0) {
 stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
 retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber+"\t";
 System.out.println(retMessage);
 }else{
 retMessage = "商品卖完了，o(╥﹏╥)o";
 }
 */
```

测试：访问`http://localhost:7777/inventory/sale`

结果：ThreadID一致了但是UUID不OK

![image-20231230224042100](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220418513.png)

`DistributedLockFactory`：bug解决，**在工厂类的构造方法中生成UUID**

```java
package com.atguigu.redislock.mylock;
import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.locks.Lock;
@Component
public class DistributedLockFactory
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private String lockName;
    private String uuidValue;

    public DistributedLockFactory()
    {
        this.uuidValue = IdUtil.simpleUUID();//UUID
    }

    public Lock getDistributedLock(String lockType)
    {
        if(lockType == null) return null;

        if(lockType.equalsIgnoreCase("REDIS")){
            lockName = "zzyyRedisLock";
            return new RedisDistributedLock(stringRedisTemplate,lockName,uuidValue);
        } else if(lockType.equalsIgnoreCase("ZOOKEEPER")){
            //TODO zookeeper版本的分布式锁实现
            return new ZookeeperDistributedLock();
        } else if(lockType.equalsIgnoreCase("MYSQL")){
            //TODO mysql版本的分布式锁实现
            return null;
        }
        return null;
    }
}
```

`RedisDistributedLock`：

```java
package com.atguigu.redislock.mylock;
import cn.hutool.core.util.IdUtil;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
public class RedisDistributedLock implements Lock
{
    private StringRedisTemplate stringRedisTemplate;
    private String lockName;
    private String uuidValue;
    private long   expireTime;

    public RedisDistributedLock(StringRedisTemplate stringRedisTemplate, String lockName,String uuidValue)
    {
        this.stringRedisTemplate = stringRedisTemplate;
        this.lockName = lockName;
        this.uuidValue = uuidValue+":"+Thread.currentThread().getId();
        this.expireTime = 30L;
    }

    @Override
    public void lock()
    {
        this.tryLock();
    }
    @Override
    public boolean tryLock()
    {
        try
        {
            return this.tryLock(-1L,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException
    {
        if(time != -1L)
        {
            expireTime = unit.toSeconds(time);
        }

        String script =
                "if redis.call('exists',KEYS[1]) == 0 or redis.call('hexists',KEYS[1],ARGV[1]) == 1 then " +
                    "redis.call('hincrby',KEYS[1],ARGV[1],1) " +
                    "redis.call('expire',KEYS[1],ARGV[2]) " +
                    "return 1 " +
                "else " +
                    "return 0 " +
                "end";
        System.out.println("lockName: "+lockName+"\t"+"uuidValue: "+uuidValue);

        while (!stringRedisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), Arrays.asList(lockName), uuidValue, String.valueOf(expireTime)))
        {
            try { TimeUnit.MILLISECONDS.sleep(60); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        return true;
    }

    @Override
    public void unlock()
    {
        String script =
                "if redis.call('HEXISTS',KEYS[1],ARGV[1]) == 0 then " +
                    "return nil " +
                "elseif redis.call('HINCRBY',KEYS[1],ARGV[1],-1) == 0 then " +
                    "return redis.call('del',KEYS[1]) " +
                "else " +
                        "return 0 " +
                "end";
        System.out.println("lockName: "+lockName+"\t"+"uuidValue: "+uuidValue);
        Long flag = stringRedisTemplate.execute(new DefaultRedisScript<>(script, Long.class), Arrays.asList(lockName), uuidValue, String.valueOf(expireTime));
        if(flag == null)
        {
            throw new RuntimeException("没有这个锁，HEXISTS查询无");
        }
    }

    //=========================================================
    @Override
    public void lockInterruptibly() throws InterruptedException
    {

    }
    @Override
    public Condition newCondition()
    {
        return null;
    }
}
```

lnventoryService类新增可重入测试方法：

```java
package com.atguigu.redislock.service;
import cn.hutool.core.util.IdUtil;
import com.atguigu.redislock.mylock.DistributedLockFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@Service
@Slf4j
public class InventoryService
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String port;
    @Autowired
    private DistributedLockFactory distributedLockFactory;

    public String sale()
    {
        String retMessage = "";
        Lock redisLock = distributedLockFactory.getDistributedLock("redis");
        redisLock.lock();
        try
        {
            //1 查询库存信息
            String result = stringRedisTemplate.opsForValue().get("inventory001");
            //2 判断库存是否足够
            Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
            //3 扣减库存
            if(inventoryNumber > 0) {
                stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber;
                System.out.println(retMessage);
                this.testReEnter();
            }else{
                retMessage = "商品卖完了，o(╥﹏╥)o";
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            redisLock.unlock();
        }
        return retMessage+"\t"+"服务端口号："+port;
    }


    private void testReEnter()
    {
        Lock redisLock = distributedLockFactory.getDistributedLock("redis");
        redisLock.lock();
        try
        {
            System.out.println("################测试可重入锁####################################");
        }finally {
            redisLock.unlock();
        }
    }
}
```

单机+并发+可重入性，测试通过

###  9.11 redis分布式锁八：自动续期

#####  9.11.1 分布式锁自动续期

处理业务的时间可能会超过锁的过期时间，不能在业务还未处理完时就将分布式锁过期删除。要提高锁的可用性，就应该确保redisLock过期时间大于业务执行时间的问题，也就是要对redis分布式锁进行自动续期

自动续期lua脚本：

```lua
//自动续期
if redis.call('HEXISTS',KEYS[1],ARGV[1]) == 1 then
  return redis.call('expire',KEYS[1],ARGV[2])
else
  return 0
end
```

自动续期脚本测试：

```lua
127.0.0.1:6379> hset zzyyRedisLock 111122223333:11 3
(integer)1
127.0.0.1:6379> expire zzyyRedisLock 40
(integer) 1
127.0.0.1:6379> ttl zzyyRedisLock
(integer) 36
127.0.0.1:6379> eval "if redis.call('HEXISTS',KEYS[1],ARGV[1]) == 1 then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end" 1 zzyyRedisLock 111122223333:11 40
(integer) 1
127.o.0.1: 6379> ttl zzyyRedisLock
(integer) 36
```

`RedisDistributedLock`：新增自动续期功能，后台自定义扫描程序，如果规定时间内没有完成业务逻辑，会调用加钟自动续期的脚本。加锁成功后，后台有新建扫描程序来看看是否到过期时间，自动续期

```java
package com.atguigu.redislock.mylock;
import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.support.collections.DefaultRedisList;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
public class RedisDistributedLock implements Lock
{
    private StringRedisTemplate stringRedisTemplate;

    private String lockName;//KEYS[1]
    private String uuidValue;//ARGV[1]
    private long   expireTime;//ARGV[2]

    public RedisDistributedLock(StringRedisTemplate stringRedisTemplate,String lockName,String uuidValue)
    {
        this.stringRedisTemplate = stringRedisTemplate;
        this.lockName = lockName;
        this.uuidValue = uuidValue+":"+Thread.currentThread().getId();
        this.expireTime = 30L;
    }
    @Override
    public void lock()
    {
        tryLock();
    }

    @Override
    public boolean tryLock()
    {
        try {tryLock(-1L,TimeUnit.SECONDS);} catch (InterruptedException e) {e.printStackTrace();}
        return false;
    }

    /**
     * 干活的，实现加锁功能，实现这一个干活的就OK，全盘通用
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException
    {
        if(time != -1L)
        {
            this.expireTime = unit.toSeconds(time);
        }

        String script =
                "if redis.call('exists',KEYS[1]) == 0 or redis.call('hexists',KEYS[1],ARGV[1]) == 1 then " +
                        "redis.call('hincrby',KEYS[1],ARGV[1],1) " +
                        "redis.call('expire',KEYS[1],ARGV[2]) " +
                        "return 1 " +
                        "else " +
                        "return 0 " +
                        "end";

        System.out.println("script: "+script);
        System.out.println("lockName: "+lockName);
        System.out.println("uuidValue: "+uuidValue);
        System.out.println("expireTime: "+expireTime);

        while (!stringRedisTemplate.execute(new DefaultRedisScript<>(script,Boolean.class), Arrays.asList(lockName),uuidValue,String.valueOf(expireTime))) {
            TimeUnit.MILLISECONDS.sleep(50);
        }
        // 加锁成功后，新建一个后台扫描程序
        // 来检查key目前的ttl，是否到我们规定的二分之一或三分之二TTL,来实现续期
        this.renewExpire();
        return true;
    }

    /**
     *干活的，实现解锁功能
     */
    @Override
    public void unlock()
    {
        String script =
                "if redis.call('HEXISTS',KEYS[1],ARGV[1]) == 0 then " +
                        "   return nil " +
                        "elseif redis.call('HINCRBY',KEYS[1],ARGV[1],-1) == 0 then " +
                        "   return redis.call('del',KEYS[1]) " +
                        "else " +
                        "   return 0 " +
                        "end";
        // nil = false 1 = true 0 = false
        System.out.println("lockName: "+lockName);
        System.out.println("uuidValue: "+uuidValue);
        System.out.println("expireTime: "+expireTime);
        Long flag = stringRedisTemplate.execute(new DefaultRedisScript<>(script, Long.class), Arrays.asList(lockName),uuidValue,String.valueOf(expireTime));
        if(flag == null)
        {
            throw new RuntimeException("This lock doesn't EXIST");
        }
    }

    private void renewExpire()
    {
        String script =
                "if redis.call('HEXISTS',KEYS[1],ARGV[1]) == 1 then " +
                        "return redis.call('expire',KEYS[1],ARGV[2]) " +
                        "else " +
                        "return 0 " +
                        "end";

        new Timer().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                if (stringRedisTemplate.execute(new DefaultRedisScript<>(script, Boolean.class), Arrays.asList(lockName),uuidValue,String.valueOf(expireTime))) {
                    // 递归调用，实现不间断地自动续期检查
                    renewExpire();
                }
            }
        },(this.expireTime * 1000)/3);
    }

    //===下面的redis分布式锁暂时用不到=======================================
    //===下面的redis分布式锁暂时用不到=======================================
    //===下面的redis分布式锁暂时用不到=======================================
    @Override
    public void lockInterruptibly() throws InterruptedException
    {

    }

    @Override
    public Condition newCondition()
    {
        return null;
    }
}
```

`lnventoryService`：记得去掉可重入测试testReEnter()，lnventoryService业务逻辑里面故意sleep一段时间测试自动续期

```java
package com.atguigu.redislock.service;
import cn.hutool.core.util.IdUtil;
import com.atguigu.redislock.mylock.DistributedLockFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@Service
@Slf4j
public class InventoryService
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String port;
    @Autowired
    private DistributedLockFactory distributedLockFactory;

    public String sale()
    {
        String retMessage = "";
        Lock redisLock = distributedLockFactory.getDistributedLock("redis");
        redisLock.lock();
        try
        {
            //1 查询库存信息
            String result = stringRedisTemplate.opsForValue().get("inventory001");
            //2 判断库存是否足够
            Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
            //3 扣减库存
            if(inventoryNumber > 0) {
                stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber;
                System.out.println(retMessage);
                //暂停几秒钟线程,为了测试自动续期
                try { TimeUnit.SECONDS.sleep(120); } catch (InterruptedException e) { e.printStackTrace(); }
            }else{
                retMessage = "商品卖完了，o(╥﹏╥)o";
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            redisLock.unlock();
        }
        return retMessage+"\t"+"服务端口号："+port;
    }


    private void testReEnter()
    {
        Lock redisLock = distributedLockFactory.getDistributedLock("redis");
        redisLock.lock();
        try
        {
            System.out.println("################测试可重入锁####################################");
        }finally {
            redisLock.unlock();
        }
    }
}
```

#####  9.11.2 分布式系统CAP理论

**CAP理论**：CAP理论是分布式系统设计中的重要概念，它指出在一个分布式系统中，一致性（Consistency）、可用性（Availability）、以及分区容错性（Partition Tolerance），这三个属性不可能同时得到满足，只能同时满足其中的两个

- 一致性（Consistency）指的是系统中的所有节点在同一时间具有相同的数据副本，即数据更新操作后，所有节点都能读取到最新的数据。在分布式系统中，保证数据的一致性是非常重要的
- 可用性（Availability）指的是系统提供的服务必须一直可用，即使出现了节点故障，系统也应该继续对外提供服务，不应该因为节点故障而导致整个系统不可用
- 分区容错性（Partition Tolerance）指的是系统能够容忍网络中的分区故障，即系统的各个节点之间的通信可能会出现延迟或失败，但系统仍然能够正常工作

在分布式系统中，CAP理论的核心观点是在出现网络分区时，系统**要么选择保证一致性和分区容忍性，牺牲可用性**；**要么保证可用性和分区容忍性，牺牲一致性**。因此，在设计分布式系统时，需要充分考虑CAP理论，并根据系统的需求权衡选择保证哪两个属性



**Redis集群是AP**：redis异步复制造成的锁丢失，比如主节点没来的及把刚刚set进来这条数据给从节点，master就挂了，从机上位但从机上无该数据

**Zookeeper集群是CP**：zk是CP原理。假如1号机注册给server1, server1同步给server2, server2同步给各个follower，为了保证—致性，只有整个过程都成功了，1号机才收到注册成功

![image-20231231003516895](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220419578.png)



故障：当leader重启或者网络故障下，整个ZK集群会重新选举新老大，选举期间client不可以注册，即zk不可用，所以牺牲了可用性A。只有选举出新老大后，系统才恢复注册。故zk为了保证数据一致性牺牲了可靠性。由于在大型分布式系统中故障难以避免,leader出故障可能性很高，所以很多大型系统都不会选择zk的原因

![image-20231231003600005](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220419618.png)



**Eureka集群是AP**：Eureak是AP原理。保证高可用而牺牲了—致性。client1注册给server1，server1直接告诉client成功，client1高兴的走了，剩下的就是server1再同步给server2，为保证高可用，都是异步同步的

![image-20231231003928302](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220419351.png)





| 服务注册与发现框架 | CAP模型 | 控制台管理 | 社区活跃度      |
| ------------------ | ------- | ---------- | --------------- |
| Eureka             | AP      | 支持       | 低(2.x版本闭源) |
| Zookeeper          | CP      | 不支持     | 中              |
| Consul             | CP      | 支持       | 高              |
| Nacos              | AP      | 支持       | 高              |

###  9.12 总结

**redis分布式锁迭代优化**：

  1.synchronized  单机版OK，上分布式死翘翘。只满足同一个JVM虚拟机中，跨机器，跨服务时无法起作用

  2.nginx分布式微服务单机锁不行。取消单机锁，上redis分布式锁set nx。只加了锁，没有释放锁，出异常的话，可能无法释放锁，所以要在代码层面finally释放锁

  3.假如服务宕机，微服务代码层面根本没有走到finally这块，没办法保证解锁，这个key没有被删除。因为要保证分布式锁能在一段时间内被释放掉，所以加入lockKey的过期时间设定

  4.为代码块加锁和为锁key增加过期时间必须是原子性操作，所以要set nx + 过期时间必须同—行。加锁和过期时同设置必须同时进行，保证原子性

  5.必须保证只能删除当前线程自己加的的锁,不能把其他线程加的锁删除了。为了防止误删，在删除锁的时候需要判断加锁与解锁是不是同一个线程，只能删除当前线程自己加的的锁，不误删其他线程加的锁

  6.`判断锁是否是当前线程加的` 和`删除当前线程加的锁`这两个步骤要同时完成，要保证判断和删除的原子性，所以将unlock解锁中的`判断+删锁`操作利用Lua脚本实现，保证了原子性

  7.保证锁的可重入性。需要对加锁和解锁的次数进行统计，当加锁次数为0时才能够去解锁。利用hset命令替代set nx + lock，并通过Lua脚本保证加锁和解锁时各种操作的原子性

  8.自动续期。处理业务的时间可能会超过锁的过期时间，所以创建锁之后就要去监测锁是否被删除，如果在2/3的TTL时间内还没被删除，就为这个自动续期，增加过期时间，保证锁的存在时间对于处理业务的时间

**实现分布式锁思路要点**：

- 按照JUC里面`java.util.concurrent.locks.Lock`接口规范编写
- lock加锁关键逻辑：加锁实际上就是在redis中，给Key键设置一个值，为避免死锁，并给定一个过期时间。加锁不成，需要while进行重试并自旋。加锁时利用Lua脚本保证原子性，并通过redis里面的hash数据模型保证可重入性。最后要实现自动续期，保证在线程解锁前锁不能自己失效

![image-20231231150037196](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220419230.png)



- unlock解锁关键逻辑：将Key键删除。但也不能乱删，不能说客户端1的请求将客户端2的锁给删除掉，只能自己删除自己的锁。考虑可重入性的递减，加锁几次就要减锁几次最后到零了，直接del删除

![image-20231231150249652](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220419676.png)

上面自研的redis锁对于一般中小公司，不是特别高并发场景足够用了，单机redis小业务也撑得住



## 10. Redlock算法和底层源码分析

###  10.1  Redlock红锁算法

**官网说明**：`https://redis.io/docs/manual/patterns/distributed-locks/`

**Redlock红锁官网原文**：使用Redis的分布式锁。Redis 的分布式锁模式。在不同进程必须以互斥方式使用共享资源的许多环境中，分布式锁是一个非常有用的原语。有许多库和博客文章描述了如何使用Redis实现DLM(分布式锁管理器)，但每个库都使用不同的方法并且许多库使用简单的方法，与稍微复杂的方法相比，保证较低设计。本页描述了一个更规范的算法来使用Redis实现分布式锁。我们提出了一种称为**Redlock的算法**，它实现了我们认为比普通单实例方法更安全的DLM。我们希望社区对其进行分析、提供反馈，并将其用作实施或更杂或替代设计的起点

**Redlock算法产生背景**：

大多数redis分布式锁的实现都存在缺陷。（官网原文)：为什么基于故障转移的实施是不够的？为了理解我们想要改进的地方，让我们分析一下大多数基于Redis的分布式锁库的现状。使用Redis锁定资源的最简单方法是在实例中创建一个键。 使用Redis的过期功能，密钥通常是在有限的生存时间内创建的，因此最终它会被释放(我们列表中的属性2)。当客户端需要释放资源时，它会删除密钥。表面上这很好，但有一个问题: 就是我们架构中可能会出现单点故障。如果Redis master宕机了怎么办？好吧，让我们添加一个副本!如果主人不可用，请使用它。不幸的是，这是不可行的。这样做我们无法实现互斥的安全属性，因为Redis复制是异步的。此模型存在竞争条件:

​       1.Client A获取master中的锁
​       2.在对密钥的写入传输到副本之前，主服务器崩溃了
​       3.副本被提升为主
​       4.客户端B获取对同一资源A已持有锁的锁。违反安全规定!

有时在特殊情况下,例如在故障期间，多个客户端可以同时持有锁是完全没问题的。如果是这种情况，您可以使用基于复制的解决方案。否则，我们建议实施本文档中描述的解决方案



**解读官网中说到的特殊故障**：

1.客户A通过redis的set命令建分布式锁成功并持有锁

2.正常情况主从机都有这分布式锁

3.突然出故障了，master还没来得同步数据给slave，此时slave机器上没有对应锁的信息

4.从机slave上位，变成了新master

5.客户B照样可以同样的建锁成功，出现了可怕情况：一锁被多建多用。CAP里面的CP遭到了破坏，而且redis无论单机、主从、响兵和主从均有这样风险

![image-20231231151210581](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220419906.png)

线程1首先获取锁成功，将键值对写入 redis 的 master 节点，在 redis 将该键值对同步到 slave 节点之前，master 发生了故障；redis 触发故障转移，其中一个 slave 升级为新的 master，此时新上位的master并不包含线程1写入的键值对，因此线程2尝试获取锁也可以成功拿到锁，此时相当于有两个线程获取到了锁，可能会导致各种预期之外的情况发生，例如最常见的脏数据。我们加的是排它独占锁，同一时间只能有一个建redis锁成功并持有锁，严禁出现2个以上的请求线程拿到锁

redis之父提出了Redlock算法解决这个问题：Redis也提供了Redlock算法，用来实现基于多个实例的分布式锁。**锁变量由多个实例维护，即使有实例发生了故障，锁变量仍然是存在的，客户端还是可以完成锁操作**。Redlock算法是实现高可靠分布式锁的一种有效解决方案，可以在实际开发中使用

**红锁算法**：

在算法的分布式版本中，我们假设我们有N个Redis主节点。这些节点是完全独立的(也就是每一个节点都是相互独立的master，不存在主从关系)，所以我们不使用复制或任何其他隐式协调系统。我们已经描述了如何在单个实例中安全地获取和释放锁。我们想当然地认为算法会使用这个方法在单个实例中获取和释放锁。在我们的示例中，我们设置N=5，这是一个合理的值，因此我们需要在不同的计算机或虚拟机上运行5个Redis master，以确保它们以几乎独立的方式发生故障。注意：不是集群，而是5个独立的Redis master

为了获取锁，客户端执行以下操作：

1.它以毫秒为单位获取当前时间

2.它尝试在所有N个实例中顺序获取锁，在所有实例中使用相同的键名和随机值。在步骤2中，在每个实例中设置锁时，客户端使用与总锁自动释放时间相比较小的超时来获取锁。例如，如果自动释放时间为10秒，则超时可能在～5-50毫秒范围内。这可以防止客户端在尝试与已关闭的Redis节点通信时长时间处于阻塞状态：如果一个实例不可用，我们应该尽快尝试与下一个实例通信

3.客户端通过从当前时间减去步骤1中获得的时间戳来计算获得锁所用的时间。当且仅当客户端能够在大多数实例(至少3个）中获得锁时，并且获得锁的总时间小于锁有效期，则认为获得了锁
4.如果获得了锁，则其有效时间被认为是初始有效时间减去经过的时间，如步骤3中计算的那样
5.如果客户端由于某种原因未能获得锁（要么无法锁定N/2+1个实例，要么有效期为负)，它将尝试解锁。所有实例(即使是它认为不能锁定的实例)可以锁定)

**Redlock算法设计理念**：

此方案也是基于 (set 加锁、Lua 脚本解锁) 进行改良的，所以redis之父antirez 只描述了差异的地方，大致方案如下：假设我们有N个Redis主节点，例如 N = 5这些节点是完全独立的，我们不使用复制或任何其他隐式协调系统，为了取到锁，客户端执行以下操作：

1.获取当前时间，以毫秒为单位

2.依次尝试从5个实例，使用相同的 key 和随机值（例如 UUID）获取锁。当向Redis 请求获取锁时，客户端应该设置一个超时时间，这个超时时间应该小于锁的失效时间。例如你的锁自动失效时间为 10 秒，则超时时间应该在 5-50 毫秒之间。这样可以防止客户端在试图与一个宕机的 Redis 节点对话时长时间处于阻塞状态。如果一个实例不可用，客户端应该尽快尝试去另外一个 Redis 实例请求获取锁；

3.客户端通过当前时间减去步骤 1 记录的时间来计算获取锁使用的时间。当且仅当从大多数（N/2+1，这里是 3 个节点）的 Redis 节点都取到锁，并且获取锁使用的时间小于锁失效时间时，锁才算获取成功

4.如果取到了锁，其真正有效时间等于初始有效时间减去获取锁所使用的时间（步骤 3 计算的结果）

5.如果由于某些原因未能获得锁（无法在至少 N/2 + 1 个 Redis 实例获取锁、或获取锁的时间超过了有效时间），客户端应该在所有的 Redis 实例上进行解锁（即便某些Redis实例根本就没有加锁成功，防止某些节点获取到锁但是客户端没有得到响应而导致接下来的一段时间不能被重新获取锁）



此方案为了解决数据不一致的问题，直接舍弃了异步复制只使用 master 节点，同时由于舍弃了 slave，为了保证可用性，引入了 N 个节点，官方建议是 5。本次教学演示用3台实例来做说明。**客户端只有在满足下面的这两个条件时，才能认为是加锁成功。条件1：客户端从超过半数（大于等于N/2+1）的Redis实例上成功获取到了锁。条件2：客户端获取锁的总耗时没有超过锁的有效时间**

 

**容错公式**：

容错公式：N = 2X + 1  (N是最终部署机器数，X是容错机器数)。redis只支持AP，为了解决CP的风险，采用N个节点，N为奇数，上面的3个master各自完全独立，不是主从或者集群

![image-20231231152709543](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220419511.png)


 什么是容错：失败了多少个机器实例后我还是可以容忍的，所谓的容忍就是数据一致性还是可以Ok的，CP数据一致性还是可以满足。加入在集群环境中，redis失败1台，可接受。2X+1 = 2 * 1+1 =3，部署3台，死了1个剩下2个可以正常工作，那就部署3台。 加入在集群环境中，redis失败2台，可接受。2X+1 = 2 * 2+1 =5，部署5台，死了2个剩下3个可以正常工作，那就部署5台

为什么是奇数：最少的机器，最多的产出效果 加入在集群环境中，redis失败1台，可接受。2N+2= 2 * 1+2 =4，部署4台 加入在集群环境中，redis失败2台，可接受。2N+2 = 2 * 2+2 =6，部署6台

 

###  10.2  使用Redisson进行编码改造V9.0

天上飞的理念(RedLock)必然有落地的实现(Redisson)：

redisson实现：

Redisson是java的redis客户端之一，提供了一些api方便操作redis

redisson之官网：`https://redisson.org/`

redisson之Github官方文档：`https://github.com/redisson/redisson/wiki/目录`

redisson之Github：`https://github.com/redisson/redisson/wiki/%E7%9B%AE%E5%BD%95`

redisson之解决分布式锁：`https://github.com/redisson/redisson/wiki/8.-Distributed-locks-and-synchronizers`

---------------------------------------------------------------



redission快速入门官方文档：`https://github.com/redisson/redisson#quick-start`

`pom.xml`中引入依赖：

```xml
<!--redisson-->
<dependency>
    <groupId>org.redisson</groupId>
    <artifactId>redisson</artifactId>
    <version>3.13.4</version>
</dependency>
```

`RedisConfig`：配置`Redisson`实例，指定要连接的Redis服务器，设置Redis服务器的地址，设置连接的数据库索引为0，设置连接密码

```java
package com.atguigu.redislock.config;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
@Configuration
public class RedisConfig
{
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory)
    {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        //设置key序列化方式string
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化方式json
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    //单Redis节点模式
    @Bean
    public Redisson redisson()
    {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.111.175:6379").setDatabase(0).setPassword("111111");
        return (Redisson) Redisson.create(config);
    }
}
```

`InventoryController`：

```java
package com.atguigu.redislock.controller;
import com.atguigu.redislock.service.InventoryService;
import com.atguigu.redislock.service.InventoryService2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@Api(tags = "redis分布式锁测试")
public class InventoryController
{
    @Autowired
    private InventoryService inventoryService;

    @ApiOperation("扣减库存，一次卖一个")
    @GetMapping(value = "/inventory/sale")
    public String sale()
    {
        return inventoryService.sale();
    }

    @ApiOperation("扣减库存saleByRedisson，一次卖一个")
    @GetMapping(value = "/inventory/saleByRedisson")
    public String saleByRedisson()
    {
        return inventoryService.saleByRedisson();
    }
}
```

`InventoryService2`：使用`Redisson`分布式锁实现库存扣减

```java
package com.atguigu.redislock.service;

import cn.hutool.core.util.IdUtil;
import com.atguigu.redislock.mylock.DistributedLockFactory;
import com.atguigu.redislock.mylock.RedisDistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
@Service
@Slf4j
public class InventoryService2
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String port;
    @Autowired
    private DistributedLockFactory distributedLockFactory;

    @Autowired
    private Redisson redisson;
    
    public String saleByRedisson()
    {
        String retMessage = "";
        String key = "zzyyRedisLock";
        RLock redissonLock = redisson.getLock(key);
        redissonLock.lock();
        try
        {
            //1 查询库存信息
            String result = stringRedisTemplate.opsForValue().get("inventory001");
            //2 判断库存是否足够
            Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
            //3 扣减库存
            if(inventoryNumber > 0) {
                stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber;
                System.out.println(retMessage);
            }else{
                retMessage = "商品卖完了，o(╥﹏╥)o";
            }
        }finally {

          redissonLock.unlock();
        }
        return retMessage+"\t"+"服务端口号："+port;
    }
}
```

JMeter测试：在极高的并发访问场景下，解锁的时候会出现如下报错，

![image-20231231163621933](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220419210.png)

业务代码修改为V9.1版：改进点，只能删除属于自己的key，不能删除别人的

```java
package com.atguigu.redislock.service;
import cn.hutool.core.util.IdUtil;
import com.atguigu.redislock.mylock.DistributedLockFactory;
import com.atguigu.redislock.mylock.RedisDistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
@Service
@Slf4j
public class InventoryService
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${server.port}")
    private String port;
    @Autowired
    private DistributedLockFactory distributedLockFactory;

    @Autowired
    private Redisson redisson;
    public String saleByRedisson()
    {
        String retMessage = "";
        String key = "zzyyRedisLock";
        RLock redissonLock = redisson.getLock(key);
        redissonLock.lock();
        try
        {
            //1 查询库存信息
            String result = stringRedisTemplate.opsForValue().get("inventory001");
            //2 判断库存是否足够
            Integer inventoryNumber = result == null ? 0 : Integer.parseInt(result);
            //3 扣减库存
            if(inventoryNumber > 0) {
                stringRedisTemplate.opsForValue().set("inventory001",String.valueOf(--inventoryNumber));
                retMessage = "成功卖出一个商品，库存剩余: "+inventoryNumber;
                System.out.println(retMessage);
            }else{
                retMessage = "商品卖完了，o(╥﹏╥)o";
            }
        }finally {
            // 改进点，只能删除属于自己的key，不能删除别人的
            if(redissonLock.isLocked() && redissonLock.isHeldByCurrentThread())
            {
                redissonLock.unlock();
            }
        }
        return retMessage+"\t"+"服务端口号："+port;
    }
}
```

###  10.3 Redisson源码解析

Redisson源码解析：加锁、可重入、续命、解锁

分析步骤：Redis分布式锁过期了，但是业务逻辑还没处理完怎么办？还记得之前说过的缓存续命吗?

守护线程"续命”

```
额外起一个线程，定期检查线程是否还持有锁，如果有则延长过期时间
Redisson 里面就实现了这个方案，使用“看门狗”定期检查（每1/3的锁时间检查1次），如果线程还持有锁，则刷新过期时间
```

在获取锁成功后，给锁加一个watchdog,watchdog，会起一个定时任务，在锁没有被释放且快要过期的时候会续期



8.1.可重入锁(Reentrant Lock)

基于Redis的Redisson分布式可重入锁RLock Java对象实现了java.util.concurrent.locks.Lock接口。同时还提供了异步(Async) 、反射式(Reactive)和RxJava2标准的接口

```java
RLock lock = redisson.getLock("anyLock");
//最常见的使用方法
lock. lock();
```

大家都知道，如果负责储存这个分布式锁的Redisson节点宕机以后，而且这个锁正好处于锁住的状态时，这个锁会出现锁死的状态。为了避免这种情况的发生，Redisson内部提供了一个监控锁的看门狗，它的作用是在Redisson实例被关闭前，不断的延长锁的有效期。默认情况下，看门狗的检查锁的超时时间是30秒钟，也可以通过修改Config.lockWatchdogTimeout来另行指定

另外Redisson还通过加锁的方法提供了leaseTime的参数来指定加锁的时间。超过这个时间后锁便自动解开了

源码分析1：通过redisson新建出来的锁key，默认是30秒

![image-20231231164839241](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220419824.png)

源码分析2：

```
RedissonLock.java
lock()---tryAcquire()---tryAcquireAsync()---
```

![image-20231231164936962](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220419270.png)

源码分析3：

```
通过exists判断，如果锁不存在，则设置值和过期时间，加锁成功
通过hexists判断，如果锁已存在，并且锁的是当前线程，则证明是重入锁，加锁成功
如果锁已存在，但锁的不是当前线程，则证明有其他线程持有锁。返回当前锁的过期时间(代表了锁key的剩余生存时间)，加锁失败
```



![image-20231231165625895](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220419897.png)

源码分析4：

![image-20231231165824587](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220419547.png)

这里面初始化了一个定时器，dely 的时间是 internalLockLeaseTime/3。在Redisson中，internalLockLeaseTime是30s，也就是每隔10s续期一次，每次30s

![image-20231231165915781](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220420999.png)

watch dog自动延期机制：客户端A加锁成功，就会启动一个watch dog看门狗，他是一个后台线程，会每隔10秒检查一下，如果客户端A还持有锁key，那么就会不断的延长锁key的生存时间，默认每次续命又从30秒新开始

![image-20231231170019393](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220420888.png)

自动续期lua脚本分析：

![image-20231231170059256](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220432537.png)

解锁：

![image-20231231170148773](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220420653.png)

###  10.4 多机案例

**理论参考来源**：

redis之父提出了Redlock算法解决这个问题

官网：

```
Redlock实现
antirez提出的redlock算法大概是这样的:
在Redis的分布式环境中，我们假设有N个Redis master。这些节点完全互相独立，不存在主从复制或者其他集群协调机制。我们确保将在N个实例上使用与在Redis单实例下相同方法获取和释放锁。现在我们假设有5个Redis master节点，同时我们需要在5台服务器上面运行这些Redis实例，这样保证他们不会同时都宕掉

为了取到锁，客户端应该执行以下操作:
1.获取当前Unix时间，以毫秒为单位
2.依次尝试从5个实例，使用相同的key和具有唯—性的value (例如UUID）获取锁。当向Redis请求获取锁时，客户端应该设置一个网络连接和响应超时时间，这个超时时间应该小于锁的失效时间。例如你的锁自动失效时间为10秒，则超时时间应该在5-50毫秒之间。这样可以避免服务器端Redis已经挂掉的情况下，客户端还在死死地等待响应结果。如果服务器端没有在规定时间内响应，客户端应该尽快尝试去另外一个Redis实例请求获取锁
3.客户端使用当前时间减去开始获取锁时间（步骤1记录的时间)就得到获取锁使用的时间。当且仅当从大多数(N/2+1，这里是3个节点)的Redis节点都取到锁，并且使用的时间小于锁失效时间时，锁才算获取成功
4.如果取到了锁，key的真正有效时间等于有效时间减去获取锁所使用的时间（步骤3计算的结果)
5.如果因为某些原因，获取锁失败（没有在至少N/2+1个Redis实例取到锁或者取锁时间已经超过了有效时间)，客户端应该在所有的Redis实例上进行解锁(即便某些Redis实例根本就没有加锁成功，防止某些节点获取到锁但是客户端没有得到响应而导致接下来的一段时间不能被重新获取锁)
```

**小总结**：

这个锁的算法实现了多redis实例的情况，相对于单redis节点来说，优点在于 防止了 单节点故障造成整个服务停止运行的情况且在多节点中锁的设计，及多节点同时崩溃等各种意外情况有自己独特的设计方法

Redisson 分布式锁支持 MultiLock 机制可以将多个锁合并为一个大锁，对一个大锁进行统一的申请加锁以及释放锁

最低保证分布式锁的有效性及安全性的要求如下：

1.互斥；任何时刻只能有一个client获取锁

2.释放死锁；即使锁定资源的服务崩溃或者分区，仍然能释放锁

3.容错性；只要多数redis节点（一半以上）在使用，client就可以获取和释放锁

网上讲的基于故障转移实现的redis主从无法真正实现Redlock：

因为redis在进行主从复制时是异步完成的，比如在clientA获取锁后，主redis复制数据到从redis过程中崩溃了，导致没有复制到从redis中，然后从redis选举出一个升级为主redis,造成新的主redis没有clientA 设置的锁，这是clientB尝试获取锁，并且能够成功获取锁，导致互斥失效



**代码参考来源**：`https://github.com/redisson/redisson/wiki/8.-Distributed-locks-and-synchronizers`

2022年第8章第4小节：

![image-20231231171239323](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220420720.png)



![image-20231231171330198](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220432640.png)

MultiLock多重锁：

![image-20231231171437872](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220420099.png)

**案例**：

docker走起3台redis的master机器，本次设置3台master各自独立无从属关系

```shell
docker run -p 6381:6379 --name redis-master-1 -d redis
docker run -p 6382:6379 --name redis-master-2 -d redis
docker run -p 6383:6379 --name redis-master-3 -d redis
# 查看进程
docker ps
```

使用命令进入上—步刚启动的redis容器实例：

```
docker exec -it redis-master-1 /bin/bash   或者 docker exec -it redis-master-1 redis-cli
docker exec -it redis-master-2 /bin/bash   或者 docker exec -it redis-master-2 redis-cli
docker exec -it redis-master-3 /bin/bash   或者 docker exec -it redis-master-3 redis-cli

[root@zzyy ~] # docker exec - it redis-master-1 /bin/bash
root@cf745fca6d6e:/data# redis-cli-p 6379
127.0.0.1:6379> keys*
(empty array)
```

建Module：redis_redlock

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.10.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>com.atguigu.redis.redlock</groupId>
    <artifactId>redis_redlock</artifactId>
    <version>0.0.1-SNAPSHOT</version>


    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.redisson</groupId>
            <artifactId>redisson</artifactId>
            <version>3.19.1</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.8</version>
        </dependency>
        <!--swagger-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.9.2</version>
        </dependency>
        <!--swagger-ui-->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.9.2</version>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.4</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.8.11</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
    </dependencies>


    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.springframework.boot</groupId>
                            <artifactId>spring-boot-configuration-processor</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

配置：

```properties
server.port=9090
spring.application.name=redlock

spring.swagger2.enabled=true

spring.redis.database=0
spring.redis.password=
spring.redis.timeout=3000
spring.redis.mode=single

spring.redis.pool.conn-timeout=3000
spring.redis.pool.so-timeout=3000
spring.redis.pool.size=10

spring.redis.single.address1=192.168.111.185:6381
spring.redis.single.address2=192.168.111.185:6382
spring.redis.single.address3=192.168.111.185:6383
```

主启动类：

```java
package com.atguigu.redis.redlock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class RedisRedlockApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(RedisRedlockApplication.class, args);
    }
}
```

配量类：

`CacheConfiguration`：

```java
package com.atguigu.redis.redlock.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class CacheConfiguration {

    @Autowired
    RedisProperties redisProperties;

    @Bean
    RedissonClient redissonClient1() {
        Config config = new Config();
        String node = redisProperties.getSingle().getAddress1();
        node = node.startsWith("redis://") ? node : "redis://" + node;
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(node)
                .setTimeout(redisProperties.getPool().getConnTimeout())
                .setConnectionPoolSize(redisProperties.getPool().getSize())
                .setConnectionMinimumIdleSize(redisProperties.getPool().getMinIdle());
        if (StringUtils.isNotBlank(redisProperties.getPassword())) {
            serverConfig.setPassword(redisProperties.getPassword());
        }
        return Redisson.create(config);
    }

    @Bean
    RedissonClient redissonClient2() {
        Config config = new Config();
        String node = redisProperties.getSingle().getAddress2();
        node = node.startsWith("redis://") ? node : "redis://" + node;
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(node)
                .setTimeout(redisProperties.getPool().getConnTimeout())
                .setConnectionPoolSize(redisProperties.getPool().getSize())
                .setConnectionMinimumIdleSize(redisProperties.getPool().getMinIdle());
        if (StringUtils.isNotBlank(redisProperties.getPassword())) {
            serverConfig.setPassword(redisProperties.getPassword());
        }
        return Redisson.create(config);
    }

    @Bean
    RedissonClient redissonClient3() {
        Config config = new Config();
        String node = redisProperties.getSingle().getAddress3();
        node = node.startsWith("redis://") ? node : "redis://" + node;
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress(node)
                .setTimeout(redisProperties.getPool().getConnTimeout())
                .setConnectionPoolSize(redisProperties.getPool().getSize())
                .setConnectionMinimumIdleSize(redisProperties.getPool().getMinIdle());
        if (StringUtils.isNotBlank(redisProperties.getPassword())) {
            serverConfig.setPassword(redisProperties.getPassword());
        }
        return Redisson.create(config);
    }


    /**
     * 单机
     * @return
     */
    /*@Bean
    public Redisson redisson()
    {
        Config config = new Config();

        config.useSingleServer().setAddress("redis://192.168.111.147:6379").setDatabase(0);

        return (Redisson) Redisson.create(config);
    }*/

}
```

`RedisPoolProperties`：

```java
package com.atguigu.redis.redlock.config;
import lombok.Data;
@Data
public class RedisPoolProperties {

    private int maxIdle;

    private int minIdle;

    private int maxActive;

    private int maxWait;

    private int connTimeout;

    private int soTimeout;

    /**
     * 池大小
     */
    private  int size;

}
```

`RedisProperties`：

```java
package com.atguigu.redis.redlock.config;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.redis", ignoreUnknownFields = false)
@Data
public class RedisProperties {

    private int database;

    /**
     * 等待节点回复命令的时间。该时间从命令发送成功时开始计时
     */
    private int timeout;

    private String password;

    private String mode;

    /**
     * 池配置
     */
    private RedisPoolProperties pool;

    /**
     * 单机信息配置
     */
    private RedisSingleProperties single;
}
```

`RedisSingleProperties`：

```java
package com.atguigu.redis.redlock.config;
import lombok.Data;
@Data
public class RedisSingleProperties {
    private  String address1;
    private  String address2;
    private  String address3;
}
```

`RedLockController`：

```java
package com.atguigu.redis.redlock.controller;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@RestController
@Slf4j
public class RedLockController {

    public static final String CACHE_KEY_REDLOCK = "ATGUIGU_REDLOCK";

    @Autowired
    RedissonClient redissonClient1;

    @Autowired
    RedissonClient redissonClient2;

    @Autowired
    RedissonClient redissonClient3;

    boolean isLockBoolean;

    @GetMapping(value = "/multiLock")
    public String getMultiLock() throws InterruptedException
    {
        String uuid =  IdUtil.simpleUUID();
        String uuidValue = uuid+":"+Thread.currentThread().getId();

        RLock lock1 = redissonClient1.getLock(CACHE_KEY_REDLOCK);
        RLock lock2 = redissonClient2.getLock(CACHE_KEY_REDLOCK);
        RLock lock3 = redissonClient3.getLock(CACHE_KEY_REDLOCK);

        RedissonMultiLock redLock = new RedissonMultiLock(lock1, lock2, lock3);
        redLock.lock();
        try
        {
            System.out.println(uuidValue+"\t"+"---come in biz multiLock");
            try { TimeUnit.SECONDS.sleep(30); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(uuidValue+"\t"+"---task is over multiLock");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("multiLock exception ",e);
        } finally {
            redLock.unlock();
            log.info("释放分布式锁成功key:{}", CACHE_KEY_REDLOCK);
        }

        return "multiLock task is over  "+uuidValue;
    }

}
```

测试：`http://localhost:9090/multilock`

命令：

```shell
ttl ATGUIGU_REDLOCK
HGETALL ATGUIGU_REDLOCK
shutdown
docker start redis-master-1
docker exec -it redis-master-1 redis-cli
```

结论：

```
127.0.0.1: 6379> keys *
(empty array)
127.0.0.1:6379> keys *
1)"ATGUIGU_REDLOcK"
127.0.0.1:6379> type ATGUIGU_REDLOCK
hash
127.0.0.1:6379> HGETALL ATGUIGU REDLOCK
1)"9a58f830-f7d7-46e1-b434-ba6e75872e2b:39"
2) "1"
```

##  11. Redis的缓存过期淘汰策略

**相关面试题**：

​     1.生产上redis内存设置多少?

​     2.如何配置、修改redis的内存大小？

​     3.如果内存满了你怎么办？

​     4.redis清理内存的方式？定期删除和惰性删除了解过吗？

​     5.redis缓存淘汰策略有哪些？分别是什么？你用那个？

​    6.redis的LRU了解过吗?请手写LRU

​    7.lru和lfu算法的区别是什么: LRU means Least Recently Used、LFU means Least Frequently Used

###  11.1 最大内存

**Redis最大内存配置**：

打开redis配置文件，设置maxmemory参数，maxmemory是bytes字节类型，注意转换。如果不设置最大内存大小或者设置最大内存大小为0,在64位操作系统下不限制内存大小,在32位操作系统下最多使用3GB。内存注意，在64bit系统下，maxmemory设置为0表示不限制Redis内存使用。—般推荐Redis设置内存为最大物理内存的四分之三

```shell
#maxmemory <bytes>
maxmemory 104857600
#MAXMEMORY POLICY: how Redis will select what to remove when maxmemory
# is reached. You can select one from the following behaviors:
```

修改redis最大内存的两种方式：通过修改配置文件中的`maxmemory`参数、通过`config set maxmemory`命令修改(重启后失效)

```shell
# 通过config set maxmemory命令修改最大内存
127.0.0.1:6379> config set maxmemory 104857600
OK
127.0.0.1:6379> config get maxmemory
1)"maxmemory"
2)"104857600"
```

查看redis内存使用情况：

```shell
info memory
config get maxmemory
```

如果Redis内存使用超出了设置的最大值会怎样？修改配置，故意把最大值设为1个byte试试

```shell
# maxmemory <bytes>
maxmemory 1
```

```
127.0.0.1:6379> config get maxmemory
1) "maxmemory"
2) "1"
127.0.0.1:6379> set k1 v11111
(error) 00M command not allowed when used memory > ' maxmemory'
```

结论：设置了maxmemory的选项，假如redis内存使用达到上限没有加上过期时间就会导致数据写满maxmemory，出现OOM的情况。为了避免类似情况，引出下—章内存淘汰策略

###  11.2 过期键删除策略

redis过期键的删除：如果一个键设置了过期时间，到了过期时间之后是不是立即就从内存中删除呢？当然不是！立即删除几乎是不可能的，要保证立即删除的话需要CPU不停地去检测这些键的过期状态。下面介绍三种过期键删除策略

**立即删除**：Redis不可能时时刻刻遍历所有被设置了生存时间的key，来检测数据是否已经到达过期时间，然后对它进行删除。立即删除能保证内存中数据的最大新鲜度，因为它保证过期键值会在过期后马上被删除，其所占用的内存也会随之释放。但是立即删除对cpu是最不友好的。因为删除操作会占用cpu的时间，如果刚好碰上了cpu很忙的时候，比如正在做交集或排序等计算的时候，就会给cpu造成额外的压力，这会产生大量的性能消耗，同时也会影响数据的读取操作。总结，立即删除对CPU不友好，用处理器性能换取存储空间(拿时间换空间)

**惰性删除**：数据到达过期时间，不做处理。等下次访问该数据时，如果未过期，返回数据。发现已过期，删除，返回不存在。惰性删除策略的缺点是，它对内存是最不友好的。如果一个键已经过期，而这个键又仍然保留在redis中，那么只要这个过期键不被删除，它所占用的内存就不会释放。在使用惰性删除策略时，如果数据库中有非常多的过期键，而这些过期键又恰好没有被访问到的话，那么它们也许永远也不会被删除(除非用户手动执行FLUSHDB)，我们甚至可以将这种情况看作是一种内存泄漏–无用的垃圾数据占用了大量的内存，而服务器却不会自己去释放它们，这对于运行状态非常依赖于内存的Redis服务器来说,肯定不是一个好消息。总结，惰性删除对memory不友好，用存储空间换取处理器性能（拿空间换时间)

**定期删除**：上面两种方案都走极端。定期删除策略是前两种策略的折中，它会定期抽样key，判断是否过期，过期则删除。定期删除策略每隔一段时间执行一次删除过期键操作并通过限制删除操作执行时长和频率来减少删除操作对CPU时间的影响。周期性轮询redis库中的时效性数据，采用随机抽取的策略，利用过期数据占比的方式控制删除频度。**特点1**：CPU性能占用设置有峰值，检测频度可自定义设置。**特点2**：内存压力不是很大，长期占用内存的冷数据会被持续清理。总结：周期性抽查存储空间(随机抽查，重点抽查)。 举例，redis默认每隔100ms检查是否有过期的key，有过期key则删除。注意：redis不是每隔100ms将所有的key检查一次而是随机抽取进行检查(如果每隔100ms,全部key进行检查，redis直接进去ICU)。因此，如果只采用定期删除策略，会导致很多key到时间没有删除。 定期删除策略的难点是确定删除操作执行的时长和频率：如果删除操作执行得太频繁或者执行的时间太长，定期删除策略就会退化成立即删除策略，以至于将CPU时间过多地消耗在删除过期键上面。如果删除操作执行得太少，或者执行的时间太短，定期删除策略又会和惰性删除束略一样，出现浪费内存的情况。因此，如果采用定期删除策略的话，服务器必须根据情况，合理地设置删除操作的执行时长和执行频率

**过期键遗漏问题**：生产上肯定不能采用立即删除的方案，但是采用定期删除策略和惰性删除策略的话，会导致一些过期键一直没有被删除。没有被删除的原因就是定期删除时，有的过期键从来没有被抽查到，惰性删除时，有的过期键也一直没有被使用到。如果过期间一直没有被删除，大量过期的key堆积在内存中，导致redis内存空间紧张或者很快耗尽。必须要有一个更好的兜底方案来清楚这些过期键，所以redis缓存淘汰策略就出现了

###  11.3  redis缓存淘汰策略

 **redis缓存淘汰策略配置**：redis配置文件`redis.conf`中`【MEMORY MANAGEMENT】`下的 `maxmemory-policy` 

```shell
############################## MEMORY MANAGEMENT ################################
# MAXMEMORY POLICY: how Redis will select what to remove when maxmemory
# is reached. You can select one from the following behaviors:
#
# volatile-lru -> Evict using approximated LRU, only keys with an expire set.
# allkeys-lru -> Evict any key using approximated LRU.
# volatile-lfu -> Evict using approximated LFU, only keys with an expire set.
# allkeys-lfu -> Evict any key using approximated LFU.
# volatile-random -> Remove a random key having an expire set.
# allkeys-random -> Remove a random key, any key.
# volatile-ttl -> Remove the key with the nearest expire time (minor TTL)
# noeviction -> Don't evict anything, just return an error on write operations.
#
# LRU means Least Recently Used
# LFU means Least Frequently Used
#
# Both LRU, LFU and volatile-ttl are implemented using approximated
# randomized algorithms.
#
# Note: with any of the above policies, when there are no suitable keys for
# eviction, Redis will return an error on write operations that require
# more memory. These are usually commands that create new keys, add data or
# modify existing keys. A few examples are: SET, INCR, HSET, LPUSH, SUNIONSTORE,
# SORT (due to the STORE argument), and EXEC (if the transaction includes any
# command that requires memory).
#
# The default is:
#
# maxmemory-policy noeviction

# LRU, LFU and minimal TTL algorithms are not precise algorithms but approximated
# algorithms (in order to save memory), so you can tune it for speed or
# accuracy. By default Redis will check five keys and pick the one that was
# used least recently, you can change the sample size using the following
# configuration directive.
#
# The default of 5 produces good enough results. 10 Approximates very closely
# true LRU but costs more CPU. 3 is faster but not very accurate.
```

**LRU和LFU算法比较**：

LRU：最近最少使用页面置换算法，淘汰**最长时间未被使用**的页面，看页面最后一次被使用到发生调度的时间长短，首先淘汰最长时间未被使用的页面

LFU：最近最不常用页面置换算法，淘汰**一定时期内被访问次数最少**的页面，看一定时间段内页面被使用的频率，淘汰一定时期内被访问次数最少的页面

对比：假设某次时期Time为10分钟,如果每分钟进行一次调页,主存块为3,若所需页面走向为2 1 2 1 2 3 4。到页面4时会发生缺页中断。若按LRU算法,应换页面1(1页面最久未被使用)，但按LFU算法应换页面3(十分钟内,页面3只使用了一次)。可见LRU关键是看页面最后一次被使用到发生调度的时间长短,而LFU关键是看一定时间段内页面被使用的频率！

**redis的八种内存淘汰策略**：

1.noeviction(默认策略)：不会驱逐任何key，表示即使内存达到上限也不进行置换，所有能引起内存增加的命令都会返回error

2.alkeys-lru：对所有key使用LRU算法进行删除，优先删除掉最近最不经常使用的key，用以保存新数据

3.volatile-lru：对所有设置了过期时间的key使用LRU算法进行删除

4.alkleys-random：对所有key随机删除

5.volatile-random：对所有设置了过期时间的key随机删除

6.volatile-ttl：删除马上要过期的key

7.alkeys-lfu：对所有key使用LFU算法进行删除

8.volatile-lfu：对所有设置了过期时间的key使用LFU算法进行删除

归纳：

- 2种筛选方式：过期键中筛选、所有键中筛选
- 4种删除方式：LRU、LFU、random、ttl
- `2种筛选方式` * `4种删除方式` 的组合就完全涵盖了上面的几种内存淘汰策略



**内存淘汰策略的选择**：

- 如果所有的key都是最近最经常使用，那么就需要选择`allkeys-lru`进行置换最近最不经常使用的key，如果不确定使用哪种策略，那么推荐使用`allkeys-lru`
- 如果所有的key的访问概率都是差不多的，那么可以选用`allkeys-random`策略去置换数据
- 如果对数据有足够的了解，能够为key指定hint(通过`expire/ttl`指定)，那么可以选`volatile-ttl`进行置换

**内存淘汰策略配置**：

配置方法：直接使用config命令 或者 直接修改redis.conf配置文件

redis缓存淘汰策略配置性能建议：避免存储bigkey、开启惰性淘汰`lazyfree-lazy-eviction=yes`

##  12. Redis经典五大类型源码及底层实现

###  12.1 redis源码总览

REmote DIctionary Server(Redis)是一个由Salvatore Sanflippo写的key-value存储系统，是跨平台的非关系型数据库。Redis 是一个开源的使用ANSI C语言编写、遵守BSD协议、支持网络、可基于内存、分布式、可选持久性的键值对(Key-Value)存储数据库，并提供多种语言的API

源码分析参考书推荐：《Redis设计与实现》、《Redis5设计与源码分析》

redis源码仓库：`https://github.com/redis/redis`

**redis的Github官网源码说明**：

- `t_hash.c` ,`t_list.c `,`t_set.c` ,`t_string.c` ,` t_zset.c` and `t_stream.c` contains the implementation of the Redis data types.They implement both an APl to access a given data type, and the client command implementations for these data types

- `ae.c` implements the Redis event loop, it's a self contained library which is simple to read and understand.`sds.c` is the Redis string library, check `http://github.com/antirez/sds` for more information.

- `anet.c` is a library to use POSIX networking in a simpler way compared to the raw interface exposed by thekernel

- `dict.c` is an implementation of a non-blocking hash table which rehashes incrementally

- `scripting.c` implements Lua scripting. lt is completely self-contained and isolated from the rest of the Redis implementation and is simple enough to understand if you are familiar with the Lua APl

- `cluster.c` implements the Redis Cluster.Probably a good read only after being very familiar with the rest of theRedis code base. lf you want to read `cluster.c` make sure to read the Redis Cluster specification



**redis源码骨架及核心源码文件**：

- **Redis基本的数据结构**：Redis对象`object.c`、字符串`t_string.c`、列表`t_list.c`、字典`t_hash.c`、集合及有序集合`t_set.c`和`t_zset.c`  、 数据流`t_stream.c`(Streams的底层实现结构`listpack.c`和`rax.c`)、简单动态字符串`sds.c`、整数集合`intset.c`、压缩列表`ziplist.c`、快速链表`quicklist.c`、紧凑列表`listpack.c`、字典`dict.c`

- **Redis数据库的实现**：数据库的底层实现`db.c`、持久化`rdb.c`和`aof.c`
- **Redis服务端和客户端实现**：事件驱动`ae.c`和`ae_epoll.c`、网络连接`anet.c`和`networking.c`、服务端程序`server.c`、客户端程序`redis-cli.c`
- **其他**：主从复制`replication.c`、哨兵`sentinel.c`、集群`cluster.c`、其他数据结构，如`hyperloglog.c`、`geo.c`等、其他功能，如`pub/sub`、`Lua`脚本



redis源码查看：源码路径`redis-7.0.5\src`

![image-20240103022611715](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220420241.png)

redis源码相关面试题：

   1.redis的跳跃列表了解吗? 这个数据结构有什么缺点?
   2.redis项自里面怎么用?  redis的数据结构都了解哪些? 布隆过滤器怎么用?
   3.redis的多路io复用如何理解，为什么单线程还可以抗那么高的qps
   4.redis的zset底层实现，说了压缩列表和跳表，问这样设计的优缺点，只说了优点，缺点没说出来
   5.redis的zset用的什么数据结构



###  12.2 10大数据类型 

redis10大数据类型说明：

```
传统的5大类型：   String、List、Hash、Set、ZSet
新介绍的5大类型：
                bitmap：实质String
                hyperLogLog：实质String
                GEO：实质Zset
                Stream：实质Stream
                BITFIELD：看具体key。BITFIELD命令可以将一个Redis字符串看作是一个由二进制位组成的数组并对这个数组中任意偏移进行访问
```

redis是key-value存储系统。key一般都是String类型的字符串对象。**value类型则为redis对象(redisObject)**。value可以是字符串对象，也可以是集合数据类型的对象，比如List对象、Hash对象、Set对象和Zset对象

![image-20240103031546147](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220420296.png)

Redis定义了redisObject结构体来表示string、hash、list、set、zset等数据类型



![image-20240103032226169](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220420858.png)

### 12.3 redis对象redisObject

Redis定义了redisObject结构体来表示string、hash、list、set、zset等数据类型

#####  12.3.1 C语言结构体 | typedef关键字 | void关键字

 **struct结构体**：在C语言中，struct用于自定义一个新的复合数据类型。struct 语句可以定义一个包含多个成员的新的数据类型。struct语句的格式如下：

```c
struct type_name{
    member_type1 member_name1;
    member_type2 member_name2;
    member_type3 member_name3;
} object_names;
```

type_name是结构体类型的名称，`member_type1` `member_name1`是标准的变量定义，比如 int i;或者float f,或者其他有效的变量定义。在接构定义的末尾，最后一个分号之前，您可以指定一个或多个结构变量，这是可选的。下面是声明一个结构体类型Books，变量为book：

```c
struct Books{
char title[50];
char author[50];
char subject[100];
int book_id;
}book;
```



**typedef关键字**：下面是一种更简单的定义结构的方式，可以利用typedef关键字为创建的类型取一个"别名"

```c
typedef struct Books{
char title[50];
char author[50];
char subject[100];
int  book_id;
}Books;
```

上面利用typedef关键字为创建的Books类型取了一个"别名"Books，所以可以直接使用Books来定义Books类型的变量，而不需要再使用struct关键字：

```c
Books Book1，Book2;
```



**void关键字**：`void` 是 C 语言中的一个关键字，用于表示"无类型"。在函数声明和定义中，`void` 用于指示函数不返回任何值。例如，`void` 可以用来声明一个没有返回值的函数：

```c
// 这表示 `myFunction` 函数不返回任何值
void myFunction() {
    // 函数体
}
```

在其他上下文中，**`void` 还可用于指示指针不指向任何特定的类型**。在 C 语言中，`void *` 是一种特殊类型的指针，它可以指向任何数据类型，因为它表示"指向不确定类型"的指针。这在编写泛型代码或需要处理不同类型的指针时非常有用。例如，可以通过 `void *` 来实现某种形式的泛型数组、链表或其他数据结构。然而，由于 `void *` 指针不指向任何特定类型，因此在使用它时需要小心确保在调用时将其正确转换为所需的类型



#####  12.3.2  dictEntry结构 | redisObject结构

Redis中每个对象都是一个redisObject结构。每个键值对都会有一个dictEntry

**dictEntry**：在 Redis 中，`dictEntry` 用于表示字典中的一个键值对。每个键值对都会有一个dictEntry

- `key`: 用于存储键的指针
- `v`: 一个联合体，根据具体情况存储值的指针，64 位的无符号整数值或者 64 位的有符号整数值
- `next`: 一个指向下一个 `dictEntry` 结构的指针，用于解决哈希冲突时形成链表

每个 `dictEntry` 结构实际上代表着字典中的一个键值对，其内部结构的简洁性以及哈希表的实现方式，为 Redis 提供了高效的数据查找和插入操作。`dictEntry`的定义如下：

```c
// 源码位置：‪redis-7.0.5\src\dict.h
typedef struct dictEntry {
    void *key;
    union {
        void *val;
        uint64_t u64;
        int64_t s64;
        double d;
    } v;
    struct dictEntry *next;     /* Next entry in the same hash bucket. */
    void *metadata[];           /* An arbitrary number of bytes (starting at a
                                 * pointer-aligned address) of size as returned
                                 * by dictType's dictEntryMetadataBytes(). */
} dictEntry;
typedef struct dict dict;

// dictEntry表示哈希表节点的结构。存放了void *key和void *value指针
// *key指向String对象，
// *value既能指向String对象也能指向集合类型的对象(比如List+Hash +Set+Zset对象)
// 备注: void *key和void *value指针指向的是内部抽象的Redis对象,Redis中的每个对象都由redisObject构成
```

**redisObject**：`redisObject` 是 Redis 中用于表示数据库中的键和值的数据结构。它的定义如下所示：

- `type`: 用于表示对象的类型，比如字符串、列表、集合、有序集合等
- `encoding`: 用于表示对象的编码方式，比如 int、embstr、raw 等
- `lru`: 表示对象的最近最少使用时间，用于在内存不足时进行淘汰对象
- `refcount`: 表示对象的引用计数，用于在共享对象时进行内存管理
- `ptr`: 指向实际存储值的指针，具体类型和内容会根据 `type` 和 `encoding` 的取值而变化

`redisObject` 结构体的灵活性使得 Redis 能够存储多种类型的数据，并根据需要动态调整其内部表示，从而提高内存利用率和整体性能  

```c
// 源码位置：‪redis-7.0.5\src\server.h
typedef struct redisObject {
    unsigned type:4;
    unsigned encoding:4;
    unsigned lru:LRU_BITS; /* LRU time (relative to global lru_clock) or
                            * LFU data (least significant 8 bits frequency
                            * and most significant 16 bits access time). */
    int refcount;
    void *ptr; // 底层数据结构：SDS、双向链表、压缩列表、哈希表、跳表、整数集合等     
} robj;
```

redisObject +Redis数据类型+Redis所有编码方式（底层实现)三者之间的关系：

![image-20240105014648534](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220420977.png)

重点：从dictEntry到RedisObject，这些键值对是如何保存进Redis并进行读取操作，O(1)复杂度

![image-20240105014222641](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220420114.png)



###   12.4 五大数据结构总览

redis数据类型与数据结构大纲：

```
1.SDS动态字符串
2.双向链表
3.压缩列表ziplist
4.哈希表hashtable
5.跳表skiplist
6.整数集合intset
7.快速列表quicklist
8.紧凑列表listpack
```

redis6，redis7数据类型与数据结构之间的关系：

```
redis6相关的底层模型和结构：
        String = SDS
        Set = intset + hashtable
        ZSet = skipList + zipList
        List = quicklist + zipList
        Hash = hashtable + zipList
redis7相关的底层模型和结构：
        String = SDS
        Set = intset + hashtable
        ZSet = skipList + listpack紧凑列表
        List = quicklist
        Hash = hashtable + listpack紧凑列表
```



![image-20240106210441946](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220420731.png)



redis7新特性之数据结构新特性：listpack紧凑列表调整。listpack是用来替代ziplist的新数据结构，在7.0版本已经没有ziplist 的配置了(6.0版本仅部分数据类型作为过渡阶段在使用）listpack已经替换了ziplist类似 hash-max-ziplist-entries的配置

源码分析总体数据结构大纲：

![image-20240105015437125](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220420081.png)

###  12.5  底层编码类型映射 | DEBUG OBJECT key命令

底层数据编码类型定义(利用数字0-11来表示对应的数据编码类型)：

![image-20240106220415610](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220420328.png)

```c
// 源码位置：redis-7.0.5\src\server.h
/* Objects encoding. Some kind of objects like Strings and Hashes can be
 * internally represented in multiple ways. The 'encoding' field of the object
 * is set to one of this fields for this object. */
#define OBJ_ENCODING_RAW 0     /* Raw representation */
#define OBJ_ENCODING_INT 1     /* Encoded as integer */
#define OBJ_ENCODING_HT 2      /* Encoded as hash table */
#define OBJ_ENCODING_ZIPMAP 3  /* No longer used: old hash encoding. */
#define OBJ_ENCODING_LINKEDLIST 4 /* No longer used: old list encoding. */
#define OBJ_ENCODING_ZIPLIST 5 /* No longer used: old list/hash/zset encoding. */
#define OBJ_ENCODING_INTSET 6  /* Encoded as intset */
#define OBJ_ENCODING_SKIPLIST 7  /* Encoded as skiplist */
#define OBJ_ENCODING_EMBSTR 8  /* Embedded sds string encoding */
#define OBJ_ENCODING_QUICKLIST 9 /* Encoded as linked list of listpacks */
#define OBJ_ENCODING_STREAM 10 /* Encoded as a radix tree of listpacks */
#define OBJ_ENCODING_LISTPACK 11 /* Encoded as a listpack */
```



每个键值对都会有一个dictEntry。以`set hello word`为例，因为Redis是KV键值对的数据库，每个键值对都会有一个dictEntry(源码位置：dict.h)，里面指向了key和value的指针，next 指向下一个 dictEntry，key 是字符串，但是 Redis 没有直接使用 C 的字符数组，而是存储在redis自定义的 SDS中，value 既不是直接作为字符串存储，也不是直接存储在 SDS 中，而是存储在redisObject 中。实际上五种常用的数据类型的任何一种，都是通过 redisObject 来存储的



![image-20240105020116781](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220420779.png)

redisObjec结构的作用：为了便于操作，Redis采用redisObjec结构来统一五种不同的数据类型，这样所有的数据类型就都可以以相同的形式在函数间传递而不用使用特定的类型结构。同时，为了识别不同的数据类型，redisObjec中定义了type和encoding字段对不同的数据类型加以区别。简单地说，redisObjec就是string、hash、list、set、zset的父类，可以在函数间传递时隐藏具体的类型信息，所以作者抽象了redisObjec结构来到达同样的目的

![image-20240105020341037](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220421647.png)

RedisObject各字段的含义：

- 4位的type表示具体的数据类型
- 4位的encoding表示该类型的物理编码方式见下表，同一种数据类型可能有不同的编码方式。(比如String就提供了3种：int、embstr、raw)
- lru字段表示当内存超限时采用LRU算法清除内存中的对象
- refcount表示对象的引用计数
- ptr指针指向真正的底层数据结构的指针

![image-20240105020540988](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220421588.png)

案例：set age 17

```shell
127.0.0.1:6379> set hello world
0K
127.0.0.1:6379> get hello
"world"
127.0.0.1:6379> type hello
string
127.0.0.1:6379> 0BJECT ENCODING hello
"embstr"
127.0.0.1: 6379> set age 17
OK
127.0.0.1:6379> type age
string
127.0.0.1:6379> OBJECT ENCODING age
"int"
```

**各个类型的数据结构的编码映射和定义**：

```c
// redis-7.0.5\src\object.c
char *strEncoding(int encoding) {
    switch(encoding) {
    case OBJ_ENCODING_RAW: return "raw";
    case OBJ_ENCODING_INT: return "int";
    case OBJ_ENCODING_HT: return "hashtable";
    case OBJ_ENCODING_QUICKLIST: return "quicklist";
    case OBJ_ENCODING_LISTPACK: return "listpack";
    case OBJ_ENCODING_INTSET: return "intset";
    case OBJ_ENCODING_SKIPLIST: return "skiplist";
    case OBJ_ENCODING_EMBSTR: return "embstr";
    case OBJ_ENCODING_STREAM: return "stream";
    default: return "unknown";
    }
}
```

**`DEBUG OBJECT key`命令**：

`DEBUG OBJECT` 命令用于检索给定键的调试信息，可以用来查看键的编码方式、对象类型和相关统计信息。命令语法如下：

```shell
# 说明：当key存在时，返回有关信息。当key不存在时，返回一个错误
127.0.0.1:6379> DEBUG OBJECT key
```

其中 `<key>` 是要调试的键的名称。调用这个命令时，Redis 将返回关于指定键的调试信息，包括键的编码方式、对象类型和其他相关统计信息

```shell
# DEBUG OBJECT key 命令使用案例
127.0.0.1:6379> set age 17
oK
127.0.0.1:6379> type age
string
127.0.0.1:6379> 0BJECT ENCODING age
"int"
127.0.0.1:6379> DEBUG object age
(error)ERR DEBUG command not allowed.If the enable-debug-command option is set to "local" , you can run it from a local comection,otherwise you need to set this option in the configuration file, and then restart the server
# 报错原因：DEBUG 命令在当前的 Redis 服务配置中被禁用了
# 解决方法一：通过配置文件进行设置，打开Redis的配置文件redis.conf将enable-debug-command设为 "local"并重启Redis服务
127.0.0.1: 6379> config get enable-debug-command
1) "enable-debug-command"
2) "no"
```

通常DEBUG命令是被禁用的，执行DEBUG命令时会出现报错，需要更改配置才能使用该命令。打开Redis的配置文件redis.conf将enable-debug-command设为 "local"并重启Redis服务：

```shell
# 修改配置，启用DEBUG OBJECT命令
# enable-protected-configs no
# enable-debug-command no
enable-debug-command local 
# enable-module-command no
```

启用DEBUG OBJECT命令后即可执行DEBUG object命令

```shell
# 启用DEBUG OBJECT命令后即可执行DEBUG object命令
127.0.0.1:6379> DEBUG object age
Value at: 0x7f236a8c4200 refcount:2147483647 encoding:int serializedlength:2 lru:14199771 lru_seconds_idle:14
```

DEBUG OBJECT命令返回参数含义解析：

```
Value at:            内存地址
refcount:            引用次数
encoding:            物理编码类型
serializedlength:    序列化后的长度（注意这里的长度是序列化后的长度，保存为rdb文件时使用了该算法，不是真正存贮在内存的大小),会对字串做一些可能的压缩以便底层优化
lru：                记录最近使用时间戳
lru_seconds_idle：   空闲时间
```

###  12.6 String数据结构介绍

#####  12.6.1  string底层对应的3大物理编码

案例演示：都是string类型，但是底层编码不一样

```shell
# 案例演示：都是string类型，但是底层编码不一样，一个是int，一个是embstr
127.0.0.1:6379> set k1 v1
0K
127.0.0.1:6379> set age 17
oK
127.0.0.1:6379> type k1
string
127.0.0.1:6379> type age
string
127.0.0.1:6379> 0BJECT ENCODING k1
"embstr"
127.0.0.1:6379> 0BJECT ENCODING age
"int"
```

redisObject结构体回顾：

```c
// 源码位置：‪redis-7.0.5\src\server.h
typedef struct redisObject {
    unsigned type:4;   // 当前值对象的数据类型
    unsigned encoding:4;  // 当前值对象底层存储的编码类型
    unsigned lru:LRU_BITS; /* LRU time (relative to global lru_clock) or
                            * LFU data (least significant 8 bits frequency
                            * and most significant 16 bits access time). */
    // 采用LRU算法清除内存中的对象
    int refcount;  // 记录对象引用次数
    void *ptr; // 指向真正的底层数据结构的指针(底层数据结构：SDS、双向链表、压缩列表、哈希表、跳表、整数集合等)
} robj;
```

![image-20240107154439540](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220421454.png)

**string类型底层编码之int**：int编码类型：保存long型(长整型)的64位(8个字节)有符号整数，long数据类型是64位、有符号的以二进制补码表示的整数，最小值是-9,223,372,036,854,775,808 ( -2^63)，最大值是9,223,372,036,854,775,807 (2^63 -1)，这种类型主要使用在需要比较大整数的系统上，默认值是0L，数字最多19位。补充：只有整数才会使用int，如果是浮点数，Redis。内部其实先将浮点数转化为字符串值，然后再保存

**string类型底层编码之embstr**：代表embstr格式的**SDS(Simple Dynamic String简单动态字符串)**,保存长度小于44字节的字符串。EMBSTR顾名思义即:embedded string，表示嵌入式的String

**string类型底层编码之raw**：保存长度大于44字节的字符串

3大物理编码案例：

![image-20240105024703531](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220421503.png)

3大物理编码演示：

```shell
127.0.0.1:6379> set k3 123
OK
127.0.0.1:6379> object encoding k3
"int"
127.0.0.1:6379> set k3 abc
OK
127.0.0.1:6379> object encoding k3
"embstr"
127.0.0.1:6379>set k3 abcdefghijklmnopqrstuvwxyzabcdeffasdffsdaadsx
oK
127.0.0.123T9> object encoding k3
"raw"
```

INT编码格式：

```shell
127.0.0.1:6379> set k3 123
OK
127.0.0.1:6379> object encoding k3
"int"
```

#####  12.6.2  SDS简单动态字符串

SDS简单动态字符串官网说明：`https://github.com/antirez/sds`

Redis没有直接复用C语言的字符串，而是新建了属于自己的结构SDS，SDS就是Redis中字符串的实现。在Redis数据库里，包含字符串值的键值对都是由SDS实现的。Redis中所有的键都是由字符串对象实现的即底层是由SDS实现，Redis中所有的值对象中包含的字符串对象底层也是由SDS实现

![image-20240107163327940](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220421439.png)



**SDS简单动态字符串定义源码**：SDS有多种结构, 用于存储不同的长度的字符串。`sdshdr5  (2^5=32byte)`、`sdshdr8  (2^8=256byte)`、`sdshdr16 (2^16=65536byte=64KB)`、`sdshdr32 (2^32byte=4GB)`、`sdshdr64，2的64次方byte＝17179869184G` 

```c
// 源码位置：redis-7.0.5\src\sds.h
typedef char *sds;

/* Note: sdshdr5 is never used, we just access the flags byte directly.
 * However is here to document the layout of type 5 SDS strings. */
struct __attribute__ ((__packed__)) sdshdr5 {
    unsigned char flags; /* 3 lsb of type, and 5 msb of string length */
    char buf[];
};
struct __attribute__ ((__packed__)) sdshdr8 {
    uint8_t len; /* used */
    uint8_t alloc; /* excluding the header and null terminator */
    unsigned char flags; /* 3 lsb of type, 5 unused bits */
    char buf[];
};
struct __attribute__ ((__packed__)) sdshdr16 {
    uint16_t len; /* used */
    uint16_t alloc; /* excluding the header and null terminator */
    unsigned char flags; /* 3 lsb of type, 5 unused bits */
    char buf[];
};
struct __attribute__ ((__packed__)) sdshdr32 {
    uint32_t len; /* used */
    uint32_t alloc; /* excluding the header and null terminator */
    unsigned char flags; /* 3 lsb of type, 5 unused bits */
    char buf[];
};
struct __attribute__ ((__packed__)) sdshdr64 {
    uint64_t len; /* used */
    uint64_t alloc; /* excluding the header and null terminator */
    unsigned char flags; /* 3 lsb of type, 5 unused bits */
    char buf[];
};
```

**SDS简单动态字符串参数解析**：`len` 字符串长度、`alloc` 分配的空间长度、`flags` sds类型、`buf[]`字节数组。`len`表示SDS 的长度，获取字符串长度的时候可以在 O(1)情况下拿到长度len，而不是像C那样需要遍历一遍字符串。`alloc` 可以用来计算字符串已经分配的未使用的空间，有了这个值就可以引入预分配空间的算法了，而不用去考虑内存分配的问题。`buf` 表示字符串数组，存储具体的字符串数据

![image-20240107164024266](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220421918.png)



**Redis重新设计一个SDS 数据结构的原因**：C语言没有Java里面的String类型，只能是靠自己的char[]来实现，按照字符串在 C 语言中的存储方式，想要获取 「Redis」的长度，需要从头开始遍历，直到遇到 '\0' 为止。所以，Redis 没有直接使用 C 语言传统的字符串标识，而是自己构建了一种名为简单动态字符串 SDS（simple dynamic string）的抽象类型，并将 SDS 作为 Redis 的默认字符串。SDS数据结构相较于C语言的char[]做了如下优化

|                    | C语言                                                        | SDS                                                          |
| ------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **字符串长度处理** | 需要从头开始遍历，直到遇到 '\0' 为止，时间复杂度O(N)         | 记录当前字符串的长度，直接读取即可，时间复杂度 O(1)          |
| **内存重新分配**   | 分配内存空间超过后，会导致数组下标越级或者内存分配溢出       | **空间预分配**：SDS 修改后，len 长度小于 1M，那么将会额外分配与 len 相同长度的未使用空间。如果修改后长度大于 1M，那么将分配1M的使用空间。**惰性空间释放**：有空间分配对应的就有空间释放。SDS 缩短时并不会回收多余的内存空间，而是使用 free 字段将多出来的空间记录下来。如果后续有变更操作，直接使用 free 中记录的空间，减少了内存的分配 |
| **二进制安全**     | 二进制数据并不是规则的字符串格式，可能会包含一些特殊的字符，比如 '\0' 等。前面提到过，C中字符串遇到 '\0' 会结束，那 '\0' 之后的数据就读取不上了 | 根据 len 长度来判断字符串结束的，二进制安全的问题就解决了    |

Redis重新设计了一个名为SDS（Simple Dynamic Strings，简单动态字符串）的数据结构，这是因为SDS具有以下几个优点：

1. **灵活性**：SDS可以根据需要动态调整大小，因此适用于存储任意长度的字符串数据，并且在变化时能够高效地进行内存重分配
2. **二进制安全**：SDS对字符编码没有限制，因此可以存储任意类型的数据，包括文本和二进制数据，而不会受到字符串终结符的限制
3. **O(1)时间复杂度的长度计算**：SDS内部记录了字符串的长度，因此无需重新遍历字符串来获取其长度，从而能够在O(1)时间内获取字符串长度
4. **缓冲区溢出保护**：Redis 在对 SDS 进行扩展时，能够对新空间进行合理的扩充，防止数据溢出   

由于这些优点，Redis选择重新设计一个SDS数据结构来满足对字符串操作的需求

#####  12.6.3 set命令源码分析

string类型的set命令底层源码实现

```c
// set命令的底层调用
// 源码位置：redis-7.0.5\src\t_string.c

/* SET key value [NX] [XX] [KEEPTTL] [GET] [EX <seconds>] [PX <milliseconds>]
 *     [EXAT <seconds-timestamp>][PXAT <milliseconds-timestamp>] */
void setCommand(client *c) {
    robj *expire = NULL;
    int unit = UNIT_SECONDS;
    int flags = OBJ_NO_FLAGS;

    if (parseExtendedStringArgumentsOrReply(c,&flags,&unit,&expire,COMMAND_SET) != C_OK) {
        return;
    }

    c->argv[2] = tryObjectEncoding(c->argv[2]);
    setGenericCommand(c,flags,c->argv[1],c->argv[2],expire,unit,NULL,NULL);
}



void setGenericCommand(client *c, int flags, robj *key, robj *val, robj *expire, int unit, robj *ok_reply, robj *abort_reply) {
    long long milliseconds = 0; /* initialized to avoid any harmness warning */
    int found = 0;
    int setkey_flags = 0;

    if (expire && getExpireMillisecondsOrReply(c, expire, flags, unit, &milliseconds) != C_OK) {
        return;
    }

    if (flags & OBJ_SET_GET) {
        if (getGenericCommand(c) == C_ERR) return;
    }

    found = (lookupKeyWrite(c->db,key) != NULL);

    if ((flags & OBJ_SET_NX && found) ||
        (flags & OBJ_SET_XX && !found))
    {
        if (!(flags & OBJ_SET_GET)) {
            addReply(c, abort_reply ? abort_reply : shared.null[c->resp]);
        }
        return;
    }

    setkey_flags |= (flags & OBJ_KEEPTTL) ? SETKEY_KEEPTTL : 0;
    setkey_flags |= found ? SETKEY_ALREADY_EXIST : SETKEY_DOESNT_EXIST;

    setKey(c,c->db,key,val,setkey_flags);
    server.dirty++;
    notifyKeyspaceEvent(NOTIFY_STRING,"set",key,c->db->id);

    if (expire) {
        setExpire(c,c->db,key,milliseconds);
        /* Propagate as SET Key Value PXAT millisecond-timestamp if there is
         * EX/PX/EXAT/PXAT flag. */
        robj *milliseconds_obj = createStringObjectFromLongLong(milliseconds);
        rewriteClientCommandVector(c, 5, shared.set, key, val, shared.pxat, milliseconds_obj);
        decrRefCount(milliseconds_obj);
        notifyKeyspaceEvent(NOTIFY_GENERIC,"expire",key,c->db->id);
    }

    if (!(flags & OBJ_SET_GET)) {
        addReply(c, ok_reply ? ok_reply : shared.ok);
    }

    /* Propagate without the GET argument (Isn't needed if we had expire since in that case we completely re-written the command argv) */
    if ((flags & OBJ_SET_GET) && !expire) {
        int argc = 0;
        int j;
        robj **argv = zmalloc((c->argc-1)*sizeof(robj*));
        for (j=0; j < c->argc; j++) {
            char *a = c->argv[j]->ptr;
            /* Skip GET which may be repeated multiple times. */
            if (j >= 3 &&
                (a[0] == 'g' || a[0] == 'G') &&
                (a[1] == 'e' || a[1] == 'E') &&
                (a[2] == 't' || a[2] == 'T') && a[3] == '\0')
                continue;
            argv[argc++] = c->argv[j];
            incrRefCount(c->argv[j]);
        }
        replaceClientCommandVector(c, argc, argv);
    }
}
```

**`object.c`源码片段**：尝试对给定对象的值进行编码，如果可以编码的话，就返回一个新的编码后的对象，否则返回原对象

```c
// 源码位置：redis-7.0.5\src\object.c
/* Try to encode a string object in order to save space */
robj *tryObjectEncoding(robj *o) {
    long value;
    sds s = o->ptr;
    size_t len;

    /* Make sure this is a string object, the only type we encode
     * in this function. Other types use encoded memory efficient
     * representations but are handled by the commands implementing
     * the type. */
    serverAssertWithInfo(NULL,o,o->type == OBJ_STRING);

    /* We try some specialized encoding only for objects that are
     * RAW or EMBSTR encoded, in other words objects that are still
     * in represented by an actually array of chars. */
    if (!sdsEncodedObject(o)) return o;

    /* It's not safe to encode shared objects: shared objects can be shared
     * everywhere in the "object space" of Redis and may end in places where
     * they are not handled. We handle them only as values in the keyspace. */
     if (o->refcount > 1) return o;

    /* Check if we can represent this string as a long integer.
     * Note that we are sure that a string larger than 20 chars is not
     * representable as a 32 nor 64 bit integer. */
    len = sdslen(s);
    if (len <= 20 && string2l(s,len,&value)) {
        /* This object is encodable as a long. Try to use a shared object.
         * Note that we avoid using shared integers when maxmemory is used
         * because every object needs to have a private LRU field for the LRU
         * algorithm to work well. */
        if ((server.maxmemory == 0 ||
            !(server.maxmemory_policy & MAXMEMORY_FLAG_NO_SHARED_INTEGERS)) &&
            value >= 0 &&
            value < OBJ_SHARED_INTEGERS)
        {
            decrRefCount(o);
            incrRefCount(shared.integers[value]);
            return shared.integers[value];
        } else {
            if (o->encoding == OBJ_ENCODING_RAW) {
                sdsfree(o->ptr);
                o->encoding = OBJ_ENCODING_INT;
                o->ptr = (void*) value;
                return o;
            } else if (o->encoding == OBJ_ENCODING_EMBSTR) {
                decrRefCount(o);
                return createStringObjectFromLongLongForValue(value);
            }
        }
    }

    /* If the string is small and is still RAW encoded,
     * try the EMBSTR encoding which is more efficient.
     * In this representation the object and the SDS string are allocated
     * in the same chunk of memory to save space and cache misses. */
    if (len <= OBJ_ENCODING_EMBSTR_SIZE_LIMIT) {
        robj *emb;

        if (o->encoding == OBJ_ENCODING_EMBSTR) return o;
        emb = createEmbeddedStringObject(s,sdslen(s));
        decrRefCount(o);
        return emb;
    }

    /* We can't encode the object...
     *
     * Do the last try, and at least optimize the SDS string inside
     * the string object to require little space, in case there
     * is more than 10% of free space at the end of the SDS string.
     *
     * We do that only for relatively large strings as this branch
     * is only entered if the length of the string is greater than
     * OBJ_ENCODING_EMBSTR_SIZE_LIMIT. */
    trimStringObjectIfNeeded(o);

    /* Return the original object. */
    return o;
}
```

##### 12.6.4  INT编码格式源码分析

INT编码格式命令演示：

```shell
127.0.0.1:6379> set k3 123
OK
127.0.0.1:6379> object encoding k3
"int"
```

 **INT编码格式源码分析**：当字符串键值的内容可以用一个64位有符号整形来表示时，Redis会将键值转化为long型来进行存储，此时即对应`OBJ_ENCODING_INT`编码类型。Redis启动时会预先建立10000个分别存储0~9999的 redisObject 变量作为共享对象，这就意味着如果set字符串的键值在0~10000之间的话，则可以 **直接指向共享对象而不需要再建立新对象，此时键值不占空间！**如`set k1 123`命令 和`set k2 123`命令产生的k1和k2都指向同一个共享的redisObject对象



![image-20240105034049299](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220421134.png)

**源码`server.h`**：Redis启动时会预先建立10000个分别存储0~9999的redisObject变量作为共享对象

```c
// 源码位置：redis-7.0.5\src\server.h
#define OBJ_SHARED_INTEGERS 10000
```

**源码`object.c`**：如果字符串长度小于等于20就将字符串转成long类型进行存储。如果配置了maxememory且值在10000L内，就直接使用共享对象值

```c
// 源码位置：redis-7.0.5\src\object.c
    /* Check if we can represent this string as a long integer.
     * Note that we are sure that a string larger than 20 chars is not
     * representable as a 32 nor 64 bit integer. */
    len = sdslen(s);
    //  字符串长度小于等于20且字符串转long类型成功
    if (len <= 20 && string2l(s,len,&value)) {
        /* This object is encodable as a long. Try to use a shared object.
         * Note that we avoid using shared integers when maxmemory is used
         * because every object needs to have a private LRU field for the LRU
         * algorithm to work well. */
        // 如果配置了maxememory且值在10000L内，就直接使用共享对象值
        if ((server.maxmemory == 0 ||
            !(server.maxmemory_policy & MAXMEMORY_FLAG_NO_SHARED_INTEGERS)) &&
            value >= 0 &&
            value < OBJ_SHARED_INTEGERS)
        {
            decrRefCount(o);
            incrRefCount(shared.integers[value]);
            return shared.integers[value];
        } else {
            if (o->encoding == OBJ_ENCODING_RAW) {
                sdsfree(o->ptr);
                o->encoding = OBJ_ENCODING_INT;
                o->ptr = (void*) value;
                return o;
            } else if (o->encoding == OBJ_ENCODING_EMBSTR) {
                decrRefCount(o);
                return createStringObjectFromLongLongForValue(value);
            }
        }
    }
```

**源码`object.c`**：`tryObjectEncoding`方法尝试对string类型的value进行encode编码处理

```c
// 源码位置：redis-7.0.5\src\object.c
/* Try to encode a string object in order to save space */
robj *tryObjectEncoding(robj *o) {
    long value;
    sds s = o->ptr;
    size_t len;

    /* Make sure this is a string object, the only type we encode
     * in this function. Other types use encoded memory efficient
     * representations but are handled by the commands implementing
     * the type. */
    serverAssertWithInfo(NULL,o,o->type == OBJ_STRING);

    /* We try some specialized encoding only for objects that are
     * RAW or EMBSTR encoded, in other words objects that are still
     * in represented by an actually array of chars. */
    if (!sdsEncodedObject(o)) return o;

    /* It's not safe to encode shared objects: shared objects can be shared
     * everywhere in the "object space" of Redis and may end in places where
     * they are not handled. We handle them only as values in the keyspace. */
     if (o->refcount > 1) return o;

    /* Check if we can represent this string as a long integer.
     * Note that we are sure that a string larger than 20 chars is not
     * representable as a 32 nor 64 bit integer. */
    len = sdslen(s);
    if (len <= 20 && string2l(s,len,&value)) {
        /* This object is encodable as a long. Try to use a shared object.
         * Note that we avoid using shared integers when maxmemory is used
         * because every object needs to have a private LRU field for the LRU
         * algorithm to work well. */
        if ((server.maxmemory == 0 ||
            !(server.maxmemory_policy & MAXMEMORY_FLAG_NO_SHARED_INTEGERS)) &&
            value >= 0 &&
            value < OBJ_SHARED_INTEGERS)
        {
            decrRefCount(o);
            incrRefCount(shared.integers[value]);
            return shared.integers[value];
        } else {
            if (o->encoding == OBJ_ENCODING_RAW) {
                sdsfree(o->ptr);
                o->encoding = OBJ_ENCODING_INT;
                o->ptr = (void*) value;
                return o;
            } else if (o->encoding == OBJ_ENCODING_EMBSTR) {
                decrRefCount(o);
                return createStringObjectFromLongLongForValue(value);
            }
        }
    }

    /* If the string is small and is still RAW encoded,
     * try the EMBSTR encoding which is more efficient.
     * In this representation the object and the SDS string are allocated
     * in the same chunk of memory to save space and cache misses. */
    if (len <= OBJ_ENCODING_EMBSTR_SIZE_LIMIT) {
        robj *emb;

        if (o->encoding == OBJ_ENCODING_EMBSTR) return o;
        emb = createEmbeddedStringObject(s,sdslen(s));
        decrRefCount(o);
        return emb;
    }

    /* We can't encode the object...
     *
     * Do the last try, and at least optimize the SDS string inside
     * the string object to require little space, in case there
     * is more than 10% of free space at the end of the SDS string.
     *
     * We do that only for relatively large strings as this branch
     * is only entered if the length of the string is greater than
     * OBJ_ENCODING_EMBSTR_SIZE_LIMIT. */
    trimStringObjectIfNeeded(o);

    /* Return the original object. */
    return o;
}
```

#####  12.6.5  EMBSTR编码格式源码分析

EMBSTR编码格式：

```shell
127.0.0.1:6379> set k3 abc
oK
127.0.0.1:6279> object encoding k3
"embstr"
```

**EMBSTR编码格式源码分析**：对于长度小于44的字符串，Redis 对键值采用OBJ_ENCODING_EMBSTR 方式，EMBSTR 顾名思义即：embedded string，表示嵌入式的String。从内存结构上来讲 即字符串 sds结构体与其对应的 redisObject 对象分配在同一块连续的内存空间，字符串sds嵌入在redisObject对象之中一样

**`object.c`源码片段**：定义常量`OBJ_ENCODING_EMBSTR_SIZE_LIMIT`为44，如果小于44就存储为`EMBSTR`编码格式、大于44就存储成`RAW`编码格式

```c
// 源码位置：redis-7.0.5\src\object.c
#define OBJ_ENCODING_EMBSTR_SIZE_LIMIT 44
```

**`object.c`源码片段**：创建string类型的redisObject对象时，如果字符串长度小于44就存储为`EMBSTR`编码格式、大于44就存储成`RAW`编码格式

```c
// 源码位置：redis-7.0.5\src\object.c
/* Create a string object with EMBSTR encoding if it is smaller than
 * OBJ_ENCODING_EMBSTR_SIZE_LIMIT, otherwise the RAW encoding is
 * used.
 *
 * The current limit of 44 is chosen so that the biggest string object
 * we allocate as EMBSTR will still fit into the 64 byte arena of jemalloc. */
#define OBJ_ENCODING_EMBSTR_SIZE_LIMIT 44
robj *createStringObject(const char *ptr, size_t len) {
    if (len <= OBJ_ENCODING_EMBSTR_SIZE_LIMIT)
        return createEmbeddedStringObject(ptr,len);
    else
        return createRawStringObject(ptr,len);
}
```

**`object.c`源码片段**：`createEmbeddedStringObject`方法将字符串存储为EMBSTR编码格式。对于长度小于44的字符串，Redis 对键值采用OBJ_ENCODING_EMBSTR 方式，EMBSTR 顾名思义即：embedded string，表示嵌入式的String。从内存结构上来讲，即**字符串sds结构体与其对应的redisObject 对象分配在同一块连续的内存空间**，字符串sds嵌入到了redisObject对象之中

```c
// 源码位置：redis-7.0.5\src\object.c
/* Create a string object with encoding OBJ_ENCODING_EMBSTR, that is
 * an object where the sds string is actually an unmodifiable string
 * allocated in the same chunk as the object itself. */
robj *createEmbeddedStringObject(const char *ptr, size_t len) {
    robj *o = zmalloc(sizeof(robj)+sizeof(struct sdshdr8)+len+1);
    struct sdshdr8 *sh = (void*)(o+1);

    o->type = OBJ_STRING;
    o->encoding = OBJ_ENCODING_EMBSTR;
    o->ptr = sh+1;
    o->refcount = 1;
    if (server.maxmemory_policy & MAXMEMORY_FLAG_LFU) {
        o->lru = (LFUGetTimeInMinutes()<<8) | LFU_INIT_VAL;
    } else {
        o->lru = LRU_CLOCK();
    }

    sh->len = len;
    sh->alloc = len;
    sh->flags = SDS_TYPE_8;
    if (ptr == SDS_NOINIT)
        sh->buf[len] = '\0';
    else if (ptr) {
        memcpy(sh->buf,ptr,len);
        sh->buf[len] = '\0';
    } else {
        memset(sh->buf,0,len+1);
    }
    return o;
}
```

`createEmbeddedStringObject`方法没有重新申请一个redisObject对象，而是在同一个redisObject对象的基础上嵌入字符串。从内存结构上来讲，即字符串sds结构体与其对应的redisObject 对象分配在同一块连续的内存空间，字符串sds嵌入到了redisObject对象之中

![image-20240107205826234](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220421438.png)



**`object.c`源码片段**：`tryObjectEncoding`方法中尝试对存储对象进行编码，如果字符串长度小于`OBJ_ENCODING_EMBSTR_SIZE_LIMIT`就会被编码为`EMBSTR`格式

```c
// 源码位置：redis-7.0.5\src\object.c
/* Try to encode a string object in order to save space */
robj *tryObjectEncoding(robj *o) {
    long value;
    sds s = o->ptr;
    size_t len;
    // 省略部分代码、省略部分代码
    /* If the string is small and is still RAW encoded,
     * try the EMBSTR encoding which is more efficient.
     * In this representation the object and the SDS string are allocated
     * in the same chunk of memory to save space and cache misses. */
    if (len <= OBJ_ENCODING_EMBSTR_SIZE_LIMIT) {
        robj *emb;

        if (o->encoding == OBJ_ENCODING_EMBSTR) return o;
        emb = createEmbeddedStringObject(s,sdslen(s));
        decrRefCount(o);
        return emb;
    }

    /* We can't encode the object...
     *
     * Do the last try, and at least optimize the SDS string inside
     * the string object to require little space, in case there
     * is more than 10% of free space at the end of the SDS string.
     *
     * We do that only for relatively large strings as this branch
     * is only entered if the length of the string is greater than
     * OBJ_ENCODING_EMBSTR_SIZE_LIMIT. */
    trimStringObjectIfNeeded(o);

    /* Return the original object. */
    return o;
}
```

#####  12.6.6 RAW编码格式源码分析

RAW编码格式命令演示：

```shell
127.0.0.1:6379> set k3 abcdefghijklmnopqrstuvwxyzabcdeffasdffsdaadsx 
oK
127.0.0.12319> object encoding k3
"raw"
```

**RAW编码格式源码分析**：当字符串的键值为长度大于44的超长字符串时，Redis 则会将键值的内部编码方式改为`OBJ_ENCODING_RAW`格式，这与`OBJ_ENCODING_EMBSTR`编码方式的不同之处在于，此时动态字符串sds的内存与其依赖的redisObject的内存不再连续了

```c
/* Create a string object with encoding OBJ_ENCODING_RAW, that is a plain
 * string object where o->ptr points to a proper sds string. */
robj *createRawStringObject(const char *ptr, size_t len) {
    return createObject(OBJ_STRING, sdsnewlen(ptr,len));
}
/* ===================== Creation and parsing of objects ==================== */

robj *createObject(int type, void *ptr) {
    robj *o = zmalloc(sizeof(*o));
    o->type = type;
    o->encoding = OBJ_ENCODING_RAW;
    o->ptr = ptr;
    o->refcount = 1;

    /* Set the LRU to the current lruclock (minutes resolution), or
     * alternatively the LFU counter. */
    if (server.maxmemory_policy & MAXMEMORY_FLAG_LFU) {
        o->lru = (LFUGetTimeInMinutes()<<8) | LFU_INIT_VAL;
    } else {
        o->lru = LRU_CLOCK();
    }
    return o;
}
```

当字符串的键值为长度大于44的超长字符串时，Redis 则会将键值的内部编码方式改为`OBJ_ENCODING_RAW`格式，这与`OBJ_ENCODING_EMBSTR`编码方式的不同之处在于，此时动态字符串sds的内存与其依赖的redisObject的内存不再连续了

![image-20240107211523080](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220421116.png)

#####  12.6.7 低于阈值但编码格式是raw

现象：字符串长度低于阈值，执行了APPEND命令之后编码格式变成了raw 

```
127.0.0.1:6379> set k3 a
OK
127.0.0.1:6379> object encoding k3
"embstr"
127.0.0.1:6379> APPEND k3 b
(integer) 2
127.0.0.1:6379> get k3
"ab"
127.0.0.1:6379> object encoding k3
"raw"
```

由于embstr是只读的，因此在对embstr 对象进行修改时，都会先转化为raw再进行修改。因此，只要是修改embstr对象，修改后的对象—定是raw的，无论是否达到了44个字节。从 "embstr" 编码格式变成 "raw" 的原因是因为在执行 APPEND 命令时，Redis 需要将 "embstr" 转换为 "raw" 编码格式

#####  12.6.8 编码格式总结

string类型编码格式源码逻辑：

![image-20240105042145084](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220421312.png)

string类型三种编码格式总结：

| 编码格式 | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| int      | Long类型整数时，RedisObject中的ptr指针直接赋值为整数数据，不再额外的指针再指向整数了，节省了指针的空间开销 |
| embstr   | 当保存的是字符串数据且字符串小于等于44字节时，embstr类型将会调用内存分配函数，只分配一块连续的内存空间，空间中依次包含 redisObject 与 sdshdr 两个数据结构，让元数据、指针和SDS是一块连续的内存区域，这样就可以避免内存碎片 |
| raw      | 当字符串大于44字节时，SDS的数据量变多变大了，SDS和RedisObject布局分家各自过，会给SDS分配多的空间并用指针指向SDS结构，raw 类型将会调用两次内存分配函数，分配两块内存空间，一块用于包含 redisObject结构，而另一块用于包含 sdshdr 结构 |

 embstr与raw类型的内存结构区别：

![image-20240105042335360](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220421214.png)

 

补充说明：只有整数才会使用int，如果是浮点数， Redis 内部其实先将浮点数转化为字符串值，然后再保存。embstr与raw类型底层的数据结构其实都是 SDS (简单动态字符串，Redis 内部定义 sdshdr 一种结构)

总结：Redis内部会根据用户给的不同键值而使用不同的编码格式，自适应地选择较优化的内部编码格式，而这一切对用户完全透明！

###  12.5  Hash数据结构介绍

redis6 hash数据类型的底层实现：hashtable和ziplist

redis6 hash数据类型的底层实现：hashtable和listpack

#####  12.5.1 redis6 Hash类型底层实现

Redis6中的Hash数据结构底层实现使用了两种不同的方式：ziplist和hashtable

1. **Ziplist：**
   - 当Hash的元素个数比较少或者每个元素的值都比较小的时候，Redis会选择使用ziplist来实现Hash
   - Ziplist是一种紧凑的数据结构，它将多个项存储在一起，占用较小的内存空间
   - 对于小型的Hash结构，ziplist可以减少内存的使用并提高性能
2. **Hashtable：**
   - 当Hash的元素个数较多或者每个元素的值较大时，Redis会使用hashtable来实现Hash
   - Hashtable是一种常用的数据结构，它通过哈希函数将Key映射到对应的Value
   - Redis使用hashtable来处理较大的Hash结构，以便高效地进行查找、插入和删除操作

根据Hash的大小和元素值的大小，Redis会根据需求灵活选择使用ziplist或hashtable来实现Hash数据结构，以获得最佳的性能和内存利用率



**redis6 Hash类型底层实现逻辑**：Hash类型键的字段个数 小于 `hash-max-ziplist-entries` 并且每个字段名和字段值的长度 小于 `hash-max-ziplist-value` 时，Redis才会使用 `OBJ_ENCODING_ZIPLIST`（ziplist）来存储该键，前述条件任意一个不满足则会转换为 `OBJ_ENCODING_HT`（hashtable）的编码方式。`hash-max-ziplist-entries`含义：使用压缩列表保存时哈希集合中的最大元素个数。`hash-max-ziplist-value`含义：使用压缩列表保存时哈希集合中单个元素的最大长度

**redis6 Hash类型底层实现逻辑验证**：

![image-20240105043446525](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220421570.png)



**redis6 Hash类型底层结论**：

- 默认配置的情况下，`哈希对象保存的键值对数量小于512个` 且 `所有的键值对的健和值的字符串长度都小于等于64byte` (一个英文字母一个字节)时用ziplist，反之用hashtable
- ziplist升级到hashtable可以，反过来降级不可以。一旦从压缩列表转为了哈希表，Hash类型就会一直用哈希表进行保存而不会再转回压缩列表了。在节省内存空间方面哈希表就没有压缩列表高效了

**redis6 Hash类型源码逻辑**：Hash类型键的字段个数 小于 `hash-max-ziplist-entries` 并且每个字段名和字段值的长度 小于 `hash-max-ziplist-value` 时，Redis才会使用 `OBJ_ENCODING_ZIPLIST`（ziplist）来存储该键，前述条件任意一个不满足则会转换为 `OBJ_ENCODING_HT`（hashtable）的编码方式。**源码逻辑图如下**

![image-20240105043748082](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220421101.png)

#####  12.5.2  redis6 hashtable源码分析

在Redis 中,hashtable被称为字典(dictionary)，它是一个数组+链表的结构。hashtable源码实现：`t_hash.c`

**OBJ_ENCODING_HT编码分析**：OBJ_ENCODING_HT 这种编码方式内部才是真正的哈希表结构，或称为字典结构，其可以实现O(1)复杂度的读写操作，因此效率很高。在 Redis内部，从 OBJ_ENCODING_HT类型到底层真正的散列表数据结构是一层层嵌套下去的，组织关系见面图：

![image-20240109062306906](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220422976.png)



```c
// 源码位置：redis-6.2.13\src\dict.h
typedef struct dictEntry {
    void *key;
    union {
        void *val;
        uint64_t u64;
        int64_t s64;
        double d;
    } v;
    struct dictEntry *next;
} dictEntry;

typedef struct dictType {
    uint64_t (*hashFunction)(const void *key);
    void *(*keyDup)(void *privdata, const void *key);
    void *(*valDup)(void *privdata, const void *obj);
    int (*keyCompare)(void *privdata, const void *key1, const void *key2);
    void (*keyDestructor)(void *privdata, void *key);
    void (*valDestructor)(void *privdata, void *obj);
    int (*expandAllowed)(size_t moreMem, double usedRatio);
} dictType;

/* This is our hash table structure. Every dictionary has two of this as we
 * implement incremental rehashing, for the old to the new table. */
typedef struct dictht {
    dictEntry **table;
    unsigned long size;
    unsigned long sizemask;
    unsigned long used;
} dictht;

typedef struct dict {
    dictType *type;
    void *privdata;
    dictht ht[2];
    long rehashidx; /* rehashing not in progress if rehashidx == -1 */
    int16_t pauserehash; /* If >0 rehashing is paused (<0 indicates coding error) */
} dict;
```



**HSET命令源码**：`hsetCommand`函数实现了"HSET"命令的功能。`hsetCommand`函数实现了"HSET"命令，功能包括参数个数检查、获取或创建哈希对象、执行对哈希对象的字段和值的设置、返回结果和相应的处理。HSET命令用于设置哈希对象（Hash Object）中的字段和对应的值。函数逻辑：

1. 首先，检查参数的个数是否为奇数，如果是奇数则返回错误；这是因为"HSET"命令的正确用法应该是键、字段、值的成对出现
2. 接着，调用`hashTypeLookupWriteOrCreate`函数来获取或者创建一个可写的哈希对象，并对其进行检查。如果获取或创建失败，则直接返回
3. 调用`hashTypeTryConversion`来检查是否需要将ziplist转换为真实的哈希表
4. 然后，通过一个循环对每对字段和值调用`hashTypeSet`函数来设置哈希对象中的字段和对应的值，同时计算创建的字段数量，这部分使用了`HASH_SET_COPY`标志
5. 最后，根据是"HSET"还是"HMSET"命令，返回相应的结果。如果是"HSET"命令，返回创建字段的数量；如果是"HMSET"命令（已废弃），则返回OK
6. 更新被修改的键，发送相应的键空间通知，并增加服务器的脏数据计数

```c
// 源码位置：redis-7.0.5\src\t_hash.c
void hsetCommand(client *c) {
    int i, created = 0;
    robj *o;

    if ((c->argc % 2) == 1) {
        addReplyErrorArity(c);
        return;
    }

    if ((o = hashTypeLookupWriteOrCreate(c,c->argv[1])) == NULL) return;
    hashTypeTryConversion(o,c->argv,2,c->argc-1);

    for (i = 2; i < c->argc; i += 2)
        created += !hashTypeSet(o,c->argv[i]->ptr,c->argv[i+1]->ptr,HASH_SET_COPY);

    /* HMSET (deprecated) and HSET return value is different. */
    char *cmdname = c->argv[0]->ptr;
    if (cmdname[1] == 's' || cmdname[1] == 'S') {
        /* HSET */
        addReplyLongLong(c, created);
    } else {
        /* HMSET */
        addReply(c, shared.ok);
    }
    signalModifiedKey(c,c->db,c->argv[1]);
    notifyKeyspaceEvent(NOTIFY_HASH,"hset",c->argv[1],c->db->id);
    server.dirty += (c->argc - 2)/2;
}
```



**判断是否需要将当前的ziplist转换为哈希表**：`hashTypeTryConversion`函数用于在需要时将ziplist转换为真实的哈希表（hash）的情况下进行长度检查的函数。主要功能为检查一系列对象的长度，以确定是否需要将ziplist转换为真实的哈希表，`hashTypeTryConversion`函数的关键目的是检查一系列对象的长度，以确定是否需要将当前的ziplist转换为哈希表，从而确保数据结构使用的效率和性能。主要逻辑如下：

1. 首先检查给定对象（o）的编码方式，如果不是ziplist，则直接返回
2. 对给定的一系列对象（argv数组中的一部分）进行遍历
3. 对于每个对象，如果它不是SDS编码的对象，就跳过。SDS是Redis中的一种字符串编码方式，其长度可以在常量时间内查询
4. 如果对象的长度超过了设定的最大ziplist值（server.hash_max_ziplist_value），则立即将对象进行哈希表的转换，并返回
5. 否则，为所有长度进行累加，并在累加完所有长度后，再次判断是否可以安全地将这些对象添加到ziplist中，若不安全则将对象进行哈希表的转换

```c
// 源码位置：redis-6.2.13\src\t_hash.c

/* Check the length of a number of objects to see if we need to convert a
 * ziplist to a real hash. Note that we only check string encoded objects
 * as their string length can be queried in constant time. */
void hashTypeTryConversion(robj *o, robj **argv, int start, int end) {
    int i;
    size_t sum = 0;

    if (o->encoding != OBJ_ENCODING_ZIPLIST) return;

    for (i = start; i <= end; i++) {
        if (!sdsEncodedObject(argv[i]))
            continue;
        size_t len = sdslen(argv[i]->ptr);
        if (len > server.hash_max_ziplist_value) {
            hashTypeConvert(o, OBJ_ENCODING_HT);
            return;
        }
        sum += len;
    }
    if (!ziplistSafeToAdd(o->ptr, sum))
        hashTypeConvert(o, OBJ_ENCODING_HT);
}
```

**哈希对象中字段和值的更新与插入**：`hashTypeSet`函数用于在哈希对象中设置新的字段（field）和对应的值（value），如果字段已经存在，则用新值覆盖旧值。函数通过修改传递进来的哈希对象（robj *o），更新或插入新的字段和值。如果字段已存在则返回1，如果是插入新的字段则返回0。函数允许传入特定的标志（flags），以决定如何处理字段(field)和值(value)的SDS字符串的所有权。默认情况下，会将字段和值的SDS字符串复制一份来进行存储，并在需要时释放。但是，通过传递适当的标志（可以通过位运算进行组合），可以改变此默认行为。此函数负责处理哈希对象中字段和值的更新与插入，并根据标志位来控制SDS字符串的所有权。主要操作包括：

1. 如果哈希对象的编码为ziplist，则在ziplist中进行字段和值的处理。如果字段已存在，则更新其对应的值，否则向ziplist中添加新的字段和值
2. 如果哈希对象的编码为哈希表（hash table），则在哈希表中进行字段和值的处理。如果字段已存在，根据标志的设定，决定是否接管传入字段或值的SDS字符串的所有权
3. 如果编码不为ziplist或哈希表，则会发生严重错误
4. 最后，根据标志的设定，释放未被引用的字段和值的SDS字符串

```c
// 源码位置：redis-6.2.13\src\t_hash.c

/* Add a new field, overwrite the old with the new value if it already exists.
 * Return 0 on insert and 1 on update.
 *
 * By default, the key and value SDS strings are copied if needed, so the
 * caller retains ownership of the strings passed. However this behavior
 * can be effected by passing appropriate flags (possibly bitwise OR-ed):
 *
 * HASH_SET_TAKE_FIELD -- The SDS field ownership passes to the function.
 * HASH_SET_TAKE_VALUE -- The SDS value ownership passes to the function.
 *
 * When the flags are used the caller does not need to release the passed
 * SDS string(s). It's up to the function to use the string to create a new
 * entry or to free the SDS string before returning to the caller.
 *
 * HASH_SET_COPY corresponds to no flags passed, and means the default
 * semantics of copying the values if needed.
 *
 */
#define HASH_SET_TAKE_FIELD (1<<0)
#define HASH_SET_TAKE_VALUE (1<<1)
#define HASH_SET_COPY 0
int hashTypeSet(robj *o, sds field, sds value, int flags) {
    int update = 0;

    if (o->encoding == OBJ_ENCODING_ZIPLIST) {
        unsigned char *zl, *fptr, *vptr;

        zl = o->ptr;
        fptr = ziplistIndex(zl, ZIPLIST_HEAD);
        if (fptr != NULL) {
            fptr = ziplistFind(zl, fptr, (unsigned char*)field, sdslen(field), 1);
            if (fptr != NULL) {
                /* Grab pointer to the value (fptr points to the field) */
                vptr = ziplistNext(zl, fptr);
                serverAssert(vptr != NULL);
                update = 1;

                /* Replace value */
                zl = ziplistReplace(zl, vptr, (unsigned char*)value,
                        sdslen(value));
            }
        }

        if (!update) {
            /* Push new field/value pair onto the tail of the ziplist */
            zl = ziplistPush(zl, (unsigned char*)field, sdslen(field),
                    ZIPLIST_TAIL);
            zl = ziplistPush(zl, (unsigned char*)value, sdslen(value),
                    ZIPLIST_TAIL);
        }
        o->ptr = zl;

        /* Check if the ziplist needs to be converted to a hash table */
        if (hashTypeLength(o) > server.hash_max_ziplist_entries)
            hashTypeConvert(o, OBJ_ENCODING_HT);
    } else if (o->encoding == OBJ_ENCODING_HT) {
        dictEntry *de = dictFind(o->ptr,field);
        if (de) {
            sdsfree(dictGetVal(de));
            if (flags & HASH_SET_TAKE_VALUE) {
                dictGetVal(de) = value;
                value = NULL;
            } else {
                dictGetVal(de) = sdsdup(value);
            }
            update = 1;
        } else {
            sds f,v;
            if (flags & HASH_SET_TAKE_FIELD) {
                f = field;
                field = NULL;
            } else {
                f = sdsdup(field);
            }
            if (flags & HASH_SET_TAKE_VALUE) {
                v = value;
                value = NULL;
            } else {
                v = sdsdup(value);
            }
            dictAdd(o->ptr,f,v);
        }
    } else {
        serverPanic("Unknown hash encoding");
    }

    /* Free SDS strings we did not referenced elsewhere if the flags
     * want this function to be responsible. */
    if (flags & HASH_SET_TAKE_FIELD && field) sdsfree(field);
    if (flags & HASH_SET_TAKE_VALUE && value) sdsfree(value);
    return update;
}
```

#####  12.5.3  redis6  ziplist 压缩列表源码分析

**源码中对于ziplist压缩列表的介绍**：Ziplist 压缩列表是一种紧凑编码格式，总体思想是多花时间来换取节约空间，即以部分读写性能为代价，来换取极高的内存空间利用率，因此只会用于字段个数少，且字段值也较小的场景。压缩列表内存利用率极高的原因与其连续内存的特性是分不开的。想想我们的学过的一种GC垃圾回收机制：标记--压缩算法。当一个hash对象只包含少量键值对且每个键值对的键和值要么就是小整数要么就是长度比较短的字符串，那么它用 ziplist 作为底层实现

```c
// 源码中对于ziplist压缩列表的介绍
// 源码位置：redis-7.0.5\src\ziplist.c
/* The ziplist is a specially encoded dually linked list that is designed
 * to be very memory efficient. It stores both strings and integer values,
 * where integers are encoded as actual integers instead of a series of
 * characters. It allows push and pop operations on either side of the list
 * in O(1) time. However, because every operation requires a reallocation of
 * the memory used by the ziplist, the actual complexity is related to the
 * amount of memory used by the ziplist.
 *
 * ----------------------------------------------------------------------------
```

**Ziplist内部结构(官方介绍)**：Ziplist（压缩列表）是 Redis 中一种用于紧凑存储数据的数据结构。Ziplist使用了多个字段来记录整个列表结构的相关元信息，并且利用这个信息提供了高效的操作，支持尾端的快速弹出操作，并且在需要时能够进行扩展操作。Ziplist内部结构定义如下：

- `<zlbytes>`：一个无符号整数，用于保存整个ziplist占用的字节数，包括`zlbytes`字段本身的四个字节。这个值的存储可以使整个结构在无需先遍历的情况下就能够被调整大小
- `<zltail>`：是指向列表中最后一个节点的偏移量。这项能够允许在不需全面遍历的情况下进行列表的尾端弹出操作
- `<zllen>`：是列表中节点数量的计数器。当节点数量超过2^16-2时，这个数值会被设置为2^16-1，这时需要遍历整个列表才能知道它一共包含多少个节点
- `<zlend>`：是一个标识ziplist结束的特殊条目，其编码为单个字节等于255。因为没有其他正常条目的开始字节设置为值为255

```c
// 源码中对于Ziplist内部结构的介绍
// 源码位置：redis-7.0.5\src\ziplist.c
/* ZIPLIST OVERALL LAYOUT
 * The general layout of the ziplist is as follows:
 * <zlbytes> <zltail> <zllen> <entry> <entry> ... <entry> <zlend>
 * NOTE: all fields are stored in little endian, if not specified otherwise.
 * <uint32_t zlbytes> is an unsigned integer to hold the number of bytes that
 * the ziplist occupies, including the four bytes of the zlbytes field itself.
 * This value needs to be stored to be able to resize the entire structure
 * without the need to traverse it first.
 *
 * <uint32_t zltail> is the offset to the last entry in the list. This allows
 * a pop operation on the far side of the list without the need for full
 * traversal.
 * <uint16_t zllen> is the number of entries. When there are more than
 * 2^16-2 entries, this value is set to 2^16-1 and we need to traverse the
 * entire list to know how many items it holds.
 * <uint8_t zlend> is a special entry representing the end of the ziplist.
 * Is encoded as a single byte equal to 255. No other normal entry starts
 * with a byte set to the value of 255.
 */
```

**Ziplist内部结构**：Ziplist 压缩列表是为了节约内存而开发的，它是由连续内存块组成的顺序型数据结构，有点类似于数组。**ziplist是一个经过特殊编码的双向链表**，但是它不存储指向前一个链表节点prev和指向下一个链表节点的指针next，而是存储上一个节点长度和当前节点长度，通过牺牲部分读写性能，来换取高效的内存空间利用率，节约内存，是一种时间换空间的思想。Ziplist 只用在字段个数少，字段值小的场景里面

![image-20240109072034660](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220422107.png)



**ziplist结构内部元素的含义及作用**：

| 属性    | 类型     | 长度  | 作用                                                         |
| ------- | -------- | ----- | ------------------------------------------------------------ |
| zlbytes | uint32_t | 4字节 | 记录整个压缩列表占用的内存字节数:在对压缩列表进行内存重分配，或者计算zlend的位置时使用 |
| zltail  | uint32_t | 4字节 | 记录压缩列表表尾节点距离压缩列表的起始地址有多少字节:通过这个偏移量，程序无须遍历整个压缩列表就可以确定表尾节点的地址 |
| zllen   | uint16_t | 2字节 | 记录了压缩列表包含的节点数量:当这个属性的值小于UINT16_MAX(65535)时，这个属性的值就是压缩列表包含节点的数量;当这个值等于UINT16_MAX时，节点的真实数量需要遍历整个压缩列表才能计算得出 |
| entryX  | 列表节点 | 不定  | 压缩列表包含的各个节点，节点的长度由节点保存的内容决定       |
| zlend   | uint8_t  | 1字节 | 特殊值0xFF(十进制255)，用于标记压缩列表的末端                |

**压缩列表节点zlentry的构成**：

源码中对于zlentry内部结构的介绍

```c
// 源码中对于zlentry内部结构的介绍
// 源码位置：redis-7.0.5\src\ziplist.c
 /* ZIPLIST ENTRIES
 * ===============
 *
 * Every entry in the ziplist is prefixed by metadata that contains two pieces
 * of information. First, the length of the previous entry is stored to be
 * able to traverse the list from back to front. Second, the entry encoding is
 * provided. It represents the entry type, integer or string, and in the case
 * of strings it also represents the length of the string payload.
 * So a complete entry is stored like this:
 *
 * <prevlen> <encoding> <entry-data>
 *
 * Sometimes the encoding represents the entry itself, like for small integers
 * as we'll see later. In such a case the <entry-data> part is missing, and we
 * could have just:
 *
 * <prevlen> <encoding>
 *
 * The length of the previous entry, <prevlen>, is encoded in the following way:
 * If this length is smaller than 254 bytes, it will only consume a single
 * byte representing the length as an unsinged 8 bit integer. When the length
 * is greater than or equal to 254, it will consume 5 bytes. The first byte is
 * set to 254 (FE) to indicate a larger value is following. The remaining 4
 * bytes take the length of the previous entry as value.
 *
 * So practically an entry is encoded in the following way:
 *
 * <prevlen from 0 to 253> <encoding> <entry>
 *
 * Or alternatively if the previous entry length is greater than 253 bytes
 * the following encoding is used:
 *
 * 0xFE <4 bytes unsigned little endian prevlen> <encoding> <entry>
 *
 * The encoding field of the entry depends on the content of the
 * entry. When the entry is a string, the first 2 bits of the encoding first
 * byte will hold the type of encoding used to store the length of the string,
 * followed by the actual length of the string. When the entry is an integer
 * the first 2 bits are both set to 1. The following 2 bits are used to specify
 * what kind of integer will be stored after this header. An overview of the
 * different types and encodings is as follows. The first byte is always enough
 * to determine the kind of entry.
 *
 * |00pppppp| - 1 byte
 *      String value with length less than or equal to 63 bytes (6 bits).
 *      "pppppp" represents the unsigned 6 bit length.
 * |01pppppp|qqqqqqqq| - 2 bytes
 *      String value with length less than or equal to 16383 bytes (14 bits).
 *      IMPORTANT: The 14 bit number is stored in big endian.
 * |10000000|qqqqqqqq|rrrrrrrr|ssssssss|tttttttt| - 5 bytes
 *      String value with length greater than or equal to 16384 bytes.
 *      Only the 4 bytes following the first byte represents the length
 *      up to 2^32-1. The 6 lower bits of the first byte are not used and
 *      are set to zero.
 *      IMPORTANT: The 32 bit number is stored in big endian.
 * |11000000| - 3 bytes
 *      Integer encoded as int16_t (2 bytes).
 * |11010000| - 5 bytes
 *      Integer encoded as int32_t (4 bytes).
 * |11100000| - 9 bytes
 *      Integer encoded as int64_t (8 bytes).
 * |11110000| - 4 bytes
 *      Integer encoded as 24 bit signed (3 bytes).
 * |11111110| - 2 bytes
 *      Integer encoded as 8 bit signed (1 byte).
 * |1111xxxx| - (with xxxx between 0001 and 1101) immediate 4 bit integer.
 *      Unsigned integer from 0 to 12. The encoded value is actually from
 *      1 to 13 because 0000 and 1111 can not be used, so 1 should be
 *      subtracted from the encoded 4 bit value to obtain the right value.
 * |11111111| - End of ziplist special entry.
 *
 * Like for the ziplist header, all the integers are represented in little
 * endian byte order, even when this code is compiled in big endian systems.
 */
```

zlentry实体结构定义：

```c
// redis-7.0.5\src\ziplist.c
/* We use this function to receive information about a ziplist entry.
 * Note that this is not how the data is actually encoded, is just what we
 * get filled by a function in order to operate more easily. */
typedef struct zlentry {
    unsigned int prevrawlensize; /* Bytes used to encode the previous entry len*/
    unsigned int prevrawlen;     /* Previous entry len. */
    unsigned int lensize;        /* Bytes used to encode this entry type/len.
                                    For example strings have a 1, 2 or 5 bytes
                                    header. Integers always use a single byte.*/
    unsigned int len;            /* Bytes used to represent the actual entry.
                                    For strings this is just the string length
                                    while for integers it is 1, 2, 3, 4, 8 or
                                    0 (for 4 bit immediate) depending on the
                                    number range. */
    unsigned int headersize;     /* prevrawlensize + lensize. */
    unsigned char encoding;      /* Set to ZIP_STR_* or ZIP_INT_* depending on
                                    the entry encoding. However for 4 bits
                                    immediate integers this can assume a range
                                    of values and must be range-checked. */
    unsigned char *p;            /* Pointer to the very start of the entry, that
                                    is, this points to prev-entry-len field. */
} zlentry;
```

zlentry实体结构解析：

![image-20240108011609699](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220422933.png)

![image-20240105052109367](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220422918.png)

zlentry存取情况：

- prevlen：记录了前一个节点的长度
- encoding：记录了当前节点实际数据的类型以及长度
- data：记录了当前节点的实际数据

![image-20240105051920444](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220422314.png)

 **zlentry结构解析**：压缩列表zlentry节点结构中，每个zlentry由前一个节点的长度、encoding和entry-data三部分组成。**前节点长度**：(前节点占用的内存字节数)表示前1个zlentry的长度，privious_entry_length有两种取值情况：1字节或5字节。取值1字节时，表示上一个entry的长度小于254字节。虽然1字节的值能表示的数值范围是0到255，但是压缩列表中zlend的取值默认是255，因此，就默认用255表示整个压缩列表的结束，其他表示长度的地方就不能再用255这个值了。所以，当上一个entry长度小于254字节时，prev_len取值为1字节，否则，就取值为5字节。记录长度的好处：占用内存小，1或者5个字节。**enncoding**：记录节点的content保存数据的类型和长度。**content**：保存实际数据内容

![image-20240105052140682](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220422247.png)



**zlentry为什么这么设计？数组和链表数据结构对比**

链表在内存中，一般是不连续的，遍历相对比较慢，而ziplist可以很好的解决这个问题。如果知道了当前的起始地址，因为entry是连续的，entry后一定是另一个entry，想知道下一个entry的地址，只要将当前的起始地址加上当前entry总长度。如果还想遍历下一个entry，只要继续同样的操作。privious_entry_length，encoding长度都可以根据编码方式推算，真正变化的是content，而content长度记录在encoding里 ，因此entry的长度就知道了。entry总长度 = privious_entry_length字节数+encoding字节数+content字节数

![image-20240105052327792](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220422894.png)

**明明有链表了，为什么要使用压缩链表？**

- 普通的双向链表会有两个指针，在存储数据很小的情况下，存储的实际数据的大小可能还没有指针占用的内存大，得不偿失。ziplist 是一个特殊的双向链表，但是没有维护双向指针:previous next；而是存储上一个 entry的长度和当前entry的长度，通过长度推算下一个元素在什么地方。牺牲读取的性能，获得高效的存储空间，因为(简短字符串的情况)存储指针比存储entry长度更费内存。这是典型的“时间换空间”
- 链表在内存中一般是不连续的，遍历相对比较慢而ziplist可以很好的解决这个问题，普通数组的遍历是根据数组里存储的数据类型找到下一个元素的(例如int类型的数组访问下一个元素时每次只需要移动一个sizeof(int)就行)，但是ziplist的每个节点的长度是可以不一样的，而面对不同长度的节点又不可能直接sizeof(entry)，所以ziplist只好将一些必要的偏移量信息记录在了每一个节点里，使之能跳到上一个节点或下一个节点。备注:sizeof实际上是获取了数据在内存中所占用的存储空间，以字节为单位来计数
- 头节点里有头节点里同时还有一个参数 len，和string类型提到的 SDS 类似，这里是用来记录链表长度的。因此获取链表长度时不用再遍历整个链表，直接拿到len值就可以了，这个时间复杂度是 O(1)

**ziplist总结**：

- ziplist为了节省内存，采用了紧凑的连续存储
- ziplist是一个双向链表，可以在时间复杂度为 O(1) 下从头部、尾部进行 pop 或 push
- 新增或更新元素可能会出现**连锁更新现象(**致命缺点导致被listpack替换)
- 不能保存过多的元素，否则查询效率就会降低，数量小和内容小的情况下可以使用

#####  12.5.4 redis7  Hash类型底层实现

 **redis7  Hash类型源码逻辑**：Hash类型键的字段个数 小于 `hash-max-listpack-entries`且每个字段名和字段值的长度 小于 `hash-max-listpack-value` 时，Redis才会使用`OBJ_ENCODING_LISTPACK`来存储该键，前述条件任意一个不满足则会转换为 `OBJ_ENCODING_HT`的编码方式。`hash-max-listpack-entries`：使用压缩列表保存时哈希集合中的最大元素个数。`hash-max-listpack-value`：使用压缩列表保存时哈希集合中单个元素的最大长度



**redis7  Hash类型源码逻辑验证**：

```shell
# redis7的Hash类型使用了listpack和hashtable,但是还保留着ziplist的配置
127.0.0.1:6379> config get hash*
1)"hash-max-ziplist-value"
2)"64"
3)"hash-max-ziplist-entries"
4)"512"
5)"hash-max-listpack-value"
6)"64"
7)"hash-max-listpack-entries"
8)"512"
127.0.0.1:6379> config set hash-max-listpack-entries 3
OK
127.0.0.1:6379> config set hash-max-listpack-value 5
OK


127.0.0.1:6379> config get hash*
1) "hash-max-ziplist-value"
2) "5"
3) "hash-max-ziplist-entries"
4) "3"
5) "hash-max-listpack-value"
6) "5"
7) "hash-max-listpack-entries"
8) "3"

# 修改了listpack的配置，ziplist的配置也会跟着变化
# 修改了ziplist的配置，listpack的配置也会跟着变化
127.0.0.1:6379> config set hash-max-ziplist-entries 7
OK
127.0.0.1:6379> config set hash-max-ziplist-value 7
OK
127.0.0.1:6379> config get hash*
1)"hash-max-ziplist-value"
2)"7"
3)"hash-max-ziplist-entries"
4)"7"
5)"hash-max-listpack-value"
6)"7"
7)"hash-max-listpack-entries"
8)"7"


127.0.0.1:6379> config set hash- max-listpack- entries 2
OK
127.0.0.1:6379> config set hash- max-listpack- value 4
OK
127.0.0.1:6379> hset user01 id 1 name z3
(integer) 2

127.0.0.1:6379> 0BJECT ENCODING user01
"listpack"
127.0.0.1:6379> hset user01 id 1 name z3 age 22
(integer) 1
127.0.0.1:6379> 0BJECT ENCODING user01
"hashtable"
```

**redis7  Hash类型源码结论**：

- 默认配置下，哈希对象保存的键值对数量小于512个。所有的键值对的健和值的字符串长度都小于等于64byte (一个英文字母一个字节)时用listpack，反之用hashtable
- listpack升级到hashtable可以，反过来降级不可以



**redis7  Hash类型源码逻辑**：逻辑和redis6一样，只不过ziplist修改为listpack

![image-20240105055243593](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220422028.png)

**创建哈希对象**。`createHashObject`函数返回类型为robj指针。函数内部首先声明了一个名为zl的无符号字符指针，然后调用了lpNew(0)函数来分配内存空间。接着，通过调用createObject函数创建了一个类型为OBJ_HASH的对象o，并将刚刚分配的内存空间zl作为参数传递给了这个对象。最后，设置了对象o的编码方式为OBJ_ENCODING_LISTPACK，并将对象o返回

```c
// 源码位置：redis-7.0.5\src\object.c
robj *createHashObject(void) {
    unsigned char *zl = lpNew(0);
    robj *o = createObject(OBJ_HASH, zl);
    o->encoding = OBJ_ENCODING_LISTPACK;
    return o;
}

/* ===================== Creation and parsing of objects ==================== */

robj *createObject(int type, void *ptr) {
    robj *o = zmalloc(sizeof(*o));
    o->type = type;
    o->encoding = OBJ_ENCODING_RAW;
    o->ptr = ptr;
    o->refcount = 1;

    /* Set the LRU to the current lruclock (minutes resolution), or
     * alternatively the LFU counter. */
    if (server.maxmemory_policy & MAXMEMORY_FLAG_LFU) {
        o->lru = (LFUGetTimeInMinutes()<<8) | LFU_INIT_VAL;
    } else {
        o->lru = LRU_CLOCK();
    }
    return o;
}
```

**创建一个新的空列表（listpack）**。`lpNew`函数的作用是使用提供的内存容量创建一个新的列表，如果成功则返回新的列表，否则返回错误。在函数中，首先使用lp_malloc函数分配内存，分配的大小至少为给定容量和6（LP_HDR_SIZE）的较大值。如果分配失败，则返回空指针。之后设置列表的总字节数为LP_HDR_SIZE+1，元素个数为0，并将第6个字节（LP_HDR_SIZE）的值设为LP_EOF。最后返回创建的列表。`lpNew`函数创建了一个空的 listpack，一开始分配的大小是 LP_HDR_SIZE 再加 1 个字节。LP_HDR_SIZE 宏定义是在 listpack.c 中，它默认是 6 个字节，其中 4 个字节是记录 listpack 的总字节数，2 个字节是记录 listpack 的元素数量。此外，listpack 的最后一个字节是用来标识 listpack 的结束，其默认值是宏定义 LP_EOF。和 ziplist 列表项的结束标记一样，LP_EOF 的值也是 255

```c
// 源码位置：redis-7.0.5\src\listpack.c
#define LP_HDR_SIZE 6       /* 32 bit total len + 16 bit number of elements. */

/* Create a new, empty listpack.
 * On success the new listpack is returned, otherwise an error is returned.
 * Pre-allocate at least `capacity` bytes of memory,
 * over-allocated memory can be shrunk by `lpShrinkToFit`.
 * */
unsigned char *lpNew(size_t capacity) {
    unsigned char *lp = lp_malloc(capacity > LP_HDR_SIZE+1 ? capacity : LP_HDR_SIZE+1);
    if (lp == NULL) return NULL;
    lpSetTotalBytes(lp,LP_HDR_SIZE+1);
    lpSetNumElements(lp,0);
    lp[LP_HDR_SIZE] = LP_EOF;
    return lp;
}
```



#####   12.5.5  redis7 listpack紧凑列表源码分析

listpack官网说明：`https://github.com/antirez/listpack/blob/master/listpack.md`

listpack由4部分组成：`total Bytes`、`Num Elem`、`Entry`以及`End`

- `Total Bytes`：为整个listpack的空间大小，占用4个字节，每个listpack最多占用4294967295Bytes
- `num-elements`：为listpack中的元素个数，即Entry的个数占用2个字节
- `element-1~element-N`：为每个具体的元素
- `listpack-end-byte`：为listpack结束标志，占用1个字节，内容为0xFF
- listpack结构图示：

![image-20240105061317790](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220422475.png)

listpack中entry的结构：当前元素的编码类型(entry-encoding)、元素数据(entry-data)、以及编码类型和元素数据这两部分的长度(entry-len)

listpackEntry结构定义：

```c
// 源码位置：redis-7.0.5\src\listpack.h
/* Each entry in the listpack is either a string or an integer. */
typedef struct {
    /* When string is used, it is provided with the length (slen). */
    unsigned char *sval;
    uint32_t slen;
    /* When integer is used, 'sval' is NULL, and lval holds the value. */
    long long lval;
} listpackEntry;
```

ziplist内存布局 与 listpack内存布局对比：

**ziplist内存结构**：

![image-20240105061847736](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220422267.png)



**listpack内存结构**：和ziplist 列表项类似，listpack 列表项也包含了元数据信息和数据本身。不过，为了避免ziplist引起的连锁更新问题，listpack 中的每个列表项，不再像ziplist列表项那样保存其前一个列表项的长度

![image-20240105061908411](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220422781.png)

**redis7使用listpack紧凑列表代替ziplist的原因**：

![image-20240105060739324](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220422270.png)



ziplist的连锁更新问题：压缩列表新增某个元素或修改某个元素时，如果空间不不够，压缩列表占用的内存空间就需要重新分配。而当新插入的元素较大时，可能会导致后续元素的 prevlen 占用空间都发生变化，从而引起「连锁更新」问题，导致每个元素的空间都要重新分配，造成访问压缩列表性能的下降。

案例说明：压缩列表每个节点正因为需要保存前一个节点的长度字段，就会有连锁更新的隐患
第一步：现在假设一个压缩列表中有多个连续的、长度在 250～253 之间的节点，如下图：



![image-20240105060434991](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220422011.png)

因为这些节点长度值小于 254 字节，所以 prevlen 属性需要用 1 字节的空间来保存这个长度值，一切OK

第二步：这时，如果将一个长度大于等于 254 字节的新节点加入到压缩列表的表头节点，即新节点将成为entry1的前置节点，如下图：

![image-20240105060526405](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220422636.png)



因为entry1节点的prevlen属性只有1个字节大小，无法保存新节点的长度，此时就需要对压缩列表的空间重分配操作并将entry1节点的prevlen 属性从原来的 1 字节大小扩展为 5 字节大小

第三步：连续更新问题出现

![image-20240105060627629](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220423172.png)



entry1节点原本的长度在250～253之间，因为刚才的扩展空间，此时entry1节点的长度就大于等于254，因此原本entry2节点保存entry1节点的 prevlen属性也必须从1字节扩展至5字节大小。entry1节点影响entry2节点，entry2节点影响entry3节点......一直持续到结尾。这种在特殊情况下产生的连续多次空间扩展操作就叫做「连锁更新」



**结论**：listpack 是 Redis 设计用来取代掉 ziplist 的数据结构，它通过每个节点记录自己的长度且放在节点的尾部，来彻底解决掉了 ziplist 存在的连锁更新的问题。在 listpack 中，因为每个列表项只记录自己的长度，而不会像 ziplist 中的列表项那样，会记录前一项的长度。所以，当在 listpack 中新增或修改元素时，实际上只会涉及每个列表项自己的操作，而不会影响后续列表项的长度变化，这就避免了连锁更新



### 12.6  List数据结构介绍

**List数据结构**：redis List类型的底层是quicklist，在redis6中quicklist的内部节点是压缩列表ziplist，而在redis7中quicklist的内部节点是紧凑列表listpack

#####  12.6.1 redis6 List类型源码

在Redis3.0之前，list采用的底层数据结构是ziplist压缩列表+linkedList双向链表。当列表对象中元素的长度比较小或者数量比较少的时候，采用压缩列表ziplist来存储。当列表对象中元素的长度比较大或者数量比较多的时候，则会转而使用双向列表linkedlist来存储。然后在高版本的Redis中底层数据结构是quicklist(替换了ziplist+linkedList)，而quicklist也用到了ziplist。quicklist就是「双向链表 + 压缩列表」组合，因为一个 quicklist 就是一个链表，而链表中的每个元素又是一个压缩列表。ziplist优点：内存紧凑，访问效率高，缺点是更新效率低，并且数据量较大时，可能导致大量的内存复制。linkedlist优点：节点修改的效率高，但是需要额外的内存开销，并且节点较多时，会产生大量的内存碎片。为了结合两者的优点，在redis 3.2之后，list的底层实现变为快速列表quicklist

Redis6版本List的编码格式：list用quicklist来存储，quicklist存储了一个双向链表，每个节点都是一个ziplist

![image-20240105062642012](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220423817.png)



quicklist是ziplist和linkedlist的结合体。quicklist 实际上是 zipList 和 linkedList 的混合体，它将 linkedList按段切分，每一段使用 zipList 来紧凑存储，多个 zipList 之间使用双向指针串接起来

![image-20240105062948115](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220423500.png)

**quicklist源码分析**：quicklist快速列表是一个 40 字节的结构体。count是所有条目的总数。len是快速列表节点的数量。compress：如果关闭压缩则为0，否则它表示在快速列表末端保留未压缩的quicklistNodes的数量。fill表示用户请求（或默认）的填充因子。bookmarks是可选特性，用于重新分配此结构体时使用，以避免在未使用时占用内存

```c
// 源码位置：redis-6.2.13\src\quicklist.h
/* quicklist is a 40 byte struct (on 64-bit systems) describing a quicklist.
 * 'count' is the number of total entries.
 * 'len' is the number of quicklist nodes.
 * 'compress' is: 0 if compression disabled, otherwise it's the number
 *                of quicklistNodes to leave uncompressed at ends of quicklist.
 * 'fill' is the user-requested (or default) fill factor.
 * 'bookmakrs are an optional feature that is used by realloc this struct,
 *      so that they don't consume memory when not used. */
typedef struct quicklist {
    quicklistNode *head;
    quicklistNode *tail;
    unsigned long count;        /* total count of all entries in all ziplists */
    unsigned long len;          /* number of quicklistNodes */
    int fill : QL_FILL_BITS;              /* fill factor for individual nodes */
    unsigned int compress : QL_COMP_BITS; /* depth of end nodes not to compress;0=off */
    unsigned int bookmark_count: QL_BM_BITS;
    quicklistBookmark bookmarks[];
} quicklist;
```

![image-20240105063339646](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220423522.png)



**quicklistNode源码**：

![image-20240105063538327](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220423919.png)

```c
// 源码位置：redis-6.2.13\src\quicklist.h
/* quicklistNode is a 32 byte struct describing a ziplist for a quicklist.
 * We use bit fields keep the quicklistNode at 32 bytes.
 * count: 16 bits, max 65536 (max zl bytes is 65k, so max count actually < 32k).
 * encoding: 2 bits, RAW=1, LZF=2.
 * container: 2 bits, NONE=1, ZIPLIST=2.
 * recompress: 1 bit, bool, true if node is temporary decompressed for usage.
 * attempted_compress: 1 bit, boolean, used for verifying during testing.
 * extra: 10 bits, free for future use; pads out the remainder of 32 bits */
typedef struct quicklistNode {
    struct quicklistNode *prev;
    struct quicklistNode *next;
    unsigned char *zl;
    unsigned int sz;             /* ziplist size in bytes */
    unsigned int count : 16;     /* count of items in ziplist */
    unsigned int encoding : 2;   /* RAW==1 or LZF==2 */
    unsigned int container : 2;  /* NONE==1 or ZIPLIST==2 */
    unsigned int recompress : 1; /* was this node previous compressed? */
    unsigned int attempted_compress : 1; /* node can't compress; too small */
    unsigned int extra : 10; /* more bits to steal for future usage */
} quicklistNode;
```

**quicklist数据结构示意图**：quicklist的节点quicklistNode的*zl指针指向一个ziplist,一个ziplist可以存放多个元素

![image-20240105063708026](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220423136.png)





**push命令源码**：

`pushGenericCommand`函数分析：

1. 定义了 `pushGenericCommand` 函数，它接受参数 `client *c`（客户端指针）、`int where`（位置），`int xx`
2. 首先，通过循环遍历给定参数，检查是否有元素过大，若有则返回错误信息并退出
3. 之后，通过 `lookupKeyWrite` 函数查找并写入指定键对应的值对象 `robj`，并进行类型检查
4. 如果键不存在且指定参数 `xx` 为真，则返回 0，并结束流程
5. 否则，创建一个 quicklist 对象 `lobj`，设定相关选项，并将其加入数据库
6. 之后，再次循环给定参数，使用 `listTypePush` 函数将元素推入列表，并对数据库进行标记
7. 最后，发送列表的长度，并发送相应的信号及事件通知

具体命令函数：

- `lpushCommand` 函数调用 `pushGenericCommand` 在列表头部进行推送
- `rpushCommand` 函数调用 `pushGenericCommand` 在列表尾部进行推送
- `lpushxCommand` 函数调用 `pushGenericCommand` 在列表头部进行推送，但只在键存在时执行
- `rpushxCommand` 函数调用 `pushGenericCommand` 在列表尾部进行推送，但只在键存在时执行

```c
// 源码位置：redis-6.2.13\src\t_list.c
/*-----------------------------------------------------------------------------
 * List Commands
 *----------------------------------------------------------------------------*/

/* Implements LPUSH/RPUSH/LPUSHX/RPUSHX. 
 * 'xx': push if key exists. */
void pushGenericCommand(client *c, int where, int xx) {
    int j;

    for (j = 2; j < c->argc; j++) {
        if (sdslen(c->argv[j]->ptr) > LIST_MAX_ITEM_SIZE) {
            addReplyError(c, "Element too large");
            return;
        }
    }

    robj *lobj = lookupKeyWrite(c->db, c->argv[1]);
    if (checkType(c,lobj,OBJ_LIST)) return;
    if (!lobj) {
        if (xx) {
            addReply(c, shared.czero);
            return;
        }

        lobj = createQuicklistObject();
        quicklistSetOptions(lobj->ptr, server.list_max_ziplist_size,
                            server.list_compress_depth);
        dbAdd(c->db,c->argv[1],lobj);
    }

    for (j = 2; j < c->argc; j++) {
        listTypePush(lobj,c->argv[j],where);
        server.dirty++;
    }

    addReplyLongLong(c, listTypeLength(lobj));

    char *event = (where == LIST_HEAD) ? "lpush" : "rpush";
    signalModifiedKey(c,c->db,c->argv[1]);
    notifyKeyspaceEvent(NOTIFY_LIST,event,c->argv[1],c->db->id);
}

/* LPUSH <key> <element> [<element> ...] */
void lpushCommand(client *c) {
    pushGenericCommand(c,LIST_HEAD,0);
}

/* RPUSH <key> <element> [<element> ...] */
void rpushCommand(client *c) {
    pushGenericCommand(c,LIST_TAIL,0);
}

/* LPUSHX <key> <element> [<element> ...] */
void lpushxCommand(client *c) {
    pushGenericCommand(c,LIST_HEAD,1);
}

/* RPUSH <key> <element> [<element> ...] */
void rpushxCommand(client *c) {
    pushGenericCommand(c,LIST_TAIL,1);
}
```

**redis6 list相关配置**：

```shell
# list相关配置
127.0.0.1:6379> config get list*
1)"list-max-ziplist-size"
2)"-2"
3)"list-compress-depth"
4)"0"
127.0.0.1:6379> type list1
list
```

**`list-compress-depth`（ziplist压缩配置）**：表示一个quicklist两端不被压缩的节点个数。这里的节点是指quicklist双向链表的节点，而不是指ziplist里面的数据项个数。参数`list-compress-depth`的取值含义如下：

- 0: 是个特殊值，表示都不压缩。这是Redis的默认值
- 1: 表示quicklist两端各有1个节点不压缩，中间的节点压缩
- 2: 表示quicklist两端各有2个节点不压缩，中间的节点压缩
- 3: 表示quicklist两端各有3个节点不压缩，中间的节点压缩
- 依此类推

**` list-max-ziplist-size`（ziplist中entry配置）**：当取正值的时候，表示按照数据项个数来限定每个quicklist节点上的ziplist长度。比如，当这个参数配置成5的时候，表示每个quicklist节点的ziplist最多包含5个数据项。当取负值的时候，表示按照占用字节数来限定每个quicklist节点上的ziplist长度。这时，它只能取-1到-5这五个值，每个值含义如下：

- -5: 每个quicklist节点上的ziplist大小不能超过64 Kb（注：1kb => 1024 bytes）
- -4: 每个quicklist节点上的ziplist大小不能超过32 Kb
- -3: 每个quicklist节点上的ziplist大小不能超过16 Kb
- -2: 每个quicklist节点上的ziplist大小不能超过8 Kb（-2是Redis给出的默认值）
- -1: 每个quicklist节点上的ziplist大小不能超过4 Kb

#####  12.6.2 redis7 List类型源码

**Redis7的List底层源码**：list用quicklist实现，quicklist存储了一个双向链表，每个节点都是一个listpack。quicklist是listpack和linkedlist的结合体。在7.0版本listpack紧凑列表替代了ziplist，在7.0版本已经没有 ziplist 的配置了（6.0版本仅部分数据类型作为过渡阶段在使用）。Redis7使用listpack替换了ziplist，其他结构基本没变

```shell
127.0.0.1:6379> config get list*
1) "list-compress-depth"
2) "0"
3) "list-max-ziplist-size"
4) "-2"
5) "list-max-listpack-size
6) "-2"
```

**push命令源码**：Redis7  List 类型的源码与 Redis6的代码相比，Redis 7 中的`quicklistSetOptions` 函数的参数 `server.list_max_ziplist_size` 变更为 `server.list_max_listpack_size`，说明Redis7使用listpack替换了ziplist

![image-20240105070415942](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220423411.png)



```c
// 源码位置：redis-7.0.5\src\t_list.c
/*-----------------------------------------------------------------------------
 * List Commands
 *----------------------------------------------------------------------------*/

/* Implements LPUSH/RPUSH/LPUSHX/RPUSHX. 
 * 'xx': push if key exists. */
void pushGenericCommand(client *c, int where, int xx) {
    int j;

    robj *lobj = lookupKeyWrite(c->db, c->argv[1]);
    if (checkType(c,lobj,OBJ_LIST)) return;
    if (!lobj) {
        if (xx) {
            addReply(c, shared.czero);
            return;
        }

        lobj = createQuicklistObject();
        quicklistSetOptions(lobj->ptr, server.list_max_listpack_size,
                            server.list_compress_depth);
        dbAdd(c->db,c->argv[1],lobj);
    }

    for (j = 2; j < c->argc; j++) {
        listTypePush(lobj,c->argv[j],where);
        server.dirty++;
    }

    addReplyLongLong(c, listTypeLength(lobj));

    char *event = (where == LIST_HEAD) ? "lpush" : "rpush";
    signalModifiedKey(c,c->db,c->argv[1]);
    notifyKeyspaceEvent(NOTIFY_LIST,event,c->argv[1],c->db->id);
}

/* LPUSH <key> <element> [<element> ...] */
void lpushCommand(client *c) {
    pushGenericCommand(c,LIST_HEAD,0);
}

/* RPUSH <key> <element> [<element> ...] */
void rpushCommand(client *c) {
    pushGenericCommand(c,LIST_TAIL,0);
}

/* LPUSHX <key> <element> [<element> ...] */
void lpushxCommand(client *c) {
    pushGenericCommand(c,LIST_HEAD,1);
}

/* RPUSH <key> <element> [<element> ...] */
void rpushxCommand(client *c) {
    pushGenericCommand(c,LIST_TAIL,1);
}
```

`object.c`：

```c
// 源码位置：redis-6.2.13\src\object.c
robj *createQuicklistObject(void) {
    quicklist *l = quicklistCreate();
    robj *o = createObject(OBJ_LIST,l);
    o->encoding = OBJ_ENCODING_QUICKLIST;
    return o;
}
```



###  12.7  set数据结构介绍

**set数据结构源码逻辑**：Redis用intset或hashtable存储set。如果元素都是整数类型，就用intset存储。如果不是整数类型，就用hashtable（数组+链表的存来储结构）。key就是元素的值，value为null。集合元素都是longlong类型并且元素个数<=`set-max-intset-entries`编码就是intset，反之就是hashtable。`set-max-intset-entries` 是 Redis 中用于优化整数集合（intset）数据结构的参数。整数集合是一种数据结构，用于存储整数值，其设计旨在节省内存空间。这个参数的作用是设置当往整数集合中添加新元素时，触发转换为普通集合（hash table）的阈值。当整数集合的元素个数超过了 `set-max-intset-entries` 参数设定的值时，Redis 会将整数集合转换为普通集合。这样做是为了在整数集合的大小适中时获得更好的内存效率，避免对于存储空间过大或者过小而浪费内存或者造成性能问题

**set数据结构源码逻辑验证**：

```shell
#  Set的两种编码格式是intset和hashtable
#  集合元素都是longlong类型
#  元素个数<=set-max-intset-entries编码就是intset,反之就是hashtable
127.0.0.1:6379> config get set*
1)"set- max-intset-entries"
2)"512"
127.0.0.1:6379> config set set-max-intset-entries 3
OK
127.0.0.1:6379> config get set*
1) "set-max-intset-entries
2) "1"
127.0.0.1:6379> sadd set1 123
(integer) 1
127.0.0.1:6379> object encoding set1
"intset" 
127.0.0.1:6379> sadd set1 a b c d
(integer) 4
127.0.0.1:6379> object encoding set1
"hashtable"
127.0.0.1: 6379> sadd set2 9223372036854775807
(integer) 1
127.0.0.1:6379> object encoding set2
"intset"
127.0.0.1:6379> sadd set2 9223372036854775808
(integer) 1
127.0.0.1:6379> object encoding set2
"hashtable"
```

**set源码分析**：`saddCommand` 函数首先检查输入指定的集合是否存在，如果不存在，则创建一个新的集合对象。接下来循环遍历要添加到集合中的元素，并通过 `setTypeAdd` 函数实现实际的添加操作。在 `setTypeAdd` 函数中，如果集合的编码是基于哈希表（OBJ_ENCODING_HT），则将新元素添加到哈希表表示的集合中；如果编码是基于整数集合（OBJ_ENCODING_INTSET），则将元素添加到整数集合中，并在需要时将其转换为哈希表表示的集合

```c
// 源码位置：t_set.c
void saddCommand(client *c) {
    robj *set;
    int j, added = 0;

    set = lookupKeyWrite(c->db,c->argv[1]);
    if (checkType(c,set,OBJ_SET)) return;
    
    if (set == NULL) {
        set = setTypeCreate(c->argv[2]->ptr);
        dbAdd(c->db,c->argv[1],set);
    }

    for (j = 2; j < c->argc; j++) {
        if (setTypeAdd(set,c->argv[j]->ptr)) added++;
    }
    if (added) {
        signalModifiedKey(c,c->db,c->argv[1]);
        notifyKeyspaceEvent(NOTIFY_SET,"sadd",c->argv[1],c->db->id);
    }
    server.dirty += added;
    addReplyLongLong(c,added);
}


/* Add the specified value into a set.
 *
 * If the value was already member of the set, nothing is done and 0 is
 * returned, otherwise the new element is added and 1 is returned. */
int setTypeAdd(robj *subject, sds value) {
    long long llval;
    if (subject->encoding == OBJ_ENCODING_HT) {
        dict *ht = subject->ptr;
        dictEntry *de = dictAddRaw(ht,value,NULL);
        if (de) {
            dictSetKey(ht,de,sdsdup(value));
            dictSetVal(ht,de,NULL);
            return 1;
        }
    } else if (subject->encoding == OBJ_ENCODING_INTSET) {
        if (isSdsRepresentableAsLongLong(value,&llval) == C_OK) {
            uint8_t success = 0;
            subject->ptr = intsetAdd(subject->ptr,llval,&success);
            if (success) {
                /* Convert to regular set when the intset contains
                 * too many entries. */
                size_t max_entries = server.set_max_intset_entries;
                /* limit to 1G entries due to intset internals. */
                if (max_entries >= 1<<30) max_entries = 1<<30;
                if (intsetLen(subject->ptr) > max_entries)
                    setTypeConvert(subject,OBJ_ENCODING_HT);
                return 1;
            }
        } else {
            /* Failed to get integer from object, convert to regular set. */
            setTypeConvert(subject,OBJ_ENCODING_HT);

            /* The set *was* an intset and this value is not integer
             * encodable, so dictAdd should always work. */
            serverAssert(dictAdd(subject->ptr,sdsdup(value),NULL) == DICT_OK);
            return 1;
        }
    } else {
        serverPanic("Unknown set encoding");
    }
    return 0;
}
```

###  12.8  ZSet数据结构介绍

redis6 ZSet数据结构底层实现：ziplist、skiplist

redis7 ZSet数据结构底层实现：listpack、skiplist

#####   12.8.1 redis6 zset源码分析

 **redis6 zset源码逻辑**：当有序集合中包含的元素数量超过服务器属性 `zset_max_ziplist_entries` 的值（默认值为 128 ），或者有序集合中新添加元素的 member 的长度大于服务器属性 `zset_max_ziplist_value` 的值（默认值为 64 ）时，redis会使用skiplist跳跃表作为有序集合的底层实现，否则会使用ziplist作为有序集合的底层实现

**redis6 zset源码逻辑验证**：

```c
127.0.0.1:6379> config get zset*
1)"zset-max-ziplist-entries"
2)"128"
3)"zset-max-ziplist-value"
4)"64"
127.0.0.1:6379> config set zset-max-ziplist-entries 3
OK
127.0.0.1:6379> config set zset-max-ziplist-value 6
OK
127.0.0.1:6379> config get zset*
1)"zset-max-ziplist-entries"
2)"3"
3)"zset- max- ziplist- value"
4)"6"


127.0.0.1:6379> zadd zset1 80 a 70 b 90 c
(integer) 3
127.0.0.1:6379> object encoding zset1
"ziplist"
127.0.0.1:6379> zadd zset1 100 d
(integer)1
127.0.0.1:6373> object encoding zset1
"skiplist"
127.0.0.1:6379> zadd zset2 80 aaaaa
(integer)1
127.0.0.1:6379> object encoding zset2
"ziplist"
127.0.0.1:6379> zadd zset2 80 aaaaaxxX
(integer)1
127.0.0.1:6379> object encoding zset2
"skiplist"
```

#####  12.8.2  redis7 zset源码分析

 **redis7 zset源码逻辑**：当有序集合中包含的元素数量超过服务器属性 `zset_max_listpack_entries` 的值（默认值为 128 ），或者有序集合中新添加元素的 member 的长度大于服务器属性 `zset_max_listpack_value` 的值（默认值为 64 ）时，redis会使用skiplist跳跃表作为有序集合的底层实现，否则会使用ziplist作为有序集合的底层实现

**redis7 zset源码逻辑验证**：

```shell
127.0.0.1:6379> config get zset*
1)"zset-max-ziplist-value"
2)"64"
3)"zset-max-ziplist-entries"
4)"128"
5)"zset-max-listpack-entries"
6)"128"
7)"zset-max-listpack-value"
8)"64"
127.0.0.1:6379> config set zset-max-listpack-entries 3
OK
127.0.0.1:6379> config set zset-max-listpack-value 4
OK
127.0.0.1:6379> config get zset*
1)"zset-max-ziplist-value"
2)"4"
3)"zset-max-ziplist-entries"
4)"3"
5)"zset-max-listpack-entries"
6)"3"
7)"zset-max-listpack-value"
8)"4"


127.0.0.1:6379> config get zset*
1)"zset-max-ziplist-value"
2)"4"
3)"zset-max-ziplist-entries"
4)"3"
5)"zset-max-listpack-entries"
6)"3"
7)"zset-max-listpack-value"
8)"4"
127.0.0.1:6379> ZADD zset1 80 a 90 b 95 c
(integer) 3
127.0.0.1:6379> type zset1
zset
127.0.0.1:6379> OBJECT ENCODING zset1
"listpack"
127.0.0.1:6379> zadd zset1 100 d
(inteqer)1
127.0.0.1:6379> OBJECT ENCODING zset1
"skiplist"
```

**redis7源码**：

```c
// 源码分析：t_zset.c
/* Add a new element or update the score of an existing element in a sorted
 * set, regardless of its encoding.
 *
 * The set of flags change the command behavior. 
 *
 * The input flags are the following:
 *
 * ZADD_INCR: Increment the current element score by 'score' instead of updating
 *            the current element score. If the element does not exist, we
 *            assume 0 as previous score.
 * ZADD_NX:   Perform the operation only if the element does not exist.
 * ZADD_XX:   Perform the operation only if the element already exist.
 * ZADD_GT:   Perform the operation on existing elements only if the new score is 
 *            greater than the current score.
 * ZADD_LT:   Perform the operation on existing elements only if the new score is 
 *            less than the current score.
 *
 * When ZADD_INCR is used, the new score of the element is stored in
 * '*newscore' if 'newscore' is not NULL.
 *
 * The returned flags are the following:
 *
 * ZADD_NAN:     The resulting score is not a number.
 * ZADD_ADDED:   The element was added (not present before the call).
 * ZADD_UPDATED: The element score was updated.
 * ZADD_NOP:     No operation was performed because of NX or XX.
 *
 * Return value:
 *
 * The function returns 1 on success, and sets the appropriate flags
 * ADDED or UPDATED to signal what happened during the operation (note that
 * none could be set if we re-added an element using the same score it used
 * to have, or in the case a zero increment is used).
 *
 * The function returns 0 on error, currently only when the increment
 * produces a NAN condition, or when the 'score' value is NAN since the
 * start.
 *
 * The command as a side effect of adding a new element may convert the sorted
 * set internal encoding from listpack to hashtable+skiplist.
 *
 * Memory management of 'ele':
 *
 * The function does not take ownership of the 'ele' SDS string, but copies
 * it if needed. */
int zsetAdd(robj *zobj, double score, sds ele, int in_flags, int *out_flags, double *newscore) {
    /* Turn options into simple to check vars. */
    int incr = (in_flags & ZADD_IN_INCR) != 0;
    int nx = (in_flags & ZADD_IN_NX) != 0;
    int xx = (in_flags & ZADD_IN_XX) != 0;
    int gt = (in_flags & ZADD_IN_GT) != 0;
    int lt = (in_flags & ZADD_IN_LT) != 0;
    *out_flags = 0; /* We'll return our response flags. */
    double curscore;

    /* NaN as input is an error regardless of all the other parameters. */
    if (isnan(score)) {
        *out_flags = ZADD_OUT_NAN;
        return 0;
    }

    /* Update the sorted set according to its encoding. */
    if (zobj->encoding == OBJ_ENCODING_LISTPACK) {
        unsigned char *eptr;

        if ((eptr = zzlFind(zobj->ptr,ele,&curscore)) != NULL) {
            /* NX? Return, same element already exists. */
            if (nx) {
                *out_flags |= ZADD_OUT_NOP;
                return 1;
            }

            /* Prepare the score for the increment if needed. */
            if (incr) {
                score += curscore;
                if (isnan(score)) {
                    *out_flags |= ZADD_OUT_NAN;
                    return 0;
                }
            }

            /* GT/LT? Only update if score is greater/less than current. */
            if ((lt && score >= curscore) || (gt && score <= curscore)) {
                *out_flags |= ZADD_OUT_NOP;
                return 1;
            }

            if (newscore) *newscore = score;

            /* Remove and re-insert when score changed. */
            if (score != curscore) {
                zobj->ptr = zzlDelete(zobj->ptr,eptr);
                zobj->ptr = zzlInsert(zobj->ptr,ele,score);
                *out_flags |= ZADD_OUT_UPDATED;
            }
            return 1;
        } else if (!xx) {
            /* check if the element is too large or the list
             * becomes too long *before* executing zzlInsert. */
            if (zzlLength(zobj->ptr)+1 > server.zset_max_listpack_entries ||
                sdslen(ele) > server.zset_max_listpack_value ||
                !lpSafeToAdd(zobj->ptr, sdslen(ele)))
            {
                zsetConvert(zobj,OBJ_ENCODING_SKIPLIST);
            } else {
                zobj->ptr = zzlInsert(zobj->ptr,ele,score);
                if (newscore) *newscore = score;
                *out_flags |= ZADD_OUT_ADDED;
                return 1;
            }
        } else {
            *out_flags |= ZADD_OUT_NOP;
            return 1;
        }
    }

    /* Note that the above block handling listpack would have either returned or
     * converted the key to skiplist. */
    if (zobj->encoding == OBJ_ENCODING_SKIPLIST) {
        zset *zs = zobj->ptr;
        zskiplistNode *znode;
        dictEntry *de;

        de = dictFind(zs->dict,ele);
        if (de != NULL) {
            /* NX? Return, same element already exists. */
            if (nx) {
                *out_flags |= ZADD_OUT_NOP;
                return 1;
            }

            curscore = *(double*)dictGetVal(de);

            /* Prepare the score for the increment if needed. */
            if (incr) {
                score += curscore;
                if (isnan(score)) {
                    *out_flags |= ZADD_OUT_NAN;
                    return 0;
                }
            }

            /* GT/LT? Only update if score is greater/less than current. */
            if ((lt && score >= curscore) || (gt && score <= curscore)) {
                *out_flags |= ZADD_OUT_NOP;
                return 1;
            }

            if (newscore) *newscore = score;

            /* Remove and re-insert when score changes. */
            if (score != curscore) {
                znode = zslUpdateScore(zs->zsl,curscore,ele,score);
                /* Note that we did not removed the original element from
                 * the hash table representing the sorted set, so we just
                 * update the score. */
                dictGetVal(de) = &znode->score; /* Update score ptr. */
                *out_flags |= ZADD_OUT_UPDATED;
            }
            return 1;
        } else if (!xx) {
            ele = sdsdup(ele);
            znode = zslInsert(zs->zsl,score,ele);
            serverAssert(dictAdd(zs->dict,ele,&znode->score) == DICT_OK);
            *out_flags |= ZADD_OUT_ADDED;
            if (newscore) *newscore = score;
            return 1;
        } else {
            *out_flags |= ZADD_OUT_NOP;
            return 1;
        }
    } else {
        serverPanic("Unknown sorted set encoding");
    }
    return 0; /* Never reached. */
}
```

#####  12.8.3  skiplist跳表

单链表的缺陷：对于一个单链表来讲，即便链表中存储的数据是有序的，如果想在其中查找某个数据，也只能从头到尾遍历链表。这样查找效率就会很低，时间复杂度会很高O(N)

![image-20240106104004506](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220423174.png)

优化思路：给链表加个索引

![image-20240106104136861](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220423408.png)

加一层索引之后，查找一个结点需要遍历的结点个数减少了，查找效率得到提升

![image-20240106104252894](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220423834.png)

建立五级索引，查询效率进一步提升。—个包含64个结点的链表，按照前面的思路，建立如下索引结构

![image-20240106105608575](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220423709.png)

**skiplist跳表**：skiplist是一种以空间换取时间的结构，是可以实现二分查找的有序链表。由于链表，无法进行二分查找，因此借鉴数据库索引的思想，提取出链表中关键节点（索引），先在关键节点上查找，再进入下层链表查找，提取多层关键节点，就形成了跳跃表。由于索引也要占据一定空间的，所以，索引添加的越多，空间占用的越多。总结：跳表 =  链表＋多级索引。跳表时间复杂度是O(logN)，空间复杂度是O(N)

**跳表的时间复杂度分析**：

跳表查询的时间复杂度分析，如果链表里有N个结点，会有多少级索引呢？
按照前面讲的，两两取首。每两个结点会抽出一个结点作为上一级索引的结点，以此估算：
第一级索引的结点个数大约就是n/2，
第二级索引的结点个数大约就是n/4，
第三级索引的结点个数大约就是n/8，依次类推......
也就是说，第k级索引的结点个数是第k-1级索引的结点个数的1/2，那第k级索引结点的个数就是n/(2^k)
假设索引有h级，最高级的索引有2个结点。通过上面的公式，
可以得到n/(2h)=2，从而求得h=(log2)n-1
如果包含原始链表这一层，整个跳表的高度就是(log2)n



![image-20240106110030031](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220423501.png)

**跳表的空间复杂度分析**：

比起单纯的单链表，跳表需要存储多级索引，肯定要消耗更多的存储空间。那到底需要消耗多少额外的存储空间呢？
来分析一下跳表的空间复杂度
第一步：首先原始链表长度为n
第二步：两两取首，每层索引的结点数：n/2, n/4, n/8 ... , 8, 4, 2 每上升一级就减少一半，直到剩下2个结点,以此类推；如果把每层索引的结点数写出来，就是一个等比数列。这几级索引的结点总和就是n/2+n/4+n/8…+8+4+2=n-2。所以，跳表的空间复杂度是O(n) 。也就是说，如果将包含n个结点的单链表构造成跳表，需要额外再用接近n个结点的存储空间



![image-20240106110639660](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220424738.png)

第三步：思考三三取首，每层索引的结点数：n/3, n/9, n/27 ... , 9, 3, 1 以此类推；第一级索引需要大约n/3个结点，第二级索引需要大约n/9个结点。每往上一级，索引结点个数都除以3。为了方便计算，假设最高一级的索引结点个数是1。把每级索引的结点个数都写下来，也是一个等比数列

![image-20240106110751826](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220424500.png)

通过等比数列求和公式，总的索引结点大约就是n/3+n/9+n/27+…+9+3+1=n/2。尽管空间复杂度还是O(n) ，但比上面的每两个结点抽一个结点的索引构建方法，要减少了一半的索引结点存储空间。所以空间复杂度是O(n)

**跳表的优点**：跳表是一个最典型的空间换时间解决方案，而且只有在数据量较大的情况下才能体现出来优势。而且应该是读多写少的情况下才能使用，所以它的适用范围应该还是比较有限的

**跳表的缺点** ：维护成本相对要高，在单链表中，一旦定位好要插入的位置，插入结点的时间复杂度是很低的，就是O(1) 。新增或者删除时需要把所有索引都更新一遍，为了保证原始链表中数据的有序性，需要先找到要修改的位置，这个查找操作就会比较耗时最后在新增和删除的过程中的更新，时间复杂度也是O(log n)

###  12.9 五大数据结构源码总结

**redis6 `类型—物理编码—对象` 对应表**：

| 类型         | 编码                      | 对象                                           |
| ------------ | ------------------------- | ---------------------------------------------- |
| REDIS_STRING | REDIS_ENCODING_INT        | 使用整数值实现的字符串对象                     |
| REDIS_STRING | REDIS_ENCODING_EMBSTR     | 使用embstr编码的简单动态字符串实现的字符串对象 |
| REDIS_STRING | REDIS_ENCODING_RAW        | 使用简单动态字符串实现的字符串对象             |
| REDIS_LIST   | REDIS_ENCODING_ZIPLIST    | 使用压缩列表实现的列表对象                     |
| REDIS_LIST   | REDIS_ENCODING_LINKEDLIST | 使用双端链表实现的列表对象                     |
| REDIS_HASH   | REDIS_ENCODING_ZIPLIST    | 使用压缩列表实现的哈希对象                     |
| REDIS_HASH   | REDIS_ENCODING_HT         | 使用字典实现的哈希对象                         |
| REDIS_SET    | REDIS_ENCODING_INTSET     | 使用整数集合实现的集合对象                     |
| REDIS_SET    | REDIS_ENCODING_HT         | 使用字典实现的集合对象                         |
| REDIS_ZSET   | REDIS_ENCODING_ZIPLIST    | 使用压缩列表实现的有序集合对象                 |
| REDIS_ZSET   | REDIS_ENCODING_SKIPLIST   | 使用跳跃表和字典实现的有序集合对象             |

**redis6数据类型对应的底层数据结构**：

1.**字符串**
int：8个字节的长整型
embstr：小于等于44个字节的字符串
raw：大于44个字节的字符串
Redis会根据当前值的类型和长度决定使用哪种内部编码实现

2.**哈希**
ziplist(压缩列表)：当哈希类型元素个数小于`hash-max-ziplist-entries` 配置(默认512个)、同时所有值都小于`hash-max-ziplist-value`配置(默认64 字节)时，Redis会使用ziplist作为哈希的内部实现，ziplist使用更加紧凑的 结构实现多个元素的连续存储，所以在节省内存方面比hashtable更加优秀。hashtable(哈希表)：当哈希类型无法满足ziplist的条件时，Redis会使 用hashtable作为哈希的内部实现，因为此时ziplist的读写效率会下降，而hashtable的读写时间复杂度为O(1)

 

3.**列表**
ziplist(压缩列表)：当列表的元素个数小于`list-max-ziplist-entries`配置 (默认512个)，同时列表中每个元素的值都小于`list-max-ziplist-value`配置时 (默认64字节)，Redis会选用ziplist来作为列表的内部实现来减少内存的使用。linkedlist(链表)：当列表类型无法满足ziplist的条件时，Redis会使用 linkedlist作为列表的内部实现。quicklist是ziplist和linkedlist的结合，且以ziplist为节点的链表(linkedlist)



4.**集合**
intset(整数集合)：当集合中的元素都是整数且元素个数小于`set-max-intset-entries`配置(默认512个)时，Redis会用intset来作为集合的内部实现，从而减少内存的使用。hashtable(哈希表)：当集合类型无法满足intset的条件时，Redis会使用hashtable作为集合的内部实现

5.**有序集合**
ziplist(压缩列表)：当有序集合的元素个数小于`zset-max-ziplist- entries`配置(默认128个)，同时每个元素的值都小于`zset-max-ziplist-value`配置(默认64字节)时，Redis会用ziplist来作为有序集合的内部实现，ziplist 可以有效减少内存的使用。skiplist(跳跃表)：当ziplist条件不满足时，有序集合会使用skiplist作 为内部实现，因为此时ziplist的读写效率会下降



**redis6数据类型以及数据结构的关系**：

![image-20240106103405717](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220424289.png)



**redis7数据类型以及数据结构的关系**：

![image-20240106103456140](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220424492.png)

**redis数据类型以及数据结构的时间复杂度**：

| 名称     | 时间复杂度 |
| -------- | ---------- |
| 哈希表   | o(1)       |
| 跳表     | O(logN)    |
| 双向链表 | O(N)       |
| 压缩列表 | O(N)       |
| 整数数组 | O(N)       |



##  13. epoll和IO多路复用深度解析

###  13.1 IO多路复用 | 同步 | 异步 | 阻塞 | 非阻塞

多路复用要解决的问题：高并发多客户端连接，在多路复用之前最简单和典型的方案：同步阻塞网络IO模型。这种模式的特点就是用一个进程来处理一个网络连接(一个用户请求)，比如一段典型的示例。代码如下，直接调用recv函数从一个socket上读取数据

```c
int main()
{
 ...
 //从用户角度来看非常简单，一个recv一用，要接收的数据就得到了
 recv(sock, ...) 
}
```

同步阻塞网络IO模型总结：优点就是非常容易理解，代码实现非常的自然，符合人的直线型思维。缺点就是性能差，每个用户请求到来都得占用一个进程来处理，来一个请求就要分配一个进程跟进处理。类似一个学生配一个老师，一位患者配一个医生。这种方案在计算机系统上是行不通的，进程是一个很笨重的东西，一台服务器上不能创建无限制的任意创建线程。进程在Linux开销较大，先不说创建，光是上下文切换一次就得几个微秒。所以为了高效地对海量用户提供服务，必须要让一个进程能同时处理很多个tcp连接才行。现在假设一个进程保持了10000条连接，那么如何发现哪条连接上有数据可读了、哪条连接可写了？当然可以采用循环遍历的方式来发现IO事件，但这种方式太低级了，非常消耗CPU。我们需要采用更高效的机制，在很多连接中的某条上有IO事件发生的时候直接快速把它找出来。其实Linux 操作系统已经实现了这样一种机制，就是IO多路复用机制，这里的复用指的是对进程的复用



I/O多路复用模型：I/O多路复用实现了用一个进程来处理大量的用户连接。IO多路复用类似一个规范和接口，落地实现可以分select、poll、epoll三种。I/O指的是网络I/O。多路指的是多个客户端TCP连接(连接就是套接字描述符，即socket或者channel)。复用就是用一个进程来处理多条的连接，通过单进程实现同时处理多个客户端的连接。动画演示：

![redisio](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220424015.gif)



Redis的IO多路复用：Redis利用epoll来实现IO多路复用，将连接信息和事件放到队列中，文件事件分派器监听事件队列，事件分派器将事件分发给事件处理器



![image-20240106141004130](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220424463.png)

Redis 是跑在单线程中的，所有的操作都是按照顺序线性执行的，但是由于读写操作等待用户输入或输出都是阻塞的，所以 I/O 操作在一般情况下往往不能直接返回，这会导致某一文件的 I/O 阻塞导致整个进程无法对其它客户提供服务，而 I/O 多路复用就是为了解决这个问题而出现。所谓 I/O 多路复用机制，就是通过一种机制，可以监视多个描述符，一旦某个描述符就绪（一般是读就绪或写就绪），能够通知程序进行相应的读写操作。这种机制的使用需要 select 、 poll 、 epoll 来配合。多个连接共用一个阻塞对象，应用程序只需要在一个阻塞对象上等待，无需阻塞等待所有连接。当某条连接有新的数据可以处理时，操作系统通知应用程序，线程从阻塞状态返回，开始进行业务处理。 Redis 服务采用 Reactor 的方式来实现文件事件处理器（每一个网络连接其实都对应一个文件描述符） Redis基于Reactor模式开发了网络事件处理器，这个处理器被称为文件事件处理器。文件事件处理器的组成结构为4部分：多个套接字、IO多路复用程序、文件事件分派器、事件处理器。因为文件事件分派器队列的消费是单线程的，所以Redis才叫单线程模型

 

文件事件：Redis基于Reactor模式开发了自己的网络事件处理器，这个处理器被称为文件事件处理器(file event handler) 。文件事件处理器使用I/O多路复用(multiplexing）程序来同时监听多个套接字，并根据套接字目前执行的任务来为套接字关联不同的事件处理器。当被监听的套接字准备好执行连接应答(accept)、读取(read) 、写入(write) 、关闭(close)等操作时，与操作相对应的文件事件就会产生，这时文件事件处理器就会调用套接字之前关联好的事件处理器来处理这些事件。虽然文件事件处理器以单线程方式运行，但通过使用IO多路复用程序来监听多个套接字，文件事件处理器既实现了高性能的网络通信模型又可以很好地与Redis服务器中其他同样以单线程方式运行的模块进行对接，这保持了Redis内部单线程设计的简单性（参考《Redis设计与实现》）

从Redis6开始，redis将网络数据读写、请求协议解析通过多个IO线程的来处理 。对于真正的命令执行来说，仍然使用单线程操作

![image-20240106141645279](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220424305.png)



>上午开会，错过了公司食堂的饭点，中午就和公司的首席架构师一起去楼下的米线店去吃米线。我们到了一看，果然很多人在排队。架构师马上发话了：嚯，请求排队啊！你看这位收银点菜的，像不像nginx的反向代理？只收请求，不处理，把请求都发给后厨去处理
>
>我们交了钱，拿着号离开了点餐收银台，找了个座位坐下等餐。架构师：你看，这就是异步处理，我们下了单就可以离开等待，米线做好了会通过小喇叭“回调”我们去取餐；如果同步处理，我们就得在收银台站着等餐，后面的请求无法处理，客户等不及肯定会离开了
>
>接下来架构师盯着手中的纸质号牌。架构师：你看，这个纸质号牌在后厨“服务器”那里也有，这不就是表示会话的ID吗？有了它就可以把大家给区分开，就不会把我的排骨米线送给别人了
>
>过了一会， 排队的人越来越多，已经有人表示不满了，可是收银员已经满头大汗，忙到极致了。架构师：你看他这个系统缺乏弹性扩容， 现在这么多人，应该增加收银台，可以没有其他收银设备，老板再着急也没用。老板看到在收银这里帮不了忙，后厨的订单也累积得越来越多， 赶紧跑到后厨亲自去做米线去了。架构师又发话了：幸亏这个系统的后台有并行处理能力，可以随意地增加资源来处理请求（做米线）。我说：他就这点儿资源了，除了老板没人再会做米线了
>
>不知不觉，我们等了20分钟， 但是米线还没上来。架构师：你看，系统的处理能力达到极限，超时了吧。这时候收银台前排队的人已经不多了，但是还有很多人在等米线。老板跑过来让这个打扫卫生的去收银，让收银小妹也到后厨帮忙。打扫卫生的做收银也磕磕绊绊的，没有原来的小妹灵活。架构师：这就叫服务降级，为了保证米线的服务，把别的服务都给关闭了
>
>又过了20分钟，后厨的厨师叫道：237号， 您点的排骨米线没有排骨了，能换成番茄的吗？架构师低声对我说：瞧瞧， 人太多， 系统异常了。然后他站了起来：不行，系统得进行补偿操作：退费。说完，他拉着我，饿着肚子，头也不回地走了



**同步 | 异步 | 阻塞 | 非阻塞**：

同步：调用者要一直等待调用结果的通知后才能进行后续的执行，现在就要，可以等，等出结果为止。异步：指被调用方先返回应答让调用者先回去，然后再计算调用结果，计算完最终结果后再通知并返回给调用方异步调用要想获得结果，一般通过回调实现。同步与异步的理解：**同步、异步的讨论对象是被调用者(服务提供者)**，重点在于获得调用结果的消息通知方式上

阻塞：调用方一直在等待而且别的事情什么都不做，当前进/线程会被挂起，啥都不干。非阻塞：调用在发出去后，调用方先去忙别的事情，不会阻塞当前进/线程，而会立即返回。阻塞与非阻塞的理解：**阻塞、非阻塞的讨论对象是调用者(服务请求者)**，重点在于等消息时候的行为，调用者是否能干其它事



4种组合方式：

同步阻塞: 服务员说快到你了，先别离开我后台看一眼马上通知你。客户在海底捞火锅前台干等着，啥都不干

同步非阻塞: 服务员说快到你了，先别离开。客户在海底捞火锅前台边刷抖音边等着叫号

异步阻塞：服务员说还要再等等，你先去逛逛，一会儿通知你。客户怕过号在海底捞火锅前台拿着排号小票啥都不干，一直等着店员通知

异步非阻塞：服务员说还要再等等，你先去逛逛，一会儿通知你。拿着排号小票+刷着抖音，等着店员通知







Unix网络编程中的五种lO模型：

Blocking lO-阻塞IO

NoneBlocking lO–非阻塞IO

lO multiplexing - IO多路复用

signal driven lO-信号驱动IO

asynchronous lO-异步IO



###  13.2 BIO(阻塞式I/O)

#####  13.2.1 BIO简介

**BIO（阻塞式I/O）**：当用户进程调用了recvfrom这个系统调用，kernel就开始了IO的第一个阶段：准备数据（对于网络IO来说，很多时候数据在一开始还没有到达。比如，还没有收到一个完整的UDP包。这个时候kernel就要等待足够的数据到来）。这个过程需要等待，也就是说数据被拷贝到操作系统内核的缓冲区中是需要一个过程的。而在用户进程这边，整个进程会被阻塞（当然，是进程自己选择的阻塞）。当kernel一直等到数据准备好了，它就会将数据从kernel中拷贝到用户内存，然后kernel返回结果，用户进程才解除block的状态，重新运行起来。所以，BIO的特点就是**在IO执行的两个阶段都被block**

![image-20240106143024545](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220424129.png)

C语言`recvfrom()`函数：

```
recvfrom()：此函数用于从（已连接）套接口上接收数据，并捕获数据发送源的地址
语言：C语言
函数名：recvfrom()
类型：socket
作用：接收一个数据报并保存源地址
支持协议：UDP/TCP
```

#####  13.2.2 accept监听程序演示

演示accept监听：使用Java中的Socket编程，通过ServerSocket类创建服务端，并且使用Socket类创建客户端。其中`RedisServer`充当服务端，不断监听端口以接受来自客户端的连接；而`RedisClient01`和`RedisClient02`充当客户端，连接到指定的IP地址和端口。通过下面的代码，演示服务端通过`accept`方法监听客户端的连接请求，当有客户端连接时，进行相应处理

- `RedisServer`解释：
  - `main`方法中通过`ServerSocket`对象创建一个服务端Socket实例，监听端口号为6379
  - 通过`accept`方法不断监听连接请求，一旦有客户端连接，则会输出"----- 成功连接"

- `RedisClient01`和`RedisClient02`解释：
  - `main`方法中创建一个Socket实例，连接到本地IP地址"127.0.0.1"的6379端口
- code案例：

`RedisServer`：

```java
package com.zzyy.study.iomultiplex.one;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class RedisServer
{
    public static void main(String[] args) throws IOException
    {
        byte[] bytes = new byte[1024];
        ServerSocket serverSocket = new ServerSocket(6379);
        while(true)
        {
            System.out.println("----- 等待连接");
            Socket socket = serverSocket.accept();
            System.out.println("----- 成功连接");
        }
    }
}
```

`RedisClient01`：

```java
package com.zzyy.study.iomultiplex.one;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
public class RedisClient01
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("------RedisClient01 start");
        Socket socket = new Socket("127.0.0.1", 6379);
    }
}
```

`RedisClient02`：

```java
package com.zzyy.study.iomultiplex.one;
import java.io.IOException;
import java.net.Socket;
public class RedisClient02
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("------RedisClient02 start");
        Socket socket = new Socket("127.0.0.1", 6379);
    }
}
```

#####  13.2.3 read读取程序演示

演示基于传统的阻塞式I/O的Socket通信过程，包括服务端和两个客户端。要演示该示例，首先需启动`RedisServerBIO`，随后再启动`RedisClient01`，然后再启动`RedisClient02`，可以通过在客户端输入的方式向服务端发送数据，并观察服务端的输出，在适当的时候输入"quit"以结束通信。代码示例解释如下：

- `RedisServerBIO`解释：
  1. 在`main`方法中创建一个`ServerSocket`实例并监听6379端口
  2. 通过`accept`方法，进入阻塞，等待客户端连接
  3. 一旦有客户端连接，会输出"-----222 成功连接"，然后获取输入流`inputStream`，并不断尝试读取客户端发送的数据，通过`inputStream.read(bytes)`方法进行阻塞式读取数据，并输出"-----444 成功读取"来验证是否成功读取数据
  4. 最后关闭输入流和Socket

- `RedisClient01`与`RedisClient02`解释：
  1. 在`main`方法中，创建Socket实例，并获取输出流`outputStream`用于向服务端发送数据
  2. 通过`while`循环不断监听用户输入，发送数据给服务端，直到用户输入"quit"为止
  3. 关闭输出流和Socket

`RedisServerBIO`：

```java
package com.zzyy.study.iomultiplex.bio;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class RedisServerBIO
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(6379);
        while(true)
        {
            System.out.println("-----111 等待连接");
            Socket socket = serverSocket.accept();//阻塞1 ,等待客户端连接
            System.out.println("-----222 成功连接");

            InputStream inputStream = socket.getInputStream();
            int length = -1;
            byte[] bytes = new byte[1024];
            System.out.println("-----333 等待读取");
            while((length = inputStream.read(bytes)) != -1)//阻塞2 ,等待客户端发送数据
            {
                System.out.println("-----444 成功读取"+new String(bytes,0,length));
                System.out.println("====================");
                System.out.println();
            }
            inputStream.close();
            socket.close();
        }
    }
}
```

`RedisClient01`：

```java
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
public class RedisClient01
{
    public static void main(String[] args) throws IOException
    {
        Socket socket = new Socket("127.0.0.1",6379);
        OutputStream outputStream = socket.getOutputStream();

        //socket.getOutputStream().write("RedisClient01".getBytes());

        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            String string = scanner.next();
            if (string.equalsIgnoreCase("quit")) {
                break;
            }
            socket.getOutputStream().write(string.getBytes());
            System.out.println("------input quit keyword to finish......");
        }
        outputStream.close();
        socket.close();
    }
}
```

`RedisClient02`：

```java
package com.zzyy.study.iomultiplex.bio;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
public class RedisClient02
{
    public static void main(String[] args) throws IOException
    {
        Socket socket = new Socket("127.0.0.1",6379);
        OutputStream outputStream = socket.getOutputStream();

        //socket.getOutputStream().write("RedisClient01".getBytes());

        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            String string = scanner.next();
            if (string.equalsIgnoreCase("quit")) {
                break;
            }
            socket.getOutputStream().write(string.getBytes());
            System.out.println("------input quit keyword to finish......");
        }
        outputStream.close();
        socket.close();
    }
}
```

测试现象：先启动`RedisServerBIO`，再启动`RedisClient01`。当`RedisClient01`与`RedisServerBIO`连接成功并进行数据传输时，`RedisClient02`测试向`RedisServerBIO`发送消息时迟迟得不到响应，只有当`RedisClient01`退出连接时，`RedisClient02`发送给`RedisServerBIO`的消息才能被接收和处理

![image-20240114104845376](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220424666.png)

结论：在上面的程序中，客户端与服务端建立了连接之后，这个连接的客户端就会一直占用连接，程序就会一直堵塞在read()方法上，这样其他客户端也不能进行连接，也就是一次只能处理一个客户端



#####  13.2.4 多线程模式优化

多线程模式优化思路：每一个连接的socket，都分配一个线程来处理，这样read()方法堵塞在每个具体线程上而不堵塞主线程，就能操作多个socket了。哪个线程中的socket有数据，就读哪个socket，各取所需，灵活统一。程序服务端只负责监听是否有客户端连接，使用 accept() 阻塞。客户端1连接服务端，就开辟一个线程（thread1）来执行 read() 方法，程序服务端继续监听。客户端2连接服务端，也开辟一个线程（thread2）来执行 read() 方法，程序服务端继续监听
客户端3连接服务端，也开辟一个线程（thread3）来执行 read() 方法，程序服务端继续监听。这样任何一个线程上的socket有数据发送过来，read()就能立马读到，cpu就能进行处理。此思路的代码实现如下：

`RedisServerBIOMultiThread`(服务端)：

1.创建一个服务器套接字监听端口号6379，使用多线程处理客户端的连接和数据读取

2.在主函数中，`ServerSocket`通过`accept()`方法等待客户端连接，一旦有客户端连接，程序会创建一个新的线程来处理客户端的请求

3.每个线程负责获取对应客户端的输入流，通过输入流读取客户端发送的数据。如果没有可用数据，输入流的`read`方法会阻塞（阻塞2）直到有数据或者连接关闭

4.一旦读取到数据，程序会打印成功读取的信息，然后关闭输入流和套接字

```java
package com.zzyy.study.iomultiplex.bio;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class RedisServerBIOMultiThread
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(6379);

        while(true)
        {
            //System.out.println("-----111 等待连接");
            Socket socket = serverSocket.accept();//阻塞1 ,等待客户端连接
            //System.out.println("-----222 成功连接");

            new Thread(() -> {
                try {
                    InputStream inputStream = socket.getInputStream();
                    int length = -1;
                    byte[] bytes = new byte[1024];
                    System.out.println("-----333 等待读取");
                    while((length = inputStream.read(bytes)) != -1)//阻塞2 ,等待客户端发送数据
                    {
                        System.out.println("-----444 成功读取"+new String(bytes,0,length));
                        System.out.println("====================");
                        System.out.println();
                    }
                    inputStream.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            },Thread.currentThread().getName()).start();

            System.out.println(Thread.currentThread().getName());

        }
    }
}
```

`RedisClient01`(客户端)：

1. 连接到IP地址127.0.0.1的6379端口
2. 客户端通过从控制台获取输入，并将其写入套接字的输出流中来向服务器发送数据
3. 当输入"quit"时，程序结束

```java
package com.zzyy.study.iomultiplex.bio;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
public class RedisClient01
{
    public static void main(String[] args) throws IOException
    {
        Socket socket = new Socket("127.0.0.1",6379);
        OutputStream outputStream = socket.getOutputStream();

        //socket.getOutputStream().write("RedisClient01".getBytes());

        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            String string = scanner.next();
            if (string.equalsIgnoreCase("quit")) {
                break;
            }
            socket.getOutputStream().write(string.getBytes());
            System.out.println("------input quit keyword to finish......");
        }
        outputStream.close();
        socket.close();
    }
}
```

`RedisClient02`(客户端)：

1. 连接到IP地址127.0.0.1的6379端口
2. 客户端通过从控制台获取输入，并将其写入套接字的输出流中来向服务器发送数据
3. 当输入"quit"时，程序结束

```java
package com.zzyy.study.iomultiplex.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
public class RedisClient02
{
    public static void main(String[] args) throws IOException
    {
        Socket socket = new Socket("127.0.0.1",6379);
        OutputStream outputStream = socket.getOutputStream();

        //socket.getOutputStream().write("RedisClient01".getBytes());

        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            String string = scanner.next();
            if (string.equalsIgnoreCase("quit")) {
                break;
            }
            socket.getOutputStream().write(string.getBytes());
            System.out.println("------input quit keyword to finish......");
        }
        outputStream.close();
        socket.close();
    }
}
```

弊端：每来一个客户端，就要开辟一个线程，如果来1万个客户端，那就要开辟1万个线程。在操作系统中用户态不能直接开辟线程，需要调用内核来创建的一个线程，这其中还涉及到用户状态的切换（上下文的切换），十分耗资源

解决方法一：使用线程池。这个在客户端连接少的情况下可以使用，但是用户量大的情况下，不知道线程池要多大，太大了内存可能不够

解决方法二：NIO（非阻塞式IO）方式。因为read()方法堵塞了，所有要开辟多个线程，如果能使read()方法不堵塞，这样就不用开辟多个线程了。这就需要另一个IO模型，NIO（非阻塞式IO）

说明：tomcat7之前就是用BIO多线程来解决多连接



两个痛点

accept

read

在阻塞式I/O模型中，应用程序在从调用recvfrom开始到它返回有数据报准备好这段时间是阻塞的，recvfrom返回成功后，应用进程才能开始处理数据报



阻塞式IO小总结：

![image-20240114123335714](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220424150.png)



思考：每个线程分配一个连接，必然会产生多个，既然是多个socket链接必然需要放入进容器，纳入统一管理





###  13.3 NIO(非阻塞式IO)

当用户进程发出read操作时，如果kernel中的数据还没有准备好，那么它并不会block用户进程，而是立刻返回一个error。从用户进程角度讲 ，它发起一个read操作后，并不需要等待，而是马上就得到了一个结果。用户进程判断结果是一个error时，它就知道数据还没有准备好，于是它可以再次发送read操作。一旦kernel中的数据准备好了，并且又再次收到了用户进程的system call，那么它马上就将数据拷贝到了用户内存，然后返回。NIO的特点是**用户进程需要不断的主动询问内核是否准备好数据 。一句话，用轮询替代阻塞**！在NIO模式中，一切都是非阻塞的。accept()方法是非阻塞的，如果没有客户端连接，就返回无连接标识。read()方法是非阻塞的，如果read()方法读取不到数据就返回空闲中标识，如果读取到数据时只阻塞read()方法读数据的时间。在NIO模式中，只有一个线程：当一个客户端与服务端进行连接，这个socket就会加入到一个数组中，隔一段时间遍历一次，看这个socket的read()方法能否读到数据，这样一个线程就能处理多个客户端的连接和读取

![image-20240114123507512](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220424137.png)

`java.net.Socket`是阻塞的，`java.nio.channels.SocketChannel`是非阻塞的，NIO程序演示如下：

`RedisServerNIO`：用非阻塞I/O方式来处理连接和数据的读取。程序监听在本地地址127.0.0.1的6379端口上，并且在启动时等待客户端的连接。程序首先创建一个`ServerSocketChannel`，然后绑定到指定的地址和端口。接着通过`ServerSocketChannel.accept()`方法来接受客户端的连接，并将这些连接放入一个`SocketChannel`列表中。在主循环中不断遍历socket列表进行读取操作，对于每个`SocketChannel`，如果有数据可读，就将数据读取到ByteBuffer中，并进行处理。如果成功连接一个新的客户端，也会将其设置为非阻塞模式并加入到socket列表中

```java
package com.zzyy.study.iomultiplex.nio;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
public class RedisServerNIO
{
    static ArrayList<SocketChannel> socketList = new ArrayList<>();
    static ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) throws IOException
    {
        System.out.println("---------RedisServerNIO 启动等待中......");
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("127.0.0.1",6379));
        serverSocket.configureBlocking(false);//设置为非阻塞模式

        while (true)
        {
            for (SocketChannel element : socketList)
            {
                int read = element.read(byteBuffer);
                if(read > 0)
                {
                    System.out.println("-----读取数据: "+read);
                    byteBuffer.flip();
                    byte[] bytes = new byte[read];
                    byteBuffer.get(bytes);
                    System.out.println(new String(bytes));
                    byteBuffer.clear();
                }
            }

            SocketChannel socketChannel = serverSocket.accept();
            if(socketChannel != null)
            {
                System.out.println("-----成功连接: ");
                socketChannel.configureBlocking(false);//设置为非阻塞模式
                socketList.add(socketChannel);
                System.out.println("-----socketList size: "+socketList.size());
            }
        }
    }
}
```

`RedisClient01`：通过Socket与`RedisServerNIO`建立连接，可以向服务器发送数据。当用户在客户端输入"quit"时，客户端程序退出

```java
package com.zzyy.study.iomultiplex.nio;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
public class RedisClient01
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("------RedisClient01 start");
        Socket socket = new Socket("127.0.0.1",6379);
        OutputStream outputStream = socket.getOutputStream();
        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            String string = scanner.next();
            if (string.equalsIgnoreCase("quit")) {
                break;
            }
            socket.getOutputStream().write(string.getBytes());
            System.out.println("------input quit keyword to finish......");
        }
        outputStream.close();
        socket.close();
    }
}
```

`RedisClient02`：通过Socket与`RedisServerNIO`建立连接，可以向服务器发送数据。当用户在客户端输入"quit"时，客户端程序退出

```java
package com.zzyy.study.iomultiplex.nio;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
public class RedisClient02
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("------RedisClient02 start");


        Socket socket = new Socket("127.0.0.1",6379);
        OutputStream outputStream = socket.getOutputStream();

        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            String string = scanner.next();
            if (string.equalsIgnoreCase("quit")) {
                break;
            }
            socket.getOutputStream().write(string.getBytes());
            System.out.println("------input quit keyword to finish......");
        }
        outputStream.close();
        socket.close();
    }
}
```

弊端：NIO成功的解决了BIO需要开启多线程的问题，NIO中一个线程就能解决多个socket，但是还存在2个问题。首先，这个模型在客户端少的时候十分好用，但是客户端如果很多，比如有1万个客户端进行连接，那么每次循环就要遍历1万个socket，如果一万个socket中只有10个socket有数据，也会遍历一万个socket，就会做很多无用功，每次遍历遇到 read 返回 -1 时仍然是一次浪费资源的系统调用。其次，这个遍历过程是在用户态进行的，用户态判断socket是否有数据还是调用内核的read()方法实现的，这就涉及到用户态和内核态的切换，每遍历一个就要切换一次，开销很大因为这些问题的存在



优点：不会阻塞在内核的等待数据过程，每次发起的 I/O 请求可以立即返回，不用阻塞等待，实时性较好

缺点：轮询将会不断地询问内核，这将占用大量的 CPU 时间，系统资源利用率较低，所以一般 Web 服务器不使用这种 I/O 模型

结论：让Linux内核搞定上述需求，我们将一批文件描述符通过一次系统调用传给内核由内核层去遍历，才能真正解决这个问题。IO多路复用应运而生，也即将上述工作直接放进Linux内核，不再两态转换而是直接从内核获得结果，因为内核是非阻塞的



问题升级：如何用单线程处理大量的链接?



非阻塞式IO小总结：

![image-20240114124334273](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220425014.png)



###  13.4  IO Multiplexing (IO多路复用)

#####  13.4.1 文件描述符

 文件描述符（File descriptor）是计算机科学中的一个术语，是一个用于表述指向文件的引用的抽象化概念。文件描述符在形式上是一个非负整数。实际上，它是一个索引值，指向内核为每一个进程所维护的该进程打开文件的记录表。当程序打开一个现有文件或者创建一个新文件时，内核向进程返回一个文件描述符。在程序设计中，一些涉及底层的程序编写往往会围绕着文件描述符展开。但是文件描述符这一概念往往只适用于UNIX、Linux这样的操作系统

```java
// java中的FileDescriptor
/**
 * Instances of the file descriptor class serve as an opaque handle
 * to the underlying machine-specific structure representing an
 * open file, an open socket, or another source or sink of bytes.
 * The main practical use for a file descriptor is to create a
 * {@link FileInputStream} or {@link FileOutputStream} to contain it.
 *
 * <p>Applications should not create their own file descriptors.
 *
 * @author  Pavani Diwanji
 * @since   JDK1.0
 */
public final class FileDescriptor {

    private int fd;

    private long handle;

    private Closeable parent;
    private List<Closeable> otherParents;
    private boolean closed;
```

#####  13.4.2  IO多路复用模型

多路复用：数据通信系统或计算机网络系统中，传输媒体的带宽或容量往往会大于传输单一信号的需求，为了有效地利用通信线路,希望一个信道同时传输多路信号，这就是所谓的多路复用技术(Multiplexing)。采用多路复用技术能把多个信号组合起来在一条物理信道上进行传输，在远距离传输时可大大节省电缆的安装和维护费用。频分多路复用FDM(Frequency Division Multiplexing)和时分多路复用TDM (Time Division Multiplexing)是两种最常用的多路复用技术

IO多路复用模型：I/O多路复用在英文中其实叫 I/O multiplexing

I/O多路复用实现：多个Socket复用一根网线这个功能是在内核＋驱动层实现的

![image-20240114125206454](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220425635.png)



I/O multiplexing 这里面的 multiplexing 指的其实是在单个线程通过记录跟踪每一个Sock(I/O流)的状态来同时管理多个I/O流. 目的是尽量多的提高服务器的吞吐能力

![redisio](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220425525.gif)

大家都用过nginx，nginx使用epoll接收请求，ngnix会有很多链接进来， epoll会把他们都监视起来，然后像拨开关一样，谁有数据就拨向谁，然后调用相应的代码处理。redis类似同理



IO多路复用：IO multiplexing就是我们说的select，poll，epoll，有些技术书籍也称这种IO方式为event driven IO事件驱动IO。就是通过一种机制，一个进程可以监视多个描述符，一旦某个描述符就绪（一般是读就绪或者写就绪），能够通知程序进行相应的读写操作。可以基于一个阻塞对象并同时在多个描述符上等待就绪，而不是使用多个线程(每个文件描述符一个线程，每次new一个线程)，这样可以大大节省系统资源。所以，I/O 多路复用的特点是通过一种机制使得一个进程能同时等待多个文件描述符而这些文件描述符（套接字描述符）其中的任意一个进入读就绪状态，select，poll，epoll等函数就可以返回

![image-20240114125835815](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220425867.png)





```
模拟一个tcp服务器处理30个客户socket，一个监考老师监考多个学生，谁举手就应答谁

假设你是一个监考老师，让30个学生解答一道竞赛考题，然后负责验收学生答卷，你有下面几个选择：

第一种选择：按顺序逐个验收，先验收A，然后是B，之后是C、D。。。这中间如果有一个学生卡住，全班都会被耽误,你用循环挨个处理socket，根本不具有并发能力


第二种选择：创建30个分身线程，每个分身线程检查一个学生的答案是否正确。 这种类似于为每一个用户创建一个进程或者线程处理连接


第三种选择，站在讲台上等，谁解答完谁举手。这时C、D举手，表示他们解答问题完毕，你下去依次检查C、D的答案，然后继续回到讲台上等。此时E、A又举手，然后去处理E和A。。。这种就是IO复用模型。Linux下的select、poll和epoll就是干这个的

将用户socket对应的fd注册进epoll，然后epoll帮你监听哪些socket上有消息到达，这样就避免了大量的无用操作。此时的socket应该采用非阻塞模式。这样，整个过程只在调用select、poll、epoll这些调用的时候才会阻塞，收发客户消息是不会阻塞的，整个进程或者线程就被充分利用起来，这就是事件驱动，也是所谓的reactor反应模式
```

#####  13.4.3 Redis的IO多路复用

Redis的IO多路复用：Redis利用epoll来实现IO多路复用，将连接信息和事件放到队列中，依次放到事件分派器，事件分派器将事件分发给事件处理器

![image-20240114130123595](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220425249.png)

Redis服务采用Reactor的方式来实现文件事件处理器（每一个网络连接其实都对应一个文件描述符）。所谓 I/O 多路复用机制，就是说通过一种机制，可以监视多个描述符，一旦某个描述符就绪（一般是读就绪或写就绪），能够通知程序进行相应的读写操作。这种机制的使用需要 select 、 poll 、 epoll 来配合。多个连接共用一个阻塞对象，应用程序只需要在一个阻塞对象上等待，无需阻塞等待所有连接。当某条连接有新的数据可以处理时，操作系统通知应用程序，线程从阻塞状态返回，开始进行业务处理

  

所谓 I/O 多路复用机制，就是说通过一种考试监考机制，一个老师可以监视多个考生，一旦某个考生举手想要交卷了，能够通知监考老师进行相应的收卷子或批改检查操作。所以这种机制需要调用班主任(select/poll/epoll)来配合。多个考生被同一个班主任监考，收完一个考试的卷子再处理其它人，无需等待所有考生，谁先举手就先响应谁，当又有考生举手要交卷，监考老师看到后从讲台走到考生位置，开始进行收卷处理

#####  13.4.4  Reactor模式

 Reactor模式基于 I/O 复用模型：多个连接共用一个阻塞对象，应用程序只需要在一个阻塞对象上等待，无需阻塞等待所有连接。当某条连接有新的数据可以处理时，操作系统通知应用程序，线程从阻塞状态返回，开始进行业务处理



Reactor 模式，是指通过一个或多个输入同时传递给服务处理器的服务请求的事件驱动处理模式。服务端程序处理传入多路请求，并将它们同步分派给请求对应的处理线程，Reactor 模式也叫 Dispatcher 模式。即 I/O 多了复用统一监听事件，收到事件后分发(Dispatch 给某进程)，是编写高性能网络服务器的必备技术

![image-20240114130314938](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220425070.png)

Reactor 模式中有 2 个关键组成：

- Reactor：Reactor 在一个单独的线程中运行，负责监听和分发事件，分发给适当的处理程序来对 IO 事件做出反应。 它就像公司的电话接线员，它接听来自客户的电话并将线路转移到适当的联系人
- Handlers：处理程序执行 I/O 事件要完成的实际事件，类似于客户想要与之交谈的公司中的实际办理人。Reactor 通过调度适当的处理程序来响应 I/O 事件，处理程序执行非阻塞操作



Redis 服务采用 Reactor 的方式来实现文件事件处理器（**每一个网络连接其实都对应一个文件描述符**）。Redis基于Reactor模式开发了网络事件处理器，这个处理器被称为文件事件处理器，它的组成结构为4部分：多个套接字、IO多路复用程序、文件事件分派器、事件处理器。因为文件事件分派器队列的消费是单线程的，所以Redis才叫单线程模型



![image-20240114130501216](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220425385.png)



###  13.5 select方法

select, poll, epoll都是I/O多路复用的具体的实现

Linux官网关于select的说明：`https://man7.org/linux/man-pages/man2/select.2.html`

Linux源码select函数定义：

```c
// Linux源码select函数定义
int select(int nfds, fd_set *readfds, fd_set *writefds,
          fd_set *exceptfds, struct timeval *timeout);
```

![image-20240114202942871](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220425095.png)

select 函数监视的文件描述符分3类，分别是readfds、writefds和exceptfds，文件描述符将用户传入的读写数据数组拷贝到内核空间。调用后select函数会阻塞，直到有描述符就绪（有数据 可读、可写、或者有except）或超时（timeout指定等待时间，如果立即返回设为null即可），函数返回。当select函数返回后，可以通过遍历fdset来找到就绪的描述符



使用NIO模拟select函数实现：linux的select函数其实就是将下面的功能进行内核化

```java
        /**
         * select 其实就是把NIO中用户态要遍历的fd数组(我们的每一个socket链接，安装进ArrayList里面的那个)拷贝到了内核态，
         * 让内核态来遍历，因为用户态判断socket是否有数据还是要调用内核态的，所有拷贝到内核态后，
         * 这样遍历判断的时候就不用一直用户态和内核态频繁切换了
         */

        while (true) {
            for (SocketChannel element : socketList) {
                int read = element.read(byteBuffer);
                if (read > 0) {
                    System.out.println("-----读取数据: " + read);
                    byteBuffer.flip();
                    byte[] bytes = new byte[read];
                    byteBuffer.get(bytes);
                    System.out.println(new String(bytes));
                    byteBuffer.clear();
                }
            }
            SocketChannel socketChannel = serverSocket.accept();
            if (socketChannel != null) {
                System.out.println("-----成功连接: ");
                socketChannel.configureBlocking(false);//设置为非阻塞模式
                socketList.add(socketChannel);
                System.out.println("-----socketList size: " + socketList.size());
            }
        }
```

select函数的执行流程：

1.select是一个阻塞函数，当没有数据时，会—直阻塞在select那一行

2.当有数据时会将rset中对应的那一位置为1

3.select函数返回，不再阻塞

4.遍历文件描述符数组，判断哪个fd被置位了

5.读取数据，然后处理

6.Linux内核源码：

![image-20240114131308560](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220425725.png)



![image-20240114131318764](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220425836.png)



**select优点**：select其实就是把NIO中用户态要遍历的fd数组(我们的每一个socket链接，安装进ArrayList里面的那个)拷贝到了内核态，让内核态来遍历，因为用户态判断socket是否有数据还是要调用内核态的，所有拷贝到内核态后，这样遍历判断的时候就不用一直用户态和内核态频繁切换了。从源码中可以看出，select系统调用后，返回了一个置位后的&rset，这样用户态只需进行很简单的二进制比较，就能很快知道哪些socket需要read数据，有效提高了效率



**select缺点**：

- bitmap默认大小为1024，虽然可以调整但还是有限度的
- rset每次循环都必须重新置位为0，不可重复使用
- 尽管将rset从用户态拷贝到内核态由内核态判断是否有数据，但是还是有拷贝的开销
- 当有数据时select就会返回，但是select函数并不知道哪个文件描述符有数据了，后面还需要再次对文件描述符数组进行遍历。效率比较低



**select总结**：select方式，既做到了一个线程处理多个客户端连接（文件描述符），又减少了系统调用的开销，**多个文件描述符**只会产生**一次select的系统调用 + N次就绪状态的文件描述符的read系统调用**



### 13.6  poll方法

Linux官网或者man：`https://man7.org/linux/man-pages/man2/poll.2.html`

Linux源码poll函数定义：

```c
 int poll(struct pollfd *fds, nfds_t nfds, int timeout);
 
struct pollfd {
           int   fd;         /* file descriptor */
           short events;     /* requested events */
           short revents;    /* returned events */
       };
```

poll函数的执行逻辑：

- 1.将五个fd从用户态拷贝到内核态
- 2.poll为阻塞方法，执行poll方法，如果有数据会将fd对应的revents置为POLLIN
- 3.poll方法返回
- 4.循环遍历，查找哪个fd被置位为POLLIN
- 5.将revents重置为0便于复用
- 6.对置位的fd进行读取和处理

![image-20240114133519907](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220425411.png)

poll函数相较于select的优化：取消了select函数bitmap的大小限制、解决了select函数rset不可重用的缺点

**poll函数优点**：

- poll使用pollfd数组来代替select中的bitmap，数组没有1024的限制，可以一次管理更多的client。它和select的主要区别就是，去掉了select只能监听1024个文件描述符的限制
- 当pollfds数组中有事件发生，相应的revents置位为1，遍历的时候又置位回零，实现了pollfd数组的重用

**poll函数缺点**：

- poll 解决了select缺点中的前两条，其本质原理还是select的方法，还存在select中原来的问题
- poll函数弊端：
  - pollfds数组拷贝到了内核态，仍然有开销
  - poll并没有通知用户态哪一个socket有数据，仍然需要O(n)的遍历

###  13.7  epoll方法

Linux官网关于epoll的介绍：`https://man7.org/linux/man-pages/man7/epoll.7.html`

epoll操作过程需要如下三个接口：

![image-20240114134359882](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220425791.png)

![image-20240114134410717](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220425483.png)

![image-20240114134427152](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220425265.png)

epoll操作过程需要如下三个接口：

| 函数                                                         | 描述                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| int epoll_create(int size)                                   | 参数size并不是限制了epoll所能监听的描述符最大个数，只是对内核初始分配内部数据结构的一个建议 |
| int epoll_ctl(int epfd, int op, int fd, struct epoll_event *event) | 见上图                                                       |
| int epoll_wait(int epfd, struct epoll_event * events, int maxevents, int timeout) | 等待epfd上的io事件，最多返回maxevents个事件。参数events用来从内核得到事件的集合，maxevents告之内核这个events有多大 |

epoll操作的三个接口作用：

- epoll_create：创建一个epoll句柄
- epoll_ctl：向内核添加、修改或删除要监控的文件描述符
- epoll_wait：类似发起了select()调用

**epoll操作的逻辑**：注意！epoll是非阻塞的

- 当有数据的时候，会把相应的文件描述符“置位”，但是epool没有revent标志位，所以并不是真正的置位。这时候会把有数据的文件描述符放到队首
- epoll会返回有数据的文件描述符的个数
- 根据返回的个数读取前N个文件描述符即可
- 读取、处理

![image-20240114134842489](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220425895.png)

###  13.8  select、poll、epoll

IO多路复用机制快的原因在于它使得`原来的while循环里多次系统调用`变成了`一次系统调用` + `内核层遍历文件描述符`。epoll是现在最先进的IO多路复用器，Redis、Nginx，linux中的Java NIO都使用的是epoll。“多路”指的是多个网络连接，“复用”指的是复用同一个线程。IO多路复用模型中，一个socket的生命周期中只有一次从用户态拷贝到内核态的过程，开销小。使用event事件通知机制，每次socket中有数据会主动通知内核，并加入到就绪链表中，不需要遍历所有的socket

 

在IO多路复用模型中，会有一个内核线程不断地去轮询多个 socket 的状态，只有当真正读写事件发送时，才真正调用实际的IO读写操作。因为在多路复用IO模型中，只需要使用一个线程就可以管理多个socket，系统不需要建立新的进程或者线程，也不必维护这些线程和进程，并且只有真正有读写事件进行时，才会使用IO资源，所以它大大减少了资源占用。多路I/O复用模型是利用 select、poll、epoll 可以同时监察多个流的 I/O 事件的能力，在空闲的时候，会把当前线程阻塞掉，当有一个或多个流有 I/O 事件时，就从阻塞态中唤醒，于是程序就会轮询一遍所有的流（epoll 是只轮询那些真正发出了事件的流），并且只依次顺序的处理就绪的流，这种做法就避免了大量的无用操作。 采用多路 I/O 复用技术可以让单个线程高效的处理多个连接请求（尽量减少网络 IO 的时间消耗），且 Redis 在内存中操作数据的速度非常快，也就是说内存内的单线程操作不会成为影响Redis性能的瓶颈

 

三个方法对比：

|                      | select                                             | poll                                             | epoll                                                        |
| -------------------- | -------------------------------------------------- | ------------------------------------------------ | ------------------------------------------------------------ |
| 操作方式             | 遍历                                               | 遍历                                             | 回调                                                         |
| 数据结构             | bitmap                                             | 数组                                             | 红黑树                                                       |
| 最大连接数           | 1024 (x86)或2048(x64)                              | 无上限                                           | 无上限                                                       |
| 最大支持文件描述符数 | 一般有最大值限制                                   | 65535                                            | 65535                                                        |
| fd拷贝               | 每次调用select，都需要把fd集合从用户态拷贝到内核态 | 每次调用poll，都需要把fd集合从用户态拷贝到内核态 | fd首次调用epoll_ctl拷贝，每次调用epoll_wait不拷贝            |
| 作效率               | 每次调用都进行线性遍历，时间复杂度为o(n)           | 每次调用都进行线性遍历，时间复杂度为o(n)         | 事件通知方式，每当fd就绪，系统注册的回调函数就会被调用，将就绪fd放到readyList里面，时间复杂度O(1) |



###  13.8  五种I/O模型总结：

IO多路复用机制快的原因在于它使得`原来的while循环里多次系统调用`变成了`一次系统调用` + `内核层遍历文件描述符`。I/O多路复用机制，就是说通过一种机制，可以监视多个描述符，一旦某个描述符就绪（一般是读就绪或写就绪），能够通知程序进行相应的读写操作。这种机制的使用需要 select 、 poll 、 epoll 来配合。多个连接共用一个阻塞对象，应用程序只需要在一个阻塞对象上等待，无需阻塞等待所有连接。当某条连接有新的数据可以处理时，操作系统通知应用程序，线程从阻塞状态返回，开始进行业务处理

 ![image-20240114135839935](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220426168.png)

##  14. 微信抢红包案例实现

腾讯面试题如何做个迷你版的微信抢红包

**需求分析**：

- 各种节假日，发红包+抢红包，100%高并发业务要求，不能用mysql来做
- 一个总的大红包，会有可能拆分成多个小红包，总金额 = 分金额1+分金额2+分金额3......分金额N
- 每个人只能抢一次，需要有记录，比如100块钱，被拆分成10个红包发出去，总计有10个红包，抢一个少一个，总数显示(10/6)直到完，需要记录那些人抢到了红包，不可以重复抢
- 有可能还需要计时，完整抢完，从发出到全部over，耗时多少？
- 红包过期，或者群主人品差，没人抢红包，原封不动退回账户
- 红包过期，剩余金额可能需要回退到发红包主账户下
- 由于是高并发不能用mysql来做，只能用redis，那需要要redis的什么数据类型？

**架构设计**：

- 如何拆分红包：红包其实就是金额，拆分算法如何 ？给你100块，分成10个小红包(金额有可能小概率相同，有2个红包都是2.58)，如何拆分随机金额设定每个红包里面安装多少钱
- 关键：抢，不加锁且原子性，还能支持高并发。每人一次且有抢红包记录。记录每个人抢了多少。所有人抢到金额之和等于红包金额，不能超过，也不能少于。每个人至少抢到一分钱。要保证所有人抢到金额的几率相等
- 次数限制：每个人只能抢一次，次数限制
- 原子性：每抢走一个红包就减少一个(类似减库存)，那这个就需要保证库存的原子性，不加锁实现。你认为存在redis什么数据类型里面？set ？hash？ list？
- 抢红包业务通用算法：二倍均值法

```
二倍均值法
剩余红包金额为M，剩余人数为N，那么有如下公式：
每次抢到金额的随机区间 = （0， (剩余红包金额M ÷ 剩余人数N ) X 2）
这个公式保证了每次随机金额的平均值是相等的，不会因为抢红包的先后顺序而造成不公平

举例说明：
假设有10个人，红包总额100元
第1次：
100÷10  X2 = 20, 所以第一个人的随机范围是（0，20 )，平均可以抢到10元。假设第一个人随机到10元，那么剩余金额是100-10 = 90 元
第2次：
90÷9  X2 = 20, 所以第二个人的随机范围同样是（0，20 )，平均可以抢到10元。假设第二个人随机到10元，那么剩余金额是90-10 = 80 元
第3次：
80÷8  X2 = 20, 所以第三个人的随机范围同样是（0，20 )，平均可以抢到10元。 以此类推，每一次随机范围的均值是相等的
```

**利用redis实现抢红包**：

```shell
# 发红包：使用list实现。list支持1对N，可实现一个红包多个金额值
# 100块钱拆分5个红包20 20 20 30 10
127.0.0.1:6379> LPUSH redpackage:01 20 20 20 30 10
(integer)5

# 抢红包：使用LPOP命令实现
# 保证高并发+多线程+不加锁且保证原子性
# 使用redis，每个命令就是单线程，原子性天生保证
127.0.0.1:6379> LPOP redpackage:01
"10"
127.0.0.1:6379> LPOP redpackage:01
"30"
127.0.0.1:6379> LRANGE redpackage:01 0 -1
1)"20"
2)"20"
3)"20"
127.0.0.1:6379> LPOP redpackage:01
"20"
127.0.0.1:6379> LRANGE redpackage:01 0 -1
1)"20"
2)"20"

# 记录红包情况。盘点+汇总。防止作弊，同一个用户不可以多次抢夺红包
# 记录红包实现：使用hash记录抢红包的情况
127.0.0.1:6379> hset redpacKage:customer:01 cid 10 cid2 30 cid3 20

# 抢红包：二倍均值算法
```

`RedPackageController`：省略不重要的部分，只写Controller

```java
package com.zzyy.study.controller;

import cn.hutool.core.util.IdUtil;
import com.google.common.primitives.Ints;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
@RestController
public class RedPackageController
{
    public static final String RED_PACKAGE_KEY = "redpackage:";
    public static final String RED_PACKAGE_CONSUME_KEY = "redpackage:consume:";

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 拆分+发送红包
     * http://localhost:5555/send?totalMoney=100&redPackageNumber=5
     * @param totalMoney
     * @param redPackageNumber
     * @return
     */
    @RequestMapping("/send")
    public String sendRedPackage(int totalMoney,int redPackageNumber)
    {
        //1 拆红包，总金额拆分成多少个红包，每个小红包里面包多少钱
        Integer[] splitRedPackages = splitRedPackage(totalMoney, redPackageNumber);
        //2 红包的全局ID
        String key = RED_PACKAGE_KEY+IdUtil.simpleUUID();
        //3 采用list存储红包并设置过期时间
        redisTemplate.opsForList().leftPushAll(key,splitRedPackages);
        redisTemplate.expire(key,1,TimeUnit.DAYS);
        return key+"\t"+"\t"+ Ints.asList(Arrays.stream(splitRedPackages).mapToInt(Integer::valueOf).toArray());
    }

    /**
     * http://localhost:5555/rob?redPackageKey=上一步的红包UUID&userId=1
     * @param redPackageKey
     * @param userId
     * @return
     */
    @RequestMapping("/rob")
    public String rodRedPackage(String redPackageKey,String userId)
    {
        //1 验证某个用户是否抢过红包
        Object redPackage = redisTemplate.opsForHash().get(RED_PACKAGE_CONSUME_KEY + redPackageKey, userId);
        //2 没有抢过就开抢，否则返回-2表示抢过
        if (redPackage == null) {
            // 2.1 从list里面出队一个红包，抢到了一个
            Object partRedPackage = redisTemplate.opsForList().leftPop(RED_PACKAGE_KEY + redPackageKey);
            if (partRedPackage != null) {
                //2.2 抢到手后，记录进去hash表示谁抢到了多少钱的某一个红包
                redisTemplate.opsForHash().put(RED_PACKAGE_CONSUME_KEY + redPackageKey,userId,partRedPackage);
                System.out.println("用户: "+userId+"\t 抢到多少钱红包: "+partRedPackage);
                //TODO 后续异步进mysql或者RabbitMQ进一步处理
                return String.valueOf(partRedPackage);
            }
            //抢完
            return "errorCode:-1,红包抢完了";
        }
        //3 某个用户抢过了，不可以作弊重新抢
        return "errorCode:-2,   message: "+"\t"+userId+" 用户你已经抢过红包了";
    }

    /**
     * 1 拆完红包总金额+每个小红包金额别太离谱
     * @param totalMoney
     * @param redPackageNumber
     * @return
     */
    private Integer[] splitRedPackage(int totalMoney, int redPackageNumber)
    {
        int useMoney = 0;
        Integer[] redPackageNumbers = new Integer[redPackageNumber];
        Random random = new Random();

        for (int i = 0; i < redPackageNumber; i++)
        {
            if(i == redPackageNumber - 1)
            {
                redPackageNumbers[i] = totalMoney - useMoney;
            }else{
                int avgMoney = (totalMoney - useMoney) * 2 / (redPackageNumber - i);
                redPackageNumbers[i] = 1 + random.nextInt(avgMoney - 1);
            }
            useMoney = useMoney + redPackageNumbers[i];
        }
        return redPackageNumbers;
    }
}
```



```shell
# 测试的过程中，可以使用如下命令批量删除红包记录
root@zzyy ~] # redis-cli keys "red*" | xargs redis-cli del
(integer) 3
root@zzyy ~]# 
```

