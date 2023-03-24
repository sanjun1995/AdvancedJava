package com.example.demo.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author caozhixin
 * @date 2023/3/23 22:03
 */
public class FileChannelDemo03 {
    public static void main(String[] args) {
        try (FileChannel channel = FileChannel.open(Paths.get("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/input.txt"), StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int bytesRead = channel.read(buffer);
            while (bytesRead != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.compact();
                bytesRead = channel.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (InputStream inputStream = new FileInputStream("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/input.txt")) {
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            while (bytesRead != -1) {
                // 处理缓冲区中的数据
                bytesRead = inputStream.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
