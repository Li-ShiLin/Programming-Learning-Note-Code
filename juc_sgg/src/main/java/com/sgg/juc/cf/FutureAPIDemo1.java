package com.sgg.juc.cf;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 轮询的方式会耗费无谓的CPu资源，而且也不见得能及时地得到计算结果.如果想要异步获取结果,
 * 通常都会以轮询的方式去获取结果
 * 尽量不要阻塞
 */
public class FutureAPIDemo1 {
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
        // 不断的轮询,使用isDone方法来判断任务是否执行完成.缺点:不断地轮询会导致CPU空转
        while (true) {
            if (futureTask.isDone()) {
                System.out.println(futureTask.get());
                break;
            } else {
                //暂停毫秒
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("正在处理中，不要再催了，越催越慢 ，再催熄火");
            }
        }
    }
/*程序执行输出：
            main	 ----忙其它任务了
            t1	 -----come in
            正在处理中，不要再催了，越催越慢 ，再催熄火
            正在处理中，不要再催了，越催越慢 ，再催熄火
            正在处理中，不要再催了，越催越慢 ，再催熄火
            正在处理中，不要再催了，越催越慢 ，再催熄火
            正在处理中，不要再催了，越催越慢 ，再催熄火
            正在处理中，不要再催了，越催越慢 ，再催熄火
            正在处理中，不要再催了，越催越慢 ，再催熄火
            正在处理中，不要再催了，越催越慢 ，再催熄火
            正在处理中，不要再催了，越催越慢 ，再催熄火
            正在处理中，不要再催了，越催越慢 ，再催熄火
            task over
 */
}
