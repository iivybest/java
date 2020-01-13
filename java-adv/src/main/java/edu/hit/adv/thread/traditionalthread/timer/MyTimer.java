package edu.hit.adv.thread.traditionalthread.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>MyTimer</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015年6月3日 - 上午8:45:55
 */
public class MyTimer {
    private Timer timer;

    public MyTimer(String name, boolean isDeamon) {
        this.timer = new Timer(name, isDeamon);
    }

    public void schedule(TimerTask task, Date date) {
        // 在指定时间执行
        this.timer.schedule(task, date);
    }

    public void schedule(TimerTask task, Date date, long period) {
        // 从指定时间开始，每隔period执行一次
        this.timer.schedule(task, date, period);
    }

    public void schedule(TimerTask task, long delay) {
        // 从现在起过delay毫秒执行一次
        this.timer.schedule(task, delay);
    }

    public void schedule(TimerTask task, long delay, long period) {
        // 从现在起过delay毫秒以后，每隔period 毫秒执行一次。
        this.timer.schedule(task, delay, period);
    }

    public void cancel() {
        // 终止此计时器，丢弃所有当前已安排的任务。
        this.timer.cancel();
    }

    public int purge() {
        // 从此计时器的任务队列中移除所有已取消的任务。
        return this.timer.purge();
    }


    public static void main(String[] args) {
        MyTimer timer = new MyTimer("Ronaldo", true);
        MyTimerTask task1 = new MyTimerTask("1...");
        MyTimerTask task2 = new MyTimerTask("2...");
        MyTimerTask task3 = new MyTimerTask("3...");
        MyTimerTask task4 = new MyTimerTask("4...");

        timer.schedule(task1, new Date());
        timer.schedule(task2, new Date(), 600);
        timer.schedule(task3, 2000);
        timer.schedule(task4, 2000, 1200);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
    }


}
