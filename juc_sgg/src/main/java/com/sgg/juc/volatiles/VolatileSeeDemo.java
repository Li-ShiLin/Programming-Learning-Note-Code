package com.sgg.juc.volatiles;

import java.util.concurrent.TimeUnit;

// 不加volatile，没有可见性，程序无法停止
// 加了volatile，保证可见性，程序可以停止
public class VolatileSeeDemo {
    //static boolean flag = true;
    static volatile boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t -----come in");
            while (flag) {
                System.out.println("flag还未修改,flag:" + flag);
            }
            System.out.println(Thread.currentThread().getName() + "\t -----flag被设置为false，程序停止");
        }, "t1").start();

        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = false;
        System.out.println(Thread.currentThread().getName() + "\t 修改完成flag: " + flag);
    }
// 程序输出：
//                flag还未修改,flag:true
//                flag还未修改,flag:true
//                flag还未修改,flag:true
//                flag还未修改,flag:true
//                flag还未修改,flag:true
//                t1	 -----flag被设置为false，程序停止
//                main	 修改完成flag: false
}
