package com.example.demo.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * @author caozhixin
 * @date 2023/3/23 16:06
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class NioServer02 {
    public static void main(String[] args) throws IOException {
        // 创建 Selector 对象
        Selector selector = Selector.open();
        // 创建 ServerSocketChannel 对象
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8888));
        serverSocketChannel.configureBlocking(false);
        // 将 ServerSocketChannel 注册到 Selector 中，监听 OP_ACCEPT 事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 不断循环，处理事件
        while (true) {
            // 阻塞等待事件
            selector.select();
            // 处理事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    // 处理连接事件
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    // 处理读取事件
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = channel.read(buffer);
                    if (len > 0) {
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        String message = new String(bytes);
                        // 将读取任务提交给线程池处理
                        executorService.submit(() -> {
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("收到客户端消息：" + message);
                        });
                    } else if (len == -1) {
                        channel.close();
                    }
                }
            }
        }
    }
}