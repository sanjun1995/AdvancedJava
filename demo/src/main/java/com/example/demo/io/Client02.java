package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/28 14:22
 */
import java.io.*;
import java.net.*;
public class Client02 {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Client02(String host, int port) throws IOException {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void start() {
        try {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("Received from server: " + in.readLine());
            }
        } catch (IOException e) {}
    }

    public static void main(String[] args) throws IOException {
        Client02 client = new Client02("localhost", 8080);
        client.start();
    }
}
