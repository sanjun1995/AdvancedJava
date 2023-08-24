package com.example.demo.zip;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import lombok.Data;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

@Data
public class UnZipUtils {
    static int corePoolSize = 100;
    static int maxPoolSize = 100;
    static int queueCapacity = 1024 * 1024 * 20;
    static ThreadPoolExecutor executorService = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(queueCapacity), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws Exception {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.@#$!*&";
        int maxLength = 20;

        for (int length = 1; length <= maxLength; length++) {
            generatePasswords(characters, length);
        }
        executorService.shutdown();
    }

    private static void generatePasswords(String characters, int length) throws Exception {
        executorService.submit(() -> {
            UnZipUtils z = new UnZipUtils();
            String source = "/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/abcd.zip";
            String dest = "/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/";
            for (String password : genPasswordIterator(characters, length)) {
                if (z.unZip(source, dest, password)) {
                    System.out.println("密码破解成功：" + password);
                    executorService.shutdownNow();
                    break;
                }
            }
        });
    }

    private static Iterable<String> genPasswordIterator(String characters, int length) {
        return () -> new PasswordIterator(characters, length);
    }

    private static class PasswordIterator implements java.util.Iterator<String> {
        private final String characters;
        private final int length;
        private final int[] indices;
        private boolean hasNext = true;

        public PasswordIterator(String characters, int length) {
            this.characters = characters;
            this.length = length;
            this.indices = new int[length];
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public String next() {
            StringBuilder sb = new StringBuilder();
            for (int index : indices) {
                sb.append(characters.charAt(index));
            }
            int i = length - 1;
            while (i >= 0 && indices[i] == characters.length() - 1) {
                indices[i] = 0;
                i--;
            }
            if (i < 0) {
                hasNext = false;
            } else {
                indices[i]++;
            }
            return sb.toString();
        }
    }

    /**
     * @param source   原始文件路径
     * @param dest     解压路径
     * @param password 解压文件密码(可以为空)
     */
    public boolean unZip(String source, String dest, String password) {
        try {
            File zipFile = new File(source);

            // 首先创建ZipFile指向磁盘上的.zip文件
            ZipFile zFile = new ZipFile(zipFile);

            zFile.setFileNameCharset("utf-8");

            // 解压目录
            File destDir = new File(dest);
            if (!destDir.exists()) {
                // 目标目录不存在时，创建该文件夹
                destDir.mkdirs();
            }
            if (zFile.isEncrypted()) {
                // 设置密码
                zFile.setPassword(password.toCharArray());
            }

            // 将文件抽出到解压目录(解压)
            zFile.extractAll(dest);

            List<FileHeader> headerList = zFile.getFileHeaders();

            List<File> extractedFileList = new ArrayList<File>();
            for (FileHeader fileHeader : headerList) {
                if (!fileHeader.isDirectory()) {
                    extractedFileList.add(new File(destDir, fileHeader.getFileName()));
                }
            }
            File[] extractedFiles = new File[extractedFileList.size()];
            extractedFileList.toArray(extractedFiles);

            for (File f : extractedFileList) {
                System.out.println(f.getAbsolutePath() + "文件解压成功!");
                return true;
            }
        } catch (ZipException e) {
            System.out.println("___password:" + password);
        }
        return false;
    }
}