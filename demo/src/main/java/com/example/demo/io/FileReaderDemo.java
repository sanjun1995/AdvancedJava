package com.example.demo.io;

import java.io.*;

/**
 * @author caozhixin
 * @date 2023/3/25 13:04
 */
public class FileReaderDemo {
    public static void main(String[] args) {
        // 读取文件内容
        try (FileReader fr = new FileReader("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/input.txt");
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 将字符串写入文件
        try (FileWriter fw = new FileWriter("output.txt")) {
            String line = "Hello, world!";
            fw.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
