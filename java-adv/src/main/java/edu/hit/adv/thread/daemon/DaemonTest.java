/**
 * @Filename DaemonTest
 * TODO
 * @author ivybest
 * @version V1.0
 * @date 2017年9月24日 下午4:52:40
 * @Company IB.
 * @Copyright Copyright(C) 2010-
 * All rights Reserved, Designed By ivybest
 * <p>
 * Modification History:
 * Date			Author		Version		Discription
 * --------------------------------------------------------
 * 2017年9月24日	ivybest		1.0			new create
 */
package edu.hit.adv.thread.daemon;

/**
 * @Classname DaemonTest
 *  守护线程使用示例
 * @author ivybest miao.xl@live.cn
 * @date 2017年9月24日 下午4:52:40
 * @Version 1.0
 */
public class DaemonTest {

    public static void main(String[] args) {
        Thread daemon = new Thread(() -> {
            long idx = 0;
            while (++idx > 0) {
                System.out.println(idx);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        daemon.setDaemon(true);
        daemon.start();

        try {
            /*
             * 将主线程join到主线程，
             * 主线程检查主线程是否运行完毕 主线程未执行完毕，
             * 主线程继续等待主线程执行结束 这样主线程一直不会退出。
             */
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main thread terminate completed...");
    }

}
