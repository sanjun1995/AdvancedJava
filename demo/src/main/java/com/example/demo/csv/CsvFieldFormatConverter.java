package com.example.demo.csv;

import org.apache.commons.csv.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CsvFieldFormatConverter {

    public static void convertCsvFile(File inputCsvFile, File outputCsvFile) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.withQuote(null);
        try (Reader reader = new FileReader(inputCsvFile, StandardCharsets.UTF_8);
             Writer writer = new FileWriter(outputCsvFile, StandardCharsets.UTF_8);
             CSVParser csvParser = new CSVParser(reader, csvFormat);
             CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {

            for (CSVRecord record : csvParser) {
                for (String value : record) {
                    String convertedValue = convertCsvFieldFormat(value);
                    csvPrinter.print(convertedValue);
                }
                csvPrinter.println();
            }
        }
    }

    public static String convertCsvFieldFormat(String csvField) {
        if (csvField.startsWith("=\"") && csvField.endsWith("\"")) {
            return csvField.substring(2, csvField.length() - 1);
        }
        return csvField;
    }

    public static void main(String[] args) {
        File inputCsvFile = new File("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/3cd888e0-169f-4ae9-8093-2bd2e891c2ad.csv");
        File outputCsvFile = new File("/Users/caozhixin/IdeaProjects/AdvancedJava/demo/src/main/resources/output.csv");
        try {
            convertCsvFile(inputCsvFile, outputCsvFile);
            System.out.println("CSV file conversion completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

