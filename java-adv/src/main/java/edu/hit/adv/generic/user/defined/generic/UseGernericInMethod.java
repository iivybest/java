package edu.hit.adv.generic.user.defined.generic;

import java.util.Vector;

/**
 * <p>UseGernericInMethod</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015年4月24日 - 上午10:38:03
 */
public class UseGernericInMethod {
    /**
     * 在方法中使用泛型
     */
    public static <T> void overview(Vector<T> v) {
        /*
         * v 中T是去确定类型，所以此处v不可以添加元素，可以删除
         */
        if (v != null) for (T t : v) System.out.println(t);
    }

    public static void main(String[] args) {
        Vector<String> v = new Vector<String>();
        v.addElement("VanGal");
        v.addElement("Ferguson");
        overview(v);
    }
}
