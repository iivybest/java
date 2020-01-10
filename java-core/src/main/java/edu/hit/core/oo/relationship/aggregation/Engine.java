package edu.hit.core.oo.relationship.aggregation;

/**
 * <p>Engine</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年7月23日 - 下午5:35:37
 */
public class Engine {
    private String id;

    public Engine(String id) {
        this.id = id;
    }

    public void start() {
        System.out.println("engine " + this.id + " start");
    }

    ;
}
