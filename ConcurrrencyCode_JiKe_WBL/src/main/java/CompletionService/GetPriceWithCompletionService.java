package CompletionService;

import java.util.concurrent.*;

/**
 * @author Demon
 * @date 2023/1/19
 */
public class GetPriceWithCompletionService {
    // 创建线程池
    static ExecutorService executor = Executors.newFixedThreadPool(3);
    // 创建 CompletionService
    static CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 异步向电商 S1 询价
        cs.submit(() -> getPriceByS1());
        // 异步向电商 S2 询价
        cs.submit(() -> getPriceByS2());
        // 异步向电商 S3 询价
        cs.submit(() -> getPriceByS3());
        // 将询价结果异步保存到数据库
        for (int i = 0; i < 3; i++) {
            Integer r = cs.take().get();
            executor.execute(() -> save(r));
        }
    }

    private static void save(int r) {
    }

    private static int getPriceByS3() {
        return 3;
    }

    private static int getPriceByS2() {
        return 2;
    }

    private static int getPriceByS1() {
        return 1;
    }

}
