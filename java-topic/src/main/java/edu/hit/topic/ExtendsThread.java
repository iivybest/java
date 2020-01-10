package edu.hit.topic;

// No.1
// 开始写代码，设计firsecondThreadThread类和secondThread两个线程，
// 使其继承Thread类并且实现一个线程每次对j加2，另一个线程每次对j减1
class FirstThread extends Thread {
    private ExtendsThread thread;
    private boolean stop;

    public FirstThread(ExtendsThread thread) {
        this.thread = thread;
        this.stop = false;
    }
    @Override
    public void run() {
        while (!stop) {
            this.thread.increase2();
        }
    }
}

class SecondThread extends Thread {
    private ExtendsThread thread;
    private boolean stop;

    public SecondThread(ExtendsThread thread) {
        this.thread = thread;
        this.stop = false;
    }
    @Override
    public void run() {
        while (!stop) {
            this.thread.sub();
        }
    }
}

// end_code
public class ExtendsThread {
    // No.2
    // 开始写代码，给两个线程上锁
    private int j;
    private int type = 1;

    public synchronized void increase2() {
        if (type == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        this.j += 2;
        this.type = 0;
        System.out.println("+2 " + j);
    }

    public synchronized void sub() {
        if (type == 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        this.j--;
        this.type = 1;
        System.out.println("-1 " + j);
    }

    // end_code
    public static void main(String[] args) {
        ExtendsThread extendsThread = new ExtendsThread();
        FirstThread firstThread = new FirstThread(extendsThread);
        SecondThread secondThread = new SecondThread(extendsThread);
        firstThread.start();
        secondThread.start();
    }

}