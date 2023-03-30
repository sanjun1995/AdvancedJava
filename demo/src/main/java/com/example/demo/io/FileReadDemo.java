package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/23 23:31
 */
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
public class FileReadDemo {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("demo/src/main/resources/input.txt");
        FileChannel channel = fis.getChannel();
        // 创建ByteBuffer对象，用于存储读取到的数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 从FileChannel中读取数据到ByteBuffer中
        int bytesRead = channel.read(buffer);
        while (bytesRead != -1) {
            // 将ByteBuffer从写模式切换为读模式
            buffer.flip();
            // 从ByteBuffer中读取数据
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            // 清空ByteBuffer，准备再次读取数据
            buffer.clear();
            // 继续从FileChannel中读取数据到ByteBuffer中
            bytesRead = channel.read(buffer);
        }
    }
}
