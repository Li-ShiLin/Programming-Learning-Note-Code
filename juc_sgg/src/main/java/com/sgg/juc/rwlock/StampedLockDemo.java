package com.sgg.juc.rwlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock = ReentrantReadWriteLock + 读的过程中也允许获取写锁介入
 */
public class StampedLockDemo {
    static int number = 37;
    static StampedLock stampedLock = new StampedLock();

    public void write()
    {
        long stamp = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName()+"\t"+"写线程准备修改");
        try
        {
            number = number + 13;
        }finally {
            stampedLock.unlockWrite(stamp);
        }
        System.out.println(Thread.currentThread().getName()+"\t"+"写线程结束修改");
    }

    //悲观读，读没有完成时候写锁无法获得锁
    public void read()
    {
        long stamp = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName()+"\t"+" come in readlock code block，4 seconds continue...");
        for (int i = 0; i < 4; i++) {
            //暂停几秒钟线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t"+" 正在读取中......");
        }

        try
        {
            int result = number;
            System.out.println(Thread.currentThread().getName()+"\t"+" 获得成员变量值result："+result);
            System.out.println("写线程没有修改成功，读锁时候写锁无法介入，传统的读写互斥");
        }finally {
            stampedLock.unlockRead(stamp);
        }
    }

    //乐观读，读的过程中也允许获取写锁介入
    public void tryOptimisticRead() {
        long stamp = stampedLock.tryOptimisticRead();
        int result = number;
        //故意间隔4秒钟，很乐观认为读取中没有其它线程修改过number值，具体靠判断
        System.out.println("4秒前stampedLock.validate方法值(true无修改，false有修改)"+"\t"+stampedLock.validate(stamp));
        for (int i = 0; i < 4; i++) {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t"+"正在读取... "+i+" 秒" +
                    "后stampedLock.validate方法值(true无修改，false有修改)"+"\t"+stampedLock.validate(stamp));
        }
        if(!stampedLock.validate(stamp))
        {
            System.out.println("有人修改过------有写操作");
            stamp = stampedLock.readLock();
            try
            {
                System.out.println("从乐观读 升级为 悲观读");
                result = number;
                System.out.println("重新悲观读后result："+result);
            }finally {
                stampedLock.unlockRead(stamp);
            }
        }
        System.out.println(Thread.currentThread().getName()+"\t"+" finally value: "+result);
    }


    public static void main(String[] args) {
        testOptimisticRead();
    }



    // 1.Reading(读模式悲观）:功能和ReentrantReadWriteLock的读锁类似
    // 2.Writing(写模式）:功能和ReentrantRedWriteLock的写锁类似
    private static void testReadingWritingMode(){
        StampedLockDemo resource = new StampedLockDemo();
        // 传统版
        new Thread(() -> {
            resource.read();
        },"readThread").start();
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t"+"----come in");
            resource.write();
        },"writeThread").start();
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println(Thread.currentThread().getName()+"\t"+"number:" +number);
//程序输出：
//        readThread	 come in readlock code block，4 seconds continue...
//        readThread	 正在读取中......
//        writeThread	----come in
//        readThread	 正在读取中......
//        readThread	 正在读取中......
//        readThread	 正在读取中......
//        readThread	 获得成员变量值result：37
//        写线程没有修改成功，读锁时候写锁无法介入，传统的读写互斥
//        writeThread	写线程准备修改
//        writeThread	写线程结束修改
//        main	number:50
    }


    // 3.Optimistic readina(乐观读模式):无锁机制，类似于数据库中的乐观锁,
    // 支持读写并发，很乐观认为读取时没人修改，假如被修改再实现升级为悲观读模式
    private static void testOptimisticRead(){
        StampedLockDemo resource = new StampedLockDemo();
        new Thread(() -> {
            resource.tryOptimisticRead();
        },"readThread").start();

        // 暂停2秒钟线程,读过程可以写介入，演示
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

//        //暂停6秒钟线程，读过程不会介入，演示
//        try { TimeUnit.SECONDS.sleep(6); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t"+"----come in");
            resource.write();
        },"writeThread").start();
        System.out.println(Thread.currentThread().getName()+"\t"+"number:" +number);
//程序输出：
//        4秒前stampedLock.validate方法值(true无修改，false有修改)	true
//        readThread	正在读取... 0 秒后stampedLock.validate方法值(true无修改，false有修改)	true
//        main	number:37
//        writeThread	----come in
//        writeThread	写线程准备修改
//        writeThread	写线程结束修改
//        readThread	正在读取... 1 秒后stampedLock.validate方法值(true无修改，false有修改)	false
//        readThread	正在读取... 2 秒后stampedLock.validate方法值(true无修改，false有修改)	false
//        readThread	正在读取... 3 秒后stampedLock.validate方法值(true无修改，false有修改)	false
//        有人修改过------有写操作
//        从乐观读 升级为 悲观读
//        重新悲观读后result：50
//        readThread	 finally value: 50
    }

}
