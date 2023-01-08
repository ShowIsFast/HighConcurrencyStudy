package chapter1.createThread;

public class ThreadTest2{

    public static void main(String[] args) {
        System.out.println("this is father thread");
        Thread thread = new Thread(new RunnableTask());
        Thread thread2 = new Thread(new RunnableTask());
        thread.run();
        thread2.run();
    }
}
