package com.jamshed.javaproject.database;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Schema {
    private final List<Column> columns;

    public Schema(List<Column> columns) {
        validateColumns(columns);
        this.columns = List.copyOf(columns);
    }

    private void validateColumns(List<Column> columns) {
        if (columns == null) {
            throw new IllegalArgumentException("Columns cannot be null");
        }

        Set<String> columnNames = new HashSet<>();

        for (Column column : columns) {
            if (column == null) {
                throw new IllegalArgumentException("Column cannot be null");
            }

            String columnName = column.getColumnName();

            if (columnName == null || columnName.isBlank()) {
                throw new IllegalArgumentException("Column name cannot be null or blank");
            }

            if (!columnNames.add(columnName)) {
                throw new IllegalArgumentException(
                        "Duplicate column name: " + columnName
                );
            }
        }
    }

    public List<Column> getColumns() {
        return columns;
    }
}