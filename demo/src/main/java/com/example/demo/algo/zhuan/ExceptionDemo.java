//package com.example.demo.algo.zhuan;
//
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
///**
// * @author caozhixin
// * @date 2023/8/23 10:51
// */
//public class ExceptionDemo {
//    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        ExecutorService pool = Executors.newCachedThreadPool();
//        pool.submit(() -> t_e("ZZ"));
//        Thread.sleep(1000);
//        Future<?> future = pool.submit(() -> t_e(null));
//        future.get();
//        pool.shutdown();
//    }
//
//    private static void t_e(String zz) {
//        System.out.println(zz);
//        System.out.println(zz.length());
//    }
//}
