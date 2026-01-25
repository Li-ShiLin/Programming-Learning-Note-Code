# Sharding-Proxy 分表效果测试步骤

## 配置说明

根据您的配置，分片规则如下：

- **分库策略**：基于 `user_id % 2`，路由到 `ds_0` (edu_1) 或 `ds_1` (edu_2)
- **分表策略**：基于 `order_id % 2`，路由到 `t_order_0` 或 `t_order_1`
- **实际数据节点**：`ds_${0..1}.t_order_${0..1}`，共4个物理表：
    - `edu_1.t_order_0`
    - `edu_1.t_order_1`
    - `edu_2.t_order_0`
    - `edu_2.t_order_1`

## 测试步骤

### 第一步：启动 Sharding-Proxy

1. 打开命令行，进入 Sharding-Proxy 的 bin 目录：

```bash
cd E:\xxxx\shardingsphere\shardingsphere\SS-06-Sharding-Proxy-Table-Sharding\apache-shardingsphere-incubating-4.0.1-sharding-proxy-bin\bin
```

2. 启动 Sharding-Proxy（Windows）：

```bash
start.bat
```

或者（Linux/Mac）：

```bash
sh start.sh
```

3. 验证启动成功：
    - 默认端口：3307
    - 查看日志：`bin/logs/stdout.log`
    - 确认看到 "Sharding-Proxy start success" 或类似信息

### 第二步：准备后端数据库表结构

**重要**：需要在每个后端数据库（edu_1 和 edu_2）中创建物理表。

直接连接到 MySQL 数据库 `192.168.56.12:3306`，分别在 `edu_1` 和 `edu_2` 数据库中执行：

```sql
-- 在 edu_1 数据库中创建表
USE edu_1;

CREATE TABLE IF NOT EXISTS t_order_0 (
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    status VARCHAR(50),
    create_time DATETIME,
    PRIMARY KEY (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS t_order_1 (
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    status VARCHAR(50),
    create_time DATETIME,
    PRIMARY KEY (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS t_order_item_0 (
    order_item_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    product_name VARCHAR(200),
    quantity INT,
    PRIMARY KEY (order_item_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS t_order_item_1 (
    order_item_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    product_name VARCHAR(200),
    quantity INT,
    PRIMARY KEY (order_item_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 在 edu_2 数据库中创建相同的表
USE edu_2;

CREATE TABLE IF NOT EXISTS t_order_0 (
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    status VARCHAR(50),
    create_time DATETIME,
    PRIMARY KEY (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS t_order_1 (
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    status VARCHAR(50),
    create_time DATETIME,
    PRIMARY KEY (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS t_order_item_0 (
    order_item_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    product_name VARCHAR(200),
    quantity INT,
    PRIMARY KEY (order_item_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS t_order_item_1 (
    order_item_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    product_name VARCHAR(200),
    quantity INT,
    PRIMARY KEY (order_item_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### 第三步：连接 Sharding-Proxy

使用 MySQL 客户端连接到 Sharding-Proxy：

```bash
mysql -h 127.0.0.1 -P 3307 -u root -proot
```

或者使用图形化工具（如 Navicat、DBeaver）：

- 主机：127.0.0.1
- 端口：3307
- 用户名：root
- 密码：root
- 数据库：sharding_db

### 第四步：插入测试数据

在 Sharding-Proxy 中执行以下 SQL，验证分库分表路由：

```sql
USE sharding_db;

-- 测试场景1：user_id=1 (1%2=1 -> ds_1), order_id=1 (1%2=1 -> t_order_1)
-- 预期：数据插入到 edu_2.t_order_1
INSERT INTO t_order (user_id, status, create_time)
VALUES (1, '待支付', NOW());
SET @order_id_1 = LAST_INSERT_ID();

-- 测试场景2：user_id=2 (2%2=0 -> ds_0), order_id=2 (2%2=0 -> t_order_0)
-- 预期：数据插入到 edu_1.t_order_0
INSERT INTO t_order (user_id, status, create_time)
VALUES (2, '已支付', NOW());
SET @order_id_2 = LAST_INSERT_ID();

-- 测试场景3：user_id=3 (3%2=1 -> ds_1), order_id=3 (3%2=1 -> t_order_1)
-- 预期：数据插入到 edu_2.t_order_1
INSERT INTO t_order (user_id, status, create_time)
VALUES (3, '已完成', NOW());
SET @order_id_3 = LAST_INSERT_ID();

-- 测试场景4：user_id=4 (4%2=0 -> ds_0), order_id=4 (4%2=0 -> t_order_0)
-- 预期：数据插入到 edu_1.t_order_0
INSERT INTO t_order (user_id, status, create_time)
VALUES (4, '已取消', NOW());
SET @order_id_4 = LAST_INSERT_ID();

-- 测试场景5：user_id=5 (5%2=1 -> ds_1), order_id=5 (5%2=1 -> t_order_1)
-- 预期：数据插入到 edu_2.t_order_1
INSERT INTO t_order (user_id, status, create_time)
VALUES (5, '待发货', NOW());
SET @order_id_5 = LAST_INSERT_ID();

-- 测试场景6：user_id=6 (6%2=0 -> ds_0), order_id=6 (6%2=0 -> t_order_0)
-- 预期：数据插入到 edu_1.t_order_0
INSERT INTO t_order (user_id, status, create_time)
VALUES (6, '已发货', NOW());
SET @order_id_6 = LAST_INSERT_ID();

-- 插入订单明细（绑定表测试）
INSERT INTO t_order_item (order_id, user_id, product_name, quantity)
VALUES (@order_id_1, 1, '商品A', 2);

INSERT INTO t_order_item (order_id, user_id, product_name, quantity)
VALUES (@order_id_2, 2, '商品B', 3);

-- 查看插入的数据（通过逻辑表查询）
SELECT * FROM t_order ORDER BY order_id;
```

### 第五步：验证分表效果

#### 5.1 在 Sharding-Proxy 中查询（逻辑表）

```sql
-- 查询所有订单（会路由到所有分片）
SELECT * FROM t_order ORDER BY order_id;

-- 根据 user_id 查询（会路由到对应分库）
SELECT * FROM t_order WHERE user_id = 1;
SELECT * FROM t_order WHERE user_id = 2;

-- 根据 order_id 查询（会路由到对应分表）
SELECT * FROM t_order WHERE order_id = @order_id_1;
SELECT * FROM t_order WHERE order_id = @order_id_2;

-- 测试绑定表关联查询
SELECT o.order_id, o.user_id, o.status, oi.product_name, oi.quantity
FROM t_order o
JOIN t_order_item oi ON o.order_id = oi.order_id
WHERE o.user_id = 1;
```

#### 5.2 直接连接后端数据库验证（物理表）

**连接到 edu_1 数据库：**

```bash
mysql -h 192.168.56.12 -P 3306 -u root -pMySql@1111 edu_1
```

```sql
-- 查看 t_order_0 表的数据
SELECT 'edu_1.t_order_0' AS table_name, * FROM t_order_0;

-- 查看 t_order_1 表的数据
SELECT 'edu_1.t_order_1' AS table_name, * FROM t_order_1;
```

**连接到 edu_2 数据库：**

```bash
mysql -h 192.168.56.12 -P 3306 -u root -pMySql@1111 edu_2
```

```sql
-- 查看 t_order_0 表的数据
SELECT 'edu_2.t_order_0' AS table_name, * FROM t_order_0;

-- 查看 t_order_1 表的数据
SELECT 'edu_2.t_order_1' AS table_name, * FROM t_order_1;
```

#### 5.3 验证数据分布规则

根据分片规则验证：

- `user_id % 2 = 0` → `ds_0` (edu_1)
- `user_id % 2 = 1` → `ds_1` (edu_2)
- `order_id % 2 = 0` → `t_order_0`
- `order_id % 2 = 1` → `t_order_1`

**预期数据分布：**

- user_id=1, order_id=奇数 → edu_2.t_order_1
- user_id=1, order_id=偶数 → edu_2.t_order_0
- user_id=2, order_id=奇数 → edu_1.t_order_1
- user_id=2, order_id=偶数 → edu_1.t_order_0

### 第六步：查看 Sharding-Proxy 日志

查看分片路由日志，确认 SQL 路由情况：

```bash
# Windows
type bin\logs\stdout.log | findstr "route\|sharding\|SQL"

# Linux/Mac
tail -f bin/logs/stdout.log | grep -i "route\|sharding\|SQL"
```

由于配置了 `sql.show: true`，日志中会显示：

- 实际执行的 SQL 语句
- 路由到的数据源和表
- 分片键的值

### 第七步：性能测试（可选）

批量插入数据测试分表性能：

```sql
-- 批量插入测试
DELIMITER $$
CREATE PROCEDURE test_batch_insert()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 100 DO
        INSERT INTO t_order (user_id, status, create_time) 
        VALUES (i, CONCAT('状态', i), NOW());
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

CALL test_batch_insert();

-- 验证数据分布
SELECT 
    CASE
        WHEN user_id % 2 = 0 THEN 'edu_1'
        ELSE 'edu_2'
    END AS database_name,
    CASE
        WHEN order_id % 2 = 0 THEN 't_order_0'
        ELSE 't_order_1'
    END AS table_name,
    COUNT(*) AS count
FROM t_order
GROUP BY database_name, table_name;
```

## 验证要点

1. ✅ **分库路由正确**：不同 user_id 的数据路由到正确的数据库
2. ✅ **分表路由正确**：不同 order_id 的数据路由到正确的表
3. ✅ **数据完整性**：通过逻辑表查询能获取所有分片数据
4. ✅ **绑定表功能**：t_order 和 t_order_item 关联查询正确
5. ✅ **主键生成**：SNOWFLAKE 算法生成的主键唯一且递增
6. ✅ **日志显示**：SQL 路由信息在日志中正确显示

## 常见问题

1. **连接失败**：检查 Sharding-Proxy 是否启动，端口 3307 是否被占用
2. **表不存在**：确保后端数据库已创建物理表
3. **数据查询不到**：检查分片键值是否符合路由规则
4. **主键冲突**：SNOWFLAKE 算法生成的主键应该唯一，如冲突检查配置

## 测试脚本

可以使用提供的 `test_sharding.sql` 脚本进行自动化测试。
