package edu.hit.adv.reflect.bean;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>MyClass</p>
 * 1、包
 * 2、构造器
 * 3、继承的类，实现的接口
 * 4、域
 * 5、方法
 * 6、注解
 * 7、内部类
 *
 * @author ivybest ivybestdev@163.com
 * @date 2014-5-30 上午09:07:25
 */
@SuppressWarnings(value = "unchecked")    // 起作用与源代码阶段，不会编译到类中
@Resource
public class MyClass implements SmartAwareAble, PowerfulAwareAble {
    boolean stop;
    public int count;
    protected String name;
    private Date birthdate;
    static boolean isSleep;
    private static boolean wait;
    private static int nnd;
    private static final double WEIGHT = 2.36;
    private static MyClass instance;

    /**
     * constructor
     */
    public MyClass(int count) {
        super();
        this.count = count;
    }

    private void launch() {
    }

    protected Date getDate() {
        return null;
    }

    ;

    public static MyClass getInstance() {
        return instance;
    }

    public int increase(int detta) {
        return -1;
    }

    // 内部类
    class innerCls {
    }

}
