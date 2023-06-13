package com.example.demo.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author caozhixin
 * @date 2023/6/13 20:03
 */
public class ProducerConsumerDemo5 {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
    private static Lock lock = new ReentrantLock();
    private static Condition notFull = lock.newCondition();
    private static Condition notEmpty = lock.newCondition();
    static class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                lock.lock();
                while (queue.size() == 10) {
                    try {
                        notFull.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.offer((int) (Math.random() * 100));
                notEmpty.signal();
            }
        }
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                lock.lock();
                while (queue.isEmpty()) {
                    try {
                        notEmpty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(queue.poll());
                notFull.signal();
            }
        }
    }
    public static void main(String[] args) {
        Thread producer = new Thread(new Producer());
        Thread consumer = new Thread(new Consumer());
        producer.start();
        consumer.start();
    }
}
