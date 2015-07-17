package javabootcampweek5.GameSuite;

import javabootcampweek4.pokergame.Card;

import java.util.ArrayList;

/**
 * Created by Abram on 7/13/2015.
 */
public class Dealer {

    ArrayList<Card> hand;

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
}
