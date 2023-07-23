package com.atguigu.gulimall.search.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 线程串行化
 * 1）、thenRun：不能获取到上一步的执行结果，无返回值
 *  .thenRunAsync(() -> {
 *             System.out.println("任务2启动了...");
 *         }, executor);
 * 2）、thenAcceptAsync;能接受上一步结果，但是无返回值
 * 3）、thenApplyAsync：;能接受上一步结果，有返回值
 */
public class CompletableFutureSeriesTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main.....start...");

//        // thenRunAsync方法的使用：
//        // thenRunAsync没有返回值且相对于前一个线程是异步执行的
//        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程：" + Thread.currentThread().getId());
//            int i = 10 / 10;
//            System.out.println("运行结果：" + i);
//            return i;
//        }, executor).thenRunAsync(() -> {
//            System.out.println("任务2启动了");
//        }, executor);
//
//                /*
//                main.....start...
//                当前线程：22
//                运行结果：1
//                main.....end...
//                任务2启动了
//                 */


//        // thenAcceptAsync方法的使用：
//        // thenAcceptAsync方法: 能接收上一步结果，但是无返回值
//        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程：" + Thread.currentThread().getId());
//            int i = 10 / 5;
//            System.out.println("运行结果：" + i);
//            return i;
//        }, executor).thenAcceptAsync(result->{
//            System.out.println("任务2启动了");
//            System.out.println("上一步的运行结果是：" + result);
//        },executor);
//
//        // void accept(T t);
//        System.out.println("main.....end...");
//                /*
//                main.....start...
//                当前线程：22
//                运行结果：2
//                main.....end...
//                任务2启动了
//                上一步的运行结果是：2
//                 */


        // thenApplyAsync方法的使用：
        // thenApplyAsync方法: 既可以能接收上一步的运行结果，也有有自己的返回值
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 5;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).thenApplyAsync(res -> {
            System.out.println("任务2启动了");
            System.out.println("上一步的运行结果是：" + res);
            return "返回结果为" + res;
        }, executor);
        //  R apply(T t);
        // 打印返回值：
        System.out.println(future.get());  // future.get()是一个阻塞方法
        System.out.println("main.....end...");
                /*
                main.....start...
                当前线程：22
                运行结果：2
                任务2启动了
                上一步的运行结果是：2
                返回结果为2
                main.....end...
                 */
    }
}
