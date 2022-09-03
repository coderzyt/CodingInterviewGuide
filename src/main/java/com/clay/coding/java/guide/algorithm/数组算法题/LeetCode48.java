package com.clay.coding.java.guide.algorithm.数组算法题;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/rotate-image/">...</a>
 */
public class LeetCode48 {

    // 将二维矩阵原地顺时针旋转90度
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 沿着对角线镜像对称二维矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i != j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
        // 反转二维矩阵的每一行
        for (int i = 0; i < n; i++) {
            reverse(matrix[i]);
        }
    }


    // 将二维矩阵原地逆时针旋转90度
    void rotate2(int[][] matrix) {
        int n = matrix.length;
        // 沿着左下到右上的对角线镜像对称二维矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][n - i - 1];
                matrix[n - j - 1][n - i - 1] = temp;
            }
        }
        // 反转每一行
        for (int[] row : matrix) {
            reverse(row);
        }
    }

    // 反转一维数组
    void reverse(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}
