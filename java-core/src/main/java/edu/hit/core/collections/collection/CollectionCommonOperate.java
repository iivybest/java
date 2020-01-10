package edu.hit.core.collections.collection;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.*;

/**
 * <p>CollectionCommonOperate</p>
 * <p>Description : 集合类基本操作 </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年7月7日 - 下午3:34:34
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)    //  按字母排序运行测试方法
public class CollectionCommonOperate {
    private Collection<Integer> collection;

    @Before
    public void before() {
        this.collection = new ArrayList<Integer>();
        for (int i = 0; i < 10; this.collection.add(i++)) ;
    }

    @Test
    public void test_01_add() {
        this.collection.add(-1);
        System.out.println("operate add -1 ----> " + this.collection);
    }

    @Test
    public void test_02_addAll() {
        Collection<Integer> set = new HashSet<Integer>();
        set.add(-1);
        set.add(-2);
        set.add(-3);
        boolean flag = this.collection.addAll(set);
        System.out.println("operate addAll [-1, -2, -3] ----> "
                + flag + this.collection);
    }

    @Test
    public void test_03_removeAll() {
        Collection<Integer> vector = new Vector<Integer>();
        vector.add(-1);
        vector.add(2);
        vector.add(3);
        boolean flag = this.collection.removeAll(vector);
        System.out.println("operate removeAll [-1, 2, 3] ----> "
                + flag + this.collection);
    }

    @Test
    public void test_04_retainAll() {
        Collection<Integer> c = new ArrayList<Integer>();
        c.add(1);
        c.add(2);
        c.add(3);
        boolean flag = this.collection.retainAll(c);
        System.out.println("operate retainAll [1, 2, 3] ----> "
                + flag + this.collection);
    }

    @Test
    public void test_05_iterator() {
        /*
         * 在使用Iterator时，不能使用集合操作，例如add remove,
         * 容易抛出java.util.ConcrrentModificationException
         */
        Iterator<Integer> it = this.collection.iterator();
        System.out.print("operate iterator ----> ");
        while (it.hasNext()) System.out.print(it.next() + ", ");
    }


}
