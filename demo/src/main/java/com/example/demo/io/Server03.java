package com.example.demo.io;

import java.net.*;

public class Server03 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(12345);
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String message = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Received message: " + message);
        socket.close();
    }
}

