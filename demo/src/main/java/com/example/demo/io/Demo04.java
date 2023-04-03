package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/27 12:59
 */
import java.io.*;

public class Demo04 {
    public static void main(String[] args) {
        String fileName = "demo/src/main/resources/input.txt";
        try (FileInputStream fis = new FileInputStream(fileName);
             BufferedInputStream bis = new BufferedInputStream(fis, 10)) {
            // 读取文件内容
            byte[] buffer = new byte[6];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                System.out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
