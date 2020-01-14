package edu.hit.base.encoding;

import lombok.extern.slf4j.Slf4j;
import org.ivy.util.common.StringUtil;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * <p> description: TODO
 * <br>---------------------------------------------------------
 * <br>
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * @date 2020/1/14 9:05
 */
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EncodingTest {

    @Test
    public void test_01_encoding() throws UnsupportedEncodingException {
        List<String> characters = Arrays.asList("A", "B", "Z", "a", "b", "z", "0", "1", "中", "啊");

        for (String e : characters) {
            String hexByGB2312 = DatatypeConverter.printHexBinary(e.getBytes("GB2312"));
            String hexByGBK = DatatypeConverter.printHexBinary(e.getBytes("GBK"));
            String hexByUTF8 = DatatypeConverter.printHexBinary(e.getBytes("UTF-8"));
            String unicode = StringUtil.toUnicodeString(e);

            log.debug("{char: {}, GB2312: {}, GBK: {}, UTF-8: {}, unicode: {}}", e, hexByGB2312, hexByGBK, hexByUTF8, unicode);
        }

//        assertEquals(ahHexByGB2312, "B0A1");
    }

}
