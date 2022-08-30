package com.clay.coding.java.guide.practice;

/**
 * @author coderclay
 * 线程死锁描述的是这样一种情况：多个线程同时阻塞，它们中的一个或者全部都在等待某个资源被释放。
 * 由于线程被无限期地阻塞，因此程序不可能正常终止。
 *
 * 下面的例子符合产生死锁的四个必要条件
 * 1. 互斥条件：该资源任意一个时刻只由一个线程占用。
 * 2. 请求和保持条件：一个线程因请求资源而阻塞时，对已获得的资源保持不放。
 * 3. 不剥夺条件：线程已获得的资源在未使用完之前不能被其他线程强行剥夺，只有自己使用完毕后才释放资源。
 * 4. 循环等待条件：若干线程之间形成一种头尾相接的循环等待资源关系。
 *
 * 如何预防死锁？
 * 1. 破环请求和保持条件：一次性申请所有的资源。
 * 2. 破坏不剥夺条件：占用部分资源的线程进一步申请其他资源时，如果申请不到，可以主动释放它占有的资源。
 * 3. 破坏循环等待条件：靠按序申请资源来预防。按某一顺序申请资源，释放资源则反序释放。破坏循环等待条件。
 *
 * sleep 和 wait 方法的区别和共同点
 * 区别：sleep方法没有释放锁，wait方法释放了锁。
 * 共同点：两者都可以暂停线程的执行。
 * wait通常被用于线程间的交互/通信，sleep通常被用于暂停执行。
 * wait方法被调用后，线程不会自动苏醒，需要别的线程调用同一个对象上的notify或者notifyAll方法。
 * sleep方法执行完成后，线程会自动苏醒。或者可以使用wait(long timeout)超时后线程会自动苏醒。
 *
 * 调用start方法方可启动线程并使线程进入就绪状态，直接执行run方法的话不会以多线程的方式执行。
 */
public class DeadLockDemo {
    private static Object resource1 = new Object();
    private static Object resource2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }, "线程 1").start();

//        new Thread(() -> {
//            synchronized (resource2) {
//                System.out.println(Thread.currentThread() + "get resource2");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread() + "waiting get resource1");
//                synchronized (resource1) {
//                    System.out.println(Thread.currentThread() + "get resource1");
//                }
//            }
//        }, "线程 2").start();

        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }, "线程 2").start();
    }
}
