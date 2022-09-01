package com.example.demo.reactor;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author caozhixin
 * @date 2022/8/31 10:35 PM
 */
public class NioClient {
    public static void main(String[] args) {
        SocketChannel socketChannel;
        Selector selector;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 9080));
            Set<SelectionKey> ops;
            while (true) {
                try {
                    selector.select();
                    ops = selector.selectedKeys();
                    for (Iterator<SelectionKey> it = ops.iterator(); it.hasNext();) {
                        SelectionKey key = it.next();
                        it.remove();
                        if (key.isConnectable()) {
                            System.out.println("client connect。。。");
                            SocketChannel sc =  (SocketChannel) key.channel();
                            if (sc.isConnectionPending()) {
                                sc.finishConnect();
                                System.out.println("完成连接。。。");
                                ByteBuffer buffer = ByteBuffer.allocate(1024);
                                buffer.put("Hello Server".getBytes());
                                buffer.flip();
                                sc.write(buffer);
                            }
                            sc.register(selector, SelectionKey.OP_READ);
                        } else if (key.isWritable()) {
                            System.out.println("客户端写入。。。");
                            SocketChannel sc = (SocketChannel)key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            buffer.put("hello server".getBytes());
                            buffer.flip();
                            sc.write(buffer);
                        } else if (key.isReadable()) {
                            System.out.println("客户端收到服务端的回复。。。");
                            SocketChannel sc =  (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            int count = sc.read(buffer);
                            if (count > 0) {
                                buffer.flip();
                                byte[] response = new byte[buffer.remaining()];
                                buffer.get(response);
                                System.out.println("response: " + new String(response));
                            }
//                            buffer = ByteBuffer.allocate(1024);
//                            buffer.put("hello server".getBytes());
//                            buffer.flip();
//                            sc.write(buffer);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
