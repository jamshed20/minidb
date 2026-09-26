package com.jamshed.javaproject.database;

import com.jamshed.javaproject.database.impl.BooleanValue;
import com.jamshed.javaproject.database.impl.IntValue;
import com.jamshed.javaproject.database.impl.TextValue;

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

    public void addValue(Integer value) {
        IntValue intValue = new IntValue();
        intValue.setValue(value);
        addValue(intValue);
    }
    public void addValue(Boolean value) {
        BooleanValue booleanValue = new BooleanValue();
        booleanValue.setValue(value);

        addValue(booleanValue);
    }
    public void addValue(String value){
        TextValue textValue = new TextValue();
        textValue.setValue(value);
        addValue(textValue);
    }
}