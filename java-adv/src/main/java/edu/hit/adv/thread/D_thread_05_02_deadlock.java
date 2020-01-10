package edu.hit.adv.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * <p>D_thread_05_deadlock</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年6月2日 - 上午9:25:58
 */
public class D_thread_05_02_deadlock {

    @Test
    public void test_01_deadLock() {
        CountDownLatch latch = new CountDownLatch(2);

        new Thread(new DeadLock(1, latch), "Wolcott").start();
        new Thread(new DeadLock(2, latch), "Fabregas").start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

/**
 * 死锁线程
 */
class DeadLock implements Runnable {
    private CountDownLatch latch;
    private String name;
    private int flag;

    // o1，o2 static修饰后，实例属于类，
    private static Object o1 = new Object();
    private static Object o2 = new Object();

    public DeadLock(int flag, CountDownLatch latch) {
        this.flag = flag;
        this.latch = latch;
    }

    private String getName() {
        if (name == null) {
            this.name = Thread.currentThread().getName();
        }
        return name;
    }

    @Override
    public void run() {
        try {
            this.operate();
            this.latch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
     * flag == 1 先锁定 o1 再锁定o2
     * flag == 2 先锁定 o2 再锁定o1
     * 两个线程，flag为1和2，同时执行，即可造成死锁
     */
    public void operate() throws Exception {
        System.out.println(this.getName() + ", is executing... flag = " + this.flag);
        if (flag == 1) {
            synchronized (o1) {
                Thread.sleep(1000);
                synchronized (o2) {
                    System.out.println(this.getName() + ", o2被锁定...");
                }
            }

        } else if (flag == 2) {
            synchronized (o2) {
                Thread.sleep(1000);
                synchronized (o1) {
                    System.out.println(this.name + ", o1被锁定...");
                }
            }
        }
        System.out.println(this.name + ", finished...");
    }

}