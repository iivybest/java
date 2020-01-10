package edu.hit.adv.java8.functionalinterface;

/**
 * <p>CreateAble</p>
 * <p>
 * **********************************************************
 *
 * @author miao.xl
 * @version 1.0
 * @FunctionalInterface 使用这个注解，接口有且仅有一个抽象方法
 * 没有这个注解的普通接口，有且就有一个抽象方法，也是函数式接口。
 * **********************************************************
 * @date 2016年7月19日-下午7:45:47
 */
@FunctionalInterface
public interface CreateAble {

    // 静态方法
    static void view() {
        System.out.println(CreateAble.class.getSimpleName() + " - static method view()");
    }


    // 默认方法
    default String readme() {
        this.create();
        return CreateAble.class.getSimpleName() + " - default method readme()";
    }


    // 抽象方法
    void create();

}
