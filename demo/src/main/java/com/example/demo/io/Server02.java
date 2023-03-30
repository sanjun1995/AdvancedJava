package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/28 14:20
 */
import java.io.*;
import java.net.*;
public class Server02 {
    private ServerSocket serverSocket;
    private boolean running;

    public Server02(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        running = true;
    }

    public void start() {
        while (running) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client: " + socket.getInetAddress().getHostAddress() + " port: " + socket.getPort());
                // 开启新线程处理socket
                new ClientHandler(socket).start();
            } catch (IOException e) {}
        }
    }

    public static void main(String[] args) throws IOException {
        Server02 server = new Server02(8080);
        server.start();
    }
}

class ClientHandler extends Thread {
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void run() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received from client: " + inputLine);
                out.println("Hello Client, I received your message!");
            }
        } catch (IOException e) {}
    }
}
