package com.example.demo.produce;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
public class ProducerConsumerDemo2 {
    private static final int CAPACITY = 5; // 队列容量
    private static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(CAPACITY); // 使用阻塞队列作为生产者和消费者之间的缓冲区
    public static void main(String[] args) {
        Thread producerThread = new Thread(new Producer()); // 创建生产者线程
        Thread consumerThread = new Thread(new Consumer()); // 创建消费者线程
        producerThread.start(); // 启动生产者线程
        consumerThread.start(); // 启动消费者线程
    }
    static class Producer implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (true) {
                try {
                    queue.put(i); // 将数据放入队列中，如果队列已满则阻塞
                    System.out.println("生产者生产数据：" + i);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    int data = queue.take(); // 从队列中获取数据，如果队列为空则阻塞
                    System.out.println("消费者消费数据：" + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
