package com.sgg.juc.atomics;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
// CountDownLatch的使用：
class MyNumber
{
    // 默认初始值是0
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addPlusPlus()
    {
        atomicInteger.getAndIncrement();
    }
}

public class AtomicIntegerDemo
{
    // 50个线程
    public static final int SIZE = 50;

    public static void main(String[] args) throws InterruptedException
    {
        MyNumber myNumber = new MyNumber();
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);

        for (int i = 1; i <=SIZE; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <=1000; j++) {
                        myNumber.addPlusPlus();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();
        }
        //等待上面50个线程全部计算完成后，再去获得最终值
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t"+"result: "+myNumber.atomicInteger.get());
 // 程序输出：  main	result: 50000
    }
}


//  没有利用countDownLatch优化之前的代码：
//class MyNumber
//{
//    // 默认初始值是0
//    AtomicInteger atomicInteger = new AtomicInteger();
//    public void addPlusPlus()
//    {
//        atomicInteger.getAndIncrement();
//    }
//}
//public class AtomicIntegerDemo
//{
//    // 50个线程
//    public static final int SIZE = 50;
//
//    public static void main(String[] args) throws InterruptedException
//    {
//        MyNumber myNumber = new MyNumber();
//        for (int i = 1; i <=SIZE; i++) {
//            new Thread(() -> {
//                    for (int j = 1; j <=1000; j++) {
//                        myNumber.addPlusPlus();
//                    }
//            },String.valueOf(i)).start();
//        }
//        //等待上面50个线程全部计算完成后，再去获得最终值
//        //暂停几秒钟线程
//        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
//        System.out.println(Thread.currentThread().getName()+"\t"+"result: "+myNumber.atomicInteger.get());
////程序输出：
////         main	result: 50000
//    }
//}
