package edu.hit.adv.generic.user.defined.generic;

/**
 * <p>Point</p>
 * <p>Description : 对泛型参数类型进行限制</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年4月24日 - 上午11:21:29
 */
public class Point<T extends Number> {
    private T x;
    private T y;

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }
}
