package com.sgg.juc.locks;

import java.util.concurrent.locks.ReentrantLock;
// 公平锁和非公平锁的演示
class Ticket1 //资源类，模拟3个售票员卖完10张票
{
    private int number = 10;
    // 非公平锁
    //    ReentrantLock lock = new ReentrantLock();
    // 公平锁
    ReentrantLock lock = new ReentrantLock(true);
    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第：\t" + (number--) + "\t 还剩下:" + number);
            }
        } finally {
            lock.unlock();
        }
    }
}

public class SaleTicketDemo1 {
    public static void main(String[] args)//一切程序的入口
    {
        Ticket1 ticket = new Ticket1();
        new Thread(() -> {
            for (int i = 0; i < 55; i++) ticket.sale();
        }, "a").start();
        new Thread(() -> {
            for (int i = 0; i < 55; i++) ticket.sale();
        }, "b").start();
        new Thread(() -> {
            for (int i = 0; i < 55; i++) ticket.sale();
        }, "c").start();
    }
}

/*  ReentrantLock lock = new ReentrantLock(); 非公平锁的输出：
a卖出第：	10	 还剩下:9
c卖出第：	9	 还剩下:8
c卖出第：	8	 还剩下:7
c卖出第：	7	 还剩下:6
c卖出第：	6	 还剩下:5
c卖出第：	5	 还剩下:4
c卖出第：	4	 还剩下:3
c卖出第：	3	 还剩下:2
c卖出第：	2	 还剩下:1
c卖出第：	1	 还剩下:0
 */

/* ReentrantLock lock = new ReentrantLock(true); 公平锁输出：
a卖出第：	10	 还剩下:9
a卖出第：	9	 还剩下:8
a卖出第：	8	 还剩下:7
a卖出第：	7	 还剩下:6
b卖出第：	6	 还剩下:5
a卖出第：	5	 还剩下:4
c卖出第：	4	 还剩下:3
b卖出第：	3	 还剩下:2
a卖出第：	2	 还剩下:1
c卖出第：	1	 还剩下:0
 */