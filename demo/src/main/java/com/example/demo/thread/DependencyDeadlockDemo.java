package com.example.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DependencyDeadlockDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try {
            DependencyTask task1 = new DependencyTask("Task1");
            DependencyTask task2 = new DependencyTask("Task2");

            // Task1 依赖 Task2
            task1.setDependency(task2);
            // Task2 依赖 Task1
            task2.setDependency(task1);

            // 同时提交 Task1 和 Task2
            executorService.submit(task1);
            executorService.submit(task2);
        } finally {
            executorService.shutdown();
        }
    }
}

class DependencyTask implements Runnable {
    private String name;
    private DependencyTask dependency;

    public DependencyTask(String name) {
        this.name = name;
    }

    public void setDependency(DependencyTask dependency) {
        this.dependency = dependency;
    }

    @Override
    public void run() {
        System.out.println(name + " is running.");
        // 模拟任务执行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 尝试获取依赖任务的执行结果
        if (dependency != null) {
            dependency.getResult();
        }

        System.out.println(name + " completes.");
    }

    public void getResult() {
        System.out.println(name + " gets result from dependency.");
    }
}

