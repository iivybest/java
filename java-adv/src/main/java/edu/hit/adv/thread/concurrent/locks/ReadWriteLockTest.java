/**
 * @Package edu.hit.guide.java.adv.thread.concurrent.locks
 * @author miao.xl
 * @date 2016年3月28日-下午2:57:15
 */
package edu.hit.adv.thread.concurrent.locks;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>ReadWriteLockTest</p>
 *
 *
 * @author miao.xl
 * @date 2016年3月28日-下午2:57:15
 * @version 1.0
 *
 */
public class ReadWriteLockTest {

    public void launch() {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();

        // 三个线程去读
        for (int i = 0; i < 3; i++)
            new Thread(() -> System.out.println(Thread.currentThread().getName() + " , " + demo.getName())).start();

        // 一个线程写
        new Thread(() -> demo.setName("Rooney")).start();

        // 三个线程读
        for (int i = 0; i < 3; i++)
            new Thread(() -> System.out.println(Thread.currentThread().getName() + " , " + demo.getName())).start();
    }

    public static void main(String[] args) {
        new ReadWriteLockTest().launch();
    }

}

/*
 * ReadWriteLock
 * 多个读锁不互斥，读锁和写锁互斥，写锁与写锁互斥
 */
class ReadWriteLockDemo {
    private String name = "Rashford";
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public String getName() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " ready to read");
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void setName(String name) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " ready to write");
            Thread.sleep(300);
            this.name = name;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

}








