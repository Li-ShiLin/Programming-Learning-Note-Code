-- 创建数据库（垂直分库）
-- 注意：edu_db_1 和 edu_db_2 可能已存在，这里仅创建 user_db

-- 创建 user_db 数据库（专库专表）
CREATE DATABASE IF NOT EXISTS `user_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `user_db`;

-- 创建 t_user 表（垂直分库：专库专表）
CREATE TABLE IF NOT EXISTS `t_user` (
  `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
  `username` VARCHAR(50) NOT NULL COMMENT '用户名',
  `ustatus` VARCHAR(50) NOT NULL COMMENT '用户状态',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表（垂直分库）';

-- 注意：
-- 1. edu_db_1 和 edu_db_2 数据库应该已经存在（来自其他模块）
-- 2. 垂直分库的目的是实现专库专表，t_user 表只存在于 user_db 数据库中
-- 3. 配置中 m0 数据源指向 user_db，m1 和 m2 分别指向 edu_db_1 和 edu_db_2
