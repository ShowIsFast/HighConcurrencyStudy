package chapter1.createThread;

import java.util.concurrent.Callable;

/**
 * chaun
 */
public class CallableTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("mang sons thread , can return");
        return "hello";
    }
}
