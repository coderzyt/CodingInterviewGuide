package com.clay.coding.java.guide.algorithm;

import java.util.Arrays;

/**
 * @author coderclay
 */
public class Main {

    public static String replaceSpace(String[] strs) {

        // 检查不合法就返回空串
        if (!checkStrs(strs)) {
            return "";
        }
        int len = strs.length;

        StringBuilder res = new StringBuilder();
        Arrays.sort(strs);
        Arrays.stream(strs).forEach(System.out::println);
        int m = strs[0].length();
        int n = strs[len - 1].length();
        int num = Math.min(m, n);
        for (int i = 0; i < num; i++) {
            if (strs[0].charAt(i) == strs[len - 1].charAt(i)) {
                res.append(strs[0].charAt(i));
            } else {
                break;
            }
        }
        return res.toString();
    }

    private static boolean checkStrs(String[] strs) {
        boolean flag = false;
        if (strs != null) {
            // 遍历strs检查元素值
            for (int i = 0; i < strs.length; i++) {
                if (strs[i] != null && strs[i].length() != 0) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        String[] strs = { "customer", "car", "cat" };
//        String[] strs = { "customer", "car", null };
        System.out.println(Main.replaceSpace(strs));
    }
}
