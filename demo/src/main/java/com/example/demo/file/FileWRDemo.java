package com.example.demo.file;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author caozhixin
 * @date 2022/9/1 8:45 PM
 */
public class FileWRDemo {
    public static void fileWrite(String filePath, String content) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileRead(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                char[] ch = new char[1];
                while (fileReader.read(ch) != -1) {
                    System.out.println(ch);
                }
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
