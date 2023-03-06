## 分布式组件

## 1.Spring Cloud Alibaba简介

### 1.1 分布式组件

- 前面用逆向工程生成了微服务的基本增删改查代码，现在说一下微服务中的三个基本概念：注册中心、配置中心、网关

![image-20230306203946346](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303070356505.png)

- **注册中心**

```sh
每一个微服务上线都应该将他自己注册到注册中心
这样做的好处就是如果订单服务想调用商品服务，就可以自动地去注册中心找一个可用地商品服务进行调用
```

- **配置中心**

```sh
各个微服务的配置众多，比如商品微服务在10台机器上部署，如果要修改商品服务的某一项配置，那这十台机器都需要修改，这时候就需要有一个配置中心来集中管理配置。有了配置中心，只需在配置中心进行修改，这些服务都会进行实时修改
```

- **网关**

```sh
所有前端的请求都需要通过网关进行统一地鉴权、过滤、路由等，再通过网关将请求发给各个微服务
```

- **spring cloud初代产品中的组件**

```sh
1.注册中心：spring cloud Netflix 中的Eureka组件起到了注册发现的作用
2.配置中心： spring cloud config 组件  可以用来集中管理配置
3.网关：spring cloud Netflix 中zuul充当网关
```

**本项目不使用Eureka、config 、zuul组件，而是使用阿里巴巴开源的SpringCloud Alibaba中的组件**

###  1.2 SpringCloud Alibaba简介

Spring Cloud Alibaba 致力于提供微服务开发的一站式解决方案。此项目包含开发分布式应用微服务的必需组件，方便开发者通过Spring Cloud 编程模型轻松使用这些组件来开发分布式应用服务

依托Spring Cloud Alibaba，您只需要添加一些注解和少量配置,就可以将Spring Cloud应用接入阿里微服务解决方案，通过阿里中间件来迅速搭建分布式应用系统

SpringCloud Alibaba   github地址：https://github.com/alibaba/spring-cloud-alibaba

**SpringCloud的几大痛点：**

- SpringCloud 部分组件停止维护和更新，给开发带来不便;
- SpringCloud,部分环境搭建复杂，没有完善的可视化界面，我们需要大量的二次开发和定
- SpringCloud配置复杂，难以上手，部分配置差别难以区分和合理应用

**SpringCloud Alibaba的优势：**

- 阿里使用过的组件经历了考验，性能强悍，设计合理，现在开源出来大家用
- 成套的产品搭配完善的可视化界面给开发运维带来极大的便利
- 搭建简单，学习曲线低

**结合SpringCloud Alibaba 我们最终的技术搭配方案:**

- SpringCloud Alibaba -Nacos :注册中心（服务发现/注册)
- SpringCloud Alibaba- Nacos :配置中心(动态配置管理)
- SpringCloud - Ribbon: 负载均衡
- SpringCloud- Feign: 声明式HTTP客户端（调用远程服务）
- SpringCloud Alibaba - Sentinel:服务容错(限流、降级、熔断)
- SpringCloud - Gateway: API网关(webflux编程模式)
- SpringCloud - Sleuth:调用链监控
- SpringCloud Alibaba - Seata:原Fescar，即分布式事务解决方案

### 1.3 版本选择

由于Spring Boot 1 和Spring Boot 2在Actuator模块的接口和注解有很大的变更，且spring-cloud-commons 从 1.x.x版本升级到 2.0.0版本也有较大的变更，因此我们采取跟SpringBoot版本号一致的版本:

- 1.5.x版本适用于Spring Boot 1.5.x
- 2.0.x 版本适用于Spring Boot 2.0.x
- 2.1.x版本适用于Spring Boot 2.1.x

![image-20230306224555478](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303070356407.png)

- 本项目微服务版本：

  - spring-boot版本：2.1.8.RELEASE

  - spring-cloud版本：Greenwich.SR3
  - 如`gulimail-member`的部分依赖如下：

```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.8.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.atguigu.gulimail</groupId>
    <artifactId>gulimail-member</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>gulimail-member</name>
    <description>gulimail-member</description>
    <properties>
        <java.version>1.8</java.version>
        <spring-cloud.version>Greenwich.SR3</spring-cloud.version>
    </properties>
```

### 1.4 项目中的依赖

在父项目中引入如下依赖,  进行统一管理 (将如下依赖导入`gulimail-common`中，其他服务再依赖`gulimail-common`即可)

```xml
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2.1.0.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```



## 2. Spring Cloud Alibaba Nacos 

Nacos 是阿里巴巴开源的一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。他是使用java 编写。需要依赖java环境

Nacos文档地址:https://nacos.io/zh-cn/docs/quick-start.html

**下载nacos-server** :  https://github.com/alibaba/nacos/releases   ，本项目使用版本：

![image-20230307002650019](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303070357930.png)

**启动nacos-server:** 双击 bin中的startup.cmd文件（或者在bin目录打开cmd窗口运行·`startup.cmd -m standalone`命令），接着访问http://localhost:8848/nacos/   。使用默认的nacos/nacos进行登录

nacos服务注册发现依赖：由于每个微服务都依赖nacos，所以直接将依赖引入到`gulimail-common`的pom.xml文件中

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

在`gulimail-coupon`服务的`/src/main/resources/application.yml`配置文件中配置Nacos Server地址,并指定微服务的名称：

```properties
spring:
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
  application:
    name: gulimail-coupon
```

在启动类上添加`@EnableDiscoveryClient`注解，开启服务注册发现的客户端，使得服务能够注册到注册中心：

```java
@EnableDiscoveryClient
@SpringBootApplication
public class GulimailCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimailCouponApplication.class, args);
    }
}
```

把其他服务的配置也依次进行配置，并依次启动，最后可以看到服务成功注册到nacos :

![image-20230307014712691](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303070357012.png)



## 3. Spring Cloud Feign

### 3.1 Feign声明式远程调用

Feign是一个声明式的HTTP客户端，它的目的就是让远程调用更加简单。Feign提供了HTTP请求的模板，通过编写简单的接口和插入注解，就可以定义好HTTP请求的参数、格式、地址等信息

Feign整合了Ribbon(负载均衡）和Hystrix(服务熔断)，可以让我们不再需要显式地使用这两个组件

SpringCloud Feign在 NetflixFeign的基础上扩展了对SpringMVC 注解的支持，在其实现下，我们只需创建一个接口并用注解的方式来配置它,即可完成对服务提供方的接口绑定。简化了SpringCloudRibbon,自行封装服务调用客户端的开发量。

### 3.2 测试远程调用

案例：测试Spring Cloud Feign远程调用

需求：模拟会员服务调用优惠卷服务

调用流程：会员服务`gulimall-member`想从优惠劵服务`gulimall-coupon`获取当前会员领取到的所有优惠卷信息，会员服务`gulimall-member`会去注册中心中找优惠劵服务`gulimall-coupon`，找到后发送请求然后接收响应，就可以获取相应的信息


1.引入openfeign依赖：

```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
```

2.在优惠劵服务`gulimail-coupon` 中声明调用接口，用于返回当前会员的所有优惠卷

```java
package com.atguigu.gulimail.coupon.controller;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;
import com.atguigu.gulimail.coupon.entity.CouponEntity;
import com.atguigu.gulimail.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.Map;
/**
 * 优惠券信息
 */
@RestController
@RequestMapping("coupon/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    // 返回当前会员的所有优惠卷
    @RequestMapping("/member/list")
    public R memberCoupons() {
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponName("满100减10");
        return R.ok().put("coupons", Arrays.asList(couponEntity));
    }
}
```

3.在会员服务`gulimall-member`中创建一个feign目录，并在其中创建一个`CouponFeignService` 接口。在接口上添加注解`@FeignClient("服务名")`，告诉springcloud 此接口是一个远程客户端，他要远程调用其他微服务。会根据服务名称到nacos查找服务进行调用

```java
package com.atguigu.gulimail.member.feign;
import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 这是一个声明式的远程调用接口
 * 声明接口的每一个方法都是调用哪个远程服务的那个请求
 * 告诉springcloud 此接口是一个远程客户端，他要调用远程服务
 */
// 1.先找到要调用的服务
@FeignClient("gulimail-coupon")
public interface CouponFeignService {
    // 2.再找到服务中要调用的具体方法
    @RequestMapping("/coupon/coupon/member/list")
    public R memberCoupons();

}
```

4.在会员服务`gulimail-member`中开启远程调用功能。在启动类上添加` @EnableFeignClients注解`,并在注解中指定feign包的路径

```java
package com.atguigu.gulimail.member;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 * 1、想要远程调用别的服务
 * 1）、引入open-feign
 * 2）、编写一个接口，告诉SpringCloud这个接口需要调用远程服务
 *   1、声明接口的每一个方法都是调用哪个远程服务的那个请求
 * 3）、开启远程调用功能
 */
//   @EnableFeignClients注解 开启远程调用功能
@EnableFeignClients(basePackages = "com.atguigu.gulimail.member.feign")
@EnableDiscoveryClient
@MapperScan("com.atguigu.gulimail.member.dao")
@SpringBootApplication
public class GulimailMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(GulimailMemberApplication.class, args);
    }

}
```

5.在会员服务`gulimail-member`中发起远程调用。注入之前声明的远程调用接口`CouponFeignService`，即可发起调用

```java
package com.atguigu.gulimail.member.controller;
import java.util.Arrays;
import java.util.Map;
import com.atguigu.gulimail.member.feign.CouponFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.atguigu.gulimail.member.entity.MemberEntity;
import com.atguigu.gulimail.member.service.MemberService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;
/**
 * 会员
 */
@RestController
@RequestMapping("member/member")
public class MemberController {

    // 注入远程调用接口
    @Autowired
    private CouponFeignService couponFeignService;

    @RequestMapping("/coupons")
    public R test(){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setNickname("张三");
        R memberCoupons = couponFeignService.memberCoupons();
        return  R.ok().put("member",memberEntity).put("coupons",memberCoupons.get("coupons"));
    }

}
```

6.测试。给会员服务`gulimail-member`发送请求——访问`http://localhost:8000/member/member/coupons`, `gulimail-member`服务再调用`gulimall-coupon`，即可获取当前会员领取到的所有优惠卷的信息。测试返回如下结果：

```json
{
    "msg":"success",
    "code":0,
    "coupons":[
        {
            "id":null,
            "couponType":null,
            "couponImg":null,
            "couponName":"满100减10",
            "num":null,
            "amount":null,
            "perLimit":null,
            "minPoint":null,
            "startTime":null,
            "endTime":null,
            "useType":null,
            "note":null,
            "publishCount":null,
            "useCount":null,
            "receiveCount":null,
            "enableStartTime":null,
            "enableEndTime":null,
            "code":null,
            "memberLevel":null,
            "publish":null
        }
    ],
    "member":{
        "id":null,
        "levelId":null,
        "username":null,
        "password":null,
        "nickname":"张三",
        "mobile":null,
        "email":null,
        "header":null,
        "gender":null,
        "birth":null,
        "city":null,
        "job":null,
        "sign":null,
        "sourceType":null,
        "integration":null,
        "growth":null,
        "status":null,
        "createTime":null
    }
}
```

