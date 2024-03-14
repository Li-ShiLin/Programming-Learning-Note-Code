package com.sgg.juc.cf;

import java.util.concurrent.*;


public class CompletableFutureBuildDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello supplyAsync";
        }, threadPool);
        System.out.println(completableFuture.get());

        threadPool.shutdown();
    }
        /* 程序输出：
                pool-1-thread-1
                hello supplyAsync
         */
}
