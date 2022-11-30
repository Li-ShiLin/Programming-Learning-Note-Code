package cn.lsl.code.extendsThread;
/**
 * �����̷߳�ʽһ:�̳�Thread�࣬��дrun()����������start�����߳�
 * �Զ����߳���̳�Thread��
 *         1.��дrun()��������д�߳�ִ����
 *         2.�����̶߳���
 *         3.����start()���������߳�
 * �̲߳�һ������ִ�У�CPU���ŵ���
 */
public class TestThread1 extends Thread{
    @Override
    public void run(){
        //run�����߳���
        for(int i=0;i<200;i++){
            System.out.println("���ڿ�����---"+i);
        }
    }

    //main�߳�->���߳�
    public static void main(String[] args) {
        // ����һ���̶߳���
        TestThread1 testThread1 =new TestThread1();
        // ����start()���������߳�
        testThread1.start();
        for (int i = 0; i < 200; i++) {
            System.out.println("����ѧϰ���߳�---"+i);
        }
    }
}
/**
 * �������̺߳����߳̽���ִ��
 * �ܽ�:ע��,�߳̿�����һ������ִ��,��CPU����ִ��
 */
