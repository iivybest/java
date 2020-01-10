/**
 * @Package edu.hit.guide.java.adv.thread.concurrent.callable$future
 * @author miao.xl
 * @date 2016年3月28日-下午2:00:28
 */
package edu.hit.adv.thread.concurrent.callable$future;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.cms.Time;
import org.ivy.util.common.DateTimeUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * CallableAndFuture
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年3月28日-下午2:00:28
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class CallableAndFutureTest {

    private ExecutorService pool;

    @Before
    public void setUp() {
        this.pool = Executors.newFixedThreadPool(3);
    }

    @After
    public void tearDown() {
        if(this.pool != null) {
            this.pool.shutdown();
        }
    }

    /**
     * 利用 Future 对象，拿到线程执行的结果
     */
    @Test
    public void test_01_callableAndFuture() {
        Future<String> future = this.pool.submit(new MyCallable(45));
        try {
            log.info("===={thread: {}, msg: {}}", Thread.currentThread().getName(), "wait final result");
            // 阻塞式等待取回，timeout 之后抛出异常
            String result = future.get(1, TimeUnit.SECONDS);
            log.info("===={thread: {}, msg: {}}", Thread.currentThread().getName(), result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            log.info("===={thread: {}, msg: {}}", Thread.currentThread().getName(), "future get timeout");
        }
        log.info("===={thread: {}, msg: {}}", Thread.currentThread().getName(), "testcase finished");
    }

    /**
     * CompletionService 执行一组线程，哪个线程先执行完，先拿到该线程的执行结果
     * 就象割麦子，那块地先熟，先割那块地
     */
    @Test
    public void test_02_completionService() {
        int taskCount = 10;
        CompletionService<String> cs = new ExecutorCompletionService<>(pool);

        for (int i = 0; i < taskCount; i++) {
            cs.submit(new MyCallable(i));
        }

        try {
            // 获得任务结果，哪个线程先完成，先得到哪个结果
            for (int i = 0; i < taskCount; i++) {
                Future<String> future = cs.take();
                log.info("===={msg: {}}", future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * futureTask 替代 future、callable 用法
     */
    @Test
    public void test_03_FutureTask() {
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable(3));
        this.pool.execute(futureTask);
        try {
            log.info("===={msg: {}}", "wait final result");
            String result = futureTask.get(3, TimeUnit.SECONDS);
            log.info("===={msg: {}}", result);
        } catch (TimeoutException e) {
            e.printStackTrace();
            log.info("===={msg: {}}", "future get timeout");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }



    /**
     * <p> description:
     * <br>---------------------------------------------------------
     * <br> Callable task for testcase
     * <br>---------------------------------------------------------
     * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
     * </p>
     *
     * @author Ivybest (ivybestdev@163.com)
     * @version 1.0
     * @date 2020/1/10 9:11
     */
    private static class MyCallable implements Callable<String> {
        private int taskNum;

        public MyCallable(int taskNum) {
            this.taskNum = taskNum;
        }

        @Override
        public String call() {
            LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 900);
            return "====taskNum_" + this.taskNum + "-execute";
        }
    }
}


