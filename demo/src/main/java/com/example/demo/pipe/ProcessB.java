package com.example.demo.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author caozhixin
 * @date 2023/4/3 23:37
 */
public class ProcessB {
    public static void main(String[] args) throws IOException {
        PipedInputStream input = new PipedInputStream();
        PipedOutputStream output = new PipedOutputStream(input);

        byte[] buffer = new byte[1024];
        int length = input.read(buffer);
        String message = new String(buffer, 0, length);

        String response = message.toUpperCase();
        output.write(response.getBytes());
    }
}
