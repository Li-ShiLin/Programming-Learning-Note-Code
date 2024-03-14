package com.sgg.juc.atomics;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
class ClickNumber //资源类
{
    int number = 0;

    // 方案一：使用synchronized实现累加
    public synchronized void clickBySynchronized()
    {
        number++;
    }

    // 方案二：使用AtomicLong实现累加
    AtomicLong atomicLong = new AtomicLong(0);
    public void clickByAtomicLong()
    {
        atomicLong.getAndIncrement();
    }

    // 方案三：使用LongAdder实现累加
    LongAdder longAdder = new LongAdder();
    public void clickByLongAdder()
    {
        longAdder.increment();
    }
    // 方案四：使用LongAccumulator实现累加
    LongAccumulator longAccumulator = new LongAccumulator((x,y) -> x + y,0);
    public void clickByLongAccumulator()
    {
        longAccumulator.accumulate(1);
    }
}

/**
 * 需求： 50个线程，每个线程100W次，统计总点赞数
 */
public class AccumulatorCompareDemo
{
    public static final int _1W = 10000;
    public static final int threadNumber = 50;

    public static void main(String[] args) throws InterruptedException
    {
        ClickNumber clickNumber = new ClickNumber();
        long startTime;
        long endTime;

        CountDownLatch countDownLatch1 = new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch2 = new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch3 = new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch4 = new CountDownLatch(threadNumber);

        startTime = System.currentTimeMillis();
        for (int i = 1; i <=threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <=100 * _1W; j++) {
                        clickNumber.clickBySynchronized();
                    }
                } finally {
                    countDownLatch1.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch1.await();
        endTime = System.currentTimeMillis();
        System.out.println("----costTime: "+(endTime - startTime) +" 毫秒"+"\t clickBySynchronized: "+clickNumber.number);

        startTime = System.currentTimeMillis();
        for (int i = 1; i <=threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <=100 * _1W; j++) {
                        clickNumber.clickByAtomicLong();
                    }
                } finally {
                    countDownLatch2.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch2.await();
        endTime = System.currentTimeMillis();
        System.out.println("----costTime: "+(endTime - startTime) +" 毫秒"+"\t clickByAtomicLong: "+clickNumber.atomicLong.get());


        startTime = System.currentTimeMillis();
        for (int i = 1; i <=threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <=100 * _1W; j++) {
                        clickNumber.clickByLongAdder();
                    }
                } finally {
                    countDownLatch3.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch3.await();
        endTime = System.currentTimeMillis();
        System.out.println("----costTime: "+(endTime - startTime) +" 毫秒"+"\t clickByLongAdder: "+clickNumber.longAdder.sum());

        startTime = System.currentTimeMillis();
        for (int i = 1; i <=threadNumber; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <=100 * _1W; j++) {
                        clickNumber.clickByLongAccumulator();
                    }
                } finally {
                    countDownLatch4.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch4.await();
        endTime = System.currentTimeMillis();
        System.out.println("----costTime: "+(endTime - startTime) +" 毫秒"+"\t clickByLongAccumulator: "+clickNumber.longAccumulator.get());
//程序输出：
//            ----costTime: 4171 毫秒	 clickBySynchronized: 50000000
//            ----costTime: 711 毫秒	 clickByAtomicLong: 50000000
//            ----costTime: 221 毫秒	 clickByLongAdder: 50000000
//            ----costTime: 79 毫秒	 clickByLongAccumulator: 50000000
    }
}



