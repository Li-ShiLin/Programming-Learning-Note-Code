package cn.lsl.code.implementsRunnable;
// �����̷߳�ʽ2�Uʵ��runnable�ӿ�,��дrun������ִ���߳���Ҫ����runnable�ӿ�ʵ���ࡣ����start����
public class TestRunnableThread1 implements Runnable{
    @Override
    public void run(){
        //run�����߳���
        for(int i=0;i<200;i++){
            System.out.println("���ڿ�����---"+i);
        }
    }
    //main�߳�->���߳�
    public static void main(String[] args) {
        // ����runnbale�ӿڵ�ʵ�������
        TestRunnableThread1 testRunnableThread1 = new TestRunnableThread1();
        // �����̶߳���,ͨ���̶߳������������ǵ��߳�,����
//        Thread thread = new Thread(testRunnableThread1);
//        thread.start();
        new Thread(testRunnableThread1).start();
        for (int i = 0; i < 200; i++) {
            System.out.println("����ѧϰ���߳�---"+i);
        }
    }
}
