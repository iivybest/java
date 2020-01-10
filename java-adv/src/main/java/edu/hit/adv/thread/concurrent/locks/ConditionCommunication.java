/**
 * @Package edu.hit.guide.java.adv.thread.concurrent.locks
 * @author miao.xl
 * @date 2016年3月28日-下午4:14:52
 */
package edu.hit.adv.thread.concurrent.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>ConditionCommunication</p>
 * <p>
 * 	condition	锁的通信机制，类似 wait 和 notify, 比wait notify 方式更灵活
 * </p>
 *
 * @author miao.xl
 * @date 2016年3月28日-下午4:14:52
 * @version 1.0
 *
 */
public class ConditionCommunication {

    public void launch() {
        final MyBiz biz = new MyBiz();
        final int count = 10;

        // 一个线程处理主过程
        new Thread(() -> {
            for (int i = 0; i < count; i++) biz.mainProcess();
        }).start();
        // 一个线程处理子过程
        new Thread(() -> {
            for (int i = 0; i < count; i++) biz.subProcess();
        }).start();
    }


    public static void main(String[] args) {
        new ConditionCommunication().launch();
    }
}


class MyBiz {
    private Lock lock;
    private Condition condition;
    private boolean isSbu;

    MyBiz() {
        this.init();
    }

    private void init() {
        this.lock = new ReentrantLock();
        this.condition = this.lock.newCondition();
        this.isSbu = true;
    }

    public void mainProcess() {
        lock.lock();
        try {
            while (this.isSbu) this.condition.await();
            this.biz("main", 9);
            this.isSbu = true;
            this.condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void subProcess() {
        lock.lock();
        try {
            while (!this.isSbu) {
                try {
                    this.condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.biz("sub", 4);
            this.isSbu = false;
            this.condition.signal();
        } finally {
            lock.unlock();
        }
    }

    private void biz(String name, int num) {
        for (int j = 0; j < num; j++) {
            System.out.print(name + "->" + (j + 1) + ", ");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

}
