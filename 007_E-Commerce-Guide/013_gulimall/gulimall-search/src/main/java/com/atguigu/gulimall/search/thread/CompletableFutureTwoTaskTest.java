package com.atguigu.gulimall.search.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureTwoTaskTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 两个都完成
         */
        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程...启动了" + Thread.currentThread().getId());
            int i = 10 / 5;
            System.out.println("任务1线程...结束");
            return i;
        }, executor);

        CompletableFuture<String> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程...启动了" + Thread.currentThread().getId());
            System.out.println("任务2线程...结束");
            return "hello";
        }, executor);

//        // 1） runAfterBothAsync： 不能感知前两个的运行结果
//        future01.runAfterBothAsync(future02, () -> {
//            System.out.println("任务3开始");
//        }, executor);
//                    /*
//                    任务1线程...启动了22
//                    任务1线程...结束
//                    任务2线程...启动了23
//                    任务2线程...结束
//                    main.....end...
//                    任务3开始
//                     */

//        // 2) thenAcceptBothAsync: 可以感知到前两个任务的结果
//        future01.thenAcceptBothAsync(future02, (f1, f2) -> {
//            System.out.println("任务3开始");
//            System.out.println("得到之前的结果：future01的结果： " + f1 + "  future02的结果:" + f2);
//        }, executor);
//                /*
//                任务1线程...启动了20
//                任务1线程...结束
//                任务2线程...启动了21
//                任务2线程...结束
//                main.....end...
//                任务3开始
//                得到之前的结果：future01的结果： 2  future02的结果:hello
//                 */


        // 2) thenAcceptBothAsync: 可以感知到前两个任务的结果
        // R apply(T t, U u);
        CompletableFuture<String> future = future01.thenCombineAsync(future02, (f1, f2) -> {
            return "future01:" + f1 + " future02:" + f2;
        }, executor);
        System.out.println("thenAcceptBothAsync示例代码返回值：" + future.get());
                /*
                任务1线程...启动了22
                任务1线程...结束
                任务2线程...启动了23
                任务2线程...结束
                thenAcceptBothAsync示例代码返回值：future01:2 future02:hello
                main.....end...
                 */
        System.out.println("main.....end...");
    }
}

