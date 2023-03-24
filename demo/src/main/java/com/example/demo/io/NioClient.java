package com.example.demo.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author caozhixin
 * @date 2023/3/23 16:11
 */
public class NioClient {
    public static void main(String[] args) throws Exception {
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.submit(() -> {
// 创建SocketChannel
                SocketChannel socketChannel = null;
                try {
                    socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8888));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 设置为非阻塞模式
                try {
                    socketChannel.configureBlocking(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 创建ByteBuffer
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.put(("Hello, world!" + finalI).getBytes());
                buffer.flip();
                // 发送数据
                try {
                    socketChannel.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                buffer.clear();
                // 接收数据
//        buffer.clear();
//        socketChannel.read(buffer);
//        buffer.flip();
//        byte[] bytes = new byte[buffer.remaining()];
//        buffer.get(bytes);
//        String message = new String(bytes, "UTF-8");
//        System.out.println("Received message: " + message);
                // 关闭Channel
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
