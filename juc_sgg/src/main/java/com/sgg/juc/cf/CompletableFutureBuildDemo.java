package com.sgg.juc.cf;

import java.util.concurrent.*;


public class CompletableFutureBuildDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            //暂停几秒钟线程
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        });
        System.out.println(completableFuture.get());
    }
}
