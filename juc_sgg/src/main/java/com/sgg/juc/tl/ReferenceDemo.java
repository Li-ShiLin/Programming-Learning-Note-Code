package com.sgg.juc.tl;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
// 新建一个带finalize()方法的对象MyObject
class MyObject {
    //finalize()方法一般不用复写，此处只是为了教学做案例演示说明
    @Override
    protected void finalize() throws Throwable
    {
        // finalize的通常目的是在对象被不可撤销地丢弃之前执行清理操作。
        System.out.println("-------invoke finalize method~!!!");
    }
}
public class ReferenceDemo {
    public static void main(String[] args) {
        phantomReference();
    }

    // 强引用演示
    private static void strongReference() {
        MyObject myObject = new MyObject();
        System.out.println("gc before: "+myObject);
        myObject = null;
        System.gc();//人工开启GC，一般不用。调用以后由后台线程进行垃圾回收
        //暂停毫秒
        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("gc after: "+myObject);
//程序输出：
//         gc before: com.bilibili.juc.tl.MyObject@16f65612
//         -------invoke finalize method~!!!
//         gc after: null
    }

    // 软引用演示
    private static void softReference() {
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        //System.out.println("-----softReference:"+softReference.get());
        System.gc();
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("-----gc after内存够用: "+softReference.get());
        try
        {
            byte[] bytes = new byte[20 * 1024 * 1024];//20MB对象
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("-----gc after内存不够: "+softReference.get());
        }
//不限制内存时程序输出：
//                -----gc after内存够用: com.bilibili.juc.tl.MyObject@2f410acf
//                -----gc after内存不够: com.bilibili.juc.tl.MyObject@2f410acf
//限制内存时程序输出：
//                -----gc after内存够用:com.bilibili.juc.tl.Myobject@30f39991
//                Exception in thread "main" java.lang.outofMemoryError Create breakpoint : Java heap space
//                at com.bilibili.juc.tl.ReferenceDemo.main(ReferenceDemo.java:35)
//                ----gc after内存不够: null
//                -------invoke finalize method~! ! !
    }


    // 弱引用演示
    private static void weakReference() {
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.out.println("-----gc before 内存够用： "+weakReference.get());
        System.gc();
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("-----gc after 内存够用： "+weakReference.get());
//程序输出：
//                -----gc before 内存够用： com.bilibili.juc.tl.MyObject@16f65612
//                -------invoke finalize method~!!!
//                -----gc after 内存够用： null
    }


    // 虚引用演示
    private static void phantomReference(){
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject,referenceQueue);
        // 虚引用的get方法总是返回null
        System.out.println(phantomReference.get());//null
        List<byte[]> list = new ArrayList<>();
        new Thread(() -> {
            // 不停地添加字节数组,导致OOM,接着会进行垃圾回收
            while (true){
                list.add(new byte[10 * 1024 * 1024]);
                try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(phantomReference.get()+"\t"+"list add ok");
            }
        },"t1").start();

        new Thread(() -> {
            while (true){
                Reference<? extends MyObject> reference = referenceQueue.poll();
                if(reference != null){
                    System.out.println("-----有虚对象回收加入了队列");
                    break;
                }
            }
        },"t2").start();
//程序输出：
//        null
//        null	list add ok
//        null	list add ok
//        null	list add ok
//                -------invoke finalize method~!!!
//        null	list add ok
//        null	list add ok
//                -----有虚对象回收加入了队列
//        null	list add ok
//        null	list add ok
    }
}
