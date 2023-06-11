package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/27 19:50
 */
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
public class Client {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            try (Socket socket = new Socket("localhost", 8888);
                 OutputStream outputStream = socket.getOutputStream()) {
                String message = "hello, server1!";
                outputStream.write(message.getBytes("UTF-8"));
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
