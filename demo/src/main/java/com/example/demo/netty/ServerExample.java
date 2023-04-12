package com.example.demo.netty;

import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerExample {
    public static void main(String[] args) throws Exception {
        int port = 8000;

        // 打开服务器套接字通道并绑定到指定的本地地址
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.socket().bind(new InetSocketAddress(port));
        System.out.println("Server listening on port " + port);

        // 接受客户端连接并处理请求
        while (true) {
            SocketChannel channel = serverChannel.accept();
            System.out.println("Accepted connection from " + channel.getRemoteAddress());

            // 处理客户端请求：将接收到的数据存储到文件中
            FileOutputStream outStream = new FileOutputStream("demo/src/main/resources/output.txt");
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (channel.read(buf) > 0) {
                buf.flip();
                outStream.getChannel().write(buf);
                buf.clear();
            }
            System.out.println("Received " + outStream.getChannel().size() + " bytes of data");
            outStream.close();
            channel.close();
        }
    }
}
