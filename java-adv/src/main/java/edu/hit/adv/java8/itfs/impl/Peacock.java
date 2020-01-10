package edu.hit.adv.java8.itfs.impl;

import edu.hit.adv.java8.itfs.FlyAble;
import edu.hit.adv.java8.itfs.MoveAble;

/**
 * <p>Peacock</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年7月19日-下午7:26:22
 */
public class Peacock implements MoveAble, FlyAble {

    public static void main(String[] args) {
        Peacock peacock = new Peacock();
        peacock.sayHi();
        peacock.getSpeed();

        MoveAble.sayHello();
    }
}
