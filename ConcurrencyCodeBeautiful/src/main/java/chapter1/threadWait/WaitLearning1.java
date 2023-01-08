package chapter1.threadWait;

/**
 * 当前线程调用共享对象的wait方法，只会释放该共享对象的锁，
 * 其他共享对象的监视器锁不会释放
 */
public class WaitLearning1 {
    // 创建资源
    private static volatile  Object resourceA = new Object();
    private static volatile  Object resourceB = new Object();
    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    System.out.println("ThreadA get resourceA lock");
                    synchronized (resourceB){
                        System.out.println("TheradA get resourceB lock");
                        System.out.println("ThreadA release resourceA lock");
                        try {
                            resourceA.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    System.out.println("threadB get resosurceB");
                    System.out.println("threadB try to get resourceA");
                    synchronized (resourceB){
                        System.out.println("threadB get resourceA");
                    }
                }
            }
        });
        try {
            threadA.start();
            // 线程A获取资源A，B锁，然后
            Thread.sleep(1000);
            threadB.start();
            threadA.join();
            threadB.join();
            System.out.println("main over");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
