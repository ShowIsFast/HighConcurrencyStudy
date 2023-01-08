package chapter1.interruptP;

/**
 * 使用interrupt方法强制阻塞线程抛出异常返回，
 * 目的：提前唤醒线程
 */
public class interruptL2 {

    public static void main(String[] args) throws InterruptedException {
        Thread threada = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threada will sleep 200000");
                try {
                    Thread.sleep(20000);
                    System.out.println("threada awaking");
                } catch (InterruptedException e) {
                    System.out.println("threada is interrupted while sleeping");
//                    throw new RuntimeException(e);
                    return;
                }
                System.out.println("threada is normal");
            }
        });

        threada.start();

        threada.interrupt();

        threada.join();

        System.out.println("main thread is over");


    }
}
