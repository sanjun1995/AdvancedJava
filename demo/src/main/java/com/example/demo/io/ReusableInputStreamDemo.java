package com.example.demo.io;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class ReusableInputStreamDemo {

    public static void main(String[] args) {
        try {
            // 创建一个 URL 对象并打开网络连接
            URL url = new URL("https://www.baidu.com");
            URLConnection connection = url.openConnection();

            // 获取网络连接的输入流
            InputStream networkInputStream = connection.getInputStream();

            // 存储从网络输入流读取的数据
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = networkInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            // 创建一个 ByteArrayInputStream 实例并进行第一次读取
            byte[] data = byteArrayOutputStream.toByteArray();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
            processInputStream(inputStream);

            // 重置 ByteArrayInputStream 的位置以进行第二次读取
            inputStream.reset();
            processInputStream(inputStream);

            // 关闭网络输入流
            networkInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 处理输入流的方法
    private static void processInputStream(InputStream inputStream) {
        try {
            int bytesRead;
            byte[] buffer = new byte[1024];

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String chunk = new String(buffer, 0, bytesRead);
                System.out.println("Read: " + chunk);
                // 在这里处理读取到的数据，可以是对账操作等
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

