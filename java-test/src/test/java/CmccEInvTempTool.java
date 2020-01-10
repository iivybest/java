import org.ivy.util.common.FileUtil;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

/**
 * @Filename CmccEInvTempTool
 * @author ivybest
 * @version V1.0
 * @date 2018年8月14日 上午10:26:30
 * @Company IB.
 * @Copyright Copyright(C) 2010-
 * All rights Reserved, Designed By ivybest
 * <p>
 * Modification History:
 * Date			Author		Version		Discription
 * --------------------------------------------------------
 * 2018年8月14日	ivybest		1.0			new create
 */

/**
 * @Classname CmccEInvTempTool
 * @author ivybest imiaodev@163.com
 * @date 2018年8月14日 上午10:26:30
 * @Version 1.0
 *
 */
public class CmccEInvTempTool {

    private String destPath = "E:/Ivybest/test/template/cmcc/";
    private String originPath = "E:/Ivybest/test/template/template/";

    @Test
    public void createRegionCatalog() {
        File[] files = FileUtil.getNonDirFileList(this.originPath);
        Arrays.asList(files).forEach(file -> {
            String _path = file.getName().substring(0, 2);
            String _dest = this.destPath + _path + "/200001.pdf";
            File f = new File(_dest);
            System.out.println(_path + ", " + _dest + ", " + f.getParent());
            FileUtil.checkDir(f.getParent(), true);
            file.renameTo(new File(_dest));
        });

    }


}
