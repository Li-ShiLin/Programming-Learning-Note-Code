package com.lsl.code.DeadLock;

// �ں�
class Lipstick{
}
// ����
class Mirror{
}

class Makeup extends Thread{
    //��Ҫ����Դֻ��һ�ݣ���static����ֻ֤��һ��
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();
    // ѡ��
    int choice;// ѡ��
    String girlName;// ʹ�û�ױƷ����
    Makeup(int choice,String girlName){
        this.choice = choice;
        this.girlName = girlName;
    }
    @Override
    public void run(){
        // ��ױ
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // ��ױ,������жԷ�������������Ҫ�õ��Է�����Դ
    private void makeup() throws InterruptedException {
        if( choice==0) {
            // ��ÿں����
            synchronized(lipstick) {
                System.out.println (this.girlName+"��ÿں����");
                Thread.sleep( 1000) ;
                synchronized (mirror){ // һ���Ӻ����þ���
                    System.out.println (this.girlName+"��þ��ӵ���");
                }
            }
        }else{
            synchronized (mirror){//��þ��ӵ���
                System.out.println (this.girlName+"��þ��ӵ���");
                Thread.sleep( 2000);
                synchronized (lipstick){ //һ���Ӻ����ÿں�
                    System.out.println (this.girlName+"��ÿں����");
                }
            }
        }
    }
}
// ����:����̻߳���ռ�жԷ���Ҫ����Դ,Ȼ���γɽ���
public class Deadlock {
    public static void main(String[] args) {
        Makeup g1 = new Makeup(0,"�ҹ���");
        Makeup g2 = new Makeup(1,"��ѩ����");
        g1.start();
        g2.start();
    }

}
/*����������
        ��ѩ������þ��ӵ���
        �ҹ����ÿں����*/
