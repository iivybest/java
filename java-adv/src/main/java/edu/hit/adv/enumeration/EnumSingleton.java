package edu.hit.adv.enumeration;

/**
 * <p>EnumSingleton</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年7月27日-下午4:03:17
 */
public enum EnumSingleton {
    /* 利用枚举实现单例模式 */
    instance;

    public void operate() {
        System.out.println("枚举式单例示例操作方法");
    }


    public static void main(String[] args) {
        EnumSingleton.instance.operate();
    }
}







