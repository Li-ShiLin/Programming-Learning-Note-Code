<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1.Redis简介 | 安装 | 基本命令](#1redis%E7%AE%80%E4%BB%8B--%E5%AE%89%E8%A3%85--%E5%9F%BA%E6%9C%AC%E5%91%BD%E4%BB%A4)
  - [1.1 reids简介](#11-reids%E7%AE%80%E4%BB%8B)
  - [1.2  redis的安装配置](#12--redis%E7%9A%84%E5%AE%89%E8%A3%85%E9%85%8D%E7%BD%AE)
  - [1.3 reids 常用命令](#13-reids-%E5%B8%B8%E7%94%A8%E5%91%BD%E4%BB%A4)
- [2. redis 十大数据类型](#2-redis-%E5%8D%81%E5%A4%A7%E6%95%B0%E6%8D%AE%E7%B1%BB%E5%9E%8B)
  - [2.1  redis字符串(String)](#21--redis%E5%AD%97%E7%AC%A6%E4%B8%B2string)
  - [2.2  redis列表(List)](#22--redis%E5%88%97%E8%A1%A8list)
  - [2.3  redis哈希表(Hash)](#23--redis%E5%93%88%E5%B8%8C%E8%A1%A8hash)
  - [2.4 redis集合(Set)](#24-redis%E9%9B%86%E5%90%88set)
  - [2.5  redis有序集合(ZSet)](#25--redis%E6%9C%89%E5%BA%8F%E9%9B%86%E5%90%88zset)
  - [2.6 redis位图(bitmap)](#26-redis%E4%BD%8D%E5%9B%BEbitmap)
  - [2.7 redis基数统计(HyperLogLog)](#27-redis%E5%9F%BA%E6%95%B0%E7%BB%9F%E8%AE%A1hyperloglog)
  - [2.8 redis地理空间(GEO)](#28-redis%E5%9C%B0%E7%90%86%E7%A9%BA%E9%97%B4geo)
  - [2.9 redis流(Stream)](#29-redis%E6%B5%81stream)
  - [2.10 redis位域(bitfield)](#210-redis%E4%BD%8D%E5%9F%9Fbitfield)
- [3.redis持久化](#3redis%E6%8C%81%E4%B9%85%E5%8C%96)
  - [3.1 持久化简介](#31-%E6%8C%81%E4%B9%85%E5%8C%96%E7%AE%80%E4%BB%8B)
  - [3.2  RDB (Redis DataBase)](#32--rdb-redis-database)
      - [3.2.1  RDB简介](#321--rdb%E7%AE%80%E4%BB%8B)
      - [3.2.2  RDB配置](#322--rdb%E9%85%8D%E7%BD%AE)
      - [3.2.3  自动触发](#323--%E8%87%AA%E5%8A%A8%E8%A7%A6%E5%8F%91)
      - [3.2.4 手动触发](#324-%E6%89%8B%E5%8A%A8%E8%A7%A6%E5%8F%91)
      - [3.2.5 RDB优缺点](#325-rdb%E4%BC%98%E7%BC%BA%E7%82%B9)
      - [3.2.6 RDB总结补充](#326-rdb%E6%80%BB%E7%BB%93%E8%A1%A5%E5%85%85)
  - [3.3 AOF (Append Only File)](#33-aof-append-only-file)
      - [3.3.1  AOF 简介](#331--aof-%E7%AE%80%E4%BB%8B)
      - [3.3.2 AOF持久化工作流程](#332-aof%E6%8C%81%E4%B9%85%E5%8C%96%E5%B7%A5%E4%BD%9C%E6%B5%81%E7%A8%8B)
      - [3.3.3  AOF缓冲区三种写回策略](#333--aof%E7%BC%93%E5%86%B2%E5%8C%BA%E4%B8%89%E7%A7%8D%E5%86%99%E5%9B%9E%E7%AD%96%E7%95%A5)
      - [3.3.4  AOF配置 | 启动 | 修复 | 恢复](#334--aof%E9%85%8D%E7%BD%AE--%E5%90%AF%E5%8A%A8--%E4%BF%AE%E5%A4%8D--%E6%81%A2%E5%A4%8D)
      - [3.3.5  正常恢复 | 异常恢复](#335--%E6%AD%A3%E5%B8%B8%E6%81%A2%E5%A4%8D--%E5%BC%82%E5%B8%B8%E6%81%A2%E5%A4%8D)
      - [3.3.6  AOF的优缺点](#336--aof%E7%9A%84%E4%BC%98%E7%BC%BA%E7%82%B9)
      - [3.3.7 AOF重写机制](#337-aof%E9%87%8D%E5%86%99%E6%9C%BA%E5%88%B6)
      - [3.3.8 AOF优化配置项](#338-aof%E4%BC%98%E5%8C%96%E9%85%8D%E7%BD%AE%E9%A1%B9)
      - [3.3.9  AOF总结](#339--aof%E6%80%BB%E7%BB%93)
  - [3.4 RDB-AOF混合持久化](#34-rdb-aof%E6%B7%B7%E5%90%88%E6%8C%81%E4%B9%85%E5%8C%96)
      - [3.4.1  RDB-AOF混合持久化](#341--rdb-aof%E6%B7%B7%E5%90%88%E6%8C%81%E4%B9%85%E5%8C%96)
      - [3.4.2  持久化方式选择](#342--%E6%8C%81%E4%B9%85%E5%8C%96%E6%96%B9%E5%BC%8F%E9%80%89%E6%8B%A9)
      - [3.4.3  纯缓存模式：同时关闭RDB+AOF](#343--%E7%BA%AF%E7%BC%93%E5%AD%98%E6%A8%A1%E5%BC%8F%E5%90%8C%E6%97%B6%E5%85%B3%E9%97%ADrdbaof)
- [4.redis事务](#4redis%E4%BA%8B%E5%8A%A1)
  - [4.1 redis事务简介](#41-redis%E4%BA%8B%E5%8A%A1%E7%AE%80%E4%BB%8B)
  - [4.2  Redis事务 VS 数据库事务](#42--redis%E4%BA%8B%E5%8A%A1-vs-%E6%95%B0%E6%8D%AE%E5%BA%93%E4%BA%8B%E5%8A%A1)
  - [4.3 redis事务使用](#43-redis%E4%BA%8B%E5%8A%A1%E4%BD%BF%E7%94%A8)
      - [4.3.1 redis事务语法简介](#431-redis%E4%BA%8B%E5%8A%A1%E8%AF%AD%E6%B3%95%E7%AE%80%E4%BB%8B)
      - [4.3.2 redis事务命令](#432-redis%E4%BA%8B%E5%8A%A1%E5%91%BD%E4%BB%A4)
  - [4.4 事务案例演示](#44-%E4%BA%8B%E5%8A%A1%E6%A1%88%E4%BE%8B%E6%BC%94%E7%A4%BA)
      - [4.4.1 case1 正常执行](#441-case1-%E6%AD%A3%E5%B8%B8%E6%89%A7%E8%A1%8C)
      - [4.4.2  Case2  放弃事务](#442--case2--%E6%94%BE%E5%BC%83%E4%BA%8B%E5%8A%A1)
      - [4.4.3  Case3 全体连坐](#443--case3-%E5%85%A8%E4%BD%93%E8%BF%9E%E5%9D%90)
      - [4.4.4 Case4 冤头债主](#444-case4-%E5%86%A4%E5%A4%B4%E5%80%BA%E4%B8%BB)
      - [4.4.5 Case5 watch监控](#445-case5-watch%E7%9B%91%E6%8E%A7)
      - [4.4.6  unwatch 命令](#446--unwatch-%E5%91%BD%E4%BB%A4)
      - [4.4.7 事务总结](#447-%E4%BA%8B%E5%8A%A1%E6%80%BB%E7%BB%93)
- [5.Redis管道](#5redis%E7%AE%A1%E9%81%93)
  - [5.1 Redis管道简介](#51-redis%E7%AE%A1%E9%81%93%E7%AE%80%E4%BB%8B)
  - [5.2 Redis管道案例演示](#52-redis%E7%AE%A1%E9%81%93%E6%A1%88%E4%BE%8B%E6%BC%94%E7%A4%BA)
  - [5.3 Pipeline与批量命令对比](#53-pipeline%E4%B8%8E%E6%89%B9%E9%87%8F%E5%91%BD%E4%BB%A4%E5%AF%B9%E6%AF%94)
  - [5.4 Pipeline与事务对比](#54-pipeline%E4%B8%8E%E4%BA%8B%E5%8A%A1%E5%AF%B9%E6%AF%94)
  - [5.5 Pipeline注意事项](#55-pipeline%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9)
- [6. Redis发布订阅](#6-redis%E5%8F%91%E5%B8%83%E8%AE%A2%E9%98%85)
  - [6.1 发布订阅简介](#61-%E5%8F%91%E5%B8%83%E8%AE%A2%E9%98%85%E7%AE%80%E4%BB%8B)
  - [6.2 发布订阅命令](#62-%E5%8F%91%E5%B8%83%E8%AE%A2%E9%98%85%E5%91%BD%E4%BB%A4)
  - [6.3 案例演示](#63-%E6%A1%88%E4%BE%8B%E6%BC%94%E7%A4%BA)
  - [6.4 总结](#64-%E6%80%BB%E7%BB%93)
- [7.Redis主从复制(replica)](#7redis%E4%B8%BB%E4%BB%8E%E5%A4%8D%E5%88%B6replica)
  - [7.1 简介](#71-%E7%AE%80%E4%BB%8B)
  - [7.2 redis复制配置 & 命令](#72-redis%E5%A4%8D%E5%88%B6%E9%85%8D%E7%BD%AE--%E5%91%BD%E4%BB%A4)
  - [7.3 案例演示](#73-%E6%A1%88%E4%BE%8B%E6%BC%94%E7%A4%BA)
      - [7.3.1 架构说明 & 主从配置](#731-%E6%9E%B6%E6%9E%84%E8%AF%B4%E6%98%8E--%E4%B8%BB%E4%BB%8E%E9%85%8D%E7%BD%AE)
      - [7.3.2 一主二从案例](#732-%E4%B8%80%E4%B8%BB%E4%BA%8C%E4%BB%8E%E6%A1%88%E4%BE%8B)
      - [7.3.3  链式复制案例](#733--%E9%93%BE%E5%BC%8F%E5%A4%8D%E5%88%B6%E6%A1%88%E4%BE%8B)
      - [7.3.4 反客为主案例](#734-%E5%8F%8D%E5%AE%A2%E4%B8%BA%E4%B8%BB%E6%A1%88%E4%BE%8B)
      - [7.3.5 主从复制问题演示](#735-%E4%B8%BB%E4%BB%8E%E5%A4%8D%E5%88%B6%E9%97%AE%E9%A2%98%E6%BC%94%E7%A4%BA)
  - [7.4 复制原理和工作流程](#74-%E5%A4%8D%E5%88%B6%E5%8E%9F%E7%90%86%E5%92%8C%E5%B7%A5%E4%BD%9C%E6%B5%81%E7%A8%8B)
  - [7.5 复制的缺点](#75-%E5%A4%8D%E5%88%B6%E7%9A%84%E7%BC%BA%E7%82%B9)
- [8.Redis哨兵(sentinel)](#8redis%E5%93%A8%E5%85%B5sentinel)
  - [8.1 哨兵简介](#81-%E5%93%A8%E5%85%B5%E7%AE%80%E4%BB%8B)
  - [8.2 案例演示](#82-%E6%A1%88%E4%BE%8B%E6%BC%94%E7%A4%BA)
      - [8.2.1 案例全局架构](#821-%E6%A1%88%E4%BE%8B%E5%85%A8%E5%B1%80%E6%9E%B6%E6%9E%84)
      - [8.2.2 哨兵配置参数](#822-%E5%93%A8%E5%85%B5%E9%85%8D%E7%BD%AE%E5%8F%82%E6%95%B0)
      - [8.2.3 本次案例的哨兵配置](#823-%E6%9C%AC%E6%AC%A1%E6%A1%88%E4%BE%8B%E7%9A%84%E5%93%A8%E5%85%B5%E9%85%8D%E7%BD%AE)
      - [8.2.4 redis集群架构](#824-redis%E9%9B%86%E7%BE%A4%E6%9E%B6%E6%9E%84)
      - [8.2.5 案例一：启动哨兵监控](#825-%E6%A1%88%E4%BE%8B%E4%B8%80%E5%90%AF%E5%8A%A8%E5%93%A8%E5%85%B5%E7%9B%91%E6%8E%A7)
      - [8.2.6 案例二：master出现故障](#826-%E6%A1%88%E4%BE%8B%E4%BA%8Cmaster%E5%87%BA%E7%8E%B0%E6%95%85%E9%9A%9C)
      - [8.2.7 总结](#827-%E6%80%BB%E7%BB%93)
  - [8.3 哨兵运行流程和选举原理](#83-%E5%93%A8%E5%85%B5%E8%BF%90%E8%A1%8C%E6%B5%81%E7%A8%8B%E5%92%8C%E9%80%89%E4%B8%BE%E5%8E%9F%E7%90%86)
      - [8.3.1 主观下线 | 客观下线](#831-%E4%B8%BB%E8%A7%82%E4%B8%8B%E7%BA%BF--%E5%AE%A2%E8%A7%82%E4%B8%8B%E7%BA%BF)
      - [8.3.2 哨兵leader选举 | Raft算法](#832-%E5%93%A8%E5%85%B5leader%E9%80%89%E4%B8%BE--raft%E7%AE%97%E6%B3%95)
      - [8.3.3 哨兵leader推动故障切换](#833-%E5%93%A8%E5%85%B5leader%E6%8E%A8%E5%8A%A8%E6%95%85%E9%9A%9C%E5%88%87%E6%8D%A2)
      - [8.3.4 故障恢复总结](#834-%E6%95%85%E9%9A%9C%E6%81%A2%E5%A4%8D%E6%80%BB%E7%BB%93)
  - [8.4 哨兵使用建议](#84-%E5%93%A8%E5%85%B5%E4%BD%BF%E7%94%A8%E5%BB%BA%E8%AE%AE)
- [9.Redis集群(cluster)](#9redis%E9%9B%86%E7%BE%A4cluster)
  - [9.1 redis集群简介](#91-redis%E9%9B%86%E7%BE%A4%E7%AE%80%E4%BB%8B)
  - [9.2 redis集群槽位概述](#92-redis%E9%9B%86%E7%BE%A4%E6%A7%BD%E4%BD%8D%E6%A6%82%E8%BF%B0)
  - [9.3 槽位映射的思路演化](#93-%E6%A7%BD%E4%BD%8D%E6%98%A0%E5%B0%84%E7%9A%84%E6%80%9D%E8%B7%AF%E6%BC%94%E5%8C%96)
      - [9.3.1  哈希取余分区](#931--%E5%93%88%E5%B8%8C%E5%8F%96%E4%BD%99%E5%88%86%E5%8C%BA)
      - [9.3.2 —致性哈希算法分区](#932-%E8%87%B4%E6%80%A7%E5%93%88%E5%B8%8C%E7%AE%97%E6%B3%95%E5%88%86%E5%8C%BA)
      - [9.3.3  哈希槽分区](#933--%E5%93%88%E5%B8%8C%E6%A7%BD%E5%88%86%E5%8C%BA)
  - [9.4 集群环境案例步骤](#94-%E9%9B%86%E7%BE%A4%E7%8E%AF%E5%A2%83%E6%A1%88%E4%BE%8B%E6%AD%A5%E9%AA%A4)
      - [9.4.1   3主3从redis集群配置](#941---3%E4%B8%BB3%E4%BB%8Eredis%E9%9B%86%E7%BE%A4%E9%85%8D%E7%BD%AE)
      - [9.4.2 3主3从redis集群读写](#942-3%E4%B8%BB3%E4%BB%8Eredis%E9%9B%86%E7%BE%A4%E8%AF%BB%E5%86%99)
      - [9.4.3  主从容错切换迁移案例](#943--%E4%B8%BB%E4%BB%8E%E5%AE%B9%E9%94%99%E5%88%87%E6%8D%A2%E8%BF%81%E7%A7%BB%E6%A1%88%E4%BE%8B)
      - [9.4.4 主从扩容案例](#944-%E4%B8%BB%E4%BB%8E%E6%89%A9%E5%AE%B9%E6%A1%88%E4%BE%8B)
      - [9.4.5  主从缩容案例](#945--%E4%B8%BB%E4%BB%8E%E7%BC%A9%E5%AE%B9%E6%A1%88%E4%BE%8B)
  - [9.5 集群常用操作命令和CRC16算法分析](#95-%E9%9B%86%E7%BE%A4%E5%B8%B8%E7%94%A8%E6%93%8D%E4%BD%9C%E5%91%BD%E4%BB%A4%E5%92%8Ccrc16%E7%AE%97%E6%B3%95%E5%88%86%E6%9E%90)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

##  1.Redis简介 | 安装 | 基本命令

###  1.1 reids简介 

**Redis官网简介**：

- Redis简称`Remote Dictionary Server`(远程字典服务)
- Redis是完全开源的，使用ANSIC语言编写，遵守BSD协议，是一个高性能的Key-Value数据库
- Redis提供了丰富的数据结构，例如String、Hash、 List、Set、SortedSet等等
- Redis的数据是存在于内存中的，同时Redis支持事务、持久化、LUA脚本、发布/订阅、缓存淘汰、流技术等多种功能特性
- Redis提供主从模式、Redis Sentinel和Redis Cluster集群架构方案

**Redis之父安特雷兹个人博客**：

- github：`https://github.com/antirez`
- 个人博客：`http://antirez.com/latest/0`

**redis文档资料地址**：

官网地址：

- 英文文档：`https://redis.io/`
- 中文文档：`http://www.redis.cn/`
- 中文文档：`https://www.redis.com.cn/documentation.html`

redis下载地址：`https://redis.io/download/`   

本教程使用版本：`redis-7.2.0.tar.gz`

其他文档资料：

- Redis源码地址：`https://github.com/redis/redis`    中国大陆打开慢多刷几次或梯子试试

- Redis在线测试：`https://try.redis.io/`
- Redis命令参考：`http://doc.redisfans.com/`
- 各版本新特性介绍：`https://github.com/redis/redis/releases`
- 历史发布版本的源码：`https://download.redis.io/releases/`

**redis功能与应用**：

![image-20231102002653508](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220330043.png)

**应用一：分布式缓存**

- 挡在mysql数据库之前的带刀护卫

- redis与传统数据库关系(mysql)

  - Redis是key-value数据库(NoSQL一种)，mysql是关系数据库

  - Redis数据操作主要在内存，而mysql主要存储在磁盘

  - Redis在某一些场景使用中要明显优于mysql，比如计数器、排行榜等方面

  - Redis通常用于一些特定场景，需要与Mysql一起配合使用

  - 两者并不是相互替换和竞争关系，而是共用和配合使用

**应用二：内存存储和持久化(RDB+AOF)**

- redis支持异步将内存中的数据写到硬盘上，同时不影响继续服务

**应用三：高可用架构搭配**

- 单机、主从、哨兵、集群

**应用四：缓存穿透、击穿、雪崩**

**应用五：分布式锁**

**应用六：队列**

- Reids提供list和set操作，这使得Redis能作为一个很好的消息队列平台来使用

- 通过Reids的队列功能做购买限制。比如到节假日或者推广期间，进行一些活动，对用户购买行为进行限制，限制今天只能购买几次商品或者一段时间内只能购买一次。也比较适合适用

**应用八：排行版＋点赞**

- 在互联网应用中，有各种各样的排行榜，如电商网站的月度销量排行榜、社交APP的礼物排行榜、小程序的投票排行榜等等
- Redis提供的zset数据类型能够快速实现这些复杂的排行榜。比如小说网站对小说进行排名，根据排名，将排名靠前的小说推荐给用户

**redis优势**：

- 性能极高-Redis能读的速度是110000次/秒，写的速度是81000次/秒
- Redis数据类型丰富，不仅仅支持简单的key-value类型的数据，同时还提供list，set，zset，hash等数据结构的存储
- Redis支持数据的持久化，可以将内存中的数据保持在磁盘中，重启的时候可以再次加载进行使用
- Redis支持数据的备份，即master-slave模式的数据备份

**总结**：Redis是一种Key-Value类型的缓存数据库

![image-20231102003348161](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220330749.png)

**Redis迭代演化和Redis7新特性浅谈**：

视频：`https://www.bilibili.com/video/BV1oW411u75R?p=1`

Redis之父安特雷兹的发言：`http://antirez.com/news/132`

Redis历史大版本核心特性回顾

![image-20231102011458499](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220330314.png)



![image-20231102011742260](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220330564.png)

Redis命名规则：

- Redis从发布至今，已经有十余年的时光了，一直遵循着自己的命名规则：


- 版本号第二位如果是奇数，则为非稳定版本如2.7、2.9、3.1
- 版本号第二位如果是偶数，则为稳定版本如2.6、2.8、3.0、3.2
- 当前奇数版本就是下一个稳定版本的开发版本，如2.9版本是3.0版本的开发版本
- 可以通过redis.io官网来下载自己感兴趣的版本进行源码阅读：
- 历史发布版本的源码：`https://download.redis.io/releases/`

Redis7.0新特性概述：

- 各版本新特性介绍：`https://github.com/redis/redis/releases`

- 2022年4月正式发布的Redis 7.0是目前Redis历史版本中变化最大的版本。此版本有超过50个以上新增命令、大量核心特性的也进行了新增和改进

- 大体和之前的redis版本保持一致和稳定，主要是自身底层性能和资源利用率上的优化和提高，如果生产上系统稳定，不用着急升级到最新的redis7版本，当然，如果你是从零开始的新系统，直接上Redis7.0-GA版

- 新特性总览：

  - Redis Functions：更高效更易用更好管理

  - Client-eviction：连接内存占用独立管理

  - Multi-part AOF：AOFRW不再是运维痛点

  - ACL v2：精细化权限管理

  - 新增命令：新增ZMPOP, BZMPOP，LMPOP, BLMPOP等新命令，对于EXPIRE和SET命令，新增了更多的命令参数选项。例如，ZMPOP的格式如下：`ZMPOP numkeys key [key ...] MIN|MAX [COUNT count]`，而BZMPOP是ZMPOP的阻塞版本

  - listpack替代ziplist：listpaqk是用来替代ziplist 的新数据结构，在7.0版本已经没有 ziplist 的配置了(6.0版本仅部分数据类型作为过渡阶段在使用)

  - 底层性能提升(和编码关系不大)


| 新特性                              | 描述                                                         |
| ----------------------------------- | ------------------------------------------------------------ |
| 多AOF文件支持                       | 7.0版本中一个比较大的变化就是 aof 文件由一个变成了多个，主要分为两种类型：基本文件(base files)、增量文件(incr files)，请注意这些文件名称是复数形式说明每一类文件不仅仅只有一个。在此之外还引入了一个清单文件(manifest)用于跟踪文件以及文件的创建和应用顺序(恢复) |
| config命令增强                      | 对于Config Set 和Get命令，支持在一次调用过程中传递多个配置参数。例如，现在我们可以在执行一次Config Set命令中更改多个参数: `config set maxmemory 10000001 maxmemory-clients 50% port 6399` |
| 限制客户端内存使用`Client-eviction` | 一旦Redis 连接较多，再加上每个连接的内存占用都比较大的时候，Redis总连接内存占用可能会达到maxmemory的上限，可以增加允许限制所有客户端的总内存使用量配置项，redis.config中对应的配置项<br/>//两种配置形式：指定内存大小、基于maxmemory的百分比<br/>maxmemory-clients 1g<br/>maxmemory-clients 10% |
| listpack紧凑列表调整                | listpack是用来替代ziplist的新数据结构，在7.0版本已经没有ziplist的配置了（6.0版本仅部分数据类型作为过渡阶段在使用）listpack已经替换了ziplist类似`hash-max-ziplist-entries`的配置 |
| 访问安全性增强ACL V2                | 在`redis.conf`配置文件中，`protected-mode`默认为`yes`，只有当你希望你的客户端在没有授权的情况下可以连接到`Redis server`的时候可以将`protected-mode`设置为`no` |
| Redis Functions                     | Redis函数，一种新的通过服务端脚本扩展Redis的方式，函数与数据本身一起存储。简言之，redis自己要去抢夺Lua脚本的饭碗 |
| RDB保存时间调整                     | 将持久化文件RDB的保存规则发生了改变，尤其是时间记录频度变化  |
| 命令新增和变动                      | Zset(有序集合)增加ZMPOP、BZMPOP、ZINTERCARD等命令<br/>Set(集合)增加SINTERCARD命令<br/>LIST (列表)增加LMPOP、BLMPOP，从提供的键名列表中的第一个非空列表键中弹出一个或多个元素 |
| 性能资源利用率、安全、等改进        | 自身底层部分优化改动，Redis核心在许多方面进行了重构和改进<br/>主动碎片整理V2：增强版主动碎片整理，配合`Jemalloc`版本更新，更快更智能，延时更低<br/>`HyperLogLog`改进：在Redis5.0中，`HyperLogLog`算法得到改进，优化了计数统计时的内存使用效率，7更加优秀更好的内存统计报告<br/>如果不为了API向后兼容，我们将不再使用slave一词 |

###  1.2  redis的安装配置

redis安装包下载：`https://github.com/redis/redis/releases`

企业里面做Redis开发，99%都是Linux版的运用和安装，几乎不会涉及到Windows版，故Windows版不作为重点，本教程进行Linux版的redis安装

Linux环境安装Redis必须先具备gcc编译环境

>gcc简介：gcc是linux下的一个编译程序，是C程序的编译工具。GCC(GNU Compiler Collection) 是 GNU(GNU's Not Unix) 计划提供的编译器家族，它能够支持 C, C++, Objective-C, Fortran, Java 和 Ada 等等程序设计语言前端，同时能够运行在 x86, x86-64, IA-64, PowerPC, SPARC和Alpha 等等几乎目前所有的硬件平台上。鉴于这些特征，以及 GCC 编译代码的高效性，使得 GCC 成为绝大多数自由软件开发编译的首选工具。虽然对于程序员们来说，编译器只是一个工具，除了开发和维护人员，很少有人关注编译器的发展，但是 GCC 的影响力是如此之大，它的性能提升甚至有望改善所有的自由软件的运行效率，同时它的内部结构的变化也体现出现代编译器发展的新特征

Linux环境安装gcc（安装redis之前需要具备c++库环境）

```shell
# Linux环境安装gcc（安装redis之前需要具备c++库环境）
yum -y install gcc-c++
# 查看版本
gcc -v

# 查看redis版本
redis-server -v
```

一、下载获得redis-7.2.3.tar.gz后将它放入Linux目录/opt

```shell
# 将windows上的压缩包上传到linux系统的/opt目录下 (第三方软件一般安装到/opt目录下)
scp C:\Users\myy\Desktop\redis-7.2.3.tar.gz  root@192.168.56.10:/opt
```

二、opt目录下解压redis

```shell
tar -zxvf redis-7.2.3.tar.gz
```

三、在/opt/redis-7.2.3目录下执行make命令

```shell
cd /opt/redis-7.2.3
make && make install
```

四、查看默认安装目录: usr/local/bin

```shell
# Linux下的/usr/local类似windows系统的C:\Program Files
cd /usr/local/bin
```

![image-20231104234723431](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220331929.png)

>redis-benchmark：性能测试工具，服务启动后运行该命令，看看自己本子性能如何
>
>redis-check-aof:修复有问题的AOF文件，rdb和aof后面讲
>
>redis-check-dump:修复有问题的dump.rqb文件
>
>redis-cli：客户端，操作入口
>
>redis-sentinel: redis集群使用
>
>redis-server: Redis服务器启动命令

五、基于redis.conf进行redis的配置（尽量保留一份原生的redis.conf配置）

```shell
# 将默认的redis.conf拷贝到自己定义好的一个路径下，比如/myredis
cd /opt/redis-7.2.3
mkdir /myredis
cp redis.conf /myredis/redis7.conf
```

六、修改/myredis目录下redis.conf配置文件做初始化设置，redis.conf配置文件，改完后确保生效，记得重启，记得重启，修改内容如下：

>默认daemonize no        改为 daemonize yes
>
>默认protected-mode yes   改为 protected-mode no
>
>默认bind 127.0.0.1       改为 直接注释掉(默认bind 127.0.0.1只能本机访问)或改成本机IP地址，否则影响远程IP连接
>
>添加redis密码           改为 requirepass 你自己设置的密码

![image-20231105002323918](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220331979.png)

七、启动redis服务

```shell
# 启动redis服务
redis-server /myredis/redis7.conf

# 查看redis进程 
[root@localhost vagrant]#  ps -ef | grep redis | grep -v grep
polkitd   2811  2760  0 12:57 ?        00:00:56 redis-server *:6379
```

八、连接服务

```shell
# 通过密码111111连接redis
[root@localhost vagrant]# redis-cli -a 123456 -p 6379
Warning: Using a password with '-a' or '-u' option on the command line interface may not be safe
127.0.0.1:6379>

# 重新打开一个linux命令窗口，查看redis进程，发现处理redis-server服务端之外还有redis-cli客户端
[vagrant@localhost ~]$ ps -ef | grep redis
polkitd   2811  2760  0 12:57 ?        00:00:58 redis-server *:6379
root     18793 18444  0 16:39 pts/0    00:00:00 redis-cli -a 123456 -p 6379
vagrant  18863 18764  0 16:40 pts/1    00:00:00 grep --color=auto redis

# 在redis客户端执行ping命令，响应PONG则说明安装成功，redis可以正常使用
127.0.0.1:6379> ping
PONG

# redis使用示例
127.0.0.1:6379> set k1 helloworld
OK
127.0.0.1:6379> get k1
"helloworld"
```

九、关闭redis服务

```shell
# 先退出redis
127.0.0.1:6379> quit
# 单实例关闭:
[root@localhost vagrant]# redis-cli -a 111111 shutdown
# 多实例关闭，指定端口关闭:
[root@localhost vagrant]# redis-cli -p 6379 shutdown
```

十、redis卸载

```shell
# 停止redis-server服务
redis-cli -a 111111 shutdown
# 删除/usr/local/lib目录下与redis相关的文件
ls -l /usr/local/bin/redis-*
rm -rf /usr/local/bin/redis-*
```

![image-20231105010035700](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220331619.png)

###  1.3 reids 常用命令

redis操作命令官方文档：

英文文档：`https://redis.io/commands/`

中文文档：`http://www.redis.cn/commands/`

redis常用基础命令：

```shell
#在key存在时删除key
DEL key

# 序列化给定key ，并返回被序列化的值
DUMP key

# 检查给定key是否存在
EXISTS key

# 为给定key设置过期时间
EXPIRE key seconds

# EXPIREAT的作用和EXPIRE类似，都用于为key设置过期时间。不同在于EXPIREAT命令接受的时间参数是UNIX时间戳(unix timestamp)
EXPIREAT key timestamp

# 设置key的过期时间亿以毫秒计
PEXPIRE key milliseconds

# 设置key过期时间的时间戳(unix timestamp)以毫秒计
PEXPIREAT key milliseconds-timestamp

# 查找所有符合给定模式( pattern)的key
KEYS pattern

# 将当前数据库的key移动到给定的数据库db当中
MOVE key db

# 移除key的过期时间，key 将持久保持
PERSIST key

# 以毫秒为单位返回key的剩余的过期时间
PTTL key

# 以秒为单位，返回给定key的剩余生存时间(TTL, time to live)
TTL key

# 从当前数据库中随机返回一个key
RANDOMKEY

# 修改key的名称
RENAME key newkey

# 仅当newkey不存在时，将key改名为newkey
RENAMENX key newkey

#  返回key所储存的值的类型
TYPE key
```



```shell
# 查看当前库所有的key
keys *

# 判断某个key是否存在(返回1代表存在、0代表不存在，查询多个key时返回值代表存在的数目)
exists key

127.0.0.1:6379> exists k1 k2 k3
(integer) 3

# 查看key的类型
type key

# 删除指定的key数据
del key

# 非阻塞删除，仅仅将keys从keyspace元数据中删除，真正的删除会在后续异步中操作
unlink key

# 查看还有多少秒过期，-1表示永不过期，-2表示已过期
ttl key

# 为给定的key设置过期时间
expire key 秒钟

# 将当前数据库的key移动到给定的数据库db当中
move key dbindex [0-15]

# 切换数据库[0-15]，默认为0
select dbindex

# 查看当前数据库key的数量
dbsize

# 清空当前库
flushdb

# 通杀全部库
flushall
```



```shell
# 命令不区分大小写，而key是区分大小写的
# 永远的帮助命令，help @类型
help @string
help @list
help @hash
help @hyperloglog
```



##  2. redis 十大数据类型

redis数据类型官方文档：`https://redis.io/docs/data-types/`

这里说的数据类型是value的数据类型，key的类型都是字符串

![image-20231105010834843](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220331969.png)

###  2.1  redis字符串(String)

**String类型**：

- redis字符特点：单key单value
- string是redis最基本的类型，一个key对应一个value
- string类型是二进制安全的，意思是redis的string可以包含任何数据，比如pgf图片或者序列化的对象
- string类型是Redis最基本的数据类型，一个redis中字符串value最多可以是512M

**命令**：

```shell
# 设置指定key的值
SET key value

# 获取指定key的值
GET key

# 返回key中字符串值的子字符
GETRANGE key start end

# 将给定key的值设为value ,并返回key的旧值(old value)
GETSET key value

# 对key所储存的字符串值，获取指定偏移量上的位(bit)
GETBIT key offset

# 获取所有(一个或多个)给定key的值
MGET key1 [key2..]

# 对key所储存的字符串值，设置或清除指定偏移量上的位(bit)
SETBIT key offset value

# 将值value关联到key ，并将key的过期时间设为seconds (以秒为单位)
SETEX key seconds value

# 只有在key不存在时设置key的值
SETNX key value

# 用value参数覆写给定key所储存的字符串值，从偏移量offset开始
SETRANGE key offset value

# 返回key所储存的字符串值的长度
STRLEN key

# 同时设置一个或多个key-value对
MSET key value [key value ...]

# 同时设置一个或多个key-value 对，当且仅当所有给定key都不存在
MSETNX key value [key value ...]

# 这个命令和SETEX 命令相似，但它以毫秒为单位设置key 的生存时间，而不是像SETEX 命令那样，以秒为单位
PSETEX key milliseconds value

# 将key中储存的数字值增一
INCR key

# 将key所储存的值加上给定的增量值(increment)
INCRBY key increment

# 将key所储存的值加上给定的浮点增量值(increment) 
INCRBYFLOAT key increment

# 将key中储存的数字值减一
DECR key

# key所储存的值减去给定的减量值(decrement)
DECRBY key decrement

# 如果key已经存在并且是一个字符串，APPEND 命令将value追加到key原来的值的末尾
APPEND key value
```



```shell
set key value [NX|XX] [GET] [EX seconds|PX milliseconds|EXAT unix-time-seconds|PXAT unix-time-milliseconds|KEEPTTL]
```



>SET命令有EX、PX、NX、XX以及KEEPTTL五个可选参数，其中KEEPTTL为6.0版本添加的可选参数，其它为2.6.12版本添加的可选参数
>
>- EX seconds：以秒为单位设置过期时间
>- PX milliseconds：以毫秒为单位设置过期时间
>- EXAT timestamp：设置以秒为单位的UNIX时间戳所对应的时间为过期时间
>- PXAT milliseconds-timestamp：设置以毫秒为单位的UNIX时间戳所对应的时间为过期时间
>- NX：键不存在的时候设置键值
>- XX： 键存在的时候设置键值
>- KEEPTTL：保留设置前指定键的生存时间
>- GET：返回指定键原本的值，若键不存在时返回nil
>
>SET命令使用EX、PX、NX参数，其效果等同于SETEX、PSETEX、SETX命令。根据官方文档的描述，未来版本中SETEX、PSETEX、SETNX命令可能会被淘汰
>
>EXAT、PXAT以及GET为Redis 6.2新增的可选参数
>
>返回值
>
>设置成功则返回ok。返回nil为未执行SET命令，如不满足NX、XX条件等。若使用GET参数，则返回该键原来的值，或在键不存在时返回nil

如何获得设置指定的Key过期的Unix时间，单位为秒
```	java
System.out.println(Long.toString(System.currentTimeMillis()/1000L));
```



```shell
#  不存在键值key的话就创建key-value键值对，否则不创建
set key value nx

# 存在键值key的话就更改key对应的value
set key value xx

# 为key设置新值value，同时获取key的旧值
set key value get

# 设置键值对并指定过期时间(单位为秒)
set key value ex 10

# 设置键值对并指定过期时间(单位为毫秒)
set key value px 8000

# 设置以毫秒为单位的UNIX时间戳所对应的时间为过期时间
# java中获取时间戳Long.toString(System.currentTimeMillis()/1000L)
set key value exat 1669298249
```

`keepttl`选项的作用：保留原有键值的过期时间

```shell
# set命令关于过期时间避开坑
# set命令指定key的过期时间为30秒，但是对key重新设置值时如果不指定过期时间，key将变成永不过期
127.0.0.1:6379> set k1 v1 ex 30
OK
127.0.0.1:6379> ttl k1
(integer) 21
127.0.0.1:6379> set k1 vv1
OK
127.0.0.1:6379> ttl k1
(integer) -1


# 使用keepttl更改键值对时可以保留原有的过期时间
127.0.0.1:6379> set k1 v1 ex 80
OK
127.0.0.1:6379> ttl k1
(integer) 74
127.0.0.1:6379> set k1 v1keep keepttl
OK
127.0.0.1:6379> ttl k1
(integer) 45
```

**同时设置/获取多个键值**：

```shell
# 同时设置多个值
# mset：同时设置一个或多个 key-value 对
MSET key value [key value...]
127.0.0.1:6379> mset k1 v1 k2 v2 k3 v3
OK
127.0.0.1:6379> mget k1 k2 k3
1) "v1"
2) "v2"
3) "v3"
127.0.0.1:6379> mget k1 k2 k4
1) "v1"
2) "v2"
3) (nil)
# msetnx:同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在
# msetnx设置多个值时，如果多个值中有一个不存在，该命令就会执行失败，所有值都保持原状
127.0.0.1:6379> msetnx k1 vv11 k4 v4
(integer) 0
127.0.0.1:6379> get k1
"v1"



MGET key [key ...]

mset/mget/msetnx
```

获取指定区间范围内的值

```shell
# getrange：获取指定区间范围内的值，类似between......and的关系
# 从零到负一表示全部
127.0.0.1:6379> set key5 abcd1234
OK
127.0.0.1:6379> getrange key5 0 -1
"abcd1234"

# setrange设置指定区间范围内的值，格式是setrange key值具体值
127.0.0.1:6379> setrange key5 1 xxx
(integer) 8
127.0.0.1:6379> get key5
"axxx1234"
```

数值增减

```shell
# 一定要是数字才能进行加减
# 递增数字
INCR key

# 增加指定的整数
INCRBY key increment

# 递减数值
DECR key

# 减少指定的整数
DECRBY key decrement
```

获取字符串长度和内容追加

```shell
# 获取字符串长度
STRLEN key

# 内容追加
APPEND key value
```

分布式锁

```shell
setnx key value

setex(set with expire)键秒值/setnx(set if not exist)

set key value [Ex seconds] [PX milliseconds][NX|XX]
EX: key在多少秒之后过期
PX: key在多少毫秒之后过期
NX: 当key不存在的时候，才创建key，效果等同于setnxx
XX: 当key存在的时候，覆盖key
```

getset(先get再set)

```shell
# getset：将给定 key 的值设为 value ，并返回 key 的旧值(old value)
# 简单一句话，先get然后立即set
127.0.0.1:6379> get key5
"axxx1234"
127.0.0.1:6379> getset key5 2468
"axxx1234"
127.0.0.1:6379> get key5
"2468"
```

应用场景：

```shell
比如抖音无限点赞某个视频或者商品，点一下加一次

是否喜欢的文章
阅读数：只要点击了rest地址，直接可以使用incr key 命令增加一个数字1，完成记录数字
```

###  2.2  redis列表(List)

**redis列表简介**：

-  redis列表特点：单key多value

- Redis列表是简单的字符串列表，按照插入顺序排序

- 可以添加一个元素到列表的头部(左边）或者尾部(右边)

- redis列表的底层实际是个双端链表，最多可以包含2^32-1个元素(4294967295,每个列表超过40亿个元素)

**redis列表底层原理**：

一个双端链表的结构，容量是2的32次方减1个元素，大概40多亿，主要功能有push/pop等，一般用在栈、队列、消息队列等场景

left、right都可以插入添加

如果键不存在，创建新的链表

如果键已存在，新增内容

如果值全移除，对应的键也就消失了

它的底层实际是个双向链表，对两端的操作性能很高，通过索引下标的操作中间的节点性能会较差

![image-20231105195320323](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220331618.png)

**redis列表常见命令**：

```shell
# 移出并获取列表的第一个元素，如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
BLPOP key1 [key2] timeout

# 移出并获取列表的最后一个元素，如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
BRPOP key1 [key2] timeout

# 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它;如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
BRPOPLPUSH source destination timeout

# 通过索引获取列表中的元素
LINDEX key index

# 在列表的元素前或者后插入元素
LINSERT key BEFOREIAFTER pivot value

# 获取列表长度
LLEN key

# 移出并获取列表的第一个元素
LPOP key

# 将一个或多个值插入到列表头部
LPUSH key value1 [value2]

# 获取列表指定范围内的元素
LRANGE key start stop

# 移除列表元素
LREM key count value

# 通过索引设置列表元素的值
LSET key index value

# 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除
LTRIM key start stop

# 移除并获取列表最后一个元素
RPOP key

# 移除列表的最后—个元素，并将该元素添加到另一个列表并返回
RPOPLPUSH source destination

# 在列表中添加—个或多个值
RPUSH key value1 [value2]

# 为已存在的列表添加值
RPUSHX key value
```

**列表命令使用案例**：

lpush/rpush/Irange

```shell
127.0.0.1:6379> LPUSH list1 1 2 3 4 5
(integer) 5
127.0.0.1:6379> LPUSH list1 11 22 33 44 55
(integer) 10
127.0.0.1:6379> type list1
list
127.0.0.1:6379> LRANGE list1 0 -1
 1) "55"
 2) "44"
 3) "33"
 4) "22"
 5) "11"
 6) "5"
 7) "4"
 8) "3"
 9) "2"
10) "1"
127.0.0.1:6379> LRANGE list1 0 2
1) "55"
2) "44"
3) "33"
```

lpop/rpop

```shell
127.0.0.1:6379> LPOP list1
"55"
127.0.0.1:6379> Lpop list1
"44"
127.0.0.1:6379> RPOP list1
"1"
127.0.0.1:6379> RPOP list1
"2"
127.0.0.1:6379> LRANGE list1 0 -1
1) "33"
2) "22"
3) "11"
4) "5"
5) "4"
6) "3"
```

lindex，按照索引下标获得元素(从上到下)

```shell
# 通过索引获取列表中的元素 lindex key index
127.0.0.1:6379> LRANGE list1 0 -1
1) "33"
2) "22"
3) "11"
4) "5"
5) "4"
6) "3"
127.0.0.1:6379> lindex list1 0
"33"
127.0.0.1:6379> lindex list1 3
"5"
```

llen  获取列表中元素的个数

```shell
127.0.0.1:6379> LLEN list1
(integer) 6
```

lrem key 数字N 给定值v1       解释(删除N个值等于v1的元素)

```shell
# 从left往right删除2个值等于v1的元素，返回的值为实际删除的数量
# LREM list3 0 值，表示删除全部给定的值。零个就是全部值
127.0.0.1:6379> lpush list2 v1 v1 v1 v2 v3 v3 v4 v5
(integer) 8
127.0.0.1:6379> LRANGE list2 0 -1
1) "v5"
2) "v4"
3) "v3"
4) "v3"
5) "v2"
6) "v1"
7) "v1"
8) "v1"
127.0.0.1:6379> LREM list2 2 v1
(integer) 2
127.0.0.1:6379> LRANGE list2 0 -1
1) "v5"
2) "v4"
3) "v3"
4) "v3"
5) "v2"
6) "v1"
```

ltrim key 开始index 结束index，截取指定范围的值后再赋值给key

```shell
# ltrim：截取指定索引区间的元素，格式是ltrim list的key 起始索引 结束索引
127.0.0.1:6379> LPUSH list1 5 4 3 2 1
(integer) 5
127.0.0.1:6379> LRANGE list1 0 -1
1) "1"
2) "2"
3) "3"
4) "4"
5) "5"
127.0.0.1:6379> LTRIM list1 2 3
OK
127.0.0.1:6379> LRANGE list1 0 -1
1) "3"
2) "4"
```

rpoplpush源列表目的列表

```shell
# 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
127.0.0.1:6379> LRANGE list1 0 -1
1) "3"
2) "4"
127.0.0.1:6379> LRANGE list2 0 -1
1) "v5"
2) "v4"
3) "v3"
4) "v3"
5) "v2"
6) "v1"
127.0.0.1:6379> RPOPLPUSH list1 list2
"4"
127.0.0.1:6379> LRANGE list1 0 -1
1) "3"
127.0.0.1:6379> LRANGE list2 0 -1
1) "4"
2) "v5"
3) "v4"
4) "v3"
5) "v3"
6) "v2"
7) "v1"
```

lset key index value  将指定位置的值替换成新值

```shell
#  将指定位置的值替换成新值
lset key index value
127.0.0.1:6379> LRANGE list2 0 -1
1) "4"
2) "v5"
3) "v4"
4) "v3"
5) "v3"
6) "v2"
7) "v1"
127.0.0.1:6379> LSET list2 3 Redis
OK
127.0.0.1:6379> LRANGE list2 0 -1
1) "4"
2) "v5"
3) "v4"
4) "Redis"
5) "v3"
6) "v2"
7) "v1"
```

linsert key before/after已有值插入的新值

```shell
# 在list某个已有值的前后再添加具体值
127.0.0.1:6379> LPUSH list3 1 2 a b c 3 d 5
(integer) 8
127.0.0.1:6379> LRANGE list3 0 -1
1) "5"
2) "d"
3) "3"
4) "c"
5) "b"
6) "a"
7) "2"
8) "1"
127.0.0.1:6379> LINSERT list3 before 3 oracle
(integer) 9
127.0.0.1:6379> LRANGE list3 0 -1
1) "5"
2) "d"
3) "oracle"
4) "3"
5) "c"
6) "b"
7) "a"
8) "2"
9) "1"
127.0.0.1:6379> LINSERT list3 before oracle mysql
(integer) 10
127.0.0.1:6379> LRANGE list3 0 -1
 1) "5"
 2) "d"
 3) "mysql"
 4) "oracle"
 5) "3"
 6) "c"
 7) "b"
 8) "a"
 9) "2"
10) "1"
```

Redis列表(List)应用场景：

微信公众号订阅的消息：

```
1 大V作者李永乐老师和CSDN发布了文章分别是 11 和 22
2 阳哥关注了他们两个，只要他们发布了新文章，就会安装进我的List
   lpush likearticle:阳哥id    11 22
3 查看阳哥自己的号订阅的全部文章，类似分页，下面0~10就是一次显示10条
  lrange likearticle:阳哥id 0 9
```

###  2.3  redis哈希表(Hash)

**redis哈希表简介**：

KV模式不变，但V是一个键值对，类似java中的`Map<string,Map<object,object>>`

Redis hash是一个 string 类型的 field (字段)和value(值）的映射表，hash特别适合用于存储对象

Redis中每个hash可以存储2^32-1键值对（40多亿)

**redis哈希表常用命令**：

```shell
# 删除—个或多个哈希表字段
HDEL key field2 [field2]

# 查看哈希表key中，指定的字段是否存在
HEXISTS key field

# 获取存储在哈希表中指定字段的值
HGET key field

# 获取在哈希表中指定key的所有字段和值
HGETALL key

# 为哈希表key中的指定字段的整数值加上增量increment
HINCRBY key field increment

# 为哈希表key中的指定字段的浮点数值加上增量increment
HINCRBYFLOAT key field increment

# 获取所有哈希表中的字段
HKEYS key

# 获取哈希表中字段的数量
HLEN key

# 获取所有给定字段的值
HMGET key field1 [field2]

# 同时将多个field-value (域-值)对设置到哈希表key中
HMSET key field1 value1 [field2 value21

# 将哈希表key中的字段field的值设为value
HSET key field value

# 只有在字段field不存在时，设置哈希表字段的值
HSETNX key field value

# 获取哈希表中所有值
HVALS key

# 迭代哈希表中的键值对
HSCAN key cursor [MATCH pattern] [COUNT count]
```

**redis哈希表案例**：

hset/hget/hmset/hmget/hgetall/hdel

```shell
127.0.0.1:6379> hset user:001 id 11 name zs age 25
(integer) 3
127.0.0.1:6379> hget user:001 id
"11"
127.0.0.1:6379> hget user:001 name
"zs"
127.0.0.1:6379> hget user:001 age
"25"

127.0.0.1:6379> HMSET user:001 id 12 name li4 age 26
OK
127.0.0.1:6379> HMGET user:001 id name age
1) "12"
2) "li4"
3) "26"

127.0.0.1:6379> HGETALL user:001
1) "id"
2) "12"
3) "name"
4) "li4"
5) "age"
6) "26"

127.0.0.1:6379> HDEL user:001 age
(integer) 1
127.0.0.1:6379> HGETALL user:001
1) "id"
2) "12"
3) "name"
4) "li4"
```

hlen 获取某个key内的全部数量

```shell
127.0.0.1:6379> HLEN user:001
(integer) 2
```

hexists key在key里面的某个值的key

```shell
127.0.0.1:6379> HEXISTS user:001 name
(integer) 1
127.0.0.1:6379> HEXISTS user:001 score
(integer) 0
```

hkeys/hvals

```shell
127.0.0.1:6379> HKEYS user:001
1) "id"
2) "name"
127.0.0.1:6379> HVALS user:001
1) "12"
2) "li4"
```

hincrby/hincrbyfloat

```shell
127.0.0.1:6379> HSET user:001 age 25 score 99.5
(integer) 2
127.0.0.1:6379> HGETALL user:001
1) "id"
2) "12"
3) "name"
4) "li4"
5) "age"
6) "25"
7) "score"
8) "99.5"
127.0.0.1:6379> HINCRBY user:001 age 2
(integer) 27
127.0.0.1:6379> HINCRBYFLOAT user:001 score 0.05
"99.55"
```

setnx 不存在则赋值

```shell
127.0.0.1:6379> HSETNX user:001 email 326723@163.com
(integer) 1
127.0.0.1:6379> HSETNX user:001 email 326723@163.com
(integer) 0
```

应用场景

```
新增商品 → hset shopcar:uid1024 334488 1
新增商品 → hset shopcar:uid1024 334477 1
增加商品数量 → hincrby shopcar:uid1024 334477 1
商品总数 → hlen shopcar:uid1024
全部选择 → hgetall shopcar:uid1024
```



###  2.4 redis集合(Set)

**redis集合简介**：

单值多value，且无重复

Redis 的 Set 是 String 类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据，集合对象的编码可以是 intset 或者 hashtable

Redis 中Set集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是 O(1)

集合中最大的成员数为 2^32 - 1 (4294967295, 每个集合可存储40多亿个成员)

**redis集合常用命令**：

```shell
# 向集合添加—个或多个成员
SADD key member1 [member2]

# 获取集合的成员数
SCARD key

# 返回给定所有集合的差集
SDIFF key1[key2]

# 返回给定所有集合的差集并存储在destination中
SDIFFSTORE destination key1 [key2]

# 返回给定所有集合的交集
SINTER key1 [key2]

# 判断member元素是否是集合key的成员
SISMEMBER key member

# 将member元素从source集合移动到destination集合
SMOVE source destination member

# 移除并返回集合中的一个随机元素
SPOP key

# 返回集合中一个或多个随机数
SRANDMEMBER key [count]

# 移除集合中—个或多个成员
SREM key member1 [member2]

# 返回所有给定集合的并集
SUNION key1 [key2]

# 所有给定集合的并集存储在destination集合中
SUNIONSTORE destination key1[key2]

# 迭代集合中的元素
SSCAN key cursor[MATCH pattern][COUNT count]
```

**redis集合案例**：

SADD key member [member ...] 添加元素

```shell
127.0.0.1:6379> SADD set1 1 1 1 2 2 2 3 4 5
(integer) 5
127.0.0.1:6379> SMEMBERS set1
1) "1"
2) "2"
3) "3"
4) "4"
5) "5"
127.0.0.1:6379> SISMEMBER set1 x
(integer) 0
127.0.0.1:6379> SISMEMBER set1 1
(integer) 1
127.0.0.1:6379> SISMEMBER set1 5
(integer) 1
127.0.0.1:6379> SREM set1 5
(integer) 1
127.0.0.1:6379> SMEMBERS set1
1) "1"
2) "2"
3) "3"
4) "4"
127.0.0.1:6379> SCARD set1
(integer) 4
```

```shell
SMEMBERS key                  遍历集合中的所有元素
SISMEMBER key member          判断元素是否在集合中
SREM key member [member ...]  删除元素
scard                         获取集合里面的元素个数
SRANDMEMBER key「数字]             从集合中随机展现设置的数字个数元素，元素不删除
```

```shell
从set集合里面随机取出2个
127.0.0.1:6379> SMEMBERS set1
1) "1"
2) "2"
3) "3"
4) "4"
127.0.0.1:6379> SRANDMEMBER set1 2
1) "4"
2) "3"

如果超过最大数量就全部取出
127.0.0.1:6379> SRANDMEMBER set1 3
1) "4"
2) "3"
3) "1"

如果写的值是负数，比如-3 ，表示需要取出3个，但是可能会有重复值
```

smove key1 key2在key1里已存在的某个值               将key1里已存在的某个值赋给key2

```shell
127.0.0.1:6379> SMEMBERS set1
1) "1"
2) "2"
3) "3"
4) "4"
127.0.0.1:6379> SADD set2 a b c
(integer) 3
127.0.0.1:6379> SMOVE set1 set2 7
(integer) 0
127.0.0.1:6379> SMOVE set1 set2 3
(integer) 1
127.0.0.1:6379> SMEMBERS set1
1) "1"
2) "2"
3) "4"
127.0.0.1:6379> SMEMBERS set2
1) "c"
2) "a"
3) "3"
4) "b"
```

SPOP key [数字]          从集合中随机弹出一个元素，出一个删一个

```shell
127.0.0.1:6379> SMEMBERS set2
1) "c"
2) "a"
3) "3"
4) "b"
127.0.0.1:6379> SPOP set2 3
1) "c"
2) "3"
3) "a"
127.0.0.1:6379> SMEMBERS set2
1) "b"
```

集合运算

集合的差集运算A-B

```shell
# 属于A但不属于B的元素构成的集合
SDIFF key [key ...]

127.0.0.1:6379> DEL set1
(integer) 1
127.0.0.1:6379> DEL set2
(integer) 1
127.0.0.1:6379> SADD set1 a b c 1 2
(integer) 5
127.0.0.1:6379> SADD set2 1 2 3 a x
(integer) 5
127.0.0.1:6379> SDIFF set1 set2
1) "c"
2) "b"
127.0.0.1:6379> SDIFF set2 set1
1) "3"
2) "x"

# 集合的并集运算AUB
属于A或者属于B的元素合并后的集合SUNION key [key ...]
127.0.0.1:6379> SUNION set1 set2
1) "a"
2) "c"
3) "3"
4) "b"
5) "2"
6) "x"
7) "1"

# 集台的交集运算ANB
属于A同时也属于B的共同拥有的元素构成的集合
SINTER key [key ...]
127.0.0.1:6379> SINTER set1 set2
1) "a"
2) "2"
3) "1"

SINTERCARD numkeys key [key ...][LIMIT limit]
redis7新命令
它不返回结果集，而只返回结果的基数
返回由所有给定集合的交集产生的集合的基数案例B

127.0.0.1:6379> SINTER set1 set2
1) "a"
2) "2"
3) "1"
127.0.0.1:6379> SINTERCARD 2 set1 set2

```

应用场景：

```shell
微信抽奖小程序

微信朋友圈点赞查看同赞朋友

QQ内推可能认识的人
```

微信抽奖小程序

| 抽奖中的场景                              | 使用的redis命令                                              |
| ----------------------------------------- | ------------------------------------------------------------ |
| 1 用户ID，立即参与按钮                    | sadd key 用户ID                                              |
| 2 显示已经有多少人参与了，上图23208人参加 | SCARD key                                                    |
| 3 抽奖(从set中任意选取N个中奖人)          | SRANDMEMBER key 2随机抽奖2个人，元素不删除<br/>SPOP key 3   随机抽奖3个人，元素会删除 |

微信朋友圈点赞查看同赞朋友

| 朋友圈点赞场景                          | 使用的redis命令                          |
| --------------------------------------- | ---------------------------------------- |
| 1新增点赞                               | sadd pub:msgID  点赞用户ID1  点赞用户ID2 |
| 2取消点赞                               | srem pub:msgID  点赞用户ID               |
| 3展现所有点赞过的用户                   | SMEMBERS pub:msgID                       |
| 4点赞用户数统计，就是常见的点赞红色数字 | scard  pub:msgID                         |
| 5判断某个朋友是否对楼主点赞过           | SISMEMBER  pub:msgID   用户ID            |

QQ内推可能认识的人

```shell
127.0.0.1:6379> SADD s1 1 2 3 4 5
(integer) 5
127.0.0.1:6379> SADD s2 3 4 5 5 6 7
(integer) 5
127.0.0.1:6379> SINTER s1 s2
1) "3"
2) "4"
3) "5"
# s2可能认识1和2
127.0.0.1:6379> SDIFF s1 s2
1) "1"
2) "2"
# s2可能认识6和7
127.0.0.1:6379> SDIFF s2 s1
1) "6"
2) "7"
```

###  2.5  redis有序集合(ZSet)

**redis有序集合简介**：

zset(sorted set：有序集合)

Redis zset 和 set 一样也是string类型元素的集合,且不允许重复的成员

不同的是每个元素都会关联一个double类型的分数，redis正是通过分数来为集合中的成员进行从小到大的排序

 zset的成员是唯一的,但分数(score)却可以重复

 zset集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是 O(1)。 集合中最大的成员数为 2^32 - 1

在set基础上，每个val值前加—一个score分数值。之前set是k1 v1 v2 v3,
现在zset是k1 score1 v1 score2 v2

**redis有序集合常用命令**：

```shell
# 向有序集合添加一个或多个成员，或者更新已存在成员的分数
ZADD key score1 member1 [score2 member2]

# 获取有序集合的成员数
ZCARD key

# 计算在有序集合中指定区间分数的成员数
ZCOUNT key min max

# 有序集合中对指定成员的分数加上增量increment
ZINCRBY key increment member

# 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合key中
ZINTERSTORE destination numkeys key [key ...1]

# 在有序集合中计算指定字典区间内成员数量
ZLEXCOUNT key min max

# 通过索引区间返回有序集合成指定区间内的成员
ZRANGE key start stop [WITHSCORES]

# 通过字典区间返回有序集合的成员
ZRANGEBYLEX key min max [LIMIT offset count]

# 返回有序集合中指定成员的索引
ZRANK key member

# 移除有序集合中的一个或多个成员
ZREM key member [member ...]

# 移除有序集合中给定的字典区间的所有成员
ZREMRANGEBYLEX key min max

# 移除有序集合中给定的排名区间的所有成员
ZREMRANGEBYRANK key start stop

# 移除有序集合中给定的分数区间的所有成员
ZREMRANGEBYSCORE key min max

# 返回有序集中指定区间内的成员，通过索引，分数从高到底
ZREVRANGE key start stop [WITHSCORES]

# 返回有序集中指定分数区间内的成员，分数从高到低排序
ZREVRANGEBYSCORE key max min [WITHSCORES]

# 返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序
ZREVRANK key member

# 计算给定的一个或多个有序集的并集，并存储在新的key中
ZUNIONSTORE destination numkeys key [key ...]

# 迭代有序集合中的元素（包括元素成员和元素分值)
ZSCAN key cursor [MATCH pattern] [COUNT count]
```

**redis列表命令案例**：

ZADD key score member [score member]  向有序集合中加入元素和该元素的分数   

```shell
127.0.0.1:6379> ZADD zset1 60 v1 70 v2 80 v3 90 v4 100 v5
(integer) 5
127.0.0.1:6379> ZRANGE zset1 0 -1
1) "v1"
2) "v2"
3) "v3"
4) "v4"
5) "v5"
```

ZRANGE key start stop [WITHSCORES]    按照元素分数从小到大的顺序返回索引从start到Jstop之间的所有元素

```shell
127.0.0.1:6379> ZRANGE zset1 0 -1 withscores
 1) "v1"
 2) "60"
 3) "v2"
 4) "70"
 5) "v3"
 6) "80"
 7) "v4"
 8) "90"
 9) "v5"
10) "100"
```

zrevrange  按照元素分数从大到小的顺序返回

```shell
127.0.0.1:6379> ZRANGE zset1 0 -1 withscores
 1) "v1"
 2) "60"
 3) "v2"
 4) "70"
 5) "v3"
 6) "80"
 7) "v4"
 8) "90"
 9) "v5"
10) "100"
127.0.0.1:6379> ZREVRANGE zset1 0 -1 withscores
 1) "v5"
 2) "100"
 3) "v4"
 4) "90"
 5) "v3"
 6) "80"
 7) "v2"
 8) "70"
 9) "v1"
10) "60"
```

`ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT offset count] `      获取指定分数范围[min max]的元素

```shell
# 默认是闭区间， ( 表示不包含
# withscores ( 不包含
# limit 作用是返回限制
# limit开始下标步多少步
127.0.0.1:6379> ZRANGE zset1 0 -1 withscores
 1) "v1"
 2) "60"
 3) "v2"
 4) "70"
 5) "v3"
 6) "80"
 7) "v4"
 8) "90"
 9) "v5"
10) "100"
127.0.0.1:6379> ZRANGEBYSCORE zset1 60 90
1) "v1"
2) "v2"
3) "v3"
4) "v4"
127.0.0.1:6379> ZRANGEBYSCORE zset1 60 90 withscores
1) "v1"
2) "60"
3) "v2"
4) "70"
5) "v3"
6) "80"
7) "v4"
8) "90"
127.0.0.1:6379> ZRANGEBYSCORE zset1 (60 90 withscores
1) "v2"
2) "70"
3) "v3"
4) "80"
5) "v4"
6) "90"
127.0.0.1:6379> ZRANGEBYSCORE zset1 60 (90 withscores
1) "v1"
2) "60"
3) "v2"
4) "70"
5) "v3"
6) "80"
127.0.0.1:6379> ZRANGEBYSCORE zset1 60 (90 withscores limit 0 2
1) "v1"
2) "60"
3) "v2"
4) "70"
127.0.0.1:6379> ZRANGEBYSCORE zset1 60 (90 withscores limit 0 3
1) "v1"
2) "60"
3) "v2"
4) "70"
5) "v3"
6) "80"
```

ZSCORE key member   获取元素的分数

```shell
127.0.0.1:6379> ZSCORE zset1 v4
"90"
```

```shell
# ZCARD key  获取集合中元素的数量
127.0.0.1:6379> ZRANGE zset1 0 -1 withscores
 1) "v1"
 2) "60"
 3) "v2"
 4) "70"
 5) "v3"
 6) "80"
 7) "v4"
 8) "90"
 9) "v5"
10) "100"
127.0.0.1:6379> ZCARD zset1
(integer) 5

# zcount ：获取分数区间内元素个数，zcount key 开始分数区间 结束分数区间
127.0.0.1:6379> zcount zset1 60 80
(integer) 3
# zrank： 获取value在zset中的下标位置
127.0.0.1:6379> ZRANGE zset1 0 -1 withscores
 1) "v1"
 2) "60"
 3) "v2"
 4) "70"
 5) "v3"
 6) "80"
 7) "v4"
 8) "90"
 9) "v5"
10) "100"
127.0.0.1:6379> ZRANK zset1 v1
(integer) 0
127.0.0.1:6379> ZRANK zset1 v2
(integer) 1
127.0.0.1:6379> ZRANK zset1 v3
(integer) 2

# zscore：按照值获得对应的分数
127.0.0.1:6379> ZRANGE zset1 0 -1 withscores
 1) "v1"
 2) "60"
 3) "v2"
 4) "70"
 5) "v3"
 6) "80"
 7) "v4"
 8) "90"
 9) "v5"
10) "100"
127.0.0.1:6379> ZSCORE zset1 60
(nil)
127.0.0.1:6379> ZSCORE zset1 70
(nil)
127.0.0.1:6379> ZSCORE zset1 v1
"60"
127.0.0.1:6379> ZSCORE zset1 v2
"70"
127.0.0.1:6379> ZSCORE zset1 v3
"80"
```

zrem key  某score下对应的value值          作用是删除元素

```shell
# zrem key score某个对应值，可以是多个值
127.0.0.1:6379> ZRANGE zset1 0 -1 withscores
 1) "v1"
 2) "60"
 3) "v2"
 4) "70"
 5) "v3"
 6) "80"
 7) "v4"
 8) "90"
 9) "v5"
10) "100"
127.0.0.1:6379> ZREM zset1 v5
(integer) 1
127.0.0.1:6379> ZRANGE zset1 0 -1 withscores
1) "v1"
2) "60"
3) "v2"
4) "70"
5) "v3"
6) "80"
7) "v4"
8) "90"
127.0.0.1:6379> ZREM zset1 v5
(integer) 0
```

ZINCRBY key increment member   增加某个元素的分数

```shell
127.0.0.1:6379> ZRANGE zset1 0 -1 withscores
1) "v1"
2) "60"
3) "v2"
4) "70"
5) "v3"
6) "80"
7) "v4"
8) "90"
127.0.0.1:6379> ZINCRBY zset1 3 v1
"63"
```

ZCOUNT key min max   获得指定分数范围内的元素个数

```shell
127.0.0.1:6379> ZRANGE zset1 0 -1 withscores
1) "v1"
2) "63"
3) "v2"
4) "70"
5) "v3"
6) "80"
7) "v4"
8) "90"
127.0.0.1:6379> ZCOUNT zset1 65 80
(integer) 2
127.0.0.1:6379> ZCOUNT zset1 60 100
(integer) 4
```

ZMPOP    从键名列表中的第一个非空排序集中弹出一个或多个元素，它们是成员分数对

```shell
127.0.0.1:6379> ZADD myset 1 one 2 two 3 three
(integer) 3
127.0.0.1:6379> ZRANGE myset 0 -1 withscores
1) "one"
2) "1"
3) "two"
4) "2"
5) "three"
6) "3"
127.0.0.1:6379> ZMPOP 1 myset min count 1
```

zrank key values值，作用是获得下标值

zrevrank key values值，作用是逆序获得下标值

```shell
127.0.0.1:6379> ZRANGE zset1 0 -1 withscores
1) "v1"
2) "63"
3) "v2"
4) "70"
5) "v3"
6) "80"
7) "v4"
8) "90"
127.0.0.1:6379> ZRANK zset1 v2
(integer) 1
127.0.0.1:6379> ZREVRANK zset1 v2
(integer) 2
```

**有序集合应用场景**：

应用场景：根据商品销售对商品进行排序显示

思路：定义商品销售排行榜(sorted set集合)，key为goods:sellsort，分数为商品销售数量

| 商品排序场景                                     | 命令                                   |
| ------------------------------------------------ | -------------------------------------- |
| 商品编号1001的销量是9，商品编号1002的销量是15    | zadd  goods:sellsort  9 1001 15 1002   |
| 有一个客户又买了2件商品1001，商品编号1001销量加2 | zincrby  goods:sellsort 2 1001         |
| 求商品销量前10名                                 | ZRANGE  goods:sellsort  0 9 withscores |

```shell
127.0.0.1:6379> ZADD goods:sellsort 9 1001 15 1002
(integer) 2
127.0.0.1:6379> ZRANGE goods:sellsort 0 10 withscores
1) "1001"
2) "9"
3) "1002"
4) "15"
127.0.0.1:6379> ZINCRBY goods:sellsort 2 1001
"11"
127.0.0.1:6379> ZRANGE goods:sellsort 0 10 withscores
1) "1001"
2) "11"
3) "1002"
4) "15"
```

###  2.6 redis位图(bitmap)

**位图简介**：

由0和1状态表现的二进制位的bit数组

![image-20231105013118967](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220332821.png)



说明：用String类型作为底层数据结构实现的一种统计二值状态的数据类型

位图本质是数组，它是基于String数据类型的按位的操作。该数组由多个二进制位组成，每个二进制位都对应一个偏移量(我们称之为一个索引)

 

Bitmap支持的最大位数是2^32位，它可以极大的节约存储空间，使用512M内存就可以存储多达42.9亿的字节信息(2^32 = 4294967296)

位图作用：用于状态统计，类似AtomicBoolean

位图的优点：内存占用少

- 以统计用户的登录情况为例：

  - 按年去存储一个用户的签到情况，365 天只需要 365 / 8 ≈ 46 Byte，1000W 用户量一年也只需要 44 MB 就足够了

  - 假如是亿级的系统，每天使用1个1亿位的Bitmap约占12MB的内存（10^8/8/1024/1024），10天的Bitmap的内存开销约为120MB，内存压力不算太高

  - 此外，在实际使用时，最好对Bitmap设置过期时间，让Redis自动删除不再需要的签到记录以节省内存开销

**位图基本命令**：

| 命令                        | 作用                                                | 时间复杂度 |
| --------------------------- | --------------------------------------------------- | ---------- |
| setbit key offset val       | 给指定key的值的第offset赋值val                      | O(1)       |
| getbit key offset           | 获取指定key的第offset位                             | O(1)       |
| bitcount key start end      | 返回指定key中[start,end]中为1的数量                 | O(n)       |
| bitop operation destkey key | 对不同的二进制存储数据进行位运算(AND、OR、NOT、XOR) | O(n)       |

**位图命令使用案例**：

setbit key offset value

setbit 键偏移位只能零或者1

Bitmap的偏移量是从零开始算的

```shell
127.0.0.1:6379> SETBIT k1 1 1
(integer) 1
127.0.0.1:6379> SETBIT k1 3 1
(integer) 1
127.0.0.1:6379> SETBIT k1 5 1
(integer) 1
127.0.0.1:6379> SETBIT k1 6 0
(integer) 1
127.0.0.1:6379> type k1
string
127.0.0.1:6379> GETBIT k1 0
(integer) 0
127.0.0.1:6379> GETBIT k1 3
(integer) 1
127.0.0.1:6379> GETBIT k1 5
(integer) 1
127.0.0.1:6379> GETBIT k1 365
(integer) 0
```

strlen  统计字节数占用多少

```shell
# 不是字符串长度而是占据几个字节，超过8位后自己按照8位一组一byte再扩容
127.0.0.1:6379> SETBIT k1 0 1
(integer) 0
127.0.0.1:6379> SETBIT k1 7 1
(integer) 0
127.0.0.1:6379> STRLEN k1
(integer) 1
127.0.0.1:6379> SETBIT k1 8 1
(integer) 0
127.0.0.1:6379> STRLEN k1
(integer) 2
```

bitcount   全部键里面含有1的有多少个?

```shell
127.0.0.1:6379> SETBIT k1 0 1
(integer) 0
127.0.0.1:6379> SETBIT k1 7 1
(integer) 0
127.0.0.1:6379> SETBIT k1 8 1
(integer) 0
127.0.0.1:6379> BITCOUNT k1
(integer) 3
# 应用场景：统计用户用户签到的天数
127.0.0.1:6379> SETBIT uid:login123 1 1
(integer) 0
127.0.0.1:6379> SETBIT uid:login123 2 1
(integer) 0
127.0.0.1:6379> SETBIT uid:login123 3 1
(integer) 0
127.0.0.1:6379> BITCOUNT uid:login123
(integer) 3
```

bitop

`  BITOP AND|OR|XOR|NOT destkey key [key ...]`       对不同的二进制存储数据进行位运算(AND、OR、NOT、XOR)

 连续2天都签到的用户

加入某个网站或者系统，它的用户有1000W，做个用户id和位置的映射

比如0号位对应用户id：uid-092iok-lkj

比如1号位对应用户id：uid-7388c-xxx

```shell
127.0.0.1:6379> help bitop
  BITOP AND|OR|XOR|NOT destkey key [key ...]
  summary: Performs bitwise operations on multiple strings, and stores the result.
  since: 2.6.0
  group: bitmap

# 0号位对应用户id：uid-092iok-lkj
127.0.0.1:6379> HSET uid:map 0 uid-092iok-lkj
(integer) 1
# 1号位对应用户id：uid-7388c-xxx
127.0.0.1:6379> HSET uid:map 1 uid-7388c-xxx
(integer) 1
127.0.0.1:6379> HGETALL uid:map
1) "0"
2) "uid-092iok-lkj"
3) "1"
4) "uid-7388c-xxx"
# 0号用户在2020-08-08这一天登录
127.0.0.1:6379> SETBIT 20200808 0 1
(integer) 0
# 1号用户在2020-08-08这一天登录
127.0.0.1:6379> SETBIT 20200808 1 1
(integer) 0
# 2号用户在2020-08-08这一天登录
127.0.0.1:6379> SETBIT 20200808 2 1
(integer) 0
# 3号用户在2020-08-08这一天登录
127.0.0.1:6379> SETBIT 20200808 3 1
(integer) 0
# 0号用户在2020-08-09这一天登录
127.0.0.1:6379> SETBIT 20200809 0 1
(integer) 0
# 2号用户在2020-08-09这一天登录
127.0.0.1:6379> SETBIT 20200809 2 1
(integer) 0
# 2020-08-08这一天登录的人数
127.0.0.1:6379> BITCOUNT 20200808
(integer) 4
# 2020-08-09这一天登录的人数
127.0.0.1:6379> BITCOUNT 20200809
(integer) 2
# 将20200808的二进制位和20200809的二进制位做与运算，运算结果放到destkey中
# destkey中的位图中x位是1则代表对应的x号用户在20200808和20200809都登陆过
127.0.0.1:6379> BITOP and destkey 20200808 20200809
(integer) 1
# 在20200808和20200809都登录的人数
127.0.0.1:6379> BITCOUNT destkey
(integer) 2
```

setbit和getbit案例说明：统计用户的登录情况

```shell
# u1用户在2021年6月的第1天登陆过
127.0.0.1:6379> SETBIT sign:u1:202106 0 1
(integer) 0
# u1用户在2021年6月的第2天登陆过
127.0.0.1:6379> SETBIT sign:u1:202106 1 1
(integer) 0
# u1用户在2021年6月的第3天登陆过
127.0.0.1:6379> SETBIT sign:u1:202106 2 1
(integer) 0
# u1用户在2021年6月的第4天登陆过
127.0.0.1:6379> SETBIT sign:u1:202106 3 1
(integer) 0
# u1用户在2021年6月的第7天登陆过
127.0.0.1:6379> SETBIT sign:u1:202106 6 1
(integer) 0
# 查询u1用户在2021年6月的第4天的登录情况
127.0.0.1:6379> GETBIT sign:u1:202106 3
(integer) 1
127.0.0.1:6379> GETBIT sign:u1:202106 30
# 查询u1用户在2021年6月的第31天的登录情况
(integer) 0
# 查询u1用户在2021年6月一共登录了多少天
127.0.0.1:6379> BITCOUNT sign:u1:202106 0 30
(integer) 5
```

**位图应用场景**：

用户是否登陆过Y、N

比如京东每日签到送京豆电影

广告是否被点击播放过

钉钉打卡上下班，签到统计

###  2.7 redis基数统计(HyperLogLog)

**redis基数简介**：

- HyperLogLog 是用来做基数统计的算法，HyperLogLog 的优点是，在输入元素的数量或者体积非常非常大时，计算基数所需的空间总是固定且是很小的
- 在Redis里面，每个HyperLogLog键只需要花费12KB内存，就可以计算接近2^64 个不同元素的基数。这和计算基数时，元素越多耗费内存就越多的集合形成鲜明对比
- 但是，因为HyperLogLog只会根据输入元素来计算基数，而不会储存输入元素本身，所以 HyperLogLog不能像集合那样，返回输入的各个元素

**HyperLogLog是什么**：

- 去重复统计功能的基数估计算法就是HyperLogLog
- 基数：是一种数据集，去重复后的真实个数。(全集) I= {2,4,6,8,77,39,4,8,10}去掉重复的内容后基数={2,4,6,8,77,39,10} =7
- 基数统计：用于统计一个集合中不重复的元素个数，就是对集合去重复后剩余元素的计算

**应用场景**：

- 统计基个网站的UV、统计某个文章的UV。什么是UV：Unique visitor，独立访客，一般理解为客户端IP。需要去重考虑
- 用户搜索网站关键词的数量
- 统计用户每天搜索不同词条个数

**redis基数常用命令**：

```shell
# 添加指定元素到HyperLogLog中
PFADD key element [element ...]

# 返回给定HyperLogLog的基数估算值
PFCOUNT key [key ...]

# 将多个HyperLogLog合并为一个HyperLogLog
PFMERGE destkey sourcekey [sourcekey ...]
```

>The API is constituted of three new commands :
>PFADD var element element ... element
>PFCOUNT var
>PFMERGE dst src src src ... src
>The commands prefix is “PF” in honor of Philippe Flajolet [4]

```shell
127.0.0.1:6379> PFADD hello1 1 3 4 5 7 9
(integer) 1
127.0.0.1:6379> PFADD hello2 2 4 4 4 6 8 9
(integer) 1
127.0.0.1:6379> PFCOUNT hello2
(integer) 5
127.0.0.1:6379> PFMERGE distResult hello1 hello2
OK
127.0.0.1:6379> PFCOUNT distResult
(integer) 9
# HyperLogLog底层是String类型
127.0.0.1:6379> type hello2
string
```

**应用场景**：

天猫网站首页亿级uv的Redis统计方案

应用场景-编码实战案例见高级篇

###  2.8 redis地理空间(GEO)

**GEO简介**：

Redis在3.2版本以后增加了地理位置的处理

Redis GEO 主要用于存储地理位置信息，并对存储的信息进行操作，包括

添加地理位置的坐标

获取地理位置的坐标

计算两个位置之间的距离

根据用户给定的经纬度坐标来获取指定范围内的地理位置集合

移动互联网时代LBS应用越来越多，交友软件中附近的小姐姐、外卖软件中附近的美食店铺、高德地图附近的核酸检查点等等，那这种附近各种形形色色的XXX地址位置选择是如何实现的？

地球上的地理位置是使用二维的经纬度表示，经度范围 (-180, 180]，纬度范围 (-90, 90]，只要我们确定一个点的经纬度就可以名取得他在地球的位置。

例如滴滴打车，最直观的操作就是实时记录更新各个车的位置，

然后当我们要找车时，在数据库中查找距离我们(坐标x0,y0)附近r公里范围内部的车辆

使用如下SQL即可：

```sql
select taxi from position where x0-r < x < x0 + r and y0-r < y < y0+r
```

但是这样会有什么问题呢？

1.查询性能问题，如果并发高，数据量大这种查询是要搞垮数据库的

2.这个查询的是一个矩形访问，而不是以我为中心r公里为半径的圆形访问

3.精准度的问题，我们知道地球不是平面坐标系，而是一个圆球，这种矩形计算在长距离计算时会有很大误差

**原理**：

核心思想就是将球体转换为平面，区块转换为一点

地理知识百科：`https://baike.baidu.com/item/经纬线/5596978?fr=aladdin`

![image-20231109012226086](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220332225.png)

主要分为三步

将三维的地球变为二维的坐标在将二维的坐标转换为一维的点块

最后将一维的点块转换为二进制再通过base32编码

**基础命令**：

```shell
GEOADD  多个经度(longitude)、纬度(latitude)、位置名称(member)添加到指定的 key中

GEOPOS 从键里面返回所有给定位置元素的位置（(经度和纬度)

GEODIST 返回两个给定位置之间的距离

GEORADIUS 以给定的经纬度为中心，返回与中心的距离不超过给定最大距离的所有位置元素

GEORADIUSBYMEMBER跟GEORADIUS类似

GEOHASH 返回一个或多个位置元素的Geohash表示
```

**命令实操**：

如何获得某个地址的经纬度：访问百度地图`http://api.map.baidu.com/lbsapi/getpoint/`，搜索地标即可获取对应的经纬度坐标

![image-20231109020622285](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220332196.png)



GEOADD  添加经纬度坐标

```shell
# geoadd 用于存储指定的地理空间位置，可以将一个或多个经度(ongitude)、纬度(latitude)、位置名称(member)添加到指定的key中

# geoadd语法格式如下:
127.0.0.1:6379> help GEOADD
  GEOADD key [NX|XX] [CH] longitude latitude member [longitude latitude member ...]
  summary: Adds one or more members to a geospatial index. The key is created if it doesn't exist.
  since: 3.2.0
  group: geo


# 添加地理位置
127.0.0.1:6379> GEOADD city 116.403963 39.915119 "天安门" 116.403414 39.924091 "故宫" 116.024067 40.362639 "长城"
(integer) 3
127.0.0.1:6379> type city
zset
127.0.0.1:6379> ZRANGE city 0 -1
1) "\xe5\xa4\xa9\xe5\xae\x89\xe9\x97\xa8"
2) "\xe6\x95\x85\xe5\xae\xab"
3) "\xe9\x95\xbf\xe5\x9f\x8e"

# 中文乱码如何处理：redis-cli --raw
127.0.0.1:6379> quit
[root@localhost vagrant]# redis-cli -a 123456 --raw
Warning: Using a password with '-a' or '-u' option on the command line interface may not be safe.
AUTH failed: ERR AUTH <password> called without any password configured for the default user. Are you sure your configuration is correct?
127.0.0.1:6379> ZRANGE city 0 -1
天安门
故宫
长城
```

GEOPOS  返回经纬度

```shell
# geopos 用于从给定的key里返回所有指定名称(member)的位置(经度和纬度)，不存在的返回nil
# geopos 语法格式如下:
127.0.0.1:6379> help GEOPOS
  GEOPOS key [member [member ...]]
  summary: Returns the longitude and latitude of members from a geospatial index.
  since: 3.2.0
  group: geo

127.0.0.1:6379> GEOPOS city 天安门 故宫 长城
116.40396326780319214
39.91511970338637383
116.40341609716415405
39.92409008156928252
116.02406591176986694
40.36263993239462167
```

GEOHASH    返回坐标的geohash表示

```shell
# Redis GEO 使用geohash来保存地理位置的坐标
# geohash用于获取一个或多个位置元素的geohash值
# geohash算法生成的base32编码值3维变2维变1维
# 主要分为三步:将三维的地球变为二维的坐标、在将二维的坐标转换为一维的点块、最后将一维的点块转换为二进制再通过base32编码
# geohash语法格式如下:
127.0.0.1:6379> help GEOHASH
  GEOHASH key [member [member ...]]
  summary: Returns members from a geospatial index as geohash strings.
  since: 3.2.0
  group: geo

127.0.0.1:6379> GEOHASH city 天安门 故宫 长城
wx4g0f6f2v0
wx4g0gfqsj0
wx4t85y1kt0
```

GEODIST   获取两个位置之间距离

```shell
127.0.0.1:6379> help GEODIST
  GEODIST key member1 member2 [M|KM|FT|MI]
  summary: Returns the distance between two members of a geospatial index.
  since: 3.2.0
  group: geo
# 后面参数是距离单位:m米 k千米 ft英尺 mi英里
127.0.0.1:6379> GEODIST city 天安门 长城 km
59.3390
127.0.0.1:6379> GEODIST city 天安门 故宫 m
998.8332
```

GEORADIUS   以半径为中心，查找附近的地标

>georadius 以给定的经纬度为中心， 返回键包含的位置元素当中， 与中心的距离不超过给定最大距离的所有位置元素
>
>GEORADIUS city 116.418017 39.914402 10 km withdist withcoord count 10 withhash desc
>
>GEORADIUS city 116.418017 39.914402 10 km withdist withcoord withhash count 10 desc
>
>WITHDIST: 在返回位置元素的同时， 将位置元素与中心之间的距离也一并返回。 距离的单位和用户给定的范围单位保持一致
>
>WITHCOORD: 将位置元素的经度和维度也一并返回
>
>WITHHASH: 以 52 位有符号整数的形式， 返回位置元素经过原始 
>
>geohash 编码的有序集合分值。 这个选项主要用于底层应用或者调试， 实际中的作用并不大COUNT 限定返回的记录数

当前位置北京王府井(116.418017 39.914402)

```shell
127.0.0.1:6379> help GEORADIUS
  GEORADIUS key longitude latitude radius M|KM|FT|MI [WITHCOORD] [WITHDIST] [WITHHASH] [COUNT count [ANY]] [ASC|DESC] [STORE key|STOREDIST key]
  summary: Queries a geospatial index for members within a distance from a coordinate, optionally stores the result.
  since: 3.2.0
  group: geo
127.0.0.1:6379> GEORADIUS city 116.418017 39.914402 10 km withdist withcoord count 10 withhash desc
故宫
1.6470
4069885568908290
116.40341609716415405
39.92409008156928252
天安门
1.2016
4069885555089531
116.40396326780319214
39.91511970338637383
39.91511970338637383
127.0.0.1:6379> GEORADIUS city 116.418017 39.914402 10 km withdist withcoord withhash count 10 desc
故宫
1.6470
4069885568908290
116.40341609716415405
39.92409008156928252
天安门
1.2016
4069885555089531
116.40396326780319214
39.91511970338637383
```

GEORADIUSBYMEMBER  找出位于指定范围内的元素，中心点是由给定的位置元素决定

```
127.0.0.1:6379> help GEORADIUSBYMEMBER
  GEORADIUSBYMEMBER key member radius M|KM|FT|MI [WITHCOORD] [WITHDIST] [WITHHASH] [COUNT count [ANY]] [ASC|DESC] [STORE key|STOREDIST key]
  summary: Queries a geospatial index for members within a distance from a member, optionally stores the result.
  since: 3.2.0
  group: geo


127.0.0.1:6379> GEORADIUSBYMEMBER city 天安门 10 km withdist withcoord count 10 withhash
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

**应用场景（编码实战案例见高级篇）**：

美团地图位置附近的酒店推送

高德地图附近的核酸检查点

###  2.9 redis流(Stream)

**redis流简介**：

Redis Stream 是 Redis 5.0 版本新增加的数据结构

Redis Stream 主要用于消息队列（MQ，Message Queue），Redis 本身是有一个Redis发布订阅 (pub/sub) 来实现消息队列的功能，但它有个缺点就是消息无法持久化，如果出现网络断开、Redis 宕机等，消息就会被丢弃

简单来说发布订阅 (pub/sub) 可以分发消息，但无法记录历史消息

而 Redis Stream 提供了消息的持久化和主备复制功能，可以让任何客户端访问任何时刻的数据，并且能记住每一个客户端的访问位置，还能保证消息不丢失

Stream还是不能100%等价于Kafka、RabbitMQ来使用的，生产案例少，慎用

**Redis消息队列的2种方案（Redis5.0之前）**：

方案一：List 实现消息队列，List 实现方式其实就是点对点的模式

如果消息队列是简单的先进先出的话，用redis的list数据类型就可以实现消息队列的功能

按照插入顺序排序，你可以添加一个元素到列表的头部（左边）或者尾部（右边）

所以常用来做异步队列使用，将需要延后处理的任务结构体序列化成字符串塞进 Redis 的列表，另一个线程从这个列表中轮询数据进行处理

![image-20231110004238928](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220332521.png)

LPUSH、RPOP 左进右出  RPUSH、LPOP 右进左出

```shell
127.0.0.1:6379> LPUSH list1 1 2 3 4 5
5
127.0.0.1:6379> LRANGE list1 0 -1
5
4
3
2
1
127.0.0.1:6379> RPOP list1 1
1
127.0.0.1:6379> RPOP list1 1
2
127.0.0.1:6379> RPOP list1 1
3
127.0.0.1:6379> RPOP list1 1
4
127.0.0.1:6379> RPOP list1 1
5
```



方案二：Redis 发布订阅 (pub/sub) 有个缺点就是消息无法持久化，如果出现网络断开、Redis 宕机等，消息就会被丢弃。而且也没有 Ack 机制来保证数据的可靠性，假设一个消费者都没有，那消息就直接被丢弃了

![image-20231110004431053](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220332344.png)

**Stream流实现消息队列**：

Redis5.0版本新增了一个更强大的Stream数据结构，Stream就是Redis版的MQ消息中间件+阻塞队列

Stream可以实现消息队列，它支持消息的持久化、支持自动生成全局唯一ID、支持ack确认消息的模式、支持消费组模式等，让消息队列更加的稳定和可靠

**Stream底层结构和原理说明**：

stream结构

![image-20231110005838720](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220332398.png)

一个消息链表，将所有加入的消息都串起来，每个消息都有一个唯一的 ID 和对应的内容

| 概念              | 描述                                                         |
| ----------------- | ------------------------------------------------------------ |
| Message Content   | 消息内容                                                     |
| Consumer group    | 消费组，通过XGROUP CREATE命令创建，同一个消费组可以有多个消费者 |
| Last_delivered_id | 游标，每个消费组会有个游标last_delivered_id，任意一个消费者读取了消息都会使游标last_delivered_id往前移动 |
| Consumer          | 消费者，消费组中的消费者                                     |
| Pending_ids       | 消费者会有一个状态变量，用于记录被当前消费已读取但未ack的消息Id，如果客户端没有ack，这个变量里面的消息ID会越来越多，一旦某个消息被ack它就开始减少。这个pending_ids变量在Redis官方被称之为 PEL(Pending Entries List)，记录了当前已经被客户端读取的消息，但是还没有 ack (Acknowledge character：确认字符），它用来确保客户端至少消费了消息一次，而不会在网络传输的中途丢失了没处理 |

**基本命令理论简介**：

队列相关指令

| 指令名称  | 指令作用                                    |
| --------- | ------------------------------------------- |
| XADD      | 添加消息到队列末尾                          |
| XTRIM     | 限制Stream的长度，如果已经超长会进行截取    |
| XDEL      | 删除消息                                    |
| XLEN      | 获取Stream中的消息长度                      |
| XRANGE    | 获取消息列表(可以指定范围)，忽略删除的消息  |
| XREVRANGE | 和XRANGE相比区别在于反向获取，ID从大到小    |
| XREAD     | 获取消息(阻塞/非阻塞)，返回大于指定ID的消息 |

消费组相关指令

| 指令名称           | 指令作用                                                     |
| ------------------ | ------------------------------------------------------------ |
| XGROUP CREATE      | 创建消费者组                                                 |
| XREADGROUP GROUP   | 读取消费者组中的消息                                         |
| XACK               | ack消息，消息被标记为"已处理”                                |
| XGROUP SETID       | 设置消费者组最后递送消息的ID                                 |
| XGROUP DELCONSUMER | 删除消费者组                                                 |
| XPENDING           | 打印待处理消息的详细信息                                     |
| XCLAIM             | 转移消息的归属权（长期未被处理/无法处理的消息，转交给其他消费者组进行处理) |
| XINFO              | 打印Stream\ConsumerlGroup的详细信息                          |
| XINFO GROUPS       | 打印消费者组的详细信息                                       |
| XINFO STREAM       | 打印Stream的详细信息                                         |

四个特殊符号

```shell
-+     最小和最大可能出现的id
$      表示只消费新的消息，当前流中最大的id，可用于将要到来的信息
>      用于XREADGROUP命令，表示迄今还没有发送给组中使用者的信息，会更新消费者组的最后ID
*      用于XADD命令中，让系统自动生成id
```

**基本命令代码实操：stream生产者相关指令**：

XADD  添加消息到队列末尾

```shell
命令说明：
        消息ID必须要比上个ID大
        默认用星号表示自动生成规矩
        * 用于XADD命令中，让系统自动生成id

127.0.0.1:6379> help XADD
  XADD key [NOMKSTREAM] [MAXLEN|MINID [=|~] threshold [LIMIT count]] *|id field value [field value ...]
  summary: Appends a new message to a stream. Creates the key if it doesn't exist.
  since: 5.0.0
  group: stream

127.0.0.1:6379> type mystream
stream
127.0.0.1:6379> XADD mystream * id 11 cname zs
1699551345931-0
127.0.0.1:6379> XADD mystream * id 12 cname ls
1699551362642-0
127.0.0.1:6379> XADD mystream * id 13 cname ww
1699551381352-0
127.0.0.1:6379> XADD mystream * k1 v1 k2 v2 k3 v3
1699551413583-0
```

XADD 用于向Stream 队列中添加消息，如果指定的Stream 队列不存在，则该命令执行时会新建一个Stream 队列

![image-20231110013956350](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220333927.png)

//* 号表示服务器自动生成 MessageID(类似mysql里面主键auto_increment)，后面顺序跟着一堆 业务key/value

| XADD说明                                                     |
| ------------------------------------------------------------ |
| 信息条目指的是序列号，在相同的毫秒下序列号从0开始递增，序列号是64位长度，理论上在同一毫秒内生成的数据量无法到达这个级别，因此不用担心序列号会不够用。millisecondsTime指的是Redis节点服务器的本地时间，如果存在当前的毫秒时间戳比以前已经存在的数据的时间戳小的话（本地时间钟后跳），那么系统将会采用以前相同的毫秒创建新的ID，也即redis 在增加信息条目时会检查当前 id 与上一条目的 id， 自动纠正错误的情况，一定要保证后面的 id 比前面大，一个流中信息条目的ID必须是单调增的，这是流的基础 |
| 客户端显示传入规则：Redis对于ID有强制要求，格式必须是时间戳-自增Id这样的方式，且后续ID不能小于前一个ID |
| Stream的消息内容，也就是图中的Message Content它的结构类似Hash结构，以key-value的形式存在 |



XRANGE 获取消息列表(可以指定范围)，忽略删除的消息

```shell
XRANGE说明:
           用于获取消息列表(可以指定范围)，忽略删除的消息
           start表示开始值，-代表最小值
           end表示结束值，+代表最大值
           count表示最多获取多少个值

127.0.0.1:6379> help XRANGE
  XRANGE key start end [COUNT count]
  summary: Returns the messages from a stream within a range of IDs.
  since: 5.0.0
  group: stream


127.0.0.1:6379> XRANGE mystream - +
1699551345931-0
id
11
cname
zs
1699551362642-0
id
12
cname
ls
1699551381352-0
id
13
cname
ww
1699551413583-0
k1
v1
k2
v2
k3
v3


127.0.0.1:6379> XRANGE mystream - + count 1
1699551345931-0
id
11
cname
zs
```

XREVRANGE 逆向输出。与XRANGE的区别在于，获取消息列表元素的方向是相反的，end在前,start在后

```shell
127.0.0.1:6379> XREVRANGE mystream + 1
1699551413583-0
k1
v1
k2
v2
k3
v3
1699551381352-0
id
13
cname
ww
1699551362642-0
id
12
cname
ls
1699551345931-0
id
11
cname
zs
```

XDEL 删除

```shell
127.0.0.1:6379> XDEL mystream 1699551381352-0
1
127.0.0.1:6379> XRANGE mystream - +
1699551345931-0
id
11
cname
zs
1699551362642-0
id
12
cname
ls
1699551413583-0
k1
v1
k2
v2
k3
v3
```

XLEN  用于获取Stream 队列的消息的长度

```shell
127.0.0.1:6379> XLEN mystream
3
# 一个id对应一个消息
127.0.0.1:6379> XRANGE mystream - +
1699551345931-0
id
11
cname
zs
1699551362642-0
id
12
cname
ls
1699551413583-0
k1
v1
k2
v2
k3
v3
```

XTRIM  用于对Stream的长度进行截取，如超长会进行截取

```shell
说明：
        MAXLEN     允许的最大长度，对流进行修剪限制长度
        MINID      允许的最小id，从某个id值开始比该id值小的将会被抛弃

127.0.0.1:6379> help XTRIM
  XTRIM key MAXLEN|MINID [=|~] threshold [LIMIT count]
  summary: Deletes messages from the beginning of a stream.
  since: 5.0.0
  group: stream
  
127.0.0.1:6379> XRANGE mystream - +
1699551345931-0
id
11
cname
zs
1699551362642-0
id
12
cname
ls
1699551413583-0
k1
v1
k2
v2
k3
v3
# MAXLEN选项：允许的最大长度，对流进行修剪限制长度
# 限定mystream的最大长度为2
127.0.0.1:6379> XTRIM mystream maxlen 2
1
# 限制mystream的长度为2,stream中时间戳较小的元素会被删除，也就是保留最后添加的元素
127.0.0.1:6379> XRANGE mystream - +
1699551362642-0
id
12
cname
ls
1699551413583-0
k1
v1
k2
v2
k3
v3




# MINID选项：允许的最小id，从某个id值开始比该id值小的将会被抛弃
127.0.0.1:6379> XRANGE mystream - +
1699551362642-0
id
12
cname
ls
1699551413583-0
k1
v1
k2
v2
k3
v3
127.0.0.1:6379> XTRIM mystream MINID 1699551413583-0
1
127.0.0.1:6379> XRANGE mystream - +
1699551413583-0
k1
v1
k2
v2
k3
v3
```

XREAD

```shell
127.0.0.1:6379> help XREAD
XREAD [COUNT count] [BLOCK milliseconds] STREAMS key [key ...] id [id ...]
COUNT   最多读取多少条消息
BLOCK   是否以阻塞的方式读取消息，默认不阻塞，如果milliseconds设置为0，表示永远阻塞


# 用于获取消息(阻塞/非阻塞)，只会返回大于指定ID的消息

# 非阻塞
# $代表特殊ID，表示以当前Stream已经存储的最大的ID作为最后一个ID，当前Stream中不存在大于当前最大ID的消息，因此此时返回nil
# 0-0代表从最小的ID开始获取Stream中的消息，当不指定count，将会返回Stream中的所有消息，注意也可以使用0（00/000也都是可以的……）
 127.0.0.1:6379> XADD mystream1 * k3 v3
1699712767032-0
127.0.0.1:6379> XADD mystream1 * k4 v4 k5 v5
1699712793859-0
127.0.0.1:6379> XADD mystream1 * k6 v6
1699713878954-0
127.0.0.1:6379> XADD mystream1 * k7 v7
1699713885668-0
127.0.0.1:6379> XRANGE mystream1 - +
1699712767032-0
k3
v3
1699712793859-0
k4
v4
k5
v5
1699713878954-0
k6
v6
1699713885668-0
k7
v7

# 非阻塞
# $代表特殊ID，表示以当前Stream已经存储的最大的ID作为最后一个ID，当前Stream中不存在大于当前最大ID的消息，因此此时返回nil
127.0.0.1:6379> XREAD count 2 streams mystream1 $
nil

# 非阻塞
# 0-0代表从最小的ID开始获取Stream中的消息，当不指定count，将会返回Stream中的所有消息，注意也可以使用0（00/000也都是可以的……）
127.0.0.1:6379> XREAD count 2 streams mystream1 0-0
mystream1
1699712767032-0
k3
v3
1699712793859-0
k4
v4
k5
v5
127.0.0.1:6379> XREAD count 2 streams mystream1 00
mystream1
1699712767032-0
k3
v3
1699712793859-0
k4
v4
k5
v5



# 阻塞
# XREAD在阻塞模式下读取时会被阻塞，只要消息队列中新增了消息就会被监听到
# 首先在客户端1对mystream1进行阻塞读取
127.0.0.1:6379> XREAD count 1 block 0 streams mystream1 $
mystream1
1699715507656-0
k8
v8
# 然后在客户端2往mystream1中添加消息，客户端1的XREAD就会读取到新增的消息
127.0.0.1:6379> XADD mystream1 * k8 v8
1699715507656-0

小总结(类似java里面的阻塞队列)
```

XREAD 小总结(类似java里面的阻塞队列)

用redis-cli启动2个客户端连接上来，进行消息的生产和消费

![image-20231111230716576](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220333998.png)

Stream的基础方法，使用xadd存入消息和xread循环阻塞读取消息的方式可以实现简易版的消息队列，交互流程如下：

![image-20231111233405358](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220333477.png)



**基本命令代码实操：stream消费者相关指令**

XGROUP CREATE  用于创建消费者组

```shell
# XGROUP CREATE  用于创建消费者组
127.0.0.1:6379> help XGROUP CREATE
  XGROUP CREATE key group id|$ [MKSTREAM] [ENTRIESREAD entries-read]
  summary: Creates a consumer group.
  since: 5.0.0
  group: stream

# $表示从Stream尾部开始消费
# 0表示从Stream头部开始消费
# 创建消费者组的时候必须指定ID, ID为0表示从头开始消费，为$表示只消费新的消息，队尾新来
127.0.0.1:6379> XGROUP CREATE mystream1 groupX $
OK
127.0.0.1:6379> XGROUP CREATE mystream1 groupA 0
OK
127.0.0.1:6379> XGROUP CREATE mystream1 groupB 0-0
OK
```

XREADGROUP 创建一个具有多个消费者的消费者组

```shell
127.0.0.1:6379> help XREADGROUP
  XREADGROUP GROUP group consumer [COUNT count] [BLOCK milliseconds] [NOACK] STREAMS key [key ...] id [id ...]
  summary: Returns new or historical messages from a stream for a consumer in a group. Blocks until a message is available otherwise.
  since: 5.0.0
  group: stream

# “>”，表示从第一条尚未被消费的消息开始读取
# 消费组groupA内的消费者consumer1从mystream消息队列中读取所有消息
127.0.0.1:6379> XREADGROUP group groupA consumer1 streams mystream1 >
mystream1
1699712767032-0
k3
v3
1699712793859-0
k4
v4
k5
v5
1699713878954-0
k6
v6
1699713885668-0
k7
v7
1699715507656-0
k8
v8

# stream中的消息—旦被消费组里的一个消费者读取了，就不能再被该消费组内的其他消费者读取了
# 即同一个消费组里的消费者不能消费同—条消息。刚才的XREADGROUP命令再执行—次，此时读到的就是空值
127.0.0.1:6379> XREADGROUP group groupA consumer2 streams mystream1 >


# 但是，不同消费组的消费者可以消费同一条消息
127.0.0.1:6379> XREADGROUP group groupB consumer1 streams mystream1 >
mystream1
1699712767032-0
k3
v3
1699712793859-0
k4
v4
k5
v5
1699713878954-0
k6
v6
1699713885668-0
k7
v7
1699715507656-0
k8
v8
# 消费组的目的?
# 消费组的目的：让组内的多个消费者共同分担读取消息
# 通常会让每个消费者读取部分消息，从而实现消息读取的负载均衡，使得消息在多个消费者间是均衡分布的
127.0.0.1:6379> XGROUP CREATE mystream1 groupC 0-0
OK
127.0.0.1:6379> XREADGROUP GROUP groupC consumer1 count 1 streams mystream1 >
mystream1
1699712767032-0
k3
v3
127.0.0.1:6379> XREADGROUP GROUP groupC consumer2 count 1 streams mystream1 >
mystream1
1699712793859-0
k4
v4
k5
v5
127.0.0.1:6379> XREADGROUP GROUP groupC consumer3 count 1 streams mystream1 >
mystream1
1699713878954-0
k6
v6
127.0.0.1:6379> XREADGROUP GROUP groupC consumer4 count 1 streams mystream1 >
mystream1
1699713885668-0
k7
v7
```

重点问题：基于Stream实现的消息队列，如何保证消费者在发生故障或宕机再次重启后，仍然可以读取未处理完的消息?

- Streams 会自动使用内部队列（也称为PENDING List）留存消费组里每个消费者读取的消息保底措施，直到消费者使用XACK命令通知Streams“消息已经处理完成”

- 消费确认增加了消息的可靠性，一般在业务处理完成之后，需要执行XACK命令确认消息已经被消费完成



![image-20231112001334835](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220333458.png)

XPENDING  查询每个消费组内所有消费者 [已读取、但尚未确认] 的消息、查看某个消费者具体读取了哪些数据

```shell
127.0.0.1:6379> help XPENDING
  XPENDING key group [[IDLE min-idle-time] start end count [consumer]]
  summary: Returns the information and entries from a stream consumer group's pending entries list.
  since: 5.0.0
  group: stream
# 查询每个消费组内所有消费者 [已读取、但尚未确认] 的消息
127.0.0.1:6379> XPENDING mystream1 groupC
4
1699712767032-0
1699713885668-0
consumer1
1
consumer2
1
consumer3
1
consumer4
1
# 查看某个消费者具体读取了哪些数据
127.0.0.1:6379> XPENDING mystream1 groupC - + 10 consumer2
1699712793859-0
consumer2
12298934
1

# 查看某个消费者具体读取了哪些数据
127.0.0.1:6379> XPENDING mystream1 groupC - + 10 consumer4
1699713885668-0
consumer4
12392322
1
```

XPENDING 返回值解读

![image-20231112205114732](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220333352.png)



![image-20231112205555445](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220336502.png)

XACK  向消息队列确认消息处理已完成

```shell
127.0.0.1:6379> help XACK
  XACK key group id [id ...]
  summary: Returns the number of messages that were successfully acknowledged by the consumer group member of a stream.
  since: 5.0.0
  group: stream

127.0.0.1:6379> XPENDING mystream1 groupC - + 10 consumer1
1699712767032-0
consumer1
14153629
1
127.0.0.1:6379> XACK mystream1 groupC 1699712767032-0
1
127.0.0.1:6379> XPENDING mystream1 groupC - + 10 consumer1

127.0.0.1:6379>
```



![image-20231112212649930](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220336029.png)

XINFO   用于打印stream\Consumer\Group的详细信息

```shell
127.0.0.1:6379> help XINFO
  XINFO (null)
  summary: A container for stream introspection commands.
  since: 5.0.0
  group: stream

  XINFO CONSUMERS key group
  summary: Returns a list of the consumers in a consumer group.
  since: 5.0.0
  group: stream

  XINFO GROUPS key
  summary: Returns a list of the consumer groups of a stream.
  since: 5.0.0
  group: stream

  XINFO HELP (null)
  summary: Returns helpful text about the different subcommands.
  since: 5.0.0
  group: stream

  XINFO STREAM key [FULL [COUNT count]]
  summary: Returns information about a stream.
  since: 5.0.0
  group: stream
  

127.0.0.1:6379> XINFO stream mystream1
length
5
radix-tree-keys
1
radix-tree-nodes
2
last-generated-id
1699715507656-0
groups
4
first-entry
1699712767032-0
k3
v3
last-entry
1699715507656-0
k8
v8
```

###  2.10 redis位域(bitfield)

**简介**：

官网介绍：`https://redis.com.cn/commands/bitfield.html`

通过bitfield命令可以一次性操作多个比特位域(指的是连续的多个比特位)，它会执行一系列操作并返回一个响应数组，这个数组中的元素对应参数列表中的相应操作的执行结果

说白了就是通过bitfield命令我们可以一次性对多个比特位域进行操作

[Redis BITFIELD 命令 对字符串执行任意位域整数运算](https://redis.com.cn/commands/bitfield.html)

>BITFIELD命令可以将一个Redis 字符串看作是一个由二进制位组成的数组，并对这个数组中任意偏移进行访问。可以使用该命令对一个有符号的5位整型数的第1234位设置指定值，也可以对一个31位无符号整型数的第4567位进行取值。类似地，本命令可以对指定的整数进行自增和自减操作，可配置的上溢和下溢处理操作
>
>BITFIELD命令可以在一次调用中同时对多个位范围进行操作:它接受—系列待执行的操作作为参数，并返回一个数组，数组中的每个元素就是对应操作的执行结果
>
>例如，对位于5位有符号整数的偏移量100执行自增操作，并获取位于偏移量0上的4位长无符号整数:



```shell
BITFIELD mykey INCRBY i5 100 1 GET u4 0
```

用途：

- 作用：位域修改、溢出控制
- BITFIELD命令的作用在于它能够将很多小的整数储存到一个长度较大的位图中，又或者将一个非常庞大的键分割为多个较小的键来进行储存，从而非常高效地使用内存，使得 Redis 能够得到更多不同的应用——特别是在实时分析领域：BITFIELD能够以指定的方式对计算溢出进行控制的能力，使得它可以被应用于这一领域
- 将一个Redis字符串看作是一个由二进制位组成的数组(比如hello等价于0110100001100101011011000110110001101111、ascii码对照表：`https://ascii.org.cn/`)，并能对变长位宽和任意没有字节对齐的指定整型位域进行寻址和修改

BITFIELD总结：将一个Redis字符串看作是一个由二进制位组成的数组,并能对变长位宽和任意没有字节对齐的指定整型位域进行寻址和修改

**命令基本语法**：

```shell
# redis BITFIELD 命令基本语法如下：
BITFIELD key [GET type offset] [SET type offset value] [INCRBY type offset increment] [OVERFLOW WRAP|SAT|FAIL]
```

支持的子命令以及整数类型：

- **GET** `<type>` `<offset>` -- 返回指定的二进制位范围。
- **SET** `<type>` `<offset>` `<value>` -- 对指定的二进制位范围进行设置，并返回它的旧值。
- **INCRBY** `<type>` `<offset>` `<increment>` -- 对指定的二进制位范围执行加法操作，并返回它的旧值。可以通过向 increment 参数传入负值来实现相应的减法操作。

除了以上三个子命令之外，还有一个子命令，它可以改变之后执行的 [INCRBY](https://redis.com.cn/commands/incrby.html) 子命令在发生溢出情况时的行为:

- **OVERFLOW** `[WRAP|SAT|FAIL]`

当被设置的二进制位范围值为整数时，用户可以在类型参数的前面添加 `i` 来表示有符号整数， `u` 来表示无符号整数。 比如说，我们可以使用 `u8` 来表示 8 位长的无符号整数，也可以使用 `i16` 来表示 16 位长的有符号整数

命令最大支持 64 位长的有符号整数以及 63 位长的无符号整数，其中无符号整数的 63 位长度限制是由于 Redis 协议目前还无法返回 64 位长的无符号整数而导致的

**命令实操**：

`BITFIELD key [GET type offset]`   返回指定的位域

```shell
# 返回指定的位域
# BITFIELD命令可以将一个Redis字符串看作是一个由二进制位组成的数组，并对这个数组中任意偏移进行访问
BITFIELD key [GET type offset]  # 返回指定的位域
127.0.0.1:6379> set fieldkey hello
OK
127.0.0.1:6379> get fieldkey
hello
127.0.0.1:6379> BITFIELD fieldkey get i8 0
104
127.0.0.1:6379> BITFIELD fieldkey get i8 8
101
127.0.0.1:6379> BITFIELD fieldkey get i8 16
108
127.0.0.1:6379> BITFIELD fieldkey get i8 32
111
```

![image-20231112223529506](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220336988.png)

`BITFIELD key [SET type offset value]`   设置指定位域的值并返回它的原值

```shell
BITFIELD key [SET type offset value]   设置指定位域的值并返回它的原值

#从第9个位开始。将接下来8个位用有符号数120 (字母x)替换
127.0.0.1:6379> BITFIELD fieldkey set i8 8 120
101
127.0.0.1:6379> get fieldkey
hxllo
```

![image-20231112224157369](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220336239.png)



`BITFIELD key [INCRBY type offset increment]`   默认情况下，INCRBY使用WRAP参数

```shell
BITFIELD key [INCRBY type offset increment] # 默认情况下，INCRBY使用WRAP参数

127.0.0.1:6379> set fieldkey hello
OK
127.0.0.1:6379> BITFIELD fieldkey incrby u4 2 1
11
127.0.0.1:6379> BITFIELD fieldkey incrby u4 2 1
12
127.0.0.1:6379> BITFIELD fieldkey incrby u4 2 1
13
127.0.0.1:6379> BITFIELD fieldkey incrby u4 2 1
14
127.0.0.1:6379> BITFIELD fieldkey incrby u4 2 1
15
# 发生了溢出，值变成0
127.0.0.1:6379> BITFIELD fieldkey incrby u4 2 1
0
127.0.0.1:6379> BITFIELD fieldkey incrby u4 2 1
1
```



![image-20231112225233916](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220336456.png)

溢出控制 OVERFLOW [WRAP|SAT|FAIL]

```shell
# 溢出控制OVERFLOW [WRAP|SAT|FAIL]
# WRAP：使用回绕(wrap around)方法处理有符号整数和无符号整数的溢出情况
# SAT：使用饱和计算(saturation arithmetic)方法处理溢出，下溢计算的结果为最小的整数值，而上溢计算的结果为最大的整教值
# FAIL：命令将拒绝执行那些会导致上溢或者下溢情况出现的计算，并向用户返回空值表示计算未被执行
127.0.0.1:6379> set test a
OK

# 默认WRAP：使用回绕(wrap around)方法处理有符号整数和无符号整数的溢出情况
# a对应的ascii码值为97
127.0.0.1:6379> BITFIELD test get i8 0
97
# i8表示有符号8位二进制，范国(-128-127)
127.0.0.1:6379> BITFIELD test set i8 0 127
97
127.0.0.1:6379> BITFIELD test get i8 0
127
# 默认overflow为wrap,即循环溢出
127.0.0.1:6379> BITFIELD test set i8 0 138
127
# 默认overflow为wrap,即循环溢出
127.0.0.1:6379> BITFIELD test get i8 0
-118


# SAT：使用饱和计算(saturation arithmetic)方法处理溢出
# 下溢计算的结果为最小的整数值，而上溢计算的结果为最大的整教值
127.0.0.1:6379> BITFIELD test overflow sat set i8 0 128
-118
127.0.0.1:6379> BITFIELD test get i8 0
127

# FAIL：命令将拒绝执行那些会导致上溢或者下溢情况出现的计算，并向用户返回空值表示计算未被执行
127.0.0.1:6379> BITFIELD test overflow fail set i8 0 888

127.0.0.1:6379>
```



## 3.redis持久化

###  3.1 持久化简介

redis持久化官方文档：`https://redis.io/docs/management/persistence/`

redis持久：Redis数据写入磁盘的方式

持久化指的是将数据写入持久性存储介质，例如固态硬盘（SSD）。Redis 提供了多种持久化选项，包括以下几种：

1. RDB（Redis 数据库）持久化：RDB 持久化会在指定的时间间隔内对数据集执行定期快照
2. AOF（追加模式文件）持久化：AOF 持久化会记录服务器接收到的每个写操作。这些操作可以在服务器启动时重新播放，从而重建原始数据集。命令使用与 Redis 协议本身相同的格式记录
3. 无持久化：可以完全禁用持久化。有时在缓存时会使用这种选项
4. RDB + AOF：还可以在同一实例中结合使用 AOF 和 RDB

如果不想考虑这些不同持久化策略之间的权衡，可以考虑使用 Redis Enterprise 的持久化选项，可以通过用户界面预先配置

redis的持久化方式：RDB和AOF

![image-20231113001916048](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220337845.png)

###  3.2  RDB (Redis DataBase)

#####  3.2.1  RDB简介

RDB (Redis 数据库）：RDB持久性以指定的时间间隔执行数据集的时间点快照

- 实现类似照片记录效果的方式，就是把某一时刻的数据和状态以文件的形式写到磁盘上，也就是快照

- 这样一来即使故障宕机，快照文件也不会丢失，数据的可靠性也就得到了保证
- 这个快照文件就称为RDB文件(dump.rdb)，其中，RDB就是Redis DataBase的缩写

在指定的时间间隔内将内存中的数据集快照写入磁盘,也就是行话讲的Snapshot内存快照，它恢复时再将硬盘快照文件直接读回到内存里

Redis的数据都在内存中，保存备份时它执行的是全量快照，也就是说，把内存中的所有数据都记录到磁盘中，一锅端

Rdb保存的是dump.rdb文件

RDB保存到磁盘的文件叫dump.rdb

![image-20231115230321433](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220337850.png)

#####  3.2.2  RDB配置

配置文件(6 VS 7)：

Redis6.0.16以下：



![image-20231115230731222](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220337984.png)

Redis6.2以及Redis-7.0.0

![image-20231115230457922](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220337634.png)

#####  3.2.3  自动触发

Redis7版本，按照redis.conf里配置的`save <seconds> <changes>`

![image-20231115231520148](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220337664.png)

本次案例5秒2次修改：

![image-20231115231625607](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220337963.png)

修改dump文件保存路径：

默认路径：

![image-20231115231735498](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220337520.png)

自定义修改的路径且可以进入redis里用`CONFIG GET dir`获取目录

![image-20231115231838960](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220337297.png)

修改dump文件名称：

![image-20231115231930959](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220337574.png)



**触发备份的条件**：

在Redis中，通过配置`save`指令来定义触发备份（持久化）的条件。`save`指令的格式是：

复制代码

```
save <seconds> <changes>
```

- `<seconds>`：表示多少秒内有至少 `<changes>` 次写操作时，触发一次备份操作。
- `<changes>`：表示至少有多少次写操作时，触发一次备份操作。

具体来说，以下情况会触发备份：

1. **根据时间间隔触发备份：** 如果在配置的 `<seconds>` 秒内发生了至少 `<changes>` 次写操作，Redis会触发备份。例如，如果配置为 `save 900 1`，表示如果在900秒（15分钟）内至少有1次写操作，就会触发备份。
2. **根据写操作次数触发备份：** 如果在Redis数据库中发生了至少 `<changes>` 次写操作，不考虑时间间隔，也会触发备份。例如，如果配置为 `save 300 10`，表示如果有至少10次写操作发生，不论时间间隔如何，都会触发备份。

需要注意的是，当触发备份时，Redis会将数据库的快照保存到磁盘上，以实现持久化。备份的频率和条件取决于您在`redis.conf`文件中配置的`save`指令的值。可以根据自己的需求来调整这些值，以满足特定的数据保护和性能要求

触发备份的两种情况：

触发备份的第1种情况：5秒钟内修改了两次

![image-20231115232235210](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220337153.png)



触发备份的第2种情况：时间间隔超过5秒

![image-20231115232315656](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220337546.png)

**如何恢复**：

一、将备份文件(dump.rdb)移动到redis安装目录并启动服务即可

二、备份成功后故意用flushdb清空redis，看看是否可以恢复数据。结论：执行flushall/flushdb命令也会产生dump.rdb文件，但里面是空的，无法根据这个dump.rdb文件进行数据恢复

![image-20231115232626616](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220337581.png)



三、物理恢复，一定服务和备份分机隔离。备注：不可以把备份文件dump.rdb和生产redis服务器放在同一台机器，必须分开各自存储，以防生产机物理损坏后备份文件也挂了

![image-20231115232721336](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220337405.png)

#####   3.2.4 手动触发

**快照工作原理**：

Redis提供了两个命令来生成RDB文件,分别是save和bgsave


![image-20231118034212425](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220338389.png)

Snapshotting（快照）

默认情况下，Redis会将数据集保存到磁盘上的一个二进制文件中，文件名为dump.rdb。您可以配置Redis，使其在数据集中至少有M个更改的情况下，每隔N秒保存一次数据集，或者您可以手动调用SAVE或BGSAVE命令。

例如，以下配置将使Redis在数据集中至少有1000个键发生更改时，每60秒自动将数据集转储到磁盘上：

```
save 60 1000
```

这种策略称为快照

快照工作原理：每当Redis需要将数据集转储到磁盘时，会发生以下情况

1. Redis进行分叉（fork）。现在我们有一个子进程和一个父进程
2. 子进程开始将数据集写入临时的RDB文件中
3. 当子进程完成新的RDB文件的写入时，它会替换旧的RDB文件

这种方法允许Redis受益于写时复制（copy-on-write）语义

**SAVA命令**：

在还没有达到自动触发条件时，手动的执行`SAVA`命令也会生成备份文件`dump.rdb`

![image-20231118035601077](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220338552.png)

在主程序中执行Save会阻塞当前redis服务器，直到持久化工作完成执行save命令期间，Redis不能处理其他命令，线上禁止使用

**BGSAVE(默认)命令**：

执行BGSAVE命令后Redis会在后台异步进行快照操作，不阻塞快照同时还可以响应客户端请求,该触发方式会fork—个子进程由子进程复制持久化过程。Redis会使用bgsave对当前内存中的所有数据做快照，这个操作是子进程在后台完成的，这就允许主进程同时可以修改数据

![image-20231118042322382](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220338422.png)

fork是什么：在Linux程序中，fork()会产生一个和父进程完全相同的子进程，但子进程在此后多会exec系统调用，出于效率考虑，尽量避免膨胀

 

官网说明：

![image-20231118041505636](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220338013.png)

BGSAVE使用案例：

![image-20231118042401479](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220338776.png)

LASTSAVE：可以通过lastsave命令获取最后一次成功执行快照的时间

![image-20231118042645182](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220338036.png)



#####  3.2.5 RDB优缺点

**RDB优点**：

官网说明：

- RDB是Redis数据的一个非常紧凑的单文件时间点表示。RDB文件非常适合备份。例如，您可能希望在最近的24小时内每小时归档一次RDB文件，并在30天内每天保存一个RDB快照。这使您可以在发生灾难时轻松恢复不同版本的数据集
- RDB非常适合灾难恢复，它是一个可以传输到远程数据中心或Amazon S3(可能已加密）的压缩文件
- RDB最大限度地提高了Redis 的性能，因为Redis 父进程为了持久化而需要做的唯一工作就是派生一个将完成所有其余工作的子进程。父进程永远不会执行磁盘I/О或类似操作
- 与AOF相比，RDB允许使用大数据集更快地重启
- 在副本上，RDB支持重启和故障转移后的部分重新同步

优点总结：

- 适合大规模的数据恢复
- 按照业务定时备份
- 对数据完整性和一致性要求不高
- RDB文件在内存中的加载速度要比AOF快得多

**RDB缺点**：

官方说明：

- 如果您需要在Redis停止工作时（例如断电后）将数据丢失的可能性降到最低，那么RDB并不好。您可以配置生成RDB的不同保存点（例如，在对数据集至少5分钟和100次写入之后，您可以有多个保存点)。但是，您通常会每五分钟或更长时间创建一次RDB快照，因此，如果Redis由于任何原因在没有正确关闭的情况下停止工作，您应该准备好丢失最新分钟的数据
- RDB需要经常fork(以便使用子进程在磁盘上持久化。如果数据集很大，fork可能会很耗时，并且如果数据集很大并且CPU性能不是很好，可能会导致Redis停止为客户端服务几毫秒甚至一秒钟。AOF也需要fork()但频率较低，您可以调整要重写日志的频率，而不需要对持久性进行任何权衡

缺点总结：

- 在—定间隔时间做一次备份，所以如果redis意外down掉的话，就会丢失从当前至最近一次快照期间的数据，快照之间的数据会丢失内存数据的全量同步，如果数据量太大会导致I/O严重影响服务器性能
- RDB依赖于主进程的fork，在更大的数据集中，这可能会导致服务请求的瞬间延迟。fork的时候内存中的数据被克隆了1份，大致2倍的膨胀性，需要考虑



**数据丢失案例**：

正常录入数据：

![image-20231118045919168](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220338967.png)

kill -9故意模拟意外down机

![image-20231118053821286](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220338095.png)

redis重启恢复，查看数据是否丢失

![image-20231118054049134](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220338374.png)

#####  3.2.6 RDB总结补充

如何检查修复dump.rdb文件

![image-20231118054614352](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220338959.png)

哪些情况会触发RDB快照

- 配置文件中默认的快照配置
- 手动save/bgsave命令
- 执行flushall/flushdb命令也会产生dump.rdb文件，但里面是空的，无意义
- 执行shutdown且没有设置开启AOF持久化
- 主从复制时，主节点自动触发


如何禁用快照：

- 动态所有停止RDB保存规则的方法: `redis-cli config set save ""`
- 快照禁用

![image-20231118054858868](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220338239.png)

RDB优化配置项详解：配置文件SNAPSHOTTING模块

```
save <seconds> <changes>
dbfilename
dir
stop-writes-on-bgsave-error
rdbcompression
rdbchecksum
rdb-del-sync-files
```

`stop-writes-on-bgsave-error`：

默认yes如果配置成no，表示你不在乎数据不一致或者有其他的手段发现和控制这种不一致，那么在快照写入失败时，也能确保redis继续接受新的写请求

`stop-writes-on-bgsave-error` 是 Redis 配置参数之一，用于控制在后台保存（bgsave）过程中出现错误时的行为。这个参数的作用是指定当 Redis 在执行后台持久化操作（如快照或AOF文件写入）时遇到错误时的行为方式。具体来说，这个参数有以下几个选项：

- `yes`：如果在后台保存操作期间出现错误，Redis 将停止写入操作，这意味着 Redis 不会再接受新的写入命令，直到后台保存操作成功或被手动取消。这可以用来确保数据的完整性，但也可能导致暂时的写入阻塞。
- `no`：如果在后台保存操作期间出现错误，Redis 将继续接受新的写入命令。这意味着即使后台保存失败，Redis 仍然会继续处理写入请求。这种方式可能会导致数据的一致性问题，但不会阻塞写入操作。

默认情况下，`stop-writes-on-bgsave-error` 参数的值是 `yes`，这意味着如果后台保存操作失败，Redis 将停止写入操作以确保数据的一致性。如果您更关注持续的可用性而不是数据的一致性，您可以将该参数设置为 `no`，以便即使后台保存失败，Redis 仍然能够继续处理写入请求。

要在 Redis 中设置 `stop-writes-on-bgsave-error` 参数，您可以在 Redis 配置文件 (`redis.conf`) 中进行配置，或者在运行时使用 `CONFIG SET` 命令进行动态设置。例如，要将该参数设置为 `no`，可以执行以下命令：

```shell
CONFIG SET stop-writes-on-bgsave-error no
```

请注意，在设置该参数时需要谨慎考虑数据一致性和可用性的权衡，根据您的应用需求来选择合适的选项。



![image-20231118055609882](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220339253.png)

rdbcompression

`rdbcompression` 是 Redis 的配置参数之一，用于控制在进行 RDB（Redis Database）持久化快照时是否使用压缩来减小快照文件的大小。RDB 是 Redis 用于将内存中的数据保存到磁盘的一种持久化方式，它可以将当前数据库的数据以快照的形式保存到一个二进制文件中，以便在服务器重启时快速加载数据。

这个参数有以下两个选项：

- `yes`：表示在进行 RDB 持久化快照时使用压缩来减小快照文件的大小。启用压缩可以节省磁盘空间，但可能会增加 CPU 使用量
- `no`：表示在进行 RDB 持久化快照时不使用压缩，快照文件会以未压缩的方式保存。这会占用更多的磁盘空间，但相对减少了 CPU 使用

默认情况下，`rdbcompression` 参数的值是 `yes`，表示在 RDB 持久化快照时会使用压缩来减小快照文件的大小，以节省磁盘空间

要在 Redis 中设置 `rdbcompression` 参数，您可以在 Redis 配置文件 (`redis.conf`) 中进行配置，或者在运行时使用 `CONFIG SET` 命令进行动态设置。例如，要将该参数设置为 `no`，可以执行以下命令：

```shell
CONFIG SET rdbcompression no
```

根据您的需求和服务器的性能特点，您可以选择是否启用 RDB 压缩，权衡磁盘空间和 CPU 使用之间的权衡。如果磁盘空间有限，启用压缩可能是一个好的选择。否则，如果您更关心 CPU 使用情况，可以将该参数设置为 `no`，以减少 CPU 开销

![image-20231118055755143](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220339343.png)

| 默认yes                                                      |
| ------------------------------------------------------------ |
| 对于存储到磁盘中的快照，可以设置是否进行压缩存储。如果是的话，redis会采用LZF算法进行压缩。 如果你不想消耗CPU来进行压缩的话，可以设置为关闭此功能 |







rdbchecksum：

`rdbchecksum` 是 Redis 的配置参数之一，用于控制在进行 RDB（Redis Database）持久化快照时是否计算并存储校验和（checksum）信息。RDB 是 Redis 用于将内存中的数据保存到磁盘的一种持久化方式，它可以将当前数据库的数据以快照的形式保存到一个二进制文件中，以便在服务器重启时快速加载数据。

这个参数有以下两个选项：

- `yes`：表示在进行 RDB 持久化快照时会计算并存储校验和信息。校验和是一种用于验证数据完整性的机制，可以在加载 RDB 文件时检查文件是否损坏或篡改。启用校验和可以增加数据的可靠性，但可能会略微增加 CPU 使用。

- `no`：表示在进行 RDB 持久化快照时不计算或存储校验和信息。这会减少 CPU 使用，但在加载 RDB 文件时无法进行数据完整性检查。

默认情况下，`rdbchecksum` 参数的值是 `yes`，表示在 RDB 持久化快照时会计算并存储校验和信息，以增加数据的可靠性。

要在 Redis 中设置 `rdbchecksum` 参数，您可以在 Redis 配置文件 (`redis.conf`) 中进行配置，或者在运行时使用 `CONFIG SET` 命令进行动态设置。例如，要将该参数设置为 `no`，可以执行以下命令：

```shell
CONFIG SET rdbchecksum no
```

根据您的需求和服务器的性能特点，您可以选择是否启用 RDB 校验和，权衡数据的可靠性和 CPU 使用之间的权衡。如果您更关心数据的可靠性，启用校验和可能是一个好的选择。否则，如果您更关心 CPU 使用情况，可以将该参数设置为 `no`，以减少 CPU 开销。

![image-20231118055934341](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220339714.png)

| 默认yes                                                      |
| ------------------------------------------------------------ |
| 在存储快照后，还可以让redis使用CRC64算法来进行数据校验，但是这样做会增加大约10%的性能消耗，如果希望获取到最大的性能提升，可以关闭此功能 |

rdb-del-sync-files：

![image-20231118060004908](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220339998.png)

rdb-del-sync-files：在没有持久性的情况下删除复制中使用的RDB文件启用。默认情况下no，此选项是禁用的

`rdb-del-sync-files` 是 Redis 的配置参数之一，用于控制在进行 RDB（Redis Database）持久化快照时是否删除同步的（synchronized）RDB 文件。RDB 是 Redis 用于将内存中的数据保存到磁盘的一种持久化方式，它可以将当前数据库的数据以快照的形式保存到一个二进制文件中，以便在服务器重启时快速加载数据。

这个参数有以下两个选项：

- `yes`：表示在进行 RDB 持久化快照时会删除同步的 RDB 文件。同步的 RDB 文件是指已经完成保存并成功同步到磁盘的 RDB 文件。删除这些文件可以释放磁盘空间，但在一些情况下可能会导致数据的不可恢复性，因为您将无法回滚到旧的 RDB 文件。

- `no`：表示在进行 RDB 持久化快照时不删除同步的 RDB 文件。这意味着即使 RDB 文件已经成功保存并同步到磁盘，它们仍然会保留在磁盘上。这可以用来确保在需要时可以回滚到旧的 RDB 文件，但可能会占用更多的磁盘空间。

默认情况下，`rdb-del-sync-files` 参数的值是 `no`，表示在进行 RDB 持久化快照时不会删除同步的 RDB 文件，以确保数据的可恢复性。

要在 Redis 中设置 `rdb-del-sync-files` 参数，您可以在 Redis 配置文件 (`redis.conf`) 中进行配置，或者在运行时使用 `CONFIG SET` 命令进行动态设置。例如，要将该参数设置为 `yes`，可以执行以下命令：

```shell
CONFIG SET rdb-del-sync-files yes
```

根据您的需求和磁盘空间的可用性，您可以选择是否删除同步的 RDB 文件，权衡磁盘空间和数据恢复性之间的权衡。如果磁盘空间有限并且您不太关心回滚到旧的 RDB 文件，那么可以考虑启用删除同步的 RDB 文件以释放空间。否则，如果您更关心数据的可恢复性，可以将该参数设置为 `no`，以保留同步的 RDB 文件



RDB总结：

![image-20231118060201811](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220339077.png)

###  3.3 AOF (Append Only File)

#####  3.3.1  AOF 简介

**AOF官网说明**：

AOF会以日志的形式来记录每个写操作，将Redis执行过的所有写指令记录下来(读操作不记录)，只许追加文件但不可以改写文件，redis启动之初会读取该文件重新构建数据，换言之，redis重启的话就根据日志文件的内容将写指令从前到后执行一次以完成数据的恢复工作

默认情况下，redis是没有开启AOF(append only file)的。开启AOF功能需要设置配置：`appendonly yes`

快照（Snapshotting）不是非常耐久的。如果运行 Redis 的计算机停止工作，电源线断开，或者意外终止了 Redis 实例（例如使用 `kill -9` 命令），最新写入 Redis 的数据将会丢失。虽然对于某些应用程序来说这可能并不是一个大问题，但对于需要完全耐久性的用例来说，Redis 快照仅仅是不可行的选择

追加日志文件（Append-only file）是 Redis 的一种备用、完全耐久性的策略。它从 Redis 版本 1.1 开始可用

您可以在配置文件中启用 AOF：

```shell
appendonly yes
```

从现在开始，每当 Redis 接收到更改数据集的命令（例如 `SET`），它将把这些命令附加到 AOF 文件中。当您重新启动 Redis 时，它将重新播放 AOF 以重建状态

自从 Redis 7.0.0 版本以后，Redis 使用了多部分 AOF 机制。这意味着原始的单一 AOF 文件被分割成基本文件（最多一个）和增量文件（可能有多个）。基本文件代表了在重写 AOF 文件时存在的数据的初始（RDB 或 AOF 格式）快照。增量文件包含了自上次创建基本 AOF 文件以来的增量更改。所有这些文件都放在一个单独的目录中，并由一个清单文件进行跟踪。**AOF保存的是`appendonly.aof`文件**

#####  3.3.2 AOF持久化工作流程

![image-20231119072050844](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220339107.png)

| 1    | Client作为命令的来源，会有多个源头以及源源不断的请求命令     |
| ---- | ------------------------------------------------------------ |
| 2    | 在这些命令到达Redis Server 以后并不是直接写入AOF文件，会将其这些命令先放入AOF缓存中进行保存。这里的AOF缓冲区实际上是内存中的一片区域，存在的目的是当这些命令达到一定量以后再写入磁盘，避免频繁的磁盘IO操作 |
| 3    | AOF缓冲会根据AOF缓冲区**同步文件的三种写回策略**将命令写入磁盘上的AOF文件 |
| 4    | 随着写入AOF内容的增加，为了避免文件膨胀，会根据规则进行命令的合并(又称**AOF重写)**，从而起到AOF文件压缩的目的 |
| 5    | 当Redis Server 服务器重启的时候会从AOF文件载入数据           |

#####  3.3.3  AOF缓冲区三种写回策略

- Always：同步写回，每个写命令执行完立刻同步地将日志写回磁盘
- everysec：默认策略。每秒写回，每个写命令执行完，只是先把日志写到AOF文件的内存缓冲区，每隔1秒把缓冲区中的内容写入磁盘
- no：操作系统控制的写回，每个写命令执行完，只是先把日志写到AOF文件的内存缓冲区，由操作系统决定何时将缓冲区内容写回磁盘



![image-20231119075049092](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220339819.png)

三种写回策略小总结update：

| 配置项   | 写回时机           | 优点                     | 缺点                             |
| -------- | ------------------ | ------------------------ | -------------------------------- |
| Always   | 同步写回           | 可靠性高，数据基本不丢失 | 每个写命令都要落盘，性能影响较大 |
| Everysec | 每秒写回           | 性能适中                 | 宕机时丢失1秒内的数据            |
| No       | 操作系统控制的写回 | 性能好                   | 宕机时丢失数据较多               |

#####  3.3.4  AOF配置 | 启动 | 修复 | 恢复

**AOF配置文件说明(6 vS 7)**：

如何开启aof

![image-20231119082405421](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220339601.png)

使用默认写回策略，`everysec`

![image-20231119082850376](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220339922.png)



aof文件-保存路径

redis6：AOF保存文件的位置和RDB保存文件的位置—样，都是通过`redis.conf`配置文件的`dir`配置
![image-20231119083059066](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220339492.png)

redis7之后：appenddirname

![image-20231119083125817](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220339371.png)

dir + appenddirname

![image-20231119083238315](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220340932.png)

**aof文件-保存名称**：

redis6有且仅有一个aof文件

![image-20231119083422150](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220340275.png)

Redis7.0中存在3个aof文件（Multi Part AOF）：base基本文件、incr增量文件、manifest清单文件

![image-20231119083847381](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220340550.png)

Redis7.0 Multi Part AOF的设计：

顾名思义，MP-AOF就是将原来的单个AOF文件拆分成多个AOF文件。在MP-AOF中，我们将AOF分为三种类型，分别为:

- BASE：表示基础AOF,它—般由子进程通过重写产生，该文件最多只有一个
- INCR：表示增量AOF，它一般会在AOFRW开始执行时被创建，该文件可能存在多个
- HISTORY：表示历史AOF，它由BASE和|NCR AOF变化而来，每次AOFRW成功完成时，本次AOFRW之前对应的BASE和INCR AOF都将变为HISTORY,HISTORY类型的AOF会被Redis自动删除

为了管理这些AOF文件，我们引入了一个manifest(清单）文件来跟踪、管理这些AOF。同时，为了便于AOF备份和拷贝，我们将所有的AOF文件和manifest文件放入一个单独的文件目录中，目录名由appenddirname配置(Redis 7.0新增配置项)决定

![image-20231119083556092](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220340606.png)

Redis7.0config中对应的配置项：

![image-20231119084023267](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220340269.png)

redis进行写操作时会生成三个文件：`appendonly. aof.1.base.rdb`、`appendonly. aof. 1.incr. aof`、`appendonly. aof.manifest`

![image-20231119160900079](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220340657.png)

`appendonly.aof.1.base.rdb`、`appendonly.aof.1.incr.aof` 和 `appendonly.aof.manifest` 是与 Redis 的持久化机制相关的文件。以下是对这三个文件的解释：

1. `appendonly.aof.1.base.rdb`:
   - 这个文件是 Redis 的 RDB（快照）持久化方式的一部分
   - Redis 使用 RDB 来创建数据库的时间点快照，将内存中的数据以二进制格式保存到磁盘上。
   - `appendonly.aof.1.base.rdb` 包含了 Redis 数据库的初始状态，通常在服务器启动时或手动执行 SAVE 或 BGSAVE 命令时创建
   - 这个文件以二进制格式存储了数据库中的键值对、过期时间等信息。

2. `appendonly.aof.1.incr.aof`:
   - 这个文件是 Redis 的 AOF（Append-Only File）持久化方式的一部分
   - Redis 使用 AOF 来记录每个写入操作，将其追加到一个日志文件中，以便在服务器重新启动时重放这些操作以还原数据。
   - `appendonly.aof.1.incr.aof` 包含了自上次 RDB 快照以来的增量写入操作。意味着它包含了从 RDB 文件之后的所有写入操作
   - 这个文件通常用于恢复 Redis 在 RDB 快照之后的状态

3. `appendonly.aof.manifest`:
   - 这个文件是 Redis AOF 持久化的一部分，但不包含数据本身，而是用于管理 AOF 文件的元信息。
   - `appendonly.aof.manifest` 可以包含有关 AOF 文件的信息，如文件的版本、大小、生成时间等。
   - 这个文件通常用于帮助 Redis 确定如何处理 AOF 文件，以及在进行 AOF 重写等操作时提供有关文件的信息。

这些文件一起协同工作，确保了 Redis 数据的持久性和可恢复性。RDB 文件提供了一个全量的数据库快照，而 AOF 文件记录了自上次快照以来的增量操作，使得在服务器发生故障或重新启动时可以尽量还原数据。`appendonly.aof.manifest` 则帮助 Redis 管理这些文件和相关的元信息

##### 3.3.5  正常恢复 | 异常恢复

**正常恢复**：

> 启动：设置Yes。修改默认的appendonly no，改为yes。写操作会生成aof文件到指定的目录，生成的三个文件：`appendonly. aof.1.base.rdb`、`appendonly. aof. 1.incr. aof`、`appendonly. aof.manifest`

![image-20231119154415509](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220341532.png)

正常重启redis然后重新加载，发现可以正常恢复。下面先清空数据，再验证数据的恢复得益于AOF持久化

> 写入数据进redis，然后flushdb+shutdown服务器
>
> 新生成了dump和aof
>
> 备份新生成的aof.bak，然后删除dump/aof再看恢复。删除dump6379. rdb和aof文件，重启后发现恢复数据失败

![image-20231119154559694](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220341350.png)

> 重启redis然后重新加载试试?  结论：删除dump6379. rdb和aof文件，重启后发现恢复数据失败![image-20231119154634006](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220341418.png)

> 停止服务器，拿出AOF备份修改后再重新启动服务器。发现数据恢复成功

![image-20231119154703889](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220341927.png)

**异常恢复**：

> 故意乱写正常的AOF文件，模拟网络闪断文件写error。重启Redis之后就会进行AOF文件的载入，发现启动都不行

![image-20231119163131303](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220341860.png)

> 异常修复命令: redis-check-aof --fix进行修复。redis-check-aof --fix 进行修复

![image-20231119164102451](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220341514.png)

#####  3.3.6  AOF的优缺点

**AOF优势**：

- 更好的保护数据不丢失。性能高、可做紧急恢复
- 使用AOF Redis更加持久∶可以有不同的fsync策略：根本不fsync、每秒 fsync、每次查询时fsync。使用每秒fsync 的默认策略，写入性能仍然很棒。fsync是使用后台线程执行的，当没有fsync正在进行时，主线程将努力执行写入，因此只能丢失一秒钟的写入
- AOF日志是一个仅附加日志，因此不会出现寻道问题，也不会在断电时出现损坏问题。即使由于某种原因（磁盘已满或其他原因）日志以写一半的命令结尾，redis-check-aof 工具也能够轻松修复它
- 当AOF变得太大时，Redis能够在后台自动重写AOF。重写是完全安全的，因为当Redis继续附加到旧文件时，会使用创建当前数据集所需的最少操作集生成一个全新的文件，一旦第二个文件准备就绪，Redis 就会切换两者并开始附加到新的那一个
- AOF 以易于理解和解析的格式依次包含所有操作的日志。您甚至可以轻松导出 AOF文件。例如，即使您不小心使用该FLUSHALL命令刷新了所有内容，只要在此期间没有执行日志重写，您仍然可以通过停止服务器、删除最新命令并重新启动Redis来保存您的数据集

![image-20231119170141858](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220341226.png)

**AOF劣势**：

- 相同数据集的数据而言aof文件要远大于rdb文件，恢复速度慢于rdb
- aof运行效率要慢于rdb,每秒同步策略效率较好，不同步效率和rdb相同
- 根据确切的fsync策略，AOF可能比RDB慢。一般来说，将fsync设置为每秒性能仍然非常高，并且在禁用fsync的情况下，即使在高负载下它也应该与RDB一样快。即使在巨大的写入负载的情况下，RDB仍然能够提供关于最大延迟的更多保证

#####  3.3.7 AOF重写机制

**官网简介**：

- AOF 文件会随着写入操作的执行而不断增大。例如，如果对计数器进行了100次递增操作，最终您的数据集中将只包含一个键，其中包含最终的值，但在您的AOF文件中会有100个条目。其中99个条目不需要用于重建当前状态


- 重写操作是完全安全的。当Redis继续向旧文件追加数据时，会生成一个全新的文件，其中包含创建当前数据集所需的最小操作集，一旦这个第二个文件准备好，Redis会切换这两个文件并开始向新文件追加数据
- 因此，Redis支持一个有趣的特性：它能够在后台重新构建AOF文件，而不会中断对客户端的服务。每当您发出BGREWRITEAOF命令时，Redis都会写入重建当前内存数据集所需的最短命令序列。如果您使用Redis 2.2的AOF，您需要定期运行BGREWRITEAOF。自Redis 2.4以来，它能够自动触发日志重写（请参阅示例配置文件以获取更多信息）
- 自Redis 7.0.0以来，当计划进行AOF重写时，Redis父进程会打开一个新的增量AOF文件以继续写入。子进程执行重写逻辑并生成一个新的基本AOF文件。Redis将使用临时清单文件来跟踪新生成的基本文件和增量文件。当它们准备好时，Redis将执行原子替换操作，以使这个临时清单文件生效。为了避免在AOF重写的重复失败和重试情况下创建许多增量文件的问题，Redis引入了AOF重写限制机制，以确保失败的AOF重写以越来越慢的速度重试

 **AOF重写机制概括**：

- AOF持久化产生的问题：由于AOF持久化是Redis不断将写命令记录到 AOF 文件中，随着Redis不断的进行，AOF 的文件会越来越大，文件越大，占用服务器内存越大以及 AOF 恢复要求时间越长
- 解决上述问题，Redis新增了重写机制，当AOF文件的大小超过所设定的峰值时，Redis就会**自动**启动AOF文件的内容压缩，只保留可以恢复数据的最小指令集。也可以手动使用命令`bgrewriteaof`来进行重写
- AOF重写机制核心描述：AOF文件的内容压缩，只保留可以恢复数据的最小指令集

**AOF重写机制的触发**：

> 官网默认配置：
>
> 条件1：根据上次重写后的aof大小，判断当前aof大小是不是增长了1倍 
>
> 条件2：重写时满足的文件大小
>
> 注意 ，两个条件要同时满足且的关系才会触发

![image-20231119174215505](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220341822.png)



> 自动触发：满足配置文件中的选项后，Redis会记录上次重写时的AOF大小，默认配置是当AOF文件大小是上次rewrite后大小的一倍且文件大于64M时就会触发重写机制
>
> 
>
> 手动触发：客户端向服务器发送bgrewriteaof命令

**AOF重写机制验证**：

> 启动AOF文件的内容压缩，只保留可以恢复数据的最小指令集
>
> 举个例子：比如有个key 
>
> 一开始你 set k1 v1
>
> 然后改成 set k1 v2
>
> 最后改成 set k1 v3
>
> 如果不重写，那么这3条语句都在aof文件中，内容占空间不说启动的时候都要执行一遍，共计3条命令；
>
> 但是，我们实际效果只需要set k1 v3这一条，所以开启重写后，只需要保存set k1 v3就可以了
>
> 只需要保留最后一次修改值，相当于给aof文件瘦身减肥，性能更好。AOF重写不仅降低了文件的占用空间，同时更小的AOF也可以更快地被Redis加载
>
> 重写机制验证：如果启动AOF文件的内容压缩，文件只保留可以恢复数据的最小指令集的话，说明重写机制生效

验证重写机制第一步：修改配置

![image-20231119215949044](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220341429.png)

验证重写机制第二步：完成上述正确配置，重启redis服务器,执行set k1 v1查看aof文件是否正常

![image-20231119220805859](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220341948.png)

验证重写机制第三步：进行redis写入操作，触发重写机制

![image-20231119221526971](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220341783.png)

手动触发案例：客户端向服务器发送bgrewriteaof命令

![image-20231119221729621](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220342897.png)

**重写机制总结**：

AOF文件重写并不是对原文件进行重新整理，而是直接读取服务器现有的键值对，然后用一条命令去代替之前记录这个键值对的多条命令，生成一个新的文件后去替换原来的AOF文件

AOF文件重写触发机制：

- redis.conf配置文件中的auto-aof-rewrite-percentage：默认值为100
- auto-aof-rewrite-min-size: 64mb配置

也就是说默认Redis会记录上次重写时的AOF大小，默认配置是当AOF文件大小是上次rewrite后大小的一倍且文件大于64M时触发

**重写机制原理**：

1：在重写开始前，redis会创建一个“重写子进程”，这个子进程会读取现有的AOF文件，并将其包含的指令进行分析压缩并写入到一个临时文件中

2：与此同时，主进程会将新接收到的写指令一边累积到内存缓冲区中，一边继续写入到原有的AOF文件中，这样做是保证原有的AOF文件的可用性，避免在重写过程中出现意外

3：当“重写子进程”完成重写工作后，它会给父进程发一个信号，父进程收到信号后就会将内存中缓存的写指令追加到新AOF文件中

4：当追加结束后，redis就会用新AOF文件来代替旧AOF文件，之后再有新的写指令，就都会追加到新的AOF文件中

5：重写aof文件的操作，并没有读取旧的aof文件，而是将整个内存中的数据库内容用命令的方式重写了一个新的aof文件，这点和快照有点类似

#####   3.3.8 AOF优化配置项

配置文件`APPENDONLY MODE`模块

| 配置指令                                                  | 配置含义                   | 配置示例                                                     |
| --------------------------------------------------------- | -------------------------- | ------------------------------------------------------------ |
| appendonly                                                | 是否开启aof                | appendonly yes                                               |
| appendfilename                                            | 文件名称                   | appendfilename "appendonly.aof"                              |
| appendfsync                                               | 同步方式                   | everysec/always/no                                           |
| no-appendfsync-on-rewrite                                 | aof重写期间是否同步        | no-appendfsync-on-rewrite no                                 |
| auto-aof-rewrite-percentage<br/>auto-aof-rewrite-min-size | 重写触发配置、文件重写策略 | auto-aof-rewrite-percentage 100<br/>auto-aof-rewrite-min-size 64mb |

#####  3.3.9  AOF总结

![image-20231119225529931](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220342061.png)

1：AOF文件时一个只进行追加的日志文件

2：Redis 可以在AOF文件体积变得过大时，自动地在后台对AOF进行重写

3：AOF文件有序地保存了对数据库执行的所有写入操作，这些写入操作以Redis协议的格式保存，因此AOF文件的内容非常容易被人读懂，对文件进行分析也很轻松

4：对于相同的数据集来说，AOF文件的体积通常要大于RDB文件的体积

5：根据所使用的fsync策略，AOF的速度可能会慢于RDB

###  3.4 RDB-AOF混合持久化

#####  3.4.1  RDB-AOF混合持久化

问题：RDB和AOF两种持久化方式可否共存? 如果共存听谁的?

答：AOF和RDB持久性可以同时启用而无问题。如果在启动时启用了AOF，Redis将加载AOF文件，这是具有更好耐久性保证的文件

![image-20231119231224055](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220342398.png)

数据恢复顺序和加载流程：在同时开启rdb和aof持久化时，重启时只会加载aof文件，不会加载rdb 文件

![image-20231119231601219](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220342886.png)

#####   3.4.2  持久化方式选择

持久化方式怎么选? 用哪种持久化方式？

- RDB持久化方式能够在指定的时间间隔能对你的数据进行快照存储

- AOF持久化方式记录每次对服务器写的操作,当服务器重启的时候会重新执行这些命令来恢复原始的数据，AOF命令以redis协议追加保存每次写的操作到文件末尾

- 同时开启两种持久化方式（RDB+AOF混合方式）：

  - 在这种情况下,当redis重启的时候会优先载入AOF文件来恢复原始的数据，因为在通常情况下AOF文件保存的数据集要比RDB文件保存的数据集要完整

  - RDB的数据不实时，同时使用两者时服务器重启也只会找AOF文件。那要不要只使用AOF呢?
    作者建议不要，因为RDB更适合用于备份数据库(AOF在不断变化不好备份)，留着rdb作为一个万一的手段

推荐方式：RDB+AOF混合方式。结合了RDB和AOF的优点，既能快速加载又能避免丢失过多的数据

- 1.开启混合方式设置。设置aof-use-rdb-preamble的值为yes。yes表示开启，设置为no表示禁用

- 2.RDB+AOF的混合方式------->结论:RDB镜像做全量持久化，AOF做增量持久化
- 先使用RDB进行快照存储，然后使用AOF持久化记录所有的写操作，当重写策略满足或手动触发重写的时候，将最新的数据存储为新的ROB记录。这样的话，重启服务的时候会从RDB和AOF两部分恢复数据，既保证了数据完整性，又提高了恢复数据的性能。简单来说：混合持久化方式产生的文件一部分是RDB格式，一部分是AOF格式。AOF包括了RDB头部+AOF混写

![image-20231119232357858](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220342848.png)

#####  3.4.3  纯缓存模式：同时关闭RDB+AOF

**禁用rdb**：

- 配置：`save ""`
- 禁用rdb持久化模式下，仍然可以使用命令save、bgsave生成rdb文件

**禁用aof**：

- 配置：`appendonly no`
- 禁用aof持久化模式下，仍然可以使用命令bgrewriteaof生成aof文件



## 4.redis事务

###  4.1 redis事务简介

**redis事务官方文档**：`https://redis.io/docs/manual/transactions/`

**redis事务**：

- 含义：可以一次执行多个命令，本质是一组命令的集合。一个事务中的
  所有命令都会序列化，按顺序地串行化执行而不会被其它命令插入，不许加塞
- 作用：—个队列中，一次性、顺序性、排他性的执行一系列命令

**redis事务官网说明**：

```markdown
Redis事务
Redis事务允许在单个步骤中执行一组命令，它们以MULTI、EXEC、DISCARD和WATCH命令为中心。Redis事务提供两个重要的保证：

1. 所有事务中的命令都是串行化的，并按顺序执行。另一个客户端发送的请求永远不会在执行Redis事务的中间被响应。这确保了命令作为单个孤立操作执行。

2. EXEC命令触发执行事务中的所有命令，因此如果客户端在事务上下文中在调用EXEC命令之前断开与服务器的连接，则不会执行任何操作。相反，如果调用EXEC命令，将执行所有操作。在使用追加写文件时，Redis确保使用单个write(2)系统调用将事务写入磁盘。然而，如果Redis服务器崩溃或以某种硬方式被系统管理员杀死，可能只有部分操作被记录。Redis将在重新启动时检测到这种情况，并报错退出。使用redis-check-aof工具可以修复追加写文件，以删除部分事务，以便服务器可以重新启动。

从版本2.2开始，Redis提供了额外的保证，类似于检查并设置（CAS）操作的乐观锁定形式。这将在本页面后面进行详细说明
```



###  4.2  Redis事务 VS 数据库事务

Redis事务和数据库事务比较：

| 概念                | 比较说明                                                     |
| ------------------- | ------------------------------------------------------------ |
| 1单独的隔离操作     | Redis的事务仅仅是保证事务里的操作会被连续独占的执行，redis命令执行是单线程架构，在执行完事务内所有指令前是不可能再去同时执行其他客户端的请求的 |
| 2没有隔离级别的概念 | 因为事务提交前任何指令都不会被实际执行，也就不存在”事务内的查询要看到事务里的更新，在事务外查询不能看到”这种问题了 |
| 3不保证原子性       | Redis的事务不保证原子性，也就是不保证所有指令同时成功或同时失败，只有决定是否开始执行全部指令的能力，没有执行到一半进行回滚的能力 |
| 4排它性             | Redis会保证一个事务内的命令依次执行，而不会被其它命令插入    |

###  4.3 redis事务使用

#####  4.3.1 redis事务语法简介

```markdown
# redis事务用法
使用MULTI命令进入Redis事务。该命令始终返回OK。在这一点上，用户可以发出多个命令。而不是执行这些命令，Redis将它们排队。只有在调用EXEC命令后，所有命令才会执行

调用DISCARD将清空事务队列并退出事务

以下示例以原子方式增加了foo和bar键的值
```

```plaintext
> MULTI
OK
> INCR foo
QUEUED
> INCR bar
QUEUED
> EXEC
1) (integer) 1
2) (integer) 1
```

```markdown
正如从上面的会话中可以看出，EXEC返回一个回复数组，其中每个元素都是事务中单个命令的回复，以与发出命令的顺序相同的顺序排列

当Redis连接处于MULTI请求的上下文中时，所有命令都将回复字符串QUEUED（从Redis协议的角度来看，这是作为状态回复发送的）。排队的命令仅在调用EXEC时计划执行
```

#####  4.3.2 redis事务命令

下表列出了redis 事务的相关命令：

| 命令                | 描述                                                         |
| ------------------- | ------------------------------------------------------------ |
| DISCARD             | 取消事务，放弃执行事务块内的所有命令                         |
| EXEC                | 执行所有事务块内的命令                                       |
| MULTI               | 标记一个事务块的开始                                         |
| UNWATCH             | 取消WATCH命令对所有key的监视                                 |
| WATCH key [key ...] | 监视一个(或多个)key，如果在事务执行之前这个(或这些) key被其他命令所改动，那么事务将被打断 |

###  4.4 事务案例演示

#####  4.4.1 case1 正常执行

案例一：事务正常执行。开启事务，执行多条命令后提交事务

```plaintext
# 开启事务
127.0.0.1:6379> MULTI
OK
127.0.0.1:6379(TX)> set k1 v1
QUEUED
127.0.0.1:6379(TX)> set k2 v2
QUEUED
127.0.0.1:6379(TX)> set k3 v3
QUEUED
127.0.0.1:6379(TX)> INCR count
QUEUED
# 执行所有事务块内的命令
127.0.0.1:6379(TX)> EXEC
1) OK
2) OK
3) OK
4) (integer) 1
```

#####  4.4.2  Case2  放弃事务

案例二：放弃事务

```shell
# 开启事务
127.0.0.1:6379> MULTI
OK
127.0.0.1:6379(TX)> set k1 v11
QUEUED
127.0.0.1:6379(TX)> set k2 v22
QUEUED
127.0.0.1:6379(TX)> INCR count
QUEUED
# 放弃事务
127.0.0.1:6379(TX)> DISCARD
OK
127.0.0.1:6379> get count
"1"
127.0.0.1:6379> get k1
"v1"
127.0.0.1:6379> get k2
"v2"
```

#####  4.4.3  Case3 全体连坐

案例三：在事务中发生的错误的情况

官网说明：

![image-20231120015846545](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220347036.png)

在事务中，可能会遇到两种类型的命令错误：

1. 一个命令可能在排队时失败，因此在调用EXEC之前可能会发生错误。例如，命令可能在语法上有问题（参数数量错误、命令名称错误等），或者可能存在一些关键条件，比如内存不足的情况（如果服务器配置了使用maxmemory指令限制内存）
2. 一个命令可能在调用EXEC后失败，例如，因为我们针对具有错误值的键执行了操作（比如对字符串值执行列表操作）

从Redis 2.6.5开始，服务器将在累积命令期间检测到错误。然后，它将拒绝执行事务并在EXEC期间返回错误，丢弃事务

注意：对于Redis < 2.6.5：在Redis 2.6.5前，客户端需要通过检查排队命令的返回值来检测在执行之前发生的错误：如果命令返回QUEUED，则排队正确，否则Redis返回错误。如果在排队命令时出现错误，大多数客户端将中止并丢弃事务。否则，如果客户端选择继续执行事务，EXEC命令将执行所有成功排队的命令，而不管以前的错误如何

演示：

![image-20231120014525725](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220346537.png)

#####  4.4.4 Case4 冤头债主

案例四：冤头债主

> 官网说明：

![image-20231120022243113](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220346129.png)

> Redis不提供事务回滚的功能，开发者必须在事务执行出错后，自行恢复数据库状态。注意和传统数据库事务区别，不一定要么一起成功要么一起失败

![image-20231120022336883](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220348079.png)

> 案例演示：

![image-20231120022729171](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220348837.png)



#####  4.4.5 Case5 watch监控

案例五：watch监控

Redis使用Watch来提供乐观锁定，类似于`CAS(Check-and-Set)`

> 悲观锁：悲观锁(Pessimistic Lock), 顾名思义，就是很悲观，每次去拿数据的时候都认为别人会修改，所以每次在拿数据的时候都会上锁，这样别人想拿这个数据就会block直到它拿到锁
>
> 乐观锁：乐观锁(Optimistic Lock), 顾名思义，就是很乐观，每次去拿数据的时候都认为别人不会修改，所以不会上锁，但是在更新的时候会判断一下在此期间别人有没有去更新这个数据。乐观锁策略：提交版本必须大于记录当前版本才能执行更新
>
> CAS：Redis使用Watch来提供乐观锁定，类似于CAS(Check-and-Set)
>
> 官网对Watch命令的说明：`WATCH` 用于为 Redis 事务提供检查并设置（CAS）行为。
>
> 被 `WATCH` 监视的键会被监测以检测对它们的更改。如果在 `EXEC` 命令之前至少有一个被监视的键被修改，整个事务会中止，`EXEC` 返回一个空响应来通知事务失败。
>
> 例如，假设我们需要以原子方式将一个键的值增加 1（假设 Redis 没有 INCR），可以使用 `WATCH` 和事务来实现

![image-20231120023447176](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220348190.png)

watch监控演示：

1：初始化k1和balance两个key,先监控再开启multi,保证两key变动在同一个事务内。先演示没有被其他进程影响的情况

![image-20231120023757827](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220348860.png)



2：演示watch命令作用的键被其他进程篡改的情况：

![image-20231120023908455](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220348821.png)

- watch命令是一种乐观锁的实现，Redis在修改的时候会检测数据是否被更改，如果更改了，则执行失败
- 第一个窗口蓝色框第5步执行结果返回为空，也就是相当于是失败
- `WATCH` 用于为 Redis 事务提供检查并设置（CAS）行为。被 `WATCH` 监视的键会被监测以检测对它们的更改。如果在 `EXEC` 命令之前至少有一个被监视的键被修改，**整个事务会中止**，`EXEC` 返回一个空响应来通知事务失败。例如，假设我们需要以原子方式将一个键的值增加 1（假设 Redis 没有 INCR），可以使用 `WATCH` 和事务来实现

#####  4.4.6  unwatch 命令

`UNWATCH` 命令是 Redis 中的一个事务命令，用于取消对当前事务中所有 `WATCH` 命令所监视的键的监视。以下是关于 `UNWATCH` 命令的详细信息：

```markdown
## UNWATCH 命令

`UNWATCH` 命令用于取消对当前 Redis 事务中所有已经使用 `WATCH` 命令监视的键的监视。这个命令的作用是取消对事务的监视，使得即将执行的事务不受之前的监视限制

### 语法

```plaintext
UNWATCH
```

返回值：`UNWATCH` 命令不返回任何值

示例

1. 在一个事务中使用 `WATCH` 命令监视一个键：

   ```plaintext
   MULTI
   WATCH mykey
   // 其他事务操作
   ```

2. 使用 `UNWATCH` 命令取消对键的监视：

   ```plaintext
   UNWATCH
   ```

3. 然后可以开始一个新的事务，不受之前的监视限制：

   ```plaintext
   MULTI
   // 新的事务操作
   ```

注意事项

- 如果在一个事务中使用了 `WATCH` 命令，但在执行事务之前使用了 `UNWATCH` 命令，那么事务将不再受到对键的监视，即使该键在 `WATCH` 之后被修改也不会引发事务回滚
- `UNWATCH` 命令不会取消事务，只是取消对键的监视

`UNWATCH` 命令通常在需要在事务之间切换监视的键时使用，以确保新事务不受先前监视的键的限制

`UNWATCH` 命令演示：

![image-20231120031506545](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220348879.png)

#####  4.4.7 事务总结

- 开启：以MULTI开始一个事务
- 入队：将多个命令入队到事务中，接到这些命令并不会立即执行，而是放到等待执行的事务队列里面
- 执行：由EXEC命令触发事务
- —旦执行了exec之前加的监控锁都会被取消掉了。当客户端连接丢失的时候(比如退出链接)，所有东西都会被取消监视

##  5.Redis管道

### 5.1 Redis管道简介

> 官方文档：`https://redis.io/docs/manual/pipelining/`
>
> 面试题：如何优化频繁命令往返造成的性能瓶颈?
>
> 问题由来：
>
> - Redis是一种基于客户端-服务端模型以及请求/响应协议的TCP服务。一个请求会遵循以下步骤：
>
>   - 1： 客户端向服务端发送命令分四步(发送命令→命令排队→命令执行→返回结果)，并监听Socket返回，通常以阻塞模式等待服务端响应
>
>   - 2： 服务端处理命令，并将结果返回给客户端
>
> - 上述两步称为Round Trip Time(简称RTT,数据包往返于两端的时间)
>
> 如果同时需要执行大量的命令，那么就要等待上一条命令应答后再执行，这中间不仅仅多了RTT（Round Time Trip），而且还频繁调用系统IO，发送网络请求，同时需要redis调用多次read()和write()系统方法，系统方法会将数据从用户态转移到内核态，这样就会对进程上下文有比较大的影响了，性能不太好



![image-20231120034132117](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220348243.png)



> 解决思路：
>
> - 引出管道这个概念
> - 管道(pipeline)可以一次性发送多条命令给服务端，服务端依次处理完完毕后，通过一条响应一次性将结果返回，通过减少客户端与redis的通信次数来实现降低往返延时时间。pipeline实现的原理是队列，先进先出特性就保证数据的顺序性



![image-20231120035112242](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220348068.png)



> Redis管道说明：
>
> - Pipeline是为了解决RTT往返回时，仅仅是将命令打包一次性发送，对整个Redis的执行不造成其它任何影响
> - 批处理命令变种优化措施，类似Redis的原生批命令(mget和mset)



### 5.2 Redis管道案例演示



![image-20231120035408140](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220348197.png)

###  5.3 Pipeline与批量命令对比 

Pipeline与原生批量命令对比：

- 原生批量命令是原子性(例如:mset, mget),pipeline是非原子性
- 原生批量命令一次只能执行一种命令，pipeline支持批量执行不同命令
- 原生批命令是服务端实现，而pipeline需要服务端与客户端共同完成

###  5.4 Pipeline与事务对比

Pipeline与事务对比：

- 事务具有原子性，管道不具有原子性
- 管道一次性将多条命令发送到服务器，事务是一条一条的发，事务只有在接收到exec命令后才会执行，管道不会
- 执行事务时会阻塞其他命令的执行，而执行管道中的命令时不会

###  5.5 Pipeline注意事项

使用Pipeline注意事项：

- pipeline缓冲的指令只是会依次执行，不保证原子性，如果执行中指令发生异常，将会继续执行后续的指令
- 使用pipeline组装的命令个数不能太多，不然数据量过大客户端阻塞的时间可能过久，同时服务端此时也被迫回复一个队列答复，占用很多内存

##  6. Redis发布订阅

###  6.1 发布订阅简介

官方文档：`https://redis.io/docs/interact/pubsub/`

定义：是一种消息通信模式：发送者(PUBLISH)发送消息，订阅者(SUBSCRIBE)接收消息，可以实现进程间的消息传递

Redis可以实现消息中间件MQ的功能，通过发布订阅实现消息的引导和分流。不推荐使用此功能，专业的事情交给专业的中间件处理，redis就做好分布式缓存功能



Redis客户端可以订阅任意数量的频道，类似我们微信关注多个公众号

![image-20231120043818830](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220348782.png)

当有新消息通过PUBLISH命令发送给频道`channel1`时

![image-20231120043853998](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220349418.png)

发布/订阅其实是一个轻量的队列，只不过数据不会被持久化，一般用来处理实时性较高的异步消息

![image-20231120043954169](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220349816.png)

###  6.2 发布订阅命令

发布订阅常用命令

| 命令                                        | 描述                             |
| ------------------------------------------- | -------------------------------- |
| PSUBSCRIBE pattern [pattern ...]            | 订阅一个或多个符合给定模式的频道 |
| PUBSUB subcommand [argument [argument ...]] | 查看订阅与发布系统状态           |
| PUBLISH channel message                     | 将信息发送到指定的频道           |
| PUNSUBSCRIBE [pattern [pattern ...]]        | 退订所有给定模式的频道           |
| SUBSCRIBE channel [channel ...]             | 订阅给定的一个或多个频道的信息   |
| UNSUBSCRIBE [channel [channel ...]]         | 指退订给定的频道                 |

```shell
命令语法： SUBSCRIBE channel [channel ...]  
作用：    订阅给定的一个或多个频道的信息
推荐先执行订阅后再发布，订阅成功之前发布的消息是收不到的
订阅的客户端每次可以收到一个包含3个参数的消息：消息的种类、始发频道的名称、实际的消息内容

命令语法： PUBLISH channel message
作用： 发布消息到指定的频道

命令语法：PSUBSCRIBE pattern [pattern ...]
作用：按照模式批量订阅，订阅一个或多个符合给定模式(支持*号?号之类的)的频道

命令语法：PUBSUB subcommand [argument [argument ...]]
作用：查看订阅与发布系统状态
PUBSUB CHANNELS  查看由活跃频道组成的列表
PUBSUB NUMSUB [channel [channel ...]] 查看某个频道有几个订阅者
PUBSUB NUMPAT  只统计使用PSUBSCRIBE命令执行的，返回客户端订阅的唯一模式的数量

命令语法：UNSUBSCRIBE [channel [channel ...]]
作用：取消订阅

命令语法：PUNSUBSCRIBE [pattern [pattern ...]
作用：退订所有给定模式的频道
```

###  6.3 案例演示

开启3个客户端，演示客户端A、B订阅消息，客户端C发布消息

![image-20231121234233099](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220349510.png)

演示批量订阅和发布

![image-20231121234401638](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220349051.png)

取消订阅

![image-20231121234456461](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220349915.png)

### 6.4 总结

Redis可以实现消息中间件MQ的功能，通过发布订阅实现消息的引导和分流

仅代表我个人，不推荐使用该功能，专业的事情交给专业的中间件处理，redis就做好分布式缓存功能

Pub/Sub缺点：

- 发布的消息在Redis系统中不能持久化，因此，必须先执行订阅，再等待消息发布。如果先发布了消息，那么该消息由于没有订阅者，消息将被直接丢弃
- 消息只管发送对于发布者而言消息是即发即失的，不管接收，也没有ACK机制，无法保证消息的消费成功
- 以上的缺点导致Redis的Pub/Sub模式就像个小玩具，在生产环境中几乎无用武之地，为此Redis5.0版本新增了Stream数据结构，不但支持多播，还支持数据持久化，相比Pub/Sub更加的强大

##  7.Redis主从复制(replica)

###  7.1 简介

官网地址：`https://redis.io/docs/management/replication/`

Redis复制：就是主从复制，master以写为主，Slave以读为主。当master数据变化的时候，自动将新的数据异步同步到其它slave数据库

Redis 复制作用：

- 读写分离
- 容灾恢复
- 数据备份
- 水平扩容支撑高并发

Redis 如何通过复制来支持高可用性和故障切换：

- 在 Redis 复制的基础上（不包括由 Redis Cluster 或 Redis Sentinel 作为附加层提供的高可用性功能），有一种领导者跟随者（主-副本）复制方式，使用起来和配置都非常简单。它允许副本 Redis 实例成为主实例的精确副本。每当链接断开时，副本将自动重新连接到主服务器，并尝试成为主服务器的精确副本，无论主服务器发生了什么情况
- 这个系统使用三种主要机制：
  - 1.当主服务器和副本实例连接良好时，主服务器通过向副本发送一系列命令来保持副本的更新，以复制由于以下原因在主服务器端发生的数据集更改效果：客户端写入、键过期或被驱逐，或任何其他更改主数据集的操作
  - 2.当主服务器和副本之间的链接断开时，无论是因为网络问题还是因为主服务器或副本感知到超时，副本都会重新连接并尝试进行部分重新同步：这意味着它将尝试获取在断开连接期间错过的命令流的一部分
  - 3.当无法进行部分重新同步时，副本将请求进行完全重新同步。这将涉及到一个更复杂的过程，其中主服务器需要创建其所有数据的快照，将其发送到副本，然后在数据集更改时继续发送命令流

- Redis 默认使用异步复制，这是大多数 Redis 使用情况的自然复制模式，具有低延迟和高性能。但是，Redis 副本会定期异步确认它们接收到的数据量。因此，主服务器不需要每次都等待命令被副本处理，但如果需要的话，它知道哪个副本已经处理了哪个命令。这允许可选的同步复制
- 客户端可以使用 WAIT 命令来请求某些数据的同步复制。然而，WAIT 只能确保在其他 Redis 实例中存在指定数量的已确认副本，它不能将一组 Redis 实例转变为具有强一致性的 CP 系统：在故障切换期间，已确认的写入仍然可能丢失，这取决于 Redis 持久性的确切配置。但是，使用 WAIT 后，在故障事件后丢失写入的概率大大降低，仅限于某些难以触发的故障模式

###  7.2 redis复制配置 & 命令

redis复制相关配置

- 配从(库)不配主(库)：由于是从库同步主库的数据，从库需要访问主库，主库最好配置访问密码，从库需要配置`masterauth`进行身份验证

- 权限细节：master如果配置了`requirepass`参数，需要密码验证，那么`slave`就要配置`masterauth`来设置校验密码，否则的话`master`会拒绝`slave`的访问请求

![image-20231122143540820](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220349084.png)

基本操作命令

| 命令                      | 描述                                                         |
| ------------------------- | ------------------------------------------------------------ |
| info replication          | 查看复制节点的主从关系和配置信息                             |
| replicaof 主库IP 主库端口 | —般写入进redis.conf配置文件内                                |
| slaveof 主库IP 主库端口   | 每次与master断开之后，都需要重新连接，除非你配置进redis.conf文件<br/>在运行期间修改slave节点的信息，如果该数据库已经是某个主数据库的从数据库，那么会停止和原主数据库的同步关系转而和新的主数据库同步 |
| slaveof no one            | 使当前数据库停止与其他数据库的同步，转成主数据库，自立为王   |

###  7.3 案例演示

##### 7.3.1 架构说明 & 主从配置

一个Master两个Slave，3台虚机，每台都安装redis，三边网络相互ping通且注意防火墙配置

![image-20231122150811086](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220349408.png)

拷贝多个redis.conf文件，并修改名称：`redis6379.conf`、`redis6380.conf`、`redis6381.conf`

三个主要命令回顾：

- 主从复制：replicaof 主库IP 主库端口
- 改换门庭：slaveof 新主库IP 新主库端口
- 自立为王：slaveof no one

修改配置文件细节操作：

```
一、共同配置：
redis6379.conf、redis6380.conf、redis6381.conf：
    1.开启：daemonize yes
    2.注释掉bind 127.0.0.1：#bind 127.0.0.1
    3.关闭保护模式：protected-mode no
    4.指定端口：port 6379
    5.指定当前工作目录：dir /myredis 
    6.pid文件名字：pidfile /var /run/ redis_6379. pid
    7.log文件名字：logfile  "/myredis/6379.log"
    8.密码配置：requirepass 111111
    9.dump.rdb名字：dbfilename dump6379.rdb
    10.aof文件(本步骤可选，非必须)：appendfilename "appendonly. aof"


二、从库的额外配置：从机需要配置，主机不用配置
redis6380.conf、redis6381.conf：
    11.当前Redis服务器作为从服务器(replica)连接到主服务器(master)：replicaof 192.168.111.183 6379
    12.从机访问主机的通行密码masterauth：masterauth 111111
```

#####  7.3.2 一主二从案例

主从复制方案一：配置文件固定写死主从关系

> 配置文件执行，replicaof主库IP主库端口
>
> 配置从机6380、配置从机6381

![image-20231122172643095](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220349361.png)

> 先master后两台slave依次启动

![image-20231122172843151](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220349933.png)

> 主从关系查看：使用命令查看主从关系  
>
> info replication 命令查看

![image-20231122174104646](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220349941.png)

> 主从关系查看：
>
> 通过redis日志查看主从关系，主机日志和备机日志中都会包含主从关系的相应信息

![image-20231122173746886](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220400302.png)



一主二从实现方法二：命令操作手动指定主从关系

> 从机停机去掉配置文件中的配置项,3台目前都是master主机状态

![image-20231122194629204](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220349990.png)

> 使用命令指定主从关系：
>
> 在预设的从机上执行命令：slaveof 主库IP 主库端口

![image-20231122194922697](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220349851.png)

> 问题：用命令指定主从关系的话，2台从机重启后，关系还在吗?
>
> 答：重启后主从关系失效

![image-20231122195051957](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220349082.png)

总结：配置 VS 命令的区别

- 配置：持久稳定
- 命令：当次生效

#####  7.3.3  链式复制案例

链式复制（chain replication）：上—个slave可以是下一个slave的master,slave同样可以接收其他slaves的连接和同步请求，那么该slave作为了链条中下一个的master,可以有效减轻主master的压力。中途变更转向会清除之前的数据，重新建立拷贝最新的。命令：`slaveof 新主库IP 新主库端口`

注意：链式复制中的所有`slave`都不能有写操作，中间节点只是作为一个复制的中转站

![image-20231122201648532](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220350173.png)

**链式复制补充**：

在 Redis 中，一个从服务器可以充当另一个从服务器的主服务器，这种配置方式称为链式复制（chain replication）。这意味着如果一个从服务器（Slave A）成为另一个从服务器（Slave B）的主服务器，Slave B 会从 Slave A 复制数据，而不是直接从主服务器（Master）复制数据。这样的架构可以有效地减轻主服务器的写入压力并提高整体性能

**示例**：

假设有一个主服务器（Master）和两个从服务器（Slave A 和 Slave B）组成的复制链。当 Slave B 成为 Slave A 的从服务器时，Slave B 会从 Slave A 中复制数据

1. 首先，Slave B 连接到 Slave A 并进行全量复制，以获取所有数据
2. 如果在此期间 Slave A 成为不可用或需要升级，Slave B 可以直接切换到主服务器（Master）作为其新的主服务器，而不需要重新获取所有数据
3. 当 Slave B 成为 Master 的新从服务器时，它也能够接受其他从服务器连接，从而构建更长的链

**注意事项**：

- 在建立链式复制时，需要考虑网络延迟和性能问题，以确保在链条中的每个从服务器都可以及时地进行数据同步
- 当进行主从切换时，旧的数据可能会被清除，并需要重新建立与新主服务器的连接来拷贝最新的数据
- 链式复制中的所有`slave`都不能有写操作，中间节点只是作为一个复制的中转站

链式复制是 Redis 中的一种高级复制配置，能够提高系统的容错性和扩展性。然而，需要仔细设计和管理，以确保整个系统的稳定性和性能

#####  7.3.4 反客为主案例

`SLAVEOF no one`   使当前数据库停止与其他数据库的同步，转成主数据库

`SLAVEOF no one`命令用于将当前的 Redis 服务器从从节点模式转换为主节点模式。当执行该命令时，当前数据库将停止与其他数据库的同步，不再作为其他主数据库的从节点进行数据复制。这将使当前的数据库成为一个独立的主数据库，可以接收写操作，并不再受其他数据库的影响

值得注意的是，当执行`SLAVEOF no one`命令后，当前数据库将不再与其他数据库同步，并且之前从其他数据库同步的数据也将停止更新。这个命令在某些场景下可能会有用，比如当需要将一个从节点转换为独立的主节点时

另外，需要谨慎使用该命令，因为一旦执行后，原先的数据同步关系将被打破，丢失更新的可能性较大

#####  7.3.5 主从复制问题演示

> 1.从机可以执行写命令吗?
>
> 答： 从机不可以执行写命令

![image-20231122181634051](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220350567.png)

> 2.从机切入点问题? 
>
> 假如主机先启动并写入了一些数据，这个时候从机才启动，那从机启动后会同步所有数据，然后主机怎么写，从机就跟着怎么写



> 3.主机shutdown后，从机会上位吗? 主机shutdown后情况如何？从机是上位还是原地待命
>
> 答：从机不动，原地待命，从机数据可以正常使用；等待主机重启动归来



![image-20231122181936233](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220350138.png)

> 4.主机shutdown后，重启后主从关系还在吗? 从机还能否顺利复制?
>
> 答：主从关系还在、且从机能顺利复制

![image-20231122182105543](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220350855.png)



> 5.某台从机down后，master继续，从机重启后它能跟上大部队吗?
>
> 答：在 Redis 主从复制中，如果某台从机（slave）因故宕机，而主服务器（master）继续工作，一旦从机重新启动，它将尝试从主服务器那里进行全量同步或部分同步，以恢复丢失的数据。Redis 会确保从机能够尽快跟上主服务器的更新，并继续复制主服务器的数据。这种方式下，从机可以在恢复后跟上主服务器的运行



### 7.4 复制原理和工作流程

1.slave启动，同步初请

```
slave启动成功连接到master后会发送一个sync命令

slave首次全新连接master，一次完全同步(全量复制)将被自动执行

slave自身原有数据会被master数据覆盖清除
```

2.首次连接，全量复制

```
master节点收到sync命令后会开始在后台保存快照(即RDB持久化，主从复制时会触发RDB),同时master节点收集所有接收到的用于修改数据集命令缓存起来

master节点执行RDB持久化完后，master将rdb快照文件和所有缓存的命令发送到所有slave,以完成一次完全同步

而slave服务在接收到数据库文件数据后，将其存盘并加载到内存中，从而完成复制初始化
```

3.心跳持续，保持通信

```
repl-ping-replica-period 10

master发出PING包的周期，默认是10秒
```

![image-20231122205936573](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220350474.png)

4.进入平稳，增量复制

```
Master继续将新的所有收集到的修改命令自动依次传给slave,完成同步
```

5.从机下线，重连续传

```
master会检查backlog里面的offset,master和slave都会保存一个复制的offset还有一个masterId

offset是保存在backlog中的。Master只会把已经复制的offset后面的数据复制给Slave，类似断点续传
```

###  7.5 复制的缺点

复制延时，信号衰减：由于所有的写操作都是先在Master上操作，然后同步更新到Slave上，所以从Master同步到Slave机器有一定的延迟，当系统很繁忙的时候，延迟问题会更加严重，Slave机器数量的增加也会使这个问题更加严重

![image-20231122220655817](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220350505.png)

master挂了如何办?  默认情况下，不会在slave节点中自动重选一个master 。那每次都要人工干预?
无人值守安装变成刚需，所以出现了哨兵和集群模式

##  8.Redis哨兵(sentinel)

###  8.1 哨兵简介

官网：`https://redis.io/docs/management/sentinel/`

哨兵：吹哨人巡查监控后台master主机是否故障，如果故障了根据**投票数**自动将某一个个从库转换为新主库，继续对外服务

哨兵的作用：监控redis运行状态，包括master和slave。当master down机，能自动将slave切换成新master



![image-20231123000636677](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220350924.png)

哨兵作用：

- **主从监控**：监控主从redis库运行是否正常
- **消息通知**：哨兵可以将故障转移的结果发送给客户端。如果Master异常，则会进行主从切换
- **故障转移**：将其中一个Slave作为新Master
- **配置中心**：客户端通过连接哨兵来获得当前Redis服务的主节点地址

Redis Sentinel实现了Redis在不使用Redis Cluster时的高可用性

Redis Sentinel还提供了其他附带任务，例如监控、通知，并充当客户端的配置提供者

以下是Sentinel在宏观层面（即大局观）的全部功能列表：

- 监控。Sentinel不断检查主服务器和从服务器实例是否按预期工作
- 通知。Sentinel可以通过API通知系统管理员或其他计算机程序，表示监控的Redis实例之一存在问题
- 自动故障切换。如果主服务器未按预期工作，Sentinel可以启动故障切换过程，将从服务器提升为主服务器，重新配置其他附加的从服务器以使用新的主服务器，并通知使用Redis服务器的应用程序使用新的连接地址
- 配置提供者。Sentinel充当客户端服务发现的权威来源：客户端连接到Sentinels以请求负责给定服务的当前Redis主服务器的地址。如果发生故障切换，Sentinels将报告新的地址

Sentinel作为分布式系统 Redis Sentinel是一个分布式系统：

Sentinel本身设计为在多个Sentinel进程协作的配置中运行。多个Sentinel进程协作的优势包括：

- 多个Sentinel在达成共识时执行故障检测，以确定特定的主服务器不再可用。这降低了误报的概率
- 即使不是所有Sentinel进程都在工作，Sentinel仍然可以工作，从而使系统对故障具有鲁棒性。毕竟，如果故障切换系统本身是单点故障，那就没有什么意义了
- Sentinels、Redis实例（主服务器和从服务器）以及连接到Sentinel和Redis的客户端的总和，也构成具有特定属性的更大型的分布式系统

###  8.2 案例演示

#####  8.2.1 案例全局架构

Redis Sentinel架构，前提说明：

- 3个哨兵：自动监控和维护集群，不存放数据，只是吹哨人

- 1主2从：用于数据读取和存放

![image-20231123004859331](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220350255.png)



#####  8.2.2 哨兵配置参数

在`/myredis`目录下新建或者拷贝`sentinel.conf`文件，名字绝不能错

先看看`/opt`目录下默认的`sentinel.conf`文件的内容

![image-20231123020704925](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220350601.png)

`sentinel.conf`文件重点参数项说明：

```shell
bind                    服务监听地址，用于客户端连接，默认本机地址
daemonize               是否以后台daemon方式运行
protected-mode          安全保护模式
port                    端口
logfile                 日志文件路径
pidfile                 pid文件路径
dir                     工作目录

# 设置要监控的master服务器
# quorum表示最少有几个哨兵认可客观下线,同意故障迁移的法定票数
sentinel monitor <master-name> <ip> <redis-port> <quorum>

# master设置了密码，连接master服务的密码
sentinel auth-pass <master-name> <password>
        

# 指定多少毫秒之后，主节点没有应答哨兵，此时哨兵主观上认为主节点下线
sentinel down-after-milliseconds <master-name> <milliseconds>


# 表示允许并行同步的slave个数，当Master挂了后，哨兵会选出新的Master
# 此时，剩余的slave会向新的master发起同步数据
sentinel parallel-syncs <master-name> <nums>


# 故障转移的超时时间，进行故障转移时，如果超过设置的毫秒，表示故障转移失败
sentinel failover-timeout <master-name> <milliseconds>


# 配置当某一事件发生时所需要执行的脚本
sentinel notification-script <master-name> <script-path> 

# 客户端重新配置主节点参数脚本
sentinel client-reconfig-script <master-name> <script-path>
```

核心参数解读：

```shell
# 设置要监控的master服务器
# quorum表示最少有几个哨兵认可客观下线,同意故障迁移的法定票数
sentinel monitor <master-name> <ip> <redis-port> <quorum>
```

行尾最后的quorum代表什么意思呢？quorum：确认客观下线的最少的哨兵数量

![image-20231123021527384](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220350197.png)

网络是不可靠的，有时候一个sentinel会因为网络堵塞而误以为一个master redis已经死掉了，在sentinel集群环境下需要多个sentinel互相沟通来确认某个master是否真的死了，quorum这个参数是进行客观下线的一个依据，意思是至少有quorum个sentinel认为这个master有故障，才会对这个master进行下线以及故障转移。因为有的时候，某个sentinel节点可能因为自身网络原因，导致无法连接master，而此时master并没有出现故障，所以，这就需要多个sentinel都一致认为该master有问题，才可以进行下一步操作，这就保证了公平性和高可用

#####  8.2.3 本次案例的哨兵配置

本次案例哨兵sentinel文件通用配置（由于机器硬件关系，此处将3个哨兵都同时配置进192.168.111.169同一台机器）：

`sentinel26379.conf`配置：

```shell
bind 0.0.0.0
daemonize yes
protected-mode no
port 26379
logfile "/myredis/sentinel26379.log"
pidfile /var/run/redis-sentinel26379.pid
dir /myredis
sentinel monitor mymaster 192.168.111.169 6379 2
sentinel auth-pass mymaster 111111
```

`sentinel26380.conf`配置：

```shell
bind 0.0.0.0
daemonize yes
protected-mode no
port 26380
logfile "/myredis/sentinel26380.log"
pidfile /var/run/redis-sentinel26380.pid
dir "/myredis"
sentinel monitor mymaster 192.168.111.169 6379 2
sentinel auth-pass mymaster 111111
```

`sentinel26381.conf`配置：

```shell
bind 0.0.0.0
daemonize yes
protected-mode no
port 26381
logfile "/myredis/sentinel26381.log"
pidfile /var/run/redis-sentinel26381.pid
dir "/myredis"
sentinel monitor mymaster 192.168.111.169 6379 2
sentinel auth-pass mymaster 111111
```

master主机配置文件说明：

![image-20231124005358557](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220350271.png)

#####  8.2.4 redis集群架构 

先启动一主二从3个redis实例，测试正常的主从复制。redis服务器架构说明：

![image-20231124005609449](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220350277.png)

redis服务器配置：

- 169机器上新建redis6379.conf配置文件，由于要配合本次案例，请设置masterauth项访问密码为11111，不然后续可能报错`master_link_status:down`
- 172机器上新建redis6380.conf配置文件，设置好`replicaof <masterip> <masterport>`
- 173机器上新建redis6381.conf配置文件，设置好`replicaof <masterip> <masterport>`
- 6379后续可能会变成从机，需要设置访问新主机的密码,请设置`masterauth`项访问密码为`111111`，不然后续可能报错`master_link_status:down`



![image-20231124011818687](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220350598.png)

启动3台不同的虚拟机实例，启动三部真实机器实例并连接

```shell
redis-cli -a 111111 -p 6379
redis-cli -a 111111 -p 6380
redis-cli -a 111111 -p 6381
```

#####  8.2.5 案例一：启动哨兵监控

再启动3个哨兵，完成监控

```shell
redis-sentinel sentinel26379.conf --sentinel
redis-sentinel sentinel26380.conf --sentinel
redis-sentinel sentinel26381.conf --sentinel
```

查看哨兵日志：

![image-20231124024609600](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220350880.png)

哨兵配置重写：查看之前哨兵配置，发现redis会自动地在原来的基础上追加了一些配置

![image-20231124024645671](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220351430.png)

哨兵监控下的主从复制：启动3个哨兵监控后先测试一下redis的主从复制是否正常

![image-20231124013445714](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220351254.png)



#####  8.2.6 案例二：master出现故障

自己手动关闭6379服务器，模拟master挂了

![image-20231124013715784](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220351535.png)



问：master出现故障后两台从机数据是否正常？

答：出现故障之后的一段时间内两台从机的数据读取出现问题，但是一段时间后又自动恢复正常。结论：哨兵模式下master出现故障后不会导致数据读取问题。但在主节点切换期间可能会有一些短暂的写请求延迟

![image-20231124025658738](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220351631.png)





问：master出现故障后，是否会从剩下的2台机器上选出新的master？

答：master出现故障后，会在从机中选举一个新的master。如下演示所示，通过命令`info replication`可以查看新的主从关系。通过`vim sentinel26379.log`查看哨兵日志，哨兵日志记录了redis服务器出现故障、master选举、master变更的过程

![image-20231124031830745](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220351159.png)

![image-20231124032301101](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220351466.png)



问：之前down机的master机器重启回来，谁将会是新老大?会不会双master冲突?

答：故障恢复后，之前的master重新加入集群，一旦主节点的故障被解决，它可以重新加入Redis集群作为一个从节点，以便继续服务

![image-20231124044303093](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220351004.png)

![image-20231124044153833](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220351823.png)

细节补充：

配置文件的内容在运行期间会被sentinel动态进行更改：Master-Slave切换后，master_redis.conf、slave_redis.conf和sentinel.conf的内容都会发生改变，即master_redis.conf中会多一行slaveof的配置，sentinel.conf的监控目标会随之调换

##### 8.2.7 总结

在Redis哨兵模式下，当主节点（master）出现故障时，哨兵系统会采取以下步骤来处理故障：

1. 哨兵检测故障：哨兵节点会定期检查主节点的健康状态。如果哨兵节点连续若干次无法与主节点通信，它们会认为主节点可能已经故障
2. 选举新主节点：一旦多个哨兵节点都认为主节点故障，它们会开始进行选举，选择一个从节点（slave）升级为新的主节点。选举的过程包括哨兵节点之间的协商，以确定哪个从节点将成为新的主节点
3. 通知客户端：一旦新的主节点被选举出来，哨兵节点会通知所有的客户端和其他Redis从节点，以便它们知道主节点已经发生变化
4. 重定向客户端：哨兵节点会向客户端发送一个重定向命令，告诉它们新的主节点的地址和端口信息，以便客户端可以重新连接到新的主节点
5. 故障恢复后，之前的master重新加入集群：一旦主节点的故障被解决，它可以重新加入Redis集群作为一个从节点，以便继续服务

总的来说，Redis哨兵模式允许在主节点故障时自动切换到备用节点，以确保高可用性和故障恢复。这种自动切换可以帮助保持Redis集群的可用性，减少了手动干预的需要

###  8.3 哨兵运行流程和选举原理

##### 8.3.1 主观下线 | 客观下线

**哨兵故障切换**：

当一个主从配置中的master失效之后，sentinel可以选举出一个新的master。用于自动接替原master的工作，主从配置中的其他redis服务器自动指向新的master同步数据。一般建议sentinel采取奇数台，防止某一台sentinel无法连接到master导致误切换



**主观下线`SDown`(`Subjectively Down`)**：

SDOWN(主观不可用)是`单个sentinel自己主观上`检测到的关于master的状态，从sentinel的角度来看,如果发送了PING心跳后，在一定时间内没有收到合法的回复，就达到了SDOWN的条件

sentinel配置文件中的`down-after-milliseconds`设置了判断主观下线的时间长度

所谓主观下线（Subjectively Down， 简称 SDOWN）指的是单个Sentinel实例对服务器做出的下线判断，即单个sentinel认为某个服务下线（有可能是接收不到订阅，之间的网络不通等等原因）。主观下线就是说如果服务器在`[sentinel down-after-milliseconds]`给定的毫秒数之内没有回应PING命令或者返回一个错误消息， 那么这个Sentinel会主观的(单方面的)认为这个master不可以用了

![image-20231125110119633](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220351273.png)

`sentinel down-after-milliseconds <masterName> <timeout>` 配置解读：

```shell
配置语法: sentinel down-after-milliseconds <masterName> <timeout>
配置解读:     
        表示master被当前sentinel实例认定为失效的间隔时间，这个配置其实就是进行主观下线的一个依据
        master在多长时间内一直没有给Sentine返回有效信息，则认定该master主观下线
        也就是说如果多久没联系上redis-servevr，认为这个redis-server进入到失效（SDOWN）状态
```

**客观下线ODown(Objectively Down)**：

ODOWN需要一定数量的sentinel，多个哨兵达成一致意见才能认为一个master客观上已经宕掉

`sentinel monitor <master-name> <ip> <redis-port> <quorum>`   配置解读：

```shell
配置语法：sentinel monitor <master-name> <ip> <redis-port> <quorum>
配置参数解读：
1.master-name:
        master-name表示要监控的master服务器
        master-name是对某个master+slave组合的一个区分标识
        (一套sentinel可以监听多组master+slave这样的组合)
2.quorum:
        quorum表示最少有几个哨兵认可客观下线,同意故障迁移的法定票数
        quorum这个参数是进行客观下线的一个依据，quorum含义法定人数/法定票数
        意思是至少有quorum个sentinel认为这个master有故障才会对这个master进行下线以及故障转移
        因为有的时候，某个sentinel节点可能因为自身网络原因导致无法连接master，而此时master并没有出现故障
        所以这就需要多个sentinel都一致认为该master有问题，才可以进行下一步操作，这就保证了公平性和高可用
```



![image-20231125110449986](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220351963.png)

##### 8.3.2 哨兵leader选举 | Raft算法

**选举出领导者哨兵(哨兵中选出leader)**：

当master主节点被判断客观下线以后，各个哨兵节点会进行协商，先选举出一个领导者哨兵节点(兵王)并由该领导者节点(也即被选举出的兵王)进行failover (故障迁移)

三哨兵日志文件解读分析： master被判断为客观下线后，会从三个`sentinel`哨兵中选举一个`sentinel`作为`leader`,并最终由该`leader`进行选择一个合适的slave作为最新的master

`sentinel26379.log` :

![image-20231125110943308](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220351998.png)

`sentinel26380.log`：

![image-20231125111040207](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220351202.png)

`sentinel26381.log`：

![image-20231125111109044](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220351405.png)

哨兵领导者leader如何选出来的? 哨兵领导者leader选举是依据Raft算法进行的

Raft算法：

- 监视该主节点的所有哨兵都有可能被选为领导者，选举使用的算法是Raft算法
- Raft算法的基本思路**是先到先得**：即在一轮选举中，哨兵A向B发送成为领导者的申请，如果B没有同意过其他哨兵，则会同意A成为领导者

![image-20231125111222804](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220351767.png)





Raft算法是一种用于分布式系统中的一致性协议，它用于确保多个节点之间的数据一致性和可靠性。在Raft算法中，领导者（leader）的选举是一个重要的过程，用于确保系统的正常运行。以下是Raft算法的详细解释：

**Raft算法简介：**
Raft算法是由Diego Ongaro和John Ousterhout于2013年提出的一种一致性算法，它被设计用于分布式系统中，以确保数据的一致性和可靠性。Raft算法的核心思想是通过选举一个领导者节点来管理复制状态机（Replicated State Machine），并确保所有节点上的数据一致

**Raft算法的基本角色：**
Raft算法定义了三种基本角色：

1. **领导者（Leader）：** 领导者是系统中的一员，负责管理复制状态机的日常操作。领导者负责接收客户端请求，并将其复制到其他节点，以确保一致性。

2. **跟随者（Follower）：** 跟随者是处于等待状态的节点，它们等待来自领导者的指令，并将其执行。跟随者只能响应来自领导者的指令，不能自行决定。

3. **候选人（Candidate）：** 候选人是在选举过程中的节点，它们试图成为新的领导者。选举发生在系统中没有现存领导者时

**Raft算法的选举过程：**
Raft算法使用了一个随机的选举定时器，节点在等待领导者的心跳时，如果定时器超时，就会转变为候选人状态，开始选举过程。选举过程分为以下几个步骤：

1. **成为候选人：** 当节点的选举定时器超时后，它会将自己转变为候选人状态，并向其他节点发送选举请求

2. **请求投票：** 候选人向其他节点发送请求投票的消息，请求其他节点投票支持自己成为领导者

3. **投票响应：** 节点在收到投票请求后，会检查自己是否已经投票给了其他候选人，如果没有，则投票给请求的候选人，并将投票响应返回

4. **选举胜出：** 如果候选人获得了大多数节点的投票（超过半数），则它会成为新的领导者，并开始处理客户端请求。如果没有节点获得多数选票，那么选举失败，候选人将重新定时器超时，重新开始选举过程

5. **领导者维护：** 新选出的领导者会周期性地发送心跳消息给其他节点，以维持其领导地位。如果跟随者在一定时间内没有收到心跳消息，它们会认为领导者已经失效，开始新一轮的选举

**总结：**
Raft算法通过定时选举和心跳机制来确保系统中的领导者始终存在，从而保证了数据的一致性和可靠性。这个算法的关键点是选举过程，只有在没有现存领导者的情况下，节点才能成为新的领导者，以便协调系统中的操作

#####  8.3.3 哨兵leader推动故障切换

当master主节点被判断客观下线以后，会由哨兵leader开始推动故障切换流程并选出一个新master

**步骤一：某个Slave被选中成为新Master**

```
选出新master的规则(剩余slave节点健康前提下)：
- 哨兵leader会在下列三个条件中依次判断，并选举一个节点作为新的master
- 三个判断条件：
  - 1.redis.conf文件中优先级slave-priority或者replica-priority最高的从节点(数字越小优先级越高)
  - 2.复制偏移位置offset最大的从节点
  - 3.最小Run ID的从节点字典顺序，ASCII码
- 哨兵leader选举过程如下：
```



![image-20231125140207949](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220352330.png)



**步骤二：所有slave更换master**

```
Sentinel leader会对选举出的新master执行slaveof no one操作，将其提升为master节点
Sentinel leader向其它slave发送命令，让剩余的slave成为新的master节点的slave
```

**步骤三：旧master成为新master的slave**

```
将之前已下线的老master设置为新选出的新master的从节点，当老master重新上线后，它会成为新master的从节点
Sentinel leader会让原来的master降级为slave并恢复正常工作 
```

#####  8.3.4 故障恢复总结

上述的failover操作均由sentinel自己独自完成，完全无需人工干预

![image-20231125130412173](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220352222.png)

###  8.4 哨兵使用建议

哨兵节点的数量应为多个，哨兵本身应该集群，保证高可用哨兵节点的数量应该是奇数

各个哨兵节点的配置应一致，硬件的性能、内存的大小最好也一致

如果哨兵节点部署在Docker等容器里面，尤其要注意端口的正确映射

哨兵集群＋主从复制，并不能保证数据零丢失

##  9.Redis集群(cluster)

###  9.1 redis集群简介

官方文档：`https://redis.io/docs/reference/cluster-spec/`

Redis集群是一个提供在多个Redis节点间共享数据的程序集，Redis集群可以支持多个Master。**由于数据量过大**，单个Master复制集难以承担，因此需要对多个复制集进行集群，形成水平扩展，每个复制集只负责存储整个数据集的一部分，这就是Redis的集群，其作用是提供在多个Redis节点间共享数据的程序集

![image-20231125215721685](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220352919.png)



Redis集群支持多个Master，每个Master又可以挂载多个Slave。redis集群支持读写分离、支持数据的高可用、支持海量数据的读写存储操作

由于Cluster自带Sentinel的故障转移机制，内置了高可用的支持，无需再去使用哨兵功能

客户端与Redis的节点连接，不再需要连接集群中所有的节点，只需要任意连接集群中的一个可用节点即可

槽位slot负责分配到各个物理服务节点，由对应的集群来负责维护节点、插槽和数据之间的关系

###  9.2 redis集群槽位概述

Redis Cluster主要组件的概述(官网)：

> Redis集群的键空间被分成16384个槽位（slots），有效地设置了集群的大小上限为16384个主节点（但是，建议的节点最大数量约为1000个节点）
>
> 集群中的每个主节点处理16384个哈希槽的一个子集。当没有集群重新配置正在进行时(即哈希槽从一个节点移动到另一个节点)，集群是稳定的。当集群稳定时，单个哈希槽将由单个节点提供服务(但是，服务节点可以有一个或多个副本，在网络分裂或故障的情况下替换它，并且可以用于扩展读取陈旧数据是可接受的操作)

redis集群的槽位slot：

> Redis集群没有使用一致性hash,而是引入了哈希槽的概念
>
> Redis集群有16384个哈希槽,每个key通过CRC16校验后对16384取模来决定放置哪个槽。集群的每个节点负责一部分hash槽。举个例子，比如当前集群有3个节点，那具体的槽位如下：

![image-20231125222548387](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220352532.png)



Redis集群的数据分片：

| 分片是什么            | 使用Redis集群时我们会将存储的数据分散到多台redis机器上，这称为分片。简言之，集群中的每个Redis实例都被认为是整个数据的一个分片 |
| --------------------- | ------------------------------------------------------------ |
| 如何找到给定key的分片 | 为了找到给定key的分片，我们对key进行CRC16(key)算法处理并通过对总分片数量取模。然后，使用确定性哈希函数，这意味着给定的key将多次始终映射到同一个分片，我们可以推断将来读取特定key的位置 |

![image-20231125222857154](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220352036.png)



redis集群分片的最大优势：方便扩缩容和数据分派查找。这种结构很容易添加或者删除节点。比如如果想新添加个节点D，需要从节点A、B、C中移部分槽到D上。如果想移除节点A，需要将A中的槽移到B和C节点上然后将没有任何槽的A节点从集群中移除即可。由于从一个节点将哈希槽移动到另一个节点并不会停止服务,所以无论添加删除或者改变某个节点的哈希槽的数量都不会造成集群不可用的状态

###  9.3 槽位映射的思路演化

slot槽位映射，一般业界有3种解决方案：

- 哈希取余分区
- —致性哈希算法分区
- 哈希槽分区



#####  9.3.1  哈希取余分区

2亿条记录就是2亿个k,v，我们单机不行必须要分布式多机，假设有3台机器构成一个集群，用户每次读写操作都是根据公式：hash(key) % N个机器台数，计算出哈希值，用来决定数据映射到哪一个节点上

![image-20231126003330110](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220352183.png)

| 优缺点                                                       |
| ------------------------------------------------------------ |
| 优点： 简单粗暴，直接有效，只需要预估好数据规划好节点，例如3台、8台、10台，就能保证一段时间的数据支撑。使用Hash算法让固定的一部分请求落到同一台服务器上，这样每台服务器固定处理一部分请求（并维护这些请求的信息），起到负载均衡+分而治之的作用 |
| 缺点：  原来规划好的节点，进行扩容或者缩容就比较麻烦了，不管扩缩，每次数据变动导致节点有变动，映射关系需要重新进行计算，在服务器个数固定不变时没有问题，如果需要弹性扩容或故障停机的情况下，原来的取模公式就会发生变化：Hash(key)/3会变成Hash(key) /?。此时地址经过取余运算的结果将发生很大变化，根据公式获取的服务器也会变得不可控。某个redis机器宕机了，由于台数数量变化，会导致hash取余全部数据重新洗牌 |

#####  9.3.2 —致性哈希算法分区

**一致性Hash算法背景**：一致性哈希算法在1997年由麻省理工学院中提出的，设计目标是为了解决分布式缓存数据变动和映射问题，某个机器宕机了，分母数量改变了，自然取余数会存在问题。提出—致性Hash解决方案的目的是当服务器个数发生变动时，尽量减少影响客户端到服务器的映射关系

**一致性哈希3大步骤**：算法构建一致性哈希环、redis服务器IP节点映射、key落到服务器的落键规则



**一、算法构建一致性哈希环**

一致性哈希环：一致性哈希算法必然有个hash函数并按照算法产生hash值，这个算法的所有可能哈希值会构成一个全量集，这个集合可以成为一个hash空间[0,2^32-1]，这个是一个线性空间，但是在算法中，我们通过适当的逻辑控制将它首尾相连(0 = 2^32),这样让它逻辑上形成了一个环形空间

它也是按照使用取模的方法，前面笔记介绍的节点取模法是对节点（服务器）的数量进行取模。而一致性Hash算法是对2^32取模，简单来说，一致性Hash算法将整个哈希值空间组织成一个虚拟的圆环，如假设某哈希函数H的值空间为0-2^32-1（即哈希值是一个32位无符号整形），整个哈希环如下图：整个空间按顺时针方向组织，圆环的正上方的点代表0，0点右侧的第一个点代表1，以此类推，2、3、4、……直到2^32-1，也就是说0点左侧的第一个点代表2^32-1， 0和2^32-1在零点中方向重合，我们把这个由2^32个点组成的圆环称为Hash环

![image-20231126003722931](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220352922.png)



**二、redis服务器IP节点映射**

节点映射：将集群中各个IP节点映射到环上的某一个位置

将各个服务器使用Hash进行一个哈希，具体可以选择服务器的IP或主机名作为关键字进行哈希，这样每台机器就能确定其在哈希环上的位置。假如4个节点NodeA、B、C、D，经过IP地址的哈希函数计算(hash(ip))，使用IP地址哈希后在环空间的位置如下： 

![image-20231126003841760](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220352917.png)



**三、key落到服务器的落键规则**

当我们需要存储一个kv键值对时，首先计算key的hash值，hash(key)，将这个key使用相同的函数Hash计算出哈希值并确定此数据在环上的位置，**从此位置沿环顺时针“行走”**，第一台遇到的服务器就是其应该定位到的服务器，并将该键值对存储在该节点上

如我们有Object A、Object B、Object C、Object D四个数据对象，经过哈希计算后，在环空间上的位置如下：根据一致性Hash算法，数据A会被定为到Node A上，B被定为到Node B上，C被定为到Node C上，D被定为到Node D上

![image-20231126003942499](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220352050.png)



**—致性哈希算法优点**

—致性哈希算法的容错性：假设Node C宕机，可以看到此时对象A、B、D不会受到影响。一般的，在一致性Hash算法中，如果一台服务器不可用，则受影响的数据仅仅是此服务器到其环空间中前一台服务器（即沿着逆时针方向行走遇到的第一台服务器）之间数据，其它不会受到影响。简单说，就是C挂了，受到影响的只是B、C之间的数据且这些数据会转移到D进行存储

![image-20231126004143236](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220352544.png)

—致性哈希算法的扩展性：数据量增加了，需要增加一台节点NodeX，X的位置在A和B之间，那收到影响的也就是A到X之间的数据，重新把A到X的数据录入到X上即可，不会导致hash取余全部数据重新洗牌

![image-20231126005626665](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220352291.png)



**一致性哈希算法的缺点**

Hash环的数据倾斜问题：一致性Hash算法在服务**节点太少时**，容易因为节点分布不均匀而造成**数据倾斜**（被缓存的对象大部分集中缓存在某一台服务器上）问题，例如系统中只有两台服务器且两个服务器节点分布不均匀：

![image-20231126005808279](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220352885.png)

**一致性哈希总结**

```
作用：在节点数目发生改变时尽可能少的迁移数据
思路：
        将所有的存储节点排列在收尾相接的Hash环上，每个key在计算Hash后会顺时针找到临近的存储节点存放
        而当有节点加入或退出时仅影响该节点在Hash环上顺时针相邻的后续节点
优点：加入和删除节点只影响哈希环中顺时针方向的相邻的节点，对其他节点无影响
缺点：数据的分布和节点的位置有关，因为这些节点不是均匀的分布在哈希环上的，所以数据在进行存储时达不到均匀分布的效果
```

#####  9.3.3  哈希槽分区

**哈希槽分区**：哈希槽分区可以解决一致性哈希算法的数据倾斜问题。哈希槽实质就是一个数组，数组[0,2^14-1]形成hash slot空间

**哈希槽分区作用**：解决均匀分配的问题，在数据和节点之间又加入了一层，把这层称为哈希槽（slot），用于管理数据和节点之间的关系，现在就相当于节点上放的是槽，槽里放的是数据。槽解决的是粒度问题，相当于把粒度变大了，这样便于数据移动。哈希解决的是映射问题，使用key的哈希值来计算所在的槽，便于数据分配

![image-20231126092007311](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220352630.png)



**哈希槽分区中hash槽的数量**：一个集群只能有16384个槽，编号0-16383（0-2^14-1）。这些槽会分配给集群中的所有主节点，分配策略没有要求。集群会记录节点和槽的对应关系，解决了节点和槽的关系后，接下来就需要对key求哈希值，然后对16384取模，余数是几key就落入对应的槽里。HASH_SLOT = CRC16(key) mod 16384。以槽为单位移动数据，因为槽的数目是固定的，处理起来比较容易，这样数据移动问题就解决了



**哈希槽计算**：Redis 集群中内置了 16384 个哈希槽，redis 会根据节点数量大致均等的将哈希槽映射到不同的节点。当需要在 Redis 集群中放置一个 key-value时，redis先对key使用crc16算法算出一个结果然后用结果对16384求余数`[ CRC16(key) % 16384]`，这样每个 key 都会对应一个编号在 0-16383 之间的哈希槽，也就是映射到某个节点上。如下代码，key之A 、B在Node2， key之C落在Node3上

![image-20231126092407832](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220352710.png)



**经典面试题**：

**问：为什么redis集群的最大槽数是16384个?** Redis集群并没有使用一致性hash而是引入了哈希槽的概念。Redis 集群有16384个哈希槽，每个key通过CRC16校验后对16384取模来决定放置哪个槽，集群的每个节点负责一部分hash槽。但为什么哈希槽的数量是16384（2^14）个呢？CRC16算法产生的hash值有16bit，该算法可以产生2^16=65536个值。换句话说值是分布在0~65535之间，有更大的65536不用为什么只用16384就够？作者在做mod运算的时候，为什么不mod65536，而选择mod16384？ HASH_SLOT = CRC16(key) mod 65536为什么没启用

答：redis作者的回答见`https://github.com/redis/redis/issues/2576`



![image-20231126093759890](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220352716.png)

**最大槽数是16384原因**：正常的心跳数据包带有节点的完整配置，可以用幂等方式用旧的节点替换旧节点，以便更新旧的配置。这意味着它们包含原始节点的插槽配置，该节点使用2k的空间和16k的插槽，但是会使用8k的空间（使用65k的插槽）。同时，由于其他设计折衷，Redis集群不太可能扩展到1000个以上的主节点。因此16k处于正确的范围内，以确保每个主机具有足够的插槽，最多可容纳1000个矩阵，但数量足够少，可以轻松地将插槽配置作为原始位图传播。请注意，在小型群集中，位图将难以压缩，因为当N较小时，位图将设置的slot / N位占设置位的很大百分比

![image-20231126093830569](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220353855.png)

下面用计算来进一步说明：

```
(1)如果槽位为65536，发送心跳信息的消息头达8k，发送的心跳包过于庞大

    在消息头中最占空间的是myslots[CLUSTER_SLOTS/8]。 当槽位为65536时，这块的大小是: 65536÷8÷1024=8kb 
    在消息头中最占空间的是myslots[CLUSTER_SLOTS/8]。 当槽位为16384时，这块的大小是: 16384÷8÷1024=2kb
    因为每秒钟，redis节点需要发送一定数量的ping消息作为心跳包，如果槽位为65536，这个ping消息的消息头太大了，浪费带宽

(2)redis的集群主节点数量基本不可能超过1000个

    集群节点越多，心跳包的消息体内携带的数据越多。如果节点过1000个，也会导致网络拥堵
    因此redis作者不建议redis cluster节点数量超过1000个
    那么，对于节点数在1000以内的redis cluster集群，16384个槽位够用了。没有必要拓展到65536个

(3)槽位越小，节点少的情况下，压缩比高，容易传输
    Redis主节点的配置信息中它所负责的哈希槽是通过一张bitmap的形式来保存的，在传输过程中会对bitmap进行压缩
    但是如果bitmap的填充率slots / N很高的话(N表示节点数)，bitmap的压缩率就很低。 如果节点数很少，而哈希槽数量很多的话，bitmap的压缩率就很低
```

计算结论：

![image-20231126095908395](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220353451.png)

**补充说明**：Redis集群不保证强—致性，这意味着在特定的条件下，Redis集群可能会丢掉一些被系统收到的写入请求命令

![image-20231126100025551](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220353974.png)

###  9.4 集群环境案例步骤

#####  9.4.1   3主3从redis集群配置

![image-20231201011035255](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220353070.png)

1.找3台真实虚拟机，各自新建`/myredis/cluster`目录

```
mkdir -p /myredis/cluster
```

2.新建6个独立的redis实例服务并进行相应配置：

IP192.168.111.175+端口6381  `vim /myredis/cluster/redisCluster6381.conf`

```shell
bind 0.0.0.0
daemonize yes
protected-mode no
port 6381
logfile "/myredis/cluster/cluster6381.log"
pidfile /myredis/cluster6381.pid
dir /myredis/cluster
dbfilename dump6381.rdb
appendonly yes
appendfilename "appendonly6381.aof"
requirepass 111111
masterauth 111111
 
cluster-enabled yes
cluster-config-file nodes-6381.conf
cluster-node-timeout 5000
```

IP192.168.111.175+端口6382  `vim /myredis/cluster/redisCluster6382.conf`

```shell
bind 0.0.0.0
daemonize yes
protected-mode no
port 6382
logfile "/myredis/cluster/cluster6382.log"
pidfile /myredis/cluster6382.pid
dir /myredis/cluster
dbfilename dump6382.rdb
appendonly yes
appendfilename "appendonly6382.aof"
requirepass 111111
masterauth 111111
 
cluster-enabled yes
cluster-config-file nodes-6382.conf
cluster-node-timeout 5000
```

IP192.168.111.172+端口6383  `vim /myredis/cluster/redisCluster6383.conf`

```shell
bind 0.0.0.0
daemonize yes
protected-mode no
port 6383
logfile "/myredis/cluster/cluster6383.log"
pidfile /myredis/cluster6383.pid
dir /myredis/cluster
dbfilename dump6383.rdb
appendonly yes
appendfilename "appendonly6383.aof"
requirepass 111111
masterauth 111111

cluster-enabled yes
cluster-config-file nodes-6383.conf
cluster-node-timeout 5000
```

IP192.168.111.172+端口6384  `vim /myredis/cluster/redisCluster6384.conf`

```shell
bind 0.0.0.0
daemonize yes
protected-mode no
port 6384
logfile "/myredis/cluster/cluster6384.log"
pidfile /myredis/cluster6384.pid
dir /myredis/cluster
dbfilename dump6384.rdb
appendonly yes
appendfilename "appendonly6384.aof"
requirepass 111111
masterauth 111111
 
cluster-enabled yes
cluster-config-file nodes-6384.conf
cluster-node-timeout 5000
```

IP192.168.111.174+端口6385  `vim /myredis/cluster/redisCluster6385.conf`

```shell
bind 0.0.0.0
daemonize yes
protected-mode no
port 6385
logfile "/myredis/cluster/cluster6385.log"
pidfile /myredis/cluster6385.pid
dir /myredis/cluster
dbfilename dump6385.rdb
appendonly yes
appendfilename "appendonly6385.aof"
requirepass 111111
masterauth 111111
 
cluster-enabled yes
cluster-config-file nodes-6385.conf
cluster-node-timeout 5000
```

   IP192.168.111.174+端口6386  `vim /myredis/cluster/redisCluster6386.conf`

```shell
bind 0.0.0.0
daemonize yes
protected-mode no
port 6386
logfile "/myredis/cluster/cluster6386.log"
pidfile /myredis/cluster6386.pid
dir /myredis/cluster
dbfilename dump6386.rdb
appendonly yes
appendfilename "appendonly6386.aof"
requirepass 111111
masterauth 111111
 
cluster-enabled yes
cluster-config-file nodes-6386.conf
cluster-node-timeout 5000
```

3.启动6台redis主机实例

```shell
redis-server /myredis/cluster/redisCluster6381.conf
......
redis-server /myredis/cluster/redisCluster6386.conf
```

4.通过redis-cli命令为6台机器构建集群关系

构建主从关系命令(注意，注意，注意自己的真实IP地址)：

![image-20231201012027386](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220353432.png)

```shell
# --cluster-replicas 1 表示为每个master创建一个slave节点 
redis-cli -a 111111 --cluster create --cluster-replicas 1 192.168.111.175:6381 192.168.111.175:6382 192.168.111.172:6383 192.168.111.172:6384 192.168.111.174:6385 192.168.111.174:6386
```

![image-20231126224011756](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220353794.png)

5.链接进入6381作为切入点，查看并检验集群状态：链接进入6381作为切入点，查看节点状态

![image-20231126224408143](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220353038.png)

`info replication`：获取有关Redis主从复制（Replication）的信息

![image-20231126224530111](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220353069.png)

`cluster info`：获取有关Redis集群的信息

![image-20231126224554976](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220353962.png)

`cluster nodes`：获取有关Redis集群中所有节点的详细信息

![image-20231126224624508](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220353550.png)

#####  9.4.2 3主3从redis集群读写

对6381新增两个key，看看效果如何

![image-20231201215931954](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220353196.png)

为什么报错：一定注意槽位的范围区间，需要路由到具体槽位

![image-20231201220045569](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220353104.png)

如何解决？为防止路由失效需要加入参数-c，优化路由。加了`-c`参数进行连接是，整个集群作为一个整体向外提供服务，不在当前redis服务器的槽位中的数据会被正确地写到其他节点而不出错

![image-20231201220221767](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220353614.png)

查看集群信息：

![image-20231201220321733](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220353475.png)

查看某个key该属于对应的槽位值`CLUSTER KEYSLOT`键名称：

![image-20231201221017335](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220353597.png)

#####  9.4.3  主从容错切换迁移案例

容错切换迁移：6381主机停了，对应的真实从机上位。主6381和从机切换，先停止主机6381。6381作为1号主机分配的从机以实际情况为准，具体是几号机器就是几号

![image-20231201014559799](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220353506.png)

再次查看集群信息,本次6381主6384从

![image-20231201221411069](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220400997.png)

停止主机6381,再次查看集群信息：6381宕机了，6384上位成为了新的master

![image-20231201224018701](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220354558.png)

随后，6381原来的主机回来了，是否会上位?

![image-20231201224353679](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220354576.png)

![image-20231201224714726](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220354830.png)

集群不保证数据一致性100%OK，可能会有数据丢失情况。Redis集群不保证强一致性，这意味着在特定的条件下，Redis集群可能会丢掉一些被系统收到的写入请求命令

手动故障转移or节点从属调整该如何处理：上面一换后6381、6384主从对调了，和原始设计图不一样了，该如何重新登陆6381机器？常用命令：`CLUSTER FAILOVER`

![image-20231201225811217](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220354345.png)

#####  9.4.4 主从扩容案例

![image-20231201021232864](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220354977.png)

新建6387、6388两个服务实例配置文件+新建后启动

lP: 192.168.111.174+端口6387   `vim /myredis/cluster/redisCluster6387.conf`

```shell
bind 0.0.0.0
daemonize yes
protected-mode no
port 6387
logfile "/myredis/cluster/cluster6387.log"
pidfile /myredis/cluster6387.pid
dir /myredis/cluster
dbfilename dump6387.rdb
appendonly yes
appendfilename "appendonly6387.aof"
requirepass 111111
masterauth 111111

cluster-enabled yes
cluster-config-file nodes-6387.conf
cluster-node-timeout 5000
```

lP: 192.168.111.174+端口6388   `vim /myredis/cluster/redisCluster6388.conf`

```shell
bind 0.0.0.0
daemonize yes
protected-mode no
port 6388
logfile "/myredis/cluster/cluster6388.log"
pidfile /myredis/cluster6388.pid
dir /myredis/cluster
dbfilename dump6388.rdb
appendonly yes
appendfilename "appendonly6388.aof"
requirepass 111111
masterauth 111111
 
cluster-enabled yes
cluster-config-file nodes-6388.conf
cluster-node-timeout 5000
```

启动87/88两个新的节点实例，此时他们自己都是master

```shell
redis-server /myredis/cluster/redisCluster6387.conf
redis-server /myredis/cluster/redisCluster6388.conf
```

![image-20231201230335187](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220354230.png)

将新增的6387节点(空槽号)作为master节点加入原集群：

```shell
# 将新增的6387作为master节点加入原有集群
命令语法： 
redis-cli -a 密码 --cluster add-node 自己实际IP地址:6387 自己实际IP地址:6381

命令解读：
        6387 就是将要作为master新增节点
        6381 就是原来集群节点里面的领路人，相当于6387拜拜6381的码头从而找到组织加入集群
        redis-cli -a 111111  --cluster add-node 192.168.111.174:6387 192.168.111.175:6381
```



![image-20231201231402204](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220354060.png)

检查集群情况第1次：

```
redis-cli -a 密码 --cluster check 真实ip地址:6381

redis-cli -a 111111 --cluster check 192.168.111.175:6381
```

![image-20231201232004734](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220354354.png)

重新分派槽号(reshard )：

```shell
重新分派槽号
命令:redis-cli -a 密码 --cluster reshard IP地址:端口号
redis-cli -a 密码 --cluster reshard 192.168.111.175:6381
```

![image-20231201232236001](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220354388.png)

检查集群情况第2次：

```shell
redis-cli --cluster check 真实ip地址:6381
 
redis-cli -a 111111 --cluster check 192.168.111.175:6381
```

![image-20231201232418169](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220354863.png)

槽号分派说明：

```shell
为什么6387是3个新的区间，以前的还是连续？
重新分配成本太高，所以前3家各自匀出来一部分，从6381/6383/6385三个旧节点分别匀出1364个坑位给新节点6387
```

![image-20231201232535202](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220354930.png)

为主节点6387分配从节点6388：

```shell
命令：redis-cli -a 密码 --cluster add-node ip:新slave端口 ip:新master端口 --cluster-slave --cluster-master-id 新主机节点ID
 
redis-cli -a 111111 --cluster add-node 192.168.111.174:6388 192.168.111.174:6387 --cluster-slave --cluster-master-id 4feb6a7ee0ed2b39ff86474cf4189ab2a554a40f-------这个是6387的编号，按照自己实际情况
```

![image-20231201232657198](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220354662.png)

检查集群情况第3次：

```shell
redis-cli --cluster check 真实ip地址:6381
 
redis-cli -a 111111 --cluster check 192.168.111.175:6381
```

![image-20231201232753031](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220354523.png)



#####  9.4.5  主从缩容案例

![image-20231201021550937](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220354673.png)

目的：6387和6388下线

![image-20231201232858983](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220355555.png)

检查集群情况第一次，先获得从节点6388的节点ID：

```shell
redis-cli -a 密码 --cluster check 192.168.111.174:6388
```

![image-20231201232958150](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220355240.png)

从集群中将4号从节点6388删除：

```shell
命令：redis-cli -a 密码 --cluster del-node ip:从机端口 从机6388节点ID
 
redis-cli -a 111111 --cluster del-node 192.168.111.174:6388 218e7b8b4f81be54ff173e4776b4f4faaf7c13da
```

![image-20231201233225604](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220355484.png)

检查一下发现，6388被删除了，只剩下7台机器了

```shell
redis-cli -a 111111 --cluster check 192.168.111.174:6385
```

![image-20231201233317261](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220355853.png)

将6387的槽号清空，重新分配，本例将清出来的槽号都给6381：

```shell
redis-cli -a 111111 --cluster reshard 192.168.111.175:6381
```

![image-20231201233503705](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220355421.png)

![image-20231201233536615](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220355171.png)

将6387删除：

```shell
命令：redis-cli -a 密码 --cluster del-node ip:端口 6387节点ID
 
redis-cli -a 111111 --cluster del-node 192.168.111.174:6387 4feb6a7ee0ed2b39ff86474cf4189ab2a554a40f
```

![image-20231201233712565](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220355816.png)

检查集群情况第三次,6387/6388被彻底祛除：

```shell
redis-cli -a 111111 --cluster check 192.168.111.175:6381
```

![image-20231201233906724](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220355301.png)

![image-20231201233933755](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220355455.png)

###  9.5 集群常用操作命令和CRC16算法分析

1.不在同一个slot槽位下的多键操作支持不好，通识占位符登场

![image-20231202000002000](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220355211.png)

```
不在同一个slot槽位下的键值无法使用mset、mget等多键操作
可以通过{}来定义同一个组的概念，使key中{}内相同内容的键值对放到一个slot槽位去
对照下图类似k1k2k3都映射为x，自然槽位一样
```

![image-20231202000047512](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220355447.png)

2.Redis集群有16384个哈希槽，每个key通过CRC16校验后对16384取模来决定放置哪个槽。集群的每个节点负责一部分hash槽。CRC16源码浅谈：

![image-20231202000317356](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220355436.png)

常用命令：

```shell
# 集群是否完整才能对外提供服务
cluster-require-full-coverage

# 槽位是否被占用
# 1，该槽位被占用
# 0，该槽位没占用
CLUSTER COUNTKEYSINSLOT 槽位数字编号


# 该键应该存在哪个槽位上
CLUSTER KEYSLOT 键名称
```

![image-20231202012528698](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/Redis/202401220355233.png)

| cluster-require-full-coverage解读                            |
| ------------------------------------------------------------ |
| 默认YES，现在集群架构是3主3从的redis cluster由3个master平分16384个slot，每个master的小集群负责1/3的slot，对应一部分数据。cluster-require-full-coverage： 默认值 yes , 即需要集群完整性，方可对外提供服务 通常情况，如果这3个小集群中，任何一个（1主1从）挂了，你这个集群对外可提供的数据只有2/3了， 整个集群是不完整的， redis 默认在这种情况下，是不会对外提供服务的 |
| 如果你的诉求是，集群不完整的话也需要对外提供服务，需要将该参数设置为no ，这样的话你挂了的那个小集群是不行了，但是其他的小集群仍然可以对外提供服务 |









