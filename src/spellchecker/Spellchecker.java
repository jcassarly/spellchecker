package spellchecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Jared Cassarly (jwc160)
 */
public class Spellchecker {

   /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        
        Scanner S = new Scanner(System.in);
        String in = "";
        
        //System.out.println("Enter Dictionary File Path: ");
        Dictionary d = new Dictionary(new File("src/resources/large.txt"));//S.nextLine())); // Please use the file path for the large.txt file
        
        //chooses to use the readInput method with a String or a File parameter based on user input in the setup funtion
        if(setup())
        {
            //gets the file path from the user and sets up the file that is inputed to readInput
            System.out.println("Enter file path:");
            in = S.next();
            File input = new File(in);
            readInput(input,d);
        }
        else
        {
            //gets the string from the user which i s checked in readInput
            System.out.println("Enter String:");
            in = S.nextLine();
            
            readInput(in,d);
        }
    }
    
    /**
     * Asks the user whether they would like to input a file or a string for use in the main method
     * and the user inputs his answer into the console
     * @return returns true if the user requests a file and false if he requests a string
     */
    public static boolean setup()
    {
        boolean ret = false;
        boolean valid = false;
        String input = "";
        Scanner S = new Scanner(System.in);
        //keep recieving input from the user until they input a valid string
        while(!valid)
        {
            System.out.println("Would you like to input a File or a String? (If the String has more than one line of text, use a file please)");
            input = S.next();
            //checks whether the input is a valid one
            if((input.equals("File")||input.equals("file")||input.equals("String")||input.equals("string")))
            {
                //checks if the valid input is file or string
                if(input.equals("File")||input.equals("file"))
                    ret=true;
                valid = true;
            }
            //tells the user if the input is invalid
            else
                System.out.println("Invalid input try again");
        }
        return ret;
    }
    
    /**
     * Reads through the file and checks each word against the dictionary
     * @param input - File containing the document to be checked
     * @param d - Dictionary to check the input against
     * @throws IOException file was not found or was corrupted
     */
    public static void readInput(File input,Dictionary d) throws IOException
    {
        // create a new reader for the file
        BufferedReader in = new BufferedReader(new FileReader(input));
        
        // initialize the process to scan parse through the file
        String currentWord = "";
        int misspelled=0;
        System.out.println("Mispelled words:");
        
        // while there are characters left in the file
        while(in.ready())
        {            
            // read the next character in the file
            char c=(char)in.read();        
            // if the character denotes the end of a word
            if((c==' '||c=='.'||c==','||c=='\n'||c=='\t'||c=='\r'||c=='"'||c==';'))
            {
                // check if the current word that has been found is mispelled
                if (isMisspelled(currentWord, d)) {
                    // print out the current word
                    System.out.println(currentWord);
                    
                    // increment the number of mispellings
                    misspelled++;
                }
                
                // reset the current word
                currentWord="";
            }
            // if the character is a valid charcater for a word (a-z or apostrophe) add the character to the current word
            else if(Character.isAlphabetic(c)||c=='\'')
                currentWord+=c;
        }
        
        // check the last word in the dictionary
        if (isMisspelled(currentWord, d)) {
            System.out.println(currentWord);
            misspelled++;
        }

        // print out the number of mispellings
        System.out.println("Number of mispelled words: "+misspelled);
    }
    
    /**
     * Reads through the file and checks each word against the dictionary
     * @param input - File containing the document to be checked
     * @param d - Dictionary to check the input against
     */
    public static void readInput(String input, Dictionary d) 
    {
        // gets ready to read the string
        String currentWord = "";
        int misspelled=0;
        System.out.println("Mispelled words:");
        
        // parse through the string character by character
        for(int i=0 ;i<input.length(); i++)
        {            
            // gets the character at the current index
            char c=input.charAt(i);           
            
            // if the character denotes the end of a word
            if((c==' '||c=='.'||c==','||c=='\n'||c=='\t'||c=='\r'||c=='"'||c==';'))
            {
                // check if the current word is misspelled
                if (isMisspelled(currentWord, d)) {
                    // print out the misspelled word
                    System.out.println(currentWord);
                    
                    // increment the number of misspellings in the string
                    misspelled++;
                }
                
                // reset the current word
                currentWord="";
            }
            // if the character is a valid charcater for a word (a-z or apostrophe) add the character to the current word
            else if(Character.isAlphabetic(c)||c=='\'')
                currentWord+=c;
        }
        
        // check the last word in the dictionary
        if (isMisspelled(currentWord, d)) {
            System.out.println(currentWord);
            misspelled++;
        }

        // print out the number of mispellings
        System.out.println("Number of mispelled words: "+misspelled);
    }
    
    /**
     * Check if a word is miseplled in the dictionary
     * @param currentWord the word to check if it is in the dictionary (should contain characters a-z and or apostrophes
     * @param d the dictionary to check if the word is in
     * @return true if the word is not in the dictionary, false if the word is in the dictionary or it is an empty string
     */
    public static boolean isMisspelled(String currentWord, Dictionary d) {
        // if the word is not an empty string
        if(!currentWord.equals(""))
        {
            // if the current word in a singular possessive
            if(currentWord.length()>2 && currentWord.substring(currentWord.length()-2).equals("'s"))
            {
                // check the word without the possessive "'s" -  there is no word in the dictionary that wihtout the "'s" it is not word but still a word with it
                currentWord=currentWord.substring(0,currentWord.length()-2);
            }
            // if the current word is a plural possesive
            else if(currentWord.length()>2 && currentWord.substring(currentWord.length()-2).equals("s'"))
            {
                // check the word without the possessive "s'" -  there is no word in the dictionary that wihtout the "s'" it is not word but still a word with it
                currentWord=currentWord.substring(0,currentWord.length()-1);
            }
            
            // return true if the non-possessive form of the word is in the dictionary, false otherwise
            // also, there is no need to check if the possessive form of the word is in the dictionary because all words with an "'s" or "s'" are words without those characters on the end
            return (!d.checkDictionary(currentWord)); 
        }
        // if the word is an empty string it is not mispelled
        else return false;
    }
    
}
