package com.example.demo.reactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author caozhixin
 * @date 2022/8/31 5:00 PM
 */
public class MainReactor implements Runnable {
    private Selector selector;
    private static final int DEFAULT_IO_THREAD_COUNT = 4;
    private SubReactorThreadGroup subReactorThreadGroup;

    /**
     * 1. 启动选择器
     * 2. 将通道注册到选择器
     * 3. 初始化subReactor
     * @param ssc
     */
    public MainReactor(ServerSocketChannel ssc) {
        try {
            selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        subReactorThreadGroup = new SubReactorThreadGroup(DEFAULT_IO_THREAD_COUNT);
    }

    @Override
    public void run() {
        System.out.println("MainReactor is running");
        while(!Thread.interrupted()) {
            Set<SelectionKey> ops = null;
            try {
                selector.select(1000);
                ops = selector.selectedKeys();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (Iterator<SelectionKey> it = ops.iterator(); it.hasNext();) {
                SelectionKey key = it.next();
                it.remove();
                try {
                    if (key.isAcceptable()) {
                        System.out.println("收到客户端请求。。。");
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        subReactorThreadGroup.dispatch(socketChannel);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("客户端主动断开。。。");
                }
            }
        }
    }
}
