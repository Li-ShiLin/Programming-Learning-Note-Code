# Sharding-JDBC 水平分表示例

## 1. 项目概述

本模块演示了如何使用 Sharding-JDBC 实现水平分表功能。通过将 `course` 表按照 `cid` 字段进行分片，实现数据的水平拆分，提升数据库的查询性能和处理能力。

**官方文档**：

- [ShardingSphere 官方文档](https://shardingsphere.apache.org/document/current/cn/)
- [ShardingSphere-JDBC 用户手册](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/)
- [水平分表配置文档](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/yaml-config/rules/sharding/)

**功能说明**：

- **水平分表**：将 `course` 表按照 `cid` 字段进行分片
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

```sql
-- 创建数据库
CREATE DATABASE IF NOT EXISTS `course_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `course_db`;
```

**创建分片表**：

创建 course_1 表：

```sql
-- 创建 course_1 表
CREATE TABLE IF NOT EXISTS `course_1` (
  `cid` BIGINT(20) NOT NULL COMMENT '课程ID',
  `cname` VARCHAR(50) NOT NULL COMMENT '课程名称',
  `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
  `cstatus` VARCHAR(50) NOT NULL COMMENT '课程状态',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表1';
```

创建 course_2 表：

```sql
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
   - 数据库名：`course_db`
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
spring.application.name=SS-01-Table-Horizontal-Sharding

# shardingjdbc 分片策略
# 配置数据源，给数据源起名称
spring.shardingsphere.datasource.names=m1
# 一个实体类对应两张表，覆盖
spring.main.allow-bean-definition-overriding=true
# 配置数据源具体内容，包含连接池，驱动，地址，用户名和密码
spring.shardingsphere.datasource.m1.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.m1.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.m1.url=jdbc:mysql://localhost:3306/course_db?serverTimezone=GMT%2B8
spring.shardingsphere.datasource.m1.username=root
spring.shardingsphere.datasource.m1.password=root
# 指定course 表分布情况，配置表在哪个数据库里面，表名称都是什么  m1.course_1 , m1.course_2
spring.shardingsphere.sharding.tables.course.actual-data-nodes=m1.course_$->{1..2}
# 指定course 表里面主键cid 生成策略  SNOWFLAKE
spring.shardingsphere.sharding.tables.course.key-generator.column=cid
spring.shardingsphere.sharding.tables.course.key-generator.type=SNOWFLAKE
# 指定分片策略  约定cid 值偶数添加到course_1 表，如果cid 是奇数添加到course_2 表
spring.shardingsphere.sharding.tables.course.table-strategy.inline.sharding-column=cid
spring.shardingsphere.sharding.tables.course.table-strategy.inline.algorithm-expression=course_$->{cid % 2 + 1}
# 打开sql 输出日志
spring.shardingsphere.props.sql.show=true

# MyBatis Plus 配置
mybatis-plus.configuration.map-underscore-to-camel-case=true
mybatis-plus.mapper-locations=classpath*:/mapper/**/*.xml
```

**配置项说明**：

**数据源配置**：

```properties
# 配置数据源，给数据源起名称
spring.shardingsphere.datasource.names=m1
```

- **作用**：定义数据源名称，本示例使用单个数据源 `m1`

```properties
# 一个实体类对应两张表，覆盖
spring.main.allow-bean-definition-overriding=true
```

- **作用**：允许 Bean 定义覆盖，解决一个实体类对应多张表时的冲突问题

```properties
# 配置数据源具体内容，包含连接池，驱动，地址，用户名和密码
spring.shardingsphere.datasource.m1.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.m1.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.m1.url=jdbc:mysql://localhost:3306/course_db?serverTimezone=GMT%2B8
spring.shardingsphere.datasource.m1.username=root
spring.shardingsphere.datasource.m1.password=root
```

- **作用**：配置数据源 `m1` 的具体连接信息，包括连接池类型、驱动、URL、用户名和密码

**表分片配置**：

```properties
# 指定course 表分布情况，配置表在哪个数据库里面，表名称都是什么  m1.course_1 , m1.course_2
spring.shardingsphere.sharding.tables.course.actual-data-nodes=m1.course_$->{1..2}
```

- **作用**：指定 `course` 逻辑表对应的实际数据节点
- **说明**：`m1.course_$->{1..2}` 表示数据源 `m1` 下的 `course_1` 和 `course_2` 两张表

**主键生成策略**：

```properties
# 指定course 表里面主键cid 生成策略  SNOWFLAKE
spring.shardingsphere.sharding.tables.course.key-generator.column=cid
spring.shardingsphere.sharding.tables.course.key-generator.type=SNOWFLAKE
```

- **作用**：配置主键 `cid` 的生成策略为雪花算法（SNOWFLAKE）
- **说明**：雪花算法可以生成全局唯一的 64 位长整型 ID，包含时间戳、机器 ID 和序列号

**分片策略配置**：

```properties
# 指定分片策略  约定cid 值偶数添加到course_1 表，如果cid 是奇数添加到course_2 表
spring.shardingsphere.sharding.tables.course.table-strategy.inline.sharding-column=cid
spring.shardingsphere.sharding.tables.course.table-strategy.inline.algorithm-expression=course_$->{cid % 2 + 1}
```

- **作用**：配置表分片策略
- **分片列**：`cid`（课程ID）
- **分片算法**：`course_$->{cid % 2 + 1}`
  - 当 `cid % 2 == 0` 时，结果为 `course_1`
  - 当 `cid % 2 == 1` 时，结果为 `course_2`
- **说明**：`cid % 2 + 1` 确保结果在 1-2 范围内，对应 `course_1` 和 `course_2`

**SQL 日志配置**：

```properties
# 打开sql 输出日志
spring.shardingsphere.props.sql.show=true
```

- **作用**：开启 SQL 输出日志，方便调试和查看实际执行的 SQL 语句

## 5. 代码实现

**项目结构**：

```
SS-01-Table-Horizontal-Sharding
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── action
│   │   │           └── shardingsphere
│   │   │               └── ss01tablehorizontalsharding
│   │   │                   ├── entity
│   │   │                   │   └── Course.java
│   │   │                   ├── mapper
│   │   │                   │   └── CourseMapper.java
│   │   │                   └── Ss01TableHorizontalShardingApplication.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── sql
│   │           └── init.sql
│   └── test
│       └── java
│           └── com
│               └── action
│                   └── shardingsphere
│                       └── ss01tablehorizontalsharding
│                           └── Ss01TableHorizontalShardingApplicationTests.java
```

**实体类实现**：

Course 实体类，位置：`src/main/java/com/action/shardingsphere/ss01tablehorizontalsharding/entity/Course.java`

```java
package com.action.shardingsphere.ss01tablehorizontalsharding.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Course 实体类
 * 对应 course_1 和 course_2 表
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
     * 用户ID
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
- 实体类对应 `course_1` 和 `course_2` 两张物理表
- `cid` 字段为主键，由 Sharding-JDBC 使用雪花算法自动生成

**Mapper 接口实现**：

CourseMapper 接口，位置：`src/main/java/com/action/shardingsphere/ss01tablehorizontalsharding/mapper/CourseMapper.java`

```java
package com.action.shardingsphere.ss01tablehorizontalsharding.mapper;

import com.action.shardingsphere.ss01tablehorizontalsharding.entity.Course;
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

Ss01TableHorizontalShardingApplication 启动类，位置：`src/main/java/com/action/shardingsphere/ss01tablehorizontalsharding/Ss01TableHorizontalShardingApplication.java`

```java
package com.action.shardingsphere.ss01tablehorizontalsharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.action.shardingsphere.ss01tablehorizontalsharding.mapper")
public class Ss01TableHorizontalShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ss01TableHorizontalShardingApplication.class, args);
    }

}
```

**说明**：
- `@SpringBootApplication`：标识为 Spring Boot 应用
- `@MapperScan`：扫描指定包下的 Mapper 接口，自动注册为 Spring Bean

## 6. 测试代码实现

**测试类结构**：

位置：`src/test/java/com/action/shardingsphere/ss01tablehorizontalsharding/Ss01TableHorizontalShardingApplicationTests.java`

**完整测试代码**：

```java
package com.action.shardingsphere.ss01tablehorizontalsharding;

import com.action.shardingsphere.ss01tablehorizontalsharding.entity.Course;
import com.action.shardingsphere.ss01tablehorizontalsharding.mapper.CourseMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ss01TableHorizontalShardingApplicationTests {

    //注入mapper
    @Autowired
    private CourseMapper courseMapper;

    //添加课程的方法
    @Test
    public void addCourse() {
        for (int i = 1; i <= 10; i++) {
            Course course = new Course();
            course.setCname("java" + i);
            course.setUserId(100L);
            course.setCstatus("Normal" + i);
            courseMapper.insert(course);
        }
    }

    //查询课程的方法
    @Test
    public void findCourse() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("cid", 465114665106538497L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
    }
}
```

**测试方法说明**：

addCourse() 方法：

**功能**：批量添加课程数据，验证分片功能

**实现逻辑**：
1. 循环 10 次，创建 10 个 `Course` 对象
2. 设置课程名称、用户ID 和课程状态
3. 调用 `courseMapper.insert(course)` 插入数据
4. Sharding-JDBC 会根据生成的 `cid`（雪花算法）自动路由到对应的分片表
   - `cid` 为偶数 → `course_1` 表
   - `cid` 为奇数 → `course_2` 表

**注意事项**：
- `cid` 字段不需要手动设置，由 Sharding-JDBC 使用雪花算法自动生成
- 插入数据后，可以通过查看数据库验证数据是否正确分布到对应的表中

findCourse() 方法：

**功能**：根据 `cid` 查询课程信息

**实现逻辑**：
1. 创建 `QueryWrapper` 查询条件
2. 设置查询条件：`cid = 465114665106538497L`
3. 调用 `courseMapper.selectOne(wrapper)` 查询数据
4. Sharding-JDBC 会根据 `cid` 值自动路由到对应的分片表进行查询

**注意事项**：
- 查询时必须包含分片键 `cid`，否则会进行全表扫描（查询所有分片表）
- 示例中的 `cid` 值需要替换为实际插入数据后生成的 ID

## 7. 运行测试

**前置条件**：

1. 确保 MySQL 数据库已启动
2. 确保已创建数据库 `course_db` 和表 `course_1`、`course_2`
3. 确保 `application.properties` 中的数据库连接信息正确

**运行添加数据测试**：

1. 在 IDE 中打开 `Ss01TableHorizontalShardingApplicationTests` 类
2. 运行 `addCourse()` 测试方法
3. 查看控制台输出的 SQL 日志，确认数据插入到对应的分片表
4. 在数据库中验证数据分布：
   - 查询 `course_1` 表，应该包含 `cid` 为偶数的数据
   - 查询 `course_2` 表，应该包含 `cid` 为奇数的数据

**运行查询数据测试**：

1. 先运行 `addCourse()` 方法，获取生成的 `cid` 值
2. 在数据库中查看生成的 `cid`，选择一个 `cid` 值
3. 修改 `findCourse()` 方法中的 `cid` 值为实际值
4. 运行 `findCourse()` 测试方法
5. 查看控制台输出，确认查询结果正确

**验证分片效果**：

查看 course_1 表数据：

```sql
SELECT * FROM course_1;
```

**预期结果**：包含 `cid` 为偶数的所有课程数据

查看 course_2 表数据：

```sql
SELECT * FROM course_2;
```

**预期结果**：包含 `cid` 为奇数的所有课程数据

验证分片规则：

```sql
-- 验证 course_1 表中的 cid 都是偶数
SELECT cid, cid % 2 as remainder FROM course_1;
-- 所有 remainder 应该为 0

-- 验证 course_2 表中的 cid 都是奇数
SELECT cid, cid % 2 as remainder FROM course_2;
-- 所有 remainder 应该为 1
```

## 8. 注意事项

**配置注意事项**：

1. **Bean 定义覆盖**：必须配置 `spring.main.allow-bean-definition-overriding=true`，解决一个实体类对应多张表时的冲突问题

2. **分片算法表达式**：`course_$->{cid % 2 + 1}` 确保结果在 1-2 范围内，对应 `course_1` 和 `course_2`

3. **主键生成**：`cid` 由 Sharding-JDBC 使用雪花算法自动生成，无需手动设置

**开发注意事项**：

1. **分片键查询**：查询时尽量包含分片键 `cid`，避免全表扫描影响性能

2. **SQL 日志**：开发阶段建议开启 `spring.shardingsphere.props.sql.show=true`，方便调试

3. **数据一致性**：确保分片规则的一致性，避免数据分布不均

**常见问题**：

问题：启动时报 Bean 定义冲突

**解决方案**：在 `application.properties` 中添加：
```properties
spring.main.allow-bean-definition-overriding=true
```

问题：查询时没有指定分片键

**现象**：查询性能较差，会扫描所有分片表

**解决方案**：尽量在查询条件中包含分片键 `cid`

问题：数据分布不均

**现象**：某些分片表数据多，某些分片表数据少

**解决方案**：检查分片算法是否正确，确保数据均匀分布

## 9. 总结

**核心要点**：

1. **水平分表**：通过 Sharding-JDBC 将单表拆分为多个物理表，提升数据库性能

2. **分片策略**：基于 `cid` 字段的取模运算，实现数据的均匀分布

3. **主键生成**：使用雪花算法生成全局唯一的分布式 ID

4. **透明分片**：业务代码无需关心分片逻辑，Sharding-JDBC 自动处理路由

**适用场景**：

- 单表数据量过大，影响查询性能
- 需要提升数据库的并发处理能力
- 需要对数据进行水平扩展

**扩展学习**：

- 水平分库：将数据分布到多个数据库中
- 垂直分库：按业务模块拆分数据库
- 读写分离：主从数据库架构
- 分布式事务：跨分片事务处理

**参考资源**：

- [ShardingSphere 官方文档](https://shardingsphere.apache.org/document/current/cn/)
- [ShardingSphere GitHub](https://github.com/apache/shardingsphere)
- [ShardingSphere 社区](https://shardingsphere.apache.org/community/cn/)
- [数据分片功能说明](https://shardingsphere.apache.org/document/current/cn/features/sharding/)（包含分片最佳实践和注意事项）
