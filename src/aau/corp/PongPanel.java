package aau.corp;
//reference: http://staticvoidgames.com

/**
 * Created by Ujjawal Gupta on 03-Apr-16.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PongPanel extends JPanel implements ActionListener, KeyListener{

    int startRate = 10;        //frame refreshes after every 100 millisecond
    Timer timer ;
    int track2 = 0;
    int boardX = 700;
    int boardY = 700;
    int paddle = 100;
    private int paddleSpeed = 5;

    int n = 2;  //number of balls
    int r = 5;  //ration of the paddle length
    int p = 5;  //paddle speed
    int t = 1;  //time ratio

    //<editor-fold desc="boolean variable for keyPressed">
    private boolean upPressed = false;
    private boolean downPressed = false;
    private boolean wPressed = false;
    private boolean sPressed = false;
    private boolean cPressed = false;
    private boolean vPressed = false;
    private boolean nPressed = false;
    private boolean mPressed = false;
    //</editor-fold>

    //y direction --> down +ve
    // y = 0 --> top point
    //y direction ---> up   -ve
    //y =500 ---> bottom most point

    private int[] ballX = new int[n];
    private int[] ballY = new int[n];

    private int diameter = 15;

    //steps that ball move

    private int ballDeltaX[] = new int[n];
    private int ballDeltaY[] = new int[n];

    int[] nextBallLeft      =   new int[n];
    int[] nextBallRight     =   new int[n];
    int[] nextBallTop       =   new int[n];
    int[] nextBallBottom    =   new int[n];
    int[] nextBallX         =   new int[n];
    int[] nextBallY         =   new int[n];

    //<editor-fold desc="variable for paddle position of each player ">
    //payer paddles initial position
    //at left
    private int playerOneX = 25;
    private int playerOneY = boardY/2 - paddle/2;
    private int playerOneWidth = 10;
    private int playerOneHeight = paddle;

    //at right
    private int playerTwoX = boardX - 25-10;
    private int playerTwoY = boardY/2 - paddle/2;
    private int playerTwoWidth = 10;
    private int playerTwoHeight = paddle;

    //at the top
    private int playerThreeX = boardY/2 -paddle/2;
    private int playerThreeY = 25;
    private int playerThreeWidth = paddle;
    private int playerThreeHeight = 10;

    //at bottom
    private int playerFourX = boardY/2 -paddle/2;
    private int playerFourY = boardY - 25 -10;
    private int playerFourWidth = paddle;
    private int playerFourHeight = 10;
    //</editor-fold>

    //<editor-fold desc="score of each player">
    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private int playerThreeScore = 0;
    private int playerFourScore = 0;
    //</editor-fold>

    //<editor-fold desc="variables for miss and hit">
    int playerOneHit    =0;
    int playerTwoHit    =0;
    int playerThreeHit  =0;
    int playerFourHit   =0;
    int playerOneMiss    =0;
    int playerTwoMiss    =0;
    int playerThreeMiss  =0;
    int playerFourMiss   =0;
    //</editor-fold>

    //construct a PongPanel
    public PongPanel(){
        setBackground(Color.BLACK);

        for(int i = 0 ; i<ballX.length ; i++) {
            ballX[i] = random(boardX / 2 - 50, boardX / 2 + 50);
            ballY[i] = random(boardY / 2 - 50, boardY / 2 + 50);
            ballDeltaX[i] = random(-3, 3);
            ballDeltaY[i] = random(-3, 3);
        }

        //listen to key presses
        setFocusable(true);
        addKeyListener(this);

        //call step() 60 fps
        //increasing the value makes the game slower
        //reducing the value makes the game go faster
  //      startTimer(startRate);
        timer = new Timer(startRate, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e){
        step();

        //will control the reaction rate of the computer player
        track2 = track2 +1;
        if(track2==t){
            track2 =0;
            //move the paddle of player one automatically
            movePlayerOne();
            movePlayerThree();
            movePlayerTwo();
        }
    }

    public void step(){

        //<editor-fold desc="managing the variable for miss and hit">
        if(playerOneHit<0){playerOneHit++;}
        if(playerTwoHit<0){playerTwoHit++;}
        if(playerThreeHit<0){playerThreeHit++;}
        if(playerFourHit<0){playerFourHit++;}
        if(playerOneMiss<0){playerOneMiss++;}
        if(playerTwoMiss<0){playerTwoMiss++;}
        if(playerThreeMiss<0){playerThreeMiss++;}
        if(playerFourMiss<0){playerFourMiss++;}
        //</editor-fold>

        //where will the ball be after it moves?
        for(int i =0 ; i<ballX.length ; i++) {
            nextBallLeft[i]      = ballX[i] + ballDeltaX[i];
            nextBallRight[i]     = ballX[i] + diameter + ballDeltaX[i];
            nextBallTop[i]       = ballY[i] + ballDeltaY[i];
            nextBallBottom[i]    = ballY[i] + diameter + ballDeltaY[i];
            nextBallX[i]         = (ballX[i] + ballX[i])/2;
            nextBallY[i]         = (ballY[i] + ballY[i])/2;
        }
        movePaddles();
        ballCollision();

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

        for(int i = 0 ; i<ballX.length ; i++) {

            //will the ball go over the top
            if (nextBallTop[i] <= 35) {
                if (nextBallLeft[i] >= playerThreeRight || nextBallRight[i] <= playerThreeLeft) {
                    ballDeltaY[i] *= -1;
                    playerThreeMiss= -5;
                } else {
                    playerThreeHit = -10;
                    playerThreeScore++;
                    ballDeltaY[i] *= -1;
                }
            }

            //will the ball go below the bottom
            if (nextBallBottom[i] >= (boardY - 35)) {
                if (nextBallLeft[i] >= playerFourRight || nextBallRight[i] <= playerFourLeft) {
                    ballDeltaY[i] *= -1;
                    playerFourMiss = -5;
                } else {
                    playerFourHit = -10;
                    playerFourScore++;
                    ballDeltaY[i] *= -1;
                }
            }

            //will the ball go off the left side?
            if (nextBallLeft[i] <= playerOneRight) {
                //is it going to miss the paddle?
                if (nextBallTop[i] >= playerOneBottom || nextBallBottom[i] <= playerOneTop) {
                    ballDeltaX[i] *= -1;
                    playerOneMiss = -5;
                } else {
                    playerOneHit =-10;
                    playerOneScore++;
                    ballDeltaX[i] *= -1;
                    if(nextBallX[i]>= playerOneY + paddle/3) {
                        ballDeltaY[i] = ballDeltaY[i]+1;
                    }
                    if(nextBallY[i] <= playerOneY -paddle/3){
                        ballDeltaY[i] = ballDeltaY[i]-1;
                    }

                    if(nextBallY[i] >= playerOneY -paddle/3 && nextBallX[i]<= playerOneY + paddle/3) {
                        ballDeltaY[i] = ballDeltaY[i];
                    }
                }
            }

            //will the ball go off the right side?
            if (nextBallRight[i] >= playerTwoLeft) {
                //is it going to miss the paddle?
                if (nextBallTop[i] >= playerTwoBottom || nextBallBottom[i] <= playerTwoTop) {
                    ballDeltaX[i] *= -1;
                    playerTwoMiss =-5;
                } else {
                    playerTwoHit=-10;
                    playerTwoScore++;
                    ballDeltaX[i] *= -1;

                    if(nextBallX[i]>= playerOneY + paddle/3) {
                        ballDeltaY[i] = ballDeltaY[i]+1;
                    }
                    if(nextBallY[i] <= playerOneY -paddle/3){
                        ballDeltaY[i] = ballDeltaY[i]-1;
                    }

                    if(nextBallY[i] >= playerOneY -paddle/3 && nextBallX[i]<= playerOneY + paddle/3) {
                        ballDeltaY[i] = ballDeltaY[i];
                    }
                }
            }



            //move the ball
            ballX[i] += ballDeltaX[i];
            ballY[i] += ballDeltaY[i];

        }
        //stuff has moved, tell this JPanel to repaint itself
        repaint();
    }

    //to move the paddles of each player
    public void movePaddles(){
        //move player 1
        if (wPressed) {
            if (playerOneY-paddleSpeed >= 35) {
                playerOneY -= paddleSpeed;
            }
        }
        if (sPressed) {
            if (playerOneY + paddleSpeed + playerOneHeight <= (boardY-35)) {
                playerOneY += paddleSpeed;
            }
        }

        //move player 2
        if (upPressed) {
            if (playerTwoY-paddleSpeed >= 35) {
                playerTwoY -= paddleSpeed;
            }
        }
        if (downPressed) {
            if (playerTwoY + paddleSpeed + playerTwoHeight <= (boardY-35)) {
                playerTwoY += paddleSpeed;
            }
        }

        //move player 3
        if (cPressed) {
            if (playerThreeX-paddleSpeed >= 35) {
                playerThreeX -= paddleSpeed;
            }
        }
        if (vPressed) {
            if (playerThreeX + paddleSpeed + playerThreeWidth <= (boardX-35)) {
                playerThreeX += paddleSpeed;
            }
        }
        //move player 4
        if (nPressed) {
            if (playerFourX-paddleSpeed >= 35) {
                playerFourX -= paddleSpeed;
            }
        }
        if (mPressed) {
            if (playerFourX + paddleSpeed + playerFourWidth <= (boardY-35)) {
                playerFourX += paddleSpeed;
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

    //paint the game screen
    public void paintComponent(Graphics g ){

        super.paintComponent(g);
        g.setColor(Color.WHITE);

        int playerOneRight = playerOneX + playerOneWidth;
        int playerTwoLeft =  playerTwoX;

        int playerThreeDown = playerThreeY + playerThreeHeight;
        int playerFourUp = playerFourY;

        //draw dashed line down center
        for (int lineY = 35; lineY <= boardY-45; lineY += 10) {
            g.drawLine(boardX/2, lineY, boardX/2, lineY+5);
        }
        for (int lineX = 35; lineX <= boardX-45; lineX += 10) {
            g.drawLine(lineX, boardY/2, lineX+5, boardY/2);
        }

        //draw the ball
        g.setColor(Color.BLUE);
        for(int i = 0 ; i<ballX.length ; i++) {
            g.fillOval(ballX[i], ballY[i], diameter, diameter);
        }

        g.setColor(Color.white);


        //<editor-fold desc="dynaically draw colored line">
        if(playerOneMiss < 0) {
            g.setColor(Color.RED);
            g.drawLine(playerOneRight, 0, playerOneRight, getHeight());
            g.setColor(Color.white);
        }else if(playerOneMiss >= 0) {
            g.setColor(Color.white);
            g.drawLine(playerOneRight, 0, playerOneRight, getHeight());
        }

        if(playerTwoMiss < 0) {
                g.setColor(Color.RED);
                g.drawLine(playerTwoLeft, 0, playerTwoLeft, getHeight());
                g.setColor(Color.white);
        }else if(playerTwoMiss >= 0){
            g.setColor(Color.white);
            g.drawLine(playerTwoLeft, 0, playerTwoLeft, getHeight());
        }

        if(playerThreeMiss < 0) {
            g.setColor(Color.RED);
            g.drawLine(0, playerThreeDown, getWidth(), playerThreeDown);
            g.setColor(Color.white);
        }else if(playerThreeMiss >= 0){
            g.setColor(Color.white);
            g.drawLine(0, playerThreeDown, getWidth(), playerThreeDown);
        }

        if(playerFourMiss < 0) {
            g.setColor(Color.RED);
            g.drawLine(0, playerFourUp, getWidth(), playerFourUp);
            g.setColor(Color.white);
        }else if(playerFourMiss >= 0){
            g.setColor(Color.white);
            g.drawLine(0, playerFourUp, getWidth(), playerFourUp);
        }
        //</editor-fold>

        //draw the scores
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        g.drawString("player 1: "+ String.valueOf(playerOneScore), 100, 100);
        g.drawString("player 2: "+String.valueOf(playerTwoScore), 300, 100);
        g.drawString("player 3: "+String.valueOf(playerThreeScore), 100, 150);
        g.drawString("player 4: "+String.valueOf(playerFourScore), 300, 150);


        //<editor-fold desc="draw coloer paddles">
        //draw the paddles
        g.setColor(Color.white);
        if(playerOneHit < 0) {
            g.setColor(Color.GREEN);
            g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
            g.setColor(Color.white);
        }else if(playerOneHit >= 0) {
            g.setColor(Color.white);
            g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
        }

        if(playerTwoHit < 0) {
            g.setColor(Color.GREEN);
            g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
            g.setColor(Color.white);
        }else if(playerTwoHit >= 0){
            g.setColor(Color.white);
            g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
        }

        if(playerThreeHit < 0) {
            g.setColor(Color.GREEN);
            g.fillRect(playerThreeX, playerThreeY, playerThreeWidth, playerThreeHeight);
            g.setColor(Color.white);
        }else if(playerThreeHit >= 0){
            g.setColor(Color.white);
            g.fillRect(playerThreeX, playerThreeY, playerThreeWidth, playerThreeHeight);
        }

        if(playerFourHit < 0) {
            g.setColor(Color.GREEN);
            g.fillRect(playerFourX, playerFourY, playerFourWidth, playerFourHeight);
            g.setColor(Color.white);
        }else if(playerFourHit >= 0){
            g.setColor(Color.white);
            g.fillRect(playerFourX, playerFourY, playerFourWidth, playerFourHeight);
        }
        //</editor-fold>

    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_W) {
            wPressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            sPressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_C) {
            cPressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_V) {
            vPressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_N) {
            nPressed = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_M) {
            mPressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_W) {
            wPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            sPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_C) {
            cPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_V) {
            vPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_N) {
            nPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_M) {
            mPressed = false;
        }

    }

    //generate random number between maximum and minimum
    public int random(int min, int max){

        int i = min +(int)(Math.random() * (max- min +1));
        while(i==0){
            i = min + (int)(Math.random() * (max- min +1));
        }
        return (int)i;
    }

    //find index of minimum value of ballX according to ballDeltaX
    //gives the index of the ball that the player one has to hit
    public int findMinXIndex(){
        int[] arr = ballX;
        int index = -1;
        int value = -1;
        for(int i = 1 ; i<arr.length ; i++){
            if(ballDeltaX[i] <0 ){
                if(index ==-1) {
                    index = i;
                    value = arr[i];
                }
                if(index!=-1 & value > arr[i]){
                    index = i;
                    value = arr[i];
                }
            }
        }
        if(index == -1) {
            index = 0;
            value = arr[0];
            for(int i = 1 ; i<arr.length ; i++){
                if(value < arr[i]){
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
        for(int i = 1 ; i<arr.length ; i++){
            if(ballDeltaX[i] >0 ){
                if(index ==-1) {
                    index = i;
                    value = arr[i];
                }
                if(index!=-1 & value < arr[i]){
                    index = i;
                    value = arr[i];
                }
            }
        }
        if(index == -1) {
            index = 0;
            value = arr[0];
            for(int i = 1 ; i<arr.length ; i++){
                if(value < arr[i]){
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
        int value = -1;
        for(int i = 1 ; i<arr.length ; i++){
            if(ballDeltaY[i] <0 ){
                if(index ==-1) {
                    index = i;
                    value = arr[i];
                }
                if(index!=-1 & value > arr[i]){
                    index = i;
                    value = arr[i];
                }
            }
        }
        if(index == -1) {
            index = 0;
            value = arr[0];
            for(int i = 1 ; i<arr.length ; i++){
                if(value > arr[i]){
                    index = i;
                    value = arr[i];
                }
            }
        }
        System.out.println(index);
        return index;
    }

    //checks for collisions and handles the event
    public void ballCollision(){
        for(int i = 0 ; i<ballX.length ; i++){

            for (int j = i+1; j < ballX.length; j++) {

                int distance = (int) Math.sqrt( (nextBallX[i] - nextBallX[j])*(nextBallX[i] - nextBallX[j]) + (nextBallY[i] - nextBallY[j])*(nextBallY[i] - nextBallY[j])) ;
                if(distance < diameter){
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
}