package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/25 00:08
 */
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class HdfsReaderDemo {
    public static void main(String[] args) throws Exception {
        String hdfsUri = "hdfs://localhost:9000";
        String filePath = "/test.txt";
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", hdfsUri);
        FileSystem fs = FileSystem.get(conf);
        BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(new Path(filePath))));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
        fs.close();
    }
}
