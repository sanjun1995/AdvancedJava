package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/23 23:31
 */
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BlockingDemo {
    public static void main(String[] args) throws Exception {
        // 阻塞式 I/O
        FileInputStream fis = new FileInputStream("test.txt");
        FileChannel fc = fis.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int bytesRea = fc.read(buf);
        while (bytesRea != -1) {
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRea = fc.read(buf);
        }
        fis.close();
    }
}
