package com.clay.coding.java.guide.algorithm.数组算法题;

import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/create-maximum-number/">...</a>
 */
public class LeetCode321 {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        for (int i = 0; i < k + 1; i++) {
            if (i <= nums1.length && k - i <= nums2.length) {
                int[] mergeResult = merge(pickMax(nums1, i), pickMax(nums2, k - i));
                res = compare(mergeResult, 0, res, 0) ? mergeResult : res;
            }
        }
        return res;
    }

    private List<Integer> pickMax(int[] nums, int k) {
        List<Integer> list = new LinkedList<>();
        int drop = nums.length - k;
        for (int num : nums) {
            while (drop > 0 && !list.isEmpty() && list.get(list.size() - 1) < num) {
                list.remove(list.size() - 1);
                drop--;
            }
            list.add(num);
        }
        if (k > list.size()) {
            return list;
        }
        return list.subList(0, k);
    }

    private int[] merge(List<Integer> list1, List<Integer> list2) {
        int l1 = list1.size(), l2 = list2.size();
        int[] res = new int[l1 + l2];
        int cur = 0, p1 = 0, p2 = 0;
        while (cur < l1 + l2) {
            if (compare(list1, p1, list2, p2)) {
                res[cur++] = list1.get(p1++);
            } else {
                res[cur++] = list2.get(p2++);
            }
        }
        return res;
    }

    private boolean compare(List<Integer> nums1, int p1, List<Integer> nums2, int p2) {
        if (p2 >= nums2.size()) {
            return true;
        }
        if (p1 >= nums1.size()) {
            return false;
        }
        if (nums1.get(p1) > nums2.get(p2)) {
            return true;
        }
        if (nums1.get(p1) < nums2.get(p2)) {
            return false;
        }
        return compare(nums1, p1 + 1, nums2, p2 + 1);
    }

    private boolean compare(int[] nums1, int p1, int[] nums2, int p2) {
        if (p2 >= nums2.length) {
            return true;
        }
        if (p1 >= nums1.length) {
            return false;
        }
        if (nums1[p1] > nums2[p2]) {
            return true;
        }
        if (nums1[p1] < nums2[p2]) {
            return false;
        }
        return compare(nums1, p1 + 1, nums2, p2 + 1);
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{3, 4, 6, 5};
        int[] nums2 = new int[]{9, 1, 2, 5, 8, 3};
        int k = 5;
        LeetCode321 leetCode321 = new LeetCode321();
        leetCode321.maxNumber(nums1, nums2, k);
    }
}
