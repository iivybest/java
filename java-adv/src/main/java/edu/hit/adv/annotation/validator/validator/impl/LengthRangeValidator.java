package edu.hit.adv.annotation.validator.validator.impl;

import edu.hit.adv.annotation.validator.annotations.LengthRange;
import edu.hit.adv.annotation.validator.exception.FieldValidateException;
import edu.hit.adv.annotation.validator.validator.FieldValidateAble;
import org.ivy.xutil.bean.BeanUtil;

import java.lang.reflect.Field;


/**
 * <p>LengthRangeValidator</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2016年2月17日-下午4:04:59
 */
public class LengthRangeValidator implements FieldValidateAble<LengthRange> {

    @Override
    public <T> boolean validate(T bean, Field field, LengthRange annotation) throws FieldValidateException {
        Object value = BeanUtil.getFieldValueByReflect(bean, field);
        if (value == null) throw new FieldValidateException(field.getName()
                + " validate failed in LengthRange [" + field.getName() + " is null]");
        int len = value.toString().length();
        int min = annotation.min();
        int max = annotation.max();
        if (len < min || len > max) throw new FieldValidateException(field.getName()
                + " validate failed in LengthRange [" + min + "," + max + "]["
                + field.getName() + " length " + len + "]");
        return (len <= max && len >= min);
    }

}
