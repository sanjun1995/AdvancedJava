package com.example.demo.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author caozhixin
 * @date 2023/3/24 00:11
 */
public class SocketChannelBlockDemo {
    public static void main(String[] args) throws IOException {
        // 创建SocketChannel，并设置为阻塞模式
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(true);
        // 连接到远程服务器
        channel.connect(new InetSocketAddress("www.baidu.com", 80));
        // 等待连接成功或超时
        if (channel.finishConnect()) {
            // 连接成功，执行相应操作
            System.out.println("Connected to remote server");
        } else {
            // 连接超时或发生错误，执行相应操作
            System.out.println("Connection timed out or error occurred");
        }
        // 关闭SocketChannel
        channel.close();
    }
}
