package com.sgg.juc.base;

public class ThreadBaseDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {

        }, "t1");
        t1.start();
    }
}

// java = C++ ---》  (C++)-- = java