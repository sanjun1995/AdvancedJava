package com.example.demo.io;

import java.io.*;

/**
 * @author caozhixin
 * @date 2023/3/25 12:36
 */
public class DataStreamDemo {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/data.txt";
        new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
        new BufferedInputStream(new DataInputStream(new FileInputStream(fileName)));
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            int intValue = 42;
            double doubleValue = 3.14159;
            String stringValue = "Hello, world!";
            dos.writeInt(intValue);
            dos.writeDouble(doubleValue);
            dos.writeUTF(stringValue);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
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
