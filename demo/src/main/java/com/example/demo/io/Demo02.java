package com.example.demo.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author caozhixin
 * @date 2023/3/26 22:47
 */
public class Demo02 {
    public static void main(String[] args) {
        String fileName = "demo/src/main/resources/input.txt";
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // 处理每行文本
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
