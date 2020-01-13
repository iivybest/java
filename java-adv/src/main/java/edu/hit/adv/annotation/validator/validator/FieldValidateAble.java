package edu.hit.adv.annotation.validator.validator;

import edu.hit.adv.annotation.validator.exception.FieldValidateException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * <p> description: Field validate able interface
 * <br>---------------------------------------------------------
 * <br>
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2014/10/30 10:12
 */
public interface FieldValidateAble<A extends Annotation> {
    /**
     * validate the field in the bean, that was modified by annotation
     *
     * @param t          t
     * @param field      field
     * @param annotation annotation
     * @param <T>        the type of t
     * @return boolean
     * @throws FieldValidateException
     */
    public <T> boolean validate(T t, Field field, A annotation) throws FieldValidateException;

}
