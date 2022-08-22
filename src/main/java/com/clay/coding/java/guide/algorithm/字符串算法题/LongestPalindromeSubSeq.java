package com.clay.coding.java.guide.algorithm.字符串算法题;

/**
 * @author coderclay
 */
public class LongestPalindromeSubSeq {

    public static int longestPalindromeSubSeq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                char c1 = s.charAt(i);
                char c2 = s.charAt(j);
                if (c1 == c2) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    int dp1 = dp[i + 1][j];
                    int dp2 = dp[i][j - 1];
                    dp[i][j] = Math.max(dp1, dp2);
                }
            }
        }
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubSeq("bbbababa"));
    }
}
