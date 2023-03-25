package com.example.demo.io;

/**
 * @author caozhixin
 * @date 2023/3/25 00:10
 */
import java.io.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.*;
public class MyOutputFormat extends TextOutputFormat<Text, IntWritable> {
    @Override
    public RecordWriter<Text, IntWritable> getRecordWriter(TaskAttemptContext job)
            throws IOException, InterruptedException {
        Configuration conf = job.getConfiguration();
        Path file = getDefaultWorkFile(job, "");
        FileSystem fs = file.getFileSystem(conf);
        FSDataOutputStream fileOut = fs.create(file, false);
        return new MyRecordWriter(fileOut);
    }
    private static class MyRecordWriter extends RecordWriter<Text, IntWritable> {
        private static final String UTF8 = "UTF-8";
        private static final byte[] newline;
        static {
            try {
                newline = "\n".getBytes(UTF8);
            } catch (UnsupportedEncodingException uee) {
                throw new IllegalArgumentException("Can't find " + UTF8 + " encoding");
            }
        }
        protected DataOutputStream out;
        public MyRecordWriter(DataOutputStream out) {
            this.out = out;
        }
        @Override
        public void write(Text key, IntWritable value)
                throws IOException, InterruptedException {
            out.write(key.getBytes(), 0, key.getLength());
            out.write(newline);
            out.write(value.toString().getBytes(), 0, value.toString().length());
            out.write(newline);
        }
        @Override
        public void close(TaskAttemptContext context)
                throws IOException, InterruptedException {
            out.close();
        }
    }
}
