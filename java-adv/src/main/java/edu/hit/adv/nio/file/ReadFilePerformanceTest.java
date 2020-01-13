package edu.hit.adv.nio.file;

import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.ivy.util.common.FileUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;


/**
 * <p> description: 文件读取性能测试
 * <br>--------------------------------------------------------
 * <br> TODO
 * <br>--------------------------------------------------------
 * <br>Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @className ReadFilePerformanceTest
 * @date 2019/8/17 12:34
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReadFilePerformanceTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String path;
    private File file;


    int count;

    @Before
    public void setUp() {
        this.path = "D:/Ivybest/test/io_preformance/einvoice_9.11k.ofd";
//		this.path = "D:/Temp/read_preformance/einvoice_96.8k.pdf";
//		this.path = "D:/Temp/read_preformance/JavaSpec_962k.pdf";
//		this.path = "D:/Temp/read_preformance/audio_29860K.flac";
//		this.path = "D:/Temp/read_preformance/book_158380K.pdf";
        this.file = new File(path);
        this.count = 100000;
    }

    @After
    public void tearDown() {
        this.path = null;
        this.file = null;
        this.logger.debug("--------------------------------------------");
    }

    @Test
    public void testcase_performance_01_init() {
        logger.debug("====>[{}]-{msg: {}}", "testcase_performance_01_init", this.file.length());
    }


    @Test
    public void testcase_performance_02_ReadFileByOSIO() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < this.count; i++) {
            try (
                    FileInputStream fis = new FileInputStream(this.file);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ) {
                byte[] buf = new byte[1024];
                int len = 0;
                while ((len = bis.read(buf)) > 0) baos.write(buf, 0, len);
                baos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        String taketime = String.valueOf((end - start));
        logger.debug("====>[{}]-{taketime: {}}", "testcase_performance_02_ReadFileByOSIO", taketime);


    }

    @Test
    public void testcase_performance_03_ReadFileByGuava() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < this.count; i++) {
            try {
                Files.toByteArray(this.file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        String taketime = String.valueOf((end - start));
        logger.debug("====>[{}]-{taketime: {}}", "testcase_performance_03_ReadFileByGuava", taketime);

    }

    @Test
    public void testcase_performance_04_ReadFileByApahceCommonsIO() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < this.count; i++) {
            try {
                FileUtils.readFileToByteArray(this.file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        String taketime = String.valueOf((end - start));
        logger.debug("====>[{}]-{taketime: {}}", "testcase_performace_04_ReadFileByApahceCommonsIO", taketime);

    }


    @Test
    public void testcase_performance_05_ReadFileByMmap() throws IOException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < this.count; i++) {
            try (
                    FileInputStream in = new FileInputStream(this.file);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ) {
                FileChannel channel = in.getChannel();
                // ---- 创建内存映射文件
                MappedByteBuffer buff = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

                byte[] buf = new byte[2048];
                int filelen = (int) file.length();
                int len = 0;

                for (int offset = 0; offset < filelen; offset += buf.length) {
                    len = filelen - offset > buf.length ? buf.length : filelen - offset;
                    buff.get(buf, 0, len);
                    baos.write(buf, 0, len);
                }
                baos.toByteArray();
            }
        }
        long end = System.currentTimeMillis();
        String taketime = String.valueOf((end - start));
        logger.debug("====>[{}]-{taketime: {}}", "testcase_performance_05_ReadFileByMmap", taketime);
    }


    //	@Test
    public void testcase_performance_06_ReadFileByOSIO() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < this.count; i++) {
            try (
                    FileInputStream fis = new FileInputStream(this.file);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ) {
                byte[] buf = new byte[1024 * 2];
                int len = 0;
                while ((len = bis.read(buf)) > 0) baos.write(buf, 0, len);
                baos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        String taketime = String.valueOf((end - start));
        logger.debug("====>[{}]-{taketime: {}}", "testcase_performance_06_ReadFileByOSIO", taketime);
    }

    //	@Test
    public void testcase_performance_07_ReadFileByMyFileUtil() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < this.count; i++) {
            FileUtil.read(this.file);
        }
        long end = System.currentTimeMillis();
        String taketime = String.valueOf((end - start));
        logger.debug("====>[{}]-{taketime: {}}", "testcase_performance_07_ReadFileByMyFileUtil", taketime);
    }

    //	@Test
    public void testcase_performance_08_ReadFileByMyFileUtilWithoutSecChecks() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < this.count; i++) {
            FileUtil.read(this.file);
        }
        long end = System.currentTimeMillis();
        String taketime = String.valueOf((end - start));
        logger.debug("====>[{}]-{taketime: {}}", "testcase_performance_08_ReadFileByMyFileUtilWithoutSecChecks", taketime);
    }

    @Test
    public void testcase_performance_09_ReadFileByJavaNIO2Files() throws IOException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < this.count; i++) {
            java.nio.file.Files.readAllBytes(Paths.get(this.path));
        }
        long end = System.currentTimeMillis();
        String taketime = String.valueOf((end - start));
        logger.debug("====>[{}]-{taketime: {}}", "testcase_performance_09_ReadFileByJavaNIO2Files", taketime);
    }
}










