package aau.corp;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.io.*;
import java.net.*;

public class Main {

    public static void main(String arg[]) throws Exception {


        DatagramSocket clientSocket = new DatagramSocket();

        // prepare Data
        byte[] sendData = "Hello".getBytes();

        // send Data to Server with fix IP (X.X.X.X)
        // Client1 uses port 7070, Client2 uses port 7071
        int connect_port =7071;
        DatagramPacket sendPacket = new DatagramPacket(sendData,
                sendData.length, InetAddress.getByName("10.192.49.191"), connect_port);
        clientSocket.send(sendPacket);

        // receive Data ==> Format:"<IP of other Client>-<Port of other Client>"
        DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
        clientSocket.receive(receivePacket);


        // Convert Response to IP and Port
        String response = new String(receivePacket.getData());
        String[] splitResponse = response.split("-");
        //System.out.println(splitResponse.length);
        InetAddress ip1 = InetAddress.getByName(splitResponse[0].substring(1));
        //  InetAddress ip2 = InetAddress.getByName(splitResponse[2].substring(1));

        int port1 = Integer.parseInt(splitResponse[1]);
        //int port2 = Integer.parseInt(splitResponse[3]);

        // output converted Data for check
        System.out.println("IP1: " + ip1 + " PORT1: " + port1);
        // System.out.println("IP2: " + ip2 + " PORT1: " + port2);

        // close socket and open new socket with SAME localport
        int localPort = clientSocket.getLocalPort();
        clientSocket.close();
        /*clientSocket = new DatagramSocket(localPort);

        // set Timeout for receiving Data
        clientSocket.setSoTimeout(5000);

        // send 5000 Messages for testing
        for (int i = 0; i < 5; i++) {

            // send Message to other client
        	if (connect_port == 7070)
        	{sendData = ("From 7070 " + i).getBytes();}
        	else if (connect_port == 7071)
        	{sendData = ("From 7071 " + i).getBytes();}
        	else if (connect_port == 7072)
        	{sendData = ("From 7072 " + i).getBytes();}
        	else
            {sendData = ("Datapacket(" + i + ")").getBytes();}
            sendPacket = new DatagramPacket(sendData, sendData.length, ip1, port1);
            clientSocket.send(sendPacket);
            DatagramPacket sendPacket1 = new DatagramPacket(sendData, sendData.length, ip2, port2);
            clientSocket.send(sendPacket1);

            // receive Message from other client
            try {
                receivePacket.setData(new byte[1024]);
                clientSocket.receive(receivePacket);
                System.out.println("REC: " + new String(receivePacket.getData()));

            } catch (Exception e) {
                System.out.println("SERVER TIMED OUT");
            }
        }
*/
        JFrame frame = new JFrame("Ping Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        PongPanelTest pongPanel  = new PongPanelTest(localPort,ip1,port1);
        JLabel aman = new JLabel();
        aman.setText("PLAYER 2");
        frame.add(aman,BorderLayout.PAGE_START);
        frame.add(pongPanel,BorderLayout.CENTER);
/*

        StartScreen startScreen = new StartScreen();
        frame.add(startScreen,BorderLayout.CENTER);
*/

        frame.setSize(1000,1000);
        frame.setVisible(true);

        // close connection
        // clientSocket.close();

    }
}
