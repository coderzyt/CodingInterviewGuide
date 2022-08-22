package com.clay.coding.java.guide.algorithm.面试指南;

import java.util.Stack;

/**
 * @author yuntzhao
 */
public class SortStack {

    private static void sort(Stack<Integer> stack, Stack<Integer> stackUtil) {
        while (!stack.isEmpty()) {
            Integer cur = stack.pop();
            while (!stackUtil.isEmpty() && stackUtil.peek() < cur) {
                stack.push(stackUtil.pop());
            }
            stackUtil.push(cur);
        }

        System.out.println(stackUtil);

        while (!stackUtil.isEmpty()) {
            stack.push(stackUtil.pop());
        }
        System.out.println(stack);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(5);
        stack.push(3);
        stack.push(4);
        stack.push(2);
        sort(stack, new Stack<Integer>());
    }
}
