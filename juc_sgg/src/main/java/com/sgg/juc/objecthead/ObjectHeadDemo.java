package com.sgg.juc.objecthead;


public class ObjectHeadDemo
{
    public static void main(String[] args)
    {
        Object o = new Object();//? new一个对象，占内存多少？

        System.out.println(o.hashCode());//这个hashcode记录在对象的什么地方？

        synchronized (o){ // 为什么任意对象都可以加锁

        }
        // 手动收集垃圾。。。。。,15次可以从新生代---养老区
        // 怎么记录对象躲过多少次垃圾回收
        System.gc();

        Customer c1 = new Customer();
        Customer c2 = new Customer();
        Customer c3 = new Customer();
    }
}
