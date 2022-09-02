package com.clay.coding.java.guide.algorithm.数组算法题;

/**
 * https://leetcode.cn/problems/corporate-flight-bookings/
 */
public class LeetCode1109 {
    int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n];
        for (int i = 0; i < bookings.length; i++) {
            int a, b, inc;
            a = bookings[i][0];
            b = bookings[i][1];
            inc = bookings[i][2];
            diff[a - 1] += inc;
            if (b < n) {
                diff[b] -= inc;
            }
        }
        int[] res = new int[n];
        res[0] = diff[0];
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }
}
