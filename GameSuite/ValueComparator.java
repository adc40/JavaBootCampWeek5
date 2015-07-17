package javabootcampweek5.GameSuite;

import javabootcampweek4.pokergame.Card;

import java.util.Comparator;

/**
 * Created by Abram on 7/14/2015.
 */
public class ValueComparator implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {
        if (o1.getValue() > o2.getValue()) {
            return 1;
        }
        return -1;
    }
}
