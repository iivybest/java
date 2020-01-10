package edu.hit.adv.reflect;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>SimpleReflectTest</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年4月18日-上午9:07:57
 */
public class SimpleReflectTest {

    /*
     * instanceof 被测试实例 是 Class 或 Interface 的实例 或其子类、子接口的实例
     */
    @Test
    public void testInstanceof() {
        ArrayList<String> arraylist = new ArrayList<String>();
        System.out.println(arraylist instanceof Collection);
        Object obj = new Object();
        System.out.println(obj instanceof String);
        System.out.println("string" instanceof Object);
    }

    /*
     * Class类的isInstance(Object obj)方法，obj是被测试的对象，
     * 如果obj是调用这个方法的class或接口 的实例，则返回true。
     * 这个方法是instanceof运算符的动态等价。
     */
    @Test
    public void testIsInstance() {
        ArrayList<String> arraylist = new ArrayList<String>();
        System.out.println(Collection.class.isInstance(arraylist));
        System.out.println(Object.class.isInstance("string"));
    }

    /*
     * 如果调用这个方法的class或接口 与 参数cls表示的类或接口相同，或者是参数cls表示的类或接口的父类，则返回true。
     */
    @Test
    public void testIsAssignableFrom() {
        System.out.println(Collection.class.isAssignableFrom(ArrayList.class));
        System.out.println(List.class.isAssignableFrom(List.class));
        System.out.println(Object.class.isAssignableFrom(Math.class));
    }


}
