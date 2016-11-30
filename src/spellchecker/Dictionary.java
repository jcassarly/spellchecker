package spellchecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Jared Cassarly (jwc160)
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
     * @param textFile the dictionary with one word per line
     * @throws IOException some error occurred in reading the file, most likely it didn't exist, but it may be corrupted too
     */
    public Dictionary(File textFile) throws IOException {
        root = new Node();
        size = 0;
        errors = 0;
        load(textFile);
    }
    
    /**
     * Loads a dictionary file into the trie -  the file must contain one word per line and nothing else
     * @param textFile the dictionary file to load
     * @throws IOException some error occurred in reading the file, most likely it didn't exist, but it may be corrupted too
     */
    private void load(File textFile) throws IOException {
        // open the file
        BufferedReader in = new BufferedReader(new FileReader(textFile));
        
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
        boolean charsValid = true;
        for (int i = 0; i < newWord.length(); i++) {
            int index = getIndex(newWord.charAt(i));
            if (index < 0 || index > 26) {
                charsValid = false;
                i = newWord.length();
            }
        }
        // if all the characters are valid, insert the word
        if (charsValid) {
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
            return true;
        }
        // if the characters were not valid, show that an error has occured
        else return false;
    }
    
    public boolean checkDictionary(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            int index = getIndex(word.charAt(i));
            if (i < word.length() - 1) {
                Node next = current.nexts[index];
                if (next != null) {
                    current = next;
                }
                else return false;
            }
            else {
                return current.wordExists(index);
            }
        }
        return false;
    }
    
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
    
    public int getSize() { 
        return size;
    }
    
    public int getErrors() {
        return errors;
    }
    
    class Node {

        private boolean[] wordExists;
        private Node[] nexts;

        public Node() {
            // set all values that indicate whether a word exists to false
            wordExists = new boolean[27];
            for (boolean e : wordExists) {
                e = false;
            }

            // create the pointers to the next nodes
            nexts = new Node[27];
            // set them all to null to avoid infinite recursion
            for (Node e : nexts) {
                e = null;
            }
        }

        public boolean wordExists(int index) {
            if (index >= 0 && index < wordExists.length) {
                return wordExists[index];
            }
            else {
                return false;
            }
        }
        
        public void setWordExistence(int index, boolean exists) {
            wordExists[index] = exists;
        }
    }
}
