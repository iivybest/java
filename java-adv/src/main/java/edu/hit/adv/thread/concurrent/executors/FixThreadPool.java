package edu.hit.adv.thread.concurrent.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>FixThreadPool</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * <p>
 * 1、Executor 负责线程使用和调度的根接口
 * |----ExecutorService 线程池主要接口
 * |----ThreadPoolExecutor 实现类
 * |----ScheduledExecutorService 负责接口调度
 * |----ScheduledThreadPoolExecutor 继承 ThreadPoolExecutor，实现了ScheduledExecutorService
 * <p>
 * 2、Use factory method from Executors 创建线程池
 * ExecutorService newFixedThreadPool();				// 固定大小线程池
 * ExecutorService newCachedThreadPool();				// 缓存线程池，线程数不固定
 * ExecutorService newSingleThreadExecutor();			// 单线程线程池
 * ScheduledExecutorService newScheduledThreadPool();	// 固定大小线程池，延迟或定时执行任务
 * @date 2016年3月28日-上午11:57:11
 */
public class FixThreadPool {

    public void launch() {
        // 创建一个固定长度的线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);
//		Executors.newCachedThreadPool();
//		Executors.newSingleThreadExecutor();
//		Executors.newScheduledThreadPool(3);

        for (int i = 0; i < 10; i++) pool.execute(new OptRunnable(i));

        // 结束线程池
        pool.shutdown();
    }

    public static void main(String[] args) {
        new FixThreadPool().launch();
    }
}

class OptRunnable implements Runnable {
    private int taskNum;

    public OptRunnable(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " execute task " + this.taskNum);
    }
}




