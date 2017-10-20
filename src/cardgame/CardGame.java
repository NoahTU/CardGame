/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.Scanner;

/**
 *
 * @author Noah
 */
public class CardGame {

    public static Player playerOne = new Player();//initialize players
    public static Player playerTwo = new Player();
    public static Player playerThree = new Player();
    public static Player playerFour = new Player();
    public static Deck deck = new Deck(); //initialize deck
    public static int highest=0, highestPlayer=0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        int countOne=1, countTwo=0, countThree=0;
        int fake=0;
        int highest=0, highestPlayer=0, highestCheck=0;
        boolean win=false, wait=false;
        //variables to be used
        
        System.out.println("Welcome to the...");
        System.out.println(".------..------..------..------.     .------..------..------..------..------.\n" +
"|C.--. ||A.--. ||R.--. ||D.--. |.-.  |G.--. ||A.--. ||M.--. ||E.--. ||!.--. |\n" +
"| :/\\: || (\\/) || :(): || :/\\: ((5)) | :/\\: || (\\/) || (\\/) || (\\/) || (\\/) |\n" +
"| :\\/: || :\\/: || ()() || (__) |'-.-.| :\\/: || :\\/: || :\\/: || :\\/: || :\\/: |\n" +
"| '--'C|| '--'A|| '--'R|| '--'D| ((1)) '--'G|| '--'A|| '--'M|| '--'E|| '--'!|\n" +
"`------'`------'`------'`------'  '-'`------'`------'`------'`------'`------'");
        System.out.println("Please enter the number of Players that will be playing today.");
        Scanner scan = new Scanner(System.in);
        int players = scan.nextInt();
        
        if (players<2){ //always defaults to 2 players as the game wouldn't be a game without that
            players=2;
        }
        if (players>4){// higher number input message
            System.out.println("Game can only contain 4 players. 4 Players have been made.");
        }
        
        //wait interval mode
        System.out.println("Would you like to apply wait intervals to your game session? This may increase flow clarity. (yes or no)");
        String answer = scan.next();
        if (answer.equals("yes")){
            wait=true;
            System.out.println("Wait intervals are on.");
        }
        else if(answer.equals("no")){
            wait=false;
            System.out.println("Wait intervals are off.");
        }
        else{
            System.out.println("Invalid input. Wait intervals will remain off.");
        }
        
        if (wait){ //quick pause
                    Thread.sleep(1000);
        }
        
        
        
        //main game loop
        while (!win){
            countTwo=0;
            System.out.println("Round "+countOne+"! Begin!");
            deck.shuffle(); //deck is shuffled every round
            countTwo=1;//reset variables for new round
            highestPlayer=0;
            highest=0;
            highestCheck=0;
            
            while (countTwo<=players){ //loop for every player in game to draw card and store it in their respective player class
                System.out.println("Player "+countTwo+": Please draw a card! [press enter]");
                new Scanner(System.in).nextLine();//waits for enter input to draw card
                
                highest= theDraw(countTwo);
                if (highest>highestCheck){
                    highestPlayer=countTwo;
                    highestCheck=highest;
                }
                
                if (wait){
                    Thread.sleep(1000);
                }
                
                countTwo++;
                
            }
            
            System.out.println("Every player has drawn a card.");
            
            if (wait){
                    Thread.sleep(1000);
            }
            
            if(highestPlayer==0){ //in the rare case that all players get a penalty card
                System.out.println("No one won the round... everyone got penalty cards... what are the odds of that?");
            }
            else{
                System.out.println("Player "+highestPlayer+" wins round!");
            }
            
            win= winDispenser(highestPlayer);// give out 2 points to the round winner and check for a win
              
            if (wait){
                    Thread.sleep(1000);
            }
            
            countThree=0;
            System.out.println("Scores:");
            countThree=1;
            while (players>=countThree){ //display scores
                if (countThree==1){
                    System.out.println("Player 1: "+playerOne.getScore());
                }
                else if(countThree==2){
                    System.out.println("Player 2: "+playerTwo.getScore());
                }
                else if(countThree==3){
                    System.out.println("Player 3: "+playerThree.getScore());
                }
                else if(countThree==4){
                    System.out.println("Player 4: "+playerFour.getScore());
                }
                countThree++;
            }
            
            if (wait){
                    Thread.sleep(1000);
            }
            
            if (win){ //check for a win
                
                //win by 20 test
                //System.out.println("WIN CHECK");
                //playerFour.setScore(20);
                
                if(winCheck(playerOne.getScore(),playerTwo.getScore(),playerThree.getScore(),playerFour.getScore(), highestPlayer)){
                    System.out.println("PLAYER "+highestPlayer+" WINS!");
                }
                else{
                    win=false;
                }
            }    
            countOne++;
        } //end of main game loop
        System.out.println("Game Over"); //end game
    }
    
    
    
    public static boolean winDispenser(int player){ // win check function for each round
        boolean wins=false;
        if (player==1){
            playerOne.change(2);
            if (playerOne.winCheck()){
                wins=true;
            }
        }
        else if(player==2){
            playerTwo.change(2);
            if (playerTwo.winCheck()){
                wins=true;
            }
        }
        else if(player==3){
            playerThree.change(2);
            if (playerThree.winCheck()){
                wins=true;
            }
        }
        else if(player==4){
            playerFour.change(2);
            if (playerFour.winCheck()){
                wins=true;
            }
        }
        return wins;
    }
    
    
    public static int theDraw(int player){ //function to draw, display, and save card
        highest=0;
        if (player==1){ //player 1 handler
            playerOne.playerCard(deck.drawCard());
            System.out.println("Player 1's card: "+playerOne.getCard().toString());

            if (playerOne.getCard().getValue()==0){ //if penalty card, subtract point
                playerOne.change(-1);
            }
            highest=playerOne.getCard().getValue();
        }
                
        else if (player==2){// player 2 handler
            playerTwo.playerCard(deck.drawCard());
            System.out.println("Player 2's card: "+playerTwo.getCard().toString());

            if (playerTwo.getCard().getValue()==0){ //if penalty card, subtract point
                playerTwo.change(-1);
            }
            highest=playerTwo.getCard().getValue();
        }
        else if (player==3){ //player 3 handler
            playerThree.playerCard(deck.drawCard());
            System.out.println("Player 3's card: "+playerThree.getCard().toString());

            if (playerThree.getCard().getValue()==0){ //if penalty card, subtract point
                playerThree.change(-1);
            }
            highest=playerThree.getCard().getValue();
        }
        else if (player==4){ //player 4 handler
            playerFour.playerCard(deck.drawCard());
            System.out.println("Player 4's card: "+playerFour.getCard().toString());

            if (playerFour.getCard().getValue()==0){ //if penalty card, subtract point
                playerFour.change(-1);
            }
            highest=playerFour.getCard().getValue();          
        }
        
        return highest;
    }
    
    
    //function for handling check of winner. If the winner isn't at least 2 points over the second highest score, the function returns false and the game continues
    public static boolean winCheck(int p1, int p2, int p3, int p4, int high){
        int winningScore=0, countfour=1;
        int winby2=0;
        boolean check=true;
        switch (high) { //set winning score
            case 1:
                winningScore=p1;
                break;
            case 2:
                winningScore=p2;
                break;
            case 3:
                winningScore=p3;
                break;
            case 4:
                winningScore=p4;
                break;
            default:
                break;
        }
        
        while (countfour<=4){// check against each score and see if they won by 2
            switch (countfour){
            case 1:
                winby2=winningScore-p1;
                if(winby2<2&&winby2!=0){
                    check=false;
                }
                break;
            case 2:
                winby2=winningScore-p2;
                if(winby2<2&&winby2!=0){
                    check=false;
                }
                break;
            case 3:
                winby2=winningScore-p3;
                if(winby2<2&&winby2!=0){
                    check=false;
                }
                break;
            case 4:
                winby2=winningScore-p4;
                if(winby2<2&&winby2!=0){
                    check=false;
                }
                break;
            default:
                break;
        }
            
            countfour++;
        }
        
        return check;
    }
    
}//end of main
