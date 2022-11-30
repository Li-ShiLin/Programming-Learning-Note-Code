package com.lsl.code;
//��̬����ģʽ�ܽ�;
//        ��ʵ����ʹ������Ҫʵ��ͬһ���ӿ�
//        �������Ҫ������ʵ��ɫ
//�ô�:
//        �������������ܶ���ʵ���������˵�����
//        ��ʵ����רע���Լ�������
interface Marry{
    public void happyMarry();
}

class You implements Marry{
    @Override
    public void happyMarry() {
        System.out.println("��Ҫ����ˣ���hi��");
    }
}

// �����ɫ->���칫˾����������
class WeddingCompany implements Marry{
    // ������Ҫ��������� �� ���������Ҫ����һ����ʵ����
    private Marry you;
    public WeddingCompany(Marry you){
        this.you = you;
    }
    @Override
    public void happyMarry() {
        before();
        this.you.happyMarry();// ��ʵ����
        after();
    }

    private void before() {
        System.out.println("���֮ǰ�����ö���");
    }
    private void after() {
        System.out.println("���֮�󣬴�����Ǯ");
    }
}

// ��̬����
public class StaticProxy {
    public static void main(String[] args) {
        //�������  ���� ��ʵ����
        You you = new You();
        // you.happyMarry();
        new WeddingCompany(you).happyMarry();
    }
}
/*
��:��ʵ��ɫ
���칫˾:�����㣬���㴦������½��:ʵ�ֶ�ʵ�ֽ��ӿڼ���
��ʾ:ʵ�־�̬����Ա�Thread
* */