//package com.example.demo.monitor;
//
///**
// * @author caozhixin
// * @date 2023/4/8 09:47
// */
//import io.micrometer.core.instrument.Counter;
//import io.micrometer.core.instrument.MeterRegistry;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MetricsGenerator {
//
//    private final MeterRegistry meterRegistry;
//    private final Counter cpuCounter;
//    private final Counter memCounter;
//    private final Counter diskCounter;
//
//    @Autowired
//    public MetricsGenerator(MeterRegistry meterRegistry) {
//        this.meterRegistry = meterRegistry;
//        cpuCounter = Counter.builder("cpu_usage")
//                .description("CPU usage")
//                .register(meterRegistry);
//        memCounter = Counter.builder("memory_usage")
//                .description("Memory usage")
//                .register(meterRegistry);
//        diskCounter = Counter.builder("disk_usage")
//                .description("Disk usage")
//                .register(meterRegistry);
//    }
//
//    @Scheduled(fixedDelay = 5000) // 每5秒运行一次
//    public void generateMetrics() {
//        double cpuUsage = Math.random() * 100;
//        double memUsage = Math.random() * 1000;
//        double diskUsage = Math.random() * 10000;
//        cpuCounter.increment(cpuUsage);
//        memCounter.increment(memUsage);
//        diskCounter.increment(diskUsage);
//    }
//}