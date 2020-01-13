package edu.hit.core.collections.collection.set;

import edu.hit.core.collections.collection.model.Person;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * <p>HashSetTest</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * <br>--------------------------------
 * HashSet 唯一，无序
 * set 可以用来去重
 * <br>--------------------------------
 * @date 2015年7月13日 - 下午6:51:40
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)    //  按字母排序运行测试方法
public class HashSetTest {
    private Set<Person> set;

    @Before
    public void before() {
        set = new HashSet<Person>();
        /*
         * set 添加对象不允许重复，
         * set判断重复对象，1、hashCode，若相同 2、equals
         */
        set.add(new Person("Nani", 27));
        set.add(new Person("Rojo", 25));
        set.add(new Person("Nani", 27));
    }


    @Test
    public void test_01_iterator() {
        Iterator<Person> it = this.set.iterator();
        while (it.hasNext()) System.out.println(it.next());
    }

    @Test
    public void test_02_contains() {
        boolean flag = this.set.contains(new Person("Nani", 27));
        System.out.println(flag);
    }

    @Test
    public void test_03_remove() {
        boolean flag = this.set.remove(new Person("Nani", 27));
        System.out.println(flag);
    }

}
