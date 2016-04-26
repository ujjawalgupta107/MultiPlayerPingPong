package aau.corp;

/**
 * Created by Ujjawal Gupta on 20-Apr-16.
 */

import java.awt.*;
import java.io.IOException;
import javax.swing.JFrame;

public class Main3 {

    public Main3(){
        JFrame frame = new JFrame("Ping Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

/*
        PongPanel pongPanel  = new PongPanel();
        frame.add(pongPanel,BorderLayout.CENTER);
*/
        PongPanel pongPanel  = new PongPanel();
        frame.add(pongPanel,BorderLayout.CENTER);

        frame.setSize(1000,1000);
        frame.setVisible(true);
    }

    public static void main(String arg[]) throws IOException {

        Main3 m3 = new Main3();
    }
}

