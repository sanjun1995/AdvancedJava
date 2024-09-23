package com.example.demo.b2c.shutdown;

/**
 * @author caozhixin
 * @date 2024/9/23 15:12
 */
public class Application {

    public static void main(String[] args) throws InterruptedException {
        // 添加 JVM 关闭钩子，确保优雅关闭
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down application...");
            // 停止外部请求的监听
            stopHttpServer();
            // 关闭数据库连接池
            closeDatabaseConnections();
            // 关闭 MQ 消费者
            closeMqConsumers();
        }));

        // 正常启动应用的其他部分
        startHttpServer();
        startMqConsumers();

        Thread.sleep(2000);
    }

    private static void startMqConsumers() {
        System.out.println("startMqConsumers...");
    }

    private static void startHttpServer() {
        System.out.println("startHttpServer...");
    }

    private static void stopHttpServer() {
        System.out.println("Stopping HTTP server...");
        // 停止接受新的请求
    }

    private static void closeDatabaseConnections() {
        System.out.println("Closing database connections...");
        // 关闭数据库连接池
    }

    private static void closeMqConsumers() {
        System.out.println("Closing MQ consumers...");
        // 关闭 MQ 消费者
    }
}

