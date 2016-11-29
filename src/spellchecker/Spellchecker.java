package spellchecker;

import java.io.FileNotFoundException;

/**
 *
 * @author Jared Cassarly (jwc160)
 */
public class Spellchecker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        String dict = "a\nabs\ncandy";
        Dictionary d = new Dictionary(dict);
        System.out.println(d.checkDictionary("burrito"));
    }
    
}
