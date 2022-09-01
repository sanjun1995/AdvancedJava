package com.example.demo.test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author caozhixin
 * @date 2022/9/1 3:19 PM
 */
public class BufferDemo {
    private static final int SIZE = 1024;

    public static void main(String[] args) throws IOException {
//        FileChannel fileChannel = new FileOutputStream("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/data.txt").getChannel();
//
//        fileChannel.write(ByteBuffer.wrap("Hello World".getBytes(StandardCharsets.UTF_8)));
//
//        fileChannel.close();

//        FileChannel fileChannel = new RandomAccessFile("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/data.txt", "rw").getChannel();
//        System.out.println("channel的文件位置：" + fileChannel.position());
//
//        fileChannel.position(fileChannel.size());
//        fileChannel.write(ByteBuffer.wrap(" Java".getBytes(StandardCharsets.UTF_8)));
//        fileChannel.close();

        FileChannel fileChannel = new FileInputStream("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/data.txt").getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(SIZE);
        fileChannel.read(byteBuffer);
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            System.out.print((char) byteBuffer.get());
        }
        fileChannel.close();
    }
}
