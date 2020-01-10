/**
 * @Filename IntergerTest
 * @author Ivybest
 * @version V1.0
 * @date 2018年5月21日 下午4:52:34
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

/**
 * @Classname IntergerTest
 * @author Ivybest imiaodev@163.com
 * @date 2018年5月21日 下午4:52:34
 * @Version 1.0
 * ------------------------------------------
 *  TODO(这里用一句话描述这个类的作用)
 */
public class IntergerTest {

    public static void main(String[] args) {
        int a = 3;
        Integer b = 3, c = 3;
        System.out.println(a == b);
        System.out.println(b == c);

        int d = 1000;
        Integer e = 1000, f = 1000;
        System.out.println(d == e);
        System.out.println(e == f);


        int g = 6;
        Integer k = 6;
        Integer l = new Integer(6);
        System.out.println(g == k);
        System.out.println(g == l);
        System.out.println(k == l);


        int t = 3;
        int tt = ++t + t++;
        System.out.println(t);
        System.out.println(tt);


    }


}
