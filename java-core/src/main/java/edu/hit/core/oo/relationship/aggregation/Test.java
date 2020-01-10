package edu.hit.core.oo.relationship.aggregation;

/**
 * <p>Test</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年7月27日 - 下午4:37:50
 */
public class Test {
    public static void main(String[] args) {
        Engine e1 = new Engine("e1");
        new Train().addEngine(e1, new Engine("e2"), new Engine("e3"))
                .start();
    }
}
