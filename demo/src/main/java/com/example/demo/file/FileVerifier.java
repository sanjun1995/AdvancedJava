package com.example.demo.file;

import java.io.*;
import java.security.*;

public class FileVerifier {
    public static void main(String[] args) throws Exception {
        String fileName = "/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/input.txt";
        String md5sum = "7eca689f0d3389d9dea66ae112e5cfd7"; // MD5 校验和

        // 计算文件的 MD5 校验和
        String fileMd5sum = getFileMd5sum(fileName);
        System.out.println("File MD5: " + fileMd5sum);

        // 验证文件的完整性
        if (md5sum.equals(fileMd5sum)) {
            System.out.println("File verified successfully.");
        } else {
            System.out.println("File verification failed.");
        }
    }

    // 计算文件的 MD5 校验和
    private static String getFileMd5sum(String fileName) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        FileInputStream fis = new FileInputStream(fileName);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            md.update(buffer, 0, len);
        }
        fis.close();
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}
