package new1;

import java.util.concurrent.Semaphore;

/**
 *
 * 演示 通过Semaphore来支持并发
 * @author Demon
 * @date 2023/1/8
 */
public class SemaphoreCount {
    static int count;
    // 初始化信号量
    static final Semaphore s = new Semaphore(1);
    // 用信号量保证互斥
    static void addOne() throws InterruptedException {
        s.acquire();
        try {
            count+=1;
        } finally {
            s.release();
        }
    }
}
