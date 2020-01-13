package edu.hit.core.collections.map;

import edu.hit.core.collections.collection.model.Person;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.Map;


/**
 * <p>HashMapTest</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015年7月16日 - 下午3:39:26
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)    //  按字母排序运行测
public class HashMapTest {
    private Map<Person, String> map;

    private void initialize() {
        this.map = new HashMap<Person, String>();
    }

    @Before
    public void before() {
        this.initialize();
    }

    @Test
    public void test_01_put() {
        String val1 = this.map.put(new Person("Nani", 27), "Manchester");
        String val2 = this.map.put(new Person("Nani", 27), null);
        String val3 = this.map.put(new Person("Nani", 27), "Resbon");
        System.out.println("test_01_put ---->"
                + val1 + "," + val2 + "," + val3 + "\n" + this.map);
    }


    @Test
    public void test_02_linkedList() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < 10; map.put(i + "", i++)) {
        }
        System.out.println(map.get(10));
    }


}
