package com.clay.coding.java.guide.algorithm.数组算法题;

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

    /**
     * @param args
     */
    public static void main(String[] args) {
        // int[][] nums = { { 3, 0, 1, 4, 2 }, { 5, 6, 3, 2, 1 }, { 1, 2, 0, 1, 5 }, { 4, 1, 0, 1, 7 },
        //         { 1, 0, 3, 0, 5 } };
        int[][] nums = {{-4, -5}};
        NumMatrix numMatrix = new NumMatrix(nums);
        // System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(0, 0, 0, 0));
    }
}
