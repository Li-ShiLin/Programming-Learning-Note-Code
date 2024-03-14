package com.sgg.juc.base;

import java.util.concurrent.*;

public class DaemonDemo
{
    public static void main(String[] args)//一切方法运行的入口
    {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 开始运行, "+
                    (Thread.currentThread().isDaemon() ? "守护线程":"用户线程"));
            while(true)
            {

            }
        },"t1");
        t1.setDaemon(true);
        t1.start();

        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println(Thread.currentThread().getName()+"\t ----end 主线程");
    }
}
// 执行结果：
//            t1	 开始运行, 守护线程
//            main	 ----end 主线程