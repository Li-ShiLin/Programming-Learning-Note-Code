package com.atguigu.java;

/**
 *
 * -13 11:55
 *
 * 过程三：初始化阶段
 */
public class InitializationTest {
    public static int id = 1;
    public static int number;

    static {
        number = 2;
        System.out.println("father static{}");
    }
}
