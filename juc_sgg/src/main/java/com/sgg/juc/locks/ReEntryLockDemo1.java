package com.sgg.juc.locks;

// 同步代码块可重入演示
public class ReEntryLockDemo1 {
    public static void main(String[] args) {
        reEntryM1();
    }

    private static void reEntryM1() {
        final Object object = new Object();
        // 三层调用都是同一把锁
        new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "\t ----外层调用");
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + "\t ----中层调用");
                    synchronized (object) {
                        System.out.println(Thread.currentThread().getName() + "\t ----内层调用");
                    }
                }
            }
        }, "t1").start();
    }
/* 程序输出：
            t1	 ----外层调用
            t1	 ----中层调用
            t1	 ----内层调用
 */
}
