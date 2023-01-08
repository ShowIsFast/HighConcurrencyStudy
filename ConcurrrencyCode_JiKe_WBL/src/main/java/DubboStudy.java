import javax.xml.ws.Response;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示 dubbo异步转同步过程
 *
 * @author Demon
 * @date 2023/1/8
 */
public class DubboStudy {
    // 创建锁与条件变量
    private final Lock lock = new ReentrantLock();
    private final Condition done = lock.newCondition();

    private Response response;

    // 调用方通过该方法等待结果
    Object get(int timeout) throws Exception {
        long start = System.nanoTime();
        lock.lock();
        try {
            while (!isDone()) {
                done.await(1, TimeUnit.SECONDS);
                long cur = System.nanoTime();
                if (isDone() || cur - start > timeout) {
                    break;
                }
            }
        } finally {
            lock.unlock();
        }
        if (!isDone()) {
            throw new TimeoutException();
        }
        return new Object();
    }

    //RPC 结果是否已经返回
    boolean isDone() {
        return response != null;
    }

    // RPC 结果返回时调用该方法
    private void doReceived(Response res) {
        lock.lock();
        try {
            response = res;
            done.signal();// 这里最好使用signalAll 随机唤醒一个线程，并发高的时候，可能导致超时
        } finally {
            lock.unlock();
        }
    }
}
