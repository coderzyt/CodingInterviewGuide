package com.clay.coding.java.guide.algorithm.数组算法题;

import java.util.Stack;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/smallest-subsequence-of-distinct-characters/">...</a>
 */
public class LeetCode1081 {

    public String smallestSubSequence(String s) {
        Stack<Character> stack = new Stack<>();
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        for (char ch : s.toCharArray()) {
            if (!stack.contains(ch)) {
                while (!stack.isEmpty() && stack.peek() > ch && count[stack.peek() - 'a'] > 0) {
                    stack.pop();
                }
                stack.push(ch);
            }
            count[ch - 'a']--;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.reverse().toString();
    }
}
