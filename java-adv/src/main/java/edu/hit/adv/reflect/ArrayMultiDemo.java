package edu.hit.adv.reflect;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * <p>ArrayMultiDemo</p>
 * <p>利用Array动态创建多维数组</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年2月17日-下午2:33:35
 */
public class ArrayMultiDemo {
    @Test
    public void test_01() {
        String[] arr = (String[]) Array.newInstance(String.class, 10);
        Array.set(arr, 2, "Giggs");
        Array.set(arr, 7, "Hebei");

        Arrays.asList(arr).forEach(i -> System.out.println(i));
    }

    @Test
    public void test_02() {
        String[][] arr = (String[][]) Array.newInstance(String.class, 3, 4);
        // 获取第一维度数组
        Object first = Array.get(arr, 0);
        // 为第一维度数组赋值
        Array.set(first, 0, "Hiba");
        System.out.println(arr[0][0]);
    }


}
