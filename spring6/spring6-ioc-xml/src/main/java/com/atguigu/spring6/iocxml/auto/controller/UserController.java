package com.atguigu.spring6.iocxml.auto.controller;

import com.atguigu.spring6.iocxml.auto.service.UserService;

public class UserController {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void addUser() {
        System.out.println("controller方法执行了...");
        //调用service的方法
        userService.addUser();
    }
}
