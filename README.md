# Spell Checker

Jared Cassarly and Stephen Davis

The project is a spell checker.  The program can accept either a path to a text file or a string.  If a text file is chosen, it is read into a string.  The program then proceeeds to parse through the string picking out words and checking them against a dictionary that is read in when the program starts (the distionary will be represented in a trie data structure).  If the word does not exist in the dictionary, then the program prints the mispelled word and continues printing all mispelled words to the console.  After the string has been completely parsed, the program prints the total number of mispelled words and then exits.

Thye project uses the tree data structure.  The spell checker uses a spceialized type of tree called a trie where each node is an array of length 26 (one for each letter of the alphabet).  Each element in the array points to another node and has a boolean data value that says whether the string of characters from the point and up the tree represent a word.

The only specialized code we will use will be the java library with classes like the Scanner class. We will create an implement our own trie data structure.  We will use a dictionary with one word per line found on the internet.  We might also find text files of books online to spell check against the dictionary.


#To-Do

Add support for possesives not in the dictionary

Add support for reading a file in to spell check (use BufferedReader)

Add support for reading a string to spell check
