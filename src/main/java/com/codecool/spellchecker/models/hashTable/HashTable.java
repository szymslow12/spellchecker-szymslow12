package com.codecool.spellchecker.models.hashTable;


import com.codecool.spellchecker.models.hashers.StringHasher;

import java.util.LinkedList;

/**
 *
 * ICS 23 Summer 2004
 * Project #5: Lost for Words
 *
 * Implement your hash table here.  You are required to use the separate
 * chaining strategy that we discussed in lecture, meaning that collisions
 * are resolved by having each cell in the table be a linked list of all of
 * the strings that hashed to that cell.
 */

public class HashTable
{
	/**
   * The constructor is given a table size (i.e. how big to make the array)
   * and a StringHasher, which is used to hash the strings.
   *
   * @param tableSize number of elements in the hash array
   *        hasher    Object that creates the hash code for a string
   * @see StringHasher
   */
	private LinkedList<KeyValue>[] table;
	private StringHasher hasher;

	public HashTable(int tableSize, StringHasher hasher)
	{
        table = new LinkedList[tableSize];
        this.hasher = hasher;
	}


	/**
   * Takes a string and adds it to the hash table, if it's not already
   * in the hash table.  If it is, this method has no effect.
   *
   * @param s String to add
   */
	public void add(String s)
	{
	    if (s == null) {
	        return;
        }
        int hashCode = Math.abs(hasher.hash(s) % table.length);
        KeyValue keyValue = new KeyValue(s);
        LinkedList<KeyValue> tableValues = table[hashCode];
        if (tableValues == null) {
            tableValues = new LinkedList<>();
            table[hashCode] = tableValues;
        }
        tableValues.add(keyValue);
	}
	

	/**
  * Takes a string and returns true if that string appears in the
	* hash table, false otherwise.
  *
  * @param s String to look up
  */
	public boolean lookup(String s)
	{
	    int hashCode = Math.abs(hasher.hash(s) % table.length);
	    LinkedList<KeyValue> tableValues = table[hashCode];
	    if (tableValues == null) {
	        return false;
        }
		return containValue(s, tableValues);
	}


	private boolean containValue(String key, LinkedList<KeyValue> tableValues) {
	    for (KeyValue keyValue: tableValues) {
	        if (key == keyValue.getValue()) {
                return true;
            }
        }
        return false;
    }

	/**
   * Takes a string and removes it from the hash table, if it
   * appears in the hash table.  If it doesn't, this method has no effect.
   *
   * @param s String to remove
  */
	public void remove(String s)
	{
        int hashCode = Math.abs(hasher.hash(s) % table.length);
        LinkedList<KeyValue> tableValues = table[hashCode];
        if (tableValues == null) {
            return;
        }
        for (KeyValue keyValue: tableValues) {
            if (keyValue.getValue() == s) {
                tableValues.remove(keyValue);
            }
        }
	}
}
