package com.sgg.juc.cf;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/**
 * 1 get容易导致阻塞，一般建议放在程序后面，一旦调用get方法，非要等到结果才会离开，不管是否计算完成，容易程序堵塞
 * 2 使用带有超时参数的get方法来避免长时间的阻塞：futureTask.get(3,TimeUnit.SECONDS)
 */
public class FutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName() + "\t -----come in");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task over";
        });
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();
        System.out.println(Thread.currentThread().getName() + "\t ----忙其它任务了");
//        System.out.println(futureTask.get());
        System.out.println(futureTask.get(3, TimeUnit.SECONDS));
    }
/*程序执行输出：
            main	 ----忙其它任务了
            t1	 -----come in
            Exception in thread "main" java.util.concurrent.TimeoutException
                at java.util.concurrent.FutureTask.get(FutureTask.java:205)
                at com.bilibili.juc.cf.FutureAPIDemo.main(FutureAPIDemo.java:27)
 */
}
