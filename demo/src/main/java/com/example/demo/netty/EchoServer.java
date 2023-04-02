package com.example.demo.netty;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.ChannelHandlerContext;
import java.net.InetSocketAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class EchoServer {
    private static final Logger logger = LoggerFactory.getLogger(EchoServer.class);
    private final int port;
    // 构造函数，传入端口号
    public EchoServer(int port) {
        this.port = port;
    }
    // 启动服务器的方法
    public void start() throws Exception {
        // 创建事件循环组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建服务端启动器
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group) // 设置事件循环组
                    .channel(NioServerSocketChannel.class) // 设置通道类型
                    .localAddress(new InetSocketAddress(port)) // 设置本地地址
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 设置通道处理器
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerHandler()); // 添加处理器
                        }
                    });
            // 绑定端口并启动服务器
            ChannelFuture future = bootstrap.bind().sync();
            logger.info("EchoServer started and listening on {}", future.channel().localAddress());
            future.channel().closeFuture().sync();
            logger.info("server close");
        } finally {
            // 优雅关闭服务器
            group.shutdownGracefully().sync();
        }
    }
    // 主函数
    public static void main(String[] args) throws Exception {
        int port = 8080;
        new EchoServer(port).start(); // 启动服务器
    }
}
// 自定义处理器
class EchoServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(EchoServerHandler.class);
    // 处理连接建立事件
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("Channel active: {}", ctx.channel().remoteAddress());
    }
    // 处理读事件
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("Server received: {}", msg);
        ctx.write(msg); // 将消息写回客户端
    }
    // 读事件读取完成后的处理
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete");
        ctx.flush(); // 刷新缓冲区
    }
    // 处理异常事件
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("Exception caught: {}", cause.getMessage(), cause);
        ctx.close(); // 关闭通道
    }
}