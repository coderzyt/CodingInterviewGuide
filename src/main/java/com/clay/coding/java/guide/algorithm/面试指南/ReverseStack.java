package com.clay.coding.java.guide.algorithm.面试指南;

import java.util.Stack;

/**
 * @author yuntzhao
 */
public class ReverseStack {

    private Stack<Integer> stack;

    private static Integer getAndRemoveLastElement(Stack<Integer> stack) throws Exception {
        if (stack.isEmpty()) {
            throw new Exception();
        }

        Integer num = stack.pop();
        if (stack.isEmpty()) {
            return num;
        } else {
            Integer last = getAndRemoveLastElement(stack);
            stack.push(num);
            return last;
        }
    }

    private static void reverseStack(Stack<Integer> stack) throws Exception {
        if (stack.isEmpty()) {
            throw new Exception();
        }
        Integer num = getAndRemoveLastElement(stack);
        if (stack.isEmpty()) {
            stack.push(num);
        } else {
            reverseStack(stack);
            stack.push(num);
        }
    }

    public static void main(String[] args) throws Exception {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack);
        reverseStack(stack);
        System.out.println(stack);
    }
}
