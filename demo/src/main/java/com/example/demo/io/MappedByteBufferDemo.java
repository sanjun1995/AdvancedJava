package com.example.demo.io;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author caozhixin
 * @date 2023/3/23 19:25
 */
public class MappedByteBufferDemo {
    public static void main(String[] args) throws Exception {
        // 打开文件通道
        FileChannel channel = new RandomAccessFile("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/input.txt", "rw").getChannel();
        // 创建内存映射文件
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
        // 写入数据
        buffer.put("Hello, world".getBytes());
        // 读取数据
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        String message = new String(bytes, "UTF-8");
        System.out.println("Received message: " + message);
        // 关闭文件通道
        channel.close();
    }
}
