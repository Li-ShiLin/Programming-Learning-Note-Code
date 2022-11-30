package com.lsl.code;
// ���������������������⣺�źŵƷ�����־λ���
// ������ -> ��Ա
class Player extends Thread{
    TV tv;
    public Player(TV tv){
        this.tv = tv;
    }
    @Override
    public void run(){
        for (int i = 1; i <= 20; i++) {
            if(i%2==0){
                this.tv.play("�ɽ�������"+ (i/2));
            }else {
                this.tv.play("ѩ�к�����"+ (i/2 + 1) );
            }
        }
    }
}
// ������ --> ����
class Watcher extends Thread{
    TV tv;
    public Watcher(TV tv){
        this.tv = tv;
    }
    @Override
    public void run(){
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}
// ��Ʒ -> ��Ŀ
class TV{
     // ��Ա���ݣ����ڵȴ�
     // ���ڹۿ�����Ա�ȴ�
    String voice; // ���ݵĽ�Ŀ
    boolean flag = true;

    // ����
    public synchronized void play(String voice){
        if(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("��Ա�����ˣ�" + voice);
        // ֪ͨ���ڹۿ�
        this.notifyAll();
        this.voice = voice;
        this.flag = !this.flag;
    }
    // �ۿ�
    public synchronized void watch(){
        if(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("���ڹۿ��ˣ�"+voice);
        // ֪ͨ��Ա����
        this.notifyAll();
        this.flag = !this.flag;
    }
}
// ���������������������⣺�źŵƷ�����־λ���
public class TestPCSignal {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}
/*�����
            ��Ա�����ˣ�ѩ�к�����1
            ���ڹۿ��ˣ�ѩ�к�����1
            ��Ա�����ˣ��ɽ�������1
            ���ڹۿ��ˣ��ɽ�������1
            ��Ա�����ˣ�ѩ�к�����2
            ���ڹۿ��ˣ�ѩ�к�����2
            ��Ա�����ˣ��ɽ�������2
            ���ڹۿ��ˣ��ɽ�������2
            ��Ա�����ˣ�ѩ�к�����3
            ���ڹۿ��ˣ�ѩ�к�����3
            ��Ա�����ˣ��ɽ�������3
            ���ڹۿ��ˣ��ɽ�������3
            ��Ա�����ˣ�ѩ�к�����4
            ���ڹۿ��ˣ�ѩ�к�����4
            ��Ա�����ˣ��ɽ�������4
            ���ڹۿ��ˣ��ɽ�������4
            ��Ա�����ˣ�ѩ�к�����5
            ���ڹۿ��ˣ�ѩ�к�����5
            ��Ա�����ˣ��ɽ�������5
            ���ڹۿ��ˣ��ɽ�������5
            ��Ա�����ˣ�ѩ�к�����6
            ���ڹۿ��ˣ�ѩ�к�����6
            ��Ա�����ˣ��ɽ�������6
            ���ڹۿ��ˣ��ɽ�������6
            ��Ա�����ˣ�ѩ�к�����7
            ���ڹۿ��ˣ�ѩ�к�����7
            ��Ա�����ˣ��ɽ�������7
            ���ڹۿ��ˣ��ɽ�������7
            ��Ա�����ˣ�ѩ�к�����8
            ���ڹۿ��ˣ�ѩ�к�����8
            ��Ա�����ˣ��ɽ�������8
            ���ڹۿ��ˣ��ɽ�������8
            ��Ա�����ˣ�ѩ�к�����9
            ���ڹۿ��ˣ�ѩ�к�����9
            ��Ա�����ˣ��ɽ�������9
            ���ڹۿ��ˣ��ɽ�������9
            ��Ա�����ˣ�ѩ�к�����10
            ���ڹۿ��ˣ�ѩ�к�����10
            ��Ա�����ˣ��ɽ�������10
            ���ڹۿ��ˣ��ɽ�������10*/
