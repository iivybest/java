package edu.hit.adv.annotation.validator.validator.impl;

import edu.hit.adv.annotation.validator.annotations.ValueRange;
import edu.hit.adv.annotation.validator.exception.FieldValidateException;
import edu.hit.adv.annotation.validator.validator.FieldValidateAble;
import org.ivy.xutil.bean.BeanUtil;

import java.lang.reflect.Field;


/**
 * ValueRangeValidator
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2016年2月18日-上午11:04:41
 */
public class ValueRangeValidator implements FieldValidateAble<ValueRange> {

    @Override
    public <T> boolean validate(T bean, Field field, ValueRange annotation) throws FieldValidateException {
        String[] elements = annotation.value();
        if (elements.length == 0) return true;

        boolean valid = false;
        Object value = BeanUtil.getFieldValue(bean, field);
        if (value != null) {
            for (String s : elements) {
                if (s.equals(value.toString())) {
                    valid = true;
                    break;
                }
            }
        }

        if (!valid) throw new FieldValidateException(bean.getClass().getName() + "." + field.getName()
                + " validate failed in ValueRange [" + field.getName() + " is " + value + "]");
        return valid;
    }

}
