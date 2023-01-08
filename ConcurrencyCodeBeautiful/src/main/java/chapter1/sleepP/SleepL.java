package chapter1.sleepP;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程在睡眠时，监视器锁资源不会释放
 */
public class SleepL {
    // 此为独占锁 任意时刻只可被一个线程获取
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("thread one start sleep");
                    Thread.sleep(1000);
                    System.out.println("thread one is in awaked");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    lock.unlock();
                }
            }
        });
        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("thread two start sleep");
                    Thread.sleep(1000);
                    System.out.println("thread two is in awaked");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        threadOne.start();
        threadTwo.start();

    }
}
