package com.example.demo.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.alibaba.ttl.TransmittableThreadLocal;

public class TransmittableThreadLocalExample {
    // 创建一个TransmittableThreadLocal对象
    private static TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();

    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 设置TransmittableThreadLocal的值
        context.set("Hello, World!");

        // 提交两个任务到线程池
        executorService.submit(() -> {
            // 在任务中获取TransmittableThreadLocal的值
            System.out.println("Task 1: " + context.get());
        });

        executorService.submit(() -> {
            // 在任务中获取TransmittableThreadLocal的值
            System.out.println("Task 2: " + context.get());
        });

        // 关闭线程池
        executorService.shutdown();
    }
}