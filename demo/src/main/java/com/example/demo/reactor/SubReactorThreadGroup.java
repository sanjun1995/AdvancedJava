package com.example.demo.reactor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author caozhixin
 * @date 2022/8/31 7:48 PM
 */
public class SubReactorThreadGroup {
    private final int ioThreadCount;
    // 业务线程池
    private ExecutorService businessExecutePool;

    public SubReactorThreadGroup(int ioThreadCount) {
        this.ioThreadCount = ioThreadCount;
        businessExecutePool = Executors.newFixedThreadPool(10, new ThreadFactory() {
            private AtomicInteger num = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("business-thread-" + num.incrementAndGet());
                return t;
            }
        });
    }
}
