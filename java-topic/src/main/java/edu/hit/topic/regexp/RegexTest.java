/**
 * @Filename RegexTest
 * @author Ivybest
 * @version V1.0
 * @date 2018年7月10日 下午4:12:12
 * @Company IB.
 * @Copyright Copyright(C) 2010-
 * All rights Reserved, Designed By Ivybest
 * <p>
 * Modification History:
 * Date				Author		Version		Discription
 * --------------------------------------------------------
 * 2018年7月10日	Ivybest			1.0			new create
 */
package edu.hit.topic.regexp;

/**
 * @Classname RegexTest
 * @author Ivybest imiaodev@163.com
 * @date 2018年7月10日 下午4:12:12
 * @Version 1.0
 * ------------------------------------------
 *  TODO(这里用一句话描述这个类的作用)
 */
public class RegexTest {
    public static void main(String[] args) {
        String neglectedRegex = "^\\S*((/mgr/img/)|(/mgr/css/)|(/mgr/js/))\\S*";
        String url = "/ecc/mgr/css/up.png";
        System.out.println(url.matches(neglectedRegex));

    }
}
