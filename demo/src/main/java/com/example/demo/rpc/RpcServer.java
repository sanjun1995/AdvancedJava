package com.example.demo.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author caozhixin
 * @date 2023/3/30 23:56
 */
public class RpcServer {
    // 创建线程池，用于处理客户端请求
    private final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    // 存储服务名称和服务实现对象的映射
    private Map<String, Object> serviceMap = new ConcurrentHashMap<>();
    // 注册服务，将服务名称和服务实现对象添加到serviceMap中
    public void addService(String serviceName, Object serviceImpl) {
        serviceMap.put(serviceName, serviceImpl);
    }
    // 启动服务
    public void start(int port) throws IOException {
        // 创建ServerSocket，监听端口
        ServerSocket server = new ServerSocket(port);
        // 接收客户端请求并交给线程池处理
        while (true) {
            Socket socket = server.accept();
            executor.submit(new RpcHandler(socket, serviceMap));
        }
    }

    public static void main(String[] args) throws IOException {
        // 创建服务提供者并注册服务
        RpcServer server = new RpcServer();
        server.addService("HelloService", new HelloServiceImpl());
        // 启动服务，监听端口
        server.start(8888);
    }
}
