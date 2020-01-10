/**
 * @Filename ThreadService
 * @author ivybest
 * @version V1.0
 * @date 2018年7月25日 下午5:10:31
 * @Company IB.
 * @Copyright Copyright(C) 2010-
 * All rights Reserved, Designed By ivybest
 * <p>
 * Modification History:
 * Date				Author		Version		Discription
 * --------------------------------------------------------
 * 2018年7月25日	ivybest		1.0			new create
 */
package edu.hit.adv.thread.closeable;

/**
 * @Classname ThreadService
 * @author ivybest imiaodev@163.com
 * @date 2018年7月25日 下午5:10:31
 * @Version 1.0
 * ------------------------------------------
 *  TODO(这里用一句话描述这个类的作用)
 */
public class ThreadService {

    private Thread executeThread;
    private volatile boolean finished;

    public ThreadService execute(Runnable task) {
        this.executeThread = new Thread() {
            @Override
            public void run() {
                Thread daemon = new Thread(task);
                daemon.setDaemon(true);
                daemon.start();
                try {
                    daemon.join();
                    ThreadService.this.finished = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("====> {" + Thread.currentThread().getName() + "} finished");
                }
            }
        };
        this.executeThread.start();
        return this;
    }

    public ThreadService shutdown(long mills) {
        long currentTime = System.currentTimeMillis();
        while (!this.finished) {
            if (System.currentTimeMillis() - currentTime >= mills) {
                this.executeThread.interrupt();
                System.out.println("====> {} interrupted by watcher thread");
                break;
            }
        }
        return this;
    }
}
