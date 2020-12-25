package ru.idcore.service;

public enum Delimiter {
    DELIMITER_COMMA(","),
    DELIMITER_COLON(":"),
    DELIMITER_SEMICOLON(";"),
    DELIMITER_PIPE("|");

    public String delimiter;

    Delimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }

    @Override
    public String toString() {
        return "Delimiter{" +
                "delimiter='" + delimiter + '\'' +
                '}';
    }
}
