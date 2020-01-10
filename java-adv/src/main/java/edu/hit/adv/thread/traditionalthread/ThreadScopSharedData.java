/**
 * @Package edu.hit.guide.java.adv.concrency.traditionalthread
 * @author miao.xl
 * @date 2016年3月25日-下午2:50:00
 */
package edu.hit.adv.thread.traditionalthread;

/**
 * <p>ThreadScopSharedData</p>
 * 线程范围内共享数据 通过ThreadLocal实现
 * 典型案例：transaction 
 * 		status = connection.beginTransaction();
 * 		connection.commit(status);
 * 		commection.rollback(status);
 *
 * @author miao.xl
 * @date 2016年3月25日-下午2:50:00
 * @version 1.0
 *
 */
public class ThreadScopSharedData {

    public static void main(String[] args) {
        ThreadScopSharedData bean = new ThreadScopSharedData();
        new Thread(bean.new MyThread("nani")).start();
        new Thread(bean.new MyThread("veho")).start();
    }

    // 模拟模块A
    static class A {
        public void getThreadLoacalEntity() {
            System.out.println(Thread.currentThread().getName() + " -> "
                    + CurrentThreadSharedData.getCurrentThreadInstance().getName() + ", "
                    + CurrentThreadSharedData.getCurrentThreadInstance().getAddr());
        }
    }

    // 模拟模块B
    static class B {
        public void getThreadLoacalEntity() {
            System.out.println(Thread.currentThread().getName() + " -> "
                    + CurrentThreadSharedData.getCurrentThreadInstance().getName() + ", "
                    + CurrentThreadSharedData.getCurrentThreadInstance().getAddr());
        }
    }

    // 线程
    class MyThread implements Runnable {
        private String msg;

        public MyThread(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            CurrentThreadSharedData.getCurrentThreadInstance().setAddr(this.msg).setName(this.msg);
            // 当前线程其他模块条用 线程共享数据对象
            new ThreadScopSharedData.A().getThreadLoacalEntity();
            new B().getThreadLoacalEntity();
        }
    }
}

// 当前线程共享数据实体类
class CurrentThreadSharedData {
    private String name;
    private String addr;

    // THreadLocal 相当于一个map，key是currentThread，value是相应实体对象
    private static ThreadLocal<CurrentThreadSharedData> threadScopData =
            new ThreadLocal<CurrentThreadSharedData>();

    // 获取当前线程的ThreadLoacalEntity对象
    public static synchronized CurrentThreadSharedData getCurrentThreadInstance() {
//		CurrentThreadSharedData instance = threadScopData.get();
//		if(instance == null) {
//			instance = new CurrentThreadSharedData();
//			threadScopData.set(instance);
//		}
//		return instance;
        if (threadScopData.get() == null) threadScopData.set(new CurrentThreadSharedData());
        return threadScopData.get();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public CurrentThreadSharedData setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return the addr
     */
    public String getAddr() {
        return addr;
    }

    /**
     * @param addr the addr to set
     */
    public CurrentThreadSharedData setAddr(String addr) {
        this.addr = addr;
        return this;
    }

}












