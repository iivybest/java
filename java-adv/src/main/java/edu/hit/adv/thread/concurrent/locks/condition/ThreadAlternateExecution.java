package edu.hit.adv.thread.concurrent.locks.condition;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ThreadAlternateExecution
 * @author: ivybest imiaodev@163.com
 * @date: 2019年3月3日 下午6:06:36
 * : 线程交替执行任务
 * ---------------------------------------------------------
 * 1、wait\notify + synchronized
 * 2、lock + condition
 * 3、
 * ---------------------------------------------------------
 */
public class ThreadAlternateExecution {

    public static void main(String[] args) {
        // ----打印线程数
        int threadCount = 3;
        // ----打印工作者
        Worker printWorker = new ThreadAlternateExecution().new Worker(threadCount, 0);
        // ----多个线程调用打印工作者的打印方法
        for (int i = 0; i < threadCount; i++)
            new Thread(() -> {
                for (int j = 0; j < 3; j++) printWorker.loopPrint(7);
            }, i + "").start();
    }


    /* *******************************************
     * 1、Lock + condition 控制线程间通信
     * 2、多个线程持有同一个Lock，每个线程都有自己的 condition
     * 3、Workor 通过标志 signal，控制可执行线程
     * 4、通过Map管理多个 condition，Workor的工作线程数可以任意指定
     */
    private class Worker {
        private int threadNum;
        private int signal;
        private Lock moniter;
        private Map<Integer, Condition> conditionMap;

        {
            this.moniter = new ReentrantLock();
            this.conditionMap = new HashMap<>();
        }

        public Worker(int threadNum, int firstExecuteThread) {
            this.signal = firstExecuteThread;
            this.threadNum = threadNum;
            for (int i = 0; i < this.threadNum; this.conditionMap.put(i++, this.moniter.newCondition())) ;
        }


        public void loopPrint(int count) {
            try {
                // ----0----moniter 加锁
                this.moniter.tryLock(1, TimeUnit.SECONDS);
                // ----1----检查当前线程是否与signal一致，一致则可执行，不能执行则await
                int curThreadName = Integer.valueOf(Thread.currentThread().getName());
                if (signal != curThreadName) this.conditionMap.get(curThreadName).await();
                // ----2----执行业务逻辑
                this.loopPrintBizLogic(count);
                // ----3----执行完成后，唤醒下一个线程进行业务处理
                signal = (signal == (this.threadNum - 1)) ? 0 : ++signal;
                this.conditionMap.get(signal).signal();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } finally {
                // ----4----moniter 释放锁
                this.moniter.unlock();
            }
        }

        private void loopPrintBizLogic(int count) {
            try {
                System.out.print("====>thread-[" + Thread.currentThread().getName() + "]-print====>");
                for (int i = 0; i < count; i++) {
                    System.out.print(i + "-> ");
                    Thread.sleep(80);
                }
                System.out.println();
                if (signal == (this.threadNum - 1)) System.out.println("----------------------------------------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
