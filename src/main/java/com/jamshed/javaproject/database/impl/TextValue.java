package com.jamshed.javaproject.database.impl;

import com.jamshed.javaproject.database.Value;
import com.jamshed.javaproject.enums.DataType;

public class TextValue implements Value<String> {
    private String value;
    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public DataType getType() {
        return DataType.TEXT;
    }
}
