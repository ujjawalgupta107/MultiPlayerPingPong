package aau.corp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class CreateGame extends Panel{

    private JComboBox playerTpeC;
    private JComboBox BallNumberC;
    private JComboBox GridC;
    private JComboBox aiC;
    public JPanel createview;
    private JButton createGameButton;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;

    static JFrame framep = new JFrame("Create Game");

    static String data;
    static String limit;
    static String level;

    public CreateGame(JFrame frame){

        createGameButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    submit(playerTpeC, BallNumberC, GridC, textField1, textField2,comboBox1);
                    frame.dispose();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void submit(JComboBox playerTpeC, JComboBox BallNumberC, JComboBox GridC, JTextField textField1,JTextField textField2, JComboBox comboBox1) throws Exception {
        System.out.println("PlayerType "+ playerTpeC.getSelectedIndex());
        System.out.println("BallNumber "+ BallNumberC.getSelectedIndex());
        System.out.println("GridC "+ GridC.getSelectedIndex());

        String ip_address  = textField1.getText();

        int grid =0;
        if(GridC.getSelectedIndex()==0){grid = 300;}
        else if(GridC.getSelectedIndex()==1){ grid = 500;}
        else if(GridC.getSelectedIndex()==2){ grid = 700;}

        int no_of_players = 2;
        if(playerTpeC.getSelectedIndex()==0){no_of_players=4;}
        if(playerTpeC.getSelectedIndex()==1){no_of_players=3;}
        if(playerTpeC.getSelectedIndex()==2){no_of_players=2;}
        if(playerTpeC.getSelectedIndex()==3){no_of_players=1;}

        int aiLevel = comboBox1.getSelectedIndex() +1;

        String lim = (textField2.getText());

        int number_of_balls = BallNumberC.getSelectedIndex()+1;

        data = grid +"-" + number_of_balls + "-";
        limit = lim + "-";
        level = aiLevel +"-";

        if(no_of_players==1){
            JFrame frame = new JFrame("Ping Pong");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            PongPanel pong = new PongPanel(grid,number_of_balls,aiLevel);
            frame.add(pong,BorderLayout.CENTER);
            frame.setSize(1000,1000);
            frame.setVisible(true);
        }
        if(no_of_players!=1){network(no_of_players, ip_address);}
    }

    public static void network(int player_number_input, String ip_address) throws Exception {

        // Waiting for Connection of Client1 on Port 7070
        // ////////////////////////////////////////////////

        // open serverSocket on Port 7070
        int no_of_players = player_number_input;

        if (no_of_players == 2) {
            DatagramSocket serverSocket1 = new DatagramSocket(7070);

            System.out.println("Waiting for Client 1 on Port "
                    + serverSocket1.getLocalPort());

            Thread t1 = new Thread(){
                public void run(){
                    try {
                        Main m = new Main(7070, player_number_input,ip_address); //send PlayerType,  BallNumber+1, grid,  aiC
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            t1.start();

            //receive Data
            DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
            serverSocket1.receive(receivePacket);
            System.out.println("Thanks for connecting port1" + new String(receivePacket.getData()));

            // Get IP-Address and Port of Client1
            InetAddress IPAddress1 = receivePacket.getAddress();
            int port1 = receivePacket.getPort();
            String msgInfoOfClient1 = IPAddress1 + "-" + port1 + "-";


            System.out.println("Client1: " + msgInfoOfClient1);

            // Waiting for Connection of Client2 on Port 7071
            // ////////////////////////////////////////////////

            // open serverSocket on Port 7071
            DatagramSocket serverSocket2 = new DatagramSocket(7071);

            System.out.println("Waiting for Client 2 on Port "
                    + serverSocket2.getLocalPort());

            // receive Data
            receivePacket = new DatagramPacket(new byte[1024], 1024);
            serverSocket2.receive(receivePacket);
            System.out.println("Thanks for connecting port2" + new String(receivePacket.getData()));

            // GetIP-Address and Port of Client1
            InetAddress IPAddress2 = receivePacket.getAddress();
            int port2 = receivePacket.getPort();
            String msgInfoOfClient2 = IPAddress2 + "-" + port2 + "-";

            System.out.println("Client2:" + msgInfoOfClient2);

            // Send the Information to the other Client
            // /////////////////////////////////////////////////

            String toClient1 = "";
            String toClient2 = "";

            toClient1 = msgInfoOfClient2 + data +limit + level;
            toClient2 = msgInfoOfClient1 + data + limit +level;

            // Send Information  to Client1
            serverSocket1.send(new DatagramPacket(toClient1.getBytes(),
                    toClient1.getBytes().length, IPAddress1, port1));

            // Send Information to Client2
            serverSocket2.send(new DatagramPacket(toClient2.getBytes(),
                    toClient2.getBytes().length, IPAddress2, port2));

            serverSocket1.close();
            serverSocket2.close();

        }

        else if (no_of_players ==3 )
        {

            DatagramSocket serverSocket1 = new DatagramSocket(7070);

            System.out.println("Waiting for Client 1 on Port "
                    + serverSocket1.getLocalPort());

            Thread t1 = new Thread(){
                public void run(){
                    try {
                        InetAddress ip = InetAddress.getLocalHost();
                        Main m = new Main(7070,player_number_input,ip.getHostAddress()); //send PlayerType,  BallNumber+1, grid,  aiC
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            t1.start();

            // receive Data
            DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
            serverSocket1.receive(receivePacket);
            System.out.println("Thanks for connecting port1" + new String(receivePacket.getData()));

            // Get IP-Address and Port of Client1
            InetAddress IPAddress1 = receivePacket.getAddress();
            int port1 = receivePacket.getPort();
            String msgInfoOfClient1 = IPAddress1 + "-" + port1 + "-";


            System.out.println("Client1: " + msgInfoOfClient1);

            // Waiting for Connection of Client2 on Port 7071
            // ////////////////////////////////////////////////

            // open serverSocket on Port 7071
            DatagramSocket serverSocket2 = new DatagramSocket(7071);

            System.out.println("Waiting for Client 2 on Port "
                    + serverSocket2.getLocalPort());

            // receive Data
            receivePacket = new DatagramPacket(new byte[1024], 1024);
            serverSocket2.receive(receivePacket);
            System.out.println("Thanks for connecting port2" + new String(receivePacket.getData()));

            // GetIP-Address and Port of Client1
            InetAddress IPAddress2 = receivePacket.getAddress();
            int port2 = receivePacket.getPort();
            String msgInfoOfClient2 = IPAddress2 + "-" + port2 + "-";

            System.out.println("Client2:" + msgInfoOfClient2);


            // open serverSocket on Port 7072
            DatagramSocket serverSocket3 = new DatagramSocket(7072);

            System.out.println("Waiting for Client 3 on Port "
                    + serverSocket3.getLocalPort());

            // receive Data
            receivePacket = new DatagramPacket(new byte[1024], 1024);
            serverSocket3.receive(receivePacket);
            System.out.println("Thanks for connecting port3" + new String(receivePacket.getData()));

            // GetIP-Address and Port of Client1
            InetAddress IPAddress3 = receivePacket.getAddress();
            int port3 = receivePacket.getPort();
            String msgInfoOfClient3 = IPAddress3 + "-" + port3 + "-";

            System.out.println("Client3:" + msgInfoOfClient3);

            // Send the Information to the other Client
            // /////////////////////////////////////////////////

            String toClient1 = "";
            String toClient2 = "";
            String toClient3 = "";
            String toClient4 = "";

            toClient1 = msgInfoOfClient2 + msgInfoOfClient3 + data +limit +level;
            toClient2 = msgInfoOfClient1 + msgInfoOfClient3 + data +limit +level;
            toClient3 = msgInfoOfClient1 + msgInfoOfClient2 + data +limit+level;


            // Send Information  to Client1
            serverSocket1.send(new DatagramPacket(toClient1.getBytes(),
                    toClient1.getBytes().length, IPAddress1, port1));

            // Send Information to Client2
            serverSocket2.send(new DatagramPacket(toClient2.getBytes(),
                    toClient2.getBytes().length, IPAddress2, port2));

            // Send Information  to Client3
            serverSocket3.send(new DatagramPacket(toClient3.getBytes(),
                    toClient3.getBytes().length, IPAddress3, port3));

            serverSocket1.close();
            serverSocket2.close();
            serverSocket3.close();

        }

        else
        {
            DatagramSocket serverSocket1 = new DatagramSocket(7070);

            System.out.println("Waiting for Client 1 on Port "
                    + serverSocket1.getLocalPort());

            Thread t1 = new Thread(){
                public void run(){
                    try {
                        InetAddress ip = InetAddress.getLocalHost();
                        Main m = new Main(7070,player_number_input,ip.getHostAddress()); //send PlayerType,  BallNumber+1, grid,  aiC
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            t1.start();

            // receive Data
            DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
            serverSocket1.receive(receivePacket);
            System.out.println("Thanks for connecting port1" + new String(receivePacket.getData()));

            // Get IP-Address and Port of Client1
            InetAddress IPAddress1 = receivePacket.getAddress();
            int port1 = receivePacket.getPort();
            String msgInfoOfClient1 = IPAddress1 + "-" + port1 + "-";


            System.out.println("Client1: " + msgInfoOfClient1);

            // Waiting for Connection of Client2 on Port 7071
            // ////////////////////////////////////////////////

            // open serverSocket on Port 7071
            DatagramSocket serverSocket2 = new DatagramSocket(7071);

            System.out.println("Waiting for Client 2 on Port "
                    + serverSocket2.getLocalPort());

            // receive Data
            receivePacket = new DatagramPacket(new byte[1024], 1024);
            serverSocket2.receive(receivePacket);
            System.out.println("Thanks for connecting port2" + new String(receivePacket.getData()));

            // GetIP-Address and Port of Client1
            InetAddress IPAddress2 = receivePacket.getAddress();
            int port2 = receivePacket.getPort();
            String msgInfoOfClient2 = IPAddress2 + "-" + port2 + "-";

            System.out.println("Client2:" + msgInfoOfClient2);


            // open serverSocket on Port 7072
            DatagramSocket serverSocket3 = new DatagramSocket(7072);

            System.out.println("Waiting for Client 3 on Port "
                    + serverSocket3.getLocalPort());

            // receive Data
            receivePacket = new DatagramPacket(new byte[1024], 1024);
            serverSocket3.receive(receivePacket);
            System.out.println("Thanks for connecting port3" + new String(receivePacket.getData()));

            // GetIP-Address and Port of Client1
            InetAddress IPAddress3 = receivePacket.getAddress();
            int port3 = receivePacket.getPort();
            String msgInfoOfClient3 = IPAddress3 + "-" + port3 + "-";

            System.out.println("Client3:" + msgInfoOfClient3);

            // open serverSocket on Port 7073
            DatagramSocket serverSocket4 = new DatagramSocket(7073);

            System.out.println("Waiting for Client 3 on Port "
                    + serverSocket4.getLocalPort());

            // receive Data
            receivePacket = new DatagramPacket(new byte[1024], 1024);
            serverSocket4.receive(receivePacket);
            System.out.println("Thanks for connecting port4" + new String(receivePacket.getData()));

            // GetIP-Address and Port of Client1
            InetAddress IPAddress4 = receivePacket.getAddress();
            int port4 = receivePacket.getPort();
            String msgInfoOfClient4 = IPAddress4 + "-" + port4 + "-";

            System.out.println("Client4:" + msgInfoOfClient4);

            // Send the Information to the other Client
            // /////////////////////////////////////////////////

            String toClient1 = "";
            String toClient2 = "";
            String toClient3 = "";
            String toClient4 = "";

            toClient1 = msgInfoOfClient2 + msgInfoOfClient3 + msgInfoOfClient4 + data +limit +level;
            toClient2 = msgInfoOfClient3 + msgInfoOfClient4 + msgInfoOfClient1 + data +limit +level;
            toClient3 = msgInfoOfClient4 + msgInfoOfClient1 + msgInfoOfClient2 + data +limit +level;
            toClient4 = msgInfoOfClient1 + msgInfoOfClient2 + msgInfoOfClient3 + data +limit +level;


            // Send Information  to Client1
            serverSocket1.send(new DatagramPacket(toClient1.getBytes(),
                    toClient1.getBytes().length, IPAddress1, port1));

            // Send Information to Client2
            serverSocket2.send(new DatagramPacket(toClient2.getBytes(),
                    toClient2.getBytes().length, IPAddress2, port2));

            // Send Information  to Client3
            serverSocket3.send(new DatagramPacket(toClient3.getBytes(),
                    toClient3.getBytes().length, IPAddress3, port3));

            // Send Information  to Client3
            serverSocket4.send(new DatagramPacket(toClient4.getBytes(),
                    toClient4.getBytes().length, IPAddress4, port4));

            serverSocket1.close();
            serverSocket2.close();
            serverSocket3.close();
            serverSocket4.close();

        }
    }

    public static void main(String[] args) {
        framep.setContentPane(new CreateGame(framep).createview);
        framep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framep.pack();
        framep.setSize(500,350);
        framep.setVisible(true);
    }
}
