package edu.hit.core.collections.collection.list;

import java.util.LinkedList;

/**
 * <p>LinkedListTest</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年7月9日 - 下午3:58:54
 */
public class LinkedListTest {
    /*
     * 利用linkedlist 模拟 堆栈stack，队列queue
     *
     * Stack Fist in Last out
     *
     * Queue Fist in Fist out
     *
     */


}


class MyQueue<E> {
    private LinkedList<E> link;

    {
        this.link = new LinkedList<E>();
    }

    public void add(E e) {
        this.link.add(e);
    }

    public E get() {
        return this.link.removeFirst();
    }

}