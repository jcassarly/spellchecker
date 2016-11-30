package spellchecker;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Jared Cassarly (jwc160)
 */
public class Spellchecker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //String dict = "a\nabs\ncandy";
        Dictionary d = new Dictionary(new File("src/resources/dictionary84000.txt"));
        System.out.println(d.checkDictionary("gerrymander"));
        System.out.println(d.getErrors());
    }
    
}
