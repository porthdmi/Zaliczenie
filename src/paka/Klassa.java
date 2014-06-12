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

	public void processAnswer(String answer) {         
        char newCharacter = answer.charAt(0);

       
        String nextGuess    = "";
        boolean foundAMatch = false;
        for( int i=0; i<targetWord.length(); i++ ) {
            char characterToMatch = targetWord.charAt(i);
            if( characterToMatch == newCharacter ) {
                nextGuess = nextGuess.concat( String.valueOf(newCharacter) );
                foundAMatch = true;
            }
            else {
                nextGuess = nextGuess.concat(String.valueOf
                                                   ( currentGuess.charAt(i) ));
            }
        }

currentGuess = nextGuess;
        wordArea.setText( currentGuess );

       
        if( currentGuess.equals( targetWord ) ) {
            
            Iterator alphaIterator = alphaButtonList.iterator();
            while( alphaIterator.hasNext() ) {
                ( (JButton)alphaIterator.next() ).setEnabled( disable );
            }
            messageArea.setText( winnerMessage );
            newGameButton.setEnabled( reset );
            exitButton.setEnabled( reset );
        }
        
        else {
            if( !foundAMatch ) {
                numberWrong++;
                switch (numberWrong){
                    case 1: { headDrawn     = true; break; }
                    case 2: { bodyDrawn     = true; break; }
                    case 3: { leftArmDrawn  = true; break; }
                    case 4: { rightArmDrawn = true; break; }
                    case 5: { leftLegDrawn  = true; break; }
                    case 6: { rightLegDrawn = true; break; }
                    default: System.out.println("powinienieœ zgin¹æ!");
                }
                
                gallowsArea.repaint();
            }
            
            if( numberWrong >= numberOfBodyParts ) {
                
                Iterator alphaIterator = alphaButtonList.iterator();
                while( alphaIterator.hasNext() ) {
                    ( (JButton)alphaIterator.next() ).setEnabled( disable );
                }
                messageArea.setText( losingPrefix + targetWord );
                newGameButton.setEnabled( reset );
                exitButton.setEnabled( reset );
            }
        }
    }


    public Component createNorthPane() {
        JPanel pane = new JPanel();
        pane.setLayout( new BoxLayout( pane, BoxLayout.X_AXIS ) );
        pane.setBorder( BorderFactory.createEmptyBorder(0, 10, 10, 10) );
        pane.add(Box.createHorizontalGlue() );
        wordArea = new JLabel("Nowa gra");
        wordArea.setFont( new Font("Helvetica", Font.PLAIN, 24) );
        wordArea.setBackground(Color.lightGray);
        wordArea.setForeground(Color.black);
        pane.add(wordArea);
        pane.add(Box.createHorizontalGlue() );
        return pane;
    }




    public Component createWestPane() {
        ActionListener alphabetButtonAction = new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                JButton buttonPushed = (JButton)e.getSource();
                buttonPushed.setEnabled( disable );
                processAnswer( buttonPushed.getText() );
            }
        };

        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createLoweredBevelBorder());
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c  = new GridBagConstraints();
        pane.setLayout( gridbag );
        c.fill = GridBagConstraints.BOTH;

        JButton button = new JButton( "a" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 0;
        c.gridy      = 0;
        c.gridheight = 1;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "b" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 1;
        c.gridy      = 0;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "c" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 2;
        c.gridy      = 0;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "d" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 0;
        c.gridy      = 1;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "e" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 1;
        c.gridy      = 1;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "f" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 2;
        c.gridy      = 1;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );


        button = new JButton( "g" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 0;
        c.gridy      = 2;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "h" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 1;
        c.gridy      = 2;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "i" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 2;
        c.gridy      = 2;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "j" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 0;
        c.gridy      = 3;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "k" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 1;
        c.gridy      = 3;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "l" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 2;
        c.gridy      = 3;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );



	button = new JButton( "m" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 0;
        c.gridy      = 4;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );



}
