package CompletionService;

import java.util.concurrent.*;

/**
 * 优化询价应用（将最终执行结果放入阻塞队列中）
 * 缺点：get方法是阻塞的，上一步获取报价时间过长会阻塞下一步
 * 已解决缺点 实际项目中建议使用completionservce
 * 使用阻塞队列
 * @author Demon
 * @date 2023/1/18
 */
public class GetPriceWithBlockedQueue {
    // 创建线程池
    static ExecutorService executor = Executors.newFixedThreadPool(3);

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


        // 创建阻塞队列
        BlockingQueue<Integer> bq =
                new LinkedBlockingQueue<>();
        // 电商 S1 报价异步进入阻塞队列
        executor.execute(()->
        {
            try {
                bq.put(f1.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        // 电商 S2 报价异步进入阻塞队列
        executor.execute(()->
        {
            try {
                bq.put(f2.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        // 电商 S3 报价异步进入阻塞队列
        executor.execute(()->
        {
            try {
                bq.put(f3.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        // 异步保存所有报价
        for (int i=0; i<3; i++) {
            Integer r = bq.take();
            executor.execute(()->save(r));
        }
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
