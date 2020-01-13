package edu.hit.adv.java8.itfs;

/**
 * <p> description: functional interface move able
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 * 
 * @author ivybest ivybestdev@163.com
 * @date 2016/7/19 19:03
 * @version 1.0
 */
public interface MoveAble {
    /**
     * interface static method sayHello
     */
    static void sayHello() {
        System.out.println(MoveAble.class.getSimpleName() + " - static method sayHello()");
    }

    /**
     * interface default method sayHi
     */
    default void sayHi() {
        System.out.println(MoveAble.class.getSimpleName() + " - default method sayHi()");
    }

    /**
     * interface default method getSpeed
     */
    default void getSpeed() {
        System.out.println(MoveAble.class.getSimpleName() + " - default method getSpeed()");
    }

    /**
     * interface default method getLength
     */
    default void getLength() {
        System.out.println(MoveAble.class.getSimpleName() + " - default method getLength()");
    }

}
