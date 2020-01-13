package edu.hit.adv.enumeration;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;

/**
 * <p> description: enumeration Color testcase
 * <br>---------------------------------------------------------
 * <br>
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2016/7/26 9:35
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class ColorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_01_enum() {
        Color color = Color.BLACK;

        log.debug("{color: {}}",  color);
        log.debug("{color instanceof Color: {}}", (color instanceof Color));
        log.debug("{ordinal: {}}", color.ordinal());
        log.debug("{compareTo Black: {}}", color.compareTo(Color.BLACK));
        log.debug("{compareTo Green: {}}", color.compareTo(Color.GREEN));
        log.debug("{valueOf: {}}", Color.valueOf("BLACK"));

        Color[] colors = Color.values();
        Arrays.stream(colors).forEach(e -> log.debug("{value: {}}", e));
    }

    @Test
    public void test_02() {
        Color color = Color.GREEN;
        switch (color) {
            case RED:
                log.debug("{color: {}}", Color.RED);
                break;
            case GREEN:
                log.debug("{color: {}}", Color.GREEN);
                break;
            case BLUE:
                log.debug("{color: {}}", Color.BLUE);
                break;
            case BLACK:
                log.debug("{color: {}}", Color.BLACK);
                break;
            default:
                log.debug("{color: {}}", "another");
                break;
        }
    }

    @Test
    public void test_EnumSingleton() {
        EnumSingleton.instance.operate();
        log.debug(EnumSingleton.instance.getClass().getSimpleName());
    }

}
