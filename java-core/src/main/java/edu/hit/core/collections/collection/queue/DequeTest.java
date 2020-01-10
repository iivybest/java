package edu.hit.core.collections.collection.queue;

import edu.hit.core.collections.collection.model.Person;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName: DequeTest
 * @author: ivybest imiaodev@163.com
 * @date: 2019年3月21日 下午5:13:57
 *
 * <br>----------------------------------------
 * Deque	Double Ended Queue
 * <p>
 * 扩展字Queue
 * <p>
 * 添加队首、添加队尾
 * 获取队首、获取队尾
 * <p>
 * 实现类： ArrayDeque，LinkedList
 *
 *
 * <br>----------------------------------------
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DequeTest {

    @Test
    public void test() {
        Deque<Person> queue = new ArrayDeque<Person>();
        queue.offerLast(new Person("Nani", 20));
        queue.offerLast(new Person("Mata", 30));
        queue.offerLast(new Person("Rojo", 28));
        queue.offerFirst(new Person("Tony", 22));

        Person p = queue.pollFirst();
        System.out.println(p);
        p = queue.pollLast();
        System.out.println(p);
    }
}
