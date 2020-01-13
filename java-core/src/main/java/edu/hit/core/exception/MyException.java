/**
 * Filename 	MyException
 * TODO
 *
 * @author ivybest ivybestdev@163.com
 * @version V1.0
 * CreateDate 	2017年9月5日 下午4:38:13
 * Company 		IB.
 * Copyright 	Copyright(C) 2010-
 * All rights Reserved, Designed By ivybest
 * <p>
 * Modification History:
 * Date			Author		Version		Discription
 * --------------------------------------------------------
 * 2017年9月5日		ivybest		1.0			new create
 */
package edu.hit.core.exception;

/**
 *  自定义异常
 * @author
 * Createdate    2017年9月5日 下午4:38:13
 */
public class MyException extends Exception {

    private static final long serialVersionUID = -1536306722370612074L;

    public MyException() {
        super();
    }

    public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(Throwable cause) {
        super(cause);
    }


}
