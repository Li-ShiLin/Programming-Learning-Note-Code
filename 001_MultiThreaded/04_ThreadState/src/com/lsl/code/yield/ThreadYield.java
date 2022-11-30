package com.lsl.code.yield;

//���������߳�
//���ò�һ���ɹ�,��cPU����
public class ThreadYield {
}

class MyYield implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "�߳̿�ʼִ��");
        Thread.yield();// ����
        System.out.println(Thread.currentThread().getName() + "�߳�ִ�н���");
    }

    public static void main(String[] args) {
        MyYield myYield = new MyYield();
        new Thread(myYield,"a").start();
        new Thread(myYield,"b").start();
    }
}
/*����ʧ�������
        a�߳̿�ʼִ��
        a�߳�ִ�н���
        b�߳̿�ʼִ��
        b�߳�ִ�н���*/

/*���óɹ������
        b�߳̿�ʼִ��
        a�߳̿�ʼִ��
        b�߳�ִ�н���
        a�߳�ִ�н���
*/
