package cn.lsl.code.implementsRunnable;
// ģ���������
public class Race implements Runnable{
    // ʤ����
    private static String winner;
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {

            // ģ������˯��
            if("����".equals(Thread.currentThread().getName()) && i%20==0){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // �жϱ����Ƿ����
            if(gameOver(i)){
               break;
            }
            System.out.println(Thread.currentThread().getName()+"-->����"+ i + "��");
        }
    }
    // �ж��Ƿ���ɱ���
    private boolean gameOver(int steps){
        // �ж��Ƿ���ʤ����
        if(winner!=null){
            return true;
        }else if (steps>=100){
            winner = Thread.currentThread().getName();
            System.out.println("winner is" + winner);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race,"����").start();
        new Thread(race,"�ڹ�").start();
    }
}
