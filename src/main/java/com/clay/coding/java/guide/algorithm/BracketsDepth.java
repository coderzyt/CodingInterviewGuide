package com.clay.coding.java.guide.algorithm;

import java.util.Scanner;

/**
 * @author coderclay
 */
public class BracketsDepth {

    public static int bracketsDepth(String s) {
        int len = s.length();
        int count = 0, max = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else {
                count--;
            }
            max = Math.max(max, count);
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(bracketsDepth(s));
    }
}
