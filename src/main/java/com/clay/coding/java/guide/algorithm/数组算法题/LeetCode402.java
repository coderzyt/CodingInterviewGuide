package com.clay.coding.java.guide.algorithm.数组算法题;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/remove-k-digits/">...</a>
 */
public class LeetCode402 {
    public String removeKdigits(String num, int k) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char digit : num.toCharArray()) {
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(digit);
        }
        for (int i = 0; i < k; i++) {
            deque.pollLast();
        }
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            char ch = deque.pollFirst();
            if (leadingZero && ch == '0') {
                continue;
            }
            leadingZero = false;
            sb.append(ch);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {

    }
}
