package com.clay.coding.interview.guide;

import java.util.Stack;

/**
 * @author yuntzhao
 */
public class GetMinStack {

    private Stack<Integer> minStack;

    private Stack<Integer> stack;

    public GetMinStack() {
        this.minStack = new Stack<Integer>();
        this.stack = new Stack<Integer>();
    }

    public void push(int num) {
        if (stack.isEmpty()) {
            stack.push(num);
            minStack.push(num);
        } else {
            stack.push(num);

            int min = minStack.pop();
            if (num <= min) {
                minStack.push(min);
                minStack.push(num);
            }
        }
    }

    public int pop() {
        if (stack.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        int num = stack.pop();
        if (num == this.getMin()) {
            minStack.pop();
        }

        return num;
    }

    public int getMin() {
        if (minStack.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return minStack.peek();
    }
}
