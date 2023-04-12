package com.example.demo.netty;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.*;

public class ZeroCopyExample {
    private static final int BUFFER_SIZE = 1024 * 64; // 缓冲区大小为 64 KB

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        transferFile("demo/src/main/resources/input.txt", "demo/src/main/resources/output.txt");
    }

    public static void transferFile(String sourceFilePath, String destFilePath) throws IOException, InterruptedException, ExecutionException {
        FileChannel sourceChannel = new FileInputStream(sourceFilePath).getChannel();
        FileChannel destChannel = new FileOutputStream(destFilePath).getChannel();
        long fileSize = sourceChannel.size();
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); // 创建线程池

        long position = 0;
        long bytesTransferred = 0;

        while (position < fileSize) {
            long remainingBytes = fileSize - position;

            // 当文件大小小于等于缓冲区大小时，直接读取源文件并向目标文件写入数据
            if (remainingBytes <= BUFFER_SIZE) {
                byte[] buffer = new byte[(int) remainingBytes];
                ByteBuffer bb = ByteBuffer.wrap(buffer);
                sourceChannel.read(bb, position);
                bb.flip(); // 切换到读模式
                destChannel.write(bb, position);
                bytesTransferred += remainingBytes;
                position += remainingBytes;
            } else {
                CompletionService<Long> completionService = new ExecutorCompletionService<>(service); // 创建任务完成服务

                // 将文件分成多个部分，并使用多个线程进行并行传输
                for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
                    long fromPosition = position + i * BUFFER_SIZE;
                    long toPosition = Math.min(position + (i + 1) * BUFFER_SIZE, fileSize);
                    long length = toPosition - fromPosition;

                    // 提交任务到线程池，使用 transferTo() 方法进行传输
                    completionService.submit(() -> transferTo(sourceChannel, destChannel, fromPosition, length));
                }

                // 等待所有任务完成，并将传输的字节数累加起来
                for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
                    Future<Long> future = completionService.take();
                    bytesTransferred += future.get();
                }

                position += bytesTransferred;
            }
        }

        service.shutdown();
    }

    // 使用 transferTo() 方法实现数据传输
    private static long transferTo(FileChannel sourceChannel, FileChannel destChannel, long fromPosition, long length) throws IOException {
        long transferred = 0;

        while (transferred != length) {
            transferred += sourceChannel.transferTo(fromPosition + transferred, length - transferred, destChannel);
        }

        return transferred;
    }
}

