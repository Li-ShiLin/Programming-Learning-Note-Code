package com.lsl.code.Join;

// ����join���� ->����Ϊ���
public class ThreadJoin implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("�߳�vip����" + i);
        }
    }

    public static void main(String[] args) {
        // �������ǵ��߳�
        ThreadJoin threadJoin = new ThreadJoin();
        Thread thread = new Thread(threadJoin);
        thread.start();
        
        // ���߳�
        for (int i = 0; i < 1000; i++) {
            if(i==200){
                try {
                    thread.join();//���
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("main�߳�" + i);
        }

    }
}
/*�������:
        main�߳�196
        main�߳�197
        main�߳�198
        main�߳�199
        �߳�vip����0
        �߳�vip����1
        �߳�vip����2*/



