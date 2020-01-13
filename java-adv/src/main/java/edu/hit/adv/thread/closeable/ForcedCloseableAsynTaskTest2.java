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
import org.ivy.util.common.UniSeqUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.locks.LockSupport;

/**
 * @author ivybest imiaodev@163.com
 * @date 2017年10月25日 下午6:52:07
 * @version 1.0
 * ------------------------------------------
 *  强制打断线程
 */
public class ForcedCloseableAsynTaskTest2 {
    private final static Logger LOGGER = LoggerFactory.getLogger(ForcedCloseableAsynTaskTest2.class);

    public static void main(String[] args) {
        Random random = new SecureRandom();
        new ForcedCloseableAsynTask()
                .taskName(UniSeqUtil.generateUniSeq("task1"))
                .execute(() -> {
                    for (int i = 0; i < 100; LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 1_000))
                        LOGGER.info("---->[{}]-{}", Thread.currentThread().getName(), ++i);

                })
                .callback(() -> LOGGER.info("---->task1 callback executed..."))
                .shutdown(10_000);

//		new ForcedCloseableAsynTask()
//				.taskName(UniSeqUtil.generateUniSeq("task2"))
//				.execute(() -> {
//					for(int i = 0; i < 100; LOGGER.info("---->[{}]-{}", Thread.currentThread().getName(), i++))
//						try {
//							TimeUnit.MILLISECONDS.sleep(1_000);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//				})
//				.callback(() -> LOGGER.info("---->task2 callback executed..."))
//				.shutdown(random.nextInt(3_000));

//		for(;;);
        LOGGER.info("----> main thread finished");
    }
}
