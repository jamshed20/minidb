package com.jamshed.javaproject.database;


import com.jamshed.javaproject.enums.DataType;

public interface Value<T> {
    T getValue();
    void setValue(T value);
    DataType getType();
}