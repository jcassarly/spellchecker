package spellchecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Jared Cassarly (jwc160)
 */
public class Dictionary {
    
    private Node root;
    
    /**
     * Load in a text file that contains a dictionary - one word on each line
     * @param textFile the dictionary with one word per line
     * @throws java.io.FileNotFoundException yup this happens if you give bad input - fight me lol // i should probably take this line out
     */
    public Dictionary(String textFile) throws FileNotFoundException {
        root = new Node();
        load(textFile);
    }
    
    private void load(String textFile) throws FileNotFoundException {
        Scanner in = new Scanner(textFile);
        
        while (in.hasNextLine()) {
            String s = in.nextLine();
            if (!s.equals("\n")) {
                this.insert(s);
            }
        }
        
        in.close();
    }
    
    public void insert(String newWord) {
        Node current = root;
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
        return c - 'a';
    }
    
    class Node {

        private boolean[] wordExists;
        private Node[] nexts;

        public Node() {
            // set all values that indicate whether a word exists to false
            wordExists = new boolean[26];
            for (boolean e : wordExists) {
                e = false;
            }

            // create the pointers to the next nodes
            nexts = new Node[26];
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
