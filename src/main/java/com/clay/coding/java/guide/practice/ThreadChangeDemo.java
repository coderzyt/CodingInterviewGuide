package com.clay.coding.java.guide.practice;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author coderclay
 */
public class ThreadChangeDemo {

    /**
     * 自定义线程池
     * @return
     */
    private static ThreadPoolExecutor buildThreadPoolExecutor() {
        return new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        });
    }

    private static String divide(int num1, int num2) {
        return String.format("%1.2f%%", Double.parseDouble(num1 + "") / Double.parseDouble(num2 + "") * 100);
    }

    private static void threadPoolStatus(ThreadPoolExecutor executor, String name) {
        LinkedBlockingQueue queue = (LinkedBlockingQueue) executor.getQueue();
        System.out.println(Thread.currentThread().getName() + "-" + name + "-:" +
                "核心线程数：" + executor.getCorePoolSize() +
                " 活动线程数：" + executor.getActiveCount() +
                " 最大线程数：" + executor.getMaximumPoolSize() +
                " 线程池活跃度：" + divide(executor.getActiveCount(), executor.getMaximumPoolSize()) +
                "任务完成数：" + executor.getCompletedTaskCount() +
                "队列大小：" + (queue.size() + queue.remainingCapacity()) +
                "当前排队线程数：" + queue.size() +
                "队列剩余大小：" + queue.remainingCapacity() +
                "队列使用数：" + divide(queue.size(), queue.size() + queue.remainingCapacity()));
    }

    /**
     * 先提交任务给线程池，并修改线程池参数
     */
    private static void dynamicModifyExecutor() throws InterruptedException {
        ThreadPoolExecutor executor = buildThreadPoolExecutor();
        for (int i = 0; i < 15; i++) {
            executor.submit(() -> {
                threadPoolStatus(executor, "创建任务");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPoolStatus(executor, "改变之前");
        executor.setCorePoolSize(10);
        executor.setMaximumPoolSize(10);
        threadPoolStatus(executor, "改变之后");
        Thread.currentThread().join();
    }

    public static void main(String[] args) throws InterruptedException {
        dynamicModifyExecutor();
    }
}
