package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/28 19:12
 */
import java.nio.ByteBuffer;
public class ByteBufferDemo02 {
    public static void main(String[] args) {
        // 创建一个容量为16的ByteBuffer对象
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 写入数据到缓冲区中
        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.put((byte) 3);
        buffer.put((byte) 4);
        // 切换到读模式
        buffer.flip();
        // 从缓冲区中读取数据
        System.out.println(buffer.get()); // 输出1
        System.out.println(buffer.get()); // 输出2
        // 切换到写模式
        buffer.compact();
        // 再次写入数据到缓冲区中
        buffer.put((byte) 5);
        buffer.put((byte) 6);
        // 切换到读模式
        buffer.flip();
        // 从缓冲区中读取数据
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
        // 输出 3 4 5 6
    }
}
