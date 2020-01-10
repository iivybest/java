package edu.hit.adv.thread.traditionalthread.timer;

import java.util.TimerTask;

/**
 * <p>MyTimerTask</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年6月3日 - 上午9:37:46
 */
public class MyTimerTask extends TimerTask {
    private String id;

    public MyTimerTask(String id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("task " + this.id + "had executed. . .");
    }
}
