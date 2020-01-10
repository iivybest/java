package edu.hit.adv.nio.file;

import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.ivy.util.common.FileUtil;
import org.ivy.xutil.sec.MessageDigestUtil;
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
import java.util.Base64;

import static org.junit.Assert.assertEquals;

/**
 * <p> description: read file performance testcase
 * <br>---------------------------------------------------------
 * <br>
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * @date 2019/8/17 12:32
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReadFileTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String path;
    private File file;
    private String fileMd5;

    @Before
    public void setUp() {
        this.path = "D:/Temp/read_preformance/einvoice_9.11k.ofd";
//		this.path = "D:/Temp/read_preformance/einvoice_96.8k.pdf";
//		this.path = "D:/Temp/read_preformance/JavaSpec_962k.pdf";
//		this.path = "D:/Temp/read_preformance/audio_29860K.flac";
//		this.path = "D:/Temp/read_preformance/book_158380K.pdf";

        this.file = new File(path);
        this.fileMd5 = Base64.getEncoder().encodeToString(MessageDigestUtil.digest(MessageDigestUtil.MD5, FileUtil.read(file)));
        ;
    }

    @After
    public void tearDown() {
        this.path = null;
        this.file = null;
        this.fileMd5 = null;
        this.logger.warn("--------------------------------------------");
        this.logger.error("--------------------------------------------");
    }

    @Test
    public void testcase_01_init() {
        logger.debug("====>[{}]-{msg: {}}", "testcase_01_init", this.file.length());
    }


    @Test
    public void testcase_02_ReadFileByOSIO() {
        byte[] contents = null;
        try (
                FileInputStream fis = new FileInputStream(this.file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ) {
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = bis.read(buf)) > 0) {
                baos.write(buf, 0, len);
            }
            contents = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String md5 = Base64.getEncoder().encodeToString(MessageDigestUtil.digest(MessageDigestUtil.MD5, contents));
        assertEquals(md5, this.fileMd5);
        logger.debug("====>[{}]-{hash: {}}", "testcase_02_ReadFileByOSIO", md5);
    }

    @Test
    public void testcase_03_ReadFileByGuava() {
        byte[] contents = null;
        try {
            contents = Files.toByteArray(this.file);

//			HashCode hashcode = Files.asByteSource(this.file).hash(Hashing.sha256());
//			logger.info(hashcode.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }
        String md5 = Base64.getEncoder().encodeToString(MessageDigestUtil.digest(MessageDigestUtil.MD5, contents));
        assertEquals(md5, this.fileMd5);
        logger.debug("====>[{}]-{hash: {}}", "testcase_03_ReadFileByGuava", md5);
    }

    @Test
    public void testcase_04_ReadFileByApahceCommonsIO() {
        byte[] contents = null;
        try {
            contents = FileUtils.readFileToByteArray(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String md5 = Base64.getEncoder().encodeToString(MessageDigestUtil.digest(MessageDigestUtil.MD5, contents));
        assertEquals(md5, this.fileMd5);
        logger.debug("====>[{}]-{hash: {}}", "testcase_04_ReadFileByApahceCommonsIO", md5);
    }


    @Test
    public void testcase_05_ReadFileByMmap() throws IOException {
        byte[] contents = null;
        try (
                FileInputStream in = new FileInputStream(this.file);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ) {
            FileChannel channel = in.getChannel();
            // ---- 创建内存映射文件
            MappedByteBuffer buff = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

            byte[] buf = new byte[1024];
            int filelen = (int) file.length();
            int len = 0;

            for (int offset = 0; offset < filelen; offset += buf.length) {
                len = filelen - offset > buf.length ? buf.length : filelen - offset;
                buff.get(buf, 0, len);
                baos.write(buf, 0, len);
            }
            contents = baos.toByteArray();
        }
        String md5 = Base64.getEncoder().encodeToString(MessageDigestUtil.digest(MessageDigestUtil.MD5, contents));
        assertEquals(md5, this.fileMd5);
        logger.debug("====>[{}]-{hash: {}}", "testcase_05_ReadFileByMmap", md5);
    }

    @Test
    public void testcase_06_ReadFileByOSIO() {
        byte[] contents = null;
        try (
                FileInputStream fis = new FileInputStream(this.file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ) {
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = bis.read(buf)) > 0) {
                baos.write(buf, 0, len);
            }
            contents = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String md5 = Base64.getEncoder().encodeToString(MessageDigestUtil.digest(MessageDigestUtil.MD5, contents));
        assertEquals(md5, this.fileMd5);
        logger.debug("====>[{}]-{hash: {}}", "testcase_06_ReadFileByOSIO", md5);
    }

    @Test
    public void testcase_07_ReadFileByMyFileUtil() {
        byte[] contents = FileUtil.read(this.file);
        String md5 = Base64.getEncoder().encodeToString(MessageDigestUtil.digest(MessageDigestUtil.MD5, contents));
        assertEquals(md5, this.fileMd5);
        logger.debug("====>[{}]-{hash: {}}", "testcase_07_ReadFileByMyFileUtil", md5);
    }

    @Test
    public void testcase_08_ReadFileByJavaNIO2Files() throws IOException {
        byte[] contents = java.nio.file.Files.readAllBytes(Paths.get(this.path));
        String md5 = Base64.getEncoder().encodeToString(MessageDigestUtil.digest(MessageDigestUtil.MD5, contents));
        assertEquals(md5, this.fileMd5);
        logger.info("====>[{}]-{hash: {}}", "testcase_08_ReadFileByJavaNIO2Files", md5);
    }


}










