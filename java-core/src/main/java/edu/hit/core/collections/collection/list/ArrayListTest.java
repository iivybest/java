package edu.hit.core.collections.collection.list;

import edu.hit.core.collections.collection.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.ivy.util.common.Arrayx;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>ArrayListTest</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015年7月14日 - 上午10:14:59
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class ArrayListTest {
    private List<Person> list;

    private void add() {
        this.list.add(new Person("Nani", 27));
        this.list.add(new Person("Rojo", 25));
        this.list.add(new Person("Nani", 27));
    }

    private void iterator(List<?> list) {
        String arrayString = Arrayx.printArray(list.toArray());
        log.debug("{list: {}}", arrayString);
    }

    @Before
    public void setUp() {
        this.list = new ArrayList<>();
        this.add();
        this.iterator(this.list);
    }

    @Test
    public void test_01_contains() {
        boolean flag = this.list.contains(new Person("Nani", 27));
        System.out.println(flag);
        this.iterator(this.list);
    }

    @Test
    public void test_02_remove() {
        boolean flag = this.list.remove(new Person("Nani", 27));
        System.out.println(flag);
        this.iterator(this.list);
    }

    @Test
    public void test_03_toArray() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        Object[] array = list.toArray();
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void test_04_toArray() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        String[] array = list.toArray(new String[1]);
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void test_05_arrayToList() {
        Integer[] array = {1, 2, 3, 4};
        List<Integer> list = Arrays.asList(array);
        List<Integer> targetList = new ArrayList<>(array.length);
        targetList.addAll(list);
        System.out.println(targetList.get(2));
    }


}
