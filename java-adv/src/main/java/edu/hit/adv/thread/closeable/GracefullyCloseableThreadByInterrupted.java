/**
 * @Filename CloseThreadByInterrupted
 * TODO
 * @author ivybest
 * @version V1.0
 * @date 2017年10月25日 下午6:51:06
 * @Company IB.
 * @Copyright Copyright(C) 2010-
 * All rights Reserved, Designed By ivybest
 * <p>
 * Modification History:
 * Date				Author		Version		Discription
 * --------------------------------------------------------
 * 2017年10月25日	ivybest		1.0			new create
 */
package edu.hit.adv.thread.closeable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Classname CloseThreadByInterrupted
 * @author ivybest imiaodev@163.com
 * @date 2017年10月25日 下午6:51:06
 * @Version 1.0
 * ------------------------------------------
 *  使用API interrupted 优雅的打断线程
 */
public class GracefullyCloseableThreadByInterrupted {
    private final static Logger LOGGER = LoggerFactory.getLogger(GracefullyCloseableThreadByInterrupted.class);

    public static void main(String[] args) {

        Thread task_0 = new Thread(() -> {
            for (long i = 0; ; i++) {
                // 判断线程是否被打断，若打断跳出循环
                if (Thread.interrupted()) break;
                // 业务处理逻辑
                if (i % 138953733 == 3) LOGGER.info("thread:{}, execute: {}", Thread.currentThread().getName(), i);
            }
            // 线程被打断之后，执行逻辑单元
            // 注意 当前线程interrupted之后，后续处理逻辑任然会执行，
            // 不能实现强制停止线程效果
            LOGGER.info("thread:{}, msg:{}", Thread.currentThread().getName(), "postLogic executed");
        }, "task_0");


        Thread task_1 = new Thread(() -> {
            try {
                for (int i = 0; i < 100000; i++) {
                    // 判断线程是否被打断，若打断跳出循环
                    if (Thread.interrupted()) throw new Exception(Thread.currentThread().getName() + "Interrupted");
                    // 业务处理逻辑
                    LOGGER.info("thread:{}, execute: {}", Thread.currentThread().getName(), i);
                    Thread.sleep(300);
                }
                // 线程被打断之后，执行逻辑单元
                LOGGER.info("thread:{}, msg:{}", Thread.currentThread().getName(), "postLogic executed");
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.info("thread:{}, msg:{}", Thread.currentThread().getName(), "使用exception处理方式，线程打断后不会执行后续逻辑的处理");
            }
        }, "task_1");


        task_0.start();
        task_1.start();

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        task_0.interrupt();
        task_1.interrupt();
        LOGGER.info("integer_max_value : {}", Integer.MAX_VALUE);
        LOGGER.info("long_max_value : {}", Long.MAX_VALUE);
    }

}









