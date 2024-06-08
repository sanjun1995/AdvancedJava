package com.example.demo.algo.top100;

import java.util.LinkedHashMap;

/**
 * @author caozhixin
 * @date 2024/6/8 12:23
 */
public class LruCache extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LruCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
