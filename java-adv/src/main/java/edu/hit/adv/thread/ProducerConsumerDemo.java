package edu.hit.adv.thread;

import java.util.Stack;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p> description: a demo of producer and consumer
 * <br>---------------------------------------------------------
 * <br> 厨师制作馒头后放入篮子中，顾客从篮子中拿馒头来吃
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015/6/2 14:36
 */
public class ProducerConsumerDemo {

    private static int countProducer = 2;
    private static int countConsumer = 5;

    private static AtomicInteger counterP = new AtomicInteger();
    private static AtomicInteger counterC = new AtomicInteger();

    private static ExecutorService serviceP = new ThreadPoolExecutor(2, 2,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            r -> new Thread("p-" + counterP.incrementAndGet()),
            new ThreadPoolExecutor.AbortPolicy());

    private static ExecutorService serviceC = new ThreadPoolExecutor(5, 5,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            r -> new Thread("c-" + counterC.incrementAndGet()),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        ProducerConsumerDemo instance = new ProducerConsumerDemo();

        // 篮子，馒头容器
        Basket basket = new Basket();

        // 2个厨师做馒头
        serviceP.execute(new Producer(basket));
        serviceP.execute(new Producer(basket));

        // 5个吃货吃馒头
        for (int i = 1; i <= 4; i++) {
//            new Thread(new Consumer(basket), "----吃货_" + i).start();
            serviceP.execute(new Consumer(basket));
        }
    }


    /**
     * @author ivybest imiaodev@163.com
     * @date 2017年11月1日 下午12:41:44
     * @version 1.0 ------------------------------------------
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
     * 篮子
     *
     * @author ivybest imiaodev@163.com
     * @date 2017年11月1日 下午12:42:08
     * @version 1.0
     * ------------------------------------------
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
         * push 放入
         *
         * @param item item
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
         * pop 取出
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
     * 生产者
     *
     * @author ivybest imiaodev@163.com
     * @date 2017年11月1日 下午12:38:55
     * @version 1.0
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
            if (num >= 20) {
                // 每人只做20个馒头
                this.setStop(true);
            }
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
     * 消费者
     *
     * @author ivybest imiaodev@163.com
     * @date 2017年11月1日 下午12:37:54
     * @version 1.0
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
            while (!stop) {
                this.eat(this.popSteamedBread());
            }
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
            if (num >= 10) {
                this.setStop(true);
            }
        }

    }

}









