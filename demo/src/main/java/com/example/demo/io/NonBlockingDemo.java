package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/23 23:43
 */
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
public class NonBlockingDemo {
    public static void main(String[] args) throws Exception {
        // 创建 SocketChannel
        SocketChannel channel = SocketChannel.open();
        // 设置为非阻塞模式
        channel.configureBlocking(false);
        // 连接百度的80端口
        channel.connect(new InetSocketAddress("www.baidu.com", 80));
        // 创建 Selector
        Selector selector = Selector.open();
        // 把 SocketChannel 注册到 Selector，并监听连接事件
        channel.register(selector, SelectionKey.OP_CONNECT);
        // 发起连接并等待连接成功
        while (true) {
            // 阻塞直到有事件发生
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }
            // 获取发生事件的 SelectionKey 集合
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            // 遍历 SelectionKey 集合
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while (keyIterator.hasNext()) {
                // 获取一个 SelectionKey
                SelectionKey key = keyIterator.next();
                // 如果是连接事件
                if (key.isConnectable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    // 如果连接正在建立中
                    if (socketChannel.isConnectionPending()) {
                        // 完成连接
                        socketChannel.finishConnect();
                    }
                    // 将 SocketChannel 注册为可写事件，准备向服务器发送数据
                    socketChannel.register(selector, SelectionKey.OP_WRITE);
                }
                // 如果是可写事件
                else if (key.isWritable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    // 准备发送的数据
                    ByteBuffer buffer = ByteBuffer.wrap("Hello, World!".getBytes());
                    // 如果还有数据未写完
                    while (buffer.hasRemaining()) {
                        socketChannel.write(buffer);
                    }
                    // 将 SocketChannel 注册为可读事件，准备接收服务器的响应
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                // 如果是可读事件
                else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    // 创建缓冲区，用于接收服务器响应
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    // 从 SocketChannel 中读取数据到缓冲区
                    int bytesRead = socketChannel.read(buffer);
                    while (bytesRead != -1) {
                        // 切换缓冲区为读模式
                        buffer.flip();
                        // 打印接收到的数据
                        while (buffer.hasRemaining()) {
                            System.out.print((char) buffer.get());
                        }
                        // 清空缓冲区，准备下一次读取
                        buffer.clear();
                        bytesRead = socketChannel.read(buffer);
                    }
                    // 关闭 SocketChannel
                    socketChannel.close();
                }
                // 从 SelectionKey 集合中移除当前 SelectionKey
                keyIterator.remove();
            }
        }
    }
}