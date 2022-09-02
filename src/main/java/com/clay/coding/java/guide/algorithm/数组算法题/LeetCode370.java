package com.clay.coding.java.guide.algorithm.数组算法题;

public class LeetCode370 {
    /**
     * 假设你有一个长度为n的数组，初始情况下所有的数字均为0，你将会被给出k个更新的操作。
     * 其中，每个操作会被表示为一个三元组：[startIndex, endIndex, inc]，你需要将子数组
     * A[startIndex ... endIndex]增加inc。
     * 请你返回k次操作后的数组。
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] diff = new int[length];
        for (int i = 0; i < updates.length; i++) {
            int a, b, inc;
            a = updates[i][0];
            b = updates[i][1];
            inc = updates[i][2];
            diff[a] += inc;
            if (b < length - 1) {
                diff[b + 1] -= inc;
            }
        }
        // 还原数组
        int[] res = new int[length];
        res[0] = diff[0];
        for (int i = 1; i < length; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }
}
