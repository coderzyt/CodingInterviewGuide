package com.clay.coding.java.guide.algorithm.经典面试题.二分查找判定子序列;

import java.util.ArrayList;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/number-of-matching-subsequences/">...</a>
 */
public class LeetCode792 {

    public int numMatchingSubseq(String s, String[] words) {
        int sLength = s.length();
        ArrayList<Integer>[] charToIndex = new ArrayList[256];
        for (int i = 0; i < sLength; i++) {
            char ch = s.charAt(i);
            if (charToIndex[ch] == null) {
                charToIndex[ch] = new ArrayList<>();
            }
            charToIndex[ch].add(i);
        }
        int count = 0;
        for (String word : words) {
            int wordIndex = 0;
            int i = 0;
            for ( ; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (charToIndex[ch] == null) {
                    break;
                }
                int pos = searchLeftBound(charToIndex[ch], wordIndex);
                if (pos == -1) {
                    break;
                }
                wordIndex = charToIndex[ch].get(pos) + 1;
            }
            if (i == word.length()) {
                count++;
            }
        }
        return count;
    }

    int searchLeftBound(ArrayList<Integer> arr, int target) {
        int left = 0, right = arr.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            int cur = arr.get(mid);
            if (cur == target) {
                right = mid;
            } else if (cur > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (left == arr.size()) {
            return -1;
        }
        return left;
    }

    public int numMatchingSubseq1(String s, String[] words) {
        int count = 0;
        for (String word : words) {
            if (isSubSequence(s, word)) {
                count++;
            }
        }
        return count;
    }

    boolean isSubSequence(String s, String word) {
        int m = word.length(), n = s.length();
        if (m > n) {
            return false;
        }
        int sIndex = 0, wIndex = 0;
        while (sIndex < n && wIndex < m) {
            if (s.charAt(sIndex) == word.charAt(wIndex)) {
                wIndex++;
            }
            sIndex++;
        }
        return wIndex == m;
    }
}
