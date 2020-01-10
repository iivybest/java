/**
 * <p>ValueRange</p>
 *
 * @author miao.xl
 * @date 2016年2月18日-上午10:53:57
 * @version 1.0
 */
package edu.hit.adv.annotation.validator.annotations;

import java.lang.annotation.*;

/**
 * <p>Validator</p>
 * <p>指定Validator来进行校验</p>
 *
 * @author miao.xl
 * @date 2016年2月18日-上午10:53:57
 * @version 1.0
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface Validator {
    public String[] value();
}
