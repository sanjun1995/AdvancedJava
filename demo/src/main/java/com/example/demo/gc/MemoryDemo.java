//package com.example.demo.gc;
//
///**
// * @author caozhixin
// * @date 2023/12/4 19:48
// */
//public class MemoryDemo {
//    static int num = 0;
//
//    public static void main(String[] args) {
//        new Thread(() -> {
//            System.out.println("Child：" + num);
//            num++;
//            System.out.println("Child End：" + num);
//        }).start();
//
//        while (num == 0);
//        System.out.println("Main：" + num);
//    }
//}
