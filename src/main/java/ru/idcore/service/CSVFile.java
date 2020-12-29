package ru.idcore.service;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * @author Alexander Gnatenko
 */
public abstract class CSVFile<T> {
    public abstract Class<T> getTType();

    public void about() {
        System.out.println("Библиотека для работы с CSV-файлами");
    }

    public List<T> getCSVObj(String fileName, Character separator) throws FileNotFoundException {
          return new CsvToBeanBuilder<T>(new FileReader(fileName))
                .withType(getTType())
                .withSeparator(separator)
                  .withIgnoreLeadingWhiteSpace(true)
                .build()
                .parse();

    }

}
