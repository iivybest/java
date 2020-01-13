package edu.hit.adv.java8.itfs;

/**
 * <p>ThinkAble</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2016年7月19日-下午7:04:01
 */
public interface ThinkAble {
    /**
     * interface default method sayHi
     */
    default void sayHi() {
        System.out.println(ThinkAble.class.getSimpleName() + " - default method sayHi()");
    }
    /**
     * interface default method think
     */
    default void think() {
        System.out.println(ThinkAble.class.getSimpleName() + " - default method think()");
    }


}
