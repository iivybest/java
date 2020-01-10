package edu.hit.core.collections.collection.queue;

import edu.hit.core.collections.collection.model.Person;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName: QueueTest
 * @author: ivybest imiaodev@163.com
 * @date: 2019年3月21日 下午4:54:11
 *
 * <br>------------------------------------------
 * Queue 队列
 * 先进先出
 * -----------------------------------------
 * |	throw	|	rtn false/null
 * ------------|-----------|----------------
 * 添加元素到队尾	|	add		|	offer
 * 取队首并删除	|	remove	|	poll
 * 取队首不删除	|	element	|	peek
 * -----------------------------------------
 *
 *
 *
 * <br>------------------------------------------
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)    //  按字母排序运行测试方法
public class QueueTest {

    @Test
    public void test_01_baseOperation() {
        Queue<Person> queue = new LinkedList<Person>();
        queue.offer(new Person("Tony", 20));
        queue.offer(new Person("Nani", 21));
        queue.offer(new Person("Mata", 30));
        queue.offer(new Person("Rojo", 28));

        System.out.println(queue.isEmpty());
        Person person = queue.peek();
        System.out.println(person);
        Person firstPerson = queue.poll();
        System.out.println(firstPerson);
        firstPerson = queue.poll();
        System.out.println(firstPerson);
    }


}
