package com.example.demo.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author caozhixin
 * @date 2023/3/23 19:32
 */
public class MappedByteBufferDemo02 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/input.txt", "rw");
        FileChannel channel = file.getChannel();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }
        file.close();
    }
}
