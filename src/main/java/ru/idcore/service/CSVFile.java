package ru.idcore.service;


import com.opencsv.*;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Gnatenko
 */
public abstract class CSVFile<T> {
    public abstract Class<T> getType();

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

    public List<T> getObjFromCSVFileWithStrategy(String csvFile, Character csvParser, String[] objColumns) {
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

    public boolean writeObjToCSVFileWithStrategy(String csvFile, Character csvParser, List<T> tList, String[] csvColumns) throws IOException {
        boolean result = false;

        try (CSVWriter csvWriter = new CSVWriter(
                new FileWriter(csvFile),
                csvParser, CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)) {

            ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(getType());
            strategy.setColumnMapping(csvColumns);

            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(csvWriter)
                    .withMappingStrategy(strategy)
                    .build();
            beanToCsv.write(tList);
            result = true;

        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
        return result;
    }

}
