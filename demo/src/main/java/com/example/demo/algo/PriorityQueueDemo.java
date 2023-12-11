//package com.example.demo.algo;
//
//import java.util.Comparator;
//import java.util.PriorityQueue;
//
///**
// * @author caozhixin
// * @date 2022/9/3 2:40 PM
// */
//public class PriorityQueueDemo {
//    public static void main(String[] args) {
//        // 升序 o1 - o2 小顶堆
//        // 降序 o2 - o1 大顶堆
//        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });
//        queue.offer(9);
//        queue.offer(5);
//        queue.offer(2);
//        queue.offer(0);
//        queue.offer(3);
//        queue.offer(4);
//        queue.offer(7);
//
//        while (!queue.isEmpty()) {
//            System.out.println(queue.poll());
//        }
//    }
//}
