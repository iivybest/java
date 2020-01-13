/**
 * @Package edu.hit.guide.java.adv.thread.concurrent.synchronizer.healthchecher
 * @author ivybest ivybestdev@163.com
 * @date 2016年3月30日-下午3:02:13
 */
package edu.hit.adv.thread.concurrent.synchronizer.healthchecher;

import java.util.concurrent.CountDownLatch;

/**
 * DatabaseServiceHealthChecker 数据库服务健康检查器
 *
 * @author ivybest ivybestdev@163.com
 * @date 2016年3月30日-下午3:02:13
 * @version 1.0
 *
 */
public class DatabaseServiceHealthChecker extends BaseHealthChecker {

    public DatabaseServiceHealthChecker(CountDownLatch latch, String serviceName) {
        super(latch, serviceName);
    }

    @Override
    public boolean verify() {
        System.out.println("====>" + this.getClass().getSimpleName()
                + " Checking [" + this.getServiceName() + "]");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
        return true;
    }

}
