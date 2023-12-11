//package com.example.demo.io;
//
///**
// * @author caozhixin
// * @date 2023/3/24 15:32
// */
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.nio.channels.SelectionKey;
//import java.nio.channels.Selector;
//import java.nio.channels.ServerSocketChannel;
//import java.util.Iterator;
//import java.util.Set;
//public class SelectDemo {
//    private Selector selector;
//    private ServerSocketChannel channel;
//    public void init() throws IOException {
//        this.selector = Selector.open();
//        this.channel = ServerSocketChannel.open();
//        this.channel.socket().bind(new InetSocketAddress(8888));
//        this.channel.configureBlocking(false);
//        this.channel.register(this.selector, SelectionKey.OP_ACCEPT);
//    }
//    public void start() throws IOException {
//        while (true) {
//            int selected = this.selector.select();
//            System.out.println("start start start");
//            System.out.println(selected);
//            if (selected == 0) {
//                continue;
//            }
//            Set<SelectionKey> keys = this.selector.selectedKeys();
//            Iterator<SelectionKey> iterator = keys.iterator();
//            while (iterator.hasNext()) {
//                SelectionKey key = iterator.next();
//                if (key.isAcceptable()) {
//                    // 处理连接请求
//                }
//                iterator.remove();
//            }
//        }
//    }
//    public void wakeup() {
//        Selector selector = this.selector;
//        if (selector != null) {
//            selector.wakeup();
//        }
//    }
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        SelectDemo demo = new SelectDemo();
//        demo.init();
//        new Thread(() -> {
//            try {
//                demo.start();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        // 在其他线程中调用 wakeup 方法，例如：
//        Thread.sleep(2000);
//        demo.wakeup();
//    }
//}