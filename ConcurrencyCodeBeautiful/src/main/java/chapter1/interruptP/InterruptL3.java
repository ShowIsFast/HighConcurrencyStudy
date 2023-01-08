package chapter1.interruptP;

/**
 * 当前线程是否中断并清楚中断标记
 */
public class InterruptL3 {

    public static void main(String[] args) throws InterruptedException {

        Thread threada = new Thread(new Runnable() {
            @Override
            public void run() {

                while (!Thread.currentThread().interrupted()){

                }
                System.out.println("threada is "+Thread.currentThread().isInterrupted());
            }
        });

        threada.start();

        threada.interrupt();

        threada.join();

        System.out.println("main is over");

    }
}
