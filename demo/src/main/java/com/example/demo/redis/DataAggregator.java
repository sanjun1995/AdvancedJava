package com.example.demo.redis;

import redis.clients.jedis.Jedis;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DataAggregator {
    private static final String REDIS_HOST = "localhost"; // Redis 主机名
    private static final int REDIS_PORT = 6379; // Redis 端口号
    private static final int THRESHOLD = 100; // 预设阈值，当错误数量超过该阈值时触发警告

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2); // 创建一个只有一个线程的定时任务执行程序
        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT); // 创建 Redis 客户端连接

        scheduler.scheduleAtFixedRate(() -> {
            // 并发情况下，线程会阻塞
            synchronized (jedis) {
                DataGenerator.generateData(20, jedis); // 生成随机错误数据，并将其存储到 Redis 数据库中
            }
        }, 0, 1, TimeUnit.SECONDS); // 定时任务间隔为 1 秒钟

        scheduler.scheduleAtFixedRate(() -> { // 定时任务逻辑
            synchronized (jedis) {
                long currentTimestamp = System.currentTimeMillis() / 1000; // 获取当前时间戳，精确到秒
                long startTimestamp = currentTimestamp - 60; // 计算起始时间戳，为当前时间戳减去 60 秒
                Set<String> data = jedis.zrangeByScore("error_data", startTimestamp, currentTimestamp); // 使用 zrange 命令获取指定时间范围内的数据

                Map<String, Map<String, Integer>> countMap = new HashMap<>(); // 用于记录聚合后的服务和错误数量信息
                for (String item : data) { // 遍历所有错误数据
                    String[] parts = item.split(":"); // 以冒号为分隔符，将错误数据分割为部分
                    String service = parts[0]; // 获取服务名
                    String error = parts[1]; // 获取错误类型
                    long timestamp = Long.parseLong(parts[2]); // 获取时间戳
                    int count = Integer.parseInt(parts[3]); // 获取错误数量

                    if (timestamp < startTimestamp) { // 如果时间戳早于起始时间戳，则跳过该数据
                        continue;
                    }

                    Map<String, Integer> serviceCountMap = countMap.computeIfAbsent(service, k -> new HashMap<>()); // 获取指定服务的错误数量信息
                    serviceCountMap.put(error, serviceCountMap.getOrDefault(error, 0) + count); // 更新指定服务和错误类型的错误数量信息
                }

                List<String> alerts = new ArrayList<>(); // 用于存储警告信息
                for (String service : countMap.keySet()) { // 遍历服务名列表
                    Map<String, Integer> serviceCountMap = countMap.get(service); // 获取服务和错误数量信息
                    int totalErrors = 0;
                    for (String error : serviceCountMap.keySet()) { // 遍历错误列表
                        int count = serviceCountMap.get(error); // 获取错误数量
                        totalErrors += count;
                    }
                    if (totalErrors > THRESHOLD) { // 如果错误数量超过预设阈值
                        alerts.add(service + " has too many errors: " + serviceCountMap.keySet() + ", count: " + totalErrors); // 将该服务名添加到警告信息列表中
                    }
                }
                if (!alerts.isEmpty()) { // 如果警告信息列表不为空
                    System.out.println(String.join("\n", alerts)); // 打印警告信息
                }
            }
        }, 0, 10, TimeUnit.SECONDS); // 定时任务间隔为 10 秒

        // 关闭 Redis 连接
        jedis.close();
    }
}