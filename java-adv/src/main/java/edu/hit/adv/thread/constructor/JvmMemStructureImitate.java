/**
 * Filename 	JvmMemStructureImitate
 * TODO
 *
 * @author ivybest
 * @version V1.0
 * CreateDate 	2017年9月18日 下午7:40:35
 * Company 		IB.
 * Copyright 	Copyright(C) 2010-
 * All rights Reserved, Designed By ivybest
 * <p>
 * Modification History:
 * Date			Author		Version		Discription
 * --------------------------------------------------------
 * 2017年9月18日	ivybest		1.0			new create
 */
package edu.hit.adv.thread.constructor;

/**
 * @Classname JvmMemStructureImitate
 *  jvm 内存结构模拟
 * @author ivybest
 * Createdate 	2017年9月18日 下午7:40:35 
 */
public class JvmMemStructureImitate {
    // Method Area
    private static int counter = 0;
    // reference in Method Area, object in HeapMem
    // reference hold 4 byte (32bit)
    private byte[] bytes = new byte[1024];

    /*
     * JVM will create a thread named main
     * create a jvm stack
     */
    public static void main(String[] args) {
        // Local Stack Frame 局部变量表
        int j = 0;
        // reference in Local Stack Frame
        // object in Heap memory
        int[] arrays = new int[1024];

        new Thread(
                // ThreadGroup
                null,
                // Target
                () -> {
                    try {
                        increse(counter);
                    } catch (Throwable e) {
                        e.printStackTrace();
                        System.out.println(counter);
                    }
                },
                // Thread name
                "Thread-sub",
                // Stack size,值越大，栈桢深度越大，但是JVM创建线程个数越少
                1 << 24).start();


    }

    public static void increse(int i) {
        ++counter;
        increse(++i);
    }
}









