/**
 * @Package edu.hit.guide.java.adv.thread.concurrent.synchronizer
 * @author ivybest ivybestdev@163.com
 * @date 2016年3月29日-上午8:49:42
 */
package edu.hit.adv.thread.concurrent.synchronizer;

import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * ExchangerTest
 *
 * @author ivybest ivybestdev@163.com
 * @date 2016年3月29日-上午8:49:42
 * @version 1.0
 *
 */
public class ExchangerTest {
    private static Exchanger<ExchangeData> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(new MyRunnableA(), "A").start();
        new Thread(new MyRunnableB(), "B").start();
    }

    private static class MyRunnableA implements Runnable {
        @Override
        public void run() {
            ExchangeData data = new ExchangeData("A", Thread.currentThread().getName());
            try {
                Thread.sleep((new Random().nextInt(9) + 1) * 1000);
                ExchangeData dataEx = exchanger.exchange(data);
                System.out.println(Thread.currentThread().getName() + "---->"
                        + dataEx.getType() + " , " + dataEx.getAddr());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MyRunnableB implements Runnable {
        @Override
        public void run() {
            ExchangeData data = new ExchangeData("B", Thread.currentThread().getName());
            try {
                Thread.sleep((new Random().nextInt(9) + 1) * 1000);
                ExchangeData dataEx = exchanger.exchange(data);
                System.out.println(Thread.currentThread().getName() + "---->"
                        + dataEx.getType() + " , " + dataEx.getAddr());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class ExchangeData {
    private String type;
    private String addr;

    public ExchangeData() {
    }

    public ExchangeData(String type, String addr) {
        super();
        this.type = type;
        this.addr = addr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

}
