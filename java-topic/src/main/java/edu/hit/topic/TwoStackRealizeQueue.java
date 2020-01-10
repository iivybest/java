package edu.hit.topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <p>TwoStackRealizeQueue</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @date 2015年6月11日 - 下午5:04:33
 * @versino 1.0
 */
public class TwoStackRealizeQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    TwoStackRealizeQueue() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    //No.1
    //开始写代码，用两个栈实现一个队列
    public void offer(int o) {
        stack1.push(o);
    }

    public int poll() {
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    //end_code
    public static void main(String[] args) {
        TwoStackRealizeQueue queue = new TwoStackRealizeQueue();
        List<Integer> number = new ArrayList<Integer>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        number.add(queue.poll());
        number.add(queue.poll());
        number.add(queue.poll());
        number.add(queue.poll());
        number.add(queue.poll());
        System.out.println(number);
    }
}
