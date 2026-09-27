package com.jamshed.javaproject.database;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Database {

    private final Map<String, Table> database;

    public Database() {
        database = new HashMap<>();
    }

    public void createTable(Table table) {
        if (table == null) {
            throw new IllegalArgumentException("Table cannot be null");
        }

        String tableName = normalizeTableName(table.getTableName());

        if (database.containsKey(tableName)) {
            throw new IllegalArgumentException(
                    "Table already exists: " + tableName
            );
        }

        database.put(tableName, table);
    }

    public void dropTable(String tableName) {
        tableName = normalizeTableName(tableName);

        if (!database.containsKey(tableName)) {
            throw new IllegalArgumentException(
                    "Table does not exist: " + tableName
            );
        }

        database.remove(tableName);
    }

    public Table getTable(String tableName) {
        tableName = normalizeTableName(tableName);

        if (!database.containsKey(tableName)) {
            throw new IllegalArgumentException(
                    "Table does not exist: " + tableName
            );
        }

        Table table = database.get(tableName);

        if (table == null) {
            throw new IllegalArgumentException(
                    "Table does not exist: " + tableName
            );
        }



        return table;
    }

    private String normalizeTableName(String tableName) {
        if (tableName == null || tableName.isBlank()) {
            throw new IllegalArgumentException(
                    "Table name cannot be null or blank"
            );
        }

        return tableName.toUpperCase(Locale.ROOT);
    }


}