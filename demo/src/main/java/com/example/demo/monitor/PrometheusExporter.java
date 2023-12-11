//package com.example.demo.monitor;
//
///**
// * @author caozhixin
// * @date 2023/4/8 11:35
// */
//import io.prometheus.client.Gauge;
//import io.prometheus.client.exporter.HTTPServer;
//
//import java.io.IOException;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//public class PrometheusExporter {
//
//    private static final Gauge gauge = Gauge.build()
//            .name("my_gauge")
//            .help("A simple gauge")
//            .register();
//
//    public static void main(String[] args) throws IOException {
//        HTTPServer server = new HTTPServer(9090, true);
//        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
//        executor.scheduleAtFixedRate(() -> {
//            double value = Math.random() * 100;
//            gauge.set(value);
//        }, 1, 1, TimeUnit.SECONDS);
//    }
//}