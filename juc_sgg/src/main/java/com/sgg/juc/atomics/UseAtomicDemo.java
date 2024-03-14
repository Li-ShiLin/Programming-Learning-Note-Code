package com.sgg.juc.atomics;

import java.util.concurrent.atomic.AtomicInteger;

public class UseAtomicDemo {
    volatile int number;
    // 读取
    public int getNumber() {
        return number;
    }
    // 写入加锁保证原子性
    public synchronized void setNumber() {
        number++;
    }
    // ====== 使用AtomicInteger ======
    AtomicInteger atomicInteger = new AtomicInteger();
    public int getAtomicInteger() {
        return atomicInteger.get();
    }
    public void setAtomicInteger() {
        atomicInteger.getAndIncrement();
    }
}
