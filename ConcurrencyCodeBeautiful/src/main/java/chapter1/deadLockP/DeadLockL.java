package chapter1.deadLockP;

/**
 * 演示产生死锁
 */
public class DeadLockL {

    private final static Object resourceA = new Object();
    private final static Object resourceB = new Object();
    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadA get resourceA");
                synchronized (resourceA) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("threadA try to get resourceB");
                        synchronized (resourceB) {
                            System.out.println("threadA get resourceB");
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadB get resourceB");
                synchronized (resourceB) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("threadB try to get resourceA");
                        synchronized (resourceA) {
                            System.out.println("threadB get resourceA");
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        threadA.start();
        threadB.start();

    }
}
