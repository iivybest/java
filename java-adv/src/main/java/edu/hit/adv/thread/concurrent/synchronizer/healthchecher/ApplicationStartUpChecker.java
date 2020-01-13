/**
 * @Package edu.hit.guide.java.adv.thread.concurrent.synchronizer.healthchecher
 * @author ivybest ivybestdev@163.com
 * @date 2016年3月30日-下午3:03:33
 */
package edu.hit.adv.thread.concurrent.synchronizer.healthchecher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>ApplicationStartUpChecker</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2016年3月30日-下午3:03:33
 */
public class ApplicationStartUpChecker {
    private static List<BaseHealthChecker> checkers;
    private static CountDownLatch latch;

    static {
        latch = new CountDownLatch(2);
        checkers = new ArrayList<>();
        checkers.add(new CacheServiceHealthChecker(latch, "CacheService_main"));
        checkers.add(new DatabaseServiceHealthChecker(latch, "DatabaseService_1"));
    }


    public static boolean check() {
        boolean valid = false;
        ExecutorService pool = Executors.newFixedThreadPool(checkers.size());
        try {
            checkers.stream().forEach(pool::execute);
            // 等待所有任务检查完毕
            latch.await();
            for (BaseHealthChecker checker : checkers) {
                if (!(valid = checker.isServiceUp())) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
        return valid;
    }

    public static void main(String[] args) {
        boolean valid = ApplicationStartUpChecker.check();
        System.out.println(valid);
    }

}
