/**
 * @Filename ArrayInitializeDemo
 * TODO
 * @author ivybest
 * @version V1.0
 * @date 2017年10月16日 上午9:26:03
 * @Company IB.
 * @Copyright Copyright(C) 2010-
 * All rights Reserved, Designed By ivybest
 * <p>
 * Modification History:
 * Date				Author		Version		Discription
 * --------------------------------------------------------
 * 2017年10月16日	ivybest		1.0			new create
 */
package edu.hit.core.array;

import lombok.extern.slf4j.Slf4j;
import org.ivy.util.common.Arrayx;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * <p> description: ArrayInitializeDemo
 * <br>---------------------------------------------------------
 * <br>
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @date 2017/10/16 16:23
 * @version 1.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class ArrayInitializeDemo {
    private  int[] array;

    @Before
    public void setUp() {
        /*
         * 数组的动态初始化
         * 指定数组大小，数组每个元素的值为相应类型的直接量
         */
        this.array = new int[4];
        for (int i = 0, len = this.array.length; i < len; array[i] = i++) {
        }
    }

    @After
    public void tearDown() {
        log.debug("====split line------------------------");
    }

    @Test
    public void test_01_printArray() {
        System.out.print("[");
        for (int i = 0, len = this.array.length; i < len; i++) {
            System.out.print(i);
            if (i == len - 1) {
                System.out.println("]");
                break;
            }
            System.out.print(", ");
        }
    }
    @Test
    public void test_02_initArray() {
        /*
         * 数组的静态初始化
         * 使用大括号直接为数组各元素初始化，不需要[]中指定长度
         */
        Integer[] array2 = new Integer[]{0, 1, 2, 3};
        /*
         * 数组的静态初始化
         * 静态初始化的简写形式
         */
        String[] array3 = {"Nani", "Martial", "Rashford", "Rooney"};

        log.debug("{array2: {}}", Arrayx.printArray(array2));
        log.debug("{array3: {}}", Arrays.toString(array3));
    }

}


