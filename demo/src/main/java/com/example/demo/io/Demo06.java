package com.example.demo.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author caozhixin
 * @date 2023/3/27 17:14
 */
public class Demo06 {
    public static void main(String[] args) throws FileNotFoundException {
        byte[] bytes = "Hello, world!".getBytes(StandardCharsets.UTF_8);
        // 读取
        try (ByteArrayInputStream is = new ByteArrayInputStream(bytes);
             BufferedInputStream bis = new BufferedInputStream(is)) {
            int c;
            while ((c = bis.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
