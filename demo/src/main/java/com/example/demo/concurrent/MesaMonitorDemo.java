//package com.example.demo.concurrent;
//
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//public class MesaMonitorDemo {
//    private final Lock lock = new ReentrantLock();
//    private final Condition notFull = lock.newCondition();
//    private final Condition notEmpty = lock.newCondition();
//    private final Queue<Integer> buffer = new LinkedList<>();
//    private final int maxSize = 10;
//
//    public void produce(int value) throws InterruptedException {
//        lock.lock();
//        try {
//            while (buffer.size() == maxSize) {
//                notFull.await(); // 等待缓冲区不满
//            }
//            buffer.offer(value);
//            System.out.println("Produced: " + value);
//            notEmpty.signal(); // 通知消费者缓冲区不为空
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public int consume() throws InterruptedException {
//        lock.lock();
//        try {
//            while (buffer.isEmpty()) {
//                notEmpty.await(); // 等待缓冲区不空
//            }
//            int value = buffer.poll();
//            System.out.println("Consumed: " + value);
//            notFull.signal(); // 通知生产者缓冲区不满
//            return value;
//        } finally {
//            lock.unlock();
//        }
//    }
//
//    public static void main(String[] args) {
//        MesaMonitorDemo monitor = new MesaMonitorDemo();
//
//        // 创建生产者线程
//        Thread producerThread = new Thread(() -> {
//            try {
//                for (int i = 1; i <= 20; i++) {
//                    monitor.produce(i);
//                    Thread.sleep(500);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
//        // 创建消费者线程
//        Thread consumerThread = new Thread(() -> {
//            try {
//                for (int i = 1; i <= 20; i++) {
//                    monitor.consume();
//                    Thread.sleep(1000);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
//        // 启动生产者和消费者线程
//        producerThread.start();
//        consumerThread.start();
//    }
//}
//
