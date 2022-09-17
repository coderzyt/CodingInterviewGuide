package com.clay.coding.java.guide.algorithm.子序列类型问题;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/edit-distance/">...</a>
 */
public class LeetCode72_1 {

    int[][] memo;
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = -1;
            }
        }
        return dp(word1, m - 1, word2, n - 1);
    }

    int dp(String word1, int i, String word2, int j) {
        if (i == -1) {
            return j + 1;
        }
        if (j == -1) {
            return i + 1;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (word1.charAt(i) == word2.charAt(j)) {
            memo[i][j] = dp(word1, i - 1, word2, j - 1);
        } else {
            int insert = dp(word1, i, word2, j - 1) + 1;
            int delete = dp(word1, i - 1, word2, j) + 1;
            int replace = dp(word1, i - 1, word2, j - 1) + 1;
            memo[i][j] = min(insert, delete, replace);
        }
        return memo[i][j];
    }

    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public static void main(String[] args) {
        LeetCode72_1 leetCode72_1 = new LeetCode72_1();
        System.out.println(leetCode72_1.minDistance("s", "e"));
    }
}
