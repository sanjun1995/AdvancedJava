package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/27 19:11
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
public class NioServer03 {
    public static void main(String[] args) throws IOException {
        // 创建一个ServerSocketChannel对象
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 将ServerSocketChannel设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 绑定本地地址和端口
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));
        // 创建一个Selector对象
        Selector selector = Selector.open();
        // 将ServerSocketChannel注册到Selector上，并监听OP_ACCEPT事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server started on port 8888");
        while (true) {
            // 阻塞等待事件发生
            selector.select();
            // 获取事件集合
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                // 处理事件
                if (key.isAcceptable()) {
                    // 如果是OP_ACCEPT事件，则创建一个SocketChannel，并将它注册到Selector上
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("New client connected: " + socketChannel.getRemoteAddress());
                } else if (key.isReadable()) {
                    // 如果是OP_READ事件，则读取客户端发送的数据
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = socketChannel.read(buffer);
                    if (len > 0) {
                        buffer.flip();
                        String msg = new String(buffer.array(), 0, len);
                        System.out.println("Received message from " + socketChannel.getRemoteAddress() + ": " + msg);
                    } else if (len == -1) {
                        // 如果读取到-1，说明客户端已经断开连接
                        System.out.println("Client disconnected: " + socketChannel.getRemoteAddress());
                        socketChannel.close();
                        key.cancel();
                    }
                }
            }
        }
    }
}
