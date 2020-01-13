/**
 * @Filename WrapperClassTest
 * @author ivybest ivybestdev@163.com
 * @version V1.0
 * @date 2018年5月21日 下午5:15:17
 * @Company IB.
 * @Copyright Copyright(C) 2010-
 * All rights Reserved, Designed By Ivybest
 * <p>
 * Modification History:
 * Date				Author		Version		Discription
 * --------------------------------------------------------
 * 2018年5月21日	Ivybest			1.0			new create
 */
package edu.hit.core.primitive;

import org.junit.Test;

/**
 * @author Ivybest imiaodev@163.com
 * @date 2018年5月21日 下午5:15:17
 * @version 1.0
 * ------------------------------------------
 *  TODO(这里用一句话描述这个类的作用)
 * --------------------------------------------------
 * 		1、装箱、拆箱
 * 		2、自动装箱、自动拆箱
 * 		3、Integer 缓存 -127 ~ 128
 * 		4、
 * --------------------------------------------------
 */
public class WrapperClassTest {

    @Test
    public void testPackingBox() {
        // 装箱
        Integer i = Integer.valueOf(3);
        // 自动装箱
        Integer ii = 3;
        // 创建 integer 对象
        Integer iii = new Integer(3);
        System.out.println(i == ii);
        System.out.println(ii == iii);
    }

}
