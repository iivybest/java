/**
 * Filename 	MyExceptionTest
 * TODO
 *
 * @author ivybest
 * @version V1.0
 * CreateDate 	2017年9月5日 下午4:39:27
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
 * @Classname MyExceptionTest
 *  TODO(这里用一句话描述这个类的作用)
 * @author
 * Createdate    2017年9月5日 下午4:39:27
 */
public class MyExceptionTest {
    public static void main(String[] args) {
        String arg0 = null;
        try {
            if (arg0.equals("null"))
                System.out.println(arg0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());


//			MyException me = new MyException(e);
//			me.printStackTrace();
        }
    }
}
