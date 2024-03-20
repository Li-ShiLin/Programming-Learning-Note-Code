package com.atguigu.spring6;

import org.junit.jupiter.api.Test;

public class ReflectDemo {

    // 演示如何使用反射创建对象
    @Test
    public void testUserObject2() throws Exception {
        //获取类Class对象
        Class clazz = Class.forName("com.atguigu.spring6.User");
        //调用方法创建对象
        //Object o = clazz.newInstance();
        User user = (User) clazz.getDeclaredConstructor().newInstance();
        System.out.println(user);
//程序输出：
//        无参数构造执行了..
//        com.atguigu.spring6.User@3c153a1
    }
}
