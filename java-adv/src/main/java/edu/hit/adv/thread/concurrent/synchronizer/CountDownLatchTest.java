package edu.hit.adv.thread.concurrent.synchronizer;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。
 * 每当一个线程完成了自己的任务后，计数器的值就会减1。
 * 当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。
 */
public class CountDownLatchTest {
    public static final int RUNNER_COUNT = 6;

    public static void main(String[] args) {
        // 模拟裁判鸣哨
        CountDownLatch whistle = new CountDownLatch(1);
        // 模拟所有运动员撞线
        CountDownLatch breastTape = new CountDownLatch(RUNNER_COUNT);

        for (int i = 1; i <= RUNNER_COUNT; i++) {
            new Thread(new Runner("runner_" + i, whistle, breastTape)).start();
        }
        new Thread(new Judgment(whistle, breastTape)).start();
    }
}

/*
 * 运动员
 * 持有两个CountDownLatch，
 * 运动员等待 whistle  countDown 为 0 ， 相当于裁判鸣哨
 * 运动员撞线后，将 breastTape countDown，便于裁判统计
 */
class Runner implements Runnable {
    private String name;
    private CountDownLatch whistle;    // 裁判鸣哨
    private CountDownLatch breastTape;    // 运动员撞线

    public Runner(String name, CountDownLatch whistle, CountDownLatch breastTape) {
        this.name = name;
        this.whistle = whistle;
        this.breastTape = breastTape;
    }

    @Override
    public void run() {
        try {
            System.out.println("====>" + this.name + " is ready...");
            // 等待裁判鸣哨
            whistle.await();
            // 裁判鸣哨后，运动员进入比赛
            this.sporting();
            // 运动员撞线后，应答裁判
            breastTape.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sporting() {
        System.out.println("====>" + Thread.currentThread().getName() + ", " + this.name + " is runing");
        try {
            Thread.sleep(((long) (new Random().nextInt(9) + 1) * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("====>" + Thread.currentThread().getName()
                + ", " + this.name
                + " arrive the finish line--[" + (System.currentTimeMillis() / 1000) + "]");
    }

}

/*
 * 裁判员
 * 持有两个CountDownLatch，
 * 裁判员鸣哨 ，将 whistle countDown 为 0，此时运动员起跑
 * 所有运动员撞线后，breastTape countDown 为0，裁判终止比赛
 */
class Judgment implements Runnable {
    private CountDownLatch whistle;    // 裁判鸣哨
    private CountDownLatch breastTape;    // 运动员撞线


    public Judgment(CountDownLatch whistle, CountDownLatch breastTape) {
        this.whistle = whistle;
        this.breastTape = breastTape;
    }

    @Override
    public void run() {
        try {
            // 裁判等待所有运动员就绪
            Thread.sleep(1000);
            System.out.println("--------> all runners are ready...");
            Thread.sleep(1000);
            // 裁判鸣哨，比赛开始
            this.whistle();
            // 裁判等待所有运动员撞线
            this.breastTape.await();
            // 裁判结束比赛
            this.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 裁判员鸣哨，开始比赛
    public void whistle() {
        System.out.println("-------->judgment whistle, competition begin--[" + (System.currentTimeMillis() / 1000) + "]");
        this.whistle.countDown();
    }

    // 裁判员结束比赛
    public void finish() {
        System.out.println("-------->judgment whistle, competition finished...");
    }

}



