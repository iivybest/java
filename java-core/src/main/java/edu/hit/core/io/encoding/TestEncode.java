package edu.hit.core.io.encoding;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

/**
 * <p>TestEncode</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年5月13日 - 下午5:48:04
 */
public class TestEncode {
    private String fileUrl;
    private String encoding;
    private String encoding_local;
    private String original;

    @Before
    public void before() {
        this.fileUrl = "e:/test/java/chap09/encode.txt";
        this.encoding = "ISO8859-1";
        this.encoding_local = System.getProperty("file.encoding");
        this.original = "分布式java应用";
    }

    @Test
    public void test_01_platformProperties() {
        System.getProperties().list(System.out);
    }

    @Test
    public void test_02_platformEncode() {
        String key = "file.encoding";
        String encoding = System.getProperty(key);
        System.out.println("----> 系统编码为 - " + encoding);
    }

    @Test
    public void test_03_writeWithEncode() {
        File file = new File(fileUrl);
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            byte[] bytes = this.original.getBytes(this.encoding);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
            bos.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) bos.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test_04_outputEncode() {
        File file = new File(fileUrl);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, this.encoding);
            osw.write(this.original);
            osw.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (osw != null) osw.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test_05_readWithEncode() {
        File file = new File(fileUrl);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            int len = 0;
            byte[] buf = new byte[1024];
            String data = "";
            while ((len = bis.read(buf)) > 0)
                data += new String(buf, 0, len, this.encoding);
            System.out.println(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) bis.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test_06_inputWithEncode() {
        File file = new File(fileUrl);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        InputStreamReader isr = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            isr = new InputStreamReader(bis, this.encoding);
            int len = 0;
            char[] cbuf = new char[1024];
            String data = "";
            while ((len = isr.read(cbuf)) > 0)
                data += new String(cbuf, 0, len);
            System.out.println(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (isr != null) isr.close();
                if (bis != null) bis.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
