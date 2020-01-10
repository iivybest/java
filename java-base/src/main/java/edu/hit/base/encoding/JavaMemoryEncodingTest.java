/**
 * @Filename JavaMemoryEncodingTest
 * TODO
 * @author ivybest
 * @version V1.0
 * @date 2017年10月19日 上午9:26:09
 * @Company IB.
 * @Copyright Copyright(C) 2010-
 * All rights Reserved, Designed By ivybest
 * <p>
 * Modification History:
 * Date				Author		Version		Discription
 * --------------------------------------------------------
 * 2017年10月19日	ivybest		1.0			new create
 */
package edu.hit.base.encoding;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * <p> description:
 * <br>----------------------------------------
 * <br> Java 内存字符编码测试
 * <br>----------------------------------------
 * </p>
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * @className JavaMemoryEncodingTest
 * @date 2019/12/5 9:47
 */
@Slf4j
public class JavaMemoryEncodingTest {

    private Character[] characters;

    @Before
    public void setUp() {
        this.characters = new Character[]{
                // \uE863
                ''
                // \u4DAE
                , '䶮'
                // \uE81B
                , ''
                // \u3447
                , '㑇'
        };
    }

    @After
    public void tearDown() {
        log.debug("====> split line------------------------");
    }

    @Test
    public void test_00_printunicode() {
        String dargonInSky_1 = "\uE863";
        String dargonInSky_2 = "\u4DAE";
        log.debug(dargonInSky_1 + ", " + dargonInSky_2);
    }


    @Test
    public void test_01_convert2MemoryEncoding() {
        Arrays.stream(this.characters).forEach(e -> {
            String memStr = JavaMemoryEncodingTest.convert2MemoryEncoding(e);
            log.debug("====>{originCode: {}, memeryCode: {}}", e, memStr);
        });
    }

    @Test
    public void test_02_convert2Unicode() {
        Arrays.stream(this.characters).forEach(e -> {
            String memStr = JavaMemoryEncodingTest.convert2Unicode(e);
            log.debug("====>{originCode: {}, memeryCode: {}}", e, memStr);
        });
    }

    @Test
    public void test_03_convert2Unicode() {
        String str_1 = "飞龙在天飞龙在天";
        log.debug("{origin: {}, \n format: {}}", str_1, convert2Unicode(str_1));
        String str_2 = str_1.replaceAll("", "䶮");
        log.debug("{origin: {}, \n format: {}}", str_2, convert2Unicode(str_2));
        String str_3 = str_1.replaceAll("\uE863", "\u4DAE");
        log.debug("{origin: {}, \n format: {}}", str_3, convert2Unicode(str_3));
    }

    /**
     * 将字符转换为内存编码
     *
     * @param origin
     * @return String
     */
    public static String convert2MemoryEncoding(char origin) {
        return Integer.toHexString(origin).toUpperCase();
    }


    /**
     * <p> description
     * <br>----------------------------------------
     * <br> 将字符串转换为 Unicode 编码
     * <br> 1、将 string 分割成 char array
     * <br> 2、遍历 char array，将每个 char 转为 unicode，并拼接起来
     * <br>----------------------------------------
     * <p/>
     * @param data
     * @return
     */
    public static String convert2Unicode(String data) {
        if (data == null) return null;
        StringBuilder sb = new StringBuilder();
        for (char e : data.toCharArray()) sb.append(convert2Unicode(e));
        return sb.toString();
    }

    /**
     * <p> description:
     * <br>----------------------------------------
     * <br> char 由 2 个字节组成，每个字节长度 8 位
     * <br> 先处理 char 高 8 位，data >>> 8 | data & 0xFF00
     * <br>	先处理 char 低 8 位，data & 0xFF | data & 0x00FF
     * <br>----------------------------------------
     * <p/>
     * @param data
     * @return String
     */
    public static String convert2Unicode(char data) {
        StringBuilder sb = new StringBuilder("\\u");
        sb.append(byteOp((byte) (data >>> 8))).append(byteOp((byte) (data & 0xFF)));
        return sb.toString();
    }

    /**
     * 单个 Byte 转换 Unicode 码操作
     *
     * @param data
     * @return
     */
    private static String byteOp(byte data) {
        String result = Integer.toHexString(data).toUpperCase();
        return result.length() == 1 ? "0" + result : result;
    }


}

