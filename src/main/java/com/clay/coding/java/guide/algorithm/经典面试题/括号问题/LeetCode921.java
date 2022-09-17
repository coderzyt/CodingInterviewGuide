package com.clay.coding.java.guide.algorithm.经典面试题.括号问题;

import java.util.Stack;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid/">...</a>
 */
public class LeetCode921 {

    public int minAddToMakeValid(String s) {
        if (s.length() == 0) {
            return  0;
        }
        int count = 0;
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push('(');
            }
            if (ch == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(')');
                }
            }
        }
        return stack.size();
    }

    public int minAddToMakeValid1(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int need = 0;
        int left = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                need++;
                left++;
            }
            if (ch == ')') {
                if (need != 0 && left != 0) {
                    need--;
                    left--;
                } else {
                    need++;
                }
            }
        }
        return need;
    }

    public int minAddToMakeValid2(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int needLeft = 0;
        int needRight = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                needRight++;
            }
            if (ch == ')') {
                needRight--;
                if (needRight == -1) {
                    needLeft++;
                    needRight = 0;
                }
            }
        }
        return needRight + needLeft;
    }

    public static void main(String[] args) {
        LeetCode921 leetCode921 = new LeetCode921();
        System.out.println(leetCode921.minAddToMakeValid2("(()))"));
    }
}
