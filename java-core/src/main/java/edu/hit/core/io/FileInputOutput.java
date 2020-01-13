package edu.hit.core.io;

import org.junit.Test;

import java.io.*;

/**
 * <p>FileInputOutput</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015年5月11日 - 上午10:07:01
 */
public class FileInputOutput {

    @Test
    public void test_01_fileInputStream() {
        String fileUrl = "e:/test/java/chap09/HelloLinux.java";
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(fileUrl);
            int b = 0;
            int count = 0;
            // fin.read()读取一个字节，读取一个字节就转为char，中文会乱码，中文占两个字符
            while ((b = fin.read()) != -1) {
                System.out.print((char) b);
                ++count;
            }
            System.out.println("一共读取了　" + count + " 个字节");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("找不到指定文件 !!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void test_02_fileOnputStream() {
        String fileUrl = "e:/test/java/chap09/CreateUser.txt";
        String fileoutUrl = "e:/test/java/chap09/new.txt";
        FileInputStream fi = null;
        FileOutputStream fo = null;
        try {
            fi = new FileInputStream(fileUrl);
            fo = new FileOutputStream(fileoutUrl);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = fi.read(buf)) != -1) {
                fo.write(buf, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("找不到指定文件 !!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fi != null) {
                    fi.close();
                }
                if (fo != null) {
                    fo.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void test_03_fileReader() {
        String fileUrl = "e:/test/java/chap09/HelloLinux.java";
        FileReader fr = null;
        try {
            fr = new FileReader(fileUrl);
            int ci = 0;
            int count = 0;
            while ((ci = fr.read()) != -1) {
                System.out.print((char) ci);
                ++count;
            }
            System.out.println("一共读取了 " + count + " 个字符");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
