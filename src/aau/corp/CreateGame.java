package aau.corp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Created by Ujjawal Gupta on 25-Apr-16.
 */
public class CreateGame extends Panel{

    private JComboBox playerTpeC;
    private JComboBox BallNumberC;
    private JComboBox GridC;
    private JComboBox aiC;
    public JPanel startview;
    private JButton createGameButton;

    public CreateGame(){

        createGameButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    submit(playerTpeC, BallNumberC, GridC, aiC);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private static void submit(JComboBox playerTpeC, JComboBox BallNumberC, JComboBox GridC, JComboBox aiC) throws Exception {
        System.out.println("PlayerType "+ playerTpeC.getSelectedIndex());
        System.out.println("BallNumber "+ BallNumberC.getSelectedIndex());
        System.out.println("GridC "+ GridC.getSelectedIndex());
        System.out.println("aiC "+ aiC.getSelectedIndex());

        int grid =0;
        if(GridC.getSelectedIndex()==0){grid = 300;}
        else if(GridC.getSelectedIndex()==1){ grid = 500;}
        else if(GridC.getSelectedIndex()==2){ grid = 700;}

        UDPHolePunchingServer u = new UDPHolePunchingServer();

        Main m = new Main(7070); //send PlayerType,  BallNumber+1, grid,  aiC

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Create Game");
        frame.setContentPane(new CreateGame().startview);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500,350);
        frame.setVisible(true);
    }
}
