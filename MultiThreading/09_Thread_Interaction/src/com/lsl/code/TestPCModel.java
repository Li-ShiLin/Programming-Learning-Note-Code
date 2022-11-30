package com.lsl.code;
// ����: ��������������ģ��
// �������: �̷ܳ� ����> ���û��������
// ��ɫ�� �����ߡ������ߡ���Ʒ��������
// ������
class Productor extends Thread{
      SynContainer container;
      public Productor(SynContainer container){
          this.container = container;
      }
    // ����
    @Override
    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println("������" + i + "ֻ��");
            container.push(new Chicken(i));
        }
    }
}

// ������
class Consumer extends Thread{
    SynContainer container;
    public Consumer(SynContainer container){
        this.container = container;
    }
    @Override
    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println("������-->" + container.pop().id + "ֻ��");
        }
    }
}

// ��Ʒ
class Chicken{
    int id; // ��Ʒ���
    public Chicken(int id){
        this.id = id;
    }
}

// ������
class SynContainer{
    // ��Ҫһ��������С
    Chicken[] chickens = new Chicken[10];
    // ����������
    int count = 0;

    // �����߷����Ʒ
    public synchronized void push(Chicken chicken){
        // ����������ˣ�����Ҫ�ȴ�����������
        if(count == chickens.length){
            // ֪ͨ���������ѡ�֪ͨ�����ߵȴ�
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // ���û���������Ǿ���Ҫ�����Ʒ
        chickens[count] = chicken;
        count++;
        // ֪ͨ����������
        this.notifyAll();
    }

    // ���������Ѳ�Ʒ
    public synchronized Chicken pop(){
        // �ж��ܷ�����
        if(count==0){
            // �ȴ������������������ߵȴ�
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // �����������
        count--;
        Chicken chicken = chickens[count];

        // �����ˣ�֪ͨ����������
        this.notifyAll();
        return chicken;
    }
}
public class TestPCModel {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        new Productor(container).start();
        new Consumer(container).start();
    }
}