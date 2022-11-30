package com.lsl.code;

// ����һ������ʽ�ӿ�
interface Like{
    void lambda();
}

// ʵ����
class ILike implements Like{
    @Override
    public void lambda() {
        System.out.println("I like lambda1");
    }
}

// �Ƶ�lamda���ʽ
public class TestLambda {

    // ��̬�ڲ���
    static class ILike2 implements Like{
        @Override
        public void lambda() {
            System.out.println("I like lambda2");
        }
    }

    public static void main(String[] args) {
        // 1.newһ���ӿ�ָ��ʵ����
        Like like1 = new ILike();
        like1.lambda();

       // 2.��̬�ڲ���
        Like like2 = new ILike2();
        like2.lambda();

        // 3.�ֲ��ڲ���
        class ILike3 implements Like{
            @Override
            public void lambda() {
                System.out.println("I like lambda3");
            }
        }
        Like like3 = new ILike3();
        like3.lambda();

        // 4.�����ڲ��࣬û���������,����ϧ���ӿڻ��߸���
        Like like4 = new Like() {
             @Override
             public void  lambda(){
                 System.out.println("I like lambda4");
             }
        };
        like4.lambda();

        // 5.����lambda���ʽ��
        Like like5 = ()->{
            System.out.println("I like lambda5");
        };
        like5.lambda();
    }
}
/** ���:
 * I like lambda1
 * I like lambda2
 * I like lambda3
 * I like lambda4
 * I like lambda5
 */