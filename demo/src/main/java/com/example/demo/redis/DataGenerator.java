package com.example.demo.redis;

import redis.clients.jedis.Jedis;
import java.util.*;

public class DataGenerator {
    // 定义服务列表
    private static final List<String> SERVICES = Arrays.asList("service1", "service2", "service3");
    // 定义错误列表
    public static final List<String> ERRORS = Arrays.asList("invalid_param", "timeout", "unknown_error");

    /**
     * 生成数据
     *
     * @param total 数据总数
     * @param jedis Redis 客户端连接
     */
    public static void generateData(int total, Jedis jedis) {
        Random rand = new Random(); // 初始化随机数生成器
        long currentTimestamp = System.currentTimeMillis() / 1000; // 获取当前时间戳，精确到秒
        long startTimestamp = currentTimestamp - 60; // 计算起始时间戳，为当前时间戳减去 60 秒

        for (int i = 0; i < total; i++) { // 循环 total 次，生成 total 条数据
            String service = SERVICES.get(rand.nextInt(SERVICES.size())); // 随机选择一个服务
            String error = ERRORS.get(rand.nextInt(ERRORS.size())); // 随机选择一个错误
            long timestamp = startTimestamp + rand.nextInt(60); // 生成一个随机时间戳，精确到秒，范围为起始时间戳到当前时间戳
            int count = 1;
            String item = String.format("%s:%s:%d:%d", service, error, timestamp, count);
            jedis.zadd("error_data", timestamp, item); // 将错误数据存储到 Redis 数据库中
        }
    }
}