package com.clay.coding.java.guide.algorithm.暴力搜索算法;

import java.util.*;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/zlDJc7/">...</a>
 */
public class LeetCodeOffer2_109 {

    Set<String> visited = new HashSet<>();
    Set<String> deads = new HashSet<>();

    public int openLock(String[] deadends, String target) {
        for (String dead : deadends) {
            deads.add(dead);
        }
        return bfs(deadends, target);
    }

    int bfs(String[] deadends, String target) {
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        visited.add("0000");
        int step = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                String cur = queue.poll();
                if (Objects.equals(cur, target)) {
                    return step;
                }
                if (deads.contains(cur)) {
                    continue;
                }
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        visited.add(up);
                        queue.offer(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        visited.add(down);
                        queue.offer(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    String plusOne(String s, int j) {
        char[] chars = s.toCharArray();
        if (chars[j] == '9') {
            chars[j] = '0';
        } else {
            chars[j] += 1;
        }
        return new String(chars);
    }

    String minusOne(String s, int j) {
        char[] chars = s.toCharArray();
        if (chars[j] == '0') {
            chars[j] = '9';
        } else {
            chars[j] -= 1;
        }
        return new String(chars);
    }
}
