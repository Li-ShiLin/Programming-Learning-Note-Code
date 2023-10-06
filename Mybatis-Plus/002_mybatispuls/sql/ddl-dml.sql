USE  `mybatis_plus`;


CREATE TABLE `t_user`
(
    `id`    BIGINT(20) NOT NULL COMMENT '主键ID',
    `name`  VARCHAR(30) DEFAULT NULL COMMENT '姓名',
    `age`   INT(11) DEFAULT NULL COMMENT '年龄',
    `email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;



INSERT INTO `t_user` (id, NAME, age, email)
VALUES (1, 'Jone', 18, 'test1@baomidou.com'),
       (2, 'Jack', 20, 'test2@baomidou.com'),
       (3, 'Tom', 28, 'test3@baomidou.com'),
       (4, 'Sandy', 21, 'test4@baomidou.com'),
       (5, 'Billie', 24, 'test5@baomidou.com');