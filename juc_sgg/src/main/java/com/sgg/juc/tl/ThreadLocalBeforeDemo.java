package com.sgg.juc.tl;

import java.util.Random;
import java.util.concurrent.TimeUnit;
class House1 //资源类
{
    int saleCount = 0;

    // 为了数据安全只能加锁
    public synchronized void saleHouse()
    {
        ++saleCount;
    }
}
/**
 * 需求1： 5个销售卖房子，集团高层只关心销售总量的准确统计数
 */
public class ThreadLocalBeforeDemo {
    public static void main(String[] args) throws InterruptedException {
        House1 house = new House1();

        for (int i = 1; i <=5; i++) {
            new Thread(() -> {
                // size：模拟当前房产销售的销售额
                int size = new Random().nextInt(5)+1;
                System.out.println(size);
                for (int j = 1; j <=size; j++) {
                    house.saleHouse();
                }
            },String.valueOf(i)).start();
        };
        //暂停毫秒
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println(Thread.currentThread().getName()+"\t"+"共计卖出多少套： "+house.saleCount);
//程序输出：
//            1
//            4
//            1
//            5
//            3
//            main	共计卖出多少套： 14
    }
}
