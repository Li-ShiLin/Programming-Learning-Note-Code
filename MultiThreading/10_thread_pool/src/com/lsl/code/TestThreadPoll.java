package com.lsl.code;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// �����̳߳�
public class TestThreadPoll {
    public static void main(String[] args) {
        // 1.�������񣬴����̳߳�
        // newFixedThreadPool ����Ϊ���̳߳صĴ�С
        ExecutorService service = Executors.newFixedThreadPool(10);

        // ִ��
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());

        // 2.�ر�����
        service.shutdown();
    }
}
class MyThread implements Runnable{
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName()+ "--->" + i);
        }
    }
}