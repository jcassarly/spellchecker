package spellchecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Jared Cassarly (jwc160) and Stephen Davis
 */
public class Dictionary {
    
    // the root of the trie that hold ths words in the dictionary
    private Node root;
    
    // the number of words in the dictionary
    private int size;
    
    // the number of inserts that caused errors when loading the dictionary to memory
    private int errors;
    
    /**
     * Load in a text file that contains a dictionary - one word on each line
     * @param dictionary the dictionary with one word per line
     * @throws IOException some error occurred in reading the file, most likely it didn't exist, but it may be corrupted too
     */
    public Dictionary(File dictionary) throws IOException {
        root = new Node();
        size = 0;
        errors = 0;
        load(dictionary);
    }
    
    /**
     * Loads a dictionary file into the trie -  the file must contain one word per line and nothing else
     * @param dictionary the dictionary file to load
     * @throws IOException some error occurred in reading the file, most likely it didn't exist, but it may be corrupted too
     */
    private void load(File dictionary) throws IOException {
        // open the file
        BufferedReader in = new BufferedReader(new FileReader(dictionary));
        
        // while file is readable
        while (in.ready()) {
            // get the next word in the file
            String s = in.readLine();
            // as long as the word is not just a new line character
            if (!s.equals("\n")) {
                // insert the word
                boolean noError = this.insert(s);
                // if there was an error in inserting the word
                if (!noError) {
                    // print out the word
                    System.out.println(s);
                    // increment the number of errors found during load
                    errors++;
                }
                // increment the size of the dictionary
                size++;
            }
        }
        
        // close the file
        in.close();
    }
    
    /**
     * Inserts a word into the trie
     * @param newWord a String to insert (should contain only a-z and apostrophes, otherwise an error is thrown
     * @return true is no errors, false otherwise
     */
    public boolean insert(String newWord) {
        Node current = root;
        // run through the word to make sure all characters are valid
        for (int i = 0; i < newWord.length(); i++) {
            // get the index that the character would be in the node's array
            int index = getIndex(newWord.charAt(i));
            
            // if the character indexes to not 0-26, the word is invalid and return false to show an error
            if (index < 0 || index > 26) {
                return false;
            }
        }
        
        // if all the characters are valid, insert the word
        for (int i = 0; i < newWord.length(); i++) {
            int index = getIndex(newWord.charAt(i));

            // if the current character is not the last one
            if (i < newWord.length() - 1) {
                // go to the next node in the trie
                // if the next node is null, create one
                if (current.nexts[index] == null) {
                    current.nexts[index] = new Node();
                }
                // set current to next
                current = current.nexts[index];
            }
            // if the current character is the last idea in the string
            else {
                current.wordExists[index] = true;
            }
        }
        // the word was successfully inserted
        return true;
    }
    
    /**
     * Check if a given word is in the dictionary
     * @param word A string that contains exactly one word and only characters a-z and apostrophes
     * @return true if the word exists in the dictionary, false otherwise
     */
    public boolean checkDictionary(String word) {
        Node current = root;
        String check = word.toLowerCase().trim();
        // parse through the String word
        for (int i = 0; i < check.length(); i++) {
            // get the index of the selected character in the node array
            int index = getIndex(check.charAt(i));
            if (index < 0) {
                return false;
            }
            
            // if the iteration is not at the last character
            if (i < check.length() - 1) {
                // get the next node
                Node next = current.nexts[index];
                // if there is a next node, move to it
                if (next != null) {
                    current = next;
                }
                // otherwise, the word does not exist as its path is longer than where it went in the dictionary
                else return false;
            }
            // if the iteration is on the last character
            else {
                // return true if the value at the node index is true, false otherwise
                return current.wordExists(index);
            }
        }
        // somehow the loop exitted without a return statement in the middle of it and the word does not exist
        return false;
    }
    
    /**
     * Get the index of the character in the node array
     * @param c a character to index
     * @return 0-25 specifies that letter of the alphabet (0=a, 2=c, 25=z, etc), 26 represents the index for an apostrophe, -1 represents an invalid character that does not index
     */
    private int getIndex(char c) {
        // if the character is a-z then return 0-25 based on position in alphabet
        if (c >= 'a' && c <= 'z') {
            return c - 'a';
        }
        // if the character is an appostrophe return 26 (last element in node array)
        else if (c == '\'') {
            return 26;
        }
        // if the character is not a-z nor an apostrophe, return -1 to signify error
        else {
            return -1;
        }
    }
    
    /**
     * Returns the number of words in the dictionary
     * @return the number of words in the dictionary
     */
    public int getSize() { 
        return size;
    }
    
    /**
     * Returns the number of errors encountered when entering words into the dictionary
     * @return the number of errors encountered when entering words into the dictionary
     */
    public int getErrors() {
        return errors;
    }
    
    /**
     * Finds all valid words with one character difference (characters added, subtracted, or changed in the word) from the specified word and returns them in an array
     * Example: "bases" would return values including: "biases" (add a letter), "based" (change a letter), "base" (subtract a letter)
     * @param w The word to find suggestions for
     * @return an arrays of Strings that contains the suggestions for the word.  If the array length is 0, there are no suggestions
     */
    public String[][] findSuggestions(String w) {
        ArrayList<String> suggestions = new ArrayList<>();
        String word = w.toLowerCase();
        // parse through the word - changing one letter in the word
        for (int i = 0; i < word.length(); i++) {
            // go through each possible character difference
            for (int j = 0; j < Node.NUM_VALID_CHARS; j++) {
                // get the character that will change in the word
                Character c = (char) ((j < 26) ? j + 'a' : '\'');
                
                // if the selected character is not the same as the character to change -  avoids getting the same word as a suggestion
                if (c != word.charAt(i)) {
                    // change the character in the word
                    String check = word.substring(0, i) + c.toString() + ((i + 1 < word.length()) ? word.substring(i + 1, word.length()) : "");

                    // if the chenged word is in the dictionary, add it to the list of suggestions
                    if (this.checkDictionary(check)) {
                        suggestions.add(check);
                    }
                }
            }
        }
        
        // parse through the word -  adding one letter to the word
        for (int i = 0; i < word.length(); i++) {
            // if the loop is not on the last charcater
            if (i < word.length() - 1) {
                // check words with one character added between current element and next element
                for (int j = 0; j < Node.NUM_VALID_CHARS; j++) {
                    Character c = (char) ((j < 26) ? j + 'a' : '\'');

                    // add the character to the word
                    String check = word.substring(0, i) + c.toString() + ((i < word.length()) ? word.substring(i, word.length()) : "");

                    // if the new word is in the dictionary, add it to the list of suggestions
                    if (this.checkDictionary(check)) {
                        suggestions.add(check);
                    }
                }
            }
            // if the loop is on the last character
            else {
                // check the words with one character added to the end of the word
                for (int j = 0; j < Node.NUM_VALID_CHARS; j++) {
                    Character c = (char) ((j < 26) ? j + 'a' : '\'');

                    // add the character to the word
                    String check = word + c;

                    // if the new word is in the dictionary, add it to the list of suggestions
                    if (this.checkDictionary(check)) {
                        suggestions.add(check);
                    }
                }
            }
        }
        
        // parse through the word -  removing one letter from the word
        for (int i = 0; i < word.length(); i++) {
            // remove the chracter at the selected index from the word
            String check = word.substring(0, i) +  ((i + 1 < word.length()) ? word.substring(i + 1, word.length()) : "");

            // if the chenged word is in the dictionary, add it to the list of suggestions
            if (this.checkDictionary(check)) {
                suggestions.add(check);
            }
        }
        
        String[][] rtn = new String[suggestions.size()][1];
        for (int i = 0, n = suggestions.size(); i < n; i++) {
            rtn[i][0] = suggestions.get(i);
        }
        
        return rtn;
    }
    
    /**
     * A node that contains an array of boolean values and pointers to other nodes all of length 27
     * The trie data structure
     */
    class Node {
        
        // Length of the wordExists and nexts arrays
        final private static int NUM_VALID_CHARS = 27;

        // boolean values that indicate whether a word in the particular path exists
        private boolean[] wordExists;
        // pointers to more nodes in the trie
        private Node[] nexts;

        /**
         * Creates a new Node in the trie
         */
        public Node() {
            // set all values that indicate whether a word exists to false
            wordExists = new boolean[NUM_VALID_CHARS];
            for (boolean e : wordExists) {
                e = false;
            }

            // create the pointers to the next nodes
            nexts = new Node[NUM_VALID_CHARS];
            // set them all to null to avoid infinite recursion
            for (Node e : nexts) {
                e = null;
            }
        }

        /**
         * Returns the value in the wordExists array at the specified index
         * @param index the index to check the value in the array
         * @return the boolean value at the specified index
         */
        public boolean wordExists(int index) {
            // if the specified index is between 0 and 26
            if (index >= 0 && index < wordExists.length) {
                // return the value at the index
                return wordExists[index];
            }
            // else return false
            else {
                return false;
            }
        }
        
        /**
         * Set the value at a specific index of the wordExists array
         * @param index the index to set the value of
         * @param exists the value to set the index to
         */
        public void setWordExistence(int index, boolean exists) {
            wordExists[index] = exists;
        }
    }
}
