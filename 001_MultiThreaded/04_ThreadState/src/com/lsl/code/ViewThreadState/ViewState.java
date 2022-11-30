package com.lsl.code.ViewThreadState;

// �۲�����̵߳�״̬
public class ViewState {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(".......");
        });
        Thread.State state= thread.getState();
        System.out.println(state);// NEW

        // �۲�������
        thread.start();//����
        state = thread.getState();
        System.out.println(state);// RUNNABLE
        // ֻҪ�߳�δ��ֹ�������״̬
        while(state!=Thread.State.TERMINATED){
            try {
                Thread.sleep(1000);
                state = thread.getState(); // �����߳�״̬
                System.out.println(state);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
/*
        NEW
        RUNNABLE
        TIMED_WAITING
        TIMED_WAITING
        .......
        TERMINATED*/
