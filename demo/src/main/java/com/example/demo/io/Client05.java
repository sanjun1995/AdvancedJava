package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/29 10:43
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
public class Client05 {
    private static final int BUFFER_SIZE = 1024;
    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 6666;
    private SocketChannel channel;
    private Selector selector;
    private ByteBuffer readBuffer;
    private ByteBuffer writeBuffer;
    public Client05() throws IOException {
        channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress(SERVER_HOST, SERVER_PORT));
        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_CONNECT);
        readBuffer = ByteBuffer.allocate(BUFFER_SIZE);
        writeBuffer = ByteBuffer.allocate(BUFFER_SIZE);
    }
    public void start() throws IOException {
        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isConnectable()) {
                    handleConnect(key);
                } else if (key.isReadable()) {
                    handleRead(key);
                } else if (key.isWritable()) {
                    handleWrite(key);
                }
            }
        }
    }
    private void handleConnect(SelectionKey key) throws IOException {
        if (channel.isConnectionPending()) {
            channel.finishConnect();
        }
        channel.register(selector, SelectionKey.OP_WRITE);
    }
    private void handleRead(SelectionKey key) throws IOException {
        readBuffer.clear();
        int count = channel.read(readBuffer);
        if (count > 0) {
            readBuffer.flip();
            System.out.println("Received message: " + new String(readBuffer.array(), 0, count));
        }
    }
    private void handleWrite(SelectionKey key) throws IOException {
        writeBuffer.clear();
        writeBuffer.put("Hello server".getBytes());
        writeBuffer.flip();
        channel.write(writeBuffer);
        channel.register(selector, SelectionKey.OP_READ);
    }
    public static void main(String[] args) throws IOException {
        Client05 client = new Client05();
        client.start();
    }
}