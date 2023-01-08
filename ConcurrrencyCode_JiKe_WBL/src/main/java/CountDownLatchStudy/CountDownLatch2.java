package CountDownLatchStudy;


import java.util.Vector;
import java.util.concurrent.*;

/**
 * 演示 优化案例：订单，派送单，对账，记录
 * 使用cyclicbarrier，使查看一条订单，查看一条派送单，后进行剩余两个操作，三个动作并行，以提升性能
 * @author Demon
 * @date 2023/1/8
 */
public class CountDownLatch2<P,D> {
    // 订单队列
    Vector<P> pos;
    // 派送单队列
    Vector<D> dos;
    // 执行回调的线程池
    Executor executor =
            Executors.newFixedThreadPool(1);
    final CyclicBarrier barrier =
            new CyclicBarrier(2, ()->{
                executor.execute(()->check());
            });
    void check(){
        P p = pos.remove(0);
        D d = dos.remove(0);
        // 执行对账操作
//        diff = check(p, d);
        // 差异写入差异库
//        save(diff);
    }
    void checkAll(){
        // 循环查询订单库
        Thread T1 = new Thread(()->{
            while("".equals("存在未对账订单")){
                // 查询订单库
//                pos.add(getPOrders());
                // 等待
                try {
                    barrier.await();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        T1.start();
        // 循环查询运单库
        Thread T2 = new Thread(()->{
            while("".equals("存在未对账订单")){
                // 查询运单库
//                dos.add(getDOrders());
                // 等待
                try {
                    barrier.await();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        T2.start();
    }

}
