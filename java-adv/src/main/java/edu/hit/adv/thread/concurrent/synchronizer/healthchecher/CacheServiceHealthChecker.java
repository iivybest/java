/**
 * @Package edu.hit.guide.java.adv.thread.concurrent.synchronizer.healthchecher
 * @author miao.xl
 * @date 2016年3月30日-下午2:58:18
 */
package edu.hit.adv.thread.concurrent.synchronizer.healthchecher;

import java.util.concurrent.CountDownLatch;

/**
 * CacheServiceHealthChecker 缓存服务健康状况检查器
 *
 * @author miao.xl
 * @date 2016年3月30日-下午2:58:18
 * @version 1.0
 *
 */
public class CacheServiceHealthChecker extends BaseHealthChecker {

    public CacheServiceHealthChecker(CountDownLatch latch, String serviceName) {
        super(latch, serviceName);
    }

    @Override
    public boolean verify() {
        System.out.println("====>" + this.getClass().getSimpleName()
                + " Checking [" + this.getServiceName() + "]");
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
        return true;
    }

}
