CREATE DATABASE `mybatis_plus_1` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `mybatis_plus_1`;
CREATE TABLE product
(
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    NAME VARCHAR(30) NULL DEFAULT NULL COMMENT '商品名称',
    price INT(11) DEFAULT 0 COMMENT '价格',
    VERSION INT(11) DEFAULT 0 COMMENT '乐观锁版本号',
    PRIMARY KEY (id)
);

INSERT INTO product (id, NAME, price) VALUES (1, '外星人笔记本', 100);


CREATE DATABASE `mybatis_plus` CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;
USE `mybatis_plus`;
CREATE TABLE `t_user` (
                          `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                          `user_name` varchar(30) DEFAULT NULL COMMENT '姓名',
                          `age` int(11) DEFAULT NULL COMMENT '年龄',
                          `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
                          `is_deleted` int(11) DEFAULT '0' COMMENT '是否被删除',
                          `sex` int(11) DEFAULT '0' COMMENT '性别',
                          PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8



insert into `t_user` (`uid`, `user_name`, `age`, `email`, `is_deleted`, `sex`) values('1','Jone','18','test1@baomidou.com','1','0');
insert into `t_user` (`uid`, `user_name`, `age`, `email`, `is_deleted`, `sex`) values('2','Jack','20','test2@baomidou.com','1','0');
insert into `t_user` (`uid`, `user_name`, `age`, `email`, `is_deleted`, `sex`) values('3','Tom','28','test3@baomidou.com','1','0');
insert into `t_user` (`uid`, `user_name`, `age`, `email`, `is_deleted`, `sex`) values('4','小明','21','test@atguigu.com','0','0');