package com.example.demo.reactor;

import org.apache.tomcat.util.net.Acceptor;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author caozhixin
 * @date 2022/8/31 7:48 PM
 */
public class SubReactorThreadGroup {
    private static final AtomicInteger requestCount = new AtomicInteger();
    private final int ioThreadCount;
    // 业务线程池
    private ExecutorService businessExecutePool;
    private SubReactorThread[] ioThreads;

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
        this.ioThreads = new SubReactorThread[ioThreadCount];
        for (int i = 0; i < ioThreadCount; i++) {
            this.ioThreads[i] = new SubReactorThread(businessExecutePool);
            this.ioThreads[i].start();
        }
    }

    public void dispatch(SocketChannel socketChannel) {
        if (socketChannel != null) {
            next().register(new NioTask(socketChannel, SelectionKey.OP_READ));
        }
    }

    private SubReactorThread next() {
        return ioThreads[requestCount.getAndIncrement() % ioThreadCount];
    }
}
