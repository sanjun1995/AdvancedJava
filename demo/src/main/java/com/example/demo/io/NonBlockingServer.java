package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/24 10:53
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
public class NonBlockingServer {
    private static final int BUFFER_SIZE = 1024;
    private static final int PORT = 8080;
    public static void main(String[] args) throws IOException {
        // 创建ServerSocketChannel，并绑定到指定端口
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(PORT));
        serverChannel.configureBlocking(false);
        // 创建Selector，并将serverChannel注册到Selector上
        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 创建线程用于处理I/O事件
        Thread ioThread = new Thread(() -> {
            try {
                while (true) {
                    // 通过Selector检查就绪的通道
                    selector.select();
                    // 获取就绪的SelectionKey集合
                    Set<SelectionKey> keys = selector.selectedKeys();
                    // 遍历就绪的SelectionKey集合，处理每个就绪的通道
                    Iterator<SelectionKey> it = keys.iterator();
                    while (it.hasNext()) {
                        SelectionKey key = it.next();
                        it.remove();
                        if (key.isAcceptable()) {
                            // 处理客户端连接请求
                            SocketChannel clientChannel = serverChannel.accept();
                            clientChannel.configureBlocking(false);
                            clientChannel.register(selector, SelectionKey.OP_READ);
                        } else if (key.isReadable()) {
                            // 处理客户端读事件
                            SocketChannel clientChannel = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                            int len = clientChannel.read(buffer);
                            if (len > 0) {
                                buffer.flip();
                                byte[] data = new byte[len];
                                buffer.get(data);
                                System.out.println(new String(data));
                            } else if (len == -1) {
                                // 客户端关闭连接，取消对该通道的注册
                                key.cancel();
                                clientChannel.close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // 启动I/O线程
        ioThread.start();
    }
}
