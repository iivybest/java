package edu.hit.adv.enumeration;

import org.junit.Test;

/**
 * <p>EnumTest</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @date 2015年5月22日 - 下午4:45:20
 * @versino 1.0
 */
public class EnumTest {
    @Test
    public void testWeek() {
        Week day = Week.SUN;
        System.out.println(day);
        System.out.println(day.name());
        System.out.println(day.ordinal());
        System.out.println(day.getClass().getName());
        System.out.println(Week.valueOf("MON").name());
        System.out.println(Week.values().length);
        Week.valueOf("WED").mood();
    }
}
