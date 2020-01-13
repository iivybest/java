/**
 * @Package edu.hit.guide.java.adv.concrency.traditionalthread
 * @author ivybest ivybestdev@163.com
 * @date 2016年3月25日-上午9:03:15
 */
package edu.hit.adv.thread.traditionalthread;

/**
 * <p>TraditionalSynchronized</p>
 *
 *
 * @author ivybest ivybestdev@163.com
 * @date 2016年3月25日-上午9:03:15
 * @version 1.0
 *
 */
public class TraditionalSynchronized {

    private static Object MONITER = new Object();

    static class Outputer {
        public void output2() {
//			synchronized(this) {}
//			synchronized(TraditionalSynchronized.class) {}
            synchronized (MONITER) {
            }
        }

        // 等价于synchronized(this) {}
//		public synchronized void output3() {
//		}

        // 等价于synchronized(Outputer.class) { }
//		public static synchronized void output4() {
//		}
    }

}
