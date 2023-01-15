package new1;

/**
 * 演示 lock 如何实现可见性
 * 1 顺序性规则 t1 count++ 对于unlock（）是可见的
 * 2 volatile变量规则 lock和unlock操作会读写声明为volatile变量state 所以t1解锁对于t2加锁是可见的
 * 3 传递性对责 count++ 对于t2的lock是可见的
 * @author Demon
 * @date 2023/1/7
 */
public class SampleLock {
    volatile int state;
    // 加锁
    void lock() {
        // 省略代码无数
        state = 1;
    }
    // 解锁
    void unlock() {
        // 省略代码无数
        state = 0;
    }
}
