-- ============================================
-- Sharding-Proxy 分表效果测试脚本
-- ============================================
-- 使用说明：
-- 1. 确保 Sharding-Proxy 已启动（端口 3307）
-- 2. 确保后端数据库表已创建
-- 3. 连接到 Sharding-Proxy：mysql -h 127.0.0.1 -P 3307 -u root -proot
-- 4. 执行此脚本：source test_sharding.sql 或直接复制执行

USE sharding_db;

-- ============================================
-- 第一步：清理测试数据（可选）
-- ============================================
-- DELETE FROM t_order_item;
-- DELETE FROM t_order;

-- ============================================
-- 第二步：插入测试数据
-- ============================================

-- 测试数据1：user_id=1 (1%2=1 -> ds_1/edu_2), order_id 由 SNOWFLAKE 生成
-- 预期路由：根据生成的 order_id % 2 决定表
INSERT INTO t_order (user_id, status, create_time) VALUES (1, '待支付', NOW());
SET @order_id_1 = LAST_INSERT_ID();
SELECT CONCAT('插入订单1 - order_id:', @order_id_1, ', user_id:1, 预期库:edu_2') AS info;

INSERT INTO t_order (user_id, status, create_time) VALUES (1, '已支付', NOW());
SET @order_id_1_2 = LAST_INSERT_ID();
SELECT CONCAT('插入订单1-2 - order_id:', @order_id_1_2, ', user_id:1, 预期库:edu_2') AS info;

-- 测试数据2：user_id=2 (2%2=0 -> ds_0/edu_1)
INSERT INTO t_order (user_id, status, create_time) VALUES (2, '已完成', NOW());
SET @order_id_2 = LAST_INSERT_ID();
SELECT CONCAT('插入订单2 - order_id:', @order_id_2, ', user_id:2, 预期库:edu_1') AS info;

INSERT INTO t_order (user_id, status, create_time) VALUES (2, '已取消', NOW());
SET @order_id_2_2 = LAST_INSERT_ID();
SELECT CONCAT('插入订单2-2 - order_id:', @order_id_2_2, ', user_id:2, 预期库:edu_1') AS info;

-- 测试数据3：user_id=3 (3%2=1 -> ds_1/edu_2)
INSERT INTO t_order (user_id, status, create_time) VALUES (3, '待发货', NOW());
SET @order_id_3 = LAST_INSERT_ID();
SELECT CONCAT('插入订单3 - order_id:', @order_id_3, ', user_id:3, 预期库:edu_2') AS info;

-- 测试数据4：user_id=4 (4%2=0 -> ds_0/edu_1)
INSERT INTO t_order (user_id, status, create_time) VALUES (4, '已发货', NOW());
SET @order_id_4 = LAST_INSERT_ID();
SELECT CONCAT('插入订单4 - order_id:', @order_id_4, ', user_id:4, 预期库:edu_1') AS info;

-- 插入订单明细（测试绑定表）
INSERT INTO t_order_item (order_id, user_id, product_name, quantity) 
VALUES (@order_id_1, 1, '商品A', 2);

INSERT INTO t_order_item (order_id, user_id, product_name, quantity) 
VALUES (@order_id_2, 2, '商品B', 3);

INSERT INTO t_order_item (order_id, user_id, product_name, quantity) 
VALUES (@order_id_3, 3, '商品C', 1);

-- ============================================
-- 第三步：验证分表效果 - 逻辑表查询
-- ============================================

SELECT '========== 所有订单数据 ==========' AS section;
SELECT 
    order_id,
    user_id,
    status,
    create_time,
    CASE 
        WHEN user_id % 2 = 0 THEN 'edu_1'
        ELSE 'edu_2'
    END AS expected_db,
    CASE 
        WHEN order_id % 2 = 0 THEN 't_order_0'
        ELSE 't_order_1'
    END AS expected_table
FROM t_order
ORDER BY order_id;

SELECT '========== 按 user_id 查询（分库路由测试）==========' AS section;
SELECT * FROM t_order WHERE user_id = 1;
SELECT * FROM t_order WHERE user_id = 2;

SELECT '========== 按 order_id 查询（分表路由测试）==========' AS section;
SELECT * FROM t_order WHERE order_id = @order_id_1;
SELECT * FROM t_order WHERE order_id = @order_id_2;

SELECT '========== 绑定表关联查询测试 ==========' AS section;
SELECT 
    o.order_id,
    o.user_id,
    o.status,
    oi.product_name,
    oi.quantity,
    CASE 
        WHEN o.user_id % 2 = 0 THEN 'edu_1'
        ELSE 'edu_2'
    END AS expected_db
FROM t_order o
JOIN t_order_item oi ON o.order_id = oi.order_id
ORDER BY o.order_id;

-- ============================================
-- 第四步：数据分布统计
-- ============================================

SELECT '========== 数据分布统计 ==========' AS section;
SELECT 
    CASE 
        WHEN user_id % 2 = 0 THEN 'edu_1'
        ELSE 'edu_2'
    END AS database_name,
    CASE 
        WHEN order_id % 2 = 0 THEN 't_order_0'
        ELSE 't_order_1'
    END AS table_name,
    COUNT(*) AS record_count,
    GROUP_CONCAT(order_id ORDER BY order_id) AS order_ids
FROM t_order
GROUP BY database_name, table_name
ORDER BY database_name, table_name;

-- ============================================
-- 第五步：验证分片规则
-- ============================================

SELECT '========== 分片规则验证 ==========' AS section;
SELECT 
    order_id,
    user_id,
    user_id % 2 AS user_id_mod,
    order_id % 2 AS order_id_mod,
    CASE 
        WHEN user_id % 2 = 0 THEN 'edu_1 (ds_0)'
        ELSE 'edu_2 (ds_1)'
    END AS routed_database,
    CASE 
        WHEN order_id % 2 = 0 THEN 't_order_0'
        ELSE 't_order_1'
    END AS routed_table,
    CONCAT(
        CASE 
            WHEN user_id % 2 = 0 THEN 'edu_1'
            ELSE 'edu_2'
        END,
        '.',
        CASE 
            WHEN order_id % 2 = 0 THEN 't_order_0'
            ELSE 't_order_1'
        END
    ) AS full_table_path
FROM t_order
ORDER BY order_id;

-- ============================================
-- 测试完成提示
-- ============================================

SELECT '========== 测试完成 ==========' AS section;
SELECT 
    '请直接连接后端数据库验证数据是否正确分布到物理表中' AS next_step,
    'edu_1: mysql -h 192.168.56.12 -P 3306 -u root -pMySql@1111 edu_1' AS db1_command,
    'edu_2: mysql -h 192.168.56.12 -P 3306 -u root -pMySql@1111 edu_2' AS db2_command,
    '查看日志: bin\\logs\\stdout.log (Windows) 或 bin/logs/stdout.log (Linux)' AS log_path;
