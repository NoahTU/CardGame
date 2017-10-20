/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author Noah
 */
public class Player {

    private int score;
    private Card temp;
    private boolean win=false;

    public void change(int s){ //function for adjusting the score

        if (s<0&&score==0){
            score=0;
        }
        else{
            score= score+s;
        }
    }

    //function for testing. allows for setting the scoree
    public void setScore(int newScore){
        score=newScore;
    }


    public int getScore(){ //returns score
        return score;
    }

    public void playerCard(Card current){ //sets player's card for the round
        temp= current;
    }

    public Card getCard(){ //returns player's card for the round
        return temp;
    }

    public boolean winCheck(){ //checks to see if player's score is at or over 21
        if (score>=21){
            win=true;
        }
        return win;
    }
}
