package com.example.demo.reactor;

import lombok.Data;

import java.nio.channels.SocketChannel;

/**
 * @author caozhixin
 * @date 2022/8/31 9:39 PM
 */
@Data
public class NioTask {
    private SocketChannel socketChannel;
    private int op;
    private Object data;

    public NioTask(SocketChannel socketChannel, int op) {
        this.socketChannel = socketChannel;
        this.op = op;
    }

    public NioTask(SocketChannel socketChannel, int op, Object data) {
        this(socketChannel, op);
        this.data = data;
    }
}
