package aau.corp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import static com.sun.glass.ui.Cursor.setVisible;

/**
 * Created by Ujjawal Gupta on 03-Apr-16.
 */
public class StartScreen {

    private JButton createNewGameButton;
    private JButton joinAnExistingGameButton;
    private JPanel startView;

    static JFrame frame = new JFrame("Start Game");


    public StartScreen(){

        createNewGameButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.setVisible(false); //you can't see me!
                    //frame.dispose(); //Destroy the JFrame object
                    JFrame frame = new JFrame("Create Game");
                    frame.setContentPane(new CreateGame().startview);
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
                    frame.setVisible(false); //you can't see me!
                    //frame.dispose(); //Destroy the JFrame object
                    JFrame frame = new JFrame("Join Game");
                    frame.setContentPane(new JoinGame().joinview);
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
        frame.setContentPane(new StartScreen().startView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setSize(500,200);
        frame.setVisible(true);

    }

}
