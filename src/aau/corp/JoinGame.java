package aau.corp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

import static aau.corp.StartScreen.frame;


/**
 * Created by Ujjawal Gupta on 25-Apr-16.
 */
public class JoinGame {
    public JPanel joinview;
    private JButton joinGameButton;
    private JTextField textField1;

    public JoinGame(){

        joinGameButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    submit(textField1);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private static void submit(JTextField textField1) throws IOException {

        System.out.println("address "+ textField1.getText());
        Main m = new Main(Integer.parseInt(textField1.getText()));//send the address of the host and port
    }


    public static void main(String[] arg) {

        JFrame frame = new JFrame("Create Game");
        frame.setContentPane((new JoinGame()).joinview);
        frame.setDefaultCloseOperation(3);
        frame.pack();
        frame.setSize(500, 200);
        frame.setVisible(true);
    }
}
