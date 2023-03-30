package com.example.demo.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author caozhixin
 * @date 2023/3/28 15:33
 */
public class Client04 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8888);
             OutputStream outputStream = socket.getOutputStream()) {
            String message = "hello, server2!";
            outputStream.write(message.getBytes("UTF-8"));
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
