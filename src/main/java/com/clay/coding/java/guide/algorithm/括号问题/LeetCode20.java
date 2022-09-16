package com.clay.coding.java.guide.algorithm.括号问题;

import java.util.Stack;

/**
 * @author coderclay
 * https://leetcode.cn/problems/valid-parentheses/
 */
public class LeetCode20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '[' || ch == '(' || ch == '{') {
                stack.push(ch);
            } else {
                if (ch == ']' && !stack.isEmpty() && stack.peek() == '[') {
                    stack.pop();
                } else if (ch == ')' && !stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else if (ch == '}' && !stack.isEmpty() && stack.peek() == '{') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        LeetCode20 leetCode20 = new LeetCode20();
        System.out.println(leetCode20.isValid("]"));
    }
}
