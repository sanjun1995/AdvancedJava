package com.example.demo.algo.zhuan;

import java.lang.ref.WeakReference;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

/**
 * @author caozhixin
 * @date 2023/8/4 11:28
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        System.out.println("start");
        ScheduledExecutorService service = newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(() -> System.out.println("Hello World"), 1, 1, TimeUnit.SECONDS);
        System.out.println("end");
        System.gc();
        System.gc();
    }
}
