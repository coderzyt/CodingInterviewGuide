package com.clay.coding.java.guide.algorithm.设计数据结构;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/">...</a>
 */
public class LeetCodeOffer09 {

    class CQueue {

        Deque<Integer> inStack;

        Deque<Integer> outStack;

        public CQueue() {
            inStack = new ArrayDeque<>();
            outStack = new ArrayDeque<>();
        }

        public void appendTail(int value) {
            inStack.push(value);
        }

        public int deleteHead() {
            if (outStack.isEmpty()) {
                if (inStack.isEmpty()) {
                    return -1;
                }
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
            return outStack.pop();
        }
    }
}
