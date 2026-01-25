# Sharding-JDBC 垂直分库示例

## 1. 项目概述

本模块演示了如何使用 Sharding-JDBC 实现垂直分库功能。通过将不同业务模块的数据存储到不同的数据库中，实现专库专表的目的，提升数据库的维护性和扩展性。

**官方文档**：

- [ShardingSphere 官方文档](https://shardingsphere.apache.org/document/current/cn/)
- [ShardingSphere-JDBC 用户手册](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/)
- [垂直分库配置文档](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/yaml-config/rules/sharding/)

**功能说明**：

- **垂直分库**：将不同业务模块的数据存储到不同的数据库中，实现专库专表
  - `t_user` 表存储在 `user_db` 数据库中（专库专表）
  - `edu_db_1` 和 `edu_db_2` 数据库用于存储其他业务数据

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
-- 创建 user_db 数据库（专库专表）
CREATE DATABASE IF NOT EXISTS `user_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `user_db`;
```

**创建用户表**：

创建 t_user 表：

```sql
-- 创建 t_user 表（垂直分库：专库专表）
CREATE TABLE IF NOT EXISTS `t_user` (
  `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
  `username` VARCHAR(50) NOT NULL COMMENT '用户名',
  `ustatus` VARCHAR(50) NOT NULL COMMENT '用户状态',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表（垂直分库）';
```

**配置数据库连接**：

修改 `application.properties` 中的数据库连接信息：

   - 数据库地址：`localhost:3306`
   - 数据库名：`user_db`（m0）、`edu_db_1`（m1）、`edu_db_2`（m2）
   - 用户名：`root`
   - 密码：`root`

**注意**：

- `edu_db_1` 和 `edu_db_2` 数据库应该已经存在（来自其他模块）
- 垂直分库的目的是实现专库专表，`t_user` 表只存在于 `user_db` 数据库中

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
spring.application.name=SS-03-Database-Vertical-Sharding

# shardingjdbc 分片策略
# 配置数据源，给数据源起名称,
# 垂直分库，配置三个数据源
spring.shardingsphere.datasource.names=m1,m2,m0
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

# 配置第三个数据源具体内容，包含连接池，驱动，地址，用户名和密码
spring.shardingsphere.datasource.m0.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.m0.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.m0.url=jdbc:mysql://localhost:3306/user_db?serverTimezone=GMT%2B8
spring.shardingsphere.datasource.m0.username=root
spring.shardingsphere.datasource.m0.password=root

# 配置user_db 数据库里面t_user 专库专表
spring.shardingsphere.sharding.tables.t_user.actual-data-nodes=m$->{0}.t_user
# 指定t_user 表里面主键user_id 生成策略  SNOWFLAKE
spring.shardingsphere.sharding.tables.t_user.key-generator.column=user_id
spring.shardingsphere.sharding.tables.t_user.key-generator.type=SNOWFLAKE
# 指定表分片策略  约定user_id 值偶数添加到t_user 表，如果user_id 是奇数添加到t_user 表
spring.shardingsphere.sharding.tables.t_user.table-strategy.inline.sharding-column=user_id
spring.shardingsphere.sharding.tables.t_user.table-strategy.inline.algorithm-expression=t_user

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
# 垂直分库，配置三个数据源
spring.shardingsphere.datasource.names=m1,m2,m0
```

- **作用**：定义数据源名称，本示例使用三个数据源 `m1`（edu_db_1）、`m2`（edu_db_2）、`m0`（user_db）

```properties
# 一个实体类对应两张表，覆盖
spring.main.allow-bean-definition-overriding=true
```

- **作用**：允许 Bean 定义覆盖，解决一个实体类对应多张表时的冲突问题

```properties
# 配置第三个数据源具体内容，包含连接池，驱动，地址，用户名和密码
spring.shardingsphere.datasource.m0.type=com.alibaba.druid.pool.DruidDataSource
spring.shardingsphere.datasource.m0.driver-class-name=com.mysql.cj.jdbc.Driver
spring.shardingsphere.datasource.m0.url=jdbc:mysql://localhost:3306/user_db?serverTimezone=GMT%2B8
spring.shardingsphere.datasource.m0.username=root
spring.shardingsphere.datasource.m0.password=root
```

- **作用**：配置数据源 `m0` 的具体连接信息，指向 `user_db` 数据库

**垂直分库配置**：

```properties
# 配置user_db 数据库里面t_user 专库专表
spring.shardingsphere.sharding.tables.t_user.actual-data-nodes=m$->{0}.t_user
```

- **作用**：指定 `t_user` 逻辑表对应的实际数据节点
- **说明**：`m$->{0}.t_user` 表示数据源 `m0` 下的 `t_user` 表，实现专库专表

**主键生成策略**：

```properties
# 指定t_user 表里面主键user_id 生成策略  SNOWFLAKE
spring.shardingsphere.sharding.tables.t_user.key-generator.column=user_id
spring.shardingsphere.sharding.tables.t_user.key-generator.type=SNOWFLAKE
```

- **作用**：配置主键 `user_id` 的生成策略为雪花算法（SNOWFLAKE）
- **说明**：雪花算法可以生成全局唯一的 64 位长整型 ID，包含时间戳、机器 ID 和序列号

**表分片策略配置**：

```properties
# 指定表分片策略  约定user_id 值偶数添加到t_user 表，如果user_id 是奇数添加到t_user 表
spring.shardingsphere.sharding.tables.t_user.table-strategy.inline.sharding-column=user_id
spring.shardingsphere.sharding.tables.t_user.table-strategy.inline.algorithm-expression=t_user
```

- **作用**：配置表分片策略（垂直分库场景下，实际上所有数据都路由到同一个表）
- **分片列**：`user_id`（用户ID）
- **分片算法**：`t_user`（固定路由到 t_user 表）
- **说明**：在垂直分库场景中，由于是专库专表，所有数据都存储在同一个表中

**SQL 日志配置**：

```properties
# 打开sql 输出日志
spring.shardingsphere.props.sql.show=true
```

- **作用**：开启 SQL 输出日志，方便调试和查看实际执行的 SQL 语句

## 5. 代码实现

**项目结构**：

```
SS-03-Database-Vertical-Sharding
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── action
│   │   │           └── shardingsphere
│   │   │               └── ss03databaseverticalsharding
│   │   │                   ├── entity
│   │   │                   │   └── User.java
│   │   │                   ├── mapper
│   │   │                   │   └── UserMapper.java
│   │   │                   └── Ss03DatabaseVerticalShardingApplication.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── sql
│   │           └── init.sql
│   └── test
│       └── java
│           └── com
│               └── action
│                   └── shardingsphere
│                       └── ss03databaseverticalsharding
│                           └── Ss03DatabaseVerticalShardingApplicationTests.java
```

**实体类实现**：

User 实体类，位置：`src/main/java/com/action/shardingsphere/ss03databaseverticalsharding/entity/User.java`

```java
package com.action.shardingsphere.ss03databaseverticalsharding.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * User 实体类
 * 对应 t_user 表（垂直分库：专库专表）
 */
@Data
@TableName(value = "t_user")  //指定对应表
public class User {
    /**
     * 用户ID，使用雪花算法生成
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户状态
     */
    private String ustatus;
}
```

**说明**：
- 使用 `@Data` 注解（Lombok）自动生成 getter、setter 等方法
- 使用 `@TableName("t_user")` 指定对应的数据库表名
- `userId` 字段为主键，由 Sharding-JDBC 使用雪花算法自动生成

**Mapper 接口实现**：

UserMapper 接口，位置：`src/main/java/com/action/shardingsphere/ss03databaseverticalsharding/mapper/UserMapper.java`

```java
package com.action.shardingsphere.ss03databaseverticalsharding.mapper;

import com.action.shardingsphere.ss03databaseverticalsharding.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * User Mapper 接口
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
```

**说明**：
- 继承 MyBatis Plus 的 `BaseMapper<User>`，提供基础的 CRUD 操作
- 使用 `@Repository` 注解标识为数据访问层组件

**启动类实现**：

Ss03DatabaseVerticalShardingApplication 启动类，位置：`src/main/java/com/action/shardingsphere/ss03databaseverticalsharding/Ss03DatabaseVerticalShardingApplication.java`

```java
package com.action.shardingsphere.ss03databaseverticalsharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.action.shardingsphere.ss03databaseverticalsharding.mapper")
public class Ss03DatabaseVerticalShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ss03DatabaseVerticalShardingApplication.class, args);
    }

}
```

**说明**：
- `@SpringBootApplication`：标识为 Spring Boot 应用
- `@MapperScan`：扫描指定包下的 Mapper 接口，自动注册为 Spring Bean

## 6. 测试代码实现

**测试类结构**：

位置：`src/test/java/com/action/shardingsphere/ss03databaseverticalsharding/Ss03DatabaseVerticalShardingApplicationTests.java`

**完整测试代码**：

```java
package com.action.shardingsphere.ss03databaseverticalsharding;

import com.action.shardingsphere.ss03databaseverticalsharding.entity.User;
import com.action.shardingsphere.ss03databaseverticalsharding.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ss03DatabaseVerticalShardingApplicationTests {

    //注入user 的mapper
    @Autowired
    private UserMapper userMapper;

    // 检查 Java 版本（用于验证运行时使用的 JDK 版本）
    static {
        String javaVersion = System.getProperty("java.version");
        String javaHome = System.getProperty("java.home");
        System.out.println("========================================");
        System.out.println("当前运行 Java 版本: " + javaVersion);
        System.out.println("当前 Java 路径: " + javaHome);
        System.out.println("========================================");
    }

    //======================测试垂直分库==================
    //添加操作
    @Test
    public void addUserDb() {
        User user = new User();
        user.setUsername("lucy");
        user.setUstatus("a");
        userMapper.insert(user);
        System.out.println("插入成功，生成的user_id: " + user.getUserId());
    }

    //查询操作 - 查询所有用户
    @Test
    public void findAllUsers() {
        java.util.List<User> users = userMapper.selectList(null);
        System.out.println("查询所有用户，数量: " + users.size());
        for (User user : users) {
            System.out.println("  user_id=" + user.getUserId() + ", username=" + user.getUsername() + ", ustatus=" + user.getUstatus());
        }
    }

    //查询操作 - 根据user_id查询
    @Test
    public void findUserById() {
        User user = userMapper.selectById(465191484111454209L);
        System.out.println("根据user_id查询结果: " + user);
    }
}
```

**测试方法说明**：

**addUserDb() 方法**：

**功能**：添加用户数据，验证垂直分库功能

**实现逻辑**：
1. 创建 `User` 对象
2. 设置用户名和用户状态
3. 调用 `userMapper.insert(user)` 插入数据
4. Sharding-JDBC 会根据配置自动路由到 `user_db` 数据库的 `t_user` 表
5. `userId` 由 Sharding-JDBC 使用雪花算法自动生成

**注意事项**：
- `userId` 字段不需要手动设置，由 Sharding-JDBC 使用雪花算法自动生成
- 插入数据后，可以通过查看 `user_db` 数据库的 `t_user` 表验证数据是否正确插入

**findAllUsers() 方法**：

**功能**：查询所有用户信息

**实现逻辑**：
1. 调用 `userMapper.selectList(null)` 查询所有用户
2. Sharding-JDBC 会从 `user_db` 数据库的 `t_user` 表查询数据
3. 遍历并输出所有用户信息

**注意事项**：
- 在垂直分库场景中，查询会路由到指定的数据库和表

**findUserById() 方法**：

**功能**：根据 `user_id` 查询用户信息

**实现逻辑**：
1. 调用 `userMapper.selectById(userId)` 根据主键查询
2. Sharding-JDBC 会根据 `user_id` 自动路由到 `user_db` 数据库的 `t_user` 表进行查询

**注意事项**：
- 示例中的 `user_id` 值需要替换为实际插入数据后生成的 ID

## 7. 运行测试

**前置条件**：

1. 确保 MySQL 数据库已启动
2. 确保已创建数据库 `user_db` 和表 `t_user`
3. 确保 `application.properties` 中的数据库连接信息正确
4. 确保 `edu_db_1` 和 `edu_db_2` 数据库已存在（虽然本模块不使用，但配置中需要）

**运行添加数据测试**：

1. 在 IDE 中打开 `Ss03DatabaseVerticalShardingApplicationTests` 类
2. 运行 `addUserDb()` 测试方法
3. 查看控制台输出的 SQL 日志，确认数据插入到 `user_db` 数据库的 `t_user` 表
4. 在数据库中验证数据：
   - 查询 `user_db.t_user` 表，应该包含新插入的用户数据

**运行查询数据测试**：

1. 先运行 `addUserDb()` 方法，获取生成的 `user_id` 值
2. 在数据库中查看生成的 `user_id`，选择一个 `user_id` 值
3. 修改 `findUserById()` 方法中的 `user_id` 值为实际值
4. 运行 `findUserById()` 测试方法
5. 查看控制台输出，确认查询结果正确

**运行查询所有用户测试**：

1. 运行 `findAllUsers()` 测试方法
2. 查看控制台输出，确认查询到所有用户数据

**验证垂直分库效果**：

查看 user_db.t_user 表数据：

```sql
USE user_db;
SELECT * FROM t_user;
```

**预期结果**：包含所有用户数据，数据存储在 `user_db` 数据库中

验证数据路由：

```sql
-- 验证数据是否存储在正确的数据库中
-- 在 user_db 数据库中查询
USE user_db;
SELECT COUNT(*) as user_count FROM t_user;

-- 验证 edu_db_1 和 edu_db_2 中没有 t_user 表（这是正常的，因为垂直分库）
-- 如果尝试在这些数据库中查询 t_user 表，应该会报错或返回空结果
```

## 8. 注意事项

**配置注意事项**：

1. **Bean 定义覆盖**：必须配置 `spring.main.allow-bean-definition-overriding=true`，解决一个实体类对应多张表时的冲突问题

2. **数据源配置**：垂直分库需要配置多个数据源，确保每个数据源的连接信息正确

3. **专库专表配置**：`actual-data-nodes=m$->{0}.t_user` 表示 `t_user` 表只存在于 `m0` 数据源（user_db）中

4. **主键生成**：`user_id` 由 Sharding-JDBC 使用雪花算法自动生成，无需手动设置

**开发注意事项**：

1. **数据库准备**：确保 `user_db` 数据库和 `t_user` 表已创建，否则会报错

2. **SQL 日志**：开发阶段建议开启 `spring.shardingsphere.props.sql.show=true`，方便调试和查看实际执行的 SQL 语句

3. **数据一致性**：垂直分库场景下，不同业务模块的数据存储在不同的数据库中，需要注意跨库事务的处理

4. **数据源管理**：多个数据源需要正确配置，确保每个数据源都能正常连接

**常见问题**：

**问题：启动时报 Bean 定义冲突**

**解决方案**：在 `application.properties` 中添加：
```properties
spring.main.allow-bean-definition-overriding=true
```

**问题：数据库连接失败**

**现象**：启动时报数据库连接错误

**解决方案**：
1. 检查 MySQL 服务是否已启动
2. 检查数据库连接信息（URL、用户名、密码）是否正确
3. 检查数据库和表是否已创建

**问题：数据插入失败**

**现象**：执行插入操作时报错

**解决方案**：
1. 检查 `user_db` 数据库和 `t_user` 表是否已创建
2. 检查表结构是否与实体类字段匹配
3. 检查主键生成策略配置是否正确

**问题：查询不到数据**

**现象**：执行查询操作返回空结果

**解决方案**：
1. 检查数据是否已成功插入到 `user_db.t_user` 表
2. 检查查询条件是否正确
3. 检查数据源路由配置是否正确

## 9. 总结

**核心要点**：

1. **垂直分库**：通过 Sharding-JDBC 将不同业务模块的数据存储到不同的数据库中，实现专库专表

2. **专库专表**：`t_user` 表只存在于 `user_db` 数据库中，实现业务模块的隔离

3. **主键生成**：使用雪花算法生成全局唯一的分布式 ID

4. **透明分库**：业务代码无需关心分库逻辑，Sharding-JDBC 自动处理路由

**适用场景**：

- 不同业务模块需要独立管理数据库
- 需要实现专库专表，提升数据库的维护性
- 需要对不同业务模块进行数据隔离
- 需要提升数据库的扩展性和可维护性

**垂直分库 vs 水平分库**：

- **垂直分库**：按业务模块拆分数据库，不同业务存储在不同数据库中（本示例）
- **水平分库**：按数据量拆分数据库，同一业务的数据分布到多个数据库中

**扩展学习**：

- 水平分表：将单表拆分为多个物理表
- 水平分库：将数据分布到多个数据库中
- 读写分离：主从数据库架构
- 分布式事务：跨分片事务处理

**参考资源**：

- [ShardingSphere 官方文档](https://shardingsphere.apache.org/document/current/cn/)
- [ShardingSphere GitHub](https://github.com/apache/shardingsphere)
- [ShardingSphere 社区](https://shardingsphere.apache.org/community/cn/)
- [数据分片功能说明](https://shardingsphere.apache.org/document/current/cn/features/sharding/)（包含分片最佳实践和注意事项）
