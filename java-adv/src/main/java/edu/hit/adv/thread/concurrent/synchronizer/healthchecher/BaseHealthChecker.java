/**
 * @Package edu.hit.guide.java.adv.thread.concurrent.synchronizer.healthchecher
 * @author miao.xl
 * @date 2016年3月30日-下午2:44:39
 */
package edu.hit.adv.thread.concurrent.synchronizer.healthchecher;

import java.util.concurrent.CountDownLatch;

/**
 * BaseHealthChecker
 *
 * @author miao.xl
 * @date 2016年3月30日-下午2:44:39
 * @version 1.0
 *
 */
public abstract class BaseHealthChecker implements Runnable {
    private CountDownLatch latch;
    private String serviceName;
    private boolean serviceUp = false;

    public BaseHealthChecker(CountDownLatch latch, String serviceName) {
        this.latch = latch;
        this.serviceName = serviceName;
    }

    /**
     * verify
     *  检查相关服务健康状况
     * @return boolean
     *
     * @author miao.xl
     * @date 2016年3月30日-下午2:45:28
     */
    public abstract boolean verify();

    @Override
    public void run() {
        this.index();
    }

    // chacker工作过程
    private void index() {
        try {
            serviceUp = this.verify();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (latch != null) {
                latch.countDown();
            }
        }

    }


    /**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName the serviceName to set
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * @return the serviceUp
     */
    public boolean isServiceUp() {
        return serviceUp;
    }

    /**
     * @param serviceUp the serviceUp to set
     */
    public void setServiceUp(boolean serviceUp) {
        this.serviceUp = serviceUp;
    }

}




