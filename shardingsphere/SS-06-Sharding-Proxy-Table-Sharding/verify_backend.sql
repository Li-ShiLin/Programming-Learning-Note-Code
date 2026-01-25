-- ============================================
-- 后端数据库数据分布验证脚本
-- ============================================
-- 使用说明：
-- 1. 直接连接到 MySQL 数据库：mysql -h 192.168.56.12 -P 3306 -u root -pMySql@1111
-- 2. 分别对 edu_1 和 edu_2 数据库执行此脚本

-- ============================================
-- 验证 edu_1 数据库
-- ============================================
USE edu_1;

SELECT '========== edu_1 数据库数据分布 ==========' AS section;

SELECT 't_order_0 表数据:' AS table_info;
SELECT 
    'edu_1.t_order_0' AS table_name,
    order_id,
    user_id,
    status,
    create_time,
    'user_id % 2 = ' AS check_info,
    user_id % 2 AS user_mod_result,
    'order_id % 2 = ' AS check_info2,
    order_id % 2 AS order_mod_result
FROM t_order_0
ORDER BY order_id;

SELECT 't_order_1 表数据:' AS table_info;
SELECT 
    'edu_1.t_order_1' AS table_name,
    order_id,
    user_id,
    status,
    create_time,
    'user_id % 2 = ' AS check_info,
    user_id % 2 AS user_mod_result,
    'order_id % 2 = ' AS check_info2,
    order_id % 2 AS order_mod_result
FROM t_order_1
ORDER BY order_id;

SELECT 'edu_1 数据统计:' AS stats;
SELECT 
    't_order_0' AS table_name,
    COUNT(*) AS record_count
FROM t_order_0
UNION ALL
SELECT 
    't_order_1' AS table_name,
    COUNT(*) AS record_count
FROM t_order_1;

-- ============================================
-- 验证 edu_2 数据库
-- ============================================
USE edu_2;

SELECT '========== edu_2 数据库数据分布 ==========' AS section;

SELECT 't_order_0 表数据:' AS table_info;
SELECT 
    'edu_2.t_order_0' AS table_name,
    order_id,
    user_id,
    status,
    create_time,
    'user_id % 2 = ' AS check_info,
    user_id % 2 AS user_mod_result,
    'order_id % 2 = ' AS check_info2,
    order_id % 2 AS order_mod_result
FROM t_order_0
ORDER BY order_id;

SELECT 't_order_1 表数据:' AS table_info;
SELECT 
    'edu_2.t_order_1' AS table_name,
    order_id,
    user_id,
    status,
    create_time,
    'user_id % 2 = ' AS check_info,
    user_id % 2 AS user_mod_result,
    'order_id % 2 = ' AS check_info2,
    order_id % 2 AS order_mod_result
FROM t_order_1
ORDER BY order_id;

SELECT 'edu_2 数据统计:' AS stats;
SELECT 
    't_order_0' AS table_name,
    COUNT(*) AS record_count
FROM t_order_0
UNION ALL
SELECT 
    't_order_1' AS table_name,
    COUNT(*) AS record_count
FROM t_order_1;

-- ============================================
-- 验证规则说明
-- ============================================
SELECT '========== 分片规则验证说明 ==========' AS section;
SELECT 
    '分库规则: user_id % 2 = 0 -> edu_1 (ds_0), user_id % 2 = 1 -> edu_2 (ds_1)' AS db_rule,
    '分表规则: order_id % 2 = 0 -> t_order_0, order_id % 2 = 1 -> t_order_1' AS table_rule,
    '验证要点: 检查 user_id % 2 结果是否与数据库匹配' AS check_point1,
    '验证要点: 检查 order_id % 2 结果是否与表名匹配' AS check_point2;
