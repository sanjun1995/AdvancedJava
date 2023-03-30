package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/27 20:00
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                byte[] buffer = new byte[1024];
                int len;
                StringBuilder sb = new StringBuilder();
                while ((len = inputStream.read(buffer)) != -1) {
                    sb.append(new String(buffer, 0, len, "UTF-8"));
                }
                System.out.println("接收到客户端消息：" + sb);
//                inputStream.close();
//                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
