package ru.idcore.service;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Alexander Gnatenko
 */
public class CSVFile {
    public static void about() {
        System.out.println("Библиотека для работы с CSV-файлами");
    }

    public static void createCSVFile(String str, String pathCSVFile, Delimiter delimiter, boolean b) throws IOException {
        boolean result = false;
        String[] csvStrings = str.split(delimiter.getDelimiter());
        try(CSVWriter csvWriter = new CSVWriter(new FileWriter(pathCSVFile, b))) {
            csvWriter.writeNext(csvStrings);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
