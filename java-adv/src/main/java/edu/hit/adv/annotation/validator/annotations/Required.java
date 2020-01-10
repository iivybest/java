package edu.hit.adv.annotation.validator.annotations;

import java.lang.annotation.*;

/**
 * <p>Required</p>
 * <p>Description : Field是不必须限制</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2014年10月30日 - 下午2:41:03
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Required {
    /**
     * 字段是否必填 default false
     */
    public boolean required() default true;
}
