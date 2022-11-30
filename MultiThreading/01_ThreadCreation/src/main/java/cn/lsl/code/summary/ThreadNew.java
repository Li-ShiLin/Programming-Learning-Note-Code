package cn.lsl.code.summary;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

// �ع��ܽ��̵߳Ĵ���
// 1.�̳�Thread
class MyThread extends Thread{
    @Override
    public void run(){
        System.out.println("MyThread");
    }
}

// 2.ʵ��Runnable�ӿ�
class MyThreadRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("MyThreadRunnable");
    }
}
// 3.ʵ��Callable�ӿ�
class MyThreadCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("MyThreadCallable");
        return 100;
    }
}

// �ع��ܽ��̵߳Ĵ���
public class ThreadNew {
    public static void main(String[] args) {

        // 1.�̳�Thread
        new MyThread().start();

        // 2.ʵ��Runnable�ӿ�
        new Thread(new MyThreadRunnable()).start();

        // 3.ʵ��Callable�ӿ�
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThreadCallable());
        new Thread(futureTask).start();

        Integer integer = null;
        try {
            integer = futureTask.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
/*
            MyThread
            MyThreadRunnable
            MyThreadCallable
            100*/
