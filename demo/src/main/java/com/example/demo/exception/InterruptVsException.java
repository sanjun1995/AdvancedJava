//package com.example.demo.exception;
//
//public class InterruptVsException {
//
//    public static void main(String[] args) {
//        Runnable task = () -> {
//            try {
//                for (int i = 1; i <= 10; i++) {
//                    if (Thread.interrupted()) { // 检测到中断信号
//                        System.out.println(Thread.currentThread().getName() + " 中断，处理剩余事情...");
//                        return; // 直接返回
//                    }
////                    Thread.sleep(1000); // 模拟长时间运算
//                    System.out.println(Thread.currentThread().getName() + " 运算中，" + i + " 秒");
//                }
//            } catch (Exception e) { // InterruptedException 异常捕获
//                System.out.println(Thread.currentThread().getName() + " 抛出了 InterruptedException 异常...");
//            }
//        };
//
//        Thread thread = new Thread(task);
//        thread.start();
//
//        try {
//            Thread.sleep(1); // 让主线程睡5秒中断子线程
//            thread.interrupt(); // 中断子线程
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}