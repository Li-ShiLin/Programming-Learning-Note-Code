package com.lsl.code.sleep;

// ģ��ʱ�ӵ���ʱ
public class ThreadSleep {
    public static void tenDown() throws InterruptedException {
        int num = 10 ;
        while(true){
            Thread.sleep(1000);
            System.out.println(num--);
            if(num<=0){
                break;
            }
        }
    }

    public static void main(String[] args) {
        try {
            tenDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
