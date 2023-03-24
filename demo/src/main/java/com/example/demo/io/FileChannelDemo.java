package com.example.demo.io;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author caozhixin
 * @date 2023/3/23 19:24
 */
public class FileChannelDemo {
    public static void main(String[] args) throws Exception {
        // 打开文件通道
        FileChannel channel = new RandomAccessFile("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/input.txt", "rw").getChannel();
        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 写入数据
        buffer.put("Hello, world!".getBytes());
        buffer.flip();
        channel.write(buffer);
        // 清空缓冲区
        buffer.clear();
        // 读取数据
        channel.read(buffer);
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        String message = new String(bytes, "UTF-8");
        System.out.println("Received message: " + message);
        // 关闭文件通道
        channel.close();
    }
}
