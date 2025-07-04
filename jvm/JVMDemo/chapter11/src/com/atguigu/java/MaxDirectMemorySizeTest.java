package com.atguigu.java;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * -Xmx20m -XX:MaxDirectMemorySize=10m
 *  shkstart@126.com
 *   0:36
 */
public class MaxDirectMemorySizeTest {
    private static final long _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe)unsafeField.get(null);
        while(true){
            unsafe.allocateMemory(_1MB);
        }

    }
}
