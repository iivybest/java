package edu.hit.core.io.file;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * <p>TestRandomAccessFile</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015年5月13日 - 下午4:06:56
 */
public class TestRandomAccessFile {
    private String file;

    @Before
    public void before() {
        this.file = "e:/test/java/chap09/file.txt";
    }

    // 得到定长为10的字符串
    private String getFixedLenStr(String original) {
        int maxlen = 10;
        String fillchar = " ";
        String result = "";
        if (original == null || original.length() == 0) {
            for (int i = 0; i < maxlen; i++) result += fillchar;
        } else if (original.length() >= 10) {
            result = original.substring(0, maxlen);
        } else {
            int fillint = maxlen - original.length();
            result = original;
            for (int i = 0; i < fillint; i++) result += fillchar;
        }
        return result;
    }

    /*
     * RandomAccessFile,写入操作是从DataOutput接口实现而来
     * 姓名10byte
     * 年龄4字节
     */
    @Test
    public void test_01_write() {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(this.file, "rw");
            // 写入第一条数据
            String name = this.getFixedLenStr("Rojo");
            raf.writeBytes(name);    // 姓名，以字节的形式写入字符串
            raf.writeInt(25);        // 年龄，写入整形
            // 写入第二条数据
            name = this.getFixedLenStr("Nani");
            raf.writeBytes(name);
            raf.writeInt(28);
            // 写入第三条数据
            name = this.getFixedLenStr("Shaw");
            raf.writeBytes(name);
            raf.writeInt(19);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (raf != null) raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * RandomAccessFile,读取操作是从Datainput接口实现而来
     */
    @Test
    public void test_02_read() {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(this.file, "r");
            System.out.println("文件长度----> " + raf.length());
            // 跳过14字节，跳到第二条数据起始位置
            raf.skipBytes(14);
            byte[] ba = new byte[10];
            raf.read(ba);             // 读取姓名
            int age = raf.readInt(); // 读取姓名
            System.out.println("第二条数据"
                    + "\n\t name - " + new String(ba)
                    + "\n\t age - " + age);
            // 文件指针调回位置0，读取第一条数据
            raf.seek(0);
            for (int i = 0; i < ba.length; i++)
                ba[i] = raf.readByte();    // 每次读取一个字节
            age = raf.readInt();        // 读取姓名
            System.out.println("第一条数据"
                    + "\n\t name - " + new String(ba)
                    + "\n\t age - " + age);
            // 此时指针走到了14，再跳过14字节，读第三条数据
            raf.skipBytes(14);
            raf.read(ba);         // 读取姓名
            age = raf.readInt(); // 读取姓名
            System.out.println("第三条数据"
                    + "\n\t name - " + new String(ba)
                    + "\n\t age - " + age);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (raf != null) raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
