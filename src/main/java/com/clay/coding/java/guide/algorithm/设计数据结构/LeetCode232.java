package com.clay.coding.java.guide.algorithm.设计数据结构;

import java.util.Stack;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/implement-queue-using-stacks/">...</a>
 */
public class LeetCode232 {

    class MyQueue {

        Stack<Integer> stack1;

        Stack<Integer> stack2;

        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void push(int x) {
            if (stack2.empty()) {
                stack2.push(x);
            } else {
                while (!stack2.isEmpty()) {
                    stack1.push(stack2.pop());
                }
                stack2.push(x);
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
        }

        public int pop() {
            return stack2.pop();
        }

        public int peek() {
            return stack2.peek();
        }

        public boolean empty() {
            return stack2.isEmpty();
        }
    }
}
