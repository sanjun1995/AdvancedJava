package com.example.demo.reactor;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @author caozhixin
 * @date 2022/8/31 10:25 PM
 */
public class Handler implements Runnable {
    private static final byte[] b = "hello,服务器收到了你的信息。".getBytes();

    private SocketChannel socketChannel;
    private ByteBuffer byteBuffer;
    private SubReactorThread subReactorThread;

    public Handler(SocketChannel socketChannel, ByteBuffer buffer, SubReactorThread subReactorThread) {
        this.socketChannel = socketChannel;
        this.byteBuffer = buffer;
        this.subReactorThread = subReactorThread;
    }

    @Override
    public void run() {
        System.out.println("业务在handler处理中。。。");
        byteBuffer.put(b);
        subReactorThread.register(new NioTask(socketChannel, SelectionKey.OP_WRITE, byteBuffer));
        System.out.println("业务在handler处理结束。。。");
    }
}
