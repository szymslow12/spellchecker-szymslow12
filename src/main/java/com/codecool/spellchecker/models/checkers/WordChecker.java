package com.codecool.spellchecker.models.checkers;

import com.codecool.spellchecker.models.WordList;
import java.util.ArrayList;

/**
 *
 * ICS 23 Summer 2004
 * Project #5: Lost for Words
 *
 * Implement your word checker here.  A word checker has two responsibilities:
 * given a word list, answer the questions "Is the word 'x' in the wordlist?"
 * and "What are some suggestions for the misspelled word 'x'?"
 *
 * WordChecker uses a class called WordList that I haven't provided the source
 * code for.  WordList has only one method that you'll ever need to call:
 *
 *     public boolean lookup(String word)
 *
 * which returns true if the given word is in the WordList and false if not.
 */

public class WordChecker
{
	/**
   * Constructor that initializes a new WordChecker with a given WordList.
   *
   * @param wordList Initial word list to check against.
   * @see WordList
   */
	private String alphabet ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private WordList wordList;

	public WordChecker(WordList wordList)
	{
        this.wordList = wordList;
	}
	

	/**
   * Returns true if the given word is in the WordList passed to the
   * constructor, false otherwise. 
   *
   * @param word Word to chack against the internal word list
   * @return bollean indicating if the word was found or not.
   */
	public boolean wordExists(String word)
	{
	    return wordList.lookup(word);
	}


	/**
   * Returns an ArrayList of Strings containing the suggestions for the
   * given word.  If there are no suggestions for the given word, an empty
   * ArrayList of Strings (not null!) should be returned.
   *
   * @param word String to check against
   * @return A list of plausible matches
   */
	public ArrayList getSuggestions(String word)
	{
        ArrayList suggestions = new ArrayList();
        suggestions.addAll(charAppended(word));
        suggestions.addAll(charMissing(word));
        suggestions.addAll(charsSwapped(word));
        suggestions.addAll(charInserted(word));
//        System.out.println(suggestions);
	    return suggestions;
	}


	private ArrayList charAppended(String word) {
        ArrayList toReturn = new ArrayList();
        for (char character: alphabet.toCharArray()) {
            String atFront = character + word;
            String atBack = word + character;
            if (wordExists(atFront)) {
                System.out.println("Added suggesstion!");
                toReturn.add(atFront);
            }
            if (wordExists(atBack)) {
                System.out.println("Added suggesstion!");
                toReturn.add(atBack);
            }
        }
        return toReturn;
    }

    private ArrayList charInserted(String word) {
	    ArrayList toReturn = new ArrayList();

	    for (int i = 0; i < word.length(); i++) {
	        for (char character: alphabet.toCharArray()) {
                String inserted = word.substring(0, i) + character + word.substring(i);
                if (wordExists(inserted)) {
                    toReturn.add(inserted);
                }
            }
        }
        return toReturn;
    }


    private ArrayList charReplace(String word) {

    }


    private ArrayList charMissing(String word) {
	    ArrayList toReturn = new ArrayList();
	    int wordLen = word.length() - 1;

	    if (wordExists(word.substring(1))) {
            System.out.println("Added suggesstion!");
	        toReturn.add(word.substring(1));
        }
        for (int i = 1; i < wordLen; i++) {
            String newWord = word.substring(0, i);
            newWord = newWord.concat(word.substring(i + 1));
            if (wordExists(newWord)) {
                System.out.println("Added suggesstion!");
                toReturn.add(newWord);
            }
        }
        if (wordExists(word.substring(0, wordLen))) {
            System.out.println("Added suggesstion!");
            toReturn.add(word.substring(0, wordLen));
        }
        return toReturn;
    }


    private ArrayList charsSwapped(String input) {
        ArrayList toReturn = new ArrayList();

        for (int i = 0; i < input.length() - 1; i++) {
            String working = input.substring(0, i);// System.out.println("    0:" + working);
            working = working + input.charAt(i + 1);  //System.out.println("    1:" + working);
            working = working + input.charAt(i); //System.out.println("    2:" + working);
            working = working.concat(input.substring((i + 2)));//System.out.println("    FIN:" + working);
            if (wordExists(working)) {
                System.out.println("Added suggesstion!");
                toReturn.add(working);
            }
        }
        return toReturn;
    }
}
