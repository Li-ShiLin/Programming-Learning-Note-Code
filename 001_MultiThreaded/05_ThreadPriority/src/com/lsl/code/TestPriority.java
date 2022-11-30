package com.lsl.code;
// �����̵߳����ȼ�
class MyPriority implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-->" + Thread.currentThread().getPriority());
    }
}

public class TestPriority {
    // ���߳�Ĭ�����ȼ�
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "-->" + Thread.currentThread().getPriority());
        MyPriority myPriority = new MyPriority();
        Thread t1 =new Thread(myPriority,"t1");
        Thread t2 =new Thread(myPriority,"t2");
        Thread t3 =new Thread(myPriority,"t3");
        Thread t4 =new Thread(myPriority,"t4");
        Thread t5 =new Thread(myPriority,"t5");
        Thread t6 =new Thread(myPriority,"t6");
        // �м� -> ���������ȼ���������
        t1.setPriority(Thread.MIN_PRIORITY);
        t1.start();

        t2.setPriority(4);
        t2.start();

        t3.setPriority(Thread.NORM_PRIORITY);
        t3.start();

        t4.setPriority(Thread.MAX_PRIORITY);
        t4.start();

        // ���ڷ�Χ -> ����
        t5.setPriority(-1);
        t5.start();

        // ���ڷ�Χ -> ����
        t6.setPriority(11);
        t6.start();
    }
}

/* �����
        main-->5
        t3-->5
        t4-->10
        t2-->4
        t1-->1*/
