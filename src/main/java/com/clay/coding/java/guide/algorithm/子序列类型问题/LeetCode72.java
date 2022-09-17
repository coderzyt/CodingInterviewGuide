package com.clay.coding.java.guide.algorithm.子序列类型问题;

/**
 * @author coderclay
 *         <a href="https://leetcode.cn/problems/edit-distance/">...</a>
 */
public class LeetCode72 {

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        return dp(word1, m - 1, word2, n - 1);
    }

    int dp(String word1, int i, String word2, int j) {
        if (i == -1) {
            return j + 1;
        }
        if (j == -1) {
            return i + 1;
        }
        if (word1.charAt(i) == word2.charAt(j)) {
            return dp(word1, i - 1, word2, j - 1);
        }
        return min(dp(word1, i, word2, j - 1) + 1,
                dp(word1, i - 1, word2, j) + 1,
                dp(word1, i - 1, word2, j - 1) + 1);
    }

    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public static void main(String[] args) {
        LeetCode72 leetCode72 = new LeetCode72();
        System.out.println(leetCode72.minDistance("horse", "ros"));
    }
}
