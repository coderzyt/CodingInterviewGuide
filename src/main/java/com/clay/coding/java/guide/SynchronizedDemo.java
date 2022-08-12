package com.clay.coding.java.guide;

/**
 * @author coderclay
 */
public class SynchronizedDemo {
    public void method() {
        synchronized(this) {
            System.out.println("synchronized 代码块");
        }
    }
}
