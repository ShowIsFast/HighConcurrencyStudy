package CountDownLatchStudy;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 演示 优化案例：订单，派送单，对账，记录
 * 查看订单，查看派送单并行
 * 对账，记录串行
 * @author Demon
 * @date 2023/1/8
 */
public class CountDownLatch1 {
    public void test1() throws InterruptedException {
        // 创建 2 个线程的线程池
        Executor executor = Executors.newFixedThreadPool(2);
        while("".equals("存在未对账订单")){
            // 计数器初始化为 2
            CountDownLatch latch = new CountDownLatch(2);
            // 查询未对账订单
            executor.execute(()-> {
                //pos = getPOrders();
                latch.countDown();
            });
            // 查询派送单
            executor.execute(()-> {
                //dos = getDOrders();
                latch.countDown();
            });

            // 等待两个查询操作结束
            latch.await();

            // 执行对账操作
            //diff = check(pos, dos);
            // 差异写入差异库
            //save(diff);
        }
    }

}
