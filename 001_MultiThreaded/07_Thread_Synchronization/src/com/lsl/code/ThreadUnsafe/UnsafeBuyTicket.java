package com.lsl.code.ThreadUnsafe;

class BuyTicket implements Runnable{
    // Ʊ
    private int ticketNums = 10;
    boolean flag = true; // �ⲿֹͣ��ʽ
    @Override
    public void run() {
        // ��Ʊ
      while(flag){
          buy();
      }
    }
    private void buy(){
        // �ж��Ƿ���Ʊ
        if(ticketNums<=0){
            flag = false;
            return;
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ��Ʊ
        System.out.println(Thread.currentThread().getName() + "�õ���" + ticketNums--);
    }
}

// ����ȫ����Ʊ
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket,"��Ƶ�С��").start();
        new Thread(buyTicket,"��Ǯ�Ĵ���").start();
        new Thread(buyTicket,"�ɶ�Ļ�ţ��").start();
    }
}
/*�̲߳���ȫ->���ָ���
            ��Ǯ�Ĵ����õ���9
            ��Ƶ�С���õ���10
            �ɶ�Ļ�ţ���õ���8
            ��Ǯ�Ĵ����õ���7
            ��Ƶ�С���õ���6
            �ɶ�Ļ�ţ���õ���5
            ��Ǯ�Ĵ����õ���4
            ��Ƶ�С���õ���3
            �ɶ�Ļ�ţ���õ���3
            ��Ǯ�Ĵ����õ���2
            ��Ƶ�С���õ���1
            �ɶ�Ļ�ţ���õ���0
            ��Ǯ�Ĵ����õ���-1*/
