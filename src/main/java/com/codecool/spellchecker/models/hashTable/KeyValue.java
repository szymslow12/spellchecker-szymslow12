package com.codecool.spellchecker.models.hashTable;

public class KeyValue {
    private String value;

    public KeyValue(String value) {
        this.value = value;
    }

    String getValue() {
        return value;
    }


    void setValue(String value) {
        this.value = value;
    }
}
