package com.example.demo.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TTLExecutorExample {
    private static ExecutorService TTLExecutor = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(5));

    // 定义另外一个线程池循环执行，模拟业务场景下多Http请求调用的情况
    private static ExecutorService loopExecutor = Executors.newFixedThreadPool(5);

    private static AtomicInteger i = new AtomicInteger(0);

    // TTL的ThreadLocal
    private static ThreadLocal tl = new TransmittableThreadLocal<>(); //这里采用TTL的实现

    public static void main(String[] args) {
        while (true) {
            loopExecutor.execute(() -> {
                if (i.get() < 10) {
                    tl.set(i.getAndAdd(1));
                    TTLExecutor.execute(() -> {
                        System.out.println(String.format("子线程名称-%s, 变量值=%s", Thread.currentThread().getName(), tl.get()));
                    });
                }
            });
        }
    }
}
