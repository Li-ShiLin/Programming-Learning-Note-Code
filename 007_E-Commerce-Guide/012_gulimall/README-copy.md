##  订单服务

##  1. 订单相关概念

###  1.1 订单中心

- 电商系统涉及到3流，分别时信息流，资金流，物流，而订单系统作为中枢将三者有机的集合起来
- 订单模块是电商系统的枢纽，在订单这个环节上需要获取多个模块的数据和信息，同时对这些信息进行加工处理后流向下个环节，这一系列就构成了订单的信息流通

###  1.2  订单构成

![image-20230824060153172](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012047903.png)



**用户信息**：

- 用户信息包括用户账号、用户等级、用户的收货地址、收货人、收货人电话等组成，用户账户需要绑定手机号码，但是用户绑定的手机号码不一定是收货信息上的电话。用户可以添加多个收货信息，用户等级信息可以用来和促销系统进行匹配，获取商品折扣，同时用户等级还可以获取积分的奖励等

**订单基础信息**：

- 订单基础信息是订单流转的核心，其包括订单类型、父/子订单、订单编号、订单状态、订单流转的时间等

- 订单类型包括实体商品订单和虚拟订单商品等，这个根据商城商品和服务类型进行区分
- 同时订单都需要做父子订单处理，之前在初创公司一直只有一个订单，没有做父子订单处理后期需要进行拆单的时候就比较麻烦，尤其是多商户商场，和不同仓库商品的时候，父子订单就是为后期做拆单准备的
- 订单编号不多说了，需要强调的一点是父子订单都需要有订单编号，需要完善的时候可以对订单编号的每个字段进行统一定义和诠释
- 订单状态记录订单每次流转过程，后面会对订单状态进行单独的说明
- 订单流转时间需要记录下单时间，支付时间，发货时间，结束时间/关闭时间等等

**商品信息**：

- 商品信息从商品库中获取商品的sKU信息、图片、名称、属性规格、商品单价、商户信息等，从用户下单行为记录的用户下单数量，商品合计价格等

**优惠信息**：

- 优惠信息记录用户参与的优惠活动,包括优惠促销活动，比如满减、满赠、秒杀等，用户使用的优惠券信息，优惠券满足条件的优惠券需要默认展示出来，具体方式已在之前的优惠券篇章做过详细介绍，另外还虚拟币抵扣信息等进行记录

- 为什么把优惠信息单独拿出来而不放在支付信息里面呢?因为优惠信息只是记录用户使用的条目,而支付信息需要加入数据进行计算,所以做为区分

**支付信息**：

- 支付流水单号，这个流水单号是在唤起网关支付后支付通道返回给电商业务平台的支付流水号，财务通过订单号和流水单号与支付通道进行对账使用
- 支付方式用户使用的支付方式，比如微信支付、支付宝支付、钱包支付、快捷支付等。支付方式有时候可能有两个――余额支付+第三方支付
- 商品总金额，每个商品加总后的金额;运费，物流产生的费用﹔优惠总金额，包括促销活动的优惠金额，优惠券优惠金额，虚拟积分或者虚拟币抵扣的金额，会员折扣的金额等之和;实付金额,用户实际需要付款的金额。用户实付金额=商品总金额+运费-优惠总金额

**物流信息**：

- 物流信息包括配送方式,物流公司，物流单号，物流状态，物流状态可以通过第三方接口来获取和向用户展示物流每个状态节点

###  1.3 订单状态

**1.待付款**：用户提交订单后，订单进行预下单，目前主流电商网站都会唤起支付，便于用户快速完成支付，需要注意的是待付款状态下可以对库存进行锁定，锁定库存需要配置支付超时时间，超时后将自动取消订单，订单变更关闭状态

**2.已付款/待发货**：用户完成订单支付,订单系统需要记录支付时间,支付流水单号便于对账，订单下放到 WMS系统，仓库进行调拨，配货，分拣，出库等操作

**3.待收货/已发货**：仓储将商品出库后，订单进入物流环节，订单系统需要同步物流信息，便于用户实时知悉物品物流状态

**4.已完成工**：用户确认收货后，订单交易完成。后续支付侧进行结算，如果订单存在间题进入售后状态

**5.已取消**：付款之前取消订单。包括超时未付款或用户商户取消订单都会产生这种订单状态

**6.售后中**：用户在付款后申请退款，或商家发货后用户申请退换货。售后也同样存在各种状态，当发起售后申请后生成售后订单，售后订单状态为待审核，等待商家审核，商家审核通过后订单状态变更为待退货，等待用户将商品寄回，商家收货后订单状态更新为待退款状态，退款到用户原账户后订单状态更新为售后成功

###  1.4 订单流程

订单流程是指从订单产生到完成整个流转的过程，从而行程了一套标准流程规则。而不同的产品类型或业务类型在系统中的流程会千差万别,比如上面提到的线上实物订单和虚拟订单的流程，线上实物订单与O2O订单等，所以需要根据不同的类型进行构建订单流程。不管类型如何，订单都包括正向流程和逆向流程，对应的场景就是购买商品和退换货流程，正向流程就是一个正常的网购步骤:订单生成->支付订单->卖家发货→确认收货->交易成功。而每个步骤的背后，订单是如何在多系统之间交互流转的，可概括如下图



![image-20230824063148132](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012047459.png)



**1.订单创建与支付**

- (1) 订单创建前需要预览订单，选择收货信息等
- (2) 订单创建需要锁定库存，库存有才可创建，否则不能创建
- (3) 订单创建后超时未支付需要解锁库存
- (4) 支付成功后，需要进行拆单，根据商品打包方式，所在仓库，物流等进行拆单
- (5) 支付的每笔流水都需要记录，以待查账
- (6) 订单创建，支付成功等状态都需要给`MQ`发送消息，方便其他系统感知订阅

**2.逆向流程**

- (1) 修改订单，用户没有提交订单，可以对订单一些信息进行修改，比如配送信息,优惠信息，及其他一些订单可修改范围的内容，此时只需对数据进行变更即可 
- (2) 订单取消，用户主动取消订单和用户超时未支付，两种情况下订单都会取消订单，而超时情况是系统自动关闭订单，所以在订单支付的响应机制上面要做支付的

##  2. 创建订单服务 & 搭建页面环境

###  2.1 静态资源上传\域名环境

**上传静态资源到`linux`服务器**

将**订单详情页**相关的静态资源上传到linux服务器的特定目录

```sh
[root@localhost static]# pwd
/mydata/nginx/html/static
[root@localhost static]# mkdir order
[root@localhost static]# cd order
[root@localhost order]# pwd
/mydata/nginx/html/static/order
[root@localhost order]# mkdir detail
# 在本地windous上利用scp命令传输文件到Linux服务器
scp -r D:\learn\大型电商--谷粒商城\2.分布式高级篇（微服务架构篇）\代码\html\等待付款\bootstrap   root@192.168.56.10:/mydata/nginx/html/static/order/detail
scp -r D:\learn\大型电商--谷粒商城\2.分布式高级篇（微服务架构篇）\代码\html\等待付款\css  root@192.168.56.10:/mydata/nginx/html/static/order/detail
scp -r D:\learn\大型电商--谷粒商城\2.分布式高级篇（微服务架构篇）\代码\html\等待付款\image  root@192.168.56.10:/mydata/nginx/html/static/order/detail
scp -r D:\learn\大型电商--谷粒商城\2.分布式高级篇（微服务架构篇）\代码\html\等待付款\img  root@192.168.56.10:/mydata/nginx/html/static/order/detail
scp -r D:\learn\大型电商--谷粒商城\2.分布式高级篇（微服务架构篇）\代码\html\等待付款\js  root@192.168.56.10:/mydata/nginx/html/static/order/detail
scp -r D:\learn\大型电商--谷粒商城\2.分布式高级篇（微服务架构篇）\代码\html\等待付款\swiper  root@192.168.56.10:/mydata/nginx/html/static/order/detail
```

![image-20230824002133256](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012048307.png)

将**订单页**相关的静态资源上传到linux服务器的特定目录：

```sh
[root@localhost detail]# cd ../
[root@localhost order]# pwd
/mydata/nginx/html/static/order
[root@localhost order]# mkdir list
# 在本地windous上利用scp命令传输文件到Linux服务器
scp -r D:\learn\大型电商--谷粒商城\2.分布式高级篇（微服务架构篇）\代码\html\订单页  root@192.168.56.10:/mydata/nginx/html/static/order
# 删除不需要的文件
[root@localhost order]# mv 订单页 list
[root@localhost order]# cd list/
[root@localhost list]# rm -rf index.html
```

![image-20230824004002402](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012048946.png)



将**订单确认页**相关的静态资源上传到linux服务器的特定目录：

```sh
[root@localhost list]# cd ../
[root@localhost order]# pwd
/mydata/nginx/html/static/order


scp -r D:\learn\大型电商--谷粒商城\2.分布式高级篇（微服务架构篇）\代码\html\结算页  root@192.168.56.10:/mydata/nginx/html/static/order

[root@localhost order]# mv 结算页/ confirm
[root@localhost order]# cd confirm/
[root@localhost confirm]# ll
[root@localhost confirm]# pwd
/mydata/nginx/html/static/order/confirm
[root@localhost confirm]# rm -rf index.html
```

![image-20230824004941978](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012048598.png)

将**支付页**相关的静态资源上传到linux服务器的特定目录：

```sh
# 在本地windous上利用scp命令传输文件到Linux服务器
scp -r D:\learn\大型电商--谷粒商城\2.分布式高级篇（微服务架构篇）\代码\html\收银页  root@192.168.56.10:/mydata/nginx/html/static/order
[root@localhost order]# mv 收银页/ pay
[root@localhost order]# cd pay/
[root@localhost pay]# rm -rf index.html
```



![image-20230824005949156](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012048958.png)



配置域名，配置`nginx`转发规则(之前已经配置好了)

```
192.168.56.10 order.gulimall.com
```



![image-20230824011353261](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012048790.png)



在网关服务`gulimall-gateway`中添加订单服务的转发规则,修改`application.yml`：

```yaml
spring:
  cloud:
    gateway:
      routes:
# 转发到订单服务
        - id: gulimall_order_route
          uri: lb://gulimall-order
          predicates:
            - Host=order.gulimall.com
```

更改页面中图片和链接的路径：



![image-20230824020229237](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012049741.png)



在订单服务的`pom.xml`添加`thymeleaf`依赖，并禁用`thymeleaf`的缓存

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```

添加`HelloController`，测试各个页面是否能够正常访问\显示

```java
@Controller
public class HelloController {
    @GetMapping("/{page}.html")
    public String listPage(@PathVariable("page") String page){
        System.out.println(page);
        return page;
    }
}
```

测试：依次访问如下链接，可成功跳转到**订单确认页**、**订单页**、**订单详情页**、**支付页**

```
http://order.gulimall.com/confirm.html
http://order.gulimall.com/list.html
http://order.gulimall.com/detail.html
http://order.gulimall.com/pay.html
```

###  2.2 整合springsession & 配置线程池 & 页面跳转环境

**一、为订单服务`gu-limall-order`整合`springsession`**

`spring-boot-starter-data-redis`默认使用`lettuce`作为底层的连接客户端，而`lettuce`存在内存泄漏的问题，此处将其排除并使用`jedis`作为连接客户端

```xml
        <!-- 引入redis -->
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
        
        <!--整合SpringSession完成session共享问题-->
        <dependency>
            <groupId>org.springframework.session</groupId>
            <artifactId>spring-session-data-redis</artifactId>
        </dependency>

```

`application.properties`：配置redis连接，指定springsession的存储方式

```properties
spring.redis.host=192.168.56.10
# 指定session的保存方式是redis
spring.session.store-type=redis
# 指定session的过期时间为30分钟
server.servlet.session.timeout=30m
```

在启动类上添加`@EnableRedisHttpSession`：

```java
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
@EnableRedisHttpSession
@SpringBootApplication
public class gulimallOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(gulimallOrderApplication.class, args);
    }
}
```

`GulimallSessionConfig`：

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

    // 自定义redis保存session时的序列化器
    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }
}
```

**二、为订单服务`gulimall-order`线程池配置**

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

**三、修改个页面的跳转连接、获取session中的用户信息**：

修改`gulimall-product`的`index.html`首页：点击我的订单可以跳转到订单服务`gulimall-order`的页面

```html
<li>
    <a href="http://order.gulimall.com/detail.html">我的订单</a>
</li>
```

在订单服务`gulimall-order`的`detail.html`页面获取session中共享的用户信息

```html
<li>
    <a  th:if="${session.loginUser!=null}">欢迎：[[${session.loginUser==null?'':session.loginUser.nickname}]]</a>
    <a href="http://auth.gulimall.com/login.html" th:if="${session.loginUser==null}">欢迎，请登录</a>
</li>
<li th:if="${session.loginUser==null}">
    <a  href="http://auth.gulimall.com/reg.html" class="li_2">免费注册</a>
</li>
```

在订单服务`gulimall-order`的`confirm.html`页面获取session中共享的用户信息

```html
<li>[[${session.loginUser==null?'':session.loginUser.nickname}]]
    <img src="/static/order/confirm/img/03.png" style="margin-bottom: 0px;margin-left3: 3px;"/>
    <img src="/static/order/confirm/img/06.png"/>
</li>
```

在订单服务`gulimall-order`的`pay.html`页面获取session中共享的用户信息

```html
<ul>
  <li><span>[[${session.loginUser==null?'':session.loginUser.nickname}]]</span><span>退出</span></li>
  <li>我的订单</li>
  <li>支付帮助</li>
</ul>
```

测试：社交登录之后在订单服务的各个页面都可以获取到用户信息

### 2.3  订单登录拦截

当访问订单服务进行结算等操作时，要**确保用户处于登录状态**，所以要在订单服务`gulimall-order`中添加登录拦截器，如果用户没有登录就要跳转到登录页进行登录

`LoginUserInterceptor`：

```java
@Component
public class LoginUserInterceptor implements HandlerInterceptor {
    public static ThreadLocal<MemberRespVo> loginUser = new ThreadLocal<>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //  /order/order/status/2948294820984028420
        String uri = request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean match = antPathMatcher.match("/order/order/status/**", uri);
        boolean match1 = antPathMatcher.match("/payed/notify", uri);
        if (match || match1) {
            return true;
        }
        MemberRespVo attribute = (MemberRespVo) request.getSession().getAttribute(AuthServerConstant.LOGIN_USER);
        if (attribute != null) {
            loginUser.set(attribute);
            return true;
        } else {
            //没登录就去登录
            request.getSession().setAttribute("msg", "请先进行登录");
            response.sendRedirect("http://auth.gulimall.com/login.html");
            return false;
        }
    }
}
```

`OrderWebConfiguration`：添加`LoginUserInterceptor`拦截器配置使拦截器生效

```java
@Configuration
public class OrderWebConfiguration implements WebMvcConfigurer {
    @Autowired
    LoginUserInterceptor interceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }
}
```

##  3. 订单确认页实现

### 3.1 订单确认页数据模型封装

订单确认页逻辑如图所示：

![image-20230831000353764](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012050999.png)

可以发现订单结算页，包含以下信息：

- 1.收货人信息：有更多地址，即有多个收货地址，其中有一个默认收货地址
- 2.支付方式：货到付款、在线支付，不需要后台提供
- 3.送货清单：配送方式(不做）及商品列表（根据购物车选中的`skuld`,到数据库中查询)
- 4.发票：不做
- 5.优惠：查询用户领取的优惠券（不做）及可用积分（京豆)

`OrderConfirmVo`：

```java
//订单确认页需要用的数据
public class OrderConfirmVo {
    //// 收货地址，ums_member_receive_address表
    @Setter
    @Getter
    List<MemberAddressVo> address;
    //所有选中的购物项
    @Setter
    @Getter
    List<OrderItemVo> items;
    //发票记录....
    //优惠券信息...
    @Setter
    @Getter
    Integer integration;
    @Setter
    @Getter
    Map<Long, Boolean> stocks;
    //防重令牌
    @Setter
    @Getter
    String orderToken;
    public Integer getCount() {
        Integer i = 0;
        if (items != null) {
            for (OrderItemVo item : items) {
                i += item.getCount();
            }
        }
        return i;
    }
//    BigDecimal total;//订单总额
    public BigDecimal getTotal() {
        BigDecimal sum = new BigDecimal("0");
        if (items != null) {
            for (OrderItemVo item : items) {
                BigDecimal multiply = item.getPrice().multiply(new BigDecimal(item.getCount().toString()));
                sum = sum.add(multiply);
            }
        }
        return sum;
    }
//    BigDecimal payPrice;
    public BigDecimal getPayPrice() {
        return getTotal();
    }
    //应付价格
}
```

`MemberAddressVo`：

```java
// 收货地址
@Data
public class MemberAddressVo {
    private Long id;
    /**
     * member_id
     */
    private Long memberId;
    /**
     * 收货人姓名
     */
    private String name;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮政编码
     */
    private String postCode;
    /**
     * 省份/直辖市
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区
     */
    private String region;
    /**
     * 详细地址(街道)
     */
    private String detailAddress;
    /**
     * 省市区代码
     */
    private String areacode;
    /**
     * 是否默认
     */
    private Integer defaultStatus;
}
```

`OrderItemVo`：

```java
@Data
public class OrderItemVo {
    private Long skuId;
    private String title;
    private String image;
    private List<String> skuAttr;
    private BigDecimal price;
    private Integer count;
    private BigDecimal totalPrice;
    private BigDecimal weight;   
}
```

###  3.2 订单确认页后端实现

#####  1.调用会员服务获取用户收货地址\获取购物车商品

引入`openfeign`远程调用依赖：

```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
```

开启远程调用功能：

```java
@EnableFeignClients
@EnableDiscoveryClient
public class gulimallOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(gulimallOrderApplication.class, args);
    }
}
```

远程调用接口定义：远程调用会员服务`gulimall-member`

```java
@FeignClient("gulimall-member")
public interface MemberFeignService {
    @GetMapping("/member/memberreceiveaddress/{memeberId}/addresses")
    List<MemberAddressVo> getAddress(@PathVariable("memeberId") Long memberId);
}
```

远程调用购物车服务`gulimall-cart`获取购物车中的所有商品

```java
@FeignClient("gulimall-cart")
public interface CartFeignService {
    @GetMapping("/currentUserCartItems")
    List<OrderItemVo> getCurrentUserCartItems();
}
```

会员服务`gulimall-member`添加接口：返回会员的所有收货地址列表

```java
@RestController
@RequestMapping("member/memberreceiveaddress")
public class MemberReceiveAddressController {
    @Autowired
    private MemberReceiveAddressService memberReceiveAddressService;
    @GetMapping("/{memeberId}/addresses")
    public List<MemberReceiveAddressEntity> getAddress(@PathVariable("memeberId") Long memberId){
        return memberReceiveAddressService.getAddress(memberId);
    }
}
```

`MemberReceiveAddressService`：

```java
public interface MemberReceiveAddressService extends IService<MemberReceiveAddressEntity> {
    List<MemberReceiveAddressEntity> getAddress(Long memberId);
}
```

`MemberReceiveAddressServiceImpl`：

```java
@Service("memberReceiveAddressService")
public class MemberReceiveAddressServiceImpl extends ServiceImpl<MemberReceiveAddressDao, MemberReceiveAddressEntity> implements MemberReceiveAddressService {
    @Override
    public List<MemberReceiveAddressEntity> getAddress(Long memberId) {
        return this.list(new QueryWrapper<MemberReceiveAddressEntity>().eq("member_id", memberId));
    }
}
```

#####  2. 调用仓储服务`gulimall-ware`查询运费和收件人地址

`WareInfoController`：

```java
@RestController
@RequestMapping("ware/wareinfo")
public class WareInfoController {
    @Autowired
    private WareInfoService wareInfoService;
    @GetMapping("/fare")
    public R getFare(@RequestParam("addrId") Long addrId){
        FareVo fare = wareInfoService.getFare(addrId);
        return R.ok().setData(fare);
    }
}
```

`WareInfoService`：

```java
public interface WareInfoService extends IService<WareInfoEntity> {
    /**
     * 根据用户的收货地址计算运费
     */
    FareVo getFare(Long addrId);
}
```

`WareInfoServiceImpl`：

```java
@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {
    @Autowired
    MemberFeignService memberFeignService;

    @Override
    public FareVo getFare(Long addrId) {
        FareVo fareVo = new FareVo();
        R r = memberFeignService.addrInfo(addrId);
        MemberAddressVo data = r.getData("memberReceiveAddress",new TypeReference<MemberAddressVo>() {
        });
        if(data!=null){
            String phone = data.getPhone();
            //123
            String substring = phone.substring(phone.length() - 1, phone.length());
            BigDecimal bigDecimal = new BigDecimal(substring);
            fareVo.setAddress(data);
            fareVo.setFare(bigDecimal);
            return fareVo;
        }
        return null;
    }
}
```

`MemberFeignService`：

```java
@FeignClient("gulimall-member")
public interface MemberFeignService {
    @RequestMapping("/member/memberreceiveaddress/info/{id}")
    R addrInfo(@PathVariable("id") Long id);
}
```

#####  3.调用会员服务获取当前登录用户的购物车中所有的购物项商品列表

远程调用购物车服务`gulimall-cart`获取当前登录用户的购物车中所有的购物项商品列表

`CartController`：

```java
@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    /**
     * 获取当前用户的所有购物车商品列表
     */
    @GetMapping("/currentUserCartItems")
    @ResponseBody
    public List<CartItem> getCurrentUserCartItems(){
        return cartService.getUserCartItems();
    }
}
```

`CartService`：

```java
public interface CartService {
    List<CartItem> getUserCartItems();
}
```

`CartServiceImpl`：

```java
@Slf4j
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductFeignService productFeignService;
    
    @Override
    public List<CartItem> getUserCartItems() {
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        if(userInfoTo.getUserId()==null){
            return null;
        }else{
            String cartKey = CART_PREFIX + userInfoTo.getUserId();
            List<CartItem> cartItems = getCartItems(cartKey);

            //获取所有被选中的购物项
            List<CartItem> collect = cartItems.stream()
                    .filter(item -> item.getCheck())
                    .map(item->{
                        R price = productFeignService.getPrice(item.getSkuId());
                        //TODO 1、更新为最新价格
                        String data = (String) price.get("data");
                        item.setPrice(new BigDecimal(data));
                        return item;
                    })
                    .collect(Collectors.toList());

            return collect;
        }
    }
}
```

#####  4.调用商品服务获取商品价格

`ProductFeignService`:

```java
@FeignClient("gulimall-product")
public interface ProductFeignService {
    /**
     * 查询商品价格
     */
    @GetMapping("/product/skuinfo/{skuId}/price")
    R getPrice(@PathVariable("skuId") Long skuId);
}
```

商品服务`gulimall-product`中添加接口，获取商品价格：`SkuInfoController`：

```java
@RestController
@RequestMapping("product/skuinfo")
public class SkuInfoController {
    @Autowired
    private SkuInfoService skuInfoService;
    /**
     * 获取商品价格
     */
    @GetMapping("/{skuId}/price")
    public R getPrice(@PathVariable("skuId") Long skuId) {
        SkuInfoEntity byId = skuInfoService.getById(skuId);
        return R.ok().setData(byId.getPrice().toString());
    }
}
```

#####  5.订单确认实现

在购物车服务`gulimall-cart`中点击`去结算`按钮后，跳转到订单服务`gulimall-order`，在购物车页面`cartList.html`中增加跳转链接

```js
<div>
    <button onclick="toTrade()" type="button">去结算</button>
</div>
<script type="text/javascript">
	function toTrade() {
		window.location.href = "http://order.gulimall.com/toTrade";
	}
</script>
```

`OrderWebController`：

```java
@Controller
public class OrderWebController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/toTrade")
    public String toTrade(Model model, HttpServletRequest request) throws ExecutionException, InterruptedException {
        OrderConfirmVo confirmVo = orderService.confirmOrder();
        model.addAttribute("orderConfirmData", confirmVo);
        //展示订单确认的数据
        return "confirm";
    }
}
```

`OrderService`：

```java
public interface OrderService extends IService<OrderEntity> {
    /**
     * 给订单确认页返回要展示的全部信息
     */
    OrderConfirmVo confirmOrder();
}
```

`OrderServiceImpl`：

```java
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {
    @Autowired
    MemberFeignService memberFeignService;
    @Autowired
    CartFeignService cartFeignService;

    @Override
    public OrderConfirmVo confirmOrder() {
        OrderConfirmVo confirmVo = new OrderConfirmVo();
        MemberRespVo memberRespVo = LoginUserInterceptor.loginUser.get();

        // 1.远程查询当前用户所有的收货地址列表
        List<MemberAddressVo> address = memberFeignService.getAddress(memberRespVo.getId());
        confirmVo.setAddress(address);

        // 2.远程查询购物车中所有选中的购物项
        List<OrderItemVo> items = cartFeignService.getCurrentUserCartItems();
        confirmVo.setItems(items);

        // 3. 查询用户积分
        Integer integration = memberRespVo.getIntegration();
        confirmVo.setIntegration(integration);
        return confirmVo;
    }
}
```

#####  6.Feign远程调用丢失请求头问题

**Feign远程调用丢失请求头问题**：访问订单服务进行订单确认的过程中，虽然请求中携带了请求头，但是利用feign远程调用购物车服务的时候，feign在构造请求的时候默认不会包含原来的请求头。所以在订单服务`gulimall-order`调用购物车服务`gulimll-cart`的接口`/currentUserCartItems`获取当前用户的所有购物车商品列表的时候，请求头中的`cookie`丢失了，购物车服务就无法根据这个`cookie`获取到用户信息，所以也就无法获取当前用户的所有购物车商品列表。请求头丢失的示意图如下：

![image-20230827202644848](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012050907.png)



**Feign远程调用丢失请求头解决方案**：加上feign远程调用的请求拦截器，利用拦截器在远程调用前将老请求中的Cookie等请求头同步到新请求中

![image-20230827204829122](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012051577.png)



`GuliFeignConfig`：在订单服务`gulimall-order`中添加feign远程调用的请求拦截器，在该拦截器中添加浏览器请求中携带的`Cookie`

```java
// feign在远程调用之前都经过拦截器的apply方法
@Configuration
public class GuliFeignConfig {

    @Bean("requestInterceptor")
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                //1、使用RequestContextHolder拿到刚进来的这个请求
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes != null) {
                    System.out.println("RequestInterceptor线程...." + Thread.currentThread().getId());
                    HttpServletRequest request = attributes.getRequest(); //老请求
                    if (request != null) {
                        //同步请求头数据，Cookie
                        String cookie = request.getHeader("Cookie");
                        //给新请求同步了老请求的cookie
                        template.header("Cookie", cookie);
                    }
                }
            }
        };
    }
}
```

对多个`feign`的远程调用进行异步编排：

```java
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {
    @Autowired
    MemberFeignService memberFeignService;
    @Autowired
    CartFeignService cartFeignService;
    @Autowired
    ThreadPoolExecutor executor;

    @Override
    public OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException {
        OrderConfirmVo confirmVo = new OrderConfirmVo();
        MemberRespVo memberRespVo = LoginUserInterceptor.loginUser.get();

        // 1.远程查询当前用户所有的收货地址列表
        CompletableFuture<Void> getAddressFuture = CompletableFuture.runAsync(() -> {
            List<MemberAddressVo> address = memberFeignService.getAddress(memberRespVo.getId());
            confirmVo.setAddress(address);
        }, executor);


        // 2.远程查询购物车中所有选中的购物项
        CompletableFuture<Void> cartFuture = CompletableFuture.runAsync(() -> {
            List<OrderItemVo> items = cartFeignService.getCurrentUserCartItems();
            confirmVo.setItems(items);
            //feign在远程调用之前要构造请求，调用很多的拦截器
            //RequestInterceptor interceptor : requestInterceptors
        }, executor);

        // 3. 查询用户积分
        Integer integration = memberRespVo.getIntegration();
        confirmVo.setIntegration(integration);

        CompletableFuture.allOf(getAddressFuture, cartFuture).get();
        return confirmVo;
    }
}
```

#####  7.Feign异步情况丢失上下文问题

**`Feign`异步情况丢失上下文问题**：通过测试发现，经过异步编排之后，`GuliFeignConfig`中的拦截器无法获取到老请求`request`。出现这个问题的原因是，浏览器的请求进来之后在主线程(假如线程id是72)中创建了两个异步的线程(假如线程id分别为101,102)，主线程中来自浏览器请求的请求上下文在这两个子线程中丢失了，所以feign请求拦截器中没有获取到老请求。解决的方法就是在创建了子线程之后要将主线程的请求上线文添加到子线程的线程上下文中



![image-20230827215205823](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012051207.png)

**`Feign`异步情况丢失上下文问题解决**：在利用异步编排创建多个线程之前，利用`RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();`获取浏览器传来的请求，在利用异步编排创建线程时利用`RequestContextHolder.setRequestAttributes(requestAttributes);`往新创建的线程中添加上请求的上下文信息



`OrderServiceImpl`：

```java
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {
    @Autowired
    MemberFeignService memberFeignService;
    @Autowired
    CartFeignService cartFeignService;
    @Autowired
    ThreadPoolExecutor executor;
    @Override
    public OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException {
        OrderConfirmVo confirmVo = new OrderConfirmVo();
        MemberRespVo memberRespVo = LoginUserInterceptor.loginUser.get();
        //获取之前的请求
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 1.远程查询当前用户所有的收货地址列表
        CompletableFuture<Void> getAddressFuture = CompletableFuture.runAsync(() -> {
            // 每一个线程都来共享之前的请求数据
            RequestContextHolder.setRequestAttributes(requestAttributes);
            List<MemberAddressVo> address = memberFeignService.getAddress(memberRespVo.getId());
            confirmVo.setAddress(address);
        }, executor);
        // 2.远程查询购物车中所有选中的购物项
        CompletableFuture<Void> cartFuture = CompletableFuture.runAsync(() -> {
            // 每一个线程都来共享之前的请求数据
            RequestContextHolder.setRequestAttributes(requestAttributes);
            List<OrderItemVo> items = cartFeignService.getCurrentUserCartItems();
            confirmVo.setItems(items);
            //feign在远程调用之前要构造请求，调用很多的拦截器
            //RequestInterceptor interceptor : requestInterceptors
        }, executor);

        // 3. 查询用户积分
        Integer integration = memberRespVo.getIntegration();
        confirmVo.setIntegration(integration);

        CompletableFuture.allOf(getAddressFuture, cartFuture).get();

        return confirmVo;
    }
}
```

### 3.3  订单确认页页面渲染

`OrderServiceImpl`完善：增加查询商品库存的功能

```java
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {
    @Autowired
    MemberFeignService memberFeignService;
    @Autowired
    CartFeignService cartFeignService;
    @Autowired
    WmsFeignService wmsFeignService;
    @Autowired
    ThreadPoolExecutor executor;
    @Override
    public OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException {
        OrderConfirmVo confirmVo = new OrderConfirmVo();
        MemberRespVo memberRespVo = LoginUserInterceptor.loginUser.get();

        //获取之前的请求
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        // 1.远程查询当前用户所有的收货地址列表
        CompletableFuture<Void> getAddressFuture = CompletableFuture.runAsync(() -> {
            // 每一个线程都来共享之前的请求数据
            RequestContextHolder.setRequestAttributes(requestAttributes);
            List<MemberAddressVo> address = memberFeignService.getAddress(memberRespVo.getId());
            confirmVo.setAddress(address);
        }, executor);


        // 2.远程查询购物车中所有选中的购物项
        CompletableFuture<Void> cartFuture = CompletableFuture.runAsync(() -> {
            // 每一个线程都来共享之前的请求数据
            RequestContextHolder.setRequestAttributes(requestAttributes);
            List<OrderItemVo> items = cartFeignService.getCurrentUserCartItems();
            confirmVo.setItems(items);
            //feign在远程调用之前要构造请求，调用很多的拦截器
            //RequestInterceptor interceptor : requestInterceptors
        }, executor).thenRunAsync(() -> {
            List<OrderItemVo> items = confirmVo.getItems();
            List<Long> collect = items.stream().map(item -> item.getSkuId()).collect(Collectors.toList());
            R hasStock = wmsFeignService.getSkusHasStock(collect);
            List<SkuStockVo> data = hasStock.getData(new TypeReference<List<SkuStockVo>>() {
            });
            if (data != null) {
                Map<Long, Boolean> map = data.stream().collect(Collectors.toMap(SkuStockVo::getSkuId, SkuStockVo::getHasStock));
                confirmVo.setStocks(map);
            }
        }, executor);

        // 3. 查询用户积分
        Integer integration = memberRespVo.getIntegration();
        confirmVo.setIntegration(integration);

        CompletableFuture.allOf(getAddressFuture, cartFuture).get();

        return confirmVo;
    }

}
```

地址：

```js
<!--主体部分-->
<p class="p1">填写并核对订单信息  <span style="color: red" th:if="${msg!=null}" th:text="${msg}"></span></p>
<div class="section">
    <!--收货人信息-->
    <div class="top-2">
        <span>收货人信息</span>
        <span>新增收货地址</span>
    </div>
    <!--			orderConfirmData-->
    <!--地址-->
    <div class="top-3 addr-item" th:each="addr:${orderConfirmData.address}">
        <p th:attr="def=${addr.defaultStatus},addrId=${addr.id}">[[${addr.name}]]</p><span>[[${addr.name}]]  [[${addr.province}]]  [[${addr.detailAddress}]]  [[${addr.phone}]]</span>
    </div>
    <p class="p2">更多地址︾</p>
    <div class="hh1"/>
</div>
```

购物项渲染：

```js
    <div class="yun1" th:each="item:${orderConfirmData.items}">
        <img style="width: 50px;height: 100px;" th:src="${item.image}" class="yun"/>
        <div class="mi">
            <p>[[${item.title}]] <span style="color: red;"> ￥[[${#numbers.formatDecimal(item.price,1,2)}]]</span>
                <span> x[[${item.count}]] </span>
                <span> [[${orderConfirmData.stocks[item.skuId]?"有货":"无货"}]] </span></p>
            <p><span>0.095kg</span></p>
            <p class="tui-1"><img src="/static/order/confirm/img/i_07.png"/>支持7天无理由退货</p>
        </div>
    </div>
```

商品金额渲染：

```js
<p class="qian_y">
    <span>[[${orderConfirmData.count}]]</span>
    <span>件商品，总商品金额：</span>
    <span class="rmb">￥[[${#numbers.formatDecimal(orderConfirmData.total,1,2)}]]</span>
</p>
```

页面显示运费和收件人地址：

```js
            <p class="qian_y">
                <span>运费： </span>
                <span class="rmb"> &nbsp ￥<b id="fareEle"></b></span>
            </p>
			<div class="yfze">
				<p class="yfze_a"><span class="z">应付总额：</span><span class="hq">￥<b id="payPriceEle">[[${#numbers.formatDecimal(orderConfirmData.payPrice,1,2)}]]</b></span>
				</p>
				<p class="yfze_b">寄送至： <span id="recieveAddressEle"></span> 收货人：<span id="recieverEle"></span></p>
			</div>
			<form action="http://order.gulimall.com/submitOrder" method="post">
				<input id="addrIdInput" type="hidden" name="addrId">
				<input id="payPriceInput" type="hidden" name="payPrice">
				<input type="hidden" name="orderToken" th:value="${orderConfirmData.orderToken}"/>
				<button class="tijiao" type="submit">提交订单</button>
			</form>
<script>
    $(document).ready(function () {
        $('.header-right li:nth-of-type(6)').hover(function () {
            $('.header-r-11').css('display', 'block')
        }, function () {
            $('.header-r-11').css('display', 'none')
        })
        $('.header-right li:nth-of-type(12)').hover(function () {
            $('.header-r-2').css('display', 'block')
        }, function () {
            $('.header-r-2').css('display', 'none')
        })
        $('.header-right li:nth-of-type(14)').hover(function () {
            $('.header-r-3').css('display', 'block')
        }, function () {
            $('.header-r-3').css('display', 'none')
        })
        $('.header-l-2').hover(function () {
            $('.header-l-d').css('display', 'block')
        }, function () {
            $('.header-l-d').css('display', 'none')
        })
        $('.header-r-4').hover(function () {
            $('.h-r-1').css('display', 'block')
        }, function () {
            $('.h-r-1').css('display', 'none')
        });

        highlight();
        getFare($(".addr-item p[def='1']").attr("addrId"));
    })


    function highlight() {
        $(".addr-item p").css({"border": "2px solid gray"})
        $(".addr-item p[def='1']").css({"border": "2px solid red"})
    }

    $(".addr-item p").click(function () {
        $(".addr-item p").attr("def", "0")
        $(this).attr("def", "1");
        highlight();
        //获取到当前的地址id
        var addrId = $(this).attr("addrId");
        //发送ajax获取运费信息
        getFare(addrId);
    });

    function getFare(addrId) {
        //给表单回填选择的地址
        $("#addrIdInput").val(addrId);
        $.get("http://gulimall.com/api/ware/wareinfo/fare?addrId=" + addrId, function (resp) {
            console.log(resp);
            //fareEle
            $("#fareEle").text(resp.data.fare);
            var total = [[${orderConfirmData.total}]];
            //设置运费等
            var payPrice = total * 1 + resp.data.fare * 1;
            $("#payPriceEle").text(payPrice);
            $("#payPriceInput").val(payPrice);

            //设置收货人信息
            $("#recieveAddressEle").text(resp.data.address.province + " " + resp.data.address.detailAddress);
            $("#recieverEle").text(resp.data.address.name);

        })
    }
</script>
```



##  4. 接口幂等性讨论

###  4.1 什么是幂等性

**接口幂等性就是用户对于同一操作发起的一次请求或者多次请求的结果是一致的**，不会因为多次点击而产生了副作用：比如说支付场景，用户购买了商品支付扣款成功，但是返回结果的时候网络异常，此时钱已经扣了，用户再次点击按钮，此时会进行第二次扣款，返回结果成功，用户查询余额返发现多扣钱了，流水记录也变成了两条，这就没有保证接口的幂等性

**哪些情况需要考虑接口幂等性**

- 用户多次点击按钮
- 用户页面回退再次提交
- 微服务互相调用，由于网络问题，导致请求失败。feign触发重试机制
- 其他业务情况

**3.什么情况下需要幂等**

以`SQL`为例，有些操作是天然幂等的

- `SELECT * FROM table WHER id=?`，无论执行多少次都不会改变状态，是天然的幂等
- `UPDATE tab1 SET col1=1 WHERE col2=2`，无论执行成功多少次状态都是一致的,也是幂等操作
- `delete from user where userid=1`，多次操作，结果—样，具备幂等性
- `insert into user(userid,name) values(1,a')` ，如`userid`为唯一主键，即重复操作上面的业务，只会插入一条用户数据，具备幂等性
- `UPDATE tab1 SET col1=col1+1 WHERE col2=2`，每次执行的结果都会发生变化，不是幂等的
- `insert into user(userid,name) values(1,a')` 如`userid`不是主键，可以重复，那上面业务多次操作，数据都会新增多条，不具备幂等性

在订单中，可以在数据库表级别实现订单的幂等性：将订单号字段 `order_sn`添加唯一性约束，保证每一个订单只有一条数据

```sql
CREATE TABLE `oms_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_sn` char(32) DEFAULT NULL COMMENT '订单号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_sn` (`order_sn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='订单'
```

###  4.2 幂等解决方案

#####  4.2.1 token机制(令牌机制)

 **token机制(令牌机制)**

- 1.服务端提供了发送token 的接口。我们在分析业务的时候，哪些业务是存在幂等问题的，就必须在执行业务前，先去获取`token`，服务器会把`token`保存到`redis`中
- 2.然后调用业务接口请求时，把`token`携带过去，一般放在请求头部
- 3.服务器判断`token`是否存在`redis`中，存在表示第一次请求，然后删除token,继续执行业务
- 4.如果判断`token`不存在`redis`中，就表示是重复操作，直接返回重复标记给client，这样就保证了业务代码，不被重复执行

**令牌机制危险性**:

- 1.先删除`token`还是后删除`token`：
  - (1)先删除可能导致，业务确实没有执行，重试还带上之前token，由于防重设计导致，
    请求还是不能执行
  - (2)后删除可能导致，业务处理成功，但是服务闪断，出现超时，没有删除token，别
    人继续重试，导致业务被执行两次
  - (3)我们最好设计为先删除 token，如果业务调用失败，就重新获取token再次请求
- 2.Token获取、比较和删除必须是原子性
  - (1) `redis.get(token)` 、 `token.equals`、`redis.del(token)`如果这两个操作不是原子，可能导致，高并发下，都get 到同样的数据，判断都成功，继续业务并发执行
  - (2)可以在redis使用lua脚本完成这个操作
    `if redis.call('get ', KEYs[1])==ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end`



##### 4.2.2 各种锁机制

**1.数据库悲观锁**

- `select * from xxxx where id = 1 for update`

- 悲观锁使用时一般伴随事务一起使用，数据锁定时间可能会很长，需要根据实际情况选用。另外要注意的是， id字段一定是主键或者唯一索引，不然可能造成锁表的结果，处理起来会非常麻烦


**2.数据库乐观锁**

- 这种方法适合在更新的场景中
- `update t_goods set count = count -1 , version = version + 1 where good_id=2 and version= 1` 
- 根据`version`版本，也就是在操作库存前先获取当前商品的`version`版本号，然后操作的时候带上此`version`号。我们梳理下，我们第一次操作库存时，得到version为1，调用库存服务version变成了2。但返回给订单服务出现了问题，订单服务又一次发起调用库存服务，当订单服务传如的version还是1，再执行上面的sql语句时，就不会执行。因为version已经变为2了，where条件就不成立。这样就保证了不管调用几次，只会真正的处理一次
- 乐观锁主要使用于处理读多写少的问题



**3.业务层分布式锁**

- 如果多个机器可能在同一时间同时处理相同的数据,比如多台机器定时任务都拿到了相同数据处理，我们就可以加分布式锁，锁定此数据，处理完成后释放锁。获取到锁的必须先判断这个数据是否被处理过


##### 4.2.3 各种唯一约束

**1.数据库唯一约束**

- 插入数据，应该按照唯一索引进行插入，比如订单号,相同的订单就不可能有两条记录插入。我们在数据库层面防止重复
- 这个机制是利用了数据库的主键唯一约束的特性，解决了在 insert场景时幂等问题。但主键的要求不是自增的主键，这样就需要业务生成全局唯一的主键
- 如果是分库分表场景下，路由规则要保证相同请求下，落地在同一个数据库和同一表中，要不然数据库主键约束就不起效果了，因为是不同的数据库和表主键不相关

**2.`redis set`防重**

- 很多数据需要处理，只能被处理一次，比如我们可以计算数据的`MD5` 将其放入`redis`的`set`，每次处理数据，先看这个`MD5`是否已经存在，存在就不处理


**3.防重表**

- 使用订单号`orderNo`做为去重表的唯一索引，把唯一索引插入去重表，再进行业务操作，且他们在同一个事务中。这个保证了重复请求时，因为去重表有唯一约束，导致请求失败，避免了幂等问题。这里要注意的是，去重表和业务表应该在同一库中，这样就保证了在同一个事务，即使业务操作失败了，也会把去重表的数据回滚。这个很好的保证了数据一致性。

- 之前说的 redis防重也算

#####  4.2.4 全局请求唯一id

调用接口时，生成一个唯一id，redis 将数据保存到集合中（去重)，存在即处理过。可以使用`nginx`设置每一个请求的唯一id 。`proxy_set_header X-Request-ld $request_id`



##  5.提交订单

###  5.1 订单提交流程

![image-20230831022724381](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012051813.png)





![image-20230831031110701](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012051423.png)



###  5.2 获取`spu`信息  

远程调用商品服务`gulimall-product`获取`spu`信息  

`ProductFeignService`：

```java
@FeignClient("gulimall-product")
public interface ProductFeignService {
    @GetMapping("/product/spuinfo/skuId/{id}")
    R getSpuInfoBySkuId(@PathVariable("id") Long skuId);
}
```

`SpuInfoController`：

```java
@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController {
    @Autowired
    private SpuInfoService spuInfoService;

    @GetMapping("/skuId/{id}")
    public R getSpuInfoBySkuId(@PathVariable("id") Long skuId){
        SpuInfoEntity entity = spuInfoService.getSpuInfoBySkuId(skuId);
        return R.ok().setData(entity);
    }
}
```

`SpuInfoService`：

```java
public interface SpuInfoService extends IService<SpuInfoEntity> {
    SpuInfoEntity getSpuInfoBySkuId(Long skuId);
}
```

`SpuInfoServiceImpl`：

```java
@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    private SkuInfoService skuInfoService;

    @Override
    public SpuInfoEntity getSpuInfoBySkuId(Long skuId) {
        SkuInfoEntity byId = skuInfoService.getById(skuId);
        Long spuId = byId.getSpuId();
        SpuInfoEntity spuInfoEntity = getById(spuId);
        return spuInfoEntity;
    }
}
```

###  5.3 锁库存

锁库存逻辑：

![image-20230831031254204](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012052379.png)

 在订单服务`gulimall-order`中添加`WareSkuLockVo`

```java
@Data
public class WareSkuLockVo {
    private String orderSn;//订单号
    private List<OrderItemVo> locks;//需要锁住的所有库存信息
}
```

调用仓库服务`gulimall-ware`进行锁库存：

```java
@FeignClient("gulimall-ware")
public interface WmsFeignService {
    @PostMapping("/ware/waresku/lock/order")
    R orderLockStock(@RequestBody WareSkuLockVo vo);
}
```

在库存服务`gulimall-ware`中实现锁库存逻辑

`WareSkuController`：

```java
@RestController
@RequestMapping("ware/waresku")
public class WareSkuController {
    @Autowired
    private WareSkuService wareSkuService;

    // 锁库存：返回库存的锁定结果
    @PostMapping("/lock/order")
    public R orderLockStock(@RequestBody WareSkuLockVo vo){
        try{
            Boolean stock = wareSkuService.orderLockStock(vo);
            return R.ok();
        }catch (NoStockException e){
            return R.error(BizCodeEnum.NO_STOCK_EXCEPTION.getCode(),e.getMessage());
        }
    }
}
```

`WareSkuService`：

```java
public interface WareSkuService extends IService<WareSkuEntity> {
    Boolean orderLockStock(WareSkuLockVo vo);
}
```

`WareSkuServiceImpl`：

```java
@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Autowired
    private WareSkuDao wareSkuDao;

    @Data
    class SkuWareHasStock {

        private Long skuId;
        private Integer num;
        private List<Long> wareId;
    }

    /**
     * 为某个订单锁定库存
     *
     * @param vo
     * @return
     */

    @Transactional   //  默认运行时异常都会回滚
    //    @Transactional(rollbackFor = NoStockException.class) // 出现NoStockException异常的时候一定要回滚
    @Override
    public Boolean orderLockStock(WareSkuLockVo vo) {
        // 实际做法：按照下单的收获地址，找到一个就近残酷，锁定库存。简化做法：找到一个有有库存的仓库即可
        // 1、找到每个商品在哪些仓库有库存
        List<OrderItemVo> locks = vo.getLocks();
        List<SkuWareHasStock> collect = locks.stream().map(item -> {
            SkuWareHasStock stock = new SkuWareHasStock();
            Long skuId = item.getSkuId();
            stock.setSkuId(skuId);
            stock.setNum(item.getCount()); // 要锁的商品数量，买几件就锁几件
            // 查询这个商品在哪里有库存
            List<Long> wareIds = wareSkuDao.listWareIdHasSkuStock(skuId);
            stock.setWareId(wareIds);
            return stock;
        }).collect(Collectors.toList());


        // 2、找到有库存的仓库后，对仓库进行锁定
        for (SkuWareHasStock hasStock : collect) {
            Boolean skuStocked = false;
            Long skuId = hasStock.getSkuId();
            List<Long> wareIds = hasStock.getWareId();
            if (wareIds == null || wareIds.size() == 0) {
                // 没有任何仓库有这个商品的库存,就抛出异常
                throw new NoStockException(skuId);
            }
            for (Long wareId : wareIds) {
                // 成功就是1，否则就是0
                Long count = wareSkuDao.lockSkuStock(skuId, wareId, hasStock.getNum());
                if (count == 1) {
                    skuStocked = true;
                    break;
                } else {
                    // 当前仓库锁库存失败，就尝试锁定下一个仓库

                }
            }
            if (skuStocked == false) {
                // 当前商品的所有仓库锁库存都没有锁住
                throw new NoStockException(skuId);
            }
        }
        // 3、程序能走到这里，锁库存操作肯定是成功的
        return true;
    }

}
```

`WareSkuDao`：

```java
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
    List<Long> listWareIdHasSkuStock(@Param("skuId") Long skuId);

    Long lockSkuStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("num") Integer num);

}
```

`WareSkuDao.xml`：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.atguigu.gulimall.ware.dao.WareSkuDao">
    <resultMap type="com.atguigu.gulimall.ware.entity.WareSkuEntity" id="wareSkuMap">
        <result property="id" column="id"/>
        <result property="skuId" column="sku_id"/>
        <result property="wareId" column="ware_id"/>
        <result property="stock" column="stock"/>
        <result property="skuName" column="sku_name"/>
        <result property="stockLocked" column="stock_locked"/>
    </resultMap>

    <select id="listWareIdHasSkuStock" resultType="java.lang.Long">
        SELECT ware_id
        FROM `wms_ware_sku`
        WHERE sku_id = #{skuId}
          AND stock - stock_locked > 0
    </select>

    <update id="lockSkuStock">
        UPDATE `wms_ware_sku`
        SET stock_locked = stock_locked + #{num}
        WHERE sku_id = #{skuId}
          AND ware_id = #{wareId}
          AND stock - stock_locked >= #{num}
    </update>
</mapper>
```

###  5.4 提交订单实现

在订单服务`gulimall-order`实现提交订单功能

`OrderWebController`：

```java
@Controller
public class OrderWebController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/submitOrder")
    public String submitOrder(OrderSubmitVo vo, Model model, RedirectAttributes redirectAttributes) {
        try {
            SubmitOrderResponseVo responseVo = orderService.submitOrder(vo);
            //下单失败回到订单确认页重新确认订单信息
            System.out.println("订单提交的数据..." + vo);
            if (responseVo.getCode() == 0) {
                //下单成功来到支付选择页
                model.addAttribute("submitOrderResp", responseVo);
                return "pay";
            } else {
                String msg = "下单失败；";
                switch (responseVo.getCode()) {
                    case 1:
                        msg += "订单信息过期，请刷新再次提交";
                        break;
                    case 2:
                        msg += "订单商品价格发生变化，请确认后再次提交";
                        break;
                    case 3:
                        msg += "库存锁定失败，商品库存不足";
                        break;
                }
                redirectAttributes.addFlashAttribute("msg", msg);
                return "redirect:http://order.gulimall.com/toTrade";
            }
        } catch (Exception e) {
            if (e instanceof NoStockException) {
                String message = ((NoStockException) e).getMessage();
                redirectAttributes.addFlashAttribute("msg", message);
            }
            return "redirect:http://order.gulimall.com/toTrade";
        }
    }
}
```

`OrderService`：

```java
public interface OrderService extends IService<OrderEntity> {
    /**
     * 下单
     * @param vo
     * @return
     */
    SubmitOrderResponseVo submitOrder(OrderSubmitVo vo);
}
```

`OrderServiceImpl`：

```java
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Autowired
    MemberFeignService memberFeignService;

    @Autowired
    CartFeignService cartFeignService;

    @Autowired
    WmsFeignService wmsFeignService;

    @Autowired
    ProductFeignService productFeignService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    ThreadPoolExecutor executor;

    // 为当前线程保存一个OrderSubmitVo
    private ThreadLocal<OrderSubmitVo> confirmVoThreadLocal = new ThreadLocal<>();

    @Override
    public SubmitOrderResponseVo submitOrder(OrderSubmitVo vo) {
        confirmVoThreadLocal.set(vo); // 将页面提交的请求数据放到ThreadLocal中以便获取
        //  下单流程： 验令牌，去创建订单，验价格，锁库存...
        SubmitOrderResponseVo response = new SubmitOrderResponseVo();
        MemberRespVo memberRespVo = LoginUserInterceptor.loginUser.get();
        response.setCode(0);
        // 1.验证令牌: 前端页面提交的令牌token 和 redis存储的令牌token进行对比\
        // 验证令牌的关键：令牌的对比和删除必须保证原子性
        //0令牌失败 - 1删除成功
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        String orderToken = vo.getOrderToken();  // 页面提交的令牌token
        // 原子验证令牌和删除令牌
        Long result = redisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class), Arrays.asList(OrderConstant.USER_ORDER_TOKEN_PREFIX + memberRespVo.getId()), orderToken);
        if (result == 0L) {
            //令牌验证失败
            response.setCode(1);
            return response;
        } else {
            //令牌验证成功
            //下单：去创建订单，验令牌，验价格，锁库存...
            //1、创建订单，订单项等信息
            OrderCreateTo order = createOrder();
            //2、验价
            BigDecimal payAmount = order.getOrder().getPayAmount();
            BigDecimal payPrice = vo.getPayPrice();
            if (Math.abs(payAmount.subtract(payPrice).doubleValue()) < 0.01) {
                //  金额对比
                //....
                //  保存订单
                try {
                    saveOrder(order);
                } catch (Exception e) {
                    log.error("异常{}:",e);
                }
                //4、库存锁定。只要有异常回滚订单数据。
                // 订单号，所有订单项（skuId，skuName，num）
                WareSkuLockVo lockVo = new WareSkuLockVo();
                lockVo.setOrderSn(order.getOrder().getOrderSn());
                List<OrderItemVo> locks = order.getOrderItems().stream().map(item -> {
                    OrderItemVo itemVo = new OrderItemVo();
                    itemVo.setSkuId(item.getSkuId());
                    itemVo.setCount(item.getSkuQuantity());
                    itemVo.setTitle(item.getSkuName());
                    return itemVo;
                }).collect(Collectors.toList());
                lockVo.setLocks(locks);
                //4、远程锁库存
                //库存成功了，但是网络原因超时了，订单回滚，库存不滚。

                //为了保证高并发。库存服务自己回滚。可以发消息给库存服务；
                //库存服务本身也可以使用自动解锁模式  消息
                R r = wmsFeignService.orderLockStock(lockVo);
                if (r.getCode() == 0) {
                    //锁成功了
                    response.setOrder(order.getOrder());

                    //  5.远程扣减积分 出异常
//                    int i = 10/0; //订单回滚，库存不滚


//                    //订单创建成功发送消息给MQ
//                    rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", order.getOrder());

                    // 6.清除购物车已经下单的商品
                    return response;
                } else {
                    //锁定失败
                    String msg = (String) r.get("msg");
                    throw new NoStockException(msg);
                }


            } else {
                response.setCode(2);
                return response;
            }

        }
    }


    /**
     * 创建订单并返回订单数据
     */
    private OrderCreateTo createOrder() {
        OrderCreateTo createTo = new OrderCreateTo();
        // 1.生成订单号
        String orderSn = IdWorker.getTimeId();
        // 2.创建订单信息
        OrderEntity orderEntity = buildOrder(orderSn);

        // 3.获取到所有的订单项
        List<OrderItemEntity> itemEntities = buildOrderItems(orderSn);

        // 4.订单验价： 计算价格、积分等相关
        computePrice(orderEntity, itemEntities);

        createTo.setOrder(orderEntity);
        createTo.setOrderItems(itemEntities);

        return createTo;
    }


    /**
     * 创建订单信息
     *
     * @param orderSn 订单号
     * @return
     */
    private OrderEntity buildOrder(String orderSn) {
        MemberRespVo respVo = LoginUserInterceptor.loginUser.get();
        OrderEntity entity = new OrderEntity();
        entity.setOrderSn(orderSn);
        entity.setMemberId(respVo.getId());

        OrderSubmitVo submitVo = confirmVoThreadLocal.get();
        //获取收货地址信息
        R fare = wmsFeignService.getFare(submitVo.getAddrId());
        FareVo fareResp = fare.getData(new TypeReference<FareVo>() {
        });

        //设置运费信息
        entity.setFreightAmount(fareResp.getFare());
        //设置收货人信息
        entity.setReceiverCity(fareResp.getAddress().getCity());
        entity.setReceiverDetailAddress(fareResp.getAddress().getDetailAddress());
        entity.setReceiverName(fareResp.getAddress().getName());
        entity.setReceiverPhone(fareResp.getAddress().getPhone());
        entity.setReceiverPostCode(fareResp.getAddress().getPostCode());
        entity.setReceiverProvince(fareResp.getAddress().getProvince());
        entity.setReceiverRegion(fareResp.getAddress().getRegion());

        //设置订单的相关状态信息
        entity.setStatus(OrderStatusEnum.CREATE_NEW.getCode());
        entity.setAutoConfirmDay(7);
        return entity;
    }

    /**
     * 构建所有订单项数据
     *
     * @return
     */
    private List<OrderItemEntity> buildOrderItems(String orderSn) {
        //最后确定每个购物项的价格
        List<OrderItemVo> currentUserCartItems = cartFeignService.getCurrentUserCartItems();
        if (currentUserCartItems != null && currentUserCartItems.size() > 0) {
            List<OrderItemEntity> itemEntities = currentUserCartItems.stream().map(cartItem -> {
                OrderItemEntity itemEntity = buildOrderItem(cartItem);
                itemEntity.setOrderSn(orderSn);
                return itemEntity;
            }).collect(Collectors.toList());
            return itemEntities;
        }

        return null;
    }

    /**
     * 构建某一个订单项
     *
     * @param cartItem
     * @return
     */
    private OrderItemEntity buildOrderItem(OrderItemVo cartItem) {
        OrderItemEntity itemEntity = new OrderItemEntity();
        //1、订单信息：订单号 v
        //2、商品的SPU信息  V
        Long skuId = cartItem.getSkuId();
        R r = productFeignService.getSpuInfoBySkuId(skuId);
        SpuInfoVo data = r.getData(new TypeReference<SpuInfoVo>() {
        });
        itemEntity.setSpuId(data.getId());
        itemEntity.setSpuBrand(data.getBrandId().toString());
        itemEntity.setSpuName(data.getSpuName());
        itemEntity.setCategoryId(data.getCatalogId());
        //3、商品的sku信息  v
        itemEntity.setSkuId(cartItem.getSkuId());
        itemEntity.setSkuName(cartItem.getTitle());
        itemEntity.setSkuPic(cartItem.getImage());
        itemEntity.setSkuPrice(cartItem.getPrice());
        String skuAttr = StringUtils.collectionToDelimitedString(cartItem.getSkuAttr(), ";");
        itemEntity.setSkuAttrsVals(skuAttr);
        itemEntity.setSkuQuantity(cartItem.getCount());
        //4、优惠信息[不做]
        //5、积分信息
        itemEntity.setGiftGrowth(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount().toString())).intValue());
        itemEntity.setGiftIntegration(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount().toString())).intValue());
        //6、订单项的价格信息
        itemEntity.setPromotionAmount(new BigDecimal("0"));
        itemEntity.setCouponAmount(new BigDecimal("0"));
        itemEntity.setIntegrationAmount(new BigDecimal("0"));
        //当前订单项的实际金额。 总额-各种优惠
        BigDecimal orign = itemEntity.getSkuPrice().multiply(new BigDecimal(itemEntity.getSkuQuantity().toString()));
        BigDecimal subtract = orign.subtract(itemEntity.getCouponAmount()).subtract(itemEntity.getPromotionAmount()).subtract(itemEntity.getIntegrationAmount());
        itemEntity.setRealAmount(subtract);

        return itemEntity;
    }


    /**
     * 订单验价： 计算价格、积分等相关
     */
    private void computePrice(OrderEntity orderEntity, List<OrderItemEntity> itemEntities) {
        BigDecimal total = new BigDecimal("0.0");

        BigDecimal coupon = new BigDecimal("0.0");
        BigDecimal integration = new BigDecimal("0.0");
        BigDecimal promotion = new BigDecimal("0.0");

        BigDecimal gift = new BigDecimal("0.0");
        BigDecimal growth = new BigDecimal("0.0");
        //订单的总额，叠加每一个订单项的总额信息
        for (OrderItemEntity entity : itemEntities) {
            coupon = coupon.add(entity.getCouponAmount());
            integration = integration.add(entity.getIntegrationAmount());
            promotion = promotion.add(entity.getPromotionAmount());
            total = total.add(entity.getRealAmount());
            gift = gift.add(new BigDecimal(entity.getGiftIntegration().toString()));
//            Integer growth = entity.getGiftGrowth();
            growth = growth.add(new BigDecimal(entity.getGiftGrowth().toString()));

        }
        //1、订单价格相关
        orderEntity.setTotalAmount(total);
        //应付总额
        orderEntity.setPayAmount(total.add(orderEntity.getFreightAmount()));
        orderEntity.setPromotionAmount(promotion);
        orderEntity.setIntegrationAmount(integration);
        orderEntity.setCouponAmount(coupon);

        //设置积分等信息
        orderEntity.setIntegration(gift.intValue());
        orderEntity.setGrowth(growth.intValue());
        orderEntity.setDeleteStatus(0);//未删除
    }

    /**
     * 保存订单数据
     *
     * @param order
     */
    private void saveOrder(OrderCreateTo order) {
        OrderEntity orderEntity = order.getOrder();
        orderEntity.setModifyTime(new Date());
        this.save(orderEntity);

        List<OrderItemEntity> orderItems = order.getOrderItems();
        orderItemService.saveBatch(orderItems);
    }
}
```



## 6.分布式事务



![image-20230903000542321](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012052131.png)

- 分布式事务下各个微服务出现异常，怎么确保数据一致性

  - 1.订单服务异常，库存锁定不运行，全部回滚，撤销操作

  - 2.库存服务事务自治，锁定失败全部回滚，订单感受到，继续回滚

  - 3.库存服务锁定成功了，但是网络原因返回数据途中问题?

  - 4.库存服务锁定成功了，库存服务下面的逻辑发生故障，订单回滚了，怎么处理?

- 当前保证事务的方法
  - `@Transactional` ： 本地事务，在分布式系统，只能控制住自己的回滚，控制不了其他服务的回滚
  - 每个服务保证自己的事务，每个服务只要发生异常就进行回滚。调用方通过服务提供方抛出的异常来继续回滚自己事务
  - 订单服务调用其他服务时通过其他服务抛出的异常感知调用是否成功，如果其他服务执行出错并且抛出异常，那我们就回滚这个服务下的数据库操作。订单服务感知到其他服务抛出的异常后继续抛出异常，就可以保证订单服务的数据库操作可以回滚

- 通过抛异常来感知调用状态并进行回滚的方法在分布式架构下存在很多问题
  - 1.远程服务假失败：远程服务其实成功了，由于网络故障等没有返回导致:订单回滚，库存却扣减
  - 2.远程服务执行完成，下面的其他方法出现问题。导致:已执行的远程请求，肯定不能回滚
  - 分布式事务： 最大原因。网络问题+分布式机器

- 分布式事务解决方案：

  - 利用消息队列实现最终一致

  - 库存服务锁定成功后发给消息队列消息(当前库存工作单)，过段时间自动解锁，解锁时先查询订单的支付状态。解锁成功修改库存工作单详情项状态为已解锁

  

###  6.1 本地事务与分布式事务

#####  6.1.1 事务的基本性质

数据库事务的几个特性: **原子性(Atomicity )**、**一致性( Consistency)**、**隔离性或独立性( lsolation)**和**持久性(Durabilily)**，简称就是ACID

- 原子性:一系列的操作整体不可拆分，要么同时成功，要么同时失败
- 一致性:数据在事务的前后，业务整体一致
  - 转账。A:1000;  B:1000;  转200   
  - 事务成功  A:800   B: 1200
- 隔离性:事务之间互相隔离
- 持久性:一旦事务成功，数据一定会落盘在数据库

在以往的单体应用中，我们多个业务操作使用同一条连接操作不同的数据表，一旦有异常，我们可以很容易的整体回滚



![image-20230903001845649](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012053719.png)



- Business：我们具体的业务代码

- Storage：库存业务代码;扣库存

- Order：订单业务代码;保存订单

- Account：账号业务代码;减账户余额

比如买东西业务，扣库存，下订单，账户扣款，是一个整体;必须同时成功或者失败。一个事务开始，代表以下的所有操作都在同一个连接里面

#####  6.1.2 事务的隔离级别

**READ UNCOMMITTED（读未提交)**

- 该隔离级别的事务会读到其它未提交事务的数据，此现象也称之为脏读

**READ COMMITTED（读提交）**

- 一个事务可以读取另一个已提交的事务，多次读取会造成不一样的结果，此现象称为不可重复读问题，Oracle和 SQL Server的默认隔离级别

**REPEATABLE READ（可重复读)**

- 该隔离级别是 MySQL 默认的隔离级别,在同一个事务里，select 的结果是事务开始时时间点的状态。因此，同样的 select 操作读到的结果会是一致的，但是，会有幻读现象。MySQL的 InnoDB引擎可以通过next-key locks 机制（参考下文"行锁的算法"一节）来避免幻读

**SERIALIZABLE（序列化）**

- 在该隔离级别下事务都是串行顺序执行的，MySQL 数据库的 InnoDB 引擎会给读操作隐式加一把读共享锁，从而避免了脏读、不可重读复读和幻读问题

`@Transactional`注解的`isolation`参数用来指定事务的隔离级别

```java
@Transactional(isolation = Isolation.REPEATABLE_READ)
```

#####  6.1.3 事务的传播行为

**1.PROPAGATION_REQUIRED**：如果当前没有事务，就创建一个新事务，如果当前存在事务，就加入该事务，该设置是最常用的设置

**2.PROPAGATION_SUPPORTS**：支持当前事务，如果当前存在事务，就加入该事务，如果当前不存在事务，就以非事务执行

**3.PROPAGATION_MANDATORY**：支持当前事务，如果当前存在事务，就加入该事务，如果当前不存在事务，就抛出异常

**4.PROPAGATION_REQUIRES_NEW**：创建新事务，无论当前存不存在事务，都创建新事务

**5.PROPAGATION_NOT_SUPPORTED**:以非事务方式执行操作，如果当前存在事务，就把当前事务挂起

**6.PROPAGATION_NEVER**：以非事务方式执行，如果当前存在事务，则抛出异常

**7.PROPAGATION_NESTED**：如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作



事务的传播行为演示一：方法a的传播行为是`propagation = Propagation.REQUIRED`，方法b的传播行为也是`propagation = Propagation.REQUIRED`，方法a调用方法b，方法b就和方法a同属于一个事务，a回滚的话b也会回滚。方法c的传播行为是`Propagation.REQUIRES_NEW`，那c的回滚与否只取决于c自身

```java
aService{
//    @Transactional // 默认 propagation = Propagation.REQUIRED
    @Transactional(propagation = Propagation.REQUIRED)
    public void a() {
        bService.b(); //a事务
        cService.c(); //新事务(不回滚)
        int i = 10 / 0;
    }    
}
```

```java
 bService{
   @Transactional(propagation = Propagation.REQUIRED)
    public void b() {
    }  
 }
```

```java
   cService{
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void c() {
    }   
}
```

#####  6.1.4 SpringBoot事务关键点

**1.事务的自动配置：`TransactionAutoConfiguration`**

**2.SpringBoot事务的坑**：

SpringBoot事务的坑：在同一个类里面，编写两个方法，内部调用的时候，会导致事务设置失效。原因是没有用到代理对象的缘故，如下代码所示：

```java
public class OrderServiceImpl implements OrderService {
    // 同一个对象内事务方法互调默认失效，原因 绕过了代理对象
    // 事务使用代理对象来控制的
    @Transactional(timeout = 30) 
    public void a() {
        //b，c做任何设置都没用。都是和a共用一个事务
        this.b(); 没用
        this.c(); 没用
        int i = 10 / 0;
    }

    @Transactional(propagation = Propagation.REQUIRED, timeout = 2)
    public void b() {
        //7s
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, timeout = 20)
    public void c() {

    }
}
```

**解决方案：使用代理对象来调用事务方法**

1.引入`spring-boot-starter-aop`依赖，此依赖引入了`aspectj`动态代理

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

2.`@EnableAspectJAutoProxy(exposeProxy = true)`开启 `aspectj `动态代理功能。以后所有的动态代理都是`aspectj`创建的，而不是默认的jdk来创建动态代理。`aspectj`创建动态代理的好处就是，即使没有接口也可以创建动态代理(jdk的动态代理必须有接口才行)。`exposeProxy = true`：对外暴露代理对象

```java
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
public class GulimallOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(GulimallOrderApplication.class, args);
    }
}
```

3.通过代理对象`AopContext.currentProxy()`实现本类互调用

- `OrderServiceImpl orderService = (OrderServiceImpl) AopContext.currentProxy();`
- `orderService.b();`
- `orderService.c();`

```java
public class OrderServiceImpl implements OrderService {
    // 同一个对象内事务方法互调默认失效，原因 绕过了代理对象,事务使用代理对象来控制的
    @Transactional(timeout = 30) 
    public void a() {
        //b，c做任何设置都没用。都是和a共用一个事务
        OrderServiceImpl orderService = (OrderServiceImpl) AopContext.currentProxy();
        orderService.b();
        orderService.c();
        int i = 10 / 0;
    }

    @Transactional(propagation = Propagation.REQUIRED, timeout = 2)
    public void b() {
        //7s
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, timeout = 20)
    public void c() {

    }
}
```

###  6.2 分布式事务

#####  6.2.1 为什么有分布式事务

分布式系统经常出现的异常：机器宕机、网络异常、消息丢失、消息乱序、数据错误、不可靠的TCP、存储数据丢失。分布式事务出现的原因就是分布式系统中各节点之间的状态不能同步或同步异常

![image-20230903022426089](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012053308.png)



分布式事务是企业集成中的一个技术难点,也是每一个分布式系统架构中都会涉及到的一个东西，特别是在微服务架构中，几乎可以说是无法避免

#####  6.2.2 CAP定理与BASE理论

######  6.2.2.1 CAP定理

CAP原则又称CAP定理，指的是在一个分布式系统中：

**一致性( Consistency):**

- 在分布式系统中的所有数据备份，在同一时刻是否同样的值。(等同于所有节点访问同一份最新的数据副本)

**可用性( Availability)**

- 在集群中一部分节点故障后，集群整体是否还能响应客户端的读写请求。(对数据更新具备高可用性)

**分区容错性( Partition tolerance)**

- 大多数分布式系统都分布在多个子网络。每个子网络就叫做一个区(partition)。分区容错的意思是，区间通信可能失败。比如，一台服务器放在中国，另一台服务器放在美国，这就是两个区，它们之间可能无法通信

CAP原则指的是，这三个要素最多只能同时实现两点，不可能三者兼顾



![image-20230903025258315](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012053943.png)



一般来说，分区容错无法避免，因此可以认为 CAP的总是成立。CAP定理告诉我们，剩下的C和A无法同时做到

分布式系统中实现一致性的raft算法演示：

- `http://thesecretlivesofdata.com/raft`
- `https://raft.github.io/`

######  6.2.2.2 CAP面临的问题

对于多数大型互联网应用的场景，主机众多、部署分散，而且现在的集群规模越来越大，所以节点故障、网络故障是常态，而且要保证服务可用性达到99.99999%(N个9），即保证P和A,舍弃C。

######  6.2.2.3 BASE理论

BASE理论是对CAP理论的延伸，思想是即使无法做到强一致性(CAP的一致性就是强一致性)，但可以采用适当的采取弱一致性，即最终一致性

**BASE是指**

`基本可用（Basically Available)`

- 基本可用是指分布式系统在出现故障的时候,允许损失部分可用性(例如响应时间、功能上的可用性)，允许损失部分可用性。需要注意的是，基本可用绝不等价于系统不可用
  - 响应时间上的损失:正常情况下搜索引擎需要在0.5秒之内返回给用户相应的查询结果，但由于出现故障(比如系统部分机房发生断电或断网故障)，查询结果的响应时间增加到了1~2秒
  - 功能上的损失:购物网站在购物高峰(如双十一)时，为了保护系统的稳定性，部分消费者可能会被引导到一个降级页面

`软状态（ Soft State)`

- 软状态是指允许系统存在中间状态，而该中间状态不会影响系统整体可用性。分布式存储中一般一份数据会有多个副本，允许不同副本同步的延时就是软状态的体现`mysql replication`的异步复制也是一种体现

`最终一致性（ Eventual Consistency)`

- 最终一致性是指系统中的所有数据副本经过一定时间后，最终能够达到一致的状
  态。弱一致性和强一致性相反，最终一致性是弱一致性的一种特殊情况

######  6.2.2.4 强一致性、弱一致性、最终一致性

从客户端角度,多进程并发访间时，更新过的数据在不同进程如何获取的不同策略，决定了不同的一致性。对于关系型数据库，要求更新过的数据能被后续的访问都能看到，这是强一致性。如果能容忍后续的部分或者全部访问不到，则是弱一致性。如果经过一段时间后要求能访问到更新后的数据，则是最终一致性

###  6.3 分布式事务几种方案

#####  6.3.1  2PC模式

数据库支持的2PC(2 phase commit二阶提交)，又叫做`XA Transactions`

MySQL从 5.5版本开始支持，SQL Server 2005开始支持，Oracle 7开始支持

其中，XA是一个两阶段提交协议，该协议分为以下两个阶段:

- 第一阶段:事务协调器要求每个涉及到事务的数据库预提交(precommit)此操作，并反映是否可以提交
- 第二阶段:事务协调器要求每个数据库提交数据。

其中，如果有任何一个数据库否决此次提交，那么所有数据库都会被要求回滚它们在此事务中的那部分信息



![image-20230903034356328](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012053004.png)



XA协议比较简单，而且一旦商业数据库实现了XA协议，使用分布式事务的成本也比较低

XA性能不理想，特别是在交易下单链路，往往并发量很高，XA无法满足高并发场景

XA目前在商业数据库支持的比较理想，在mysql数据库中支持的不太理想，mySQL的XA实现，没有记录prepare阶段日志，主备切换回导致主库与备库数据不一致

许多nosql也没有支持XA，这让XA的应用场景变得非常狭隘

也有3Pc，引入了超时机制（无论协调者还是参与者，在向对方发送请求后，若长时间未收到回应则做出相应处理)

#####  6.3.2 柔性事务-TCC事务补偿型方案

刚性事务:遵循ACID原则，强一致性

柔性事务:遵循BASE理论,最终一致性

与刚性事务不同，柔性事务允许一定时间内，不同节点的数据不一致，但要求最终一致



![image-20230903034911979](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012054038.png)



一阶段prepare行为：调用自定义的 prepare逻辑

二阶段commit行为：调用自定义的commit逻辑

二阶段rollback 行为：调用自定义的 rollback逻辑

所谓TCC 模式，是指支持把自定义的分支事务纳入到全局事务的管理中

#####  6.3.3 柔性事务-最大努力通知型方案

按规律进行通知，不保证数据一定能通知成功，但会提供可查询操作接口进行核对。这种方案主要用在与第三方系统通讯时，比如:调用微信或支付宝支付后的支付结果通知。这种方案也是结合MQ进行实现，例如:通过MQ发送http请求，设置最大通知次数。达到通知次数后即不再通知

案例：银行通知、商户通知等(各大交易业务平台间的商户通知:多次通知、查询校对、对账文件)，支付宝的支付成功异步回调



#####  6.3.4 柔性事务-可靠消息+最终一致性方案（异步确保型)

实现:业务处理服务在业务事务提交之前，向实时消息服务请求发送消息，实时消息服务只记录消息数据，而不是真正的发送。业务处理服务在业务事务提交之后，向实时消息服务确认发送。只有在得到确认发送指令后，实时消息服务才会真正发送



##  7.分布式事务组件-seata

###  7.1 seata简介

- seata官方文档：`https://seata.io/zh-cn/index.html`
- seata文档`快速开始`：`https://seata.io/zh-cn/docs/user/quickstart/`
- Seata是一款开源的分布式事务解决方案，致力于提供高性能和简单易用的分布式事务服务。Seata将为用提供了AT、TCC、SAGA 和XA事务模式，为用户打造一站式的分布式解决方案

###  7.2 seata 快速入门

#####  7.2.1 分布式事务场景 & seata解决方案

让我们从一个微服务示例开始

**用例**

```
用户购买商品的业务逻辑。整个业务逻辑由3个微服务提供支持:
    仓储服务：对给定的商品扣除仓储数量
    订单服务：根据采购需求创建订单
    帐户服务：从用户帐户中扣除余额
```

**架构图**

![image-20230903130637925](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012054080.png)

**仓库服务**

```java
public interface StorageService {
    /**
     * 扣除存储数量
     */
    void deduct(String commodityCode, int count);
}
```

**订单服务**

```java
public interface OrderService {
    /**
     * 创建订单
     */
    Order create(String userId, String commodityCode, int orderCount);
}
```

**帐户服务**

```java
public interface AccountService {
    /**
     * 从用户账户中借出
     */
    void debit(String userId, int money);
}
```

**主要业务逻辑**

```java
public class BusinessServiceImpl implements BusinessService {

    private StorageService storageService;

    private OrderService orderService;

    /**
     * 采购
     */
    public void purchase(String userId, String commodityCode, int orderCount) {

        storageService.deduct(commodityCode, orderCount);

        orderService.create(userId, commodityCode, orderCount);
    }
}
```

```java
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;

    private AccountService accountService;

    public Order create(String userId, String commodityCode, int orderCount) {

        int orderMoney = calculate(commodityCode, orderCount);

        accountService.debit(userId, orderMoney);

        Order order = new Order();
        order.userId = userId;
        order.commodityCode = commodityCode;
        order.count = orderCount;
        order.money = orderMoney;

        // INSERT INTO orders ...
        return orderDAO.insert(order);
    }
}
```



**SEATA的分布式交易解决方案**



![img](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012054971.png)





| Seata术语     | 描述                                                         |
| ------------- | ------------------------------------------------------------ |
| TC-事务协调者 | 维护全局和分支事务的状态，驱动全局事务提交或回滚             |
| TM-事务管理器 | 定义全局事务的范围:开始全局事务、提交或回滚全局事务          |
| RM-资源管理器 | 管理分支事务处理的资源，与TC交谈以注册分支事务和报告分支事务的状态，并驱动分支事务提交或回滚 |


我们只需要使用一个 `@GlobalTransactional` 注解在业务方法上：

```java
    @GlobalTransactional
    public void purchase(String userId, String commodityCode, int orderCount) {
        ......
    }
```

#####  7.2.2 由Dubbo + SEATA提供支持的示例

**步骤1：建立数据库**

- 要求：具有lnnoDB引擎的MySQL

- 注意：实际上，在示例用例中，这3个服务应该有3个数据库。但是，为了简单起见，我们只创建一个数据库并配置3个数据源

- 使用您刚创建的数据库URL/username/password 修改Spring XML
- `dubbo-account-service.xml`  、`dubbo-order-service.xml`  、`dubbo-storage-service.xml`：

```xml
        <property name="url" value="jdbc:mysql://x.x.x.x:3306/xxx" />
        <property name="username" value="xxx" />
        <property name="password" value="xxx" />
```

**步骤2:创建UNDO_LOG表**  ：`SEATA AT` 模式需要 `UNDO_LOG` 表

```sql
-- 注意此处0.3.0+ 增加唯一索引 ux_undo_log
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```

**步骤3:为示例业务创建表**

```java
DROP TABLE IF EXISTS `storage_tbl`;
CREATE TABLE `storage_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_code` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`commodity_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `order_tbl`;
CREATE TABLE `order_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `commodity_code` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT 0,
  `money` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `account_tbl`;
CREATE TABLE `account_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `money` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

**步骤4:启动服务**

- 从 https://github.com/seata/seata/releases,下载服务器软件包，将其解压缩

```sh
Usage: sh seata-server.sh(for linux and mac) or cmd seata-server.bat(for windows) [options]
  Options:
    --host, -h
      The address is expose to registration center and other service can access seata-server via this ip
      Default: 0.0.0.0
    --port, -p
      The port to listen.
      Default: 8091
    --storeMode, -m
      log store mode : file、db
      Default: file
    --help

e.g.

sh seata-server.sh -p 8091 -h 127.0.0.1 -m file
```

**步骤5：运行示例**

示例仓库: `https://github.com/seata/seata-samples`

启动`DubboAccountServiceStarter`

启动`DubboStorageServicestarter`

启动`DubboOrderServiceStarter`

运行`DubboBusinessTester for demo test`

###  7.3 订单服务整合seata(AT模式)解决分布式事务

**订单服务整合seata(AT模式)解决分布式事务**

1.各个微服务的数据库必须创建`undo_log`数据库表 

```sql
USE `gulimall_wms`;
#USE `gulimall_ums`;
#USE `gulimall_pms`;
#USE `gulimall_sms`;
#USE `gulimall_oms`;
DROP TABLE IF EXISTS `undo_log`;
-- 注意此处0.3.0+ 增加唯一索引 ux_undo_log
CREATE TABLE `undo_log` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `branch_id` BIGINT(20) NOT NULL,
  `xid` VARCHAR(100) NOT NULL,
  `context` VARCHAR(128) NOT NULL,
  `rollback_info` LONGBLOB NOT NULL,
  `log_status` INT(11) NOT NULL,
  `log_created` DATETIME NOT NULL,
  `log_modified` DATETIME NOT NULL,
  `ext` VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```

2.引入seata依赖：在公共包`gulimall-common`中添加`spring-cloud-starter-alibaba-seata`

```xml
      <!--  seata 分布式事务-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
        </dependency>
```

3.安装并配置事务协调器`seata-server`

- `seata-server`下载地址：`https://github.com/seata/seata/releases`
- 注册中心配置：在配置文件`registry.conf`中修改`registry type=nacos` 、指定nacos地址和端口  ` serverAddr = "localhost:8848"`

```
registry {
  # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
  type = "nacos"
  nacos {
    serverAddr = "localhost:8848"
    namespace = "public"
    cluster = "default"
  }
}
```

4.启动测试分布式事务：双击`seata-server.bat`启动事务协调器`seata-server`

5.所有想要用到分布式事务的微服务都要使用`seata DataSourceProxy`代理自己的数据源

- 因为 Seata 通过代理数据源实现分支事务，如果没有注入，事务无法成功回滚

- 要参与分布式事务的订单服务`gulimall-order`、仓库服务`gulimall-ware`都要添加代理数据源配置
- 参考文档：`https://github.com/seata/seata-samples/tree/master/springcloud-jpa-seata`

```java
import com.zaxxer.hikari.HikariDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import javax.sql.DataSource;
@Configuration
public class MySeataConfig {
    @Autowired
    DataSourceProperties dataSourceProperties;
    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties){
        //properties.initializeDataSourceBuilder().type(type).build();
        HikariDataSource dataSource = dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        if (StringUtils.hasText(dataSourceProperties.getName())) {
            dataSource.setPoolName(dataSourceProperties.getName());
        }
        return new DataSourceProxy(dataSource);
    }
}
```

6.每个微服务，都必须导入`registry.conf` 、`file.conf`

![image-20230903214808517](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012055970.png)



7.`file.conf`的`service.vgroup_mapping` 配置必须和`spring.application.name`—致

- 在 `org.springframework.cloud:spring-cloud-starter-alibaba-seata` 的`org.springframework.cloud.alibaba.seata.GlobalTransactionAutoConfiguration` 类中，默认会使用 `${spring.application.name}-fescar-service-group`作为服务名注册到 Seata Server上，如果和`file.conf` 中的配置不一致，会提示 `no available server to connect`错误

- 也可以通过配置 `spring.cloud.alibaba.seata.tx-service-group`修改后缀，但是必须和`file.conf`中的配置保持一致

![image-20230903221451360](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012055987.png)



![image-20230903221345487](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012055766.png)



8.给分布式大事务的入口标注`@GlobalTransactional`，每一个远程的小事务用`@Transactional`

```java
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {
    @Autowired
    MemberFeignService memberFeignService;
    @Autowired
    CartFeignService cartFeignService;
    @Autowired
    WmsFeignService wmsFeignService;
    @Autowired
    ProductFeignService productFeignService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    ThreadPoolExecutor executor;
    @Autowired
    RabbitTemplate rabbitTemplate;

    // 为当前线程保存一个OrderSubmitVo
    private ThreadLocal<OrderSubmitVo> confirmVoThreadLocal = new ThreadLocal<>();

    @GlobalTransactional
    @Transactional
    @Override
    public SubmitOrderResponseVo submitOrder(OrderSubmitVo vo) {
        confirmVoThreadLocal.set(vo); // 将页面提交的请求数据放到ThreadLocal中以便获取
        //  下单流程： 验令牌，去创建订单，验价格，锁库存...
        SubmitOrderResponseVo response = new SubmitOrderResponseVo();
        MemberRespVo memberRespVo = LoginUserInterceptor.loginUser.get();
        response.setCode(0);
        // 1.验证令牌: 前端页面提交的令牌token 和 redis存储的令牌token进行对比\
        // 验证令牌的关键：令牌的对比和删除必须保证原子性
        //0令牌失败 - 1删除成功
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        String orderToken = vo.getOrderToken();  // 页面提交的令牌token
        // 原子验证令牌和删除令牌
        Long result = redisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class), Arrays.asList(OrderConstant.USER_ORDER_TOKEN_PREFIX + memberRespVo.getId()), orderToken);
        if (result == 0L) {
            //令牌验证失败
            response.setCode(1);
            return response;
        } else {
            //令牌验证成功
            //下单：去创建订单，验令牌，验价格，锁库存...
            //1、创建订单，订单项等信息
            OrderCreateTo order = createOrder();
            //2、验价
            BigDecimal payAmount = order.getOrder().getPayAmount();
            BigDecimal payPrice = vo.getPayPrice();
            if (Math.abs(payAmount.subtract(payPrice).doubleValue()) < 0.01) {
                //  金额对比
                //....
                //  保存订单
                try {
                    saveOrder(order);
                } catch (Exception e) {
                    log.error("异常{}:", e);
                }
                //4、库存锁定。只要有异常回滚订单数据。
                // 订单号，所有订单项（skuId，skuName，num）
                WareSkuLockVo lockVo = new WareSkuLockVo();
                lockVo.setOrderSn(order.getOrder().getOrderSn());
                List<OrderItemVo> locks = order.getOrderItems().stream().map(item -> {
                    OrderItemVo itemVo = new OrderItemVo();
                    itemVo.setSkuId(item.getSkuId());
                    itemVo.setCount(item.getSkuQuantity());
                    itemVo.setTitle(item.getSkuName());
                    return itemVo;
                }).collect(Collectors.toList());
                lockVo.setLocks(locks);
                //4、远程锁库存
                //库存成功了，但是网络原因超时了，订单回滚，库存不滚。

                //为了保证高并发。库存服务自己回滚。可以发消息给库存服务；
                //库存服务本身也可以使用自动解锁模式  消息
                R r = wmsFeignService.orderLockStock(lockVo);
                if (r.getCode() == 0) {
                    //锁成功了
                    response.setOrder(order.getOrder());

                    // todo 5.远程扣减积分
//                    int i = 10/0; //此处出现异常，如果没有分布式事务的话，订单回滚，库存不滚


//                    //订单创建成功发送消息给MQ
//                    rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", order.getOrder());

                    // 6.清除购物车已经下单的商品
                    return response;
                } else {
                    //锁定失败
                    String msg = (String) r.get("msg");
                    throw new NoStockException(msg);
                }


            } else {
                response.setCode(2);
                return response;
            }

        }
    }


    /**
     * 创建订单并返回订单数据
     */
    private OrderCreateTo createOrder() {
        OrderCreateTo createTo = new OrderCreateTo();
        // 1.生成订单号
        String orderSn = IdWorker.getTimeId();
        // 2.创建订单信息
        OrderEntity orderEntity = buildOrder(orderSn);

        // 3.获取到所有的订单项
        List<OrderItemEntity> itemEntities = buildOrderItems(orderSn);

        // 4.订单验价： 计算价格、积分等相关
        computePrice(orderEntity, itemEntities);

        createTo.setOrder(orderEntity);
        createTo.setOrderItems(itemEntities);

        return createTo;
    }


    /**
     * 创建订单信息
     *
     * @param orderSn 订单号
     * @return
     */
    private OrderEntity buildOrder(String orderSn) {
        MemberRespVo respVo = LoginUserInterceptor.loginUser.get();
        OrderEntity entity = new OrderEntity();
        entity.setOrderSn(orderSn);
        entity.setMemberId(respVo.getId());

        OrderSubmitVo submitVo = confirmVoThreadLocal.get();
        //获取收货地址信息
        R fare = wmsFeignService.getFare(submitVo.getAddrId());
        FareVo fareResp = fare.getData(new TypeReference<FareVo>() {
        });

        //设置运费信息
        entity.setFreightAmount(fareResp.getFare());
        //设置收货人信息
        entity.setReceiverCity(fareResp.getAddress().getCity());
        entity.setReceiverDetailAddress(fareResp.getAddress().getDetailAddress());
        entity.setReceiverName(fareResp.getAddress().getName());
        entity.setReceiverPhone(fareResp.getAddress().getPhone());
        entity.setReceiverPostCode(fareResp.getAddress().getPostCode());
        entity.setReceiverProvince(fareResp.getAddress().getProvince());
        entity.setReceiverRegion(fareResp.getAddress().getRegion());

        //设置订单的相关状态信息
        entity.setStatus(OrderStatusEnum.CREATE_NEW.getCode());
        entity.setAutoConfirmDay(7);
        return entity;
    }

    /**
     * 构建所有订单项数据
     *
     * @return
     */
    private List<OrderItemEntity> buildOrderItems(String orderSn) {
        //最后确定每个购物项的价格
        List<OrderItemVo> currentUserCartItems = cartFeignService.getCurrentUserCartItems();
        if (currentUserCartItems != null && currentUserCartItems.size() > 0) {
            List<OrderItemEntity> itemEntities = currentUserCartItems.stream().map(cartItem -> {
                OrderItemEntity itemEntity = buildOrderItem(cartItem);
                itemEntity.setOrderSn(orderSn);
                return itemEntity;
            }).collect(Collectors.toList());
            return itemEntities;
        }

        return null;
    }

    /**
     * 构建某一个订单项
     *
     * @param cartItem
     * @return
     */
    private OrderItemEntity buildOrderItem(OrderItemVo cartItem) {
        OrderItemEntity itemEntity = new OrderItemEntity();
        //1、订单信息：订单号 v
        //2、商品的SPU信息  V
        Long skuId = cartItem.getSkuId();
        R r = productFeignService.getSpuInfoBySkuId(skuId);
        SpuInfoVo data = r.getData(new TypeReference<SpuInfoVo>() {
        });
        itemEntity.setSpuId(data.getId());
        itemEntity.setSpuBrand(data.getBrandId().toString());
        itemEntity.setSpuName(data.getSpuName());
        itemEntity.setCategoryId(data.getCatalogId());
        //3、商品的sku信息  v
        itemEntity.setSkuId(cartItem.getSkuId());
        itemEntity.setSkuName(cartItem.getTitle());
        itemEntity.setSkuPic(cartItem.getImage());
        itemEntity.setSkuPrice(cartItem.getPrice());
        String skuAttr = StringUtils.collectionToDelimitedString(cartItem.getSkuAttr(), ";");
        itemEntity.setSkuAttrsVals(skuAttr);
        itemEntity.setSkuQuantity(cartItem.getCount());
        //4、优惠信息[不做]
        //5、积分信息
        itemEntity.setGiftGrowth(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount().toString())).intValue());
        itemEntity.setGiftIntegration(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount().toString())).intValue());
        //6、订单项的价格信息
        itemEntity.setPromotionAmount(new BigDecimal("0"));
        itemEntity.setCouponAmount(new BigDecimal("0"));
        itemEntity.setIntegrationAmount(new BigDecimal("0"));
        //当前订单项的实际金额。 总额-各种优惠
        BigDecimal orign = itemEntity.getSkuPrice().multiply(new BigDecimal(itemEntity.getSkuQuantity().toString()));
        BigDecimal subtract = orign.subtract(itemEntity.getCouponAmount()).subtract(itemEntity.getPromotionAmount()).subtract(itemEntity.getIntegrationAmount());
        itemEntity.setRealAmount(subtract);

        return itemEntity;
    }


    /**
     * 订单验价： 计算价格、积分等相关
     */
    private void computePrice(OrderEntity orderEntity, List<OrderItemEntity> itemEntities) {
        BigDecimal total = new BigDecimal("0.0");

        BigDecimal coupon = new BigDecimal("0.0");
        BigDecimal integration = new BigDecimal("0.0");
        BigDecimal promotion = new BigDecimal("0.0");

        BigDecimal gift = new BigDecimal("0.0");
        BigDecimal growth = new BigDecimal("0.0");
        //订单的总额，叠加每一个订单项的总额信息
        for (OrderItemEntity entity : itemEntities) {
            coupon = coupon.add(entity.getCouponAmount());
            integration = integration.add(entity.getIntegrationAmount());
            promotion = promotion.add(entity.getPromotionAmount());
            total = total.add(entity.getRealAmount());
            gift = gift.add(new BigDecimal(entity.getGiftIntegration().toString()));
//            Integer growth = entity.getGiftGrowth();
            growth = growth.add(new BigDecimal(entity.getGiftGrowth().toString()));

        }
        //1、订单价格相关
        orderEntity.setTotalAmount(total);
        //应付总额
        orderEntity.setPayAmount(total.add(orderEntity.getFreightAmount()));
        orderEntity.setPromotionAmount(promotion);
        orderEntity.setIntegrationAmount(integration);
        orderEntity.setCouponAmount(coupon);

        //设置积分等信息
        orderEntity.setIntegration(gift.intValue());
        orderEntity.setGrowth(growth.intValue());
        orderEntity.setDeleteStatus(0);//未删除
    }

    /**
     * 保存订单数据
     *
     * @param order
     */
    private void saveOrder(OrderCreateTo order) {
        OrderEntity orderEntity = order.getOrder();
        orderEntity.setModifyTime(new Date());
        this.save(orderEntity);

        List<OrderItemEntity> orderItems = order.getOrderItems();
        if (orderItems != null && orderItems.size() > 0) {
            for (OrderItemEntity orderItem : orderItems) {
                orderItemService.save(orderItem);
            }
        }

//        List<OrderItemEntity> orderItems = order.getOrderItems();
//        orderItemService.saveBatch(orderItems);
    }
}
```

**总结**：整合`Seata`控制分布式事务

```
1、每一个微服务先必须创建 undo_log；
2、安装事务协调器；seata-server： https://github.com/seata/seata/releases
3、整合
      1、导入依赖 spring-cloud-starter-alibaba-seata  seata-all-0.7.1
      2、解压并启动seata-server；
          registry.conf: 注册中心配置； 修改registry type=nacos
          file.conf：
      3、所有想要用到分布式事务的微服务使用seata DataSourceProxy代理自己的数据源
      4、每个微服务，都必须导入
              registry.conf
              file.conf  vgroup_mapping.{application.name}-fescar-service-group = "default"
      5、启动测试分布式事务
      6、给分布式大事务的入口标注@GlobalTransactional
      7、每一个远程的小事务用 @Transactional
```



##  8.RabbitMQ延时队列

###  8.1 RabbitMQ延时队列简介 & 延时队列取消订单

**RabbitMQ延时队列应用：取消订单**

- 延时队列场景：比如未付款订单，超过一定时间后，系统自动取消订单并解锁库存，释放占有物品

可以通过定时任务来取消订单：

- 定时任务取消订单：spring的schedule定时任务轮询数据库

- 定时任务缺点：消耗系统内存、增加了数据库的压力、存在较大的时间误差

取消订单较好的方案：RabbitMQ延时队列取消订单

- 解决：`rabbitmq`的消息TTL和死信`Exchange`结合，结合消息的存活时间和死信路由解决订单关闭问题

![image-20230904012537291](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012056846.png)

**消息的TTL (Time To Live)**

- 消息的TTL就是**消息的存活时间**

- RabbitMQ可以对**队列**和**消息**分别设置TTL
  - 对队列设置TTL就是队列没有消费者连着的保留时间，也可以对每一个单独的消息做单独的设置。超过了这个时间，我们认为这个消息就死了，称之为死信
  - 如果队列设置了TTL，消息也设置了TTL，那么会取小的。所以一个消息如果被路由到不同的队列中，这个消息死亡的时间有可能不一样（不同的队列设置)。这里单讲单个消息的TTL，因为它才是实现延迟任务的关键。可以通过设置消息的`expiration`字段或者`x-message-ttl`属性来设置TTL时间，两者是—样的效果



**死信路由 Dead Letter Exchanges (DLX)**

- 一个消息在满足如下条件下，会进**死信路由**，记住这里是路由而不是队列，一个路由可以对应很多队列

- 什么是死信

  - 一个消息被Consumer拒收了，并且reject方法的参数里requeue是false。也就是说不会被再次放在队列里被其他消费者使用。(basic.reject/ basic.nack) requeue=false-上面的消息的TTL到了，消息过期了

  - 队列的长度限制满了。排在前面的消息会被丢弃或者扔到死信路由上

- `Dead Letter Exchange`其实就是一种普通的exchange，和创建其他exchange没有两样。只是在某一个设置`Dead Letter Exchange`的队列中有消息过期了，会自动触发消息的转发，发送到Dead Letter Exchange中去
- 我们**既可以控制消息在一段时间后变成死信，又可以控制变成死信的消息被路由到某一个指定的交换机，结合二者，其实就可以实现一个延时队列**



![image-20230904015041318](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012056870.png)



**延时队列实现-1**：设置队列过期时间实现延时队列(推荐)

消息的生产者P将路由键为`deal.message`的消息发送到交换机X，交换机X根据路由键`deal.message`把信息路由到队列`delay.5m.queue`。队列`delay.5m.queue`通过`x-message-ttl`设置存活时间为300000s(5分钟)、通过`x-dead-letter-exchange`设置死信交换机为`delay.exchange`(消息过期后会被转发到交换机`delay.exchange`)、通过`x-dead-letter-routing-key`设置路由键为`delay-message`(交换机通过路由键`delay-message`将消息转发出去)。最后将交换机`delay.exchange`和消息队列`test.queue`通过路由键`delay-message`进行绑定，队列`delay.5m.queue`中的消息超过过期时间之后就会被路由到队列`test.queue`中供消费者C进行消费



![image-20230904015111980](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012057530.png)

**延时队列实现-2**：设置消息过期时间实现延时队列

消费者P发送一个过期时间为300000s的信息到队列`delay.5m.queue`，队列中的消息过期之后将其路由到最终的`test.queue`队列中供消费者C消费

![image-20230904015514763](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012058277.png)

chatgpt补充：

在消息队列中，交换机（Exchange）和队列（Queue）之间通过绑定（Binding）来建立关联关系。绑定定义了消息从交换机发送到特定队列的规则

绑定有以下几个要素：

1. 交换机：消息发布者发送消息到交换机。交换机负责将消息路由到一个或多个队列。根据消息的路由键（Routing Key）和交换机的类型，交换机决定将消息发送到哪些队列。

2. 队列：消息被存储在队列中等待消费者获取。消费者从队列中获取消息并进行处理。

3. 路由键（Routing Key）：消息发布者在发送消息时，附带一个路由键。路由键是一个消息属性，用于交换机根据路由规则将消息发送到特定的队列。

通过绑定，将交换机和队列关联起来，以及定义消息的路由规则。绑定包含以下关键信息：

- 交换机名称：指定要绑定的交换机的名称。
- 队列名称：指定要绑定的队列的名称。
- 路由键（Routing Key）：指定与交换机路由规则匹配的路由键。

根据绑定的方式，可以建立不同类型的绑定关系：

1. 直接绑定（Direct Binding）：绑定的路由键与消息的路由键完全匹配时，消息将路由到绑定的队列。

2. 通配符绑定（Topic Binding）：通过通配符（`*`、`#`）匹配路由键，并根据匹配规则将消息路由到绑定的队列。

3. 扇形交换机绑定（Fanout Exchange Binding）：将所有发送到交换机的消息广播到所有绑定的队列中。

4. 头部绑定（Header Binding）：通过消息的头部属性进行匹配，根据匹配结果将消息路由到绑定的队列。

绑定的创建和配置可以通过消息队列中间件的管理界面、命令行工具或使用相关的客户端库进行操作



### 8.2 延时队列定时关单模拟

下订单以及库存的自动解锁功能：



延时队列关单模拟：可采用如下设计实现延时队列模拟关单操作(连线上的标注表示路由键名称）



![image-20230904020324967](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012059377.png)



消息队列设计及命名建议规范：(基于事件模型的交换机设计)

- 1.交换机命名：业务+exchange;交换机为Topic
- 2.路由键：事件.需要感知的业务(可以不写)
- 3.队列命名：事件+想要监听服务+queue
- 4.绑定关系：事件.感知的业务(#)
- 经过上述规范，我们关闭订单的消息队列设计如下(连线上的标注表示路由键名称)：

![image-20230904020910632](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012059968.png)



`MyMQConfig`：通过配置Bean的方式来创建RabbitMQ的绑定关系Binding、消息队列queue、交换机Exchange。配置完成后在spring容器中就生成这些对象，如果RabbitMQ服务器之前没有这些Binding、queue、Exchange的话，那这些Binding、queue、Exchange就会被创建。如果RabbitMQ之前有这些Binding、queue、Exchange，@Bean声明的属性发生了变化也不会覆盖之前的`Binding\queue\Exchange`的属性

```java
@Configuration
public class MyMQConfig {
    //@Bean Binding，Queue，Exchange
    /**
     * 容器中的 Binding，Queue，Exchange 都会自动创建（RabbitMQ没有的情况）
     * RabbitMQ 只要有。@Bean声明属性发生变化也不会覆盖
     * @return
     */
    @Bean
    public Queue orderDelayQueue() {
        Map<String, Object> arguments = new HashMap<>();
        /**
         * x-dead-letter-exchange: order-event-exchange
         * x-dead-letter-routing-key: order.release.order
         * x-message-ttl: 60000
         */
        arguments.put("x-dead-letter-exchange", "order-event-exchange");
        arguments.put("x-dead-letter-routing-key", "order.release.order");
        arguments.put("x-message-ttl", 60000);
        //String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        Queue queue = new Queue("order.delay.queue", true, false, false, arguments);
        return queue;
    }

    @Bean
    public Queue orderReleaseOrderQueue() {
        Queue queue = new Queue("order.release.order.queue", true, false, false);
        return queue;
    }

    @Bean
    public Exchange orderEventExchange() {
        //String name, boolean durable, boolean autoDelete, Map<String, Object> arguments
        return new TopicExchange("order-event-exchange", true, false);
    }

    @Bean
    public Binding orderCreateOrderBingding() {
        //String destination, DestinationType destinationType, String exchange, String routingKey,
        //			Map<String, Object> arguments
        return new Binding("order.delay.queue", Binding.DestinationType.QUEUE, "order-event-exchange", "order.create.order", null);

    }

    @Bean
    public Binding orderReleaseOrderBingding() {
        return new Binding("order.release.order.queue", Binding.DestinationType.QUEUE, "order-event-exchange", "order.release.order", null);
    }

}
```

`HelloController`：编写代码测试延时队列。延时队列逻辑：向交换机`order-event-exchange`发送路由键为`order.create.order`的消息，然后监听最终的队列`order.release.order.queue`，TTL时间以后从队列`order.release.order.queue`中就可以监听到我们的消息

```java
@Controller
public class HelloController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @ResponseBody
    @GetMapping("/test/createOrder")
    public String createOrderTest() {
        //订单下单成功
        OrderEntity entity = new OrderEntity();
        entity.setOrderSn(UUID.randomUUID().toString());
        entity.setModifyTime(new Date());

        //给MQ发送消息。
        rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", entity);
        return "ok";
    }

    @RabbitListener(queues = "order.release.order.queue")
    public void listener(OrderEntity entity, Channel channel, Message message) throws IOException {
        System.out.println("收到过期的订单消息，准备关闭订单");
        System.out.println("订单号： " + entity.getOrderSn());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
```

测试：访问`http://order.gulimall.com/test/createOrder`，`rabbitMQ`服务端出现一个消息，一分钟后订单服务`gulimall-order`监听到消息并消费掉，`rabbitMQ`服务端的消息被消费后就消失了

![image-20230907023219270](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012100029.png)



###  8.3  使用延时队列实现库存的自动解锁，实现最终一致性

利用`RabbitMq`实现最终一致性的分布式事务，最终一致性方案更适合高并发场景

库存的自动解锁：未付款订单，超过一定时间后，系统自动取消订单并解锁库存，释放占有物品

需要进行库存解锁的两种情况：

- 下订单成功，订单过期没有支付被系统自动取消、被用户手动取消
- 下订单成功，库存锁定成功，接下来的业务调用失败，导致订单回滚，之前锁定的库存就要自动解锁



交换机`stock-event-exchange`通过路由键`stock.locked`和延时队列`stock.delay.queue.50min`进行绑定，通过路由键`stock.realse`和消息队列`stock.release.stock.queue`进行绑定。库存锁定成功之后就生成路由键为`stock.locked`的消息A，消息A中包含的内容包括订单，仓库，商品数量，商品详情等数据，消息A首先会被发送给交换机`stock-event-exchange`，然后通过路由键`stock.locked`匹配后会进入延时队列`stock.delay.queue.50min`，超过50min之后通过路由键`stock.realse`路由到交换机`stock-event-exchange`，最终会被路由到消息队列`stock.release.stock.queue`。库存服务监听消息队列`stock.release.stock.queue`进行库存解锁。消息队列`stock.release.stock.queue`的消息来源于两个地方，一个是库存服务的订单延时队列，一个是订单服务的支付模块生成的消息(如果支付成功也需要对订单进行相应处理)



![image-20230907030851917](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012100220.png)



增强版锁库存逻辑

![image-20230904004517567](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012100286.png)



在库存服务`gulimall-ware`中加入`rabbitmq`依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

在库存服务`gulimall-ware`中加入`rabbitmq`配置：

```properties
spring.rabbitmq.host=192.168.56.10
spring.rabbitmq.virtual-host=/
spring.rabbitmq.listener.simple.acknowledge-mode=manual
```

在库存服务`gulimall-ware`中的启动类上添加`@EnableRabbit`注解：

```java
@EnableRabbit
@SpringBootApplication
public class gulimallWareApplication {
    public static void main(String[] args) {
        SpringApplication.run(gulimallWareApplication.class, args);
    }
}
```

`MyRabbitConfig`：在库存服务`gulimall-ware`中利用Bean方式创建交换机、消息队列、以及绑定关系

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

    @Bean
    public Exchange stockEventExchange() {
        //String name, boolean durable, boolean autoDelete, Map<String, Object> arguments
        return new TopicExchange("stock-event-exchange", true, false);
    }

    @Bean
    public Queue stockReleaseStockQueue() {
        //String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        return new Queue("stock.release.stock.queue", true, false, false);
    }

    @Bean
    public Queue stockDelayQueue() {
        /**
         * x-dead-letter-exchange: stock-event-exchange
         * x-dead-letter-routing-key: order.release.order
         * x-message-ttl: 60000
         */
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "stock-event-exchange");
        args.put("x-dead-letter-routing-key", "stock.release");
        args.put("x-message-ttl", 120000);
        return new Queue("stock.delay.queue", true, false, false, args);
    }

    @Bean
    public Binding stockReleaseBinding() {
        /**
         * String destination, DestinationType destinationType, String exchange, String routingKey,
         * 			Map<String, Object> arguments
         */
        return new Binding("stock.release.stock.queue", Binding.DestinationType.QUEUE, "stock-event-exchange", "stock.release.#", null);
    }

    @Bean
    public Binding stockLockedBinding() {
        /**
         * String destination, DestinationType destinationType, String exchange, String routingKey,
         * 			Map<String, Object> arguments
         */
        return new Binding("stock.delay.queue", Binding.DestinationType.QUEUE, "stock-event-exchange", "stock.locked", null);
    }
}
```

`StockReleaseListener`：在库存服务`gulimall-ware`中监听消息队列进行库存解锁

```java
@Service
@RabbitListener(queues = "stock.release.stock.queue")
public class StockReleaseListener {
    @Autowired
    WareSkuService wareSkuService;
    /**
     * 库存自动解锁
     * 1.下订单成功，库存锁定成功，接下来的业务调用失败，导致订单回滚。之前锁定的库存就要自动解锁
     * 2.订单失败：锁库存失败
     * 如果解锁库存的消息失败，一定要告诉服务解锁失败
     */
    @RabbitHandler
    public void handleStockLockedRelease(StockLockedTo to, Message message, Channel channel) throws IOException {
        System.out.println("收到解锁库存的消息");
        try {
            wareSkuService.unlockStock(to);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
```

`WareSkuService`：`unlockStock`方法解锁库存

```java
public interface WareSkuService extends IService<WareSkuEntity> {
    void unlockStock(StockLockedTo to);
}
```

`WareSkuServiceImpl`：解锁库存实现

```java
@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {
    @Autowired
    private WareSkuDao wareSkuDao;
    @Autowired
    private ProductFeignService productFeignService;
    @Autowired
    WareOrderTaskService orderTaskService;
    @Autowired
    WareOrderTaskDetailService orderTaskDetailService;
    @Autowired
    OrderFeignService orderFeignService;
    
    @Override
    public void unlockStock(StockLockedTo to) {
        StockDetailTo detail = to.getDetail();
        Long detailId = detail.getId();
        // 解锁
        // 1、查询数据库关于这个订单的锁定库存信息
        WareOrderTaskDetailEntity byId = orderTaskDetailService.getById(detailId);
        // 1.1 如果信息不存在，则代表之前锁定库存失败，库存发生了回滚，这种情况不需要进行库存解锁
        if (byId != null) {
            // 1.2 如果信息存在，证明库存锁定成功了
            // 1.2.1 继续判断订单情况，如果没有这个订单，就必须解锁库存
            // 1.2.1 如果有这个订单且订单的状态是已取消，那就需要解锁库存，如果订单没取消，就不能解锁库存
            Long id = to.getId();  // 库存工作单的id
            WareOrderTaskEntity taskEntity = orderTaskService.getById(id);
            String orderSn = taskEntity.getOrderSn();
            R r = orderFeignService.getOrderStatus(orderSn);
            if (r.getCode() == 0) {
                // 订单数据返回成功
                OrderVo data = r.getData(new TypeReference<OrderVo>() {
                });

                if (data == null || data.getStatus() == 4) {
                    // 订单不存在 或者 订单已经被取消了，都要解锁库存
                    if (byId.getLockStatus() == 1) {
                        // 当前库存工作单详情的状态是1(未解锁)，已锁定但是未解锁时才可以解锁
                        unLockStock(detail.getSkuId(), detail.getWareId(), detail.getSkuNum(), detailId);
                    }
                }
            } else {
                // 消息拒绝以后将消息重新放到队列里面让别人继续消费解锁
                throw new RuntimeException("远程服务失败");
            }
        } else {
            // 无需解锁
        }
    }

    private void unLockStock(Long skuId, Long wareId, Integer num, Long taskDetailId) {
        // 库存解锁
        wareSkuDao.unlockStock(skuId, wareId, num);
        // 更新库存工作单的状态
        WareOrderTaskDetailEntity entity = new WareOrderTaskDetailEntity();
        entity.setId(taskDetailId);
        entity.setLockStatus(2); // 将订单状态设置为已解锁
        orderTaskDetailService.updateById(entity);

    }
}
```

feign接口定义：在库存服务`gulimall-ware`远程调用订单服务`gulimall-order`获取订单状态

```java
@FeignClient("gulimall-order")
public interface OrderFeignService {
    @GetMapping("/order/order/status/{orderSn}")
    R getOrderStatus(@PathVariable("orderSn") String orderSn);
}
```

`OrderController`：在订单服务`gulimall-order`中编写获取订单状态的接口

```java
@RestController
@RequestMapping("order/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/status/{orderSn}")
    public R getOrderStatus(@PathVariable("orderSn") String orderSn){
        OrderEntity orderEntity = orderService.getOrderByOrderSn(orderSn);
        return R.ok().setData(orderEntity);
    }
}
```

`OrderService`：在订单服务`gulimall-order`中编写获取订单状态的接口

```java
public interface OrderService extends IService<OrderEntity> {
    OrderEntity getOrderByOrderSn(String orderSn);
}
```

`OrderServiceImpl`：在订单服务`gulimall-order`中实现订单状态的获取功能

```java
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {
    @Override
    public OrderEntity getOrderByOrderSn(String orderSn) {
        OrderEntity order_sn = this.getOne(new QueryWrapper<OrderEntity>().eq("order_sn", orderSn));
        return order_sn;
    }

}
```

订单服务`gulimall-order`中的拦截器` LoginUserInterceptor`要放行feign调用的接口，否则调用会出错

```java
@Component
public class LoginUserInterceptor implements HandlerInterceptor {
    public static ThreadLocal<MemberRespVo> loginUser = new ThreadLocal<>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // feign的请求不用登录也可以访问，所以排除这些接口
        //  /order/order/status/2948294820984028420
        String uri = request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean match = antPathMatcher.match("/order/order/status/**", uri);
        boolean match1 = antPathMatcher.match("/payed/notify", uri);
        if (match || match1) {
            return true;
        }
        MemberRespVo attribute = (MemberRespVo) request.getSession().getAttribute(AuthServerConstant.LOGIN_USER);
        if (attribute != null) {
            loginUser.set(attribute);
            return true;
        } else {
            //没登录就去登录
            request.getSession().setAttribute("msg", "请先进行登录");
            response.sendRedirect("http://auth.gulimall.com/login.html");
            return false;
        }
    }
}
```

`WareSkuDao`：解锁库存

```java
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
    void unlockStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("num") Integer num);
}
```

`WareSkuDao.xml`：解锁库存时恢复库存的商品数量

```xml
<mapper namespace="com.atguigu.gulimall.ware.dao.WareSkuDao">
    <resultMap type="com.atguigu.gulimall.ware.entity.WareSkuEntity" id="wareSkuMap">
        <result property="id" column="id"/>
        <result property="skuId" column="sku_id"/>
        <result property="wareId" column="ware_id"/>
        <result property="stock" column="stock"/>
        <result property="skuName" column="sku_name"/>
        <result property="stockLocked" column="stock_locked"/>
    </resultMap>
    <update id="unlockStock">
        UPDATE `wms_ware_sku`
        SET stock_locked=stock_locked - #{num}
        WHERE sku_id = #{skuId}
          AND ware_id = #{wareId}
    </update>
</mapper>
```

###  8.4 定时关单



**定时关单**：订单创建完成后往延时队列发送消息，如果超过时间TTL就将消息路由到队列`stock.release.stock.queue`，利用程序监听队列中的消息，根据监听到的订单消息进行订单的关闭。对于监听到的订单消息，只有订单状态是待付款才需要关单



**可能存在的问题**：  由于机器卡顿等原因可能导致订单解锁跑到库存解锁之后，那在库存解锁查询订单状态时就会发现订单处于新建状态，库存解锁的程序虽然消费了解锁库存的消息，但是订单被误判成新建状态导致没有正常解锁库存。之后订单服务对订单进行解锁改变了订单状态，最终会造成库存永远得不到释放

![image-20230909061007986](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012101066.png)

**解决方案**：除了订单创建完成后往延时队列发送消息触发库存服务超时自动解锁库存之外。当订单释放的时候也要主动地发送一份消息到`order-event-exchange`交换机，最终路由到`stock.release.stock.queue`，解锁库存的服务监听到这些消息的话也会进行库存解锁



![image-20230904005107454](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012101959.png)



`MyMQConfig`：在订单服务`gulimall-order`中利用Bean方式创建交换机、消息队列、以及绑定关系

```java
@Configuration
public class MyMQConfig {
    //@Bean Binding，Queue，Exchange
    /**
     * 容器中的 Binding，Queue，Exchange 都会自动创建（RabbitMQ没有的情况）
     * RabbitMQ 只要有。@Bean声明属性发生变化也不会覆盖
     *
     * @return
     */
    @Bean
    public Queue orderDelayQueue() {

        Map<String, Object> arguments = new HashMap<>();
        /**
         * x-dead-letter-exchange: order-event-exchange
         * x-dead-letter-routing-key: order.release.order
         * x-message-ttl: 60000
         */
        arguments.put("x-dead-letter-exchange", "order-event-exchange");
        arguments.put("x-dead-letter-routing-key", "order.release.order");
        arguments.put("x-message-ttl", 60000);
        //String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        Queue queue = new Queue("order.delay.queue", true, false, false, arguments);
        return queue;
    }

    @Bean
    public Queue orderReleaseOrderQueue() {
        Queue queue = new Queue("order.release.order.queue", true, false, false);
        return queue;
    }

    @Bean
    public Exchange orderEventExchange() {
        //String name, boolean durable, boolean autoDelete, Map<String, Object> arguments
        return new TopicExchange("order-event-exchange", true, false);
    }

    @Bean
    public Binding orderCreateOrderBingding() {
        //String destination, DestinationType destinationType, String exchange, String routingKey,
        //			Map<String, Object> arguments
        return new Binding("order.delay.queue", Binding.DestinationType.QUEUE, "order-event-exchange", "order.create.order", null);

    }

    @Bean
    public Binding orderReleaseOrderBingding() {
        return new Binding("order.release.order.queue", Binding.DestinationType.QUEUE, "order-event-exchange", "order.release.order", null);
    }


    /**
     * 订单释放直接和库存释放进行绑定
     * @return
     */
    @Bean
    public Binding orderReleaseOtherBingding() {
        return new Binding("stock.release.stock.queue", Binding.DestinationType.QUEUE, "order-event-exchange", "order.release.other.#", null);
    }


    @Bean
    public Queue orderSeckillOrderQueue() {
        //String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        return new Queue("order.seckill.order.queue", true, false, false);
    }

    @Bean
    public Binding orderSeckillOrderQueueBinding() {
        /**
         * String destination, DestinationType destinationType, String exchange, String routingKey,
         * 			Map<String, Object> arguments
         */
        return new Binding("order.seckill.order.queue", Binding.DestinationType.QUEUE, "order-event-exchange", "order.seckill.order", null);
    }

}
```

`OrderServiceImpl`：提交订单的时候向消息队列发送订单消息，订单消息在消息队列中的时间超过TTL就会被路由到`order.release.order.queue`中，`OrderCloseListener`类的`listener`方法监听到消息后就会将订单关闭

```java
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {
    @Autowired
    MemberFeignService memberFeignService;
    // 为当前线程保存一个OrderSubmitVo
    private ThreadLocal<OrderSubmitVo> confirmVoThreadLocal = new ThreadLocal<>();

    //    @GlobalTransactional
    @Transactional   // 本地事务，在分布式系统，只能控制住自己的回滚，控制不了其他服务的回滚
    @Override
    public SubmitOrderResponseVo submitOrder(OrderSubmitVo vo) {
        confirmVoThreadLocal.set(vo); // 将页面提交的请求数据放到ThreadLocal中以便获取
        //  下单流程： 验令牌，去创建订单，验价格，锁库存...
        SubmitOrderResponseVo response = new SubmitOrderResponseVo();
        MemberRespVo memberRespVo = LoginUserInterceptor.loginUser.get();
        response.setCode(0);
        // 1.验证令牌: 前端页面提交的令牌token 和 redis存储的令牌token进行对比\
        // 验证令牌的关键：令牌的对比和删除必须保证原子性
        //0令牌失败 - 1删除成功
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        String orderToken = vo.getOrderToken();  // 页面提交的令牌token
        // 原子验证令牌和删除令牌
        Long result = redisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class), Arrays.asList(OrderConstant.USER_ORDER_TOKEN_PREFIX + memberRespVo.getId()), orderToken);
        if (result == 0L) {
            //令牌验证失败
            response.setCode(1);
            return response;
        } else {
            //令牌验证成功
            //下单：去创建订单，验令牌，验价格，锁库存...
            //1、创建订单，订单项等信息
            OrderCreateTo order = createOrder();
            //2、验价
            BigDecimal payAmount = order.getOrder().getPayAmount();
            BigDecimal payPrice = vo.getPayPrice();
            if (Math.abs(payAmount.subtract(payPrice).doubleValue()) < 0.01) {
                //  金额对比
                //....
                //  保存订单
                try {
                    saveOrder(order);
                } catch (Exception e) {
                    log.error("异常{}:", e);
                }
                //4、库存锁定。只要有异常回滚订单数据。
                // 订单号，所有订单项（skuId，skuName，num）
                WareSkuLockVo lockVo = new WareSkuLockVo();
                lockVo.setOrderSn(order.getOrder().getOrderSn());
                List<OrderItemVo> locks = order.getOrderItems().stream().map(item -> {
                    OrderItemVo itemVo = new OrderItemVo();
                    itemVo.setSkuId(item.getSkuId());
                    itemVo.setCount(item.getSkuQuantity());
                    itemVo.setTitle(item.getSkuName());
                    return itemVo;
                }).collect(Collectors.toList());
                lockVo.setLocks(locks);
                //4、远程锁库存
                //库存成功了，但是网络原因超时了，订单回滚，库存不滚。
                //为了保证高并发。库存服务自己回滚。可以发消息给库存服务；
                //库存服务本身也可以使用自动解锁模式  消息
                R r = wmsFeignService.orderLockStock(lockVo);
                if (r.getCode() == 0) {
                    //锁成功了
                    response.setOrder(order.getOrder());
                    // todo 5.远程扣减积分
//                    int i = 10/0; //此处出现异常，如果没有分布式事务的话，订单回滚，库存不滚
//                    //订单创建成功发送消息给MQ
                    rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", order.getOrder());
                    // 6.清除购物车已经下单的商品
                    return response;
                } else {
                    //锁定失败
                    String msg = (String) r.get("msg");
                    throw new NoStockException(msg);
                }
            } else {
                response.setCode(2);
                return response;
            }
        }
    }
}
```

`OrderCloseListener`：监听消息队列`order.release.order.queue`，将过期的订单关闭

```java
@RabbitListener(queues = "order.release.order.queue")
@Service
public class OrderCloseListener {
    @Autowired
    OrderService orderService;
    @RabbitHandler
    public void listener(OrderEntity entity, Channel channel, Message message) throws IOException {
        System.out.println("收到过期的订单信息：准备关闭订单" + entity.getOrderSn() + "==>" + entity.getId());
        try {
            orderService.closeOrder(entity);
            //手动调用支付宝收单；
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 关闭订单失败的话拒绝消息，让消息重新回到消息队列，而不是被丢弃掉
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
```

`OrderServiceImpl`：订单关闭实现

订单关闭时要向消息队列发送订单关闭的消息，库存服务`gulimall-ware`监听这个队列中的消息，根据这个订单消息进行库存的解锁

```java
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void closeOrder(OrderEntity entity) {
        //查询当前这个订单的最新状态
        OrderEntity orderEntity = this.getById(entity.getId());
        // 如果订单状态是待付款才需要关单
        if (orderEntity.getStatus() == OrderStatusEnum.CREATE_NEW.getCode()) {
            //关单
            OrderEntity update = new OrderEntity();
            update.setId(entity.getId());
            update.setStatus(OrderStatusEnum.CANCLED.getCode());
            this.updateById(update);
            OrderTo orderTo = new OrderTo();
            BeanUtils.copyProperties(orderEntity, orderTo);
            //发给MQ一个
            try {
                //TODO 保证消息一定会发送出去，每一个消息都可以做好日志记录（给数据库保存每一个消息的详细信息）。
                //TODO 定期扫描数据库将失败的消息再发送一遍；
                rabbitTemplate.convertAndSend("order-event-exchange", "order.release.other", orderTo);
            } catch (Exception e) {
                //TODO 将没法送成功的消息进行重试发送。
//                while)
            }
        }
    }

}
```



在库存服务`gulimall-ware`中对库存进行解锁：`StockReleaseListener`：8.3中我们考虑到在`提交订单然后锁定库存`这个过程中可能出现异常，导致`订单回滚而库存无法回滚，库存被锁定`非法状态。为了保证`提交订单然后锁定库存`这个过程中的事务的一致性(一起成功或一起失败)，当订单服务中出现异常时就向消息队列发送解锁库存的消息，库存服务监听到解锁库存的消息时对库存进行解锁从而实现事务的一致性。此处还要再加一个解锁库存方法`handleOrderCloseRelease`，这个方法用来实现订单关闭之后的库存解锁

```java
@Service
@RabbitListener(queues = "stock.release.stock.queue")
public class StockReleaseListener {
    @Autowired
    WareSkuService wareSkuService;


    // 订单服务中订单提交异常时发送解锁库存的消息到消息队列，在此处对该消息进行监听，监听到该消息后对库进行解锁
    /**
     * 库存自动解锁
     * 1.下订单成功，库存锁定成功，接下来的业务调用失败，导致订单回滚。之前锁定的库存就要自动解锁
     * 2.订单失败：锁库存失败
     * 如果解锁库存的消息失败，一定要告诉服务解锁失败
     */
    @RabbitHandler
    public void handleStockLockedRelease(StockLockedTo to, Message message, Channel channel) throws IOException {
        System.out.println("收到解锁库存的消息");
        try {
            wareSkuService.unlockStock(to);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }


    /**
     * 监听订单关闭的消息，订单关闭之后也需要解锁库存
     */
    @RabbitHandler
    public void handleOrderCloseRelease(OrderTo orderTo, Message message, Channel channel) throws IOException {
        System.out.println("订单关闭准备解锁库存...");
        try {
            wareSkuService.unlockStock(orderTo);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }

    }
}
```

`WareSkuService`：订单关闭之后的库存解锁逻辑

```java
public interface WareSkuService extends IService<WareSkuEntity> {
    void unlockStock(OrderTo orderTo);
}
```

`WareSkuServiceImpl`：订单关闭之后的库存解锁逻辑

```java
@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    WareOrderTaskService orderTaskService;

    @Autowired
    WareOrderTaskDetailService orderTaskDetailService;

    //防止订单服务卡顿，导致订单状态消息一直改不了，库存消息优先到期。查订单状态新建状态，什么都不做就走了。
    //导致卡顿的订单，永远不能解锁库存
    @Transactional
    @Override
    public void unlockStock(OrderTo orderTo) {
        String orderSn = orderTo.getOrderSn();
        //查一下最新库存的状态，防止重复解锁库存
        WareOrderTaskEntity task = orderTaskService.getOrderTaskByOrderSn(orderSn);
        Long id = task.getId();
        //按照工作单找到所有 没有解锁的库存，进行解锁
        List<WareOrderTaskDetailEntity> entities = orderTaskDetailService.list(
                new QueryWrapper<WareOrderTaskDetailEntity>()
                        .eq("task_id", id)
                        .eq("lock_status", 1));

        //Long skuId, Long wareId, Integer num, Long taskDetailId
        for (WareOrderTaskDetailEntity entity : entities) {
            unLockStock(entity.getSkuId(),entity.getWareId(),entity.getSkuNum(),entity.getId());
        }

    }
    
        private void unLockStock(Long skuId, Long wareId, Integer num, Long taskDetailId) {
        // 库存解锁
        wareSkuDao.unlockStock(skuId, wareId, num);
        // 更新库存工作单的状态
        WareOrderTaskDetailEntity entity = new WareOrderTaskDetailEntity();
        entity.setId(taskDetailId);
        entity.setLockStatus(2); // 将订单状态设置为已解锁
        orderTaskDetailService.updateById(entity);

    }
}
```

`WareOrderTaskService`：`getOrderTaskByOrderSn`查询库存工作单

```java
public interface WareOrderTaskService extends IService<WareOrderTaskEntity> {
    WareOrderTaskEntity getOrderTaskByOrderSn(String orderSn);
}
```

`WareOrderTaskServiceImpl`：查询库存工作单

```java
@Service("wareOrderTaskService")
public class WareOrderTaskServiceImpl extends ServiceImpl<WareOrderTaskDao, WareOrderTaskEntity> implements WareOrderTaskService {
    @Override
    public WareOrderTaskEntity getOrderTaskByOrderSn(String orderSn) {
        //
        WareOrderTaskEntity one = this.getOne(new QueryWrapper<WareOrderTaskEntity>().eq("order_sn", orderSn));
        return one;
    }
}
```

###  8.5 消息丢失、积压、重复的解决方案

#####  8.5.1 消息丢失的场景及解决方法

场景一：消息发送出去，由于网络问题没有抵达服务器

- 做好容错方法(try-catch)，发送消息可能会网络失败，失败后要有重试机制，可记录到数据库，采用定期扫描重发的方式
- 做好日志记录，每个消息状态是否都被服务器收到都应该记录
- 做好定期重发，如果消息没有发送成功，定期去数据库扫描未成功的消息进行重发

```sql
#做好日志记录，每个消息状态是否都被服务器收到都应该记录
#利用数据库存储消息状态及消息发送情况，定期扫描数据库将失败的消息再发送一遍
CREATE TABLE `mq_message` (
`message_id` char(32) NOT NULL,
`content` text,
`to_exchane` varchar(255) DEFAULT NULL,
`routing_key` varchar(255) DEFAULT NULL,
`class_type` varchar(255) DEFAULT NULL,
`message_status` int(1) DEFAULT '0' COMMENT '0-新建 1-已发送 2-错误抵达 3-已抵达',
`create_time` datetime DEFAULT NULL,
`update_time` datetime DEFAULT NULL,
PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

场景二：消息抵达Broker，Broker要将消息写入磁盘(持久化)才算成功。此时Broker尚未持久化完成，宕机

- publisher也必须加入确认回调机制，确认成功的消息，修改数据库消息状态

场景三：自动ACK的状态下。消费者收到消息，但没来得及消费然后宕机

- 一定开启手动ACK，消费成功才移除，失败或者没来得及处理就noAck并重新入队

防止消息丢失：

​       做好消息确认机制（pulisher和consumer都需要进行确认【手动ack】）

​      每一个发送的消息都在数据库做好记录。定期将失败的消息再次发送一遍

#####  8.5.2 消息重复的场景及解决方法

场景一：消息消费成功，事务已经提交，`ack`时，机器宕机。导致没有`ack`成功，`Broker`的消息重新由`unack`变为`ready`，并发送给其他消费者



场景二：消息消费失败，由于重试机制，自动又将消息发送出去



场景三：成功消费，`ack`时宕机，消息由`unack`变为`ready`，`Broker`又重新发送

- 消费者的业务消费接口应该设计为幂等性的。比如扣库存有工作单的状态标志
- 使用防重表`(redis/mysql)`，发送消息每一个都有业务的唯一标识，处理过就不用处理
- `rabbitMQ`的每一个消息都有`redelivered`字段，可以获取是否是被重新投递过来的，而不是第一次投递过来的

#####  8.5.3 消息积压

 消息积压原因：

- 消费者宕机积压

- 消费者消费能力不足积压
- 发送者发送流量太大

解决方案：

- 上线更多的消费者，进行正常消费
- 上线专门的队列消费服务，将消息先批量取出来，记录数据库，离线慢慢处理

##  9.支付宝支付

###  9.1 支付宝支付开发

- 进入“蚂蚁金服开放平台”
  - `https://open.alipay.com/platform/home.htm`
- 下载支付宝官方demo，进行配置和测试，文档地址：
  - 支付宝&蚂蚁金服开发者平台：`https://open.alipay.com/platform/home.htm`     
  -  开发者文档：`https://docs.open.alipay.com/catalog`
  - 电脑网站支付文档，下载demo：`https://opendocs.alipay.com/open/270/106291/` 

- 配置使用沙箱进行测试
  - 1.使用 RSA工具生成签名
  - 2.下载沙箱版钱包
  - 3.运行官方demo进行测试

###  9.2 支付流程中的加密

##### 9.2.1 对称加密

- 对称加密：加密解密使用同一把钥匙
- `DES`、`3DES ( TripleDES)`、`AES`、`RC2`、`RC4`、`RC5`和`Blowfish`等
- 对称加密流程：发送方利用密钥将明文加密成一段密文，密文经过网络传输到达接收方后，接收方利用密钥就可以将密文解密
- 缺点：发送方或者接收方的密钥泄露，都会影响通信的安全性。如果黑客知道了发送方密钥，并对发送方发送的信息进行网络截取，那就可以利用这个密钥实现双向的通信，既可以解密发送方发来的消息，也可以伪造一些信息对发送方进行命令控制、信息篡改

![image-20230916035412228](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012102353.png)

#####  9.2.2 非对称加密

- 非对称加密：加密解密使用不同钥匙
- 非对称加密的代表：`RSA`加密算法、`Elgamal`加密算法等
- 非对称加密流程：发送方利用密钥A将密文加密成一段密文，密文经过网络传输之后到达接收方，接收方利用密钥B对密文进行解密得到明文。接收方向发送方发送消息的时候利用密钥C对密文进行加密，发送方接收到消息后利用密钥D对密文进行解密得到明文。如果黑客知道发送方的密钥A，即使他通过网络截取到了发送方发送的密文，他也无法将密文解密成明文。如果黑客知道了解密用的密钥B，那他就可以对发送方传过来的密文进行解密，但是只知道密钥B的话，黑客是无法伪造信息来和发送方进行“有效交流”的。除非黑客知道了整个过程中的4种密钥，否则是很难进行有效的请求和通信的。这就保证了通信的安全

![image-20230916041421007](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012102315.png)

#####  9.2.3 公钥、私钥、加密、签名和验签

**公钥私钥**

- 公钥和私钥是一个相对概念


- 它们的公私性是相对于生成者来说的


- 一对密钥生成后，保存在生成者手里的就是私钥，生成者发布出去大家用的就是公钥
- 以如下的非对称加密流程为例：发送方给接收方发送消息时将密钥A保存在自己手里，将密钥B发布出去给接收方使用，那对于发送方来说密钥A是私钥，密钥B是公钥。接收方给发送方发送消息时将密钥C保存在自己手里，将密钥D公布出去给发送方使用，那对于接收方来说密钥C就是私钥，密钥D就是公钥



![image-20230916041421007](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012102013.png)



**加密**

- 加密是指我们使用—对公私钥中的一个密钥来对数据进行加密，而使用另一个密钥来进行解密的技术

- 公钥和私钥都可以用来加密，也都可以用来解密

- 但这个加解密必须是—对密钥之间的互相加解密，否则不能成功
- 加密的目的是：为了确保数据传输过程中的不可读性，就是不想让别人看到

**签名**

- 给我们将要发送的数据，做上—个唯—签名(类似于指纹)

- 用来互相验证接收方和发送方的身份

- 在验证身份的基础上再验证—下传递的数据是否被篡改过。因此使用数字签名可以用来达到数据的明文传输

**验签**

- 支付宝为了验证请求的数据是否商户本人发的
- 商户为了验证响应的数据是否支付宝发的



#####  9.2.4 支付宝加密流程

在使用支付宝的过程中有4把钥匙：商户私钥、商户公钥、支付宝公钥、支付宝私钥。商户(或者支付宝)通过RSA算法生成私钥和公钥，接着商户\支付宝将公钥告诉支付宝\商户（RSA算法生成密钥时都是生成一对）。



支付过程：商户生成私钥和公钥，自己保留私钥，给支付宝一份公钥。商户想要支付一份订单号为1234金额为100的订单。我们可以将这个订单消息进行加密后再传输给支付宝，让支付宝进行解密，但是如果支付消息被截取伪造的话就不安全。在这里采取签名和验签的方法，商户传送订单信息的时候利用私钥作用于订单信息生成一串签名，然后将订单消息和签名一并发送给支付宝，支付宝接收到支付信息后利用商户公钥验证明文和签名是不是吻合，如果传输过程中支付信息被篡改的话，那明文和签名就匹配不上，支付就会失败。如果支付宝验证支付信息是正确的话就会向商户传输支付成功的消息，传输时同样会对信息进行签名和校验，这样就保证了支付的安全性

![image-20230920075212363](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012103804.png)

#####  9.2.5 内网穿透

正常情况下别人的电脑无法访问我们的电脑：

![image-20230923202431676](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012103828.png)

内网穿透功能可以允许我们使用外网的网址来访问主机

正常的外网需要访问我们项目的流程是：

- 1.买服务器并且有公网固定IP

- 2.买域名映射到服务器的IP

- 3.域名需要进行备案和审核

2.内网穿透使用场景

- 1.开发测试（微信、支付宝)

- 2.智慧互联
- 3.远程控制

3.内网穿透的几个常用软件

- 1.natapp: https://natapp.cn/
- 2.续断:www.zhexi.tech
- 3.花生壳:https://www.oray.com/

内网穿透：



![image-20230923202500464](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012103431.png)

在支付开发时，利用内网穿透来进行开发测试：

![image-20230923202801613](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012104566.png)





###  9.3 整合支付宝

#####  9.3.1 支付宝信息配置

支付包开发文档：`https://opendocs.alipay.com/common/02khjo`

下载支付宝开放平台密钥工具。支付宝开放平台密钥工具可以生成应用公钥、应用私钥、CSR文件，并提供密钥匹配、格式转换、签名、同步验签、异步验签等功能。利用密钥生成工具生成的私钥配置到本地，公钥上传到支付宝。同时生成支付宝公钥并配置到本地

`return_url`：

- 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问

- 同步通知，支付成功，一般跳转到成功页



#####  9.3.2 订单服务`gulimall-order`实现订单支付

在订单服务中添加支付宝依赖：

```xml
<!-- https://mvnrepository.com/artifact/com.alipay.sdk/alipay-sdk-java -->
<!--        导入支付宝的SDK-->
<dependency>
    <groupId>com.alipay.sdk</groupId>
    <artifactId>alipay-sdk-java</artifactId>
    <version>4.9.28.ALL</version>
</dependency>
```

`AlipayTemplate`：配置支付相关信息

```java
package com.atguigu.gulimall.order.config;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.atguigu.gulimall.order.vo.PayVo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AlipayTemplate {

    //在支付宝创建的应用的id
    private   String app_id = "2016092200568607";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private  String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDD8TcACeOuLpXaG3EupnxffW+TjJqYVWVSEmqEaTCwNLa3i1o4qXYShcq0uzU0AxjOAgub5kzlVb+9F213/Qg0VCJp5alT5mIf2EI7X2ChJndoC+pEC3Lo+/3Uos4Cd6LpoYbQPQyJFWPX5spzBK4qP/+5r8vEkdFurl6YDlIallcxpQ2DbQLNyb2yjs3kryvGYjGuYtd09ah/ZoFQis/ha7d4K/E9twgPQaxBHB55DqZUkPJk+03rwKW2HZZDe4+6kYtEygpCC6JlnnbRMGcjXWbF0XCX9AkbG4oS37FahMiG5jrsX79kbs3RJ138QvTnYqmWil3Mry6bFoMSQiYdAgMBAAECggEASckA5CPVOBdU2MAyu9V3ymGq7Y4p/SlTMTe8IsaVzhTbpXx1ahbuXoVBUxOYMlDkO1dr1bZAyK+kt2kmEOQY/dOc7fHM9Moxszo1nrQI6EZa+rRZ8Gu7Rt71s+fm2ekBUSdBZBNJMCn84iWHoQ5nI4PcIamAezJKAnrpJitmYZoHx3mMIZHL7UPpev/gIZmWgaONe2jIdLzjwTJIo9rdvwqCpC8DXwuPd7Y6MF3z71HZIRoU0xkF2+QtX4nd6xxU6qPIEvOSW4ExcrwraV72bHDUdoR+dYq6tnvV2z5rQ14A/dy2jhkVDSRIek47u5Syr/ykWR/BmM/P+V42V0pwgQKBgQD7YtylMjbA3AQLrfwXFqbcWOomE/hPObK56gxaxPonq/mJ813L0xf0JtrPn2Z9qTNtv78ukC8sx7T3BvyfC41QgS48zzejhQ8mzuX6gueqJ4nceh17YZB3Thetw7+exTVnbIgZ0wkbzVPETr82+H3hP+8Fwh9DtgocDB/SFglNfQKBgQDHidl/CIcZ4ex2b2clM6ayioYMQdDixg/BgtCajrlAYmvnwyGHzjxhxCPqDw1EpK9zrYwBihyq2IdGtMeYjY8KoeP9KC5hOrF5hnWeUwKrYE8OCBnv3AlXQ0DfRmYI1axHI+oB6KPbAwurA45bQYxP17b6r9oOdEeE1olnBOgdIQKBgGCKBtIqBnzqAWO63STAvjPAsasN4D4MmmZrS3NQUgEKOjqQC2VLG9fXVBCH1oRLhkSPbowjMvqvdv+m3zz04sAX2/neWi+h2b+9lPQvcWgW5wqUmt3XCfPHObCxi9UdRvUA6QOB1+2lXJX259fkdfMa6l/7vKX0Ms2ymkXD0K7BAoGBAMPpisx5OPymn56hPqp+DhuaeoaqUEd3o5yhreMyYpzehYemRNOsIAj1pTV31dUhxMpVCPOyAPeVZSx87sPPqTQNQ2YZ/3+jOwFQjUcHxcUx6Cyks6KXbexpsdYGrsPZ1y56CpYZLlgoo1ojQbBDqVnzOErmQbiTXm0Enx+w551BAoGAHg1FfHnEsibV8b6IY1j0FgZxWhaj9vFeJhloo9xW7KOBm5tEsV3SsBUHIibHYl3djwWeeThULKpwZV2ONgonN8hXloeltayfA1L39aZbFbCP+Iq12PcTG2KL1z+gFYjqBxk+HFVjJBzX9qJMUdoZ4iMpKQjYxJbAWqfBWym69Ho=";;
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private  String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyQQceVUChTJGtF/a8SXufhSxDTKporieTq9NO7yDZSpDlAX1zVPT/nf0KWAlxq1TYappWMIYtyrOABhJyn6flNP6vuSBiM5lYsepHvYrtRHqlFiJruEkiaCgEZBKL5aCfBHYj0oqgQn9MpNV/PEH4cBYAVaiI4+VX8CBUQfeEGjgN6OkpLULZ3X0JUkmSnVvCNJ1m3PD68IIlbOfEZXJUKCqmZhzprGR5VWswjxA+g87cMwvijL4gdkSy/daG62Bz5vApcmmMkuX1k1fMWP4ajZCASVw8HD+MSLRhd8We9F97gd8CW0TavzbdR+mTS5H4yEgO8F9HRAsbkhV9yu0yQIDAQAB";
    // 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    private  String notify_url = "http://497n86m7k7.52http.net/payed/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    private  String return_url = "http://member.gulimall.com/memberOrder.html";

    // 签名方式
    private  String sign_type = "RSA2";

    // 字符编码格式
    private  String charset = "utf-8";

    private String timeout = "30m";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    private  String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public  String pay(PayVo vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+timeout+"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        System.out.println("支付宝的响应："+result);

        return result;

    }
}
```

`PayVo`：封装支付信息

```java
@Data
public class PayVo {
    private String out_trade_no; // 商户订单号 必填
    private String subject; // 订单名称 必填
    private String total_amount;  // 付款金额 必填
    private String body; // 商品描述 可空
}
```

`pay.html`：支付页面显示订单信息

```html
  <li>
    <img src="/static/order/pay/img/zhifubao.png" style="weight:auto;height:30px;" alt="">
    <a th:href="'http://order.gulimall.com/payOrder?orderSn='+${submitOrderResp.order.orderSn}">支付宝</a>
  </li>
```

`PayWebController`：获取订单信息，调用支付宝支付模板`alipayTemplate`进行支付

```java
@Controller
public class PayWebController {
    @Autowired
    AlipayTemplate alipayTemplate;
    @Autowired
    OrderService orderService;
    /**
     * 1、将支付页让浏览器展示。
     * 2、支付成功以后，我们要跳到用户的订单列表页
     * @param orderSn
     * @return
     * @throws AlipayApiException
     */
    @ResponseBody
    @GetMapping(value = "/payOrder",produces = "text/html")
    public String payOrder(@RequestParam("orderSn") String orderSn) throws AlipayApiException {

//        PayVo payVo = new PayVo();
//        payVo.setBody();//订单的备注
//        payVo.setOut_trade_no();//订单号
//        payVo.setSubject();//订单的主题
//        payVo.setTotal_amount();
        PayVo payVo = orderService.getOrderPay(orderSn);
        //返回的是一个页面。将此页面直接交给浏览器就行
        String pay = alipayTemplate.pay(payVo);
        System.out.println(pay);
        return pay;
    }
}
```

`OrderService`：获取当前订单的支付信息

```java
public interface OrderService extends IService<OrderEntity> {
    /**
     * 获取当前订单的支付信息
     * @param orderSn
     * @return
     */
    PayVo getOrderPay(String orderSn);
}
```

`OrderServiceImpl`：获取当前订单的支付信息

```java
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {
    @Autowired
    OrderItemService orderItemService;
    @Override
    public PayVo getOrderPay(String orderSn) {
        PayVo payVo = new PayVo();
        OrderEntity order = this.getOrderByOrderSn(orderSn);
        BigDecimal bigDecimal = order.getPayAmount().setScale(2, BigDecimal.ROUND_UP);
        payVo.setTotal_amount(bigDecimal.toString());
        payVo.setOut_trade_no(order.getOrderSn());

        List<OrderItemEntity> order_sn = orderItemService.list(new QueryWrapper<OrderItemEntity>().eq("order_sn", orderSn));
         OrderItemEntity entity = order_sn.get(0);

        payVo.setSubject(entity.getSkuName());
        payVo.setBody(entity.getSkuAttrsVals());
        return payVo;
    }
}
```

#####  9.3.3 支付成功后的跳转会员服务`gulimall-member`的订单列表页

会员服务导入`thymeleaf`依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

将会员服务页面所需的前端页面上传到linux服务器

```sh
# 在本地windows系统上传订单也静态文件到linux服务器
scp -r D:\learn\大型电商--谷粒商城\2.分布式高级篇（微服务架构篇）\代码\html\订单页  root@192.168.56.10:/mydata/nginx/html/static
# 在linux服务器修改文件名
cd /mydata/nginx/html/static
mv 订单页 member
cd member/
rm -rf index.html
```

将会员服务的订单页面拷贝到项目中，修改链接地址

![image-20230924195112848](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012104826.png)



在会员服务`gulimall-member`中添加登录拦截功能，只有登录的用户才能够访问会员服务的各种链接

`LoginUserInterceptor`：登录拦截器

```java
@Component
public class LoginUserInterceptor implements HandlerInterceptor {
    public static ThreadLocal<MemberRespVo> loginUser = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // /member/memberreceiveaddress/info/{id}
        //  /order/order/status/2948294820984028420
        String uri = request.getRequestURI();
        boolean match = new AntPathMatcher().match("/member/**", uri);
        if (match) {
            return true;
        }

        MemberRespVo attribute = (MemberRespVo) request.getSession().getAttribute(AuthServerConstant.LOGIN_USER);
        if (attribute != null) {
            loginUser.set(attribute);
            return true;
        } else {
            //没登录就去登录
            request.getSession().setAttribute("msg", "请先进行登录");
            response.sendRedirect("http://auth.gulimall.com/login.html");
            return false;
        }
    }
}
```

`MemberWebConfig`：使登录拦截生效

```java
@Configuration
public class MemberWebConfig implements WebMvcConfigurer {
    @Autowired
    LoginUserInterceptor loginUserInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginUserInterceptor).addPathPatterns("/**");
    }
}
```

在网关服务`gulimall-gateway`的配置中添加会员服务的请求路由：

```yaml
spring:
  cloud:
    gateway:
      routes:
# 转发到会员服务
        - id: gulimall_member_route
          uri: lb://gulimall-member
          predicates:
            - Host=member.gulimall.com
```

打开`switchHost`，添加会员服务的域名映射

```sh
# gulimall
192.168.56.10 gulimall.com
192.168.56.10 search.gulimall.com
192.168.56.10 item.gulimall.com
192.168.56.10 auth.gulimall.com
192.168.56.10 cart.gulimall.com
192.168.56.10 order.gulimall.com
192.168.56.10 member.gulimall.com

127.0.0.1 ssoserver.com
127.0.0.1 client1.com
127.0.0.1 client2.com
```

引入`SpringSession`相关依赖：

```xml
<!-- 引入redis -->
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

<!--整合SpringSession完成session共享问题-->
<dependency>
    <groupId>org.springframework.session</groupId>
    <artifactId>spring-session-data-redis</artifactId>
</dependency>
```

`application.properties`：配置文件添加`springsession`相关配置

```properties
spring.session.store-type=redis
spring.redis.host=192.168.56.10
```

添加`@EnableRedisHttpSession`注解开启`springsession`功能

```java
@EnableRedisHttpSession
@SpringBootApplication
public class gulimallMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(gulimallMemberApplication.class, args);
    }
}
```

`GulimallSessionConfig`：添加`springsession`的相关配置

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
    // 自定义redis保存session时的序列化器
    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }
}
```

#####  9.3.4 渲染订单列表页

**查询订单列表数据、渲染会员服务`gulimall-member`的订单列表页**

订单服务通过接口`/order/order/listWithItem`，分页查询当前登录用户的所有订单

`OrderController`：

```java
@RestController
@RequestMapping("order/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    /**
     * 分页查询当前登录用户的所有订单
     * @param params
     * @return
     */
    @PostMapping("/listWithItem")
    public R listWithItem(@RequestBody Map<String, Object> params){
        PageUtils page = orderService.queryPageWithItem(params);

        return R.ok().put("page", page);
    }
}
```

`OrderService`：

```java
public interface OrderService extends IService<OrderEntity> {
    PageUtils queryPageWithItem(Map<String, Object> params);
}
```

`OrderServiceImpl`：

```java
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {
    @Autowired
    OrderItemService orderItemService;

    @Override
    public PageUtils queryPageWithItem(Map<String, Object> params) {
        MemberRespVo memberRespVo = LoginUserInterceptor.loginUser.get();

        IPage<OrderEntity> page = this.page(new Query<OrderEntity>().getPage(params), new QueryWrapper<OrderEntity>().eq("member_id", memberRespVo.getId()).orderByDesc("id"));

        List<OrderEntity> order_sn = page.getRecords().stream().map(order -> {
            List<OrderItemEntity> itemEntities = orderItemService.list(new QueryWrapper<OrderItemEntity>().eq("order_sn", order.getOrderSn()));
            order.setItemEntities(itemEntities);
            return order;
        }).collect(Collectors.toList());

        page.setRecords(order_sn);

        return new PageUtils(page);
    }
}
```

会员服务`gulimall-member`调用订单服务获取当前登录用户的所有订单

```java
@FeignClient("gulimall-order")
public interface OrderFeignService {
    @PostMapping("/order/order/listWithItem")
    public R listWithItem(@RequestBody Map<String, Object> params);
}
```

`MemberWebController`：

```java
@Controller
public class MemberWebController {
    @Autowired
    OrderFeignService orderFeignService;
    /**
     * 订单分页查询
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("/memberOrder.html")
    public String memberOrderPage(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                  Model model, HttpServletRequest request){
        //获取到支付宝给我们传来的所有请求数据；
//        request。验证签名，如果正确可以去修改。
        //查出当前登录的用户的所有订单列表数据
        Map<String,Object> page =new HashMap<>();
        page.put("page",pageNum.toString());
        //
        R r = orderFeignService.listWithItem(page);
        System.out.println(JSON.toJSONString(r));
        model.addAttribute("orders",r);
        return "orderList";
    }
}
```

`GuliFeignConfig`：解决 feign在远程调用时请求头丢失问题

```java
// feign在远程调用之前都经过拦截器的apply方法
@Configuration
public class GuliFeignConfig {

    @Bean("requestInterceptor")
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                //1、使用RequestContextHolder拿到刚进来的这个请求
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes != null) {
                    System.out.println("RequestInterceptor线程...." + Thread.currentThread().getId());
                    HttpServletRequest request = attributes.getRequest(); //老请求
                    if (request != null) {
                        //同步请求头数据，Cookie
                        String cookie = request.getHeader("Cookie");
                        //给新请求同步了老请求的cookie
                        template.header("Cookie", cookie);
                    }
                }
            }
        };
    }
}
```

`orderList.html`：订单列表页进行渲染

```html
<table class="table" th:each="order:${orders.page.list}">
  <tr>
    <td colspan="7" style="background:#F7F7F7" >
      <span style="color:#AAAAAA">2017-12-09 20:50:10</span>
      <span><ruby style="color:#AAAAAA">订单号:</ruby> [[${order.orderSn}]]</span>
      <span>谷粒商城<i class="table_i"></i></span>
      <i class="table_i5 isShow"></i>
    </td>
  </tr>
  <tr class="tr" th:each="item,itemStat:${order.itemEntities}">
    <td colspan="3" style="border-right: 1px solid #ccc">
      <img style="height: 60px;width: 60px;" th:src="${item.skuPic}" alt="" class="img">
      <div>
        <p style="width: 242px;height: auto;overflow: auto">
          [[${item.skuName}]]
        </p>
        <div><i class="table_i4"></i>找搭配</div>
      </div>
      <div style="margin-left:15px;">x[[${item.skuQuantity}]]</div>
      <div style="clear:both"></div>
    </td>
    <td th:if="${itemStat.index==0}" th:rowspan="${itemStat.size}">[[${order.receiverName}]]<i><i class="table_i1"></i></i></td>
    <td th:if="${itemStat.index==0}" th:rowspan="${itemStat.size}" style="padding-left:10px;color:#AAAAB1;">
      <p style="margin-bottom:5px;">总额 ￥[[${order.payAmount}]]</p>
      <hr style="width:90%;">
      <p>在线支付</p>
    </td>
    <td th:if="${itemStat.index==0}" th:rowspan="${itemStat.size}">
      <ul>
        <li style="color:#71B247;" th:if="${order.status==0}">待付款</li>
        <li style="color:#71B247;" th:if="${order.status==1}">已付款</li>
        <li style="color:#71B247;" th:if="${order.status==2}">已发货</li>
        <li style="color:#71B247;" th:if="${order.status==3}">已完成</li>
        <li style="color:#71B247;" th:if="${order.status==4}">已取消</li>
        <li style="color:#71B247;" th:if="${order.status==5}">售后中</li>
        <li style="color:#71B247;" th:if="${order.status==6}">售后完成</li>
        <li style="margin:4px 0;" class="hide"><i class="table_i2"></i>跟踪<i class="table_i3"></i>
            <div class="hi">
              <div class="p-tit">
                普通快递   运单号:390085324974
              </div>
              <div class="hideList">
                <ul>
                  <li>
                    [北京市] 在北京昌平区南口公司进行签收扫描,快件已被拍照(您
                    的快件已签收,感谢您使用韵达快递)签收
                  </li>
                  <li>
                    [北京市] 在北京昌平区南口公司进行签收扫描,快件已被拍照(您
                    的快件已签收,感谢您使用韵达快递)签收
                  </li>
                  <li>
                    [北京昌平区南口公司] 在北京昌平区南口公司进行派件扫描
                  </li>
                  <li>
                    [北京市] 在北京昌平区南口公司进行派件扫描;派送业务员:业务员;联系电话:17319268636
                  </li>
                </ul>
              </div>
            </div>
        </li>
        <li class="tdLi">订单详情</li>
      </ul>
    </td>
    <td th:if="${itemStat.index==0}" th:rowspan="${itemStat.size}">
      <button>确认收货</button>
      <p style="margin:4px 0; ">取消订单</p>
      <p>催单</p>
    </td>
  </tr>
</table>
```

#####  9.3.5 支付宝异步回调通知

**支付结果异步通知**

对于PC网站支付的交易，在用户支付完成之后，支付宝会根据API中商户传入的 `notify_url`，通过POST请求的形式将支付结果作为参数通知到商户系统。支付之后支付宝会异步地访问配置的`return_url`地址，通过一个`post`请求将支付的结果通知到系统。如果支付成功，就应该修改订单的状态为已支付，并将支付信息记入流水信息表`oms_payment_info`，方便后续对账

文档：`https://opendocs.alipay.com/open/270/105902/`

一个订单对应一个流水：

```sql
CREATE TABLE `oms_payment_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_sn` char(64) DEFAULT NULL COMMENT '订单号（对外业务号）',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `alipay_trade_no` varchar(50) DEFAULT NULL COMMENT '支付宝交易流水号',
  `total_amount` decimal(18,4) DEFAULT NULL COMMENT '支付总金额',
  `subject` varchar(200) DEFAULT NULL COMMENT '交易内容',
  `payment_status` varchar(20) DEFAULT NULL COMMENT '支付状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `confirm_time` datetime DEFAULT NULL COMMENT '确认时间',
  `callback_content` varchar(4000) DEFAULT NULL COMMENT '回调内容',
  `callback_time` datetime DEFAULT NULL COMMENT '回调时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_sn` (`order_sn`),
  UNIQUE KEY `alipay_trade_no` (`alipay_trade_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='支付信息表'
```



开发测试期间，需要让支付宝能够访问我们本地的计算机，这里我们采用内网穿透来让本地端口可以被访问到

![image-20230924235332162](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012104251.png)



内网穿透联调请求转发图解：

![image-20230924230512800](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012104800.png)

```sh
[root@localhost conf.d]# pwd
/mydata/nginx/conf/conf.d
[root@localhost conf.d]# ls
default.conf  gulimall.conf
```

修改`nginx`的配置文件`gulimall.conf`：

![image-20230926002317496](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012105082.png)

修改`nginx`的配置文件`gulimall.conf`：

```sh
listen 80;
server_name gulimall.com *.gulimall.com 497n86m7k7.52http.net;
#charset koi8-r;
#access_log /var/log/nginx/log/host.access.log main;
location /static/ {
root /usr/share/nginx/html;
}
location /payed/ {
proxy_set_header Host order.gulimall.com;
proxy_pass http://gulimall;
}
```



配置异步回调地址后可以测试支付宝通过`post`请求发送的消息都包含哪些信息。打印回调信息可以发现，该异步回调包含如下信息，我们将这些信息封装成如下一个类：

```java
@ToString
@Data
public class PayAsyncVo {

    private String gmt_create;
    private String charset;
    private String gmt_payment;
    private Date notify_time;
    private String subject;
    private String sign;
    private String buyer_id;//支付者的id
    private String body;//订单的信息
    private String invoice_amount;//支付金额
    private String version;
    private String notify_id;//通知id
    private String fund_bill_list;
    private String notify_type;//通知类型； trade_status_sync
    private String out_trade_no;//订单号
    private String total_amount;//支付的总额
    private String trade_status;//交易状态  TRADE_SUCCESS
    private String trade_no;//流水号
    private String auth_app_id;//
    private String receipt_amount;//商家收到的款
    private String point_amount;//
    private String app_id;//应用id
    private String buyer_pay_amount;//最终支付的金额
    private String sign_type;//签名类型
    private String seller_id;//商家的id
}
```

`OrderPayedListener`：支付宝通过`post`请求通知商户支付的结果，系统通过支付宝返回的信息判断是否支付成功，如果支付成功就修改订单状态

```java
@RestController
public class OrderPayedListener {
    @Autowired
    AlipayTemplate alipayTemplate;
    @Autowired
    OrderService orderService;
    /**
     * 支付宝成功异步通知
     * @param request
     * @return
     */
    @PostMapping("/payed/notify")
    public String handleAlipayed(PayAsyncVo vo,HttpServletRequest request) throws AlipayApiException, UnsupportedEncodingException {
        //只要我们收到了支付宝给我们异步的通知，告诉我们订单支付成功。返回success，支付宝就再也不通知
//        Map<String, String[]> map = request.getParameterMap();
//        for (String key : map.keySet()) {
//            String value = request.getParameter(key);
//            System.out.println("参数名："+key+"==>参数值："+value);
//        }
        //验签
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayTemplate.getAlipay_public_key(), alipayTemplate.getCharset(), alipayTemplate.getSign_type()); //调用SDK验证签名
        if(signVerified){
            System.out.println("签名验证成功...");
            String result = orderService.handlePayResult(vo);
            return result;
        }else {
            System.out.println("签名验证失败...");
            return "error";
        }
        
    }
}
```

`OrderService`：

```java
public interface OrderService extends IService<OrderEntity> {
    /**
     * 支付成功以后处理支付结果
     */
    String handlePayResult(PayAsyncVo vo);

}
```

`OrderServiceImpl`：支付成功以后处理支付结果，保存交易流水，修改订单的状态信息

```java
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {
    @Autowired
    PaymentInfoService paymentInfoService;

    /**
     * 处理支付宝的支付结果
     *
     * @param vo
     * @return
     */
    @Override
    public String handlePayResult(PayAsyncVo vo) {
        //1、保存交易流水
        PaymentInfoEntity infoEntity = new PaymentInfoEntity();
        infoEntity.setAlipayTradeNo(vo.getTrade_no());
        infoEntity.setOrderSn(vo.getOut_trade_no());
        infoEntity.setPaymentStatus(vo.getTrade_status());
        infoEntity.setCallbackTime(vo.getNotify_time());

        paymentInfoService.save(infoEntity);

        //2、修改订单的状态信息
        if (vo.getTrade_status().equals("TRADE_SUCCESS") || vo.getTrade_status().equals("TRADE_FINISHED")) {
            //支付成功状态
            String outTradeNo = vo.getOut_trade_no();
            this.baseMapper.updateOrderStatus(outTradeNo, OrderStatusEnum.PAYED.getCode());
        }
        return "success";
    }

}
```

`OrderDao`：

```java
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
    void updateOrderStatus(@Param("outTradeNo") String outTradeNo, @Param("code") Integer code);
}
```

`OrderDao.xml`：

```xml
    <update id="updateOrderStatus">
        UPDATE `oms_order`
        SET `status`=#{code}
        WHERE order_sn = #{outTradeNo}
    </update>
```

#####  9.3.6 支付过程中的一些问题

**问题1**：支付宝收单问题

订单在支付页，不支付，一直刷新，订单过期了才支付，订单状态改为已支付了，但是库存解锁了

**问题1解决**：

使用支付宝自动收单功能解决。只要一段时间不支付，就不能支付了

配置支付宝自动收单功能：配置`timeout_express`，超过一段时间没支付，该订单就会失效，继续支付就会响应支付已超时

```java
@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AlipayTemplate {

    //在支付宝创建的应用的id
    private   String app_id = "2016092200568607";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private  String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDD8TcACeOuLpXaG3EupnxffW+TjJqYVWVSEmqEaTCwNLa3i1o4qXYShcq0uzU0AxjOAgub5kzlVb+9F213/Qg0VCJp5alT5mIf2EI7X2ChJndoC+pEC3Lo+/3Uos4Cd6LpoYbQPQyJFWPX5spzBK4qP/+5r8vEkdFurl6YDlIallcxpQ2DbQLNyb2yjs3kryvGYjGuYtd09ah/ZoFQis/ha7d4K/E9twgPQaxBHB55DqZUkPJk+03rwKW2HZZDe4+6kYtEygpCC6JlnnbRMGcjXWbF0XCX9AkbG4oS37FahMiG5jrsX79kbs3RJ138QvTnYqmWil3Mry6bFoMSQiYdAgMBAAECggEASckA5CPVOBdU2MAyu9V3ymGq7Y4p/SlTMTe8IsaVzhTbpXx1ahbuXoVBUxOYMlDkO1dr1bZAyK+kt2kmEOQY/dOc7fHM9Moxszo1nrQI6EZa+rRZ8Gu7Rt71s+fm2ekBUSdBZBNJMCn84iWHoQ5nI4PcIamAezJKAnrpJitmYZoHx3mMIZHL7UPpev/gIZmWgaONe2jIdLzjwTJIo9rdvwqCpC8DXwuPd7Y6MF3z71HZIRoU0xkF2+QtX4nd6xxU6qPIEvOSW4ExcrwraV72bHDUdoR+dYq6tnvV2z5rQ14A/dy2jhkVDSRIek47u5Syr/ykWR/BmM/P+V42V0pwgQKBgQD7YtylMjbA3AQLrfwXFqbcWOomE/hPObK56gxaxPonq/mJ813L0xf0JtrPn2Z9qTNtv78ukC8sx7T3BvyfC41QgS48zzejhQ8mzuX6gueqJ4nceh17YZB3Thetw7+exTVnbIgZ0wkbzVPETr82+H3hP+8Fwh9DtgocDB/SFglNfQKBgQDHidl/CIcZ4ex2b2clM6ayioYMQdDixg/BgtCajrlAYmvnwyGHzjxhxCPqDw1EpK9zrYwBihyq2IdGtMeYjY8KoeP9KC5hOrF5hnWeUwKrYE8OCBnv3AlXQ0DfRmYI1axHI+oB6KPbAwurA45bQYxP17b6r9oOdEeE1olnBOgdIQKBgGCKBtIqBnzqAWO63STAvjPAsasN4D4MmmZrS3NQUgEKOjqQC2VLG9fXVBCH1oRLhkSPbowjMvqvdv+m3zz04sAX2/neWi+h2b+9lPQvcWgW5wqUmt3XCfPHObCxi9UdRvUA6QOB1+2lXJX259fkdfMa6l/7vKX0Ms2ymkXD0K7BAoGBAMPpisx5OPymn56hPqp+DhuaeoaqUEd3o5yhreMyYpzehYemRNOsIAj1pTV31dUhxMpVCPOyAPeVZSx87sPPqTQNQ2YZ/3+jOwFQjUcHxcUx6Cyks6KXbexpsdYGrsPZ1y56CpYZLlgoo1ojQbBDqVnzOErmQbiTXm0Enx+w551BAoGAHg1FfHnEsibV8b6IY1j0FgZxWhaj9vFeJhloo9xW7KOBm5tEsV3SsBUHIibHYl3djwWeeThULKpwZV2ONgonN8hXloeltayfA1L39aZbFbCP+Iq12PcTG2KL1z+gFYjqBxk+HFVjJBzX9qJMUdoZ4iMpKQjYxJbAWqfBWym69Ho=";;
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private  String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyQQceVUChTJGtF/a8SXufhSxDTKporieTq9NO7yDZSpDlAX1zVPT/nf0KWAlxq1TYappWMIYtyrOABhJyn6flNP6vuSBiM5lYsepHvYrtRHqlFiJruEkiaCgEZBKL5aCfBHYj0oqgQn9MpNV/PEH4cBYAVaiI4+VX8CBUQfeEGjgN6OkpLULZ3X0JUkmSnVvCNJ1m3PD68IIlbOfEZXJUKCqmZhzprGR5VWswjxA+g87cMwvijL4gdkSy/daG62Bz5vApcmmMkuX1k1fMWP4ajZCASVw8HD+MSLRhd8We9F97gd8CW0TavzbdR+mTS5H4yEgO8F9HRAsbkhV9yu0yQIDAQAB";
    // 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    private  String notify_url = "http://497n86m7k7.52http.net/payed/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    private  String return_url = "http://member.gulimall.com/memberOrder.html";

    // 签名方式
    private  String sign_type = "RSA2";

    // 字符编码格式
    private  String charset = "utf-8";

    private String timeout = "30m";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    private  String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public  String pay(PayVo vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+timeout+"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        System.out.println("支付宝的响应："+result);

        return result;

    }
}
```



**问题2**：由于时延等问题。订单解锁完成，正在解锁库存的时候，异步通知才到

**问题2解决**：订单解锁，手动调用收单



**问题3**：网络阻塞问题，订单支付成功的异步通知一直不到达

**问题3解决**：查询订单列表时，ajax获取当前未支付的订单状态，查询订单状态时，再获取一下支付宝此订单的状态



**解决交易争议问题**：每天晚上服务空闲时下载支付宝对账单，一一进行对账



## 10. 秒杀

###  10.1 秒杀限流 & 上架流程 & 秒杀流程 & 秒杀相关问题

秒杀具有**瞬间高并发**的特点，针对这一特点，必须要做限流＋异步＋缓存（页面静态化)＋独立部署

**一、常见的限流方式**：

1.前端限流，一些高并发的网站直接在前端页面开始限流，例如:小米的验证码设计

2.`nginx`限流，直接负载部分请求到错误的静态页面：令牌算法漏斗算法

3.网关限流，限流的过滤器

4.代码中使用分布式信号量

5.`rabbitMQ`限流（能者多劳:`chanel.basicQos(1)`），保证发挥所有服务器的性能



**二、商品定时上架流程**：

首先查询最近三天需要进行秒杀的商品信息，将这些信息存储到redis缓存中。秒杀过程中需要频繁的访问商品信息、商品秒杀等信息，这些信息在秒杀开始前都需要添加到redis缓存中，从而加速数据处理的过程。商品定时上架流程：

![image-20230930112138751](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012105774.png)



**三、本项目采用的秒杀流程**

![image-20231001041812735](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012105519.png)



**四、秒杀（高并发)系统关注的问题**

**服务单一职责 &独立部署**：秒杀服务即使自己扛不住压力，挂掉。不要影响别人

**秒杀链接加密**：防止恶意攻击，模拟秒杀请求，1000次/s攻击。防止链接暴露，自己工作人员，提前秒杀商品

**库存预热 & 快速扣减**：秒杀读多写少。无需每次实时校验库存。我们库存预热，放到redis中。信号量控制进来秒杀的请求

**动静分离**：nginx做好动静分离。保证秒杀和商品详情页的动态请求才打到后端的服务集群。使用CDN网络，分担本集群压力

**恶意请求拦截**：识别非法攻击请求并进行拦截，网关层

**流量错峰**：使用各种手段，将流量分担到更大宽度的时间点。比如验证码，加入购物车

**限流&熔断&降级**：前端限流+后端限流。限制次数，限制总量，快速失败降级运行，熔断隔离防止雪崩

**队列削峰**：1万个商品，每个1000件秒杀。双11所有秒杀成功的请求，进入队列，慢慢创建订单，扣减库存即可

###  10.2 后台添加秒杀商品

在网关服务`gulimall-gateway`中配置路由规则，让前端项目可以成功请求优惠服务`gulimall-coupon`

```yaml
spring:
  cloud:
    gateway:
      routes:
# 转发到优惠服务
        - id: coupon_route
          uri: lb://gulimall-coupon
          predicates:
            - Path=/api/coupon/**
          filters:
            - RewritePath=/api/(?<segment>.*),/$\{segment}
```

启动`renren-fast`后台管理系统和`renren-fast-vue`前端项目，点击`新增`按钮新增秒杀数据

![image-20230929113411503](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012106405.png)

秒杀活动除了场次和时间等信息之外，还需要和特定的商品进行关联

`秒杀活动商品关联表`：

```sql
CREATE TABLE `sms_seckill_sku_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `promotion_id` bigint(20) DEFAULT NULL COMMENT '活动id',
  `promotion_session_id` bigint(20) DEFAULT NULL COMMENT '活动场次id',
  `sku_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `seckill_price` decimal(10,0) DEFAULT NULL COMMENT '秒杀价格',
  `seckill_count` decimal(10,0) DEFAULT NULL COMMENT '秒杀总量',
  `seckill_limit` decimal(10,0) DEFAULT NULL COMMENT '每人限购数量',
  `seckill_sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀活动商品关联';
```

在优惠服务`gulimall-coupon`中实现查询秒杀活动关联商品的接口：

```java
@RestController
@RequestMapping("coupon/seckillskurelation")
public class SeckillSkuRelationController {
    @Autowired
    private SeckillSkuRelationService seckillSkuRelationService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seckillSkuRelationService.queryPage(params);
        return R.ok().put("page", page);
    }
}
```

`SeckillSkuRelationService`：查询秒杀活动关联商品

```java
public interface SeckillSkuRelationService extends IService<SeckillSkuRelationEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
```

`SeckillSkuRelationServiceImpl`：查询秒杀活动关联商品

```java
@Service("seckillSkuRelationService")
public class SeckillSkuRelationServiceImpl extends ServiceImpl<SeckillSkuRelationDao, SeckillSkuRelationEntity> implements SeckillSkuRelationService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<SeckillSkuRelationEntity> queryWrapper = new QueryWrapper<SeckillSkuRelationEntity>();
        //场次id不是null
        String promotionSessionId = (String) params.get("promotionSessionId");
        if (!StringUtils.isEmpty(promotionSessionId)) {
            queryWrapper.eq("promotion_session_id", promotionSessionId);
        }
        IPage<SeckillSkuRelationEntity> page = this.page(new Query<SeckillSkuRelationEntity>().getPage(params), queryWrapper);
        return new PageUtils(page);
    }
}
```



###  10.3 秒杀商品定时上架

定时任务框架quartz：`http://www.quartz-scheduler.org/`

#####  10.5.1 cron表达式

cron表达式相关文档：`http://www.quartz-scheduler.org/documentation/quartz-2.3.0/tutorials/crontrigger.html`

在线`Cron`表达式生成器：`https://cron.qqe2.com/`

cron表达式语法：秒 分 时 日 月 周 年 (Spring不支持年)

cron 表达式是由 6 或 7 个用空格分隔的字段组成的字符串。字段可以包含任何允许的值，以及该字段允许的特殊字符的各种组合。字段如下：

| Field Name   | Mandatory | Allowed Values   | Allowed Special Characters |
| ------------ | --------- | ---------------- | -------------------------- |
| Seconds      | YES       | 0-59             | ，- * /                    |
| Minutes      | YES       | 0-59             | ，- * /                    |
| Hours        | YES       | 0-23             | ，- * /                    |
| Day of Month | YES       | 1-31             | , - * ? / L W              |
| Month        | YES       | 1-12 or JAN-DEC  | , - * /                    |
| Day of week  | YES       | 1-7 or SUN-SAT   | , - * ? / L #              |
| Year         | NO        | empty, 1970-2099 | , - * /                    |

`cron 表达式`特殊字符：

```sh
特殊字符:
    , 枚举:
          (cron="7,9,23 * * * * ?"): 任意时刻的7,9，23秒启动这个任务
    - 范围:
          (cron="7-20 * * * * ?"): 任意时刻的7-20秒之间，每秒启动一次
    *  任意:
          指定位置的任意时刻都可以
    /  步长：
          (cron="7/5 * * * * ?"): 第7秒启动，每5秒一次
          (cron="*/5 * * * * ?"): 任意秒启动，每5秒一次

    ? :（出现在日和周几的位置）:
           为了防止日和周冲突，在周和日上如果要写通配符使用?
          (cron="* * * 1 * ?"): 每月的1号，而且必须是周二然后启动这个任务

     L:（出现在日和周的位置）
           last:最后一个
           (cron="* * * ? * 3 L"):每月的最后一个周二

     W：Work Day:工作日
           (cron="* * * W * ?"): 每个月的工作日触发
           (cron="* * * L W * ?"): 每个月的最后一个工作日触发
     #：第几个
           (cron="* * * ? * 5 #2"):每个月的 第2个周4
```

`cron` 表达式示例：

| Expression               | Meaning                                                      |
| ------------------------ | ------------------------------------------------------------ |
| 0 0 12 * * ?             | Fire at 12pm (noon) every day                                |
| 0 15 10 ? * *            | Fire at 10:15am every day                                    |
| 0 15 10 * * ?            | Fire at 10:15am every day                                    |
| 0 15 10 * * ? *          | Fire at 10:15am every day                                    |
| 0 15 10 * * ? 2005       | Fire at 10:15am every day during the year 2005               |
| 0 * 14 * * ?             | Fire every minute starting at 2pm and ending at 2:59pm, every day |
| 0 0/5 14 * * ?           | Fire every 5 minutes starting at 2pm and ending at 2:55pm, every day |
| 0 0/5 14,18 * * ?        | Fire every 5 minutes starting at 2pm and ending at 2:55pm, AND fire every 5 minutes starting at 6pm and ending at 6:55pm, every day |
| 0 0-5 14 * * ?           | Fire every minute starting at 2pm and ending at 2:05pm, every day |
| 0 10,44 14 ? 3 WED       | Fire at 2:10pm and at 2:44pm every Wednesday in the month of March |
| 0 15 10 ? * MON-FRI      | Fire at 10:15am every Monday, Tuesday, Wednesday, Thursday and Friday |
| 0 15 10 15 * ?           | Fire at 10:15am on the 15th day of every month               |
| 0 15 10 L * ?            | Fire at 10:15am on the last day of every month               |
| 0 15 10 L-2 * ?          | Fire at 10:15am on the 2nd-to-last last day of every month   |
| 0 15 10 ? * 6L           | Fire at 10:15am on the last Friday of every month            |
| 0 15 10 ? * 6L           | Fire at 10:15am on the last Friday of every month            |
| 0 15 10 ? * 6L 2002-2005 | Fire at 10:15am on every last friday of every month during the years 2002, 2003, 2004 and 2005 |
| 0 15 10 ? * 6#3          | Fire at 10:15am on the third Friday of every month           |
| 0 0 12 1/5 * ?           | Fire at 12pm (noon) every 5 days every month, starting on the first day of the month |
| 0 11 11 11 11 ?          | Fire every November 11th at 11:11am                          |

**示例：使用Spring自带的定时任务**

**HelloSchedule**：使用Spring自带的定时任务

```java
/**
 * 定时任务
 *      1、@EnableScheduling 开启定时任务
 *      2、@Scheduled  开启一个定时任务
 *      3、自动配置类 TaskSchedulingAutoConfiguration
 *
 * 异步任务
 *      1、@EnableAsync 开启异步任务功能
 *      2、@Async 给希望异步执行的方法上标注
 *      3、自动配置类 TaskExecutionAutoConfiguration 属性绑定在TaskExecutionProperties
 *
 */
@Slf4j
@Component
@EnableAsync
@EnableScheduling
public class HelloSchedule {
    /**
     * 1、Spring中6位组成，不允许第7位的年
     * 2、在周几的位置，1-7代表周一到周日； MON-SUN
     * 3、定时任务不应该阻塞。默认是阻塞的
     *      1）、可以让业务运行以异步的方式，自己提交到线程池
     *              CompletableFuture.runAsync(()->{
     *                  xxxxService.hello();
     *              },executor);
     *      2）、支持定时任务线程池；设置 TaskSchedulingProperties；
     *              spring.task.scheduling.pool.size=5
     *
     *      3）、让定时任务异步执行
     *          异步任务；
     *
     *     解决：使用异步+定时任务来完成定时任务不阻塞的功能；
     */
    @Async
    @Scheduled(cron = "* * * ? * 5")
    public void hello() throws InterruptedException {
        log.info("hello...");
        Thread.sleep(3000);
    }
}
```

`application.properties`配置：

```properties
# 异步任务线程池配置
spring.task.execution.pool.core-size=5
spring.task.execution.pool.max-size=50
```

Spring自带的定时任务**使用总结**：

```java
1.Spring自带的定时任务与quartz框架存在区别：
      1.Spring中6位组成，不允许第7位的年
      2.在周几的位置，1-7代表周一到周日； MON-SUN
2.定时任务中要注意的点：
      1.定时任务不应该阻塞。默认是阻塞的
            在定时任务的应用场景中，某一个定时任务不应该阻塞其他定时任务的执行
            但是spring的定时任务默认是阻塞的
            解决：使用异步+定时任务来完成定时任务不阻塞的功能
      2.解决定时任务阻塞方法一：可以让业务运行以异步的方式，自己提交到线程池
             CompletableFuture.runAsync(()->{
                 xxxxService.hello();
             },executor);
      3.解决定时任务阻塞方法二：spring支持定时任务线程池:
            定时任务线程池设置 TaskSchedulingProperties
            spring.task.scheduling.pool.size=5
      4.解决定时任务阻塞方法三(推荐)：让定时任务异步执行， 定时任务+异步任务
            定时任务：
                  1.@EnableScheduling 开启定时任务
                  2.@Scheduled  开启一个定时任务
                  3.自动配置类 TaskSchedulingAutoConfiguration
            异步任务：
                  1.@EnableAsync 开启异步任务功能
                  2.@Async 给希望异步执行的方法上标注
                  3.自动配置类 TaskExecutionAutoConfiguration 属性绑定在TaskExecutionPropertiess
                    # 异步任务线程池配置
                    spring.task.execution.pool.core-size=5
                    spring.task.execution.pool.max-size=50  
```

#####  10.5.2 秒杀商品定时上架

`ScheduledConfig`：`@EnableAsync`注解开启定时任务，`@EnableScheduling`注解开启异步任务

```java
@EnableAsync
@EnableScheduling
@Configuration
public class ScheduledConfig {
}
```

`application.properties`：

```properties
spring.task.execution.pool.core-size=5
spring.task.execution.pool.max-size=50
```

优惠服务`gulimall-coupon`查询最近三天的秒杀活动

```java
@RestController
@RequestMapping("coupon/seckillsession")
public class SeckillSessionController {
    @Autowired
    private SeckillSessionService seckillSessionService;
    @GetMapping("/lates3DaySession")
    public R getLates3DaySession(){
        List<SeckillSessionEntity> sessions = seckillSessionService.getLates3DaySession();
        return R.ok().setData(sessions);
    }
}
```

`SeckillSessionService`：

```java
public interface SeckillSessionService extends IService<SeckillSessionEntity> {
    List<SeckillSessionEntity> getLates3DaySession();
}
```

`SeckillSessionServiceImpl`：

```java
@Service("seckillSessionService")
public class SeckillSessionServiceImpl extends ServiceImpl<SeckillSessionDao, SeckillSessionEntity> implements SeckillSessionService {

    @Autowired
    SeckillSkuRelationService seckillSkuRelationService;

    @Override
    public List<SeckillSessionEntity> getLates3DaySession() {
        //计算最近三天
//        Date date = new Date(); //2020-12-12 13:59:16

        List<SeckillSessionEntity> list = this.list(new QueryWrapper<SeckillSessionEntity>().between("start_time", startTime(), endTime()));

        if (list != null && list.size() > 0) {
            List<SeckillSessionEntity> collect = list.stream().map(session -> {
                Long id = session.getId();
                List<SeckillSkuRelationEntity> relationEntities = seckillSkuRelationService.list(new QueryWrapper<SeckillSkuRelationEntity>().eq("promotion_session_id", id));
                session.setRelationSkus(relationEntities);
                return session;
            }).collect(Collectors.toList());
            return collect;
        }
        return null;
    }

    private String startTime(){
        LocalDate now = LocalDate.now();
        LocalTime min = LocalTime.MIN;
        LocalDateTime start = LocalDateTime.of(now, min);

        String format = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return format;
    }

    private String endTime(){
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.plusDays(2);
        LocalDateTime of = LocalDateTime.of(localDate, LocalTime.MAX);
        String format = of.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return format;
    }
}
```



###  10.4 秒杀服务`gulimall-seckill`

创建秒杀服务`gulimall-seckill`

添加`pom.xml`依赖：

```xml
    <dependencies>
        <dependency>
            <groupId>com.atguigu.gulimall</groupId>
            <artifactId>gulimall-common</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <exclusions>
                <exclusion>
                    <groupId>com.alibaba.cloud</groupId>
                    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>com.alibaba.cloud</groupId>
                    <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
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
```

##### 10.4.1 缓存秒杀活动及商品信息

**一、秒杀服务`gulimall-seckill`调用优惠服务`gulimall-coupon`获取最近三天秒杀活动**

```java
@FeignClient("gulimall-product")
public interface ProductFeignService {
    @RequestMapping("/product/skuinfo/info/{skuId}")
    R getSkuInfo(@PathVariable("skuId") Long skuId);
}
```

在秒杀服务`gulimall-seckill`中封装秒杀相关数据`SeckillSesssionsWithSkus`和`SeckillSkuVo`：

`SeckillSesssionsWithSkus`：

```java
@Data
public class SeckillSesssionsWithSkus {
    private Long id;
    /**
     * 场次名称
     */
    private String name;
    /**
     * 每日开始时间
     */
    private Date startTime;
    /**
     * 每日结束时间
     */
    private Date endTime;
    private Integer status;
    private Date createTime;
    private List<SeckillSkuVo> relationSkus;
}
```

`SeckillSkuVo`：

```java
@Data
public class SeckillSkuVo {
    private Long id;
    /**
     * 活动id
     */
    private Long promotionId;

    /**
     * 活动场次id
     */
    private Long promotionSessionId;
    /**
     * 商品id
     */
    private Long skuId;
    /**
     * 秒杀价格
     */
    private BigDecimal seckillPrice;
    /**
     * 秒杀总量
     */
    private Integer seckillCount;
    /**
     * 每人限购数量
     */
    private Integer seckillLimit;
    /**
     * 排序
     */
    private Integer seckillSort;
}
```

**二、秒杀服务`gulimall-seckill`调用商品服务`gulimall-product`获取商品详情**

在秒杀服务`gulimall-seckill`下添加`Feign`调用接口定义`/product/skuinfo/info/{skuId}`：

```java
@FeignClient("gulimall-product")
public interface ProductFeignService {
    @RequestMapping("/product/skuinfo/info/{skuId}")
    R getSkuInfo(@PathVariable("skuId") Long skuId);
}
```

商品服务`gulimall-product`提供接口`/product/skuinfo/info/{skuId}`：

`SkuInfoController`：

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
    public R info(@PathVariable("skuId") Long skuId) {
        SkuInfoEntity skuInfo = skuInfoService.getById(skuId);

        return R.ok().put("skuInfo", skuInfo);
    }
}
```

**三、基于`Redisson`分布式锁解决秒杀系统的“超卖”问题：`Redisson`的`Semaphore`使用**

![image-20231001013638161](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012106995.png)

引入`redis`、`redisson`依赖：

```xml
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
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
</dependency>

<dependency>
    <groupId>org.redisson</groupId>
    <artifactId>redisson</artifactId>
    <version>3.12.0</version>
</dependency>
```

配置`redisson`：

```java
@Configuration
public class MyRedissonConfig {
    /**
     * 所有对Redisson的使用都是通过RedissonClient对象
     * @return
     * @throws IOException
     */
    @Bean(destroyMethod="shutdown")
    public RedissonClient redisson(@Value("${spring.redis.host}") String url) throws IOException {
        //1、创建配置
        //Redis url should start with redis:// or rediss://
        Config config = new Config();
        config.useSingleServer().setAddress("redis://"+url+":6379");
        //2、根据Config创建出RedissonClient示例
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
```

**四、把要保存到`redis`缓存的数据封装成`SecKillSkuRedisTo`和`SkuInfoVo`：**

`SecKillSkuRedisTo`：

```java
@Data
public class SecKillSkuRedisTo {
    /**
     * 活动id
     */
    private Long promotionId;

    /**
     * 活动场次id
     */
    private Long promotionSessionId;
    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 商品秒杀随机码
     */
    private String randomCode;
    /**
     * 秒杀价格
     */
    private BigDecimal seckillPrice;
    /**
     * 秒杀总量
     */
    private Integer seckillCount;
    /**
     * 每人限购数量
     */
    private Integer seckillLimit;
    /**
     * 排序
     */
    private Integer seckillSort;
    //当前商品秒杀的开始时间
    private Long startTime;
    //当前商品秒杀的结束时间
    private Long endTime;
    //sku的详细信息
    private SkuInfoVo skuInfo;
}
```

`SkuInfoVo`：

```java
@Data
public class SkuInfoVo {
    private Long skuId;
    /**
     * spuId
     */
    private Long spuId;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * sku介绍描述
     */
    private String skuDesc;
    /**
     * 所属分类id
     */
    private Long catalogId;
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     * 默认图片
     */
    private String skuDefaultImg;
    /**
     * 标题
     */
    private String skuTitle;
    /**
     * 副标题
     */
    private String skuSubtitle;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 销量
     */
    private Long saleCount;
}
```

**五、上架最近三天需要秒杀的商品信息**

`SeckillSkuScheduled`：

```java
/**
 * 秒杀商品的定时上架；
 *     每天晚上3点；上架最近三天需要秒杀的商品。
 *     当天00:00:00  - 23:59:59
 *     明天00:00:00  - 23:59:59
 *     后天00:00:00  - 23:59:59
 */
@Slf4j
@Service
public class SeckillSkuScheduled {
    @Autowired
    SeckillService seckillService;
    @Autowired
    RedissonClient redissonClient;
    private  final String  upload_lock = "seckill:upload:lock";
    
//    @Scheduled(cron = "*/3 * * * * ?")
    @Scheduled(cron = "0 * * * * ?") //每分钟执行一次吧，上线后调整为每天晚上3点执行
//    @Scheduled(cron = "0 0 3 * * ?") 线上模式
    public void uploadSeckillSkuLatest3Days(){
        //1、重复上架无需处理
        log.info("上架秒杀的商品信息...");
        // 分布式锁。锁的业务执行完成，状态已经更新完成。释放锁以后。其他人获取到就会拿到最新的状态。
        RLock lock = redissonClient.getLock(upload_lock);
        lock.lock(10, TimeUnit.SECONDS);
        try{
            seckillService.uploadSeckillSkuLatest3Days();
        }finally {
            lock.unlock();
        }
    }
}
```

`SeckillService`：

```java
public interface SeckillService {
    void uploadSeckillSkuLatest3Days();
}
```

`SeckillServiceImp`：

```java
@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {
    @Autowired
    CouponFeignService couponFeignService;
    
    @Autowired
    ProductFeignService productFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RedissonClient redissonClient;

    private final String SESSIONS_CACHE_PREFIX = "seckill:sessions:";
    private final String SKUKILL_CACHE_PREFIX = "seckill:skus";

    private final String SKU_STOCK_SEMAPHORE = "seckill:stock:";//+商品随机码


    @Override
    public void uploadSeckillSkuLatest3Days() {
        //1、扫描最近三天需要参与秒杀的活动
        R session = couponFeignService.getLates3DaySession();
        if (session.getCode() == 0) {
            //上架商品
            List<SeckillSesssionsWithSkus> sessionData = session.getData(new TypeReference<List<SeckillSesssionsWithSkus>>() {
            });
            //缓存到redis
            //1、缓存活动信息
            saveSessionInfos(sessionData);
            //2、缓存活动的关联商品信息
            saveSessionSkuInfos(sessionData);
        }

    }

    private void saveSessionInfos(List<SeckillSesssionsWithSkus> sesssions) {
        if (sesssions != null) sesssions.stream().forEach(sesssion -> {

            Long startTime = sesssion.getStartTime().getTime();
            Long endTime = sesssion.getEndTime().getTime();
            String key = SESSIONS_CACHE_PREFIX + startTime + "_" + endTime;
            Boolean hasKey = redisTemplate.hasKey(key);
            if (!hasKey) {
                List<String> collect = sesssion.getRelationSkus().stream().map(item -> item.getPromotionSessionId() + "_" + item.getSkuId().toString()).collect(Collectors.toList());
                //缓存活动信息
                redisTemplate.opsForList().leftPushAll(key, collect);
                //TODO 设置过期时间[已完成]
                redisTemplate.expireAt(key, new Date(endTime));
            }


        });
    }

    private void saveSessionSkuInfos(List<SeckillSesssionsWithSkus> sesssions) {
        if (sesssions != null) sesssions.stream().forEach(sesssion -> {
            //准备hash操作
            BoundHashOperations<String, Object, Object> ops = redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
            sesssion.getRelationSkus().stream().forEach(seckillSkuVo -> {
                //4、随机码？  seckill?skuId=1&key=dadlajldj；
                String token = UUID.randomUUID().toString().replace("-", "");

                if (!ops.hasKey(seckillSkuVo.getPromotionSessionId().toString() + "_" + seckillSkuVo.getSkuId().toString())) {
                    //缓存商品
                    SecKillSkuRedisTo redisTo = new SecKillSkuRedisTo();
                    //1、sku的基本数据
                    R skuInfo = productFeignService.getSkuInfo(seckillSkuVo.getSkuId());
                    if (skuInfo.getCode() == 0) {
                        SkuInfoVo info = skuInfo.getData("skuInfo", new TypeReference<SkuInfoVo>() {
                        });
                        redisTo.setSkuInfo(info);
                    }

                    //2、sku的秒杀信息
                    BeanUtils.copyProperties(seckillSkuVo, redisTo);

                    //3、设置上当前商品的秒杀时间信息
                    redisTo.setStartTime(sesssion.getStartTime().getTime());
                    redisTo.setEndTime(sesssion.getEndTime().getTime());

                    // 4.设置商品秒杀随机码
                    redisTo.setRandomCode(token);
                    String jsonString = JSON.toJSONString(redisTo);
                    //TODO 每个商品的过期时间不一样。所以，我们在获取当前商品秒杀信息的时候，做主动删除，代码在 getSkuSeckillInfo 方法里面
                    ops.put(seckillSkuVo.getPromotionSessionId().toString() + "_" + seckillSkuVo.getSkuId().toString(), jsonString);

                    //如果当前这个场次的商品的库存信息已经上架就不需要上架
                    //5、使用库存作为分布式的信号量 限流； 如果秒杀请求进来以后获取不到信号量，那后续的操作也就不用执行了
                    RSemaphore semaphore = redissonClient.getSemaphore(SKU_STOCK_SEMAPHORE + token);
                    //商品可以秒杀的数量作为信号量
                    semaphore.trySetPermits(seckillSkuVo.getSeckillCount());
                    //TODO 设置过期时间。
                    semaphore.expireAt(sesssion.getEndTime());
                }
            });
        });
    }
}
```

##### 10.4.2 查询秒杀商品

一、在网关服务`gulimall-gateway`中添加秒杀服务`gulimall-seckill`的路由配置

```yaml
spring:
  cloud:
    gateway:
      routes:
# 转发到秒杀服务
        - id: gulimall_seckill_route
          uri: lb://gulimall-seckill
          predicates:
            - Host=seckill.gulimall.com
```

二、利用`switchHost`添加秒杀服务的域名配置：

```sh
# gulimall
192.168.56.10 gulimall.com
192.168.56.10 search.gulimall.com
192.168.56.10 item.gulimall.com
192.168.56.10 auth.gulimall.com
192.168.56.10 cart.gulimall.com
192.168.56.10 order.gulimall.com
192.168.56.10 member.gulimall.com
192.168.56.10 seckill.gulimall.com

127.0.0.1 ssoserver.com
127.0.0.1 client1.com
127.0.0.1 client2.com
```

三、秒杀服务`gulimall-seckill`返回当前时间可以参与的秒杀商品信息

`SeckillController`：

```java
@Slf4j
@Controller
public class SeckillController {
    @Autowired
    SeckillService seckillService;
    /**
     * 返回当前时间可以参与的秒杀商品信息
     * @return
     */
    @ResponseBody
    @GetMapping("/currentSeckillSkus")
    public R getCurrentSeckillSkus(){
        log.info("currentSeckillSkus正在执行。。。");
        List<SecKillSkuRedisTo> vos = seckillService.getCurrentSeckillSkus();
        return R.ok().setData(vos);
    }
}
```

`SeckillService`：

```java
public interface SeckillService {
    List<SecKillSkuRedisTo> getCurrentSeckillSkus();
}
```

`SeckillServiceImpl`：

```java
@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {
    @Autowired
    CouponFeignService couponFeignService;

    @Autowired
    ProductFeignService productFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

//    @Autowired
//    RabbitTemplate rabbitTemplate;

    @Autowired
    RedissonClient redissonClient;

    private final String SESSIONS_CACHE_PREFIX = "seckill:sessions:";
    private final String SKUKILL_CACHE_PREFIX = "seckill:skus";

    private final String SKU_STOCK_SEMAPHORE = "seckill:stock:";//+商品随机码


    @Override
    public void uploadSeckillSkuLatest3Days() {
        //1、扫描最近三天需要参与秒杀的活动
        R session = couponFeignService.getLates3DaySession();
        if (session.getCode() == 0) {
            //上架商品
            List<SeckillSesssionsWithSkus> sessionData = session.getData(new TypeReference<List<SeckillSesssionsWithSkus>>() {
            });
            //缓存到redis
            //1、缓存活动信息
            saveSessionInfos(sessionData);
            //2、缓存活动的关联商品信息
            saveSessionSkuInfos(sessionData);
        }
    }

    private void saveSessionInfos(List<SeckillSesssionsWithSkus> sesssions) {
        if (sesssions != null) sesssions.stream().forEach(sesssion -> {

            Long startTime = sesssion.getStartTime().getTime();
            Long endTime = sesssion.getEndTime().getTime();
            String key = SESSIONS_CACHE_PREFIX + startTime + "_" + endTime;
            Boolean hasKey = redisTemplate.hasKey(key);
            List<String> collect = sesssion.getRelationSkus().stream().map(item -> item.getPromotionSessionId() + "_" + item.getSkuId().toString()).collect(Collectors.toList());
            if (!hasKey && collect != null && collect.size() > 0) {
                //缓存活动信息
                redisTemplate.opsForList().leftPushAll(key, collect);
                //TODO 设置过期时间[已完成]
                redisTemplate.expireAt(key, new Date(endTime));
            }
        });
    }

    private void saveSessionSkuInfos(List<SeckillSesssionsWithSkus> sesssions) {
        if (sesssions != null) sesssions.stream().forEach(sesssion -> {
            //准备hash操作
            BoundHashOperations<String, Object, Object> ops = redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
            sesssion.getRelationSkus().stream().forEach(seckillSkuVo -> {
                //4、随机码？  seckill?skuId=1&key=dadlajldj；
                String token = UUID.randomUUID().toString().replace("-", "");

                if (!ops.hasKey(seckillSkuVo.getPromotionSessionId().toString() + "_" + seckillSkuVo.getSkuId().toString())) {
                    //缓存商品
                    SecKillSkuRedisTo redisTo = new SecKillSkuRedisTo();
                    //1、sku的基本数据
                    R skuInfo = productFeignService.getSkuInfo(seckillSkuVo.getSkuId());
                    if (skuInfo.getCode() == 0) {
                        SkuInfoVo info = skuInfo.getData("skuInfo", new TypeReference<SkuInfoVo>() {
                        });
                        redisTo.setSkuInfo(info);
                    }

                    //2、sku的秒杀信息
                    BeanUtils.copyProperties(seckillSkuVo, redisTo);

                    //3、设置上当前商品的秒杀时间信息
                    redisTo.setStartTime(sesssion.getStartTime().getTime());
                    redisTo.setEndTime(sesssion.getEndTime().getTime());

                    // 4.设置商品秒杀随机码
                    redisTo.setRandomCode(token);
                    String jsonString = JSON.toJSONString(redisTo);
                    //TODO 每个商品的过期时间不一样。所以，我们在获取当前商品秒杀信息的时候，做主动删除，代码在 getSkuSeckillInfo 方法里面
                    ops.put(seckillSkuVo.getPromotionSessionId().toString() + "_" + seckillSkuVo.getSkuId().toString(), jsonString);

                    //如果当前这个场次的商品的库存信息已经上架就不需要上架
                    //5、使用库存作为分布式的信号量 限流； 如果秒杀请求进来以后获取不到信号量，那后续的操作也就不用执行了
                    RSemaphore semaphore = redissonClient.getSemaphore(SKU_STOCK_SEMAPHORE + token);
                    //商品可以秒杀的数量作为信号量
                    semaphore.trySetPermits(seckillSkuVo.getSeckillCount());
                    //TODO 设置过期时间。
                    semaphore.expireAt(sesssion.getEndTime());
                }
            });
        });
    }


    //返回当前时间可以参与的秒杀商品信息
    @Override
    public List<SecKillSkuRedisTo> getCurrentSeckillSkus() {
        // 1.确定当前时间属于哪个秒杀场次
        long time = new Date().getTime(); // 获取的long数字代表当前时间距离1970年的差值

        Set<String> keys = redisTemplate.keys(SESSIONS_CACHE_PREFIX + "*");
        for (String key : keys) {
            //seckill:sessions:1582250400000_1582254000000
            String replace = key.replace(SESSIONS_CACHE_PREFIX, "");
            String[] s = replace.split("_");
            Long start = Long.parseLong(s[0]);
            Long end = Long.parseLong(s[1]);
            if (time >= start && time <= end) {
                //2、获取这个秒杀场次需要的所有商品信息
                List<String> range = redisTemplate.opsForList().range(key, -100, 100);
                BoundHashOperations<String, String, String> hashOps = redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);
                List<String> list = hashOps.multiGet(range);
                if (list != null) {
                    List<SecKillSkuRedisTo> collect = list.stream().map(item -> {
                        SecKillSkuRedisTo redis = JSON.parseObject((String) item, SecKillSkuRedisTo.class);
//                        redis.setRandomCode(null); 当前秒杀开始就需要随机码
                        return redis;
                    }).collect(Collectors.toList());
                    return collect;
                }
                break;
            }
        }
        return null;
    }
}
```

访问`http://seckill.gulimall.com/currentSeckillSkus`，响应如下：

```json
{
    "msg": "success",
    "code": 0,
    "data": [
        {
            "promotionId": null,
            "promotionSessionId": 4,
            "skuId": 5,
            "randomCode": "f8586332aff740dd8963a2b37f0883a0",
            "seckillPrice": 2,
            "seckillCount": 6,
            "seckillLimit": 2,
            "seckillSort": 0,
            "startTime": 1696064400000,
            "endTime": 1696071600000,
            "skuInfo": {
                "skuId": 5,
                "spuId": 11,
                "skuName": "华为 HUAWEI Mate 30 Pro 翡冷翠 8GB+256GB",
                "skuDesc": null,
                "catalogId": 225,
                "brandId": 9,
                "skuDefaultImg": "https://g-search2.alicdn.com/img/bao/uploaded/i4/i4/4103434468/O1CN01syLUXM1isOSu0Oa0x_!!0-item_pic.jpg_250x250.jpg_.webp",
                "skuTitle": "华为 HUAWEI Mate 30 Pro 翡冷翠 8GB+256GB麒麟990旗舰芯片OLED环幕屏双4000万徕卡电影四摄4G全网通手机",
                "skuSubtitle": "【现货抢购！享白条12期免息！】麒麟990，OLED环幕屏双4000万徕卡电影四摄；Mate30系列享12期免息》",
                "price": 6299,
                "saleCount": 0
            }
        }
    ]
}
```

#####  10.4.3 商品服务秒杀相关信息展示

**一、商品服务`gulimall-product`的首页秒杀页面渲染**：

修改商品服务`gulimall-product`的首页`index.html`：

```js
<body>
<div>
     <div class="section_second_list">
        <div class="swiper-container swiper_section_second_list_left">
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <ul id="seckillSkuContent">

                    </ul>
                </div>
                <div class="swiper-slide">
                    <ul>
                        <li>
                            <img src="/static/index/img/section_second_list_img6.jpg" alt="">
                            <p>Apple iMac 21.5英寸一体机（2017新款四核Core i5 处理器/8GB内存/1TB/RP555显卡/4K屏 MNDY2CH/A） Apple iMac
                                21.5英寸一体机（2017新款四核Core i5 处理</p>
                            <span>¥9588.00</span><s>¥10288.00</s>
                        </li>
                        <li>
                            <img src="/static/index/img/section_second_list_img7.jpg" alt="">
                            <p>中柏（Jumper）EZpad 4S Pro 10.6英寸二合一平板电脑（X5 z</p>
                            <span>¥848.00</span><s>¥899.00</s>
                        </li>
                        <li>
                            <img src="/static/index/img/section_second_list_img8.jpg" alt="">
                            <p>飞利浦（PHILIPS）电动牙刷HX6761/03亮白型成人充电式声波震动牙刷粉色 飞利浦（PHILIPS）电动牙刷HX6761/03亮白型成人充电式声波
                            </p>
                            <span>¥379.00</span><s>¥698.00</s>
                        </li>
                        <li>
                            <img src="/static/index/img/section_second_list_img9.jpg" alt="">
                            <p>美的(Midea) 258升 变频智能三门冰箱 一级能效 风冷无霜 中门</p>
                            <span>¥3088.00</span><s>¥3299.00</s>
                        </li>
                        <li>
                            <img src="/static/index/img/section_second_list_img10.jpg" alt="">
                            <p>【第二件减50元】蒙羊 内蒙古羔羊羊肋排 2.4斤</p>
                            <span>¥99.90</span><s>¥199.00</s>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="swiper-button-prev second_list">
                <p></p>
            </div>
            <div class="swiper-button-next second_list">
                <p></p>
            </div>
        </div>
        <ul class="section_second_list_right">
            <li>
                <img src="/static/index/img/section_second_list_right_img.jpg" alt="">
            </li>
            <li>
                <img src="/static/index/img/section_second_list_right_img.png" alt="">
            </li>
            <div class="section_second_list_right_button">
                <p class="section_second_list_right_button_active"></p>
                <p></p>
            </div>
        </ul>
    </div>
</div>
</body>
<script type="text/javascript">
    function search() {
        var keyword = $("#searchText").val()
        window.location.href = "http://search.gulimall.com/list.html?keyword=" + keyword;
    }
    function to_href(skuId){
        location.href = "http://item.gulimall.com/"+skuId+".html";
    }
    //     发送ajax的get请求
    $.get("http://seckill.gulimall.com/currentSeckillSkus",function(resp){
        if(resp.data.length > 0){
            resp.data.forEach(function(item){

                $("<li onclick='to_href("+item.skuId+")'></li>")
                    .append($("<img style='width: 130px;height: 130px' src='"+item.skuInfo.skuDefaultImg+"'/>"))
                    .append($("<p>"+item.skuInfo.skuTitle+"</p>"))
                    .append($("<span>"+item.seckillPrice+"</span>"))
                    .append($("<s>"+item.skuInfo.price+"</s>"))
                    .appendTo("#seckillSkuContent");
            })
        }
        /**
         *   <li>
         <img src="/static/index/img/section_second_list_img1.jpg" alt="">
         <p>花王 (Merries) 妙而舒 纸尿裤 大号 L54片 尿不湿（9-14千克） （日本官方直采） 花王 (Merries) 妙而舒 纸尿裤 大号 L54片
         尿不湿（9-14千</p>
         <span>¥83.9</span><s>¥99.9</s>
         </li>
         */
    });
</script>
```

**二、商品服务`gulimall-product`的商品详情页秒杀相关信息渲染**

`SeckillController`：秒杀服务`gulimall-seckill`根据`skuid`查询商品的秒杀信息

```java
@Slf4j
@Controller
public class SeckillController {
    @Autowired
    SeckillService seckillService;
    @ResponseBody
    @GetMapping("/sku/seckill/{skuId}")
    public R getSkuSeckillInfo(@PathVariable("skuId") Long skuId){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SecKillSkuRedisTo to =  seckillService.getSkuSeckillInfo(skuId);
        return R.ok().setData(to);
    }
}
```

`SeckillService`：秒杀服务`gulimall-seckill`根据`skuid`查询商品的秒杀信息

```java
public interface SeckillService {
    SecKillSkuRedisTo getSkuSeckillInfo(Long skuId);
}
```

`SeckillServiceImpl`：秒杀服务`gulimall-seckill`根据`skuid`查询商品的秒杀信息

```java
@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {
    @Autowired
    CouponFeignService couponFeignService;

    @Autowired
    ProductFeignService productFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RedissonClient redissonClient;

    private final String SESSIONS_CACHE_PREFIX = "seckill:sessions:";
    private final String SKUKILL_CACHE_PREFIX = "seckill:skus";

    private final String SKU_STOCK_SEMAPHORE = "seckill:stock:";//+商品随机码

    @Override
    public SecKillSkuRedisTo getSkuSeckillInfo(Long skuId) {

        //1、找到所有需要参与秒杀的商品的key
        BoundHashOperations<String, String, String> hashOps = redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);


        Set<String> keys = hashOps.keys();
        if (keys != null && keys.size() > 0) {
            String regx = "\\d_" + skuId;
            for (String key : keys) {
                //6_4
                if (Pattern.matches(regx, key)) {
                    String json = hashOps.get(key);
                    SecKillSkuRedisTo skuRedisTo = JSON.parseObject(json, SecKillSkuRedisTo.class);
                    //TODO 加入非空判断
                    if (skuRedisTo == null) return null;
                    //随机码
                    long current = new Date().getTime();
                    if (current >= skuRedisTo.getStartTime() && current <= skuRedisTo.getEndTime()) {
                        //TODO
                    } else {
                        //TODO 当前商品已经过了秒杀时间要直接删除
                        hashOps.delete(key);
                        skuRedisTo.setRandomCode(null);
                    }
                    return skuRedisTo;
                }
                ;
            }
        }
        return null;
    }
}
```

`ItemController`：商品服务`gulimall-product`展示商品的详情

```java
@Controller
public class ItemController {
    @Autowired
    SkuInfoService skuInfoService;
    /**
     * 展示当前sku的详情
     * @param skuId
     * @return
     */
    @GetMapping("/{skuId}.html")
    public String skuItem(@PathVariable("skuId") Long skuId, Model model) throws ExecutionException, InterruptedException {

        System.out.println("准备查询"+skuId+"详情");
        SkuItemVo vo = skuInfoService.item(skuId);
        model.addAttribute("item",vo);

        return "item";
    }
}
```

`SkuInfoService`：商品服务`gulimall-product`展示商品的详情

```java
public interface SkuInfoService extends IService<SkuInfoEntity> {
    SkuItemVo item(Long skuId) throws ExecutionException, InterruptedException;
}
```

`SkuInfoServiceImpl`：商品服务`gulimall-product`展示商品的详情

```java
@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {
    @Autowired
    private SkuImagesService skuImagesService;

    @Autowired
    private SpuInfoDescService spuInfoDescService;

    @Autowired
    private AttrGroupService attrGroupService;
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;


    // 注入配置好的线程池
    @Autowired
    ThreadPoolExecutor executor;

    @Autowired
    SeckillFeignService seckillFeignService;

    // 异步编排优化后
    @Override
    public SkuItemVo item(Long skuId) throws ExecutionException, InterruptedException {
        SkuItemVo skuItemVo = new SkuItemVo();

        CompletableFuture<SkuInfoEntity> infoFuture = CompletableFuture.supplyAsync(() -> {
            // 1.sku基本信息获取  pms_sku_info表
            SkuInfoEntity info = getById(skuId);
            skuItemVo.setInfo(info);
            return info;
        }, executor);

        CompletableFuture<Void> saleAttrFuture = infoFuture.thenAcceptAsync((res) -> {
            //3、获取spu的销售属性组合。
            List<SkuItemSaleAttrVo> saleAttrVos = skuSaleAttrValueService.getSaleAttrsBySpuId(res.getSpuId());
            skuItemVo.setSaleAttr(saleAttrVos);
        }, executor);


        CompletableFuture<Void> descFuture = infoFuture.thenAcceptAsync(res -> {
            //4、获取spu的介绍  pms_spu_info_desc
            SpuInfoDescEntity spuInfoDescEntity = spuInfoDescService.getById(res.getSpuId());
            skuItemVo.setDesp(spuInfoDescEntity);
        }, executor);


        CompletableFuture<Void> baseAttrFuture = infoFuture.thenAcceptAsync(res -> {
            //5、获取spu的规格参数信息。
            List<SpuItemAttrGroupVo> attrGroupVos = attrGroupService.getAttrGroupWithAttrsBySpuId(res.getSpuId(), res.getCatalogId());
            skuItemVo.setGroupAttrs(attrGroupVos);
        }, executor);


        //2、sku的图片信息  pms_sku_images
        CompletableFuture<Void> imageFuture = CompletableFuture.runAsync(() -> {
            List<SkuImagesEntity> images = skuImagesService.getImagesBySkuId(skuId);
            skuItemVo.setImages(images);
        }, executor);

        //3、查询当前sku是否参与秒杀优惠
        CompletableFuture<Void> secKillFuture = CompletableFuture.runAsync(() -> {
            R seckillInfo = seckillFeignService.getSkuSeckillInfo(skuId);
            if (seckillInfo.getCode() == 0) {
                SeckillInfoVo seckillInfoVo = seckillInfo.getData(new TypeReference<SeckillInfoVo>() {
                });
                skuItemVo.setSeckillInfo(seckillInfoVo);
            }
        }, executor);

        // 等到所有任务都完成
        CompletableFuture.allOf(saleAttrFuture, descFuture, baseAttrFuture, imageFuture,secKillFuture).get();

        return skuItemVo;
    }
}
```

商品服务`gulimall-product`详情页`item.html`展示秒杀相关数据

```js
<div class="box-summary clear">
   <ul>
      <li>谷粒商城价</li>
      <li>
         <span>￥</span>
         <span th:text="${#numbers.formatDecimal(item.info.price,3,2)}">4499.00</span>
      </li>
      <li style="color: red" th:if="${item.seckillInfo!=null}">
               <span th:if="${#dates.createNow().getTime()< item.seckillInfo.startTime}">
                  商品将会在[[${#dates.format(new java.util.Date(item.seckillInfo.startTime),"yyyy-MM-dd HH:mm:ss")}]]进行秒杀
               </span>

         <span th:if="${#dates.createNow().getTime()>=item.seckillInfo.startTime && #dates.createNow().getTime()<=item.seckillInfo.endTime}">
                  秒杀价：[[${#numbers.formatDecimal(item.seckillInfo.seckillPrice,1,2)}]]
               </span>
      </li>
      <li>
         <a href="/static/item/">
            预约说明
         </a>
      </li>
   </ul>
</div>
```

商品详情页：如果当前商品处于秒杀状态，商品详情页就显示`立即抢购`，点击立即抢购就会触发秒杀流程。如果不是秒杀状态

```js
        <div class="box-btns-two" th:if="${item.seckillInfo!=null && (#dates.createNow().getTime()>=item.seckillInfo.startTime && #dates.createNow().getTime()<=item.seckillInfo.endTime)}">
            <a href="#" id="secKillA" th:attr="skuId=${item.info.skuId},sessionId=${item.seckillInfo.promotionSessionId},code=${item.seckillInfo.randomCode}">
                立即抢购
            </a>
        </div>
        <div class="box-btns-two" th:if="${item.seckillInfo==null||(#dates.createNow().getTime()<item.seckillInfo.startTime || #dates.createNow().getTime()>item.seckillInfo.endTime)}">
            <a href="#" id="addToCartA" th:attr="skuId=${item.info.skuId}">
                加入购物车
            </a>
        </div>
<script>
                
   $("#secKillA").click(function(){
      var isLogin = [[${session.loginUser!=null}]]; // true
      if(isLogin){
         var killId =$(this).attr("sessionid")+"_"+ $(this).attr("skuid");
         var key = $(this).attr("code");
         var num = $("#numInput").val();
         location.href = "http://seckill.gulimall.com/kill?killId="+killId+"&key="+key+"&num="+num;
      }else {
         alert("秒杀请先登录");
      }
      return false;
   });

	$("#addToCartA").click(function(){
		var  num = $("#numInput").val();
		var skuId = $(this).attr("skuId");
		location.href = "http://cart.gulimall.com/addToCart?skuId="+skuId+"&num="+num;
		return false;
	});

</script>
```

最终效果：商品首页能够展示最近三天的秒杀商品，商品详情页能够看到商品秒杀的相关信息

![image-20230930201538391](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310012106508.png)



#####  10.4.4 整合`springsession`、登录拦截、整合`rabbitMQ`

**一、整合`springsession`**

添加`SpringSession`依赖：

```xml
<!--整合SpringSession完成session共享问题-->
<dependency>
    <groupId>org.springframework.session</groupId>
    <artifactId>spring-session-data-redis</artifactId>
</dependency>
```

`application.properties`：

```properties
spring.redis.host=192.168.56.10
spring.session.store-type=redis
```

`GulimallSessionConfig`：

```java
@EnableRedisHttpSession
@Configuration
public class GulimallSessionConfig {

    @Bean
    public CookieSerializer cookieSerializer(){
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

**二、登录拦截**

`LoginUserInterceptor`：

```java
@Component
public class LoginUserInterceptor implements HandlerInterceptor {
    
    public static ThreadLocal<MemberRespVo> loginUser = new ThreadLocal<>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //  /order/order/status/2948294820984028420
        String uri = request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean match = antPathMatcher.match("/kill", uri);

        if(match){
            MemberRespVo attribute = (MemberRespVo) request.getSession().getAttribute(AuthServerConstant.LOGIN_USER);
            if(attribute!=null){
                loginUser.set(attribute);
                return true;
            }else {
                //没登录就去登录
                request.getSession().setAttribute("msg","请先进行登录");
                response.sendRedirect("http://auth.gulimall.com/login.html");
                return false;
            }
        }
        return true;
    }
}
```

`SeckillWebConfig`：将拦截器添加到web配置中，使其生效

```java
@Configuration
public class SeckillWebConfig implements WebMvcConfigurer {
    @Autowired
    LoginUserInterceptor loginUserInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginUserInterceptor).addPathPatterns("/**");
    }
}
```

**三、整合`rabbitMQ`**

`pom.xml`引入`rabbitMQ`依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

`application.properties`：

```properties
spring.rabbitmq.virtual-host=/
spring.rabbitmq.host=192.168.56.10
```

`rabbitmq`配置：使用JSON序列化机制，进行消息转换

```java
@Configuration
public class MyRabbitConfig {
    /**
     * 使用JSON序列化机制，进行消息转换
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
```

### 10.5 秒杀流程及最终实现

#####  10.5.1 秒杀流程 

第一种秒杀流程（小米官网的秒杀方案）：

![image-20231001042222657](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/image-20231001042222657.png)



第二种秒杀方案（本项目采用）：

![image-20231001041812735](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/image-20231001041812735.png)

第二种秒杀方案中`rabbitMQ`实现消息发送示意图：

![image-20231001044504390](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/image-20231001044504390.png)

将秒杀成功后需要发送到`rabbitMQ` 的数据信息封装为`SeckillOrderTo`：

`SeckillOrderTo`：

```java
@Data
public class SeckillOrderTo {

    private String orderSn; //订单号
    private Long promotionSessionId;  //活动场次id
    private Long skuId;  //商品id
    private BigDecimal seckillPrice; //秒杀价格
    private Integer num; //购买数量
    private Long memberId;//会员id；

}
```

#####  10.5.2 秒杀服务`gulimall-seckill`秒杀实现

秒杀服务`gulimall-seckill`进行秒杀逻辑：

`SeckillController`：

```java
@Slf4j
@Controller
public class SeckillController {
    @Autowired
    SeckillService seckillService;
    
    @GetMapping("/kill")
    public String secKill(@RequestParam("killId") String killId,
                          @RequestParam("key") String key,
                          @RequestParam("num") Integer num,
                          Model model){

       String orderSn =  seckillService.kill(killId,key,num);

        model.addAttribute("orderSn",orderSn);
        //1、判断是否登录
        return "success";
    }

}
```

`SeckillService`：

```java
public interface SeckillService {
    String kill(String killId, String key, Integer num);
}
```

`SeckillServiceImpl`：

```java
@Slf4j
@Service
public class SeckillServiceImpl implements SeckillService {
    @Autowired
    CouponFeignService couponFeignService;

    @Autowired
    ProductFeignService productFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RedissonClient redissonClient;

    private final String SESSIONS_CACHE_PREFIX = "seckill:sessions:";
    private final String SKUKILL_CACHE_PREFIX = "seckill:skus";

    private final String SKU_STOCK_SEMAPHORE = "seckill:stock:";//+商品随机码

    // TODO 上架秒杀商品的时候，每一个数据都有过期时间。
    // TODO 秒杀后续的流程，简化了收货地址等信息。
    @Override
    public String kill(String killId, String key, Integer num) {

        long s1 = System.currentTimeMillis();
        MemberRespVo respVo = LoginUserInterceptor.loginUser.get();

        //1、获取当前秒杀商品的详细信息
        BoundHashOperations<String, String, String> hashOps = redisTemplate.boundHashOps(SKUKILL_CACHE_PREFIX);

        String json = hashOps.get(killId);
        if (StringUtils.isEmpty(json)) {
            return null;
        } else {
            SecKillSkuRedisTo redis = JSON.parseObject(json, SecKillSkuRedisTo.class);
            //校验合法性
            Long startTime = redis.getStartTime();
            Long endTime = redis.getEndTime();
            long time = new Date().getTime();

            long ttl = endTime - time;

            //1、校验时间的合法性
            if (time >= startTime && time <= endTime) {
                //2、校验随机码和商品id
                String randomCode = redis.getRandomCode();
                String skuId = redis.getPromotionSessionId() + "_" + redis.getSkuId();
                if (randomCode.equals(key) && killId.equals(skuId)) {
                    //3、验证购物数量是否合理
                    if (num <= redis.getSeckillLimit()) {
                        //4、验证这个人是否已经购买过。幂等性; 如果只要秒杀成功，就去占位。  userId_SessionId_skuId
                        //SETNX
                        String redisKey = respVo.getId() + "_" + skuId;
                        //自动过期
                        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(redisKey, num.toString(), ttl, TimeUnit.MILLISECONDS);
                        if (aBoolean) {
                            //占位成功说明从来没有买过
                            RSemaphore semaphore = redissonClient.getSemaphore(SKU_STOCK_SEMAPHORE + randomCode);
                            //120  20ms
                            boolean b = semaphore.tryAcquire(num);
                            if (b) {
                                //秒杀成功;
                                //快速下单。发送MQ消息  10ms
                                String timeId = IdWorker.getTimeId();
                                SeckillOrderTo orderTo = new SeckillOrderTo();
                                orderTo.setOrderSn(timeId);
                                orderTo.setMemberId(respVo.getId());
                                orderTo.setNum(num);
                                orderTo.setPromotionSessionId(redis.getPromotionSessionId());
                                orderTo.setSkuId(redis.getSkuId());
                                orderTo.setSeckillPrice(redis.getSeckillPrice());
                                rabbitTemplate.convertAndSend("order-event-exchange", "order.seckill.order", orderTo);
                                long s2 = System.currentTimeMillis();
                                log.info("耗时...{}", (s2 - s1));
                                return timeId;
                            }
                            return null;

                        } else {
                            //说明已经买过了
                            return null;
                        }

                    }
                } else {
                    return null;
                }

            } else {
                return null;
            }
        }
        return null;
    }

}
```

#####  10.5.3 订单服务`gulimall-order`订单处理

订单服务`gulimall-order`监听消息队列并对秒杀订单进行处理

`MyMQConfig`：创建消息队列`order.seckill.order.queue`对秒杀成功的订单进行监听

```java
@Configuration
public class MyMQConfig {
    @Bean
    public Queue orderSeckillOrderQueue(){
        //String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        return new Queue("order.seckill.order.queue",true,false,false);
    }

    @Bean
    public Binding orderSeckillOrderQueueBinding(){
        /**
         * String destination, DestinationType destinationType, String exchange, String routingKey,
         *           Map<String, Object> arguments
         */
        return new Binding("order.seckill.order.queue",
                Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.seckill.order",
                null);
    }

}
```



`OrderSeckillListener`：

```java
@Slf4j
@RabbitListener(queues = "order.seckill.order.queue")
@Component
public class OrderSeckillListener {
    @Autowired
    OrderService orderService;
    @RabbitHandler
    public void listener(SeckillOrderTo seckillOrder, Channel channel, Message message) throws IOException {
        try{
            log.info("准备创建秒杀单的详细信息。。。");
            orderService.createSeckillOrder(seckillOrder);
            //手动调用支付宝收单；
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (Exception e){
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }

    }
}
```

`OrderService`：秒杀成功后创建订单

```java
public interface OrderService extends IService<OrderEntity> {
    void createSeckillOrder(SeckillOrderTo seckillOrder);
}
```

`OrderServiceImpl`：秒杀成功后创建订单

```java
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Override
    public void createSeckillOrder(SeckillOrderTo seckillOrder) {
        //TODO 保存订单信息
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderSn(seckillOrder.getOrderSn());
        orderEntity.setMemberId(seckillOrder.getMemberId());

        orderEntity.setStatus(OrderStatusEnum.CREATE_NEW.getCode());

        BigDecimal multiply = seckillOrder.getSeckillPrice().multiply(new BigDecimal("" + seckillOrder.getNum()));
        orderEntity.setPayAmount(multiply);
        this.save(orderEntity);

        //TODO 保存订单项信息
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setOrderSn(seckillOrder.getOrderSn());
        orderItemEntity.setRealAmount(multiply);
        //TODO 获取当前SKU的详细信息进行设置  productFeignService.getSpuInfoBySkuId()
        orderItemEntity.setSkuQuantity(seckillOrder.getNum());

        orderItemService.save(orderItemEntity);
    }
}
```

#####  10.5.4 秒杀页面渲染

`success.html`：

```js
<div class="success-wrap">
    <div class="w" id="result">
        <div class="m succeed-box">

            <div th:if="${orderSn != null}" class="mc success-cont">
                <h1>恭喜，秒杀成功，订单号[[${orderSn}]]</h1>
                <h2>正在准备订单数据，10s以后自动跳转支付 <a style="color: red" th:href="${'http://order.gulimall.com/payOrder?orderSn='+orderSn}">去支付</a></h2>

            </div>
            <div th:if="${orderSn == null}">
                <h1>手气不好，秒杀失败，下次再来</h1>
            </div>
        </div>
    </div>
</div>
```

测试：秒杀成功后跳转到秒杀成功页，点击去支付进行后续支付操作

![image-20231001145221176](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/image-20231001145221176.png)

