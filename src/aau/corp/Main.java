package aau.corp;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.io.*;
import java.net.*;

public class Main {

    public Main() throws IOException {
        start1();
    }

    public Main(int port) throws IOException {
        start(port);
    }

    public void start(int port) throws IOException {

        int connect_port = port;

        int players = 2;
        DatagramSocket clientSocket = new DatagramSocket();
        // prepare Data
        byte[] sendData = "Hello".getBytes();

        // send Data to Server with fix IP (X.X.X.X)
        // Client1 uses port 7070, Client2 uses port 7071

        DatagramPacket sendPacket = new DatagramPacket(sendData,
                sendData.length, InetAddress.getByName("10.204.152.94"), connect_port);
        clientSocket.send(sendPacket);

        // receive Data ==> Format:"<IP of other Client>-<Port of other Client>"
        DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
        clientSocket.receive(receivePacket);


        // Convert Response to IP and Port
        String response = new String(receivePacket.getData());
        String[] splitResponse = response.split("-");
        //System.out.println(splitResponse.length);
        if (players ==2) {

            InetAddress ip1 = InetAddress.getByName(splitResponse[0].substring(1));

            InetAddress[] send_ip = {ip1};

            int port1 = Integer.parseInt(splitResponse[1]);

            int[] send_port = {port1};

            // output converted Data for check
            System.out.println("IP1: " + ip1 + " PORT1: " + port1);

            // close socket and open new socket with SAME localport
            int localPort = clientSocket.getLocalPort();
            clientSocket.close();
            JFrame frame = new JFrame("Ping Pong");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            JLabel aman = new JLabel();


            if (connect_port == 7070) {
                aman.setText("PLAYER 1");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelOne pongPanel = new PongPanelOne(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);

            } else if (connect_port == 7071) {
                aman.setText("PLAYER 2");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelTwo pongPanel = new PongPanelTwo(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);
            } else if (connect_port == 7072) {
                aman.setText("PLAYER 3");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelThree pongPanel = new PongPanelThree(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);
            } else if (connect_port == 7073) {
                aman.setText("PLAYER 4");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelFour pongPanel = new PongPanelFour(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);
            }
            frame.setSize(400, 400);
            frame.setVisible(true);
        }
        else if(players==3)
        {
            InetAddress ip1 = InetAddress.getByName(splitResponse[0].substring(1));
            InetAddress ip2 = InetAddress.getByName(splitResponse[2].substring(1));

            InetAddress[] send_ip = {ip1, ip2};

            int port1 = Integer.parseInt(splitResponse[1]);
            int port2 = Integer.parseInt(splitResponse[3]);

            int[] send_port = {port1, port2};

            // output converted Data for check
            System.out.println("IP1: " + ip1 + " PORT1: " + port1);
            System.out.println("IP2: " + ip2 + " PORT2: " + port2);

            // close socket and open new socket with SAME localport
            int localPort = clientSocket.getLocalPort();
            clientSocket.close();


            JFrame frame = new JFrame("Ping Pong");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            JLabel aman = new JLabel();


            if (connect_port == 7070) {
                aman.setText("PLAYER 1");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelOne pongPanel = new PongPanelOne(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);

            } else if (connect_port == 7071) {
                aman.setText("PLAYER 2");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelTwo pongPanel = new PongPanelTwo(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);
            } else if (connect_port == 7072) {
                aman.setText("PLAYER 3");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelThree pongPanel = new PongPanelThree(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);
            } else if (connect_port == 7073) {
                aman.setText("PLAYER 4");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelFour pongPanel = new PongPanelFour(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);
            }

            frame.setSize(400, 400);
            frame.setVisible(true);
        }
        else
        {
            InetAddress ip1 = InetAddress.getByName(splitResponse[0].substring(1));
            InetAddress ip2 = InetAddress.getByName(splitResponse[2].substring(1));
            InetAddress ip3 = InetAddress.getByName(splitResponse[4].substring(1));

            InetAddress[] send_ip = {ip1, ip2, ip3};

            int port1 = Integer.parseInt(splitResponse[1]);
            int port2 = Integer.parseInt(splitResponse[3]);
            int port3 = Integer.parseInt(splitResponse[5]);

            int[] send_port = {port1, port2, port3};

            // output converted Data for check
            System.out.println("IP1: " + ip1 + " PORT1: " + port1);
            System.out.println("IP2: " + ip2 + " PORT2: " + port2);
            System.out.println("IP3: " + ip3 + " PORT3: " + port3);

            // close socket and open new socket with SAME localport
            int localPort = clientSocket.getLocalPort();
            clientSocket.close();


            JFrame frame = new JFrame("Ping Pong");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            JLabel aman = new JLabel();


            if (connect_port == 7070) {
                aman.setText("PLAYER 1");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelOne pongPanel = new PongPanelOne(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);

            } else if (connect_port == 7071) {
                aman.setText("PLAYER 2");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelTwo pongPanel = new PongPanelTwo(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);
            } else if (connect_port == 7072) {
                aman.setText("PLAYER 3");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelThree pongPanel = new PongPanelThree(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);
            } else if (connect_port == 7073) {
                aman.setText("PLAYER 4");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelFour pongPanel = new PongPanelFour(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);
            }
            frame.setSize(400, 400);
            frame.setVisible(true);

        }


    }

    public void start1() throws IOException {

        int connect_port = 7070;
        int players = 2;
        DatagramSocket clientSocket = new DatagramSocket();
        // prepare Data
        byte[] sendData = "Hello".getBytes();

        // send Data to Server with fix IP (X.X.X.X)
        // Client1 uses port 7070, Client2 uses port 7071

        DatagramPacket sendPacket = new DatagramPacket(sendData,
                sendData.length, InetAddress.getByName("169.254.111.176"), connect_port);
        clientSocket.send(sendPacket);

        // receive Data ==> Format:"<IP of other Client>-<Port of other Client>"
        DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
        clientSocket.receive(receivePacket);


        // Convert Response to IP and Port
        String response = new String(receivePacket.getData());
        String[] splitResponse = response.split("-");
        //System.out.println(splitResponse.length);
        if (players ==2) {

            InetAddress ip1 = InetAddress.getByName(splitResponse[0].substring(1));

            InetAddress[] send_ip = {ip1};

            int port1 = Integer.parseInt(splitResponse[1]);

            int[] send_port = {port1};

            // output converted Data for check
            System.out.println("IP1: " + ip1 + " PORT1: " + port1);

            // close socket and open new socket with SAME localport
            int localPort = clientSocket.getLocalPort();
            clientSocket.close();
            JFrame frame = new JFrame("Ping Pong");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            JLabel aman = new JLabel();


            if (connect_port == 7070) {
                aman.setText("PLAYER 1");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelOne pongPanel = new PongPanelOne(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);

            } else if (connect_port == 7071) {
                aman.setText("PLAYER 2");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelTwo pongPanel = new PongPanelTwo(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);
            } else if (connect_port == 7072) {
                aman.setText("PLAYER 3");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelThree pongPanel = new PongPanelThree(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);
            } else if (connect_port == 7073) {
                aman.setText("PLAYER 4");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelFour pongPanel = new PongPanelFour(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);
            }
            frame.setSize(400, 400);
            frame.setVisible(true);
        }
        else if(players==3)
        {
            InetAddress ip1 = InetAddress.getByName(splitResponse[0].substring(1));
            InetAddress ip2 = InetAddress.getByName(splitResponse[2].substring(1));

            InetAddress[] send_ip = {ip1, ip2};

            int port1 = Integer.parseInt(splitResponse[1]);
            int port2 = Integer.parseInt(splitResponse[3]);

            int[] send_port = {port1, port2};

            // output converted Data for check
            System.out.println("IP1: " + ip1 + " PORT1: " + port1);
            System.out.println("IP2: " + ip2 + " PORT2: " + port2);

            // close socket and open new socket with SAME localport
            int localPort = clientSocket.getLocalPort();
            clientSocket.close();


            JFrame frame = new JFrame("Ping Pong");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            JLabel aman = new JLabel();


            if (connect_port == 7070) {
                aman.setText("PLAYER 1");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelOne pongPanel = new PongPanelOne(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);

            } else if (connect_port == 7071) {
                aman.setText("PLAYER 2");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelTwo pongPanel = new PongPanelTwo(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);
            } else if (connect_port == 7072) {
                aman.setText("PLAYER 3");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelThree pongPanel = new PongPanelThree(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);
            } else if (connect_port == 7073) {
                aman.setText("PLAYER 4");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelFour pongPanel = new PongPanelFour(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);
            }

            frame.setSize(400, 400);
            frame.setVisible(true);
        }
        else
        {
            InetAddress ip1 = InetAddress.getByName(splitResponse[0].substring(1));
            InetAddress ip2 = InetAddress.getByName(splitResponse[2].substring(1));
            InetAddress ip3 = InetAddress.getByName(splitResponse[4].substring(1));

            InetAddress[] send_ip = {ip1, ip2, ip3};

            int port1 = Integer.parseInt(splitResponse[1]);
            int port2 = Integer.parseInt(splitResponse[3]);
            int port3 = Integer.parseInt(splitResponse[5]);

            int[] send_port = {port1, port2, port3};

            // output converted Data for check
            System.out.println("IP1: " + ip1 + " PORT1: " + port1);
            System.out.println("IP2: " + ip2 + " PORT2: " + port2);
            System.out.println("IP3: " + ip3 + " PORT3: " + port3);

            // close socket and open new socket with SAME localport
            int localPort = clientSocket.getLocalPort();
            clientSocket.close();


            JFrame frame = new JFrame("Ping Pong");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            JLabel aman = new JLabel();


            if (connect_port == 7070) {
                aman.setText("PLAYER 1");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelOne pongPanel = new PongPanelOne(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);

            } else if (connect_port == 7071) {
                aman.setText("PLAYER 2");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelTwo pongPanel = new PongPanelTwo(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);
            } else if (connect_port == 7072) {
                aman.setText("PLAYER 3");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelThree pongPanel = new PongPanelThree(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);
            } else if (connect_port == 7073) {
                aman.setText("PLAYER 4");
                frame.add(aman, BorderLayout.PAGE_START);
                PongPanelFour pongPanel = new PongPanelFour(localPort, send_ip, send_port);
                frame.add(pongPanel, BorderLayout.CENTER);
            }
            frame.setSize(400, 400);
            frame.setVisible(true);

        }


    }

    public static void main(String arg[]) throws Exception {
    }
}
