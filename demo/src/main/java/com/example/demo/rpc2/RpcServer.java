package com.example.demo.rpc2;

/**
 * @author caozhixin
 * @date 2023/3/31 21:41
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class RpcServer {
    // 缓冲区大小
    private static final int BUFFER_SIZE = 1024;
    // 服务端Socket通道
    private ServerSocketChannel serverSocketChannel;
    // 选择器
    private Selector selector;
    // 注册的服务
    private Map<String, Service> services = new HashMap<>();
    // 构造函数，初始化ServerSocketChannel和Selector
    public RpcServer(int port) throws IOException {
        // 打开ServerSocketChannel
        serverSocketChannel = ServerSocketChannel.open();
        // 绑定端口号
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        // 设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 打开Selector
        selector = Selector.open();
        // 将ServerSocketChannel注册到Selector上，监听ACCEPT事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }
    // 注册服务
    public void registerService(String serviceName, Service service) {
        services.put(serviceName, service);
    }
    // 启动服务
    public void start() throws IOException {
        while (true) {
            // 阻塞等待事件
            selector.select();
            // 获取所有的事件
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                // 处理ACCEPT事件
                if (key.isAcceptable()) {
                    handleAccept(key);
                    // 处理READ事件
                } else if (key.isReadable()) {
                    handleRead(key);
                }
                // 处理完事件后移除该事件
                keyIterator.remove();
            }
        }
    }
    // 处理ACCEPT事件
    private void handleAccept(SelectionKey key) throws IOException {
        // 获取ServerSocketChannel
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        // 接收客户端连接
        SocketChannel socketChannel = serverChannel.accept();
        // 设置为非阻塞模式
        socketChannel.configureBlocking(false);
        // 将SocketChannel注册到Selector上，监听READ事件
        socketChannel.register(selector, SelectionKey.OP_READ);
    }
    // 处理READ事件
    private void handleRead(SelectionKey key) throws IOException {
        // 获取SocketChannel
        SocketChannel socketChannel = (SocketChannel) key.channel();
        // 分配缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        // 读取数据
        int numBytes = socketChannel.read(buffer);
        // 如果读取到末尾，关闭连接
        if (numBytes == -1) {
            key.cancel();
            socketChannel.close();
            return;
        }
        // 解析请求数据
        String requestData = new String(buffer.array(), 0, numBytes);
        String[] requestArray = requestData.split("\\|");
        if (requestArray.length != 3) {
            // 如果请求格式不正确，发送错误响应
            sendError(socketChannel, "Invalid request format");
            return;
        }
        // 获取服务名、方法名和参数
        String serviceName = requestArray[0];
        String methodName = requestArray[1];
        String[] args = requestArray[2].split(",");
        // 获取对应的服务
        Service service = services.get(serviceName);
        if (service == null) {
            // 如果找不到对应的服务，发送错误响应
            sendError(socketChannel, "No such service: " + serviceName);
            return;
        }
        // 调用对应的方法，获取响应
        Response response = service.callMethod(methodName, args);
        String responseData = response.getData();
        ByteBuffer responseBuffer = ByteBuffer.allocate(responseData.getBytes().length);
        responseBuffer.put(responseData.getBytes());
        responseBuffer.flip();
        // 发送响应
        socketChannel.write(responseBuffer);
    }
    // 发送错误响应
    private void sendError(SocketChannel socketChannel, String errorMessage) throws IOException {
        Response response = new Response(errorMessage);
        String responseData = response.getData();
        ByteBuffer responseBuffer = ByteBuffer.allocate(responseData.getBytes().length);
        responseBuffer.put(responseData.getBytes());
        responseBuffer.flip();
        socketChannel.write(responseBuffer);
    }
    public static void main(String[] args) throws IOException {
        // 创建RpcServer实例
        RpcServer rpcServer = new RpcServer(8080);
        // 注册服务
        rpcServer.registerService("userService", new UserServiceImpl());
        // 启动服务
        rpcServer.start();
    }
}