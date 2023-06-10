package com.example.demo.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.atomic.AtomicLong;

public class LimitLatch {
    // 定义 Sync 类作为 AQS 的实现
    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int arg) {
            // 获取计数并增加 1
            long newCount = count.incrementAndGet();
            // 如果增加后的计数大于限制，则减少计数并返回 -1 表示获取失败
            if (newCount > limit) {
                count.decrementAndGet();
                return -1;
            } else { // 计数小于或等于限制，则返回 1 表示获取成功
                return 1;
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            // 减少计数并返回 true 表示释放成功
            count.decrementAndGet();
            return true;
        }
    }

    private final Sync sync; // 使用 AQS 实现同步控制逻辑
    private final AtomicLong count; // 维护当前的连接数
    private volatile long limit; // 连接数的上限

    public LimitLatch() {
        count = new AtomicLong(0);
        sync = new Sync();
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public void countUpOrAwait() throws InterruptedException {
        // 获取共享锁，如果当前的计数已经超过了限制，则调用线程将会被阻塞，直到有一个许可证可用。
        sync.acquireSharedInterruptibly(1);
    }

    public long countDown() {
        // 释放共享锁，并返回当前计数
        sync.releaseShared(0);
        return getCount();
    }

    public long getCount() {
        // 获取当前的计数
        return count.get();
    }
}
