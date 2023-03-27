package com.example.demo.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

/**
 * @author caozhixin
 * @date 2023/3/25 12:41
 */
public class GZIPStreamDemo {
    public static void main(String[] args) throws IOException {
        try (FileInputStream fis = new FileInputStream("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/wechat_v3.txt.zip");
             BufferedInputStream bis = new BufferedInputStream(fis);
             GZIPInputStream gzis = new GZIPInputStream(bis);
             InputStreamReader isr = new InputStreamReader(gzis, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                // 处理读取到的数据
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
