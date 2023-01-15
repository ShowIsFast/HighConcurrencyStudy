package new1;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示 通过lock破坏占有等待条件
 * @author Demon
 * @date 2023/1/7
 */
public class Account {
    private int balance;
    private final Lock lock = new ReentrantLock();

    // 转账
    void transfer(Account tar, int amt) throws InterruptedException {
        Random random = new Random(47);
        while (true) {
            // 增加随机等待时间 解决活锁问题
            // 转账成功后 需要跳出循环
            Thread.sleep(random.nextInt(10));
            if (this.lock.tryLock()) {
                try {
                    if (tar.lock.tryLock()) {
                        try {
                            this.balance -= amt;
                            tar.balance += amt;
                        } finally {
                            tar.lock.unlock();
                        }
                    }//if
                } finally {
                    this.lock.unlock();
                }
            }//if
        }//while
    }//transfer
}
