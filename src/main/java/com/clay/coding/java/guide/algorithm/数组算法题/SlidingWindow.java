package com.clay.coding.java.guide.algorithm.数组算法题;

import java.util.HashMap;

/**
 * @author coderclay
 */
public class SlidingWindow {
    /* 滑动窗口算法框架 */
    void slidingWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0));
        }
        int left = 0, right = 0;
        int valid = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            // 右移（增大）窗口
            right++;
            // 进行窗口内数据的一系列更新
            while (true /*window needs shrink*/) {
                char d = s.charAt(left);
                // 左移（缩小）窗口
                left++;
                // 进行窗口内数据的一系列更新
            }
        }
    }
}
