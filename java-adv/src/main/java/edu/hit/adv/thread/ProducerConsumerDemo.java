package edu.hit.adv.thread;

import java.util.Stack;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * <p> description: a demo of producer and consumer
 * <br>---------------------------------------------------------
 * <br> 厨师制作馒头后放入篮子中，顾客从篮子中拿馒头来吃
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * @date 2015/6/2 14:36
 */
public class ProducerConsumerDemo {

    private int countProducer = 2;
    private int countConsumer = 5;
    private ExecutorService serviceP = new ThreadPoolExecutor(2, 2,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            r -> new Thread("p-"),
            new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) {
        ProducerConsumerDemo instance = new ProducerConsumerDemo();

        // 篮子，馒头容器
        Basket basket = new Basket();

        // 2个厨师做馒头
        new Thread(new Producer(basket), "````厨子_1").start();
        new Thread(new Producer(basket), "````厨子_2").start();

        // 5个吃货吃馒头
        for (int i = 1; i <= 4; i++) {
            new Thread(new Consumer(basket), "----吃货_" + i).start();
        }
    }


    /**
     * @author ivybest imiaodev@163.com
     * @Classname SteamedBread
     * @date 2017年11月1日 下午12:41:44
     * @Version 1.0 ------------------------------------------
     * 馒头
     */
    private static class SteamedBread {
        private String id;

        public SteamedBread(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "[SteamedBread:" + this.id + "]";
        }
    }

    /**
     * @author ivybest imiaodev@163.com
     * @Classname Basket
     * @date 2017年11月1日 下午12:42:08
     * @Version 1.0 ------------------------------------------
     * 篮子
     */
    private static class Basket {
        private int max;
        private Stack<SteamedBread> stack;

        public Basket() {
            this.stack = new Stack<SteamedBread>();
            this.max = 20;
        }

        public Basket(int size) {
            this();
            this.max = size;
        }

        /**
         * push
         * 放入
         *
         * @param item
         * @return void
         */
        public synchronized void push(SteamedBread item) {
            while (stack.size() >= max) {
                /*
                 * 当前的正在该对象上访问的线程wait，必须synchronized，才能wait
                 * wait后，必须notify叫醒，而且wait后锁不在归该线程所有
                 * sleep是有锁的
                 */
                try {
                    System.out.println("Basket is full....");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 叫醒一个正在wait在该对象上的线程
            this.notifyAll();
            this.stack.push(item);
        }

        /**
         * pop
         * 取出
         *
         * @return SteamedBread
         */
        public synchronized SteamedBread pop() {
            while (stack.size() <= 0) {
                try {
                    System.out.println("Basket is empty....");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.notifyAll();
            return stack.pop();
        }

        public synchronized int getSize() {
            return this.stack.size();
        }

    }

    /**
     * @author ivybest imiaodev@163.com
     * @Classname Producer
     * @date 2017年11月1日 下午12:38:55
     * @Version 1.0 ------------------------------------------
     * 生产者
     */
    private static class Producer implements Runnable {
        private int num = 0;
        private Basket basket;
        private boolean stop;

        public Producer(Basket basket) {
            this.basket = basket;
            this.stop = false;
        }

        public void setStop(boolean stop) {
            this.stop = stop;
        }

        @Override
        public void run() {
            while (!stop) this.produceFlow();
        }

        /**
         * produceFlow
         * 生成流程，制作一个馒头，然后放入篮子中
         *
         * @return void
         */
        private void produceFlow() {
            this.pushSteamedBread(this.createSteamedBread());
            System.out.println(Thread.currentThread().getName() + " made" + ++num
                    + ", basket " + this.basket.getSize());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (num >= 20) this.setStop(true); // 每人只做20个馒头
        }

        // 生产馒头
        private SteamedBread createSteamedBread() {
            return new SteamedBread(UUID.randomUUID().toString());
        }

        // 放置馒头
        private void pushSteamedBread(SteamedBread item) {
            this.basket.push(item);
        }
    }

    /**
     * @author ivybest imiaodev@163.com
     * @Classname Consumer
     * @date 2017年11月1日 下午12:37:54
     * @Version 1.0 ------------------------------------------
     * 消费者
     */
    private static class Consumer implements Runnable {
        private int num = 0;
        private boolean stop;

        private Basket basket;

        public Consumer(Basket basket) {
            super();
            this.basket = basket;
            this.stop = false;
        }

        public void setStop(boolean stop) {
            this.stop = stop;
        }

        @Override
        public void run() {
            while (!stop) this.eat(this.popSteamedBread());
        }

        private SteamedBread popSteamedBread() {
            return this.basket.pop();
        }

        private void eat(SteamedBread item) {
            System.out.println(Thread.currentThread().getName() + " eat " + ++num + item
                    + " , basket " + this.basket.getSize());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (num >= 10) this.setStop(true);
        }

    }

}









