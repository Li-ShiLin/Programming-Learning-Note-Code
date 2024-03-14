package com.sgg.juc.volatiles;


// 开销较低的读，写锁策略
public class UseVolatileDemo1 {
    /**
     * 使用：当读远多于写，结合使用内部锁和volatile变量来减少同步的开销
     * 理由：利用volatile保证读取操作的可见性;利用synchronized保证复合操作的原子性
     */
    public class Counter {
        private volatile int value;
        public int getValue() {
            return value;  // 利用volatile保证读取操作的可见性
        }
        public synchronized int increment() {
            return value++;  // 利用synchronized保证复合操作的原子性
        }
    }
}
