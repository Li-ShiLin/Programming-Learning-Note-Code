-- 创建数据库
CREATE DATABASE IF NOT EXISTS `course_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `course_db`;

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

