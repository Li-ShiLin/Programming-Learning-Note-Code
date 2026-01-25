-- 创建数据库（公共表：广播表）
-- 注意：公共表需要在每个数据库中创建相同结构的表

-- 创建 edu_db_1 数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS `edu_db_1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `edu_db_1`;

-- 创建 t_udict 表（公共表：广播表）
CREATE TABLE IF NOT EXISTS `t_udict` (
  `dictid` BIGINT(20) NOT NULL COMMENT '字典ID',
  `ustatus` VARCHAR(50) NOT NULL COMMENT '状态',
  `uvalue` VARCHAR(50) NOT NULL COMMENT '值',
  PRIMARY KEY (`dictid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表（公共表）';

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

-- 注意：
-- 1. 公共表（广播表）需要在每个数据库中创建相同结构的表
-- 2. 对公共表的任何操作（增删改查）都会同步到所有数据库中的该表
-- 3. 公共表适用于存储固定数据，如字典表、配置表等
