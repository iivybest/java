package edu.hit.adv.annotation.validator;

import edu.hit.adv.annotation.validator.exception.FieldValidateException;
import edu.hit.adv.annotation.validator.validator.FieldValidateAble;
import edu.hit.adv.annotation.validator.validator.impl.FieldValidatorDispatch;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * <p> description: Bean Field validator
 * <br>---------------------------------------------------------
 * <br> validate the fields that was bean modified by annotations
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * @date 2016/2/17 15:28
 */
public class FieldAnnotationValidator {

    /**
     * FieldValidator 调度者，将某个Field交给指定的Validator去校验
     */
    private static FieldValidateAble<Annotation> dispatch;

    // 实际场景中通过spring注入，实现初始化
    static {
        dispatch = new FieldValidatorDispatch<>();
    }

    public static <T> boolean validate(T bean) throws FieldValidateException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 对于private私有化的成员变量，通过setAccessible来修改器访问权限
//			field.setAccessible(true);
            if (!fieldValidate(bean, field)) {
                return false;
            }
            // 重新设置会私有权限
//			field.setAccessible(false);
        }
        return true;
    }


    private static <T> boolean fieldValidate(T bean, Field field) throws FieldValidateException {
        boolean valid = true;
        Annotation[] annotations = field.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            valid = dispatch.validate(bean, field, annotation);
            if (!valid) {
                break;
            }
        }
        return valid;
    }

}
