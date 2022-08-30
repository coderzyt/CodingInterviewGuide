package com.clay.coding.java.guide.practice;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author coderclay
 */
public class VolatileAtomicityDemo {

    public volatile static int inc = 0;
//    public AtomicInteger inc =  new AtomicInteger();

    public synchronized void increase() {
        inc++;
//        inc.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService threadPool = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        VolatileAtomicityDemo volatileAtomicityDemo = new VolatileAtomicityDemo();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(() -> {
                for (int j = 0; j < 500; j++) {
                    volatileAtomicityDemo.increase();
                }
            });
        }
//        Thread.sleep(1500);
//        System.out.println(volatileAtomicityDemo.inc.get());
        System.out.println(inc);
        threadPool.shutdown();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}