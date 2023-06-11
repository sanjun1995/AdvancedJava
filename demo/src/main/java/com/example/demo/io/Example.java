package com.example.demo.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Example {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;

        try {
            fileInputStream = new FileInputStream("input.txt");
            bufferedInputStream = new BufferedInputStream(fileInputStream);
        } catch (IOException e) {
        } finally {
            // 关闭流对象
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

