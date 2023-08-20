package com.atguigu.gulimall.search.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureTest {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main.....start...");

//        // runAsync方法示例：
//        CompletableFuture.runAsync(() -> {
//            System.out.println("当前线程：" + Thread.currentThread().getId());
//            int i = 10 / 2;
//            System.out.println("运行结果：" + i);
//        }, executor);
            /*
            main.....start...
            main.....end...
            当前线程：22
            运行结果：5
             */


//        // supplyAsync方法使用：
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程：" + Thread.currentThread().getId());
//            int i = 10 / 2;
//            System.out.println("运行结果：" + i);
//            return i;
//        }, executor);
//        System.out.println("supplyAsync返回结果：" + future.get());
//        System.out.println("main.....end...");
//                /*
//                main.....start...
//                当前线程：22
//                运行结果：5
//                supplyAsync返回结果：5
//                main.....end...
//                 */


//        // whenComplete使用：
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程：" + Thread.currentThread().getId());
//            int i = 10 / 0;
//            System.out.println("运行结果：" + i);
//            return i;
//        }, executor).whenComplete((result, exception) -> {
//            System.out.println("异步任务成功完成了...结果是:" + result + "  异常是:" + exception);
//        });
//        System.out.println("main.....end...");
//            /*
//            main.....start...
//            当前线程：22
//            异步任务成功完成了...结果是:null  异常是:java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
//            main.....end...
//             */


//        // exceptionally使用示例：
//        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//            System.out.println("当前线程：" + Thread.currentThread().getId());
//            int i = 10 / 0;
//            System.out.println("运行结果：" + i);
//            return i;
//        }, executor).whenComplete((result, exception) -> {
//            // whenComplete虽然能够得到异常信息，但是无法修改返回数据
//            System.out.println("异步任务成功完成了...结果是:" + result + "  异常是:" + exception);
//        }).exceptionally(throwable -> {
//            // exceptionally可以感知异常并且返回默认值
//            return 10;
//        });
//        System.out.println("结果是：" + future.get());
//        System.out.println("main.....end...");
//            /*
//            main.....start...
//            当前线程：22
//            异步任务成功完成了...结果是:null  异常是:java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
//            结果是：10
//            main.....end...
//             */


        // handle方法示例代码：
        // handle: 方法执行完以后的处理
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 10;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).handle((result, exception) -> {
            if (result != null) {
                return result * 2;
            }
            if (exception != null) {
                return 0;
            }
            return 0;
        });
        //     R apply(T t, U u);
        System.out.println("结果是：" + future.get());
        System.out.println("main.....end...");
                /*
                main.....start...
                当前线程：22
                运行结果：1
                结果是：2
                main.....end...
                 */
    }
}
