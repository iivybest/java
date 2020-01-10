package edu.hit.adv.annotation.validator.validator.impl;

import edu.hit.adv.annotation.validator.annotations.Required;
import edu.hit.adv.annotation.validator.exception.FieldValidateException;
import edu.hit.adv.annotation.validator.validator.FieldValidateAble;
import org.ivy.xutil.bean.BeanUtil;

import java.lang.reflect.Field;

/**
 * <p> description: RequiredValidator
 * <br>---------------------------------------------------------
 * <br> check the field whether required
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * @date 2016/2/17 16:37
 */
public class RequiredValidator implements FieldValidateAble<Required> {

    @Override
    public <T> boolean validate(T bean, Field field, Required annotation) throws FieldValidateException {
        if (!annotation.required()) return true;

        Object value = BeanUtil.getFieldValue(bean, field);
        if (value == null) throw new FieldValidateException(field.getName()
                + " validate failed in Required [" + field.getName() + " is null]");
        return true;
    }


}
