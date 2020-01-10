package edu.hit.adv.thread;

import edu.hit.adv.thread.traditionalthread.ThreadDemo;
import lombok.extern.slf4j.Slf4j;
import org.ivy.util.annotation.Recommend;

import java.util.stream.IntStream;

/**
 * <p> description:
 * <br>---------------------------------------------------------
 * <br> traditional thread testcase
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 * 
 * @author Ivybest (ivybestdev@163.com)
 * @date 2015/5/15 16:39
 * @version 1.0
 */
@Slf4j
public class TraditionalThreadDemo02 {

    public static void main(String[] args) {
        IntStream.range(0, 3).forEach(e -> new ThreadDemo("thread-" + e).start());
        IntStream.range(0, 3).forEach(e -> new Thread(new RunnableDemo("runnable-" + e)).start());
        log.debug("====> {msg: {}}", "main thread executed finished...");
    }

    /**
     * <p> description:
     * <br>---------------------------------------------------------
     * <br> Thread implementation by inheritance
     * <br>---------------------------------------------------------
     * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
     * </p>
     *
     * @author Ivybest (ivybestdev@163.com)
     * @date 2020/1/6 15:01
     * @version 1.0
     */
    @Recommend(value = false, msg = {"recommended use interface runnable"})
    private static class ThreadDemo extends Thread {
        public ThreadDemo(String name) {
            this.setName(name);
        }

        @Override
        public void run() {
            this.execute();
        }

        private void execute() {
            for (int i = 0; i < 3; i++) {
                TraditionalThreadDemo02.log.debug("====>{thread: {}, name: {}, msg: {}}",
                        Thread.currentThread().getName(), this.getName(), "threadTask-" + i + "...");
            }
        }
    }

    /**
     * <p> description:
     * <br>---------------------------------------------------------
     * <br> Thread implementation by interface runnable
     * <br>---------------------------------------------------------
     * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
     * </p>
     *
     * @author Ivybest (ivybestdev@163.com)
     * @date 2020/1/6 15:02
     * @version 1.0
     */
    @Recommend
    private static class RunnableDemo implements Runnable {
        private String name;

        public RunnableDemo(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            this.execute();
        }

        private void execute() {
            for (int i = 0; i < 3; i++) {
                TraditionalThreadDemo02.log.debug("====>{thread: {}, name: {}, msg: {}}",
                        Thread.currentThread().getName(), this.name, "threadTask-" + i + "...");
            }
        }
    }
}
















