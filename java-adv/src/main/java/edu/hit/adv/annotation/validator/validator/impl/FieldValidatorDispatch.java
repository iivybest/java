package edu.hit.adv.annotation.validator.validator.impl;

import edu.hit.adv.annotation.validator.exception.FieldValidateException;
import edu.hit.adv.annotation.validator.validator.FieldValidateAble;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * <p> description: validator dispatch
 * <br>---------------------------------------------------------
 * <br>
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * @date 2016/2/18 09:07
 */
public class FieldValidatorDispatch<A extends Annotation> implements FieldValidateAble<A> {

    @Override
    public <T> boolean validate(T bean, Field field, A annotation) throws FieldValidateException {
        FieldValidateAble<A> validator = this.getValidator(annotation);
        if (validator == null) {
            return false;
        }
        return validator.validate(bean, field, annotation);
    }


    @SuppressWarnings("unchecked")
    private FieldValidateAble<A> getValidator(A annotation) {
        String name = annotation.annotationType().getSimpleName();
        // TODO 实际场景中通过 Spring 或 properties实现映射关系
        String pkg = "edu.hit.adv.annotation.validator.validator.impl";
        String validatorClasspath = pkg + "." + name + "Validator";
        FieldValidateAble<A> validator = null;
        try {
            validator = (FieldValidateAble<A>) Class.forName(validatorClasspath).newInstance();
        } catch (InstantiationException
                | IllegalAccessException
                | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return validator;
    }


}
