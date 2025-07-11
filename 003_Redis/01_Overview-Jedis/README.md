<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1.Redis简介](#1redis%E7%AE%80%E4%BB%8B)
  - [1.1.NoSQL简介](#11nosql%E7%AE%80%E4%BB%8B)
    - [1.1.1.结构化与非结构化](#111%E7%BB%93%E6%9E%84%E5%8C%96%E4%B8%8E%E9%9D%9E%E7%BB%93%E6%9E%84%E5%8C%96)
    - [1.1.2.关联和非关联](#112%E5%85%B3%E8%81%94%E5%92%8C%E9%9D%9E%E5%85%B3%E8%81%94)
    - [1.1.3.查询方式](#113%E6%9F%A5%E8%AF%A2%E6%96%B9%E5%BC%8F)
    - [1.1.4.事务](#114%E4%BA%8B%E5%8A%A1)
    - [1.1.5.关系型数据库 VS 非关系型数据库](#115%E5%85%B3%E7%B3%BB%E5%9E%8B%E6%95%B0%E6%8D%AE%E5%BA%93-vs-%E9%9D%9E%E5%85%B3%E7%B3%BB%E5%9E%8B%E6%95%B0%E6%8D%AE%E5%BA%93)
  - [1.2.Redis简介](#12redis%E7%AE%80%E4%BB%8B)
  - [1.3.Redis安装](#13redis%E5%AE%89%E8%A3%85)
    - [1.3.1.依赖库](#131%E4%BE%9D%E8%B5%96%E5%BA%93)
    - [1.3.2.上传安装包并解压](#132%E4%B8%8A%E4%BC%A0%E5%AE%89%E8%A3%85%E5%8C%85%E5%B9%B6%E8%A7%A3%E5%8E%8B)
    - [1.3.3.启动](#133%E5%90%AF%E5%8A%A8)
    - [1.3.4.默认启动](#134%E9%BB%98%E8%AE%A4%E5%90%AF%E5%8A%A8)
    - [1.3.5.指定配置启动](#135%E6%8C%87%E5%AE%9A%E9%85%8D%E7%BD%AE%E5%90%AF%E5%8A%A8)
    - [1.3.6.开机自启](#136%E5%BC%80%E6%9C%BA%E8%87%AA%E5%90%AF)
  - [1.4.Redis桌面客户端](#14redis%E6%A1%8C%E9%9D%A2%E5%AE%A2%E6%88%B7%E7%AB%AF)
    - [1.4.1.Redis命令行客户端](#141redis%E5%91%BD%E4%BB%A4%E8%A1%8C%E5%AE%A2%E6%88%B7%E7%AB%AF)
    - [1.4.2.图形化桌面客户端](#142%E5%9B%BE%E5%BD%A2%E5%8C%96%E6%A1%8C%E9%9D%A2%E5%AE%A2%E6%88%B7%E7%AB%AF)
    - [1.4.3.安装图形化桌面客户端](#143%E5%AE%89%E8%A3%85%E5%9B%BE%E5%BD%A2%E5%8C%96%E6%A1%8C%E9%9D%A2%E5%AE%A2%E6%88%B7%E7%AB%AF)
    - [1.4.4.建立连接](#144%E5%BB%BA%E7%AB%8B%E8%BF%9E%E6%8E%A5)
- [2.Redis常见命令](#2redis%E5%B8%B8%E8%A7%81%E5%91%BD%E4%BB%A4)
  - [2.1.Redis通用命令](#21redis%E9%80%9A%E7%94%A8%E5%91%BD%E4%BB%A4)
  - [2.2.String类型](#22string%E7%B1%BB%E5%9E%8B)
    - [2.2.1.String的常见命令](#221string%E7%9A%84%E5%B8%B8%E8%A7%81%E5%91%BD%E4%BB%A4)
    - [2.2.2.Redis的Key结构](#222redis%E7%9A%84key%E7%BB%93%E6%9E%84)
  - [2.3.Hash类型](#23hash%E7%B1%BB%E5%9E%8B)
  - [2.4.List类型](#24list%E7%B1%BB%E5%9E%8B)
  - [2.5.Set类型](#25set%E7%B1%BB%E5%9E%8B)
  - [2.6.SortedSet类型](#26sortedset%E7%B1%BB%E5%9E%8B)
- [3.Redis的Java客户端](#3redis%E7%9A%84java%E5%AE%A2%E6%88%B7%E7%AB%AF)
  - [3.1.Jedis客户端](#31jedis%E5%AE%A2%E6%88%B7%E7%AB%AF)
    - [3.1.1 Jedis入门案例](#311-jedis%E5%85%A5%E9%97%A8%E6%A1%88%E4%BE%8B)
    - [3.1.2.连接池](#312%E8%BF%9E%E6%8E%A5%E6%B1%A0)
  - [3.2.SprngDataRedis客户端](#32sprngdataredis%E5%AE%A2%E6%88%B7%E7%AB%AF)
    - [3.2.1.快速入门](#321%E5%BF%AB%E9%80%9F%E5%85%A5%E9%97%A8)
      - [1）引入依赖](#1%E5%BC%95%E5%85%A5%E4%BE%9D%E8%B5%96)
      - [2）配置Redis](#2%E9%85%8D%E7%BD%AEredis)
      - [3）注入RedisTemplate](#3%E6%B3%A8%E5%85%A5redistemplate)
      - [4）编写测试](#4%E7%BC%96%E5%86%99%E6%B5%8B%E8%AF%95)
    - [3.2.2.数据序列化器](#322%E6%95%B0%E6%8D%AE%E5%BA%8F%E5%88%97%E5%8C%96%E5%99%A8)
    - [3.2.3.StringRedisTemplate](#323stringredistemplate)
    - [3.2.4 Hash结构操作](#324-hash%E7%BB%93%E6%9E%84%E6%93%8D%E4%BD%9C)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

Redis入门：Redis的常见命令和客户端使用

# 1.Redis简介

Redis是一种键值型的NoSql数据库，这里有两个关键字：

- 键值型

- NoSql

其中**键值型**，是指Redis中存储的数据都是以key、value对的形式存储，而value的形式多种多样，可以是字符串、数值、甚至json：

![img](https://img-blog.csdnimg.cn/6531e3d0be2747ce974ab998d59863f0.png)



而NoSql则是相对于传统关系型数据库而言，有很大差异的一种数据库

## 1.1.NoSQL简介

NoSql可以翻译做Not Only Sql（不仅仅是SQL），或者是No Sql（非Sql的）数据库
是相对于传统关系型数据库而言，有很大差异的一种特殊的数据库，因此也称之为非关系型数据库

### 1.1.1.结构化与非结构化

传统关系型数据库是结构化数据，每一张表都有严格的约束信息：字段名、字段数据类型、字段约束等等信息，插入的数据必须遵守这些约束：

![img](https://img-blog.csdnimg.cn/1055802c7d364858aee0678b67847318.png)

而NoSql则对数据库格式没有严格约束，往往形式松散，自由

可以是键值型：

![img](https://img-blog.csdnimg.cn/f41dbf5afb53460cb82a9532800e328d.png)

也可以是文档型：

![img](https://img-blog.csdnimg.cn/f830517b0ff64ac48c7f72eb2c7352fe.png)



甚至可以是图格式：

![img](https://img-blog.csdnimg.cn/43390063cb044931a3ee75a22c6315f0.png)



### 1.1.2.关联和非关联

传统数据库的表与表之间往往存在关联，例如外键：

![img](https://img-blog.csdnimg.cn/a26a2ed59736489f9c43616e627bd532.png)



而非关系型数据库不存在关联关系，要维护关系要么靠代码中的业务逻辑，要么靠数据之间的耦合：

```json
{
  id: 1,
  name: "张三",
  orders: [
    {
       id: 1,
       item: {
	 id: 10, title: "荣耀6", price: 4999
       }
    },
    {
       id: 2,
       item: {
	 id: 20, title: "小米11", price: 3999
       }
    }
  ]
}
```

此处要维护“张三”的订单与商品“荣耀”和“小米11”的关系，不得不冗余的将这两个商品保存在张三的订单文档中，不够优雅。还是建议用业务来维护关联关系。



### 1.1.3.查询方式

传统关系型数据库会基于Sql语句做查询，语法有统一标准；

而不同的非关系数据库查询语法差异极大，五花八门各种各样。

![img](https://img-blog.csdnimg.cn/f090991621244c62b66d1ac72e221013.png)



### 1.1.4.事务

传统关系型数据库能满足事务ACID的原则。

![img](https://img-blog.csdnimg.cn/8bb78dca042f45278f2f823d25b64493.png)



而非关系型数据库往往不支持事务，或者不能严格保证ACID的特性，只能实现基本的一致性。



### 1.1.5.关系型数据库 VS 非关系型数据库

除了上述四点以外，在存储方式、扩展性、查询性能上关系型与非关系型也都有着显著差异，总结如下：

![img](https://img-blog.csdnimg.cn/4768f1e0cd9d4da7b8a0676f83e31aba.png)

- 存储方式
  - 关系型数据库基于磁盘进行存储，会有大量的磁盘IO，对性能有一定影响
  - 非关系型数据库，他们的操作更多的是依赖于内存来操作，内存的读写速度会非常快，性能自然会好一些

* 扩展性
  * 关系型数据库集群模式一般是主从，主从数据一致，起到数据备份的作用，称为垂直扩展。
  * 非关系型数据库可以将数据拆分，存储在不同机器上，可以保存海量数据，解决内存大小有限的问题。称为水平扩展。
  * 关系型数据库因为表之间存在关联关系，如果做水平扩展会给数据查询带来很多麻烦




## 1.2.Redis简介

Redis诞生于2009年全称是**Re**mote  **D**ictionary **S**erver 远程词典服务器，是一个基于内存的键值型NoSQL数据库。

**特征**：

- 键值（key-value）型，value支持多种不同数据结构，功能丰富
- 单线程，每个命令具备原子性
- 低延迟，速度快（基于内存、IO多路复用、良好的编码）。
- 支持数据持久化
- 支持主从集群、分片集群
- 支持多语言客户端

**作者**：Antirez

Redis的官方网站地址：https://redis.io/



## 1.3.Redis安装

大多数企业都是基于Linux服务器来部署项目，而且Redis官方也没有提供Windows版本的安装包。因此课程中我们会基于Linux系统来安装Redis.

此处选择的Linux版本为CentOS 7.

### 1.3.1.依赖库

Redis是基于C语言编写的，因此首先需要安装Redis所需要的gcc依赖：

```sh
yum install -y gcc tcl
```



### 1.3.2.上传安装包并解压

然后将课前资料提供的Redis安装包上传到虚拟机的任意目录：



![img](https://img-blog.csdnimg.cn/c36e9a960ed44ce19994694bdedd3a3e.png)

例如，我放到了/usr/local/src 目录：

![img](https://img-blog.csdnimg.cn/b465b60240ed46afb712f356d06c47fa.png)

解压缩：

```sh
tar -zxvf redis-6.2.6.tar.gz
```

解压后：

![img](https://img-blog.csdnimg.cn/d352bd7531d0414da5422befcf1395e0.png)

进入redis目录：

```sh
cd redis-6.2.6
```



运行编译命令：

```sh
make && make install
```

如果没有出错，应该就安装成功了。

默认的安装路径是在 `/usr/local/bin`目录下：

该目录已经默认配置到环境变量，因此可以在任意目录下运行这些命令。其中：

- redis-cli：是redis提供的命令行客户端
- redis-server：是redis的服务端启动脚本
- redis-sentinel：是redis的哨兵启动脚本



### 1.3.3.启动

redis的启动方式有很多种，例如：

- 默认启动
- 指定配置启动
- 开机自启



### 1.3.4.默认启动

安装完成后，在任意目录输入redis-server命令即可启动Redis：

```
redis-server
```

如图：

![img](https://img-blog.csdnimg.cn/73070b533ead415db0d68768000f5ae5.png)

这种启动属于`前台启动`，会阻塞整个会话窗口，窗口关闭或者按下`CTRL + C`则Redis停止。不推荐使用。

### 1.3.5.指定配置启动

如果要让Redis以`后台`方式启动，则必须修改Redis配置文件，就在我们之前解压的redis安装包下（`/usr/local/src/redis-6.2.6`），名字叫redis.conf：

![img](https://img-blog.csdnimg.cn/03994ebbb4f54575ae89ef6d40477549.png)

我们先将这个配置文件备份一份：

```
cp redis.conf redis.conf.bck
```



然后修改redis.conf文件中的一些配置：

```properties
# 允许访问的地址，默认是127.0.0.1，会导致只能在本地访问
# 设为0.0.0.0表示任意一台计算机都可以访问这个redis服务器
# 修改为0.0.0.0则可以在任意IP访问，生产环境不要设置为0.0.0.0
bind 0.0.0.0
# 守护进程，修改为yes后即可后台运行
daemonize yes 
# 密码，设置后访问Redis必须输入密码
requirepass 123321
```



Redis的其它常见配置：

```properties
# 监听的端口
port 6379
# 工作目录，默认是当前目录，也就是运行redis-server时的命令，日志、持久化等文件会保存在这个目录
dir .
# 数据库数量，设置为1，代表只使用1个库，默认有16个库，编号0~15
databases 1
# 设置redis能够使用的最大内存
maxmemory 512mb
# 日志文件，默认为空，不记录日志，可以指定日志文件名
logfile "redis.log"
```

启动Redis：

```sh
# 进入redis安装目录 
cd /usr/local/src/redis-6.2.6
# 启动：  redis-server命令指定配置文件名启动对应配置的redis服务 
redis-server redis.conf
# 查看是否启动成功（查看是否有redis进程）
ps -ef | grep redis
```

![img](https://img-blog.csdnimg.cn/1b1b471cc2804dc1ae94e7428ae70b79.png)

停止服务：

```sh
# 方法一：kill -9命令杀死进程
kill -9 15556
# 利用redis-cli来执行 shutdown 命令，即可停止 Redis 服务，
# 因为之前配置了密码，因此需要通过 -u 来指定密码
redis-cli -u 123321 shutdown
```

### 1.3.6.开机自启

我们也可以通过配置来实现开机自启

首先，新建一个系统服务文件：

```sh
vi /etc/systemd/system/redis.service
```

内容如下：

```conf
[Unit]
Description=redis-server
After=network.target

[Service]
Type=forking
ExecStart=/usr/local/bin/redis-server /usr/local/src/redis-6.2.6/redis.conf
PrivateTmp=true

[Install]
WantedBy=multi-user.target
```

<table align="center">
    <b>利用edit-plus创建并修改redis.service文件</b></th>
    <tr>
    <th ><img src="https://img-blog.csdnimg.cn/2f2650edf3f84be0bdda57a39efd83ed.png" > 
    </tr>
    </table>



然后重载系统服务，使配置生效：

```sh
systemctl daemon-reload
```

配置生效后就可以用下面这组命令来操作redis了：

```sh
# 启动
systemctl start redis
# 停止
systemctl stop redis
# 重启
systemctl restart redis
# 查看状态
systemctl status redis
```



执行下面两个命令，可以让redis开机自启：

```sh
systemctl enable redis
systemctl start redis
```



## 1.4.Redis桌面客户端

安装完成Redis，我们就可以操作Redis，实现数据的CRUD了。这需要用到Redis客户端，包括：

- 命令行客户端
- 图形化桌面客户端
- 编程客户端

### 1.4.1.Redis命令行客户端

Redis安装完成后就自带了命令行客户端：redis-cli，使用方式如下：

```sh
redis-cli [options] [commonds]
```

其中常见的options有：

- `-h 127.0.0.1`：指定要连接的redis节点的IP地址，默认是127.0.0.1
- `-p 6379`：指定要连接的redis节点的端口，默认是6379
- `-a 123321`：指定redis的访问密码 

其中的commonds就是Redis的操作命令，例如：

- `ping`：与redis服务端做心跳测试，服务端正常会返回`pong`

不指定commond时，会进入`redis-cli`的交互控制台：

```sh
# 通过命令使用密码连接特点主机的特点端口
redis-cli -h 192.168.54.133 -p 6379 -a 123321
```

![img](https://img-blog.csdnimg.cn/1c644d60eee94272ba198f7275c1e968.png)

![img](https://img-blog.csdnimg.cn/6dd1924d21214851be550be8c28ab2d8.png)

### 1.4.2.图形化桌面客户端

GitHub上的大神编写了Redis的图形化桌面客户端，地址：https://github.com/uglide/RedisDesktopManager

不过该仓库提供的是RedisDesktopManager的源码，并未提供windows安装包。

在下面这个仓库可以找到安装包：https://github.com/lework/RedisDesktopManager-Windows/releases



### 1.4.3.安装图形化桌面客户端

在课前资料中可以找到Redis的图形化桌面客户端：

![img](https://img-blog.csdnimg.cn/8b41b67138bf41fb91f7678f8dfcdc84.png)

解压缩后，运行安装程序即可安装：

![img](https://img-blog.csdnimg.cn/8efa84bfb5fb41e2acc2672e6340a078.png)

安装完成后，在安装目录下找到rdm.exe文件：

![img](https://img-blog.csdnimg.cn/98da81ed72524901b5ab5f162d0e40ec.png)

双击即可运行：

![img](https://img-blog.csdnimg.cn/6f85fae347fe446c8ace09bd732e98ab.png)



### 1.4.4.建立连接

点击左上角的`连接到Redis服务器`按钮：

![img](https://img-blog.csdnimg.cn/ef6e44cf153b4c938b6c9d668a625f7a.png)

在弹出的窗口中填写Redis服务信息：

![img](https://img-blog.csdnimg.cn/85e7008350314425af1c571d32e461c1.png)

点击确定后，在左侧菜单会出现这个链接：

![img](https://img-blog.csdnimg.cn/95c4154f257c4fe4a630811055ad078c.png)

点击即可建立连接了。

![img](https://img-blog.csdnimg.cn/869bc8487ff140008b271be607ad66cb.png)



Redis默认有16个仓库，编号从0至15.  通过配置文件可以设置仓库数量，但是不超过16，并且不能自定义仓库名称。

如果是基于redis-cli连接Redis服务，可以通过select命令来选择数据库：

```sh
# 选择 0号库
select 0
```



# 2.Redis常见命令

Redis是典型的key-value数据库，key一般是字符串，而value包含很多不同的数据类型：

![img](https://img-blog.csdnimg.cn/99a6cec5b93c436094a77f4e5f2fd753.png)



Redis为了方便我们学习，将操作不同数据类型的命令也做了分组，在官网（ [https://redis.io/commands ](https://redis.io/commands)）可以查看到不同的命令：

![img](https://img-blog.csdnimg.cn/3dfc20fd77114ee1aaef06f2e558cf1f.png)

不同类型的命令称为一个group，我们也可以通过help命令来查看各种不同group的命令：

![img](https://img-blog.csdnimg.cn/99560a05fe8243eb98b6c69c19dd1b5d.png)

接下来，我们就学习常见的五种基本数据类型的相关命令。



## 2.1.Redis通用命令

通用指令是部分数据类型的，都可以使用的指令，常见的有：

- KEYS：查看符合模板的所有key
- DEL：删除一个指定的key
- EXISTS：判断key是否存在
- EXPIRE：给一个key设置有效期，有效期到期时该key会被自动删除
- TTL：查看一个KEY的剩余有效期

通过help [command] 可以查看一个命令的具体用法，例如：

```sh
# 查看keys命令的帮助信息：
127.0.0.1:6379> help keys

KEYS pattern
summary: Find all keys matching the given pattern
since: 1.0.0
group: generic
```

课堂代码如下

* KEYS

```sh
127.0.0.1:6379> keys *
1) "name"
2) "age"
127.0.0.1:6379>

# 查询以a开头的key
127.0.0.1:6379> keys a*
1) "age"
127.0.0.1:6379>
```

**贴心小提示：在生产环境下，不推荐使用keys 命令，因为这个命令在key过多的情况下，效率不高**

* DEL

```sh
127.0.0.1:6379> help del

  DEL key [key ...]
  summary: Delete a key
  since: 1.0.0
  group: generic

127.0.0.1:6379> del name #删除单个
(integer) 1  #成功删除1个

127.0.0.1:6379> keys *
1) "age"

127.0.0.1:6379> MSET k1 v1 k2 v2 k3 v3 #批量添加数据
OK

127.0.0.1:6379> keys *
1) "k3"
2) "k2"
3) "k1"
4) "age"

127.0.0.1:6379> del k1 k2 k3 k4
(integer) 3   #此处返回的是成功删除的key，由于redis中只有k1,k2,k3 所以只成功删除3个，最终返回
127.0.0.1:6379>

127.0.0.1:6379> keys * #再查询全部的key
1) "age"	#只剩下一个了
127.0.0.1:6379>
```

**贴心小提示：同学们在拷贝代码的时候，只需要拷贝对应的命令哦~**

* EXISTS

```sh
127.0.0.1:6379> help EXISTS

  EXISTS key [key ...]
  summary: Determine if a key exists
  since: 1.0.0
  group: generic

127.0.0.1:6379> exists age
(integer) 1

127.0.0.1:6379> exists name
(integer) 0
```

* EXPIRE

**贴心小提示**：内存非常宝贵，对于一些数据，我们应当给他一些过期时间，当过期时间到了之后，他就会自动被删除~

```sh
127.0.0.1:6379> expire age 10
(integer) 1

127.0.0.1:6379> ttl age
(integer) 8

127.0.0.1:6379> ttl age
(integer) 6

127.0.0.1:6379> ttl age
(integer) -2

127.0.0.1:6379> ttl age
(integer) -2  #当这个key过期了，那么此时查询出来就是-2 

127.0.0.1:6379> keys *
(empty list or set)

127.0.0.1:6379> set age 10 #如果没有设置过期时间
OK

127.0.0.1:6379> ttl age
(integer) -1  # ttl的返回值就是-1
```



## 2.2.String类型

String类型，也就是字符串类型，是Redis中最简单的存储类型。

其value是字符串，不过根据字符串的格式不同，又可以分为3类：

- string：普通字符串
- int：整数类型，可以做自增、自减操作
- float：浮点类型，可以做自增、自减操作

不管是哪种格式，底层都是字节数组形式存储，只不过是编码方式不同。字符串类型的最大空间不能超过512m.

![img](https://img-blog.csdnimg.cn/cd694f3c007f4fd28eaa60e5282fee4a.png)



### 2.2.1.String的常见命令

String的常见命令有：

- SET：添加或者修改已经存在的一个String类型的键值对
- GET：根据key获取String类型的value
- MSET：批量添加多个String类型的键值对
- MGET：根据多个key获取多个String类型的value
- INCR：让一个整型的key自增1
- INCRBY:让一个整型的key自增并指定步长，例如：incrby num 2 让num值自增2
- INCRBYFLOAT：让一个浮点类型的数字自增并指定步长
- SETNX：添加一个String类型的键值对，前提是这个key不存在，否则不执行
- SETEX：添加一个String类型的键值对，并且指定有效期

**贴心小提示**：以上命令除了INCRBYFLOAT 都是常用命令

* SET 和GET: 如果key不存在则是新增，如果存在则是修改

```java
127.0.0.1:6379> set name Rose  //原来不存在
OK

127.0.0.1:6379> get name 
"Rose"

127.0.0.1:6379> set name Jack //原来存在，就是修改
OK

127.0.0.1:6379> get name
"Jack"
```

* MSET和MGET

```java
127.0.0.1:6379> MSET k1 v1 k2 v2 k3 v3
OK

127.0.0.1:6379> MGET name age k1 k2 k3
1) "Jack" //之前存在的name
2) "10"   //之前存在的age
3) "v1"
4) "v2"
5) "v3"
```

* INCR和INCRBY和DECY

```java
127.0.0.1:6379> get age 
"10"

127.0.0.1:6379> incr age //增加1
(integer) 11
    
127.0.0.1:6379> get age //获得age
"11"

127.0.0.1:6379> incrby age 2 //一次增加2
(integer) 13 //返回目前的age的值
    
127.0.0.1:6379> incrby age 2
(integer) 15
    
127.0.0.1:6379> incrby age -1 //也可以增加负数，相当于减
(integer) 14
    
127.0.0.1:6379> incrby age -2 //一次减少2个
(integer) 12
    
127.0.0.1:6379> DECR age //相当于 incr 负数，减少正常用法
(integer) 11
    
127.0.0.1:6379> get age 
"11"

192.168.54.133:6379> set float 10.5
OK
    
192.168.54.133:6379> incrbyfloat float 0.5
"11"
    
192.168.54.133:6379> incrbyfloat float 0.5
"11.5"
    
192.168.54.133:6379> incrbyfloat float 0.5
"12"
```

* SETNX

```java
127.0.0.1:6379> help setnx

  SETNX key value
  summary: Set the value of a key, only if the key does not exist
  since: 1.0.0
  group: string

127.0.0.1:6379> set name Jack  //设置名称
OK
127.0.0.1:6379> setnx name lisi //如果key不存在，则添加成功
(integer) 0
127.0.0.1:6379> get name //由于name已经存在，所以lisi的操作失败
"Jack"
127.0.0.1:6379> setnx name2 lisi //name2 不存在，所以操作成功
(integer) 1
127.0.0.1:6379> get name2 
"lisi"
```

* SETEX

```sh
127.0.0.1:6379> setex name 10 jack
OK

127.0.0.1:6379> ttl name
(integer) 8

127.0.0.1:6379> ttl name
(integer) 7

127.0.0.1:6379> ttl name
(integer) 5
```

### 2.2.2.Redis的Key结构

Redis没有类似MySQL中的Table的概念，我们该如何区分不同类型的key呢？

例如，需要存储用户、商品信息到redis，有一个用户id是1，有一个商品id恰好也是1，此时如果使用id作为key，那就会冲突了，该怎么办？

我们可以通过给key添加前缀加以区分，不过这个前缀不是随便加的，有一定的规范：

Redis的key允许有多个单词形成层级结构，多个单词之间用':'隔开，格式如下：

```
	项目名:业务名:类型:id
```

![img](https://img-blog.csdnimg.cn/73e7bb67d1064c3c848f24035f732bc1.png)

这个格式并非固定，也可以根据自己的需求来删除或添加词条。这样以来，我们就可以把不同类型的数据区分开了。从而避免了key的冲突问题

例如我们的项目名称叫 heima，有user和product两种不同类型的数据，我们可以这样定义key：

- user相关的key：**heima:user:1**

- product相关的key：**heima:product:1**



如果Value是一个Java对象，例如一个User对象，则可以将对象序列化为JSON字符串后存储：

| **KEY**         | **VALUE**                                  |
| --------------- | ------------------------------------------ |
| heima:user:1    | {"id":1,  "name": "Jack", "age": 21}       |
| heima:product:1 | {"id":1,  "name": "小米11", "price": 4999} |

![img](https://img-blog.csdnimg.cn/62272242e55843fc8d54e8c7bc4f66d5.png)



**命令演示：**

```sh
192.168.54.133:6379> set lsl:user:1 '{"id":1, "name":"Jack", "age": 21}'
OK

192.168.54.133:6379> set lsl:user:2 '{"id":2, "name":"Rose", "age": 18}'
OK

192.168.54.133:6379> set lsl:user:3 '{"id":1, "name":"小米11", "price": 4999}'
OK

192.168.54.133:6379> set lsl:product:1 '{"id":1, "name":"小米11", "price": 4999}'
OK

192.168.54.133:6379> set lsl:product:2 '{"id":2, "name":"荣耀6", "price": 2999}'
OK
```



并且，在Redis的桌面客户端中，还会以相同前缀作为层级结构，让数据看起来层次分明，关系清晰：

![img](https://img-blog.csdnimg.cn/4ae93800313f4531aa7648e70cf6b964.png)

String类型的三种格式:

- 字符串
- int
- float

Redis的key的格式:

- [项目名]：[业务名]：[类型名]：[id]

## 2.3.Hash类型

> Hash类型，也叫散列，其value是一个无序字典，类似于Java中的HashMap结构。
>
> String结构是将对象序列化为JSON字符串后存储，当需要修改对象某个字段时很不方便：

![img](https://img-blog.csdnimg.cn/27e2e01275c348c2b50e6138393ac47b.png)



> Hash结构可以将对象中的每个字段独立存储，可以针对单个字段做CRUD：

![img](https://img-blog.csdnimg.cn/a1b1b52182d648b58a5311af9e422230.png)

Hash的常见命令有：

- HSET key field value：添加或者修改hash类型key的field的值

- HGET key field：获取一个hash类型key的field的值

- HMSET：批量添加多个hash类型key的field的值

- HMGET：批量获取多个hash类型key的field的值

- HGETALL：获取一个hash类型的key中的所有的field和value
- HKEYS：获取一个hash类型的key中的所有的field
- HINCRBY:让一个hash类型key的字段值自增并指定步长
- HSETNX：添加一个hash类型的key的field值，前提是这个field不存在，否则不执行

**贴心小提示**：哈希结构也是我们以后实际开发中常用的命令哟

* HSET和HGET

```java
127.0.0.1:6379> HSET heima:user:3 name Lucy//大key是 heima:user:3 小key是name，小value是Lucy
(integer) 1
127.0.0.1:6379> HSET heima:user:3 age 21// 如果操作不存在的数据，则是新增
(integer) 1
127.0.0.1:6379> HSET heima:user:3 age 17 //如果操作存在的数据，则是修改
(integer) 0
127.0.0.1:6379> HGET heima:user:3 name 
"Lucy"
127.0.0.1:6379> HGET heima:user:3 age
"17"
```

* HMSET和HMGET

```java
127.0.0.1:6379> HMSET heima:user:4 name HanMeiMei
OK
127.0.0.1:6379> HMSET heima:user:4 name LiLei age 20 sex man
OK
127.0.0.1:6379> HMGET heima:user:4 name age sex
1) "LiLei"
2) "20"
3) "man"
```

* HGETALL

```java
127.0.0.1:6379> HGETALL heima:user:4
1) "name"
2) "LiLei"
3) "age"
4) "20"
5) "sex"
6) "man"
```

* HKEYS和HVALS

```java
127.0.0.1:6379> HKEYS heima:user:4
1) "name"
2) "age"
3) "sex"
127.0.0.1:6379> HVALS heima:user:4
1) "LiLei"
2) "20"
3) "man"
```

* HINCRBY

```java
127.0.0.1:6379> HINCRBY  heima:user:4 age 2
(integer) 22
127.0.0.1:6379> HVALS heima:user:4
1) "LiLei"
2) "22"
3) "man"
127.0.0.1:6379> HINCRBY  heima:user:4 age -2
(integer) 20
```

* HSETNX

```java
127.0.0.1:6379> HSETNX heima:user4 sex woman
(integer) 1
127.0.0.1:6379> HGETALL heima:user:3
1) "name"
2) "Lucy"
3) "age"
4) "17"
127.0.0.1:6379> HSETNX heima:user:3 sex woman
(integer) 1
127.0.0.1:6379> HGETALL heima:user:3
1) "name"
2) "Lucy"
3) "age"
4) "17"
5) "sex"
6) "woman"
```



## 2.4.List类型

Redis中的List类型与Java中的LinkedList类似，可以看做是一个双向链表结构。既可以支持正向检索和也可以支持反向检索。

特征也与LinkedList类似：

- 有序
- 元素可以重复
- 插入和删除快
- 查询速度一般

常用来存储一个有序数据，例如：朋友圈点赞列表，评论列表等。

**List的常见命令有：**

- LPUSH key element ... ：向列表左侧插入一个或多个元素
- LPOP key：移除并返回列表左侧的第一个元素，没有则返回nil
- RPUSH key element ... ：向列表右侧插入一个或多个元素
- RPOP key：移除并返回列表右侧的第一个元素
- LRANGE key star end：返回一段角标范围内的所有元素
- BLPOP和BRPOP：与LPOP和RPOP类似，只不过在没有元素时等待指定时间，而不是直接返回nil

![img](https://img-blog.csdnimg.cn/66bbbc7697de43c3b5723ff068f0c87f.png)

* LPUSH和RPUSH

```java
127.0.0.1:6379> LPUSH users 1 2 3
(integer) 3
127.0.0.1:6379> RPUSH users 4 5 6
(integer) 6
```

* LPOP和RPOP

```java
127.0.0.1:6379> LPOP users
"3"
127.0.0.1:6379> RPOP users
"6"
```

* LRANGE

```java
127.0.0.1:6379> LRANGE users 1 2
1) "1"
2) "4"
```

**如何利用List结构模拟一个栈?**

- 入口和出口在同一边

**如何利用List结构模拟一个队列?**

- 入口和出口在不同边

**如何利用List结构模拟一个阻塞队列?**

- 入口和出口在不同边
- 出队时采用BLPOP或BRPOP

## 2.5.Set类型

Redis的Set结构与Java中的HashSet类似，可以看做是一个value为null的HashMap。因为也是一个hash表，因此具备与HashSet类似的特征：

- 无序

- 元素不可重复

- 查找快

- 支持交集、并集、差集等功能

Set的常见命令有：

- SADD key member ... ：向set中添加一个或多个元素
- SREM key member ... : 移除set中的指定元素
- SCARD key： 返回set中元素的个数
- SISMEMBER key member：判断一个元素是否存在于set中
- SMEMBERS：获取set中的所有元素
- SINTER key1 key2 ... ：求key1与key2的交集



例如两个集合：s1和s2:

![img](https://img-blog.csdnimg.cn/997398275cd14476b4c48c530b7af7bf.png)

求交集：SINTER s1 s2

求s1与s2的不同：SDIFF s1 s2

![img](https://img-blog.csdnimg.cn/e39fc02020c94738b28128ea950666aa.png)

**具体命令**

```java
127.0.0.1:6379> sadd s1 a b c
(integer) 3
127.0.0.1:6379> smembers s1
1) "c"
2) "b"
3) "a"
127.0.0.1:6379> srem s1 a
(integer) 1
    
127.0.0.1:6379> SISMEMBER s1 a
(integer) 0
    
127.0.0.1:6379> SISMEMBER s1 b
(integer) 1
    
127.0.0.1:6379> SCARD s1
(integer) 2
```

**案例**

* 将下列数据用Redis的Set集合来存储：
* 张三的好友有：李四.王五.赵六
* 李四的好友有：王五.麻子.二狗
* 利用Set的命令实现下列功能：
* 计算张三的好友有几人
* 计算张三和李四有哪些共同好友
* 查询哪些人是张三的好友却不是李四的好友
* 查询张三和李四的好友总共有哪些人
* 判断李四是否是张三的好友
* 判断张三是否是李四的好友
* 将李四从张三的好友列表中移除

```java
127.0.0.1:6379> SADD zs lisi wangwu zhaoliu
(integer) 3
    
127.0.0.1:6379> SADD ls wangwu mazi ergou
(integer) 3
    
127.0.0.1:6379> SCARD zs
(integer) 3
    
127.0.0.1:6379> SINTER zs ls
1) "wangwu"
    
127.0.0.1:6379> SDIFF zs ls
1) "zhaoliu"
2) "lisi"
    
127.0.0.1:6379> SUNION zs ls
1) "wangwu"
2) "zhaoliu"
3) "lisi"
4) "mazi"
5) "ergou"
    
127.0.0.1:6379> SISMEMBER zs lisi
(integer) 1
    
127.0.0.1:6379> SISMEMBER ls zhangsan
(integer) 0
    
127.0.0.1:6379> SREM zs lisi
(integer) 1
    
127.0.0.1:6379> SMEMBERS zs
1) "zhaoliu"
2) "wangwu"
```



## 2.6.SortedSet类型

Redis的SortedSet是一个可排序的set集合，与Java中的TreeSet有些类似，但底层数据结构却差别很大。SortedSet中的每一个元素都带有一个score属性，可以基于score属性对元素排序，底层的实现是一个跳表（SkipList）加 hash表。

SortedSet具备下列特性：

- 可排序
- 元素不重复
- 查询速度快

因为SortedSet的可排序特性，经常被用来实现排行榜这样的功能。

SortedSet的常见命令有：

- ZADD key score member：添加一个或多个元素到sorted set ，如果已经存在则更新其score值
- ZREM key member：删除sorted set中的一个指定元素
- ZSCORE key member : 获取sorted set中的指定元素的score值
- ZRANK key member：获取sorted set 中的指定元素的排名
- ZCARD key：获取sorted set中的元素个数
- ZCOUNT key min max：统计score值在给定范围内的所有元素的个数
- ZINCRBY key increment member：让sorted set中的指定元素自增，步长为指定的increment值
- ZRANGE key min max：按照score排序后，获取指定排名范围内的元素
- ZRANGEBYSCORE key min max：按照score排序后，获取指定score范围内的元素
- ZDIFF、ZINTER、ZUNION：求差集、交集、并集

**注意：所有的排名默认都是升序，如果要降序则在命令的Z后面添加REV即可**，例如：

- **升序**获取sorted set 中的指定元素的排名：ZRANK key member

- **降序**获取sorted set 中的指定元素的排名：ZREVRANK key memeber

练习题：

将班级的下列学生得分存入Redis的SortedSet中：

Jack 85, Lucy 89, Rose 82, Tom 95, Jerry 78, Amy 92, Miles 76

并实现下列功能：

- 删除Tom同学
- 获取Amy同学的分数
- 获取Rose同学的排名
- 查询80分以下有几个学生
- 给Amy同学加2分
- 查出成绩前3名的同学
- 查出成绩80分以下的所有同学

```sh
192.168.54.133:6379> zadd stus 85 Jack 89 Lucy 82 Rose 95 Tom 78 Jerry 92 Amy 76 Miles
(integer) 7

192.168.54.133:6379> zrem stus Tom
(integer) 1

192.168.54.133:6379> zrank stus Rose
(integer) 2

192.168.54.133:6379> zrevrank stus Rose
(integer) 3

192.168.54.133:6379> zcard stus
(integer) 6

192.168.54.133:6379> zcount stus 0 80
(integer) 2

192.168.54.133:6379> zincrby stus 2 Amy
"94"

192.168.54.133:6379> zrange stus 0 2
1) "Miles"
2) "Jerry"
3) "Rose"

192.168.54.133:6379> zrevrange stus 0 2
1) "Amy"
2) "Lucy"
3) "Jack"

192.168.54.133:6379> zrangebyscore stus 0 80
1) "Miles"
2) "Jerry"
```



# 3.Redis的Java客户端

在Redis官网中提供了各种语言的客户端，地址：https://redis.io/docs/clients/

![img](https://img-blog.csdnimg.cn/2fa5f8ba97d74e40bfafeb7781bc6c5e.png)



![img](https://img-blog.csdnimg.cn/d2ae7dc7da6041e292e414f1c8642fc7.png)

其中Java客户端也包含很多：

![img](https://img-blog.csdnimg.cn/0f0f6e527e524b7991ea262f188215bd.png)



> 标记为❤的就是推荐使用的java客户端，包括：
>
> - Jedis和Lettuce：这两个主要是提供了Redis命令对应的API，方便我们操作Redis，而SpringDataRedis又对这两种做了抽象和封装，因此我们后期会直接以SpringDataRedis来学习
> - Redisson：是在Redis基础上实现了分布式的可伸缩的java数据结构，例如Map、Queue等，而且支持跨进程的同步机制：Lock、Semaphore等待，比较适合用来实现特殊的功能需求

## 3.1.Jedis客户端

Jedis的官网地址： https://github.com/redis/jedis

### 3.1.1 Jedis入门案例

**入门案例详细步骤**

案例分析：

0）创建工程：

1）引入依赖：

```xml
<!--jedis-->
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>3.7.0</version>
</dependency>
<!--单元测试-->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.7.0</version>
    <scope>test</scope>
</dependency>
```

2）建立连接

新建一个单元测试类，内容如下：

```java
private Jedis jedis;

@BeforeEach
void setUp() {
    // 1.建立连接
    // jedis = new Jedis("192.168.150.101", 6379);
    jedis = JedisConnectionFactory.getJedis();
    // 2.设置密码
    jedis.auth("123321");
    // 3.选择库
    jedis.select(0);
}
```

3）测试：

```java
@Test
void testString() {
    // 存入数据
    String result = jedis.set("name", "虎哥");
    System.out.println("result = " + result);  // result = OK
    // 获取数据
    String name = jedis.get("name");
    System.out.println("name = " + name); // name = 虎哥
}

@Test
void testHash() {
    // 插入hash数据
    jedis.hset("user:1", "name", "Jack");
    jedis.hset("user:1", "age", "21");

    // 获取
    Map<String, String> map = jedis.hgetAll("user:1");
    System.out.println(map);   // {name=Jack, age=21}
}
```

4）释放资源

```java
@AfterEach
void tearDown() {
    if (jedis != null) {
        jedis.close();
    }
}
```

> Jedis使用的基本步骤:
>            1．引入依赖
>            2．创建Jedis对象，建立连接
>            3．使用Jedis，方法名与Redis命令一致
>            4．释放资源



### 3.1.2.连接池

**Jedis本身是线程不安全的**，并且频繁的创建和销毁连接会有性能损耗，因此我们推荐大家使用Jedis连接池代替Jedis的直连方式

有关池化思想，并不仅仅是这里会使用，很多地方都有，比如说数据库连接池，比如tomcat中的线程池，这些都是池化思想的体现

```java
package com.heima.jedis.util;

import redis.clients.jedis.*;

public class JedisConnectionFactory {

    private static JedisPool jedisPool;

    static {
        // 配置连接池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(8);
        poolConfig.setMaxIdle(8);
        poolConfig.setMinIdle(0);
        poolConfig.setMaxWaitMillis(1000);
        // 创建连接池对象，参数：连接池配置、服务端ip、服务端端口、超时时间、密码
        jedisPool = new JedisPool(poolConfig, "192.168.150.101", 6379, 1000, "123321");
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
```

**代码说明：**

- 1） JedisConnectionFacotry：工厂设计模式是实际开发中非常常用的一种设计模式，我们可以使用工厂，去降低代的耦合，比如Spring中的Bean的创建，就用到了工厂设计模式

- 2）静态代码块：随着类的加载而加载，确保只能执行一次，我们在加载当前工厂类的时候，就可以执行static的操作完成对 连接池的初始化

- 3）最后提供返回连接池中连接的方法

**改造原始代码\代码说明:**

1.在我们完成了使用工厂设计模式来完成代码的编写之后，我们在获得连接时，就可以通过工厂来获得。而不用直接去new对象，降低耦合，并且使用的还是连接池对象

2.当我们使用了连接池后，当我们关闭连接其实并不是关闭，而是将Jedis还回连接池的

```java
    @BeforeEach
    void setUp(){
        //建立连接
        /*jedis = new Jedis("127.0.0.1",6379);*/
        jedis = JedisConnectionFacotry.getJedis();
         //选择库
        jedis.select(0);
    }

   @AfterEach
    void tearDown() {
        if (jedis != null) {
            jedis.close();   // 并非关闭，而是将连接归还到连接池
        }
    }
```



## 3.2.SprngDataRedis客户端

SpringData是Spring中数据操作的模块，包含对各种数据库的集成，其中对Redis的集成模块就叫做SpringDataRedis，官网地址：https://spring.io/projects/spring-data-redis

- 提供了对不同Redis客户端的整合（Lettuce和Jedis）
- 提供了RedisTemplate统一API来操作Redis
- 支持Redis的发布订阅模型
- 支持Redis哨兵和Redis集群
- 支持基于Lettuce的响应式编程
- 支持基于JDK、JSON、字符串、Spring对象的数据序列化及反序列化
- 支持基于Redis的JDKCollection实现

SpringDataRedis中提供了RedisTemplate工具类，其中封装了各种对Redis的操作。并且将不同数据类型的操作API封装到了不同的类型中：

![img](https://img-blog.csdnimg.cn/e2f7feec9c414fe2a3e253a181607e2f.png)



### 3.2.1.快速入门

SpringBoot已经提供了对SpringDataRedis的支持，使用非常简单

首先，新建一个maven项目，然后按照下面步骤执行：

#### 1）引入依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.7</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.heima</groupId>
    <artifactId>redis-demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>redis-demo</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>1.8</java.version>
    </properties>
    <dependencies>
        <!--redis依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <!--common-pool-->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>
        <!--Jackson依赖-->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
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
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

#### 2）配置Redis

```yaml
spring:
  redis:
    host: 192.168.150.101
    port: 6379
    password: 123321
    lettuce:
      pool:
        max-active: 8  #最大连接
        max-idle: 8   #最大空闲连接
        min-idle: 0   #最小空闲连接
        max-wait: 100ms #连接等待时间
```

#### 3）注入RedisTemplate

因为有了SpringBoot的自动装配，我们可以拿来就用：

```java
@SpringBootTest
class RedisStringTests {

    @Autowired
    private RedisTemplate redisTemplate;
}
```

#### 4）编写测试

```java
@SpringBootTest
class RedisStringTests {

    @Autowired
    private RedisTemplate edisTemplate;

    @Test
    void testString() {
        // 写入一条String数据
        redisTemplate.opsForValue().set("name", "虎哥");
        // 获取string数据
        Object name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }
}
```

**贴心小提示：SpringDataJpa使用起来非常简单，记住如下几个步骤即可**

SpringDataRedis的使用步骤：

* 引入spring-boot-starter-data-redis依赖
* 在application.yml配置Redis信息
* 注入RedisTemplate



### 3.2.2.数据序列化器

RedisTemplate可以接收任意Object作为值写入Redis：

![img](https://img-blog.csdnimg.cn/e41fe0c74074487c80dd7bd65d92c5a2.png)



只不过写入前会把Object序列化为字节形式，默认是采用JDK序列化，得到的结果是这样的：

![img](https://img-blog.csdnimg.cn/6fad751ec6444cf1b0f8fbc58af19e9f.png)



缺点：

- 可读性差
- 内存占用较大

我们可以自定义RedisTemplate的序列化方式，代码如下：

```java
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory){
        // 创建RedisTemplate对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 设置连接工厂
        template.setConnectionFactory(connectionFactory);
        // 创建JSON序列化工具
        GenericJackson2JsonRedisSerializer jsonRedisSerializer = 
            							new GenericJackson2JsonRedisSerializer();
        // 设置Key的序列化
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());
        // 设置Value的序列化
        template.setValueSerializer(jsonRedisSerializer);
        template.setHashValueSerializer(jsonRedisSerializer);
        // 返回
        return template;
    }
}
```



这里采用了JSON序列化来代替默认的JDK序列化方式。最终结果如图：

![img](https://img-blog.csdnimg.cn/6fc8a779dc5849318d6cead105437d2d.png)

整体可读性有了很大提升，并且能将Java对象自动的序列化为JSON字符串，并且查询时能自动把JSON反序列化为Java对象。不过，其中记录了序列化时对应的class名称，目的是为了查询时实现自动反序列化。这会带来额外的内存开销。

### 3.2.3.StringRedisTemplate

为了在反序列化时知道对象的类型，JSON序列化器会将类的class类型写入json结果中，存入Redis，会带来额外的内存开销。

为了减少内存的消耗，我们可以采用手动序列化的方式，换句话说，就是不借助默认的序列化器，而是我们自己来控制序列化的动作，同时，我们只采用String的序列化器，这样，在存储value时，我们就不需要在内存中就不用多存储数据，从而节约我们的内存空间

为了节省内存空间，我们可以不使用JSON序列化器来处理value，而是统一使用String序列化器，要求只能存储String类型的key和value。当需要存储Java对象时，手动完成对象的序列化和反序列化。

![img](https://img-blog.csdnimg.cn/ec7594c6ceb643ff81f9c47f94a3d8c5.png)

因为存入和读取时的序列化及反序列化都是我们自己实现的，SpringDataRedis就不会将class信息写入Redis了。



这种用法比较普遍，因此SpringDataRedis就提供了RedisTemplate的子类：StringRedisTemplate，它的key和value的序列化方式默认就是String方式。

![img](https://img-blog.csdnimg.cn/b2eef57c356647efaf00e635fe1cbdf6.png)



省去了我们自定义RedisTemplate的序列化方式的步骤，而是直接使用：

```java
@Autowired
private StringRedisTemplate stringRedisTemplate;
// JSON序列化工具
private static final ObjectMapper mapper = new ObjectMapper();

@Test
void testSaveUser() throws JsonProcessingException {
    // 创建对象
    User user = new User("虎哥", 21);
    // 手动序列化
    String json = mapper.writeValueAsString(user);
    // 写入数据
    stringRedisTemplate.opsForValue().set("user:200", json);

    // 获取数据
    String jsonUser = stringRedisTemplate.opsForValue().get("user:200");
    // 手动反序列化
    User user1 = mapper.readValue(jsonUser, User.class);
    System.out.println("user1 = " + user1);
}

```



此时我们再来看一看存储的数据，小伙伴们就会发现那个class数据已经不在了，节约了我们的空间~

![1653054945211](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202301020216683.png)

最后小总结：

RedisTemplate的两种序列化实践方案：

* 方案一：
  * 自定义RedisTemplate
  * 修改RedisTemplate的序列化器为GenericJackson2JsonRedisSerializer

* 方案二：
  * 使用StringRedisTemplate
  * 写入Redis时，手动把对象序列化为JSON
  * 读取Redis时，手动把读取到的JSON反序列化为对象

### 3.2.4 Hash结构操作

在基础篇的最后，咱们对Hash结构操作一下，收一个小尾巴，这个代码咱们就不再解释啦

马上就开始新的篇章~~~进入到我们的Redis实战篇

```java
@SpringBootTest
class RedisStringTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    void testHash() {
        stringRedisTemplate.opsForHash().put("user:400", "name", "虎哥");
        stringRedisTemplate.opsForHash().put("user:400", "age", "21");

        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:400");
        System.out.println("entries = " + entries);
    }
}
```
