package com.clay.coding.java.guide.algorithm.nSum问题;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coderclay
 */
public class NSumTarget {

    public List<List<Integer>> nSumTarget(int[] nums, int n, int start, long target) {
        int length = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (n < 2 || length < n) {
            return res;
        }
        if (n == 2) {
            int left = start, right = nums.length - 1;
            while (left < right) {
                int lo = nums[left], hi = nums[right];
                long sum = lo + hi;
                if (sum == target) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(lo);
                    temp.add(hi);
                    res.add(temp);
                    while (left < right && nums[left] == lo) {
                        left++;
                    }
                    while (left < right && nums[right] == hi) {
                        right--;
                    }
                } else if (sum > target) {
                    while (left < right && nums[right] == hi) {
                        right--;
                    }
                } else {
                    while (left < right && nums[left] == lo) {
                        left++;
                    }
                }
            }
        } else {
            for (int i = start; i < length; i++) {
                List<List<Integer>> subRes = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> sub : subRes) {
                    sub.add(nums[i]);
                    res.add(sub);
                }
                while (i < length - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1000000000, 1000000000, 1000000000, 1000000000};

        NSumTarget nSumTarget = new NSumTarget();
        System.out.println(nSumTarget.nSumTarget(nums, 4, 0, -294967296));
    }
}
