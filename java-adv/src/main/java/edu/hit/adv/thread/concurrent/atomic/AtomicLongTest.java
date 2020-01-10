/**
 * @Filename AtomicLongTest
 * @author Ivybest
 * @version V1.0
 * @date 2018年1月23日 上午9:53:52
 * @Company IB.
 * @Copyright Copyright(C) 2010-
 * All rights Reserved, Designed By Ivybest
 * <p>
 * Modification History:
 * Date				Author		Version		Discription
 * --------------------------------------------------------
 * 2018年1月23日	Ivybest			1.0			new create
 */
package edu.hit.adv.thread.concurrent.atomic;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Classname AtomicLongTest
 * @author Ivybest imiaodev@163.com
 * @date 2018年1月23日 上午9:53:52
 * @Version 1.0
 * ------------------------------------------
 *  TODO(这里用一句话描述这个类的作用)
 */
public class AtomicLongTest {
    private AtomicLong version;

    @Before
    public void setUp() {
        version = new AtomicLong(0L);
    }

    @Test
    public void test_01() {
        final CountDownLatch latch = new CountDownLatch(100);
        final CountDownLatch whistle = new CountDownLatch(1);


        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    System.out.println("----> [" + Thread.currentThread().getId() + "], is waiting for whistle...");
                    whistle.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("----> [" + Thread.currentThread().getId() + "], " + version.getAndIncrement());
                latch.countDown();
            }).start();
        }

        try {
            // 吹哨
            Thread.sleep(3000);
            whistle.countDown();
            // 等待所有工作线程完成
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(this.version.get());

    }


}
