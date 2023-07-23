package com.atguigu.gulimall.search.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureOneOfTaskTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 两个都完成
         */
        CompletableFuture<Object> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程...启动了" + Thread.currentThread().getId());
            int i = 10 / 5;
            System.out.println("任务1线程...结束");
            return i;
        }, executor);

        CompletableFuture<Object> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程...启动了" + Thread.currentThread().getId());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务2线程...结束");
            return "hello";
        }, executor);

        /**
         * 两个任务只要有一个完成，就可以执行任务三
         */
//        // 1) runAfterEitherAsync: 不感知前面两个任务的结果，自己也没有返回值
//        CompletableFuture<Void> future = future01.runAfterEitherAsync(future02, () -> {
//            System.out.println("任务3开始");
//        }, executor);
//                    /*
//                    任务1线程...启动了22
//                    任务1线程...结束
//                    任务2线程...启动了23
//                    main.....end...
//                    任务3开始
//                    任务2线程...结束
//                     */


//        // 2) acceptEitherAsync: 感知前面两个任务的结果，自己有返回值
//        CompletableFuture<Void> future = future01.acceptEitherAsync(future02, (res) -> {
//            System.out.println("任务3开始,之前的结果是：" + res);
//        }, executor);
//        //     void accept(T t);
//        System.out.println("main.....end...");
//                    /*
//                    任务1线程...启动了22
//                    任务1线程...结束
//                    任务2线程...启动了23
//                    main.....end...
//                    任务3开始,之前的结果是：2
//                    任务2线程...结束
//                     */

        // 3) applyToEitherAsync: 能感知前面两个任务的结果且自己有返回值
        CompletableFuture<String> future = future01.applyToEitherAsync(future02, (res) -> {
            System.out.println("任务3开始...之前的结果：" + res);
            return res.toString() + "-> 哈哈";
        }, executor);
        System.out.println("任务三的返回结果：" + future.get());
        System.out.println("main.....end...");
                    /*
                    任务1线程...启动了22
                    任务2线程...启动了23
                    任务1线程...结束
                    任务3开始...之前的结果：2
                    任务三的返回结果：2-> 哈哈
                    main.....end...
                     */
    }
}

