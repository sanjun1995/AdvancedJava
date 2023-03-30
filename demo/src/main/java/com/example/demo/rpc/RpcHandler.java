package com.example.demo.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @author caozhixin
 * @date 2023/3/30 23:57
 */
public class RpcHandler implements Runnable {
    // Socket连接客户端
    private Socket socket;
    // 存储服务名称和服务实现对象的映射
    private Map<String, Object> serviceMap;
    // 构造方法，传入Socket连接和serviceMap
    public RpcHandler(Socket socket, Map<String, Object> serviceMap) {
        this.socket = socket;
        this.serviceMap = serviceMap;
    }
    // 处理客户端请求
    @Override
    public void run() {
        try {
            // 创建输入流，用于接收客户端发送的数据
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            // 创建输出流，用于向客户端写入数据
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            // 从输入流中读取服务名称、方法名称、参数类型和参数值
            String serviceName = in.readUTF();
            String methodName = in.readUTF();
            Class<?>[] parameterTypes = (Class<?>[]) in.readObject();
            Object[] parameters = (Object[]) in.readObject();
            // 从serviceMap中获取服务实现对象
            Object serviceImpl = serviceMap.get(serviceName);
            // 如果serviceImpl为空，说明没有对应的服务提供者
            if (serviceImpl == null) {
                throw new RuntimeException("No provider available for service " + serviceName);
            }
            // 使用反射调用服务实现对象的方法
            Method method = serviceImpl.getClass().getMethod(methodName, parameterTypes);
            Object result = method.invoke(serviceImpl, parameters);
            // 将结果写入输出流中，返回给客户端
            out.writeUTF(result.toString());
            out.flush();
            // 关闭流和Socket连接
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
