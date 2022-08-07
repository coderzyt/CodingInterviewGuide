package com.clay.coding.interview.guide;

/**
 * @author yuntzhao
 */
public class MyStack {

    private int[] nums;

    private int size;

    public MyStack(int length) {
        this.nums = new int[length];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int length() {
        return this.nums.length;
    }

    public int size() {
        return this.size;
    }

    public boolean isFull() {
        return this.nums.length == size;
    }

    public void push(int num) {
        if (nums.length == size) {
            throw new IndexOutOfBoundsException();
        }
        nums[size - 1 + 1] = num;
        size++;
    }

    public int pop() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        int result = nums[size - 1];
        size--;
        return result;
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack(4);

        System.out.println(myStack.isEmpty());

        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);

        System.out.println(myStack.isEmpty());
        System.out.println(myStack.length());
        System.out.println(myStack.size());

        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());

        System.out.println(myStack.isEmpty());
        System.out.println(myStack.length());
        System.out.println(myStack.size());
    }
}
