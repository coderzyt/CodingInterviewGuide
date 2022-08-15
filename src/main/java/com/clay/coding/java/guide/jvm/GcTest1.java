package com.clay.coding.java.guide.jvm;

/**
 * @author coderclay
 */
public class GcTest1 {

    private static final int num = 0;
    public static void main(String[] args) {
        byte[] allocation1, allocation2;
        allocation1 = new byte[30900 * 1024];
        allocation2 = new byte[900*1024];
    }
}
