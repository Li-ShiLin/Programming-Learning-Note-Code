package com.lsl.code;

// �����ػ��߳�
class DaemonThread implements Runnable{
    @Override
    public void run() {
        while(true){
            System.out.println("�ػ��߳��ػ���");
        }
    }
}

class UserThread implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("�û��߳�running-->" + i);
        }
        System.out.println("====goodbye world!====");
    }
}

public class TestDaemon {
    public static void main(String[] args) {
        DaemonThread   daemonThread = new DaemonThread();
        UserThread userThread = new UserThread();

        Thread thread = new Thread(daemonThread);
        thread.setDaemon(true); // Ĭ����false��ʾ���û��߳�,�������̶߳����û��߳�...

        thread.start(); // �����ػ��߳�

        new Thread(userThread).start();
    }
}
/*�û��߳̽������ػ��̻߳������У�
                �ػ��߳��ػ���
                ====goodbye world!====
                �ػ��߳��ػ���
                �ػ��߳��ػ���
                �ػ��߳��ػ���
                �ػ��߳��ػ���
                �ػ��߳��ػ���*/
