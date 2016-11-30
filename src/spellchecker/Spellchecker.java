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
        
        long starttime = System.currentTimeMillis();
        Dictionary d = new Dictionary(new File("src/resources/large.txt"));
        long endtime =  System.currentTimeMillis();
        
        long insert = endtime - starttime;
        
        starttime = System.currentTimeMillis();
        System.out.println(d.checkDictionary("brian's"));
        endtime = System.currentTimeMillis();
        
        long check = endtime - starttime;
        
        System.out.println("Load: " + insert + ", Check: " + check);
        
        System.out.println(d.getErrors());
    }
    
}
