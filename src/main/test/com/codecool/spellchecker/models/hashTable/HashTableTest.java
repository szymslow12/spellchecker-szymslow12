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
        when(stringHasher.hash(any(String.class)))
                .thenReturn(0)
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(4);
    }

    @Test
    void shouldAddString() {
        hashTable.add("string");

        int expected = 1;
        int actual = hashTable.getTotalItemsNumber();

        assertEquals(expected, actual);
    }

    @Test
    void shouldAddMoreStrings() {
        hashTable.add("string0");
        hashTable.add("string1");
        hashTable.add("string2");
        hashTable.add("string3");

        int expected = 4;
        int actual = hashTable.getTotalItemsNumber();

        assertEquals(expected, actual);
    }

    @Test
    void shouldLookupExistWord() {
        hashTable.add("word");

        assertTrue(hashTable.lookup("word"));
    }
}