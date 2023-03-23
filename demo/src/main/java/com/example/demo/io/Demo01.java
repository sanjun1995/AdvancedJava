package com.example.demo.io;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author caozhixin
 * @date 2023/3/22 15:39
 */
public class Demo01 {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/input.txt");
            int c;
            while ((c = fis.read()) != -1) {
                System.out.print((char) c);
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
