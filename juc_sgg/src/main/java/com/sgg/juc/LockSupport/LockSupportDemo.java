package com.sgg.juc.LockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class LockSupportDemo {
    static int x = 0;
    static int y = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t ----come in" + System.currentTimeMillis());
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒" + System.currentTimeMillis());
        }, "t1");
        t1.start();

        //暂停几秒钟线程
        //try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
        }, "t2").start();

    }

    private static void lockAwaitSignal() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        //暂停几秒钟线程
        //try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }

    private static void syncWaitNotify() {
        Object objectLock = new Object();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
            }
        }, "t1").start();
        //暂停几秒钟线程
        //try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
            }
        }, "t2").start();
    }
}




/*
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
public class LockSupportDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            //暂停几秒钟线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t ----come in" + System.currentTimeMillis());
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒" + System.currentTimeMillis());
        }, "t1");
        t1.start();
        new Thread(() -> {
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
        }, "t2").start();

    }
//程序执行输出：
//            t2	 ----发出通知
//            t1	 ----come in1706347362930
//            t1	 ----被唤醒1706347362930
}
*/



/*
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
public class LockSupportDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            //暂停几秒钟线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t ----come in" + System.currentTimeMillis());
            LockSupport.park();
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒" + System.currentTimeMillis());
        }, "t1");
        t1.start();
        new Thread(() -> {
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
        }, "t2").start();

    }
//程序执行输出（程序出现阻塞）：
//                            t2	 ----发出通知
//                            t1	 ----come in1706347007391
}
*/


// LockSupport照样支持先唤醒后等待
/*public class LockSupportDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            //暂停几秒钟线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t ----come in" + System.currentTimeMillis());
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒" + System.currentTimeMillis());
        }, "t1");
        t1.start();
        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
        }, "t2").start();

    }
//程序执行输出：
//                t2	 ----发出通知
//                t1	 ----come in1706344340866
//                t1	 ----被唤醒1706344340866
}*/


/*
// 正常+无锁块要求
public class LockSupportDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ----come in" + System.currentTimeMillis());
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ----被唤醒" + System.currentTimeMillis());
        }, "t1");
        t1.start();

        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
        }, "t2").start();

    }
//程序执行输出：
//        t1	 ----come in1706344026613
//        t2	 ----发出通知
//        t1	 ----被唤醒1706344027620
}
*/


/*
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockSupportDemo {
    public static void main(String[] args) {
        lockAwaitSignal();
    }

    private static void lockAwaitSignal() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            try {
                // 阻塞当前线程,保证signal在await之前执行
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();
        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
// 程序输出(程序阻塞):
//        t2	 ----发出通知
//        t1	 ----come in
}
*/



/*
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class LockSupportDemo {
    public static void main(String[] args) {
        lockAwaitSignal();
    }
    private static void lockAwaitSignal() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
//            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
//                lock.unlock();
            }
        }, "t1").start();
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
//            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
            } finally {
//                lock.unlock();
            }
        }, "t2").start();
    }
// 程序执行报错:
//    t1	 ----come in
//    Exception in thread "t1" java.lang.IllegalMonitorStateException
//    at java.util.concurrent.locks.ReentrantLock$Sync.tryRelease(ReentrantLock.java:151)
//    at java.util.concurrent.locks.AbstractQueuedSynchronizer.release(AbstractQueuedSynchronizer.java:1261)
//    at java.util.concurrent.locks.AbstractQueuedSynchronizer.fullyRelease(AbstractQueuedSynchronizer.java:1723)
//    at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2036)
//    at com.bilibili.juc.LockSupport.LockSupportDemo.lambda$lockAwaitSignal$0(LockSupportDemo.java:115)
//    at java.lang.Thread.run(Thread.java:748)
//    Exception in thread "t2" java.lang.IllegalMonitorStateException
//    at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.signal(AbstractQueuedSynchronizer.java:1939)
//    at com.bilibili.juc.LockSupport.LockSupportDemo.lambda$lockAwaitSignal$1(LockSupportDemo.java:132)
}
*/



/*
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
// 正常情况演示：
public class LockSupportDemo {
    public static void main(String[] args) {
        lockAwaitSignal();
    }
    private static void lockAwaitSignal() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
//程序输出：
//            t1	 ----come in
//            t2	 ----发出通知
//            t1	 ----被唤醒
}
*/


// 先notify后wait的情况会出现异常
/*public class LockSupportDemo {
    public static void main(String[] args) {
        syncWaitNotify();
    }
    private static void syncWaitNotify() {
        Object objectLock = new Object();
        new Thread(() -> {
            // 让线程1阻塞1秒钟，保证出现先notify后wait的情况
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
            }
        }, "t1").start();
        //暂停几秒钟线程
        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
            }
        }, "t2").start();
// 程序输出（程序一直卡死，无法退出）：
//            t2	 ----发出通知
//            t1	 ----come in
    }
}*/


// wait和notify正确使用示例
/*
public class LockSupportDemo {
    public static void main(String[] args) {
        syncWaitNotify();
    }
    private static void syncWaitNotify() {
        Object objectLock = new Object();
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
            }
        }, "t1").start();
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
            }
        }, "t2").start();
// 程序输出：
//            t1	 ----come in
//            t2	 ----发出通知
//            t1	 ----被唤醒
    }
}
*/

