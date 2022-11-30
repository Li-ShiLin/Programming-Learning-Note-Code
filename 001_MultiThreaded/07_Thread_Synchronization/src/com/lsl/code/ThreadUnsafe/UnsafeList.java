package com.lsl.code.ThreadUnsafe;

import java.util.ArrayList;
import java.util.List;

// �̲߳���ȫ�ļ��ϣ���ArrayList
public class UnsafeList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                list.add(Thread.currentThread().getName());}
            ).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());//���9987 -> �̲߳���ȫ
    }
}
