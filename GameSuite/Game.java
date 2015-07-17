package javabootcampweek5.GameSuite;

import javabootcampweek4.pokergame.Player;

import java.util.Scanner;

/**
 * Created by Abram on 7/13/2015.
 */
public class Game {

    public static void main(String[] args) {

        Player player = new Player();
        GamesFrame gamesFrame = new GamesFrame(player);

        Scanner in = new Scanner(System.in);
        String userInput;
        boolean continuePlaying = true;
        boolean underTwentyOne;
        int bet;

        Hand hand = new Hand();
        Player dealer = new Player();
        UserPrompt userPrompt = new UserPrompt();
        Table table = new Table();

        userPrompt.start();
        userInput = in.nextLine().toLowerCase();
        if(userInput.contains("n")) {
            continuePlaying = false;
        }
        player.setStack(userPrompt.setUpStack());

        while(continuePlaying) {

            underTwentyOne = true;
            bet = userPrompt.getBet();
            hand.dealCards();
            player.setHand(hand.getPlayerCards());
            dealer.setHand(hand.getDealerCards());

            while(underTwentyOne) {
                table.showTable(dealer.getHand().get(0).showCard(), hand.showHand(player),
                        player.getStack(), bet);
                if(player.getHand().get(0).getValue() > 10 && player.getHand().get(1).getValue() > 10
                        && hand.sum(player.getHand()) == 21) {
                    System.out.println("Blackjack!");
                    player.setStack(player.getStack() + bet);
                    System.out.println("Your money:  $" + player.getStack());
                    underTwentyOne = false;
                    break;
                }
                System.out.println("Hit or stay?");
                userInput = in.nextLine().toLowerCase();
                if (userInput.contains("h")) {
                    hand.hit(player.getHand());
                    if (hand.sum(player.getHand()) > 21) {
                        table.showTable(dealer.getHand().get(0).showCard(), hand.showHand(player),
                                player.getStack(), bet);
                        System.out.println("You lose");
                        player.setStack(player.getStack() - bet);
                        underTwentyOne = false;
                        System.out.println("Your money:  $" + player.getStack());
                    } else if (hand.sum(player.getHand()) == 21) {
                        table.showTable(dealer.getHand().get(0).showCard(), hand.showHand(player),
                                player.getStack(), bet);
                        System.out.println("21!");
                        break;
                    }
                } else {
                    break;
                }
            }

            if(underTwentyOne) {
                while (hand.sum(dealer.getHand()) < 17) {
                    table.showTable(hand.showHand(dealer), hand.showHand(player), player.getStack(), bet);
                    hand.hit(dealer.getHand());
                }

                if (hand.sum(dealer.getHand()) > 21) {
                    table.showTable(hand.showHand(dealer), hand.showHand(player), player.getStack(), bet);
                    System.out.println("You win!");
                    player.setStack(player.getStack() + bet);
                    System.out.println("Your money:  $" + player.getStack());
                } else if (hand.sum(dealer.getHand()) > hand.sum(player.getHand())) {
                    table.showTable(hand.showHand(dealer), hand.showHand(player), player.getStack(), bet);
                    System.out.println("You lose!");
                    player.setStack(player.getStack() - bet);
                    System.out.println("Your money:  $" + player.getStack());
                } else if (hand.sum(dealer.getHand()) == hand.sum(player.getHand())) {
                    table.showTable(hand.showHand(dealer), hand.showHand(player), player.getStack(), bet);
                    System.out.println("Push");
                    System.out.println("Your money:  $" + player.getStack());
                } else {
                    table.showTable(hand.showHand(dealer), hand.showHand(player), player.getStack(), bet);
                    System.out.println("You win!");
                    player.setStack(player.getStack() + bet);
                    System.out.println("Your money:  $" + player.getStack());
                }
            }

            if(player.getStack() < 1) {
                System.out.println("You're out of money! Game Over");
                continuePlaying = false;
            }

            if(continuePlaying) {
                userPrompt.playAgain();
                userInput = in.nextLine().toLowerCase();
                if (userInput.contains("n")) {
                    System.out.println("Bye");
                    continuePlaying = false;
                }
            }

            player.clearHand();
            dealer.clearHand();
        }


    }
}
