package paka;

/**
 *
 * @autor Tomasz Brynza & Emilka Rogalska
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import java.util.*;


public class Klassa {
    GallowsArea gallowsArea = null;

    JButton exitButton    = null;
    JButton newGameButton = null;

    JLabel    wordArea    = null;
    JLabel    messageArea = null;
    java.util.List alphaButtonList = new ArrayList();
    Iterator alphaIterator = null;

    boolean reset        = true;
    boolean disable      = false;
    boolean dontWrap     = false;
    boolean wrap         = true;
    boolean headDrawn    = false;
    boolean bodyDrawn    = false;
    boolean leftArmDrawn = false;
    boolean rightArmDrawn= false;
    boolean leftLegDrawn = false;
    boolean rightLegDrawn= false;

    
    String[] targetWords = {"program","informatyka","komputer" };
    String winnerMessage = "Gratulacje!  Jesteœ ocalony!";
    String losingPrefix  = "Ÿle! Umierasz. Chodzi³o o inne s³owo.. ";
    String currentGuess;
    String targetWord;

    int numberWrong       = 0;
    int numberOfBodyParts = 6;
    int next              = 0;

    public void setUpNewGame() {
        numberWrong = 0;
        messageArea.setText("wygraj albo zgiñ!");

        
        Iterator alphaIterator = alphaButtonList.iterator();
        while( alphaIterator.hasNext() ) {
            ( (JButton)alphaIterator.next() ).setEnabled( reset );
        }

        
        newGameButton.setEnabled( disable );

      
        wordArea.setBackground(Color.lightGray);

        
        double numb = Math.random();
        next = (int)( numb * targetWords.length );
        targetWord  = targetWords[next];

       
        currentGuess = "?";
        for( int i=0; i<targetWord.length()-1; i++) {
            currentGuess = currentGuess.concat("?");
        }
        wordArea.setText( currentGuess );

        
        headDrawn    = false;
        bodyDrawn    = false;
        leftArmDrawn = false;
        rightArmDrawn= false;
        leftLegDrawn = false;
        rightLegDrawn= false;
        gallowsArea.repaint();

    }


}
