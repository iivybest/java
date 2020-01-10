/**
 * @Package edu.hit.guide.java.adv.concrency.traditionalthread
 * @author miao.xl
 * @date 2016年3月24日-下午4:48:30
 */
package edu.hit.adv.thread.traditionalthread;

/**
 * <p>TraditionalThread</p>
 *
 * @author miao.xl
 * @date 2016年3月24日-下午4:48:30
 * @version 1.0
 *
 */
public class TraditionalThread {

    public void launch() {
        new InnerThread().start();        // 启动线程
    }

    // 成员内部类
    // 线程子类实现线程
    private class InnerThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        new TraditionalThread().launch();
    }

}




















