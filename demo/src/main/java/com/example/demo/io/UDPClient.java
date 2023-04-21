package com.example.demo.io;

import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        int port = 9876;

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        String sentence = "Hello Server!";
        sendData = sentence.getBytes();

        clientSocket.connect(IPAddress, port);

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("FROM SERVER:" + modifiedSentence);

        clientSocket.close();
    }
}
