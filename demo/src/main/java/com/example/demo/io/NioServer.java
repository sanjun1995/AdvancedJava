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
public class NioServer {
    public static void main(String[] args) throws Exception {
        // 创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress("localhost", 8888));
        // 设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 创建Selector
        Selector selector = Selector.open();
        // 将ServerSocketChannel注册到Selector中
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            // 阻塞等待事件
            selector.select();
            // 获取事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 处理事件
            for (SelectionKey selectionKey : selectionKeys) {
                if (selectionKey.isAcceptable()) {
                    // 处理连接事件
                    ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverChannel.accept();
                    socketChannel.configureBlocking(false);
                    // 将SocketChannel注册到Selector中
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    // 处理读取事件
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int readBytes = socketChannel.read(buffer);
                    if (readBytes > 0) {
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        String message = new String(bytes, "UTF-8");
                        System.out.println("Received message: " + message);
                        // 回复客户端
                        ByteBuffer writeBuffer = ByteBuffer.allocate(message.getBytes().length);
                        writeBuffer.put(message.getBytes());
                        writeBuffer.flip();
                        socketChannel.write(writeBuffer);
                    } else if (readBytes < 0) {
                        // 关闭Channel
                        selectionKey.cancel();
                        socketChannel.close();
                    } else {
                        // 读取到0字节，忽略
                    }
                }
            }
            // 清理已处理的事件
            selectionKeys.clear();
        }
    }
}
