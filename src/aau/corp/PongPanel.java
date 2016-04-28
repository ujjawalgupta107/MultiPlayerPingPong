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

public class PongPanel extends JPanel implements ActionListener, KeyListener {


    int size; //= 300;
    Timer timer;
    int track2 = 0;
    int boardX;
    int boardY;
    int paddle;
    private int paddleSpeed;
    int time = 0;
    int limit = 5;
    int no_of_players =2 ;
    int n; //= 2;  //number of balls
    int r ;  //ration of the paddle length
    int p ;  //paddle speed
    int t ;  //time ratio
    int speed_increase_time =0;
    int startRate = 20;        //frame refreshes after every 100 millisecond


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

    int[] nextBallLeft;
    int[] nextBallRight;
    int[] nextBallTop;
    int[] nextBallBottom;
    int[] nextBallX;
    int[] nextBallY;

    //<editor-fold desc="variable for paddle position of each player ">
    //payer paddles initial position
    //at left
    private int playerOneX = 25;
    //this player's cordinate
    private int playerOneY = boardY / 2 - paddle / 2;
    private int playerOneWidth = 10;
    private int playerOneHeight = paddle;

    //at right
    private int playerTwoX = boardX - 25 - 10;
    private int playerTwoY = boardY / 2 - paddle / 2;
    private int playerTwoWidth = 10;
    private int playerTwoHeight = paddle;

    //at the top
    private int playerThreeX = boardY / 2 - paddle / 2;
    private int playerThreeY = 25;
    private int playerThreeWidth = paddle;
    private int playerThreeHeight = 10;

    //at bottom
    private int playerFourX = boardY / 2 - paddle / 2;
    private int playerFourY = boardY - 25 - 10;
    private int playerFourWidth = paddle;
    private int playerFourHeight = 10;
    //</editor-fold>

    //<editor-fold desc="score of each player">
    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private int playerThreeScore = 0;
    private int playerFourScore = 0;

    private int playerOneP = 0;
    private int playerTwoP = 0;
    private int playerThreeP = 0;
    private int playerFourP = 0;

    //</editor-fold>

    //<editor-fold desc="variables for miss and hit">
    int playerOneHit = 0;
    int playerTwoHit = 0;
    int playerThreeHit = 0;
    int playerFourHit = 0;
    int playerOneMiss = 0;
    int playerTwoMiss = 0;
    int playerThreeMiss = 0;
    int playerFourMiss = 0;
    //</editor-fold>

    //construct a PongPanel
    public PongPanel(int grid, int balls, int aiLevel) throws IOException {
        //<editor-fold desc="variables">
        setBackground(Color.BLACK);
        n = balls;
        size = grid;
        limit = 5;
        boardX = size;
        boardY = size;
        paddle = size / 6;
        paddleSpeed = paddle / 10;
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

        playerOneX = 25;
        playerOneY = boardY / 2 - paddle / 2;
        playerOneWidth = 10;
        playerOneHeight = paddle;
        playerTwoX = boardX - 25 - 10;
        playerTwoY = boardY / 2 - paddle / 2;
        playerTwoWidth = 10;
        playerTwoHeight = paddle;
        playerThreeX = boardY / 2 - paddle / 2;
        playerThreeY = 25;
        playerThreeWidth = paddle;
        playerThreeHeight = 10;
        playerFourX = boardY / 2 - paddle / 2;
        playerFourY = boardY - 25 - 10;
        playerFourWidth = paddle;
        playerFourHeight = 10;
        //</editor-fold>

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

        timer = new Timer(startRate, this);
        timer.start();
           }

    public void actionPerformed(ActionEvent e) {
       step();
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

    }

    private void step() {

        track2 ++;
        if (track2 == t) {
            track2 =0;

            if(playerTwoP<limit) {
                movePlayerTwo();
            }
        }



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
        if (playerOneHit > 0) {
            playerOneHit--;
        }
        if (playerOneMiss > 0) {
            playerOneMiss--;
        }
        if (playerTwoHit > 0) {
            playerTwoHit--;
        }
        if (playerTwoMiss > 0) {
            playerTwoMiss--;
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
        if(playerOneP<limit){
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
        int playerTwoTop = playerTwoY;
        int playerTwoBottom = playerTwoY + playerTwoHeight;

        //</editor-fold>

        for (int i = 0; i < n; i++) {

            //will the ball go over the top
            if (nextBallTop[i] <= 35) {
                ballDeltaY[i] *= -1;
            }

            //will the ball go below the bottom
            if (nextBallBottom[i] >= (boardY - 35)) {
                ballDeltaY[i] *= -1;
            }

            //will the ball go off the left side?
            if (nextBallLeft[i] <= playerOneRight) {
                //is it going to miss the paddle?
                ballDeltaX[i] *= -1;
                if(playerOneP<limit) {
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
            if (nextBallRight[i] >= boardX - 35) {
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


        repaint();
    }

    //to move the paddles of each player
    private void movePaddles() {
        //move player 1
        if (upPressed) {
            if (playerOneY - paddleSpeed >= 35) {
                playerOneY -= paddleSpeed;
            }
        }
        if (downPressed) {
            if (playerOneY + paddleSpeed + playerOneHeight <= (boardY - 35)) {
                playerOneY += paddleSpeed;
            }
        }
    }

    //paint the game screen
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        int playerOneRight = playerOneX + playerOneWidth;
        int playerTwoLeft = playerTwoX;

        int playerThreeDown = playerThreeY + playerThreeHeight;
        int playerFourUp = playerFourY;

        g.setColor(Color.WHITE);
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
        g.setColor(Color.WHITE);


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
        try {
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if (no_of_players >= 2) {
            if (playerTwoHit > 0) {
                g.setColor(Color.GREEN);
                g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
                g.setColor(Color.white);
            } else if (playerTwoHit == 0) {
                g.setColor(Color.white);
                g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
            }
        }
        if (no_of_players >= 3) {

            if (playerThreeHit > 0) {
                g.setColor(Color.GREEN);
                g.fillRect(playerThreeX, playerThreeY, playerThreeWidth, playerThreeHeight);
                g.setColor(Color.white);
            } else if (playerThreeHit == 0) {
                g.setColor(Color.white);
                g.fillRect(playerThreeX, playerThreeY, playerThreeWidth, playerThreeHeight);
            }
        }
        if(no_of_players==4) {
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
    private void ballCollision(int I, int J) {
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
    public void movePlayerOne() {

        int paddleSpeed = p;                                //ability of the computer player depends on the paddle speed of the computer player
        int playerMiddleY = playerOneY + playerOneHeight / 2;   //middle of the paddle of player one
        //ball moving in opposite direction and will not hit the goal of player one

        int i = findMinXIndex();

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
    public void movePlayerTwo() {

        int paddleSpeed = p;                                //ability of the computer player depends on the paddle speed of the computer player
        int playerMiddleY = playerTwoY + playerTwoHeight / 2;   //middle of the paddle of player one
        //ball moving in opposite direction and will not hit the goal of player one

        int i = findMaxXIndex();

        if ((playerMiddleY - ballY[i] <= -(paddle / (2 * r)))) {
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
    public void movePlayerThree() {

        int paddleSpeed = p;                                //ability of the computer player depends on the paddle speed of the computer player
        int playerMiddleX = playerThreeX + playerThreeWidth / 2;   //middle of the paddle of player one
        //ball moving in opposite direction and will not hit the goal of player one

        int i = findMinYIndex();

        if ((ballX[i] - playerMiddleX >= (paddle / (2 * r)))) {
            if (playerThreeX + paddleSpeed + playerThreeWidth <= (boardX - 35)) {
                playerThreeX += paddleSpeed;
            }
        }
        if ((playerMiddleX - ballX[i] >= (paddle / (2 * r)))) {
            if (playerThreeX - paddleSpeed >= 35) {
                playerThreeX -= paddleSpeed;
            }
        }
    }

    //AI for playerFour
    public void movePlayerFour() {

        int paddleSpeed = p;                                //ability of the computer player depends on the paddle speed of the computer player
        int playerMiddleX = playerFourX + playerFourWidth / 2;   //middle of the paddle of player one
        //ball moving in opposite direction and will not hit the goal of player one

        int i = findMaxYIndex();

        if ((ballX[i] - playerMiddleX >= (paddle / (2 * r)))) {
            if (playerFourX + paddleSpeed + playerFourWidth <= (boardX - 35)) {
                playerFourX += paddleSpeed;
            }
        }
        if ((playerMiddleX - ballX[i] >= (paddle / (2 * r)))) {
            if (playerFourX - paddleSpeed >= 35) {
                playerFourX -= paddleSpeed;
            }
        }
    }

    //find index of minimum value of ballX according to ballDeltaX
    //gives the index of the ball that the player one has to hit
    public int findMinXIndex() {
        int[] arr = ballX;
        int index = -1;
        int value = 10000;
        for (int i = 0; i < arr.length; i++) {
            if (ballDeltaX[i] < 0 & value >= arr[i]) {
                index = i;
                value = arr[i];
            }
        }
        if (index == -1) {
            index = 0;
            value = arr[0];
            for (int i = 0; i < arr.length; i++) {
                if (value <= arr[i]) {
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
    public int findMaxXIndex() {
        int[] arr = ballX;
        int index = -1;
        int value = -1;
        for (int i = 0; i < arr.length; i++) {
            if (ballDeltaX[i] > 0 & value <= arr[i]) {
                index = i;
                value = arr[i];
            }
        }
        if (index == -1) {
            index = 0;
            value = arr[0];
            for (int i = 0; i < arr.length; i++) {
                if (value >= arr[i]) {
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
    public int findMinYIndex() {
        int[] arr = ballY;
        int index = -1;
        int value = 1000;
        for (int i = 0; i < arr.length; i++) {
            if (ballDeltaY[i] < 0 & value > arr[i]) {
                index = i;
                value = arr[i];
            }
        }
        if (index == -1) {
            index = 0;
            value = arr[0];
            for (int i = 0; i < arr.length; i++) {
                if (value > arr[i]) {
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
    public int findMaxYIndex() {
        int[] arr = ballY;
        int index = -1;
        int value = 1000;
        for (int i = 0; i < arr.length; i++) {
            if (ballDeltaY[i] > 0 & value < arr[i]) {
                index = i;
                value = arr[i];
            }
        }
        if (index == -1) {
            index = 0;
            value = arr[0];
            for (int i = 0; i < arr.length; i++) {
                if (value < arr[i]) {
                    index = i;
                    value = arr[i];
                }
            }
        }
        System.out.println(index);
        return index;
    }
}