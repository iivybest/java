package edu.hit.core.collections.collection.list;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.management.AttributeList;
import javax.management.relation.RoleList;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

/**
 * <p>ListTest</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年7月8日 - 下午6:58:22
 */
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)    //  按字母排序运行测试方法
public class ListTest {
    private List<Integer> list;

    @Before
    public void before() {
        this.list = new ArrayList<Integer>();
        IntStream.range(0, 10).forEach(list::add);
    }

    @Test
    public void test_01_list() {
        /*
         * ArrayList
         * 非线程安全，
         * 变长数组实现，变长增长50%
         * remove、contains之比较equals(),不比较hashCode()
         */
        List<Integer> list1 = new ArrayList<Integer>();
        /*
         * LinkedList
         * 非同步
         * 双向循环列表结构实现，
         */
        List<Integer> list2 = new LinkedList<Integer>();
        /*
         * Vector （java1.2以前，集合类）
         * 线程安全，
         * 变长数组实现，变长增长100%
         */
        List<Integer> list3 = new Vector<Integer>();

        List<Integer> list4 = new Stack<Integer>();

        List<Integer> list5 = new CopyOnWriteArrayList<Integer>();            // 线程安全
        List<Object> list6 = new RoleList();
        List<Object> list7 = new AttributeList();
    }


    @Test
    public void test_02_ListIterator() {
        /*
         * 在使用Iterator时，不能使用集合操作，例如add remove,
         * 容易抛出java.util.ConcrrentModificationException
         * 使用ListIterator时， 可通过ListIterator对list进行操作
         */
        ListIterator<Integer> lit = this.list.listIterator();
        Integer in = -1;
        while (lit.hasNext()) {
            in = lit.next();
            if (in % 2 == 0) lit.add(Integer.valueOf("-" + in));
            System.out.print(in + ", ");
        }

        System.out.println("\nListIterator 处理后的序列--------------------->");
        lit = this.list.listIterator();
        while (lit.hasNext()) {
            System.out.print(lit.next() + ", ");
        }

        System.out.println("\nListIterator 从index 10开始--------------------->");
        lit = this.list.listIterator(10);
        while (lit.hasNext()) {
            System.out.print(lit.next() + ", ");
        }

    }


}
