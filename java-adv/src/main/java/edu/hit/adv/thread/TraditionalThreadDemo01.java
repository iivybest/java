package edu.hit.adv.thread;

import lombok.extern.slf4j.Slf4j;
import org.ivy.util.annotation.Recommend;
import org.ivy.util.annotation.ThreadSafe;

/**
 * <p> description:
 * <br>----------------------------------------
 * <br> TODO
 * <br>----------------------------------------
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2019/11/28 11:26
 * @since 1.8 +
 */
@Slf4j
@Recommend(value = false, msg = {"recommended using thread pool"})
@ThreadSafe
public class TraditionalThreadDemo01 {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; TraditionalThreadDemo01.log.debug(Thread.currentThread().getName() + "-" + i++));
            }
        }).start();
    }
}

