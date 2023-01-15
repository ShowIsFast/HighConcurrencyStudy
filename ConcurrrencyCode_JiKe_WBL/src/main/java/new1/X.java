package new1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示 lock加锁解锁范式
 *
 * @author Demon
 * @date 2023/1/7
 */
public class X {
    private final Lock rtl = new ReentrantLock();
    int value;

    public void addOne() {
        // 获取锁
        rtl.lock();
        try {
            value += 1;
        } finally {
            // 保证锁能释放
            rtl.unlock();
        }
    }
}
