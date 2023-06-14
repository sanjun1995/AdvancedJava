package com.example.demo.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @author caozhixin
 * @date 2023/6/13 23:34
 */
public class CloseDemo {
    public static void main(String[] args) throws IOException {
        new Thread(() -> test1()).start();
        new Thread(() -> test1()).start();
    }

    public static void test1() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("demo/src/main/resources/input.txt");
            FileChannel fileChannel = fileOutputStream.getChannel();

            FileLock lock = fileChannel.tryLock(); // 尝试获取文件锁
            if (lock != null) {
                // 成功获取文件锁
                try {
                    // 进行文件操作
                    fileOutputStream.write(1);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.release(); // 释放文件锁
                }
            } else {
                // 获取文件锁失败
                System.out.println("无法获取文件锁");
            }

            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
