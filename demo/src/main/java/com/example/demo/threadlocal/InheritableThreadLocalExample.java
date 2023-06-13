package com.example.demo.threadlocal;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author caozhixin
 * @date 2023/6/13 12:55
 */
public class InheritableThreadLocalExample {

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1));

    public static void main(String[] args) {
        ThreadLocal local = new InheritableThreadLocal();
        local.set(1);


        executor.execute(() -> {
            System.out.println("打印1:" + local.get());
        });

        local.set(2);

        System.out.println("打印2:"+local.get());

        executor.execute(()->{
            local.set(2);
            System.out.println("打印3:"+local.get());
        });
    }
}
