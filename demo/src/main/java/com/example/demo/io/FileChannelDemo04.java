package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/28 17:24
 */
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
public class FileChannelDemo04 {
    public static void main(String[] args) throws Exception {
        // 创建一个RandomAccessFile对象，以读写方式打开文件
        RandomAccessFile file = new RandomAccessFile("test.txt", "rw");
        // 获取文件通道
        FileChannel channel = file.getChannel();
        // 写入数据到文件
        String data = "Hello, World!";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.clear();
        buf.put(data.getBytes());
        buf.flip();
        channel.write(buf);
        // 读取文件数据
        buf.clear();
        channel.read(buf);
        buf.flip();
        byte[] bytes = new byte[buf.remaining()];
        buf.get(bytes);
        System.out.println(new String(bytes));
        // 截取文件
        channel.truncate(10);
        // 文件映射
        ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
        buffer.put(0, (byte) 'h');
        buffer.put(1, (byte) 'e');
        buffer.put(2, (byte) 'l');
        buffer.put(3, (byte) 'l');
        buffer.put(4, (byte) 'o');
        // 文件锁定
        FileLock lock = channel.lock(0, 10, true);
        // 通道之间的数据传输
        FileChannel fromChannel = new RandomAccessFile("from.txt", "rw").getChannel();
        FileChannel toChannel = new RandomAccessFile("to.txt", "rw").getChannel();
        long position = 0;
        long count = fromChannel.size();
        fromChannel.transferTo(position, count, toChannel);
    }
}
