package com.sgg.juc.rwlock;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
class ReentrantLockResource //资源类，模拟一个简单的缓存
{
    Map<String,String> map = new HashMap<>();
    //=====ReentrantLock 等价于 =====synchronized，之前讲解过
    Lock lock = new ReentrantLock();
    //=====ReentrantReadWriteLock 一体两面，读写互斥，读读共享

    // 写锁：对公共资源map进行写更改时，需要加写锁
    public void write(String key ,String value)
    {
        lock.lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"\t"+"正在写入");
            map.put(key,value);
            //暂停毫秒
            try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t"+"完成写入");
        }finally {
            lock.unlock();
        }
    }


    // 读锁：对公共资源map进行读取时，需要加读锁
    public void read(String key)
    {
        lock.lock();
        try
        {
            System.out.println(Thread.currentThread().getName()+"\t"+"正在读取");
            String result = map.get(key);
            //暂停200毫秒
//            //try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
//            try { TimeUnit.MILLISECONDS.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t"+"完成读取"+"\t"+result);
        }finally {
            lock.unlock();
        }
    }
}

public class ReentrantLockDemo {

    public static void main(String[] args) {
        testReentrantLock();
    }

    // 演示利用ReentrantLock读读和写进行加锁
    private static void testReentrantLock(){
        ReentrantLockResource reentrantLockResource = new ReentrantLockResource();

        for (int i = 1; i <=10; i++) {
            int finalI = i;
            new Thread(() -> {
                reentrantLockResource.write(finalI +"", finalI +"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <=10; i++) {
            int finalI = i;
            new Thread(() -> {
                reentrantLockResource.read(finalI +"");
            },String.valueOf(i)).start();
        }
//程序输出：任何一个写（读）线程在写（读）的时候不会被其他读写线程干扰，也就是只有写完或者是读完之后，其他线程才会进行读或者写
//        1	正在写入
//        1	完成写入
//        2	正在写入
//        2	完成写入
//        3	正在写入
//        3	完成写入
//        9	正在写入
//        9	完成写入
//        5	正在写入
//        5	完成写入
//        1	正在读取
//        1	完成读取	1
//        7	正在写入
//        7	完成写入
//        8	正在写入
//        8	完成写入
//        4	正在写入
//        4	完成写入
//        8	正在读取
//        8	完成读取	8
//        2	正在读取
//        2	完成读取	2
//        6	正在写入
//        6	完成写入
//        3	正在读取
//        3	完成读取	3
//        4	正在读取
//        4	完成读取	4
//        5	正在读取
//        5	完成读取	5
//        6	正在读取
//        6	完成读取	6
//        7	正在读取
//        7	完成读取	7
//        10	正在写入
//        10	完成写入
//        9	正在读取
//        9	完成读取	9
//        10	正在读取
//        10	完成读取	10
    }
}
