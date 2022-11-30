package com.lsl.code.ThreadUnsafe;

// �˻�
class Account{
    int money;  // ���
    String name;// ����
    public Account(int money,String name){
        this.money = money;
        this.name = name;
    }
}

// ����ģ��ȡǮ
class Drawing extends Thread{
    // �˻�
    Account account;
    // ȡ�˶���Ǯ
    int drawingMoney;
    // ���������ж���Ǯ
    int nowMoney;
    public Drawing(Account account,int drawingMoney ,String name){
        super(name); // �̳и��� -> �߳�����
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    //ȡǮ
    @Override
    public void run(){
        // �ж���û��Ǯ
        if (account.money-drawingMoney<0){
            System.out.println(Thread.currentThread ().getName()+"Ǯ����,ȡ����");
            return;
        }
        // sleep���ԷŴ�����ķ�����
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // �������=���C��ȡ��Ǯ
        account.money = account.money - drawingMoney;//�������Ǯ
        nowMoney = nowMoney + drawingMoney;
        System.out.println(account.name+"���Ϊ:"+account.money) ;
        //Thread.currentThread().getNawe() �ȼ��� this.getName(
        System.out.println(this.getName()+"�����Ǯ: "+nowMoney) ;
        }
}

// ����ȫ��ȡǮ
// ������ȥ����ȡǮ���˻�
public class UnsafeBank {
    public static void main(String[] args) {
        Account account = new Account(100,"������");
        Drawing you = new Drawing(account,50,"��");
        Drawing girlFriend = new Drawing(account,100,"Ů����");
        you.start();
        girlFriend.start();
    }
}

/*�̲߳���ȫ:
        ���������Ϊ:-50
        ���������Ϊ:-50
        �������Ǯ: 50
        Ů���������Ǯ: 100*/
