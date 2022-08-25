package com.clay.coding.java.guide.algorithm.数组算法题;

/**
 * @author coderclay
 */
public class LeetCode344 {

    // https://leetcode.cn/problems/reverse-string/
    public void reverseString(char[] s) {
        int len = s.length;
        int left = 0, right = len - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {

    }
}
