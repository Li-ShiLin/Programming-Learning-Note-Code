-- 创建数据库（读写分离）
-- 注意：读写分离需要配置 MySQL 主从复制
-- 主库：localhost:3306
-- 从库：localhost:3307

-- 在主库创建 user_db 数据库
CREATE DATABASE IF NOT EXISTS `user_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `user_db`;

-- 在主库创建 t_user 表
CREATE TABLE IF NOT EXISTS `t_user` (
  `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
  `username` VARCHAR(50) NOT NULL COMMENT '用户名',
  `ustatus` VARCHAR(50) NOT NULL COMMENT '用户状态',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表（读写分离）';

-- 注意：
-- 1. 从库的数据库和表会通过 MySQL 主从复制自动创建
-- 2. 需要在从库（localhost:3307）中配置主从复制
-- 3. 配置主从复制后，主库的数据会自动同步到从库
-- 4. Sharding-JDBC 会自动将写操作路由到主库，读操作路由到从库
