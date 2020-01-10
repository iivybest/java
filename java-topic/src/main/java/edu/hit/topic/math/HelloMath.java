package edu.hit.topic.math;

import org.junit.Test;

/**
 * <p>HelloMath</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @date 2015年6月30日 - 下午4:37:05
 * @versino 1.0
 */
public class HelloMath {
    /*
     * Test-01 绝对值
     * |-100| = 100
     */
    @Test
    public void test_01_abs() {
        Number n1 = Math.abs(-100);
        System.out.println(n1);
    }

    /*
     * Test_02 指数运算，冪运算
     * 2^3 = 8
     */
    @Test
    public void test_02_pow() {
        Number n1 = Math.pow(2, 3);
        System.out.println(n1);
    }

    /*
     * Test-03 e为底数的幂运算
     * e^2
     */
    @Test
    public void test_03_exp() {
        // Math.exp(2) 相当于 Math.pow(Math.E, 2)
        Number n1 = Math.exp(2);
        System.out.println(n1);
    }

    /*
     * Test-04 ln (以e为底数的对数)
     * Math.log(num)表示自然对数 是以e为底数的对数
     */
    @Test
    public void test_04_ln() {
        Number n1 = Math.log(Math.E);
        System.out.println(n1);
    }

    /*
     * Test-05 对数
     *  log(2)8 = 3
     */
    @Test
    public void test_05_log() {
        Number n1 = Math.log(8) / Math.log(2);
        System.out.println(n1);
    }

    /*
     * Test-06 round 四舍五入
     */
    @Test
    public void test_06_round() {
        Long l1 = Math.round(Math.PI);
        System.out.println(l1.intValue());
    }


    /*
     * Test-
     */
    @Test
    public void test_() {

    }


}




















