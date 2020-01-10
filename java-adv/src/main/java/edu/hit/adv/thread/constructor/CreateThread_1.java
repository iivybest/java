package edu.hit.adv.thread.constructor;

/**
 * @author ivybest
 */
public class CreateThread_1 {

    private static int counter;

    public static void main(String[] args) {
        try {
            // 不断压栈，导致栈溢出
            // main的栈帧大小不可改变，自定义线程栈帧大小可设置。
            add(counter);
        } catch (Error e) {
            // counter 可以理解为栈帧的深度
            System.out.println(counter);
            e.printStackTrace();
        }

    }

    /**
     * 写一个死循环压栈操作，而没有出口，导致栈被占满，抛出错误，
     */
    public static void add(int i) {
        add(counter++);
    }

}
