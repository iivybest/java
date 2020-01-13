/**
 * @Package edu.hit.guide.java.adv.concrency.timer
 * @author ivybest ivybestdev@163.com
 * @date 2016年3月24日-下午5:14:56
 */
package edu.hit.adv.thread.traditionalthread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * TraditinalTimer
 *
 * @author ivybest ivybestdev@163.com
 * @date 2016年3月24日-下午5:14:56
 * @version 1.0
 *
 */
public class TraditionalTimer {

    public static void main(String[] args) {
        // 10秒后执行一个定时任务
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("task excuted...");
            }
        }, 1000, 2000);
    }

}
