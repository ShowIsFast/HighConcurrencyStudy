package CompletionService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 优化询价应用
 * 缺点：get方法是阻塞的，上一步获取报价时间过长会阻塞下一步
 * @author Demon
 * @date 2023/1/18
 */
public class GetPrice {
    // 创建线程池
    static ExecutorService executor =
            Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int a1 = 0, a2 = 0, a3 = 0;
        // 异步向电商 S1 询价
        Future<Integer> f1 =
                executor.submit(
                        () -> getPriceByS1(), a1);
        // 异步向电商 S2 询价
        Future<Integer> f2 =
                executor.submit(
                        () -> getPriceByS2(), a2);
        // 异步向电商 S3 询价
        Future<Integer> f3 =
                executor.submit(
                        () -> getPriceByS3(), a3);


        // 获取电商 S1 报价并保存
        int r1 = f1.get();
        executor.execute(() -> save(r1));

        // 获取电商 S2 报价并保存
        int r2 = f2.get();
        executor.execute(() -> save(r2));

        // 获取电商 S3 报价并保存
        int r3 = f3.get();
        executor.execute(() -> save(r3));
    }

    private static void save(int r) {
    }

    private static void getPriceByS3() {
    }

    private static void getPriceByS2() {
    }

    private static void getPriceByS1() {
    }
}
