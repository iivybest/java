package edu.hit.core.io.file;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * <p>TestFile</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年5月13日 - 上午8:39:09
 */
public class TestFile {
    private String dir;

    @Before
    public void before() {
        this.dir = "e:/test/java/chap09/";
    }

    @Test
    public void test_01_fileBasedOperate() {
        String url = this.dir + "HelloLinux.java";
        String separator = " \t- ";
        File file = new File(url);
        System.out.println(
                "----测试操作---- \n"
                        + "exsits()" + separator + file.exists() + "\n"
                        + "isFile()" + separator + file.isFile() + "\n"
                        + "isDirectory()" + separator + file.isDirectory() + "\n"
                        + "isHidden()" + separator + file.isHidden() + "\n"
                        + "isAbsolute()" + separator + file.isAbsolute() + "\n"
                        + "canRead()" + separator + file.canRead() + "\n"
                        + "canWrite()" + separator + file.canWrite() + "\n"
                        + "----关于文件目录名的操作---- \n"
                        + "getAbsolutePath()" + separator + file.getAbsolutePath() + "\n"
                        + "getPath()" + separator + file.getPath() + "\n"
                        + "getParent()" + separator + file.getParent() + "\n"
                        + "getName()" + separator + file.getName() + "\n"
                        + "----获取常规文件信息操作---- \n"
                        + "lastModified()" + separator + file.lastModified() + "\n"
                        + "length()" + separator + file.length() + "\n"
        );
        String subDir = "dir";
        String filename1 = "file01.txt";
        String filename2 = "file02.txt";

        // 创建file对象
        File file1 = new File(this.dir, filename1);
        File file2 = new File(this.dir + filename2);
        try {
            if (!file1.exists()) file1.createNewFile();
            if (!file2.exists()) file2.createNewFile();

            file1.setWritable(true);
            file1.setReadable(true);
            file2.setReadOnly();
            file1.renameTo(new File(this.dir + "newName.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 设置和修改操作
        file1.delete();
        // JVM终止时，删除文件或目录
        file2.deleteOnExit();

        // 目录操作
        File dir = new File(this.dir + subDir);
        if (!dir.exists()) {
            dir.mkdir();
            // mkdir只能创建当前目录，mkdirs可创建完整目录
            dir.mkdirs();
//		dir.mkdirs();
        }
    }
}
