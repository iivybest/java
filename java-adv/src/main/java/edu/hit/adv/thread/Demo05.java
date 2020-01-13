package edu.hit.adv.thread;

import lombok.extern.slf4j.Slf4j;
import org.ivy.util.common.DateTimeUtil;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.concurrent.locks.LockSupport;

/**
 * <p> description:
 * <br>---------------------------------------------------------
 * <br> the testcase of traditional thread
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015/6/1 17:49
 */
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Demo05 {

    @Test
    public void test_01_unsync() {
        Timer timer = new Timer();
        Thread ronaldo = new Thread(new RunnableDemo(timer, 1), "Ronaldo");
        Thread beckham = new Thread(new RunnableDemo(timer, 1), "Beckham");
        ronaldo.start();
        beckham.start();

        this.finalized(ronaldo, beckham);
    }

    @Test
    public void test_02_sync() {
        Timer timer = new Timer();
        Thread rooney = new Thread(new RunnableDemo(timer, 2), "Rooney");
        Thread falcao = new Thread(new RunnableDemo(timer, 2), "Falcao");
        rooney.start();
        falcao.start();
        this.finalized(rooney, falcao);
    }

    @Test
    public void test_03_sync() {
        Timer timer = new Timer();
        Thread rooney = new Thread(new RunnableDemo(timer, 3), "Rooney");
        Thread falcao = new Thread(new RunnableDemo(timer, 3), "Falcao");
        rooney.start();
        falcao.start();
        this.finalized(rooney, falcao);
    }

    @Test
    public void test_04_sync() {
        Timer timer = new Timer();
        Thread rooney = new Thread(new RunnableDemo(timer, 4), "Rooney");
        Thread falcao = new Thread(new RunnableDemo(timer, 4), "Falcao");
        rooney.start();
        falcao.start();
        this.finalized(rooney, falcao);
    }

    /**
     * *********************************************
     * 解决 Junit 测试多线程退出问题
     * Junit线程结束，所有线程会结束，
     * 将此方法加到方法后面，阻止 Junit 线程提前结束
     *
     * @param ts thread
     */
    private void finalized(Thread... ts) {
        boolean isAlive = true;
        start:
        while (isAlive) {
            for (int i = 0; i < ts.length; i++) {
                if (ts[i].isAlive()) {
                    continue start;
                }
            }
            isAlive = false;
        }
    }

    private static class RunnableDemo implements Runnable {
        private Timer timer;
        private int type;

        public RunnableDemo(Timer timer, int type) {
            this.timer = timer;
            this.type = type;
        }

        @Override
        public void run() {
            this.operate();
        }

        private void operate() {
            if (type == 1) {
                timer.op_1(Thread.currentThread().getName());
            } else if (type == 2) {
                timer.op_2(Thread.currentThread().getName());
            } else if (type == 3) {
                timer.op_3(Thread.currentThread().getName());
            } else if (type == 4) {
                timer.op_4(Thread.currentThread().getName());
            }
        }
    }

    private static class Timer {
        private int num;

        public void op_1(String name) {
            this.op(name);
        }

        public void op_2(String name) {
            synchronized (this) {
                this.op(name);
            }
        }

        public synchronized void op_3(String name) {
            this.op(name);
        }

        public void op_4(String name) {
            synchronized (Timer.class) {
                this.op(name);
            }
        }

        private void op(String name) {
            ++num;
            // sleep 5 mills
            LockSupport.parkUntil(DateTimeUtil.getTimestamp(null) + 5);
            log.debug("{msg: {}, 第 {} 个使用 timer 线程}", name, this.num);
        }
    }
}





