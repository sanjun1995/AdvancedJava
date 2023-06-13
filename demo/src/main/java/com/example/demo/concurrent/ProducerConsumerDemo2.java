package com.example.demo.concurrent;

import java.util.concurrent.*;

public class ProducerConsumerDemo2 {
    private static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
    private static Semaphore semaphore = new Semaphore(10);

    static class Producer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    int num = (int) (Math.random() * 100);
                    semaphore.acquire();
                    queue.put(num);
                    System.out.println("Produced: " + num);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    int num = queue.take();
                    System.out.println("Consumed: " + num);
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(new Producer());
        executor.submit(new Consumer());

        // 等待一段时间后停止生产者和消费者
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdownNow();
    }
}

