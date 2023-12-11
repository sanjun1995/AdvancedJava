//package com.example.demo.concurrent;
//
//import java.util.concurrent.locks.*;
//
//public class LockSupportDemo {
//    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(() -> {
//            System.out.println("Thread started");
//            LockSupport.park(); // 线程阻塞
//            System.out.println("Thread resumed");
//        });
//
//        thread.start();
//        System.out.println("Main thread sleeping");
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        LockSupport.unpark(thread); // 唤醒线程
//    }
//}
