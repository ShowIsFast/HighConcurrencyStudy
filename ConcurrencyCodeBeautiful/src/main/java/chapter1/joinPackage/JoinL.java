package chapter1.joinPackage;

/**
 *  线程a调用线程b的join方法后被阻塞，
 *  会因其他线程调用线程a的interrupt方法，
 *  中断线程a抛出异常而返回
 */
public class JoinL {
    public static void main(String[] args) {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread one begin");
                for (;;){

                }
            }
        });

        Thread mainThread = Thread.currentThread();

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    mainThread.interrupt();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        threadOne.start();

        threadTwo.start();

        try {
            threadOne.join();//蹲adc走过来，反被对方打野秒杀（1，2线程是一伙）
        } catch (InterruptedException e) {
            System.out.println("mainThread e :"+e);
            throw new RuntimeException(e);
        }


    }
}
