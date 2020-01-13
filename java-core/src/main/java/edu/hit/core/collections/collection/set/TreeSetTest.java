package edu.hit.core.collections.collection.set;

import edu.hit.core.collections.collection.model.Person;
import edu.hit.core.collections.collection.model.comparator.PersonNameComparator;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.*;


/**
 * <p>TreeSetTest</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 *
 * <br>------------------------------------------
 * TreeSet 有序、唯一
 * 按元素排序顺序而不是元素添加顺序
 * 传入 comparator接口进行自定义排序
 * <br>------------------------------------------
 * @date 2015年7月15日 - 上午8:48:57
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)    //  按字母排序运行测
public class TreeSetTest {
    private Set<Person> set;

    private void initialize() {
//		this.set = new TreeSet<Person>();
        this.set = new TreeSet<Person>(new PersonNameComparator());
    }

    private void iterator(Set<?> set) {
        for (Iterator<?> it = set.iterator(); it.hasNext(); System.out.println(it.next())) ;
    }

    @Before
    public void before() {
        this.initialize();
    }

    @Test
    public void test_01_add() {
        this.set.add(new Person("Nani", 27));
        this.set.add(new Person("Rojo", 25));
        this.set.add(new Person("Mata", 27));
        this.set.add(new Person("Nani", 27));
        this.set.add(new Person("Nani", 28));

        this.iterator(this.set);
    }

    /*
     * 利用treeSet实现去重，并排序
     */
    @Test
    public void test_02_removeDuplication() {
        List<String> list = Arrays.asList("Rojo", "Nani", "Mata", "Tony", "Nani");
        Set<String> set = new TreeSet<>(list);
        list = new ArrayList<>(set);
        list.stream().forEach(System.out::println);
    }
}





