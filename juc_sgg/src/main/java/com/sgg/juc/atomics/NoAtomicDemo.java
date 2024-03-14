package com.sgg.juc.atomics;


// 没有atomics时，通过synchronized保证原子性
public class NoAtomicDemo {
    volatile int number = 0;
    // 读取
    public int getNumber() {
        return number;
    }
    // 写入加锁保证原子性
    public synchronized void setNumber() {
        number++;
    }
}
