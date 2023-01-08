package chapter1;

/**
 * 设置为守护线程
 */
public class DemonTest {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        // 设置为守护线程
        daemonThread.setDaemon(true);

    }
}
