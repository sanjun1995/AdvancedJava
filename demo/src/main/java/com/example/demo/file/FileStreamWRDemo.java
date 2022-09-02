package com.example.demo.file;

import org.springframework.util.CollectionUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Vector;

/**
 * @author caozhixin
 * @date 2022/9/1 8:46 PM
 */
public class FileStreamWRDemo {
    public static void fileWrite(String filePath, String content) {
        FileOutputStream outputStream = null;
        try {
            File file = new File(filePath);
            boolean isCreate = file.createNewFile();
            if (isCreate) {
                outputStream = new FileOutputStream(file);
                outputStream.write(content.getBytes(StandardCharsets.UTF_8));
            } else {
                outputStream = new FileOutputStream(file, true);
                outputStream.write(content.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void fileRead(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                while (fis.read(bytes, 0, bytes.length) != -1) {
                    System.out.println(new String(bytes, StandardCharsets.UTF_8));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void mergeFile(List<String> inputPaths, String outputPath) throws Exception {
        Vector<InputStream> inputStreams = new Vector<>();
        if (CollectionUtils.isEmpty(inputPaths)) {
            throw new Exception("合并文件路径不能为空");
        }

        for (String inputPath : inputPaths) {
            InputStream inputStream = new FileInputStream(inputPath);
            inputStreams.add(inputStream);
        }

        SequenceInputStream sequenceInputStream = new SequenceInputStream(inputStreams.elements());
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputPath));
            byte[] bytes = new byte[10 * 1024];
            int len = -1;
            while ((len = sequenceInputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, len);
                bufferedOutputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                sequenceInputStream.close();
            } catch (IOException e) {

            }
        }
    }
}
