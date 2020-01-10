package edu.hit.adv.java8.itfs;

/**
 * <p>FlyAble</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年7月19日-下午7:03:36
 */
public interface FlyAble extends MoveAble {
    /**
     * interface default method sayHi
     */
    @Override
    default void sayHi() {
        System.out.println(FlyAble.class.getSimpleName() + " - default method sayHi()");
    }
}
