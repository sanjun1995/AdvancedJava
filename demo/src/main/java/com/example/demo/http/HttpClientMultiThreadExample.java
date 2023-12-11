//package com.example.demo.http;
//
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.conn.socket.PlainConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//
//import java.io.IOException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
//public class HttpClientMultiThreadExample {
//    public static void main(String[] args) {
//        // 创建连接池管理器
////        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
////                .register("http", PlainConnectionSocketFactory.getSocketFactory())
////                .register("https", SSLConnectionSocketFactory.getSocketFactory())
////                .build();
////        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
////        connManager.setMaxTotal(200);
////        connManager.setDefaultMaxPerRoute(100);
//
//        // 创建HttpClient实例
//        CloseableHttpClient httpClient = HttpClients.custom()
////                .setConnectionManager(connManager)
//                .build();
//
//        // 创建线程池
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//
//        // 创建多个线程执行请求
//        for (int i = 0; i < 1; i++) {
//            executorService.execute(() -> {
//                try {
//                    // 创建HttpGet请求
//                    HttpGet httpGet = new HttpGet("http://www.baidu.com");
//
//                    // 执行请求
//                    long startTime = System.currentTimeMillis(); // 记录开始时间
//                    CloseableHttpResponse response = httpClient.execute(httpGet);
//                    long endTime = System.currentTimeMillis(); // 记录结束时间
//                    long duration = endTime - startTime; // 计算耗时
//                    System.out.println("Thread " + Thread.currentThread().getName() + " took " + duration + "ms");
//
//                    // 处理响应
//                    // ...
//
//                    // 关闭响应
//                    response.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//
//        try {
//            // 等待所有线程执行完毕或超时
//            if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
//                // 如果超时，强制关闭连接池
//                executorService.shutdownNow();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            // 捕获中断异常，强制关闭连接池
//            executorService.shutdownNow();
//        }
//
//        // 关闭HttpClient
//        try {
//            httpClient.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}