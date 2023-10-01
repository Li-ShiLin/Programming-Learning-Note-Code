package com.atguigu.gulimall.search.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureMultiTaskTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main.....start");
        /**
         * 多任务组合
         */
        CompletableFuture<String> futureImg = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品图片信息");
            return "hello.jpg";
        }, executor);

        CompletableFuture<String> futureAttr = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品属性");
            return "黑色+256G";
        }, executor);

        CompletableFuture<String> futureDesc = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("查询商品的介绍");
            return "华为";
        }, executor);

//        // 1) allOf使用
//        CompletableFuture<Void> allOf = CompletableFuture.allOf(futureImg, futureAttr, futureDesc);
//        allOf.get(); //等待所有结果完成
//        System.out.println(futureImg.get() + "  " + futureAttr.get() + "  " + futureDesc.get());
//                    /*
//                    main.....start
//                    查询商品图片信息
//                    查询商品属性
//                    查询商品的介绍
//                    hello.jpg  黑色+256G  华为
//                    main.....end...
//                     */

        // 2) anyOf
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(futureImg, futureAttr, futureDesc);
        anyOf.get();
        System.out.println(anyOf.get());
                /*
                main.....start
                查询商品图片信息
                查询商品属性
                hello.jpg
                main.....end...
                查询商品的介绍
                 */

        System.out.println("main.....end...");

    }
}

