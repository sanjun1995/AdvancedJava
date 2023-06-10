package com.example.demo.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LimitLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建 LimitLatch 实例
        LimitLatch limitLatch = new LimitLatch();
        // 设置连接许可证的上限为 3
        limitLatch.setLimit(3);
        // 创建线程池，最多同时运行 5 个线程
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // 向线程池提交任务
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    // 获取一个连接许可证，如果当前的计数已经超过了限制，则调用线程将会被阻塞，直到有一个许可证可用。
                    limitLatch.countUpOrAwait();
                    // 打印线程名称和当前活动连接数
                    System.out.println(Thread.currentThread().getName() + " entered. Active connections: " + limitLatch.getCount());
                    Thread.sleep(1000); // 睡眠1秒钟模拟线程正在处理工作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放一个连接许可证并返回当前计数
                    long count = limitLatch.countDown();
                    // 打印线程名称和当前活动连接数
                    System.out.println(Thread.currentThread().getName() + " exited. Active connections: " + count);
                }
            });
        }

        // 关闭线程池并等待所有任务完成
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}