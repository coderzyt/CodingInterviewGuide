package com.clay.coding.java.guide.algorithm.暴力搜索算法;

import java.util.*;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/open-the-lock/">...</a>
 */
public class LeetCode752 {
    Set<String> deads = new HashSet<>();

    Set<String> visited = new HashSet<>();

    int step = 0;

    public int openLock(String[] deadends, String target) {
        for (String s : deadends) {
            deads.add(s);
        }
        return bfs(deadends, target);
    }

    // 将s[j]向上拨动一次
    String plusOne(String s, int j) {
        char[] chars = s.toCharArray();
        if (chars[j] == '9') {
            chars[j] = '0';
        } else {
            chars[j] += 1;
        }
        return new String(chars);
    }

    // 将s[j]向下拨动一次
    String minusOne(String s, int j) {
        char[] chars = s.toCharArray();
        if (chars[j] == '0') {
            chars[j] = '9';
        } else {
            chars[j] -= 1;
        }
        return new String(chars);
    }

    int bfs(String[] deadends, String target) {
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        visited.add("0000");
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                String cur = queue.poll();

                // 判断是否到达终点
                if (deads.contains(cur)) {
                    continue;
                }
                if (Objects.equals(cur, target)) {
                    return step;
                }

                System.out.println(cur);
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    // 双向bfs
//    int openLock(String[] deadends, String target) {
//        Set<String> deads = new HashSet<>();
//        for (String s : deadends) {
//            deads.add(s);
//        }
//        Set<String> q1 = new HashSet<>();
//        Set<String> q2 = new HashSet<>();
//        Set<String> visited = new HashSet<>();
//
//        int step = 0;
//        q1.add("0000");
//        q2.add(target);
//        while (!q1.isEmpty() && !q2.isEmpty()) {
//            // 哈希集合在遍历的过程中不能修改，用temp暂存扩散结果
//            Set<String> temp = new HashSet<>();
//
//            /*
//            将q1中的所有节点向周围扩散
//             */
//            for (String cur : q1) {
//                if (deads.contains(cur)) {
//                    continue;
//                }
//                if (q2.contains(cur)) {
//                    return step;
//                }
//                visited.add(cur);
//                for (int i = 0; i < 4; i++) {
//                    String up = plusOne(cur, i);
//                    if (!visited.contains(up)) {
//                        temp.add(up);
//                    }
//                    String down = minusOne(cur, i);
//                    if (!visited.contains(down)) {
//                        temp.add(down);
//                    }
//                }
//            }
//            step++;
//            q1 = q2;
//            q2 = temp;
//        }
//        return -1;
//    }
}
