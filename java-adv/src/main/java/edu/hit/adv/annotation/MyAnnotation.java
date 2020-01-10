package edu.hit.adv.annotation;

import java.lang.annotation.*;

/**
 * <br> description: My Annotation note
 * <br>---------------------------------------------------------
 * <br> 标记注解 不包含成员变量的注解
 * <br> 元数据注解 包含成员变量的注解
 * <br>
 * <br> JDK 提供了 4 种元数据注解，用于修饰其他的自定义注解
 * <br> @Retention 用于描述注解的使用范围
 * <br>     表示需要在什么级别保存该注释信息，用于描述注解的生命周期
 * <br>	    1.SOURCE:在源文件中有效（即源文件保留）
 * <br>	    2.CLASS:在class文件中有效（即class保留）
 * <br>	    3.RUNTIME:在运行时有效（即运行时保留）
 * <br>
 * <br> @Target 指定被修饰的元素
 * <br>     Metohd          修饰方法
 * <br>     ANNOTATION_TYPE 修饰注解
 * <br>     Constructor     构造函数
 * <br>     Field           成员变量
 * <br>     local_variable  局部变量
 * <br>     parameter       修饰参数
 * <br>     Type            修饰类
 * <br>
 * <br> @Documented	指定被修饰的注解能被javadoc提取出来，形成文档
 * <br>
 * <br> @Inherited 指定被修饰的注解是可以被继承的
 * <br>         若一个 @Inherited 修饰的 annotation 类型作用于一个 class，
 * <br>         则这个 annotation 将被用于该 class 的子类。
 * <br>	        # 注意：@Inherited annotation 类型是被标注过的 class 的子类所继承。
 * <br>         类并不从它所实现的接口继承 annotation，
 * <br>         方法并不从它所重载的方法继承 annotation。
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * @date 2017/6/29 21:39
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Documented
@Inherited
public @interface MyAnnotation {

    String value() default "";

    String name() default "Ares";

    String gender() default "mele";

    String[] msg() default "";

}



