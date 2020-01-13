package edu.hit.core.collections.collection.set;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <p>LinkedHashSetTest</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 *
 * <br>-------------------------------------
 * LinkedHashSet 使用LinkedhashSet 实现，
 * 记录元素插入顺序
 * <br>-------------------------------------
 * @date 2015年7月15日 - 上午8:39:17
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)    //  按字母排序运行测
public class LinkedHashSetTest {
    private Set<String> set;

    private void initialize() {
        // LinkedHashSet, 保证有序且唯一
        this.set = new LinkedHashSet<String>();
    }

    private void iterator(Set<?> set) {
        for (Iterator<?> it = set.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }
    }

    @Before
    public void before() {
        this.initialize();
    }

    @Test
    public void test_01_add() {
        this.set.add("Nani");
        this.set.add("Rojo");
        this.set.add("Mata");
        this.set.add("Nani");
        this.iterator(this.set);
    }


}
