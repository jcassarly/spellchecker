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
    
    private Node root;
    private int size;
    private int errors;
    
    /**
     * Load in a text file that contains a dictionary - one word on each line
     * @param textFile the dictionary with one word per line
     * @throws java.io.FileNotFoundException yup this happens if you give bad input - fight me lol // i should probably take this line out
     */
    public Dictionary(File textFile) throws IOException {
        root = new Node();
        size = 0;
        errors = 0;
        load(textFile);
    }
    
    private void load(File textFile) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(textFile));
        
        while (in.ready()) {
            String s = in.readLine();
            if (!s.equals("\n")) {
                boolean noError = this.insert(s);
                if (!noError) {
                    System.out.println(s);
                    errors++;
                }
                size++;
            }
        }
        
        in.close();
    }
    
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
