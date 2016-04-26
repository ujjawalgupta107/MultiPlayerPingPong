package aau.corp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;


/**
 * Created by Ujjawal Gupta on 25-Apr-16.
 */
public class JoinGame {
    public JPanel joinview;
    private JButton joinGameButton;
    private JTextField textField1;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JLabel answer_player;
    private static int player_number;

    public JoinGame(JFrame frame){

        joinGameButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    submit(textField1);
                    frame.dispose();
                    System.out.println("rrrrrrrr");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        a1Button.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer_player.setText("1");
                player_number=1;
            }
        });
        a2Button.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer_player.setText("2");
                player_number=2;
            }
        });
        a3Button.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer_player.setText("3");
                player_number =3;
            }
        });
        a4Button.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer_player.setText("4");
                player_number=4;
            }
        });
    }

    private static void submit(JTextField textField1) throws IOException {

        System.out.println("address "+ textField1.getText());
        Main m = new Main(Integer.parseInt(textField1.getText()),player_number);//send the address of the host and port
    }

    public static void main(String[] arg) {

        JFrame jframe = new JFrame("Create Game");
        jframe.setContentPane((new JoinGame(jframe)).joinview);
        jframe.setDefaultCloseOperation(3);
        jframe.pack();
        jframe.setSize(500, 200);
        jframe.setVisible(true);
    }
}
