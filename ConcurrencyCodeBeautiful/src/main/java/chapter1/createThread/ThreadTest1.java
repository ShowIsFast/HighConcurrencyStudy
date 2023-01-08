package chapter1.createThread;

/**
 * 继承Thread
 */
public class ThreadTest1 extends Thread {
    @Override
    public void run() {
        System.out.println("this is son thread");
    }

    public static void main(String[] args) {
        System.out.println("this is father thread");
        ThreadTest1 threadTest1 = new ThreadTest1();
        threadTest1.start();
    }
}
