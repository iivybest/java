package edu.hit.adv.dynamic.proxy.bytecode;

/**
 * <p>MyClassLoader</p>
 *
 * @author ivybest ivybestdev@163.com
 * @date 2014-6-3 下午04:17:29
 */
public class MyClassLoader extends ClassLoader {
    public Class<?> defineClassByName(String name, byte[] b, int off, int len) {
        Class<?> clazz = super.defineClass(name, b, off, len);
        return clazz;
    }
}
