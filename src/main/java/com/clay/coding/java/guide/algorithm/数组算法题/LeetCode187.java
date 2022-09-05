package com.clay.coding.java.guide.algorithm.数组算法题;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/repeated-dna-sequences/
 */
public class LeetCode187 {
    public List<String> findRepeatedDnaSequences1(String s) {
        int n = s.length();
        HashSet<String> seen = new HashSet<>();
        HashSet<String> res = new HashSet<>();
        for (int i = 0; i + 10 <= n; i++) {
            String subStr = s.substring(i, i + 10);
            if (seen.contains(subStr)) {
                res.add(subStr);
            } else {
                seen.add(subStr);
            }
        }
        return new LinkedList<>(res);
    }

    public List<String> findRepeatedDnaSequences(String s) {
        int[] nums = new int[s.length()];
        for (int i = 0; i < nums.length; i++) {
            switch (s.charAt(i)) {
                case 'A':
                    nums[i] = 0;
                    break;
                case 'G':
                    nums[i] = 1;
                    break;
                case 'C':
                    nums[i] = 2;
                    break;
                case 'T':
                    nums[i] = 3;
                    break;
            }
        }
        HashSet<Integer> seen = new HashSet<>();
        HashSet<String> res = new HashSet<>();

        int L = 10;
        int R = 4;
        int RL = (int) Math.pow(R, L - 1);
        int windowHash = 0;

        int left = 0, right = 0;
        while (right < nums.length) {
            windowHash = R * windowHash + nums[right];
            right++;
            if (right - left == L) {
                if (seen.contains(windowHash)) {
                    res.add(s.substring(left, right));
                } else {
                    seen.add(windowHash);
                }
                windowHash = windowHash - nums[left] * RL;
                left++;
            }
        }
        return new LinkedList<>(res);
    }
}
