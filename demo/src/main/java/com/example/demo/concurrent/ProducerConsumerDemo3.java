package com.example.demo.concurrent;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerDemo3 {
    private static final int CAPACITY = 10; // 队列容量
    private static final Object lock = new Object(); // 共享锁对象
    private static final Queue<Integer> queue = new LinkedList<>(); // 消息队列

    static class Producer implements Runnable {
        @Override
        public void run() {
            // 目标就是循环，不断生产消息
            while (true) {
                synchronized (lock) {
                    while (queue.size() == CAPACITY) { // 队列已满时，生产者等待
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int num = (int) (Math.random() * 100);
                    queue.offer(num); // 生产消息
                    System.out.println("Produced: " + num);
                    lock.notifyAll(); // 通知消费者可以消费了
                }
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (queue.isEmpty()) { // 队列为空时，消费者等待
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int num = queue.poll(); // 消费消息
                    System.out.println("Consumed: " + num);
                    lock.notifyAll(); // 通知生产者可以生产了
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread producer = new Thread(new Producer());
        Thread consumer = new Thread(new Consumer());
        producer.start();
//        consumer.start();
    }
}