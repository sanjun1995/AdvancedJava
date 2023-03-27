package com.example.demo.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author caozhixin
 * @date 2023/3/26 23:30
 */
public class Demo03 {
    public static void main(String[] args) {
        String fileName = "demo/src/main/resources/input.txt";
        try (InputStream inputStream = new FileInputStream(fileName)) {
            byte[] buffer = new byte[8192]; // 定义缓冲区
            int length; // 记录已读取的数据长度
            while ((length = inputStream.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
