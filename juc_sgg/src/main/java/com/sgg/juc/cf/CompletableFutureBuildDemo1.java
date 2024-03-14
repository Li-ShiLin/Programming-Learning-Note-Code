package com.sgg.juc.cf;

import java.util.concurrent.*;

public class CompletableFutureBuildDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, threadPool);
        System.out.println(completableFuture.get());
        threadPool.shutdown();
    }
/* 执行结果：
            pool-1-thread-1
            null
 */
}
