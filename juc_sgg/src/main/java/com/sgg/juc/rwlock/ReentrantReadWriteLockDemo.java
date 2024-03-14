package com.sgg.juc.rwlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
// 演示读写锁
class RWLockResource //资源类，模拟一个简单的缓存
{
    Map<String,String> map = new HashMap<>();
    //=====ReentrantLock 等价于 =====synchronized，之前讲解过
    Lock lock = new ReentrantLock();
    //=====ReentrantReadWriteLock 一体两面，读写互斥，读读共享
    ReadWriteLock rwLock = new ReentrantReadWriteLock();


    // 写锁：对公共资源map进行写更改时，需要加写锁
    public void write(String key ,String value)
    {
        rwLock.writeLock().lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"\t"+"正在写入");
            map.put(key,value);
            //暂停毫秒
            try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t"+"完成写入");
        }finally {
            rwLock.writeLock().unlock();
        }
    }


    // 读锁：对公共资源map进行读取时，需要加读锁
    public void read(String key)
    {
        rwLock.readLock().lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"\t"+"正在读取");
            String result = map.get(key);
            //暂停200毫秒
            //try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }

            //暂停2000毫秒,演示读锁没有完成之前，写锁无法获得
            try { TimeUnit.MILLISECONDS.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t"+"完成读取"+"\t"+result);
        }finally {
            rwLock.readLock().unlock();
        }
    }
}


public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        testReentrantReadWriteLock();
    }

    private static void testReentrantReadWriteLock(){
        RWLockResource RWLockResource = new RWLockResource();

        for (int i = 1; i <=10; i++) {
            int finalI = i;
            new Thread(() -> {
                RWLockResource.write(finalI +"", finalI +"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <=10; i++) {
            int finalI = i;
            new Thread(() -> {
                RWLockResource.read(finalI +"");
            },String.valueOf(i)).start();
        }

        // 暂停几秒钟线程
        // 暂停2000毫秒,演示“读锁没有完成之前，写锁无法获得”
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        for (int i = 1; i <=3; i++) {
            int finalI = i;
            new Thread(() -> {
                RWLockResource.write(finalI +"", finalI +"");
            },"新写锁线程->"+String.valueOf(i)).start();
        }
//程序输出：1.写线程和其他读写线程都互斥，读线程之间不会互斥    2.读锁没有完成之前，写锁无法获得（防止脏读）
//        2	正在写入
//        2	完成写入
//        3	正在写入
//        3	完成写入
//        1	正在写入
//        1	完成写入
//        8	正在写入
//        8	完成写入
//        10	正在写入
//        10	完成写入
//        6	正在写入
//        6	完成写入
//        7	正在写入
//        7	完成写入
//        4	正在写入
//        4	完成写入
//        9	正在写入
//        9	完成写入
//        5	正在写入
//        5	完成写入
//        1	正在读取
//        2	正在读取
//        3	正在读取
//        4	正在读取
//        5	正在读取
//        6	正在读取
//        7	正在读取
//        8	正在读取
//        10	正在读取
//        9	正在读取
//        4	完成读取	4
//        1	完成读取	1
//        2	完成读取	2
//        10	完成读取	10
//        6	完成读取	6
//        5	完成读取	5
//        7	完成读取	7
//        9	完成读取	9
//        3	完成读取	3
//        8	完成读取	8
//        新写锁线程->1	正在写入
//        新写锁线程->1	完成写入
//        新写锁线程->2	正在写入
//        新写锁线程->2	完成写入
//        新写锁线程->3	正在写入
//        新写锁线程->3	完成写入
    }

}
