package com.example.demo.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author caozhixin
 * @date 2022/9/1 3:51 PM
 */
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread thread = new Thread(task);
        thread.start();
        Thread.sleep(3000);
        thread.interrupt();

    }

    static class Task implements Runnable {
        private LinkedBlockingQueue taskQueue = new LinkedBlockingQueue();
        private AtomicBoolean running = new AtomicBoolean(true);

        public void submitTask(Object task) throws InterruptedException {
            taskQueue.put(task);
        }

        @Override
        public void run() {
            while (running.get()) {
                try {
                    Object task = taskQueue.take(); // 如果没有任务，会使线程阻塞，一旦有任务，会被唤醒
                    doSomething(task);

                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("被中断");
                        //线程被中断，跳出循环，线程停止
                        break;
                    }

                    //这是一个耗时很长的方法
                    doSomething2(task);

                } catch (Throwable e) {
                    e.printStackTrace();
                    System.out.println("阻塞中断");
                    break;
                }
            }
        }

        public void shutdown() {
            if (running.compareAndSet(true, false)) {
                System.out.println(Thread.currentThread() + " is stoped");
            }
        }

        private void doSomething(Object task) {
        }

        private void doSomething2(Object task) {
        }

    }

//    static class Task implements Runnable {
//        private LinkedBlockingQueue taskQueue = new LinkedBlockingQueue();
//        private AtomicBoolean running = new AtomicBoolean(true);
//
//        public void submitTask(Object task) throws InterruptedException {
//            taskQueue.put(task);
//        }
//
//        @Override
//        public void run() {
//            while (running.get()) {
//                try {
//                    System.out.println("take before");
//                    Object task = taskQueue.take(); // 如果没有任务，会使线程阻塞，一旦有任务，会被唤醒
//                    System.out.println("take after");
//                    doSomething(task);
//                } catch (Throwable e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        public void shutdown() {
//            if (running.compareAndSet(true, false)) {
//                System.out.println(Thread.currentThread() + " is stoped");
//            }
//        }
//
//        private void doSomething(Object task) {
//            System.out.println("执行任务");
//        }
//    }

//    static class Task implements Runnable {
//        @Override
//        public void run() {
//            while (true) {
//                if (shouldRun()) {// 符合业务规则就运行
//                    doSomething();
//                } else {
//                    try {
//                        // 休眠1s,继续去判断是否可运行
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }
//
//        private void doSomething() {
//        }
//
//        private boolean shouldRun() {
//            //根据具体业务规则进行判断
//            return false;
//        }
//    }
}
