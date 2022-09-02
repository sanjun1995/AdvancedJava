package com.example.demo.file;

import com.baomidou.mybatisplus.extension.api.R;

import java.util.Random;

/**
 * @author caozhixin
 * @date 2022/9/1 8:47 PM
 */
public class MainDemo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("start: " + start);
        int count = 10;
        int type = 1;
        String name = "test";
        for (int i = 0; i < count; i++) {
            System.out.println("file start: " + System.currentTimeMillis());
            if (type == 1) {
                String fileName = name + i + ".txt";
                MappedRWDemo.fileWrite(fileName, getFixStr(5000000), 0);
            } else if (type == 2) {
                String fileName = name + i + ".txt";
                FileStreamWRDemo.fileWrite(fileName, getFixStr(5000000));
            } else {
                System.exit(0);
            }
            System.out.println("file end: " + System.currentTimeMillis());
        }
        long end = System.currentTimeMillis();
        System.out.println("end: " + end + " total_time: " + (end - start));
    }

    public static String getRandomNumStr(long n) {
        Random random = new Random();
        StringBuilder randomStr = new StringBuilder();
        for (long i = 0; i < n; i++) {
            randomStr.append(random.nextInt(10));
        }
        return randomStr.toString();
    }

    private static String getFixStr(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append("hello hi how are you i am ok i am fine do you i love you you love me");
        }
        return stringBuilder.toString();
    }
}
