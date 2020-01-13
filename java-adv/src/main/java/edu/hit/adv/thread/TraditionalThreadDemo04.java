package edu.hit.adv.thread;

import lombok.extern.slf4j.Slf4j;
import org.ivy.util.annotation.Description;
import org.ivy.util.annotation.Recommend;
import org.ivy.util.common.DateTimeUtil;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;
import java.util.concurrent.locks.LockSupport;

/**
 * <p> description: traditional thread test
 * <br>---------------------------------------------------------
 * <br> sleep()     线程休眠
 * <br> interrupt() 打断线程
 * <br> 线程 sleep 时被 interrupt，会抛出 InterruptedException，
 * <br> 抛出异常后 return，可以停止线程 ---
 * <br> 不推荐使用这种方式停止线程 ----
 * <br>
 * <br>
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015/6/1 10:12
 */
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TraditionalThreadDemo04 {

    @Test
    public void test_01_SleepAndInterrupt() {
        Thread thread = new Thread(new ThreadDemo01(1), "t-1");
        thread.start();

        // interrupt thread after 3 mills
        LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 3000);
        thread.interrupt();

        LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 1000);
        log.debug("{thread: {}, isAlive: {}}", thread.getName(), thread.isAlive());

        LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 1000);
    }

    @Test
    @Description("// ----> stop thread demo ----")
    @Recommend(false)
    public void test_01_SleepAndInterruptAndReturn() {
        Thread thread = new Thread(new ThreadDemo01(2), "t-1");
        thread.start();

        // interrupt thread after 3 mills
        LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 3000);
        thread.interrupt();

        LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 1000);
        log.debug("{thread: {}, isAlive: {}}", thread.getName(), thread.isAlive());

        LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 1000);
    }

    @Test
    public void test_01_LockSupportAndInterrupt() {
        Thread thread = new Thread(new ThreadDemo01(3), "t-1");
        thread.start();

        // interrupt thread after 3 mills
        LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 3000);
        thread.interrupt();

        LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 1000);
        log.debug("{thread: {}, isAlive: {}}", thread.getName(), thread.isAlive());

        LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 1000);
    }

    @Test
    public void test_02_stopThread2() {
        ThreadDemo02 mt = new ThreadDemo02();
        Thread t = new Thread(mt);
        t.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mt.setStop(true);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug(t.getName() + "是否活着 ---- " + t.isAlive());
    }

    // test, join 将线程合并到当前线程
    @Test
    public void test_03_join() {
        ThreadDemo03 nani = new ThreadDemo03("nani");
        Thread t = new Thread(nani);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("main线程内容会在t执行完后再执行。。。");
    }

    // test, 测试yield
    @Test
    public void test_04_yield() {
        ThreadDemo04 messi = new ThreadDemo04("Messi");
        ThreadDemo04 kaka = new ThreadDemo04("kaka");

        messi.start();
        kaka.start();

        // Junit测试，若测试线程关闭，其他线程会停止，顾添加下面代码
        boolean isAlive = true;
        while (isAlive) {
            isAlive = !(!messi.isAlive() && !kaka.isAlive());
        }
    }

    // test, priority
    @Test
    public void test_05_priority() {
        ThreadDemo05 figo = new ThreadDemo05("Figo");
        ThreadDemo05 zidane = new ThreadDemo05("Zinedine·Yazid·Zidane");

        figo.setPriority(Thread.NORM_PRIORITY + 3);
        figo.start();
        zidane.start();

        boolean isAlive = true;
        while (isAlive) {
            isAlive = !(!figo.isAlive() && !zidane.isAlive());
        }
    }


    private static class ThreadDemo01 implements Runnable {
        private int type;

        public ThreadDemo01 (int type) {
            this.type = type;
        }

        @Override
        public void run() {
            if (1 == type) opWithSleep();
            else if (2 == type) opWithSleepAndReturn();
            else if (3 == type) opWithLockSupportParkUntil();
        }


        private void opWithSleep() {
            while (true) {
                log.debug("{====>{}, ===={}====}", Thread.currentThread().getName(), DateTimeUtil.formate(new Date()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        private void opWithSleepAndReturn() {
            while (true) {
                log.debug("{====>{}, ===={}====}", Thread.currentThread().getName(), DateTimeUtil.formate(new Date()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.debug("{====>{}, ===={}====}", Thread.currentThread().getName(), "return");
                    return;
                }
            }
        }

        private void opWithLockSupportParkUntil() {
            while (true) {
                log.debug("{====>{}, ===={}====}", Thread.currentThread().getName(), DateTimeUtil.formate(new Date()));
                LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 1000);
            }
        }


    }

    // 线程类2
    private static class ThreadDemo02 implements Runnable {
        private boolean isStop;    // 是否停止线程

        public ThreadDemo02() {
            isStop = false;
        }

        @Override
        public void run() {
            while (!isStop) {
                operate();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (isStop)
                log.debug("------ " + Thread.currentThread().getName()
                        + " 线程结束");
        }

        private void operate() {
            log.debug(Thread.currentThread().getName() + ", ==== "
                    + new Date() + " ==== ");
        }

        public void setStop(boolean isStop) {
            this.isStop = isStop;
        }
    }

    // 线程类，测试join
    private static class ThreadDemo03 implements Runnable {
        private boolean isStop; // 是否停止线程
        private String name;    // 线程名称

        public ThreadDemo03(String name) {
            super();
            this.isStop = false;
            this.name = name;
        }

        @Override
        public void run() {
            int i = 0;
            while (!isStop) {
                operate();
                // 执行三次后设置停止标志
                if (++i == 3) this.setStop(true);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (isStop) log.debug("---- " + this.name + " 线程结束");
        }

        private void operate() {
            log.debug(this.name + ", ==== " + new Date() + " ==== ");
        }

        public void setStop(boolean isStop) {
            this.isStop = isStop;
        }

        public String getName() {
            return name;
        }
    }

    //线程类，测试yield
    private static class ThreadDemo04 extends Thread {
        private boolean isStop; // 是否停止线程

        public ThreadDemo04(String name) {
            super(name);
            this.isStop = false;
        }

        @Override
        public void run() {
            int i = 0;
            while (!isStop) {
                // 执行10000次后设置停止标志
                if (++i == 10000) this.setStop(true);
                if (i % 100 == 0) this.operate(i);
                // 执行到 10 的整数倍时，让出线程执行权
                if (i % 1000 == 0) yield();
            }
            if (isStop) log.debug("---- " + this.getName() + " 线程结束");
        }

        private void operate(int i) {
            log.debug(this.getName() + ", ==== " + i + " ==== ");
        }

        public void setStop(boolean isStop) {
            this.isStop = isStop;
        }
    }

    //线程类，测试setPriority
    private static class ThreadDemo05 extends Thread {
        private boolean isStop; // 是否停止线程

        public ThreadDemo05(String name) {
            super(name);
            this.isStop = false;
        }

        @Override
        public void run() {
            int i = 0;
            while (!isStop) {
                if (++i == 1000) this.setStop(true);
                operate(i);
            }
            if (isStop) log.debug("---- " + this.getName() + " 线程结束");
        }

        private void operate(int i) {
            log.debug(this.getName() + ", ==== " + i + " ==== ");
        }

        public void setStop(boolean isStop) {
            this.isStop = isStop;
        }
    }
}





