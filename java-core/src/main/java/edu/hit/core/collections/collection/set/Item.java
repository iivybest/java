package edu.hit.core.collections.collection.set;

/**
 * <p>Item</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年7月14日 - 上午9:49:50
 */
public class Item {
    private static native void registerNatives();

    static {
        registerNatives();
    }

    @Override
    public native int hashCode();

    public static void main(String[] args) {
        Item item = new Item();
        System.out.println(item.hashCode());
    }


}
