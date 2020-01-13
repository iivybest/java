/**
 * @Package edu.hit.guide.java.adv.thread.concurrent.locks
 * @author ivybest ivybestdev@163.com
 * @date 2016年3月28日-下午2:36:58
 */
package edu.hit.adv.thread.concurrent.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>LockTest</p>
 *
 *
 * @author ivybest ivybestdev@163.com
 * @date 2016年3月28日-下午2:36:58
 * @version 1.0
 *
 */
public class LockTest {

    public void launch() {
        final MyLockDemo demo = new MyLockDemo();
        new Thread() {
            @Override
            public void run() {
                demo.increase();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                demo.decrease();
            }
        }.start();
    }

    public static void main(String[] args) {
        LockTest test = new LockTest();
        test.launch();
    }

}

/*
 * 给互斥操作加Lock,
 */
class MyLockDemo {
    private int i = 100;
    private Lock lock = new ReentrantLock();

    public void increase() {
        lock.lock();
        try {
            Thread.sleep(1500);
            i++;
            System.out.println(Thread.currentThread().getName() + ", increase " + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrease() {
        lock.lock();
        try {
            Thread.sleep(500);
            i--;
            System.out.println(Thread.currentThread().getName() + ", decrease " + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}

