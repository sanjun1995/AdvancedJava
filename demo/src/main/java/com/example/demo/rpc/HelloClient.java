package com.example.demo.rpc;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author caozhixin
 * @date 2023/3/30 23:55
 */
public class HelloClient {
    // 服务提供者地址和端口
    private String host;
    private int port;
    // 构造方法，传入服务提供者地址和端口
    public HelloClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public String sayHello(String name) throws IOException {
        int retries = 3; // 最多重试3次
        while (retries > 0) {
            try {
                // 创建Socket连接服务提供者
                Socket socket = new Socket(host, port);
                // 创建输出流，用于向服务端写入数据
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                // 创建输入流，用于接收服务端返回的数据
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                // 写入服务名称、方法名称、参数类型和参数值到输出流中
                out.writeUTF("HelloService");
                out.writeUTF("sayHello");
                out.writeObject(new Class<?>[]{String.class});
                out.writeObject(new Object[]{name});
                // 从输入流中读取服务端返回的数据
                String result = in.readUTF();
                // 关闭流和Socket连接
                out.close();
                in.close();
                socket.close();
                // 返回服务端返回的数据
                return result;
            } catch (EOFException e) {
                // 出现异常，重试
                retries--;
                if (retries == 0) {
                    // 重试次数达到上限，输出错误日志
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        // 创建服务消费者并调用服务
        HelloClient client = new HelloClient("localhost", 8888);
        String result = client.sayHello("Alice");
        System.out.println(result);
    }
}
