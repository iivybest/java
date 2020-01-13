package edu.hit.adv.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * <p> description:
 * <br>---------------------------------------------------------
 * <br> traditional thread testcase
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @date 2015/6/1 9:57
 * @version 1.0
 */
@Slf4j
public class TraditionalThreadDemo03 {

    @Test
    public void test_01_currentThread() {
        Thread t = Thread.currentThread();
        log.debug("{\nid: {}, \nname: {}, \nisAlive: {}, \npriority: {}, \nstate: {}, \nisDaemon: {}, \nisInterrupted: {}}",
                t.getId(),
                t.getName(),
                t.isAlive(),
                t.getPriority(),
                t.getState(),
                t.isDaemon(),
                t.isInterrupted()
        );
    }
}
