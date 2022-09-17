package com.clay.coding.java.guide.algorithm.经典面试题.二分查找判定子序列;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/is-subsequence/">...</a>
 */
public class LeetCode392 {

    public boolean isSubSequence(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }
        int sIndex = 0, tIndex = 0;
        while (sIndex < s.length() && tIndex < t.length()) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                sIndex++;
            }
            tIndex++;
        }
        return sIndex == s.length();
    }

    public boolean[] isSubSequence(String[] sn, String t) {
        boolean[] res = new boolean[sn.length];
        for (int i = 0; i < sn.length; i++) {
            res[i] = isSubSequence(sn[i], t);
        }
        return res;
    }

//    public boolean[] isSubSequence(String[] sn, String t, ArrayList<Integer>[] index) {
//        for (int i = 0; i < t.length(); i++) {
//            char c = t.charAt(i);
//            if (index[c] == null) {
//                index[c] = new ArrayList<>();
//            }
//            index[c].add(i);
//        }
//        int tIndex = 0;
//    }

    boolean isSubSequence1(String s, String t) {
        int m = s.length(), n = t.length();
        ArrayList<Integer>[] index = new ArrayList[256];
        for (int i = 0; i < n; i++) {
            char c = t.charAt(i);
            if (index[c] == null) {
                index[c] = new ArrayList<>();
            }
            index[c].add(i);
        }
        int j = 0;
        for (int i = 0; i < m; i++) {
            char c = s.charAt(i);
            if (index[c] == null) {
                return false;
            }
            int pos = searchLeftBound(index[c], j);
            if (pos == - 1) {
                return false;
            }
            j = index[c].get(pos) + 1;
        }
        return true;
    }

    int searchLeftBound(ArrayList<Integer> arr, int target) {
        int left = 0, right = arr.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            int cur = arr.get(mid);
            if (cur == target) {
                right = mid;
            } else if (cur > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (left == arr.size()) {
            return -1;
        }
        return left;
    }

    public static void main(String[] args) {
        LeetCode392 leetCode392 = new LeetCode392();
        System.out.println(leetCode392.isSubSequence1("abbc", "ahbbbcgdc"));
    }
}
