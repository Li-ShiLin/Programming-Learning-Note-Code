<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [1.压力测试](#1%E5%8E%8B%E5%8A%9B%E6%B5%8B%E8%AF%95)
  - [1.1 压力测试简介](#11-%E5%8E%8B%E5%8A%9B%E6%B5%8B%E8%AF%95%E7%AE%80%E4%BB%8B)
  - [1.2 性能指标](#12-%E6%80%A7%E8%83%BD%E6%8C%87%E6%A0%87)
  - [1.3 JMeter](#13-jmeter)
      - [1.JMeter安装](#1jmeter%E5%AE%89%E8%A3%85)
      - [2、JMeter压测示例](#2jmeter%E5%8E%8B%E6%B5%8B%E7%A4%BA%E4%BE%8B)
      - [3.JMeter Address Already in use错误解决](#3jmeter-address-already-in-use%E9%94%99%E8%AF%AF%E8%A7%A3%E5%86%B3)
      - [4.影响性能的因素](#4%E5%BD%B1%E5%93%8D%E6%80%A7%E8%83%BD%E7%9A%84%E5%9B%A0%E7%B4%A0)
- [2.性能监控](#2%E6%80%A7%E8%83%BD%E7%9B%91%E6%8E%A7)
  - [2.1 jvm内存模型](#21-jvm%E5%86%85%E5%AD%98%E6%A8%A1%E5%9E%8B)
  - [2.2 堆](#22-%E5%A0%86)
  - [2.3 jconsole 与 jvisualvm](#23-jconsole-%E4%B8%8E-jvisualvm)
  - [2.4 监控指标](#24-%E7%9B%91%E6%8E%A7%E6%8C%87%E6%A0%87)
      - [1.中间件指标](#1%E4%B8%AD%E9%97%B4%E4%BB%B6%E6%8C%87%E6%A0%87)
      - [2.数据库指标](#2%E6%95%B0%E6%8D%AE%E5%BA%93%E6%8C%87%E6%A0%87)
  - [2.5 性能优化一：nginx动静分离](#25-%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E4%B8%80nginx%E5%8A%A8%E9%9D%99%E5%88%86%E7%A6%BB)
  - [2.6 性能优化二：优化三级分类数据获取](#26-%E6%80%A7%E8%83%BD%E4%BC%98%E5%8C%96%E4%BA%8C%E4%BC%98%E5%8C%96%E4%B8%89%E7%BA%A7%E5%88%86%E7%B1%BB%E6%95%B0%E6%8D%AE%E8%8E%B7%E5%8F%96)
- [3. 缓存与分布式锁](#3-%E7%BC%93%E5%AD%98%E4%B8%8E%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81)
  - [3.1 缓存使用](#31-%E7%BC%93%E5%AD%98%E4%BD%BF%E7%94%A8)
  - [3.2 整合redis](#32-%E6%95%B4%E5%90%88redis)
  - [3.3 使用redis缓存优化三级分类数据获取](#33-%E4%BD%BF%E7%94%A8redis%E7%BC%93%E5%AD%98%E4%BC%98%E5%8C%96%E4%B8%89%E7%BA%A7%E5%88%86%E7%B1%BB%E6%95%B0%E6%8D%AE%E8%8E%B7%E5%8F%96)
  - [3.4 高并发下缓存失效问题](#34-%E9%AB%98%E5%B9%B6%E5%8F%91%E4%B8%8B%E7%BC%93%E5%AD%98%E5%A4%B1%E6%95%88%E9%97%AE%E9%A2%98)
      - [1.缓存穿透](#1%E7%BC%93%E5%AD%98%E7%A9%BF%E9%80%8F)
      - [2.缓存雪崩](#2%E7%BC%93%E5%AD%98%E9%9B%AA%E5%B4%A9)
      - [3. 缓存击穿](#3-%E7%BC%93%E5%AD%98%E5%87%BB%E7%A9%BF)
      - [4.总结：](#4%E6%80%BB%E7%BB%93)
  - [3.5 加锁解决缓存击穿问题-采用本地锁](#35-%E5%8A%A0%E9%94%81%E8%A7%A3%E5%86%B3%E7%BC%93%E5%AD%98%E5%87%BB%E7%A9%BF%E9%97%AE%E9%A2%98-%E9%87%87%E7%94%A8%E6%9C%AC%E5%9C%B0%E9%94%81)
      - [1.思路 & 实现](#1%E6%80%9D%E8%B7%AF--%E5%AE%9E%E7%8E%B0)
      - [2.锁-时序问题](#2%E9%94%81-%E6%97%B6%E5%BA%8F%E9%97%AE%E9%A2%98)
      - [3.本地锁存在的问题](#3%E6%9C%AC%E5%9C%B0%E9%94%81%E5%AD%98%E5%9C%A8%E7%9A%84%E9%97%AE%E9%A2%98)
  - [3.6 加锁解决缓存击穿问题-分布式锁](#36-%E5%8A%A0%E9%94%81%E8%A7%A3%E5%86%B3%E7%BC%93%E5%AD%98%E5%87%BB%E7%A9%BF%E9%97%AE%E9%A2%98-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81)
      - [3.6.1 分布式锁原理](#361-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E5%8E%9F%E7%90%86)
      - [3.6.2 set命令演示](#362-set%E5%91%BD%E4%BB%A4%E6%BC%94%E7%A4%BA)
      - [3.6.3 分布式锁解决缓存击穿最终实现](#363-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E8%A7%A3%E5%86%B3%E7%BC%93%E5%AD%98%E5%87%BB%E7%A9%BF%E6%9C%80%E7%BB%88%E5%AE%9E%E7%8E%B0)
  - [3.7  分布式锁演进（逐步推导3.6.3中的最终实现）](#37--%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E6%BC%94%E8%BF%9B%E9%80%90%E6%AD%A5%E6%8E%A8%E5%AF%BC363%E4%B8%AD%E7%9A%84%E6%9C%80%E7%BB%88%E5%AE%9E%E7%8E%B0)
      - [1.方案一](#1%E6%96%B9%E6%A1%88%E4%B8%80)
      - [2.方案二](#2%E6%96%B9%E6%A1%88%E4%BA%8C)
      - [3.方案三](#3%E6%96%B9%E6%A1%88%E4%B8%89)
      - [4. 方案四](#4-%E6%96%B9%E6%A1%88%E5%9B%9B)
      - [5.方案五(最终实现)](#5%E6%96%B9%E6%A1%88%E4%BA%94%E6%9C%80%E7%BB%88%E5%AE%9E%E7%8E%B0)
- [4.redission分布式锁](#4redission%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81)
  - [4.1 redission概述 & 依赖 & 配置](#41-redission%E6%A6%82%E8%BF%B0--%E4%BE%9D%E8%B5%96--%E9%85%8D%E7%BD%AE)
  - [4.2 redission 分布式可重入锁](#42-redission-%E5%88%86%E5%B8%83%E5%BC%8F%E5%8F%AF%E9%87%8D%E5%85%A5%E9%94%81)
  - [4.3 redission看门狗机制](#43-redission%E7%9C%8B%E9%97%A8%E7%8B%97%E6%9C%BA%E5%88%B6)
  - [4.4 redission 分布式读写锁](#44-redission-%E5%88%86%E5%B8%83%E5%BC%8F%E8%AF%BB%E5%86%99%E9%94%81)
  - [4.5 redission 分布式闭锁 CountDownLatch](#45-redission-%E5%88%86%E5%B8%83%E5%BC%8F%E9%97%AD%E9%94%81-countdownlatch)
  - [4.6 redission  分布式信号量(Semaphore)](#46-redission--%E5%88%86%E5%B8%83%E5%BC%8F%E4%BF%A1%E5%8F%B7%E9%87%8Fsemaphore)
  - [4.7 redission解决缓存击穿问题](#47-redission%E8%A7%A3%E5%86%B3%E7%BC%93%E5%AD%98%E5%87%BB%E7%A9%BF%E9%97%AE%E9%A2%98)
- [5.缓存数据一致性](#5%E7%BC%93%E5%AD%98%E6%95%B0%E6%8D%AE%E4%B8%80%E8%87%B4%E6%80%A7)
  - [5.1 方案一：双写模式](#51-%E6%96%B9%E6%A1%88%E4%B8%80%E5%8F%8C%E5%86%99%E6%A8%A1%E5%BC%8F)
  - [5.2 方案二：失效模式](#52-%E6%96%B9%E6%A1%88%E4%BA%8C%E5%A4%B1%E6%95%88%E6%A8%A1%E5%BC%8F)
  - [5.3 缓存数据一致性解决方案选择与优化](#53-%E7%BC%93%E5%AD%98%E6%95%B0%E6%8D%AE%E4%B8%80%E8%87%B4%E6%80%A7%E8%A7%A3%E5%86%B3%E6%96%B9%E6%A1%88%E9%80%89%E6%8B%A9%E4%B8%8E%E4%BC%98%E5%8C%96)
  - [5.4 缓存数据一致性解决：Canal](#54-%E7%BC%93%E5%AD%98%E6%95%B0%E6%8D%AE%E4%B8%80%E8%87%B4%E6%80%A7%E8%A7%A3%E5%86%B3canal)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

##  1.压力测试

###  1.1 压力测试简介

压力测试考察当前软硬件环境下系统所能承受的最大负荷并帮助找出系统瓶颈所在。压测都是为了系统在线上的处理能力和稳定性维持在一个标准范围内，做到心中有数。

使用压力测试,我们有希望找到很多种用其他测试方法更难发现的错误。有两种错误类型是:**内存泄漏,并发与同步**

有效的压力测试系统将应用以下这些关键条件:重复，并发，量级，随机变化

### 1.2 性能指标

- 响应时间（Response Time: RT)
  - 响应时间指用户从客户端发起一个请求开始,到客户端接收到从服务器端返回的响应结束,整个过程所耗费的时间
- HPS （Hits Per Second) :每秒点击次数，单位是次/秒
- TPS （Transaction per Second）:系统每秒处理交易数，单位是笔/秒
- QPS (Query per Second）:系统每秒处理查询次数，单位是次/秒
  - 对于互联网业务中，如果某些业务有且仅有一个请求连接，那么 TPS=QPS=HPS,一般情况下用TPS来衡量整个业务流程，用QPS来衡量接口查询次数，用 HPS来表示对服务器单击请求
- 无论 TPS、QPS、HPS,此指标是衡量系统处理能力非常重要的指标，越大越好，根据经验，一般情况下:
  - 金融行业:1000TPS~50000TPS，不包括互联网化的活动
  - 保险行业:100TPS~100000TPs，不包括互联网化的活动
  - 制造行业:10TPS~5000TPS
  - 互联网电子商务:10000TPS~1000000TPS
  - 互联网中型网站:1000TPS~5000OTPS
  - 互联网小型网站:500TPS~10000TPS
- 最大响应时间（MaxResponse Time）指用户发出请求或者指令到系统做出反应(响应)的最大时间
- 最少响应时间( Mininum ResponseTime）指用户发出请求或者指令到系统做出反应(响应）的最少时间
- 90%响应时间（90% Response Time)是指所有用户的响应时间进行排序，第90%的响应时间
- 从外部看，性能测试主要关注如下三个指标
  - 吞吐量:每秒钟系统能够处理的请求数、任务数
  - 响应时间:服务处理一个请求或一个任务的耗时
  - 错误率:一批请求中结果出错的请求所占比例

###  1.3 JMeter

##### 1.JMeter安装

- `https://jmeter.apache.org/download_jmeter.cgi`
- 下载对应的压缩包，解压运行`jmeter.bat`即可

<table align="center">
<tr>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300251420.png" > <b>下载Jmeter</b></b></td>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300251753.png" > <b>更改语言</b></td>
</tr>
</table>  



##### 2、JMeter压测示例
**1、添加线程组**

<table align="center">
<tr>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300253014.png" > <b>1</b></td>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300254544.png" > <b>2</b></td>
</tr>
</table>  



**2、添加HTTP请求**

<table align="center">
<tr>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300255996.png" > <b>1</b></td>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300256162.png" > <b>2</b></td>
</tr>
</table>  





**3、添加监听器**

依次添加如下几种监听器

![image-20230528150325499](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300257415.png)

**4、启动压测&查看**

![image-20230528150612525](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300257360.png)



#####  3.JMeter Address Already in use错误解决

- 原因：
  - windows本身提供的端口访问机制的问题
  - Windows提供给 TCP/IP链接的端口为1024-5000，并且要四分钟来循环回收他们。就导致我们在短时间内跑大量的请求时将端口占满了
- 解决
  - 1.cmd中，用regedit命令打开注册表
  - 2.在`HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\Tcpip\Parameters`下
    - 1.右击parameters，添加一个新的 DWORD，名字为MaxUserPort
    - 2.然后双击MaxUserPort，输入数值数据为65534，基数选择十进制（如果是分布式运行的话，控制机器和负载机器都需要这样操作哦)
    - 3．修改配置完毕之后记得重启机器才会生效
  - 再添加一个参数 `TCPTimedWaitDelay`并设为30。 `TCPTimedWaitDelay`: 30 
- widdows相关文档： `https://support.microsoft.com/zh-cn/help/196271/when-you-try-to-connect-from-tcp-ports-greater-than-5000-you-receive-t`



<table align="center">
<tr>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300258191.png" > <b>1</b></td>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300258312.png" > <b>2</b></td>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300259168.png" > <b>3</b></td>
</tr>
</table>  



#####  4.影响性能的因素

影响性能考虑点包括:

- 数据库、应用程序、中间件（tomact、Nginx）、网络和操作系统等方面
- 首先考虑自己的应用属于CPU密集型还是IO密集型

##  2.性能监控

### 2.1 jvm内存模型

![image-20230528161220388](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300301343.png)



- 程序计数器`Program Counter Register`:
  - 记录的是正在执行的虚拟机字节码指令的地址
  - 此内存区域是唯一一个在JAVA虚拟机规范中没有规定任何OutOfMemoryError的区域
- 虚拟机:VM Stack
  - 描述的是JAVA方法执行的内存模型，每个方法在执行的时候都会创建一个栈帧，用于存储局部变量表,操作数栈，动态链接,方法接口等信息
  - 局部变量表存储了编译期可知的各种基本数据类型、对象引用
  - 线程请求的栈深度不够会报`StackOverflowError`异常
  - 栈动态扩展的容量不够会报 `OutOfMemoryError`异常
  - 虚拟机栈是线程隔离的,即每个线程都有自己独立的虚拟机栈
- 本地方法:Native Stack
  - 本地方法栈类似于虚拟机柱,只不过本地方法栈使用的是本地方法
- 堆:Heap
  - 几乎所有的对象实例都在堆上分配内存（我们的优化通常是优化堆）

![image-20230528162037244](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300302873.png)

###  2.2 堆

所有的对象实例以及数组都要在堆上分配。堆是垃圾收集器管理的主要区域,也被称为`GC堆`。也是我们优化最多考虑的地方

![image-20230528162935429](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300302725.png)

**堆可以细分为:**

- 新生代

  - Eden空间
  - From Survivor 空间
  - To Survivor空间 老年代

- 永久代/元空间(MetaSpace)

  - Java8以前永久代，受jvm管理，java8以后元空间，直接使用物理内存。因此，默认情况下，元空间的大小仅受本地内存限制

  - 从Java8开始，HotSpot,已经完全将永久代(Permanent Generation）移除，取而代之的是一个新的区域一元空间(MetaSpace)

![image-20230528163051901](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300302438.png)

- 垃圾回收
  - 垃圾回收发送在Eden空间、 Survivor 空间、 老年代
  - 内存分配\垃圾回收流程图：

	<table align="center">
	<tr>
		<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300303568.png" > <b>1</b></td>
		<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300303085.png" > <b>2</b></td>
	</tr>
	</table>  

###  2.3 jconsole 与 jvisualvm

 **jconsole与 jvisualvm**

- Jdk的两个小工具jconsole、jvisualvm (升级版的jconsole) 
- 通过命令行启动，可监控本地和远程应用，远程应用需要配置
- `jconsole`和`jvisualvm`使用：由于安装了jdk,所以直接在命令行窗口输入`jconsole`或者`jvisualvm`即可启动`jconsole` 或`jvisualvm`控制台
  - 可能有的版本没有`jvisualvm`，则需要自己安装这个工具

	<table align="center">
	<tr>
		<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300304913.png" > <b>1</b></td>
		<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300305591.png" > <b>2</b></td>
	</tr>
	</table>  



**jvisualvm能干什么**

- 监控内存泄露，跟踪垃圾回收，执行时内存、cpu,分析，线程分析..
- 可以在`jvisualvm`看到线程的状态：运行、休眠、等待、驻留、监视
  - 运行：正在运行的
  - 休眠：sleep
  - 等待：wait
  - 驻留：线程池里面的空闲线程
  - 监视：阻塞的线程，正在等待锁，可能发生了锁的竞争

**安装插件方便查看`gc`**

- `Cmd`后启动`jvisualvm`
- 工具->插件
- 如果在点击`检查最新更新`时报503错误，解决方法如下:
  - 打开网址`https://visualvm.github.io/pluginscenters.html`
  - cmd查看自己的jdk版本，找到对应的
  - 复制下面查询出来的链接。并重新设置上即可

	<table align="center">
	<tr>
		<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300306263.png" > <b>1</b></td>
		<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300306117.png" > <b>2</b></td>
	</tr>
	</table>  



![image-20230528181508485](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300307495.png)

- 下载`Visual GC`插件，通过此插件可以观察到整个垃圾回收的过程

### 2.4 监控指标

#####  1.中间件指标

- 当前正在运行的线程数不能超过设定的最大值。一般情况下系统性能科较好的情况下，线程数最小值设置50和最大值设置200比较合适
- 当前运行的JDBC连接数不能超过设定的最大值。一般情况下系统性能较好的情况下，JDBC最小值设置50和最大值设置200比较合适
- GC频率不能频繁，特别是FULLGC更不能频繁，一般情况下系统性能较好的情况下，JVM最小堆大小和最大堆大小分别设置1024M比较合适

#####  2.数据库指标

- SQL耗时越小越好，一般情况下微秒级别
- 命中率越高越好，一般情况下不能低于95%
- 锁等待次数越低越好，等待时间越短越好

| 压测内容                            | 压测线程数 | 吞吐量[s]         | 90%响应时间 | 99%响应时间 |
| ----------------------------------- | ---------- | ----------------- | ----------- | ----------- |
| Nginx                               | 50         | 2335              | 11          | 944         |
| Gateway                             | 50         | 10367             | 8           | 31          |
| 简单服务                            | 50         | 11341             | 8           | 17          |
| 首页一级菜单渲染                    | 50         | 270(db,thymeleaf) | 267         | 365         |
| 首页渲染（开缓存）                  | 50         | 290               | 251         | 365         |
| 首页渲染(开缓存,优化数据库，关日志) | 50         | 700               | 105         | 183         |
| 三级分类数据获取                    | 50         | 2(db)             | ...         | ...         |
| 三级分类数据获取(优化业务)          | 50         | 111               | 571         | 896         |
| 三级分类数据获取(redis缓存优化)     | 50         | 417               | 153         |             |
| 首页全量数据获取                    | 50         | 7(静态资源)       |             |             |
| Nginx+Gateway                       | 50         |                   |             |             |
| Gateway+简单服务                    | 50         | 3126              | 30          | 125         |
| 全链路                              | 50         | 800               | 88          | 310         |

- 压测规律：中间件越多，性能损失越大，大多都损失在网络交互了


- 业务相关要进行的优化的因素:
  - db（Mysql 优化）
  - 模板的渲染速度（上线后要打开缓存）
  - 静态资源

### 2.5 性能优化一：nginx动静分离

1、以后将所有项目的静态资源都应该放在`nginx`里面

2、规则:   `/static/**`所有请求都由`nginx`直接返回

```html
<table align="center">
<tr>
	<td><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300308026.png"> <b>nginx动静分离</b></td>
	<td><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300310590.png"> <b>Nginx转发效果</b></td>
</tr>
</table>
```

在`/mydata/nginx/html/`目录下新建`static`目录，并将`gulimall-product`商品服务下的`resources\index`文件(静态资源)上传到虚拟机的`/mydata/nginx/html/static`目录下：

```html
<table align="center">
<tr>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300308646.png" > <b>1</b></td>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300309185.png" > <b>2</b></td>
</tr>
</table>  
```

修改`index.html`页面里的路径：

![image-20230529020559891](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300309439.png)

修改`nginx`配置文件`/mydata/nginx/conf/conf.d/gulimall.conf`，添加如下`location`:



![image-20230529015802002](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300310985.png)



测试：重启nginx,重启网关服务、重启商品服务，访问`http://gulimall.com/`后成功看到商品服务的首页

###  2.6 性能优化二：优化三级分类数据获取

优化三级分类数据获取接口：预先查出所有的分类，再将其保存起来，则可以将多次的数据库查询变为一次

`IndexController`类(无改动)：

```java
@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;
    /**
     * 渲染二级、三级分类数据
     * /index/catalog.json
     */
    @ResponseBody
    @GetMapping("/index/catalog.json")
    public Map<String, List<Catelog2Vo>> getCatelogJson() {

        Map<String, List<Catelog2Vo>> catalogJson = categoryService.getCatalogJson();
        return catalogJson;
    }
}
```

`CategoryService`类(无改动)：

```java
public interface CategoryService extends IService<CategoryEntity> {
    Map<String, List<Catelog2Vo>> getCatalogJson();
}
```

`CategoryServiceImpl`类：三级分类数据获取性能优化—将数据库的多次查询变为一次

```java
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;
    // 三级分类数据获取性能优化：将数据库的多次查询变为一次
    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {

        /**
         *  三级分类数据获取性能优化： 1.将数据库的多次查询变为一次
         */
        // 查到所有数据
        List<CategoryEntity> selectList = baseMapper.selectList(null);


        // 1.查出所有分类1级分类
        List<CategoryEntity> level1Categorys = getParentCid(selectList, 0L);


        // 2.封装数据
        Map<String, List<Catelog2Vo>> parent_cid = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            // 每一个的一级分类，查到这个一级分类的二级分类
            List<CategoryEntity> categoryEntities = getParentCid(selectList, v.getCatId() );

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

    private List<CategoryEntity> getParentCid(List<CategoryEntity> selectList, Long parentCid) {
        List<CategoryEntity> collect = selectList.stream().filter(item -> item.getParentCid() == parentCid).collect(Collectors.toList());
        return collect;
    }

}
```

测试：访问`http://localhost:10001/index/catalog.json`后正确返回json数据，访问`http://gulimall.com/`后可以看到商品服务的首页



## 3. 缓存与分布式锁

###  3.1 缓存使用

为了系统性能的提升，我们一般都会将部分数据放入缓存中，加速访问。而db承担数据落盘工作，即db负责数据的持久化工作

**哪些数据适合放入缓存?**

- 即时性、数据一致性要求不高的
- 访问量大且更新频率不高的数据(读多，写少)
- 举例:
  - 电商类应用，商品分类，商品列表等适合缓存并加一个失效时间(根据数据更新频率来定)
  - 后台如果发布一个商品，买家需要5分钟才能看到新的商品-般还是可以接受的

![image-20230529213625075](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300314512.png)

- 本地缓存模式在分布式下存在的问题：
  - 本地缓存相互之间不可见，容易出现数据的一致性问题
- 分布式场景下应该使用`分布式缓存`：
  - 将缓存的数据集中放到一个地方，当数据修改更新后，其它服务仍然可见，保证了数据的可见性

   <table align="center">
	<tr>
		<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300315960.png" > <b>1</b></td>
		<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300316590.png" > <b>2</b></td>
	</tr>
	</table>  

### 3.2 整合redis

前面已经通过docker在虚拟机上安装了redis，先启动redis ,` sudo docker update redis --restart=always`  、`sudo docker restart redis`，接下来在项目中整合redis



1、在`gulimall-product`商品服务的`pom.xml`中引入`redis`的依赖：

```xml
        <!--  引入redis  -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
```

2、在`application.yml`中配置：

```yaml
spring:
  redis:
    host: 192.168.56.10
    port: 6379
```

3、测试：使用`StringRedisTemplate`操作`String`类型的数据

```java
import java.util.UUID;
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class DataBaseCrudTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testStringRedisTemplate() {
        // redis 操作字符串类型
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

        // 保存
        ops.set("redis_key", "word" + UUID.randomUUID());

        // 查询
        String value = ops.get("redis_key");
        System.out.println("之前保存的数据是" + value);

    }

}
```

```sh
6、整合redis
        1）、引入data-redis-starter
        2）、简单配置redis的host等信息
        3）、使用SpringBoot自动配置好的StringRedisTemplate来操作redis
              redis-》Map；存放数据key，数据值value

```

###  3.3 使用redis缓存优化三级分类数据获取

`IndexController`类(未改动)：

```java
@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;
    /**
     * 渲染二级、三级分类数据
     * /index/catalog.json
     */
    @ResponseBody
    @GetMapping("/index/catalog.json")
    public Map<String, List<Catelog2Vo>> getCatelogJson() {

        Map<String, List<Catelog2Vo>> catalogJson = categoryService.getCatalogJson();
        return catalogJson;
    }
}
```

` CategoryService`类(未改动)：

```java
public interface CategoryService extends IService<CategoryEntity> {
    Map<String, List<Catelog2Vo>> getCatalogJson();
}
```

`CategoryServiceImpl`类：使用redis缓存优化三级分类数据获取

```java
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 三级分类数据获取性能优化一：使用redis缓存优化三级分类数据获取
     */
    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        // 给缓存中放json字符串，拿出的json字符串， 还要逆转为能用的对象类型; [序列化与反序列化]

        // 1、加入缓存逻辑
        String catalogJSON = stringRedisTemplate.opsForValue().get("catalogJSON");
        if (StringUtils.isEmpty(catalogJSON)) {
            // 2、如果缓存中不存在，则到数据库中查询
            // 缓存中数据都要存成JSON字符串。存成JSON的好处：JSON格式的数据是跨平台、跨语言兼容的
            Map<String, List<Catelog2Vo>> catalogJsonFromDb = getCatalogJsonFromDb();


            // 3、将查到的数据放到缓存，将对象转为JSON后存到redis缓存中
            String jsonString = JSON.toJSONString(catalogJsonFromDb);
            stringRedisTemplate.opsForValue().set("catalogJSON", jsonString);

            // 返回数据
            return catalogJsonFromDb;

        }

        // 将redis中取出来的json数据转成我们指定的对象
        Map<String, List<Catelog2Vo>> result = JSON.parseObject(catalogJSON, new TypeReference<Map<String, List<Catelog2Vo>>>() {
        });

        return result;
    }


    // 从数据库查询并封装分类数据
    public Map<String, List<Catelog2Vo>> getCatalogJsonFromDb() {

        /**
         *  三级分类数据获取性能优化： 1.将数据库的多次查询变为一次
         */
        // 查到所有数据
        List<CategoryEntity> selectList = baseMapper.selectList(null);


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

    private List<CategoryEntity> getParentCid(List<CategoryEntity> selectList, Long parentCid) {
        List<CategoryEntity> collect = selectList.stream().filter(item -> item.getParentCid() == parentCid).collect(Collectors.toList());
        return collect;
    }

}
```

**在压力测试过程中出现内存泄露问题**：

-  `Redis exception; nested exception is io.lettuce.core.RedisException: io.ntty.utilinternal.OutOfDirectMemoryError: failed to allocate 46137344 byte(s) of direct memory (used:`

**内存泄露原因**：

- springboot2.0以后默认使用lettuce作为操作redis的客户端。它使用netty进行网络通信
- lettuce的bug导致netty堆外内存溢出 由于我们把JVM内存设置为-Xmx300m；netty如果没有指定堆外内存，默认使用-Xmx300m作为堆外内存

**解决方案有两种**：

- 方案一：升级lettuce客户端
- 方案二：切换使用jedis （此处采用切换jedis的解决方法）
-  lettuce和jedis区别介绍 ：lettuce和jedis都是操作redis的底层客户端。Spring再次封装lettuce和jedis后得到redisTemplate

**切换使用jedis内存泄露问题**：排除掉`lettuce`依赖，引入`jedis`依赖

```xml
        <!--        引入redis-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>io.lettuce</groupId>
                    <artifactId>lettuce-core</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <!--jedis依赖-->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
        </dependency>
```

**总结**：

```java
一： redis缓存中数据都要存成JSON字符串
        1）存成JSON的好处：JSON格式的数据是跨平台、跨语言兼容的
        2）给缓存中放json字符串，拿出的json字符串， 还要逆转为能用的对象类型; [序列化与反序列化]
        3）将查到的数据放到缓存，将对象转为JSON后存到redis缓存中
                Map<String, List<Catelog2Vo>> catalogJsonFromDb = getCatalogJsonFromDb();
                String jsonString = JSON.toJSONString(catalogJsonFromDb);
                stringRedisTemplate.opsForValue().set("catalogJSON", jsonString);
        4）从redis中取出来的json数据要转成我们指定的对象
                   Map<String, List<Catelog2Vo>> result = JSON.parseObject(catalogJSON, new TypeReference<Map<String, List<Catelog2Vo>>>() {});

二、产生堆外内存溢出：OutOfDirectMemoryError
        1）、springboot2.0以后默认使用lettuce作为操作redis的客户端。它使用netty进行网络通信。
        2）、lettuce的bug导致netty堆外内存溢出 由于JVM设置了内存-Xmx300m；netty如果没有指定堆外内存，默认使用-Xmx300m作为堆外内存
      可以通过-Dio.netty.maxDirectMemory进行设置
三、解决方案：不能只使用-Dio.netty.maxDirectMemory去调大堆外内存。
        1）、升级lettuce客户端
        2）、切换使用jedis
四、redisTemplate的底层实现：
          lettuce和jedis都是操作redis的底层客户端。
          Spring再次封装lettuce和jedis后得到redisTemplate；
```

###  3.4 高并发下缓存失效问题

#####   1.缓存穿透

- 缓存穿透：
  - 指查询一个一定不存在的数据，由于缓存是不命中，将去查询数据库，但是数据库也无此记录，我们没有将这次查询的null写入缓存，这将导致这个不存在的数据每次请求都要到存储层去查询，失去了缓存的意义
- 风险:
  - 利用不存在的数据进行攻击，数据库瞬时压力增大，最终导致崩溃

- 解决 :
  - null结果缓存，并加入短暂过期时间

#####  2.缓存雪崩

- 缓存雪崩
  - 缓存雪崩是指在我们设置缓存时key采用了相同的过期时间，导致缓存在某一时刻同时失效，请求全部转发到DB，DB瞬时压力过重雪崩

- 解决:
  - 原有的失效时间基础上增加一个随机值，比如1-5分钟随机，这样每-个缓存的过期时间的重复率就会降低，就很难引发集体失效的事件

#####  3. 缓存击穿

- 缓存击穿

  - 对于一些设置了过期时间的key,如果这些key可能会在某些时间点被超高并发地访问，是一种非常"热点"的数据。

  - 如果这个key在大量请求同时进来前正好失效，那么所有对这个key的数据查询都落到db,我们称为缓存击穿

- 解决: 

  -  加锁

  - 大量并发只让一个去查，其他人等待，查到以后释放锁，其他人获取到锁，先查缓存,就会有数据，不用去db

#####  4.总结：

 1、解决缓存穿透: 空结果缓存
 2、解决缓存雪崩: 设置过期时间（加随机值）
 3、解决缓存击穿: 加锁

###  3.5 加锁解决缓存击穿问题-采用本地锁

#####   1.思路 & 实现

- 加锁解决缓存击穿问题：大量并发只让一个去查，其他人等待，查到以后释放锁，其他人获取到锁，先查缓存,就会有数据，不用去db
- 只要是同一把锁，就能锁住需要这个锁的所有线程。synchronized (this)：SpringBoot所有的组件在容器中都是单例的
- 在单体应用中，利用本地锁：synchronized，JUC（Lock）即可锁住。在分布式情况下，想要锁住所有，必须使用分布式锁

`IndexController`类：

```java
@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;
    /**
     * 渲染二级、三级分类数据
     * /index/catalog.json
     */
    @ResponseBody
    @GetMapping("/index/catalog.json")
    public Map<String, List<Catelog2Vo>> getCatelogJson() {

        Map<String, List<Catelog2Vo>> catalogJson = categoryService.getCatalogJson();
        return catalogJson;
    }

}
```

`CategoryService`类：

```java
public interface CategoryService extends IService<CategoryEntity> {
    Map<String, List<Catelog2Vo>> getCatalogJson();
}
```

`CategoryServiceImpl`实现类：

```java
@Slf4j
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 三级分类数据获取性能优化二：加锁解决缓存击穿问题
     */
    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        // 给缓存中放json字符串，拿出的json字符串， 还用逆转为能用的对象类型; [序列化与反序列化]

        // 1、加入缓存逻辑
        String catalogJSON = stringRedisTemplate.opsForValue().get("catalogJSON");
        if (StringUtils.isEmpty(catalogJSON)) {
            log.info("缓存不命中...将要查询数据库");
            // 2、如果缓存中不存在，则到数据库中查询
            // 缓存中数据都要存成JSON字符串。存成JSON的好处：JSON格式的数据是跨平台、跨语言兼容的
            Map<String, List<Catelog2Vo>> catalogJsonFromDb = getCatalogJsonFromDb();

            // 返回数据
            return catalogJsonFromDb;

        }
        log.info("缓存命中，直接返回数据");
        // 将redis中取出来的json数据转成我们指定的对象
        Map<String, List<Catelog2Vo>> result = JSON.parseObject(catalogJSON, new TypeReference<Map<String, List<Catelog2Vo>>>() {
        });

        return result;
    }


    // 从数据库查询并封装分类数据
    public  Map<String, List<Catelog2Vo>> getCatalogJsonFromDb() {


        // 只要是同一把锁、就能锁住需要这个锁的所有线程
        // 因为springboot的所有组件在容器中都是单例的，所以利用此处的this即可实现上锁
        synchronized (this) {
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

    }

    private List<CategoryEntity> getParentCid(List<CategoryEntity> selectList, Long parentCid) {
        List<CategoryEntity> collect = selectList.stream().filter(item -> item.getParentCid() == parentCid).collect(Collectors.toList());
        return collect;
    }


}
```

测试：JMeter压力测试下，缓存中虽然没有键值为`catalogJSON`的缓存，但是当大量请求同时到达时，对数据库的查询也只有一次。所以即便在缓存突然失效时请求量激增也不会出现数据库崩溃的情况

![image-20230601223832611](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101216416.png)





#####   2.锁-时序问题

要注意锁的范围，锁的范围太小可能导致其他问题。假如在把查到的结果放入缓存前就释放了锁，由于把结果放入缓存这个动作也需要消耗一定时间，此时有的线程如果获取了释放的锁且缓存中还没有数据，那就会再去数据库中查数据。所以正确的做法就是要在`结果放入缓存`以后再释放锁

<table align="center">
<tr>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101218916.png" > <b>1</b></td>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101218847.png" > <b>2</b></td>
</tr>
</table>  



##### 3.本地锁存在的问题 

本地锁，只能锁住当前进程。在分布式情况下，每个服务都将会进行一次数据库的查询。在分布式场景下要让所有的服务总共查一次数据库的话，就需要采用分布式锁

![image-20230531213650628](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101219302.png)



模拟分布式场景：启动多个商品服务，进行测试，发现每个服务都查询了一次数据库，说明在分布式场景下本地锁无法锁住各个微服务的进程

<table align="center">
<tr>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101219655.png" > <b>1</b></td>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101220224.png" > <b>2</b></td>
</tr>
</table>  



###  3.6 加锁解决缓存击穿问题-分布式锁

#####   3.6.1 分布式锁原理

- `redis`分布式锁就是利用带了`NX`参数的`set`命令实现的（NX 代表not exist ,不存在的时候才往里面放）不存在才往里面放，存在就set失败

![image-20230602033834327](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101221009.png)



- `redis`官方文档`set`命令：`http://redis.cn/commands/set.html`
- `set`命令：

```sql
SET key value [EX seconds] [PX milliseconds] [NX|XX]
#  EX seconds             -设置键key的过期时间，单位时秒
#  PX milliseconds        -设置键key的过期时间，单位时毫秒
#  NX                     -只有键key不存在的时候才会设置key的值
#  XX                     -只有键key存在的时候才会设置key的值
```

#####  3.6.2 set命令演示

- `set`命令演示：同时开启多个redis客户端，同时利用`docker exec -it redis redis-cli`命令连接`redis`服务器。多个客户端同时向`redis`发送命令`set lock hh NX`，则只有一个客户端能够抢占成功

<table align="center">
<tr>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101222486.png" > <b>1</b></td>
	<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101223741.png" > <b>2</b></td>
</tr>
</table>  



- 多个客户端同时执行占锁命令`set lock hh NX`，只有一个客户端会返回`OK`，其余的都会返回null

![image-20230603014922858](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101224919.png)



![image-20230603051543509](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101224599.png)

#####  3.6.3 分布式锁解决缓存击穿最终实现

要点：

- `获取锁的值进行对比 + 对比成功后删除锁` = `原子操作`：要确保`获取锁的值进行对比`和`对比成功后删除锁`这两个操作是原子操作

- 获取锁以后，执行业务的时间可能会很长，此时锁过期就会导致其他线程抢占锁，所以要在删除锁之前为锁续期。也可以将过期时间设置得长一些，保证业务在过期之前执行完

![image-20230603160705183](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101225718.png)

`IndexController`类：

```java
@Controller
public class IndexController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/", "index.html"})
    public String indexPage(Model model) {
    /**
     * 渲染二级、三级分类数据
     * /index/catalog.json
     */
    @ResponseBody
    @GetMapping("/index/catalog.json")
    public Map<String, List<Catelog2Vo>> getCatelogJson() {

        Map<String, List<Catelog2Vo>> catalogJson = categoryService.getCatalogJson();
        return catalogJson;
    }
}
```

`CategoryService`类：

```java
public interface CategoryService extends IService<CategoryEntity> {
    Map<String, List<Catelog2Vo>> getCatalogJson();
}
```

`CategoryServiceImpl`实现类：

```java
@Slf4j
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 三级分类数据获取性能优化二：加锁解决缓存击穿问题
     */
    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
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
```

###   3.7  分布式锁演进（逐步推导3.6.3中的最终实现）

下面展示几种缓存击穿问题的解决方案，前几个解决方案都存在一定的问题和漏洞，我们一步步的解决这些漏洞，最后实现一个最终的解决方案。在比较这几个方案时要深刻体会线程、锁、以及原子性的重要性

##### 1.方案一

方案二流程图：

![image-20230603060110899](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101226732.png)

核心代码实现：

```java
// 从数据库查询并封装分类数据（使用redis缓存解决缓存击穿问题）
public Map<String, List<Catelog2Vo>> getCatalogJsonFromDbWithRedisLock() {
    // 1、占分布式锁。去redis占坑
    Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent("lock", "hh");
    if (lock) {
        // 加锁成功...执行业务
        Map<String, List<Catelog2Vo>> dateFromDB = getDateFromDB();
        // 执行完业务要把锁释放,将锁删除
        stringRedisTemplate.delete("lock");
        return dateFromDB;
    } else {
        // 加锁失败...重试。
        // 本地锁的synchronized ()自带监听功能，以自旋的方式一直重试，当其他线程释放锁时尝试获取锁
        // 此处redis缓存获取锁，我们自己通过递归调用，继续尝试获取锁（自旋的方式）
        // 为限制重试频率，让线程休眠一段时间
        return getCatalogJsonFromDbWithRedisLock();
    }

}
```

方案一存在的问题:

- `setnx`占好了位,假如业务代码异常或者程序在业务过程中宕机，导致没有执行删除锁的逻辑,就会造成死锁

解决：

- 设置锁的自动过期，即使因为异常导致删除失败，`redis`中的`key`过期之后会自动删除，达到删除\释放锁的目的

#####  2.方案二

方案二流程图：



![image-20230603122604059](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101226493.png)

核心代码实现：

```java
    // 从数据库查询并封装分类数据（使用redis缓存解决缓存击穿问题）
    public Map<String, List<Catelog2Vo>> getCatalogJsonFromDbWithRedisLock() {
        // 1、占分布式锁。去redis占坑
        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent("lock", "hh");
        if (lock) {
            // 加锁成功...执行业务
            // 设置过期时间,防止执行业务代码时出错而导致锁无法释放，锁没有释放就会造成死锁，加上过期时间后即便业务出错，也会在指定时间内释放锁
            stringRedisTemplate.expire("lock", 30, TimeUnit.SECONDS);
            Map<String, List<Catelog2Vo>> dateFromDB = getDateFromDB();
            // 执行完业务要把锁释放,将锁删除
            stringRedisTemplate.delete("lock");
            return dateFromDB;
        } else {
            // 加锁失败...重试。
            // 本地锁的synchronized ()自带监听功能，以自旋的方式一直重试，当其他线程释放锁时尝试获取锁
            // 此处redis缓存获取锁，我们自己通过递归调用，继续尝试获取锁（自旋的方式）
            // 为限制重试频率，让线程休眠一段时间

            return getCatalogJsonFromDbWithRedisLock();
        }

    }
```

方案二存在的问题:

- 使用`setnx`占到锁以后， 由于`占锁`和`设置过期时间`不是同步完成(不是原子操作)，假如占好锁之后宕机，还没来得及设置过期时间，死锁问题还是存在

解决:

- 设置过期时间和占位必须是原子的。redis支持使用`setnx ex`命令
- 利用如下命令保证原子性：加锁的同时设置过期时间

```sql
set lock hh EX 300 NX
```

#####  3.方案三

方案三流程图：

![image-20230603122709951](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101227033.png)

核心代码实现：

```java
    // 从数据库查询并封装分类数据（使用redis缓存解决缓存击穿问题）
    public Map<String, List<Catelog2Vo>> getCatalogJsonFromDbWithRedisLock() {
        // 1、占分布式锁。去redis占坑
        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent("lock", "hh", 30, TimeUnit.SECONDS);
        if (lock) {
            // 加锁成功...执行业务
            // 设置过期时间,防止执行业务代码时出错而导致锁无法释放，锁没有释放就会造成死锁，加上过期时间后即便业务出错，也会在指定时间内释放锁
            // stringRedisTemplate.expire("lock", 30, TimeUnit.SECONDS);
            Map<String, List<Catelog2Vo>> dateFromDB = getDateFromDB();
            // 执行完业务要把锁释放,将锁删除
            stringRedisTemplate.delete("lock");
            return dateFromDB;
        } else {
            // 加锁失败...重试。
            // 本地锁的synchronized ()自带监听功能，以自旋的方式一直重试，当其他线程释放锁时尝试获取锁
            // 此处redis缓存获取锁，我们自己通过递归调用，继续尝试获取锁（自旋的方式）
            // 为限制重试频率，让线程休眠一段时间

            return getCatalogJsonFromDbWithRedisLock();
        }

    }
```

方案三存在的问题：

- 如果业务非常耗时，某个线程获取锁以后开始执行业务代码，但是在业务执行完之前锁就过期了，那其它线程就会获取锁，导致有多个线程同时执行业务代码，从而导致一些错误
- 误删其它线程的如果线程A获取到锁，A在执行业务代码的过程中锁过期了。线程B又在此时成功抢占到锁，线程A执行完业务代码就会把线程B的锁误删

解决:

- 占锁的时候，值指定为uuid,每个线程匹配是自己的锁才删除



#####  4. 方案四

方案四流程图：



![image-20230603153744284](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101227519.png)

核心代码实现：

```java
    // 从数据库查询并封装分类数据（使用redis缓存解决缓存击穿问题）
    public Map<String, List<Catelog2Vo>> getCatalogJsonFromDbWithRedisLock() {
        // 1、占分布式锁。去redis占坑
        String uuid = UUID.randomUUID().toString();
        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent("lock", uuid, 30, TimeUnit.SECONDS);
        if (lock) {
            // 加锁成功...执行业务
            // 设置过期时间,防止执行业务代码时出错而导致锁无法释放，锁没有释放就会造成死锁，加上过期时间后即便业务出错，也会在指定时间内释放锁
            // stringRedisTemplate.expire("lock", 30, TimeUnit.SECONDS);
            Map<String, List<Catelog2Vo>> dateFromDB = getDateFromDB();
            // 执行完业务要把锁释放,将锁删除
            String lockValue = stringRedisTemplate.opsForValue().get("lock");
            if (uuid.equals(lockValue)){
                // 如果是自己的锁才能删除：防止误删其他线程的锁
                stringRedisTemplate.delete("lock");
            }
            return dateFromDB;
        } else {
            // 加锁失败...重试。
            // 本地锁的synchronized ()自带监听功能，以自旋的方式一直重试，当其他线程释放锁时尝试获取锁
            // 此处redis缓存获取锁，我们自己通过递归调用，继续尝试获取锁（自旋的方式）
            // 为限制重试频率，让线程休眠一段时间

            return getCatalogJsonFromDbWithRedisLock();
        }

    }
```

问题：

- 由于`查询redis并判断锁值`这个操作和`删除redis锁`这两个操作并非原子操作，所以可能在查到缓存中的锁是当前进程的锁并准备将其删除时，当前线程的锁过期了，其他线程继续抢占锁，那还是会出现误删的情况

解决:

- 删除锁必须保证原子性。使用redis+Lua脚本完成

#####  5.方案五(最终实现)

方案五流程图：



![image-20230603160705183](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101228113.png)

要点：

- 获取锁的值进行对比 + 对比成功后删除锁 = 原子操作

- 获取锁以后，执行业务的时间可能会很长，此时锁过期就会导致其他线程抢占锁，所以要在删除锁之前为锁续期。也可以将过期时间设置得长一些，保证业务在过期之前执行完

核心代码(完整代码见3.6.3)：

```java
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
```

##  4.redission分布式锁

###  4.1 redission概述 & 依赖 & 配置

- 概述：`redisson`和`jedis`、`lettuce`都是操作`redis`的客户端，`redisson`可以提供更加强大的功能

- 官方描述：
  - Redisson是一个在Redis的基础上实现的Java驻内存数据网格（In-Memory Data Grid）。它不仅提供了一系列的分布式的Java常用对象，还提供了许多分布式服务。其中包括(`BitSet`, `Set`, `Multimap`, `SortedSet`, `Map`, `List`, `Queue`, `BlockingQueue`, `Deque`, `BlockingDeque`, `Semaphore`, `Lock`, `AtomicLong`, `CountDownLatch`, `Publish / Subscribe`, `Bloom filter`, `Remote service`, `Spring cache`, `Executor service`, `Live Object service`, `Scheduler service`) Redisson提供了使用Redis的最简单和最便捷的方法。Redisson的宗旨是促进使用者对Redis的关注分离（Separation of Concern），从而让使用者能够将精力更集中地放在处理业务逻辑上

- redis官方文档—redis分布式锁：`https://redis.io/docs/manual/patterns/distributed-locks/`
- redission官方github地址：`https://github.com/redisson/redisson`
- redission官方文档：`github.com/redisson/redisson/wiki/Table-of-Content`

引入`redisson`的原生依赖：

```xml
        <!-- 以后使用redisson作为所有分布式锁，分布式对象等功能框架-->
        <dependency>
            <groupId>org.redisson</groupId>
            <artifactId>redisson</artifactId>
            <version>3.13.4</version>
        </dependency>
```

配置`redission` :

```java
/**
 * 配置redisson
 * MyRedissonConfig给容器中配置一个RedissonClient实例即可
 */
@Configuration
public class MyRedissonConfig {

    /**
     * 所有对Redisson的使用都是通过RedissonClient对象
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() throws IOException {
        //1、创建配置
        //Redis url should start with redis:// or rediss://
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.56.10:6379");

        //2、根据Config配置创建出RedissonClient示例
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
```

`application.yml`配置：

```yaml
spring:
  redis:
    host: 192.168.56.10
    port: 6379
```

###  4.2 redission 分布式可重入锁

**可重入锁与不可重入锁区分**：

- 讨论前提：
  - 条件一：A方法中调用了B方法   
  - 条件二：A方法和B方法都要获取同一把锁C才能运行
- 可重入锁：
  - 假如A方法获取了锁C,那么其内部的B方法就天然地获取了锁C,也就是B方法可以直接运行,那么锁就是可重入锁
- 不可重入锁         
  - 假如A方法获取了锁C,B方法要执行的话需要A方法释放锁C，那么锁就是不可重入锁
  - 不可重入锁一定会导致死锁

```sh
# 可重入锁:
A方法(){ // A方法获取了锁C
    B方法(){ // A方法获取了锁C,那么B方法天然地获取锁C
    }
}

# 不可重入锁
A方法(){ // A方法获取了锁C
    B方法(){ // A方法获取了锁C,B方法要执行的话需要A方法释放锁C
    }
}

可重入锁与不可重入锁：
     讨论前提：
              条件一：A方法中调用了B方法   
              条件二：A方法和B方法都要获取同一把锁C才能运行
     可重入锁：
              假如A方法获取了锁C,那么其内部的B方法就天然地获取了锁C,也就是B方法可以直接运行,那么锁就是可重入锁
     不可重入锁         
             假如A方法获取了锁C,B方法要执行的话需要A方法释放锁C，那么锁就是不可重入锁
             不可重入锁一定会导致死锁
```

- `redisson`官方文档—分布式锁：`github.com/redisson/redisson/wiki/8.-分布式锁和同步器`
- 基于`Redis`的`Redisson`分布式可重入锁`RLock Java`对象实现了`java.util.concurrent.locks.Lock`接口。同时还提供了异步`（Async）`、反射式`（Reactive）`和`RxJava2`标准的接口
- Redisson的lock锁采用了看门狗机制，实现了过期时间的自动续期、实现了阻塞式等待

**redission可重入锁测试**：

`RedissionTestController`类：

```java
@Controller
public class RedissionTestController {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * redission的强大之处：
     * 1、解决了锁的自动续期：如果业务时间超长，运行期间自动给锁续上新的30秒。不用担心业务时间长，锁自动过期被删掉
     * 2、加锁的业务只要运行完成，就不会给当前锁续期，即使不手动解锁，锁默认在30s以后自动删除。
     */
    @ResponseBody
    @GetMapping("/hello")
    public String redissionLock() {

        // 1、获取一把锁，只要锁的名字一样，就是同一把锁
        RLock lock = redissonClient.getLock("my-lock");

        // 2. 加锁     阻塞式等待：默认加的锁都是30s时间
        lock.lock();  // 阻塞式等待： 一直执行此语句，直到抢占到锁，才执行后面的程序
        try {
            System.out.println("加锁成功，执行业务..." + Thread.currentThread().getId());
            // 模拟业务的超长时间
            Thread.sleep(30000);
        } catch (Exception e) {

        } finally {
            // 3. 解锁
            System.out.println("释放锁" + Thread.currentThread().getId());
            lock.unlock();
        }
        return "hello";
    }
}
```

测试：多个浏览器页面同时访问`http://localhost:10001/hello`，每个线程获取锁以后下一个线程才能抢占到锁并执行业务。锁会自动分配30s的过期时间，如果业务过长的话，redission会对过期时间进行续期，执行完业务锁就会被删除



![image-20230605222208051](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101228695.png)





![image-20230605213545594](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101228649.png)

### 4.3 redission看门狗机制

3.6 节和3.7节中最后对于分布式锁的实现和redission的看门狗机制原理的某些实现思路差不多。下面通过测试研究一下redission看门狗机制中关于过期时间续期的一些细节内容：

` lock.lock(10, TimeUnit.SECONDS);`

```java
@Controller
public class RedissionTestController {

    @Autowired
    private RedissonClient redissonClient;
    
    /**
     * 看门狗原理
     * @return
     */
    @ResponseBody
    @GetMapping("/helloworld")
    public String redissionLockTime() {

        // 1、获取一把锁，只要锁的名字一样，就是同一把锁
        RLock lock = redissonClient.getLock("my-lock");

        // 2. 加锁
        // 阻塞式等待：默认加的锁都是30s时间
        // 阻塞式等待： 一直执行此语句，直到抢占到锁，才执行后面的程序
        lock.lock(10, TimeUnit.SECONDS); // 10秒后自动解锁，自动解锁时间一定大于业务的执行时间
        //  问题：指定了过期时间之后，在锁时间到了以后，不会自动续期
        //  1、如果我们传递了锁的超时时间，就发送给redis执行脚本，进行占锁，默认超时就是我们指定的时间
        //  2、如果我们未指定锁的超时时间，就使用30 * 1000【LockwatchdogTimeout看门狗的默认时间】;
        //  只要占锁成功，就会启动一个定时任务，这个定时任务会重新给锁设置过期时间，新的过期时间就是看门狗的默认过期时间
        //  到三分之一看门狗时间，也就是10s后就会自动续期，  internalLockLeaseTime【看门狗时间】 / 3,10s

        //最佳实践
        //1）、lock.lock(30,TimeUnit.SECONDS);省掉了整个续期操作。手动解锁
        try {
            System.out.println("加锁成功，执行业务..." + Thread.currentThread().getId());
            // 模拟业务的超长时间
            Thread.sleep(30000);
        } catch (Exception e) {

        } finally {
            // 3. 解锁
            System.out.println("释放锁" + Thread.currentThread().getId());
            lock.unlock();
        }
        return "hello";
    }
}
```

测试：访问 `http://localhost:10001/helloworld`，由于过期时间为10秒，而业务执行时间长于10秒，导致程序异常。程序报错`There was an unexpected error (type=Internal Server Error, status=500).attempt to unlock lock, not locked by current thread by node id: 6185840d-5843-4f10-bb63-028c60239df9 thread-id: 78`

原因：代码中通过`lock.lock(10, TimeUnit.SECONDS);` 指定了过期时间。而在redission的看门狗机制实现中，**如果指定了过期时间之后，在锁时间到了以后，不会自动续期**



**看门狗机制中关于过期时间的细节**：

- 如果我们传递了锁的超时时间，就发送给redis执行脚本，进行占锁，默认超时就是我们指定的时间
- 如果我们未指定锁的超时时间，就使用30 * 1000【LockwatchdogTimeout看门狗的默认时间】
  - 只要占锁成功，就会启动一个定时任务，这个定时任务会重新给锁设置过期时间，新的过期时间就是看门狗的默认过期时间
  - 到三分之一看门狗时间`internalLockLeaseTime【看门狗时间】 / 3`，也就是10s后就会自动续期

- 最佳实践
  - `lock.lock(30,TimeUnit.SECONDS);`省掉了整个续期操作。手动解锁

### 4.4 redission 分布式读写锁

加读写锁目的： 保证一定能读到最新数据。修改期间，写锁是一个排他锁（互斥锁、独享锁）。读锁是一个共享锁

`RedissionTestController`类：

```java
@Controller
public class RedissionTestController {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 加读写锁目的： 保证一定能读到最新数据,修改期间，写锁是一个排他锁（互斥锁、独享锁）。读锁是一个共享锁
    // 写锁没释放读就必须等待
    // 读 + 读： 相当于无锁，并发读，只会在redis中记录好，所有当前的读锁。他们都会同时加锁成功
    // 写 + 读： 等待写锁释放，读才能开始
    // 写 + 写： 阻塞方式。上一个写完下一个才能写
    // 读 + 写： 有读锁。写也需要等待。
    // 总结：只要有写的存在，都必须等待
    @GetMapping("/write")
    @ResponseBody
    public String writeValue() {
        RReadWriteLock lock = redissonClient.getReadWriteLock("rw-lock");
        String s = "";
        RLock rLock = lock.writeLock();
        try {
            //1、改数据加写锁，读数据加读锁
            rLock.lock();
            System.out.println("写锁加锁成功..." + Thread.currentThread().getId());
            s = UUID.randomUUID().toString();
            Thread.sleep(30000);
            stringRedisTemplate.opsForValue().set("writeValue", s);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
            System.out.println("写锁释放" + Thread.currentThread().getId());
        }

        return s;
    }

    @GetMapping("/read")
    @ResponseBody
    public String readValue() {
        RReadWriteLock lock = redissonClient.getReadWriteLock("rw-lock");
//        ReentrantReadWriteLock writeLock = new ReentrantReadWriteLock();
        String s = "";
        //加读锁
        RLock rLock = lock.readLock();
        rLock.lock();
        try {
            System.out.println("读锁加锁成功" + Thread.currentThread().getId());
            s = stringRedisTemplate.opsForValue().get("writeValue");
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
            System.out.println("读锁释放" + Thread.currentThread().getId());
        }
        return s;
    }

}
```

测试：先访问`http://localhost:10001/write` ，紧接着访问`http://localhost:10001/read` ，redis中出现`rw-lock` 和`writeValue` 此时还不能读到数据，当写进程释放锁以后，读进程才能读到数据



**读写锁总结**：

- 加读写锁目的： 保证一定能读到最新数据,修改期间，写锁是一个排他锁（互斥锁、独享锁）。读锁是一个共享锁， 写锁没释放读就必须等待
- 读 + 读： 相当于无锁，并发读，只会在redis中记录好，所有当前的读锁。他们都会同时加锁成功
- 写 + 读： 等待写锁释放，读才能开始
- 写 + 写： 阻塞方式。上一个写完下一个才能写
- 读 + 写： 有读锁。写也需要等待
- 总结：只要有写的存在，都必须等待

###   4.5 redission 分布式闭锁 CountDownLatch

`CountDownLatch`用于多线程调度任务中，只有多个线程将子任务都完成，整个任务才算完成

```java
@Controller
public class RedissionTestController {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 闭锁
     * 放假，锁门
     * 1班没人了，2
     * 5个班全部走完，我们可以锁大门
     */
    @GetMapping("/lockDoor")
    @ResponseBody
    public String lockDoor() throws InterruptedException {
        RCountDownLatch door = redissonClient.getCountDownLatch("door");
        door.trySetCount(5); // 等待5个班的人都走完
        door.await(); //等待闭锁都完成

        return "放假了...";
    }

    @GetMapping("/gogogo/{id}")
    @ResponseBody
    public String gogogo(@PathVariable("id") Long id) {
        RCountDownLatch door = redissonClient.getCountDownLatch("door");
        door.countDown();//计数减一；

//        CountDownLatch

        return id + "班的人都走了...";
    }

}
```

测试：访问`http://localhost:10001/lockDoor`,请求不会立即返回数据，只有访问`http://localhost:10001/gogogo/1`并且访问次数达到5次以后，前者才返回数据

###  4.6 redission  分布式信号量(Semaphore)

`RedissionTestController`类：

```java
@Controller
public class RedissionTestController {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 分布式信号量 Semaphore
     * 车库停车，
     * 3车位
     * 信号量也可以用作分布式限流；
     */
    @GetMapping("/park")
    @ResponseBody
    public String park() throws InterruptedException {
        RSemaphore park = redissonClient.getSemaphore("park");
        // 阻塞式等待
        park.acquire();//获取一个信号，获取一个值,占一个车位
        
        //        // 非阻塞式等待
//        boolean b = park.tryAcquire();
//        if (b) {
//            //执行业务
//        } else {
//            return "error";
//        }
//
//        return "ok=>" + b;
        return "ok";
    }

    //  分布式信号量 Semaphore
    @GetMapping("/go")
    @ResponseBody
    public String go() throws InterruptedException {
        RSemaphore park = redissonClient.getSemaphore("park");
        park.release();//释放一个车位

        return "ok";
    }

}
```

测试：往redis中添加键为`park`值为`3`的数据，访问`http://localhost:10001/go`则`park`的值增加，访问`http://localhost:10001/park`则`park`的值增加



分布式信号量(Semaphore)的应用：实现分布式限流

- 假如当前服务只能支持每秒1000的并发请求，为防止过多请求导致服务崩溃，就要对服务进行限流。将信号量总量设为1000。如果线程能够获取信号量，就说明系统存在空余线程，可以处理请求。如果获取失败，就说明没有空闲线程，只有当其他线程的信号量释放以后，这个请求才能被处理
- 分布式限流时也可以采用` boolean b = park.tryAcquire();`实现非阻塞式等待，如果信号量获取失败的话，直接提醒用户当前流量过高，请稍后重试

### 4.7 redission解决缓存击穿问题

redission解决缓存击穿问题: 将3.6.3中的`getCatalogJsonFromDbWithRedisLock`方法改为如下的`getCatalogJsonFromDbWithRedissionLock`方法即可

```java
    /**
     * 从数据库查询并封装分类数据（使用redission缓存解决缓存击穿问题）
     * 缓存数据一致性问题： 缓存里面的数据如何与数据库保持一致
     * 数据一致性的两种模式：
     *     1）、双写模式：
     *     2）、失效模式：
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
```

分布锁的命名与锁的粒度：

```sh
1、锁的粒度越细，程序效率越高
2、锁的名字相同就代表同一把锁，锁的名字尽量具体一些，具体点的名字就可以保证锁的粒度
3、最佳实践：
       缓存某一个具体的数据，如缓存11号商品防止该商品的缓存击穿问题，就应该将锁的名字设为product-11-lock,
       缓存13号商品,就应该将锁的名字设为product-13-lock，而不应该将所有商品的锁笼统地设为product-lock
```

##  5.缓存数据一致性

###  5.1 方案一：双写模式

![image-20230610050136462](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101229271.png)

###  5.2 方案二：失效模式

![image-20230610050201531](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101229265.png)

###   5.3 缓存数据一致性解决方案选择与优化

- 无论是双写模式还是失效模式，都会导致缓存的不一致问题。即多个实例同时更新会出事。怎么办?

  - 1、如果是用户维度数据(订单数据、用户数据)，这种并发几率非常小(用户不可能在短时间内频繁地修改自己的信息)，不用考虑这个问题，缓存数据加上过期时间，每隔一段时间触发读的主动更新即可

  - 2、如果是菜单，商品介绍等基础数据，一般缓存的一致性要求不高，可以容忍一定程度的不一致。如果要保证更好的一致性，也可以去使用canal订阅binlog的方式

  - 3、缓存数据+过期时间也足够解决大部分业务对于缓存的要求

  - 4、通过加锁保证并发读写，写写的时候按顺序排好队。读读无所谓。所以适合使用读写锁。(业务不关心脏数据，允许临时脏数据可忽略)

- 总结:
  - 我们能放入缓存的数据本就不应该是实时性、一致性要求超高的。所以缓存数据的时候加上过期时间，保证每天拿到当前最新数据即可
  - 我们不应该过度设计，增加系统的复杂性
  - 遇到实时性、一致性要求高的数据，就应该查数据库，即使慢点



###  5.4 缓存数据一致性解决：Canal

canal 是阿里开源的一款中间件，canal的主要作用就是实现数据的实时同步。这个中间件可以模拟成一个数据库的从服务器。比如我们有一个MySQL的数据库，canal将自己伪装成一个MySQL的从服务器，MySQL真正的服务器中只要有变化，都会被同步到canal的从服务器上



使用Canal解决缓存数据一致性问题：让canal监控binlog，binlog发生变化canal监听到变化就去更新redis缓存

![image-20230610121236199](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101229901.png)







**拓展知识**： canal在大数据场景下的应用——解决数据异构问题

每个人的京东首页推荐的东西都不同，这些都是基于用户的爱好进行推荐。在数据库中存储特定用户的浏览记录、商品信息表、购物车记录表等信息，让canal订阅这些表的更新，然后实时地进行计算，通过计算生成一个用户推荐表，然后当用户访问京东的时候，直接将这些推荐表中的商品推荐到首页即可

![image-20230610121337790](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202306101229117.png)





