package com.example.demo.io;

import java.io.*;

/**
 * @author caozhixin
 * @date 2023/3/27 16:56
 */
public class Demo05 {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "demo/src/main/resources/data.txt";
        // 写入
        try (FileOutputStream fos = new FileOutputStream(fileName);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             DataOutputStream dos = new DataOutputStream(bos)) {
            int intValue = 42;
            double doubleValue = 3.14159;
            String stringValue = "Hello, world!";
            dos.writeInt(intValue);
            dos.writeDouble(doubleValue);
            dos.writeUTF(stringValue);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 读取
        try (FileInputStream fis = new FileInputStream(fileName);
             BufferedInputStream bis = new BufferedInputStream(fis);
             DataInputStream dis = new DataInputStream(bis)) {
            int intValue = dis.readInt();
            double doubleValue = dis.readDouble();
            String stringValue = dis.readUTF();
            System.out.println("intValue = " + intValue);
            System.out.println("doubleValue = " + doubleValue);
            System.out.println("stringValue = " + stringValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
