package edu.hit.adv.java8.itfs.impl;

import edu.hit.adv.java8.itfs.FlyAble;
import edu.hit.adv.java8.itfs.ThinkAble;

/**
 * <p>Dragon</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年7月19日-下午7:13:28
 */
public class Dragon implements FlyAble, ThinkAble {

    /**
     * sayHi 在FlyAble和ThinkAble中都有定义，
     * 所以需要子类重写来指定使用哪个接口中的方法
     */
    @Override
    public void sayHi() {
        ThinkAble.super.sayHi();
    }


    public static void main(String[] args) {
        Dragon dragon = new Dragon();
        dragon.sayHi();
        dragon.getLength();
        dragon.getSpeed();
    }

}
