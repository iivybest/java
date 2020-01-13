package edu.hit.adv.thread.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.management.StringValueExp;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.stream.IntStream;

/**
 * <p> description:
 * <br>---------------------------------------------------------
 * <br>
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2020/1/10 13:10
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class AtomicIntegerTest {
    private volatile int count;

    @Before
    public void setUp() {
        this.count = 100;
    }

    @Test
    public void test_01_baseOpt() {
        // ---- construct a atomic integer with a initial value
        AtomicInteger counter = new AtomicInteger(this.count);

        log.debug("{opt: {}, val: {}}", "getAndIncrement()", counter.getAndIncrement());
        log.debug("{opt: {}, val: {}}", "incrementAndGet()", counter.incrementAndGet());
        log.debug("{opt: {}, val: {}}", "getAndAdd(1)", counter.getAndAdd(1));
        log.debug("{opt: {}, val: {}}", "getAndAdd(-3)", counter.getAndAdd(-3));
        log.debug("{opt: {}, val: {}}", "getAndDecrement()", counter.getAndDecrement());

        counter.set(this.count);
        log.debug("{opt: {}, val: {}}", "set(100)", counter.get());
        /*
         * getAndAccumulate 用户指定数据更新操作 (x, y) -> x + y
         * x                 current value
         * y                 delta value
         * (x, y) -> x + y   expect value
         */
        log.debug("{opt: {}, val: {}}", "counter.getAndAccumulate(1, (x, y) -> x + y)",
                counter.getAndAccumulate(1, (x, y) -> x + y));
        log.debug("{opt: {}, val: {}}", "counter.accumulateAndGet(1, (x, y) -> x + y)",
                counter.accumulateAndGet(1, (x, y) -> x + y));
    }

    @Test
    public void test_02_optByUpdater() {
        log.debug("{position: {}, count: {}}", "01", this.count);
        // 通过 updater 原子性更新某个对象的某个 int 类型 field。
        AtomicIntegerFieldUpdater<AtomicIntegerTest> updater =
                AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerTest.class, "count");

        int countVal = updater.addAndGet(this, 11);
        log.debug("{position: {}, count: {}}", "02", this.count);
        updater.accumulateAndGet(this, 11, (x, y) -> (x - y) * y);
        log.debug("{position: {}, count: {}}", "03", this.count);
    }




}














