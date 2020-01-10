package edu.hit.adv.thread.concurrent.locks;

import edu.hit.adv.unsafe.UnsafeUtil;
import org.ivy.util.common.DateTimeUtil;
import sun.misc.Unsafe;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @ClassName: LockPerformanceComparison
 * @author: ivybest imiaodev@163.com
 * @date: 2019年5月20日 上午10:49:35
 * : TODO(用一句话描述该文件做什么)
 */
public class LockPerformanceComparison {

    public static void main(String[] args) {
        new LockPerformanceComparison().launch();
    }

    private LockPerformanceComparison launch() {
        List<Counter> counters = Arrays.asList(
                new StupidCounter(),
                new SynchronizedCounter(),
                new LockCounter(),
                new AtomicCounter(),
                new CasCounter()
        );

        // ----工作线程数
        final int threadCount = 1000;
        // ----每个工作线程的自增次数
        final int count = 100;

        counters.forEach(e -> {
            ExecutorService service = Executors.newFixedThreadPool(threadCount);
            long start = DateTimeUtil.getTimestamp();

//			for (int j = 0; j < threadCount; j++ ) service.submit(new CounterRunnable(e, count));
            IntStream.range(0, threadCount).forEach(i -> service.submit(new CounterRunnable(e, count)));

            try {
                service.shutdown();
                service.awaitTermination(1, TimeUnit.HOURS);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            long end = DateTimeUtil.getTimestamp();
            System.out.printf("%8s", "[" + e.getClass().getSimpleName() + "]-result : ");
            System.out.println(e.getCount() + ", take up time : " + (end - start) + " mills");
        });
        return this;
    }

    private interface Counter {
        void increment();

        void increment(int flag);

        long getCount();
    }

    private class CounterRunnable implements Runnable {
        private final Counter counter;
        private final int num;

        public CounterRunnable(Counter counter, int num) {
            super();
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            // ----线程任务：调用 Counter 实例 num 次，完成 num 次自增
//			IntStream.range(0, this.num).forEach( e -> this.counter.increment());
            IntStream.range(0, this.num).forEach(this.counter::increment);
        }

    }

    private class StupidCounter implements Counter {
        private long counter = 0;

        @Override
        public void increment() {
            this.counter++;
        }

        @Override
        public void increment(int flag) {
            this.increment();
        }

        @Override
        public long getCount() {
            return this.counter;
        }
    }

    private class SynchronizedCounter implements Counter {
        private long counter = 0;

        @Override
        public synchronized void increment() {
            this.counter++;
        }

        @Override
        public void increment(int flag) {
            this.increment();
        }

        @Override
        public long getCount() {
            return this.counter;
        }
    }

    private class LockCounter implements Counter {
        private final Lock lock = new ReentrantLock();
        private long counter = 0;

        @Override
        public void increment() {
            lock.lock();
            try {
                this.counter++;
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void increment(int flag) {
            this.increment();
        }

        @Override
        public long getCount() {
            return this.counter;
        }
    }

    private class AtomicCounter implements Counter {
        private AtomicLong counter = new AtomicLong();

        @Override
        public void increment() {
            this.counter.incrementAndGet();
        }

        @Override
        public void increment(int flag) {
            this.increment();
        }

        @Override
        public long getCount() {
            return this.counter.get();
        }
    }

    private class CasCounter implements Counter {
        private volatile long counter;
        private Unsafe unsafe = UnsafeUtil.getUnsafe();
        private long offset;

        public CasCounter() {
            try {
                offset = this.unsafe.objectFieldOffset(CasCounter.class.getDeclaredField("counter"));
            } catch (NoSuchFieldException | SecurityException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void increment() {
            long current = this.counter;
            while (!unsafe.compareAndSwapLong(this, offset, current, current + 1))
                current = this.counter;
        }

        @Override
        public void increment(int flag) {
            this.increment();
        }

        @Override
        public long getCount() {
            return this.counter;
        }
    }
}












