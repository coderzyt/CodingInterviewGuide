package com.clay.coding.java.guide.algorithm.数组算法题;

/**
 * https://leetcode-cn.com/problems/car-pooling/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 */
public class LeetCode1094 {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] diff = new int[1001];
        for (int i = 0; i < trips.length; i++) {
            int a, b, inc;
            a = trips[i][1];
            b = trips[i][2] - 1;
            inc = trips[i][0];
            diff[a] += inc;
            if (b < 1000) {
                diff[b + 1] -= inc;
            }
        }
        int[] res = new int[1001];
        res[0] = diff[0];
        for (int i = 1; i < 1001; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        for (int i = 0; i < 1001; i++) {
            if (res[i] > capacity) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] trips = {{2, 1, 5}, {3, 5, 7}};
        LeetCode1094 leetCode1094 = new LeetCode1094();
        System.out.println(leetCode1094.carPooling(trips, 3));
    }
}
