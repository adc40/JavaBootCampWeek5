package javabootcampweek5.GameSuite;

import javabootcampweek4.pokergame.Card;
import javabootcampweek4.pokergame.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Abram on 7/13/2015.
 */
public class Hand {

    private Random rand = new Random();
    private String suits = "SHCD";

    ArrayList<Card> player = new ArrayList<>();
    ArrayList<Card> dealer = new ArrayList<>();

    public void dealCards() {
        for (int i = 0; i < 4; i++) {
            Card card = new Card(rand.nextInt(13) + 2, suits.charAt(rand.nextInt(4)));
            if (i % 2 == 0) {
                player.add(card);
            } else {
                dealer.add(card);
            }
        }
    }

    public void hit(ArrayList<Card> hand){
        Card card = new Card(rand.nextInt(13) + 2, suits.charAt(rand.nextInt(4)));
        hand.add(card);
    }

    public int sum(ArrayList<Card> hand) {
        int sum = 0;
        ArrayList<Integer> values = new ArrayList<>();
        for (Card card : hand) {
            values.add(card.getValue());
        }
        Collections.sort(values);
        for(Integer cardValue : values) {
            if(cardValue < 11) {
                sum += cardValue;
            } else if( cardValue > 10 && cardValue < 14) {
                sum += 10;
            } else if(sum + 11 > 21){
                sum++;
            } else {
                sum += 11;
            }
        }
        return sum;
    }

    public ArrayList<Card> getPlayerCards() {
        return player;
    }

    public ArrayList<Card> getDealerCards() {
        return dealer;
    }

    public String showHand (Player player) {
        String cards = "";
        for (Card card : player.getHand()){
            cards = cards + card.showCard() + " ";
        }
        return cards;
    }
}
