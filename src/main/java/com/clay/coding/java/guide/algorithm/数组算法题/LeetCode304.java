package com.clay.coding.java.guide.algorithm.数组算法题;

/**
 * https://leetcode.cn/problems/range-sum-query-2d-immutable/
 */
public class LeetCode304 {
    /**
     * NumMatrix
     */
    public class NumMatrix {
    
        int[][] sumMatrix;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            if (m == 0 || n == 0) {
                return;
            }
            sumMatrix = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    sumMatrix[i][j] = matrix[i - 1][j - 1] +
                            sumMatrix[i - 1][j] + sumMatrix[i][j - 1] - sumMatrix[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            row1++;
            col1++;
            row2++;
            col2++;
            int diff = sumMatrix[row2][col2] - sumMatrix[row1 - 1][col2] -
                    sumMatrix[row2][col1 - 1] + sumMatrix[row1 - 1][col1 - 1];
            return diff;
        }
    }
}
