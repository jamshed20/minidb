package com.jamshed.javaproject.database.impl;


import com.jamshed.javaproject.database.Value;
import com.jamshed.javaproject.enums.DataType;

public class BooleanValue implements Value<Boolean> {
    private Boolean value;
    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public DataType getType() {
        return DataType.BOOLEAN;
    }
}
