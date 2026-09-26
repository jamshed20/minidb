package com.jamshed.javaproject.database;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private final List<Value<?>> values;

    public Row() {
        this.values = new ArrayList<>();
    }

    public List<Value<?>> getValues() {
        return List.copyOf(values);
    }

    public void addValue(Value<?> value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        values.add(value);
    }
}