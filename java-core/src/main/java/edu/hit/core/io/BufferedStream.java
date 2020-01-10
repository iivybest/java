package edu.hit.core.io;

import lombok.extern.slf4j.Slf4j;
import org.ivy.util.common.DateTimeUtil;
import org.ivy.util.common.SystemUtil;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.*;

/**
 * <p>BufferedStream</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年5月11日 - 下午4:41:06
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class BufferedStream {

    private String classpath = SystemUtil.getClasspath();

    private String srcPath;
    private String destPath;

    @Before
    public void setUp() {
        this.srcPath = this.classpath + "/material/hello.txt";
        this.destPath = this.classpath + "/material/hello_copy_" + DateTimeUtil.getCurrentTimestamp() + ".txt";

        File dest = new File(this.destPath);
        if (dest.exists()) {
            dest.delete();
        }
    }


    @Test
    public void test_01_bufferedStreamBeforeJdk7() {
        FileInputStream fis;
        FileOutputStream fos;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            fis = new FileInputStream(this.srcPath);
            fos = new FileOutputStream(this.destPath);
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            int len;
            byte[] buf = new byte[1024];
            while ((len = bis.read(buf)) > 0) {
                bos.write(buf, 0, len);
            }
            bos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
