/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Noah
 */
public class Deck {
    
    private ArrayList<Card> deck; //represents the deck of cards

    Deck(){
        //int count=0;
        deck = new ArrayList<Card>();
        for(int i=0; i<4; i++){
            for(int j=1; j<=13; j++){
                deck.add(new Card(i,j));
                //count++;
            }
        }
        deck.add(new Card(4,1));
        deck.add(new Card(4,2));
        deck.add(new Card(4,3));
        deck.add(new Card(4,4));
        /*count=count+4; made for testing deck count
        System.out.println("Deck Count: "+count);*/
    }

    void shuffle(){ //shuffles deck
    
        Random random = new Random();
        Card temp;
        for(int i=0; i<300; i++){ //switches the index of random cards in the deck 300 times
            int indexOne = random.nextInt(deck.size()-1);
            int indexTwo = random.nextInt(deck.size()-1);
            temp = deck.get(indexTwo);
            deck.set(indexTwo, deck.get(indexOne));
            deck.set(indexOne, temp);
        }
    }

    Card drawCard(){ //card drawing function
        Card temp;
        temp=deck.remove(0);
        deck.add(temp);
        return temp;
    }
     
}
