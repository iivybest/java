package edu.hit.adv.reflect;

import org.junit.Test;

/**
 * 非反射方式获取方法签名
 *
 * @author chengxiao51
 * @date 2017-12-15 15:50:48
 */
public class Function {

    @Test
    public void getMethodSignTest() {
        String methodSign = getMethodSign();
        System.out.println(methodSign);
    }

    private String getMethodSign() {
        String result = "";
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stackTrace.length; i++) {
//			if (stackTrace[i].getMethodName().equals("getMethodSign")) {
            result = stackTrace[i].getClassName() + "." + stackTrace[i].getMethodName();
            System.out.println(i + "----" + result);
//				break;
//			}
        }
        return result;
    }
}






