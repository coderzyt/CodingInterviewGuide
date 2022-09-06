package com.clay.coding.java.guide.algorithm.数组算法题;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/insert-delete-getrandom-o1/">...</a>
 */
public class LeetCode380 {
    /**
     * InnerLeetCode380
     */
    public class RandomizedSet {

        LinkedList<Integer> nums;

        HashMap<Integer, Integer> valToIndex;

        public RandomizedSet() {
            nums = new LinkedList<>();
            valToIndex = new HashMap<>();
        }

        public boolean insert(int val) {
            if (valToIndex.containsKey(val)) {
                return false;
            }
            valToIndex.put(val, nums.size());
            nums.addLast(val);
            return true;
        }

        public boolean remove(int val) {
            if (!valToIndex.containsKey(val)) {
                return false;
            }
            int index = valToIndex.get(val);
            int last = nums.getLast();

            nums.set(index, last);
            valToIndex.put(last, index);

            nums.remove(nums.size() - 1);
            valToIndex.remove(val);

            return  true;
        }

        public int getRandom() {
            return nums.get((int) (Math.random() * nums.size()));
        }
    }
}
