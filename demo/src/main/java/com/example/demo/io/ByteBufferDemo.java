package com.example.demo.io;

import java.nio.ByteBuffer;

/**
 * @author caozhixin
 * @date 2023/3/24 16:45
 */
public class ByteBufferDemo {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024); // 分配一个大小为 1024 的 ByteBuffer
        buffer.put("Hello World".getBytes()); // 将字符串写入 ByteBuffer
        buffer.flip(); // 切换为读模式
        System.out.println(buffer.limit());
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes); // 读取 ByteBuffer 中的数据到字节数组中
        System.out.println(new String(bytes)); // 输出：Hello World
        buffer.clear(); // 切换为写模式，并清空
        buffer.put("Hello".getBytes()); // 再次写入数据
        buffer.put("World".getBytes());
        buffer.flip(); // 切换为读模式
        buffer.get(); // 读取一个字节
        buffer.get(); // 读取一个字节
        buffer.mark(); // 标记当前位置
        buffer.get(); // 读取一个字节
        buffer.get(); // 读取一个字节
        buffer.reset(); // 将当前位置重置为标记位置
        buffer.compact(); // 将未读完的数据移动到 ByteBuffer 的最前面，并切换为写模式
    }
}
