package com.jamshed.javaproject.database.impl;

import com.jamshed.javaproject.database.Value;
import com.jamshed.javaproject.enums.DataType;

public class IntValue implements Value<Integer> {
    private Integer value;
    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public DataType getType() {
        return DataType.INT;
    }
}
