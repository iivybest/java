package edu.hit.core.collections.collection.queue;

import edu.hit.core.collections.collection.model.Person;
import edu.hit.core.collections.collection.model.comparator.PersonNameComparator;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @ClassName: PriorityQueueTest
 * @author: ivybest imiaodev@163.com
 * @date: 2019年3月21日 下午4:04:53
 * :
 * <br>-----------------------------------
 * PriorityQueue 优先级队列
 * 优先级高的先取出
 * PriorityQueue 中元素必须实现 comparable接口，
 * 或者PriorityQueue 添加 comparator接口
 * <br>-----------------------------------
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)    //  按字母排序运行测试方法
public class PriorityQueueTest {

    @Test
    public void test() {
//		Queue<Person> queue = new PriorityQueue<Person>();
        Queue<Person> queue = new PriorityQueue<Person>(new PersonNameComparator());
        queue.offer(new Person("Tony", 20));
        queue.offer(new Person("Nani", 21));
        queue.offer(new Person("Mata", 30));
        queue.offer(new Person("Rojo", 28));

//		while (!queue.isEmpty()) System.out.println(queue.poll());
        queue.stream().forEach(System.out::println);

    }

}






