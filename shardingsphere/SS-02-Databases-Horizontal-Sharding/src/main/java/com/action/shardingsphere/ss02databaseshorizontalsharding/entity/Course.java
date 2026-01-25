package com.action.shardingsphere.ss02databaseshorizontalsharding.entity;

import lombok.Data;

/**
 * Course 实体类
 * 对应 edu_db_1 和 edu_db_2 数据库中的 course_1 和 course_2 表
 */
@Data
public class Course {
    /**
     * 课程ID，使用雪花算法生成
     */
    private Long cid;

    /**
     * 课程名称
     */
    private String cname;

    /**
     * 用户ID，用于数据库分片
     */
    private Long userId;

    /**
     * 课程状态
     */
    private String cstatus;
}
