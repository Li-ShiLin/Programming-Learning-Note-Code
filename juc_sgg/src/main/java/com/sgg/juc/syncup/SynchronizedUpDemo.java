package com.sgg.juc.syncup;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

public class SynchronizedUpDemo {

    public static void main(String[] args) {
        biasedLock4();
    }

    private static void noLock01() {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
//程序输出：
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//    4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }

    // hashCode测试：对象的hashCode只有在调用hashCode方法时才会生成
    private static void noLock02() {
        Object o = new Object();
        System.out.println("10进制："+o.hashCode());
        System.out.println("16进制："+Integer.toHexString(o.hashCode()));
        System.out.println("2进制："+Integer.toBinaryString(o.hashCode()));
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
//程序输出：
//    10进制：991505714
//    16进制：3b192d32
//    2进制：111011000110010010110100110010
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           01 32 2d 19 (00000001 00110010 00101101 00011001) (422392321)
//    4     4        (object header)                           3b 00 00 00 (00111011 00000000 00000000 00000000) (59)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }

    // 由于默认情况下偏向锁的开启有4秒延迟，所以会发现锁的标志位显示的是轻量级锁
    private static void biasedLock1() {
        Object o = new Object();

        new Thread(() -> {
            synchronized (o){
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        },"t1").start();
//程序输出（一切默认）：
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           70 ed 5f a2 (01110000 11101101 01011111 10100010) (-1570771600)
//    4     4        (object header)                           c7 00 00 00 (11000111 00000000 00000000 00000000) (199)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }


    // 演示偏向锁：关闭延迟(演示偏向锁时需要设置-XX:BiasedLockingStartupDelay=0)
    private static void biasedLock2() {
        Object o = new Object();

        new Thread(() -> {
            synchronized (o){
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        },"t1").start();
//程序输出：
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           05 b0 4d 03 (00000101 10110000 01001101 00000011) (55422981)
//    4     4        (object header)                           64 02 00 00 (01100100 00000010 00000000 00000000) (612)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }


    // 演示偏向锁：不用关闭延时，让程序运行5秒，超过延时时间以后再去检验偏向锁
    private static void biasedLock3() {
        //先睡眠5秒，保证开启偏向锁
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        Object o = new Object();
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
//程序输出：
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           05 b0 4d 03 (00000101 10110000 01001101 00000011) (55422981)
//    4     4        (object header)                           64 02 00 00 (01100100 00000010 00000000 00000000) (612)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }


    // 偏向锁演示：一个对象不使用synchronized加锁 和 使用synchronized加锁后对象头的差异
    // 结论：锁状态都为101可偏向锁状态。如果对象使用synchronized加锁，对象的对象头还会记录偏向锁对应的线程id
    private static void biasedLock4() {
        //先睡眠5秒，保证开启偏向锁
        try { TimeUnit.SECONDS.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
        Object o = new Object();
        // 可以看到，锁状态为101可偏向锁状态，只是由于o对象未用synchronized加锁，所以线程id是空的。其余数据无锁状态一样
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println("=========================================");
        new Thread(() -> {
            synchronized (o){
                // o对象使用synchronized加锁，偏向锁带线程id情况，第一行中后面不再是0了，有了线程id的值。
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        },"t1").start();
//程序输出：
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
//    4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
//
//    =========================================
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           20 f6 1f d9 (00100000 11110110 00011111 11011001) (-652216800)
//    4     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }


    private static void synchronizedLock() {
        //先睡眠5秒，保证开启偏向锁
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println("=========================================");
        new Thread(() -> {
            synchronized (o){
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        },"t1").start();
    }


    // 重锁演示：
    private static void heavyWeightLock() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                synchronized (objectLock){
                    // o对象使用synchronized加锁，偏向锁带线程id情况，第一行中后面不再是0了，有了线程id的值。
                    System.out.println(ClassLayout.parseInstance(objectLock).toPrintable());
                }
                System.out.println("#################" + finalI + "##############");
            },"thread" + i).start();
        }
//程序输出：
//#################7##############
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           ca b5 75 ad (11001010 10110101 01110101 10101101) (-1384794678)
//    4     4        (object header)                           8a 01 00 00 (10001010 00000001 00000000 00000000) (394)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
//
//#################5##############
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           ca b5 75 ad (11001010 10110101 01110101 10101101) (-1384794678)
//    4     4        (object header)                           8a 01 00 00 (10001010 00000001 00000000 00000000) (394)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }
    private static Object objectLock = new Object();


    private static void lockLashCode01(){
        //先睡眠5秒，保证开启偏向锁
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }

        Object o = new Object();
        System.out.println("本应是偏向锁");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        o.hashCode();//没有重写，一致性哈希，重写后无效,当一个对象已经计算过identity hash code，它就无法进入偏向锁状态；

        synchronized (o){
            System.out.println("本应是偏向锁，但是由于计算过一致性哈希，会直接升级为轻量级锁");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
//程序输出：
//    本应是偏向锁
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
//    4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
//
//    本应是偏向锁，但是由于计算过一致性哈希，会直接升级为轻量级锁
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           38 f1 df 4a (00111000 11110001 11011111 01001010) (1256190264)
//    4     4        (object header)                           2b 00 00 00 (00101011 00000000 00000000 00000000) (43)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }


    private static void lockLashCode02(){
        //先睡眠5秒，保证开启偏向锁
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        Object o = new Object();
        synchronized (o){
            o.hashCode();//没有重写，一致性哈希，重写后无效
            System.out.println("偏向锁过程中遇到一致性哈希计算请求，立马撤销偏向模式，膨胀为重量级锁");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
//程序输出：
//    偏向锁过程中遇到一致性哈希计算请求，立马撤销偏向模式，膨胀为重量级锁
//    java.lang.Object object internals:
//    OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//    0     4        (object header)                           7a cf a4 77 (01111010 11001111 10100100 01110111) (2007289722)
//    4     4        (object header)                           0f 02 00 00 (00001111 00000010 00000000 00000000) (527)
//    8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
//    12     4        (loss due to the next object alignment)
//    Instance size: 16 bytes
//    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    }


}