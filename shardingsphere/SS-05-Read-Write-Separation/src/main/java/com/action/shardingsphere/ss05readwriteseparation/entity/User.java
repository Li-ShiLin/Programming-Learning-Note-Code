package com.action.shardingsphere.ss05readwriteseparation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * User 实体类
 * 对应 t_user 表（读写分离）
 */
@Data
@TableName(value = "t_user")  //指定对应表
public class User {
    /**
     * 用户ID，使用雪花算法生成
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户状态
     */
    private String ustatus;
}
