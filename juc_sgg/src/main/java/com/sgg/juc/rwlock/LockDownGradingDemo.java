package com.sgg.juc.rwlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 锁降级：遵循获取写锁→再获取读锁→再释放写锁的次序，写锁能够降级成为读锁。
 * 总结：如果有线程在读，那么写线程是无法获取写锁的，是悲观锁的策略
 * <p>
 * 如果一个线程占有了写锁，在不释放写锁的情况下，它还能占有读锁，即写锁降级为读锁。
 * <p>
 * 读没有完成时候写锁无法获得锁，必须要等着读锁读完后才有机会写
 */
public class LockDownGradingDemo {
    public static void main(String[] args) {
//        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
//        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
//        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
//        //本例，only one 同一个线程
//        readLock.lock();
//        System.out.println("----读取");
//        readLock.unlock();
//
//        writeLock.lock();
//        System.out.println("----写入");
//        writeLock.unlock();
        test3();
    }


    // 正常的读锁、写锁用法
    private static void test1() {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        //正常 A B两个线程
        // A
        readLock.lock();
        System.out.println("----读取");
        readLock.unlock();
        // B
        writeLock.lock();
        System.out.println("----写入");
        writeLock.unlock();
//程序输出：
//        ----读取
//        ----写入
    }


    // 如果一个线程占有了写锁，在不释放写锁的情况下，它还能占有读锁，即写锁降级为读锁。
    // 同一个线程写后可以立刻读
    // 锁降级：获取写锁→ 再获取读锁 → 再释放写锁的次序，写锁能够降级成为读锁
    private static void test2() {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        //本例，only one 同一个线程
        writeLock.lock();
        System.out.println("----写入");

        readLock.lock();
        System.out.println("----读取");

        writeLock.unlock();
        readLock.unlock();
//程序输出：
//        ----写入
//        ----读取
    }

    // 读锁不能变成写锁
    // 读没有完成时候写锁无法获得锁，必须要等着读锁读完后才有机会写
    private static void test3() {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        //本例，only one 同一个线程
        readLock.lock();
        System.out.println("----读取");

        writeLock.lock();
        System.out.println("----写入");

        writeLock.unlock();
        readLock.unlock();
//程序输出：
//        ----读取
    }
}
