/**
 * @Filename ForcedCloseableAsynTaskTest
 * TODO
 * @author ivybest ivybestdev@163.com
 * @version V1.0
 * @date 2017年10月25日 下午6:52:07
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

import org.ivy.util.common.DateTimeUtil;
import org.ivy.util.common.RandomSeqUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.locks.LockSupport;


/**
 * <p> description: ForcedCloseableAsynTaskTest
 * <br>---------------------------------------------------------
 * <br> 强制打断线程
 * <br> 所有的非 daemon 线程都结束后，jvm 才会退出 daemon 线程
 * <br>
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @date 2017/10/25 18:52
 * @version 1.0
 */
public class ForcedCloseableAsynTaskTest {
    private final static Logger log = LoggerFactory.getLogger(ForcedCloseableAsynTaskTest.class);
    private static String prefix = "(*^_^*)-";
    private static Random random = new SecureRandom();

    public static void main(String[] args) throws InterruptedException {
        // -----任务名称------构造一个随机任务名称
        final String taskname = prefix + "task-" + RandomSeqUtil.generateRandomHex(2);
        // ----任务结束时间----构造一个10-15 seconds 的随机结束时间
        final long shutdowntime = 10_000 + random.nextInt(5) * 1_000;

        // ---- 等待若干秒再 进入测试流程，便于启动监控程序
        LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 8_000);
        log.info("---->{}-shutdowntme-{}", taskname, shutdowntime);
        log.info("---->testcase begin...");
        LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 1_000);

        // ---- 异步任务方式启动可结束异步任务
        new Thread(() -> {
            new ForcedCloseableAsynTask()
                    .taskName(taskname)
                    .execute(() -> {
                        for (int i = 0; i < 100; i++) {
                            log.info("---->[{}]-{}", Thread.currentThread().getName(), i);
                            LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 1_000);
                        }
                    })
                    .callback(() -> log.info("---->{} callback task executed...", taskname))
                    .shutdown(shutdowntime);

//			LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 1_000);
        }, prefix + "anonymous").start();

        // ---- 路人甲，模拟无关的线程任务
//		new Thread(() -> LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 32_000), prefix + "stranger").start();

        // ---- main 线程，最大等待时间，便于查看监控系统
//		LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 16_000);
        log.info("---->main thread finished");
    }
}





