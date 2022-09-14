package com.clay.coding.java.guide.algorithm.设计数据结构;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/implement-stack-using-queues/">...</a>
 */
public class LeetCode225_1 {
    class MyStack {

        Queue<Integer> queue1;

        Queue<Integer> queue2;

        public MyStack() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        public void push(int x) {
            if (queue2.isEmpty()) {
                queue2.offer(x);
            } else {
                queue1.offer(x);
                while (!queue2.isEmpty()) {
                    queue1.offer(queue2.poll());
                }
                Queue<Integer> temp = queue1;
                queue1 = queue2;
                queue2 = temp;
            }
        }

        public int pop() {
            return queue2.poll();
        }

        public int top() {
            return queue2.peek();
        }

        public boolean empty() {
            return queue2.isEmpty();
        }
    }
}
