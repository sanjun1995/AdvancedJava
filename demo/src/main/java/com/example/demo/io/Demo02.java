package com.example.demo.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author caozhixin
 * @date 2023/3/26 22:47
 */
public class Demo02 {
    public static void main(String[] args) {
        String fileName = "demo/src/main/resources/input.txt";
        try (FileInputStream inputStream = new FileInputStream(fileName)) {
            int word;
            while ((word = inputStream.read()) != -1) {
                System.out.println((char) word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader fileReader = new FileReader(fileName)) {
            int word;
            while ((word = fileReader.read()) != -1) {
                System.out.println((char) word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

