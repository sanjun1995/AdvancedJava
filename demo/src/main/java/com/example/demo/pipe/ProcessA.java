package com.example.demo.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author caozhixin
 * @date 2023/4/3 23:37
 */
public class ProcessA {
    public static void main(String[] args) throws IOException {
        PipedOutputStream output = new PipedOutputStream();
        PipedInputStream input = new PipedInputStream(output);

        String message = "hello";
        output.write(message.getBytes());

        byte[] buffer = new byte[1024];
        int length = input.read(buffer);
        System.out.println(new String(buffer, 0, length));
    }
}
