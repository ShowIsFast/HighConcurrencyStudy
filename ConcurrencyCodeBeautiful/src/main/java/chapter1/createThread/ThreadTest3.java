package chapter1.createThread;

import java.util.concurrent.FutureTask;

public class ThreadTest3 {
    public static void main(String[] args) {
        System.out.println("this is main");
        FutureTask<String> futureTask = new FutureTask(new CallableTask());
        FutureTask<String> futureTask2 = new FutureTask(new CallableTask());
        new Thread(futureTask).start();
        new Thread(futureTask2).start();
        try {
            String result = futureTask.get();
            String result2 = futureTask2.get();
            System.out.println(result);
            System.out.println(result2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
