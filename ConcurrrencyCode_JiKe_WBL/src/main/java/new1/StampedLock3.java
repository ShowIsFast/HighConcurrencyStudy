package new1;

import java.util.concurrent.locks.StampedLock;

/**
 * 演示 stampedlock写模板
 *
 * @author Demon
 * @date 2023/1/8
 */
public class StampedLock3 {
    public void test() {
        final StampedLock sl = new StampedLock();
        long stamp = sl.writeLock();
        try {
            // 写共享变量
            //......
        } finally {
            sl.unlockWrite(stamp);
        }
    }

}
