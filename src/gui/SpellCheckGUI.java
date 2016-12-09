package gui;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import spellchecker.Spellchecker;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import spellchecker.Dictionary;

/**
 *
 * @author Jared Cassarly (jwc160)
 */
public class SpellCheckGUI extends javax.swing.JFrame {
    
    final static public String BASE_TITLE = "Spell Checker";
    
    final static private String LABEL_TEXT = "Suggestions for: ";
    
    final private Dictionary dict;
    
    private int start;
    private int end;

    /**
     * Creates the GUI for the spell checker program
     * @throws IOException the dictionary file was not found or was corrupted
     */
    public SpellCheckGUI() throws IOException {
        dict = new Dictionary(new File("src/resources/large.txt"));
        start = -1;
        end = -1;
        initComponents();
        this.setTitle(BASE_TITLE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        enterString = new javax.swing.JTextArea();
        fileCheck = new javax.swing.JButton();
        stringCheck = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        textAreaLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        suggestionsTable = new javax.swing.JTable();
        suggestLabel = new javax.swing.JLabel();
        fixIt = new javax.swing.JButton();
        next = new javax.swing.JButton();
        prev = new javax.swing.JButton();
        note = new javax.swing.JLabel();

        jButton2.setText("jButton2");

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        title.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Spell Checker");

        enterString.setColumns(20);
        enterString.setRows(5);
        jScrollPane1.setViewportView(enterString);

        fileCheck.setText("Open a File to Spell Check");
        fileCheck.setToolTipText("Choose a text file to spell check");
        fileCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileCheckActionPerformed(evt);
            }
        });

        stringCheck.setText("Spell Check!");
        stringCheck.setToolTipText("Spell check the text above");
        stringCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stringCheckActionPerformed(evt);
            }
        });

        textAreaLabel.setText("Enter some text to spell check (please no '<' or '>' symbols):");

        suggestionsTable.setModel(getSuggestions());
        jScrollPane2.setViewportView(suggestionsTable);

        suggestLabel.setText(LABEL_TEXT);

        fixIt.setText("Use Suggestion");
        fixIt.setToolTipText("Uses the selected suggestion and changes the misspelled word to the selected suggestion");
        fixIt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fixItActionPerformed(evt);
            }
        });

        next.setText("Next Suggestion");
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        prev.setText("Previous Suggestion");
        prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevActionPerformed(evt);
            }
        });

        note.setText("Misspelled word highlighted in yellow surrounded by '<' and '>' symbols");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(fileCheck))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(textAreaLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(next)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(prev))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(suggestLabel)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                    .addComponent(fixIt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(stringCheck)
                                .addGap(18, 18, 18)
                                .addComponent(note)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fileCheck)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textAreaLabel)
                            .addComponent(suggestLabel)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(next)
                        .addComponent(prev)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fixIt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stringCheck)
                    .addComponent(note))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stringCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stringCheckActionPerformed
        // read the input in the text area and spell check it
        String spellChecked = Spellchecker.readInput(enterString.getText(), dict);
        // set the text in the text area to the spell checked text
        enterString.setText(spellChecked);
        // go to the next suggestion, or the first in this case
        next.doClick();
    }//GEN-LAST:event_stringCheckActionPerformed

    private void fileCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileCheckActionPerformed
        // create the file chooser
        JFileChooser jfc = new JFileChooser();
        
        // open the file choose and get the value returned by it when it closes
        int actionVal = jfc.showOpenDialog(this);
        // if the value returned by the closed file chooser shows that the open file button was clicked
        if (actionVal == JFileChooser.APPROVE_OPTION) {
            try {
                // read the input in the text area and spell check it
                String text = Spellchecker.readInput(jfc.getSelectedFile(), dict);
                // set the text in the text area to the spell checked text
                enterString.setText(text);
                // go to the next suggestion, or the first in this case
                next.doClick();
            } catch (IOException ex) {
                errorMessage("Chosen file was not found or was corrupted");
            }
        }
    }//GEN-LAST:event_fileCheckActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        boolean madeChange = false;
        
        // parse through the text area string from the end of the last misspelled word (or beginning of there was no last word)
        for (int i = end + 1; i < enterString.getText().length(); i++) {
            // if the current character is a '<' character
            if (enterString.getText().charAt(i) == '<') {
                // start the selection
                enterString.setSelectionStart(i+1);
                start = i+1;
            }
            // if the current character is a '>' character
            else if (enterString.getText().charAt(i) == '>') {
                // end the selection
                enterString.setSelectionEnd(i);
                end = i;
                
                // load the suggestions for the selected word
                suggestionsTable.setModel(getSuggestions(dict.findSuggestions(enterString.getSelectedText())));
                // tell the user what word is being look at
                suggestLabel.setText(LABEL_TEXT + enterString.getSelectedText());
                
                // a change to the text was made
                madeChange = true;
                // exit the loop
                i = enterString.getText().length();
            }
        }
        // if no change to the text was made
        if (!madeChange) {
            // show nothing in suggestions and go back to the beginning for when the next suggestion is asked for
            resetSuggestions();
        }
        
        try {
            // highlight the mispelled words
            highlightMisspelled();
        } catch (BadLocationException ex) {
            errorMessage("Error occured while highlighting misspelled words.  \nMay have been the user added text while program was spell checking");
        }
    }//GEN-LAST:event_nextActionPerformed

    private void prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevActionPerformed
        boolean madeChange = false;
        
        // parse through the text area string from the start of the last (or next on becuase we are going backwards in the string) misspelled word
        // do nothing if no words are before this
        for (int i = start - 2; i >= 0; i--) {
            // if the current character is a '>' character
            if (enterString.getText().charAt(i) == '>') {
                // find the end selection index
                end = i;
            }
            // if the current character is a '<' character
            else if (enterString.getText().charAt(i) == '<') {
                // make the selection
                enterString.setSelectionStart(i+1);
                enterString.setSelectionEnd(end);
                start = i+1;
                
                // load the suggestions for the selected word
                suggestionsTable.setModel(getSuggestions(dict.findSuggestions(enterString.getSelectedText())));
                // tell the user what word is being look at
                suggestLabel.setText(LABEL_TEXT + enterString.getSelectedText());
                
                // a change to the text was made
                madeChange = true;
                // exit the loop
                i = -1;
            }
        }
        // if no change to the text was made
        if (!madeChange) {
            // show nothing in suggestions and go back to the beginning for when the next suggestion is asked for
            resetSuggestions();
        }
        
        try {
            // highlight the mispelled words
            highlightMisspelled();
        } catch (BadLocationException ex) {
            errorMessage("Error occured while highlighting misspelled words.  \nMay have been the user added text while program was spell checking");
        }
    }//GEN-LAST:event_prevActionPerformed

    private void fixItActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fixItActionPerformed
        // find the currently selected word to change the misspelled word to
        int row = suggestionsTable.getSelectedRow();
        int col = suggestionsTable.getSelectedColumn();
        
        // if the selection is valid
        if (row >= 0 && row < suggestionsTable.getRowCount() && col >= 0 && col < suggestionsTable.getColumnCount()) {
            // get the string at the selected element
            String change = suggestionsTable.getValueAt(row, col).toString();

            // create the new text with the changed word for the text area
            String newText = enterString.getText().substring(0, enterString.getSelectionStart() - 1) + change + enterString.getText().substring(enterString.getSelectionEnd() + 1);
            enterString.setText(newText);
            
            // fix the start and end points for the selected word
            start--;
            end = end - 2;
            
            // go to next selection
            next.doClick();
        }
    }//GEN-LAST:event_fixItActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SpellCheckGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SpellCheckGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SpellCheckGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SpellCheckGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new SpellCheckGUI().setVisible(true);
                } catch (IOException ex) {
                    errorMessage("Dictionary File was not found or was corrupted");
                }
            }
        });
    }
    
    /**
     * Set the default suggestions for a word (none)
     * @return the model for the suggestions (basically an empty array except that the header has one that says "Suggestions")
     */
    private DefaultTableModel getSuggestions() {
        DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Suggestions"
            }
        );
        return model;      
    }
    
    /**
     * Set the suggestions to have the suggestions for a word
     * @param suggestions a 2 dimensional array of strings, but there should only be one column.  Its basically a one dimensional array put into a 2d array so that the array can be accepted
     * @return the model with the word suggestions
     */
    private DefaultTableModel getSuggestions(String[][] suggestions) {
        DefaultTableModel model = new javax.swing.table.DefaultTableModel(
            suggestions,
            new String [] {
                "Suggestions"
            }
        );
        return model;      
    }
    
    /**
     * Empties the suggestions list and resets the selection index to no selection index
     */
    private void resetSuggestions() {
        suggestionsTable.setModel(getSuggestions());
        suggestLabel.setText(LABEL_TEXT);
        start = -1;
        end = -1;
    }
    
    /**
     * Highlights the misspelled words
     * @throws BadLocationException improper locations for misspelled words were found
     */
    private void highlightMisspelled() throws BadLocationException {
        // gets the text in the text area
        String s = enterString.getText();
        // initialize the start and end points of the  highlighted words
        int beginHighlight = 0;
        int endHighlight = 0;
        // parse through the string
        for (int i = 0; i < s.length(); i++) {
            // if the current character is a '<'
            if (s.charAt(i) == '<') {
                // set the starting point for the highlight
                beginHighlight = i+1;
            }
            // if the current character is a '>'
            else if (s.charAt(i) == '>') {
                // set the end point for the highlight of this word
                endHighlight = i;
                
                // highlight the word
                Highlighter hl = enterString.getHighlighter();
                HighlightPainter hlp = new DefaultHighlightPainter(Color.yellow);
                hl.addHighlight(beginHighlight, endHighlight, hlp);
            }
        }
    }
    
    /**
     * Opens a window that tells the user an error has occurred and tells the user about what probably happened
     * @param message what to tell the user about the error
     */
    public static void errorMessage(String message) {
        JFrame frame = new JFrame("Error");
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea enterString;
    private javax.swing.JButton fileCheck;
    private javax.swing.JButton fixIt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton next;
    private javax.swing.JLabel note;
    private javax.swing.JButton prev;
    private javax.swing.JButton stringCheck;
    private javax.swing.JLabel suggestLabel;
    private javax.swing.JTable suggestionsTable;
    private javax.swing.JLabel textAreaLabel;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
