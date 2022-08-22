package com.clay.coding.java.guide.algorithm;

/**
 * @author coderclay
 */
public class StringToInt {

    public static Integer stringToInt(String s) {
        if (s == null) {
            return null;
        }
        if (s.length() == 0) {
            return 0;
        }
        char first = s.charAt(0);
        boolean isNegative = false;
        int result = 0;
        int i = 0;
        if (first == '-') {
            isNegative = true;
            i = 1;
        } else if (first == '+') {
            i = 1;
        }
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int temp = s.charAt(i) - '0';
                result = result * 10 + temp;
            } else {
                return 0;
            }
            i++;
        }
        return isNegative ? -result : result;
    }

    public static Integer stringToInt2(String s) {
        if (s.length() == 0) {
            return 0;
        }
        s = s.trim();
        char first = s.charAt(0);
        boolean isNegative = false;
        int result = 0;
        int i = 0;
        if (first == '-') {
            isNegative = true;
            i = 1;
        } else if (first == '+') {
            i = 1;
        } else if (!Character.isDigit(first)) {
            return 0;
        }
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                int temp = s.charAt(i) - '0';
                result = result * 10 + temp;
            } else {
                break;
            }
            i++;
        }
        return isNegative ? -result : result;
    }

    public static void main(String[] args) {
        String s = "-12312312";
        System.out.println("Integer.valueOf() : " + Integer.valueOf(s));
        int result = stringToInt(s);
        System.out.println("stringToInt : " + result);
        System.out.println(stringToInt2("4193 with words"));
    }
}
