/**
 * @Filename RegexTest
 * @author ivybest ivybestdev@163.com
 * @version V1.0
 * @date 2018年7月10日 下午4:12:12
 * @Company IB.
 * @Copyright Copyright(C) 2010-
 * All rights Reserved, Designed By Ivybest
 * <p>
 * Modification History:
 * Date				Author		Version		Discription
 * --------------------------------------------------------
 * 2018年7月10日	Ivybest			1.0			new create
 */
package edu.hit.topic.regexp;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ivybest imiaodev@163.com
 * @date 2018年7月10日 下午4:12:12
 * @version 1.0
 * ------------------------------------------
 * TODO(这里用一句话描述这个类的作用)
 */
public class UpperCaseConvertor {

    public static int TYPE_UPPER_2_LOWER = 1;
    public static int TYPE_LOWER_2_UPPER = 2;

    private String path;
    private String regex_findLower;
    private String regex_findUpper;
    private Pattern pattern_findLower;
    private Pattern pattern_findUpper;

    @Before
    public void setUp() {
        // ----文件路径----传入文件夹则批量操作
        this.path = "D:\\Ivybest\\test\\reg\\";
        // ----注解正则表达式
        this.regex_findLower = "@JSONField(\\s*)\\((\\s*)name(\\s*)=(\\s*)\"[a-z|0-9|_]{1,}\"(\\s*)\\)";
        this.regex_findUpper = "@JSONField(\\s*)\\((\\s*)name(\\s*)=(\\s*)\"[A-Z|0-9|_]{1,}\"(\\s*)\\)";
        this.pattern_findLower = Pattern.compile(this.regex_findLower);
        this.pattern_findUpper = Pattern.compile(this.regex_findUpper);
    }

    /*
     * type 1 up -> lower
     * type 2 lower -> up
     */
    private String handleMatchedItem(String str, int type) {
        int idxL = str.indexOf("\"") + 1;
        int idxR = str.lastIndexOf("\"");

        String prifix = str.substring(0, idxL);
        String suffix = str.substring(idxR);
        String key = str.substring(idxL, idxR);

        String keyResult = null;
        if (type == 1) keyResult = key.toLowerCase();
        else if (type == 2) keyResult = key.toUpperCase();

        String result = prifix + keyResult + suffix;
        return result;
    }

    private void atomOpt(File file, int type) throws IOException {
        Path path = Paths.get(file.getAbsolutePath());
        String content = new String(Files.readAllBytes(path));
        Matcher matcher = null;
        if (type == 1) {
            // upper -> lower
            matcher = this.pattern_findUpper.matcher(content);
        } else if (type == 2) {
            // lower -> upper
            matcher = this.pattern_findLower.matcher(content);
        }
        String find;
        // 正则表达式匹配次数
        int times = 0;
        while (matcher.find()) {
            times++;
            find = matcher.group();
            content = content.replace(find, this.handleMatchedItem(find, type));
//			System.out.println(find);
        }
//		System.out.println(this.content);
        // ----文件重写
        if (times > 0) {
            Files.write(path, content.getBytes(), StandardOpenOption.WRITE);
        }
    }

    /**
     * 大小写转换
     *
     * @param file file
     * @param type type
     * @throws IOException
     */
    public void opt(File file, int type) throws IOException {
        if (file == null || (!file.exists())) {
            throw new IOException(file + " 不存在");
        }

        if (file.isFile()) this.atomOpt(file, type);
        else if (file.isDirectory())
            for (File e : file.listFiles()) {
                this.opt(e, type);
            }
    }

    public void opt(String file, int type) throws IOException {
        if (file == null || file.length() == 0) {
            throw new IOException(file + " 不存在");
        }
        this.opt(new File(file), type);
    }


    @Test
    public void testLower2Upper() throws IOException {
        this.opt(this.path, UpperCaseConvertor.TYPE_LOWER_2_UPPER);
    }

    @Test
    public void testUpper2Lower() throws IOException {
        this.opt(this.path, UpperCaseConvertor.TYPE_UPPER_2_LOWER);
    }

}














