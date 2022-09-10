package com.clay.coding.java.guide.algorithm.数组算法题;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/advantage-shuffle/">...</a>
 */
public class LeetCode870 {

    int[] advantageCount(int[] nums1, int[] nums2) {

        int n = nums1.length;

        PriorityQueue<int[]> maxq = new PriorityQueue<>((int[] pair1, int[] pair2) -> pair2[1] - pair1[1]);
        for (int i = 0; i < n; i++) {
            // 升序排列
            maxq.offer(new int[]{i, nums2[i]});
        }

        // 升序排列
        Arrays.sort(nums1);

        int left = 0, right = n - 1;
        int[] res = new int[n];

        while (!maxq.isEmpty()) {
            int[] pair = maxq.poll();

            int i = pair[0], maxVal = pair[1];
            if (maxVal < nums1[right]) {
                res[i] = nums1[right];
                right--;
            } else {
                res[i] = nums1[left];
                left++;
            }
        }
        return res;
    }
}
