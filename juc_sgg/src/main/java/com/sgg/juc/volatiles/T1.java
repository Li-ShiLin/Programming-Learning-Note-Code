package com.sgg.juc.volatiles;


// line1新建了一个ThreadLocal对象，t1是强引用指向这个对象
// line2调用set()方法后新建一个Entry，通过源码可知Entry对象里的k是弱引用指向这个对象
public class T1 {
    public static void main(String[] args) {
        //新建了一个ThreadLocal对象，t1是强引用指向这个对象
        ThreadLocal<String> tl = new ThreadLocal<>();    //line1
        // 调用set()方法后新建一个Entry，通过源码可知Entry对象里的k是弱引用指向这个对象
        tl.set("zzyybs@126.com");                        //line2
        tl.get();                                        //line3
    }
}
