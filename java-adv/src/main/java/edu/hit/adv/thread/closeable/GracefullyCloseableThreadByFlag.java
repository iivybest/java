package edu.hit.adv.thread.closeable;

/**
 * @author ivybest imiaodev@163.com
 * @date 2017年10月27日 下午8:55:31
 * @version 1.0 ------------------------------------------
 * 使用标识位实现优雅的停止线程
 */
public class GracefullyCloseableThreadByFlag {

    public static void main(String[] args) {
        // 工作线程初始化
        Worker worker = new Worker();
        // 启动worker
        worker.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 关闭worker
        worker.shutdowm();
        // main线程执行完毕
        System.out.println("====> main thread finished...");
    }

    private static class Worker extends Thread {
        // volatile 线程内存可见，示例可以用作 volatile 教学
        // 任务可运行状态
        private volatile boolean status = true;

        @Override
        public void run() {
            long counter = 0;
            // 判断状态为true，并执行业务逻辑----计数器自增
            for (; this.status; counter++) ;
            // 线程业务结束--打印业务执行次数
            System.out.println("task executed count: " + counter + ", task finished...");
        }

        public void shutdowm() {
            this.status = false;
        }

    }

}
