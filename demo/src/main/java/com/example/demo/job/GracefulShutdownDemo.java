package com.example.demo.job;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author caozhixin
 * @date 2024/12/24 10:26
 */
public class GracefulShutdownDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 提交一个任务
        executor.submit(() -> {
            int progress = 0;
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    // 模拟任务逻辑
                    System.out.println("Running task... Progress: " + progress);
                    progress++;
                    Thread.sleep(1000); // 模拟耗时操作
                }
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted. Exiting...");
                Thread.currentThread().interrupt(); // 重新设置中断状态
            } finally {
                // 执行清理工作
                System.out.println("Cleaning up... Saving progress: " + progress);
                saveProgress(progress);
            }
        });

        // 等待一段时间后中断线程
        Thread.sleep(5000); // 运行 5 秒后模拟停机
        System.out.println("Shutdown signal sent.");
    }

    // 模拟保存任务进度
    private static void saveProgress(int progress) {
        System.out.println("Progress saved: " + progress);
    }
}
