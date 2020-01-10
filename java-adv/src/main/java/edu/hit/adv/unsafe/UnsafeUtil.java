package edu.hit.adv.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @ClassName: UnseafeUtil
 * @author: ivybest imiaodev@163.com
 * @date: 2019年5月16日 上午9:04:50
 * : 通过反射获取 Unsafe 实例
 */
public class UnsafeUtil {

    public static Unsafe getUnsafe() {
        Unsafe instance = null;
        try {
            /*
             * ----theUnsafe 为 Unsafe 中 field
             * ----theUnsafe 名称不能出错
             * ----setAccessible 设置 theUnsafe 为 可见
             * ----即可获取 Unsafe 实例
             * ***************************************************
             */
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            instance = (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

}
