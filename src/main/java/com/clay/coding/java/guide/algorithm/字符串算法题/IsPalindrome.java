package com.clay.coding.java.guide.algorithm.字符串算法题;

/**
 * @author coderclay
 */
public class IsPalindrome {

    public static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (!Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            } else if (!Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            } else {
                // 判断二者是否相等
                char c1 = Character.toLowerCase(s.charAt(l));
                char c2 = Character.toLowerCase(s.charAt(r));
                if (c1 != c2) {
                    return false;
                } else {
                    l++;
                    r--;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }
}
