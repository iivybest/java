package edu.hit.adv.reflect;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Modifier;

/**
 * <p>TestModifiers</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015年6月10日 - 上午10:01:14
 */
public class TestModifiers {
    private String separator;

    @Before
    public void before() {
        this.separator = ":";
    }

    @Test
    public void test_01_modifiers() {
        System.out.println(" \n"
                + Modifier.ABSTRACT + this.separator + "ABSTRACT" + "\n"
                + Modifier.INTERFACE + this.separator + "INTERFACE" + "\n"
                + Modifier.NATIVE + this.separator + "NATIVE" + "\n"
                + Modifier.PRIVATE + this.separator + "PRIVATE" + "\n"
                + Modifier.PROTECTED + this.separator + "PROTECTED" + "\n"
                + Modifier.PUBLIC + this.separator + "PUBLIC" + "\n"
                + Modifier.STATIC + this.separator + "STATIC" + "\n"
                + Modifier.STRICT + this.separator + "STRICT" + "\n"
                + Modifier.SYNCHRONIZED + this.separator + "SYNCHRONIZED" + "\n"
                + Modifier.TRANSIENT + this.separator + "TRANSIENT" + "\n"
                + Modifier.VOLATILE + this.separator + "VOLATILE");
    }

}
