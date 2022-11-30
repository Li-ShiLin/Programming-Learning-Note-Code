package com.lsl.code;

interface Love{
    void love(int a);
}
class Ilove implements Love{
    @Override
    public void love(int a) {
        System.out.println("I love you-->" + a);
    }
}

public class TestLambdaTwo {
    public static void main(String[] args) {
        Love love1 = new Ilove();
        love1.love(1);

        Love love2 = new Ilove(){
            @Override
            public void love(int a){
                System.out.println("I love you-->" + a);
            }
        };
        love2.love(2);
        // ��: ����lambda���ʽ
        Love love3 = (int a)->{
                System.out.println("I love you-->" + a);
        };
        love3.love(3);

        // ��: ȥ����������
        Love love4 = (a)->{
            System.out.println("I love you-->" + a);
        };
        love4.love(4);

        // ��: ȥ������������
        Love love5 = a->{
            System.out.println("I love you-->" + a);
        };
        love5.love(5);

        // ��: ȥ��������Ļ�����
        Love love6 = a-> System.out.println("I love you-->" + a);
        love6.love(6);
    }
}

//�ܽ�:
//    lambda���ʽֻ����һ�д��������²��ܼ򻯳�Ϊһ��
//    ����ж��У���ô���ô���������ǰ���ǽӿ�Ϊ����ʽ�ӿ�
//    �������Ҳ����ȥ�Ӳ������ͣ�Ҫȥ�ӾͶ�ȥ��,�����������