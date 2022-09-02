package com.clay.coding.java.guide.algorithm.数组算法题;

public class LeetCode303 {
    
    /**
     * NumArray
     * https://leetcode.cn/problems/range-sum-query-immutable/
     */
    public class NumArray {

        private int[] preSum;
    
        public NumArray(int[] nums) {
            preSum = new int[nums.length];
            preSum[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i];
            } 
        }

        public int sumRange(int left, int right) {
            return left == 0 ? preSum[right] : preSum[right] - preSum[left - 1];
        }
    }
}
