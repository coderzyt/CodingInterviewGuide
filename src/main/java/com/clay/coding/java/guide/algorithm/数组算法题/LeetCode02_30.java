package com.clay.coding.java.guide.algorithm.数组算法题;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/FortPu/">...</a>
 */
public class LeetCode02_30 {

    public class RandomizedSet {

        Map<Integer, Integer> valToIndex;

        LinkedList<Integer> nums;

        Random random;
        public RandomizedSet() {
            random = new Random();
            valToIndex = new HashMap<>();
            nums = new LinkedList<>();
        }

        public boolean insert(int val) {
            if (valToIndex.containsKey(val)) {
                return false;
            }
            nums.addLast(val);
            valToIndex.put(val, nums.size() - 1);
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

            nums.removeLast();
            valToIndex.remove(val);

            return true;
        }

        public int getRandom() {
            return nums.get(random.nextInt(nums.size()));
        }
    }
}
