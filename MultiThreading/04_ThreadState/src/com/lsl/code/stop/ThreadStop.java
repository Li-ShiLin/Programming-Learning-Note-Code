package com.lsl.code.stop;
//����stop
//1.�����߳�����ֹͣ--->���ô���,��������ѭ��
//2.����ʹ�ñ�־λ--->����һ����־λ
//3.��Ҫʹ��stop����destroy�ȹ�ʱ����JDK������ʹ�õķ���
public class ThreadStop implements Runnable{
    // ����һ����־λ
    private boolean flag = true;
    @Override
    public void run() {
        int i = 0;
        while(flag){
            System.out.println("ThreadStop_" + i++);
        }
    }

    // ����һ�������ķ���ֹͣ�̣߳�ת����־λ
    public void stop(){
        this.flag = false;
    }
    public static void main(String[] args) {
           ThreadStop threadStop = new ThreadStop();
           new Thread(threadStop).start();
           int i = 0;
           for (i = 0; i < 1000; i++) {
            System.out.println("main_" + i);
               if(i==900){
                   // �����Լ�д��stop����,�л���־λ���߳�ֹͣ
                   threadStop.stop();
                   System.out.println("�̸߳�ֹͣ��...");
               }
           }
    }
}
