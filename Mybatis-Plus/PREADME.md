##   主要内容 & 代码目录

![image-20231005020517859](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310070119928.png)

`001_mybatisplus`模块对应 1-4节内�?

`002_mybatisplus`模块对应5-10节内�?

`003_mybatis_plus_datasource`模块对应11节内�?

`004_mybatisx_demo`模块对应12节内�?

## 1.MyBatis-Plus概述

#####  1.1 �?�?

MyBatis-Plus是一个MyBatis的增强工具，在MyBatis的基�?上只做增强不做改变，为简化开发�?�提高效率�?�生。MyBatis-Plus提供了�?�用的mapper和service，可以在不编写任何SQL语句的情况下，快速的实现对单表的CRUD、批量�?��?�辑删除、分页等操作。本视频从MyBatis-Plus的特性及使用，到MyBatis-Plus�?提供的优�?的插件，以及多数据源的配置都有详细的讲解。并对ldea中的快�?�开发插件MyBatisX也进行了功能的演�?



MyBatis-Plus优势概括�?

- **只做增强不做改变**，引入它不会对现有工程产生影响，如丝般顺�?
- 只需�?单配置，即可快�?�进行单表CRUD操作，从而节省大呈时�?
- 代码生成、自动分页�?��?�辑删除、自动填充等功能—应俱全

#####  1.2 特�??

MyBatis-Plus特�??

- **无侵�?**：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
- **损�?�小**：启动即会自动注入基�? CURD，�?�能基本无损耗，直接面向对象操作
- **强大的CRUD操作**：内置�?�用Mapper、�?�用Service，仅仅�?�过少量配置即可实现单表大部分CRUD操作，更有强大的条件构�?�器，满足各类使用需�?
- **支持Lambda形式调用**：�?�过Lambda表达式，方便的编写各类查询条件，无需再担心字段写�?
- **支持主键自动生成**：支持多�?4种主键策�?(内含分布式唯�?ID生成�?-Sequence)，可自由配置，完美解决主键问�?
- **支持ActiveRecord模式**：支持ActiveRecord形式调用，实体类只需继承Model类即可进行强大的CRUD操作
- **支持自定义全�?通用操作**：支持全�?通用方法注入( Write once, use anywhere )
- **内置代码生成�?**：采用代码或者Maven插件可快速生成Mapper 、Model、Service、Controller层代码，支持模板引擎，更有超多自定义配置等您来使�?
- **内置分页插件**：基于MyBatis物理分页，开发�?�无�?关心具体操作，配置好插件之后，写分页等同于普通List查询
- **分页插件支持多种数据�?**：支持MySQL,MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer等多种数据库
- **内置性能分析插件**：可输出SQL语句以及其执行时间，建议�?发测试时启用该功能，能快速揪出慢查询
- **内置全局拦截插件**：提供全表delete、update操作智能分析阻断，也可自定义拦截规则，预防误操作

#####  1.3 支持的数据库

MyBatis-Plus支持的数据库

- 任何能使用AyBatis进行CRUD,并且支持标准SQL的数据库，具体支持情况如下，如果不在下列表查看分页部分教程PR您的支持
- MySQL，Oracle，DB2，H2，HSQL，SQLite，PostgreSQL，SQLServer，Phoenix，Gauss，ClickHouse，Sybase,OceanBase，Firebird，Cubrid，Goldilocks，csiidb，informix，TDengine，redshift
- 达梦数据库，虚谷数据库，人大金仓数据库，南大通用(华库)数据库，南大通用数据库，神�?�数据库，�?�高数据库，优炫数据库，星瑞格数据库

#####  1.4 框架结构

MyBatis-Plus框架结构�?

`Scan Entity`：扫描数据库表对应的实体�?

`Reflection|extraction`：�?�过反射�?术来抽取实体类中的属�?

`Analysis TableName Column`：分析表和实体类之间的关系�?�分析实体类的属性与数据库字段之间的关系

`SQL: Insert Update Delete select`：根据调用的方法来生成对应的sql语句

`lnjection Mybatis Container`：将增删改查功能注入到容器中

![image-20231005022236203](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310070120167.png)

#####  1.5 代码及文档地�?

官方文档地址: `http://mp.baomidou.com`

代码发布地址: 

- Github: `https://github.com/baomidou/mybatis-plus`

- Gitee: `https://gitee.com/baomidou/mybatis-plus`


文档发布地址: `https://baomidou.com/pages/24112f/`

## 2.springboot整合mybatis_plus

�?、开发环�?

```
IDE: idea 2019.2
JDK: JDK8+
构建工具: maven 3.5.4
MySQL版本:MySQL 5.8 
Spring Boot: 2.6.3
MyBatis-Plus: 3.5.1
```

二�?�创建数据库及表

```sql
# 创建数据库mybatis_plus
CREATE DATABASE `mybatis_plus` CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;
USE `mybatis_plus`;

# 创建数据表user
CREATE TABLE `user` (
`id` BIGINT(20) NOT NULL COMMENT '主键ID',
`name` VARCHAR(30) DEFAULT NULL COMMENT '姓名',
`age` INT(11) DEFAULT NULL COMMENT '年龄',
`email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

# 插入数据
INSERT INTO USER (id, NAME, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```

三�?�`pom.xml`：引入依�?

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!--mybatis-plus启动�?-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.5.1</version>
        </dependency>

        <!--lombok用于�?化实体类�?�?-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!--mysql驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
```

四�?�`application.yml`：数据源相关配置

```yaml
spring:
  # 配置数据源信�?
  datasource:
    # 配置数据源类�?
    type: com.zaxxer.hikari.HikariDataSource
    # 配置连接数据库的各个信息
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://192.168.56.10:3306/mybatis_plus?characterEncoding=utf-8&userSSL=false
    username: root
    password: root


mybatis-plus:
  configuration:
    # 日志配置
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

配置注意事项�?

```
1.驱动类driver-class-name
       1) spring boot 2.0（内置jdbc5驱动），驱动类使用：driver-class-name: com.mysql.jdbc.Driver
       2) spring boot 2.1及以上（内置jdbc8驱动），驱动类使用：driver-class-name: com.mysql.cj.jdbc.Driver
       3) 否则运行测试用例的时候会�? WARN 信息

2.连接地址url

    1) MySQL5.7版本的url：jdbc:mysql://localhost:3306/mybatis_plus?characterEncoding=utf-8&useSSL=false
    2) MySQL8.0版本的url：jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false
    3) MySQL8.0版本的url要指定时区serverTimezone,否则运行测试用例报告如下错误�?
        java.sql.SQLException: The server time zone value 'ÖÐ¹ú±ê×¼Ê±¼ä' is unrecognized or
        represents more
```

添加数据库表`user`对应的实体类User

```java
@Data //lombok注解
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```

在Spring Boot启动类中添加`@MapperScan`注解，扫描mapper包�?�在启动类上添加了`@MapperScan`注解以后就可以将指定包下�?有的mapper接口�?动�?�生成的代理类交给IOC容器来管�?

```java
@SpringBootApplication
@MapperScan("com.atguigu.mybatisplus.mapper")
public class MybatisplusApplication {
public static void main(String[] args) {
SpringApplication.run(MybatisplusApplication.class, args);
}
}
```

在`com.atguigu.mybatisplus.mapper`包下添加`UserMapper`接口。BaseMapper是MyBatis-Plus提供的模板mapper，其中包含了基本的CRUD方法，泛型为操作�? 实体类型

```java
@Repository
public interface UserMapper extends BaseMapper<User> {

}
```

测试�?

```java
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    // SELECT id,name,age,email FROM user
    @Test
    public void testSelectList() {
        // selectList()根据MP内置的条件构造器查询�?个list集合，null表示没有条件，即查询�?�?
        // 通过条件构�?�器查询�?个list集合，若没有条件，则可以设置null为参�?
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }
}
```

## 3.基本增删改查：BaseMapper的使�?

#####  3.1 内置的BaseMapper

MyBatis-Plus中的基本CRUD在内置的BaseMapper中都已得到了实现，我们可以直接使用，接口�? 下：

```java
/**
 * Mapper 继承该接口后，无�?编写 mapper.xml 文件，即可获得CRUD功能
 * <p>这个 Mapper 支持 id 泛型</p>
 *
 * @author hubin
 * @since 2016-01-23
 */
public interface BaseMapper<T> extends Mapper<T> {

    /**
     * 插入�?条记�?
     *
     * @param entity 实体对象
     */
    int insert(T entity);

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    int deleteById(Serializable id);

    /**
     * 根据实体(ID)删除
     *
     * @param entity 实体对象
     * @since 3.4.4
     */
    int deleteById(T entity);

    /**
     * 根据 columnMap 条件，删除记�?
     *
     * @param columnMap 表字�? map 对象
     */
    int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    /**
     * 根据 entity 条件，删除记�?
     *
     * @param queryWrapper 实体对象封装操作类（可以�? null,里面�? entity 用于生成 where 语句�?
     */
    int delete(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 删除（根据ID或实�? 批量删除�?
     *
     * @param idList 主键ID列表或实体列�?(不能�? null 以及 empty)
     */
    int deleteBatchIds(@Param(Constants.COLLECTION) Collection<?> idList);

    /**
     * 根据 ID 修改
     *
     * @param entity 实体对象
     */
    int updateById(@Param(Constants.ENTITY) T entity);

    /**
     * 根据 whereEntity 条件，更新记�?
     *
     * @param entity        实体对象 (set 条件�?,可以�? null)
     * @param updateWrapper 实体对象封装操作类（可以�? null,里面�? entity 用于生成 where 语句�?
     */
    int update(@Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper);

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    T selectById(Serializable id);

    /**
     * 查询（根据ID 批量查询�?
     *
     * @param idList 主键ID列表(不能�? null 以及 empty)
     */
    List<T> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);

    /**
     * 查询（根�? columnMap 条件�?
     *
     * @param columnMap 表字�? map 对象
     */
    List<T> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    /**
     * 根据 entity 条件，查询一条记�?
     * <p>查询�?条记录，例如 qw.last("limit 1") 限制取一条记�?, 注意：多条数据会报异�?</p>
     *
     * @param queryWrapper 实体对象封装操作类（可以�? null�?
     */
    default T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper) {
        List<T> ts = this.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(ts)) {
            if (ts.size() != 1) {
                throw ExceptionUtils.mpe("One record is expected, but the query result is multiple records");
            }
            return ts.get(0);
        }
        return null;
    }

    /**
     * 根据 Wrapper 条件，判断是否存在记�?
     *
     * @param queryWrapper 实体对象封装操作�?
     * @return
     */
    default boolean exists(Wrapper<T> queryWrapper) {
        Long count = this.selectCount(queryWrapper);
        return null != count && count > 0;
    }

    /**
     * 根据 Wrapper 条件，查询�?�记录数
     *
     * @param queryWrapper 实体对象封装操作类（可以�? null�?
     */
    Long selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 entity 条件，查询全部记�?
     *
     * @param queryWrapper 实体对象封装操作类（可以�? null�?
     */
    List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询全部记�?
     *
     * @param queryWrapper 实体对象封装操作类（可以�? null�?
     */
    List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询全部记�?
     * <p>注意�? 只返回第�?个字段的�?</p>
     *
     * @param queryWrapper 实体对象封装操作类（可以�? null�?
     */
    List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 entity 条件，查询全部记录（并翻页）
     *
     * @param page         分页查询条件（可以为 RowBounds.DEFAULT�?
     * @param queryWrapper 实体对象封装操作类（可以�? null�?
     */
    <P extends IPage<T>> P selectPage(P page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    /**
     * 根据 Wrapper 条件，查询全部记录（并翻页）
     *
     * @param page         分页查询条件
     * @param queryWrapper 实体对象封装操作�?
     */
    <P extends IPage<Map<String, Object>>> P selectMapsPage(P page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
```

通过观察BaseMapper中的方法，大多方法中都有Wrapper类型的形参，此为条件构�?�器，可�?
对于SQL语句设置不同的条件，若没有条件，则可以为该形参赋值null，即查询（删�?/修改）所
有数�?

#####  3.2 新增数据：insert

`INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )`

```java
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;
    // 新增数据
    // INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        user.setEmail("zhangsan@atguigu.com");

        int result = userMapper.insert(user);
        System.out.println("受影响行数：" + result);
        System.out.println("id自动获取�?" + user.getId());
        /*
                受影响行数：1
                id自动获取�?1709849120311668738
         */
    }
}
```

�?获取的id�?1709849120311668738 这是因为MyBatis-Plus在实现插入数据时，会默认基于雪花算法的策略生成id

#####  3.3 删除数据：deleteById | deleteBatchIds | deleteByMap

 **deleteById**：�?�过id删除

```java
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;
    // 通过id删除用户信息
    // DELETE FROM user WHERE id=?
    @Test
    public void testDeleteById() {
        int result = userMapper.deleteById(1709849120311668738L);
        System.out.println("受影响行数：" + result);
        // 受影响行数：1
    }
}
```

**deleteBatchIds**：�?�过多个id批量删除

```java
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;
    // 通过多个id批量删除
    // DELETE FROM user WHERE id IN ( ? , ? , ? )
    @Test
    public void testDeleteBatchIds() {
        List<Long> idList = Arrays.asList(1L, 2L, 3L);
        int result = userMapper.deleteBatchIds(idList);
        System.out.println("受影响行数：" + result);
        // 受影响行数：3
    }
}
```

**deleteByMap**：根据map集合中所设置的条件删�?

```java
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;
    
    // 根据map集合中所设置的条件删除用户信�?
    // DELETE FROM user WHERE name = ? AND age = ?
    @Test
    public void testDeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 23);
        int result = userMapper.deleteByMap(map);
        System.out.println("result:" + result); // result:2
    }
}
```

##### 3.4 修改数据：updateById

**updateById**：�?�过id进行修改

```java
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;
    // 通过id进行修改
    // UPDATE user SET name=?, email=? WHERE id=?
    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(5L);
        user.setName("李四");
        user.setEmail("zhangsan@qq.com");
        int result = userMapper.updateById(user);
        System.out.println("受影响行数：" + result);
    }
}
```

#####  3.5 查询数据：selectById | selectBatchIds | selectByMap | selectList

**selectById**：�?�过id查询数据

```java
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;
    
    // 根据id查询用户信息
    // SELECT id,name,age,email FROM user WHERE id=?
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(5L);
        System.out.println(user); // User(id=5, name=李四, age=24, email=zhangsan@qq.com)
    }
}
```

**selectBatchIds**：根据多个id查询多条数据

```java
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;
    //根据多个id查询多个用户信息
    //SELECT id,name,age,email FROM user WHERE id IN ( ? , ? )
    @Test
    public void testSelectBatchIds() {
        List<Long> idList = Arrays.asList(4L, 5L);
        List<User> list = userMapper.selectBatchIds(idList);
        list.forEach(System.out::println);
    }
}
```

**selectByMap**：�?�过map条件查询

```java
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;
    // 通过map条件查询用户信息
    // SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
    @Test
    public void testSelectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 22);
        map.put("name", "admin");
        List<User> list = userMapper.selectByMap(map);
        list.forEach(System.out::println);
    }
}
```

**selectList**：查询数据库表中的所有数�?

```java
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;
    // 查询�?有用户信�?
    // SELECT id,name,age,email FROM user
    @Test
    public void testSelectListt() {
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }
}
```

#####  3.6 自定义sql查询

`application.yml`：配置`mapper`文件位置

```yml
mybatis-plus:
  configuration:
  # 日志配置
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  # mapper文件的默认位置就是classpath*:/mapper/**/*.xml
  mapper-locations: classpath*:/mapper/**/*.xml
```

`UserMapper`：数据库操作接口定义

```java
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据id查询用户信息为map集合
     */
    Map<String, Object> selectMapById(Long id);
}
```

`src/main/resources/mapper/UserMapper.xml`：自定义sql

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.atguigu.mybatisplus.mapper.UserMapper">

    <!--Map<String, Object> selectMapById(Long id);-->
    <select id="selectMapById" resultType="map">
        select id,name,age,email from user where id = #{id}
    </select>
</mapper>
```

测试�?

```java
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;
   // 自定义sql：根据id查询用户信息为map集合
   // select id,name,age,email from user where id = ?
   @Test
   public void testSelfDefine() {
       Map<String, Object> map = userMapper.selectMapById(1L);
       System.out.println(map);
       // {name=Jone, id=1, age=18, email=test1@baomidou.com}
   }
}
```

## 4. 通用Service 

#####  4.1 通用Service 概述

**Service CRUD接口**�?

- 通用 Service CRUD 封装`IService`接口，进�?步封�? CRUD操作
- 采用 `get 查询单行`�? `remove 删除`�? `list 查询集合` 、`page 分页` 前缀命名方式区分 `Mapper` 层避免混�?
- 泛型 `T` 为任意实体对�?
- 建议如果存在自定义�?�用 Service 方法的可能，请创建自己的 `IBaseService` 继承 `Mybatis-Plus` 提供的基�?
- 对象 `Wrapper` �? `条件构�?�器`

**IService和ServiceImpl**�?

- MyBatis-Plus中有�?个接口IService和其实现�? ServiceImpl，封装了常见的业务层逻辑 详情查看源码IService和ServiceImpl

官方文档：`https://baomidou.com/pages/49cc81/#service-crud-接口`

#####  4.2 IService和ServiceImpl使用

`UserService`�?

```java
/**
 * UserService继承IService模板提供的基�?功能
 */
public interface UserService extends IService<User> {
}
```

`UserServiceImpl`�?

```java
/**
 * ServiceImpl实现了IService，提供了IService中基�?功能的实�?
 * 若ServiceImpl无法满足业务�?求，则可以使用自定的UserService定义方法，并在实现类中实�?
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
```

**count**：查询�?�记录数

```java
@SpringBootTest
public class MyBatisPlusServiceTest {

    @Autowired
    private UserService userService;

    //查询总记录数
    //SELECT COUNT( * ) FROM user
    @Test
    public void testGetCount(){
        long count = userService.count();
        System.out.println("总记录数�?"+count);
    }
}
```

**saveBatch**：批量添�?

```java
@SpringBootTest
public class MyBatisPlusServiceTest {
    @Autowired
    private UserService userService;
    // 批量添加
    // INSERT INTO user ( id, name, age ) VALUES ( ?, ?, ? )
    @Test
    public void testInsertMore(){
        List<User> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setName("ybc"+i);
            user.setAge(20+i);
            list.add(user);
        }
        boolean b = userService.saveBatch(list);
        System.out.println(b);
    }
}
```



## 5.常用注解

#####  5.1 @TableName

**�?、类名和数据库表的表名不�?致问�?**

MyBatis-Plus在确定操作的表时，由BaseMapper的泛型决定，即实体类型决定，且默认操作的表名和实体类型的类名�?�?

若实体类类型的类名和要操作的表的表名不一致，会出现什么问题？

我们将表user更名为t_user，测试查询功�? 程序抛出异常，Table 'mybatis_plus.user' doesn't exist，因为现在的表名为t_user，�?�默认操�? 的表名和实体类型的类名一致，即user�?

```sql
USE `mybatis_plus`;

CREATE TABLE `t_user` (
`uid` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
`user_name` VARCHAR(30) DEFAULT NULL COMMENT '姓名',
`age` INT(11) DEFAULT NULL COMMENT '年龄',
`email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
`is_deleted` INT(11) DEFAULT '0' COMMENT '是否被删�?'
PRIMARY KEY (`uid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


INSERT INTO `t_user` (user_name, age, email) VALUES
('Jone', 18, 'test1@baomidou.com'),
('Jack', 20, 'test2@baomidou.com'),
('Tom', 28, 'test3@baomidou.com'),
('Sandy', 21, 'test4@baomidou.com'),
('Billie', 24, 'test5@baomidou.com');
```

**二�?��?�过@TableName解决问题**

在实体类类型上添加@TableName("t_user")，标识实体类对应的表，即可成功执行SQL语句

```java
@Data //lombok注解
@TableName("t_user") // 设置实体类所对应的表�?
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```

**三�?��?�过全局配置解决问题**

在开发的过程中，可�?�实体类�?对应的表都有固定的前�?，例如`t_`�? `tbl_` 此时，可以使用MyBatis-Plus提供的全�?配置，为实体类所对应的表名设置默认的前缀，那么就 不需要在每个实体类上通过@TableName标识实体类对应的�?

`application.yml`：设置实体类�?对应的表的统�?前缀

```yml
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  # 设置MyBatis-Plus的全�?配置
  global-config:
    db-config:
      # 设置实体类所对应的表的统�?前缀
      table-prefix: t_
```

**四�?�测试发现sql操作的数据库表变成`t_user`**

```java
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;
    // 通过map条件查询用户信息
    // SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE user_name = ? AND age = ? AND is_deleted=0
    @Test
    public void testSelectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 22);
        map.put("user_name", "admin");
        List<User> list = userMapper.selectByMap(map);
        list.forEach(System.out::println);
    }
}
```

#####  5.2 @TableId

**�?、使用`@TableId`将属性所对应的字段指定为主键**

MyBatis-Plus在实现CRUD时，会默认将id作为主键列，并在插入数据时，默认 基于雪花算法的策略生成id

若实体类和表中表示主键的不是id，�?�是其他字段，例如uid，那MyBatis-Plus不会自动识别uid为键列，就会出现程序异常

在实体类中uid属�?�上通过@TableId将其标识为主键，即可成功执行SQL语句

```java
@Data //lombok注解
@TableName("t_user") // 设置实体类所对应的表�?
public class User {

    //将属性所对应的字段指定为主键
    //@TableId注解的value属�?�用于指定主键的字段
    //@TableId注解的type属�?�设置主键生成策�?
    @TableId("uid")   // 也可以写成@TableId(value = "uid")
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```

测试�?

```java
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    // 通过map条件查询用户信息
    // SELECT uid AS id,name,age,email FROM t_user WHERE name = ? AND age = ?
    @Test
    public void testSelectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 22);
        map.put("name", "admin");
        List<User> list = userMapper.selectByMap(map);
        list.forEach(System.out::println);
    }
}
```

**二�?�@TableId的type属�??**

type属�?�用来定义主键策略常用的主键策略�?

| �?                     | 描述                                                         |
| ---------------------- | ------------------------------------------------------------ |
| ldType.ASSIGN_ID(默认) | 基于雪花算法的策略生成数据id，与数据库id是否设置自增无关     |
| ldType.AUTO            | 使用数据库的自增策略。注意，该类型请确保数据库设置了id自增，否则无�? |

`User`：`@TableId`注解的type属�?�设置主键生成策�?

```java
@Data //lombok注解
@TableName("t_user") // 设置实体类所对应的表�?
public class User {
    
    //@TableId注解的type属�?�设置主键生成策�?
    @TableId(value = "uid", type = IdType.AUTO)
    private Long id;

    private String name;
    private Integer age;
    private String email;
}
```

测试�?

```java
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;
    // 新增数据
    // INSERT INTO t_user ( name, age, email ) VALUES ( ?, ?, ? )
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        user.setEmail("zhangsan@atguigu.com");

        int result = userMapper.insert(user);
        System.out.println("受影响行数：" + result);
        System.out.println("id自动获取�?" + user.getId());
        /*
                受影响行数：1
                id自动获取�?6
         */
    }
}
```

**三�?��?�过配置文件全局设置主键生成策略**

`application.yml`：全�?设置主键生成策略

```yml
mybatis-plus:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  # 设置MyBatis-Plus的全�?配置
  global-config:
    db-config:
      # 设置统一的主键生成策�?
      id-type: auto
```

#####  5.3 @TableField

若实体类中的属�?�使用的是驼峰命名风格，而表中的字段使用的是下划线命名风�? 例如实体类属性userName，表中字段user_name 此时MyBatis-Plus会自动将下划线命名风格转化为驼峰命名风格 相当于在MyBatis中配�?

若实体类中的属�?�和表中的字段不满足情况1 例如实体类属性name，表中字段username 此时�?要在实体类属性上使用@TableField("username")设置属�?�所对应的字段名

`@TableField`：指定属性所对应的字段名

```java
@Data
//设置实体类所对应的表�?
@TableName("t_user")
public class User {

    //将属性所对应的字段指定为主键
    @TableId(value = "uid", type = IdType.AUTO)
    private Long id;

    // 指定属�?�所对应的字段名
    @TableField("user_name")
    private String name;

    private Integer age;

    private String email;

    private SexEnum sex;
}
```

#####  5.4 @TableLogic：�?�辑删除

物理删除：真实删除，将对应数据从数据库中删除，之后查询不到此条被删除的数�?

逻辑删除：假删除，将对应数据中代表是否被删除字段的状态修改为"被删除状�?"，之后在数据库中仍旧能看到此条数据记�?

使用场景：可以进行数据恢�?

`User`实体类：

```java
@Data
//设置实体类所对应的表�?
@TableName("t_user")
public class User {
    //将属性所对应的字段指定为主键
    @TableId(value = "uid", type = IdType.AUTO)
    private Long id;
    
    // 指定属�?�所对应的字段名
    @TableField("user_name")
    private String name;
    
    private Integer age;
    
    private String email;
    
    @TableLogic
    private Integer isDeleted;
}
```

测试�?

```java
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    // 通过多个id批量删除（�?�辑删除�?
    // UPDATE t_user SET is_deleted=1 WHERE uid IN ( ? , ? , ? ) AND is_deleted=0
    @Test
    public void testDeleteBatchIds() {
        List<Long> idList = Arrays.asList(1L, 2L, 3L);
        int result = userMapper.deleteBatchIds(idList);
        System.out.println("受影响行数：" + result);
        // 受影响行数：3
    }

    // 通过map条件查询用户信息
    // SELECT uid AS id,name,age,email,is_deleted FROM t_user WHERE name = ? AND age = ? AND is_deleted=0
    @Test
    public void testSelectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", 22);
        map.put("name", "admin");
        List<User> list = userMapper.selectByMap(map);
        list.forEach(System.out::println);
    }
}
```

```
测试删除功能，真正执行的是修�?
UPDATE t_user SET is_deleted=1 WHERE uid IN ( ? , ? , ? ) AND is_deleted=0

测试查询功能，被逻辑删除的数据默认不会被查询
SELECT uid AS id,name,age,email,is_deleted FROM t_user WHERE name = ? AND age = ? AND is_deleted=0
```



#####  5.5 雪花算法

**雪花算法背景**�?

- �?要�?�择合�?�的方案去应对数据规模的增长，以应对逐渐增长的访问压力和数据�?

- 数据库的扩展方式主要包括：业务分库�?�主从复制，数据库分�?

**数据库分�?**�?

- 将不同业务数据分散存储到不同的数据库服务器，能够支撑百万甚至千万用户规模的业务，但如果业务继续发展，同一业务的单表数据也会达到单台数据库服务器的处理瓶颈。例如，淘宝的几亿用户数据，如果全部存放在一台数据库服务器的�?张表中，肯定是无法满足�?�能要求的，此时就需要对单表数据进行拆分

- 单表数据拆分有两种方式：垂直分表和水平分表�?�示意图如下�?

![image-20231005223639525](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310070120858.png)

**垂直分表**�?

- 垂直分表适合将表中某些不常用且占了大量空间的列拆分出�?

- 例如，前面示意图中的nickname和description字段，假设我们是�?个婚恋网站，用户在筛选其他用户的时�?��?�主要是用age和sex两个字段进行查询，�?�nickname和description两个字段主要用于展示，一般不会在业务查询中用到�?�description本身又比较长，因此我们可以将这两个字段独立到另外�?张表中，这样在查询age和sex时，就能带来—定的�?�能提升

**水平分表**�?

- 水平分表适合表行数特别大的表，有的公司要求单表行数超�?5000万就必须进行分表，这个数字可以作为参考，但并不是绝对标准，关键还是要看表的访问�?�能。对于一些比较复杂的表，可能超过1000万就要分表了。�?�对于一些简单的表，即使存储数据超过1亿行，也可以不分�?

- 但不管�?�样，当看到表的数据量达到千万级别时，作为架构师就要警觉起来，因为这很可能是架构的�?�能瓶颈或�?�隐�?

- 水平分表相比垂直分表，会引入更多的复杂�?�，例如要求全局唯一的数据id该如何处理？
- **生成全局唯一id的方式：主键自增、取模�?�雪花算�?**

- **主键自增**�?

  - 以最常见的用户ID为例，可以按�?1000000的范围大小进行分段，1~999999放到�?1中，1000000 ~1999999放到�?2中，以此类推

  - 复杂点：分段大小的�?�取。分段太小会导致切分后子表数量过多，增加维护复杂�?;分段太大可能会导致单表依然存在�?�能问题，一般建议分段大小在100万至2000万之间，具体�?要根据业务�?�取合�?�的分段大小

  - 优点：可以随�?数据的增加平滑地扩充新的表�?�例如，现在的用户是100万，如果增加�?1000万，只需要增加新的表就可以了，原有的数据不需要动

  - 缺点：分布不均匀。假如按�?1000万来进行分表，有可能某个分段实际存储的数据量只有1条，而另外一个分段实际存储的数据量有1000万条

- **取模**�?

  - 同样以用户ID为例，假如我们一�?始就规划�?10个数据库表，可以�?单地用user_id %10的�?�来表示数据�?属的数据库表编号，ID�?985的用户放到编号为5的子表中，ID�?10086的用户放到编号为6的子表中

  - 复杂点：初始表数量的确定。表数量太多维护比较麻烦，表数量太少又可能导致单表�?�能存在问题

  - 优点：表分布比较均匀

  - 缺点：扩充新的表很麻烦，�?有数据都要重分布

- **雪花算法**�?

  - 雪花算法是由Twitter公布�?**分布式主键生成算�?**，它能够保证不同表的主键的不重复性，以及相同表的主键的有序�??

  - 优点：整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞，并且效率较�?

  - 核心思想�?

    - 长度�?64bit (—个long�?)

    - 首先是一个符号位�?1bit标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id�?般是正数，最高位�?0

    - 41bit时间�?(毫秒�?)，存储的是时间截的差�?(当前时间�?-�?始时间截)，结果约等于69.73年�??10bit作为机器的ID(5个bit是数据中心，5个bit的机器ID，可以部署在1024个节�?)

    - 12bit作为毫秒内的流水�?(意味�?每个节点在每毫秒可以产生4096个ID)

![image-20231006020306938](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310070120510.png)

##  6.条件构�?�器和常用接�?

###  6.1 wapper继承关系

Wrapper：条件构造抽象类，最顶端父类

- AbstractWrapper : 用于查询条件封装，生成sql的where条件

  - QueryWrapper：查询条件封�?

  - UpdateWrapper : Update条件封装

  - AbstractLambdaWrapper：使用Lambda语法

    - LambdaQueryWrapper：用于Lambda语法使用的查�?

    - WrapperLambdaUpdateWrapper : Lambda更新封装Wrapper

![image-20231006024722405](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310070121916.png)

###  6.2 QueryWrapper使用

#####  �?1:组装查询条件

```java
@SpringBootTest
public class MyBatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;
    //查询用户名包含a，年龄在20�?30之间，邮箱信息不为null的用户信�?
    //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (user_name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
    @Test
    public void test01() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name", "a").between("age", 20, 30).isNotNull("email");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
}
```

##### �?2:组装排序条件

```java
@SpringBootTest
public class MyBatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;
    // �?2：组装排序条�?
    // 查询用户信息，按照年龄的降序排序，若年龄相同，则按照id升序排序
    // SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY age DESC,uid ASC
    @Test
    public void test02() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("uid");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
    
}
```

##### �?3:组装删除条件

```java
@SpringBootTest
public class MyBatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;
    
    // �?3：组装删除条�?
    // 删除邮箱地址为null的用户信�?
    // UPDATE t_user SET is_deleted=1 WHERE is_deleted=0 AND (email IS NULL)
    @Test
    public void test03() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("result:" + result);
    }
}
```

#####  �?4:条件的优先级

```java
@SpringBootTest
public class MyBatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;

    // �?4：条件的优先�?
    //将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息修�?
    //UPDATE t_user SET user_name=?, email=? WHERE is_deleted=0 AND (age > ? AND user_name LIKE ? OR email IS NULL)
    @Test
    public void test04(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 20)
                .like("user_name", "a")
                .or()
                .isNull("email");
        User user = new User();
        user.setName("小明");
        user.setEmail("test@atguigu.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result:"+result);
    }
    
}
```

##### �?5:条件的优先级

```java
@SpringBootTest
public class MyBatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;
    
    // �?5：条件的优先�?
    //将用户名中包含有a并且（年龄大�?20或邮箱为null）的用户信息修改
    //lambda中的条件优先执行
    //UPDATE t_user SET user_name=?, email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
    @Test
    public void test05() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name", "a").and(i -> i.gt("age", 20).or().isNull("email"));
        User user = new User();
        user.setName("小红");
        user.setEmail("test@atguigu.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result:" + result);
    }
}
```

#####  �?6:组装select子句

```java
@SpringBootTest
public class MyBatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;

    // �?6：组装select子句
    //查询用户的用户名、年龄�?�邮箱信�?
    //SELECT user_name,age,email FROM t_user WHERE is_deleted=0
    @Test
    public void test06(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_name", "age", "email");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }
}
```

#####  �?7:实现子查�?

```java
@SpringBootTest
public class MyBatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;

    // �?7：实现子查询
    //查询id小于等于100的用户信�?
    //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (uid IN (select uid from t_user where uid <= 100))
    @Test
    public void test07() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("uid", "select uid from t_user where uid <= 100");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

}
```

###  6.3 UpdateWrapper的使�?

#####  �?8: UpdateWrapper的使�?

```java
@SpringBootTest
public class MyBatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;
    // �?8: UpdateWrapper的使�?
    //将用户名中包含有a并且（年龄大�?20或邮箱为null）的用户信息修改
    // UPDATE t_user SET user_name=?,email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
    @Test
    public void test08(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("user_name", "a")
                .and(i -> i.gt("age", 20).or().isNull("email"));
        updateWrapper.set("user_name", "小黑").set("email","abc@atguigu.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result�?"+result);
    }
}
```

### 6.4 condition条件组装

#####  �?9:�?般条件组�?

```
在真正开发的过程中，组装条件是常见的功能，�?�这些条件数据来源于用户输入，是可�?�的
因此我们在组装这些条件时，必须先判断用户是否选择了这些条�?
若�?�择则需要组装该条件，若没有选择则一定不能组装，以免影响SQL执行的结�?
```

```java
@SpringBootTest
public class MyBatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;
    //�?9:�?般条件组�?
    //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (user_name LIKE ? AND age <= ?)
    @Test
    public void test09() {
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            //isNotBlank判断某个字符创是否不为空字符串�?�不为null、不为空白符
            queryWrapper.like("user_name", username);
        }
        if (ageBegin != null) {
            queryWrapper.ge("age", ageBegin);
        }
        if (ageEnd != null) {
            queryWrapper.le("age", ageEnd);
        }
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

}
```

#####  �?10:condition条件组装

```
上面的实现方案没有问题，但是代码比较复杂
我们可以使用带condition参数的重载方法构建查询条件，�?化代码的编写
```

```java
@SpringBootTest
public class MyBatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;
    //�?10:condition条件组装
    //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (user_name LIKE ? AND age <= ?)
    @Test
    public void test10() {
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), "user_name", username)
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
    
}
```

###  6.5 LambdaUpdateWrapper使用

```java
@SpringBootTest
public class MyBatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;
    //�?11:LambdaUpdateWrapper使用
    //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (user_name LIKE ? AND age <= ?)
    @Test
    public void test11() {
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), User::getName, username)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
}
```

### 6.6 LambdaUpdateWrapper使用

```java
@SpringBootTest
public class MyBatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;
    //�?12:LambdaUpdateWrapper使用
    //UPDATE t_user SET user_name=?,email=? WHERE is_deleted=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
    @Test
    public void test12() {
        //将用户名中包含有a并且（年龄大�?20或邮箱为null）的用户信息修改
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(User::getName, "a")
                .and(i -> i.gt(User::getAge, 20).or().isNull(User::getEmail));
        updateWrapper.set(User::getName, "小黑").set(User::getEmail, "abc@atguigu.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result�?" + result);
    }
}
```

##  7. MyBatis-Plus插件

#####  7.1 分页插件使用

```
MyBatis Plus自带分页插件，只要简单的配置即可实现分页功能
```

`MyBatisPlusConfig`：配置分页插�?

```java
@Configuration
//扫描mapper接口�?在的�?
@MapperScan("com.atguigu.mybatispuls.mapper")
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //添加分页插件，指定数据库类型
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
```

分页插件使用�?

```java
@SpringBootTest
public class MyBatisPlusPluginsTest {
    @Autowired
    private UserMapper userMapper;

    // MyBatisPlus分页插件
    @Test
    public void testPage() {
        //设置分页参数
        Page<User> page = new Page<>(2, 3);
        userMapper.selectPage(page, null);
        /*控制台sql日志打印�?
         ==> Preparing: SELECT COUNT(*) AS total FROM t_user WHERE is_deleted = 0
         ==> Preparing: SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 LIMIT ?,?
        */
        //获取分页数据
        List<User> list = page.getRecords();
        list.forEach(System.out::println);
        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示的条数：" + page.getSize());
        System.out.println("总记录数�?" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
        System.out.println("是否有上�?页：" + page.hasPrevious());
        System.out.println("是否有下�?页：" + page.hasNext());
        /*
            当前页：2
            每页显示的条数：3
            总记录数�?15
            总页数：5
            是否有上�?页：true
            是否有下�?页：true
         */
    }
}
```

#####  7.2 自定义分页功�?

1.UserMapper中定义接口方�?

```java
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 通过年龄查询用户信息并分�?
     * @param page MyBatis-Plus�?提供的分页对象，必须位于第一个参数的位置
     *             分页对象,xml中可以从里面进行取�??,传�?�参�? Page 即自动分�?,必须放在第一�?
     * @param age 年龄
     * @return
     */
    Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age") Integer age);

}
```

2.`UserMapper.xml`中编写SQL

```xml
    <!--SQL片段，记录基�?字段-->
    <sql id="BaseColumns">uid,user_name,age,email</sql>

    <!--Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age") Integer age);-->
    <select id="selectPageVo" resultType="User">
        select <include refid="BaseColumns"></include> from t_user where age > #{age}
    </select>
```

3.`application.yml`：添加配置`mybatis-plus.type-aliases-package`

`mybatis-plus.type-aliases-package`作用：配置类型别名所对应的包。`UserMapper.xml`中使用`User`类的类型别名`User`，�?�不是用全类名名`com.atguigu.mybatispuls.pojo.User`来指定实体类`User`。添加此配置可以直接用类名代替全类名

```yaml
mybatis-plus:
  # 配置类型别名�?对应的包
  type-aliases-package: com.atguigu.mybatispuls.pojo
  # mapper文件的默认位置就是classpath*:/mapper/**/*.xml
  mapper-locations: classpath*:/mapper/**/*.xml
```

4.自定义分页功能：

```java
@SpringBootTest
public class MyBatisPlusPluginsTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelectPageVo() {
        //设置分页参数
        Page<User> page = new Page<>(2, 3);
        userMapper.selectPageVo(page, 20);
        /*控制台sql日志打印�?
         ==>  Preparing: SELECT COUNT(*) AS total FROM t_user WHERE age > ?
         ==>  Preparing: select uid,user_name,age,email from t_user where age > ? LIMIT ?,?
         */

        //获取分页数据
        List<User> list = page.getRecords();
        list.forEach(System.out::println);
        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示的条数：" + page.getSize());
        System.out.println("总记录数�?" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
        System.out.println("是否有上�?页：" + page.hasPrevious());
        System.out.println("是否有下�?页：" + page.hasNext());
        /*
            当前页：2
            每页显示的条数：3
            总记录数�?12
            总页数：4
            是否有上�?页：true
            是否有下�?页：true
         */
    }
}
```

##  8.Mybatis-Plus实现乐观�?

### 8.1 乐观锁与悲观�?

**数据库操作场�?**�?

—件商品，成本价�?80元，售价�?100元�?��?�板先是通知小李，说你去把商品价格增�?50元�?�小李正在玩游戏，�?�搁了一个小时�?�正好一个小时后，�?�板觉得商品价格增加�?150元，价格太高，可能会影响�?星�?�又通知小王，你把商品价格降�?30�?

此时，小李和小王同时操作商品后台系统。小李操作的时�?�，系统先取出商品价�?100�?;小王也在操作，取出的商品价格也是100元�?�小李将价格加了50元，并将100+50=150元存入了数据�?;小王将商品减�?30元，并将100-30=70元存入了数据库�?�是的，如果没有锁，小李的操作就完全被小王的覆盖�?

现在商品价格�?70元，比成本价�?10元�?�几分钟后，这个商品很快出售�?1千多件商品，老板�?1万多

**乐观锁与悲观�?**�?

上面的故事，**如果是乐观锁**，小王保存价格前，会�?查下价格是否被人修改过了。如果被修改过了，则重新取出的被修改后的价格�?150元，这样他会�?120元存入数据库。乐观锁�?般都是�?�过版本号实�?

**如果是悲观锁**，小李取出数据后，小王只能等小李操作完之后，才能对价格进行操作，也会保证�?终的价格�?120�?

### 8.2 模拟修改冲突

1.数据库中增加商品表�?�添加数据：

```sql
USE `mybatis_plus`;
CREATE TABLE `t_product` (
  `id` BIGINT(20) NOT NULL COMMENT '主键ID',
  `NAME` VARCHAR(30) DEFAULT NULL COMMENT '商品名称',
  `price` INT(11) DEFAULT '0' COMMENT '价格',
  `VERSION` INT(11) DEFAULT '0' COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;


INSERT INTO t_product (id, NAME, price) VALUES (1, '外星人笔记本', 100);
```

2.创建实体类：

```java
@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;
    private Integer version;
}
```

3.添加mapper�?

```java
@Repository
public interface ProductMapper extends BaseMapper<Product> {
}
```

4.模拟修改冲突：在上面的场景中，商品价格预期应该是120，结果价格变成了70

```java
public class MybatisPlusOptimisticTest {
    @Autowired
    private ProductMapper productMapper;

    // 1.模拟修改冲突 (没采用乐观锁之前)
    @Test
    public void testBefore() {
        //小李查询商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李查询的商品价格：" + productLi.getPrice());
        //小王查询商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小王查询的商品价格：" + productWang.getPrice());
        //小李将商品价�?+50
        productLi.setPrice(productLi.getPrice() + 50);
        productMapper.updateById(productLi);
        //小王将商品价�?-30
        productWang.setPrice(productWang.getPrice() - 30);
        productMapper.updateById(productWang);

        //老板查询商品价格
        Product productLaoban = productMapper.selectById(1);
        System.out.println("老板查询的商品价格：" + productLaoban.getPrice());
        // 老板查询的商品价格：70
    }
}
```

###  8.3 Mybatis-Plus实现乐观�?

#####  8.3.1 乐观锁实现流�?

数据库中添加version字段 取出记录时，获取当前version

```sql
SELECT id,`name`,price,`version` FROM product WHERE id=1
```

更新时，version + 1，如果where语句中的version版本不对，则更新失败

```sql
UPDATE product SET price=price+50, `version`=`version` + 1 WHERE id=1 AND `version`=1
```

#####  8.3.2 Mybatis-Plus实现乐观�?

1.修改实体�?

```java
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;
    @Version //标识乐观锁版本号字段
    private Integer version;
}
```

2.添加乐观锁插件配�?

```java
@Configuration
//扫描mapper接口�?在的�?
@MapperScan("com.atguigu.mybatispuls.mapper")
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //添加分页插件，指定数据库类型
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        //添加乐观锁插�?
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }
}
```

3.继续测试：发现由于有了乐观锁，第二次去修改数据的时�?�就不会成功

```java
@SpringBootTest
public class MybatisPlusOptimisticTest {
    @Autowired
    private ProductMapper productMapper;

    // 采用乐观�?
    @Test
    public void testOptimistic() {
        //小李查询商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李查询的商品价格：" + productLi.getPrice());
        //小王查询商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小王查询的商品价格：" + productWang.getPrice());
        //小李将商品价�?+50
        productLi.setPrice(productLi.getPrice() + 50);
        productMapper.updateById(productLi);
        /*控制台sql日志:
            UPDATE t_product SET name=?, price=?, version=? WHERE id=? AND version=?
            ==> Parameters: 外星人笔记本(String), 150(Integer), 2(Integer), 1(Long), 1(Integer)
         */   
        
        
        //小王将商品价�?-30
        productWang.setPrice(productWang.getPrice() - 30);
        productMapper.updateById(productWang);
        /*
        UPDATE t_product SET name=?, price=?, version=? WHERE id=? AND version=?
        ==> Parameters: 外星人笔记本(String), 70(Integer), 2(Integer), 1(Long), 1(Integer)
         */
        
        //老板查询商品价格
        Product productLaoban = productMapper.selectById(1);
        System.out.println("老板查询的商品价格：" + productLaoban.getPrice());
        // 老板查询的商品价格：150
    }
}
```

4.采用乐观锁模拟小王和小李修改商品价格的场景：得到预期的商品价�?120

```java
@SpringBootTest
public class MybatisPlusOptimisticTest {
    @Autowired
    private ProductMapper productMapper;
    
    // 采用乐观锁模拟小王和小李修改商品价格的场�?
    @Test
    public void testMybatisPlusOptimistic() {
        //小李查询商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李查询的商品价格：" + productLi.getPrice());
        //小王查询商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小王查询的商品价格：" + productWang.getPrice());
        //小李将商品价�?+50
        productLi.setPrice(productLi.getPrice() + 50);
        productMapper.updateById(productLi);
        /*控制台sql日志:
            ==>  Preparing: UPDATE t_product SET name=?, price=?, version=? WHERE id=? AND version=?
            ==> Parameters: 外星人笔记本(String), 150(Integer), 3(Integer), 1(Long), 2(Integer)
         */
        
        
        //小王将商品价�?-30
        productWang.setPrice(productWang.getPrice() - 30);
        int result = productMapper.updateById(productWang);
        /*控制台sql日志:
            ==>  Preparing: UPDATE t_product SET name=?, price=?, version=? WHERE id=? AND version=?
            ==> Parameters: 外星人笔记本(String), 70(Integer), 3(Integer), 1(Long), 2(Integer)
         */
        
        
        if (result == 0) {
            //操作失败，重试�?�重新获取版本号
            Product productNew = productMapper.selectById(1);
            productNew.setPrice(productNew.getPrice() - 30);
            productMapper.updateById(productNew);
            /*控制台sql日志:
                ==>  Preparing: UPDATE t_product SET name=?, price=?, version=? WHERE id=? AND version=?
                ==> Parameters: 外星人笔记本(String), 120(Integer), 4(Integer), 1(Long), 3(Integer)
             */
        }

        //老板查询商品价格
        Product productLaoban = productMapper.selectById(1);
        System.out.println("老板查询的商品价格：" + productLaoban.getPrice());
        // 老板查询的商品价格：120
    }

}
```



## 9. 通用枚举

表中的有些字段�?�是固定的，例如性别(男或�?)，此时我们可以使用MyBatis-Plus的�?�用枚举来实�?

修改`t_user`表，添加sex性别字段�?

![image-20231006221453281](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310070121482.png)

创建通用枚举类型

`SexEnum`�?

```java
@Getter
public enum SexEnum {
    MALE(1, "�?"),
    FEMALE(2, "�?");

    @EnumValue //将注解所标识的属性的值存储到数据库中
    private Integer sex;
    private String sexName;

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
```

`application.yml`：配置扫描�?�用枚举

```yaml
mybatis-plus:
  # 扫描通用枚举的包
  type-enums-package: com.atguigu.mybatispuls.enums
```

`MyBatisPlusEnumTest`：测�?

```java
@SpringBootTest
public class MyBatisPlusEnumTest {
    @Autowired
    private UserMapper userMapper;
    
    @Test
    public void test() {
        User user = new User();
        user.setName("admin");
        user.setAge(33);
        //设置性别信息为枚举项，会将@EnumValue注解�?标识的属性�?�存储到数据�?
        user.setSex(SexEnum.MALE);
        int result = userMapper.insert(user);
        // INSERT INTO t_user ( user_name, sex, age ) VALUES ( ?, ?, ? )
        // Parameters: admin(String), 1(Integer), 33(Integer)
        System.out.println("result:" + result);
    }
}
```

##  10. 代码生成�?

代码生成器相关文档：`https://baomidou.com/pages/779a6e/#使用`

添加依赖�?

```xml
<!-- 使用代码生成�? -->
 <dependency>
     <groupId>com.baomidou</groupId>
     <artifactId>mybatis-plus-generator</artifactId>
     <version>3.5.1</version>
 </dependency>

 <!-- 使用代码生成�? -->
 <dependency>
     <groupId>org.freemarker</groupId>
     <artifactId>freemarker</artifactId>
     <version>2.3.31</version>
 </dependency>
```

`FastAutoGeneratorTest`�?

```java
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.Collections;

public class FastAutoGeneratorTest {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://192.168.56.10:3306/mybatis_plus?characterEncoding=utf-8&userSSL=false", "root", "root").globalConfig(builder -> {
                    builder.author("atguigu") // 设置作�??
                            //.enableSwagger() // �?�? swagger 模式
                            .fileOverride() // 覆盖已生成文�?
                            .outputDir("D://mybatis_plus"); // 指定输出目录
                }).packageConfig(builder -> {
                    builder.parent("com.atguigu") // 设置父包�?
                            .moduleName("mybatisplus") // 设置父包模块�?
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://mybatis_plus")); // 设置mapperXml生成路径
                }).strategyConfig(builder -> {
                    builder.addInclude("t_user") // 设置�?要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前�?
                }).templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
```



运行上面代码后在目录`D://mybatis_plus`下生成相关代码：

![image-20231006224037867](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/gulimall/202310070121386.png)



##  11. 多数据源

#####  11.1 多数据源应用场景

**MyBatis-Plus多数据源相关�?**档：`https://baomidou.com/pages/a61e1b/#文档-documentation`

**多数据源应用场景**：纯粹多库�?�读写分离�?��?�主多从、混合模式等。目前我们就来模拟�?�个纯粹多库的一个场景，其他场景类似

**场景说明**：我们创建两个库，分别为: mybatis_plus (以前的库不动)与mybatis_plus_1 (新建)，将mybatis_plus库的product表移动到mybatis_plus_1库，这样每个库一张表，�?�过�?个测试用例分别获取用户数据与商品数据，如果获取到说明多库模拟成功

#####  11.2 MyBatis-Plus多数据源实现

1.创建数据库及表：创建数据库`mybatis_plus_1`和表`product`并添加测试数据，创建数据库`mybatis_plus`和表`t_user`并添加测试数�?

```sql
CREATE DATABASE `mybatis_plus_1` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `mybatis_plus_1`;
CREATE TABLE product
(
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    NAME VARCHAR(30) NULL DEFAULT NULL COMMENT '商品名称',
    price INT(11) DEFAULT 0 COMMENT '价格',
    VERSION INT(11) DEFAULT 0 COMMENT '乐观锁版本号',
    PRIMARY KEY (id)
);

INSERT INTO product (id, NAME, price) VALUES (1, '外星人笔记本', 100);


CREATE DATABASE `mybatis_plus` CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;
USE `mybatis_plus`;
CREATE TABLE `t_user` (
                          `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                          `user_name` varchar(30) DEFAULT NULL COMMENT '姓名',
                          `age` int(11) DEFAULT NULL COMMENT '年龄',
                          `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
                          `is_deleted` int(11) DEFAULT '0' COMMENT '是否被删�?',
                          `sex` int(11) DEFAULT '0' COMMENT '性别',
                          PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8



insert into `t_user` (`uid`, `user_name`, `age`, `email`, `is_deleted`, `sex`) values('1','Jone','18','test1@baomidou.com','1','0');
insert into `t_user` (`uid`, `user_name`, `age`, `email`, `is_deleted`, `sex`) values('2','Jack','20','test2@baomidou.com','1','0');
insert into `t_user` (`uid`, `user_name`, `age`, `email`, `is_deleted`, `sex`) values('3','Tom','28','test3@baomidou.com','1','0');
insert into `t_user` (`uid`, `user_name`, `age`, `email`, `is_deleted`, `sex`) values('4','小明','21','test@atguigu.com','0','0');
```

2.引入依赖�?

```xml
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.5.1</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!--多数据源-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
            <version>3.5.0</version>
        </dependency>
```

3.`application.yml`：配置多数据�?

```yaml
spring:
  # 配置数据源信�?
  datasource:
    dynamic:
      # 设置默认的数据源或�?�数据源�?,默认值即为master
      primary: master
      # 严格匹配数据�?,默认false.true未匹配到指定数据源时抛异�?,false使用默认数据�?
      strict: false
      datasource:
        master:
          url: jdbc:mysql://192.168.56.10:3306/mybatis_plus?characterEncoding=utf-8&useSSL=false
          driver-class-name: com.mysql.cj.jdbc.Driver
          username: root
          password: root
        slave_1:
          url: jdbc:mysql://192.168.56.10:3306/mybatis_plus_1?characterEncoding=utf-8&useSSL=false
          driver-class-name: com.mysql.cj.jdbc.Driver
          username: root
          password: root
```

4.创建用户service及实现类

```
1.使用@DS指定�?操作的数据源
2.使用@DS切换数据源，@DS可以注解在方法上或类上，同时存在就近原则方法上注解优先于类上注解
```

`UserService`�?

```java
public interface UserService extends IService<User> {
}
```

`UserServiceImpl`�?

```java
@Service
@DS("master")  //指定�?操作的数据源
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
```

5.创建商品service

`UserService`�?

```java
public interface UserService extends IService<User> {
}
```

`ProductServiceImpl`�?

```java
@Service
@DS("slave_1")   //指定�?操作的数据源
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
```

6.测试�?

```java
@SpringBootTest
class MybatisPlusDatasourceApplicationTests {
   @Autowired
   private UserService userService;
   @Autowired
   private ProductService productService;

   @Test
   public void test(){
      //  SELECT uid,user_name,age,sex,email,is_deleted FROM t_user WHERE uid=?
      System.out.println(userService.getById(1));
      // SELECT id,name,price,version FROM product WHERE id=?
      System.out.println(productService.getById(1));
   }

}
```

##  12.MyBatisX插件

MyBatis-Plus为我们提供了强大的mapper和service模板，能够大大的提高�?发效�?

但是在真正开发过程中，MyBatis-Plus并不能为我们解决�?有问题，例如�?些复杂的SQL，多表联查，我们就需要自己去编写代码和SQL语句，我们该如何快�?�的解决这个问题呢，这个时�?�可以使用MyBatisX插件

MyBatisX�?款基于IDEA的快速开发插件，为效率�?�生

MyBatisX插件官方文档: `https://baomidou.com/pages/ba5b24/`