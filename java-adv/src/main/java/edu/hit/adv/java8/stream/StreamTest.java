package edu.hit.adv.java8.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * <p> description:
 * <br>----------------------------------------
 * <br> 流式编程测试
 * <br>----------------------------------------
 * </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @className StreamTest
 * @date 2019/12/4 14:12
 */
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StreamTest {
    // ----已知姓名列表
    private String[] names;
    // ----根据姓名列表构造 persons 集合
    private List<Person> persons;

    @Before
    public void setUp() {
        // 人员姓名集合 --名字比较多，可以从文件中加载名字
        this.names = new String[]{
                "丁一",
                "陈二",
                "张三",
                "赵四",
                "刘五",
                "赵六",
                "孙七",
                "周八",
                "吴九",
                "郑十",
                "琼十八"
        };
        this.persons = new ArrayList<Person>();
    }

    @After
    public void tearDown() {
        log.debug("-----> split line ---------------------------------------------");
    }


    @Test
    public void test_00_printAllNames() {
        /* 打印所有姓名*/
        Stream.of(this.names).map(e -> "[test_00_init]====>" + e).forEach(log::debug);
    }


    @Test
    public void test_01_generatePersons() {
        /* 通过 stream 构造多个 Person 对象，并把每个元素加入到集合 persons 中 */
        Stream.of(this.names)
                .map(e -> Person.newInstance().setName(e))
                .forEach(this.persons::add);
        /* 打印所有 person 姓名 */
        this.persons.stream().map(e -> "[test_01_generatePersons]====>" + e.getName()).forEach(log::debug);
    }

    @Test
    public void test_02_generatePersons() {
        /* 通过 stream 构造多个 Person 对象，并把每个元素加入到集合 persons 中 */
        this.persons = Stream.of(names).map(e -> Person.newInstance().setName(e)).collect(Collectors.toList());
        /* 打印所有 person 姓名 */
        this.persons.stream().map(e -> "[test_02_generatePersons]====>" + e.getName()).forEach(log::debug);
    }

    @Test
    public void test_03_generatePersons() {
        /* 通过整数流，遍历 names 下标，装入 persons 中*/
        IntStream.range(0, this.names.length)
                // 将 [0-n) 映射为 n 个 Person 对象
                .mapToObj(e -> Person.newInstance().setName(this.names[e]))
                // 将 Person 对象添加到集合中
                .forEach(this.persons::add);
        /* 打印所有 person 姓名 */
        this.persons.stream().map(e -> "[test_03_generatePersons]====>" + e.getName()).forEach(log::debug);
    }

    @Test
    public void test_04_generatePersons() {
        /* Person.newInstance()::setName 写法 Person 只实例化一次， */
        Stream.of(names).map(Person.newInstance()::setName).forEach(this.persons::add);
        /* 打印所有 person 姓名 */
        this.persons.stream().map(e -> "[test_04_generatePersons]====>" + e.getName()).forEach(log::debug);
    }

    @Test
    public void test_05_filter() {
        // ----打印名字中含有赵字的人
        Stream.of(this.names).filter(e -> e.contains("赵")).map(e -> "[test_05_filter]====>" + e).forEach(log::debug);
    }

}






