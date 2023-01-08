package chapter1.threadWait;

/**
 * 其他线程中断
 * 某线程的使用wait方法挂起后
 * 会抛出InterruptException
 */
public class InterruptL {
    static Object object = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("begin");
                synchronized (object){
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("end");
                }
            }
        });
        threadA.start();
        Thread.sleep(1000);
        System.out.println("-- begin interrupt threadA");
        threadA.interrupt();
        System.out.println("-- end interrupt threadA");


    }
}
