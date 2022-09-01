package com.example.demo.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author caozhixin
 * @date 2022/8/31 8:40 PM
 */
public class SubReactorThread extends Thread {
    private Selector selector;
    private ExecutorService businessExecutorPool;
    private List<NioTask> nioTasks = new ArrayList<>();
    private ReentrantLock reentrantLock = new ReentrantLock();

    public SubReactorThread(ExecutorService businessExecutorPool) {
        try {
            this.businessExecutorPool = businessExecutorPool;
            this.selector = Selector.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register(NioTask nioTask) {
        if (nioTask != null) {
            try {
                reentrantLock.lock();
                nioTasks.add(nioTask);
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public void run() {
        System.out.println("SubReactorThread 启动：" + Thread.currentThread().getName());
        while(!Thread.interrupted()) {
            Set<SelectionKey> ops = null;
            try {
                selector.select(1000);
                ops = selector.selectedKeys();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (Iterator<SelectionKey> it = ops.iterator(); it.hasNext();) {
                SelectionKey key = it.next();
                it.remove();
                try {
                    if (key.isWritable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        socketChannel.write(buffer);
                        System.out.println("服务端向客户端发送数据。。。");

                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        System.out.println("服务端收到客户端的请求。。。");
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);

                        socketChannel.read(buffer);
                        dispatch(socketChannel, buffer);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("客户端主动断开连接。。。");
                }
            }

            if (!nioTasks.isEmpty()) {
                System.out.println("存在任务");
                try {
                    reentrantLock.lock();
                    for (Iterator<NioTask> it = nioTasks.iterator(); it.hasNext();) {
                        NioTask nioTask = it.next();
                        try {
                            SocketChannel socketChannel = nioTask.getSocketChannel();
                            if (nioTask.getData() != null) {
                                ByteBuffer byteBuffer = (ByteBuffer) nioTask.getData();
                                byteBuffer.flip();
                                int count = socketChannel.write(byteBuffer);
                                if (count < 1 && byteBuffer.hasRemaining()) {
                                    socketChannel.register(selector, nioTask.getOp(), nioTask.getData());
                                }
                            } else {
                                socketChannel.register(selector, nioTask.getOp());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        it.remove();
                    }
                } finally {
                    reentrantLock.unlock();
                }
            }
        }
    }

    private void dispatch(SocketChannel socketChannel, ByteBuffer buffer) {
        businessExecutorPool.submit(new Handler(socketChannel, buffer, this));
    }
}
