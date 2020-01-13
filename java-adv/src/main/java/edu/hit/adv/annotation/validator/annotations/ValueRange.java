/**
 * <p>ValueRange</p>
 *
 * @author ivybest ivybestdev@163.com
 * @date 2016年2月18日-上午10:53:57
 * @version 1.0
 */
package edu.hit.adv.annotation.validator.annotations;

import java.lang.annotation.*;

/**
 * <p>ValueRange</p>
 * <p>Feild取值范围限制</p>
 *
 * @author ivybest ivybestdev@163.com
 * @date 2016年2月18日-上午10:53:57
 * @version 1.0
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValueRange {
    public String[] value();
}
