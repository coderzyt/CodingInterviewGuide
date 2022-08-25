package com.clay.coding.java.guide.algorithm.数组算法题;

/**
 * @author coderclay
 */
public class LeetCode125 {

    // https://leetcode.cn/problems/valid-palindrome/
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() == 0 || s.length() == 1) {
            return true;
        }
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            } else if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            } else {
                if (Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right))) {
                    left++;
                    right--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
