package com.example.demo.io;
import java.io.*;

/**
 * @author caozhixin
 * @date 2023/3/22 15:39
 */
public class Demo01 {
    public static void main(String[] args) {
        try {
            String fileName = "/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/input.txt";
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(fileName));
            dataInputStream.readUTF();
//            byte[] buffer = new byte[1024];
//            int len;
//            while ((len = dataInputStream.readUTF()) != -1) {
//                System.out.println(new String(buffer, 0, len));
//            }
            dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
