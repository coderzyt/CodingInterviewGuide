package com.clay.coding.java.guide.algorithm.数组算法题;

import java.util.Stack;

/**
 * @author coderclay
 * <a href="https://leetcode.cn/problems/remove-duplicate-letters/">...</a>
 */
public class LeetCode316 {

    public String removeDuplicateLetters1(String s) {
        boolean[] vis = new boolean[26];
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!vis[ch - 'a']) {
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch) {
                    if (num[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        break;
                    }
                }
                vis[ch - 'a'] = true;
                sb.append(ch);
            }
            num[ch - 'a'] -= 1;
        }
        return sb.toString();
    }

    public String removeDuplicatedLetters(String s) {
        Stack<Character> stack = new Stack<>();
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        for (char ch : s.toCharArray()) {
            if (!stack.contains(ch)) {
                while (!stack.isEmpty() && stack.peek() > ch && count[stack.peek() - 'a'] > 0) {
                    stack.pop();
                }
                stack.push(ch);
            }
            count[ch - 'a']--;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.reverse().toString();
    }

    public String removeDuplicateLetters(String s) {
        Stack<Character> stk = new Stack<>();
        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }
        boolean[] inStack = new boolean[256];
        for (char c : s.toCharArray()) {
            count[c]--;
            if (inStack[c]) {
                continue;
            }
            while (!stk.isEmpty() && stk.peek() > c) {
                if (count[stk.peek()] == 0) {
                    break;
                }
                inStack[stk.pop()] = false;
            }
            stk.push(c);
            inStack[c] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()) {
            sb.append(stk.pop());
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        LeetCode316 leetCode316 = new LeetCode316();
        System.out.println(leetCode316.removeDuplicateLetters("cbacdcbc"));
    }
}
