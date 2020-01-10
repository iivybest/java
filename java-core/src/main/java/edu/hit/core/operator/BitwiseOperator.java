package edu.hit.core.operator;

import lombok.extern.slf4j.Slf4j;
import org.ivy.util.common.DigitUtil;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * <p> description:
 * <br>--------------------------------------------------------
 * <br> 位运算
 * <br> Java 的位运算（bitwise operators）直接对整数类型的位进行操作，
 * <br> 这些整数类型包括 long、int、short、char和 byte，
 * <br>
 * <br>	位运算符具体如下表所示：
 * <br>	_____________________________________________________________________________
 * <br>	| 运算符 | 说明
 * <br> |-------|--------------------------------------------------------------------
 * <br>	| <<    | 左移位，在低位处补0
 * <br>	| >>    | 右移位，若为正数则高位补0，若为负数则高位补1
 * <br>	| >>>   | 无符号右移位，无论正负都在高位补0
 * <br>	| &     | 与（AND），对两个整型操作数中对应位执行布尔代数，两个位都为1时输出1，否则0。
 * <br>	| |     | 或（OR），对两个整型操作数中对应位执行布尔代数，两个位都为0时输出0，否则1。
 * <br>	| ~     | 非（NOT），一元运算符
 * <br>	| ^     | 异或（XOR），对两个整型操作数中对应位执行布尔代数，两个位相等0，不等1。
 * <br>	￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣
 * <br> # 位运算支持运算并赋值操作：<<=, >>=, >>>=, &=, |=, ^=
 * <br> # Java 并没有提供同或（xnor）操作（同或操作为：相同为 1，不同为 0）。
 * <br> # Xnor = (a  xor  b)  xor  1
 * <br>--------------------------------------------------------
 * <br>Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * @className BitwiseOperator
 * @date 2019/12/6 9:48
 */
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BitwiseOperator {

    private String prefix = "----> computational process ------------";
    private String splitLine = "-------------------------------------";
    private int[][] numbers = {
            {5, 2},
            {5, -8}
    };

    @After
    public void tearDown() {
        log.debug("==== split line ---------------------------------");
    }

    /**
     * 打印计算过程，支持任意参数个数
     *
     * @param result
     * @param desc
     * @param arg
     */
    private void printProcess(int result, String desc, int... arg) {
        String code = "\n";
        StringBuilder argMessage = new StringBuilder();
        for (int i = 0, len = arg.length; i < len; argMessage.append(DigitUtil.toBinString(arg[i++]) + code)) {
        }
        argMessage.delete(argMessage.length() - code.length(), argMessage.length());

        log.debug("\n{}\n{}\n{}\n{}\n{}",
                desc,
                this.prefix,
                argMessage.toString(),
                this.splitLine,
                DigitUtil.toBinString(result)
        );
    }

    @Test
    public void test_00_init() {
        log.debug("{type: {}, value: {}, bin: {}}", "int", 0xFFFFFFFF, DigitUtil.toBinString(0xFFFFFFFF));
        log.debug("{type: {}, value: {}, bin: {}}", "int", Integer.MIN_VALUE, DigitUtil.toBinString(Integer.MIN_VALUE));
        log.debug("{type: {}, value: {}, bin: {}}", "int", Integer.MAX_VALUE, DigitUtil.toBinString(Integer.MAX_VALUE));
        log.debug("{type: {}, value: {}, bin: {}}", "int", 0, DigitUtil.toBinString(0));
        log.debug("{type: {}, value: {}, bin: {}}", "int", -1, DigitUtil.toBinString(-1));
        log.debug("{type: {}, value: {}, bin: {}}", "int", -4, DigitUtil.toBinString(-4));

        log.debug("{type: {}, value: {}, bin: {}}", "byte", 1, DigitUtil.toBinString((byte)1));
        log.debug("{type: {}, value: {}, bin: {}}", "short", 1, DigitUtil.toBinString((short)1));
        log.debug("{type: {}, value: {}, bin: {}}", "long", 1L, DigitUtil.toBinString(-1L));
        log.debug("{type: {}, value: {}, bin: {}}", "char", '中', DigitUtil.toBinString('中'));
    }

    // Test '&' 同为1，则为1，否则为0
    @Test
    public void test_01_BinaryAnd() {
        String desc = "----> BinaryAnd/与/'&'，同时为1，则结果为1，否则为 0";
        int result = 0;
        for (int[] e : numbers) {
            result = e[0] & e[1];
            log.debug("{} & {} = {}", e[0], e[1], result);
            this.printProcess(result, desc, e[0], e[1], e[1]);
        }
    }

    // Test '|' 同为0，则为0，否则为1
    @Test
    public void test_02_BinaryOr() {
        String desc = "----> BinaryOr/或/'|'，同时为0，则结果为0，否则为 1";
        int result = 0;
        for (int[] e : numbers) {
            result = e[0] | e[1];
            log.debug("{} | {} = {}", e[0], e[1], result);
            this.printProcess(result, desc, e[0], e[1]);
        }
    }

    // Test '~' 同为0，则为0，否则为1
    @Test
    public void test_03_BinaryNot() {
        String desc = "----> BinaryNot/非/'~'，单目运算符,结果取反";
        int i1 = 5;
        int result = ~i1;
        log.debug("~ {} = {}", i1, result);
        this.printProcess(result, desc, i1);
    }

    // Test '^'
    @Test
    public void test_04_BinaryXor() {
        String desc = "----> BinaryXor/异或/’^'，相同，则结果为0，否则为 1";
        int result = 0;
        for (int[] e : numbers) {
            result = e[0] ^ e[1];
            log.debug("{} ^ {} = {}", e[0], e[1], result);
            this.printProcess(result, desc, e[0], e[1]);
        }
    }

    // Test '<<'
    @Test
    public void test_05_BinaryLeftShift() {
        String desc = "----> BinaryLeftShift/左移/'<<', 每位向左移动相应位数,低位补 0;左移 1 位，相当于 * 2";
        int i1 = 5, i2 = 1;
        int result = i1 << i2;

        log.debug("{} << {} = {}", i1, i2, result);
        this.printProcess(result, desc, i1, i2);
    }

    // Test '>>'
    @Test
    public void test_06_BinaryRightShift() {
        String desc = "---->BinaryRightShift/右移/'>>'，每位向右移动相应位数；空位补 0 或 1，最高位 1 则补 1，否则补 0，不改变符号";
        int i1 = -5, i2 = 1;
        int result = i1 >> i2;
        log.debug("{} >> {} = {}", i1, i2, result);
        this.printProcess(result, desc, i1, i2);
    }

    // Test '>>>'
    @Test
    public void test_07_BinaryRightShiftZeroFill() {
        String desc = "---->BinaryRightShiftZeroFill/无符号右移/'>>>'，向右移动相应位数，空位补 0;";
        int i1 = -5, i2 = 1;
        int result = i1 >>> i2;
        log.debug("{} >>> {} = {}", i1, i2, result);
        this.printProcess(result, desc, i1, i2);
    }

    /**
     * <p> description：
     * <br>--------------------------------------
     * <br> 同或运算，
     * <br> java 并没有提供同或运算
     * <br> 同或运算等同于每一位运算 (a ^ b) ^ 1
     * <br>--------------------------------------
     * <p/>
     */
    @Test
    public void test_08_BinaryXnor() {
        String desc = "----> BinaryXnor/同或/，相同，则结果为 1，否则为 0";
        int result = 0;
        for (int[] e : numbers) {
            result = e[0] ^ e[1] ^ 0xFFFFFFFF;
            log.debug("{} xnor {} = {}", e[0], e[1], result);
            this.printProcess(result, desc, e[0], e[1]);
        }

    }


    private int xnor(int arg0, int arg1) {
        int result = arg0 ^ arg1 ^ 0xFFFFFFFF;
        return result;
    }

    private long xnor(long arg0, long arg1) {
        long result = arg0 ^ arg1 ^ -1L;
        return result;
    }

}
