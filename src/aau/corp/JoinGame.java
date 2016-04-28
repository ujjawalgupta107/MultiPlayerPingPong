package aau.corp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.InetAddress;


/**
 * Created by Ujjawal Gupta on 25-Apr-16.
 */
public class JoinGame {
    public JPanel joinview;
    private JButton joinGameButton;
    private JTextField textField1;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JLabel answer_player;
    private static int player_number;

    public JoinGame(final JFrame frame){

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

    private static void submit(final JTextField textField1) throws IOException {

        System.out.println("address "+ textField1.getText());

        Thread t1 = new Thread(){
            public void run(){
                try {
                    Main m1 = new Main(7071,player_number, (textField1.getText()));//send the address of the host and port
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();

        Thread t2 = new Thread(){
            public void run(){
                try {
                    Main m1 = new Main(7072,player_number, (textField1.getText()));//send the address of the host and port
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t2.start();
        Thread t3 = new Thread(){
            public void run(){
                try {
                    Main m1 = new Main(7073,player_number, (textField1.getText()));//send the address of the host and port
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t3.start();

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
