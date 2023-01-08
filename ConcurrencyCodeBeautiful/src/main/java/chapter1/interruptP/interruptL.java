package chapter1.interruptP;

/**
 * 使用中断标记优雅退出线程
 */
public class interruptL {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted())
                    System.out.println(Thread.currentThread().getName()+":hello");

            }
        });

        threadA.start();

        Thread.sleep(1000);

        System.out.println("main thread interrupt threadA");
        threadA.interrupt();

        threadA.join();

        System.out.println("main is over");
    }
}
