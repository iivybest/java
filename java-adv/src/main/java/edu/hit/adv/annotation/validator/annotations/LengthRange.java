package edu.hit.adv.annotation.validator.annotations;

import java.lang.annotation.*;

/**
 * <br> description: Field取值长度限制
 * <br>---------------------------------------------------------
 * <br>
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2014/10/30 14:39
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LengthRange {
    /**
     * 字段长度最小值 default 0
     *
     * @return mix value
     */
    int min() default 0;

    /**
     * 字段长度最大值 default 100
     *
     * @return max value
     */
    int max() default 100;
}
