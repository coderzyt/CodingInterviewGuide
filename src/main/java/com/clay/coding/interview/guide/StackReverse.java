package com.clay.coding.interview.guide;

import java.util.Stack;

/**
 * @author yuntzhao
 */
public class StackReverse {

//    private Stack<Integer> stack;
//
//    public StackReverse(Stack<Integer> stack) {
//        stack = new Stack<Integer>();
//    }

    private static Integer getLastAndRemove(Stack<Integer> stack) throws Exception {
        if (stack.isEmpty()) {
            throw new Exception();
        }
        Integer item = stack.pop();
        if (stack.isEmpty()) {
            return item;
        } else {
            Integer last = getLastAndRemove(stack);
            stack.push(item);
            return last;
        }
    }

    private static void reverse(Stack<Integer> stack) throws Exception {
        Integer num = getLastAndRemove(stack);
        if (!stack.isEmpty()) {
            reverse(stack);
            stack.push(num);
        } else {
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
        reverse(stack);
        System.out.println(stack);
    }
}
