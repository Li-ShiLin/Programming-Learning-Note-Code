package com.sgg.juc.tl;

import java.util.Random;
class House //资源类
{
    int saleCount = 0;
    /*ThreadLocal<Integer> saleVolume = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue()
        {
            return 0;
        }
    };*/
    ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(() -> 0);
    public void saleVolumeByThreadLocal()
    {
        saleVolume.set(1+saleVolume.get());
    }
}

/**
 * 需求2： 5个销售卖完随机数房子，各自独立销售额度，自己业绩按提成走，分灶吃饭，各个销售自己动手，丰衣足食
 * 希望各自分牡吃饭, 各凭销售本事提成，按照出单数各自统计，比如某房产中介销售都有自己的销售额指标，自己专属自己的，不和别人掺和
 */
public class ThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {
        House house = new House();
        for (int i = 1; i <=5; i++) {
            new Thread(() -> {
                int size = new Random().nextInt(5)+1;
                for (int j = 1; j <=size; j++) {
                    house.saleVolumeByThreadLocal();
                }
                System.out.println(Thread.currentThread().getName()+"\t"+"号销售卖出："+house.saleVolume.get());
            },String.valueOf(i)).start();
        };
    }
//程序输出：
//            5	号销售卖出：1
//            4	号销售卖出：1
//            3	号销售卖出：5
//            2	号销售卖出：3
//            1	号销售卖出：3
}







































