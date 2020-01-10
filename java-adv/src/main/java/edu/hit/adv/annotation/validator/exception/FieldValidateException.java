package edu.hit.adv.annotation.validator.exception;

/**
 * <p>FIeldValidateException</p>
 * <p>字段校验异常</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年2月17日-下午3:49:42
 */
public class FieldValidateException extends Exception {

    private static final long serialVersionUID = 5286737172734185703L;

    public FieldValidateException() {
        super();
    }

    public FieldValidateException(String msg) {
        super(msg);
    }

}
