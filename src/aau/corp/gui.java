package aau.corp;

/**
 * Created by Ujjawal Gupta on 03-Apr-16.
 */

import javax.swing.*;
import java.awt.*;

public class gui {

    JFrame as =  new JFrame("GUI hello world");

    private JLabel title;

    public gui(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        BoxLayout layout = new BoxLayout(as.getContentPane(),BoxLayout.Y_AXIS);

        as.setLayout(layout);
        as.pack();
        as.setVisible(true);

        title = new JLabel("GUI world here");
        as.add(title);

        as.setSize(400,200);
        as.setVisible(true);
        as.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
