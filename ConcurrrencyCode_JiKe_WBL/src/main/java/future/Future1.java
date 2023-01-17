package future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**主线程等待子线程执行结果，通过result共享数据（executor(Runnable task, T result)）
 * @author Demon
 * @date 2023/1/17
 */
public class Future1 {
    private static ExecutorService executor = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建 Result 对象 r
        Result r = new Result();
        r.setBalance(99);
        // 提交任务
        Future<Result> future = executor.submit(new Task(r), r);
        Result fr = future.get();
        // 下面等式成立
//        fr === r;
        System.out.println(fr.getBalance());
    }
    static class Task implements Runnable{
        Result r;
        // 通过构造函数传入 result
        Task(Result r){
            this.r = r;
        }
        public void run() {
            // 可以操作 result
            int balance = r.getBalance();
            r.setBalance(balance+1);
        }
    }
}
