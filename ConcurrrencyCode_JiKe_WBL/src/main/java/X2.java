import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示 什么是重入锁
 * addOne 顺利执行需要同一线程重复获取锁资源
 * @author Demon
 * @date 2023/1/7
 */
public class X2 {
    private final Lock rtl =
            new ReentrantLock();
    int value;
    public int get() {
        // 获取锁
        rtl.lock(); //②
        try {
            return value;
        } finally {
            // 保证锁能释放
            rtl.unlock();
        }
    }
    public void addOne() {
        // 获取锁
        rtl.lock();
        try {
            value = 1 + get(); //①
        } finally {
            // 保证锁能释放
            rtl.unlock();
        }
    }
}
