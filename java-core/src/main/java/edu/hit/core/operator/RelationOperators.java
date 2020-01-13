package edu.hit.core.operator;

import org.junit.Test;

import java.util.Date;

/**
 * <p> description:
 * <br>--------------------------------------------------------
 * <br> 关系运算
 * <br>--------------------------------------------------------
 * <br>Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @className RelationOperators
 * @date 2019/12/6 13:38
 */
public class RelationOperators {
    @Test
    public void test_01_EqualOrNot() {
        int a = 6, b = 6;
        System.out.println(a == b);
        System.out.println(a != 7);
    }

    @Test
    public void test_02_GreaterOrLess() {
        char c1 = 'a', c2 = 'A';
        System.out.println(
                (c1 > c2) + "\n" +
                        (c1 < 100));
    }

    @Test
    public void test_03_GreatOrEaual() {
        byte b1 = 66;
        int i = 97;
        char c = 'a';
        System.out.println(i >= b1);
        System.out.println(i >= c);
    }

    @Test
    public void test_04_LessOrEaual() {
        byte b1 = 66;
        int i = 97;
        char c = 'a';
        System.out.println(i <= b1);
        System.out.println(i <= c);
    }

    // instanceof 判断对象类型
    @Test
    public void test_05_instanceof() {
        Date date = new Date();
        System.out.println(date instanceof Date);
    }


}
