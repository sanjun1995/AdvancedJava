package com.example.demo.concurrent;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerDemo4 {

    private static final int SIZE = 10;
    private static Queue<Integer> queue = new LinkedList<>();
    private static Object lock = new Object();

    static class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (queue.size() == SIZE) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.offer((int) (Math.random() * 100));
                    lock.notifyAll();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (queue.isEmpty()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(queue.poll());
                    lock.notifyAll();
                }
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
