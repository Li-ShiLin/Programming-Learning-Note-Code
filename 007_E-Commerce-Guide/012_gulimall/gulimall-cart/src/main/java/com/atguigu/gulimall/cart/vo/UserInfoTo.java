package com.atguigu.gulimall.cart.vo;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UserInfoTo {

    private Long userId; // 如果用户登录了，会有对应的userId
    private String userKey; //一定封装,登录与否都要封装

    // 是否是临时用户
    private boolean tempUser = false;
}
