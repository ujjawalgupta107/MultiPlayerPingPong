package aau.corp;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Ujjawal Gupta on 03-Apr-16.
 */
public class StartScreen {

    private JButton createNewGameButton;
    private JButton joinAnExistingGameButton;
    private JPanel startView;

    static JFrame startframe = new JFrame("Start Game");


    public StartScreen(){

        createNewGameButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    startframe.setVisible(false); //you can't see me!
                    //frame.dispose(); //Destroy the JFrame object
                    JFrame frame = new JFrame("Create Game");
                    frame.setContentPane(new CreateGame(frame).createview);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();

                    frame.setSize(500,350);
                    frame.setVisible(true);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        joinAnExistingGameButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    startframe.setVisible(false); //you can't see me!
                    //frame.dispose(); //Destroy the JFrame object
                    JFrame frame = new JFrame("Join Game");
                    frame.setContentPane(new JoinGame(frame).joinview);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();

                    frame.setSize(600,200);
                    frame.setVisible(true);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    public static void main(String arg[]) {
       startframe.setContentPane(new StartScreen().startView);
       startframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       startframe.pack();
       startframe.setSize(500,200);
       startframe.setVisible(true);
   }

}
