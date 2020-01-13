/**
 * @Filename ForcedCloseableAsynTask
 * @author ivybest ivybestdev@163.com
 * @version V1.0
 * @date 2017年10月27日 下午12:25:16
 * @Company IB.
 * @Copyright Copyright(C) 2010-
 * All rights Reserved, Designed By ivybest
 * <p>
 * Modification History:
 * Date				Author		Version		Discription
 * --------------------------------------------------------
 * 2017年10月27日	ivybest		1.0			new create
 */
package edu.hit.adv.thread.closeable;

import org.ivy.util.common.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @author ivybest imiaodev@163.com
 * @date 2017年10月27日 下午12:25:16
 * @version 1.0
 * ------------------------------------------
 *  可强制停止的异步任务
 */
public class ForcedCloseableAsynTask {
    private final static Logger LOGGER = LoggerFactory.getLogger(ForcedCloseableAsynTask.class);

    // 当前任务线程
    private volatile Thread currentTask;
    // 当前任务观察线程
    private volatile Thread watcher;
    // 回调任务
    private volatile Thread callbackTask;
    // 当前任务结束标志
    private volatile AtomicBoolean finished = new AtomicBoolean(false);
    // 当前任务名称
    private String taskName;
    // 当前任务名称默认自增序列
    private static int sequence;
    // 任务超时时长
    private Long timeout;
    // 扫描时间间隔
    private Long interval = 2L << 1;

    public ForcedCloseableAsynTask execute(Runnable task, String taskName, Runnable callback, long mills) {
        this.taskName(taskName).execute(task).callback(callback).shutdown(mills);
        return this;
    }

    public ForcedCloseableAsynTask execute(Runnable task, Runnable callback, long mills) {
        this.execute(task).callback(callback).shutdown(mills);
        return this;
    }

    public ForcedCloseableAsynTask execute(Runnable task, String taskName, long mills) {
        this.taskName(taskName).execute(task, mills);
        return this;
    }

    public ForcedCloseableAsynTask execute(Runnable task, long mills) {
        this.execute(task).shutdown(mills);
        return this;
    }

    public ForcedCloseableAsynTask execute(Runnable task, String taskName) {
        this.taskName(taskName).execute(task);
        return this;
    }

    /**
     *  		execute
     * @param        task
     * @param        taskName
     * @return ForcedCloseableAsynTask
     *
     * <br>-------------------------------------------
     * 		1、设置当前任务为 task，并执行
     * <br>-------------------------------------------
     */
    public ForcedCloseableAsynTask execute(Runnable task) {
        this.currentTask = new Thread(new ForcedCloseableAsynTask.Executer(task), this.taskName());
        this.currentTask.start();
        return this;
    }

    /**
     *  		callback
     *  为当前任务设置回调函数线程任务
     * @param        target
     * @return ForcedCloseableAsynTask
     */
    public ForcedCloseableAsynTask callback(Runnable target) {
        this.callbackTask = new Thread(() -> {
            // ----线程阻塞----等待任务线程执行完毕
            for (; !this.finished.get(); LockSupport.parkUntil(DateTimeUtil.getTimestamp() + this.interval)) ;
            // ----执行回调逻辑
            if (ForcedCloseableAsynTask.this.finished.get()) {
                Thread t = new Thread(target, this.taskName + "#callback");
                t.setDaemon(true);
                t.start();
                try {
                    t.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, this.taskName + "#callback#Mgr");
        this.callbackTask.start();
        return this;
    }

    /**
     *  		shutdown
     *  在指定的时间内结束当前任务
     * @param        mills
     * @return ForcedCloseableAsynTask
     */
    /**
     * : shutdown
     * : 在指定的时间内结束当前任务, 并在结束前执行target任务
     * @param mills
     * @param target
     * @return ForcedCloseableAsynTask
     */
    @SuppressWarnings("static-access")
    public ForcedCloseableAsynTask shutdown(long mills, Runnable target) {
        this.timeout = mills;
        long beginTimestamp = DateTimeUtil.getTimestamp();
        this.watcher = new Thread(() -> {
            while (!ForcedCloseableAsynTask.this.finished.get()) {
                if (DateTimeUtil.getTimestamp() - beginTimestamp >= this.timeout) {
                    ForcedCloseableAsynTask.this.currentTask.interrupt();
                    LOGGER.error("====>[{}]-interrupted_by-[{}]...",
                            ForcedCloseableAsynTask.this.currentTask.getName(),
                            Thread.currentThread().getName());
                    break;
                }
                // 当前执行线程睡眠1毫秒
                LockSupport.parkUntil(DateTimeUtil.getTimestamp() +1);
//                try {
//                    ForcedCloseableAsynTask.this.watcher.sleep(1);
//                } catch (Exception e) {
//                    LOGGER.error("====>{} interrupted by [{}]",
//                            ForcedCloseableAsynTask.this.currentTask.getName(),
//                            Thread.currentThread().getName());
//                }
            }
        }, this.taskName() + "#watcher");
        this.watcher.setDaemon(true);
        this.watcher.start();
        return this;
    }

    /**
     *  		shutdown
     *  在指定的时间内结束当前任务
     * @param        mills
     * @return ForcedCloseableAsynTask
     */
    @SuppressWarnings("static-access")
    public ForcedCloseableAsynTask shutdown(long mills) {
        this.timeout = mills;
        long beginTimestamp = DateTimeUtil.getTimestamp();
        this.watcher = new Thread(() -> {
            while (!ForcedCloseableAsynTask.this.finished.get()) {
                if (DateTimeUtil.getTimestamp() - beginTimestamp >= this.timeout) {
                    ForcedCloseableAsynTask.this.currentTask.interrupt();
                    LOGGER.error("====>[{}] interrupted by [{}]...",
                            ForcedCloseableAsynTask.this.currentTask.getName(),
                            Thread.currentThread().getName());
                    break;
                }
                // 当前执行线程睡眠1毫秒
                LockSupport.parkUntil(DateTimeUtil.getTimestamp() +1);
            }
        }, this.taskName() + "#watcher");
        this.watcher.setDaemon(true);
        this.watcher.start();
        return this;
    }

    /**
     *  		shutdown
     *  立刻打断当前任务
     * @return
     * @return ForcedCloseableAsynTask
     */
    public ForcedCloseableAsynTask shutdown() {
        if (!this.finished.get()) this.currentTask.interrupt();
        return this;
    }

    /**
     *  		taskName
     *  获取当前任务名称
     * @return String
     */
    public String taskName() {
        if (this.taskName == null || this.taskName.trim().length() <= 0) {
            synchronized (ForcedCloseableAsynTask.class) {
                if (this.taskName == null || this.taskName.trim().length() <= 0)
                    this.taskName = "FCAT_" + sequence++;
            }
        }
        return this.taskName;
    }

    /**
     *  		taskName
     *  设置当前任务名称
     * @param        taskName
     * @return ForcedCloseableAsynTask
     */
    public ForcedCloseableAsynTask taskName(String taskName) {
        this.taskName = taskName;
        return this;
    }


    /**
     * @author ivybest imiaodev@163.com
     * @date 2017年10月27日 下午12:46:52
     * @version 1.0
     * ------------------------------------------
     *  任务执行线程，封装任务处理逻辑单元
     */
    private class Executer implements Runnable {
        private Runnable target;

        public Executer(Runnable target) {
            this.target = target;
        }

        @Override
        public void run() {
            Thread daemonWorker = new Thread(target, Thread.currentThread().getName() + "#daemon");
            daemonWorker.setDaemon(true);
            daemonWorker.start();

//			boolean flag = false;
//			while (! (flag = ForcedCloseableAsynTask.this.currentTask.isInterrupted())) {
//				LockSupport.parkUntil(DateTimeUtil.getTimestamp() + 4);
//				ForcedCloseableAsynTask.LOGGER.error("====>{}-{}-{}", "#e-watcher-listen", 
//					flag, ForcedCloseableAsynTask.this.interval);
//			}
//			daemonWorker.stop();

            try {
                daemonWorker.join();
                LOGGER.info("====>[{}]-finished", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // 任务执行线程被打断，结束,daemon 线程也就结束了，实现暴力结束任务
                LOGGER.error("====>task:{}, timeout:{}, msg:{}",
                        Thread.currentThread().getName(),
                        ForcedCloseableAsynTask.this.timeout,
                        "interrupted forced by watcher thread");
            }
            // 任务执行结束后，将标志置为true
            ForcedCloseableAsynTask.this.finished.set(true);
        }

    }

}
