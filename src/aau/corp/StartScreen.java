package aau.corp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Ujjawal Gupta on 03-Apr-16.
 */
public class StartScreen  extends JPanel {

    public StartScreen(){
        setFocusable(true);

        JFrame frame = new JFrame("Welcome to the Game");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FlowLayout frameLayout = new FlowLayout();
        frame.setLayout(frameLayout);

        addPLayerDetails(frame);
        numberOfBalls(frame);
        paddleLength(frame);

        frame.setSize(500, 250);
        frame.setVisible(true);

    }

    private void numberOfBalls(JFrame frame) {

        JPanel ballNumber   = new JPanel();

        ballNumber.setLayout(new BorderLayout());

        ballNumber.add(new JLabel("Number of Balls"), BorderLayout.CENTER);
        JTextArea playerOne = new JTextArea("EnterNumber of Balls Here");
        playerOne.setSize(50,20);
        ballNumber.add(playerOne, BorderLayout.AFTER_LAST_LINE);
        frame.add(ballNumber, BorderLayout.CENTER);

    }

    private void paddleLength(JFrame frame){

        JPanel paddleLength   = new JPanel();


        paddleLength.setLayout(new BorderLayout());

        paddleLength.add(new JLabel("PaddleLength"), BorderLayout.CENTER);
        JTextArea playerOne = new JTextArea("Enter PaddleLength");
        playerOne.setSize(50,20);
        paddleLength.add(playerOne, BorderLayout.AFTER_LAST_LINE);

        frame.add(paddleLength, BorderLayout.CENTER);


    }

    public void addPLayerDetails(JFrame frame){
        JPanel playerOnePanel   = new JPanel();
        JPanel playerTwoPanel   = new JPanel();
        JPanel playerThreePanel = new JPanel();
        JPanel playerFourPanel  = new JPanel();

        playerOnePanel.setLayout(new BorderLayout());
        playerTwoPanel.setLayout(new BorderLayout());
        playerThreePanel.setLayout(new BorderLayout());
        playerFourPanel.setLayout(new BorderLayout());

        playerOnePanel.add(new JLabel("Player One"), BorderLayout.CENTER);
        JTextArea playerOne = new JTextArea("Enter Name here");
        playerOne.setSize(50,20);
        playerOnePanel.add(playerOne, BorderLayout.AFTER_LAST_LINE);

        playerTwoPanel.add(new JLabel("Player Two"), BorderLayout.CENTER);
        JTextArea playerTwo = new JTextArea("Enter Name here");
        playerTwo.setSize(50,20);
        playerTwoPanel.add(playerTwo, BorderLayout.AFTER_LAST_LINE);

        playerThreePanel.add(new JLabel("Player Three"), BorderLayout.CENTER);
        JTextArea playerThree = new JTextArea("Enter Name here");
        playerThree.setSize(50,20);
        playerThreePanel.add(playerThree, BorderLayout.AFTER_LAST_LINE);

        playerFourPanel.add(new JLabel("Player Four"), BorderLayout.CENTER);
        JTextArea playerFour = new JTextArea("Enter Name here");
        playerFour.setSize(50,20);
        playerFourPanel.add(playerFour, BorderLayout.AFTER_LAST_LINE);


        frame.add(playerOnePanel, BorderLayout.CENTER);
        frame.add(playerTwoPanel,BorderLayout.AFTER_LAST_LINE);
        frame.add(playerThreePanel,BorderLayout.AFTER_LAST_LINE);
        frame.add(playerFourPanel,BorderLayout.AFTER_LAST_LINE);

    }

}
