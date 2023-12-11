//package com.example.demo.csv;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.*;
//import java.nio.charset.Charset;
//
//public class CsvToXlsxConverter {
//
//    public static void convertCsvToXlsx(String csvFile, File xlsxFile) throws IOException {
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Sheet1");
//        BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile, Charset.defaultCharset()));
//        String line;
//        int rowNumber = 0;
//        while ((line = bufferedReader.readLine()) != null) {
//            String[] data = line.split(",");
//            Row row = sheet.createRow(rowNumber++);
//            for (int i = 0; i < data.length; i++) {
//                Cell cell = row.createCell(i);
//                cell.setCellValue(data[i]);
//            }
//        }
//        bufferedReader.close();
//
//        FileOutputStream fileOutputStream = new FileOutputStream(xlsxFile);
//        workbook.write(fileOutputStream);
//        workbook.close();
//        fileOutputStream.close();
//    }
//
//    public static void main(String[] args) {
//        File xlsxFile = new File("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/output.xlsx");
//        try {
//            convertCsvToXlsx("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/3cd888e0-169f-4ae9-8093-2bd2e891c2ad.csv", xlsxFile);
//            System.out.println("CSV to XLSX conversion completed.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
