/**
 * @Filename CmccTemplateDirFixHandler
 * @author Ivybest
 * @version V1.0
 * @date 2019年9月10日 上午9:41:33
 * @Company ivybest Co.,Ltd.
 * @Copyright Copyright(C) 2010-
 * All rights Reserved, Designed By Ivybest
 * <p>
 * Modification History:
 * Date			Author			Version			Discription
 * --------------------------------------------------------
 * 2019年9月10日		Ivybest			1.0				new create
 */
package hit.tool;

import org.ivy.util.common.FileUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.stream.Stream;

/**
 * @Classname CmccTemplateDirFixHandler
 * @author ivybest imiaodev@163.com
 * @date 2019年9月10日 上午9:41:33
 * 	TODO
 */
public class CmccTemplateDirFixHandler {

    private String basepath;

    @Before
    public void setUp() {
        this.basepath = "D:\\Temp\\cmcc/";
    }

    @Test
    public void test_fix() {
        File[] files = FileUtil.getAllNonDirFileList(this.basepath);
        // ----step-1--create dir
        Stream.of(files)
                .map(e -> e.getName().substring(0, 2))
                .forEach(e -> FileUtil.checkDir(true, this.basepath + e));
        // ----step-2--move file
        Stream.of(files).forEach(e -> {
            String dest = this.basepath + e.getName().substring(0, 2) + "/200001.pdf";
            e.renameTo(new File(dest));
        });
    }
}






