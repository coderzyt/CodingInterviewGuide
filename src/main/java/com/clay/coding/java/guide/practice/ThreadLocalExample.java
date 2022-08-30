package com.clay.coding.java.guide.practice;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author coderclay
 */
public class ThreadLocalExample implements Runnable {

    private static final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat(
            "yyyyMMdd HHmm"
    ));

    @Override
    public void run() {
        System.out.println("Thread Name = " + Thread.currentThread().getName() + " default formatter = " + formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        formatter.set(new SimpleDateFormat());
        System.out.println("Thread Name = " + Thread.currentThread().getName() + " formatter = " + formatter.get().toPattern());
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalExample obj = new ThreadLocalExample();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(obj, "" + i);
            Thread.sleep(new Random().nextInt(1000));
            t.start();
        }
    }
}
