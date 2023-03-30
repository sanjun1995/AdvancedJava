package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/28 17:07
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
public class NonBlockingClient {
    public static void main(String[] args) throws IOException {
        // 创建SocketChannel对象
        SocketChannel channel = SocketChannel.open();
        // 将SocketChannel设置为非阻塞模式
        channel.configureBlocking(false);
        // 连接服务器
        channel.connect(new InetSocketAddress("localhost", 8080));
        // 等待连接完成
        while (!channel.finishConnect()) {
            // 连接未完成，可以进行其他操作
            System.out.println("连接未完成，可以进行其他操作");
        }
        // 发送数据
        String message = "Hello,Server!";
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
        // 关闭SocketChannel
        channel.close();
    }
}
