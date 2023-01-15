package new1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示 通过读写锁缓存 范式
 * 使用写锁保证hashmap存操作的安全
 * @author Demon
 * @date 2023/1/8
 */
class Cache<K, V> {
    final Map<K, V> m = new HashMap<>();
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    // 读锁
    final Lock r = rwl.readLock();
    // 写锁
    final Lock w = rwl.writeLock();

    // 读缓存
    V get(K key) {
        r.lock();
        try {
            return m.get(key);
        } finally {
            r.unlock();
        }
    }

    // 写缓存
    V put(K key, V v) {
        w.lock();
        try {
            return m.put(key, v);
        } finally {
            w.unlock();
        }
    }
}
