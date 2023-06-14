package com.example.demo.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandleLeakDemo {
    public static void main(String[] args) throws IOException {
        int i = 0;
        while (true) {
            test();
//            System.gc();
            System.out.println(i++);
        }
    }

    public static void test() throws IOException {
        FileOutputStream fos = new FileOutputStream("demo/src/main/resources/input.txt");
        fos.write("Hello, world!".getBytes());
        // 不关闭文件流
    }
}


