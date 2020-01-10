/**
 * @Package edu.hit.guide.java.adv.concrency.traditionalthread
 * @author miao.xl
 * @date 2016年3月25日-上午9:13:53
 */
package edu.hit.adv.thread.traditionalthread;

/**
 * ThreadDemo
 * 两个线程对同一对象加锁，使两个线程交替执行
 *
 * @author miao.xl
 * @date 2016年3月25日-上午9:13:53
 * @version 1.0
 *
 */
public class ThreadDemo {

    private final int count = 50;

    public void launch() {
        MyThread sub = new MyThread("sub", this.count);
        Thread subThread = new Thread(sub);
        subThread.start();
        for (int i = 0; i < this.count; i++) {
            synchronized (sub) {
                for (int j = 0; j < 15; j++) {
                    System.out.print("main" + "->" + (j + 1) + ", ");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void index() {
        final MyThread2 mt = new MyThread2();
        final int size = this.count;

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < size; i++) mt.subBiz(10);
            }
        }.start();

        for (int i = 0; i < size; i++) mt.mainBiz(15);
    }


    public static void main(String[] args) {
//		new ThreadDemo().launch();
        new ThreadDemo().index();
    }
}

class MyThread implements Runnable {
    private int num;
    private String name;

    public MyThread(String name, int num) {
        this.name = name;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.num; i++) {
            synchronized (this) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(this.name + "->" + (j + 1) + ", ");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


/*
 * 标准实现
 * 两个线程交替执行，使用wait、notify机制（需要对方法加snychronized锁）
 */
class MyThread2 {
    private boolean isSub = true;    // 自己管理自己的状态，不让外部线程去管理

    public synchronized void mainBiz(int num) {
        try {
            // while if 都可以
            // while 更好，可以防止spurious wakeup
            while (isSub) this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.biz("main", num);
        this.isSub = true;
        this.notify();
    }

    public synchronized void subBiz(int num) {
        try {
            while (!isSub) this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.biz("subb", num);
        this.isSub = false;
        this.notify();
    }

    private void biz(String name, int num) {
        for (int j = 0; j < num; j++) {
            System.out.print(name + "->" + (j + 1) + ", ");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

}









