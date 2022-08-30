package com.clay.coding.java.guide.algorithm.字符串算法题;

/**
 * @author coderclay
 */
public class Solution {
    /**
     * 把 We Are Happy 替换成 We%20Are%20Happy
     */
    public static String replaceSpace(String target) {
        int length = target.length();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char ch = target.charAt(i);
            if (ch == ' ') {
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }

    public static String replaceSpace2(String target) {
        return target.replaceAll("\\s", "%20");
    }

    public static String replaceSpace3(String target) {
        return target.replace(" ", "%20");
    }

    public static void main(String[] args) {
        String result = replaceSpace("We Are Happy");
        String result2 = replaceSpace2("We Are Happy");
        String result3 = replaceSpace3("We Are Happy");
        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);
    }
}
