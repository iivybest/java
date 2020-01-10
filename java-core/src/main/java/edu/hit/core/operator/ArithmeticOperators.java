package edu.hit.core.operator;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * <p> description:
 * <br>--------------------------------------------------------
 * <br> Java 数学运算操作符
 * <br>--------------------------------------------------------
 * <br>Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * @className ArithmeticOperators
 * @date 2019/12/6 9:20
 */
@Slf4j
public class ArithmeticOperators {

    private double d = 12.31D;
    private int i = 2;

    // Test '+' & '-' & '*' & '\'
    @Test
    public void test_01_AddAndSubAndMultiAndDivis() {
        double addition = d + i;
        double subtraction = d - i;
        double multiplication = d * i;
        double division = d / i;

        log.debug("----> {} {} {} = {}", d, "+", i, addition);
        log.debug("----> {} {} {} = {}", d, "-", i, subtraction);
        log.debug("----> {} {} {} = {}", d, "*", i, multiplication);
        log.debug("----> {} {} {} = {}", d, "/", i, division);
    }

    // tets '%' 取模
    @Test
    public void test_02_modulus() {
        Number[][] numbers = {
                {2, -4},
                {12.32, 2},
                {2, 3},
                {3, 2},
        };

        for (Number[] e : numbers) {
            log.debug("----> {} % {} = {}", e[0], e[1], (e[0].intValue() % e[1].intValue()));
        }
        for (Number[] e : numbers) {
            log.debug("----> {} % {} = {}", e[0], e[1], (e[0].doubleValue() % e[1].doubleValue()));
        }
    }

    // test '--'
    @Test
    public void test_03_selfDecrement() {
        int t = 6;
        int o = --t + t--;
        System.out.println("o = " + o);
        System.out.println("t = " + t);
        /*
         * --t + --t 分析，
         * --t, 表示先减一再运算，此时 t = 5
         * t--, 表示先运算，运算后再减一 所以o的赋值运算时t = 5，赋值后 t = 4
         */
    }

    // test '++'
    @Test
    public void test_04_selfIncrement() {
        int t = 6;
        int o = ++t - t++;
        System.out.println("o = " + o);
        System.out.println("t = " + t);
        /*
         * ++ t - t ++ 分析，
         * ++ t, 表示先加一再运算，此时 t = 7
         * t ++, 表示先运算，运算后再加一 所以o的赋值运算时t = 7，赋值后 t = 8
         */
    }


}
