package com.example.demo.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author caozhixin
 * @date 2023/3/23 16:11
 */
public class NioClient {
    public static void main(String[] args) throws Exception {
        // 创建SocketChannel
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8888));
        // 设置为非阻塞模式
        socketChannel.configureBlocking(false);
        // 创建ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("Hello, world!".getBytes());
        buffer.flip();
        // 发送数据
        socketChannel.write(buffer);
        // 接收数据
        buffer.clear();
        socketChannel.read(buffer);
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        String message = new String(bytes, "UTF-8");
        System.out.println("Received message: " + message);
        // 关闭Channel
        socketChannel.close();
    }
}
