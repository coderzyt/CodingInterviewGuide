package com.clay.coding.java.guide.algorithm.数组算法题;

/**
 * https://leetcode.cn/problems/reverse-words-in-a-string/
 */
public class LeetCode151 {
    public String reverseWords(String s) {
        String[] strs = s.split(" ");
        int len = strs.length;
        int left = 0, right = len - 1;
        while (left < right) {
            String temp = strs[left];
            strs[left] = strs[right];
            strs[right] = temp;
            left++;
            right--;
        }
        String result = "";
        for (int i = 0; i < len; i++) {
            if (!strs[i].equals("")) {
                result += strs[i] + " ";
            }
        }
        return result.trim();
    }

    public static void main(String[] args) {
        LeetCode151 leetCode151 = new LeetCode151();
        leetCode151.reverseWords("  hello world  ");
    }
}
