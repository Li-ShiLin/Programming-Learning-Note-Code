package com.lsl.code.sleep;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestSleep {
    public static void main(String[] args) {
        // ��ӡϵͳ��ǰʱ��
        Date startTime = new Date(System.currentTimeMillis());// ��ȡϵͳ��ǰʱ��
        while(true){
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
                startTime = new Date(System.currentTimeMillis());// ��ȡϵͳ��ǰʱ��
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
