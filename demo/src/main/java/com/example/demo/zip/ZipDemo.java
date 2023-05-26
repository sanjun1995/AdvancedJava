package com.example.demo.zip;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author caozhixin
 * @date 2023/5/26 17:20
 */
public class ZipDemo {
    public static void main(String[] args) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/xxx.zip"));
        ZipEntry entry = zipInputStream.getNextEntry();

        while (entry != null) {
            if (!entry.isDirectory() && entry.getName().equals("xxx.csv")) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                // 获取整个 Entry 的 InputStream
                InputStream entryInputStream = new ByteArrayInputStream(outputStream.toByteArray());

                // 在这里可以对 entryInputStream 进行进一步处理

                // 关闭 entryInputStream
                entryInputStream.close();

                // 关闭输出流
                outputStream.close();
            }

            zipInputStream.closeEntry();
            entry = zipInputStream.getNextEntry();
        }

        zipInputStream.close();
    }
}
