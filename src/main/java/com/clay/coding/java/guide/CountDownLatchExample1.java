package com.clay.coding.java.guide;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author coderclay
 */
public class CountDownLatchExample1 {

//    // 文件夹位置
//    private static final int threadCount = 6;
//
//    // 异步处理所有文件
//    public static void main(String[] args) throws InterruptedException {
//        ExecutorService threadPool = Executors.newFixedThreadPool(10);
//        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
//        for (int i = 0; i < threadCount; i++) {
//            final int threadNum = 1;
//            threadPool.execute(() -> {
//                try {
//                    List<String> filePaths = Arrays.asList();
//                    List<CompletableFuture<String>> fileFutures = filePaths.stream()
//                            .map(filePath -> doSomeThing(filePath))
//                            .collect(Collectors.toList());
//                    // 将他们合并起来
//                    CompletableFuture<Void> allFutures = CompletableFuture.allOf(
//                            fileFutures.toArray(new CompletableFuture[fileFutures.size()])
//                    );
//                } finally {
//                    countDownLatch.countDown();
//                }
//            });
//        }
//        countDownLatch.wait();
//        threadPool.shutdown();
//        System.out.println("finish");
//    }
}
