package edu.hit.adv.thread.traditionalthread.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>TimerConcrete</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015年7月20日 - 下午2:56:29
 */
public class TimerConcrete {
    private final Timer timer;
    private TimerTask task;

    private void init() {
        // 匿名内部类实现定时任务
        this.task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("innerclass implemented timer task...");
            }
        };
    }

    public TimerConcrete(String name, boolean isDeamon) {
        this.timer = new Timer(name, isDeamon);
        this.init();
    }

    public void schedule(Date date) {
        // 在指定时间执行
        this.timer.schedule(task, date);
    }

    public void schedule(Date date, long period) {
        // 从指定时间开始，每隔period执行一次
        this.timer.schedule(task, date, period);
    }

    public void schedule(long delay) {
        // 从现在起过delay毫秒执行一次
        this.timer.schedule(task, delay);
    }

    public void schedule(long delay, long period) {
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
        TimerConcrete tc = new TimerConcrete("Rojo", true);
        tc.schedule(new Date(), 1000);

    }

}
