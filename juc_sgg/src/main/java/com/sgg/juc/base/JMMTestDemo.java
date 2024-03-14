package com.sgg.juc.base;


//  修复02：把value定义为volatile变量，由于setter方法对value的修改不依赖value的原值，满足volatile关键字使用场景
public class JMMTestDemo {
    /**
     * 使用：把value定义为volatile变量，由于setter方法对value的修改不依赖value的原值，满足volatile关键字使用场景
     * 理由：利用volatile保证读取操作的可见性;利用synchronized保证复合操作的原子性结合使用锁和volatile变量来减少同步的开销
     */
    private volatile int value = 0;

    public int getValue() {
        return value;   // 利用volatile保证读取操作的可见性
    }

    public synchronized int setValue() {
        return ++value;   // 利用synchronized保证复合操作的原子性
    }
}


// 修复01：把getter/setter方法都定义为synchronized方法
/*public class JMMTestDemo {
    private int value = 0;

    public synchronized int getValue() {
        return value;
    }

    public synchronized int setValue() {
        return ++value;
    }
}*/


/*
public class JMMTestDemo {
    private int value = 0;
    public int getValue() {
        return value;
    }
    public int setValue() {
        return ++value;
    }
}
*/
