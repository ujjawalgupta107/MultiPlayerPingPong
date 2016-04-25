/*
//things to send
//paddleY
//score
//Hit
//Miss


package aau.corp;
//reference: http://staticvoidgames.com

*/
/**
 * Created by Ujjawal Gupta on 03-Apr-16.
 *//*

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PongPanelTest2 extends JPanel implements ActionListener, KeyListener{

    int startRate = 10;        //frame refreshes after every 100 millisecond
    Timer timer ;
    int track2 = 0;
    int boardX = 700;
    int boardY = 700;
    int paddle = 100;
    private int paddleSpeed = 5;
    int time = 0;

    int n = 4;  //number of balls
    int r = 1;  //ration of the paddle length
    int p = 5;  //paddle speed
    int t = 2;  //time ratio

    //<editor-fold desc="boolean variable for keyPressed">
    private boolean upPressed = false;
    private boolean downPressed = false;
    //</editor-fold>

    //y direction --> down +ve
    // y = 0 --> top point
    //y direction ---> up   -ve
    //y =500 ---> bottom most point

    private int[] ballX = new int[n];
    private int[] ballY = new int[n];

    private int diameter = 15;

    //steps that ball move

    private int ballDeltaX[] = new int[n];			//run of each machine
    private int ballDeltaY[] = new int[n];			//run of each machine

    int[] BallDeltaXArray = {-2,3,-1,-2};
    int[] BallDeltaYArray = {1,1,3,-2};

    int[] nextBallLeft      =   new int[n];
    int[] nextBallRight     =   new int[n];
    int[] nextBallTop       =   new int[n];
    int[] nextBallBottom    =   new int[n];
    int[] nextBallX         =   new int[n];
    int[] nextBallY         =   new int[n];

    */
/*//*
/<editor-fold desc="variable for paddle position of each player ">
    //payer paddles initial position
    //at left
    private int playerOneX = 25;
    private int playerOneY = boardY/2 - paddle/2 ;			///the value of the paddle position of other player
    private int playerOneWidth = 10;
    private int playerOneHeight = paddle;

    //at right
    private int playerTwoX = boardX - 25-10;
    private int playerTwoY = boardY/2 - paddle/2; 			
    private int playerTwoWidth = 10;
    private int playerTwoHeight = paddle;

    //at the top
    private int playerThreeX = boardY/2 -paddle/2;			///the value of the paddle position of other player
    private int playerThreeY = 25;
    private int playerThreeWidth = paddle;
    private int playerThreeHeight = 10;

    //at bottom
    private int playerFourX = boardY/2 -paddle/2;			///the value of the paddle position of other player
    private int playerFourY = boardY - 25 -10;
    private int playerFourWidth = paddle;
    private int playerFourHeight = 10;
    //</editor-fold>

    //<editor-fold desc="score of each player">
    private int playerOneScore = 0;
    private int playerTwoScore = 0;							///get hte value of score
    private int playerThreeScore = 0;
    private int playerFourScore = 0;
    //</editor-fold>

    //<editor-fold desc="variables for miss and hit">
    int playerOneHit    =0;						//value obtained
    int playerTwoHit    =0;
    int playerThreeHit  =0;//value obtained
    int playerFourHit   =0;//value obtained
    int playerOneMiss    =0;//value obtained
    int playerTwoMiss    =0;
    int playerThreeMiss  =0;//value obtained
    int playerFourMiss   =0;//value obtained
    //</editor-fold>
*//*

    //construct a PongPanel
    public PongPanelTest2(){
        setBackground(Color.BLACK);		

        for(int i = 0 ; i<ballX.length ; i++) {
            ballX[i] = boardX/2-7;			//has to be changed by ujjwal
            ballY[i] = boardY/2-7;			//has to be changed by ujjwal
            ballDeltaX[i] = 0 ;
            ballDeltaY[i] = 0 ;
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
    }

    public void actionPerformed(ActionEvent e){
        step();

        //will control the reaction rate of the computer player
        track2 = track2 +1;
        if(track2==t){
            track2 =0;
            //move the paddle of player one automatically
            //movePlayerOne();
        }
    }

    public void step(){


        if(time==0){
            ballDeltaX[0] = BallDeltaXArray[0];
            ballDeltaY[0] = BallDeltaYArray[0] ;
        }if((time==50) & (n>=2)){
            ballDeltaX[1] = BallDeltaXArray[1] ;
            ballDeltaY[1] = BallDeltaYArray[1] ;
        }if((time==100) & (n>=3)){
            ballDeltaX[2] = BallDeltaXArray[2] ;
            ballDeltaY[2] = BallDeltaYArray[2] ;
        }if((time==150) & (n>=4)){
            ballDeltaX[3] = BallDeltaXArray[3] ;
            ballDeltaY[3] = BallDeltaYArray[3] ;
        }

        if(time<=200){time++;}
	
        //<editor-fold desc="managing the variable for miss and hit">
        
        if(playerTwoHit<0){playerTwoHit++;}
        if(playerTwoMiss<0){playerTwoMiss++;}
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

        if(time>150 & n==4){
            ballCollision(3,3);
        }if(time<=150 & time>100 & n>=3){
            ballCollision(2,2);
        }if(time<=100 & time>50 & n>=2){
            ballCollision(1,1);
        }
        //<editor-fold desc="defining variables for paddle position for each player">
        
        int playerTwoLeft = playerTwoX;
        int playerTwoTop = playerTwoY;
        int playerTwoBottom = playerTwoY + playerTwoHeight;

        //</editor-fold>

        for(int i = 0 ; i<ballX.length ; i++) {
            //will the ball go over the top
            if (nextBallTop[i] <= 35) {
                    ballDeltaY[i] *= -1;
			}

            //will the ball go below the bottom
            if (nextBallBottom[i] >= (boardY - 35)) {
                    ballDeltaY[i] *= -1;
            }

            //will the ball go off the left side?
            if (nextBallLeft[i] <= 35) {
                    ballDeltaX[i] *= -1;
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
                }
            }

            //move the ball
            ballX[i] += ballDeltaX[i];
            ballY[i] += ballDeltaY[i];

        }
		
		//send paddle position(data) a array
		//playerTwoPaddleY = data[0];
		//scoe = data[1];
		
        //stuff has moved, tell this JPanel to repaint itself
        repaint();
    }

    //to move the paddles of each player
    public void movePaddles(){

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
    }

    //AI for playerOne
    public void movePlayerOne(){

        int paddleSpeed = p;                                //ability of the computer player depends on the paddle speed of the computer player
        int playerMiddleY = playerOneY+playerOneHeight/2;   //middle of the paddle of player one
        //ball moving in opposite direction and will not hit the goal of player one


        if ((playerMiddleY - ballY[1] <= -(paddle / (2 * r))) || (2 < 0)) {
            if (playerOneY + paddleSpeed + playerOneHeight <= (boardY - 35)) {
                playerOneY += paddleSpeed;
            }
        }
        if ((playerMiddleY - ballY[1] >= (paddle / (2 * r))) || (2 < 0)) {
            if (playerOneY - paddleSpeed >= 35) {
                playerOneY -= paddleSpeed;
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
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = false;
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

    //checks for collisions and handles the event
    public void ballCollision(int I ,int J){
        for(int i = 0 ; i<I ; i++){

            for (int j = i+1; j <J; j++) {

                int distance = (int) Math.sqrt( (nextBallX[i] - nextBallX[j])*(nextBallX[i] - nextBallX[j]) + (nextBallY[i] - nextBallY[j])*(nextBallY[i] - nextBallY[j])) ;
                if(distance < diameter ){
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
}*/
