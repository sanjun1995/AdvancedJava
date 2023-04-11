package com.example.demo.produce;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerDemo3 {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }
}

class Producer implements Runnable {
    private final Queue<Integer> queue;

    public Producer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            synchronized (queue) {
                System.out.println("Producer produces: " + i);
                queue.add(i);
                queue.notify(); // 唤醒等待的线程
                try {
                    queue.wait(); // 生产一个数字就wait
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Consumer implements Runnable {
    private final Queue<Integer> queue;

    public Consumer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int num = queue.remove();
                System.out.println("Consumer consumes: " + num);
                queue.notify(); // 唤醒等待的线程
            }
        }
    }
}

