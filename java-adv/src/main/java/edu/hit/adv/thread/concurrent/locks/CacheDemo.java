/**
 * @Package edu.hit.guide.java.adv.thread.concurrent.locks
 * @author ivybest ivybestdev@163.com
 * @date 2016年3月28日-下午3:42:00
 */
package edu.hit.adv.thread.concurrent.locks;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>CacheDemo</p>
 * <p>自定义缓存系统</p>
 *
 * @author ivybest ivybestdev@163.com
 * @date 2016年3月28日-下午3:42:00
 * @version 1.0
 *
 */
public class CacheDemo {
    private Map<String, Object> cache = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public Object getData(Serializable key) {
        lock.readLock().lock();
        try {
            Object val = cache.get(key);
            if (val == null) {
                lock.readLock().unlock();
                lock.writeLock().lock();
                try {
                    // 添加 if 判断，防止多个线程，同时未读到数据
                    // 同时拿到写锁，依次执行数据库查询操作
                    // val被第一个线程修改后，之后的线程不会修改val了
                    if (val == null) {
                        // TODO   去数据库查询相应数据
                    }
                } finally {
                    lock.writeLock().unlock();
                }
                lock.readLock().lock();
            }
            return val;
        } finally {
            lock.readLock().unlock();
        }
    }


}
