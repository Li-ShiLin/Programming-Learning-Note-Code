package com.lsl.code.sleep;

// ģ��������ʱ->�Ŵ�����ķ�����
public class ticketRunnableThread implements Runnable{
        // Ʊ��
        private int ticketNums = 10;

        @Override
        public void run() {
            while(true){
                if(ticketNums<=0){
                    break;
                }
                // ģ����ʱ
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "�õ��˵�"+ ticketNums-- +"��Ʊ");
            }
        }
        public static void main(String[] args) {
            ticketRunnableThread ticket = new ticketRunnableThread();
            new Thread(ticket,"С��").start();
            new Thread(ticket,"��ʦ").start();
            new Thread(ticket,"��ţ��").start();
        }
    }
// �˳�����ڵ�����:����̲߳���ͬһ����Դ�������,�̲߳���ȫ�����ݿ�������(�������߳�ͬ�����Ż�)

