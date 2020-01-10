package edu.hit.adv.generic;

import java.lang.reflect.Array;

/**
 * <p> description: Generic Array Guide
 * <br>---------------------------------------------------------
 * <br>
 * <br>---------------------------------------------------------
 * <br> Copyright@2019 www.ivybest.org Inc. All rights reserved.
 * </p>
 *
 * @author Ivybest (ivybestdev@163.com)
 * @version 1.0
 * @date 2019/12/30 8:50
 */
public class GenericArray {

    /**
     * object array sub array
     *
     * @param array    array
     * @param beginIdx array begin index
     * @param endIdx   array end index
     * @param <T>      the type of element which in array
     * @return T array
     */
    public static <T> T[] subarray(T[] array, int beginIdx, int endIdx) {
        if (array == null) return null;
        if (beginIdx < 0) beginIdx = 0;
        if (endIdx > array.length - 1) endIdx = array.length;
        if (beginIdx > endIdx) beginIdx = endIdx;


        int newSize = endIdx - beginIdx + 1;
        Class<T> type = (Class<T>) array[0].getClass();
        if (newSize <= 0) {
            return (T[]) Array.newInstance(type, 0);
        } else {
            T[] subarray = (T[]) Array.newInstance(type, newSize);
            System.arraycopy(array, beginIdx, subarray, 0, newSize);
            return subarray;
        }
    }
}










