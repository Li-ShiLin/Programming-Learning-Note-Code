package com.sgg.juc.volatiles;

import java.util.concurrent.TimeUnit;
// volatile不具备原子性
// volatile变量的复合操作不具有原子性，比如number++
class MyNumber {
    volatile int number;

    public void addPlusPlus() {
        number++;
    }


    // synchronized同步方法保证原子性，如果使用下面的同步方法，就会正确输出
//    public synchronized void addPlusPlus() {
//        number++;
//    }
}

public class VolatileNoAtomicDemo {
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myNumber.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(myNumber.number);
        // 9883
    }
}
