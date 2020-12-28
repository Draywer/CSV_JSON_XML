package ru.idcore.service;

public enum Separator {
    SEPARATOR_COMMA(','),
    SEPARATOR_COLON(':'),
    SEPARATOR_SEMICOLON(';'),
    SEPARATOR_PIPE('|');

    public Character separator;

    Separator(Character separator) {
        this.separator = separator;
    }

    public Character getSeparator() {
        return separator;
    }

    @Override
    public String toString() {
        return "Separator{" +
                "separator='" + separator + '\'' +
                '}';
    }
}
