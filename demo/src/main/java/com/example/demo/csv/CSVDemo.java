package com.example.demo.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class CSVDemo {
    public static void main(String[] args) {
        String csvFilePath = "path/to/csv/file.csv";

        try (Reader reader = new FileReader(csvFilePath);
             Writer writer = new FileWriter(csvFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);

            for (CSVRecord record : records) {
                // 获取需要修改的字段值
                String fieldValue = record.get("FieldName");

                // 修改字段值
                String modifiedValue = modifyValue(fieldValue);

                // 创建新的CSVRecord对象并写入CSV文件
//                CSVRecord modifiedRecord = CSVRecord.of(record.getRecordNumber(), record.getComment(), modifiedValue);
//                csvPrinter.printRecord(modifiedRecord);
            }

            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 示例方法：修改字段值的逻辑
    private static String modifyValue(String value) {
        // 根据实际需求修改字段值
        // 返回修改后的值
        return value;
    }
}

