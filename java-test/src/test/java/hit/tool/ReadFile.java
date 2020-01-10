/**
 * @Filename ReadFile
 * @author Ivybest
 * @version V1.0
 * @date 2019年10月25日 上午11:13:37
 * @Company ivybest Co.,Ltd.
 * @Copyright Copyright(C) 2010-
 * All rights Reserved, Designed By Ivybest
 * <p>
 * Modification History:
 * Date			Author			Version			Discription
 * --------------------------------------------------------
 * 2019年10月25日		Ivybest			1.0				new create
 */
package hit.tool;

import org.ivy.util.common.FileUtil;
import org.ivy.xutil.sec.Base64Util;

/**
 * @Classname ReadFile
 * @author ivybest imiaodev@163.com
 * @date 2019年10月25日 上午11:13:37
 * 	TODO
 */
public class ReadFile {
    public static void main(String[] args) {
        String filepath = "D:\\ImiaoDev\\Test\\jd/011001900411-27694049_jd.pdf";
        byte[] bytes = FileUtil.read(filepath);
        String base64String = Base64Util.encodeToString(bytes);
        System.out.println(base64String);
    }
}
