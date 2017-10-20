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
public class Card {

    private int rank; //rank of a card
    private int suit; //suit of a card
    private int value; //value of a card
    private static String[] ranks = {"Joker","Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"};
    private static String[] suits = {"Clubs","Diamonds","Hearts","Spades", "Penalty"};

    Card(int suit, int values){
        this.rank=values;
        this.suit=suit;
    }

    public String toString(){ //returns a string version of a card
        return "The "+ranks[rank]+" of "+suits[suit];
    }

    public int getRank(){ //returns rank
        return rank;
    }

    public int getSuit(){ //returns suit
        return suit;
    }
    
    public void setValue(int set){ //give value to card
        value = set;
    }

    public int getValue() //returns value: jack, queen, or king = a value of 10. Aces are 11.
    {
        if(rank==1){
            value=11;
        }
        else if(rank>10){
            value=10;
        }
        else{
            value=rank;
        }
        if (suit==4){ //if penalty card, set value to 0 as to register in card game
            value=0;
        }
        return value;
    }

   
}
