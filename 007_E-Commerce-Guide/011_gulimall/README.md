<!-- TOC start (generated with https://github.com/derlin/bitdowntoc) -->

- [1.认证服务](#1)
  * [1.1 认证服务环境搭建](#11-)
  * [1.2 页面跳转实现](#12-)
  * [1.3 用户注册功能](#13-)
      - [1.3.1 验证码倒计时](#131-)
      - [1.3.2  springMVC视图映射](#132--springmvc)
      - [1.3.3 阿里云短信验证码](#133-)
      - [1.3.4 注册信息保存](#134-)
        * [1.3.4.1  MD5&MD5盐值加密](#1341--md5md5)
        * [1.3.4.2 注册功能实现](#1342-)
  * [1.4 用户登录功能](#14-)
  * [1.5 社交登录功能](#15-)
      - [1.5.1 社交登录](#151-)
      - [1.5.2 OAuth2.0](#152-oauth20)
      - [1.5.3 微博登陆准备工作](#153-)
        * [1.5.3.1 登录微博开放平台 & 注册应用](#1531---)
        * [1.5.3.2 微博社交登录接口调用](#1532-)
      - [1.5.4 实现完整的社交登录功能](#154-)
  * [1.6 spring session解决session共享问题](#16-spring-sessionsession)
      - [1.6.1 遗留问题：页面信息共享](#161-)
      - [1.6.2 session原理](#162-session)
      - [1.6.3 session共享问题一：分布式下session共享问题](#163-sessionsession)
      - [1.6.4 session共享问题二：session不能跨不同域名进行共享](#164-sessionsession)
      - [1.6.5 `springsession`的核心原理](#165-springsession)
      - [1.6.6 使用`SpringSession`解决session共享问题](#166-springsessionsession)
      - [1.6.7 页面优化](#167-)
  * [1.7 单点登录](#17-)
      - [1.7.1 单点登录简介](#171-)
      - [1.7.2 单点登录服务搭建](#172-)
- [2.购物车功能](#2)
  * [2.1 基础环境搭建](#21-)
  * [2.2 购物车需求](#22-)
  * [2.3 购物车数据存储 & 封装](#23---)
  * [2.4 离线&在线购物车](#24-)
      - [2.4.1 需求&实现方案](#241-)
      - [2.4.2 ThreadLocal的使用](#242-threadlocal)
      - [2.4.3 页面环境搭建](#243-)
      - [2.4.4 添加商品到购物车](#244-)
      - [2.4.5 `RedirectAttributes`防刷新](#245-redirectattributes)
      - [2.4.6 查询购物车 & 合并购物项](#246---)
      - [2.4.7 勾选购物项](#247-)
      - [2.4.8 改变购物项数量](#248-)
      - [2.4.9 删除购物项](#249-)
- [3.消息中间件—RabbitMQ](#3rabbitmq)
  * [3.1 消息队列概述](#31-)
  * [3.2 RabbitMQ核心概念&安装](#32-rabbitmq)
  * [3.3 RabbitMQ运行机制](#33-rabbitmq)
  * [3.4 RabbitMQ整合&使用案例](#34-rabbitmq)
  * [3.5 RabbitMQ消息确认机制-可靠抵达](#35-rabbitmq-)
      - [3.5.1 可靠抵达](#351-)
      - [3.5.2 可靠抵达-`ConfirmCallback`](#352--confirmcallback)
      - [3.5.3 可靠抵达-`ReturnCallback`：](#353--returncallback)
      - [3.5.4 可靠抵达-`Ack`消息确认机制](#354--ack)
  * [3.6 RabbitMQ延时队列(实现定时任务)](#36-rabbitmq)

<!-- TOC end -->

##    1.认证服务

###  1.1 认证服务环境搭建

1.创建认证服务`gulimall-auth-server`模块，引入如下依赖`pom.xml`：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.12.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.atguigu.gulimall</groupId>
    <artifactId>gulimall-auth-server</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>gulimall-auth-server</name>
    <description>认证中心（社交登录、OAuth2.0、单点登录）</description>
    
    <properties>
        <java.version>1.8</java.version>
        <spring-cloud.version>Greenwich.SR4</spring-cloud.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>com.atguigu.gulimall</groupId>
            <artifactId>gulimall-common</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <exclusions>
                <exclusion>
                    <groupId>com.baomidou</groupId>
                    <artifactId>mybatis-plus-boot-starter</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>


        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <!-- 配置属性提示工具  -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
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

2.`application.properties`:添加配置将服务注册到`nacos`注册中心，并禁用`thymeleaf`缓存

```properties
spring.application.name=gulimall-auth-server
spring.cloud.nacos.discovery.server-addr=127.0.0.1:8848
server.port=20000
spring.thymeleaf.cache=false
```

3.在启动类上添加`@EnableDiscoveryClient`注解，开启服务注册与发现功能

```java
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class GulimallAuthServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(GulimallAuthServerApplication.class, args);
	}

}
```

4.利用`switchhost`配置域名映射`192.168.56.10 auth.gulimall.com`，在浏览器输入`auth.gulimall.com`,请求会被转发给`linux`服务器所在的地址`192.168.56.10`,`linux`服务器在将请求转发给`nginx`,再由`nginx`最后将请求转发给后台服务集群

![image-20230723215942029](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307232159050.png)

5.将资料中的`登录页`和`注册页`拷贝到`认证服务`的`templates`目录下，并将文件名进行更改：

![image-20230723205457480](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307232054511.png)



6.将资料中和`登录页`、`注册页`相关的静态文件上传到`nginx`的特定目录，让`nginx`进行静态文件的转发

![image-20230723215915237](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307232159274.png)

7.更改`templates`目录下的`登录页`和`注册页`中的链接路径，加上`nginx`的访问路径

![image-20230723220812019](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307232208052.png)

8.在网关服务`gulimall-gateway`的配置文件 `application.properties`中添加路由转发配置：

```yaml
spring:
  cloud:
    gateway:
      routes:
##  转发到认证服务  gulimall-auth-server
        - id: gulimall_auth_route
          uri: lb://gulimall-auth-server
          predicates:
            - Host=auth.gulimall.com
```

###  1.2 页面跳转实现

1.修改认证服务`gulimall-auth-server`的登录页面 `templates/login.html`和注册页面`templates/reg.html`

在登录页面点击`商城图标`后跳转到`商城服务`页面,点击`立即注册`后跳转到`注册页面`

```js
<!--顶部logo-->
<header>
    <a href="http://gulimall.com"><img src="/static/login/JD_img/logo.jpg" /></a>
</header>
```

```js
<h5 class="rig">
   <img src="/static/login/JD_img/4de5019d2404d347897dee637895d02b_25.png" />
   <span><a href="http://auth.gulimall.com/reg.html">立即注册</a></span>
</h5>
```

在`注册页面`点击请登录,跳转到`登录页面`,点击`商城图标`后跳转到`商城服务`页面，点击`商城图标`后跳转到`商城服务`页面:

```js
<header>
    <a href="http://gulimall.com"
       class="logo"><img src="/static/reg/img/logo1.jpg" alt=""></a>
    <div class="desc">欢迎注册</div>
    <div class="dfg">
        <span>已有账号？</span>
        <a href="http://auth.gulimall.com/login.html">请登录</a>
    </div>
</header>
```

2.修改商品服务`gulimall-product`的登录页面 `templates/index.html`，点击`登录`，`注册`时跳转到`登录页`或`注册页`

```js
<li>
    <a href="http://auth.gulimall.com/login.html">你好，请登录</a>
</li>
<li>
    <a href="http://auth.gulimall.com/reg.html" class="li_2">免费注册</a>
</li>
```

3.在认证服务`gulimall-auth-server`添加`LoginController`，返回相应的请求页面

```java
@Controller
public class LoginController {
    @GetMapping("/login.html")
    public String loginPage() {

        return "login";
    }
    @GetMapping("/reg.html")
    public String regPage() {

        return "reg";
    }
}
```

###  1.3 用户注册功能

#####  1.3.1 验证码倒计时

需求：点击`发生验证码`，出现倒计时显示`x秒后再次发送`：

![image-20230724001447633](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307240014675.png)

绑定单击事件`sendCode`：

```js
<div class="register-box">
    <label class="other_label">验 证 码
        <input name="code" maxlength="20" type="text" placeholder="请输入验证码" class="caa">
    </label>
    <a id="sendCode"> 发送验证码 </a>
    <div class="tips" style="color:red"
         th:text="${errors!=null?(#maps.containsKey(errors, 'code')?errors.code:''):''}">
    </div>
</div>
```

`JavaScript`计时事件:

- 通过使用JavaScript，可以在一个设定的时间间隔之后来执行代码，而不是在函数祯调用后立即执行。我们称之为计时事件
- 在Javascritp 中使用计时事件是很容易的,两个关键方法是:
  - setTimeout()
    - 未来的某时执行代码
  - clearTimeout()
    - 取消setTimeout()

`sendCode`逻辑 ：

```java
    $(function () {
        $("#sendCode").click(function () {
            //2、倒计时效果
            if ($(this).hasClass("disabled")) {
				// 如果class为disabled就说明已经在倒计时，此时不会调用倒计时函数
                //正在倒计时。
            } else {
                //1、给指定手机号发送验证码
                $.get("/sms/sendcode?phone=" + $("#phoneNum").val(), function (data) {
                    if (data.code != 0) {
                        alert(data.msg);
                    }
                });
                timeoutChangeStyle();
            }
        });
    })
    var num = 60;

    function timeoutChangeStyle() {
        // 函数开始执行后更改class为disabled，如果正在倒计时的话就不会被再次调用
        $("#sendCode").attr("class", "disabled");
        if (num == 0) {
            $("#sendCode").text("发送验证码");
            num = 60;
            $("#sendCode").attr("class", "");
        } else {
            var str = num + "s 后再次发送";
            $("#sendCode").text(str);
            setTimeout("timeoutChangeStyle()", 1000);
        }
        num--;
    }
```

#####  1.3.2  springMVC视图映射

如下的`LoginController`中定义的两个方法都只做了一件事：将发送来的请求直接转到一个页面。我们可以优化一下，使用`SpringMVC`的`viewcontroller`功能将请求和页面映射起来

```java
@Controller
public class LoginController {
    /**
     * 发送一个请求直接跳转到一个页面
     */
    @GetMapping("/login.html")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/reg.html")
    public String regPage() {

        return "reg";
    }
}
```

上述代码的等效写法，使用`SpringMVC`的`viewcontroller`功能将请求和页面映射起来

```java
@Configuration
public class GulimallWebConfig implements WebMvcConfigurer {
    /**
     * 视图映射
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /**
         *     @GetMapping("/login.html")
         *     public String loginPage(){
         *         return "login";
         *     }
         */
        registry.addViewController("/login.html").setViewName("login");
        //只是get请求能映射
        registry.addViewController("/reg.html").setViewName("reg");
    }
}
```

#####  1.3.3 阿里云短信验证码

1.登录阿里云官网，在云市场中购买短信服务，此课程购买的短信服务地址:`https://market.aliyun.com/products/57126001/cmapi025334.html#sku=yuncode1933400000`

2.拷贝阿里云提供的`HttpUtils`工具类到第三方服务`gulimall-third-party`的`util`包下。拷贝`java示例代码`进行短信验证码发生测试：

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class GulimallThirdPartyApplicationTests {

    @Test
    public void sendSms(){
        String host = "https://smsmsgs.market.alicloudapi.com";
        String path = "/sms/";
        String method = "GET";
        String appcode = "xxx";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("code", "6789");
        querys.put("phone", "19801333953");
        querys.put("skin", "1");
        querys.put("sign", "175622");
        //JDK 1.8示例代码请在这里下载：  http://code.fegine.com/Tools.zip

        try {
            // 注意：
            // httpUtils 请从此处下载： https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //System.out.println(response.toString());如不输出json, 请打开这行代码，打印调试头部状态码。
            //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

3.1 在第三方服务`gulimall-third-party`中将发生短信的功能封装到一个`spring bean`组件中，并将短信相关参数提取出来，发到配置文件中，利用`@ConfigurationProperties(prefix = "spring.cloud.alicloud.sms")`将组件与具体配置进行绑定

```java
@ConfigurationProperties(prefix = "spring.cloud.alicloud.sms")
@Data
@Component
public class SmsComponent {

    private String host;
    private String path;
    private String skin;
    private String sign;

    private String appcode;

    public void sendSmsCode(String phone,String code){
        String method = "GET";
        String appcode = "xxx";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("code", code);
        querys.put("phone", phone);
        querys.put("skin", skin);
        querys.put("sign", sign);
        //JDK 1.8示例代码请在这里下载：  http://code.fegine.com/Tools.zip

        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString()); // 如不输出json, 请打开这行代码，打印调试头部状态码。
            //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

3.2 在第三方服务`gulimall-third-party`的配置文件`application.yml`中添加配置：

```yaml
spring:
    alicloud:
      sms:
        host: https://smsmsgs.market.alicloudapi.com
        path: /sms/
        skin: 1
        sign: 1
        appcode: xxx
```

3.3测试组件：

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class GulimallThirdPartyApplicationTests {

    @Autowired
    SmsComponent smsComponent;

    @Test
    public void testSendCode(){
        smsComponent.sendSmsCode("17512080612","78495");
    }
}
```

4.在第三方服务`gulimall-third-party`添加短信发生的接口，提供给别的服务进行调用

```java
@RestController
@RequestMapping("/sms")
public class SmsSendController {
    @Autowired
    SmsComponent smsComponent;
    /**
     * 提供给别的服务进行调用
     */
    @GetMapping("/sendcode")
    public R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code) {
        smsComponent.sendSmsCode(phone, code);
        return R.ok();
    }
}
```

在认证服务`gulimall-auth-server`的`feign`目录下添加`feign`的远程调用接口`ThirdPartFeignService`：

```java
@FeignClient("gulimall-third-party")
public interface ThirdPartFeignService {

    @GetMapping("/sms/sendcode")
    public R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code);
}
```

在认证服务`gulimall-auth-server`的`LoginController`中添加`获取短信验证码`的接口供前端调用：

```java
@Controller
public class LoginController {
    @Autowired
    ThirdPartFeignService thirdPartFeignService;
    /**
     * 获取短信验证码
     * @param phone
     * @return
     */
    @ResponseBody
    @GetMapping("/sms/sendcode")
    public R sendCode(@RequestParam("phone") String phone){
        // 验证码
        String code = UUID.randomUUID().toString().substring(0, 5);
        thirdPartFeignService.sendCode(phone,code);
        return R.ok();
    }
}
```

前端：

```js
<div class="register-box">
    <label class="other_label">
        <span>中国 0086∨</span>
        <input name="phone" class="phone" id="phoneNum" maxlength="20" type="text" placeholder="建议使用常用手机">
    </label>
    <div class="tips" style="color:red"
         th:text="${errors!=null?(#maps.containsKey(errors, 'phone')?errors.phone:''):''}">

    </div>
</div>

<div class="register-box">
    <label class="other_label">验 证 码
        <input name="code" maxlength="20" type="text" placeholder="请输入验证码" class="caa">
    </label>
    <a id="sendCode"> 发送验证码 </a>
    <div class="tips" style="color:red"
         th:text="${errors!=null?(#maps.containsKey(errors, 'code')?errors.code:''):''}">
    </div>
</div>
<script type="text/javascript" charset="utf-8">
    $(function () {
        $("#sendCode").click(function () {
            //2、倒计时效果
            if ($(this).hasClass("disabled")) {
				// 如果class为disabled就说明已经在倒计时，此时不会调用倒计时函数
                //正在倒计时。
            } else {
                //1、给指定手机号发送验证码
                $.get("/sms/sendcode?phone=" + $("#phoneNum").val(), function (data) {
                    if (data.code != 0) {
                        alert(data.msg);
                    }
                });
                timeoutChangeStyle();
            }
        });
    })
    var num = 60;

    function timeoutChangeStyle() {
        // 函数开始执行后更改class为disabled，如果正在倒计时的话就不会被再次调用
        $("#sendCode").attr("class", "disabled");
        if (num == 0) {
            $("#sendCode").text("发送验证码");
            num = 60;
            $("#sendCode").attr("class", "");
        } else {
            var str = num + "s 后再次发送";
            $("#sendCode").text(str);
            setTimeout("timeoutChangeStyle()", 1000);
        }
        num--;
    }
</script>
```

问题：1.虽然倒计时时60秒，但是可以刷新浏览器访问实现在60秒前重新发送验证码。为了解决这个问题，就需要通过一定措施来防止用户通过浏览器重刷  2.验证码要设置为有时效的，在超过指定时间后，验证码应当不再有效

解决：利用`redis`缓存验证码实现对验证码的保存和验证，同时防止同一个phone在60秒内再次发送验证码

在认证服务`gulimall-auth-server`的引入`redis`依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

添加配置`application.properties`：

```properties
spring.redis.host=192.168.56.10
spring.redis.port=6379
```

在`gulimall-common`包中添加常量`AuthServerConstant`：

```java
public class AuthServerConstant {
    public static final String SMS_CODE_CACHE_PREFIX = "sms:code:";
    public static final String LOGIN_USER = "loginUser";
}
```

认证服务`gulimall-auth-server`短信验证码功能实现：

```java
@Controller
public class LoginController {
    @Autowired
    ThirdPartFeignService thirdPartFeignService;
    @Autowired
    StringRedisTemplate redisTemplate;
    /**
     * 获取短信验证码
     */
    @ResponseBody
    @GetMapping("/sms/sendcode")
    public R sendCode(@RequestParam("phone") String phone) {
        //TODO 1、接口防刷。
        //60秒内不能再发
        String redisCode = redisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone);
        if (!StringUtils.isEmpty(redisCode)) {
            long l = Long.parseLong(redisCode.split("_")[1]);
            if (System.currentTimeMillis() - l < 60000) {
                //60秒内不能再发
                return R.error(BizCodeEnum.SMS_CODE_EXCEPTION.getCode(), BizCodeEnum.SMS_CODE_EXCEPTION.getMsg());
            }
        }
        //2、验证码的再次校验。redis。存key-phone，value-code   sms:code:17512080612 -> 45678
        String code = UUID.randomUUID().toString().substring(0, 5);
        String substring = code + "_" + System.currentTimeMillis();
        //redis缓存验证码，防止同一个phone在60秒内再次发送验证码

        redisTemplate.opsForValue().set(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone, substring, 10, TimeUnit.MINUTES);

        thirdPartFeignService.sendCode(phone, code);
        return R.ok();
    }   
}
```

#####  1.3.4 注册信息保存

######   1.3.4.1  MD5&MD5盐值加密

- **可逆加密**：
  - 如果找到加密算法，通过密文可以推算出明文

- **不可逆加密**：
  - 即使知道加密算法，通过密文也无法推算出明文

- **MD5**:
  - `Message Digest algorithm 5`，信息摘要算法
  - 压缩性:任意长度的数据，算出的MD5值长度都是固定的
  - 容易计算:从原数据计算出MD5值很容易
  - 抗修改性:对原数据进行任何改动，哪怕只修改1个字节，所得到的MD5值都有很大区别
  - 强抗碰撞:想找到两个不同的数据，使它们具有相同的MD5值，是非常困难的
  - 不可逆
- **加盐**:
  - 通过生成随机数与`MD5`生成字符串进行组合
  - 数据库同时存储`MD5`值与`salt`值。验证正确性时使用`sal`t进行`MD5`即可
- **MD5加密**：`123456`通过`MD5`加密后变成`e10adc3949ba59abbe56e057f20f883e`。如果知道密文`e10adc3949ba59abbe56e057f20f883e`，通过网上的一些破解程序也能反向得到密码是`123456`。所以**MD5不能直接进行密码的加密存储**，通常储存`MD5`时还应该拼接一段随机数，也就是对其`加盐`

```java
@Test
public void textMD5() {
    //抗修改性:  彩虹表。123456->xxxx   234567->dddd
    String s = DigestUtils.md5Hex("123456");
    System.out.println(s); // e10adc3949ba59abbe56e057f20f883e
    //MD5不能直接进行密码的加密存储;
}
```

- 盐值加密：MD5不能直接进行密码的加密存储，`md5Crypt`方法对`MD5`进行盐值加密

```java
    @Test
    public void textMd5Crypt() {
        //抗修改性:  彩虹表。123456->xxxx   234567->dddd
        String s = DigestUtils.md5Hex("123456");
        System.out.println(s); // e10adc3949ba59abbe56e057f20f883e

        //盐值加密：md5Crypt方法
        // md5Crypt方法参数：md5Crypt(MD5字符串) ，默认盐值是$1$+8位字符
        String s1 = Md5Crypt.md5Crypt("123456".getBytes());
        System.out.println(s1);// $1$K9RVSJWw$nY2IL4O2HDch7x4iFi06U/

        // md5Crypt方法参数：md5Crypt(MD5字符串,盐值)，指定盐值
        String s2 = Md5Crypt.md5Crypt("123456".getBytes(), "$1$qqqqqqqq");
        System.out.println(s2);// $1$qqqqqqqq$AZofg3QwurbxV3KEOzwuI1
    }
```

- 如何验证用户提交的密码是否正确？
  - 用户第一次设置密码时候，将用户提交的密码进行`MD5`加密，生成一个`随机盐值`并将对`md5`进行`盐值加密`，同时将`随机盐值`和`盐值加密`生成的密码保存到数据库的`salt`、`password`字段
  - 当下一次用户登录时，将用户提供的密码进行`MD5`加密，同时从数据库查到该用户的`MD5`盐值，利用`md5Crypt`方法对`MD5`再次进行盐值加密，如果用户提供的密码经过`MD5`加密和`盐值加密`后和数据库中的`password`一致的话，就说明该用户提供的密码是正确的
  - 如果每次都往数据库存取盐值的话也比较麻烦，spring提供的`BCryptPasswordEncoder`可以直接将明文解析为密文，相同明文可能生成不同的密文，但是这些密文都对应一个明文

```java
    @Test
    public void textBCryptPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
        // 第一次encode：$2a$10$.URsevohNUxedjP/7NRMkuxiUHwKQU9TIhC7oWni42RiWeYgrtDCO
        // 第二次encode：$2a$10$cYdG1Ov3OWv5gtUq.coPyewyLnta/fas/o55JKbZmITcvSUXVP0gu
        // 每次encode都是不同的


        // passwordEncoder.matches(提交的密码，数据库储存的密文)
        boolean matches1 = passwordEncoder.matches("123456", "$2a$10$.URsevohNUxedjP/7NRMkuxiUHwKQU9TIhC7oWni42RiWeYgrtDCO");
        boolean matches2 = passwordEncoder.matches("123456", "$2a$10$cYdG1Ov3OWv5gtUq.coPyewyLnta/fas/o55JKbZmITcvSUXVP0gu");

        // 多次生成的不同密文都能够匹配上密码
        System.out.println(encode + "=>" + matches1);
        // $2a$10$hzGcivxZggHeVUtEPSmE.uR/oB/g4uWQsj4I6INd8hshj84/JuYri=>true
        System.out.println(encode + "=>" + matches2);
        // $2a$10$hzGcivxZggHeVUtEPSmE.uR/oB/g4uWQsj4I6INd8hshj84/JuYri=>true

        // 原因：根据密码生成密文就已经在密文中融合了盐值，在验证时可以从密文中解析出盐值，对密码进行校验
    }
```

######  1.3.4.2 注册功能实现

1.完善登录页面`reg.html`，提供用户登录信息表单：

```js
<form action="/regist" method="post" class="one">
    <div class="tips" style="color:red"
         th:text="${errors!=null?(#maps.containsKey(errors, 'msg')?errors.msg:''):''}">
    </div>
    <div class="register-box">
        <label class="username_label">用 户 名
            <input name="userName" maxlength="20" type="text" placeholder="您的用户名和登录名">
        </label>
        <div class="tips" style="color:red"
             th:text="${errors!=null?(#maps.containsKey(errors, 'userName')?errors.userName:''):''}">
        </div>
    </div>
    <div class="register-box">
        <label class="other_label">设 置 密 码
            <input name="password" maxlength="20" type="password" placeholder="建议至少使用两种字符组合">
        </label>
        <div class="tips" style="color:red"
             th:text="${errors!=null?(#maps.containsKey(errors, 'password')?errors.password:''):''}">
        </div>
    </div>
    <div class="register-box">
        <label class="other_label">确 认 密 码
            <input maxlength="20" type="password" placeholder="请再次输入密码">
        </label>
        <div class="tips">
        </div>
    </div>
    <div class="register-box">
        <label class="other_label">
            <span>中国 0086∨</span>
            <input name="phone" class="phone" id="phoneNum" maxlength="20" type="text" placeholder="建议使用常用手机">
        </label>
        <div class="tips" style="color:red"
             th:text="${errors!=null?(#maps.containsKey(errors, 'phone')?errors.phone:''):''}">
        </div>
    </div>
    <div class="register-box">
        <label class="other_label">验 证 码
            <input name="code" maxlength="20" type="text" placeholder="请输入验证码" class="caa">
        </label>
        <a id="sendCode"> 发送验证码 </a>
        <div class="tips" style="color:red"
             th:text="${errors!=null?(#maps.containsKey(errors, 'code')?errors.code:''):''}">
        </div>
    </div>
    <div class="arguement">
        <input type="checkbox" id="xieyi"> 阅读并同意
        <a href="/static/reg/#">《谷粒商城用户注册协议》</a>
        <a href="/static/reg/#">《隐私政策》</a>
        <div class="tips">
        </div>
        <br/>
        <div class="submit_btn">
            <button type="submit" id="submit_btn">立 即 注 册</button>
        </div>
    </div>
</form>
```

2.封装用户提交的表单用户信息`UserRegistVo`:

```java
@Data
public class UserRegistVo {
    @NotEmpty(message = "用户名必须提交")
    @Length(min = 6,max = 18,message = "用户名必须是6-18位字符")
    private String userName;
    @NotEmpty(message = "密码必须填写")
    @Length(min = 6,max = 18,message = "密码必须是6-18位字符")
    private String password;
    @NotEmpty(message = "手机号必须填写")
    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$",message = "手机号格式不正确")
    private String phone;
    @NotEmpty(message = "验证码必须填写")
    private String code;
}
```

3.认证服务需要将用户的注册信息保存到会员服务`gulimall-member`中，所以需要在会员服务`gulimall-member`中添加相应接口来实现用户信息的保存

3.1`MemberController`：会员服务`gulimall-member`中添加相应接口来实现用户信息的保存

```java
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @PostMapping("/regist")
    public R regist(@RequestBody MemberRegistVo vo) {

        try {
            memberService.regist(vo);
        } catch (PhoneExsitException e) {
            return R.error(BizCodeEnum.PHONE_EXIST_EXCEPTION.getCode(), BizCodeEnum.PHONE_EXIST_EXCEPTION.getMsg());
        } catch (UsernameExistException e) {
            return R.error(BizCodeEnum.USER_EXIST_EXCEPTION.getCode(), BizCodeEnum.USER_EXIST_EXCEPTION.getMsg());
        }

        return R.ok();
    }
}
```

3.2`MemberService`类：会员服务`gulimall-member`中添加相应接口来实现用户信息的保存

```java
public interface MemberService extends IService<MemberEntity> {
    void regist(MemberRegistVo vo);
    void checkPhoneUnique(String phone) throws PhoneExsitException;
    void checkUsernameUnique(String username) throws UsernameExistException;
}
```

3.3`MemberServiceImpl`实现类：会员服务`gulimall-member`中添加相应接口来实现用户信息的保存

```java
@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {
    @Autowired
    private MemberLevelDao memberLevelDao;
    @Override
    public void regist(MemberRegistVo vo) {
        MemberDao memberDao = this.baseMapper;
        MemberEntity entity = new MemberEntity();

        //设置默认等级
        MemberLevelEntity levelEntity = memberLevelDao.getDefaultLevel();
        entity.setLevelId(levelEntity.getId());

        //检查用户名和手机号是否唯一。为了让controller能感知异常，异常机制
        checkPhoneUnique(vo.getPhone());
        checkUsernameUnique(vo.getUserName());

        entity.setMobile(vo.getPhone());
        entity.setUsername(vo.getUserName());

        entity.setNickname(vo.getUserName());

        //密码要进行加密存储。
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(vo.getPassword());
        entity.setPassword(encode);
        //其他的默认信息
        //保存
        memberDao.insert(entity);
    }

    @Override
    public void checkPhoneUnique(String phone) throws PhoneExsitException {
        MemberDao memberDao = this.baseMapper;
        Integer mobile = memberDao.selectCount(new QueryWrapper<MemberEntity>().eq("mobile", phone));
        if (mobile > 0) {
            throw new PhoneExsitException();
        }

    }

    @Override
    public void checkUsernameUnique(String username) throws UsernameExistException {
        MemberDao memberDao = this.baseMapper;
        Integer count = memberDao.selectCount(new QueryWrapper<MemberEntity>().eq("username", username));
        if (count > 0) {
            throw new UsernameExistException();
        }
    }
}
```

3.4报错解决：如果之前没有添加会员信息或者中途删除了会员信息的话，调用会员服务时可能会报错，需要向会员服务的数据库表`ums_member_level`中添加会员等级信息，可以在后台管理系统中添加，也可以直接运行下面SQL

![image-20230730003452653](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307300034693.png)



```sql
insert into `ums_member_level` (`id`, `name`, `growth_point`, `default_status`, `free_freight_point`, `comment_growth_point`, `priviledge_free_freight`, `priviledge_member_price`, `priviledge_birthday`, `note`) values('1','普通会员','0','0','299.0000','10','0','0','0','初级会员');
insert into `ums_member_level` (`id`, `name`, `growth_point`, `default_status`, `free_freight_point`, `comment_growth_point`, `priviledge_free_freight`, `priviledge_member_price`, `priviledge_birthday`, `note`) values('2','铜牌会员','3000','0','198.0000','30','1','1','1','铜牌会员');
insert into `ums_member_level` (`id`, `name`, `growth_point`, `default_status`, `free_freight_point`, `comment_growth_point`, `priviledge_free_freight`, `priviledge_member_price`, `priviledge_birthday`, `note`) values('3','银牌会员','5000','0','100.0000','50','1','1','1','银牌会员');
```

4.在认证服务`gulimall-auth-server`中添加`feign`调用接口`MemberFeignService`

```java
@FeignClient("gulimall-member")
public interface MemberFeignService {
    @PostMapping("/member/member/regist")
    R regist(@RequestBody UserRegistVo vo);
}
```

5.在认证服务`gulimall-auth-server`中添加`LoginController`，实现对表单数据的校验和对验证码的验证、以及验证失败\验证成功时的跳转

```java
@Controller
public class LoginController {
    @Autowired
    private ThirdPartFeignService thirdPartFeignService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private MemberFeignService memberFeignService;

    /**
     * //TODO 重定向携带数据，利用session原理。将数据放在session中。只要跳到下一个页面取出这个数据以后，session里面的数据就会删掉
     * <p>
     * //TODO 1、分布式下的session问题。
     * RedirectAttributes redirectAttributes：模拟重定向携带数据
     * @param vo
     * @param result
     * @param redirectAttributes
     */
    @PostMapping("/regist")
    public String regist(@Valid UserRegistVo vo, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session) {
        // 如果校验出错，就把错误信息发展到errors中再转发到注册页
        if (result.hasErrors()) {
            /**
             * .map(fieldError -> {
             *                 String field = fieldError.getField();
             *                 String defaultMessage = fieldError.getDefaultMessage();
             *                 errors.put(field,defaultMessage);
             *                 return
             *             })
             */
            Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
//            model.addAttribute("errors",errors);
            redirectAttributes.addFlashAttribute("errors", errors); // RedirectAttributes redirectAttributes：模拟重定向携带数据
            //Request method 'POST' not supported
            //用户注册->/regist[post]----》转发/reg.html（路径映射默认都是get方式访问的。）

//            session.set
            // 如果校验出错，转发到注册页
            return "redirect:http://auth.gulimall.com/reg.html";
        }

        //1、校验验证码
        String code = vo.getCode();

        String s = redisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
        if (!StringUtils.isEmpty(s)) {
            if (code.equals(s.split("_")[0])) {
                //验证成功后删除验证码;令牌机制
                redisTemplate.delete(AuthServerConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
                //验证码通过。 //真正注册。调用远程服务进行注册
                R r = memberFeignService.regist(vo);
                if (r.getCode() == 0) {
                    //成功
                    return "redirect:http://auth.gulimall.com/login.html";
                } else {
                    Map<String, String> errors = new HashMap<>();
                    errors.put("msg", r.getData("msg", new TypeReference<String>() {
                    }));
                    redirectAttributes.addFlashAttribute("errors", errors);
                    return "redirect:http://auth.gulimall.com/reg.html";
                }

            } else {
                Map<String, String> errors = new HashMap<>();
                errors.put("code", "验证码错误");
                redirectAttributes.addFlashAttribute("errors", errors);
                return "redirect:http://auth.gulimall.com/reg.html";
            }
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("code", "验证码错误");
            redirectAttributes.addFlashAttribute("errors", errors);
            //校验出错，转发到注册页
            return "redirect:http://auth.gulimall.com/reg.html";
        }
    }
}
```

###  1.4 用户登录功能

1.在认证服务`gulimall-auth-server`中封装登录信息`Vo`

```java
@Data
public class UserLoginVo {
    private String loginacct;
    private String password;
}
```

2.在认证服务`gulimall-auth-server`的登录页`login.html`中编写登录页面的表单 ：

```js
<form action="/login" method="post">
    <div style="color: red" th:text="${errors!=null?(#maps.containsKey(errors, 'msg')?errors.msg:''):''}"></div>
    <ul>
        <li class="top_1">
            <img src="/static/login/JD_img/user_03.png" class="err_img1"/>
            <input type="text" name="loginacct" placeholder=" 邮箱/用户名/已验证手机" class="user"/>
        </li>
        <li>
            <img src="/static/login/JD_img/user_06.png" class="err_img2"/>
            <input type="password" name="password" placeholder=" 密码" class="password"/>
        </li>
        <li class="bri">
            <a href="/static/login/">忘记密码</a>
        </li>
        <li class="ent">
            <button class="btn2" type="submit"><a>登 &nbsp; &nbsp;录</a></button>
        </li>
    </ul>
</form>
```

3.登录时认证服务`gulimall-auth-server`需要调用会员服务`gulimall-member`来判断用户是否是系统的注册用户，所以要在`gulimall-member`服务中编写相应接口进行验证

3.1 在`gulimall-member`中封装`认证服务`传过来的登录信息`MemberLoginVo`

```java
@Data
public class MemberLoginVo {
    private String loginacct;
    private String password;
}
```

3.2`gulimall-member`下的`MemberController`:

```java
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @PostMapping("/login")
    public R login(@RequestBody MemberLoginVo vo) {

        MemberEntity entity = memberService.login(vo);
        if (entity != null) {
            return R.ok().setData(entity);
        } else {
            return R.error(BizCodeEnum.LOGINACCT_PASSWORD_INVAILD_EXCEPTION.getCode(), BizCodeEnum.LOGINACCT_PASSWORD_INVAILD_EXCEPTION.getMsg());
        }
    }
}
```

3.3`gulimall-member`下的`MemberService`:

```java
public interface MemberService extends IService<MemberEntity> {
    MemberEntity login(MemberLoginVo vo);
}
```

3.4 `gulimall-member`下的 `MemberServiceImpl`实现类:

```java
@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Override
    public MemberEntity login(MemberLoginVo vo) {
        String loginacct = vo.getLoginacct();
        String password = vo.getPassword(); //123456

        //1、去数据库查询 SELECT * FROM `ums_member` WHERE username=? OR mobile=?
        MemberDao memberDao = this.baseMapper;
        MemberEntity entity = memberDao.selectOne(new QueryWrapper<MemberEntity>().eq("username", loginacct)
                .or().eq("mobile", loginacct));
        if (entity == null) {
            //登录失败
            return null;
        } else {
            //1、获取到数据库的password  $2a$10$2xOI1.2DTQxpWeWd3Rk0qOVPTpauodlYkafTjNb4LOMuS1zBEZc5K
            String passwordDb = entity.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            //2、密码匹配
            boolean matches = passwordEncoder.matches(password, passwordDb);
            if (matches) {
                return entity;
            } else {
                return null;
            }
        }
    }

}
```

4.在认证服务`gulimall-auth-server`下定义`feign`远程调用接口`MemberFeignService`：

```java
@FeignClient("gulimall-member")
public interface MemberFeignService {
    @PostMapping("/member/member/login")
    R login(@RequestBody UserLoginVo vo);
}
```

5.`LoginController`：认证服务`gulimall-auth-server`调用会员服务`gulimall-member`进行登录信息验证，并将错误信息返回给页面

```java
@Controller
public class LoginController {
    @Autowired
    private MemberFeignService memberFeignService;
    @PostMapping("/login")
    public String login(UserLoginVo vo, RedirectAttributes redirectAttributes, HttpSession session) {
        //远程登录
        R login = memberFeignService.login(vo);
        if (login.getCode() == 0) {

            MemberRespVo data = login.getData("data", new TypeReference<MemberRespVo>() {
            });
            ////成功放到session中
            session.setAttribute(AuthServerConstant.LOGIN_USER, data);
            return "redirect:http://gulimall.com";
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("msg", login.getData("msg", new TypeReference<String>() {
            }));
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:http://auth.gulimall.com/login.html";
        }
    }
}
```

6.测试：输入密码登录成功后跳转到商品服务`gulimall-product`首页

###  1.5 社交登录功能

#####  1.5.1 社交登录

QQ、微博、github等网站的用户量非常大，别的网站为了简化自我网站的登陆与注册逻辑，引入社交登陆功能

- 社交登录步骤(以QQ为例)：

  - 1.用户点击QQ按钮

  - 2.引导跳转到QQ授权页
  - 3.用户主动点击授权，跳回之前网页

#####  1.5.2 OAuth2.0

- **OAuth**: OAuth(开放授权）是一个开放标准，允许用户授权第三方网站访问他们存储在另外的服务提供者上的信息,而不需要将用户名和密码提供给第三方网站或分享他们数据的所有内容
- **OAuth2.0**:对于用户相关的OpenAPI（例如获取用户信息，动态同步，照片，日志，分享等，为了保护用户数据的安全和隐私，第三方网站访问用户数据前都需要显式的向用户征求授权
- **OAuth2.0官方版流程**:
  - (A)用户打开客户端以后，客户端要求用户给予授权
  - (B）用户同意给予客户端授权
  - (C）客户端使用上一步获得的授权，向认证服务器申请令牌
  - (D)认证服务器对客户端进行认证以后，确认无误，同意发放令牌
  - (E)客户端使用令牌，向资源服务器申请获取资源
  - (F）资源服务器确认令牌无误，同意向客户端开放资源
  - 流程图示例：

![image-20230730212734014](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307302127075.png)

- **OAuth2.0社交登录原理**：

![image-20230730212919846](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307302129885.png)

#####  1.5.3 微博登陆准备工作

######  1.5.3.1 登录微博开放平台 & 注册应用

1.进入微博开放平台：百度`微博开放平台`,点击进去后登录微博账号，点击`微连接`(地址`https://open.weibo.com/connect`),填写相应的开发者认证信息，认证完成后点击`微连接`中的`网站接入`

![image-20230730222035805](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202307302220841.png)           



2.登陆微博，进入微连接，选择网站接入



![image-20230803080649014](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308030806098.png)



3.配置回调地址：授权回调页: `http://gulimall.com/success`      取消授权回调页: `http://gulimall.com/fail`

![image-20230803080708380](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308030807432.png)

######   1.5.3.2 微博社交登录接口调用

一、微博社交登录接口调用相关文档： `OAuth2.0授权认证`文档，`https://open.weibo.com/wiki/授权机制说明`

![image-20230803081354765](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308030813796.png)



二、实现微博社交登录并调用相关接口主要包含如下四个步骤：

**1.引导需要授权的用户到如下地址：**

```
https://api.weibo.com/oauth2/authorize?client_id=YOUR_CLIENT_ID&response_type=code&redirect_uri=YOUR_REGISTERED_REDIRECT_URI
```

其中的`client_id`对应之前注册应用的`appKey`,`redirect_uri`对应之前配置的授权回调页

![image-20230803083819844](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308030838876.png)

在`login.html`中添加如下地址，将用户引导到授权登录页

```js
<li>
    <a href="https://api.weibo.com/oauth2/authorize?client_id=2636917288&response_type=code&redirect_uri=http://gulimall.com/success">
        <img style="width: 50px;height: 18px" src="/static/login/JD_img/weibo.png"/>
    </a>
</li>
```

在微博提供的第三方认证登录页面输入自己的微博账号和密码，就可以跳转到授权回调页`http://gulimall.com/success`

![image-20230803085219575](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308030852623.png)



2.**如果用户同意授权，页面跳转至 `YOUR_REGISTERED_REDIRECT_URI/?code=CODE`**

此处`code`的作用是用来获取`Access Token`,每个`code`只能使用一次

![image-20230805040758286](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308050408327.png)



3.**换取`Access Token`：利用2中获取的`code`去换取`Access Token`** (接口文档地址：`https://open.weibo.com/wiki/Oauth2/access_token`)

URL:`https://api.weibo.com/oauth2/access_token`

请求参数：

| 参数          | 必选 | 类型及范围 | 说明                                                         |
| ------------- | ---- | ---------- | ------------------------------------------------------------ |
| client_id     | true | string     | 第三方应用在微博开放平台注册的APPKEY                         |
| client_secret | true | string     | 在微博开放平台注册的应用所对应的AppSecret                    |
| grant_type    | true | string     | 请求的类型，需填写authorization_code                         |
| code          | true | string     | 调用第一步authorize接口所获得的授权code                      |
| redirect_uri  | true | string     | 授权回调地址，传的值需与在开放平台网站填写的回调地址—致，设置填写位置:“我的应用>应用信息>高级信息" |

拼接上请求参数以后完整访问路径如下：

```
https://api.weibo.com/oauth2/access_token?client_id=YOUR_CLIENT_ID&client_secret=YOUR_CLIENT_SECRET&grant_type=authorization_code&redirect_uri=YOUR_REGISTERED_REDIRECT_URI&code=CODE
```

说明：其中`client_id=YOUR_CLIENT_ID&client_secret=YOUR_CLIENT_SECRET`可以使用`basic`方式加入`header`中

接口测试：将上面获取`Access Token`的访问地址拷贝到`postman`进行模拟，并将对应的参数替换成我们注册应用时的信息，将`code`换成2(上一个步骤)中获取到的`code`值(请求方式为`GET`时报错，查看文档说明可知此接口为的请求方式要求为`POST`)

![image-20230805112517397](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308051125454.png)

返回结果：

```json
{
    "access_token": "ACCESS_TOKEN",
    "expires_in": "7200",
    "remind_in": "7200",
    "uid": "1404376560"
}
```

返回字段说明

| 返回字段     | 字段类型 | 字段说明                                                     |
| ------------ | -------- | ------------------------------------------------------------ |
| access_token | string   | 用户授权的唯—票据，用于调用微博的开放接口，同时也是第三方应用验证微博用户登录的唯—票据，第三方应用应该对该票据进行校验，校验方法为调用oauth2lget_token_info接口，对比返回的授权信息中的APPKEY是否正确—致，然后用access_token 与自己应用内的用户建立唯—影射关系，来识别登录状态，不能只是简单的使用本返回值里的UID字段来做登录识别 |
| expires_in   | string   | access_token的生命周期，单位是秒数                           |
| uid          | string   | 授权用户的UID，本字段只是为了方便开发者，减少一次user/show接口调用而返回的，第三方应用不能用此字段作为用户登录状态的识别，只有access_token才是用户授权的唯一票据 |

4.**使用获得的`Access Token`调用`API`**：

获取到`Acess Token`之后，就可以携带这个`Acess Token`去访问微博提供的一些接口，具体调用方法见`接口管理`下面的`已有权限`中具体接口的定义

![image-20230805113022066](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308051130119.png)

测试：参照文档，利用`postman`获取当前用户的`uuid`

![image-20230805114933824](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308051149879.png)

三、总结

```
1.微博社交登录步骤：
        1.引导需要授权的用户到微博社交登录授权页面进行微博账号登陆验证：
        2.如果用户账号及密码验证成功，页面跳转至 YOUR_REGISTERED_REDIRECT_URI/?code=CODE
        3.利用2中获取的code换取Access Token
        4.使用获得的Access Token调用API
2.关于code和AccessToken：
        1.使用Code换取AccessToken,Code只能用一次
        2.同一个用户的accessToken一段时间是不会变化的，即使多次获取
```

#####  1.5.4 实现完整的社交登录功能

一、完整的社交登录流程：

![image-20230805172038873](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308051720929.png)



二、社交登录功能实现：

1.`OAuth2`授权第二步调用接口，用 `code`换取授权`access_token`，该步需在服务端完成。因为获取到`code`以后最好是让后端服务携带这个`code`去换取`AccessToken`，确保这个`accessToken`不被泄露 ,所以我们在认证服务`gulimall-auth-server` 添加`OAuth2Controller`  来实现`code`换取`Access Token`的功能。此处我们将回调地址改成`http://auth.gulimall.com/oauth2.0/weibo/success`，对应`OAuth2Controller`  中处理社交登录请求的URL

![image-20230805155421643](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308051554674.png)

2.在`gulimall-auth-server`认证服务中添`OAuth2Controller`类，用于处理用户社交登录授权成功以后的回调。处理逻辑：向微博服务器发起`http`请求，并将响应封装到`SocialUser`中，接着调用会员服务`gulimall-member`进行相应的用户登录和用户信息处理（其中的`HttpUtils`拷贝至`gulimall-third-party`）

```java
/**
 * 处理社交登录请求
 */
@Slf4j
@Controller
public class OAuth2Controller {
    @GetMapping("/oauth2.0/weibo/success")
    public String weibo(@RequestParam("code") String code, HttpSession session, HttpServletResponse servletResponse, HttpServletRequest request) throws Exception {
        Map<String, String> header = new HashMap<>();
        Map<String, String> query = new HashMap<>();
        Map<String,String> map = new HashMap<>();
        map.put("client_id","2636917288");
        map.put("client_secret","6a263e9284c6c1a74a62eadacc11b6e2");
        map.put("grant_type","authorization_code");
        map.put("redirect_uri","http://auth.gulimall.com/oauth2.0/weibo/success");
        map.put("code",code);
        //1、根据code换取accessToken；
        HttpResponse response = HttpUtils.doPost("https://api.weibo.com", "/oauth2/access_token", "post", header, query, map);

        //2、处理
        if (response.getStatusLine().getStatusCode() == 200) { // 响应的状态码是200说明请求成功
            //获取到了 accessToken
            String json = EntityUtils.toString(response.getEntity());
            SocialUser socialUser = JSON.parseObject(json, SocialUser.class);

            //知道当前是哪个社交用户
            //1）、当前用户如果是第一次进网站，自动注册进来(为当前社交用户生成一个会员信息账号，以后这个社交账号就对应指定的会员)
            //调用会员服务，判断这个用户有没有注册，进而登录或者注册这个社交用户
            R oauthlogin = memberFeignService.oauthlogin(socialUser);
            if (oauthlogin.getCode() == 0) {
                MemberRespVo data = oauthlogin.getData("data", new TypeReference<MemberRespVo>() {
                });
                log.info("登录成功：用户：{}", data.toString());
                return "redirect:http://gulimall.com";
            } else {
                return "redirect:http://auth.gulimall.com/login.html";
            }
        } else {
            // 响应的状态码不是200说明请求失败，跳转到登录页
            return "redirect:http://auth.gulimall.com/login.html";
        }
    }
}
```

`SocialUser`：封装微博服务器的响应结果

```java
@Data
public class SocialUser {
    private String access_token;
    private String remind_in;
    private long expires_in;
    private String uid;
    private String isRealName;
}
```

`HttpUtils`拷贝至`gulimall-third-party` 中调用阿里云短信服务的`HttpUtils`,所以需要引入相关依赖：

```xml
<!-- 导入org.apache.http.client.HttpClient依赖-->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.15</version>
</dependency>
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
    <version>4.2.1</version>
</dependency>
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpcore</artifactId>
    <version>4.2.1</version>
</dependency>
<dependency>
    <groupId>commons-lang</groupId>
    <artifactId>commons-lang</artifactId>
    <version>2.6</version>
</dependency>
<dependency>
    <groupId>org.eclipse.jetty</groupId>
    <artifactId>jetty-util</artifactId>
    <version>9.3.7.v20160115</version>
</dependency>
```

在`认证服务`中定义`feign`远程调用接口`MemberFeignService`：

```java
@FeignClient("gulimall-member")
public interface MemberFeignService {
    @PostMapping("/member/member/oauth2/login")
    R oauthlogin(@RequestBody SocialUser socialUser) throws Exception;
}
```

3.在`OAuth2Controller`需要调用会员服务`gulimall-member`进行相应的用户登录和用户信息处理，所以需要先在会员服务中实现相应的处理逻辑。要让社交登录的用户信息能够保存到本系统，就要求社交用户的相关信息能够和在会员服务`gulimall-member`进行存储和关联，所以此处修改数据库表 `ums_member`：添加`social_uid`、  `access_token`、 `expires_in`三个字段，最终数据库表如下

```sql
-- ----------------------------
-- Table structure for ums_member
-- ----------------------------
DROP TABLE IF EXISTS `ums_member`;
CREATE TABLE `ums_member` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `level_id` BIGINT(20) DEFAULT NULL COMMENT '会员等级id',
  `username` CHAR(64) DEFAULT NULL COMMENT '用户名',
  `password` VARCHAR(64) DEFAULT NULL COMMENT '密码',
  `nickname` VARCHAR(64) DEFAULT NULL COMMENT '昵称',
  `mobile` VARCHAR(20) DEFAULT NULL COMMENT '手机号码',
  `email` VARCHAR(64) DEFAULT NULL COMMENT '邮箱',
  `header` VARCHAR(500) DEFAULT NULL COMMENT '头像',
  `gender` TINYINT(4) DEFAULT NULL COMMENT '性别',
  `birth` DATE DEFAULT NULL COMMENT '生日',
  `city` VARCHAR(500) DEFAULT NULL COMMENT '所在城市',
  `job` VARCHAR(255) DEFAULT NULL COMMENT '职业',
  `sign` VARCHAR(255) DEFAULT NULL COMMENT '个性签名',
  `source_type` TINYINT(4) DEFAULT NULL COMMENT '用户来源',
  `integration` INT(11) DEFAULT NULL COMMENT '积分',
  `growth` INT(11) DEFAULT NULL COMMENT '成长值',
  `status` TINYINT(4) DEFAULT NULL COMMENT '启用状态',
  `create_time` DATETIME DEFAULT NULL COMMENT '注册时间',
  `social_uid` VARCHAR(255) DEFAULT NULL COMMENT '社交用户的唯一id',
  `access_token` VARCHAR(255) DEFAULT NULL COMMENT '访问令牌',
  `expires_in` VARCHAR(255) DEFAULT NULL COMMENT '访问令牌的时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='会员';
```

数据库表`ums_member`对应的会员实体类也要进行相应修改：

```java
@Data
@TableName("ums_member")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 会员等级id
	 */
	private Long levelId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 头像
	 */
	private String header;
	/**
	 * 性别
	 */
	private Integer gender;
	/**
	 * 生日
	 */
	private Date birth;
	/**
	 * 所在城市
	 */
	private String city;
	/**
	 * 职业
	 */
	private String job;
	/**
	 * 个性签名
	 */
	private String sign;
	/**
	 * 用户来源
	 */
	private Integer sourceType;
	/**
	 * 积分
	 */
	private Integer integration;
	/**
	 * 成长值
	 */
	private Integer growth;
	/**
	 * 启用状态
	 */
	private Integer status;
	/**
	 * 注册时间
	 */
	private Date createTime;
	private String socialUid;
	private String accessToken;
	private Long expiresIn;
}
```

`MemberController`：社交登录

```java
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    /**
     * 社交登录
     */
    @PostMapping("/oauth2/login")
    public R oauthlogin(@RequestBody SocialUser socialUser) throws Exception {
        MemberEntity entity =  memberService.login(socialUser);
        if(entity!=null){
            //TODO 1、登录成功处理
            return R.ok().setData(entity);
        }else{
            return R.error(BizCodeEnum.LOGINACCT_PASSWORD_INVAILD_EXCEPTION.getCode(),BizCodeEnum.LOGINACCT_PASSWORD_INVAILD_EXCEPTION.getMsg());
        }
    }
}
```

`MemberService`：社交登录

```java
public interface MemberService extends IService<MemberEntity> {
    // 社交登录
    MemberEntity login(SocialUser socialUser) throws Exception;
}
```

`MemberServiceImpl`：社交登录接口合并了登录和注册逻辑，如果社交登录的用户没有注册过就根据社交登录的相关信息进行注册，如果已经注册过就进行登录

```java
@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {
    /**
     * 社交登录
     * @param socialUser
     * @return
     * @throws Exception
     */
    @Override
    public MemberEntity login(SocialUser socialUser) throws Exception {
        //社交登录具有接口具有合并了登录和注册逻辑
        String uid = socialUser.getUid();
        //1、判断当前社交用户是否已经登录过系统；
        MemberDao memberDao = this.baseMapper;
        MemberEntity memberEntity = memberDao.selectOne(new QueryWrapper<MemberEntity>().eq("social_uid", uid));
        if (memberEntity != null) {
            //这个用户已经注册
            MemberEntity update = new MemberEntity();
            update.setId(memberEntity.getId());
            update.setAccessToken(socialUser.getAccess_token());
            update.setExpiresIn(socialUser.getExpires_in());

            memberDao.updateById(update);

            memberEntity.setAccessToken(socialUser.getAccess_token());
            memberEntity.setExpiresIn(socialUser.getExpires_in());
            return memberEntity;
        }else{
            //2、没有查到当前社交用户对应的记录我们就需要注册一个
            MemberEntity regist = new MemberEntity();
            try{
                //3、查询当前社交用户的社交账号信息（昵称，性别等）
                Map<String,String> query = new HashMap<>();
                query.put("access_token",socialUser.getAccess_token());
                query.put("uid",socialUser.getUid());
                HttpResponse response = HttpUtils.doGet("https://api.weibo.com", "/2/users/show.json", "get", new HashMap<String, String>(), query);
                if(response.getStatusLine().getStatusCode() == 200){
                    //查询成功
                    String json = EntityUtils.toString(response.getEntity());
                    JSONObject jsonObject = JSON.parseObject(json);
                    //昵称
                    String name = jsonObject.getString("name");
                    String gender = jsonObject.getString("gender");
                    //........
                    regist.setNickname(name);
                    regist.setGender("m".equals(gender)?1:0);
                    //........
                }
            }catch (Exception e){}
            regist.setSocialUid(socialUser.getUid());
            regist.setAccessToken(socialUser.getAccess_token());
            regist.setExpiresIn(socialUser.getExpires_in());
            memberDao.insert(regist);
            return regist;
        }
    }
}
```

测试：微博社交登录后直接跳转到商品服务首页`http://gulimall.com`，且没有注册过的用户也注册到`会员服务`

![image-20230805191029238](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308051910283.png)



###  1.6 spring session解决session共享问题

#####  1.6.1 遗留问题：页面信息共享

遗留问题：登陆后商品服务首页还是`请登录`字样，而我们希望登录成功以后页面显示`XXX已登录`。如果是单体应用的话，同一个单体应用下的不同页面进行跨页面共享数据的话可以使用`session`来实现，因为session是会话级别的，如果浏览器不关闭的话，整个访问期间session中的用户信息都可以获取到

<table align="center">
<tr>
<td >
<img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308052058247.png" > <b>遗留问题：登陆后没有显示登录用户的信息</b>          </td>
</tr>
</table>


尝试使用`session`来实现用户信息跨页面展示：在认证服务`gulimall-auth-server`中添加session对象，并将用户信息添加到session，将session添加到视图传给页面进行展示

```java
/**
 * 处理社交登录请求
 */
@Slf4j
@Controller
public class OAuth2Controller {
    @Autowired
    MemberFeignService memberFeignService;
    @GetMapping("/oauth2.0/weibo/success")
    public String weibo(@RequestParam("code") String code, HttpSession session, HttpServletResponse servletResponse, HttpServletRequest request) throws Exception {
        Map<String, String> header = new HashMap<>();
        Map<String, String> query = new HashMap<>();
        Map<String,String> map = new HashMap<>();
        map.put("client_id","2636917288");
        map.put("client_secret","6a263e9284c6c1a74a62eadacc11b6e2");
        map.put("grant_type","authorization_code");
        map.put("redirect_uri","http://auth.gulimall.com/oauth2.0/weibo/success");
        map.put("code",code);
        //1、根据code换取accessToken；
        HttpResponse response = HttpUtils.doPost("https://api.weibo.com", "/oauth2/access_token", "post", header, query, map);

        //2、处理
        if (response.getStatusLine().getStatusCode() == 200) { // 响应的状态码是200说明请求成功
            //获取到了 accessToken
            String json = EntityUtils.toString(response.getEntity());
            SocialUser socialUser = JSON.parseObject(json, SocialUser.class);

            //知道当前是哪个社交用户
            //1）、当前用户如果是第一次进网站，自动注册进来(为当前社交用户生成一个会员信息账号，以后这个社交账号就对应指定的会员)
            //调用会员服务，判断这个用户有没有注册，进而登录或者注册这个社交用户
            R oauthlogin = memberFeignService.oauthlogin(socialUser);
            if (oauthlogin.getCode() == 0) {
                MemberRespVo data = oauthlogin.getData("data", new TypeReference<MemberRespVo>() {
                });
                session.setAttribute("loginUser",data);
                log.info("登录成功：用户：{}", data.toString());
                return "redirect:http://gulimall.com";
            } else {
                return "redirect:http://auth.gulimall.com/login.html";
            }
        } else {
            // 响应的状态码不是200说明请求失败，跳转到登录页
            return "redirect:http://auth.gulimall.com/login.html";
        }
    }
}
```

尝试使用`session`来实现用户信息跨页面展示：在商品服务`gulimall-product`首页`index.html`获取`session`中的用户信息进行展示

```js
<li>
    <a  th:if="${session.loginUser!=null}">欢迎：[[${session.loginUser==null?'':session.loginUser.nickname}]]</a>
    <a href="http://auth.gulimall.com/login.html" th:if="${session.loginUser==null}">欢迎，请登录</a>
</li>
<li>
    <a th:if="${session.loginUser==null}" href="http://auth.gulimall.com/reg.html" class="li_2">免费注册</a>
</li>
```

测试后发现并没有显示用户名，原因是此处的商品服务和认证服务是两个不同的分布式应用。分布式架构下存在session共享问题！下面对session共享问题进行介绍

#####  1.6.2 session原理

`session`的原理：

![image-20230806123013644](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308061230685.png)

服务器A为了保存浏览器X、浏览器Y、浏览器Z与服务器A的某次会话的数据，会分别为浏览器X、浏览器Y、浏览器Z创建一个session对象，服务器A将所有创建的session放到`sessionManager`进行管理。假设用户a使用浏览器a访问了服务器a，服务器a为了保存会话期间的信息，会为浏览器a创建一个session用来保存用户信息，服务器创建session后会命令浏览器a保存一个cookie，这个cookie中携带一个jessionid，这个jessionid可以用来标识具体的session。每个Cookie中都携带着jessionid,每个Cookie都有作用域，具体的Cookie只对具体的域名起作用，可以在浏览器开发者模式的`application`中查看Cookie：



![image-20230806120935988](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308061209037.png)

`session`原理补充：`https://blog.csdn.net/m0_51545690/article/details/123384986`

#####  1.6.3 session共享问题一：分布式下session共享问题

**分布式下session共享问题**：在分布式项目中，即使是同域名下也会出现session共享问题。比如会员服务的域名虽然都是`member.gulimall.com`，但是多个会员服务部署在不同的服务器上，这几个服务器之间的session是无法共享的。假如第一次访问会员服务时被负载均衡到服务器A上，那用户信息被保存到服务器A上，假设下一次访问被负载均衡到了服务器B,由于服务器B没有包含用户信息的session，就无法获取到用户信息

![image-20230806125513106](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308061255140.png)



**Session共享问题解决方案一：session复制**

- session复制：创建session后服务器将session同步到所有相关的服务器上



![image-20230806130305389](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308061303483.png)

- session复制优点：
  -  web-server (Tomcat)原生支持，只需要修改配置文件

- session复制缺点：
  -  session同步需要数据传输，占用大量网络带宽，降低了服务器群的业务处理能力
  -  任意一台web-server保存的数据都是所有web-server的session总和，受到内存限制无法水平扩展更多的web-server
  -  大型分布式集群情况下，由于所有web-server都全量保存数据，所以此方案不可取



**Session共享问题解决方案二：客户端存储**

- 客户端存储：将sesion保存到客户端(保存到浏览器)

![image-20230806134957359](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308061349399.png)





- 客户端存储优点：
  - 服务器不需存储session，用户保存自己的session信息到cookie中。节省服务端资源
- 客户端存储缺点：
  - 都是缺点，这只是一种思路，这种方式不会使用
  - 每次http请求，携带用户在cookie中的完整信息，浪费网络带宽
  - session数据放在cookie中,cookie有长度限制4K，不能保存大量信息
  - session数据放在cookie中，存在泄漏、篡改、窃取等安全隐患



**Session共享问题解决方案三：hash一致性**

- **hash一致性**：利用负载均衡机制，浏览器a访问服务时，负载均衡器记录浏览器对应的ip，并将请求路由到服务器a。下一次浏览器a再次访问时，负载均衡根据IP的hash一致性，将请求路由继续路由到服务器a，就可以正确地获取到对应的session



![image-20230806135330297](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308061353389.png)

- hash一致性优点：
  - 只需要改nginx配置，不需要修改应用代码
  - 负载均衡，只要hash属性的值分布是均匀的，多台web-server的负载是均衡的
  - 可以支持web-server水平扩展(session同步法是不行的，受内存限制)
- hash一致性缺点：
  - session还是存在web-server中的，所以web-server重启可能导致部分session丢失，影响业务，如部分用户需要重新登录
  - 如果web-server水平扩展，rehash后session重新分布，也会有一部分用户路由不到正确的session
- 以上缺点问题也不是很大，因为session本来都是有效期的。所以这两种反向代理的方式可以使用



**session共享问题解决方案四：后端统一存储**

- 统一存储：后端使用MySQL、redis等数据库对session进行统一存储

![image-20230806141518627](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308061415708.png)



- 后端统一存储优点:
  - 没有安全隐患
  - 可以水平扩展，数据库/缓存水平切分即可
  - web-server重启或者扩容都不会有session丢失
- 后端统一存储不足
  - 增加了一次网络调用，并且需要修改应用代码;如将所有的getSession方法替换为从Redis查数据的方式。redis获取数据比内存慢很多
  - 上面缺点可以用`SpringSession`完美解决



#####  1.6.4 session共享问题二：session不能跨不同域名进行共享

1.上面没有成功获取到用户信息的原因：**session不能跨不同域名进行共享**，具体的Cookie只对具体的域名起作用。我们在认证服务`gulimall-auth-server`中登录成功，并将用户信息保存到session中，此时浏览器会保存对应的cookie，cookie中保存一个jseeionId用于标识session，这个cookie的作用域是`auth.gulimall.com` 这个域名。我们登陆成功后跳转到了商品服务`gulimall-product`的首页，而此时对应的域名是`gulimall.com`，在`gulimall.com`域名下获取不到域名`auth.gulimall.com` 下的cookie,所以无法获取到保存在session中的用户信息

2.不同域名下的session的共享问题解决：

cookie默认的Domain是当前系统域名，由于父域为`gulimall.com`，子域为 `auth.gulimall.com`  `order.gulimall.com`，我们可以修改cookie的作用域，将cookie的作用域从子域提升为父域。修改域名为父域名，即使是子域系统的cookie，也能让父域直接使用，各子域系统的之间的cookie就能进行共享，那么各系统之间都能够从相同的服务器获取到session,从而实现session的共享。当我们分拆服务，不同服务使用不同域名进行部署的时候，我们可以使用如下解决方案：放大cookie的作用域，实现效果如下图所示



![image-20230806142459414](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308061424497.png)





#####  1.6.5 `springsession`的核心原理

**springsession**：

- `SpringSession`考虑到了session的很多问题并进行了相应的解决，上面关于session共享的问题都可以通过`SpringSession`进行解决：`SpringSession`整合了利用`redis`对session进行后端存储的功能，同时还可以通过自定义配置来改变cookie的Domain,从而解决上面说到的session共享问题

**`SpringSession` 核心原理**

- `@EnableRedisHttpSession`注解导入了`RedisHttpSessionConfiguration`配置
- `@EnableRedisHttpSession`给容器中添加了`RedisOperationsSessionRepository`和`SessionRepositoryFilter` 组件：
  - `SessionRepository` ——> `RedisOperationsSessionRepository`。`RedisOperationsSessionRepository`的作用：封装了可以对session进行增删改查的类。能够利用`redis`操作`session`
  - `SessionRepositoryFilter` ——> 实质上就是`http Filter`,每个请求过来都必须经过filter, session存储过滤器。`SessionRepositoryFilter`创建的时候，就自动从容器中获取到了`SessionRepository`。原始的request，response都被包装。request被包装成`SessionRepositoryRequestWrapper`，response被包装成`SessionRepositoryResponseWrapper`。以后获取session都要调用`request.getSession()`，由于request被包装成`SessionRepositoryRequestWrapper`，调用`request.getSession()`就相当于调用`wrappedRequest.getSession()``wrappedRequest.getSession()`获取的session是从`SessionRepository` 中获取到的。`wrappedRequest`其实就是装饰者模式的体现
- `SpringSession`考虑到了session的很多问题并进行了相应的解决：比如只要浏览器不关闭，session过期后就会进行自动续期，而`SpringSession`也对此进行了实现，也就是服务器中的session进行更改或续期后，`SpringSession`也会操作`redis`进行session刷新或对session的过期时间进行续期
- 原理补充：见博客`https://www.cnblogs.com/duanxz/p/3471448.html`
- ``SessionRepositoryFilter`源码片段：

![image-20230806235351119](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308062353192.png)



#####  1.6.6 使用`SpringSession`解决session共享问题

1.`SpringSession`考虑到了session的很多问题并进行了相应的解决，上面关于session共享的问题都可以通过`SpringSession`进行解决：`SpringSession`整合了利用`redis`对session进行后端存储的功能，同时还可以通过自定义配置来改变cookie的Domain,从而解决上面说到的session共享问题

2.`SpringSession`技术文档：

- `SpringSession` 官方文档：`https://spring.io/projects/spring-session`

- `SpringSession` 整合redis文档： `https://docs.spring.io/spring-session/docs/2.2.1.RELEASE/reference/html5/guides/boot-redis.html`

3.整合SpringSession解决session共享问题：

在认证服务`gulimall-auth-server`添加`SpringSession`依赖和`redis`依赖：

```xml
<!-- 整合SpringSession完成session共享问题-->
<dependency>
    <groupId>org.springframework.session</groupId>
    <artifactId>spring-session-data-redis</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

在认证服务`gulimall-auth-server`的配置文件`application.properties`中添加配置，指定session的保存方式是redis，指定session的过期时间为30分钟

```properties
# 配置redis的连接信息
spring.redis.host=192.168.56.10
spring.redis.port=6379
# 指定session的保存方式是redis
spring.session.store-type=redis
# 指定session的过期时间为30分钟
server.servlet.session.timeout=30m
```

在启动类上添加注解`@EnableRedisHttpSession `

```java
@EnableRedisHttpSession  //整合redis作为session存储
@SpringBootApplication
public class GulimallAuthServerApplication {
	public static void main(String[] args) {
	SpringApplication.run(GulimallAuthServerApplication.class, args);
	}
}
```

想怎配置类`GulimallSessionConfig`：通过配置自定义cookie并指定作用域、 自定义redis保存session时的序列化方式

```java
@Configuration
public class GulimallSessionConfig {

    // 自定义cookie并修改相关参数
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        // 设置cookie的作用域为gulimall.com
        cookieSerializer.setDomainName("gulimall.com");
        // 更改cookie的键的名称
        cookieSerializer.setCookieName("GULISESSION");
        return cookieSerializer;
    }

    // 自定义redis保存session时的序列化方式
    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }
}
```

在controller中添加session，将用户信息放到session中传递给页面

```java
@Slf4j
@Controller
public class OAuth2Controller {
    @Autowired
    MemberFeignService memberFeignService;
    /**
     * 社交登录成功回调
     */
    @GetMapping("/oauth2.0/weibo/success")
    public String weibo(@RequestParam("code") String code, HttpSession session, HttpServletResponse servletResponse, HttpServletRequest request) throws Exception {
        Map<String, String> header = new HashMap<>();
        Map<String, String> query = new HashMap<>();


        Map<String, String> map = new HashMap<>();
        map.put("client_id", "2636917288");
        map.put("client_secret", "6a263e9284c6c1a74a62eadacc11b6e2");
        map.put("grant_type", "authorization_code");
        map.put("redirect_uri", "http://auth.gulimall.com/oauth2.0/weibo/success");
        map.put("code", code);
        //1、根据code换取accessToken；
        HttpResponse response = HttpUtils.doPost("https://api.weibo.com", "/oauth2/access_token", "post", header, query, map);
        //2、处理
        if (response.getStatusLine().getStatusCode() == 200) {
            //获取到了 accessToken
            String json = EntityUtils.toString(response.getEntity());
            SocialUser socialUser = JSON.parseObject(json, SocialUser.class);

            //知道当前是哪个社交用户
            //1）、当前用户如果是第一次进网站，自动注册进来(为当前社交用户生成一个会员信息账号，以后这个社交账号就对应指定的会员)
            //登录或者注册这个社交用户
            R oauthlogin = memberFeignService.oauthlogin(socialUser);
            if (oauthlogin.getCode() == 0) {
                MemberRespVo data = oauthlogin.getData("data", new TypeReference<MemberRespVo>() {
                });
                log.info("登录成功：用户：{}", data.toString());
                //1、第一次使用session；命令浏览器保存卡号。JSESSIONID这个cookie；
                //以后浏览器访问哪个网站就会带上这个网站的cookie；
                //子域之间； gulimall.com  auth.gulimall.com  order.gulimall.com
                //发卡的时候(指定域名为父域名)，即使是子域系统发的卡，也能让父域直接使用。
                //TODO 1、默认发的令牌。session=dsajkdjl。作用域：当前域；（解决子域session共享问题）
                //TODO 2、使用JSON的序列化方式来序列化对象数据到redis中
                session.setAttribute("loginUser", data);
//                new Cookie("JSESSIONID","dadaa").setDomain("");
//                servletResponse.addCookie();
                //2、登录成功就跳回首页
                return "redirect:http://gulimall.com";

            } else {
                return "redirect:http://auth.gulimall.com/login.html";
            }

        } else {
            return "redirect:http://auth.gulimall.com/login.html";
        }
    }
}
```

在商品服务`gulimall-product`的页面`index.html`中获取session数据

```js
<li>
    <a  th:if="${session.loginUser!=null}">欢迎：[[${session.loginUser==null?'':session.loginUser.nickname}]]</a>
    <a href="http://auth.gulimall.com/login.html" th:if="${session.loginUser==null}">欢迎，请登录</a>
</li>
<li>
    <a th:if="${session.loginUser==null}" href="http://auth.gulimall.com/reg.html" class="li_2">免费注册</a>
</li>
```

`gulimall-product`、`gulimall-search`等需要使用共享session的服务也按照上面的方法整合一下SpringSession

#####  1.6.7 页面优化

1.上面通过整合`springssion`解决了session共享问题，只要在认证服务`gulimall-auth-server`登录成功后，去`商品服务`就不用登录了。并且可以获取到在session中的用户信息。下面优化认证服务、商品服务的一些细节，并且将`检索服务gulimall-search`也进行`springsession`的整合，使得检索服务也能够获取到共享的session

2.修改`gulimall-product`的`item.html`：

```js
<li style="border: 0;">
    <a href="http://auth.gulimall.com/login.html" th:if="${session.loginUser==null}" class="aa">你好，请登录</a>
    <a  th:if="${session.loginUser!=null}" class="aa" style="width: 100px">[[${session.loginUser.nickname}]]</a>
</li>
<li th:if="${session.loginUser==null}"><a href="http://auth.gulimall.com/reg.html" style="color: red;">免费注册</a> |</li>
```

3.完善`gulimall-product`登录逻辑：如果在认证服务进行了登录，跳转到登录页的话就不需要重新跳到登录页，而是直接跳转到商品服务首页。

`GulimallWebConfig`:注释掉注册页的视图映射，新增逻辑对登录情况进行判断

```java
@Configuration
public class GulimallWebConfig implements WebMvcConfigurer {
    /**
     * 视图映射
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        /**
         *     @GetMapping("/login.html")
         *     public String loginPage(){
         *
         *         return "login";
         *     }
         */
//        registry.addViewController("/login.html").setViewName("login");
        //只是get请求能映射
        registry.addViewController("/reg.html").setViewName("reg");
    }
}
```

`LoginController`：注释掉注册页的视图映射，新增逻辑对登录情况进行判断

```java
@Controller
public class LoginController {
    // 登录页的逻辑
    @GetMapping("/login.html")
    public String loginPage(HttpSession session) {
        Object attribute = session.getAttribute(AuthServerConstant.LOGIN_USER);
        if (attribute == null) {
            //没登录
            return "login";
        } else {
            return "redirect:http://gulimall.com";
        }
    }
}
```

4.修改`gulimall-product`的`item.html`：

```js
<div class="nav_top_one" style="width:auto;">
    <a href="http://gulimall.com"><img src="/static/item/image/logo1.jpg"/></a></div>
<div class="nav_top_two"><input type="text"/><button>搜索</button></div>
```

5.对检索服务`gulimall-search`也进行`springsession`的整合,整合步骤省略(同1.6.6节)。修改检索服务首页`list.html`获取共享的session信息

```js
<li>
    <a th:if="${session.loginUser==null}" href="http://auth.gulimall.com/login.html"
       class="li_2">你好，请登录</a>
    <a th:if="${session.loginUser!=null}" style="width: 100px">[[${session.loginUser.nickname}]]</a>
</li>
<li>
    <a th:if="${session.loginUser == null}" href="http://auth.gulimall.com/reg.html">免费注册</a>
</li>
```

###  1.7 单点登录

#####  1.7.1 单点登录简介

前面整合`springsession`实现了一个效果：只要在商品服务`gulimall-product`的首页点击登录，跳转到登录页进行登录之后，就可以在认证服务、商品服务、检索服务获取到登录用户的信息。其中登录状态的用户信息被保存到了session中，session又被统一存储到了redis中，同时扩大了浏览器端cookie的作用域，可以使得session中的用户信息被父域下的多个子域共享，也就实现了多个系统对用户信息的共享。但是在更大的系统中，比如尚硅谷旗下存在多个系统，域名分别是`gulixueyuan.com`,`gulimall.com` 、`gulifunding.com`，那我们就不能通过扩大`cookie`作用域来实现session共享，因为扩大成`.com`的话岂不是任何网站都可以获取我们的用户信息了？那信息就被泄露了



在尚硅谷旗下有众多的系统，如果已经在尚硅谷的官方网站登录，到尚硅谷旗下的其他系统还要进行登录的话，用户体验就会很差。解决方案就是抽取一个认证中心，只要在认证中心进行了登录，尚硅谷旗下的其它任何系统都不用再登录，这个方案就是单点登录：



![image-20230807032005780](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308070320828.png)



**gitee开源的单点登录框架**：`https://gitee.com/xuxueli0323/xxl-sso?_from=gitee_search`

**单点登录核心**: 多个系统即使域名不一样，想办法给各个系统同步同一个用户的票据

- 1.提供一个专门的中央认证服务器`ssoserver.com`，所有的登录请求都应该发送给中央认证服务器
- 2.其他系统登录时都要去`ssoserver.com`进行登录验证，登录验证成功后跳转回当前登录系统
- 3.只要有一个系统登录成功，其他系统都不用重复登录
- 4.所有系统可能域名都不相同，但是各个系统能够统一一个用户标识`sso-sessionid`

#####  1.7.2 单点登录服务搭建

1.**配置域名映射**：

![image-20230810015017599](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308100150631.png)

2.**建立三个项目**：

![image-20230810015743731](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308100157758.png)

- 单点登录服务端： `gulimall-test-sso-server`  

  - 作用：模拟单点登录中的认证中心

  - 域名：`ssoserver.com`  端口：`8080`  

- 单点登录客户端1： `gulimall-test-sso-client1 `  
  - 作用：模拟任意需要登录的业务系统
  - 域名 `client1.com` 端口  `8081` 

- 单点登录客户端2 ： `gulimall-test-sso-client1 `  
  - 作用：模拟任意需要登录的业务系统
  - 域名 `client2.com` 端口  `8082` 

3.打通**第一步流程**：如果客户端没有登录的话就跳转到登录服务端的登录页

![image-20230810014222939](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308100142046.png)

客户端1： `gulimall-test-sso-client1 `  

`application.properties`

```properties
server.port=8081
sso.server.url=http://ssoserver.com:8080/login.html
```

`HelloController`

```java
@Controller
public class HelloController {
    @Value("${sso.server.url}")
    String ssoServerUrl;
    @GetMapping("/employees")
    public String employees(Model model, HttpSession session) {
        // 判断session中是否有当前会话的用户信息，如果有就是已经登录，否则没有登录
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser == null) { // 由于没登录，就要命令浏览器重定向到新位置
            // 没登录,跳转到登录服务器进行登录
            return "redirect:" + ssoServerUrl;
        } else {
            List<String> emps = new ArrayList<>();
            emps.add("张三");
            emps.add("李四");
            model.addAttribute("emps", emps);
            return "list";
        }
    }
}
```

`list.html`

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>员工列表</title>
</head>
<body>
    <h1>欢迎：[[${session.loginUser}]]</h1>
    <ul>
        <li th:each="emp:${emps}"> 姓名：[[${emp}]] </li>
    </ul>

</body>
</html>
```

单点登录服务端： `gulimall-test-sso-server`  

`application.properties`

```properties
server.port=8080
```

`LoginController`

```java
@Controller
public class LoginController {
    @GetMapping("/login.html")
    public String loginPage() {
        return "login";
    }
}
```

`login.html`

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页</title>
</head>
<body>
<form action="/doLogin" method="post">
    用户名: <input name="username"/><br/>
    密码： <input name="password"/><br/>
    <input type="submit" value="登录"/>
</form>
</body>
</html>
```

尝试访问客户端1的接口`http://client1.com:8081/employees`，由于未登录，被重定向到客户端的登录页`http://ssoserver.com:8080/login.html`

![image-20230810023049184](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308100230245.png)



4.打通**第二步流程**：实现客户端1的完整登录流程。如果客户端没有登录的话就跳转到服务端的登录页，在服务端的登录页进行用户信息和密码验证以后，服务端将用户信息保存到`redis`,并给用户生成访问令牌token。接着服务端重定向到客户端跳转登录前的接口，客户端再次判断用户是否登录，如果已经登录则可以让用户访问当前页面，否则继续到认证中心进行登录校验。完整流程图如下：



![image-20230810035312332](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308100353427.png)



**服务端(认证中心)`gulimall-test-sso-server`**：

服务端`LoginController`的`/login.html`接口：业务系统判断用户没有登录，将请求重定向到`http://ssoserver.com:8080/login.html`，`loginPage`方法将业务系统的当前请求URL传给`login.html`视图，以便在认证中心登录成功后可以跳转回来

```java
@Controller
public class LoginController {
    @GetMapping("/login.html")
    public String loginPage(@RequestParam("redirect_url") String url, Model model) {
        model.addAttribute("url", url);
        return "login";
    }
}
```

服务端`login.html`：用户提交用户名和密码，同时`loginPage`方法带过来的`url`参数也会随用户名和密码提交并调用`/doLogin`接口

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>登录页</title>
</head>
<body>
<form action="/doLogin" method="post">
    用户名: <input name="username"/><br/>
    密码： <input name="password"/><br/>
    <input type="hidden" name="url" th:value="${url}">
    <input type="submit" value="登录"/>
</form>
</body>
</html>
```

服务端`LoginController`的`/doLogin`接口：对用户提交的用户名和密码进行校验，校验通过的话就保存用户信息到redis，并将生成的token作为参数放到回调地址上，重定向到原来的业务系统路径

```java
@Controller
public class LoginController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @PostMapping("/doLogin")
    public String doLogin(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("url") String url) {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            // 把登录成功的用户保存到redis,实现用户信息的共享，跳转到之前页面后判断是否登陆成功
            // 如果redis中存在用户信息则不进行登录跳转，不做此判断的话即便已经在认证中心做了登录，也会一直跳到认证中心的登录页
            String uuid = UUID.randomUUID().toString().replace("-", "");
            redisTemplate.opsForValue().set(uuid, username);
            // 登录成功后进行跳转，跳回到之前的页面：从哪里跳到登录页，就跳回到哪里
            return "redirect:" + url + "?token=" + uuid;
        }
        // 登录失败则停留在登录页继续登录
        return "login";
    }
}
```

客户端`gulimall-test-sso-client1`：判断是否登录，登录成功就展示当前接口页面，否则继续到认证中心进行校验

```java
@Controller
public class HelloController {
    @Value("${sso.server.url}")
    String ssoServerUrl;
    @GetMapping("/employees")
    public String employees(Model model, HttpSession session,
                            // 只要去ssoserver登录成功跳回来就会带上token
                            @RequestParam(value = "token", required = false) String token) {  // 利用token,感知这次是在ssoserver登录成功跳回来的
        // 判断session中是否有当前会话的用户信息，如果有就是已经登录，否则没有登录

        if (!StringUtils.isEmpty(token)) {
            // 只要去ssoserver登录成功跳回来就会带上token
            // 1、TODO 去ssoserver服务器获取token对应的用户信息
            session.setAttribute("loginUser", "zhangsan");
        }
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser == null) { // 由于没登录，就要命令浏览器重定向到新位置
            // 没登录,跳转到登录服务器进行登录
            // 在请求路径上加上参数redirect_url表示当前请求的路径，在认证中心登录成功后还要跳转回当前请求路径
            return "redirect:" + ssoServerUrl + "?redirect_url=http://client1.com:8081/employees";
        } else {
            List<String> emps = new ArrayList<>();
            emps.add("张三");
            emps.add("李四");
            model.addAttribute("emps", emps);
            return "list";
        }
    }
}
```

5.打通**第三步流程**：实现受信系统之间的免登录，只要多个受信系统中的任意一个登录成功，那其他受信系统就不用再去认证中心进行登录。要实现受信系统间的免登录，可以这么做：第一个系统在认证中心`ssoserver`登录成功后，认证中心所在服务器`ssoserver.com`生成一个session,session中保存该用户的信息，然后服务器命令浏览器保存一个对应的cookie。当用户访问第二个受信系统时，首先会携带cookie尝试重定向到认证中心登录页`http://ssoserver.com:8080/login.html`,此时的域名是`ssoserver.com`,也就是通过这个cookie可以找到对应的session中保存的用户信息，说明其他受信系统已经登录过来，那就直接跳转回第二个系统，跳转的同时将之前生成的token给第二个系统返回一份，第二个系统通过这个token就可以通过认证中心的认证，也可以调用认证中心的相关接口获取用户信息

![image-20230812031859578](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308120318686.png)

![image-20230812032223465](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308120322539.png)



`gulimall-test-sso-server` 认证中心  `LoginController`的`/doLogin`接口：用户登录成功后，认证中心生成token令牌，并将token令牌和对应的用户信息保存到`redis`，同时命令浏览器保存一个键为 `soo_token`值为`uuid`的cookie。当业务系统重定向到`login.html`接口进行登录时，如果业务系统携带了session对应的cookie,那说明该用户已经登录过了，就直接跳转回之前的请求而不是跳转到登录页。认证中心还要提供接口`/userInfo`,供其他系统通过token获取用户信息

```java
@Controller
public class LoginController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/login.html")
    public String loginPage(@RequestParam("redirect_url") String url, Model model,
                            @CookieValue(value = "sso_token",required = false) String ssoToken) {
        // 如果server.com下的cookie中存在ssoToken，则说明之前有人已经在其他受信系统中登录过
        if (!StringUtils.isEmpty(ssoToken)){
            return "redirect:" + url + "?token=" + ssoToken;
        }
        model.addAttribute("url", url);
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("url") String url,
                          HttpServletResponse response) {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            // 把登录成功的用户保存到redis,实现用户信息的共享，跳转到之前页面后判断是否登陆成功
            // 如果redis中存在用户信息则不进行登录跳转，不做此判断的话即便已经在认证中心做了登录，也会一直跳到认证中心的登录页
            String uuid = UUID.randomUUID().toString().replace("-", "");
            redisTemplate.opsForValue().set(uuid, username);
            // 登录成功后进行跳转，跳回到之前的页面：从哪里跳到登录页，就跳回到哪里

            Cookie ssoToken = new Cookie("sso_token", uuid);
            response.addCookie(ssoToken);
            return "redirect:" + url + "?token=" + uuid;
        }
        // 登录失败则停留在登录页继续登录
        return "login";
    }

    @ResponseBody
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("token") String token){
        String s = redisTemplate.opsForValue().get(token);
        return s;
    }
}
```

`gulimall-test-sso-client2`：假如其他受信系统已经成功登录，当前系统client2访问`/boss`时，浏览器会话仍然和上一个系统处于同一个浏览器会话，所以当前系统携带cookie访问`ssoserver`认证中心时，无需在登录页进行密码验证即可认证通过。认证通过后进行重定向跳转时会在路径上带回`token`，所以当前系统会根据token从认证中心获取用户信息并保存到当前会话的session，session中存在用户信息就说明处于登录状态，后续就不用到认证中心进行验证

```java
@Controller
public class HelloController {
    @Value("${sso.server.url}")
    String ssoServerUrl;
    @GetMapping("/boss")
    public String employees(Model model, HttpSession session,
                            // 只要去ssoserver登录成功跳回来就会带上token
                            @RequestParam(value = "token", required = false) String token) {  // 利用token,感知这次是在ssoserver登录成功跳回来的
        // 判断session中是否有当前会话的用户信息，如果有就是已经登录，否则没有登录

        if (!StringUtils.isEmpty(token)) {  // 只要去ssoserver登录成功跳回来就会带上token
            // 去ssoserver服务器获取token对应的用户信息
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> forEntity = restTemplate.getForEntity("http://ssoserver.com:8080/userInfo?token=" + token, String.class);
            String body = forEntity.getBody();
            session.setAttribute("loginUser", body);
        }
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser == null) { // 由于没登录，就要命令浏览器重定向到新位置
            // 没登录,跳转到登录服务器进行登录
            // 在请求路径上加上参数redirect_url表示当前请求的路径，在认证中心登录成功后还要跳转回当前请求路径
            return "redirect:" + ssoServerUrl + "?redirect_url=http://client2.com:8082/boss";
        } else {
            List<String> emps = new ArrayList<>();
            emps.add("张三");
            emps.add("李四");
            model.addAttribute("emps", emps);
            return "list";
        }
    }
}
```

测试：首先访问系统`client1`的链接`http://client1.com:8081/employees`时，需要跳转到认证中心进行登录。`client1`登录成功后访问系统`client2`的链接      `http://client2.com:8082/boss`,发现无需登录即可成功访问

单点登录流程总结：

```
1.给登录服务器留下登录痕迹
2.登录服务器要将token信息重定向的时候，带到url地址上
3.其他系统要处理url地址上的关键token，只要有，将token对应的用户保存到自己的session中
4.自己系统将用户保存在自己的会话中
```

待优化的地方：在实际项目中每一个接口都要做登录验证，可以将上面的一些功能抽取到filter中

##  2.购物车功能

### 2.1 基础环境搭建

配置域名：

```
192.168.56.10 cart.gulimall.com
```

创建购物车服务`gulimall-cart`

首页跳转

```xml
<li class="hd_home"><i class="glyphicon glyphicon-home"></i>
    <a href="https://gulimall.com">谷粒商城首页</a>
</li>
```

`pom.xml`：redis保存购物车数据、springsession整合session相关功能

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.14</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>gulimall-cart</groupId>
	<artifactId>gulimall-cart</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>gulimall-cart</name>
	<description>gulimall-cart</description>
	<properties>
		<java.version>1.8</java.version>
		<spring-cloud.version>2021.0.8</spring-cloud.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>com.atguigu.gulimall</groupId>
			<artifactId>gulimall-common</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
		</dependency>
        <!--整合SpringSession完成session共享问题-->
        <dependency>
            <groupId>org.springframework.session</groupId>
            <artifactId>spring-session-data-redis</artifactId>
        </dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
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

`application.properties`：

```properties
server.port=30000
spring.application.name=gulimall-cart
spring.cloud.nacos.discovery.server-addr=127.0.0.1:8848
spring.redis.host=192.168.56.10
```

`springsession`配置类：`GulimallSessionConfig`引入`springsession`，利用session共享登录用户的信息

```java
@EnableRedisHttpSession
@Configuration
public class GulimallSessionConfig {

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();

        cookieSerializer.setDomainName("gulimall.com");
        cookieSerializer.setCookieName("GULISESSION");

        return cookieSerializer;
    }

    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }
}
```

启动类：因为目前不用数据库，故还需要排除掉`mybatis`相关依赖

```java
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class GulimallCartApplication {
    public static void main(String[] args) {
        SpringApplication.run(GulimallCartApplication.class, args);
    }
}
```

配置网关`gulimall-gateway`：

`application.yml`：

```yaml
spring:
  cloud:
    gateway:
      routes:
## 转发到购物车服务
        - id: gulimall_cart_route
          uri: lb://gulimall-cart
          predicates:
            - Host=cart.gulimall.com
```

将静态文件从本地`windous`系统上传到`linux`虚拟机

```shell
scp -r D:\learn\大型电商--谷粒商城\2.分布式高级篇（微服务架构篇）\代码\html\购物车\bootstrap   root@192.168.56.10:/mydata/nginx/html/static/cart
scp -r D:\learn\大型电商--谷粒商城\2.分布式高级篇（微服务架构篇  代码\html\购物车\css    root@192.168.56.10:/mydata/nginx/html/static/cart
scp -r D:\learn\大型电商--谷粒商城\2.分布式高级篇（微服务架构篇）\代码\html\购物车\image   root@192.168.56.10:/mydata/nginx/html/static/cart
scp -r D:\learn\大型电商--谷粒商城\2.分布式高级篇（微服务架构篇）\代码\html\购物车\img   root@192.168.56.10:/mydata/nginx/html/static/cart
scp -r D:\learn\大型电商--谷粒商城\2.分布式高级篇（微服务架构篇）\代码\html\购物车\js   root@192.168.56.10:/mydata/nginx/html/static/cart
```

将静态文件上传到`linux`服务器，拷贝`cartList.html`、`success.html`到 `gulimall-cart`的template目录下

![image-20230812153651887](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308131528398.png)

修改`cartList.html`、`success.html`下的链接路径和图片路径



![image-20230812165931781](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308131528734.png)



测试：访问`http://cart.gulimall.com/`，成功访问到购物车服务的首页

###  2.2 购物车需求

**需求描述**：

- 用户可以在登录状态下将商品添加到购物车【购物车/在线购物车】
  - 放入数据库
  - mongodb
  - 放入redis（采用)：因为购物车是一个“读多写多”的操作，最好选择redis
  - 登录以后，会将临时购物车的数据全部合并过来，并清空临时购物车

- 用户可以在未登录状态下将商品添加到购物车【游客购物车/离线购物车】

  - 放入浏览器的`localstorage`
  - cookie

  - webSQL
  - 放入redis（采用)
  - 浏览器即使关闭,下次进入，临时购物车数据都在

- 用户可以使用购物车一起结算下单

- 用户可以给购物车添加商品

- 用户可以查询自己的购物车

- 用户可以在购物车中修改购买商品的数量

- 用户可以在购物车中删除商品

- 在购物车中展示商品优惠信息

- 提示购物车商品价格变化

###  2.3 购物车数据存储 & 封装

**购物车数据结构数据存储**:

- Redis的持久化机制存储购物车数据：

  - 购物车是一个读多写多的场景，放入数据库并不合适，因此可以选用redis(它有读写锁)

  - 但购物车又需要进行购物车中商品数据的持久化，因此这里我们可以使用Redis的持久化机制存储购物车数据

- 既然选择了redis存储我们购物车中的商品数据，那么应该以什么数据结构存储这些商品数据呢?

  - 首先不同用户应该有独立的购物车，因此购物车应该以用户的作为 key来存储，value是用户的所有购物车信息。这样看来基本的`k-v`结构就可以了

  - 但是，我们对购物车中的商品进行增、删、改操作，基本都需要根据商品id进行判断，为了方便后期处理，我们的购物车也应该是k-v结构，key是商品id，value才是这个商品的购物车信息
  - 综上所述，我们的购物车结构是一个双层Map: `Map<String, Map<String,String>>`
    - 第一层Map，Key是用户id
    - 第二层Map，Key是购物车中商品id，值是购物项数据



![image-20230812205343231](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308131529409.png)



`CartItem`：购物项内容，表示购物车中的单个商品项

```java
/**
 * 购物项内容
 */
public class CartItem {
    /**
     * 商品id
     */
    private Long skuId;
    /**
     * 商品是否被选中
     */
    private Boolean check = true;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品图片
     */
    private String image;
    /**
     * 商品的套餐信息
     */
    private List<String> skuAttr;
    /**
     * 商品价格
     */
    private BigDecimal price;
    /**
     * 商品的数量
     */
    private Integer count;
    /**
     * 商品的总计数量
     */
    private BigDecimal totalPrice;
    public Long getSkuId() {
        return skuId;
    }
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
    public Boolean getCheck() {
        return check;
    }
    public void setCheck(Boolean check) {
        this.check = check;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public List<String> getSkuAttr() {
        return skuAttr;
    }
    public void setSkuAttr(List<String> skuAttr) {
        this.skuAttr = skuAttr;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    /**
     * 计算当前项的总价
     * @return
     */
    public BigDecimal getTotalPrice() {
        return this.price.multiply(new BigDecimal("" + this.count));
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
```

`Cart`：整个购物车，购物车中的各项数据需要根据购物项的相应值计算得到。 需要计算的属性，必须重写他的get方法，保证每次获取属性都会进行计算

```java
/**
 * 整个购物车
 * 需要计算的属性，必须重写他的get方法，保证每次获取属性都会进行计算
 */
public class Cart {
    List<CartItem> items;
    private Integer countNum;//商品数量
    private Integer countType;//商品类型数量
    private BigDecimal totalAmount;//商品总价
    private BigDecimal reduce = new BigDecimal("0.00");//减免价格
    public List<CartItem> getItems() {
        return items;
    }
    public void setItems(List<CartItem> items) {
        this.items = items;
    }
    public Integer getCountNum() {
        int count = 0;
        if (items != null && items.size() > 0) {
            for (CartItem item : items) {
                count += item.getCount();
            }
        }
        return count;
    }
    public Integer getCountType() {
        int count = 0;
        if (items != null && items.size() > 0) {
            for (CartItem item : items) {
                count += 1;
            }
        }
        return count;
    }
    public BigDecimal getTotalAmount() {
        BigDecimal amount = new BigDecimal("0");
        //1、计算购物项总价
        if (items != null && items.size() > 0) {
            for (CartItem item : items) {
                if(item.getCheck()){
                    BigDecimal totalPrice = item.getTotalPrice();
                    amount = amount.add(totalPrice);
                }
            }
        }
        //2、减去优惠总价
        BigDecimal subtract = amount.subtract(getReduce());
        return subtract;
    }
    public BigDecimal getReduce() {
        return reduce;
    }
    public void setReduce(BigDecimal reduce) {
        this.reduce = reduce;
    }
}
```

###  2.4 离线&在线购物车

#####  2.4.1 需求&实现方案

**离线购物车和在线购物车的需求及实现方案**：

- 京东和淘宝都有离线购物车功能，就是在用户未登录的时候也可以将商品加入购物车。并且，用户进行登录之后，临时购物车的商品并不会丢失，而是会加到用户的购物车里
- 问题是：如何保留购物车中的商品？
  - 我们可以将购物车中的商品保存到Redis中，Redis具有持久化策略，服务器宕机了也可以恢复数据。
  - 但是，如何去记住是那些用户将商品添加到了购物车？也就是Redis中的key应该是什么？如果我们登录的话，这个key可以利用用户的账号生成，但是临时用户如何保存呢？学习jd的解决方案可以这样来做：
    - 用户第一次访问购物车的时候为用户颁发一个Cookie(无论是否登录都颁发)，cookie的key就叫user-key，value在后台随机生成。同时要设置cookie的过期时间为1个月，还有cookie的作用域。如果手动清除user-key，那么临时购物车的购物项也被清除，所以user-key是用来标识和存储临时购物车数据的
    - 同时，在我们进入购物车页面的时候，要判断用户是否登录，同时要判断是否已经向用户颁发了cookie。如果我们在每一个controller层的方法都进行判断的话，会使代码变得冗余。所以，我们使用SpringMVC的拦截器机制
- 综上分析，购物车数据展示需求如下：
  - **用户登录之后**访问购物车系统：访问Session中存储的之前登录的用户信息，并进行用户身份信息（userId）的封装，userId只有登录过的UserInfoTo才有，没登陆过的这个属性值为null，这个属性就是购物车取数据时是取用户购物车还是临时购物车的一个判断
  - **用户未登录的时候**访问购物车系统：Cookie中有 user-key，则表示有临时用户，访问临时用户的购物车信息，把user-key进行用户身份信息的封装。Cookie中没有 user-key，则表示没有临时用户。创建一个临时用户即生成一个name为user-key的cookie临时标识，过期时间为一个月，以后每次浏览器进行访问购物车的时候都会携带user-key。user-key 是用来标识和存储临时购物车数据的，处理完后会返回这个user-key
- 总之没登陆的用户userId为空，登录的userId有值，但是登不登陆其都有userKey这个属性的值

#####  2.4.2 ThreadLocal的使用

**`ThreadLocal`保存用户信息**：

- 作用：在同一个线程共享数据。利用`ThreadLocal`在拦截器中对登录用户信息或未登录用户的cookie进行保存，以便在整个线程中可以方便地获取用户信息

- 原理：`ThreadLocal`其实就是一个Map,Map的key就是当前线程，`Map<Thread,Object> threadLocal`，每一次请求进来： 拦截器 ——> Controller ——> Service ——> dao 用的都是同一个线程：



![image-20230812221157353](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308131530415.png)

- `ThreadLocal`使用：在拦截器中统一拦截请求，并将用户信息添加到`ThreadLocal`进行保存，当请求到达`controller`中时可以很方便地获取到用户信息

`UserInfoTo`：封装用户信息

```java
@ToString
@Data
public class UserInfoTo {
    private Long userId; // 如果用户登录了，会有对应的userId
    private String userKey; //一定封装,登录与否都要封装
    // 是否是临时用户
    private boolean tempUser = false;
}
```

`CartInterceptor`：在拦截器中判断用户的登录状态，并封装用户信息传递给controller

```java
/**
 * 在执行目标方法之前，判断用户的登录状态。并封装传递(用户信息)给controller
 */
public class CartInterceptor implements HandlerInterceptor {
    public static ThreadLocal<UserInfoTo> threadLocal = new ThreadLocal<>();
    /**
     * 目标方法执行之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserInfoTo userInfoTo = new UserInfoTo();

        HttpSession session = request.getSession();
        MemberRespVo member = (MemberRespVo) session.getAttribute(AuthServerConstant.LOGIN_USER);
        if (member != null) {
            //用户已经登录过
            userInfoTo.setUserId(member.getId());
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                //user-key
                String name = cookie.getName();
                if (name.equals(CartConstant.TEMP_USER_COOKIE_NAME)) {
                    userInfoTo.setUserKey(cookie.getValue());
                    userInfoTo.setTempUser(true);
                }
            }
        }
        //如果没有临时用户一定要分配一个临时用户
        if (StringUtils.isEmpty(userInfoTo.getUserKey())) {
            String uuid = UUID.randomUUID().toString();
            userInfoTo.setUserKey(uuid);
        }
        //目标方法执行之前
        threadLocal.set(userInfoTo);
        return true;
    }
    /**
     * 业务执行之后；分配一个临时用户，命令浏览器在cookie中保存这个临时用户，有效期一个月
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserInfoTo userInfoTo = threadLocal.get();
        //如果没有临时用户一定保存一个临时用户
        if (!userInfoTo.isTempUser()) {
            //持续的延长临时用户的过期时间
            Cookie cookie = new Cookie(CartConstant.TEMP_USER_COOKIE_NAME, userInfoTo.getUserKey());
            cookie.setDomain("gulimall.com");
            // 设置cookie的过期时间
            cookie.setMaxAge(CartConstant.TEMP_USER_COOKIE_TIMEOUT);
            response.addCookie(cookie);
        }
    }
}
```

`GulimallWebConfig`：配置拦截器`CartInterceptor`需要拦截的请求路径

```java
// 配置拦截器
@Configuration
public class GulimallWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CartInterceptor()).addPathPatterns("/**");
    }
}
```

`CartController`：获取`threadLocal`中的用户信息

```java
@Controller
public class CartController {
    /**
     * 浏览器有一个cookie；user-key；标识用户身份，一个月后过期；
     * 如果第一次使用jd的购物车功能，都会给一个临时的用户身份；
     * 浏览器以后保存，每次访问都会带上这个cookie；
     * 登录：session有
     * 没登录：按照cookie里面带来user-key来做
     * 第一次：如果没有临时用户，帮忙创建一个临时用户
     */
    @GetMapping("cart.html")
    public String cartListPage(HttpSession session) {
        // 1、从threadLocal得到用户信息，id，user-key
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        System.out.println(userInfoTo);
        return "cartList";
    }
}
```

#####  2.4.3 页面环境搭建

需求：实现页面的跳转，商品添加购物车成功或者用户查询购物车之后展示购物车中的商品

```
当我们在商品详情页item.html点击加入购物车之后，跳转到加入成功页success.html
在成功页success.html 点击 购物车 进入购物车列表页 cartList.html
在成功页success.html 点击 查看商品详情 跳转到该商品的详情页
在 首页 index.html 中点击我的购物车也跳转到 购物车列表页 cartList.html
```

修改商品服务`gulimall-product`的`index.html`页面和`item.html`页面

`index.html`：

```html
<div class="header_gw">
    <img src="/static/index/img/img_15.png"/>
    <span><a href="http://cart.gulimall.com/cart.html">我的购物车</a></span>
    <span>0</span>
</div>
```

`item.html`：

```html
<div class="box-btns-two">
    <a href="#" id="addToCartA" th:attr="skuId=${item.info.skuId}">
        加入购物车
    </a>
</div>
$("#addToCartA").click(function () {
    var num = $("#numInput").val();
    var skuId = $(this).attr("skuId");
    location.href = "http://cart.gulimall.com/addToCart?skuId=" + skuId + "&num=" + num;
    return false;
});
```

在购物车服务`gulimall-cart`中添加`/addToCart`接口和`cart.html`接口，分别实现`添加商品到购物车`和`展示购物车详情`

```java
@Controller
public class CartController {
    @GetMapping("cart.html")
    public String cartListPage(HttpSession session) {
        // 1、从threadLocal得到用户信息，id，user-key
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        System.out.println(userInfoTo);
        return "cartList";
    }
    /**
     * 添加商品到购物车
     */
    @GetMapping("/addToCart")
    public String addToCart() {
        return "success";
    }
}
```

`success.html`：添加商品成功后跳转到此页面

```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8"/>
    <title></title>
    <script type="text/javascript" src="/static/cart/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/static/cart/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="/static/cart/js/swiper.min.js"></script>
    <script src="/static/cart/js/swiper.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/cart//css/swiper.min.css"/>
    <link rel="stylesheet" type="text/css" href="/static/cart//bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="/static/cart//css/success.css"/>
</head>
<body>
<!--头部-->
<div class="alert-info">
    <div class="hd_wrap_top">
        <ul class="hd_wrap_left">
            <li class="hd_home"><i class="glyphicon glyphicon-home"></i>
                <a href="http://gulimall.com">谷粒商城首页</a>
            </li>
        </ul>
        <ul class="hd_wrap_right">
            <li>
                <a th:if="${session.loginUser==null}" href="http://auth.gulimall.com/login.html" class="li_2">请登录</a>
                <a th:if="${session.loginUser!=null}" style="width: 100px">[[${session.loginUser.nickname}]]</a>
            </li>
            <li>
                <a th:if="${session.loginUser == null}" href="http://auth.gulimall.com/reg.html">免费注册</a>
            </li>
            <li class="spacer"></li>
            <li>
                <a href="/javascript:;">我的订单</a>
            </li>
        </ul>
    </div>
</div>
<div class="nav-tabs-justified">
    <div class="nav_wrap">
        <div class="nav_top">
            <div class="nav_top_one">
                <a href="http://gulimall.com"><img src="/static/cart/img/logo1.jpg" style="height: 60px;width:180px;"/></a>
            </div>
            <div class="nav_top_two"><input type="text"/>
                <button>搜索</button>
            </div>
        </div>
    </div>
</div>
<div class="main">

    <div class="success-wrap">
        <div class="w" id="result">
            <div class="m succeed-box">

                <div th:if="${item!=null}" class="mc success-cont">
                    <div class="success-lcol">
                        <div class="success-top"><b class="succ-icon"></b>
                            <h3 class="ftx-02">商品已成功加入购物车</h3></div>
                        <div class="p-item">
                            <div class="p-img">
                                <a href="/javascript:;" target="_blank"><img style="height: 60px;width:60px;"
                                                                             th:src="${item.image}"></a>
                            </div>
                            <div class="p-info">
                                <div class="p-name">
                                    <a th:href="'http://item.gulimall.com/'+${item.skuId}+'.html'"
                                       th:text="${item.title}">TCL 55A950C 55英寸32核人工智能 HDR曲面超薄4K电视金属机身（枪色）</a>
                                </div>
                                <div class="p-extra"><span class="txt" th:text="'数量：'+${item.count}">  数量：1</span></div>
                            </div>
                            <div class="clr"></div>
                        </div>
                    </div>
                    <div class="success-btns success-btns-new">
                        <div class="success-ad">
                            <a href="/#none"></a>
                        </div>
                        <div class="clr"></div>
                        <div class="bg_shop">
                            <a class="btn-tobback" th:href="'http://item.gulimall.com/'+${item.skuId}+'.html'">查看商品详情</a>
                            <a class="btn-addtocart" href="http://cart.gulimall.com/cart.html"
                               id="GotoShoppingCart"><b></b>去购物车结算</a>
                        </div>
                    </div>
                </div>
                <div th:if="${item == null}" class="mc success-cont">
                    <h2>购物车中无商品</h2> <a href="http://gulimall.com">去购物</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="/static/cart/js/success.js"></script>
</html>
```

#####  2.4.4 添加商品到购物车

**1.配置线程池**：

添加商品的过程中需要用到异步编排，故将商品服务`gulimall-product`中的线程池配置也添加购物车服务`gulimall-cart`

`MyThreadConfig`：

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

`ThreadPoolConfigProperties`：

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

`application.properties`：

```properties
gulimall.thread.core-size=20
gulimall.thread.max-size=200
gulimall.thread.keep-alive-time=10
```

**2.远程调用商品服务`gulimall-product`获取商品信息**：用户在具体的商品详情页点击`加入购物车`，需要获取这个商品的具体信息，将这些信息封装到购物车`CartItem`中，然后再存到`redis`进行保存

`ProductFeignService`： 在购物车服务`gulimall-cart`中添加远程调用接口的定义，两个接口分别用来`获取商品信息`、`查询商品的sku的组合信息`

```java
@FeignClient("gulimall-product")
public interface ProductFeignService {
    /**
     * 远程调用商品服务获取商品信息
     */
    @RequestMapping("/product/skuinfo/info/{skuId}")
    R getSkuInfo(@PathVariable("skuId") Long skuId);
    /**
     * 远程查询商品sku的组合信息
     */
    @GetMapping("/product/skusaleattrvalue/stringlist/{skuId}")
    List<String> getSkuSaleAttrValues(@PathVariable("skuId") Long skuId);
}
```

`SkuInfoController`：商品服务`gulimall-product`中用来获取商品信息的接口

```java
@RestController
@RequestMapping("product/skuinfo")
public class SkuInfoController {
    @Autowired
    private SkuInfoService skuInfoService;
    /**
     * 信息
     */
    @RequestMapping("/info/{skuId}")
    public R info(@PathVariable("skuId") Long skuId){
		SkuInfoEntity skuInfo = skuInfoService.getById(skuId);

        return R.ok().put("skuInfo", skuInfo);
    }
}
```

`SkuSaleAttrValueController`：商品服务`gulimall-product`中用于查询商品`sku`组合信息的接口

```java
@RestController
@RequestMapping("product/skusaleattrvalue")
public class SkuSaleAttrValueController {
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;
    @GetMapping("/stringList/{skuId}")
    public List<String> getSkuSaleAttrValues(@PathVariable("skuId") Long skuId) {
        return skuSaleAttrValueService.getSkuSaleAttrValuesAsStringList(skuId);
    }
}
```

`SkuSaleAttrValueService`：

```java
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {
    List<String> getSkuSaleAttrValuesAsStringList(Long skuId);
}
```

`SkuSaleAttrValueServiceImpl`：

```java
@Service("skuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValueEntity> implements SkuSaleAttrValueService {
    @Override
    public List<String> getSkuSaleAttrValuesAsStringList(Long skuId) {
        SkuSaleAttrValueDao dao =  this.baseMapper;
        return dao.getSkuSaleAttrValuesAsStringList(skuId);
    }
}
```

`SkuSaleAttrValueDao`：

```java
@Mapper
public interface SkuSaleAttrValueDao extends BaseMapper<SkuSaleAttrValueEntity> {
    List<String> getSkuSaleAttrValuesAsStringList(@Param("skuId") Long skuId);
}
```

`SkuSaleAttrValueDao.xml`：用于查询的sql语句定义

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.atguigu.gulimall.product.dao.SkuSaleAttrValueDao">
    <resultMap type="com.atguigu.gulimall.product.entity.SkuSaleAttrValueEntity" id="skuSaleAttrValueMap">
        <result property="id" column="id"/>
        <result property="skuId" column="sku_id"/>
        <result property="attrId" column="attr_id"/>
        <result property="attrName" column="attr_name"/>
        <result property="attrValue" column="attr_value"/>
        <result property="attrSort" column="attr_sort"/>
    </resultMap>
    <select id="getSkuSaleAttrValuesAsStringList" resultType="java.lang.String">
        SELECT CONCAT(attr_name, "：", attr_value)
        FROM `pms_sku_sale_attr_value`
        WHERE sku_id = #{skuId}
    </select>
</mapper>
```

**3.添加商品到购物车**：在购物车服务`gulimall-cart`中对请求进行处理

`CartController`：

```java
@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    /**
     * 添加商品到购物车
     */
    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num,
                            Model model) throws ExecutionException, InterruptedException {
        CartItem cartItem = cartService.addToCart(skuId, num);
        model.addAttribute("item",cartItem);
        return "success";
    }
}
```

`CartService`接口：定义添加商品到购物车的接口

```java
public interface CartService {
    CartItem addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException;
}
```

`CartServiceImpl`实现类：判断当前用户是在线(登录)用户还是离线用户并获取当前用户的购物车信息，然后远程调用商品服务`gulimall-product`获取用户选择的商品的信息，将这个商品的信息存到`redis`进行保存，如果这个商品之前就加入过购物车的话就增加商品数量，如果是第一次加入购物车就直接写入`redis`

```java
@Slf4j
@Service
public class CartServiceImpl implements CartService {

    private final String CART_PREFIX = "gulimall:cart:";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    ProductFeignService productFeignService;

    @Autowired
    ThreadPoolExecutor executor;

    @Override
    public CartItem addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException {
        // 1.获取到我们要操作特定用户的购物车
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        // 2.判断购物车中有没有相同的商品，有的话增加商品数量，没有的话就增加新商品
        String res = (String) cartOps.get(skuId.toString());
        if (StringUtils.isEmpty(res)) { // 没有的话就增加新商品
            // 添加新商品到购物车
            // 3.远程查询当前要添加的商品的信息（远程调用商品服务获取商品信息）
            CartItem cartItem = new CartItem();
            CompletableFuture<Void> getSkuInfoTask = CompletableFuture.runAsync(() -> {
                R skuInfo = productFeignService.getSkuInfo(skuId);
                SkuInfoVo data = skuInfo.getData("skuInfo", new TypeReference<SkuInfoVo>() {
                });
                // 4.将商品添加到购物车
                cartItem.setCheck(true);
                cartItem.setCount(num);
                cartItem.setImage(data.getSkuDefaultImg());
                cartItem.setTitle(data.getSkuTitle());
                cartItem.setSkuId(skuId);
                cartItem.setPrice(data.getPrice());
            }, executor);

            // 5.远程查询sku的组合信息
            CompletableFuture<Void> getSkuSaleAttrValues = CompletableFuture.runAsync(() -> {
                List<String> values = productFeignService.getSkuSaleAttrValues(skuId);
                cartItem.setSkuAttr(values);
            }, executor);

            // 等信息都查询到之后，将信息存到redis
            CompletableFuture.allOf(getSkuInfoTask, getSkuSaleAttrValues).get();
            String jsonString = JSON.toJSONString(cartItem);
            cartOps.put(skuId.toString(), jsonString);
            return cartItem;
        } else {
            // 之前购物车中就有此商品的话，增加商品数量即可
            CartItem cartItem = JSON.parseObject(res, CartItem.class);
            cartItem.setCount(cartItem.getCount() + num);
            cartOps.put(skuId.toString(), JSON.toJSONString(cartItem));
            return cartItem;
        }
    }
    /**
     * 获取到我们要操作的购物车
     */
    private BoundHashOperations<String, Object, Object> getCartOps() {
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        String cartKey = "";
        // 1.判断是添加到临时购物车(用户没有登录)，还是在线购物车
        if (userInfoTo.getUserId() != null) { // 登录用户
            // gulimall:cart:1
            cartKey = CART_PREFIX + userInfoTo.getUserId();
        } else { // 临时用户
            cartKey = CART_PREFIX + userInfoTo.getUserKey();
        }
        // 判断当前购物车里有没有这个商品，如果没有的话就新增商品，如果之前就有的话就增加商品数量
        BoundHashOperations<String, Object, Object> operations = redisTemplate.boundHashOps(cartKey);
        return operations;
    }
}
```

测试：搜索到商品后点击商品就跳转到商品详情页，在详情页点击`加入购物车`，成功加入购物车后跳转到购物车展示页，如果之前就已经有该商品的话商品的数量就会增加

![image-20230820033940681](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308202303324.png)



#####  2.4.5 `RedirectAttributes`防刷新

上面加入购物车成功后，如果在购物车详情页一直刷新`http://cart.gulimall.com/addToCart?skuId=2&num=1`请求的话，用户虽然没有继续往购物车加入商品，但是这个商品在购物车中的数量会增加(原因是因为添加和展示的请求路径一样，如果在展示页刷新就会重复添加)。为了防止刷新，我们将`添加商品到购物车`和`跳转到购物车详情页`分成两个步骤：商品添加到购物车后重定向跳转到新的链接地址，再查询一次`redis`获取商品数据。这次不用`Model`保存视图数据，而是利用`RedirectAttributes`的`addAttribute`方法往链接地址上添加请求参数，保证**添加商品到购物车的链接**`http://cart.gulimall.com/addToCart?skuId=2&num=1`和**展示购物车的地址**`http://cart.gulimall.com/addToCartSuccess.html?skuId=3`不一样，就可以防止重复添加商品

`CartController`：

```java
@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    /**
     * 添加商品到购物车
     */
    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num, RedirectAttributes ra) throws ExecutionException, InterruptedException {
        cartService.addToCart(skuId, num);
        ra.addAttribute("skuId", skuId);
        /*
            RedirectAttributes方法说明：
                  ra.addFlashAttribute();将数据放在session里面,可以在页面取出，但是只能取一次
                  ra.addAttribute("skuId",skuId);将数据放在url后面
         */
        return "redirect:http://cart.gulimall.com/addToCartSuccess.html";
    }
    /**
     * 跳转到购物车页
     */
    @GetMapping("/addToCartSuccess.html")
    public String addToCartSuccessPage(@RequestParam("skuId") Long skuId, Model model) {
        //重定向到成功页面。再次查询购物车数据即可
        CartItem item = cartService.getCartItem(skuId);
        model.addAttribute("item", item);
        return "success";
    }
}
```

`获取购物车中某个购物项`：

```java
public interface CartService {
    /**
     * 获取购物车中某个购物项
     */
    CartItem getCartItem(Long skuId);
}
```

`CartServiceImpl`：获取购物车中某个购物项

```java
@Slf4j
@Service
public class CartServiceImpl implements CartService {
    @Override
    public CartItem getCartItem(Long skuId) {
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        String str = (String) cartOps.get(skuId.toString());
        CartItem cartItem = JSON.parseObject(str, CartItem.class);
        return cartItem;
    }
}
```

#####  2.4.6 查询购物车 & 合并购物项

查询购物车中的数据，如果用户是登录状态就判断当前浏览器中是否存在临时购物车，如果临时购物车中存在商品，就将临时购物车与在线购物车进行合并。如果用户没登录就查询临时购物车中的数据进行渲染

`CartController`：

```java
@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    @GetMapping("cart.html")
    public String cartListPage(Model model) throws ExecutionException, InterruptedException {
        Cart cart = cartService.getCart();
        model.addAttribute("cart", cart);
        return "cartList";
    }
}
```

`CartService`：

```java
public interface CartService {
    /**
     * 获取整个购物车
     */
    Cart getCart() throws ExecutionException, InterruptedException;
}

```

`CartServiceImpl`的`getCart()方法`：查询购物车中的数据，如果用户是登录状态就判断当前浏览器中是否存在临时购物车，如果临时购物车中存在商品，就将临时购物车与在线购物车进行合并。如果用户没登录就查询临时购物车中的数据进行渲染

```java
@Slf4j
@Service
public class CartServiceImpl implements CartService {
    private final String CART_PREFIX = "gulimall:cart:";
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public Cart getCart() throws ExecutionException, InterruptedException {
        Cart cart = new Cart();
        // 1.区分用户是离线用户还是在线(登录)用户
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        if (userInfoTo.getUserId() != null) {

            // 2.1 用户是登录状态
            String cartKey = CART_PREFIX + userInfoTo.getUserId();
            // 2.2 如果临时购物车中的数据还没有进行合并
            String tempCartKey = CART_PREFIX + userInfoTo.getUserKey();
            List<CartItem> tempCartItems = getCartItems(tempCartKey);
            if (tempCartItems != null) { // 临时购物车中有数据，需要合并
                for (CartItem item : tempCartItems) {
                    addToCart(item.getSkuId(), item.getCount());
                }
            }
            // 2.3 清除临时购物车数据
            clearCart(tempCartKey);

            //2.4 获取登录后的购物车的数据【包含合并过来的临时购物车的数据，和登录后的购物车的数据】
            List<CartItem> cartItems = getCartItems(cartKey);
            cart.setItems(cartItems);

        } else {
            // 3.1 用户没登录,购物车是临时购物车
            String cartKey = CART_PREFIX + userInfoTo.getUserKey();
            // 3.2 获取临时购物车的所有购物项
            List<CartItem> cartItems = getCartItems(cartKey);
            cart.setItems(cartItems);
        }
        return cart;
    }

    /**
     * 获取购物车中的购物项
     */
    private List<CartItem> getCartItems(String cartKey) {
        BoundHashOperations<String, Object, Object> hashOps = redisTemplate.boundHashOps(cartKey);
        List<Object> values = hashOps.values();
        if (values != null && values.size() > 0) {
            List<CartItem> collect = values.stream().map((obj) -> {
                String str = (String) obj;
                CartItem cartItem = JSON.parseObject(str, CartItem.class);
                return cartItem;
            }).collect(Collectors.toList());
            return collect;
        }
        return null;
    }

    /**
     * 清空购物车数据
     */
    public void clearCart(String cartKey) {
        redisTemplate.delete(cartKey);
    }
    
    @Override
    public CartItem getCartItem(Long skuId) {
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        String str = (String) cartOps.get(skuId.toString());
        CartItem cartItem = JSON.parseObject(str, CartItem.class);
        return cartItem;
    }
    
    /**
     * 获取到我们要操作的购物车
     */
    private BoundHashOperations<String, Object, Object> getCartOps() {
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        String cartKey = "";
        // 1.判断是添加到临时购物车(用户没有登录)，还是在线购物车
        if (userInfoTo.getUserId() != null) { // 登录用户
            // gulimall:cart:1
            cartKey = CART_PREFIX + userInfoTo.getUserId();
        } else { // 临时用户
            cartKey = CART_PREFIX + userInfoTo.getUserKey();
        }
        // 判断当前购物车里有没有这个商品，如果没有的话就新增商品，如果之前就有的话就增加商品数量
        BoundHashOperations<String, Object, Object> operations = redisTemplate.boundHashOps(cartKey);
        return operations;
    }

    @Override
    public CartItem addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException {
        // 1.获取到我们要操作特定用户的购物车
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        // 2.判断购物车中有没有相同的商品，有的话增加商品数量，没有的话就增加新商品
        String res = (String) cartOps.get(skuId.toString());
        if (StringUtils.isEmpty(res)) { // 没有的话就增加新商品
            // 添加新商品到购物车
            // 3.远程查询当前要添加的商品的信息（远程调用商品服务获取商品信息）
            CartItem cartItem = new CartItem();
            CompletableFuture<Void> getSkuInfoTask = CompletableFuture.runAsync(() -> {
                R skuInfo = productFeignService.getSkuInfo(skuId);
                SkuInfoVo data = skuInfo.getData("skuInfo", new TypeReference<SkuInfoVo>() {
                });
                // 4.将商品添加到购物车
                cartItem.setCheck(true);
                cartItem.setCount(num);
                cartItem.setImage(data.getSkuDefaultImg());
                cartItem.setTitle(data.getSkuTitle());
                cartItem.setSkuId(skuId);
                cartItem.setPrice(data.getPrice());
            }, executor);

            // 5.远程查询sku的组合信息
            CompletableFuture<Void> getSkuSaleAttrValues = CompletableFuture.runAsync(() -> {
                List<String> values = productFeignService.getSkuSaleAttrValues(skuId);
                cartItem.setSkuAttr(values);
            }, executor);

            // 等信息都查询到之后，将信息存到redis
            CompletableFuture.allOf(getSkuInfoTask, getSkuSaleAttrValues).get();
            String jsonString = JSON.toJSONString(cartItem);
            cartOps.put(skuId.toString(), jsonString);
            return cartItem;
        } else {
            // 之前购物车中就有此商品的话，增加商品数量即可
            CartItem cartItem = JSON.parseObject(res, CartItem.class);
            cartItem.setCount(cartItem.getCount() + num);
            cartOps.put(skuId.toString(), JSON.toJSONString(cartItem));
            return cartItem;
        }
    }    
}
```

`cartList.html`：渲染购物车数据

```html
		<div class="One_ShopCon">
			<h1 th:if="${cart.items == null}">
				购物车还没有商品，<a href="http://gulimall.com">去购物</a>
			</h1>
			<ul th:if="${cart.items != null}">
				<li th:each="item:${cart.items}">
					<div>
					</div>
					<div>
						<ol>
							<li><input type="checkbox" th:attr="skuId=${item.skuId}" class="itemCheck" th:checked="${item.check}"></li>
							<li>
								<dt><img th:src="${item.image}" alt=""></dt>
								<dd style="width: 300px">
									<p>
										<span th:text="${item.title}">TCL 55A950C 55英寸32核</span>
										<br/>
										<span th:each="attr:${item.skuAttr}" th:text="${attr}+' '">尺码: 55时 超薄曲面 人工智能</span>
									</p>
								</dd>
							</li>
							<li>
								<p class="dj" th:text="'￥'+${#numbers.formatDecimal(item.price,3,2)}">￥4599.00</p>

							</li>
							<li>
								<p th:attr="skuId=${item.skuId}">
									<span class="countOpsBtn">-</span>
									<span class="countOpsNum" th:text="${item.count}">5</span>
									<span class="countOpsBtn">+</span>
								</p>

							</li>
							<li style="font-weight:bold"><p class="zj">￥[[${#numbers.formatDecimal(item.totalPrice,3,2)}]]</p></li>
							<li>
								<p class="deleteItemBtn" th:attr="skuId=${item.skuId}">删除</p>

							</li>
						</ol>
					</div>
				</li>

			</ul>
		</div>
```

测试：成功添加商品后点击`去购物车结算`后跳转到购物车详情页

![image-20230820151019068](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308202303202.png)

#####  2.4.7 勾选购物项

`cartList.html`：

```js
<li><input type="checkbox" th:attr="skuId=${item.skuId}" class="itemCheck" th:checked="${item.check}"></li>

// 为勾选按钮绑定单击事件
$(".itemCheck").click(function(){
    var skuId = $(this).attr("skuId");
    var check = $(this).prop("checked");
    location.href = "http://cart.gulimall.com/checkItem?skuId="+skuId+"&check="+(check?1:0);
})
```

`CartController`：

```java
@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping("/checkItem")
    public String checkItem(@RequestParam("skuId") Long skuId,@RequestParam("check")  Integer check){
        cartService.checkItem(skuId,check);
        return "redirect:http://cart.gulimall.com/cart.html";
    }
}
```

`CartService`：

```java
public interface CartService {
    /**
     * 勾选购物项
     */
    void checkItem(Long skuId, Integer check);
}
```

`CartServiceImpl`：

```java
@Slf4j
@Service
public class CartServiceImpl implements CartService {

    private final String CART_PREFIX = "gulimall:cart:";

    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     * 勾选购物项
     */
    @Override
    public void checkItem(Long skuId, Integer check) {
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        CartItem cartItem = getCartItem(skuId);
        cartItem.setCheck(check == 1 ? true : false);
        String jsonString = JSON.toJSONString(cartItem);
        cartOps.put(skuId.toString(), jsonString);
    }
    /**
     * 获取到我们要操作的购物车
     */
    private BoundHashOperations<String, Object, Object> getCartOps() {
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        String cartKey = "";
        // 1.判断是添加到临时购物车(用户没有登录)，还是在线购物车
        if (userInfoTo.getUserId() != null) { // 登录用户
            // gulimall:cart:1
            cartKey = CART_PREFIX + userInfoTo.getUserId();
        } else { // 临时用户
            cartKey = CART_PREFIX + userInfoTo.getUserKey();
        }
        // 判断当前购物车里有没有这个商品，如果没有的话就新增商品，如果之前就有的话就增加商品数量
        BoundHashOperations<String, Object, Object> operations = redisTemplate.boundHashOps(cartKey);
        return operations;
    }
    
    @Override
    public CartItem getCartItem(Long skuId) {
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        String str = (String) cartOps.get(skuId.toString());
        CartItem cartItem = JSON.parseObject(str, CartItem.class);
        return cartItem;
    }
}
```

#####  2.4.8 改变购物项数量

`cartList.html`：

```js
<li>
    <p th:attr="skuId=${item.skuId}">
        <span class="countOpsBtn">-</span>
        <span class="countOpsNum" th:text="${item.count}">5</span>
        <span class="countOpsBtn">+</span>
    </p>
</li>
// 为增减按钮绑定单击事件
$(".countOpsBtn").click(function(){
    //1、skuId
    var skuId = $(this).parent().attr("skuId");
    var num = $(this).parent().find(".countOpsNum").text();
    location.href = "http://cart.gulimall.com/countItem?skuId="+skuId+"&num="+num;
});
```

`CartController`：

```java
@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    /**
     * 改变购物项的数量
     */
    @GetMapping("/countItem")
    public String countItem(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num) {
        cartService.changeItemCount(skuId, num);
        return "redirect:http://cart.gulimall.com/cart.html";
    }

}
```

`CartService`：

```java
public interface CartService {
    /**
     * 改变购物项的数量
     */
    void changeItemCount(Long skuId, Integer num);
}
```

`CartServiceImpl`：

```java
@Slf4j
@Service
public class CartServiceImpl implements CartService {
    /**
     * 改变购物项的数量
     */
    @Override
    public void changeItemCount(Long skuId, Integer num) {
        CartItem cartItem = getCartItem(skuId);
        cartItem.setCount(num);
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        cartOps.put(skuId.toString(),JSON.toJSONString(cartItem));
    }

}
```

#####  2.4.9 删除购物项

`cartList.html`：

```js
        <div>
            <button type="button" onclick="deleteItem()">删除</button>
        </div>
        <li>
            <p class="deleteItemBtn" th:attr="skuId=${item.skuId}">删除</p>
        </li>

	var deleteId = 0;
	//删除购物项
	function deleteItem(){
		location.href = "http://cart.gulimall.com/deleteItem?skuId="+deleteId;
	}

	$(".deleteItemBtn").click(function () {
		deleteId = $(this).attr("skuId");
	});
```

`CartController`：

```java
@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    /**
     * 删除购物项
     */
    @GetMapping("/deleteItem")
    public String deleteItem(@RequestParam("skuId") Long skuId) {
        cartService.deleteItem(skuId);
        return "redirect:http://cart.gulimall.com/cart.html";
    }
}
```

`CartService`：

```java
public interface CartService {
    /**
     * 删除购物项
     */
    void deleteItem(Long skuId);
}
```

`CartServiceImpl`：

```java
@Slf4j
@Service
public class CartServiceImpl implements CartService {
    /**
     * 删除购物项
     */
    @Override
    public void deleteItem(Long skuId) {
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        cartOps.delete(skuId.toString());
    }
}
```



##  3.消息中间件—RabbitMQ

### 3.1 消息队列概述

- MQ(Message Queue)：消息队列。市面的MQ产品：ActiveMQ、RabbitMQ、RocketMQ、Kafka

- 消息服务中两个重要概念:
  - **消息代理(message broker)**和**目的地(destination)**消息代理：可以理解为安装了消息队列中间件的服务器，用于接收和转发消息。目的地：消息代理将信息转发到目的地
  - 当消息发送者发送消息以后，将由消息代理接管，消息代理保证消息传递到指定目的地
- 消息队列主要有两种形式的目的地
  - 队列(queue)︰点对点消息通信(point-to-point)
  - 主题(topic) :发布(publish)/订阅(subscribe) 消息通信
- 点对点式：
  - 消息发送者发送消息，消息代理将其放入一个队列中，消息接收者从队列中获取消息内容，消息读取后被移出队列
  - 消息只有唯一的发送者和接受者，但并不是说只能有一个接收者
- 发布订阅式:
  - 发送者（发布者）发送消息到主题，多个接收者(订阅者）监听(订阅）这个主题，那么就会在消息到达时同时收到消息

- 消息队列协议：

  - JMS (`Java MessageIService`) JAVA消息服务:
    - 基于JVM消息代理的规范。`ActiveMQ`、`HornetMQ`是`JMS`实现

  - `AMQP` (`Advanced Message Queuing Protocol`)
    - 高级消息队列协议，也是一个消息代理的规范，兼容JMS
    - `RabbitMQ`是`AMQP`的实现

- `JMS` 和 `AMQP` 对比：

|              | JMS (Java Message Service)                                   | AMQP (Advanced Message Queuing Protocol)                     |
| ------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 定义         | Java api                                                     | 网络线级协议                                                 |
| 跨语言       | 否                                                           | 是                                                           |
| 跨平台       | 否                                                           | 是                                                           |
| Model        | 提供两种消息模型：<br/>Peer-2-Peer<br/>Pub/sub               | 提供了五种消息模型: direct exchange 、 fanout exchange、 topic change、headers exchange、system exchange<br/>本质来讲，后四种和`JMS`的pub/sub模型没有太大差别，仅是在路由机制上做了更详细的划分 |
| 支持消息类型 | 多种消息类型:<br/>`TextMessage`<br/>`MapMessage`<br/>`BytesMessage`<br/>`StreamMessage`<br/>`ObjectMessage`<br/>Message(只有消息头和属性) | byte[]<br/>当实际应用时，有复杂的消息，可以将消息序列化后发送 |
| 综合评价     | `JMS`定义了`JAVA API`层面的标准;在`java`体系中，多个client均可以通过`JMS`进行交互，不需要应用修改代码，但是其对跨平台的支持较差 | `AMQP`定义了`wire-level`层的协议标准;天然具有跨平台、跨语言特性 |

- Spring对消息队列的支持：
  - spring-jms提供了对JMS的支持
  - spring-rabbit提供了对AMQP的支持
  - 需要ConnectionFactory的实现来连接消息代理
  - 提供JmsTemplate、RabbitTemplate来发送消息
  - @JmsListener (JMS)、@RabbitListener (AMQP）注解在方法上监听消息代理发布的消息

  - EnableJms、@EnableRabbit开启支持

- spring Boot自动配置：
  - JmsAutoConfiguration
  - RabbitAutoConfiguration



**消息队列应用场景**：大多应用中，可通过消息服务中间件来提升系统异步通信、扩展解耦能力、还可以进行流量控制

- 消息队列应用场景一：异步处理

![image-20230813003715951](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308131531767.png)

- 消息队列应用场景二：应用解耦。订单系统和库存系统改用消息队列进行通信，库存系统不再关心订单系统的接口是怎么实现的

![image-20230813010043014](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308131531773.png)

- 消息队列应用场景三：流量控制。把所有流量请求放到消息队列中，后台根据它的能力来进行处理

![image-20230813005413836](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308131532010.png)



###  3.2 RabbitMQ核心概念&安装

**RabbitMQ简介**:

- `RabbitMQ`是一个由`erlang`语言开发的`AMQP(Advanved Message Queue Protocol)`的开源实现
- `RabbitMQ`文档：`https://www.rabbitmq.com/networking.html`

**核心概念**：

- **Message**：消息。消息是不具名的，它由消息头和消息体组成。消息体是不透明的(需要程序员自己设定)，而消息头则由一系列的可选属性组成，这些属性包括routing-key_(路由键)、priority(相对于其他消息的优先权)、delivery-mode(指出该消息可能需要持久性存储)等
- **Publisher**：消息的生产者，也是一个向交换器发布消息的客户端应用程序
- **Exchange**：交换器，用来接收生产者发送的消息并将这些消息路由给服务器中的队列。`Exchange`有4种类型: `direct`(默认)，`fanout`, `topic`,和`headers`，不同类型的`Exchange`转发消息的策略有所区别
- **Queue**：消息队列，用来保存消息直到发送给消费者。它是消息的容器，也是消息的终点。一个消息可投入一个或多个队列。消息一直在队列里面，等待消费者连接到这个队列将其取走
- **Binding**：绑定，用于消息队列和交换器之间的关联。一个绑定就是基于路由键将交换器和消息队列连接起来的路由规则，所以可以将交换器理解成一个由绑定构成的路由表。`Exchange`和`Queue`的绑定可以是多对多的关系。交换机可以和交换机绑定，也可以和队列绑定
- **Connection**：网络连接，比如一个TCP连接
- **Channel**：信道，多路复用连接中的一条独立的双向数据流通道。信道是建立在真实的TCP连接内的虚拟连接，`AMQP `命令都是通过信道发出去的，不管是发布消息、订阅队列还是接收消息，这些动作都是通过信道完成。因为对于操作系统来说建立和销毁TCР都是非常昂贵的开销，所以引入了信道的概念，以复用一条TCP连接
- **Consumer**：消息的消费者，表示一个从消息队列中取得消息的客户端应用程序
- **Virtual Host**：虚拟主机，表示一批交换器、消息队列和相关对象。虚拟主机是共享相同的身份认证和加密环境的独立服务器域。每个vhost本质上就是一个mini版的RabbitMQ服务器，拥有自己的队列、交换器、绑定和权限机制。vhost是AMQP概念的基础，必须在连接时指定,RabbitMQ默认的vhost是/
- **Broker**：表示消息队列服务器实体

![image-20230813025752389](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308131532611.png)



![image-20230813030320923](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308131532265.png)



`Docker`安装`RabbitMQ`

```shell
#Docker安装RabbitMQ
docker run -d --name rabbitmq \
-p 5671:5671 -p 5672:5672 \
-p 4369:4369 -p 25672:25672 \
-p 15671:15671 \
-p 15672:15672  \
rabbitmq:management

#设置开机随docker启动
docker update rabbitmq --restart=always

#端口详情
4369, 25672 (Erlang发现&集群端口)
5672, 5671 (AMQP端口)
15672 (web管理后台端口)
61613, 61614 (STOMP协议端口)
1883, 8883 (MQTT协议端口)
# 文档：https://www.rabbitmq.com/networking.html
```

访问控制台界面：`rabbitmq`的web管理后台端口为`15672`，所以访问`192.168.56.10:15672`，输入默认用户`guest`，默认密码`guest`即可访问控制他界面

###  3.3 RabbitMQ运行机制

**`AMQP`中的消息路由**：`AMQP`中消息的路由过程和`Java`开发者熟悉的`JMS`存在—些差别，`AMQP`中增加了`Exchange`和`Binding`的角色。生产者把消息发布到`Exchange`上，消息最终到达队列并被消费者接收，而`Binding`决定交换器的消息应该发送到哪个队列

![image-20230813161111178](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/image-20230813161111178.png)



**Exchange类型**：Exchange分发消息时根据类型的不同分发策略有区别，目前共四种类型:`direct`、 `fanout`、`topic`、`headers`。`headers`匹配AMQP消息的header而不是路由键，headers交换器和direct交换器完全一致，但性能差很多，自前几乎用不到了，所以直接着另外三种类型:



**Direct Exchange**：消息中的`路由键(routing key)`如果和`Binding`中的`bindingkey`一致，交换器就将消息发到对应的队列中，路由键与队列名需要完全匹配。如果一个队列绑定到交换机要求路由键为`“dog”`，则只转发`routing key`标记为`“dog"`的消息，不会转发`“dog.puppy”`，也不会转发`“dog.guard"`等等。它是完全匹配、单播的模式

![image-20230813162414331](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308202302219.png)



**Fanout Exchange**：每个发到 fanout类型交换器的消息都会分到所有绑定的队列上去。fanout交换器不处理路由键,只是简单的将队列绑定到交换器上，每个发送到交换器的消息都会被转发到与该交换器绑定的所有队列上。很像子网广播，每台子网内的主机都获得了一份复制的消息.。fanout类型转发消息是最快的

![image-20230813164311694](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308202302997.png)



**Topic Exchange**：topic交换器通过模式匹配分配消息的路由键属性，将路由键和某个模式进行匹配，此时队列需要绑定到一个模式上。它将路由键和绑定键的字符串切分成单词，这些单词之间用点隔开。它同样也会识别两个通配符：符号`“#"`和符号`“*”`。`“#”`匹配0个或多个单词，`“*”`匹配一个单词

![image-20230813165558739](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308202302903.png)



在RabbitMq控制台创建交换机和队列，并将交换机和队列进行绑定

![image-20230813213935069](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308202301998.png)

练习：通过控制台创建如下信息队列



![image-20230813214400541](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308202301865.png)



###   3.4 RabbitMQ整合&使用案例

在订单服务`gulimall-order`中对`RabbitMQ`进行整合

1.引入依赖`spring-boot-starter-amqp`

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

2.`application.properties`配置

```properties
spring.rabbitmq.host=192.168.56.10
```

3.在启动类上添加`@EnableRabbit`注解，开启`RabbitMQ`功能

```java
@EnableRabbit
@SpringBootApplication
public class gulimallOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(gulimallOrderApplication.class, args);
    }
}
```

4.创建交换机Exchange：使用AmqpAdmin创建交换机

```java
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMQTest {
    @Autowired
    private AmqpAdmin amqpAdmin;
    /**
     * 创建交换机Exchange
     * 使用AmqpAdmin创建名为hello-java-exchange的交换机Exchange
     */
    @Test
    public void createExchange() {
        // 构造器DirectExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments)
        // DirectExchange构造器参数：交换机名称、是否持久化、是否自动删除
        DirectExchange directExchange = new DirectExchange("hello-java-exchange", true, false);
        amqpAdmin.declareExchange(directExchange);
        log.info("Exchange[{}]创建成功", "hello-java-exchange");
    }
}
```

5.创建消息队列Queue：使用AmqpAdmin创建消息队列

```java
    @Autowired
    private AmqpAdmin amqpAdmin;
    /**
     * 创建消息队列Queue
     * 使用AmqpAdmin创建名为hello-java-queue的消息队列Queue
     */
    @Test
    public void createQueue() {
        // 构造器：public Queue(String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
        // Queue的构造参数：队列名、是否持久、是否排他、是否自动删除、自定义参数
        Queue queue = new Queue("hello-java-queue", true, false, false); // org.springframework.amqp.core.Queue包中的queue
        amqpAdmin.declareQueue(queue);
        log.info("Queue[{}]创建成功", "hello-java-queue");
    }
```

6.创建绑定关系Binding：使用AmqpAdmin创建绑定关系

```java
    @Autowired
    private AmqpAdmin amqpAdmin;
    /**
     * 创建绑定关系Binding
     */
    @Test
    public void createBinding() {
        //  Binding构造器参数：目的地、目的地类型、交换机、路由键、自定义参数
        //  public Binding(String destination, DestinationType destinationType, String exchange, String routingKey, Map<String, Object> arguments)
        //将exchange指定的交换机和destination目的地进行绑定，使用routingKey作为指定的路由键
        Binding binding = new Binding("hello-java-queue", Binding.DestinationType.QUEUE, "hello-java-exchange", "hello.java", null);
        amqpAdmin.declareBinding(binding);
        log.info("Binding[{}]创建成功", "hello-java-binding");
    }
```

7.使用`RabbitMQ`发送消息：使用`RabbitTemplate`的`convertAndSend`方法发送消息

```java
@Autowired
private RabbitTemplate rabbitTemplate;
/**
 * 使用RabbitMQ发送消息
 */
@Test
public void sendMessages() {
    //1、发送消息
    String msg = "Hello World!";
    rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", msg);

    // 2、如果发送的消息是个对象，我们会使用序列化机制，将对象写出去。对象必须实现Serializable
    // 发送的对象类型的消息，可以转成一个json
    for (int i = 0; i < 10; i++) {
        if (i % 2 == 0) {
            OrderReturnReasonEntity reasonEntity = new OrderReturnReasonEntity();
            reasonEntity.setId(1L);
            reasonEntity.setCreateTime(new Date());
            reasonEntity.setName("哈哈-" + i);
            rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", reasonEntity, new CorrelationData(UUID.randomUUID().toString()));
        } else {
            OrderEntity entity = new OrderEntity();
            entity.setOrderSn(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", entity, new CorrelationData(UUID.randomUUID().toString()));
        }
    }
    log.info("消息发送完成");
}
```

如果发送的消息是个对象，对象必须实现`Serializable`

```java
public class OrderReturnReasonEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Long id;
	/**
	 * 退货原因名
	 */
	private String name;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 启用状态
	 */
	private Integer status;
	/**
	 * create_time
	 */
	private Date createTime;

}
```

查看发送到消息队列中的消息，string类型字符串可以正常显示，对象类型的数据被序列化成一段字符串

![image-20230816042807082](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308202301435.png)



消息如果是序列化后的字符串就不方便查看消息的具体内容，我们可以通过配置使得发送的消息转成json类型：

```java
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class MyRabbitConfig {
    /**
     * 使用JSON序列化机制，进行消息转换
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

```

将消息转化成json类型后，再次发送消息，可以看到消息类型变成`content_type: application/json`

![image-20230816043952704](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308202300174.png)



8.监听消息(接收消息)

```java
/**
 * @RabbitListener注解: 监听RabbitMQ中的消息
 * queues：指明要监听的队列，可以监听多个消息队列
 * Object message：封装接收到的消息
 */
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {
    /**
     * @RabbitListener注解: 监听RabbitMQ中的消息
     * queues：指明要监听的队列，可以监听多个消息队列
     */
    @RabbitListener(queues = {"hello-java-queue"})
    public void receiveMessage(Object message) {
        System.out.println("接收到消息...内容：" + message +"  消息的类型：" + message.getClass());
        // 消息的类型： org.springframework.amqp.core.Message

    }
}
```

启动7中发送消息的单元测试方法，此处就能监听到消息并打印如下内容：可以发现`Object message`的类型在运行过程中的具体类型是基类`Object`的子类`org.springframework.amqp.core.Message`，所以可以将方法message的类型指定为`org.springframework.amqp.core.Message` 

```java
接收到消息...内容：(Body:{"id":1,"name":"哈哈","sort":null,"status":null,"createTime":1581144531744}, MessageProperties [headers={__TypeId__=java.lang.String}, contentType=application/json, contentEncoding=UTF-8, contentLength=0, receivedDeliveryMode=PERSISTENT, priority=0, redelivered=false, receivedExchange=hello-java-exchange, receivedRoutingKey=hello.java, deliveryTag=9, consumerTag=amq.ctag-KIWk4Y1Im1CpfhxpwnvyIA, consumerQueue=hello-java-queue])  消息的类型：class org.springframework.amqp.core.Message
```

接收消息的参数可以写成Message message：原生消息详细信息。包含 消息头、消息体等

```java
   /**
     * @RabbitListener注解: 监听RabbitMQ中的消息
     * queues：指明要监听的队列，可以监听多个消息队列
     * 接收消息的参数可以写成Message message：原生消息详细信息。包含 消息头、消息体等
     */
    @RabbitListener(queues = {"hello-java-queue"})
    public void receiveMessage(Message message) {
        // 消息体： {"id":1,"name":"哈哈","sort":null,"status":null,"createTime":1581144531744}
        byte[] body = message.getBody();
        // 消息头属性信息
        MessageProperties messageProperties = message.getMessageProperties();
        System.out.println("接收到消息...内容：" + message + "  消息的类型：" + message.getClass()); // 消息的类型： org.springframework.amqp.core.Message
    }
```

接收消息的参数也可以直接写成发送的消息体的类型： `OrderEntity content`

```java
    /**
     * @RabbitListener注解: 监听RabbitMQ中的消息
     * queues：指明要监听的队列，可以监听多个消息队列
     * 接收消息的参数也可以直接写成发送的消息体的类型： OrderEntity content
     */
    @RabbitListener(queues = {"hello-java-queue"})
    public void receiveMessage(Message message, OrderEntity content) {
        System.out.println("接收到消息：" + message);
        System.out.println("接收到的消息内容: " + content);
        // 接收到的消息内容: OrderEntity(id=1, memberId=null, orderSn=null, couponId=null, memberUsername=null, totalAmount=null, payAmount=null, freightAmount=null
    }
```

另外，监听消息队列的方法的参数也可以写成Channel channel：当前传输数据的通道

```java
    /**
     * @RabbitListener注解: 监听RabbitMQ中的消息
     * queues：指明要监听的队列，可以监听多个消息队列
     * 监听消息队列的方法的参数也可以写成Channel channel：当前传输数据的通道
     * Channel类型：  com.rabbitmq.client.Channel
     */
    @RabbitListener(queues = {"hello-java-queue"})
    public void receiveMessage(Message message, OrderEntity content, Channel channel) {
        System.out.println("接收到消息：" + message);
        System.out.println("接收到的消息内容: " + content);
        // 接收到的消息内容: OrderEntity(id=1, memberId=null, orderSn=null, couponId=null, memberUsername=null, totalAmount=null, payAmount=null, freightAmount=null
    }
```

可以同时允许很多人监听消息队列。只要接收到消息，队列中的消息就会被删除，而且只能有一个人接收到消息。只有一个消息完全处理完，方法运行结束，才可以接收到下一个消息

```java
    /**
     * 可以同时允许很多人监听消息队列。只要接收到消息，队列中的消息就会被删除，而且只能有一个人接收到消息
     * 场景演示：
     *          1）订单服务启动多个，同一个消息，只能有一个客户端服务接收到
     *          2) 只有一个消息完全处理完，方法运行结束，我们才可以接收到下一个消息
     */
    @RabbitListener(queues = {"hello-java-queue"})
    public void receiveMessage(Message message, OrderReturnReasonEntity content, Channel channel) throws InterruptedException {
        System.out.println("接收到消息：" + message);
        System.out.println("接收到的消息内容: " + content);
        Thread.sleep(3000);
        System.out.println("消息处理完成......");
    }
```

除了在方法上添加注解`@RabbitListener`来监听消息，我们也可以在类上面添加`@RabbitListener`注解并在类的方法上添加`@RabbitHandler` 对消息进行监听。 比如多个队列中的消息类型可能不一样，或者一个消息队列中也存在不同类型的消息，我们可以在类上面添加`@RabbitListener`注解，然后在类的方法上添加`@RabbitHandler` ，方法会自动地监听并获取相应类型的消息。下面我们产生`OrderReturnReasonEntity`和`OrderEntity`两种不同类型的消息，然后利用同一个类下的两个方法对各自的消息类型进行监听

产生`OrderReturnReasonEntity`和`OrderEntity`两种不同类型的消息：

```java
@RestController
public class RabbitController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @GetMapping("/sendMq")
    public String sendMq(@RequestParam(value = "num",defaultValue = "10") Integer num){
        for (int i=0;i<num;i++){
            if(i%2 == 0){
                OrderReturnReasonEntity reasonEntity = new OrderReturnReasonEntity();
                reasonEntity.setId(1L);
                reasonEntity.setCreateTime(new Date());
                reasonEntity.setName("哈哈-"+i);
                rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", reasonEntity,new CorrelationData(UUID.randomUUID().toString()));
            }else {
                OrderEntity entity = new OrderEntity();
                entity.setOrderSn(UUID.randomUUID().toString());
                rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", entity,new CorrelationData(UUID.randomUUID().toString()));
            }
        }
        return "ok";
    }
}
```

利用同一个类下的两个方法对各自的消息类型进行监听，`receiveMessage`方法会监听到`OrderReturnReasonEntity`类型的消息，`recieveMessage2`会监听到`OrderEntity`类型的消息

```java
@RabbitListener(queues = {"hello-java-queue"})
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {
    @RabbitHandler
    public void receiveMessage(OrderReturnReasonEntity content)  {
        System.out.println("receiveMessage方法接收到的消息内容: " + content);
    }
    @RabbitHandler
    public void recieveMessage2(OrderEntity content) {
        System.out.println("recieveMessage2方法接收到的消息内容："+content);
    }
}
```

监听消息的两个注解 `@RabbitListener`和`@RabbitHandler`比较：

-  `@RabbitListener`: 类或方法上（表示监听哪些队列）
-  `@RabbitHandler`：标在方法上（重载区分不同的消息）

9.`RabbitMQ`使用总结：

```sh
RabbitMQ使用
1.引入了amqp场景启动器，RabbitMqd的自动配置类RabbitAutoConfiguration 就会自动生效
2.自动配置类RabbitAutoConfiguration给容器中自动添加如下组件：
             RabbitTemplate
             AmqpAdmin
             CachingConnectionFactory
             RabbitMessagingTemplate
4.所有的属性配置都是RabbitProperties中进行绑定，配置的前缀为spring.rabbitmq 
      @ConfigurationProperties(prefix = "spring.rabbitmq")
      public class RabbitProperties
5.给配置文件中配置spring.rabbitmq信息
6.@EnableRabbit: @EnableXxxxx；开启功能
7.AmqpAdmin:管理组件，用于创建交换机、消息队列、绑定关系等
8.RabbitTemplate:消息发送处理组件
9.监听消息：使用@RabbitListener；启动类上必须有@EnableRabbit注解
        @RabbitListener: 类+方法上（监听哪些队列即可）
        @RabbitHandler：标在方法上（重载区分不同的消息）
```

###  3.5 RabbitMQ消息确认机制-可靠抵达

#####  3.5.1 可靠抵达

- 官方文档-可靠投递：`https://rabbitmq.com/reliability.html`

- 在分布式系统中，存在很多微服务，微服务连接消息队列RabbitMQ的服务器进行消息发送和消息监听。消息的收发过程中，可能会由于网络抖动、MQ服务器的宕机、收发消息的服务器宕机等原因导致收发消息出现异常。为了保证消息不丢失，可靠抵达，就要采取一些策略

- 保证消息不丢失，可靠抵达，可以使用事务消息，但是会导致性能下降250倍，为此引入确认机制
- `publisher confirmCallback` 确认模式：保证消息可靠到达服务端

- `publisher returnCallback`未投递到`queue`退回模式：确保消息可靠到达消息队列

- `consumer ack`机制：确保消费端收到消息

![image-20230817034448532](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308202300021.png)



#####  3.5.2 可靠抵达-`ConfirmCallback`

-  开启`ConfirmCallback`：`spring.rabbitmq.publisher-confirms=true`

-  在创建`connectionFactory`的时候设置`PublisherConfirms(true)`选项，开启`confirmcallback`
-  ``CorrelationData`:用来表示当前消息唯一性
-  `confirmCallback`触发时机：消息只要被`broker`接收到就会执行`confirmCallback`，如果是`cluster`模式，需要所有`broker`接收到才会调用`confirmCallback`
-  被`broker`接收到只能表示`message`已经到达服务器，并不能保证消息一定会被投递到目标`queue`里。所以需要用到接下来的`returnCallback`
-  如何开启`ConfirmCallback`功能：
   - 1.`spring.rabbitmq.publisher-confirms=true`
   - 2.设置确认回调`ConfirmCallback`，编写确认逻辑。只要服务器`broker`收到消息就会回调

```properties
#开启发送端确认
spring.rabbitmq.publisher-confirms=true
```

通过配置类定制`RabbitTemplate`,设置确认回调`ConfirmCallback`，编写确认逻辑：如果消息收到就打印`correlationData`关联数据，`ack`是否收到消息，如果失败打印失败的原因`cause`

```java
@Configuration
public class MyRabbitConfig {
    /**
     * 使用JSON序列化机制，进行消息转换
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 定制RabbitTemplate
     */
    @PostConstruct //MyRabbitConfig对象创建完成以后，执行这个方法
    public void initRabbitTemplate() {
        //设置确认回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             *
             * 1、只要消息成功抵达Broker，则ack=true
             * @param correlationData 当前消息的唯一关联数据（可以看成是消息的唯一id）
             * @param ack  消息是否成功收到
             * @param cause 失败的原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                /**
                 * 1、做好消息确认机制（pulisher，consumer【手动ack】）
                 * 2、每一个发送的消息都在数据库做好记录。定期将失败的消息再次发送一遍
                 */
                //服务器收到了；
                //修改消息的状态
                System.out.println("confirm...correlationData[" + correlationData + "]==>ack[" + ack + "]==>cause[" + cause + "]");
                // confirm...correlationData[CorrelationData [id=f4a53afd-dce0-45b1-85b5-db5bd7754e14]]==>ack[true]==>cause[null]
            }
        });
    }
}
```

将`RabbitTemplate`注入到`RabbitController`中进行消息发送，在发送的时候可以生成一个`UUID`用来构造`CorrelationData`，当消息成功(失败)到达后可以根据这个`CorrelationData`判断是哪个消息成功(失败)到达

```java
@RestController
public class RabbitController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @GetMapping("/sendMq")
    public String sendMq(@RequestParam(value = "num",defaultValue = "10") Integer num){
        for (int i=0;i<num;i++){
            if(i%2 == 0){
                OrderReturnReasonEntity reasonEntity = new OrderReturnReasonEntity();
                reasonEntity.setId(1L);
                reasonEntity.setCreateTime(new Date());
                reasonEntity.setName("哈哈-"+i);
                rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", reasonEntity,new CorrelationData(UUID.randomUUID().toString()));
            }else {
                OrderEntity entity = new OrderEntity();
                entity.setOrderSn(UUID.randomUUID().toString());
                rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", entity,new CorrelationData(UUID.randomUUID().toString()));
            }
        }
        return "ok";
    }
}
```

测试：访问`http://localhost:9000/sendMq`，消息成功抵达`Mq`服务器，控制台打印`confirm...correlationData[CorrelationData [id=f4a53afd-dce0-45b1-85b5-db5bd7754e14]]==>ack[true]==>cause[null]`

#####  3.5.3 可靠抵达-`ReturnCallback`：

- 开启发送端消息成功抵达队列的确认：`spring.rabbitmq.publisher-returns=true`

- 只要抵达队列，以异步发送优先回调`ReturnCallback`：`spring.rabbitmq.template.mandatory=true`
- ``confrim`模式只能保证消息到达`broker`，不能保证消息准确投递到目标queue里。在有些业务场景下，我们需要保证消息一定要投递到目标queue里，此时就需要用到return退回模式
- 这样如果未能投递到目标`queue`里将调用`returnCallback`，可以记录下详细到投递数据，定期的巡检或者自动纠错都需要这些数据
- 如何开启`ReturnCallback`功能：
  - 开启发送端消息成功抵达队列的确认：`spring.rabbitmq.publisher-returns=true`
  - 只要抵达队列，以异步发送优先回调`ReturnCallback`：`spring.rabbitmq.template.mandatory=true`
  - 说明：只要消息没有投递给指定的队列（投递失败），就触发失败回调

在`application.properties`中添加如下配置：

```properties
#开启发送端消息成功抵达队列的确认
spring.rabbitmq.publisher-returns=true
#只要抵达队列，以异步发送优先回调ReturnCallback
spring.rabbitmq.template.mandatory=true
```

通过配置类定制`RabbitTemplate`,设置失败回调`ReturnCallback`。编写确认逻辑：如果消息到达消息队列时失败了，就打印投递失败的消息详细信息`message`,   服务端回复的状态码`replyCode `, 服务端回复的文本内容`replyText`,当时这个消息发给哪个交换机`exchange`,当时这个消息用哪个路由键`routingKey `

```java
@Configuration
public class MyRabbitConfig {
    /**
     * 使用JSON序列化机制，进行消息转换
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 定制RabbitTemplate
     */
    @PostConstruct //MyRabbitConfig对象创建完成以后，执行这个方法
    public void initRabbitTemplate() {
        //设置消息抵达队列的确认回调
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             * 只要消息没有投递给指定的队列（投递失败），就触发这个失败回调
             * @param message   投递失败的消息详细信息
             * @param replyCode 服务端回复的状态码
             * @param replyText 服务端回复的文本内容
             * @param exchange  当时这个消息发给哪个交换机
             * @param routingKey 当时这个消息用哪个路由键
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                //报错误了。修改数据库当前消息的状态->错误。
                System.out.println("==>Fail Message[" + message + "]");
                System.out.println("==>replyCode[" + replyCode + "]");
                System.out.println("==>replyText[" + replyText + "]");
                System.out.println("==>exchange[" + exchange + "]");
                System.out.println("==>routingKey[" + routingKey + "]");
            }
        });
    }
}
```

使用`rabbitTemplate`发送消息，使用不存在的路由键`hello22.java`进行路由

```java
@RestController
public class RabbitController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @GetMapping("/sendMqFail")
    public String sendMqFail() {
        for (int i = 0; i < 5; i++) {
            OrderReturnReasonEntity reasonEntity = new OrderReturnReasonEntity();
            reasonEntity.setId(1L);
            reasonEntity.setCreateTime(new Date());
            reasonEntity.setName("哈哈-" + i);
            rabbitTemplate.convertAndSend("hello-java-exchange", "hello22.java", reasonEntity, new CorrelationData(UUID.randomUUID().toString()));
        }
        return "sendMqFail";
    }
}
```

测试：访问`http://localhost:9000//sendMqFail`，由于消息没有成功抵达消息队列，触发失败回调`ReturnCallback`，打印如下信息

```java
==>Fail Message[(Body:'{"id":1,"name":"哈哈-2","sort":null,"status":null,"createTime":1692298195359}' MessageProperties [headers={spring_returned_message_correlation=1d4def07-9387-4bd9-8c52-0ee4dd48eae0, __TypeId__=com.atguigu.gulimall.order.entity.OrderReturnReasonEntity}, contentType=application/json, contentEncoding=UTF-8, contentLength=0, receivedDeliveryMode=PERSISTENT, priority=0, deliveryTag=0])]
==>replyCode[312]
==>replyText[NO_ROUTE]
==>exchange[hello-java-exchange]
==>routingKey[hello22.java]
```



#####  3.5.4 可靠抵达-`Ack`消息确认机制

- 客户端默认是自动确认的，只要消息接收到，客户端会自动确认，服务端就会移除这个消息
  - 存在的问题：假如我们的客户端一次性收到很多消息，此时会自动回复给服务器ack，此时客户端宕机了，但是只有一个消息处理成功，其他消息就相当于发生了消息丢失
  - 解决方法：消费者手动确认模式。只要客户端没有明确告诉`MQ`货物被签收，客户端就不会发送`Ack`,消息就一直是`unacked`状态。即使`Consumer`宕机。消息不会丢失，会重新变为`Ready`，下一次有新的`Consumer`连接进来就发给他

- 消费者获取到消息，成功处理，可以回复`Ack`给`Broker`

  - `basic.ack`用于肯定确认；`broker`将移除此消息

  - `basic.nack`用于否定确认;可以指定`broker`是否丢弃此消息，可以批量

  - `basic.reject`用于否定确认;同上，但不能批量

- 默认，消息被消费者收到，就会从`broker`的`queue`中移除
- `queue`无消费者，消息依然会被存储，直到消费者消费
- 消费者收到消息，默认会自动`ack`。但是如果无法确定此消息是否被处理完成或者成功处理。我们可以开启手动`ack`模式
  - 消息处理成功,`ack()`，接受下一个消息，此消息`broker`就会移除
  - 消息处理失败,`nack()/reject()`，重新发送给其他人进行处理，或者容错处理后`ack`
  - 消息一直没有调用`ack/nack`方法，`broker`认为此消息正在被处理，不会投递给别人此时客户端断开，消息不会被broker移除，会投递给别人



`application.properties`：添加配置，开启手动确认机制

```properties
#开启手动ack确认模式
spring.rabbitmq.listener.simple.acknowledge-mode=manual
```

在业务代码中编写确认或拒收的处理逻辑：

```java
@RabbitListener(queues = {"hello-java-queue"})
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {
    /*
      消费端确认机制：手动确认（保证每个消息被正确消费，此时才让broker删除这个消息
              spring.rabbitmq.listener.simple.acknowledge-mode=manual 手动签收
              1、默认是自 动确认的，只要消息接收到，客户端会自动确认，服务端就会移除这个消息
                  问题：
                    我们收到很多消息，自动回复给服务器ack，只有一个消息处理成功，宕机了。就会发生消息丢失；
                    消费者手动确认模式。只要我们没有明确告诉MQ，货物被签收。没有Ack，
                    消息就一直是unacked状态。即使Consumer宕机。消息不会丢失，会重新变为Ready，下一次有新的Consumer连接进来就发给他
              2、如何签收:
                   channel.basicAck(deliveryTag,false);签收；业务成功完成就应该签收
                   channel.basicNack(deliveryTag,false,true);拒签；业务失败，拒签
     */
    @RabbitListener(queues = {"hello-java-queue"})
    public void receiveMessage(Message message, OrderReturnReasonEntity content, Channel channel) {
        System.out.println("接收到消息：" + message);
        // channel内按顺序自增的
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("deliveryTag==>" + deliveryTag);

        try {
            if (deliveryTag % 2 == 0) {
                //签收货物，发送ack，非批量模式
                channel.basicAck(deliveryTag, false);
                System.out.println("签收了货物..." + deliveryTag);
            } else {
                //long deliveryTag, boolean multiple, boolean requeue
                // 拒签basicNack方法的参数：标签deliveryTag，是否批量，拒签的消息是否重新入队
                //退货 requeue=false 丢弃  requeue=true 发回服务器，服务器重新入队。
                channel.basicNack(deliveryTag, false, false);
                //long deliveryTag, boolean requeue
//                channel.basicReject();
                System.out.println("没有签收了货物..." + deliveryTag);
            }
        } catch (Exception e) {
            //网络中断
        }
    }
}
```

测试：访问`http://localhost:9000/sendMq`之前，在`receiveMessage`方法中打上断点，`sendMq`请求进入该方法后关闭程序(模拟客户端宕机)。查看`rabbitMq`控制台发现消息还存在，消息状态变为`Unacked`,重启程序后消息状态变为`ready`

![image-20230818053429641](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202308202259327.png)

###  3.6 RabbitMQ延时队列(实现定时任务)

**场景**：比如未付款订单，超过一定时间后，系统自动取消订单并释放占有物品

**常用解决方案**：spring的schedule定时任务轮询数据库

**缺点**：消耗系统内存、增加了数据库的压力、存在较大的时间误差

**解决**：`rabbitmq`的消息`TTL`和死信`Exchange`结合
