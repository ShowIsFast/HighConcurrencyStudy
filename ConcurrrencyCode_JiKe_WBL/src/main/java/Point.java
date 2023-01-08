import java.util.concurrent.locks.StampedLock;

/**
 * 演示 乐观读用法
 * 注意：34行计算业务期间可能x，y值会变化，需要看业务需求是否严格要求互斥
 * @author Demon
 * @date 2023/1/8
 */
public class Point {
    private int x, y;
    final StampedLock sl = new StampedLock();
    // 计算到原点的距离
    int distanceFromOrigin() {
        // 乐观读
        long stamp =
                sl.tryOptimisticRead();
        // 读入局部变量，
        // 读的过程数据可能被修改
        int curX = x, curY = y;
        // 判断执行读操作期间，
        // 是否存在写操作，如果存在，
        // 则 sl.validate 返回 false
        if (!sl.validate(stamp)){
            // 升级为悲观读锁 与写锁互斥，保证x,获取的是最新值
            stamp = sl.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                // 释放悲观读锁
                sl.unlockRead(stamp);
            }
        }
        return (int) Math.sqrt(curX * curX + curY * curY);
    }
}
