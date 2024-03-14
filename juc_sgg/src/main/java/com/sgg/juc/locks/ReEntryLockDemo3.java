package com.sgg.juc.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 显式锁（即Lock）ReentrantLock可重入锁演示
// 注意：正常情况，加锁几次就要解锁几次
public class ReEntryLockDemo3 {
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----come in外层调用");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "\t ----come in内层调用");
                } finally {
                    lock.unlock();
                }

            } finally {
                // 如果加锁次数和释放次数不一样，第二个线程始终无法获取到锁，导致一直在等待。
                lock.unlock();// 正常情况，加锁几次就要解锁几次
            }
        }, "t1").start();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----come in外层调用");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
/*程序输出：
            t1	 ----come in外层调用
            t1	 ----come in内层调用
            t2	 ----come in外层调用
 */
}
