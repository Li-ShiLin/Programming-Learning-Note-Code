## 1.项目简介

谷粒商城: 谷粒商城是个B2C模式的电商平台，销售自营商品给客户

#### 1.1 电商模式

- 市面上有5种常见的电商模式B2B、B2C、C2B、C2C、020
- B2B模式
  - B2B (Business to Business)，是指商家与商家建立的商业关系。如:阿里巴巴
- B2C模式
  - B2C(Business to Consumer)，就是我们经常看到的供应商直接把商品卖给用户，即“商对客"模式，也就是通常说的商业零售，直接面向消费者销售产品和服务。如:苏宁易购、京东、天猫、小米商城

- C2B模式
  - C2B (Customer to Business)，即消费者对企业。先有消费者需求产生而后有企业生产，即先有消费者提出需求，后有生产企业按需求组织生产
- C2C模式
  - C2C (Customer to Consumer)，客户之间自己把东西放上网去卖，如:淘宝，闲鱼
- O2O模式
  - O2O即 online To Offline，也即将线下商务的机会与互联网结合在了一起，让互联网成为线下交易的前台。线上快速支付，线下优质服务。如:饿了么，美团，淘票票，京东到家

#### 1.2 项目技术 & 特色 & 项目架构图

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

​      微服务架构风格，就像是把一个单独的应用程序开发为一套小服务，每个小服务运行在自己的进程中，并使用轻量级机制通信，通常是HTTP API。这些服务围绕业务能力来构建，并通过完全白动化部署机制来独立部署。这些服务使用不同的编程语言书写,以及不同数据存储技术，并保持最低限度的集中式管理

简而言之: 拒绝大型单体应用，基于业务边界进行服务细化坼分，各个服务独立部署运行

#### 2.2 集群&分布式&节点

- 集群是个物理形态，分布式是个工作方式。

- 只要是一堆机器，就可以叫集群，他们是不是一起协作着干活，这个谁也不知道;

- 《分布式系统原理与范型》定义:
  - “分布式系统是若干独立计算机的集合，这些计算机对于用户来说就像单个相关系统”.分布式系统( distributed system）是建立在网络之上的软件系统
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
  - 在运维期间，当系统处于高峰期，系统资源紧张，我们可以让非核心业务降级运行。降级:某些服务不处理,或者简单处理【抛异常、返回NULL、调用Mock数据、调用Fallback 处理逻辑】

#### 2.8 API网关
- 在微服务架构中，APl Gateway作为整体架构的重要组件，它抽象了微服务中都需要的公共功能，同时提供了客户端负载均衡，服务自动熔断，灰度发布，统一认证，限流流控，日志统计等丰富的功能，帮助我们解决很多API管理难题

## 3.项目搭建

#### 3.1 安装linux虚拟机

**一、下载&安装VitualBox （要开启CPU虚拟化）**

![image-20230301234953687](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303012349359.png)

![image-20230302000334135](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303020003314.png)

- VitualBox  官网：`https://www.virtualbox.org/` ,进入下载页面`https://www.virtualbox.org/wiki/Downloads`下载windows版本：

![image-20230301235206242](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303012352590.png)

- 下载后”下一步、下一步“即可

**二、下载&安装Vagrant**

- Vagrant官方镜像仓库:  `https://app.vagrantup.com/boxes/search`
- Vagrant下载: `https://developer.hashicorp.com/vagrant/downloads`
- 安装之后重启电脑，打开window cmd窗口，运行`Vagrant` 命令，出现如下命令则表示安装成功

![image-20230302005405501](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303020054512.png)

- 打开window cmd窗口，运行`vagrant init centos/7`，即可初始化一个centos7系统，并在目录下生成一个vagrantfile文件

  ![image-20230302012203988](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303020123184.png)

  ![image-20230302012318835](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303020123002.png)

- 打开VitualBox，并在window cmd窗口运行`vagrant up`即可启动虚拟机。系统root用户的密码是vagrant，启动成功即可在virtualBox中看到虚拟机：

![image-20230302011722450](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303020118829.png)

- vagrant其他常用命令
  - vagrant ssh 命令：  自动使用vagrant用户连接虚拟机
    - vagrant upload source [destination] [namel id]:上传文件
  - https://www.vagrantup.com/docs/cli/init.html Vagrant命令行

![image-20230302011615703](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303020116934.png)

- 默认虚拟机的ip地址不是固定ip，开发不方便
  - 上面创建的虚拟机的网络默认采用网络地址转换和端口转发的方式，这种方式开发不方便
  - 端口转发时每次在virtualbox上安装一个软件，都要配置端口映射
  - 端口转发示意图：

![image-20230302014525418](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303020149565.png)

- 修改Vagrantfile：

![image-20230302013635011](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303020149884.png)

- 修改完利用`vagrant reload`命令重启虚拟机，使用`vagrant ssh`重新连接，利用`ip addr`命令查看虚拟机ip地址：

![image-20230302015622360](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303020223901.png)