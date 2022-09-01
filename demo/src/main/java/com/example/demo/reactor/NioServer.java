package com.example.demo.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author caozhixin
 * @date 2022/8/31 4:43 PM
 */
public class NioServer {
    private static final int SERVER_PORT = 9080;

    private static class Accepter implements Runnable {
        // main Reactor线程池（单线程），用于处理客户端的连接请求
        private static ExecutorService mainReactor = Executors.newSingleThreadExecutor(new ThreadFactory() {
            private AtomicInteger num = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("main-reactor-" + num.incrementAndGet());
                return t;
            }
        });

        @Override
        public void run() {
            try {
                ServerSocketChannel ssc = ServerSocketChannel.open();
                ssc.configureBlocking(false);
                ssc.bind(new InetSocketAddress(SERVER_PORT));

                // 转发请求到MainReactor
                dispatch(ssc);
                System.out.println("服务端已启动。。。");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 本质：把ssc的行使权交给mainReactor，故mainReactor处理客户端请求
         * @param ssc
         */
        private void dispatch(ServerSocketChannel ssc) {
            mainReactor.submit(new MainReactor(ssc));
        }
    }

    public static void main(String[] args) {
        new Thread(new Accepter()).start();
    }
}
