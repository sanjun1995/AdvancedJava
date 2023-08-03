package com.example.demo.algo.zhuan;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author caozhixin
 * @date 2023/8/2 22:18
 */
public class TestClass {
    private static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

    public static void add(String key) {
        Integer value = map.get(key);
        if (value == null) {
            map.put(key, 1);
        } else {
            map.put(key, value + 1);
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> add("key")).start();
        }
        System.out.println(map.get("key"));
    }
}
