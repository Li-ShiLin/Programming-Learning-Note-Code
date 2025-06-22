package com.qf.service.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 
 *
 */
public class provider {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"provider.xml"});
        context.start();
        try {
            System.in.read();  // 让当前服务⼀直在线，不会被关闭，按任意键退出
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
