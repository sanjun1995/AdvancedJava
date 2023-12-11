//package com.example.demo.rpc2;
//
///**
// * @author caozhixin
// * @date 2023/3/31 21:51
// */
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.nio.ByteBuffer;
//import java.nio.channels.SocketChannel;
//public class RpcClient {
//    private static final int BUFFER_SIZE = 1024;
//    private SocketChannel socketChannel;
//    public RpcClient(String host, int port) throws IOException {
//        // 首先创建一个SocketChannel实例
//        socketChannel = SocketChannel.open();
//        // 调用SocketChannel的connect方法连接到指定的服务器地址和端口号
//        socketChannel.connect(new InetSocketAddress(host, port));
//        // 将SocketChannel设置为非阻塞模式，以便在读写数据时不会阻塞线程。
//        socketChannel.configureBlocking(false);
//    }
//
//    public Response callService(String serviceName, String methodName, String[] args) throws IOException, InterruptedException {
//        // 构造请求数据
//        String requestData = serviceName + "|" + methodName + "|" + String.join(",", args);
//        // 分配缓冲区
//        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
//        // 将请求数据写入缓冲区
//        buffer.put(requestData.getBytes());
//        // 切换为读模式
//        buffer.flip();
//        // 将缓冲区的数据写入到 socket 通道中
//        socketChannel.write(buffer);
//        // 清空缓冲区
//        buffer.clear();
//        // 从 socket 通道中读取响应数据
//        int numBytes;
//        while (true) {
//            numBytes = socketChannel.read(buffer);
//            if (numBytes > 0) {
//                break;
//            } else if (numBytes == 0) {
//                continue;
//            } else {
//                // 发生了错误，抛出异常
//                throw new IOException("Error reading data from server");
//            }
//        }
//        // 将缓冲区的数据转换为字符串
//        String responseData = new String(buffer.array(), 0, numBytes);
//        // 返回响应对象
//        return new Response(responseData);
//    }
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        RpcClient rpcClient = new RpcClient("localhost", 8080);
//        Response response = rpcClient.callService("userService", "getUser", new String[]{"1"});
//        System.out.println(response.getData());
//    }
//}
