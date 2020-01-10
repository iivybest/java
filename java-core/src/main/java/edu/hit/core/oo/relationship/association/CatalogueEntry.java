package edu.hit.core.oo.relationship.association;

/**
 * <p>CatalogueEntry</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年7月27日 - 下午3:21:47
 */
public class CatalogueEntry {
    private String name;
    private int num;
    private double cost;

    public CatalogueEntry(String name, int num, double cost) {
        this.name = name;
        this.num = num;
        this.cost = cost;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }
}
