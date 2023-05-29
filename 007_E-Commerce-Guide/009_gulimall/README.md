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

	<table align="center">
	<tr>
		<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300308026.png" > <b>nginx动静分离</b></td>
		<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300310590.png" > <b>Nginx转发效果</b></td>
	</tr>
	</table>  

在`/mydata/nginx/html/`目录下新建`static`目录，并将`gulimall-product`商品服务下的`resources\index`文件(静态资源)上传到虚拟机的`/mydata/nginx/html/static`目录下：

	<table align="center">
	<tr>
		<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300308646.png" > <b>1</b></td>
		<td ><img src="https://cdn.jsdelivr.net/gh/Li-ShiLin/images/D:%5Cgithub%5Cimages202305300309185.png" > <b>2</b></td>
	</tr>
	</table>  

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

###  3.4 缓存穿透

**高并发下缓存失效问题：缓存穿透**

- 缓存穿透：指查询一个一定不存在的数据，由于缓存是不命中，将去查询数据库，但是数据库也无此记录，我们没有将这次查询的null写入缓存，这将导致这个不存在的数据每次请求都要到存储层去查询，失去了缓存的意义

**风险**:

- 利用不存在的数据进行攻击，数据库瞬时压力增大，最终导致崩溃

**解决** :

- null结果缓存，并加入短暂过期时间

### 3.5 缓存雪崩

**高并发下缓存失效问题-缓存雪崩**

- 缓存雪崩是指在我们设置缓存时key采用了相同的过期时间，导致缓存在某一时刻同时失效，请求全部转发到DB，DB瞬时压力过重雪崩

**解决**:

- 原有的失效时间基础上增加一个随机值，比如1-5分钟随机，这样每-个缓存的过期时间的重复率就会降低，就很难引发集体失效的事件

###  3.6 缓存击穿

**高并发下缓存失效问题-缓存击穿**

- 对于一些设置了过期时间的key,如果这些key可能会在某些时间点被超高并发地访问，是一种非常"热点"的数据。
- 如果这个key在大量请求同时进来前正好失效，那么所有对这个key的数据查询都落到db,我们称为缓存击穿。

**解决**:  加锁

- 大量并发只让一个去查，其他人等待，查到以后释放锁，其他人获取到锁，先查缓存,就会有数据，不用去db





```
 7、整合redisson作为分布式锁等功能框架
       1）、引入依赖
            <dependency>
                    <groupId>org.redisson</groupId>
                     <artifactId>redisson</artifactId>
                     <version>3.12.0</version>
             </dependency>
       2）、配置redisson
              MyRedissonConfig给容器中配置一个RedissonClient实例即可
       3）、使用
               参照文档做
```

