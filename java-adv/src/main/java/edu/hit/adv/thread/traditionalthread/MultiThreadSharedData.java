/**
 * @Package edu.hit.guide.java.adv.concrency.demo
 * @author miao.xl
 * @date 2016年3月28日-上午9:36:29
 */
package edu.hit.adv.thread.traditionalthread;

/**
 * <p>MultiThreadSharedData</p>
 * 多线程共享数据，并执行同步操作
 *		1、若每个线程执行方法相同，可使用同一个Runnable对象共享数据
 *		2、若每个线程执行方法不同，将共享数据封装一个对象，每个操作对应一个Runnable对象
 *		3、对2进行改造，Runnable对象添加标志位，不同标志位对应不同操作。
 * @author miao.xl
 * @date 2016年3月28日-上午9:36:29
 * @version 1.0
 *
 */
public class MultiThreadSharedData {
    /**
     *  		strategy_1
     *  1、若每个线程执行方法相同，可使用同一个Runnable对象共享数据
     * 				买票程序
     * 				叫号程序
     * @return void
     */
    public void strategy_1() {
        Runnable ticketsRunnable = new Runnable() {
            private int tickets = 100;

            @Override
            public void run() {
                synchronized (this) {
                    tickets--;
                    System.out.println(Thread.currentThread().getName() + "买票后，剩余票数 " + tickets);
                }
            }
        };
        // 十个顾客进行买票
        for (int i = 0; i < 10; i++) new Thread(ticketsRunnable, "customer-" + i).start();
    }

    /**
     *  		strategy_2
     *  2、若每个线程执行方法不同，将共享数据封装一个对象，每个操作对应一个Runnable对象
     * @return void
     */
    public void strategy_2() {
        ThreadSharedData data = new ThreadSharedData();
        OptRunnable1 opt1Runnable = new OptRunnable1(data);
        OptRunnable2 opt2Runnable = new OptRunnable2(data);
        new Thread(opt1Runnable).start();
        new Thread(opt2Runnable).start();
    }

    /**
     *  		strategy_3
     *  3、对2进行改造，Runnable对象添加标志位，不同标志位对应不同操作。
     * @return void
     */
    public void strategy_3() {
        ThreadSharedData data = new ThreadSharedData();
        new Thread(new MyOptRunnable(data, 1)).start();
        new Thread(new MyOptRunnable(data, 2)).start();
    }

    public static void main(String[] args) {
        MultiThreadSharedData mtsd = new MultiThreadSharedData();
        mtsd.strategy_1();
        mtsd.strategy_2();
        mtsd.strategy_3();
    }

}


// 线程之间共享数据实体类
class ThreadSharedData {
    private int i = 100;

    // 增加操作
    public synchronized void increase() {
        ++i;
        System.out.println(Thread.currentThread().getName() + " increase " + i);
    }

    // 减少操作
    public synchronized void decrease() {
        --i;
        System.out.println(Thread.currentThread().getName() + " decrease " + i);
    }
}

// 共享数据操作1 Runnabled 对象
class OptRunnable1 implements Runnable {
    private ThreadSharedData data;

    public OptRunnable1(ThreadSharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        this.data.increase();
    }
}

//共享数据操作2 Runnabled 对象
class OptRunnable2 implements Runnable {
    private ThreadSharedData data;

    public OptRunnable2(ThreadSharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        this.data.decrease();
    }
}

// 对 Runnable 增加 标志位，不同标志对应不同操作
class MyOptRunnable implements Runnable {
    private int optType;
    private ThreadSharedData data;
    private ThreadSharedDataHandler handler;

    public MyOptRunnable(ThreadSharedData data, int optType) {
        this.data = data;
        this.optType = optType;
        this.handler = new ThreadSharedDataHandler(this.data, this.optType);
    }

    @Override
    public void run() {
        this.handler.doService();
    }

}

// 共享数据处理类
class ThreadSharedDataHandler {
    private ThreadSharedData data;
    private int optType;

    public ThreadSharedDataHandler(ThreadSharedData data, int optType) {
        this.data = data;
        this.optType = optType;
    }

    public void doService() {
        if (1 == this.optType) this.increase();
        if (2 == this.optType) this.decrease();
    }

    // 增加操作
    private void increase() {
        this.data.increase();
    }

    // 减少操作
    private void decrease() {
        this.data.decrease();
    }

}




