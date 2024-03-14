package com.sgg.juc.volatiles;


// 什么是顺序读？什么是顺序写？
public class VolatileTest {
    int i = 0;
    volatile boolean flag = false;
    public void write() {
        i = 2;
        // 在每一个volatile写操作前面插入一个StoreStore屏障
        // 在每一个volatile写操作后面插入一个StoreLoad屏障
        // volatile 写之前的操作，都禁止重排序到volatile之后
        flag = true;
    }
    public void read() {
        // 在每一个volatile读操作后面插入一个LoadLoad屏障
        // 在每一个volatile读操作后面插入一个LoadStore屏障
        // volatile读之后的操作，都禁止重排序到volatile之前
        if (flag) {
            System.out.println("--i = " + i);
        }
    }
}
