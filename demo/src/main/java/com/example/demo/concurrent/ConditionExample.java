package com.example.demo.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean flag = false;

    public void waitForSignal() throws InterruptedException {
        lock.lock();
        try {
            while (!flag) {
                condition.await(); // 线程等待，释放锁
            }
            // 执行等待之后的操作
            System.out.println("Received signal");
        } finally {
            lock.unlock();
        }
    }

    public void signal() {
        lock.lock();
        try {
            flag = true;
            condition.signal(); // 唤醒等待的线程
            System.out.println("唤醒线程了");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionExample example = new ConditionExample();

        // 创建并启动等待线程
        Thread waitThread = new Thread(() -> {
            try {
                example.waitForSignal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        waitThread.start();

        // 主线程等待一段时间后发送信号
        Thread.sleep(2000);
        example.signal();

        // 等待线程完成
        waitThread.join();
    }
}

