package com.example.demo.rpc;

import java.io.IOException;

/**
 * @author caozhixin
 * @date 2023/3/30 23:58
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // 创建服务提供者并注册服务
        RpcServer server = new RpcServer();
        server.addService("HelloService", new HelloServiceImpl());
        // 启动服务，监听端口
        server.start(8888);

        // 创建服务消费者并调用服务
        HelloClient client = new HelloClient("localhost", 8888);
        String result = client.sayHello("Alice");
        System.out.println(result);
    }
}
