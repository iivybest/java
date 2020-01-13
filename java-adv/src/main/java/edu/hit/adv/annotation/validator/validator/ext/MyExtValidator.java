package edu.hit.adv.annotation.validator.validator.ext;

import edu.hit.adv.annotation.validator.annotations.Validator;
import edu.hit.adv.annotation.validator.exception.FieldValidateException;
import edu.hit.adv.annotation.validator.validator.FieldValidateAble;

import java.lang.reflect.Field;

/**
 * <p>MyExtValidator</p>
 * <p>自定义校验器</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2016年2月18日-上午11:04:41
 */
public class MyExtValidator implements FieldValidateAble<Validator> {

    @Override
    public <T> boolean validate(T t, Field field, Validator annotation) throws FieldValidateException {
        System.out.println("-------- [" + t.getClass().getName() + "]-"
                + field.getName() + " validate by MyExtValidator");
        return true;
    }

}
