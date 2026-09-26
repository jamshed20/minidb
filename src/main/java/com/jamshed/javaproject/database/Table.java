package com.jamshed.javaproject.database;



import com.jamshed.javaproject.enums.DataType;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private final String tableName;
    private final Schema schema;
    private final List<Row> rows;

    public Table(String tableName, Schema schema) {
        validate(tableName, schema);

        this.tableName = tableName;
        this.schema = schema;
        this.rows = new ArrayList<>();
    }

    private void validate(String tableName, Schema schema) {
        if(tableName == null || tableName.isBlank())
            throw new IllegalArgumentException("Table name cannot be null or empty");

        if(schema == null)
            throw new IllegalArgumentException("Schema cannot be null");

    }

    public String getTableName() {
        return tableName;
    }
    public Schema getSchema() {
        return schema;
    }
    public List<Row> getRows() {
        return List.copyOf(rows);
    }

    public void insert(Row row) {
        if(row == null){
            throw new IllegalArgumentException("row is null");
        }

        List<Value<?>> rowValues = row.getValues();
        List<Column> columns = schema.getColumns();

        if(rowValues.size() != columns.size()){
            throw new IllegalArgumentException("row values size mismatch");
        }

        for(int i = 0; i < rowValues.size(); i++){
            if(rowValues.get(i) == null){
                throw new IllegalArgumentException("row value " + i + " is null");
            }

            DataType dataType = rowValues.get(i).getType();
            DataType columnType = columns.get(i).getColumnType();

            if (!dataType.equals(columnType)) {
                throw new IllegalArgumentException(
                        "Column " + columns.get(i)
                                + " expects " + columnType
                                + " but received " + dataType
                );
            }
        }

        rows.add(row);
    }
}
