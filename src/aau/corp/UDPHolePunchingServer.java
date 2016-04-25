package aau.corp;


import java.io.*;
import java.net.*;


public class UDPHolePunchingServer {

    public UDPHolePunchingServer() throws Exception {

        // Waiting for Connection of Client1 on Port 7070
        // ////////////////////////////////////////////////

        // open serverSocket on Port 7070

        int no_of_players = 2;
        if (no_of_players == 2) {
            DatagramSocket serverSocket1 = new DatagramSocket(7070);

            System.out.println("Waiting for Client 1 on Port "
                    + serverSocket1.getLocalPort());

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

            // Send the Information to the other Client
            // /////////////////////////////////////////////////

            String toClient1 = "";
            String toClient2 = "";

            toClient1 = msgInfoOfClient2;
            toClient2 = msgInfoOfClient1;

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

            toClient1 = msgInfoOfClient2 + msgInfoOfClient3;
            toClient2 = msgInfoOfClient1 + msgInfoOfClient3;
            toClient3 = msgInfoOfClient1 + msgInfoOfClient2;


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

            toClient1 = msgInfoOfClient2 + msgInfoOfClient3 + msgInfoOfClient4;
            toClient2 = msgInfoOfClient3 + msgInfoOfClient4 + msgInfoOfClient1;
            toClient3 = msgInfoOfClient4 + msgInfoOfClient1 + msgInfoOfClient2;
            toClient4 = msgInfoOfClient1 + msgInfoOfClient2 + msgInfoOfClient3;


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


    public static void main(String args[]) throws Exception {

        // Waiting for Connection of Client1 on Port 7070
        // ////////////////////////////////////////////////

        // open serverSocket on Port 7070

        int no_of_players = 2;
        if (no_of_players == 2) {
            DatagramSocket serverSocket1 = new DatagramSocket(7070);

            System.out.println("Waiting for Client 1 on Port "
                    + serverSocket1.getLocalPort());

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

            // Send the Information to the other Client
            // /////////////////////////////////////////////////

            String toClient1 = "";
            String toClient2 = "";

            toClient1 = msgInfoOfClient2;
            toClient2 = msgInfoOfClient1;

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

            toClient1 = msgInfoOfClient2 + msgInfoOfClient3;
            toClient2 = msgInfoOfClient1 + msgInfoOfClient3;
            toClient3 = msgInfoOfClient1 + msgInfoOfClient2;


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

            toClient1 = msgInfoOfClient2 + msgInfoOfClient3 + msgInfoOfClient4;
            toClient2 = msgInfoOfClient3 + msgInfoOfClient4 + msgInfoOfClient1;
            toClient3 = msgInfoOfClient4 + msgInfoOfClient1 + msgInfoOfClient2;
            toClient4 = msgInfoOfClient1 + msgInfoOfClient2 + msgInfoOfClient3;


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

}


