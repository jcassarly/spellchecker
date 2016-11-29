package spellchecker;

/**
 *
 * @author Jared Cassarly (jwc160)
 */
public class Node {
    
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
}
