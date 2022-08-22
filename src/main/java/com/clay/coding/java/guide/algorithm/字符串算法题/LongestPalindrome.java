package com.clay.coding.java.guide.algorithm.字符串算法题;

import java.util.HashSet;

/**
 * @author coderclay
 */
public class LongestPalindrome {

    public static int longestPalindrome(String s) {
        if (s.length() == 0) {
            return 0;
        }
        // 用于存放字符
        HashSet<Character> hashSet = new HashSet<>();
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!hashSet.contains(chars[i])) {
                hashSet.add(chars[i]);
            } else {
                hashSet.remove(chars[i]);
                count++;
            }
        }
        return hashSet.isEmpty() ? count * 2 : count * 2 + 1;
    }

    public static int longestPalindrome1(String s) {
        int[] arr = new int[128];
        for (char c : s.toCharArray()) {
            arr[c]++;
        }
        int count = 0;
        for (int v : arr) {
            count += (v % 2);
        }
        return count == 0 ? s.length() : (s.length() - count + 1);
    }

    public static int longestPalindrome2(String s) {
        int[] arr = new int[128];
        for (char c : s.toCharArray()) {
            arr[c]++;
        }
        int count = 0;
        for (int v : arr) {
            count += v / 2 * 2;
            if (v % 2 == 1 && count % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(LongestPalindrome.longestPalindrome("abccccdd"));
    }
}
