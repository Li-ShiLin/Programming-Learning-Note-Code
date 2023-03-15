## 分布式组�?

## 1.Spring Cloud Alibaba�?�?

### 1.1 分布式组�?

- 前面用�?�向工程生成了微服务的基本增删改查代码，现在说一下微服务中的三个基本概念：注册中心�?�配置中心�?�网�?

![image-20230306203946346](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303070356505.png)

- **注册中心**

```sh
每一个微服务上线都应该将他自己注册到注册中心
这样做的好处就是如果订单服务想调用商品服务，就可以自动地去注册中心找�?个可用地商品服务进行调用
```

- **配置中心**

```sh
各个微服务的配置众多，比如商品微服务�?10台机器上部署，如果要修改商品服务的某�?项配置，那这十台机器都需要修改，这时候就�?要有�?个配置中心来集中管理配置。有了配置中心，只需在配置中心进行修改，这些服务都会进行实时修改
```

- **网关**

```sh
�?有前端的请求都需要�?�过网关进行统一地鉴权�?�过滤�?�路由等，再通过网关将请求发给各个微服务
```

- **spring cloud初代产品中的组件**

```sh
1.注册中心：spring cloud Netflix 中的Eureka组件起到了注册发现的作用
2.配置中心�? spring cloud config 组件  可以用来集中管理配置
3.网关：spring cloud Netflix 中zuul充当网关
```

**本项目不使用Eureka、config 、zuul组件，�?�是使用阿里巴巴�?源的SpringCloud Alibaba中的组件**

###  1.2 SpringCloud Alibaba�?�?

Spring Cloud Alibaba 致力于提供微服务�?发的�?站式解决方案。此项目包含�?发分布式应用微服务的必需组件，方便开发�?��?�过Spring Cloud 编程模型轻松使用这些组件来开发分布式应用服务

依托Spring Cloud Alibaba，您只需要添加一些注解和少量配置,就可以将Spring Cloud应用接入阿里微服务解决方案，通过阿里中间件来迅�?�搭建分布式应用系统

SpringCloud Alibaba   github地址：https://github.com/alibaba/spring-cloud-alibaba

**SpringCloud的几大痛点：**

- SpringCloud 部分组件停止维护和更新，给开发带来不�?;
- SpringCloud,部分环境搭建复杂，没有完善的可视化界面，我们�?要大量的二次�?发和�?
- SpringCloud配置复杂，难以上手，部分配置差别难以区分和合理应�?

**SpringCloud Alibaba的优势：**

- 阿里使用过的组件经历了�?�验，�?�能强悍，设计合理，现在�?源出来大家用
- 成套的产品搭配完善的可视化界面给�?发运维带来极大的便利
- 搭建�?单，学习曲线�?

**结合SpringCloud Alibaba 我们�?终的�?术搭配方�?:**

- SpringCloud Alibaba -Nacos :注册中心（服务发�?/注册)
- SpringCloud Alibaba- Nacos :配置中心(动�?�配置管�?)
- SpringCloud - Ribbon: 负载均衡
- SpringCloud- Feign: 声明式HTTP客户端（调用远程服务�?
- SpringCloud Alibaba - Sentinel:服务容错(限流、降级�?�熔�?)
- SpringCloud - Gateway: API网关(webflux编程模式)
- SpringCloud - Sleuth:调用链监�?
- SpringCloud Alibaba - Seata:原Fescar，即分布式事务解决方�?

### 1.3 版本选择

由于Spring Boot 1 和Spring Boot 2在Actuator模块的接口和注解有很大的变更，且spring-cloud-commons �? 1.x.x版本升级�? 2.0.0版本也有较大的变更，因此我们采取跟SpringBoot版本号一致的版本:

- 1.5.x版本适用于Spring Boot 1.5.x
- 2.0.x 版本适用于Spring Boot 2.0.x
- 2.1.x版本适用于Spring Boot 2.1.x

![image-20230306224555478](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303070356407.png)

- 本项目微服务版本�?

  - spring-boot版本�?2.1.8.RELEASE

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

在父项目中引入如下依�?,  进行统一管理 (将如下依赖导入`gulimail-common`中，其他服务再依赖`gulimail-common`即可)

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



## 2. Spring Cloud Alibaba Nacos 服务注册发现

Nacos 是阿里巴巴开源的�?个更易于构建云原生应用的动�?�服务发现�?�配置管理和服务管理平台。他是使用java 编写。需要依赖java环境

Nacos文档地址:https://nacos.io/zh-cn/docs/quick-start.html

**下载nacos-server** :  https://github.com/alibaba/nacos/releases   ，本项目使用版本�?

![image-20230307002650019](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303070357930.png)

**启动nacos-server:** 双击 bin中的startup.cmd文件（或者在bin目录打开cmd窗口运行·`startup.cmd -m standalone`命令），接着访问http://localhost:8848/nacos/   。使用默认的nacos/nacos进行登录

nacos服务注册发现依赖：由于每个微服务都依赖nacos，所以直接将依赖引入到`gulimail-common`的pom.xml文件�?

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

把其他服务的配置也依次进行配置，并依次启动，�?后可以看到服务成功注册到nacos :

![image-20230307014712691](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303070357012.png)



## 3. Spring Cloud Feign 远程调用

### 3.1 Feign声明式远程调�?

Feign是一个声明式的HTTP客户端，它的目的就是让远程调用更加简单�?�Feign提供了HTTP请求的模板，通过编写�?单的接口和插入注解，就可以定义好HTTP请求的参数�?�格式�?�地�?等信�?

Feign整合了Ribbon(负载均衡）和Hystrix(服务熔断)，可以让我们不再�?要显式地使用这两个组�?

SpringCloud Feign�? NetflixFeign的基�?上扩展了对SpringMVC 注解的支持，在其实现下，我们只需创建�?个接口并用注解的方式来配置它,即可完成对服务提供方的接口绑定�?�简化了SpringCloudRibbon,自行封装服务调用客户端的�?发量�?

### 3.2 测试远程调用

案例：测试Spring Cloud Feign远程调用

�?求：模拟会员服务调用优惠卷服�?

调用流程：会员服务`gulimall-member`想从优惠劵服务`gulimall-coupon`获取当前会员领取到的�?有优惠卷信息，会员服务`gulimall-member`会去注册中心中找优惠劵服务`gulimall-coupon`，找到后发�?�请求然后接收响应，就可以获取相应的信息


1.引入openfeign依赖�?

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
 * 优惠券信�?
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
        couponEntity.setCouponName("�?100�?10");
        return R.ok().put("coupons", Arrays.asList(couponEntity));
    }
}
```

3.在会员服务`gulimall-member`中创建一个feign目录，并在其中创建一个`CouponFeignService` 接口。在接口上添加注解`@FeignClient("服务�?")`，告诉springcloud 此接口是�?个远程客户端，他要远程调用其他微服务。会根据服务名称到nacos查找服务进行调用

```java
package com.atguigu.gulimail.member.feign;
import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 这是�?个声明式的远程调用接�?
 * 声明接口的每�?个方法都是调用哪个远程服务的那个请求
 * 告诉springcloud 此接口是�?个远程客户端，他要调用远程服�?
 */
// 1.先找到要调用的服�?
@FeignClient("gulimail-coupon")
public interface CouponFeignService {
    // 2.再找到服务中要调用的具体方法
    @RequestMapping("/coupon/coupon/member/list")
    public R memberCoupons();

}
```

4.在会员服务`gulimail-member`中开启远程调用功能�?�在启动类上添加` @EnableFeignClients注解`,并在注解中指定feign包的路径

```java
package com.atguigu.gulimail.member;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 * 1、想要远程调用别的服�?
 * 1）�?�引入open-feign
 * 2）�?�编写一个接口，告诉SpringCloud这个接口�?要调用远程服�?
 *   1、声明接口的每一个方法都是调用哪个远程服务的那个请求
 * 3）�?�开启远程调用功�?
 */
//   @EnableFeignClients注解 �?启远程调用功�?
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

5.在会员服务`gulimail-member`中发起远程调用�?�注入之前声明的远程调用接口`CouponFeignService`，即可发起调�?

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

6.测试。给会员服务`gulimail-member`发�?�请求�?��?�访问`http://localhost:8000/member/member/coupons`, `gulimail-member`服务再调用`gulimall-coupon`，即可获取当前会员领取到的所有优惠卷的信息�?�测试返回如下结果：

```json
{
    "msg":"success",
    "code":0,
    "coupons":[
        {
            "id":null,
            "couponType":null,
            "couponImg":null,
            "couponName":"�?100�?10",
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

## 4. Cloud Alibaba-Nacos 配置中心

### 4.1 读取本地配置

1.在本地application.properties文件中添加如下配置：

```properties
coupon.user.naem=zhangsan
coupon.user.age=18
```

2.在CouponController中利用@Value读取配置�?

```java
package com.atguigu.gulimail.coupon.controller;
import com.atguigu.common.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.Map;
/**
 * 优惠券信�?
 */
@RestController
@RequestMapping("coupon/coupon")
public class CouponController {

    @Value("${coupon.user.name}")
    private String name;

    @Value("${coupon.user.age}")
    private  Integer age;

    @RequestMapping("/test")
    public R test(){
        return R.ok().put("name",name).put("age",age);
    }
}
```

3.测试：访问`http://localhost:7000/coupon/coupon/test`返回如下信息,可以看到成功地读取到了本地配�?

```json
{
    msg: "success",
    code: 0,
    name: "zhangsan",
    age: 18
}
```

### 4.2 使用nacos配置中心

1.首先，修改pom.xml文件，引入Nacos Config Starter

```xml
<!--配置中心来做配置管理-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
```

2.创建/src/main/resources/bootstrap.properties文件，springboot中规定bootstrap.properties配置文件会优先于application.properties加载，其中的配置也会优先被读取�??

```properties
spring.application.name=gulimail-coupon
spring.cloud.nacos.config.server-addr=127.0.0.1:8848
```

3.在nacos配置中心添加配置`gulimail-coupon.properties`（服务名.properties�?

![image-20230316015141987](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303160208602.png)



4. 在`CouponController`类上添加`@RefreshScope`注解

```java
package com.atguigu.gulimail.coupon.controller;
import com.atguigu.common.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.Map;
/**
 * 优惠券信�?
 */
@RefreshScope
@RestController
@RequestMapping("coupon/coupon")
public class CouponController {

    @Value("${coupon.user.naem}")
    private String name;

    @Value("${coupon.user.age}")
    private  Integer age;

    @RequestMapping("/test")
    public R test(){
        return R.ok().put("name",name).put("age",age);
    }
}
```

5.重启服务，并发布配置，访问`http://localhost:7000/coupon/coupon/test`返回如下信息，说明读取到了nacos上的配置

```json
{
    msg: "success",
    code: 0,
    name: "zhangsanfen",
    age: 25
}
```

**Nacos配置中心总结�?**

```sh
1、如何使用Nacos作为配置中心统一管理配置
   1）�?�引入依赖：
          <dependency>
              <groupId>com.alibaba.cloud</groupId>
             <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
          </dependency>
   2）�?�创建一个bootstrap.properties
          spring.application.name=gulimall-coupon
          spring.cloud.nacos.config.server-addr=127.0.0.1:8848
   3）�?�需要给配置中心默认添加�?个叫 数据集（Data Id）gulimall-coupon.properties。默认规则，应用�?.properties
   4）�?�给 应用�?.properties 添加任何配置
   5）�?�动态获取配�?
          @RefreshScope：动态获取并刷新配置
          @Value("${配置项的名}")：获取到配置
          如果配置中心和当前应用的配置文件中都配置了相同的项，优先使用配置中心的配�?
```



### 4.3 nacos 配置中心进阶

#### 1.命名空间:

用于进行租户粒度的配置隔离�?�不同的命名空间下，可以存在相同�? Group�? Data ID 的配置�?�Namespace 的常用场景之�?是不同环境的配置的区分隔离，例如�?发测试环境和生产环境的资源（如配置�?�服务）隔离等�??

**命名空间使用�?**场景�?：开发，测试，生产：利用命名空间来做环境隔离

1.创建命名空间�?

![image-20230316013733931](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303160208265.png)

2.在具体的命名空间下创建配置文�?

 <table align="center">
    <tr>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303160208147.png" > <b>创建命名空间</b></td>
        <td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303160209320.png" > <b>添加配置</b></td>
    </tr>
    </table>



3.将命名空间下的命名空间id复制到`bootstrap.properties`文件中，并添加配置`spring.cloud.nacos.config.namespace=a3a0ccd5-82a2-444a-b53e-2afcc579772c`

![image-20230316014818862](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303160209504.png)



在`bootstrap.properties`文件中添加配置`spring.cloud.nacos.config.namespace=a3a0ccd5-82a2-444a-b53e-2afcc579772c`:

```properties
spring.application.name=gulimail-coupon
spring.cloud.nacos.config.server-addr=127.0.0.1:8848
spring.cloud.nacos.config.namespace=a3a0ccd5-82a2-444a-b53e-2afcc579772c
```



访问`http://localhost:7000/coupon/coupon/test`,返回如下数据，可以发现读取到了命名空间prod下的配置文件

```json
{
    "msg": "success",
    "code": 0,
    "name": "zhangsanfeng",
    "age": 24
}
```



**命名空间使用�?**场景二：每一个微服务之间互相隔离配置，每�?个微服务都创建自己的命名空间，只加载自己命名空间下的�?有配�?

比如可以创建�?个`coupon`命名空间，`coupon`微服务的配置文件都可以放到该命名空间�?

![image-20230316021310655](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303160214780.png)



#### 2.配置�?:

�?组相关或者不相关的配置项的集合称为配置集。在系统中，�?个配置文件�?�常就是�?个配置集，包含了系统各个方面的配置�?�例如，�?个配置集可能包含了数据源、线程池、日志级别等配置�?



#### 3.配置集ID:

Nacos 中的某个配置集的 ID。配置集D是组织划分配置的维度之一。DatalD通常用于组织划分系统的配置集。一个系统或者应用可以包含多个配置集，每个配置集都可以被�?个有意义的名称标识�?�Data ID通常采用类Java包（如com.taobao.tc.refund.log.level）的命名规则保证全局唯一性�?�此命名规则非强制�??

#### 4.配置分组:

Nacos 中的�?组配置集，是组织配置的维度之�?。�?�过�?个有意义的字符串（如 Buy 或Trade ）对配置集进行分组，从�?�区�? Data lD相同的配置集。当您在Nacos上创建一个配置时，如果未填写配置分组的名称，则配置分组的名称默认采用 DEFAULT_GROUP。配置分组的常见场景:不同的应用或组件使用了相同的配置类型，如 database_url配置和MQ_topic配置

**配置分组使用�?**

1.新建配置时指定配置分组：

![image-20230316022423150](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303160227905.png)

2.相同命名空间下的可以存在Group相异的同名配置文�?

![image-20230316022507928](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303160231527.png)

3.通过配置指定Group:   `spring.cloud.nacos.config-group=1111`

![image-20230316022546206](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303160231569.png)

####  5.总结

```sh
2、细�?
 1）�?�命名空间：配置隔离�?
          默认：public(保留空间)；默认新增的�?有配置都在public空间�?
          1、场景一：开发，测试，生产：利用命名空间来做环境隔离�?
             注意：在bootstrap.properties；配置上，需要使用哪个命名空间下的配置，
             spring.cloud.nacos.config.namespace=9de62e44-cd2a-4a82-bf5c-95878bd5e871
          2、场景二：每�?个微服务之间互相隔离配置，每�?个微服务都创建自己的命名空间，只加载自己命名空间下的�?有配�?

 2）�?�配置集：所有的配置的集�?
 3）�?�配置集ID：类似文件名�?
      Data ID：类似文件名

  4）�?�配置分组：
      默认�?有的配置集都属于：DEFAULT_GROUP�?
      1111�?618�?1212
      本项目中的使用：每个微服务创建自己的命名空间，使用配置分组区分环境，dev，test，prod

 3、同时加载多个配置集
         1)、微服务任何配置信息，任何配置文件都可以放在配置中心�?
         2）�?�只�?要在bootstrap.properties说明加载配置中心中哪些配置文件即�?
         3）�?�@Value，@ConfigurationProperties。�?��??
         以前SpringBoot任何方法从配置文件中获取值，都能使用�?
         配置中心有的优先使用配置中心中的�?
```

 本项目中对nacos配置中心的使用：每个微服务创建自己的命名空间，使用配置分组区分环境，dev，test，prod

![image-20230316023444888](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303160235748.png)



### 4.4 加载多配置集

随着微服务项目不断扩展，如果把使用配置都写到本地的`application.yml`中，配置会非常臃肿�?�一般会把本地`application.yml`中的配置分成`和数据源有关的配置`、`和微服务有关的配置`、`和框架有关的配置`等不同的配置文件，并把这些配置转移到nacos配置中心�?



例如本项目的本地配置文件`application.yml`如下，接下来就把他拆分并配置到nacos配置中心�?

```yaml
spring:
  datasource:
    username: root
    password: root
    url: jdbc:mysql://192.168.56.10:3306/gulimail_sms?autoReconnect=true&useSSL=false&characterEncoding=utf-8
    driver-class-name: com.mysql.cj.jdbc.Driver
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
  application:
    name: gulimail-coupon

mybatis-plus:
  mapper-locations: classpath:/mapper/**/*.xml
  global-config:
    db-config:
      id-type: auto
server:
  port: 7000
```

1.先把上面的本地配置`application.yml`全部注释�?

2.把和数据源有关的配置放到新建的` datasource.yml`配置文件中：

![image-20230316030501007](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303160339274.png)

3.将和mybatis有关的配置放到新建的` mybatis.yml`配置文件中：

![image-20230316030636139](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303160339051.png)

4.将剩下的配置添加到` other.yml`配置文件中：



![image-20230316030846552](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303160338677.png)

5.coupon命名空间下的配置文件如下�?

![image-20230316030951544](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202303160338596.png)

6.配置本地`bootstrap.properties`文件，即可加载nacos配置中心上的各个配置文件及对应配置信�?

```properties
spring.application.name=gulimail-coupon
spring.cloud.nacos.config.server-addr=127.0.0.1:8848
spring.cloud.nacos.config.namespace=466bb46f-ab30-407b-b49e-cb133220bcdf
spring.cloud.nacos.config.group=dev


spring.cloud.nacos.config.ext-config[0].data-id=datasource.yml
spring.cloud.nacos.config.ext-config[0].group=dev
spring.cloud.nacos.config.ext-config[0].refresh=true  
#设置为动态刷�?

spring.cloud.nacos.config.ext-config[1].data-id=mybatis.yml
spring.cloud.nacos.config.ext-config[1].group=dev
spring.cloud.nacos.config.ext-config[1].refresh=true

spring.cloud.nacos.config.ext-config[2].data-id=other.yml
spring.cloud.nacos.config.ext-config[2].group=dev
spring.cloud.nacos.config.ext-config[2].refresh=true
```

**多配置集总结�?**

```sh
同时加载多个配置�?:
     1.微服务任何配置信息，任何配置文件都可以放在配置中心中
     2.只需要在bootstrap.properties说明加载配置中心中哪些配置文件即�?
     3.使用spring boot加载配置的注解@Value，@ConfigurationProperties等来获取配置中心的配�?
     4.以前SpringBoot任何方法从配置文件中获取值的注解，都能用来获取配置中心的配置�?
     5.配置中心有的优先使用配置中心中的
```

