package com.clay.coding.java.guide.algorithm.数组算法题;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/spiral-matrix/
 */
public class LeetCode54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int upper_bound = 0, lower_bound = m - 1, left_bound = 0, right_bound = n - 1;
        List<Integer> res = new LinkedList<>();
        // res.size() == m * n 则遍历完 整个数组
        while (res.size() < m * n) {
            if (upper_bound <= lower_bound) {
                // 在顶部从左向右遍历
                for (int i = left_bound; i <= right_bound; i++) {
                    res.add(matrix[upper_bound][i]);
                }
                // 上边界下移
                upper_bound++;
            }

            if (left_bound <= right_bound) {
                // 在右侧从上向下遍历
                for (int i = upper_bound; i <= lower_bound; i++) {
                    res.add(matrix[i][right_bound]);
                }
                // 右边界左移
                right_bound--;
            }

            if (upper_bound <= lower_bound) {
                for (int i = right_bound; i >= left_bound; i--) {
                    res.add(matrix[lower_bound][i]);
                }
                // 下边界上移
                lower_bound--;
            }

            if (left_bound <= right_bound) {
                for (int i = lower_bound; i >= upper_bound; i--) {
                    res.add(matrix[i][left_bound]);
                }
                // 左边界右移
                left_bound++;
            }
        }
        return res;
    }
}
