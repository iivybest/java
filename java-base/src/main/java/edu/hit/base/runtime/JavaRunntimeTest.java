package edu.hit.base.runtime;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Enumeration;
import java.util.Properties;

/**
 * <p> description:
 * <br>----------------------------------------
 * <br> java 运行时，系统环境
 * <br>----------------------------------------
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * @className JavaRunntimeTest
 * @date 2019/12/5 13:25
 */
@Slf4j
public class JavaRunntimeTest {

    @Test
    public void test_00_envVariables() {
        String os = System.getProperty("os.name");
        log.debug(os);
        log.debug("-------------------------------------------");

        Properties prop = System.getProperties();
        Enumeration<?> e = prop.propertyNames();
        String key, val;
        while (e.hasMoreElements()) {
            key = String.valueOf(e.nextElement());
            val = prop.getProperty(key);
            log.debug("{}: {}", key, val);
        }
    }
}
