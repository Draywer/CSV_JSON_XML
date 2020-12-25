package ru.idcore.service;

public enum Delimiter {
    DELIMITER_COMMA(','),
    DELIMITER_COLON(':'),
    DELIMITER_SEMICOLON(';'),
    DELIMITER_PIPE('|');

    public Character delimiter;

    Delimiter(Character delimiter) {
        this.delimiter = delimiter;
    }

    public Character getDelimiter() {
        return delimiter;
    }

    @Override
    public String toString() {
        return "Delimiter{" +
                "delimiter='" + delimiter + '\'' +
                '}';
    }
}
