package com.sgg.juc.atomics;

import java.util.concurrent.atomic.AtomicIntegerArray;
public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
        // 指定容量为5，初始值都是0
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[5]);
        // 指定容量为5
        //AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(5);
        // 设置了初始值
        //AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[]{1,2,3,4,5});

        for (int i = 0; i <atomicIntegerArray.length(); i++) {
            System.out.println(atomicIntegerArray.get(i));
        }
        System.out.println();
        int tmpInt = 0;
        // 获取下标为0的原子类，再将它的值设置为1122
        tmpInt = atomicIntegerArray.getAndSet(0,1122);
        System.out.println(tmpInt+"\t"+atomicIntegerArray.get(0));
        // 获取下标为0的原子类，再让它的值自增
        tmpInt = atomicIntegerArray.getAndIncrement(0);
        System.out.println(tmpInt+"\t"+atomicIntegerArray.get(0));
//程序输出：
//            0
//            0
//            0
//            0
//            0
//
//            0	1122
//            1122	1123
    }
}
