package com.clay.coding.java.guide.algorithm.设计数据结构;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/implement-stack-using-queues/">...</a>
 */
public class LeetCode225 {

    class MyStack {

        Queue<Integer> queue;

        int topElement = 0;
        public MyStack() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            queue.offer(x);
            topElement = x;
        }

        public int pop() {
            int size = queue.size();
            while (size > 2) {
                queue.offer(queue.poll());
                size--;
            }
            topElement = queue.peek();
            queue.offer(queue.poll());
            return queue.poll();
        }

        public int top() {
            return topElement;
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }
}
