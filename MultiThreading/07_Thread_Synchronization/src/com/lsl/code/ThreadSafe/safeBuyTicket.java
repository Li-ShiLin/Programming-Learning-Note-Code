package com.lsl.code.ThreadSafe;

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
    // synchronized ͬ�������� ������this��Ĭ����this��
    private synchronized void buy(){
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

// ��ȫ����Ʊ
public class safeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket,"��Ƶ�С��").start();
        new Thread(buyTicket,"��Ǯ�Ĵ���").start();
        new Thread(buyTicket,"�ɶ�Ļ�ţ��").start();
    }
}
/*�̰߳�ȫ��
            ��Ǯ�Ĵ����õ���10
            ��Ǯ�Ĵ����õ���9
            �ɶ�Ļ�ţ���õ���8
            �ɶ�Ļ�ţ���õ���7
            �ɶ�Ļ�ţ���õ���6
            �ɶ�Ļ�ţ���õ���5
            �ɶ�Ļ�ţ���õ���4
            �ɶ�Ļ�ţ���õ���3
            �ɶ�Ļ�ţ���õ���2
            �ɶ�Ļ�ţ���õ���1
            */
