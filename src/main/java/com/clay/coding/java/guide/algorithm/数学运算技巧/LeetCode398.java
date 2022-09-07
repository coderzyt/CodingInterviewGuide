package com.clay.coding.java.guide.algorithm.数学运算技巧;

import java.util.Random;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/random-pick-index/">...</a>
 */
public class LeetCode398 {

    int[] nums;

    Random random;

    public LeetCode398(int[] nums) {
        random = new Random();
        this.nums = nums;
    }

    public int pick(int target) {
        int count = 0, res = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) {
                continue;
            }
            count++;
            if (random.nextInt(count) == 0) {
                res = i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
       LeetCode398 leetCode398 = new LeetCode398(new int[]{1, 2, 3, 3, 3});
       System.out.println(leetCode398.pick(3));
       System.out.println(leetCode398.pick(1));
       System.out.println(leetCode398.pick(3));
    }
}
