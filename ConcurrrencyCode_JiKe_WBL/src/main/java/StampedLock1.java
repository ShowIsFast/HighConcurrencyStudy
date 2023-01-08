import java.util.concurrent.locks.StampedLock;

/**
 * 演示 stampedlock 悲观读锁和写锁
 * @author Demon
 * @date 2023/1/8
 */
public class StampedLock1 {
    final StampedLock sl = new StampedLock();

    public void test() {
        // 获取 / 释放悲观读锁示意代码
        long stamp1 = sl.readLock();
        try {
            // 省略业务相关代码
        } finally {
            sl.unlockRead(stamp1);
        }

        // 获取 / 释放写锁示意代码
        long stamp2 = sl.writeLock();
        try {
            // 省略业务相关代码
        } finally {
            sl.unlockWrite(stamp2);
        }
    }


}
