package com.example.demo.io;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author caozhixin
 * @date 2023/3/22 15:39
 */
public class Demo01 {
    public static void main(String[] args) {
        String fileName = "demo/src/main/resources/input.txt";
        try (FileOutputStream outputStream = new FileOutputStream(fileName);
             FileInputStream inputStream = new FileInputStream(fileName)) {
            // 写入
            byte[] words = "Hello World!!!".getBytes(StandardCharsets.UTF_8);
            outputStream.write(words);
            // 读取
            int word;
            while ((word = inputStream.read()) != -1) {
                System.out.println((char) word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
