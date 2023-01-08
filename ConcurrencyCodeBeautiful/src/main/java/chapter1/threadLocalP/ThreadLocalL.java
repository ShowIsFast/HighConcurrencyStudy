package chapter1.threadLocalP;

/**
 * 演示通过ThreadLocal存取变量，各个线程之间不受影响
 */
public class ThreadLocalL {

    private static ThreadLocal<String> localVariable = new ThreadLocal<>();

    private static void print(String str){
        // 打印当前线程存储的变量
        System.out.println(str+localVariable.get());
        // 删除当前线程存储的变量
        localVariable.remove();
    }


    public static void main(String[] args) {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                localVariable.set("threadA variable");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                print("threadA:");
                System.out.println("threadA after remove, variable:" + localVariable.get());

            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                localVariable.set("threadB variable");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                print("threadB:");
                System.out.println("threadB after remove, variable:" + localVariable.get());

            }
        });

        threadA.start();
        threadB.start();

    }
}
