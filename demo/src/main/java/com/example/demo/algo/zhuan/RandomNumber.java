package com.example.demo.algo.zhuan;

import java.util.Arrays;
import java.util.Random;

/**
 * @author caozhixin
 * @date 2023/8/2 14:11
 */
public class RandomNumber {
    public static void main(String[] args) {
        // 生成随机数
        int arraySize = 32768;
        int data[] = new int[arraySize];
        Random rnd = new Random(0);
        for (int c = 0; c < arraySize; ++c) {
            data[c] = rnd.nextInt() % 256;
        }
        long start1 = System.nanoTime();
        long sum1 = 0;
        for (int i = 0; i < 100000; ++i) {
            for (int c = 0; c < arraySize; ++c) {
                if (data[c] >= 128) {
                    sum1 += data[c];
                }
            }
        }
        System.out.println((System.nanoTime() - start1) / 1000000000.0);
        System.out.println("sum = " + sum1);
        //排序
        Arrays.sort(data);
        // 累加，测试执行时间
        long start = System.nanoTime();
        long sum = 0;
        for (int i = 0; i < 100000; ++i) {
            for (int c = 0; c < arraySize; ++c) {
                if (data[c] >= 128) {
                    sum += data[c];
                }
            }
        }
        System.out.println((System.nanoTime() - start) / 1000000000.0);
        System.out.println("sum = " + sum);
    }
}
