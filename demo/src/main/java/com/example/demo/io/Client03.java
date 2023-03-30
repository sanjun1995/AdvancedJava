package com.example.demo.io;

import java.io.IOException;

/**
 * @author caozhixin
 * @date 2023/3/28 14:43
 */
public class Client03 {
    public static void main(String[] args) throws IOException {
        Client02 client = new Client02("localhost", 8080);
        client.start();
    }
}
