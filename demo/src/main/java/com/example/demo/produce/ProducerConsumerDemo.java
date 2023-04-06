package com.example.demo.produce;

import java.util.LinkedList;
import java.util.Queue;
public class ProducerConsumerDemo {
    // 队列容量
    private static final int CAPACITY = 5;
    // 使用 LinkedList 作为队列
    private static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) {
        // 创建生产者线程和消费者线程
        Thread producerThread = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());
        // 启动线程
        producerThread.start();
        consumerThread.start();
    }
    // 生产者线程
    static class Producer implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (true) {
                synchronized (queue) {
                    // 如果队列已满，则等待
                    while (queue.size() == CAPACITY) {
                        try {
                            System.out.println("队列已满，生产者线程等待...");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 队列未满，可以生产数据
                    queue.offer(i);
                    System.out.println("生产者生产数据：" + i);
                    i++;
                    // 通知其他线程
                    queue.notifyAll();
                }
            }
        }
    }
    // 消费者线程
    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    // 如果队列为空，则等待
                    while (queue.isEmpty()) {
                        try {
                            System.out.println("队列为空，消费者线程等待...");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 队列不为空，可以消费数据
                    int data = queue.poll();
                    System.out.println("消费者消费数据：" + data);
                    // 通知其他线程
                    queue.notifyAll();
                }
            }
        }
    }
}