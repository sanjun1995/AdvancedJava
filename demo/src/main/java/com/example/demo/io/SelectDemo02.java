package com.example.demo.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author caozhixin
 * @date 2023/3/24 15:44
 */
public class SelectDemo02 {
    private Selector selector;
    private ServerSocketChannel channel;
    public void init() throws IOException {
        this.selector = Selector.open();
        this.channel = ServerSocketChannel.open();
        this.channel.socket().bind(new InetSocketAddress(8888));
        this.channel.configureBlocking(false);
        this.channel.register(this.selector, SelectionKey.OP_ACCEPT);
    }
    public void start() throws IOException {
        while (true) {
            int selected = this.selector.select();
            if (selected == 0) {
                continue;
            }
            Set<SelectionKey> keys = this.selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    // 处理连接请求
                }
                iterator.remove();
            }
        }
    }
}
