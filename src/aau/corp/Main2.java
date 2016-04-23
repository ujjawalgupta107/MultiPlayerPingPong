package aau.corp;

/**
 * Created by Ujjawal Gupta on 20-Apr-16.
 */

        import java.awt.*;
        import java.io.IOException;
        import javax.swing.JFrame;

public class Main2 {

    public static void main(String arg[]) throws IOException {

        JFrame frame = new JFrame("Ping Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

/*
        PongPanel pongPanel  = new PongPanel();
        frame.add(pongPanel,BorderLayout.CENTER);
*/
//        PongPanelFour pongPanel  = new PongPanelFour();
//        frame.add(pongPanel,BorderLayout.CENTER);

        frame.setSize(1000,1000);
        frame.setVisible(true);

    }
}

