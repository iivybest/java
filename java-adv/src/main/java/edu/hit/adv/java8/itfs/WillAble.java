package edu.hit.adv.java8.itfs;

/**
 * <p>意志力，念力</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2016年7月19日-下午7:33:31
 */
public interface WillAble extends MoveAble, ThinkAble {

    /**
     * parent interface MoveAble、ThinkAble both have default Method sayHi
     * so this interface must override method sayHi
     */
    @Override
    default void sayHi() {
        System.out.println(WillAble.class.getSimpleName() + " - default method sayHi()");
    }

}
