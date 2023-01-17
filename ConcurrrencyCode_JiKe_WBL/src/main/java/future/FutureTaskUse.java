package future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/** futuretask既可以作为任务传递，也可以获取执行结果
 * @author Demon
 * @date 2023/1/17
 */
public class FutureTaskUse {

    public static void main(String[] args) {
        // 创建 FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1 + 2);
        // 创建线程池
        ExecutorService es = Executors.newCachedThreadPool();
        // 提交 FutureTask
        es.submit(futureTask);
        // 获取计算结果
        Integer result;
        try {
            result = futureTask.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);
    }

}
