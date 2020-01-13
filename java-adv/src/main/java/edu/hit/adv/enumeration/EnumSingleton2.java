package edu.hit.adv.enumeration;

/**
 * <p> description: enumeration singleton case2
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2019/12/30 7:20
 */
public class EnumSingleton2 {

    /**
     * get instance
     *
     * @return EnumSingleton2
     */
    public static EnumSingleton2 getInstance() {
        return InstanceHolder.HOLDER.getInstance();
    }

    /**
     * enumeration InstanceHolder
     *
     * @author ivybest ivybestdev@163.com
     * @version 1.0
     * @date 2019/12/30 7:21
     */
    private enum InstanceHolder {
        /**
         * Singleton instance holder
         */
        HOLDER;

        private EnumSingleton2 instance;

        InstanceHolder() {
            instance = new EnumSingleton2();
        }

        public EnumSingleton2 getInstance() {
            return this.instance;
        }
    }
}







