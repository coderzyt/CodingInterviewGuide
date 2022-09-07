package com.clay.coding.java.guide.algorithm.数组算法题;

import java.util.*;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/random-pick-with-blacklist/">...</a>
 */
public class LeetCode710 {

    HashMap<Integer, Integer> whiteList;

    Random random;

    int bound;

    public LeetCode710(int n, int[] blackList) {
        random = new Random();
        whiteList = new HashMap<>();
        int blackLen = blackList.length;
        bound = n - blackLen;

        HashSet<Integer> black = new HashSet<>();
        for (int i = 0; i < blackLen; i++) {
            if (blackList[i] >= bound) {
                black.add(blackList[i]);
            }
        }

        int w = bound;
        for (int b : blackList) {
            if (b < bound) {
                while (black.contains(w)) {
                    w = w + 1;
                }
                whiteList.put(b, w);
                w = w + 1;
            }
        }
    }

    public int pick() {
        int x = random.nextInt(bound);
        return whiteList.getOrDefault(x, x);
    }

    public static void main(String[] args) {
        LeetCode710 leetCode710 = new LeetCode710(7, new int[]{2, 3, 5});

    }
}
