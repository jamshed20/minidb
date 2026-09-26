package com.jamshed.javaproject.database;


import com.jamshed.javaproject.enums.DataType;

import java.util.Locale;

public class Column {
    private final String columnName;
    private final DataType columnType;

    public Column(String columnName, DataType columnType) {
        if (columnName == null || columnName.isBlank()) {
            throw new IllegalArgumentException("Column name cannot be null or blank");
        }
        if (columnType == null) {
            throw  new IllegalArgumentException("columnType is null");
        }
        this.columnName = columnName.toUpperCase(Locale.ROOT);
        this.columnType = columnType;
    }

    public String getColumnName() {
        return columnName;
    }

    public DataType getColumnType() {
        return columnType;
    }
}
