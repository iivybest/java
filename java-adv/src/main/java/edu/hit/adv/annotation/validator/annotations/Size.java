package edu.hit.adv.annotation.validator.annotations;

import java.lang.annotation.*;

/**
 * <p>Size</p>
 * <p>Description : Field取值长度限制</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2014年10月30日 - 下午2:39:11
 */

//用于描述注解的使用范围
@Target(ElementType.FIELD)
// 表示需要在什么级别保存该注释信息，用于描述注解的生命周期
// 1.SOURCE:在源文件中有效（即源文件保留）
// 2.CLASS:在class文件中有效（即class保留）
// 3.RUNTIME:在运行时有效（即运行时保留）
@Retention(RetentionPolicy.RUNTIME)
// 描述annotation可以被javadoc工具提取，形成文档。Documented是一个标记注解，没有成员
@Documented
// @Inherited阐述了某个被标注的类型是被继承的。
// 如果一个使用了@Inherited修饰的annotation类型被用于一个class，
// 则这个annotation将被用于该class的子类。
// 注意：@Inherited annotation类型是被标注过的class的子类所继承。
// 类并不从它所实现的接口继承annotation，方法并不从它所重载的方法继承annotation。
@Inherited
public @interface Size {
    /**
     * 字段长度最小值 default 0
     *
     * @return
     * @author ivybest ivybestdev@163.com
     * @date 2014年10月30日 下午2:42:05
     * @version 1.0
     */
    public int min() default 0;

    /**
     * 字段长度最大值 default 100
     *
     * @return
     * @author ivybest ivybestdev@163.com
     * @date 2014年10月30日 下午2:42:14
     * @version 1.0
     */
    public int max() default 100;
}
