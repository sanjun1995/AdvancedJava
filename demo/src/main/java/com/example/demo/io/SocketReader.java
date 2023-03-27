package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/27 19:41
 */
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
public class SocketReader {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("www.baidu.com", 80));
        InputStream inputStream = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int length = inputStream.read(buffer);
        String data = new String(buffer, 0, length);
        System.out.println(data);
        inputStream.close();
        socket.close();
    }
}
