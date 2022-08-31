package com.example.demo.reactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author caozhixin
 * @date 2022/8/31 5:00 PM
 */
public class MainReactor implements Runnable {
    private Selector selector;
    private static final int DEFAULT_IO_THREAD_COUNT = 4;

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

    }

    @Override
    public void run() {
        
    }
}
