package com.example.demo.file;

import com.baomidou.mybatisplus.extension.api.R;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author caozhixin
 * @date 2022/9/1 8:47 PM
 */
public class MappedRWDemo {
    public static long fileWrite(String filePath, String content, int index) {
        File file = new File(filePath);
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            // 1k = 1024byte
            // 1M = 1024k
            // 1G = 1024M
            // 1G = 1 * 1024 * 1024 * 1024
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 1 * 1024 * 1024 * 1024);

//            ByteBuffer buffer = ByteBuffer.allocateDirect(content.length());
//            buffer.put(content.getBytes(StandardCharsets.UTF_8));
//            buffer.flip();
//            while (buffer.hasRemaining()) {
//                fileChannel.write(buffer);
//            }
//            byte[] bytes = new byte[content.length()];
//            mappedByteBuffer.get(bytes);
//            System.out.println(new String(bytes));

            mappedByteBuffer.position(index);
            mappedByteBuffer.put(content.getBytes());
            return mappedByteBuffer.position();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return 0L;
    }
    //    String woshihaoren = "woshihaoren";
//        try (RandomAccessFile f = new RandomAccessFile("D:\\a.txt", "rw")) {
//        FileChannel fc = f.getChannel();
//        // 创建一个MappedByteBuffer,此时MappedByteBuffer获取到的内容都是空
//        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, woshihaoren.length());
//        // 将字符串写入
//        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(woshihaoren.length());
//        byteBuffer.put(woshihaoren.getBytes());
//        byteBuffer.flip();
//        while (byteBuffer.hasRemaining()) {
//            fc.write(byteBuffer);
//        }
//
//        // MappedByteBuffer再次获取内存中的内容,获取到的内容是woshihaoren
//        byte[] bytes = new byte[woshihaoren.length()];
//        mbb.get(bytes);
//        System.out.println(new String(bytes));
//    } catch (Exception e) {
//        e.printStackTrace();
//    }

    public static String fileRead(String filePath, long index) {
        File file = new File(filePath);
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, index);
            byte[] bytes = new byte[10 * 1024];
            mappedByteBuffer.get(bytes, 0, (int) index);
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return "";
    }
}
