package new1;

import java.util.concurrent.locks.StampedLock;

/**
 * 演示 stampedlock读模板
 *
 * @author Demon
 * @date 2023/1/8
 */
public class StampedLock2 {
    public void test() {
        final StampedLock sl = new StampedLock();
        // 乐观读
        long stamp = sl.tryOptimisticRead();
        // 读入方法局部变量
        //......
        // 校验 stamp
        if (!sl.validate(stamp)) {
            // 升级为悲观读锁
            stamp = sl.readLock();
            try {
                // 读入方法局部变量
                //.....
            } finally {
                // 释放悲观读锁
                sl.unlockRead(stamp);
            }
        }
        // 使用方法局部变量执行业务操作
        //......
    }

}
