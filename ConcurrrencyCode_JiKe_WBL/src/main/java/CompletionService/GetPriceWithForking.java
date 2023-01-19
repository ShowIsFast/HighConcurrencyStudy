package CompletionService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 演示 forking模式
 *
 * @author Demon
 * @date 2023/1/18
 */
public class GetPriceWithForking {

    // 创建线程池
    static ExecutorService executor = Executors.newFixedThreadPool(3);
    // 创建 CompletionService
    static CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
    // 用于保存 Future 对象
    List<Future<Integer>> futures = new ArrayList<>(3);

    public static void main(String[] args) {

    }

    private int getPrice() {
        // 提交异步任务，并保存 future 到 futures
        futures.add(
                cs.submit(() -> geocoderByS1()));
        futures.add(
                cs.submit(() -> geocoderByS2()));
        futures.add(
                cs.submit(() -> geocoderByS3()));
        // 获取最快返回的任务执行结果
        Integer r = 0;
        try {
            // 只要有一个成功返回，则 break
            for (int i = 0; i < 3; ++i) {
                r = cs.take().get();
                // 简单地通过判空来检查是否成功返回
                if (r != null) {
                    break;
                }
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 取消所有任务
            for (Future<Integer> f : futures)
                f.cancel(true);
        }
        // 返回结果
        return r;
    }

    private static void save(int r) {
    }

    private static int geocoderByS3() {
        return 3;
    }

    private static int geocoderByS2() {
        return 2;
    }

    private static int geocoderByS1() {
        return 1;
    }
}
