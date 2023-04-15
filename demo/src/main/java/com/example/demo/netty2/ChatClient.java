package com.example.demo.netty2;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChatClient {

    private final String host;
    private final int port;

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new MessageDecoder());
                            pipeline.addLast(new MessageEncoder());
                        }
                    });

            ChannelFuture f = b.connect(host, port).sync();
            System.out.println("Chat Client connected to " + host + ":" + port);

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String message = in.readLine();
                if (message == null || message.isEmpty()) {
                    continue;
                }
                ByteBuf byteBuf = f.channel().alloc().buffer();
                byteBuf.writeBytes(message.getBytes());
                f.channel().writeAndFlush(byteBuf);
            }
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new ChatClient("localhost", 8080).start();
    }
}
