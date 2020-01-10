package edu.hit.core.oo.relationship.association;

/**
 * <p>Part</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年7月27日 - 下午3:24:26
 */
public class Part {
    private CatalogueEntry entry;

    public Part(CatalogueEntry entry) {
        this.entry = entry;
    }

    public double cost() {
        return this.entry.getCost();
    }
}
