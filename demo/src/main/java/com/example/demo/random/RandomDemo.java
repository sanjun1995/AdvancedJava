//package com.example.demo.random;
//
//import java.util.Random;
//
//public class RandomDemo {
//    public static void main(String[] args) {
//        long seed = System.currentTimeMillis(); // 使用当前时间的毫秒数作为种子
//        Random random1 = new Random(seed); // 创建第一个Random对象
//        Random random2 = new Random(seed); // 创建第二个Random对象
//
//        Thread thread1 = new Thread(() -> {
//            int randomNumber = random1.nextInt(100); // 生成第一个线程的随机数
//            System.out.println(randomNumber);
//        });
//
//        Thread thread2 = new Thread(() -> {
//            int randomNumber = random2.nextInt(100); // 生成第二个线程的随机数
//            System.out.println(randomNumber);
//        });
//
//        thread1.start();
//        thread2.start();
//    }
//}
