package com.clay.coding.java.guide.algorithm.数组算法题;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/wtcaE1/
 */
public class LeetCodeOffer2_16 {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0, len = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                window.put(d, window.get(d) - 1);
            }
            len = right - left > len ? right - left : len;
        }
        return len;
    }
}
