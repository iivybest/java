package edu.hit.adv.annotation.validator.validator.impl;

import edu.hit.adv.annotation.validator.annotations.Validator;
import edu.hit.adv.annotation.validator.exception.FieldValidateException;
import edu.hit.adv.annotation.validator.validator.FieldValidateAble;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * <p> description: the dispatch of user define validator
 * <br>---------------------------------------------------------
 * <br> 1、User can customize one or more field validators
 * <br> 2、according to the name of validator，
 * <br>    find the specified validator.
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * @date 2019/12/27 16:10
 */
@Slf4j
public class ValidatorValidator implements FieldValidateAble<Validator> {


    @Override
    public <T> boolean validate(T bean, Field field, Validator annotation) throws FieldValidateException {
        String[] elements = annotation.value();
        if (elements.length == 0) return true;

        // 分发给指定的校验器处理
        for (String element : elements) {
            try {
                // TODO 实际场景中通过Sping 或 Properties 实现 映射关系
                String pkg = "edu.hit.adv.annotation.validator.validator.ext";
                String elementClass = pkg + "." + element;
                @SuppressWarnings("unchecked")
                FieldValidateAble<Validator> a = (FieldValidateAble<Validator>) Class.forName(elementClass).newInstance();

                if (!a.validate(bean, field, annotation)) return false;
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                throw new FieldValidateException(e.getMessage());
            }
        }
        return true;
    }

}







