package com.clay.coding.java.guide.algorithm.经典面试题.括号问题;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/minimum-insertions-to-balance-a-parentheses-string/">...</a>
 */
public class LeetCode1541 {

    public int minInsertions(String s) {
        int needLeft = 0, needRight = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                needRight += 2;
                if (needRight % 2 == 1) {
                    needRight--;
                    needLeft++;
                }
            }
            if (ch == ')') {
                needRight--;
                if (needRight == -1) {
                    needLeft++;
                    needRight = 1;
                }
            }
        }
        return needLeft + needRight;
    }

    public static void main(String[] args) {
        LeetCode1541 leetCode1541 = new LeetCode1541();
        System.out.println(leetCode1541.minInsertions("()()"));
    }
}
