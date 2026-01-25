# Sharding-JDBC 水平分库示例

## 1. 项目概述

本模块演示了如何使用 Sharding-JDBC 实现水平分库功能。通过将 `course` 表按照 `user_id` 字段进行数据库分片，同时按照 `cid` 字段进行表分片，实现数据的水平拆分，提升数据库的查询性能和处理能力。

**官方文档**：

- [ShardingSphere 官方文档](https://shardingsphere.apache.org/document/current/cn/)
- [ShardingSphere-JDBC 用户手册](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/)
- [水平分库配置文档](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/yaml-config/rules/sharding/)

**功能说明**：

- **水平分库**：将 `course` 表按照 `user_id` 字段进行数据库分片
  - `user_id` 为偶数时，数据存储到 `edu_db_1` 数据库
  - `user_id` 为奇数时，数据存储到 `edu_db_2` 数据库
- **水平分表**：在每个数据库中，按照 `cid` 字段进行表分片
  - `cid` 为偶数时，数据存储到 `course_1` 表
  - `cid` 为奇数时，数据存储到 `course_2` 表

**技术栈**：

- Spring Boot 2.2.1
- MyBatis Plus 3.0.5
- Sharding-JDBC 4.0.0-RC1
- Druid 连接池
- MySQL 5.7+ 或 MySQL 8.0+
- Lombok

## 2. 环境准备

**环境要求**：

- JDK 1.8+
- Maven 3.6+
- MySQL 5.7+ 或 MySQL 8.0+
- Spring Boot 2.2.1

**依赖配置**：

项目使用 Maven 进行依赖管理，主要依赖如下：

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
    </dependency>

    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    <dependency>
        <groupId>org.apache.shardingsphere</groupId>
        <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

## 3. 数据库准备

**创建数据库**：

执行 `src/main/resources/sql/init.sql` 脚本创建数据库和表：

**创建 edu_db_1 数据库**：

```sql
-- 创建数据库 edu_db_1
CREATE DATABASE IF NOT EXISTS `edu_db_1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `edu_db_1`;

-- 创建 course_1 表
CREATE TABLE IF NOT EXISTS `course_1` (
  `cid` BIGINT(20) NOT NULL COMMENT '课程ID',
  `cname` VARCHAR(50) NOT NULL COMMENT '课程名称',
  `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
  `cstatus` VARCHAR(50) NOT NULL COMMENT '课程状态',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表1';

-- 创建 course_2 表
CREATE TABLE IF NOT EXISTS `course_2` (
  `cid` BIGINT(20) NOT NULL COMMENT '课程ID',
  `cname` VARCHAR(50) NOT NULL COMMENT '课程名称',
  `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
  `cstatus` VARCHAR(50) NOT NULL COMMENT '课程状态',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表2';
```

**创建 edu_db_2 数据库**：

```sql
-- 创建数据库 edu_db_2
CREATE DATABASE IF NOT EXISTS `edu_db_2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `edu_db_2`;

-- 创建 course_1 表
CREATE TABLE IF NOT EXISTS `course_1` (
  `cid` BIGINT(20) NOT NULL COMMENT '课程ID',
  `cname` VARCHAR(50) NOT NULL COMMENT '课程名称',
  `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
  `cstatus` VARCHAR(50) NOT NULL COMMENT '课程状态',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表1';

-- 创建 course_2 表
CREATE TABLE IF NOT EXISTS `course_2` (
  `cid` BIGINT(20) NOT NULL COMMENT '课程ID',
  `cname` VARCHAR(50) NOT NULL COMMENT '课程名称',
  `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
  `cstatus` VARCHAR(50) NOT NULL COMMENT '课程状态',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表2';
```

**配置数据库连接**：

修改 `application.properties` 中的数据库连接信息：

   - 数据库地址：`localhost:3306`
   - 数据库名：`edu_db_1` 和 `edu_db_2`
   - 用户名：`root`
   - 密码：`root`

## 4. Sharding-JDBC 配置说明

**配置文件位置**：

Sharding-JDBC 的分片策略配置在 `src/main/resources/application.properties` 文件中。

**官方配置文档**：

- [ShardingSphere 官方文档](https://shardingsphere.apache.org/document/current/cn/)
- [ShardingSphere-JDBC 用户手册](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/)
- [分片规则配置](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/yaml-config/rules/sharding/)（推荐：详细的分片配置说明）
- [数据分片功能说明](https://shardingsphere.apache.org/document/current/cn/features/sharding/)（包含分片算法、主键生成等核心概念）

> **提示**：由于 ShardingSphere 版本更新较快，部分具体配置页面链接可能发生变化。建议从[官方文档首页](https://shardingsphere.apache.org/document/current/cn/)开始，导航到"用户手册" → "ShardingSphere-JDBC" → "配置手册"查找相关配置说明。

**完整配置内容**：

```properties
spring.application.name=SS-02-Databases-Horizontal-Sharding

# shardingjdbc 分片策略
# 配置数据源，给数据源起名称,
# 水平分库，配置两个数据源
spring.shardingsphere.datasource.names=m1,m2
# 一个实体类对应两张表，覆盖
spring.main.allow-bean-definition-overriding=true
# 配置第一个数据源具体内容，包含连接池，驱动，地址，用户名和密码
spring.shardingsphere.datasource.m1.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.m1.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.m1.url=jdbc:mysql://localhost:3306/edu_db_1?serverTimezone=GMT%2B8
spring.shardingsphere.datasource.m1.username=root
spring.shardingsphere.datasource.m1.password=root
# 配置第二个数据源具体内容，包含连接池，驱动，地址，用户名和密码
spring.shardingsphere.datasource.m2.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.m2.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.m2.url=jdbc:mysql://localhost:3306/edu_db_2?serverTimezone=GMT%2B8
spring.shardingsphere.datasource.m2.username=root
spring.shardingsphere.datasource.m2.password=root
# 指定数据库分布情况，数据库里面表分布情况
# m1  m2    course_1 course_2
spring.shardingsphere.sharding.tables.course.actual-data-nodes=m$->{1..2}.course_$->{1..2}
# 指定course 表里面主键cid 生成策略  SNOWFLAKE
spring.shardingsphere.sharding.tables.course.key-generator.column=cid
spring.shardingsphere.sharding.tables.course.key-generator.type=SNOWFLAKE
# 指定表分片策略  约定cid 值偶数添加到course_1 表，如果cid 是奇数添加到course_2 表
spring.shardingsphere.sharding.tables.course.table-strategy.inline.sharding-column=cid
spring.shardingsphere.sharding.tables.course.table-strategy.inline.algorithm-expression=course_$->{cid % 2 + 1}
# 指定数据库分片策略 约定user_id 是偶数添加m1，是奇数添加m2
#spring.shardingsphere.sharding.default-database-strategy.inline.sharding-column=user_id
#spring.shardingsphere.sharding.default-database-strategy.inline.algorithm-expression=m$->{user_id % 2 + 1}
spring.shardingsphere.sharding.tables.course.database-strategy.inline.sharding-column=user_id
spring.shardingsphere.sharding.tables.course.database-strategy.inline.algorithm-expression=m$->{user_id % 2 + 1}
# 打开sql 输出日志
spring.shardingsphere.props.sql.show=true

# MyBatis Plus 配置
mybatis-plus.configuration.map-underscore-to-camel-case=true
mybatis-plus.mapper-locations=classpath*:/mapper/**/*.xml
```

**配置项说明**：

**数据源配置**：

```properties
# 配置数据源，给数据源起名称,
# 水平分库，配置两个数据源
spring.shardingsphere.datasource.names=m1,m2
```

- **作用**：定义数据源名称，本示例使用两个数据源 `m1` 和 `m2`，分别对应 `edu_db_1` 和 `edu_db_2` 数据库

```properties
# 一个实体类对应两张表，覆盖
spring.main.allow-bean-definition-overriding=true
```

- **作用**：允许 Bean 定义覆盖，解决一个实体类对应多张表时的冲突问题

```properties
# 配置第一个数据源具体内容，包含连接池，驱动，地址，用户名和密码
spring.shardingsphere.datasource.m1.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.m1.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.m1.url=jdbc:mysql://localhost:3306/edu_db_1?serverTimezone=GMT%2B8
spring.shardingsphere.datasource.m1.username=root
spring.shardingsphere.datasource.m1.password=root
# 配置第二个数据源具体内容，包含连接池，驱动，地址，用户名和密码
spring.shardingsphere.datasource.m2.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.m2.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.m2.url=jdbc:mysql://localhost:3306/edu_db_2?serverTimezone=GMT%2B8
spring.shardingsphere.datasource.m2.username=root
spring.shardingsphere.datasource.m2.password=root
```

- **作用**：配置数据源 `m1` 和 `m2` 的具体连接信息，包括连接池类型、驱动、URL、用户名和密码

**表分片配置**：

```properties
# 指定数据库分布情况，数据库里面表分布情况
# m1  m2    course_1 course_2
spring.shardingsphere.sharding.tables.course.actual-data-nodes=m$->{1..2}.course_$->{1..2}
```

- **作用**：指定 `course` 逻辑表对应的实际数据节点
- **说明**：`m$->{1..2}.course_$->{1..2}` 表示数据源 `m1` 和 `m2` 下的 `course_1` 和 `course_2` 表，共 4 个物理表：
  - `m1.course_1`（edu_db_1 数据库的 course_1 表）
  - `m1.course_2`（edu_db_1 数据库的 course_2 表）
  - `m2.course_1`（edu_db_2 数据库的 course_1 表）
  - `m2.course_2`（edu_db_2 数据库的 course_2 表）

**主键生成策略**：

```properties
# 指定course 表里面主键cid 生成策略  SNOWFLAKE
spring.shardingsphere.sharding.tables.course.key-generator.column=cid
spring.shardingsphere.sharding.tables.course.key-generator.type=SNOWFLAKE
```

- **作用**：配置主键 `cid` 的生成策略为雪花算法（SNOWFLAKE）
- **说明**：雪花算法可以生成全局唯一的 64 位长整型 ID，包含时间戳、机器 ID 和序列号

**表分片策略配置**：

```properties
# 指定表分片策略  约定cid 值偶数添加到course_1 表，如果cid 是奇数添加到course_2 表
spring.shardingsphere.sharding.tables.course.table-strategy.inline.sharding-column=cid
spring.shardingsphere.sharding.tables.course.table-strategy.inline.algorithm-expression=course_$->{cid % 2 + 1}
```

- **作用**：配置表分片策略
- **分片列**：`cid`（课程ID）
- **分片算法**：`course_$->{cid % 2 + 1}`
  - 当 `cid % 2 == 0` 时，结果为 `course_1`
  - 当 `cid % 2 == 1` 时，结果为 `course_2`
- **说明**：`cid % 2 + 1` 确保结果在 1-2 范围内，对应 `course_1` 和 `course_2`

**数据库分片策略配置**：

```properties
# 指定数据库分片策略 约定user_id 是偶数添加m1，是奇数添加m2
#spring.shardingsphere.sharding.default-database-strategy.inline.sharding-column=user_id
#spring.shardingsphere.sharding.default-database-strategy.inline.algorithm-expression=m$->{user_id % 2 + 1}
spring.shardingsphere.sharding.tables.course.database-strategy.inline.sharding-column=user_id
spring.shardingsphere.sharding.tables.course.database-strategy.inline.algorithm-expression=m$->{user_id % 2 + 1}
```

- **作用**：配置数据库分片策略
- **分片列**：`user_id`（用户ID）
- **分片算法**：`m$->{user_id % 2 + 1}`
  - 当 `user_id % 2 == 0` 时，结果为 `m1`（对应 `edu_db_1` 数据库）
  - 当 `user_id % 2 == 1` 时，结果为 `m2`（对应 `edu_db_2` 数据库）
- **说明**：
  - 注释掉的两行是全局数据库分片策略配置，适用于所有表
  - 当前使用的是表级别的数据库分片策略，只对 `course` 表生效
  - `user_id % 2 + 1` 确保结果在 1-2 范围内，对应 `m1` 和 `m2`

**SQL 日志配置**：

```properties
# 打开sql 输出日志
spring.shardingsphere.props.sql.show=true
```

- **作用**：开启 SQL 输出日志，方便调试和查看实际执行的 SQL 语句

## 5. 代码实现

**项目结构**：

```
SS-02-Databases-Horizontal-Sharding
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── action
│   │   │           └── shardingsphere
│   │   │               └── ss02databaseshorizontalsharding
│   │   │                   ├── entity
│   │   │                   │   └── Course.java
│   │   │                   ├── mapper
│   │   │                   │   └── CourseMapper.java
│   │   │                   └── Ss02DatabasesHorizontalShardingApplication.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── sql
│   │           └── init.sql
│   └── test
│       └── java
│           └── com
│               └── action
│                   └── shardingsphere
│                       └── ss02databaseshorizontalsharding
│                           └── Ss02DatabasesHorizontalShardingApplicationTests.java
```

**实体类实现**：

Course 实体类，位置：`src/main/java/com/action/shardingsphere/ss02databaseshorizontalsharding/entity/Course.java`

```java
package com.action.shardingsphere.ss02databaseshorizontalsharding.entity;

import lombok.Data;

/**
 * Course 实体类
 * 对应 edu_db_1 和 edu_db_2 数据库中的 course_1 和 course_2 表
 */
@Data
public class Course {
    /**
     * 课程ID，使用雪花算法生成
     */
    private Long cid;

    /**
     * 课程名称
     */
    private String cname;

    /**
     * 用户ID，用于数据库分片
     */
    private Long userId;

    /**
     * 课程状态
     */
    private String cstatus;
}
```

**说明**：
- 使用 `@Data` 注解（Lombok）自动生成 getter、setter 等方法
- 实体类对应 `edu_db_1` 和 `edu_db_2` 数据库中的 `course_1` 和 `course_2` 表
- `cid` 字段为主键，由 Sharding-JDBC 使用雪花算法自动生成
- `userId` 字段用于数据库分片，根据其值决定数据存储到哪个数据库

**Mapper 接口实现**：

CourseMapper 接口，位置：`src/main/java/com/action/shardingsphere/ss02databaseshorizontalsharding/mapper/CourseMapper.java`

```java
package com.action.shardingsphere.ss02databaseshorizontalsharding.mapper;

import com.action.shardingsphere.ss02databaseshorizontalsharding.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * Course Mapper 接口
 */
@Repository
public interface CourseMapper extends BaseMapper<Course> {

}
```

**说明**：
- 继承 MyBatis Plus 的 `BaseMapper<Course>`，提供基础的 CRUD 操作
- 使用 `@Repository` 注解标识为数据访问层组件

**启动类实现**：

Ss02DatabasesHorizontalShardingApplication 启动类，位置：`src/main/java/com/action/shardingsphere/ss02databaseshorizontalsharding/Ss02DatabasesHorizontalShardingApplication.java`

```java
package com.action.shardingsphere.ss02databaseshorizontalsharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.action.shardingsphere.ss02databaseshorizontalsharding.mapper")
public class Ss02DatabasesHorizontalShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ss02DatabasesHorizontalShardingApplication.class, args);
    }

}
```

**说明**：
- `@SpringBootApplication`：标识为 Spring Boot 应用
- `@MapperScan`：扫描指定包下的 Mapper 接口，自动注册为 Spring Bean

## 6. 测试代码实现

**测试类结构**：

位置：`src/test/java/com/action/shardingsphere/ss02databaseshorizontalsharding/Ss02DatabasesHorizontalShardingApplicationTests.java`

**完整测试代码**：

```java
package com.action.shardingsphere.ss02databaseshorizontalsharding;

import com.action.shardingsphere.ss02databaseshorizontalsharding.entity.Course;
import com.action.shardingsphere.ss02databaseshorizontalsharding.mapper.CourseMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ss02DatabasesHorizontalShardingApplicationTests {

    //注入mapper
    @Autowired
    private CourseMapper courseMapper;

    // 检查 Java 版本（用于验证运行时使用的 JDK 版本）
    static {
        String javaVersion = System.getProperty("java.version");
        String javaHome = System.getProperty("java.home");
        System.out.println("========================================");
        System.out.println("当前运行 Java 版本: " + javaVersion);
        System.out.println("当前 Java 路径: " + javaHome);
        System.out.println("========================================");
    }

    //======================测试水平分库=====================

    //添加操作
    @Test
    public void addCourseDb() {
        Course course = new Course();
        course.setCname("javademo1");
        //分库根据user_id
        course.setUserId(111L);
        course.setCstatus("Normal1");
        courseMapper.insert(course);
    }

    //查询操作
    @Test
    public void findCourseDb() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        //设置userid 值
        wrapper.eq("user_id", 100L);
        //设置cid 值
        wrapper.eq("cid", 465162909769531393L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
    }
}
```

**测试方法说明**：

addCourseDb() 方法：

**功能**：添加课程数据，验证水平分库和水平分表功能

**实现逻辑**：
1. 创建 `Course` 对象
2. 设置课程名称、用户ID 和课程状态
3. 调用 `courseMapper.insert(course)` 插入数据
4. Sharding-JDBC 会根据以下规则自动路由：
   - **数据库分片**：根据 `userId` 值决定存储到哪个数据库
     - `userId` 为偶数 → `edu_db_1` 数据库（数据源 `m1`）
     - `userId` 为奇数 → `edu_db_2` 数据库（数据源 `m2`）
   - **表分片**：根据生成的 `cid`（雪花算法）值决定存储到哪个表
     - `cid` 为偶数 → `course_1` 表
     - `cid` 为奇数 → `course_2` 表

**注意事项**：
- `cid` 字段不需要手动设置，由 Sharding-JDBC 使用雪花算法自动生成
- `userId` 字段必须设置，用于数据库分片路由
- 插入数据后，可以通过查看数据库验证数据是否正确分布到对应的数据库和表中

findCourseDb() 方法：

**功能**：根据 `user_id` 和 `cid` 查询课程信息

**实现逻辑**：
1. 创建 `QueryWrapper` 查询条件
2. 设置查询条件：`user_id = 100L` 和 `cid = 465162909769531393L`
3. 调用 `courseMapper.selectOne(wrapper)` 查询数据
4. Sharding-JDBC 会根据以下规则自动路由：
   - **数据库分片**：根据 `user_id` 值路由到对应的数据库
   - **表分片**：根据 `cid` 值路由到对应的表

**注意事项**：
- 查询时必须包含数据库分片键 `user_id`，否则会进行全库扫描（查询所有数据库）
- 查询时建议包含表分片键 `cid`，否则会进行全表扫描（查询所有分片表）
- 示例中的 `cid` 值需要替换为实际插入数据后生成的 ID

## 7. 运行测试

**前置条件**：

1. 确保 MySQL 数据库已启动
2. 确保已创建数据库 `edu_db_1`、`edu_db_2` 和表 `course_1`、`course_2`
3. 确保 `application.properties` 中的数据库连接信息正确

**运行添加数据测试**：

1. 在 IDE 中打开 `Ss02DatabasesHorizontalShardingApplicationTests` 类
2. 运行 `addCourseDb()` 测试方法
3. 查看控制台输出的 SQL 日志，确认数据插入到对应的数据库和表
4. 在数据库中验证数据分布：
   - 查询 `edu_db_1.course_1` 表，应该包含 `user_id` 为偶数且 `cid` 为偶数的数据
   - 查询 `edu_db_1.course_2` 表，应该包含 `user_id` 为偶数且 `cid` 为奇数的数据
   - 查询 `edu_db_2.course_1` 表，应该包含 `user_id` 为奇数且 `cid` 为偶数的数据
   - 查询 `edu_db_2.course_2` 表，应该包含 `user_id` 为奇数且 `cid` 为奇数的数据

**运行查询数据测试**：

1. 先运行 `addCourseDb()` 方法，获取生成的 `cid` 值
2. 在数据库中查看生成的 `cid` 和对应的 `user_id`，选择一组值
3. 修改 `findCourseDb()` 方法中的 `user_id` 和 `cid` 值为实际值
4. 运行 `findCourseDb()` 测试方法
5. 查看控制台输出，确认查询结果正确

**验证分片效果**：

查看 edu_db_1 数据库数据：

```sql
-- 查看 edu_db_1.course_1 表数据
SELECT * FROM edu_db_1.course_1;

-- 查看 edu_db_1.course_2 表数据
SELECT * FROM edu_db_1.course_2;
```

**预期结果**：包含 `user_id` 为偶数的所有课程数据

查看 edu_db_2 数据库数据：

```sql
-- 查看 edu_db_2.course_1 表数据
SELECT * FROM edu_db_2.course_1;

-- 查看 edu_db_2.course_2 表数据
SELECT * FROM edu_db_2.course_2;
```

**预期结果**：包含 `user_id` 为奇数的所有课程数据

验证分片规则：

```sql
-- 验证 edu_db_1 数据库中的 user_id 都是偶数
SELECT user_id, user_id % 2 as remainder FROM edu_db_1.course_1;
SELECT user_id, user_id % 2 as remainder FROM edu_db_1.course_2;
-- 所有 remainder 应该为 0

-- 验证 edu_db_2 数据库中的 user_id 都是奇数
SELECT user_id, user_id % 2 as remainder FROM edu_db_2.course_1;
SELECT user_id, user_id % 2 as remainder FROM edu_db_2.course_2;
-- 所有 remainder 应该为 1

-- 验证每个数据库中的表分片规则
-- edu_db_1.course_1 中的 cid 应该都是偶数
SELECT cid, cid % 2 as remainder FROM edu_db_1.course_1;
-- 所有 remainder 应该为 0

-- edu_db_1.course_2 中的 cid 应该都是奇数
SELECT cid, cid % 2 as remainder FROM edu_db_1.course_2;
-- 所有 remainder 应该为 1
```

## 8. 注意事项

**配置注意事项**：

1. **Bean 定义覆盖**：必须配置 `spring.main.allow-bean-definition-overriding=true`，解决一个实体类对应多张表时的冲突问题

2. **数据库分片策略**：`m$->{user_id % 2 + 1}` 确保结果在 1-2 范围内，对应 `m1` 和 `m2` 数据源

3. **表分片算法表达式**：`course_$->{cid % 2 + 1}` 确保结果在 1-2 范围内，对应 `course_1` 和 `course_2`

4. **主键生成**：`cid` 由 Sharding-JDBC 使用雪花算法自动生成，无需手动设置

5. **数据节点配置**：`m$->{1..2}.course_$->{1..2}` 表示 4 个物理表，需要确保所有表都已创建

**开发注意事项**：

1. **分片键查询**：查询时尽量包含数据库分片键 `user_id` 和表分片键 `cid`，避免全库全表扫描影响性能

2. **SQL 日志**：开发阶段建议开启 `spring.shardingsphere.props.sql.show=true`，方便调试

3. **数据一致性**：确保分片规则的一致性，避免数据分布不均

4. **事务处理**：跨数据库的事务需要使用分布式事务解决方案

**常见问题**：

问题：启动时报 Bean 定义冲突

**解决方案**：在 `application.properties` 中添加：
```properties
spring.main.allow-bean-definition-overriding=true
```

问题：查询时没有指定数据库分片键

**现象**：查询性能较差，会扫描所有数据库和表

**解决方案**：尽量在查询条件中包含数据库分片键 `user_id`

问题：查询时没有指定表分片键

**现象**：查询性能较差，会扫描所有分片表

**解决方案**：尽量在查询条件中包含表分片键 `cid`

问题：数据分布不均

**现象**：某些数据库或表数据多，某些数据库或表数据少

**解决方案**：检查分片算法是否正确，确保数据均匀分布

问题：找不到数据

**现象**：插入数据后查询不到

**解决方案**：
- 检查查询条件中是否包含正确的分片键值
- 检查数据是否插入到正确的数据库和表中
- 查看 SQL 日志确认实际执行的 SQL 语句

## 9. 总结

**核心要点**：

1. **水平分库**：通过 Sharding-JDBC 将数据分布到多个数据库中，提升数据库的并发处理能力

2. **水平分表**：在每个数据库中，将单表拆分为多个物理表，提升单库的查询性能

3. **双重分片**：本示例同时实现了数据库分片和表分片，实现更细粒度的数据分布

4. **分片策略**：
   - 数据库分片：基于 `user_id` 字段的取模运算
   - 表分片：基于 `cid` 字段的取模运算

5. **主键生成**：使用雪花算法生成全局唯一的分布式 ID

6. **透明分片**：业务代码无需关心分片逻辑，Sharding-JDBC 自动处理路由

**适用场景**：

- 单库单表数据量过大，影响查询性能
- 需要提升数据库的并发处理能力
- 需要对数据进行水平扩展
- 需要同时实现数据库和表的分片

**扩展学习**：

- 垂直分库：按业务模块拆分数据库
- 读写分离：主从数据库架构
- 分布式事务：跨分片事务处理
- 分片算法：自定义分片算法实现更复杂的分片规则

**参考资源**：

- [ShardingSphere 官方文档](https://shardingsphere.apache.org/document/current/cn/)
- [ShardingSphere GitHub](https://github.com/apache/shardingsphere)
- [ShardingSphere 社区](https://shardingsphere.apache.org/community/cn/)
- [数据分片功能说明](https://shardingsphere.apache.org/document/current/cn/features/sharding/)（包含分片最佳实践和注意事项）
