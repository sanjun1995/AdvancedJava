package com.example.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

/**
 * @author caozhixin
 * @date 2023/4/1 20:55
 */
// EchoClient类，用于启动客户端并连接服务器
public class EchoClient {
    private final String host; // 服务器主机名
    private final int port; // 服务器端口号
    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    // 启动客户端
    public void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup(); // 创建NIO事件循环组
        try {
            Bootstrap bootstrap = new Bootstrap(); // 创建客户端引导程序
            bootstrap.group(group) // 指定事件循环组
                    .channel(NioSocketChannel.class) // 指定通道类型
                    .remoteAddress(new InetSocketAddress(host, port)) // 指定连接的服务器地址和端口号
                    .handler(new ChannelInitializer<SocketChannel>() { // 配置通道初始化器
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler()); // 添加客户端处理器
                        }
                    });
            ChannelFuture future = bootstrap.connect(host, port).sync();
            Channel channel = future.channel();
            if (future.isSuccess()) {
                // 连接成功
                System.out.println("EchoClient started and connected to " + future.channel().remoteAddress()); // 输出连接成功的提示信息
            } else {
                // 连接失败
                System.out.println("连接失败");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // 创建输入流
            while (true) { // 循环读取输入
                String line = reader.readLine(); // 读取一行输入
                if (line == null || "quit".equalsIgnoreCase(line)) { // 如果输入为空或是"quit"，则退出循环
                    break;
                }
                ByteBuf data = Unpooled.copiedBuffer(line, CharsetUtil.UTF_8);
                channel.writeAndFlush(data);
            }
            future.channel().closeFuture().sync(); // 等待通道关闭
            System.out.println("client close");
        } finally {
            group.shutdownGracefully().sync(); // 优雅关闭事件循环组
        }
    }
    // 主函数，创建EchoClient对象并启动客户端
    public static void main(String[] args) throws Exception {
        String host = "localhost"; // 服务器主机名
        int port = 8080; // 服务器端口号
        new EchoClient(host, port).start(); // 创建EchoClient对象并启动客户端
    }
}
// EchoClientHandler类，用于处理客户端的输入输出
class EchoClientHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(EchoClientHandler.class);
    // 读取通道中的消息并输出
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf data = (ByteBuf) msg;
        String message = data.toString(CharsetUtil.UTF_8);
        logger.info("Client received: {}", message);
    }
    // 处理异常并关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}