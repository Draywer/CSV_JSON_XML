package ru.idcore.service;


import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Gnatenko
 */
public abstract class CSVFile<T> {
    abstract Class<T> getType();

    public void about() {
        System.out.println("Библиотека для работы с CSV-файлами");
    }

    public List<T> getCSVObj(String fileName, Character separator) throws FileNotFoundException {
          return new CsvToBeanBuilder<T>(new FileReader(fileName))
                .withType(getType())
                .withSeparator(separator)
                .build()
                .parse();

    }

    public List<T> getObjFromCSVFileWithHeader(String csvFile, Character csvParser) {
        List<T> tList = new ArrayList<>();
        CSVParser parser = new CSVParserBuilder().withSeparator(csvParser).build();

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFile)).withCSVParser(parser).build()) {
            HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(getType());
            tList = new CsvToBeanBuilder<T>(csvReader)
                    .withMappingStrategy(strategy)
                    .build()
                    .parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tList;
    }

    public List<T> getObjFromCSVFile(String csvFile, Character csvParser, String[] objColumns) {
        List<T> tList = new ArrayList<>();
        CSVParser parser = new CSVParserBuilder().withSeparator(csvParser).build();

        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFile)).withCSVParser(parser).build()) {
            ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(getType());
            strategy.setColumnMapping(objColumns);
            tList = new CsvToBeanBuilder<T>(csvReader)
                    .withMappingStrategy(strategy)
                    .build()
                    .parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tList;
    }

}
