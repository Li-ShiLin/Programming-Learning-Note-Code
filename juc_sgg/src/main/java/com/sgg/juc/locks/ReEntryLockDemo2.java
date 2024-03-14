package com.sgg.juc.locks;

// 同步方法可重入演示
public class ReEntryLockDemo2 {

    public static void main(String[] args) {
        ReEntryLockDemo2 reEntryLockDemo2 = new ReEntryLockDemo2();
        new Thread(() -> {
            reEntryLockDemo2.m1();
        }, "t1").start();
    }

    public synchronized void m1() {
        //指的是可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁，这样的锁就叫做可重入锁。
        System.out.println(Thread.currentThread().getName() + "\t ----come in");
        m2();
        System.out.println(Thread.currentThread().getName() + "\t ----end m1");
    }

    public synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + "\t ----come in");
        m3();
    }

    public synchronized void m3() {
        System.out.println(Thread.currentThread().getName() + "\t ----come in");
    }
/* 程序输出：
            t1	 ----come in
            t1	 ----come in
            t1	 ----come in
            t1	 ----end m1
 */
}
