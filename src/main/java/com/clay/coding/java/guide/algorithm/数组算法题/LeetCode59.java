package com.clay.coding.java.guide.algorithm.数组算法题;

public class LeetCode59 {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int upper_bound = 0, lower_bound = n - 1, left_bound = 0, right_bound = n - 1;
        int count = 1;
        while (count <= n * n) {
            if (upper_bound <= lower_bound) {
                for (int i = left_bound; i <= right_bound; i++) {
                    res[upper_bound][i] = count++;
                }
                upper_bound++;
            }
            if (left_bound <= right_bound) {
                for (int i = upper_bound; i <= lower_bound; i++) {
                    count++;
                    res[i][right_bound] = count++;
                }
                right_bound--;
            }
            if (upper_bound <= lower_bound) {
                for (int i = right_bound; i >= left_bound; i--) {
                    res[lower_bound][i] = count++;
                }
                lower_bound--;
            }
            if (left_bound <= right_bound) {
                for (int i = lower_bound; i >= upper_bound; i--) {
                    res[i][left_bound] = count++;
                }
                left_bound++;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        LeetCode59 leetCode59 = new LeetCode59();
        System.out.println(leetCode59.generateMatrix(2));
        
    }
}
