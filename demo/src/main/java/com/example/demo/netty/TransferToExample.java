package com.example.demo.netty;

import java.io.File;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class TransferToExample {
    public static void main(String[] args) throws Exception {
        String fileName = "demo/src/main/resources/input.txt";
        File file = new File(fileName);
        if (!file.exists() || file.length() == 0) {
            System.err.println("File " + fileName + " not found or empty");
            return;
        }

        // 打开文件通道，并获取文件大小
        FileChannel inChannel = FileChannel.open(file.toPath());
        long count = inChannel.size();

        // 连接服务器并获取网络通道
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("127.0.0.1", 8000));
        channel.configureBlocking(true);

        // 将文件内容直接传输到网络中，实现零拷贝
        long position = 0;
        while (position < count) {
            position += inChannel.transferTo(position, count - position, channel);
        }
        System.out.println("Transferred " + position + " bytes to server");

        // 关闭通道
        channel.close();
        inChannel.close();
    }
}
