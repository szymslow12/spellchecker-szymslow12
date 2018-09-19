package com.codecool.spellchecker.models.hashTable;

import com.codecool.spellchecker.models.hashers.StringHasher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class HashTableTest {

    private HashTable hashTable;

    @Mock
    private StringHasher stringHasher;

    @BeforeEach
    public void setup() {
        hashTable = new HashTable(4, stringHasher);
        hashTable.add("HashTable");
        hashTable.add(" ");
        hashTable.add("test");
        hashTable.add("!");
        when(stringHasher.hash(any(String.class))).thenReturn(0);
    }

    @Test
    void shouldAdd() {
        hashTable.lookup()
    }
}