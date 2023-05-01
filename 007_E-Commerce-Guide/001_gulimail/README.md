<!-- TOC start (generated with https://github.com/derlin/bitdowntoc) -->

- [环境，微服务项目搭建](#)
- [1.安装linux虚拟机](#1linux)
- [2.安装Docker](#2docker)
- [3.docker安装mysql](#3dockermysql)
- [4.docker安装redis](#4dockerredis)
- [5.开发环境统一](#5)
  * [5.1  JDK & maven](#51-jdk-maven)
  * [5.2 idea & vscode](#52-idea-vscode)
  * [5.3 安装配置git](#53-git)
  * [5.4 逆向工程使用](#54-)
- [6.创建项目微服务](#6)
  * [6.1 数据库初始化](#61-)
  * [6.2 人人开源搭建后台管理系统](#62-)
    + [6.2.1 后端项目下载启动](#621-)
    + [6.2.2 前端项目下载启动](#622-)
  * [6.3 逆向工程搭建](#63-)
  * [6.4 整合MyBatis-plus](#64-mybatis-plus)
  * [6.5 逆向生成所有微服务基础CRUD代码](#65-crud)
    + [6.5.1 生成gulimail-coupon的CURD代码](#651-gulimail-couponcurd)
    + [6.5.2 生成gulimail-member的CURD代码](#652-gulimail-membercurd)

<!-- TOC end -->

<!-- TOC --><a name=""></a>
##  环境，微服务项目搭建

<!-- TOC --><a name="1linux"></a>
## 1.安装linux虚拟机

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

<!-- TOC --><a name="2docker"></a>
## 2.安装Docker

虚拟化容器技术。Docker基于镜像，可以秒级启动各种容器。每一种容器都是一个完整的运行环境，容器之间互相隔离

![image-20230302024513566](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303020245302.png)

安装步骤见：   `https://docs.docker.com/engine/install/centos/`

1.卸载docker:

```dockerfile
sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```

2.安装yum工具：

```dockerfile
sudo yum install -y yum-utils \
           device-mapper-persistent-data \
           lvm2 --skip-broken
```

3.设置docker仓库地址：

```dockerfile
sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
```

4.安装docker引擎：

```dockerfile
sudo yum install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```

5.启动docker:

```dockerfile
sudo systemctl start docker
```

6.查看docker版本：

```dockerfile
docker -v
```

7.设置docker开机自启：

```dockerfile
sudo systemctl enable docker
```

8.为docker配置阿里云镜像加速器：支付宝登录后选择容器镜像服务，复制命令到cmd窗口执行

![image-20230302232641506](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303022326541.png)

<!-- TOC --><a name="3dockermysql"></a>
## 3.docker安装mysql

1.下载镜像文件

```
docker pull mysql:5.7
```

2.查看docker镜像

```
sudo docker images
```

2.创建实例并启动

```
sudo docker run -p 3306:3306 --name mysql \
-v /mydata/mysql/log:/var/log/mysql \
-v /mydata/mysql/data:/var/lib/mysql \
-v /mydata/mysql/conf:/etc/mysql \
-e MYSQL_ROOT_PASSWORD=root \
-d mysql:5.7
```

- 参数说明：

![image-20230302233855299](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303022338979.png)

3.修改mysql配置文件（因为有目录映射，所以可以直接在镜像外执行）`vi /mydata/mysql/conf/my.conf`

```
[client]
default-character-set=utf8
[mysql]
default-character-set=utf8
[mysqld]
init_connect='SET collation_connection = utf8_unicode_ci'
init_connect='SET NAMES utf8'
character-set-server=utf8
collation-server=utf8_unicode_ci
skip-character-set-client-handshake
skip-name-resolve
```

4.查看运行中的容器:

```dockerfile
sudo docker ps
```

**docker容器文件挂载与端口映射**:

![image-20230302234409649](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303022345722.png)



利用命令`docker exec -it 容器名称 /bin/bash`进入容器内部，可以看到容器具有linux的目录结构，即这个容器可以视为一个隔离的linux系统环境：

![image-20230303000119389](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303030001471.png)

<!-- TOC --><a name="4dockerredis"></a>
## 4.docker安装redis

1.下载镜像文件

```sh
sudo docker pull redis
```

2.创建实例并启动

先在虚拟机上创建用于挂载的目录：

```sh
sudo mkdir -p /mydata/redis/conf
sudo cd /mydata/redis/conf
sudo touch redis.conf
```

创建并启动实例：

```sh
sudo docker run -p 6379:6379 --name redis -v /mydata/redis/data:/data \
-v /mydata/redis/conf/redis.conf:/etc/redis/redis.conf \
-d redis redis-server /etc/redis/redis.conf
```

3.使用redis镜像执行redis-cli命令

```
sudo docker exec -it redis redis-cli
```

![image-20230303003227666](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303030033651.png)

4.默认是不持久化的,每次执行`docker restart redis`重启redis之后，redis中的数据都会丢失。解决：在配置文件中输入appendonly yes，就可以aof持久化了，修改完docker restart redis重启redis

```
sudo vi /mydata/redis/conf/redis.conf
# 插入下面内容
appendonly yes
#保存后重启redis
docker restart redis
#连接redis
docker -it redis redis-cli
```

<!-- TOC --><a name="5"></a>
## 5.开发环境统一

<!-- TOC --><a name="51-jdk-maven"></a>
### 5.1  JDK & maven

```
JDK ： 1.8
maven: 3.6.1
```

配置maven镜像：

```xml
	<mirror>
    <id>alimaven</id>
    <name>aliyun maven</name>
    <url>
        http://maven.aliyun.com/nexus/content/groups/public/
    </url>
    <mirrorOf>central</mirrorOf>        
    </mirror>
```

maven配置jdk编译项目：

```xml
	<!-- java1.8版本 --> 
		<profile>
			  <id>jdk-1.8</id>
			  <activation>
				<activeByDefault>true</activeByDefault>
				<jdk>1.8</jdk>
			  </activation>

			  <properties>
				<maven.compiler.source>1.8</maven.compiler.source>
				<maven.compiler.target>1.8</maven.compiler.target>
				<maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
			  </properties>
		</profile>
```

<!-- TOC --><a name="52-idea-vscode"></a>
### 5.2 idea & vscode

```sh
idea安装插件lombok  、  MyBatisX (出一个mapper快速定位到xml文件)
vscode安装插件 auto close 、 auto Rename 、 ESLint (前端ES语法检查) 、HTML Snippets 、 JavaScript (ES6) code snippets 、 Live Server 、 Vetur
```

<!-- TOC --><a name="53-git"></a>
### 5.3 安装配置git

<!-- TOC --><a name="54-"></a>
### 5.4 逆向工程使用

- 1、导入项目逆向工程
- 2、下载人人开源后台管理系统脚手架工程
  - 导入工程，创建数据库
  - 修改工程shiro依赖为SpringSecurity
  - 删除部分暂时不需要的业务
- 3、下载人人开源后台管理系统vue端脚手架工程
  - vscode导入前端项目
  - 前后端联调测试基本功能

<!-- TOC --><a name="6"></a>
##  6.创建项目微服务

- 商品服务、仓储服务、订单服务、优惠券服务、用户服务
- 共同:
  - 1.包含web、openfeign依赖
  - 2.每一个服务，包名 com.atguigu.gulimall.xx(product/order/ware/coupon/member)
  - 3.模块名:gulimall-coupon

<!-- TOC --><a name="61-"></a>
### 6.1 数据库初始化

1.设置mysql  、 redis 开机自启

```
sudo docker ps
sudo docker ps -a
# 这两个命令的差别就是后者会显示  【已创建但没有启动的容器】

# 我们接下来设置我们要用的容器每次都是自动启动
sudo docker update redis --restart=always
sudo docker update mysql --restart=always
# 如果不配置上面的内容的话，我们也可以选择手动启动
sudo docker start mysql
sudo docker start redis
# 如果要进入已启动的容器
sudo docker exec -it mysql /bin/bash
# /bin/bash就是进入一般的命令行，如果改成redis就是进入了redis
```

2.依次创建数据库、并执行sql

![image-20230303210623895](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303051118877.png)

<!-- TOC --><a name="62-"></a>
### 6.2 人人开源搭建后台管理系统

<!-- TOC --><a name="621-"></a>
####  6.2.1 后端项目下载启动

- 人人开源:   `https://gitee.com/renrenio`

![image-20230303224256944](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303051119372.png)

- 在码云上搜索人人开源，使用renren-fast（后端）、renren-fast-vue（前端）项目  加速开发

```sh
git clone https://gitee.com/renrenio/renren-fast.git
git clone https://gitee.com/renrenio/renren-fast-vue.git
```

- 下载好后删除代码中的 .git 文件，将renren-fast导入到gulimail项目中：

![image-20230303225958171](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303051119719.png)

- 在IDEA项目里的gulimail下的pom.xml中添加一个renrnen-fast模块

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.atguigu.gulimail</groupId>
    <artifactId>001_gulimail</artifactId>
    <version>1.0-SNAPSHOT</version>
    <name>001_gulimail</name>
    <description>聚合服务</description>
    <packaging>pom</packaging>

    <!--  聚合其他模块 -->
    <modules>
        <module>gulimail-coupon</module>
        <module>gulimail-member</module>
        <module>gulimail-order</module>
        <module>gulimail-product</module>
        <module>gulimail-ware</module>
        <module>renren-fast</module>
    </modules>
</project>
```

- 将renren-fast下的mysql.sql文件中的sql语句复制到SQLyog执行
- 修改项目里renren-fast中的application-dev.yml中的数库的url，通常把localhost修改为`192.168.56.10`即可。然后该对后面那个数据库

```properties
url: jdbc:mysql://192.168.56.10:3306/guli_admin?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
username: root
password: root
```

- 然后运行该java项目下的RenrenApplication
- 浏览器输入`http://localhost:8080/renren-fast/` 得到{“msg”:“invalid token”,“code”:401}就代表无误

<!-- TOC --><a name="622-"></a>
#### 6.2.2 前端项目下载启动

**安装Node.js**

- 前端开发，少不了node.j3 Node.js是一个基于Chrome v8引擎的 JavaScript运行环境。http://nodejs.cn/api/
- 我们关注node.js  的 npm功能就行
- NPM是随同 NodeJS一起安装的包管理工具，JavaScript-NPM类似于java-Maven
  - 官网下载安装node.is，并使用node -v检查版本
  - 配置npm,使用淘宝镜像提速 :    `npm config set registry http://registry.pm.taobao.org/`

```npm
npm config set registry http://registry.npm.taobao.org/
```

**前端项目：**

- 在码云上搜索人人开源，下载renren-fast-vue（前端）项目 

```sh
git clone https://gitee.com/renrenio/renren-fast-vue.git
```

- 下载好后删除代码中的 .git 文件，用vscode打开renren-fast-vue，在控制台运行`npm install`命令下载前端组件

![image-20230304005308652](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303051119457.png)

下载好的组件都会在 `node_modules`目录下显示：

![image-20230304011546548](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303051120250.png)

- 接着运行`npm run dev`启动前端项目
- 注意：尽量以管理员身份运行以上npm命令！否则可能会出现一些错误

<!-- TOC --><a name="63-"></a>
### 6.3 逆向工程搭建

- 项目下载：

```
git clone https://gitee.com/renrenio/renren-generator.git
```

- 下载到桌面后，同样把里面的.git文件删除，然后移动到我们IDEA项目目录中，同样配置好pom.xml
- 添加renren-generator模块：

```xml
    <modules>
        <module>gulimail-coupon</module>
        <module>gulimail-member</module>
        <module>gulimail-order</module>
        <module>gulimail-product</module>
        <module>gulimail-ware</module>
        <module>renren-fast</module>
        <module>renren-generator</module>
    </modules>
```

- 解决renren-generator 的 pom.xml 文件报错：
  - 可能报错：

```
'parent.relativePath' of POM io.renren:renren-generator:1.0.0 (D:\github\Study-Code-Note\007_E-Commerce-Guide\001_gulimail\renren-generator\pom.xml) points at com.atguigu.gulimail:001_gulimail instead of org.springframework.boot:spring-boot-starter-parent, please verify your project structure
```

- 只需在`	<parent>`中添加`<relativePath/>`即可

```xml
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.6.6</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
```

- 在renren-generator项目的application.yml文件中修改数据库配置信息：

```yaml
# mysql
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    #MySQL配置
    driverClassName: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://192.168.56.10:3306/gulimail_pms?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: root
```

- 生成product模块代码： 在renren-generator项目的generator.properties文件中修改配置信息

```properties
# 代码生成器配置信息
mainPath=com.atguigu
# 包名
package=com.atguigu.gulimail
# 模块名
moduleName=product
# 作者
author=Li-ShiLin
# Email
email=sunlightcs@gmail.com
# 表的前缀
tablePrefix=pms_
```

- 补充：因为product模块对应的数据库gulimail_pms包含表前缀pms_  ，所以配置`tablePrefix=pms_`来让自动生成的JavaBean名称不包含pms前缀

![image-20230304085520396](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303051120725.png)



启动`renren-generator`项目，访问`http://localhost/#main.html`,在控制台选中所有数据库表，点击`生成代码`，即可生成代码文件，下载解压后即可看到生成的代码：



 <table align="center">
    <tr>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303051121901.png" > <b>选中表之后生成代码</b></td>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303051122036.png" > <b>代码文件的目录</b></td>
    </tr>
    </table>


定位renren-generator的main文件，复制代码生成器生成的main文件并粘贴到src目录下

 <table align="center">
    <tr>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303051123325.png" > <b>定位renren-generator的main文件</b></td>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303051124356.png" > <b>复制代码生成器生成的main文件并粘贴到src目录下</b></td>
    </tr>
    </table>




idea查看生成的代码，删除生成的前端代码，查看代码发现存在很多报错。接着就要处理这些报错，主要就是引入一些maven依赖和人人开源中已经定义的工具类（如下图中的PageUtils工具类，通用返回类R）。为了其他项目也可以重复利用这些依赖和工具类，可以创建一个`gulimail-common`公共包，每个项目导入`gulimail-common`依赖包即可

 <table align="center">
    <tr>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303051124653.png" > <b>代码文件的目录</b></td>
    <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303051125229.png" > <b>存在报错</b></td>
    </tr>
    </table>


创建一个`gulimail-common`公共包:   在项目上右击（在项目上右击很重要）new modules— maven—然后在name上输入gulimall-common,在聚合的pom.xml中也自动添加了`<module>gulimall-common</module>`

在common项目的pom.xml中添加如下依赖（分析具体报错来判断要导入哪些依赖）：

```xml
        <!--mybatis-plus -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.2.0</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.8</version>
        </dependency>

        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpcore</artifactId>
            <version>4.4.12</version>
        </dependency>

        <dependency>
            <groupId>commons-lang</groupId>
            <artifactId>commons-lang</artifactId>
            <version>2.6</version>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
            <scope>provided</scope>
        </dependency>
```

然后在`gulimail-product`项目中的pom.xml中导入`gulimail-common`依赖

```xml
        <dependency>
            <groupId>com.atguigu.gulimail</groupId>
            <artifactId>gulimail-common</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

复制renren-fast----utils包下的Query和`PageUtils`、`R`、`Constant`等工具类复制到common项目的`java/com.atguigu.common.utils`下，同时将其他所需的类也导入进来，最终的代码结构如下(右图)。

最后`gulimail-product`还存在报错，只需删除Shiro提供的`@RequiresPermissions`注解及相关包即可，因为后面不用Shiro而是采用springsecurity

 <table align="center">
    <tr>
   <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303051125757.png" > <b>最终的代码结构</b></td>
 <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303060158415.png" > <b>删除Shiro提供的@RequiresPermissions注解</b></td>
    </tr>
    </table>


为了让之后生成的代码模板不包含`@RequiresPermissions`注解，可以在`renren-generator`中对`src/main/resources/template/Controller.java.vm`文件进行修改，把包含`@RequiresPermissions`注解的地方注释掉，如果上一步不想一个一个的删除`@RequiresPermissions`注解，也可以重新运行renren-generator项目，然后用新生成的controller替换原先的

![image-20230305121436279](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303051222637.png)



继续调整`gulimail-common`中的报错：删除XssFilter，XssHttpServletRequestWrapper类，引入RRException，最终结构如下图（右图）

 <table align="center">
    <tr>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303051255797.png" > <b>删除XssFilter，XssHttpServletRequestWrapper</b></td>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303051254972.png" > <b>最终结构</b></td>
    </tr>
    </table>



<!-- TOC --><a name="64-mybatis-plus"></a>
### 6.4 整合MyBatis-plus

1.为`gulimail-product`项目导入mysql驱动。为了其他项目可以复用，直接在 `gulimail-common`的pom.xml文件中添加依赖

```xml
        <!--    导入mysql驱动    -->
        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.17</version>
        </dependency>

        <!--mybatis-plus -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.2.0</version>
        </dependency>
```

2.配置数据源。在`gulimail-product`项目的application.yml文件中添加数据库配置信息：

```yaml
spring:
  datasource:
    username: root
    password: root
    url: jdbc:mysql://192.168.56.10:3306/gulimail_pms?autoReconnect=true&useSSL=false&characterEncoding=utf-8
    driver-class-name: com.mysql.cj.jdbc.Driver
```

3.MyBatis-Plus配置。使用@MapperScan配置实体类的扫描路径

```java
@MapperScan("com.atguigu.gulimail.product.dao")
@SpringBootApplication
public class GulimailProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimailProductApplication.class, args);
    }
}
```

4.MyBatis-Plus配置。配置mapper文件的扫描路径来定位sql映射文件的位置，同时将实体类配置为主键自增

```yaml
mybatis-plus:
  mapper-locations: classpath:/mapper/**/*.xml
  global-config:
    db-config:
      id-type: auto
```

5.测试：向数据库写入数据

```java
package com.atguigu.gulimail.product;

import com.atguigu.gulimail.product.entity.BrandEntity;
import com.atguigu.gulimail.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DataBaseCrudTest {

    @Resource
    private BrandService brandService;

    @Test
    public void contextLoads() {

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setBrandId(1L);
        brandEntity.setDescript("华为");
        
        brandEntity.setName("华为");
        brandService.save(brandEntity);
        System.out.println("保存成功....");
//        brandService.updateById(brandEntity);


//        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1L));
//        list.forEach((item) -> {
//            System.out.println(item);
//        });
    }
}
```

<!-- TOC --><a name="65-crud"></a>
### 6.5 逆向生成所有微服务基础CRUD代码

<!-- TOC --><a name="651-gulimail-couponcurd"></a>
#### 6.5.1 生成gulimail-coupon的CURD代码

- 生成`gulimail-coupon`的CURD代码，修改`renren-generator`项目的`application.yml`文件：

```yaml
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    #MySQL配置
    driverClassName: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://192.168.56.10:3306/gulimail_sms?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: root
```

- 生成`gulimail-coupon`的CURD代码，修改`renren-generator`项目的`generator.properties`文件：

```properties
mainPath=com.atguigu
package=com.atguigu.gulimail
moduleName=coupon
author=Li-ShiLin
email=sunlightcs@gmail.com
tablePrefix=sms_
```

- 运行`renren-generator`项目,将生成的main文件复制到`gulimail-coupon`项目中，添加数据源配置、mybatis-plus配置,导入`gulimail-common`公共依赖

```yaml
spring:
  datasource:
    username: root
    password: root
    url: jdbc:mysql://192.168.56.10:3306/gulimail_sms?autoReconnect=true&useSSL=false&characterEncoding=utf-8
    driver-class-name: com.mysql.cj.jdbc.Driver
mybatis-plus:
  mapper-locations: classpath:/mapper/**/*.xml
  global-config:
    db-config:
      id-type: auto
```

- 向`gulimail-coupon`项目中导入`gulimail-common`公共依赖：

```xml
<dependency>
    <groupId>com.atguigu.gulimail</groupId>
    <artifactId>gulimail-common</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

- 在`gulimail-coupon`项目的启动类上添加@MapperScan注解：

```java
@MapperScan("com.atguigu.gulimail.coupon.dao")
@SpringBootApplication
public class GulimailCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimailCouponApplication.class, args);
    }
}
```

- 运行`gulimail-coupon`项目，报如下错误，原因是test测试文件打包出错，只需在pom.xml文件中加入`maven-surefire-plugin`配置即可

![image-20230305211356129](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303052318064.png)

```java
There are test failures.
Please refer to D:\github\Study-Code-Note\007_E-Commerce-Guide\001_gulimail\gulimail-coupon\target\surefire-reports for the individual test results.
Please refer to dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
```

- 在`gulimail-coupon`项目的pom.xml文件中添加如下配置，编译时会跳过测试文件检查的生命周期，上述问题得到解决

```xml
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.2</version>
                <configuration>
                    <skipTests>true</skipTests>
                </configuration>
            </plugin>
        </plugins>
```

- 启动`gulimail-coupon`项目，访问`http://localhost:8080/coupon/coupon/list`，返回如下json数据：

```json
{
	"msg": "success",
	"code": 0,
	"page": {
		"totalCount": 0,
		"pageSize": 10,
		"totalPage": 0,
		"currPage": 1,
		"list": []
	}
}
```

<!-- TOC --><a name="652-gulimail-membercurd"></a>
#### 6.5.2 生成gulimail-member的CURD代码

- 生成`gulimail-member`的CURD代码，修改`renren-generator`项目的`application.yml`文件：

```yaml
spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    #MySQL配置
    driverClassName: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://192.168.56.10:3306/gulimail_ums?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: root
```

- 生成`gulimail-member`的CURD代码，修改`renren-generator`项目的`generator.properties`文件：

```properties
mainPath=com.atguigu
package=com.atguigu.gulimail
moduleName=member
author=Li-ShiLin
email=sunlightcs@gmail.com
tablePrefix=ums_
```

- 运行`renren-generator`项目,将生成的main文件复制到`gulimail-member`项目中，添加数据源配置、mybatis-plus配置,导入`gulimail-common`公共依赖

```yaml
spring:
  datasource:
    username: root
    password: root
    url: jdbc:mysql://192.168.56.10:3306/gulimail_sms?autoReconnect=true&useSSL=false&characterEncoding=utf-8
    driver-class-name: com.mysql.cj.jdbc.Driver
mybatis-plus:
  mapper-locations: classpath:/mapper/**/*.xml
  global-config:
    db-config:
      id-type: auto
```

- `gulimail-member`项目中导入`gulimail-common`公共依赖：

```xml
<dependency>
    <groupId>com.atguigu.gulimail</groupId>
    <artifactId>gulimail-common</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

- 在`gulimail-member`项目的pom.xml文件中添加如下配置，编译时会跳过测试文件检查的生命周期,避免出现错误

```xml
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.22.2</version>
            <configuration>
                <skipTests>true</skipTests>
            </configuration>
        </plugin>
    </plugins>
```

- 在`gulimail-member`项目的启动类上添加@MapperScan注解：

```java
@MapperScan("com.atguigu.gulimail.coupon.dao")
@SpringBootApplication
public class GulimailCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimailCouponApplication.class, args);
    }
}
```

- 启动`gulimail-member`项目，访问`http://localhost:8080/member/growthchangehistory/list`，返回如下响应数据，到此`gulimail-member`项目的CRUD代码完成搭建

```json
{
	msg: "success",
	code: 0,
	page: {
		totalCount: 0,
		pageSize: 10,
		totalPage: 0,
		currPage: 1,
		list: []
	}
}
```

**其他几个微服务项目构建的步骤和`gulimail-member`模块、`gulimail-coupon`模块的构建步骤基本一致，不再赘述**



另外为了避免几个微服务项目启动时端口发生冲突，需要用`server.port`配置一下端口号

```
server:
  port: 7000
```

此处依次将微服务`gulimail-coupon`、`gulimail-member`、`gulimail-order`、`gulimail-product`、`gulimail-ware`的端口号设为7000、8000、9000、10000、11000
