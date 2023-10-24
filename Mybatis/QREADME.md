代码模块和笔记章节的对应关系：

- 1节对应`mybatis_demo1`
- 2节对应`mybatis_demo2_config`
- 3-5节对应`mybatis_demo3`
- 6-8节对应`mybatis_demo4`
- 9-10节对应`mybatis_demo4_MBG`

##  1.MyBatis简介 & 入门

###  1.1  MyBatis简介

#####  1.1.1  MyBatis历史

MyBatis最初是Apache的一个开源项目iBatis, 2010年6月这个项目由Apache Software Foundation迁移到了Google Code。随着开发团队转投Google Code旗下，iBatis3.x正式更名为MyBatis。代码于2013年11月迁移到Github

iBatis一词来源于"internet"和"abatis"的组合，是一个基于Java的持久层框架。iBatis提供的持久层框架包括SQL Maps和Data Access Objects (DAO)、

#####  1.1.2 MyBatis特性

1.MyBatis是支持定制化SQL、存储过程以及高级映射的优秀的持久层框架

2.MyBatis避免了几乎所有的JDBC代码和手动设置参数以及获取结果集

3.MyBatis可以使用简单的XML或注解用于配置和原始映射，将接口和Java的POJO (Plain Old JavaObjects，普通的Java对象）映射成数据库中的记录

4.MyBatis是一个半自动的ORM (Object Relation Mapping）框架

#####  1.1.3 MyBatis源码下载

MyBatis下载地址: `https://github.com/mybatis/mybatis-3`

#####   1.1.4 和其它持久化层技术对比

**JDBC**：

- SQL夹杂在Java代码中耦合度高，导致硬编码内伤

- 维护不易且实际开发需求中SQL有变化，频繁修改的情况多见。代码冗长，开发效率低

**Hibernate和JPA**：

- 操作简便，开发效率高

- 程序中的长难复杂SQL需要绕过框架。内部自动生产的SQL，不容易做特殊优化

- 基于全映射的全自动框架，大量字段的POJO进行部分映射时比较困难

- 反射操作太多，导致数据库性能下降

**MyBatis**：

- 轻量级，性能出色

- SQL和Java编码分开，功能边界清晰。Java代码专注业务、SQL语句专注数据
- 开发效率稍逊于Hlbernate，但是完全能够接受

###  1.2  搭建Mybatis

#####  1.2.1 创建数据库

```sql
CREATE DATABASE `mybatis` CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

#####  1.2.2 引入`mybatis`依赖

```xml
        <!-- Mybatis核心 -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.7</version>
        </dependency>
        
        <!-- MySQL驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.3</version>
        </dependency>
```

#####  1.2.3 创建MyBatis的核心配置文件

> 习惯上命名为mybatis-config.xml，这个文件名仅仅只是建议，并非强制要求。将来整合Spring之后，这个配置文件可以省略，所以大家操作时可以直接复制、粘贴
>
> 核心配置文件主要用于配置连接数据库的环境以及MyBatis的全局配置信息核心配置文件存放的位置是src/main/resources目录下

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--设置连接数据库的环境-->
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url"
                          value="jdbc:mysql://192.168.56.10:3306/mybatis"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>
    <!--引入映射文件-->
    <mappers>
        <mapper resource="mapper/UserMapper.xml"/>
    </mappers>
</configuration>
```

#####  1.2.4  创建mapper接口

> MyBatis中的mapper接口相当于以前的dao。但是区别在于，mapper仅仅是接口，我们不需要提供实现类

```java
public interface UserMapper {
/**
* 添加用户信息
*/
int insertUser();
}
```

#####  1.2.5 创建MyBatis的映射文件

**相关概念**：

- ORM (Object Relationship Mapping)对象关系映射

- 对象：Java的实体类对象

- 关系：关系型数据库

- 映射：二者之间的对应关系

**Java概念类与数据库表的对应关系**：

| Java概念 | 数据库概念 |
| -------- | ---------- |
| 类       | 表         |
| 属性     | 字段/列    |
| 对象     | 记录/行    |

**映射文件的命名规则**：

- 表所对应的实体类的类名+Mapper.xml 

- 例如：表t_user，映射的实体类为User，所对应的映射文件为UserMapper.xml 

- 因此一个映射文件对应一个实体类，对应一张表的操作 

- MyBatis映射文件用于编写SQL，访问以及操作表中的数据 

- MyBatis映射文件存放的位置是src/main/resources/mappers目录下

**MyBatis中可以面向接口操作数据，要保证两个一致**： 

- mapper接口的全类名和映射文件的命名空间（namespace）保持一致 
- mapper接口中方法的方法名和映射文件中编写SQL的标签的id属性保持一致

**`UserMapper.xml`**：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.atguigu.mybatis.mapper.UserMapper">


    <!--int insertUser();-->
    <insert id="insertUser">
        insert into t_user
        values (null, '张三', '123', 23, '女', "12345@qq.com")
    </insert>


</mapper>
```

#####  1.2.6 mybatis执行sql

- SqlSession：代表Java程序和数据库之间的会话（HttpSession是Java程序和浏览器之间的 会话） 

- SqlSessionFactory：是“生产”SqlSession的工厂

- 工厂模式：如果创建某一个对象，使用的过程基本固定，那么我们就可以把创建这个对象的 

- 相关代码封装到一个“工厂类”中，以后都使用这个工厂类来“生产”我们需要的对象

```sql
@SpringBootTest
public class MybatisTest {

    /**
     * SqlSession默认不自动提交事务，若需要自动提交事务可以使用SqlSessionFactory.openSession(true);
     */

    @Test
    public void testMyBatis() throws IOException {
        //读取MyBatis的核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        //创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);

        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都必须手动提交或回滚事务
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建SqlSession对象，此时通过SqlSession对象所操作的sql都会自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //通过代理模式创建UserMapper接口的代理实现类对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        //调用UserMapper接口中的方法，就可以根据UserMapper的全类名匹配元素文件，通过调用的方法名匹配映射文件中的SQL标签，并执行标签中的SQL语句
        int result = userMapper.insertUser();
//        // 提交事务
//        sqlSession.commit();
        System.out.println("结果：" + result);
    }
}
```

#####  1.2.7 加入log4j日志功能

加入依赖：

```xml
        <!-- log4j日志 -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
```

log4j的配置文件名为log4j.xml，存放的位置是src/main/resources目录下

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
    <appender name="STDOUT" class="org.apache.log4j.ConsoleAppender">
        <param name="Encoding" value="UTF-8"/>
        <layout class="org.apache.log4j.PatternLayout">
            <param name="ConversionPattern" value="%-5p %d{MM-dd HH:mm:ss,SSS} %m (%F:%L) \n"/>
        </layout>
    </appender>
    <logger name="java.sql">
        <level value="debug"/>
    </logger>
    <logger name="org.apache.ibatis">
        <level value="info"/>
    </logger>
    <root>
        <level value="debug"/>
        <appender-ref ref="STDOUT"/>
    </root>
</log4j:configuration>
```

###  1.3  mybatis增删改查功能

##### 1.3.1 修改数据

`UserMapper`接口：

```java
public interface UserMapper {
    /**
     * 修改用户信息
     */
    void updateUser();
}
```

`UserMapper.xml`: 

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.atguigu.mybatis.mapper.UserMapper">
    
    <update id="updateUser">
        update t_user
        set username = '张三'
        where id = 4
    </update>
    
</mapper>
```

`updateUser`方法调用：

```java
@SpringBootTest
public class MybatisTest {
    @Test
    public void testCRUDUpdate() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUser();
    }

}
```

##### 1.3.2 删除数据

`UserMapper`：

```java
public interface UserMapper {
    /**
     * 删除用户信息
     */
    void deleteUser();
}
```

`UserMapper.xml`：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.atguigu.mybatis.mapper.UserMapper">

    <delete id="deleteUser">
        delete
        from t_user
        where id = 5
    </delete>

</mapper>
```

`deleteUser`方法调用：

```java
@SpringBootTest
public class MybatisTest {
    @Test
    public void testCRUDDelete() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUser();
    }
}
```

#####  1.3.3 查询单条数据

查询功能的标签必须设置resultType或resultMap：

- resultType：设置默认的映射关系(字段名和属性名一致)
- resultMap：设置自定义的映射关系（通常在字段名和属性名不一致时使用）

`UserMapper`：

```java
public interface UserMapper {
    /**
     * 根据id查询用户信息
     */
    User getUserById();
}
```

`UserMapper.xml`：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.atguigu.mybatis.mapper.UserMapper">
    <!--
        查询功能的标签必须设置resultType或resultMap
        resultType：设置默认的映射关系
        resultMap：设置自定义的映射关系（通常在字段名和属性名不一致时使用）
    -->
    <select id="getUserById" resultType="com.atguigu.mybatis.pojo.User">
        select *
        from t_user
        where id = 3
    </select>
</mapper>
```

测试查询方法：

```java
@SpringBootTest
public class MybatisTest {
    @Test
    public void testCRUDQueryUser() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userById = mapper.getUserById();
        System.out.println(userById);
    }

}
```

#####  1.3.4 查询多条数据

`UserMapper`：

```java
public interface UserMapper {
    /**
     * 查询所有的用户信息
     */
    List<User> getAllUser();
}
```

`UserMapper.xml`：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.atguigu.mybatis.mapper.UserMapper">

    <select id="getAllUser" resultType="com.atguigu.mybatis.pojo.User">
        select *
        from t_user
    </select>

</mapper>
```

查询多条数据：

```java
@SpringBootTest
public class MybatisTest {
    @Test
    public void testCRUDQueryUserList() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.getAllUser();
        for (User user : userList) {
            System.out.println(user);
        }
    }

}
```

##  2. 核心配置文件详解

在SSM整合的环境中，可以没有核心配置文件。因为在SSM环境中，核心配置文件中的所有配置都可以直接交给spring进行管理

核心配置文件中的标签必须按照固定的顺序： 

- properties

- settings

- typeAliases

- typeHandlers

- objectFactory

- objectWrapperFactory

- reflectorF actory

- plugins

- environments

- databaseIdProvider

- mappers

`src/main/resources/mybatis-config.xml`：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!--引入properties文件，此时就可以${属性名}的方式访问属性值-->
    <properties resource="jdbc.properties"></properties>


    <!--设置类型别名-->
    <typeAliases>
        <!--typeAlias：设置某个具体的类型的别名。typeAlias的属性包含type和alias-->
        <!--typeAlias的type属性：需要设置别名的类型的全类名-->
        <typeAlias type="com.atguigu.mybatis.pojo.User"></typeAlias>


        <!--typeAlias的alias属性：设置某个类型的别名。若不设置该属性，那么该类型拥有默认的别名，即类名不区分大小写。若设置此属性，此时该类型的别名只能使用alias所设置的值-->
        <!--<typeAlias type="com.atguigu.mybatis.pojo.User" alias="abc"></typeAlias>-->


        <!--以包为单位，将包下所有的类型设置默认的类型别名，即类名且不区分大小写-->
        <!--<package name="com.atguigu.mybatis.pojo"/>-->
    </typeAliases>


    <!--
    environments标签：配置多个连接数据库的环境
    environments属性：default属性设置默认使用的环境的id
    -->
    <!--设置连接数据库的环境-->
    <environments default="development">
        <!-- environment：配置某个具体的环境,id属性表示连接数据库的环境的唯一标识，不能重复-->
        <environment id="development">
            <!--transactionManager：设置事务管理方式-->
            <!--type属性可以取值JDBC或MANAGED
            JDBC：表示当前环境中，执行SQL时，使用的是JDBC中原生的事务管理方式，事务的提交或回滚需要手动处理
            MANAGED：被管理，例如Spring -->
            <transactionManager type="JDBC"/>

            <!--dataSource：配置数据源
            dataSource的type属性：设置数据源的类型。type的取值可以是POOLED或UNPOOLED或JNDI
            POOLED：表示使用数据库连接池缓存数据库连接
            UNPOOLED：表示不使用数据库连接池
            JNDI：表示使用上下文中的数据源 -->
            <dataSource type="POOLED">
                <!--设置驱动类的全类名-->
                <property name="driver" value="${jdbc.driver}"/>
                <!--设置连接数据库的连接地址-->
                <property name="url" value="${jdbc.url}"/>
                <!--设置连接数据库的用户名-->
                <property name="username" value="${jdbc.username}"/>
                <!--设置连接数据库的密码-->
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>

        <environment id="test">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <!--设置驱动类的全类名-->
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <!--设置连接数据库的连接地址-->
                <property name="url"
                          value="jdbc:mysql://192.168.56.10:3306/mybatis"/>
                <!--设置连接数据库的用户名-->
                <property name="username" value="root"/>
                <!--设置连接数据库的密码-->
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>


    <!--引入映射文件-->
    <mappers>
        <mapper resource="mapper/UserMapper.xml"/>


        <!--以包为单位，将包下所有的映射文件引入核心配置文件。注意：此方式必须保证mapper接口和mapper映射文件必须在相同的包下-->
        <!--举例：如果mapper接口在src/main/java/com/atguigu/mybatis/mapper目录下，那mapper映射文件就应该放在src/main/resources/com/atguigu/mybatis/mapper下-->
        <!-- <package name="com.atguigu.mybatis.mapper"/>-->
    </mappers>
</configuration>
```



## 3.MyBatis获取参数值

###  3.1 项目搭建

1.创建新模块`mybatis_demo3`并引入依赖

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

        <!-- Mybatis核心 -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.7</version>
        </dependency>

        <!-- MySQL驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.3</version>
        </dependency>

        <!-- junit测试 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>


        <!-- log4j日志 -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
```

2.添加实体类`User`：

```java
@Data
public class User {

    private Integer id;

    private String username;

    private String password;

    private Integer age;

    private String sex;

    private String email;
}
```

3.添加`ParameterMapper`接口

```java
public interface ParameterMapper {
    /**
     * 查询所有的用户信息
     */
    List<User> getAllUser();
}
```

4.添加mybatis核心配置文件`mybatis-config.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!--引入properties文件，此时就可以${属性名}的方式访问属性值-->
    <properties resource="jdbc.properties"></properties>

    <!--设置类型别名-->
    <typeAliases>
        <!--以包为单位，将包下所有的类型设置默认的类型别名，即类名且不区分大小写-->
        <package name="com.atguigu.mybatis.pojo"/>
    </typeAliases>

    <!--设置连接数据库的环境-->
    <environments default="development">
        <!-- environment：配置某个具体的环境,id属性表示连接数据库的环境的唯一标识，不能重复-->
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <!--设置驱动类的全类名-->
                <property name="driver" value="${jdbc.driver}"/>
                <!--设置连接数据库的连接地址-->
                <property name="url" value="${jdbc.url}"/>
                <!--设置连接数据库的用户名-->
                <property name="username" value="${jdbc.username}"/>
                <!--设置连接数据库的密码-->
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>

    <!--引入映射文件-->
    <mappers>
        <!--以包为单位，将包下所有的映射文件引入核心配置文件。注意：此方式必须保证mapper接口和mapper映射文件必须在相同的包下-->
        <!--举例：如果mapper接口在src/main/java/com/atguigu/mybatis/mapper目录下，那mapper映射文件就应该放在src/main/resources/com/atguigu/mybatis/mapper下-->
        <package name="com.atguigu.mybatis.mapper"/>
    </mappers>
</configuration>
```

5.添加mapper映射文件`src/main/resources/com/atguigu/mybatis/mapper/ParameterMapper.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.atguigu.mybatis.mapper.ParameterMapper">

    <select id="getAllUser" resultType="User">
        select * from t_user
    </select>

</mapper>
```

6.`SqlSessionUtils`工具类封装

```java
public class SqlSessionUtils {

    public static SqlSession getSqlSession(){
        SqlSession sqlSession = null;
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            sqlSession = sqlSessionFactory.openSession(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSession;
    }
}
```

7.测试工具类：

```java
public class ParameterMapperTest {
    @Test
    public void testGetAllUser(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        List<User> list = mapper.getAllUser();
        list.forEach(user -> System.out.println(user));
    }
}
```

### 3.2 JDBC中获取参数值

JDBC中获取参数的方式：字符串拼接、占位符

```java
public class ParameterMapperTest {

    @Test
    public void testJDBC() throws Exception {
        String username = "admin";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.56.10:3306/mybatis", "root", "root");
        // 方式一：字符串拼接
        //PreparedStatement ps = connection.prepareStatement("select * from t_user where username = '" + username + "'");
        // 方式二：占位符
        PreparedStatement ps = connection.prepareStatement("selelt * from t_user where username = ?");
        ps.setString(1, username);
    }
}
```

###  3.3 MyBatis获取参数值

#####  3.3.1 MyBatis获取参数值的两种方式

 **1.MyBatis获取参数值的两种方式：${}和#{}** 

- ${}的本质就是字符串拼接，#{}的本质就是占位符赋值 

- ${}使用字符串拼接的方式拼接sql，若为字符串类型或日期类型的字段进行赋值时，需要手动加单引 号
- #{}使用占位符赋值的方式拼接sql，此时为字符串类型或日期类型的字段进行赋值时，可以自动添加单引号



`ParameterMapper`接口：

```java
public interface ParameterMapper {
    /**
     * 根据用户名查询用户信息
     */
    User getUserByUsername(String username);
}
```

`ParameterMapper.xml`：利用#{} 获取参数的值

```xml
<mapper namespace="com.atguigu.mybatis.mapper.ParameterMapper">
    <select id="getUserByUsername" resultType="User">
       select * from t_user where username = #{username}
    </select>
</mapper>
```

测试：查看sql日志打印发现所有的是占位符的方式进行参数赋值

```java
@Test
public void testGetUserByUsername() {
    SqlSession sqlSession = SqlSessionUtils.getSqlSession();
    ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
    User user = mapper.getUserByUsername("admin");
    // select * from t_user where username = ?
    System.out.println(user);
}
```

`ParameterMapper.xml`：利用${}获取参数的值

```xml
<mapper namespace="com.atguigu.mybatis.mapper.ParameterMapper">
    <select id="getUserByUsername" resultType="User">
        select * from t_user where username = '${username}'
    </select>
</mapper>
```

测试：查看sql日志打印发现所有的是通过字符串拼接的方式进行参数赋值

```java
@Test
public void testGetUserByUsername() {
        /**
     * MyBatis获取参数值的两种方式：${}和#{}
     * ${}本质字符串拼接
     * #{}本质占位符赋值
     * MyBatis获取参数值的各种情况：
     * 1、mapper接口方法的参数为单个的字面量类型
     * 可以通过${}和#{}以任意的名称获取参数值，但是需要注意${}的单引号问题
     */
    SqlSession sqlSession = SqlSessionUtils.getSqlSession();
    ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
    User user = mapper.getUserByUsername("admin");
    //  select * from t_user where username = 'admin'
    System.out.println(user);
}
```

#####  3.3.2  单个字面量类型的参数

>若mapper接口中的方法参数为单个的字面量类型
>此时可以使用${}和#{}以任意的名称获取参数的值，注意${}需要手动加单引号



#####  3.3.3  多个字面量类型的参数

> 若mapper接口中的方法参数为多个。MyBatis会自动将这些参数放在一个map集合中，以arg0,arg1...为键，以参数为值以 param1,param2...为键，以参数为值
>
> 因此只需要通过${}和#{}访问map集合的键就可以获取相对应的 值，注意${}需要手动加单引号

`ParameterMapper`：

```java
public interface ParameterMapper {
    /**
     * 验证登录
     */
    User checkLogin(String username, String password);
}
```

`ParameterMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.ParameterMapper">
    <select id="checkLogin" resultType="User">
        <!--select * from t_user where username = #{arg0} and password = #{arg1}-->
        select * from t_user where username = '${param1}' and password = '${param2}'
    </select>
</mapper>
```

测试：

```java
public class ParameterMapperTest {
    /**
     * 2、mapper接口方法的参数为多个时
     * 此时MyBatis会将这些参数放在一个map集合中，以两种方式进行存储
     * a>以arg0,arg1...为键，以参数为值
     * b>以param1,param2...为键，以参数为值
     * 因此只需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题
     */
    @Test
    public void testCheckLogin() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.checkLogin("admin", "123456");
        System.out.println(user);
    }

}
```

#####  3.3.4  map集合类型的参数

> 若mapper接口中的方法需要的参数为多个时，此时可以手动创建map集合，将这些数据放在map中 
>
> 只需要通过${}和#{}访问map集合的键就可以获取相对应的值，注意${}需要手动加单引号

`ParameterMapper`：

```java
public interface ParameterMapper {
    /**
     * 验证登录（参数为map）
     */
    User checkLoginByMap(Map<String, Object> map); 
}
```

`ParameterMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.ParameterMapper">
    <!--User checkLoginByMap(Map<String, Object> map);-->
    <select id="checkLoginByMap" resultType="User">
        select * from t_user where username = #{username} and password = #{password}
    </select>
</mapper>
```

测试：

```java
public class ParameterMapperTest {
    /**
     * 3、若mapper接口方法的参数有多个时，可以手动将这些参数放在一个map中存储
     * 只需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题
     */
    @Test
    public void testCheckLoginByMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("username", "admin");
        map.put("password", "123456");
        User user = mapper.checkLoginByMap(map);
        System.out.println(user);
    }
}
```

#####  3.3.5  实体类类型的参数

> 若mapper接口中的方法参数为实体类对象时 此时可以使用${}和#{}，通过访问实体类对象中的属性名获取属性值，注意${}需要手动加单引号



`ParameterMapper`：

```java
public interface ParameterMapper {
    /**
     * 添加用户信息
     */
    int insertUser(User user);
}
```

`ParameterMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.ParameterMapper">
    <insert id="insertUser">
        insert into t_user values(null,#{username},#{password},#{age},#{sex},#{email})
    </insert>
</mapper>
```

测试：

```java
public class ParameterMapperTest {
    /**
     * 4、mapper接口方法的参数是实体类类型的参数
     * 只需要通过#{}和${}以属性的方式访问属性值即可，但是需要注意${}的单引号问题
     */
    @Test
    public void testInsertUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        int result = mapper.insertUser(new User(null, "李四", "123", 23, "男", "123@qq.com"));
        System.out.println(result);
    }
}
```

#####  3.3.6  使用@Param标识参数 

> 可以通过@Param注解标识mapper接口中的方法参数 
>
> 此时，会将这些参数放在map集合中，以@Param注解的value属性值为键，以参数为值；以 param1,param2...为键，以参数为值；
>
> 只需要通过${}和#{}访问map集合的键就可以获取相对应的值， 注意${}需要手动加单引号

`ParameterMapper`：

```java
public interface ParameterMapper {
    /**
     * 验证登录（使用@Param）
     */
    User checkLoginByParam(@Param("username") String username, @Param("password") String password);
}
```

`ParameterMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.ParameterMapper">
    <!--User checkLoginByParam(@Param("username") String username, @Param("password") String password);-->
    <select id="checkLoginByParam" resultType="User">
        select * from t_user where username = #{username} and password = #{password}
    </select>
</mapper>
```

测试：

```java
public class ParameterMapperTest {
    /**
     * 5、使用@Param注解命名参数
     * 此时MyBatis会将这些参数放在一个map集合中，以两种方式进行存储
     * a>以@Param注解的值为键，以参数为值
     * b>以param1,param2...为键，以参数为值
     * 因此只需要通过#{}和${}以键的方式访问值即可，但是需要注意${}的单引号问题
     */
    @Test
    public void testCheckLoginByParam() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.checkLoginByParam("admin", "123456");
        System.out.println(user);
    }

}
```

##  4.MyBatis的各种查询功能 

###  4.1 查询一个实体类对象

`SelectMapper`：

```java
public interface SelectMapper {
    /**
     * 根据id查询用户信息
     */
    List<User> getUserById(@Param("id") Integer id);
}
```

`SelectMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.SelectMapper">
    <select id="getUserById" resultType="User">
        select * from t_user where id = #{id}
    </select>
</mapper>
```

测试：

```java
public class SelectMapperTest {

    /**
     * 1、若查询出的数据只有一条
     * a>可以通过实体类对象接收
     * b>可以通过list集合接收
     * c>可以通过map集合接收
     * 结果：{password=123456, sex=男, id=3, age=23, email=12345@qq.com, username=admin}
     */

    @Test
    public void testGetUserById(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getUserById(3));
    }
}
```

###  4.2 查询一个list集合

`SelectMapper`：

```java
public interface SelectMapper {
    /**
     * 查询所有的用户信息
     */
    List<User> getAllUser();
}
```

`SelectMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.SelectMapper">
    <select id="getAllUser" resultType="User">
        select * from t_user
    </select>
</mapper>
```

测试：

```java
public class SelectMapperTest {
    /**
     * 2、若查询出的数据有多条
     * a>可以通过实体类类型的list集合接收
     * b>可以通过map类型的list集合接收
     * c>可以在mapper接口的方法上添加@MapKey注解，此时就可以将每条数据转换的map集合作为值，以某个字段的值作为键，放在同一个map集合中
     * 注意：一定不能通过实体类对象接收，此时会抛异常TooManyResultsException
     *
     * MyBatis中设置了默认的类型别名
     * java.lang.Integer-->int,integer
     * int-->_int,_integer
     * Map-->map
     * String-->string
     */
    @Test
    public void testGetAllUser(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getAllUser());
    }
}
```

### 4.3  查询单个数据

`SelectMapper`：

```java
public interface SelectMapper {
    /**
     * 查询用户信息的总记录数
     */
    Integer getCount();
}
```

`SelectMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.SelectMapper">
    <select id="getCount" resultType="int">
        select count(*) from t_user
    </select>
</mapper>
```

测试：

```java
public class SelectMapperTest {
    /**
     * MyBatis中设置了默认的类型别名
     * java.lang.Integer-->int,integer
     * int-->_int,_integer
     * Map-->map
     * String-->string
     */
    @Test
    public void testGetCount(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getCount());
    }
}
```

###  4.4 查询一条数据为map集合

有时候查到的数据来源于多张表，这时候没有实体类对应的话。就可以将数据的返回类型设置为map，再将map响应给前端

`SelectMapper`：

```java
public interface SelectMapper {
    /**
     * 根据id查询用户信息为一个map集合
     */
    Map<String, Object> getUserByIdToMap(@Param("id") Integer id);
}
```

`SelectMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.SelectMapper">
    <select id="getUserByIdToMap" resultType="map">
        select * from t_user where id = #{id}
    </select>
</mapper>
```

测试：

```java
public class SelectMapperTest {
    @Test
    public void testGetUserByIdToMap(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getUserByIdToMap(3));
    }
}
```

###  4.5 查询多条数据为map集合

#####  4.5.1 方式一：将map作为List的元素

`SelectMapper`：

```java
public interface SelectMapper {
    /**
     * 查询所有用户信息为map集合
     * 将表中的数据以map集合的方式查询，一条数据对应一个map；若有多条数据，就会产生多个map集合，此时可以将这些map放在一个list集合中获取
     */
    List<Map<String, Object>> getAllUserToMap1();
}
```

`SelectMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.SelectMapper">
    <select id="getAllUserToMap1" resultType="map">
        select * from t_user
    </select>
</mapper>
```

测试：返回结果是List类型

```java
    @Test
    public void testGetAllUserToMap1(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getAllUserToMap1());
        // 返回结果是List
//        [{password=123, sex=?, id=5, age=23, email=12345@qq.com, username=??}, 
//        {password=123, sex=?, id=6, age=23, email=123@qq.com, username=??},
//        {password=123, sex=男, id=7, age=23, email=123@qq.com, username=李四}]
    }
```

#####  4.5.2  方式二：`@MapKey`注解指定键

> 可以在mapper接口的方法上添加@MapKey注解，此时就可以将每条数据转换的map集合作为值，以某个字段的值作为键，放在同一个map集合中



`SelectMapper`：

```java
public interface SelectMapper {
    /**
     * 查询所有用户信息为map集合
     * 将表中的数据以map集合的方式查询，一条数据对应一个map；若有多条数据，就会产生多个map集合，并
     * 且最终要以一个map的方式返回数据，此时需要通过@MapKey注解设置map集合的键，值是每条数据所对应的
     * map集合
     */
    @MapKey("id")
    Map<String, Object> getAllUserToMap();
}
```

`SelectMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.SelectMapper">
    <select id="getAllUserToMap" resultType="map">
        select * from t_user
    </select>
</mapper>
```

测试：返回结果是map类型，id作为key

```java
    @Test
    public void testGetAllUserToMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getAllUserToMap());
        // 返回结果是map类型，id作为key
//        {
//            1 = {password = 123, sex = ?, id = 1, age = 23, email = 12345 @qq.com,username =??},
//            2 = {password = 123, sex = ?, id = 2, age = 23, email = 12345 @qq.com,username =??},
//            3 = {password = 123, sex = ?, id = 3, age = 23, email = 12345 @qq.com,username =??},
//            4 = {password = 123, sex = ?, id = 4, age = 23, email = 12345 @qq.com,username =??},
//            5 = {password = 123, sex = ?, id = 5, age = 23, email = 12345 @qq.com,username =??},
//            6 = {password = 123, sex = ?, id = 6, age = 23, email = 123 @qq.com,username =??},
//            7 = {password = 123, sex = 男, id = 7, age = 23, email = 123 @qq.com,username = 李四}
//        }
    }
```

##  5. 特殊SQL的执行

###  5.1 模糊查询

`SQLMapper`：

```java
public interface SQLMapper {
    /**
     * 根据用户名模糊查询用户信息
     */
    List<User> getUserByLike(@Param("username") String username);
}
```

`SQLMapper.xml`：可以用如下三种方式实现模糊查询

```xml
<mapper namespace="com.atguigu.mybatis.mapper.SQLMapper">
    <select id="getUserByLike" resultType="User">
        <!--select * from t_user where username like '%${username}%' -->
        <!--select * from t_user where username like concat('%',#{username},'%')-->
        select * from t_user where username like "%"#{username}"%"
    </select>
</mapper>
```

测试：

```java
public class SQLMapperTest {
    @Test
    public void testGetUserByLike(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        List<User> list = mapper.getUserByLike("a");
        System.out.println(list);
    }
}
```

###  5.2 批量删除

`SQLMapper`：

```java
public interface SQLMapper {
    /**
     * 批量删除
     */
    int deleteMore(@Param("ids") String ids);
}
```

`SQLMapper.xml`：此处的批量删除不能使用`#{}`,因为`#{}`会自动加单引号

```xml
<mapper namespace="com.atguigu.mybatis.mapper.SQLMapper">
    <delete id="deleteMore">
        delete from t_user where id in (${ids})
    </delete>
</mapper>
```

测试：

```java
public class SQLMapperTest {
    @Test
    public void testDeleteMore(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        int result = mapper.deleteMore("1,2,3");
        System.out.println(result);
    }
}
```

###  5.3 动态设置表名

`SQLMapper`：

```java
public interface SQLMapper {
    /**
     * 查询指定表中的数据
     */
    List<User> getUserByTableName(@Param("tableName") String tableName);
}
```

`SQLMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.SQLMapper">
    <select id="getUserByTableName" resultType="User">
        select * from ${tableName}
    </select>
</mapper>
```

测试：

```java
public class SQLMapperTest {
    @Test
    public void testGetUserByTableName(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        List<User> list = mapper.getUserByTableName("t_user");
        System.out.println(list);
    }
}
```

###  5.4 添加功能获取自增的主键

`SQLMapper`：

```java
public interface SQLMapper {
    /**
     * 添加用户信息
     */
    void insertUser(User user);

}
```

`SQLMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.SQLMapper">
    <!--
        void insertUser(User user);
        useGeneratedKeys:设置当前标签中的sql使用了自增的主键
        keyProperty:将自增的主键的值赋值给传输到映射文件中参数的某个属性
    -->
    <insert id="insertUser" useGeneratedKeys="true" keyProperty="id">
        insert into t_user values(null,#{username},#{password},#{age},#{sex},#{email})
    </insert>

</mapper>
```

测试：

```java
public class SQLMapperTest {
    @Test
    public void testInsertUser(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
        User user = new User(null, "王五", "123", 23, "男", "123@163.com");
        mapper.insertUser(user);
        System.out.println(user);
        // User(id=8, username=王五, password=123, age=23, sex=男, email=123@163.com)
    }
}
```

##  6. 自定义映射resultMap

###  6.1 新增数据库表 &  项目搭建

1.新增数据库表

```sql
CREATE TABLE `t_dept` (
  `did` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

insert into `t_dept` (`did`, `dept_name`) values('1','A');
insert into `t_dept` (`did`, `dept_name`) values('2','B');
insert into `t_dept` (`did`, `dept_name`) values('3','C');


CREATE TABLE `t_emp` (
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` char(4) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `did` int(11) DEFAULT NULL,
  PRIMARY KEY (`eid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

insert into `t_emp` (`eid`, `emp_name`, `age`, `sex`, `email`, `did`) values('1','张三','22','男','2762373@qq.com','1');
insert into `t_emp` (`eid`, `emp_name`, `age`, `sex`, `email`, `did`) values('2','李四','32','男','2929@qq.com','2');
insert into `t_emp` (`eid`, `emp_name`, `age`, `sex`, `email`, `did`) values('3','王五','20','男','ywyyw@163.com','3');
insert into `t_emp` (`eid`, `emp_name`, `age`, `sex`, `email`, `did`) values('4','钱多多','25','男','237636@168.com','2');
```

2.创建`mybatis_demo4`项目，引入依赖：

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

<!-- Mybatis核心 -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.7</version>
</dependency>

<!-- MySQL驱动 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.3</version>
</dependency>

<!-- junit测试 -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>


<!-- log4j日志 -->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

3.创建核心配置文件`mybatis-config.xml`、配置信息`jdbc.properties`、日志配置文件`log4j.xml`：

`jdbc.properties`：

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://192.168.56.10:3306/mybatis?useUnicode=true&characterEncoding=utf8&useSSL=false
jdbc.username=root
jdbc.password=root
```

`mybatis-config.xml`：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!--引入properties文件，此时就可以${属性名}的方式访问属性值-->
    <properties resource="jdbc.properties"></properties>

    <!--设置类型别名-->
    <typeAliases>
        <!--以包为单位，将包下所有的类型设置默认的类型别名，即类名且不区分大小写-->
        <package name="com.atguigu.mybatis.pojo"/>
    </typeAliases>

    <!--设置连接数据库的环境-->
    <environments default="development">
        <!-- environment：配置某个具体的环境,id属性表示连接数据库的环境的唯一标识，不能重复-->
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <!--设置驱动类的全类名-->
                <property name="driver" value="${jdbc.driver}"/>
                <!--设置连接数据库的连接地址-->
                <property name="url" value="${jdbc.url}"/>
                <!--设置连接数据库的用户名-->
                <property name="username" value="${jdbc.username}"/>
                <!--设置连接数据库的密码-->
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>

    <!--引入映射文件-->
    <mappers>
        <!--以包为单位，将包下所有的映射文件引入核心配置文件。注意：此方式必须保证mapper接口和mapper映射文件必须在相同的包下-->
        <!--举例：如果mapper接口在src/main/java/com/atguigu/mybatis/mapper目录下，那mapper映射文件就应该放在src/main/resources/com/atguigu/mybatis/mapper下-->
        <package name="com.atguigu.mybatis.mapper"/>
    </mappers>
</configuration>
```

`log4j.xml`：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
    <appender name="STDOUT" class="org.apache.log4j.ConsoleAppender">
        <param name="Encoding" value="UTF-8"/>
        <layout class="org.apache.log4j.PatternLayout">
            <param name="ConversionPattern" value="%-5p %d{MM-dd HH:mm:ss,SSS} %m (%F:%L) \n"/>
        </layout>
    </appender>
    <logger name="java.sql">
        <level value="debug"/>
    </logger>
    <logger name="org.apache.ibatis">
        <level value="info"/>
    </logger>
    <root>
        <level value="debug"/>
        <appender-ref ref="STDOUT"/>
    </root>
</log4j:configuration>
```

3.创建实体类、mapper接口、mapper映射文件、添加工具类

实体类`Dept`：

```java
@Data
public class Dept {

    private Integer did;

    private String deptName;
}
```

实体类`Emp`：

```java
@Data
public class Emp {

    private Integer eid;

    private String empName;

    private Integer age;

    private String sex;

    private String email;

}
```

`DeptMapper`：

```java
public interface DeptMapper {

}
```

`EmpMapper`：

```java
public interface EmpMapper {

}
```

`SqlSessionUtils`工具类：

```java
public class SqlSessionUtils {

    public static SqlSession getSqlSession(){
        SqlSession sqlSession = null;
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            sqlSession = sqlSessionFactory.openSession(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSession;
    }
}
```

###  6.2  字段名和属性名不一致问题

#####  6.2.1 字段名和属性名不一致问题

字段名和属性名不一致导致的问题：

- 数据表`t_emp`的字段名称为`emp_name`

- 实体类`Emp`的属性名为`empName`
- 问题：如果字段名和属性名不一致，那sql查询后无法给实体类的字段进行赋值，导致实体类的属性是空值

```java
public interface EmpMapper {
    /**
     * 查询所有的员工信息
     */
    List<Emp> getAllEmpByFirstWay();
}
```

`EmpMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.EmpMapper">
    <select id="getAllEmpByFirstWay" resultType="Emp">
        select * from t_emp
    </select>
</mapper>
```

测试：发现数据表的字段名和实体类的属性名不一致时没有报错，但是实体类的`empName`字段没有得到数据库中的值

```java
public class MybatisTest {
    @Test
    public void testGetAllEmpByFirstWay(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> list = mapper.getAllEmpByFirstWay();
        list.forEach(emp -> System.out.println(emp));
//        Emp(eid=1, empName=null, age=22, sex=男, email=2762373@qq.com)
//        Emp(eid=2, empName=null, age=32, sex=男, email=2929@qq.com)
//        Emp(eid=3, empName=null, age=20, sex=男, email=ywyyw@163.com)
//        Emp(eid=4, empName=null, age=25, sex=男, email=237636@168.com)
    }
}
```

#####  6.2.2 通过设置别名的方式处理字段名和属性名不一致

`EmpMapper`：

```java
public interface EmpMapper {
    /**
     * 查询所有的员工信息
     */
    List<Emp> getAllEmpBySecondWay();
}
```

`EmpMapper.xml`：通过在sql查询语句中设置别名的方式让字段名和属性名称保持一致

```xml
<mapper namespace="com.atguigu.mybatis.mapper.EmpMapper">
    <select id="getAllEmpBySecondWay" resultType="Emp">
        select eid,emp_name AS empName,age,sex,email  from t_emp
    </select>
</mapper>
```

测试：查询语句中设置别名的方式可以解决问题

```java
public class MybatisTest {
    @Test
    public void testGetAllEmpBySecondWay(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> list = mapper.getAllEmpBySecondWay();
        list.forEach(emp -> System.out.println(emp));
//        Emp(eid=1, empName=张三, age=22, sex=男, email=2762373@qq.com)
//        Emp(eid=2, empName=李四, age=32, sex=男, email=2929@qq.com)
//        Emp(eid=3, empName=王五, age=20, sex=男, email=ywyyw@163.com)
//        Emp(eid=4, empName=钱多多, age=25, sex=男, email=237636@168.com)
    }
}
```

#####  6.2.3 通过MyBatis的全局配置实现驼峰映射

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--设置MyBatis的全局配置-->
    <settings>
        <!--将_自动映射为驼峰，emp_name:empName-->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
</configuration>
```

#####  6.2.4  resultMap处理字段和属性的映射关系

若字段名和实体类中的属性名不一致，则可以通过resultMap设置自定义映射

```java
public interface EmpMapper {
    /**
     * 查询所有的员工信息
     */
    List<Emp> getAllEmpByResultMap();
}
```

`EmpMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.EmpMapper">
    <!--resultMap：设置自定义映射关系-->
    <!--resultMap的id属性：唯一标识，不能重复。resultMap的type属性：设置映射关系中的实体类类型-->
    <!--子标签：id：设置主键的映射关系. result：设置普通字段的映射关系-->
    <!--子标签的property属性：设置映射关系中的属性名，必须是type属性所设置的实体类类型中的属性名-->
    <!--子标签的column属性：设置映射关系中的字段名，必须是sql语句查询出的字段名-->
    <resultMap id="empResultMap" type="Emp">
        <id property="eid" column="eid"></id>
        <result property="empName" column="emp_name"></result>
        <result property="age" column="age"></result>
        <result property="sex" column="sex"></result>
        <result property="email" column="email"></result>
    </resultMap>

    <select id="getAllEmpByResultMap" resultMap="empResultMap">
        select *
        from t_emp
    </select>
</mapper>
```

测试：

```java
public class MybatisTest {
    /**
     * 解决字段名和属性名不一致的情况：
     * a>为字段起别名，保持和属性名的一致
     * b>设置全局配置，将_自动映射为驼峰
     * <setting name="mapUnderscoreToCamelCase" value="true"/>
     * c>通过resultMap设置自定义的映射关系
     * <resultMap id="empResultMap" type="Emp">
     *     <id property="eid" column="eid"></id>
     *     <result property="empName" column="emp_name"></result>
     *     <result property="age" column="age"></result>
     *     <result property="sex" column="sex"></result>
     *     <result property="email" column="email"></result>
     * </resultMap>
     *
     * 处理多对一的映射关系：
     * a>级联属性赋值
     * b>association
     * c>分步查询
     *
     * 处理一对多的映射关系
     * a>collection
     * b>分步查询
     */
    @Test
    public void testGetAllEmpByResultMap(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> list = mapper.getAllEmpByResultMap();
        list.forEach(emp -> System.out.println(emp));
//        Emp(eid=1, empName=张三, age=22, sex=男, email=2762373@qq.com)
//        Emp(eid=2, empName=李四, age=32, sex=男, email=2929@qq.com)
//        Emp(eid=3, empName=王五, age=20, sex=男, email=ywyyw@163.com)
//        Emp(eid=4, empName=钱多多, age=25, sex=男, email=237636@168.com)
    }
}
```

#####  6.2.5  总结

若字段名和实体类中的属性名不一致，但是字段名符合数据库的规则（使用_），实体类中的属性 名符合Java的规则（使用驼峰）

此时也可通过以下三种方式处理字段名和实体类中的属性的映射关系 

- 为字段起别名，保持和属性名的一致：通过设置别名的方式处理字段名和属性名不一致

- 可以通过为字段起别名的方式，保证和实体类中的属性名保持一致

- 可以在MyBatis的核心配置文件中设置一个全局配置信息mapUnderscoreToCamelCase，可 以在查询表中数据时，自动将_类型的字段名转换为驼峰 例如：字段名user_name，设置了mapUnderscoreToCamelCase，此时字段名就会转换为 userName

###  6.3 多对一映射处理

多对一映射场景：查询员工信息以及员工所对应的部门信息（一个部门有多个员工，一个员工隶属于一个部门。员工和部门形成了多对一的映射关系）

`Emp`实体类添加部门`Dept`属性：

```java
@Data
public class Emp {

    private Integer eid;

    private String empName;

    private Integer age;

    private String sex;

    private String email;

    private Dept dept;
}
```

#####  6.3.1  使用级联方式处理多对一映射关系

`EmpMapper`：

```java
public interface EmpMapper {
    /**
     * 查询员工以及员工所对应的部门信息
     */
    Emp getEmpAndDeptOne(@Param("eid") Integer eid);
}
```

`EmpMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.EmpMapper">
    
    <!--处理多对一映射关系方式一：级联属性赋值-->
    <resultMap id="empAndDeptResultMapOne" type="Emp">
        <id property="eid" column="eid"></id>
        <result property="empName" column="emp_name"></result>
        <result property="age" column="age"></result>
        <result property="sex" column="sex"></result>
        <result property="email" column="email"></result>
        <result property="dept.did" column="did"></result>
        <result property="dept.deptName" column="dept_name"></result>
    </resultMap>

    <select id="getEmpAndDeptOne" resultMap="empAndDeptResultMapOne">
        select *
        from t_emp
                 left join t_dept on t_emp.did = t_dept.did
        where t_emp.eid = #{eid}
    </select>
</mapper>
```

测试：

```java
public class ResultMapTest {
    /**
     * <p>
     * 处理多对一的映射关系：
     * a>级联属性赋值
     */
    @Test
    public void testGetEmpAndDeptOne() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDeptOne(3);
        System.out.println(emp);
        // Emp(eid=3, empName=王五, age=20, sex=男, email=ywyyw@163.com, dept=Dept(did=3, deptName=C))
    }
}
```

#####  6.3.2  使用association处理多对一映射关系

`EmpMapper`：

```java
public interface EmpMapper {
    /**
     * 查询员工以及员工所对应的部门信息
     */
    Emp getEmpAndDeptTwo(@Param("eid") Integer eid);
}
```

`EmpMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.EmpMapper">
    <!--处理多对一映射关系方式二：association-->
    <resultMap id="empAndDeptResultMapTwo" type="Emp">
        <id property="eid" column="eid"></id>
        <result property="empName" column="emp_name"></result>
        <result property="age" column="age"></result>
        <result property="sex" column="sex"></result>
        <result property="email" column="email"></result>
        <!--association:处理多对一的映射关系-->
        <!--association标签的property属性:需要处理多对的映射关系的属性名-->
        <!--association标签的javaType属性:该属性的类型-->
        <association property="dept" javaType="Dept">
            <id property="did" column="did"></id>
            <result property="deptName" column="dept_name"></result>
        </association>
    </resultMap>

    <select id="getEmpAndDeptTwo" resultMap="empAndDeptResultMapTwo">
        select *
        from t_emp
                 left join t_dept on t_emp.did = t_dept.did
        where t_emp.eid = #{eid}
    </select>

</mapper>
```

测试：

```java
public class ResultMapTest {

    /**
     * 处理多对一的映射关系：
     * b>association
     */
    @Test
    public void testGetEmpAndDeptTwo() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDeptTwo(3);
        System.out.println(emp);
        // Emp(eid=3, empName=王五, age=20, sex=男, email=ywyyw@163.com, dept=Dept(did=3, deptName=C))
    }
}
```

#####  6.3.3 使用分步查询处理多对一映射关系

`EmpMapper`：查询员工信息

```java
public interface EmpMapper {
    /**
     * 通过分步查询查询员工以及员工所对应的部门信息
     * 分步查询第一步：查询员工信息
     */
    Emp getEmpAndDeptByStepOne(@Param("eid") Integer eid);
}
```

`EmpMapper.xml`：查询员工信息

```xml
<mapper namespace="com.atguigu.mybatis.mapper.EmpMapper">
    <resultMap id="empAndDeptByStepResultMap" type="Emp">
        <id property="eid" column="eid"></id>
        <result property="empName" column="emp_name"></result>
        <result property="age" column="age"></result>
        <result property="sex" column="sex"></result>
        <result property="email" column="email"></result>
        <!--select:设置分步查询的sql的唯一标识（namespace.SQLId或mapper接口的全类名.方法名）-->
        <!--column:设置分布查询的条件-->
        <!--fetchType:当开启了全局的延迟加载之后，可通过此属性手动控制延迟加载的效果-->
        <!--fetchType="lazy|eager":lazy表示延迟加载，eager表示立即加载-->
        <association property="dept"
                     select="com.atguigu.mybatis.mapper.DeptMapper.getEmpAndDeptByStepTwo"
                     column="did"
                     fetchType="eager"></association>
    </resultMap>

    <select id="getEmpAndDeptByStepOne" resultMap="empAndDeptByStepResultMap">
        select * from t_emp where eid = #{eid}
    </select>
</mapper>
```

`DeptMapper`：根据员工所对应的部门id查询部门信息

```java
public interface DeptMapper {

    /**
     * 通过分步查询查询员工以及员工所对应的部门信息
     * 分步查询第二步：通过did查询员工所对应的部门
     */
    Dept getEmpAndDeptByStepTwo(@Param("did") Integer did);
}
```

`DeptMapper.xml`：根据员工所对应的部门id查询部门信息

```xml
<mapper namespace="com.atguigu.mybatis.mapper.DeptMapper">
    <select id="getEmpAndDeptByStepTwo" resultType="Dept">
        select * from t_dept where did = #{did}
    </select>
</mapper>
```

测试：查看打印的日志可以发现查询分成两部进行

```java
public class ResultMapTest {
    /**
     * 处理多对一的映射关系：
     * c>分步查询
     */
    @Test
    public void testGetEmpAndDeptByStep() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDeptByStepOne(3);
        // select * from t_emp where eid = ?
        // select * from t_dept where did = ?
        System.out.println(emp);
        // Emp(eid=3, empName=王五, age=20, sex=男, email=ywyyw@163.com, dept=Dept(did=3, deptName=C))
    }
}
```

#####  6.3.4 补充：分布查询的延迟加载

分步查询的优点：可以实现延迟加载，但是必须在核心配置文件中设置全局配置信息： 

- lazyLoadingEnabled：延迟加载的全局开关。当开启时，所有关联对象都会延迟加载 

- aggressiveLazyLoading：当开启时，任何方法的调用都会加载该对象的所有属性。 否则，每个属性会按需加载 

配置了按需加载，获取的数据是什么，就只会执行相应的sql。此时可通过association和 collection中的fetchType属性设置当前的分步查询是否使用延迟加载，fetchType="lazy(延迟加 载)|eager(立即加载)"

**延迟加载演示**：

`mybatis-config.xml`：开启延迟加载

```xml
<!--设置MyBatis的全局配置-->
<settings>
    <!--开启延迟加载-->
    <setting name="lazyLoadingEnabled" value="true"/>
</settings>
```

`EmpMapper.xml`：将`fetchType`属性设置为lazy

```xml
<resultMap id="empAndDeptByStepResultMap" type="Emp">
    <id property="eid" column="eid"></id>
    <result property="empName" column="emp_name"></result>
    <result property="age" column="age"></result>
    <result property="sex" column="sex"></result>
    <result property="email" column="email"></result>
    <!--select:设置分步查询的sql的唯一标识（namespace.SQLId或mapper接口的全类名.方法名）-->
    <!--column:设置分布查询的条件-->
    <!--fetchType:当开启了全局的延迟加载之后，可通过此属性手动控制延迟加载的效果-->
    <!--fetchType="lazy|eager":lazy表示延迟加载，eager表示立即加载-->
    <association property="dept"
                 select="com.atguigu.mybatis.mapper.DeptMapper.getEmpAndDeptByStepTwo"
                 column="did"
                 fetchType="lazy"></association>
</resultMap>

<select id="getEmpAndDeptByStepOne" resultMap="empAndDeptByStepResultMap">
    select * from t_emp where eid = #{eid}
</select>
```

测试：开启延时加载之后，因为后续的代码中只用到了员工的信息，所以只执行查询员工的sql

```java
public class ResultMapTest {
    @Test
    public void testGetEmpAndDeptByStepLazy() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDeptByStepOne(3);
        System.out.println(emp.getEmpName());
        // select * from t_emp where eid = ?
    }
}
```

###  6.4  一对多映射处理

一对多映射场景：一个部门中包含多名员工

`Dept`：部门实体类`Dept`中添加属性`List<Emp>`，存储一个部门下的多个员工`Emp`

```java
@Data
public class Dept {

    private Integer did;

    private String deptName;

    private List<Emp> emps;
}
```

#####  6.4.1 使用collection处理一对多映射

`DeptMapper`：

```java
public interface DeptMapper {
    /**
     * 获取部门以及部门中所有的员工信息
     */
    Dept getDeptAndEmp(@Param("did") Integer did);
}
```

`DeptMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.DeptMapper">

    <resultMap id="deptAndEmpResultMap" type="Dept">
        <id property="did" column="did"></id>
        <result property="deptName" column="dept_name"></result>
        <!--collection：处理一对多的映射关系. ofType：表示该属性所对应的集合中存储数据的类型-->
        <collection property="emps" ofType="Emp">
            <id property="eid" column="eid"></id>
            <result property="empName" column="emp_name"></result>
            <result property="age" column="age"></result>
            <result property="sex" column="sex"></result>
            <result property="email" column="email"></result>
        </collection>
    </resultMap>
    
    <select id="getDeptAndEmp" resultMap="deptAndEmpResultMap">
        select *
        from t_dept
                 left join t_emp on t_dept.did = t_emp.did
        where t_dept.did = #{did}
    </select>

</mapper>
```

测试：

```java
public class ResultMapTest {
    /**
     * 处理一对多的映射关系
     * a>collection
     */
    @Test
    public void testGetDeptAndEmp() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmp(2);
        // select * from t_dept left join t_emp on t_dept.did = t_emp.did where t_dept.did = ?
        System.out.println(dept);
        // Dept(did=2, deptName=B, emps=[Emp(eid=2, empName=李四, age=32, sex=男, email=2929@qq.com, dept=null), Emp(eid=4, empName=钱多多, age=25, sex=男, email=237636@168.com, dept=null)])
    }

}
```

#####  6.4.2 使用分布查询处理多对一映射

`DeptMapper`：分步查询第一步：查询部门信息

```java
public interface DeptMapper {
    /**
     * 通过分步查询查询部门以及部门中所有的员工信息
     * 分步查询第一步：查询部门信息
     */
    Dept getDeptAndEmpByStepOne(@Param("did") Integer did);

}
```

`DeptMapper.xml`：分步查询第一步：查询部门信息

```xml
<mapper namespace="com.atguigu.mybatis.mapper.DeptMapper">
    
    <resultMap id="deptAndEmpByStepResultMap" type="Dept">
        <id property="did" column="did"></id>
        <result property="deptName" column="dept_name"></result>
        <collection property="emps"
                    select="com.atguigu.mybatis.mapper.EmpMapper.getDeptAndEmpByStepTwo"
                    column="did" fetchType="eager"></collection>
    </resultMap>
    
    <select id="getDeptAndEmpByStepOne" resultMap="deptAndEmpByStepResultMap">
        select *
        from t_dept
        where did = #{did}
    </select>
</mapper>
```

`EmpMapper`：分步查询第二步：根据did查询员工信息

```java
public interface EmpMapper {
    /**
     * 通过分步查询查询部门以及部门中所有的员工信息
     * 分步查询第二步：根据did查询员工信息
     */
    List<Emp> getDeptAndEmpByStepTwo(@Param("did") Integer did);

}
```

`EmpMapper.xml`：分步查询第二步：根据did查询员工信息

```xml
<mapper namespace="com.atguigu.mybatis.mapper.EmpMapper">

    <select id="getDeptAndEmpByStepTwo" resultType="Emp">
        select * from t_emp where did = #{did}
    </select>

</mapper>
```

测试：

```java
public class ResultMapTest {

    /**
     * 处理一对多的映射关系
     * b>分步查询
     */

    @Test
    public void testGetDeptAndEmpByStep() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmpByStepOne(2);
        // select * from t_dept where did = ?
        // select * from t_emp where did = ?
        System.out.println(dept);
       // Dept(did=2, deptName=B, emps=[Emp(eid=2, empName=李四, age=32, sex=男, email=2929@qq.com, dept=null), Emp(eid=4, empName=钱多多, age=25, sex=男, email=237636@168.com, dept=null)])
    }
    
}
```

#####  6.4.3 补充：分布查询的延迟加载

分步查询的优点：可以实现延迟加载，但是必须在核心配置文件中设置全局配置信息： 

- lazyLoadingEnabled：延迟加载的全局开关。当开启时，所有关联对象都会延迟加载 

- aggressiveLazyLoading：当开启时，任何方法的调用都会加载该对象的所有属性。 否则，每个 属性会按需加载

实现按需加载，获取的数据是什么，就只会执行相应的sql。此时可通过association和 collection中的fetchType属性设置当前的分步查询是否使用延迟加载，fetchType="lazy(延迟加 载)|eager(立即加载)"

## 7. 动态sql

Mybatis框架的动态SQL技术是一种根据特定条件动态拼装SQL语句的功能，它存在的意义是为了解决拼接SQL语句字符串时的痛点问题

###  7.1 if标签

if标签可通过test属性的表达式进行判断，若表达式的结果为true，则标签中的内容会执行；反之标签中 的内容不会执行

`DynamicSQLMapper`：

```java
public interface DynamicSQLMapper {
    /**
     * 多条件查询 (if标签)
     */
    List<Emp> getEmpByConditionIf(Emp emp);
}
```

`DynamicSQLMapper.xml`：加上恒成立条件`1=1`，即便所有条件都不满足，`where`关键字后面仍然有一个条件判断语句，从而保证sql语句的语法正确

```xml
<mapper namespace="com.atguigu.mybatis.mapper.DynamicSQLMapper">

    <select id="getEmpByConditionIf" resultType="Emp">
        select * from t_emp where 1=1
        <if test="empName != null and empName != ''">
            emp_name = #{empName}
        </if>
        <if test="age != null and age != ''">
            and age = #{age}
        </if>
        <if test="sex != null and sex != ''">
            and sex = #{sex}
        </if>
        <if test="email != null and email != ''">
            and email = #{email}
        </if>
    </select>
    
</mapper>
```

为`Emp`添加构造函数：

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {

    private Integer eid;

    private String empName;

    private Integer age;

    private String sex;

    private String email;

    private Dept dept;

    public Emp(Integer eid, String empName, Integer age, String sex, String email) {
        this.eid = eid;
        this.empName = empName;
        this.age = age;
        this.sex = sex;
        this.email = email;
    }
}
```

测试：

```java
public class DynamicSQLMapperTest {

    /**
     * 动态SQL：
     * 1、if：根据标签中test属性所对应的表达式决定标签中的内容是否需要拼接到SQL中
     */
    @Test
    public void getEmpByConditionIf() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> list = mapper.getEmpByConditionIf(new Emp(null, "", null, "", null));
        // select * from t_emp where 1=1
        System.out.println(list);
        List<Emp> list2 = mapper.getEmpByConditionIf(new Emp(3, "", 23, "男", "2762373@qq.com"));
        // select * from t_emp where 1=1 and age = ? and sex = ? and email = ?
        System.out.println(list2);
    }
    
}
```

###  7.2 where标签

where和if一般结合使用： 

- 若where标签中的if条件都不满足，则where标签没有任何功能，即不会添加where关键字 b

- 若where标签中的if条件满足，则where标签会自动添加where关键字，并将条件最前方多余的 and去掉 

- 注意：where标签不能去掉条件最后多余的and

`DynamicSQLMapper`：

```java
public interface DynamicSQLMapper {
    /**
     * 多条件查询（where标签）
     */
    List<Emp> getEmpByConditionWhere(Emp emp);
}
```

`DynamicSQLMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.DynamicSQLMapper">

    <select id="getEmpByConditionWhere" resultType="Emp">
        select * from t_emp
        <where>
            <if test="empName != null and empName != ''">
                emp_name = #{empName}
            </if>
            <if test="age != null and age != ''">
                and age = #{age}
            </if>
            <if test="sex != null and sex != ''">
                or sex = #{sex}
            </if>
            <if test="email != null and email != ''">
                and email = #{email}
            </if>
        </where>
    </select>

</mapper>
```

测试：

```java
public class DynamicSQLMapperTest {
    /**
     * 动态SQL：
     * 2、where：
     * 当where标签中有内容时，会自动生成where关键字，并且将内容前多余的and或or去掉
     * 当where标签中没有内容时，此时where标签没有任何效果,也就是不会生成where关键字
     * 注意：where标签不能将其中内容后面多余的and或or去掉
     */

    @Test
    public void testGetEmpByConditionWhere() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> list = mapper.getEmpByConditionWhere(new Emp(null, "", null, "", null));
        // select * from t_emp
        System.out.println(list);
        List<Emp> list2 = mapper.getEmpByConditionWhere(new Emp(3, "", 23, "男", "2762373@qq.com"));
        // select * from t_emp WHERE age = ? or sex = ? and email = ?
        System.out.println(list2);
    }
}
```

###  7.3 trim标签

trim标签用于去掉或添加标签中的内容 

trim标签常用属性： 

- prefix：在trim标签中的内容的前面添加某些内容 

- prefixOverrides：在trim标签中的内容的前面去掉某些内容 

- suffix：在trim标签中的内容的后面添加某些内容 

- suffixOverrides：在trim标签中的内容的后面去掉某些内容

`DynamicSQLMapper`：

```java
public interface DynamicSQLMapper {
    /**
     * 多条件查询（Trim标签）
     */
    List<Emp> getEmpByConditionTrim(Emp emp);
}
```

`DynamicSQLMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.DynamicSQLMapper">

    <sql id="empColumns">eid
    ,emp_name,age,sex,email</sql>

    <select id="getEmpByConditionTrim" resultType="Emp">
        select <include refid="empColumns"></include> from t_emp
        <trim prefix="where" suffixOverrides="and|or">
            <if test="empName != null and empName != ''">
                emp_name = #{empName} and
            </if>
            <if test="age != null and age != ''">
                age = #{age} or
            </if>
            <if test="sex != null and sex != ''">
                sex = #{sex} and
            </if>
            <if test="email != null and email != ''">
                email = #{email}
            </if>
        </trim>
    </select>
</mapper>
```

测试：

```java
public class DynamicSQLMapperTest {
    /**
     * 动态SQL：
     * 3、trim：
     * 若标签中有内容时：
     * prefix|suffix：将trim标签中内容前面或后面添加指定内容
     * suffixOverrides|prefixOverrides：将trim标签中内容前面或后面去掉指定内容
     * 若标签中没有内容时，trim标签也没有任何效果
     */

    @Test
    public void testGetEmpByConditionTrim() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> list = mapper.getEmpByConditionTrim(new Emp(null, "", null, "", null));
        // select eid ,emp_name,age,sex,email from t_emp
        System.out.println(list);
        List<Emp> list2 = mapper.getEmpByConditionTrim(new Emp(3, "", 23, "男", "2762373@qq.com"));
        // select eid ,emp_name,age,sex,email from t_emp where age = ? or sex = ? and email = ?
        System.out.println(list2);
    }
}
```

###  7.4 choose、when、otherwise标签

choose、when、otherwise相当于if...else if..else

`DynamicSQLMapper`：

```java
public interface DynamicSQLMapper {
    /**
     * 测试choose、when、otherwise
     */
    List<Emp> getEmpByChoose(Emp emp);
}
```

`DynamicSQLMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.DynamicSQLMapper">

    <select id="getEmpByChoose" resultType="Emp">
        select * from t_emp
        <where>
            <choose>
                <when test="empName != null and empName != ''">
                    emp_name = #{empName}
                </when>
                <when test="age != null and age != ''">
                    age = #{age}
                </when>
                <when test="sex != null and sex != ''">
                    sex = #{sex}
                </when>
                <when test="email != null and email != ''">
                    email = #{email}
                </when>
                <otherwise>
                    did = 1
                </otherwise>
            </choose>
        </where>
    </select>
</mapper>
```

测试：

```java
public class DynamicSQLMapperTest {
    /**
     * 动态SQL：
     * 4、choose、when、otherwise，相当于if...else if...else
     * when至少要有一个，otherwise最多只能有一个
     */
    
    @Test
    public void testGetEmpByChoose() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> list = mapper.getEmpByChoose(new Emp(null, "", null, "", ""));
        //  select * from t_emp WHERE did = 1
        List<Emp> list2 = mapper.getEmpByChoose(new Emp(null, "", 34, "", ""));
        // select * from t_emp WHERE age = ?
        List<Emp> list3 = mapper.getEmpByChoose(new Emp(null, "张三", 34, "男", "2376236@qq.com"));
        // select * from t_emp WHERE emp_name = ?
    }
    
}
```

###  7.5 foreach标签

**foreach标签**：

- collection：设置需要循环的数组或集合
- item：表示数组或集合中的每一个数据
- separator：循环体之间的分割符
- open：foreach标签所循环的所有内容的开始符
- close：foreach标签所循环的所有内容的结束符

#####  7.5.1 批量删除

**批量删除案例一**：

`DynamicSQLMapper`：

```java
public interface DynamicSQLMapper {
    /**
     * 通过数组实现批量删除
     */
    int deleteMoreByArrayOne(@Param("eids") Integer[] eids);
}
```

`DynamicSQLMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.DynamicSQLMapper">

    <delete id="deleteMoreByArrayOne">
        delete from t_emp where eid in
        <foreach collection="eids" item="eid" separator="," open="(" close=")">
            #{eid}
        </foreach>
    </delete>

</mapper>
```

测试：

```java
public class DynamicSQLMapperTest {
    /**
     * 动态SQL：
     * 5、foreach
     * collection:设置需要循环的数组或集合
     * item:表示数组或集合中的每一个数据
     * separator:循环体之间的分割符
     * open:foreach标签所循环的所有内容的开始符
     * close:foreach标签所循环的所有内容的结束符
     */
    @Test
    public void testDeleteMoreByArrayOne() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        int result = mapper.deleteMoreByArrayOne(new Integer[]{6, 7, 8});
        // delete from t_emp where eid in ( ? , ? , ? )
        System.out.println(result);
    }
}
```

**批量删除案例二**：

`DynamicSQLMapper`：

```java
public interface DynamicSQLMapper {
    /**
     * 通过数组实现批量删除
     */
    int deleteMoreByArrayTwo(@Param("eids") Integer[] eids);
}
```

`DynamicSQLMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.DynamicSQLMapper">

    <delete id="deleteMoreByArrayTwo">
        delete from t_emp where
        <foreach collection="eids" item="eid" separator="or">
            eid = #{eid}
        </foreach>
    </delete>

</mapper>
```

测试：

```java
public class DynamicSQLMapperTest {

    /**
     * 动态SQL：
     * 5、foreach
     * collection:设置需要循环的数组或集合
     * item:表示数组或集合中的每一个数据
     * separator:循环体之间的分割符
     * open:foreach标签所循环的所有内容的开始符
     * close:foreach标签所循环的所有内容的结束符
     */

    @Test
    public void testDeleteMoreByArrayTwo() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        int result = mapper.deleteMoreByArrayTwo(new Integer[]{6, 7, 8});
        // delete from t_emp where eid = ? or eid = ? or eid = ?
        System.out.println(result);
    }

}
```

#####  7.5.2  批量添加

`DynamicSQLMapper`：

```java
public interface DynamicSQLMapper {
    /**
     * 通过list集合实现批量添加
     */
    int insertMoreByList(@Param("emps") List<Emp> emps);
}
```

`DynamicSQLMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.DynamicSQLMapper">
    
    <insert id="insertMoreByList">
        insert into t_emp values
        <foreach collection="emps" item="emp" separator=",">
            (null,#{emp.empName},#{emp.age},#{emp.sex},#{emp.email},null)
        </foreach>
    </insert>

</mapper>
```

测试：

```java
public class DynamicSQLMapperTest {
    /**
     * 动态SQL：
     * 5、foreach
     * collection:设置需要循环的数组或集合
     * item:表示数组或集合中的每一个数据
     * separator:循环体之间的分割符
     * open:foreach标签所循环的所有内容的开始符
     * close:foreach标签所循环的所有内容的结束符
     */

    @Test
    public void testInsertMoreByList() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Emp emp1 = new Emp(null, "a1", 23, "男", "123@qq.com");
        Emp emp2 = new Emp(null, "a2", 23, "男", "123@qq.com");
        Emp emp3 = new Emp(null, "a3", 23, "男", "123@qq.com");
        List<Emp> emps = Arrays.asList(emp1, emp2, emp3);
        System.out.println(mapper.insertMoreByList(emps));
        // insert into t_emp values (null,?,?,?,?,null) , (null,?,?,?,?,null) , (null,?,?,?,?,null)
    }
    
}
```

###  7.6 sql标签

sql片段，可以记录一段公共sql片段，在使用的地方通过include标签进行引入

`DynamicSQLMapper.xml`：

```xml
<sql id="empColumns">eid
,emp_name,age,sex,email</sql>

<select id="getEmpByConditionTrim" resultType="Emp">
    select <include refid="empColumns"></include> from t_emp
    <trim prefix="where" suffixOverrides="and|or">
        <if test="empName != null and empName != ''">
            emp_name = #{empName} and
        </if>
        <if test="age != null and age != ''">
            age = #{age} or
        </if>
        <if test="sex != null and sex != ''">
            sex = #{sex} and
        </if>
        <if test="email != null and email != ''">
            email = #{email}
        </if>
    </trim>
</select>
```

##  8. Mybatis缓存

###  8.1  MyBatis的一级缓存

#####  8.1.1 一级缓存简介 & 一级缓存失效情况

**一级缓存是SqlSession级别的**。通过同一个SqlSession查询的数据会被缓存，下次查询相同的数据，就会从缓存中直接获取，不会从数据库重新访问。注意：缓存只针对查询有效



**一级缓存失效的四种情况**：

- 不同的SqlSession对应不同的一级缓存 
- 同一个SqlSession但是查询条件不同：缓存的是查询过的数据，如果查询时条件和之前的不一致，说明之前没有进行过数据查询及缓存，所以就不可能从缓存中获取到数据，只能去数据库中获取数据
- 同一个SqlSession两次查询期间执行了任何一次增删改操作 
- 同一个SqlSession两次查询期间手动清空了缓存

#####  8.1.2 一级缓存演示

`CacheMapper`：

```java
public interface CacheMapper {
    Emp getEmpByEid(@Param("eid") Integer eid);
}
```

`CacheMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.CacheMapper">
    <select id="getEmpByEid" resultType="Emp">
        select * from t_emp where eid = #{eid}
    </select>
</mapper>
```

测试现象及结论：

- 间隔两次的相同查询，第二次没有执行sql，但是同样获得了数据，说明第二次的数据来自mybatis的缓存

- 同一个sqlSession下的同一个mapper,一级缓存生效。同一个sqlSession下的不同mapper,一级缓存也生效

- 结论：一级缓存是SqlSession级别的

```java
public class CacheMapperTest {

    // 同一个sqlSession下的同一个mapper,一级缓存生效
    @Test
    public void testOneCacheSameSqlSessionFirst() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper1 = sqlSession.getMapper(CacheMapper.class);

        // select * from t_emp where eid = ?
        Emp emp1 = mapper1.getEmpByEid(1);
        System.out.println(emp1);

        // 第二次查询没有执行任何sql
        Emp emp2 = mapper1.getEmpByEid(1);
        System.out.println(emp2);
    }

    // 同一个sqlSession下的不同mapper,一级缓存也生效
    @Test
    public void testOneCacheSameSqlSessionSecond() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper1 = sqlSession.getMapper(CacheMapper.class);
        CacheMapper mapper2 = sqlSession.getMapper(CacheMapper.class);

        // select * from t_emp where eid = ?
        Emp emp1 = mapper1.getEmpByEid(1);
        System.out.println(emp1);

        // 第二次查询没有执行任何sql
        Emp emp2 = mapper2.getEmpByEid(1);
        System.out.println(emp2);
    }
}
```

#####  8.1.3  一级缓存失效情况一：SqlSession不同

测试：两个不同的SqlSession执行进行相同的查询，发现两个查询都进行了数据库查询，缓存失效

```java
public class CacheMapperTest {
    //  一级缓存失效情况一：不同的SqlSession对应不同的一级缓存
    @Test
    public void testOneCacheDifferentSqlSession() {
        SqlSession sqlSession1 = SqlSessionUtils.getSqlSession();
        SqlSession sqlSession2 = SqlSessionUtils.getSqlSession();
        CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
        CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);

        // select * from t_emp where eid = ?
        Emp emp1 = mapper1.getEmpByEid(1);
        System.out.println(emp1);

        // select * from t_emp where eid = ?
        Emp emp2 = mapper2.getEmpByEid(1);
        System.out.println(emp2);
    }
}
```

#####  8.1.4  一级缓存失效情况二：同一个SqlSession但查询条件不同

```java
public class CacheMapperTest {
    // 一级缓存失效情况二：同一个SqlSession但查询条件不同
    @Test
    public void testOneCacheSameSqlSessionThird() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);

        // select * from t_emp where eid = ?
        Emp emp1 = mapper.getEmpByEid(1);
        System.out.println(emp1);

        //  select * from t_emp where eid = ?
        Emp emp2 = mapper.getEmpByEid(4);
        System.out.println(emp2);
    }
}
```

#####  8.1.5 一级缓存失效情况三：查询期间执行增删改操作 

`CacheMapper`：

```java
public interface CacheMapper {
    void insertEmp(Emp emp);
}
```

`CacheMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.CacheMapper">
    <insert id="insertEmp">
        insert into t_emp values(null,#{empName},#{age},#{sex},#{email},null)
    </insert>
</mapper>
```

测试：两次查询sql都执行了，说明缓存失效

```java
public class CacheMapperTest {
    // 一级缓存失效情况三：同一个SqlSession两次查询期间执行了任何一次增删改操作
    @Test
    public void testOneCacheInsert() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp1 = mapper.getEmpByEid(1);
        // select * from t_emp where eid = ?
        System.out.println(emp1);
        mapper.insertEmp(new Emp(null, "abc", 23, "男", "123@qq.com"));
        // insert into t_emp values(null,?,?,?,?,null)

        Emp emp2 = mapper.getEmpByEid(1);
        // select * from t_emp where eid = ?
        System.out.println(emp2);
    }
}
```

#####  8.1.6 一级缓存失效情况四：两次查询期间清空了缓存

测试：

```java
public class CacheMapperTest {
    // 一级缓存失效情况四：同一个SqlSession两次查询期间手动清空了缓存
    @Test
    public void testOneCacheClear() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp1 = mapper.getEmpByEid(1);
        // select * from t_emp where eid = ?
        System.out.println(emp1);
        sqlSession.clearCache();
        Emp emp2 = mapper.getEmpByEid(1);
        // select * from t_emp where eid = ?
        System.out.println(emp2);
    }
}
```

###  8.2 MyBatis的二级缓存

二级缓存是SqlSessionFactory级别，通过同一个SqlSessionFactory创建的SqlSession查询的结果会被缓存此后若再次执行相同的查询语句，结果就会从缓存中获取 

#####  8.2.1 二级缓存开启的条件

**二级缓存开启的条件**： 

- 在核心配置文件中，设置全局配置属性cacheEnabled="true"，默认为true，不需要设置 
- 查询的数据所转换的实体类类型必须实现序列化的接口

- 在映射文件中设置标签`<cache/>`

- 二级缓存必须在SqlSession关闭或提交之后有效

使二级缓存失效的情况： 两次查询之间执行了任意的增删改，会使一级和二级缓存同时失效

**二级缓存演示**：

实体类类型必须实现序列化的接口：

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp implements Serializable {

    private Integer eid;

    private String empName;

    private Integer age;

    private String sex;

    private String email;

    private Dept dept;

    public Emp(Integer eid, String empName, Integer age, String sex, String email) {
        this.eid = eid;
        this.empName = empName;
        this.age = age;
        this.sex = sex;
        this.email = email;
    }
}
```

在映射文件中设置标签`<cache/>`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.CacheMapper">
    
    <cache/>
    
    <select id="getEmpByEid" resultType="Emp">
        select * from t_emp where eid = #{eid}
    </select>


    <insert id="insertEmp">
        insert into t_emp values(null,#{empName},#{age},#{sex},#{email},null)
    </insert>

</mapper>
```

测试：二级缓存必须在SqlSession关闭或提交之后有效

```java
public class CacheMapperTest {
    @Test
    public void testTwoCache() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
            CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
            System.out.println(mapper1.getEmpByEid(1));
            // select * from t_emp where eid = ?
            sqlSession1.close();
            SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
            CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);
            System.out.println(mapper2.getEmpByEid(1));
            // 没有执行sql
            sqlSession2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
```

#####  8.2.2 二级缓存的相关配置

因为内存是有限制的，缓存不可能无限制缓存。当缓存到一定的数据量之后，就需要撤销一些被缓存的数据

当缓存超过阈值后，该撤销哪些缓存，这就需要具体的缓存策略来决定

在mapper配置文件中添加的cache标签可以设置一些属性，缓存的相关配置如下：

- eviction属性：缓存回收策略 

- LRU（Least Recently Used） –最近最少使用的：移除最长时间不被使用的对象

- FIFO（First in First out） – 先进先出：按对象进入缓存的顺序来移除它们

- SOFT – 软引用：移除基于垃圾回收器状态和软引用规则的对象

- WEAK – 弱引用：更积极地移除基于垃圾收集器状态和弱引用规则的对象。 默认的是 LRU

- flushInterval属性：刷新间隔，单位毫秒 默认情况是不设置，也就是没有刷新间隔，缓存仅仅调用语句时刷新

- size属性：引用数目，正整数 代表缓存最多可以存储多少个对象，太大容易导致内存溢出

- readOnly属性：只读，true/false 
  - true：只读缓存；会给所有调用者返回缓存对象的相同实例。因此这些对象不能被修改。这提供了 很重要的性能优势
  - false：读写缓存；会返回缓存对象的拷贝（通过序列化）。这会慢一些，但是安全，因此默认是 false

#####  8.2.3 MyBatis缓存查询的顺序

先查询二级缓存，因为二级缓存中可能会有其他程序已经查出来的数据，可以拿来直接使用

如果二级缓存没有命中，再查询一级缓存

如果一级缓存也没有命中，则查询数据库

SqlSession关闭之后，一级缓存中的数据会写入二级缓存

###  8.3  整合第三方缓存EHCache

MyBatis作为一个持久层框架，在对缓存的支持上功能不是特别完善，所以可以整合一些第三方技术实现缓存



1.添加依赖

```xml
<!-- Mybatis EHCache整合包 -->
<dependency>
<groupId>org.mybatis.caches</groupId>
<artifactId>mybatis-ehcache</artifactId>
<version>1.2.1</version>
</dependency>
<!-- slf4j日志门面的一个具体实现 -->
<dependency>
<groupId>ch.qos.logback</groupId>
<artifactId>logback-classic</artifactId>
<version>1.2.3</version>
</dependency>
```

2.各jar包功能：

| jar包名称       | 作用                            |
| --------------- | ------------------------------- |
| mybatis-ehcache | Mybatis和EHCache的整合包        |
| ehcache         | EHCache核心包                   |
| slf4j-api       | SLF4J日志门面包                 |
| logback-classic | 支持SLF4J门面接口的一个具体实现 |

3.创建EHCache的配置文件`ehcache.xml`：

```xml
<?xml version="1.0" encoding="utf-8" ?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="../config/ehcache.xsd">
    <!-- 磁盘保存路径 -->
    <diskStore path="D:\atguigu\ehcache"/>

    <defaultCache
            maxElementsInMemory="1000"
            maxElementsOnDisk="10000000"
            eternal="false"
            overflowToDisk="true"
            timeToIdleSeconds="120"
            timeToLiveSeconds="120"
            diskExpiryThreadIntervalSeconds="120"
            memoryStoreEvictionPolicy="LRU">
    </defaultCache>
</ehcache>
```

4.EHCache配置文件说明

| 属性名                          | 是 否 必 须 | 作用                                                         |
| ------------------------------- | ----------- | ------------------------------------------------------------ |
| maxElementsInMemory             | 是          | 在内存中缓存的element的最大数目                              |
| maxElementsOnDisk               | 是          | 在磁盘上缓存的element的最大数目，若是0表示无 穷大            |
| eternal                         | 是          | 设定缓存的elements是否永远不过期。 如果为 true，则缓存的数据始终有效， 如果为false那么还 要根据timeToIdleSeconds、timeToLiveSeconds 判断 |
| overflowToDisk                  | 是          | 设定当内存缓存溢出的时候是否将过期的element 缓存到磁盘上     |
| timeToIdleSeconds               | 否          | 当缓存在EhCache中的数据前后两次访问的时间超 过timeToIdleSeconds的属性取值时， 这些数据便 会删除，默认值是0,也就是可闲置时间无穷大 |
| timeToLiveSeconds               | 否          | 缓存element的有效生命期，默认是0.,也就是 element存活时间无穷大 |
| diskSpoolBufferSizeMB           | 否          | DiskStore(磁盘缓存)的缓存区大小。默认是 30MB。每个Cache都应该有自己的一个缓冲区 |
| diskPersistent                  | 否          | 在VM重启的时候是否启用磁盘保存EhCache中的数 据，默认是false  |
| diskExpiryThreadIntervalSeconds | 否          | 磁盘缓存的清理线程运行间隔，默认是120秒。每 个120s， 相应的线程会进行一次EhCache中数据的 清理工作 |
| memoryStoreEvictionPolicy       | 否          | 当内存缓存达到最大，有新的element加入的时 候， 移除缓存中element的策略。 默认是LRU（最 近最少使用），可选的有LFU（最不常使用）和 FIFO（先进先出） |

5.设置二级缓存的类型

```xml
<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
```

6.加入logback日志

存在SLF4]时，作为简易日志的log4j将失效，此时我们需要借助SLF4J的具体实现logback来打印日志。

创建logback的配置文件logback.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration debug="true">
    <!-- 指定日志输出的位置 -->
    <appender name="STDOUT"
              class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <!-- 日志输出的格式 -->
            <!-- 按照顺序分别是：时间、日志级别、线程名称、打印日志的类、日志主体内容、换行 -->
            <pattern>[%d{HH:mm:ss.SSS}] [%-5level] [%thread] [%logger] [%msg]%n</pattern>
        </encoder>
    </appender>

    <!-- 设置全局日志级别。日志级别按顺序分别是：DEBUG、INFO、WARN、ERROR -->
    <!-- 指定任何一个日志级别都只打印当前级别和后面级别的日志。 -->
    <root level="DEBUG">
        <!-- 指定打印日志的appender，这里通过“STDOUT”引用了前面配置的appender -->
        <appender-ref ref="STDOUT" />
    </root>

    <!-- 根据特殊需求指定局部日志级别 -->
    <logger name="com.atguigu.crowd.mapper" level="DEBUG"/>

</configuration>
```

7.在mapper映射文件中利用`<cache>标签`的type属性来使第三方日志中框架生效

`CacheMapper.xml`：

```xml
<mapper namespace="com.atguigu.mybatis.mapper.CacheMapper">
<!--    <cache/>-->
    <cache type="org.mybatis.caches.ehcache.EhcacheCache" />

    <select id="getEmpByEid" resultType="Emp">
        select * from t_emp where eid = #{eid}
    </select>


    <insert id="insertEmp">
        insert into t_emp values(null,#{empName},#{age},#{sex},#{email},null)
    </insert>
</mapper>
```

8.测试：对之前的查询进行缓存测试

```java
public class CacheMapperTest {

    @Test
    public void testOneCacheSameSqlSessionFirst() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper1 = sqlSession.getMapper(CacheMapper.class);

        Emp emp1 = mapper1.getEmpByEid(1);
        System.out.println(emp1);

        Emp emp2 = mapper1.getEmpByEid(1);
        System.out.println(emp2);
    }

    @Test
    public void testOneCacheSameSqlSessionSecond() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper1 = sqlSession.getMapper(CacheMapper.class);
        CacheMapper mapper2 = sqlSession.getMapper(CacheMapper.class);

        Emp emp1 = mapper1.getEmpByEid(1);
        System.out.println(emp1);

        Emp emp2 = mapper2.getEmpByEid(1);
        System.out.println(emp2);
    }


    @Test
    public void testOneCacheDifferentSqlSession() {
        SqlSession sqlSession1 = SqlSessionUtils.getSqlSession();
        SqlSession sqlSession2 = SqlSessionUtils.getSqlSession();
        CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
        CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);

        Emp emp1 = mapper1.getEmpByEid(1);
        System.out.println(emp1);

        Emp emp2 = mapper2.getEmpByEid(1);
        System.out.println(emp2);
    }

    @Test
    public void testOneCacheSameSqlSessionThird() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);

        Emp emp1 = mapper.getEmpByEid(1);
        System.out.println(emp1);

        Emp emp2 = mapper.getEmpByEid(4);
        System.out.println(emp2);
    }


    @Test
    public void testOneCacheInsert() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp1 = mapper.getEmpByEid(1);
        System.out.println(emp1);
        mapper.insertEmp(new Emp(null, "abc", 23, "男", "123@qq.com"));

        Emp emp2 = mapper.getEmpByEid(1);
        System.out.println(emp2);
    }

    @Test
    public void testOneCacheClear() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp1 = mapper.getEmpByEid(1);
        System.out.println(emp1);
        sqlSession.clearCache();
        Emp emp2 = mapper.getEmpByEid(1);
        System.out.println(emp2);
    }


    @Test
    public void testTwoCache() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
            CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
            System.out.println(mapper1.getEmpByEid(1));
            sqlSession1.close();
            SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
            CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);
            System.out.println(mapper2.getEmpByEid(1));
            sqlSession2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
```

##  9. MyBatis的逆向工程

###  9.1 正向过程\逆向工程

逆向工程：先创建数据库表，由框架负责根据数据库表，反向生成如下资源：

- Java实体类

- Mapper接口

- Mapper映射文件

正向工程：先创建Java实体类，由框架负责根据实体类生成数据库表。Hibernate是支持正向工程的

###  9.2 创建逆向工程

**1.添加依赖和插件**：

```xml
<!-- 依赖MyBatis核心包 -->
<dependencies>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.7</version>
    </dependency>
</dependencies>
<!-- 控制Maven在构建过程中相关配置 -->
<build>
    <!-- 构建过程中用到的插件 -->
    <plugins>
        <!-- 具体插件，逆向工程的操作是以构建过程中插件形式出现的 -->
        <plugin>
            <groupId>org.mybatis.generator</groupId>
            <artifactId>mybatis-generator-maven-plugin</artifactId>
            <version>1.3.0</version>
            <!-- 插件的依赖 -->
            <dependencies>
                <!-- 逆向工程的核心依赖 -->
                <dependency>
                    <groupId>org.mybatis.generator</groupId>
                    <artifactId>mybatis-generator-core</artifactId>
                    <version>1.3.2</version>
                </dependency>
                <!-- 数据库连接池 -->
                <dependency>
                    <groupId>com.mchange</groupId>
                    <artifactId>c3p0</artifactId>
                    <version>0.9.2</version>
                </dependency>
                <!-- MySQL驱动 -->
                <dependency>
                    <groupId>mysql</groupId>
                    <artifactId>mysql-connector-java</artifactId>
                    <version>5.1.8</version>
                </dependency>
            </dependencies>
        </plugin>
    </plugins>
</build>
```

**3.创建逆向工程的配置文件**：

文件名必须是：`generatorConfig.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <!--
            targetRuntime: 执行生成的逆向工程的版本
                    MyBatis3Simple: 生成基本的CRUD（清新简洁版）
                    MyBatis3: 生成带条件的CRUD（奢华尊享版）
     -->
    <context id="DB2Tables" targetRuntime="MyBatis3">
        <!-- 数据库的连接信息 -->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://192.168.56.10:3306/mybatis"
                        userId="root"
                        password="root">
        </jdbcConnection>
        <!-- javaBean的生成策略-->
        <javaModelGenerator targetPackage="com.atguigu.mybatis.pojo" targetProject=".\src\main\java">
            <property name="enableSubPackages" value="true" />
            <property name="trimStrings" value="true" />
        </javaModelGenerator>
        <!-- SQL映射文件的生成策略 -->
        <sqlMapGenerator targetPackage="com.atguigu.mybatis.mapper"  targetProject=".\src\main\resources">
            <property name="enableSubPackages" value="true" />
        </sqlMapGenerator>
        <!-- Mapper接口的生成策略 -->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.atguigu.mybatis.mapper"  targetProject=".\src\main\java">
            <property name="enableSubPackages" value="true" />
        </javaClientGenerator>
        <!-- 逆向分析的表 -->
        <!-- tableName设置为*号，可以对应所有表，此时不写domainObjectName -->
        <!-- domainObjectName属性指定生成出来的实体类的类名 -->
        <table tableName="t_emp" domainObjectName="Emp"/>
        <table tableName="t_dept" domainObjectName="Dept"/>
    </context>
</generatorConfiguration>
```

**4.执行MBG插件的generate目标**：

点击`Plugins`下的MBG插件，就会生成对应的代码

![image-20231024025524977](https://cdn.jsdelivr.net/gh/Li-ShiLin/images/linux/image-20231024025524977.png)



###  9.3 逆向工程生成的CURD方法解读

**1.逆向工程生成的CURD方法解读**：

- 所有包含`ByExample`的方法都表示`根据条件进行操作`

- 所有包含`ByPrimaryKey`的方法都表示`根据主键进行操作`

- 所有包含`Selective`的方法都表示`根据条件进行选择性操作`

**2.几个修改方法比较**：

- `updateByExampleSelective`：根据条件进行选择性修改。如果某个属性的值为null，就不会对这个属性进行修改
- `updateByExample`：如果某个属性的值为null，就会将这个字段修改成null

**3.mapper接口示例**：

`DeptMapper`：

```java
public interface DeptMapper {

    // 根据条件进行统计（可以根据任意的字段进行条件统计）
    int countByExample(DeptExample example);

    // 根据条件进行删除
    int deleteByExample(DeptExample example);

    // 根据主键进行删除
    int deleteByPrimaryKey(Integer did);

    int insert(Dept record);

    // 选择性添加（如果为null就不会进行添加）
    int insertSelective(Dept record);

    List<Dept> selectByExample(DeptExample example);

    Dept selectByPrimaryKey(Integer did);

    int updateByExampleSelective(@Param("record") Dept record, @Param("example") DeptExample example);

    int updateByExample(@Param("record") Dept record, @Param("example") DeptExample example);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);
}
```

**4.mapper映射文件示例**：

`DeptMapper.xml`：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.atguigu.mybatis.mapper.DeptMapper">
    <resultMap id="BaseResultMap" type="com.atguigu.mybatis.pojo.Dept">
        <id column="did" property="did" jdbcType="INTEGER"/>
        <result column="dept_name" property="deptName" jdbcType="VARCHAR"/>
    </resultMap>
    <sql id="Example_Where_Clause">
        <where>
            <foreach collection="oredCriteria" item="criteria" separator="or">
                <if test="criteria.valid">
                    <trim prefix="(" suffix=")" prefixOverrides="and">
                        <foreach collection="criteria.criteria" item="criterion">
                            <choose>
                                <when test="criterion.noValue">
                                    and ${criterion.condition}
                                </when>
                                <when test="criterion.singleValue">
                                    and ${criterion.condition} #{criterion.value}
                                </when>
                                <when test="criterion.betweenValue">
                                    and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}
                                </when>
                                <when test="criterion.listValue">
                                    and ${criterion.condition}
                                    <foreach collection="criterion.value" item="listItem" open="(" close=")"
                                             separator=",">
                                        #{listItem}
                                    </foreach>
                                </when>
                            </choose>
                        </foreach>
                    </trim>
                </if>
            </foreach>
        </where>
    </sql>
    <sql id="Update_By_Example_Where_Clause">
        <where>
            <foreach collection="example.oredCriteria" item="criteria" separator="or">
                <if test="criteria.valid">
                    <trim prefix="(" suffix=")" prefixOverrides="and">
                        <foreach collection="criteria.criteria" item="criterion">
                            <choose>
                                <when test="criterion.noValue">
                                    and ${criterion.condition}
                                </when>
                                <when test="criterion.singleValue">
                                    and ${criterion.condition} #{criterion.value}
                                </when>
                                <when test="criterion.betweenValue">
                                    and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}
                                </when>
                                <when test="criterion.listValue">
                                    and ${criterion.condition}
                                    <foreach collection="criterion.value" item="listItem" open="(" close=")"
                                             separator=",">
                                        #{listItem}
                                    </foreach>
                                </when>
                            </choose>
                        </foreach>
                    </trim>
                </if>
            </foreach>
        </where>
    </sql>
    <sql id="Base_Column_List">
        did
        , dept_name
    </sql>
    <select id="selectByExample" resultMap="BaseResultMap" parameterType="com.atguigu.mybatis.pojo.DeptExample">
        select
        <if test="distinct">
            distinct
        </if>
        <include refid="Base_Column_List"/>
        from t_dept
        <if test="_parameter != null">
            <include refid="Example_Where_Clause"/>
        </if>
        <if test="orderByClause != null">
            order by ${orderByClause}
        </if>
    </select>
    <select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="java.lang.Integer">
        <!--
          WARNING - @mbggenerated
          This element is automatically generated by MyBatis Generator, do not modify.
          This element was generated on Tue Oct 24 03:18:56 CST 2023.
        -->
        select
        <include refid="Base_Column_List"/>
        from t_dept
        where did = #{did,jdbcType=INTEGER}
    </select>
    <delete id="deleteByPrimaryKey" parameterType="java.lang.Integer">
        delete
        from t_dept
        where did = #{did,jdbcType=INTEGER}
    </delete>
    <delete id="deleteByExample" parameterType="com.atguigu.mybatis.pojo.DeptExample">
        delete from t_dept
        <if test="_parameter != null">
            <include refid="Example_Where_Clause"/>
        </if>
    </delete>
    <insert id="insert" parameterType="com.atguigu.mybatis.pojo.Dept">
        insert into t_dept (did, dept_name)
        values (#{did,jdbcType=INTEGER}, #{deptName,jdbcType=VARCHAR})
    </insert>
    <insert id="insertSelective" parameterType="com.atguigu.mybatis.pojo.Dept">
        insert into t_dept
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <if test="did != null">
                did,
            </if>
            <if test="deptName != null">
                dept_name,
            </if>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <if test="did != null">
                #{did,jdbcType=INTEGER},
            </if>
            <if test="deptName != null">
                #{deptName,jdbcType=VARCHAR},
            </if>
        </trim>
    </insert>
    <select id="countByExample" parameterType="com.atguigu.mybatis.pojo.DeptExample" resultType="java.lang.Integer">
        select count(*) from t_dept
        <if test="_parameter != null">
            <include refid="Example_Where_Clause"/>
        </if>
    </select>
    <update id="updateByExampleSelective" parameterType="map">

        update t_dept
        <set>
            <if test="record.did != null">
                did = #{record.did,jdbcType=INTEGER},
            </if>
            <if test="record.deptName != null">
                dept_name = #{record.deptName,jdbcType=VARCHAR},
            </if>
        </set>
        <if test="_parameter != null">
            <include refid="Update_By_Example_Where_Clause"/>
        </if>
    </update>
    <update id="updateByExample" parameterType="map">

        update t_dept
        set did = #{record.did,jdbcType=INTEGER},
        dept_name = #{record.deptName,jdbcType=VARCHAR}
        <if test="_parameter != null">
            <include refid="Update_By_Example_Where_Clause"/>
        </if>
    </update>
    <update id="updateByPrimaryKeySelective" parameterType="com.atguigu.mybatis.pojo.Dept">

        update t_dept
        <set>
            <if test="deptName != null">
                dept_name = #{deptName,jdbcType=VARCHAR},
            </if>
        </set>
        where did = #{did,jdbcType=INTEGER}
    </update>
    <update id="updateByPrimaryKey" parameterType="com.atguigu.mybatis.pojo.Dept">

        update t_dept
        set dept_name = #{deptName,jdbcType=VARCHAR}
        where did = #{did,jdbcType=INTEGER}
    </update>
</mapper>
```

###  9.4 逆向工程方法使用

逆向工程方法使用：

```java
public class MBGTest {

    //查询所有数据
    @Test
    public void testSelectByExampleOne() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            //查询所有数据
            List<Emp> list = mapper.selectByExample(null);
            // select eid, emp_name, age, sex, email, did from t_emp
            list.forEach(emp -> System.out.println(emp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //根据条件查询(QBC风格)
    @Test
    public void testSelectByExampleTwo() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            //根据条件查询(QBC风格)
            EmpExample example = new EmpExample();
            example.createCriteria().andEmpNameEqualTo("张三").andAgeGreaterThanOrEqualTo(20);
            example.or().andDidIsNotNull();
            // select eid, emp_name, age, sex, email, did from t_emp WHERE ( emp_name = ? and age >= ? ) or( did is not null )
            List<Emp> list = mapper.selectByExample(example);
            list.forEach(emp -> System.out.println(emp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 普通修改
    @Test
    public void testUpdateByPrimaryKey() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            // 构造方法：public Emp(Integer eid, String empName, Integer age, String sex, String email, Integer did) {}
            Emp emp = new Emp(1, "admin", 22, null, "456@qq.com", 3);
            // 普通修改
            mapper.updateByPrimaryKey(emp);
            // update t_emp set emp_name = ?, age = ?, sex = ?, email = ?, did = ? where eid = ?
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // updateByPrimaryKeySelective 选择性修改
    @Test
    public void testUpdateByPrimaryKeySelective() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            // 构造方法：public Emp(Integer eid, String empName, Integer age, String sex, String email, Integer did) {}
            Emp emp = new Emp(1, "admin", 22, null, "456@qq.com", 3);
            // Selective 选择性修改
            mapper.updateByPrimaryKeySelective(emp);
            // update t_emp SET emp_name = ?, age = ?, email = ?, did = ? where eid = ?
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
```

##  10. 分页插件

###  10.1  整合分页插件

1.添加依赖

```xml
<!-- https://mvnrepository.com/artifact/com.github.pagehelper/pagehelper -->
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper</artifactId>
    <version>5.2.0</version>
</dependency>
```

2.在配置文件`mybatis-config.xml`中添加分页插件配置

`mybatis-config.xml`：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <properties resource="jdbc.properties"/>

    <typeAliases>
        <package name="com.atguigu.mybatis.pojo"/>
    </typeAliases>

    <plugins>
        <!--设置分页插件-->
        <plugin interceptor="com.github.pagehelper.PageInterceptor"></plugin>
    </plugins>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}"/>
                <property name="url" value="${jdbc.url}"/>
                <property name="username" value="${jdbc.username}"/>
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <package name="com.atguigu.mybatis.mapper"/>
    </mappers>
</configuration>
```

###  10.2 分页插件的使用

使用步骤一：在查询功能之前使用`PageHelper.startPage(int pageNum, int pageSize)`开启分页功能

- pageNum：当前页的页码

- pageSize：每页显示的条数

使用步骤二：在查询获取list集合之后，使用`PageInfo pageInfo = new PageInfo<>(List list, int navigatePages)`获取分页相关数据

分页插件返回的分页常用数据：

- pageNum：当前页的页码 
- pageSize：每页显示的条数 
- size：当前页显示的真实条数 
- total：总记录数 
- pages：总页数 
- prePage：上一页的页码 
- nextPage：下一页的页码
- isFirstPage/isLastPage：是否为第一页/最后一页 
- hasPreviousPage/hasNextPage：是否存在上一页/下一页 
- navigatePages：导航分页的页码数 
- navigatepageNums：导航分页的页码，[1,2,3,4,5]



```java
public class PageHelperTest {

    /**
     * 一.sql分页实现：
     * limit index,pageSize
     * index:当前页的起始索引
     * pageSize：每页显示的条数
     * pageNum：当前页的页码
     * index=(pageNum-1)*pageSize
     * <p>
     * 二、使用MyBatis的分页插件实现分页功能：
     * 1、需要在查询功能之前开启分页
     * PageHelper.startPage(int pageNum, int pageSize);
     * 2、在查询功能之后获取分页相关信息
     * PageInfo<Emp> page = new PageInfo<>(list, 5);
     * list表示分页数据
     * 5表示当前导航分页的数量
     */

    @Test
    public void testPageHelperOne() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            PageHelper.startPage(1, 4);
            List<Emp> list = mapper.selectByExample(null);
            //  SELECT count(0) FROM t_emp
            //  select eid, emp_name, age, sex, email, did from t_emp LIMIT ?
            list.forEach(emp -> System.out.println(emp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPageHelperTwo() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            PageHelper.startPage(3, 2);
            List<Emp> list = mapper.selectByExample(null);
            //  SELECT count(0) FROM t_emp
            //  select eid, emp_name, age, sex, email, did from t_emp LIMIT ?, ?
            list.forEach(emp -> System.out.println(emp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 在查询功能之后获取分页相关信息方式一：PageHelper.startPage的返回值中包含分页数据
    @Test
    public void testPageHelperThree() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            Page<Object> page = PageHelper.startPage(2, 2);
            //  SELECT count(0) FROM t_emp
            //  select eid, emp_name, age, sex, email, did from t_emp LIMIT ?, ?
            List<Emp> list = mapper.selectByExample(null);
            System.out.println(page);
            //  Page{count=true, pageNum=2, pageSize=2, startRow=2, endRow=4, total=9, pages=5, reasonable=false, pageSizeZero=false}
            //  [Emp{eid=3, empName='王五', age=20, sex='男', email='ywyyw@163.com', did=3},
            //  Emp{eid=4, empName='钱多多', age=25, sex='男', email='237636@168.com', did=2}]
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    //   在查询功能之后获取分页相关信息方式二：使用 new PageInfo<>(list, 5) 对查询后的list进行处理，再从返回值中获取分页信息
    @Test
    public void testPageHelperFour() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            PageHelper.startPage(2, 2);
            List<Emp> list = mapper.selectByExample(null);
            //  SELECT count(0) FROM t_emp
            //  select eid, emp_name, age, sex, email, did from t_emp LIMIT ?, ?
            PageInfo<Emp> page = new PageInfo<>(list, 2);
            //  PageInfo{pageNum=2, pageSize=2, size=2, startRow=3, endRow=4, total=9, pages=5,
            //  list=Page{count=true, pageNum=2, pageSize=2, startRow=2, endRow=4, total=9, pages=5, reasonable=false, pageSizeZero=false}
            //  [Emp{eid=3, empName='王五', age=20, sex='男', email='ywyyw@163.com', did=3},
            //  Emp{eid=4, empName='钱多多', age=25, sex='男', email='237636@168.com', did=2}],
            //  prePage=1, nextPage=3, isFirstPage=false, isLastPage=false, hasPreviousPage=true,
            //  hasNextPage=true, navigatePages=2, navigateFirstPage=1, navigateLastPage=2, navigatepageNums=[1, 2]}
            System.out.println(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
```







