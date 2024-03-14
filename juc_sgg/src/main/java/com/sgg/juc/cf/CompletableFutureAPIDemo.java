package com.sgg.juc.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        group1();
    }

    /**
     * 获得结果和触发计算
     */
    private static void group1() throws InterruptedException, ExecutionException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });

        // get() 方法会阻塞程序，直到对应的completableFuture执行结束或者出现异常
        System.out.println(completableFuture.get());

        // get(long timeout, TimeUnit unit)方法 会阻塞程序，直到线程结束 或者 出现异常 或者 超过指定时间
//         System.out.println(completableFuture.get(2L,TimeUnit.SECONDS));

        // join() 方法 和 get() 方法几乎一致，区别在于join()方法不会抛出异常
//        System.out.println(completableFuture.join());

        // 暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //  getNow(T valueIfAbsent)方法：如果CompletableFuture已经完成，它会返回结果值；如果尚未完成，则返回valueIfAbsent的值
//          System.out.println(completableFuture.getNow("xxx"));

//         public boolean complete(T value)  是否打断get方法立即返回括号值
        System.out.println(completableFuture.complete("completeValue") + "\t" + completableFuture.get());
    }
}
