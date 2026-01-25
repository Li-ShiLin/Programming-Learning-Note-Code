# Sharding-JDBC 公共表示例

## 1. 项目概述

本模块演示了如何使用 Sharding-JDBC 实现公共表（广播表）功能。公共表是一种特殊的分片表，其数据会在所有分片数据库中保持一致，适用于存储字典数据、配置信息等固定数据。

**官方文档**：

- [ShardingSphere 官方文档](https://shardingsphere.apache.org/document/current/cn/)
- [ShardingSphere-JDBC 用户手册](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/)
- [公共表配置文档](https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/yaml-config/rules/sharding/)

**功能说明**：

- **公共表（广播表）**：存储固定数据的表，表数据很少发生变化，查询时经常进行关联
- 在每个数据库中创建出相同结构的公共表
- 对公共表的任何操作（增删改查）都会同步到所有数据库中的该表

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
-- 创建 edu_db_1 数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS `edu_db_1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `edu_db_1`;
```

**创建公共表**：

在 `edu_db_1` 数据库中创建 `t_udict` 表：

```sql
-- 创建 t_udict 表（公共表：广播表）
CREATE TABLE IF NOT EXISTS `t_udict` (
  `dictid` BIGINT(20) NOT NULL COMMENT '字典ID',
  `ustatus` VARCHAR(50) NOT NULL COMMENT '状态',
  `uvalue` VARCHAR(50) NOT NULL COMMENT '值',
  PRIMARY KEY (`dictid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表（公共表）';
```

在 `edu_db_2` 数据库中创建相同的 `t_udict` 表：

```sql
-- 创建 edu_db_2 数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS `edu_db_2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `edu_db_2`;

-- 创建 t_udict 表（公共表：广播表）
CREATE TABLE IF NOT EXISTS `t_udict` (
  `dictid` BIGINT(20) NOT NULL COMMENT '字典ID',
  `ustatus` VARCHAR(50) NOT NULL COMMENT '状态',
  `uvalue` VARCHAR(50) NOT NULL COMMENT '值',
  PRIMARY KEY (`dictid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表（公共表）';
```

**配置数据库连接**：

修改 `application.properties` 中的数据库连接信息：

   - 数据库地址：`localhost:3306`
   - 数据库名：`edu_db_1`（m1）、`edu_db_2`（m2）
   - 用户名：`root`
   - 密码：`root`

**注意**：

- 公共表（广播表）需要在每个数据库中创建相同结构的表
- 对公共表的任何操作（增删改查）都会同步到所有数据库中的该表
- 公共表适用于存储固定数据，如字典表、配置表等

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
spring.application.name=SS-04-BroadCast-Table

# shardingjdbc 分片策略
# 配置数据源，给数据源起名称,
# 公共表，配置两个数据源（公共表需要在每个数据库中创建）
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

# 配置公共表（广播表）
spring.shardingsphere.sharding.broadcast-tables=t_udict
spring.shardingsphere.sharding.tables.t_udict.key-generator.column=dictid
spring.shardingsphere.sharding.tables.t_udict.key-generator.type=SNOWFLAKE

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
# 公共表，配置两个数据源（公共表需要在每个数据库中创建）
spring.shardingsphere.datasource.names=m1,m2
```

- **作用**：定义数据源名称，本示例使用两个数据源 `m1`（edu_db_1）、`m2`（edu_db_2）

```properties
# 一个实体类对应两张表，覆盖
spring.main.allow-bean-definition-overriding=true
```

- **作用**：允许 Bean 定义覆盖，解决一个实体类对应多张表时的冲突问题

**公共表配置**：

```properties
# 配置公共表（广播表）
spring.shardingsphere.sharding.broadcast-tables=t_udict
```

- **作用**：配置公共表（广播表），`t_udict` 表会在所有数据源中同步操作
- **说明**：对公共表的任何操作（增删改查）都会同步到所有数据库中的该表

**主键生成策略**：

```properties
# 指定t_udict 表里面主键dictid 生成策略  SNOWFLAKE
spring.shardingsphere.sharding.tables.t_udict.key-generator.column=dictid
spring.shardingsphere.sharding.tables.t_udict.key-generator.type=SNOWFLAKE
```

- **作用**：配置主键 `dictid` 的生成策略为雪花算法（SNOWFLAKE）
- **说明**：雪花算法可以生成全局唯一的 64 位长整型 ID，包含时间戳、机器 ID 和序列号

**SQL 日志配置**：

```properties
# 打开sql 输出日志
spring.shardingsphere.props.sql.show=true
```

- **作用**：开启 SQL 输出日志，方便调试和查看实际执行的 SQL 语句

## 5. 代码实现

**项目结构**：

```
SS-04-BroadCast-Table
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── action
│   │   │           └── shardingsphere
│   │   │               └── ss04broadcasttable
│   │   │                   ├── entity
│   │   │                   │   └── Udict.java
│   │   │                   ├── mapper
│   │   │                   │   └── UdictMapper.java
│   │   │                   └── Ss04BroadCastTableApplication.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── sql
│   │           └── init.sql
│   └── test
│       └── java
│           └── com
│               └── action
│                   └── shardingsphere
│                       └── ss04broadcasttable
│                           └── Ss04BroadCastTableApplicationTests.java
```

**实体类实现**：

Udict 实体类，位置：`src/main/java/com/action/shardingsphere/ss04broadcasttable/entity/Udict.java`

```java
package com.action.shardingsphere.ss04broadcasttable.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Udict 实体类
 * 对应 t_udict 表（公共表：广播表）
 */
@Data
@TableName(value = "t_udict")
public class Udict {
    /**
     * 字典ID，使用雪花算法生成
     */
    private Long dictid;

    /**
     * 状态
     */
    private String ustatus;

    /**
     * 值
     */
    private String uvalue;
}
```

**说明**：
- 使用 `@Data` 注解（Lombok）自动生成 getter、setter 等方法
- 使用 `@TableName("t_udict")` 指定对应的数据库表名
- `dictid` 字段为主键，由 Sharding-JDBC 使用雪花算法自动生成

**Mapper 接口实现**：

UdictMapper 接口，位置：`src/main/java/com/action/shardingsphere/ss04broadcasttable/mapper/UdictMapper.java`

```java
package com.action.shardingsphere.ss04broadcasttable.mapper;

import com.action.shardingsphere.ss04broadcasttable.entity.Udict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * Udict Mapper 接口
 */
@Repository
public interface UdictMapper extends BaseMapper<Udict> {

}
```

**说明**：
- 继承 MyBatis Plus 的 `BaseMapper<Udict>`，提供基础的 CRUD 操作
- 使用 `@Repository` 注解标识为数据访问层组件

**启动类实现**：

Ss04BroadCastTableApplication 启动类，位置：`src/main/java/com/action/shardingsphere/ss04broadcasttable/Ss04BroadCastTableApplication.java`

```java
package com.action.shardingsphere.ss04broadcasttable;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.action.shardingsphere.ss04broadcasttable.mapper")
public class Ss04BroadCastTableApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ss04BroadCastTableApplication.class, args);
    }

}
```

**说明**：
- `@SpringBootApplication`：标识为 Spring Boot 应用
- `@MapperScan`：扫描指定包下的 Mapper 接口，自动注册为 Spring Bean

## 6. 测试代码实现

**测试类结构**：

位置：`src/test/java/com/action/shardingsphere/ss04broadcasttable/Ss04BroadCastTableApplicationTests.java`

**完整测试代码**：

```java
package com.action.shardingsphere.ss04broadcasttable;

import com.action.shardingsphere.ss04broadcasttable.entity.Udict;
import com.action.shardingsphere.ss04broadcasttable.mapper.UdictMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ss04BroadCastTableApplicationTests {

    //注入udict 的mapper
    @Autowired
    private UdictMapper udictMapper;

    // 检查 Java 版本（用于验证运行时使用的 JDK 版本）
    static {
        String javaVersion = System.getProperty("java.version");
        String javaHome = System.getProperty("java.home");
        System.out.println("========================================");
        System.out.println("当前运行 Java 版本: " + javaVersion);
        System.out.println("当前 Java 路径: " + javaHome);
        System.out.println("========================================");
    }

    //======================测试公共表===================
    //添加操作
    @Test
    public void addDict() {
        Udict udict = new Udict();
        udict.setUstatus("a");
        udict.setUvalue("已启用");
        udictMapper.insert(udict);
        System.out.println("插入成功，生成的dictid: " + udict.getDictid());
    }

    //删除操作
    @Test
    public void deleteDict() {
        QueryWrapper<Udict> wrapper = new QueryWrapper<>();
        //设置dictid 值
        wrapper.eq("dictid", 465191484111454209L);
        int result = udictMapper.delete(wrapper);
        System.out.println("删除结果: " + (result > 0 ? "成功" : "失败"));
    }

    //查询操作 - 查询所有字典
    @Test
    public void findAllDicts() {
        java.util.List<Udict> dicts = udictMapper.selectList(null);
        System.out.println("查询所有字典，数量: " + dicts.size());
        for (Udict dict : dicts) {
            System.out.println("  dictid=" + dict.getDictid() + ", ustatus=" + dict.getUstatus() + ", uvalue=" + dict.getUvalue());
        }
    }

    //查询操作 - 根据dictid查询
    @Test
    public void findDictById() {
        Udict dict = udictMapper.selectById(465191484111454209L);
        System.out.println("根据dictid查询结果: " + dict);
    }
}
```

**测试方法说明**：

**addDict() 方法**：

**功能**：添加字典数据，验证公共表功能

**实现逻辑**：
1. 创建 `Udict` 对象
2. 设置状态和值
3. 调用 `udictMapper.insert(udict)` 插入数据
4. Sharding-JDBC 会根据公共表配置，自动将数据同步到所有数据库的 `t_udict` 表
5. `dictid` 由 Sharding-JDBC 使用雪花算法自动生成

**注意事项**：
- `dictid` 字段不需要手动设置，由 Sharding-JDBC 使用雪花算法自动生成
- 插入数据后，数据会同步到所有数据库中的 `t_udict` 表

**deleteDict() 方法**：

**功能**：根据 `dictid` 删除字典数据

**实现逻辑**：
1. 创建 `QueryWrapper` 查询条件
2. 设置查询条件：`dictid = 465191484111454209L`
3. 调用 `udictMapper.delete(wrapper)` 删除数据
4. Sharding-JDBC 会根据公共表配置，自动从所有数据库的 `t_udict` 表中删除数据

**注意事项**：
- 删除操作会同步到所有数据库中的 `t_udict` 表
- 示例中的 `dictid` 值需要替换为实际插入数据后生成的 ID

**findAllDicts() 方法**：

**功能**：查询所有字典信息

**实现逻辑**：
1. 调用 `udictMapper.selectList(null)` 查询所有字典
2. Sharding-JDBC 会从所有数据库的 `t_udict` 表查询数据并合并结果
3. 遍历并输出所有字典信息

**注意事项**：
- 查询会从所有数据库中的 `t_udict` 表查询数据并合并结果

**findDictById() 方法**：

**功能**：根据 `dictid` 查询字典信息

**实现逻辑**：
1. 调用 `udictMapper.selectById(dictid)` 根据主键查询
2. Sharding-JDBC 会根据 `dictid` 从所有数据库的 `t_udict` 表中查询数据

**注意事项**：
- 示例中的 `dictid` 值需要替换为实际插入数据后生成的 ID

## 7. 运行测试

**前置条件**：

1. 确保 MySQL 数据库已启动
2. 确保已创建数据库 `edu_db_1` 和 `edu_db_2`，并在每个数据库中创建 `t_udict` 表
3. 确保 `application.properties` 中的数据库连接信息正确

**运行添加数据测试**：

1. 在 IDE 中打开 `Ss04BroadCastTableApplicationTests` 类
2. 运行 `addDict()` 测试方法
3. 查看控制台输出的 SQL 日志，确认数据插入到所有数据库的 `t_udict` 表
4. 在数据库中验证数据：
   - 查询 `edu_db_1.t_udict` 表，应该包含新插入的字典数据
   - 查询 `edu_db_2.t_udict` 表，应该包含相同的字典数据

**运行删除数据测试**：

1. 先运行 `addDict()` 方法，获取生成的 `dictid` 值
2. 在数据库中查看生成的 `dictid`，选择一个 `dictid` 值
3. 修改 `deleteDict()` 方法中的 `dictid` 值为实际值
4. 运行 `deleteDict()` 测试方法
5. 查看控制台输出，确认删除结果
6. 在数据库中验证数据：
   - 查询 `edu_db_1.t_udict` 表，应该已删除对应数据
   - 查询 `edu_db_2.t_udict` 表，应该已删除对应数据

**运行查询数据测试**：

1. 先运行 `addDict()` 方法，插入一些测试数据
2. 运行 `findAllDicts()` 测试方法
3. 查看控制台输出，确认查询到所有字典数据（合并了所有数据库的数据）
4. 运行 `findDictById()` 测试方法（需要先修改 `dictid` 值）
5. 查看控制台输出，确认查询结果正确

**验证公共表效果**：

查看 edu_db_1.t_udict 表数据：

```sql
USE edu_db_1;
SELECT * FROM t_udict;
```

查看 edu_db_2.t_udict 表数据：

```sql
USE edu_db_2;
SELECT * FROM t_udict;
```

**预期结果**：两个数据库中的 `t_udict` 表应该包含相同的数据

验证数据同步：

```sql
-- 验证两个数据库中的数据是否一致
-- 在 edu_db_1 中查询
USE edu_db_1;
SELECT COUNT(*) as count_db1 FROM t_udict;

-- 在 edu_db_2 中查询
USE edu_db_2;
SELECT COUNT(*) as count_db2 FROM t_udict;

-- 两个数据库中的数据数量应该相同
```

## 8. 注意事项

**配置注意事项**：

1. **Bean 定义覆盖**：必须配置 `spring.main.allow-bean-definition-overriding=true`，解决一个实体类对应多张表时的冲突问题

2. **公共表配置**：公共表需要在每个数据库中创建相同结构的表，否则会报错

3. **数据同步**：对公共表的任何操作（增删改查）都会同步到所有数据库中的该表，需要注意性能影响

4. **主键生成**：`dictid` 由 Sharding-JDBC 使用雪花算法自动生成，无需手动设置

**开发注意事项**：

1. **数据库准备**：确保所有数据库都已创建，并在每个数据库中创建公共表，否则会报错

2. **SQL 日志**：开发阶段建议开启 `spring.shardingsphere.props.sql.show=true`，方便调试和查看实际执行的 SQL 语句

3. **数据一致性**：公共表的数据会在所有数据库中保持一致，适用于存储字典数据、配置信息等固定数据

4. **性能考虑**：对公共表的操作会同步到所有数据库，如果数据库数量较多，可能会影响性能

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
3. 检查数据库是否已创建

**问题：公共表操作失败**

**现象**：执行公共表操作时报错

**解决方案**：
1. 检查所有数据库中是否都已创建公共表
2. 检查表结构是否与实体类字段匹配
3. 检查公共表配置是否正确

**问题：数据不同步**

**现象**：操作公共表后，某些数据库中的数据没有更新

**解决方案**：
1. 检查所有数据库连接是否正常
2. 检查公共表配置是否正确
3. 查看 SQL 日志，确认操作是否同步到所有数据库

## 9. 总结

**核心要点**：

1. **公共表（广播表）**：通过 Sharding-JDBC 将固定数据存储到所有数据库中，实现数据同步

2. **数据同步**：对公共表的任何操作（增删改查）都会同步到所有数据库中的该表

3. **主键生成**：使用雪花算法生成全局唯一的分布式 ID

4. **透明操作**：业务代码无需关心数据同步逻辑，Sharding-JDBC 自动处理

**适用场景**：

- 存储固定数据，如字典表、配置表等
- 数据很少发生变化，查询时经常进行关联
- 需要在所有分片数据库中保持一致的数据

**公共表 vs 分片表**：

- **公共表**：数据在所有数据库中保持一致，适用于字典、配置等固定数据（本示例）
- **分片表**：数据按分片规则分布到不同数据库或表中，适用于业务数据

**扩展学习**：

- 水平分表：将单表拆分为多个物理表
- 水平分库：将数据分布到多个数据库中
- 垂直分库：按业务模块拆分数据库
- 读写分离：主从数据库架构
- 分布式事务：跨分片事务处理

**参考资源**：

- [ShardingSphere 官方文档](https://shardingsphere.apache.org/document/current/cn/)
- [ShardingSphere GitHub](https://github.com/apache/shardingsphere)
- [ShardingSphere 社区](https://shardingsphere.apache.org/community/cn/)
- [数据分片功能说明](https://shardingsphere.apache.org/document/current/cn/features/sharding/)（包含分片最佳实践和注意事项）
