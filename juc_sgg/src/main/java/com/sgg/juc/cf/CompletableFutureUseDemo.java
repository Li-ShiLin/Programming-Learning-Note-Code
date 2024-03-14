package com.sgg.juc.cf;

import java.util.concurrent.*;
public class CompletableFutureUseDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try {
            CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "----come in");
                int result = ThreadLocalRandom.current().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("-----1秒钟后出结果：" + result);
                if (result > 2) {
                    int i = 10 / 0;
                }
                return result;
            }, threadPool).whenComplete((v, e) -> {
                if (e == null) {
                    System.out.println("-----计算完成，更新系统UpdateValue：" + v);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println("异常情况：" + e.getCause() + "\t" + e.getMessage());
                return null;
            });

            System.out.println(Thread.currentThread().getName() + "线程先去忙其它任务");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
/* 执行输出：
            pool-1-thread-1----come in
            main线程先去忙其它任务
            -----1秒钟后出结果：0
            -----计算完成，更新系统UpdateValue：0
 */
}
