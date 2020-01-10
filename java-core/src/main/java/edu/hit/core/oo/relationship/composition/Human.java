package edu.hit.core.oo.relationship.composition;

/**
 * <p>Human</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年7月28日 - 上午9:22:39
 */
public class Human {
    private Head head;
    private Body body;
    private Arm lArm;
    private Arm rArm;
    private Leg lLeg;
    private Leg rLeg;

    public Human() {
        this.initialize();
    }

    private void initialize() {
        this.head = new Head();
        this.body = new Body();
        this.lArm = new Arm();
        this.rArm = new Arm();
        this.lLeg = new Leg();
        this.rLeg = new Leg();
    }

}

class Head {
}

class Body {
}

class Arm {
}

class Leg {
}
