/**
 * @Filename EInvTestSuite
 * @author ivybest ivybestdev@163.com
 * @version V1.0
 * @date 2019年9月18日 下午1:58:47
 * @Company ivybest Co.,Ltd.
 * @Copyright Copyright(C) 2010-
 * All rights Reserved, Designed By Ivybest
 * <p>
 * Modification History:
 * Date			Author			Version			Discription
 * --------------------------------------------------------
 * 2019年9月18日		Ivybest			1.0				new create
 */
package hit.guide.logger.logback.biz.einv;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author ivybest imiaodev@163.com
 * @date 2019年9月18日 下午1:58:47
 * 	TODO
 */
@RunWith(Suite.class)
@SuiteClasses({EInvBizLogEmulator.class, EInvGenBizLogEmulator.class})
public class EInvTestSuite {
}
