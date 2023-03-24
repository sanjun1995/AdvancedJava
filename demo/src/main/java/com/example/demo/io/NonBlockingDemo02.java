package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/23 23:43
 */
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NonBlockingDemo02 {
    public static void main(String[] args) throws Exception {
        // 创建 SocketChannel
        SocketChannel channel1 = SocketChannel.open();
        // 创建 SocketChannel
        SocketChannel channel2 = SocketChannel.open();
        channel1.configureBlocking(false);
        channel2.configureBlocking(false);
        Selector selector = Selector.open();
// 注册多个通道
        channel1.register(selector, SelectionKey.OP_READ);
        channel2.register(selector, SelectionKey.OP_WRITE);
        while (true) {
            // 等待 IO 事件的到来
            int readyChannels = selector.select();
            System.out.println("22222");
            // 处理已经就绪的事件
            if (readyChannels > 0) {
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    if (key.isReadable()) {
                        // 处理读事件
                    } else if (key.isWritable()) {
                        // 处理写事件
                    }
                    keyIterator.remove();
                }
            }
            // 进行其他操作
            System.out.println("1111222");;
        }
    }
}