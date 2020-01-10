/**
 * @Filename DoubleTest
 * TODO
 * @author ivybest
 * @version V1.0
 * @date 2017年12月24日 上午9:31:55
 * @Company IB.
 * @Copyright Copyright(C) 2010-
 * All rights Reserved, Designed By ivybest
 * <p>
 * Modification History:
 * Date				Author		Version		Discription
 * --------------------------------------------------------
 * 2017年12月24日	ivybest		1.0			new create
 */
package edu.hit.topic.number;

import org.junit.Test;

/**
 * @Classname DoubleTest
 * @author ivybest imiaodev@163.com
 * @date 2017年12月24日 上午9:31:55
 * @Version 1.0
 * ------------------------------------------
 *  TODO(这里用一句话描述这个类的作用)
 */
public class DoubleTest {
    @Test
    public void test() {
        double sl = -1.00000000d;
        double dj = 60.00000000d;
        double je = sl * dj;
        System.out.println(je);
        System.out.println(je == -60.00d);
    }
}
