package com.sgg.juc.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * 使用:作为一个布尔状态标志，用于指示发生了一个重要的一次性事件，例如完成初始化或任务结束
 * 理由:状态标志并不依赖于程序内任何其他状态，且通常只有一种状态转换
 * 例子:判断业务是否结束
 */
public class UseVolatileDemo {
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
