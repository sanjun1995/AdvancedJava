package com.example.demo.io.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;

public class AioClient {
    public static void main(String[] args) throws Exception {
        AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
        channel.connect(new InetSocketAddress(8080));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("Java AIO".getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        Thread.sleep(1000L);
        channel.write(buffer);
    }
}
