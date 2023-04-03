package com.example.demo.socket;

/**
 * @author caozhixin
 * @date 2023/4/2 10:37
 */
import java.net.*;
public class SocketDemo {
    public static void main(String[] args) throws Exception {
        String hostname = "www.baidu.com";
        InetAddress address = InetAddress.getByName(hostname);
        System.out.println("IP address of " + hostname + ": " + address.getHostAddress());
        Socket socket = new Socket(address, 80);
        System.out.println("Connected to " + socket.getRemoteSocketAddress());
        socket.close();
    }
}
