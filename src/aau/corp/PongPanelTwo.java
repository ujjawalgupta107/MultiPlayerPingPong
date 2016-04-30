package aau.corp;

/**
 * Created by Ujjawal Gupta on 03-Apr-16.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PongPanelTwo extends JPanel implements ActionListener, KeyListener {

    boolean connection1 = true;
    boolean connection3 = true;
    boolean connection4 = true;
    int doit1=0,doit3=0,doit4=0;
    int won2=0,won1=0,won3=0,won4=0;

    int local_port_number;
    InetAddress[] second_ip;
    int[] second_port;
    DatagramSocket clientsocket = new DatagramSocket();

    int no_of_players;
    boolean isPlayerOneActive = true;
    boolean isPlayerTwoActive = true;
    boolean isPlayerThreeActive = true;
    boolean isPlayerFourActive = true;
    int size;// =300;
    int limit;// = 5;
    int speed_increase_time =0;
    int startRate = 20;        //frame refreshes after every 100 millisecond
    Timer timer;

    int track2 = 0;
    int boardX;
    int boardY;
    int paddle;
    private int paddleSpeed;
    int time = 0;
    int n;// = 2;  //number of balls
    int r;  //ration of the paddle length
    int p;  //paddle speed
    int t;  //time ratio

    //<editor-fold desc="boolean variable for keyPressed">
    private boolean upPressed = false;
    private boolean downPressed = false;
    //</editor-fold>


    private int[] ballX;
    private int[] ballY;

    private int diameter = 15;

    //steps that ball move

    private int[] ballDeltaX;
    private int[] ballDeltaY;

    int[] BallDeltaXArray = {-2, 3, -1, -2};
    int[] BallDeltaYArray = {1, 1, 3, -2};

    int[] nextBallLeft ;
    int[] nextBallRight ;
    int[] nextBallTop ;
    int[] nextBallBottom ;
    int[] nextBallX ;
    int[] nextBallY ;

    //<editor-fold desc="variable for paddle position of each player ">
    //payer paddles initial position
    //at left
    private int playerOneX      ;
    private int playerOneY      ;
    private int playerOneWidth  ;
    private int playerOneHeight ;

    //at right
    private int playerTwoX      ;
    private int playerTwoY      ;
    private int playerTwoWidth  ;
    private int playerTwoHeight ;

    //at the top
    private int playerThreeX ;
    private int playerThreeY ;
    private int playerThreeWidth ;
    private int playerThreeHeight ;

    //at bottom
    private int playerFourX ;
    private int playerFourY ;
    private int playerFourWidth ;
    private int playerFourHeight ;
    //</editor-fold>

    //<editor-fold desc="score of each player">
    private int playerOneScore = 0;
    private int playerTwoScore = 0;                            ///get hte value of score
    private int playerThreeScore = 0;
    private int playerFourScore = 0;

    private int playerOneP = 0;
    private int playerTwoP = 0;
    private int playerThreeP = 0;
    private int playerFourP = 0;
    //</editor-fold>

    //<editor-fold desc="variables for miss and hit">
    int playerOneHit = 0;                        //value obtained
    int playerTwoHit = 0;
    int playerThreeHit = 0;//value obtained
    int playerFourHit = 0;//value obtained
    int playerOneMiss = 0;//value obtained
    int playerTwoMiss = 0;
    int playerThreeMiss = 0;//value obtained
    int playerFourMiss = 0;//value obtained
    //</editor-fold>

    //construct a PongPanel
    public PongPanelTwo(int localport, InetAddress[] ip, int[] port,int grid,int balls,int limit_miss, int no_of_players_input, int aiLevel) throws IOException {

        setBackground(Color.BLACK);
        no_of_players = no_of_players_input;
        local_port_number = localport;
        second_ip = new InetAddress[ip.length];
        second_port = new int[port.length];
        second_ip = ip;
        second_port = port;
        n = balls;
        size = grid;
        limit = limit_miss;
        boardX = size;
        boardY = size;
        paddle = size/6;
        paddleSpeed = paddle/10;
        ballX = new int[n];
        ballY = new int[n];
        ballDeltaX = new int[n];
        ballDeltaY = new int[n];
        nextBallLeft = new int[n];
        nextBallRight = new int[n];
        nextBallTop = new int[n];
        nextBallBottom = new int[n];
        nextBallX = new int[n];
        nextBallY = new int[n];

        playerOneX      = 25;
        playerOneY      = boardY / 2 - paddle / 2;
        playerOneWidth  = 10;
        playerOneHeight = paddle;
        playerTwoX      = boardX - 25 - 10;
        playerTwoY      = boardY / 2 - paddle / 2;
        playerTwoWidth  = 10;
        playerTwoHeight = paddle;
        playerThreeX = boardY / 2 - paddle / 2;
        playerThreeY = 25;
        playerThreeWidth = paddle;
        playerThreeHeight = 10;
        playerFourX = boardY / 2 - paddle / 2;
        playerFourY = boardY - 25 - 10;
        playerFourWidth = paddle;
        playerFourHeight = 10;

        if(aiLevel==1){
            r = 2;  //ration of the paddle length
            p = paddle/10;  //paddle speed
            t = 2;  //time ratio
        }
        if(aiLevel==2){
            r = 1;  //ration of the paddle length
            p = paddle/20;  //paddle speed
            t = 3;  //time ratio
        }
        if(aiLevel==3) {
            r = 1;  //ration of the paddle length
            p = paddle / 25;  //paddle speed
            t = 4;  //time ratio
        }
        for (int i = 0; i < ballX.length; i++) {
            ballX[i] = boardX / 2 - 7;            //has to be changed by ujjwal
            ballY[i] = boardY / 2 - 7;            //has to be changed by ujjwal
            ballDeltaX[i] = 0;//BallDeltaXArray[i] ;
            ballDeltaY[i] = 0;//BallDeltaYArray[i] ;

        }

        //listen to key presses
        setFocusable(true);
        addKeyListener(this);

        //call step() 60 fps
        //increasing the value makes the game slower
        //reducing the value makes the game go faster
        //startTimer(startRate);
        timer = new Timer(startRate, this);
        timer.start();
        clientsocket = new DatagramSocket(local_port_number);
        clientsocket.setSoTimeout(1000);
        String info = playerTwoY + "-" + playerTwoHit + "-" + playerTwoMiss + "-" + playerTwoScore + "-" + "2" + "-" + playerTwoP + "-";


        for (int i = 0; i < second_ip.length; i++) {
            byte[] senddata = info.getBytes();
            DatagramPacket sendpack = new DatagramPacket(senddata, senddata.length, second_ip[i], second_port[i]);
            clientsocket.send(sendpack);
            //   System.out.println("sending to" + second_port[i]);
        }
        //byte[] senddata = info.getBytes();
        //DatagramPacket sendpack = new DatagramPacket(senddata, senddata.length, second_ip[0], second_port[0]);
        //clientsocket.send(sendpack);

    }

    public void actionPerformed(ActionEvent e) {
        try {
            step();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        speed_increase_time ++;

        if(speed_increase_time> 10000/startRate){
            speed_increase_time = 0;
            if (startRate>=10) {
                timer.stop();
                startRate --;
                timer = new Timer(startRate, this);
                timer.start();
            }
        }
        //will control the reaction rate of the computer player
        track2 = track2 + 1;
        if (track2 == t) {
            track2 = 0;
            if(!isPlayerOneActive){movePlayerOne();}
            if(!isPlayerTwoActive){movePlayerTwo();}
            if(!isPlayerThreeActive){movePlayerThree();}
            if(!isPlayerFourActive){movePlayerFour();};
        }
    }

    public void step() throws IOException {


        int a = 0;
        int b = 0;
        int c = 0;

        int x=0,y=0,z=0;
        if(!connection1){x=1;}
        if (!connection4){y=1;}
        if (!connection3){z=1;}

        System.out.println(second_ip.length-x-y-z);
        if (second_ip.length-x-y-z>0) {
            for (int j = 0; j < (second_ip.length - x - y - z); j++) {
                try {
                    if (connection1 == true || connection3 == true || connection4 == true) {
                        DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
                        clientsocket.receive(receivePacket);
                        String response = new String(receivePacket.getData());
                        System.out.println("REC: " + new String(receivePacket.getData()));
                        String[] splitResponse = response.split("-");
                        if (Integer.parseInt(splitResponse[4]) == 1) {
                            playerOneY = Integer.parseInt(splitResponse[0]);
                            playerOneHit = Integer.parseInt(splitResponse[1]);
                            playerOneMiss = Integer.parseInt(splitResponse[2]);
                            playerOneScore = Integer.parseInt(splitResponse[3]);
                            playerOneP = Integer.parseInt(splitResponse[5]);
                            a = 1;
                        } else if (Integer.parseInt(splitResponse[4]) == 3) {
                            playerThreeX = Integer.parseInt(splitResponse[0]);
                            playerThreeHit = Integer.parseInt(splitResponse[1]);
                            playerThreeMiss = Integer.parseInt(splitResponse[2]);
                            playerThreeScore = Integer.parseInt(splitResponse[3]);
                            playerThreeP = Integer.parseInt(splitResponse[5]);
                            b = 1;
                        } else if (Integer.parseInt(splitResponse[4]) == 4) {
                            playerFourX = Integer.parseInt(splitResponse[0]);
                            playerFourHit = Integer.parseInt(splitResponse[1]);
                            playerFourMiss = Integer.parseInt(splitResponse[2]);
                            playerFourScore = Integer.parseInt(splitResponse[3]);
                            playerFourP = Integer.parseInt(splitResponse[5]);
                            c = 1;
                        }
                    }


                    //  System.out.println("REC: " + new String(receivePacket.getData()));

                } catch (Exception e) {
                    System.out.println("SERVER TIMED OUT");

                    if (a == 0) {
                        doit1++;
                        if(doit1>9) {
                            if(connection1==true){
                                connection1 = false;
                                System.out.println("connection 1 gone");
                                doit4=0;
                                doit3=0;}
                        }
                    }
                    else if (a==1){
                        connection1=true;
                    }
                    if(second_ip.length>1) {
                        if (b == 0) {
                            doit3++;
                            if(doit3>9) {
                                if(connection3==true){
                                    connection3 = false;
                                    System.out.println("connection 3 gone");
                                    doit1=0;
                                    doit4=0;}
                            }
                        }
                        else if (b==1){
                            connection3=true;
                        }
                    }
                    if (second_ip.length>2) {
                        if (c == 0) {
                            doit4++;
                            if(doit4>9) {
                                if(connection4==true){
                                    connection4 = false;
                                    System.out.println("connection 4 gone");
                                    doit3=0;
                                    doit1=0;}
                            }
                        }
                        else if (c==1){
                            connection4=true;
                        }
                    }

                }
            }
        }

        if (a * b * c == 0) {
            if (!connection1) {
                isPlayerOneActive= false;
                //playerOneHit = 0;
                //playerOneMiss = 0;
                //  playerOneScore = 0;
            }

            if (!connection3) {
                if (second_ip.length > 1) {
                    isPlayerThreeActive = false;
                    // playerThreeHit = 0;
                    //playerThreeMiss = 0;}
                    //  playerThreeScore = 0;
                }
            }

            if (!connection4) {
                if(second_ip.length>2) {
                    isPlayerFourActive = false;
                  //  playerFourHit = 0;
                    //playerFourMiss = 0;
                    // playerFourScore = 0;
                }

            }

        }

        if(playerOneP>=limit){isPlayerOneActive=true;}
        if(playerThreeP>=limit){isPlayerThreeActive=true;}
        if(playerFourP>=limit){isPlayerFourActive=true;}


        if (time == 0) {
            ballDeltaX[0] = BallDeltaXArray[0];
            ballDeltaY[0] = BallDeltaYArray[0];
        }
        if ((time == 50) & (n >= 2)) {
            ballDeltaX[1] = BallDeltaXArray[1];
            ballDeltaY[1] = BallDeltaYArray[1];
        }
        if ((time == 100) & (n >= 3)) {
            ballDeltaX[2] = BallDeltaXArray[2];
            ballDeltaY[2] = BallDeltaYArray[2];
        }
        if ((time == 150) & (n >= 4)) {
            ballDeltaX[3] = BallDeltaXArray[3];
            ballDeltaY[3] = BallDeltaYArray[3];
        }
        if (time <= 200) {
            time++;
        }

        //<editor-fold desc="managing the variable for miss and hit">
        if (playerTwoHit > 0) {playerTwoHit--;}
        if (playerTwoMiss > 0) {playerTwoMiss--;}
        if(!isPlayerFourActive){
            if (playerFourHit > 0) { playerFourHit--;}
            if (playerFourMiss > 0) {playerFourMiss--;}
        }
        if(!isPlayerOneActive){
            if (playerOneHit > 0) { playerOneHit--;}
            if (playerOneMiss > 0) {playerOneMiss--;}
        }
        if(!isPlayerThreeActive){
            if(playerThreeHit>0){playerThreeHit--;}
            if(playerThreeMiss>0){playerThreeMiss--;}
        }
        //</editor-fold>

        //where will the ball be after it moves?
        for (int i = 0; i < ballX.length; i++) {
            nextBallLeft[i] = ballX[i] + ballDeltaX[i];
            nextBallRight[i] = ballX[i] + diameter + ballDeltaX[i];
            nextBallTop[i] = ballY[i] + ballDeltaY[i];
            nextBallBottom[i] = ballY[i] + diameter + ballDeltaY[i];
            nextBallX[i] = (ballX[i] + ballX[i]) / 2;
            nextBallY[i] = (ballY[i] + ballY[i]) / 2;
        }
        if(playerTwoP<limit) {
            movePaddles();
        }

        if (time > 150 & n == 4) {
            ballCollision(4, 4);
        }
        if (time <= 150 & time > 100 & n == 3) {
            ballCollision(3, 3);
        }
        if (time <= 100 & time > 50 & n >= 2) {
            ballCollision(2, 2);
        }

        if (time > 100 & n == 2) {
            ballCollision(2, 2);
        }
        if (time > 150 & n == 3) {
            ballCollision(3, 3);
        }
        //<editor-fold desc="defining variables for paddle position for each player">
        int playerOneRight = playerOneX + playerOneWidth;
        int playerOneTop = playerOneY;
        int playerOneBottom = playerOneY + playerOneHeight;
        int playerTwoLeft = playerTwoX;
        int playerTwoTop = playerTwoY;
        int playerTwoBottom = playerTwoY + playerTwoHeight;
        int playerThreeTop = playerThreeY +playerThreeHeight;
        int playerThreeLeft = playerThreeX;
        int playerThreeRight = playerThreeX + playerThreeWidth;
        int playerFourBottom = playerFourY;
        int playerFourLeft = playerFourX;
        int playerFourRight = playerFourX + playerFourWidth;
        //</editor-fold>

        for (int i = 0; i < ballX.length; i++) {

            //will the ball go over the top
            if (nextBallTop[i] <= 35) {
                ballDeltaY[i] *= -1;
                if(isPlayerThreeActive==false) {
                    if (nextBallLeft[i] >= playerThreeRight || nextBallRight[i] <= playerThreeLeft) {
                        playerThreeP++;
                        playerThreeMiss = 5;
                    } else {
                        playerThreeHit = 10;
                        playerThreeScore++;
                    }
                }
            }

            //will the ball go below the bottom
            if (nextBallBottom[i] >= (boardY - 35)) {
                ballDeltaY[i] *= -1;
                if(isPlayerFourActive==false){
                    if (nextBallLeft[i] >= playerFourRight || nextBallRight[i] <= playerFourLeft) {
                        playerFourP++;
                        playerFourMiss = 5;
                    } else {
                        playerFourHit = 10;
                        playerFourScore++;
                    }
                }
            }

            //will the ball go off the left side?
            if (nextBallLeft[i] <= 35) {
                ballDeltaX[i] *= -1;
                if(isPlayerOneActive==false){
                    if (nextBallTop[i] >= playerOneBottom || nextBallBottom[i] <= playerOneTop) {
                        playerOneP++;
                        playerOneMiss = 5;
                    } else {
                        playerOneHit = 10;
                        playerOneScore++;
                    }
                }
            }

            //will the ball go off the right side?
            if (nextBallRight[i] >= playerTwoLeft) {
                //is it going to miss the paddle?
                ballDeltaX[i] *= -1;
                if(playerTwoP<limit) {
                    if (nextBallTop[i] >= playerTwoBottom || nextBallBottom[i] <= playerTwoTop) {
                        playerTwoP++;
                        playerTwoMiss = 5;
                    } else {
                        playerTwoHit = 10;
                        playerTwoScore++;
                    }
                }
            }

            //move the ball
            ballX[i] += ballDeltaX[i];
            ballY[i] += ballDeltaY[i];

        }

        String information = playerTwoY + "-" + playerTwoHit + "-" + playerTwoMiss + "-" + playerTwoScore + "-" + "2" + "-" + playerTwoP + "-";

        for (int i = 0; i < second_ip.length; i++) {
            byte[] senddata = information.getBytes();
            DatagramPacket sendpack = new DatagramPacket(senddata, senddata.length, second_ip[i], second_port[i]);
            clientsocket.send(sendpack);
            // System.out.println("sending again to" + second_port[i]);
        }

        if (won2+won1+won3+won4==1)
        {
            for(int i = 0 ; i<ballDeltaX.length ; i++){
                ballDeltaX[i] = 0;}

            for(int i = 0 ; i<ballDeltaX.length ; i++){
                ballDeltaY[i] = 0;}
        }


        repaint();
    }

    //to move the paddles of each player
    public void movePaddles() {

        //move player 2
        if (upPressed) {
            if (playerTwoY - paddleSpeed >= 35) {
                playerTwoY -= paddleSpeed;
            }
        }
        if (downPressed) {
            if (playerTwoY + paddleSpeed + playerTwoHeight <= (boardY - 35)) {
                playerTwoY += paddleSpeed;
            }
        }
    }

    //paint the game screen
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.WHITE);

        int playerOneRight = playerOneX + playerOneWidth;
        int playerTwoLeft = playerTwoX;

        int playerThreeDown = playerThreeY + playerThreeHeight;
        int playerFourUp = playerFourY;

        //draw dashed line down center
        for (int lineY = 35; lineY <= boardY - 45; lineY += 10) {
            g.drawLine(boardX / 2, lineY, boardX / 2, lineY + 5);
        }
        for (int lineX = 35; lineX <= boardX - 45; lineX += 10) {
            g.drawLine(lineX, boardY / 2, lineX + 5, boardY / 2);
        }

        //draw the ball
        g.setColor(Color.BLUE);
        for (int i = 0; i < ballX.length; i++) {
            g.fillOval(ballX[i], ballY[i], diameter, diameter);
        }

        g.setColor(Color.white);


        //<editor-fold desc="dynaically draw colored line">
        if (playerOneMiss > 0) {
            g.setColor(Color.RED);
            g.drawLine(playerOneRight, 0, playerOneRight, getHeight());
            g.setColor(Color.white);
        } else if (playerOneMiss == 0) {
            g.setColor(Color.white);
            g.drawLine(playerOneRight, 0, playerOneRight, getHeight());
        }

        if (playerTwoMiss > 0) {
            g.setColor(Color.RED);
            g.drawLine(playerTwoLeft, 0, playerTwoLeft, getHeight());
            g.setColor(Color.white);
        } else if (playerTwoMiss == 0) {
            g.setColor(Color.white);
            g.drawLine(playerTwoLeft, 0, playerTwoLeft, getHeight());
        }

        if (playerThreeMiss > 0) {
            g.setColor(Color.RED);
            g.drawLine(0, playerThreeDown, getWidth(), playerThreeDown);
            g.setColor(Color.white);
        } else if (playerThreeMiss == 0) {
            g.setColor(Color.white);
            g.drawLine(0, playerThreeDown, getWidth(), playerThreeDown);
        }

        if (playerFourMiss > 0) {
            g.setColor(Color.RED);
            g.drawLine(0, playerFourUp, getWidth(), playerFourUp);
            g.setColor(Color.white);
        } else if (playerFourMiss == 0) {
            g.setColor(Color.white);
            g.drawLine(0, playerFourUp, getWidth(), playerFourUp);
        }
        //</editor-fold>

        //draw the scores

        g.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        g.drawString("SCORE ", boardX / 2 + 2, boardY / 2 - 25);
        g.drawString("P1: " + String.valueOf(playerOneScore), boardX / 2 - 100, boardY / 2 - 5);
         if(no_of_players>=2){g.drawString("P2: " + String.valueOf(playerTwoScore), boardX / 2 - 50, boardY / 2 - 5);}
         if(no_of_players>=3){g.drawString("P3: " + String.valueOf(playerThreeScore), boardX / 2 + 10, boardY / 2 - 5);}
         if(no_of_players==4){g.drawString("P4: " + String.valueOf(playerFourScore), boardX / 2 + 60, boardY / 2 - 5);}

        g.drawString("FOULS ", boardX / 2 + 2, boardY / 2 + 50);
        g.drawString("P1: " + String.valueOf(playerOneP), boardX / 2 - 100, boardY / 2 + 25);
        if(no_of_players>=2){g.drawString("P2: " + String.valueOf(playerTwoP), boardX / 2 - 50, boardY / 2 + 25);}
        if(no_of_players>=3){g.drawString("P3: " + String.valueOf(playerThreeP), boardX / 2 + 10, boardY / 2 + 25);}
        if(no_of_players==4){g.drawString("P4: " + String.valueOf(playerFourP), boardX / 2 + 60, boardY / 2 + 25);}


        if(doit1>8 && doit1<10 )
        {g.drawString("PLAYER 1 DISCONNECTS",100,50);}
        if(doit3>8 && doit3<10 )
        {g.drawString("PLAYER 3 DISCONNECTS",100,50);}
        if(doit4>8 && doit4<10 )
        {g.drawString("PLAYER 4 DISCONNECTS",100,50);}


        if (playerTwoP>=limit)
        {
            g.drawString("You LOST",boardX/2 + 25,boardY/2 -50);
        }


            if (no_of_players == 2) {
                if (playerOneP >= limit) {
                    g.drawString("YOU WON", boardX/2 -25, boardY - 50);
                    won2=1;
                }
                else if(playerTwoP >=limit){
                    g.drawString("PLAYER 1 WON", boardX/2 - 25, boardY - 50);
                    won1=1;
                }
            }
            else if (no_of_players==3)
            {
                if((playerOneP>=limit) && (playerThreeP>=limit))
                {
                    g.drawString("YOU WON",boardX/2 -25, boardY - 50);
                    won2=1;
                }
                if((playerOneP>=limit) && (playerTwoP>=limit))
                {
                    g.drawString("PLAYER 3 WON",boardX/2 - 25, boardY - 50);
                    won3=1;
                }
                if((playerTwoP>=limit) && (playerThreeP>=limit))
                {
                    g.drawString("PLAYER 1 WON",boardX/2 - 25, boardY - 50);
                    won1=1;
                }
            }
            else
            {
                if((playerOneP>=limit) && (playerThreeP>=limit) &&(playerFourP>=limit))
                {
                    g.drawString("YOU WON",boardX/2 -25, boardY - 50);
                    won2=1;
                }
                if((playerTwoP>=limit) && (playerThreeP>=limit) &&(playerFourP>=limit))
                {
                    g.drawString("PLAYER 1 WON",boardX/2 - 25, boardY - 50);
                    won1=1;
                }
                if((playerTwoP>=limit) && (playerOneP>=limit) &&(playerFourP>=limit))
                {
                    g.drawString("PLAYER 3 WON",boardX/2 - 25, boardY - 50);
                    won3=1;
                }
                if((playerTwoP>=limit) && (playerThreeP>=limit) &&(playerOneP>=limit))
                {
                    g.drawString("PLAYER 4 WON",boardX/2 - 25, boardY - 50);
                    won4=1;
                }



            }







        //<editor-fold desc="draw coloer paddles">
        //draw the paddles
        g.setColor(Color.white);
        if(no_of_players>=1) {
            if (playerOneHit > 0) {
                g.setColor(Color.GREEN);
                g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
                g.setColor(Color.white);
            } else if (playerOneHit == 0) {
                g.setColor(Color.white);
                g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
            }
        }
        if(no_of_players>=2) {
            if (playerTwoHit > 0) {
                g.setColor(Color.GREEN);
                g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
                g.setColor(Color.white);
            } else if (playerTwoHit == 0) {
                g.setColor(Color.white);
                g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
            }
        }
        if(no_of_players>=3) {
            if (playerThreeHit > 0) {
                g.setColor(Color.GREEN);
                g.fillRect(playerThreeX, playerThreeY, playerThreeWidth, playerThreeHeight);
                g.setColor(Color.white);
            } else if (playerThreeHit == 0) {
                g.setColor(Color.white);
                g.fillRect(playerThreeX, playerThreeY, playerThreeWidth, playerThreeHeight);
            }
        }
        if(no_of_players>=4) {
            if (playerFourHit > 0) {
                g.setColor(Color.GREEN);
                g.fillRect(playerFourX, playerFourY, playerFourWidth, playerFourHeight);
                g.setColor(Color.white);
            } else if (playerFourHit == 0) {
                g.setColor(Color.white);
                g.fillRect(playerFourX, playerFourY, playerFourWidth, playerFourHeight);
            }
        }
        //</editor-fold>

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
    }

    //generate random number between maximum and minimum
    public int random(int min, int max) {

        int i = min + (int) (Math.random() * (max - min + 1));
        while (i == 0) {
            i = min + (int) (Math.random() * (max - min + 1));
        }
        return (int) i;
    }

    //checks for collisions and handles the event
    public void ballCollision(int I, int J) {
        for (int i = 0; i < I; i++) {

            for (int j = i + 1; j < J; j++) {

                int distance = (int) Math.sqrt((nextBallX[i] - nextBallX[j]) * (nextBallX[i] - nextBallX[j]) + (nextBallY[i] - nextBallY[j]) * (nextBallY[i] - nextBallY[j]));
                if (distance < diameter) {
                    int temp;
                    temp = ballDeltaX[i];
                    ballDeltaX[i] = ballDeltaX[j];
                    ballDeltaX[j] = temp;
                    temp = ballDeltaY[i];
                    ballDeltaY[i] = ballDeltaY[j];
                    ballDeltaY[j] = temp;
                }
            }
        }
    }
    //AI for playerOne
    public void movePlayerOne(){

        int paddleSpeed = p;                                //ability of the computer player depends on the paddle speed of the computer player
        int playerMiddleY = playerOneY+playerOneHeight/2;   //middle of the paddle of player one
        //ball moving in opposite direction and will not hit the goal of player one

        int  i = findMinXIndex();

        if ((playerMiddleY - ballY[i] <= -(paddle / (2 * r))) || (2 < 0)) {
            if (playerOneY + paddleSpeed + playerOneHeight <= (boardY - 35)) {
                playerOneY += paddleSpeed;
            }
        }
        if ((playerMiddleY - ballY[i] >= (paddle / (2 * r))) || (2 < 0)) {
            if (playerOneY - paddleSpeed >= 35) {
                playerOneY -= paddleSpeed;
            }
        }
    }

    //AI for playerTwo
    public void movePlayerTwo(){

        int paddleSpeed = p;                                //ability of the computer player depends on the paddle speed of the computer player
        int playerMiddleY = playerTwoY+playerTwoHeight/2;   //middle of the paddle of player one
        //ball moving in opposite direction and will not hit the goal of player one

        int  i = findMaxXIndex();

        if ((playerMiddleY - ballY[i] <= -(paddle / (2 * r))) ) {
            if (playerTwoY + paddleSpeed + playerTwoHeight <= (boardY - 35)) {
                playerTwoY += paddleSpeed;
            }
        }
        if ((playerMiddleY - ballY[i] >= (paddle / (2 * r)))) {
            if (playerTwoY - paddleSpeed >= 35) {
                playerTwoY -= paddleSpeed;
            }
        }
    }

    //AI for playerThree
    public void movePlayerThree(){

        int paddleSpeed = p;                                //ability of the computer player depends on the paddle speed of the computer player
        int playerMiddleX = playerThreeX+playerThreeWidth/2;   //middle of the paddle of player one
        //ball moving in opposite direction and will not hit the goal of player one

        int  i = findMinYIndex();

        if ((ballX[i] - playerMiddleX >= (paddle / (2 * r))) ) {
            if (playerThreeX + paddleSpeed + playerThreeWidth <= (boardX - 35)) {
                playerThreeX += paddleSpeed;
            }
        }
        if ((playerMiddleX - ballX[i] >= (paddle / (2 * r))) ) {
            if (playerThreeX - paddleSpeed >= 35) {
                playerThreeX-= paddleSpeed;
            }
        }
    }

    //AI for playerFour
    public void movePlayerFour(){

        int paddleSpeed = p;                                //ability of the computer player depends on the paddle speed of the computer player
        int playerMiddleX = playerFourX+playerFourWidth/2;   //middle of the paddle of player one
        //ball moving in opposite direction and will not hit the goal of player one

        int  i = findMaxYIndex();

        if ((ballX[i] - playerMiddleX >= (paddle / (2 * r))) ) {
            if (playerFourX + paddleSpeed + playerFourWidth <= (boardX - 35)) {
                playerFourX += paddleSpeed;
            }
        }
        if ((playerMiddleX - ballX[i] >= (paddle / (2 * r))) ) {
            if (playerFourX - paddleSpeed >= 35) {
                playerFourX-= paddleSpeed;
            }
        }
    }
    //find index of minimum value of ballX according to ballDeltaX
    //gives the index of the ball that the player one has to hit
    public int findMinXIndex(){
        int[] arr = ballX;
        int index = -1;
        int value = 10000;
        for(int i = 0 ; i<arr.length ; i++){
            if(ballDeltaX[i] <0 & value >= arr[i]){
                index = i;
                value = arr[i];
            }
        }
        if(index == -1) {
            index = 0;
            value = arr[0];
            for(int i = 0 ; i<arr.length ; i++){
                if(value <= arr[i]){
                    index = i;
                    value = arr[i];
                }
            }
        }
        System.out.println(index);
        return index;
    }


    //find index of max value of ballX according to ballDeltaX
    //gives the index of the ball that the player two has to hit
    public int findMaxXIndex(){
        int[] arr = ballX;
        int index = -1;
        int value = -1;
        for(int i = 0 ; i<arr.length ; i++){
            if(ballDeltaX[i] >0 & value <= arr[i] ){
                index = i;
                value = arr[i];
            }
        }
        if(index == -1) {
            index = 0;
            value = arr[0];
            for(int i = 0 ; i<arr.length ; i++){
                if(value >= arr[i]){
                    index = i;
                    value = arr[i];
                }
            }
        }
        System.out.println(index);
        return index;
    }

    //find index of minimum value of ballY according to ballDeltaY
    //gives the index of ball that player 3 has to hit
    public int findMinYIndex(){
        int[] arr = ballY;
        int index = -1;
        int value = 1000;
        for(int i = 0 ; i<arr.length ; i++){
            if(ballDeltaY[i] <0  & value>arr[i]){
                index = i;
                value = arr[i];
            }
        }
        if(index == -1) {
            index = 0;
            value = arr[0];
            for(int i = 0 ; i<arr.length ; i++){
                if(value > arr[i]){
                    index = i;
                    value = arr[i];
                }
            }
        }
        System.out.println(index);
        return index;
    }


    //find index of minimum value of ballY according to ballDeltaY
    //gives the index of ball that player 3 has to hit
    public int findMaxYIndex(){
        int[] arr = ballY;
        int index = -1;
        int value = 1000;
        for(int i = 0 ; i<arr.length ; i++){
            if(ballDeltaY[i] >0  & value<arr[i]){
                index = i;
                value = arr[i];
            }
        }
        if(index == -1) {
            index = 0;
            value = arr[0];
            for(int i = 0 ; i<arr.length ; i++){
                if(value < arr[i]){
                    index = i;
                    value = arr[i];
                }
            }
        }
        System.out.println(index);
        return index;
    }
}
