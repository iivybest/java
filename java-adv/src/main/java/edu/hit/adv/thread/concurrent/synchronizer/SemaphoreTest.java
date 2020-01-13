/**
 * @Package edu.hit.guide.java.adv.thread.concurrent.synchronizer
 * @author ivybest ivybestdev@163.com
 * @date 2016年3月29日-上午8:48:02
 */
package edu.hit.adv.thread.concurrent.synchronizer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * <p> description:
 * <br>----------------------------------------
 * <br> 信号灯
 * <br>	主要用途：并发访问量控制
 * <br>----------------------------------------
 * </p>
 * @className SemaphoreTest
 * @author ivybest ivybestdev@163.com
 * @date 2019/11/28 10:35
 * @version 1.0
 * @since 1.5+
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        new SemaphoreTest().launch();
    }

    public void launch() {
        final int customerCount = 6;
        Restaurant restaurant = new Restaurant();
        Semaphore semaphore = restaurant.getSemaphore();

        /* 线程池提供线程服务 */
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < customerCount; i++) pool.execute(new Customer(restaurant, semaphore));
        pool.shutdown();
    }

}

/**
 *  TODO(这里用一句话描述这个类的作用)
 * @author
 * Createdate    2016年3月29日 上午10:06:12
 */
class Customer implements Runnable {
    private Semaphore semaphore;
    private Restaurant restaurant;

    public Customer(Restaurant restaurant, Semaphore semaphore) {
        this.restaurant = restaurant;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            // 顾客拿到信号灯许可，方可进入饭店，否则等待有人离开饭店，才能进入
            this.semaphore.acquire();
            Thread.sleep(30 * (new Random().nextInt(9) + 1));
            System.out.println("-------->" + Thread.currentThread().getName() + " get in restaurant...");
            this.restaurant.enter(this);
            Thread.sleep(3000 * (new Random().nextInt(9) + 1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("-------->" + Thread.currentThread().getName() + " get out from restaurant...");
            this.restaurant.leave(this);
            /* 顾客离开时，释放信息 */
            this.semaphore.release();
        }
    }
}

/**
 *  TODO(这里用一句话描述这个类的作用)
 * @author
 * Createdate    2016年3月29日 上午10:07:46
 */
class Restaurant {
    // 饭店容量最大30桌顾客
    private static final int capacity = 2;
    private List<Customer> customers;
    private Semaphore semaphore;

    {
        this.semaphore = new Semaphore(capacity);
        this.customers = new CopyOnWriteArrayList<>();
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    // 顾客进入饭店
    public void enter(Customer c) {
        this.customers.add(c);
        System.out.println("==============> restaurant current customers count " + this.getCurrentCount());
    }

    // 顾客离开饭店
    public void leave(Customer c) {
        this.customers.remove(c);
        System.out.println("==============> restaurant current customers count " + this.getCurrentCount() + "\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 当前饭店桌数
    public int getCurrentCount() {
        return this.customers.size();
    }
}



