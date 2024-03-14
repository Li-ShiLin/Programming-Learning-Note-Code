package com.sgg.juc.atomics;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;
/**
 * 原子更新带有标记位的引用类型对象
 * 解决是否修改过：它的定义就是将状态戳简化true\false，类似一次性筷子
 * 状态戳(true/false)原子引用
 */
public class AtomicMarkableReferenceDemo {
    static AtomicMarkableReference markableReference = new AtomicMarkableReference(100,false);

    public static void main(String[] args) {
        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName()+"\t"+"默认标识："+marked);
            //暂停1秒钟线程,等待后面的T2线程和我拿到一样的模式flag标识，都是false
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            markableReference.compareAndSet(100,1000,marked,!marked);
        },"t1").start();

        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName()+"\t"+"默认标识："+marked);

            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            boolean b = markableReference.compareAndSet(100, 2000, marked, !marked);
            System.out.println(Thread.currentThread().getName()+"\t"+"t2线程CASresult： "+b);
            System.out.println(Thread.currentThread().getName()+"\t"+markableReference.isMarked());
            System.out.println(Thread.currentThread().getName()+"\t"+markableReference.getReference());
        },"t2").start();
    }
//程序输出：
//                t1	默认标识：false
//                t2	默认标识：false
//                t2	t2线程CASresult： false
//                t2	true
//                t2	1000
}

/**
 *  CAS----Unsafe----do while+ABA---AtomicStampedReference,AtomicMarkableReference
 *
 *  AtomicStampedReference,version号，+1；
 *
 *  AtomicMarkableReference，一次，false，true
 *
 */