package javabootcampweek5.GameSuite;

import java.util.Scanner;

/**
 * Created by Abram on 7/13/2015.
 */
public class UserPrompt {

    Scanner in = new Scanner(System.in);

    public void start(){
        System.out.println("Start BlackjackGame game? (Yes or no)");
    }

    public void playAgain(){
        System.out.println("Play again? ");
    }

    public int setUpStack(){
        System.out.println("How much money are you playing with?  ");
        return in.nextInt();
    }

    public int getBet(){
        System.out.println("How much you want to bet? ");
        return in.nextInt();
    }

    public void hitOrStay(){
        System.out.println("Hit or stay?");
    }
}
