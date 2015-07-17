package javabootcampweek5.GameSuite;

import javabootcampweek4.pokergame.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Abram on 7/16/2015.
 */
public class BlackjackGame {

    CardLayout cl = new CardLayout();
    JFrame jFrame = new JFrame("BlackjackGame");
    JPanel jPanelContainer = new JPanel();
    JPanel jPanelNoCards = new JPanel();
    JPanel jPanelWithCards = new JPanel();
    JPanel dealerPanel = new JPanel();
    JPanel userPanel = new JPanel();
    JPanel moneyPanel = new JPanel();
    JLabel moneyLabel = new JLabel();
    JLabel dealerLabel = new JLabel("Dealer");
    JButton betButton = new JButton("Place Bet");
    JButton hitButton = new JButton("Hit");
    JButton stayButton = new JButton("Stay");
    JButton continueButton = new JButton("Continue");
    JTextField betText = new JFormattedTextField("Ready for your bet");
    JPanel winLosePanel = new JPanel();
    JLabel winLoseLabel = new JLabel();
    JLabel dealerCards = new JLabel();
    JLabel userCards = new JLabel();

    Player player = new Player();
    Hand hand = new Hand();
    Player dealer = new Player();
    int bet;
    boolean stay = true;
    boolean underTwentyOne;

    public BlackjackGame(Player player) {

        this.player = player;
        jFrame.setSize(300, 300);

        //sets up card layout. one panel containing two panels - one that shows cards, and one that doesn't
        jPanelContainer.setLayout(cl);
        jPanelNoCards.setLayout(new GridLayout(0, 1));
        jPanelWithCards.setLayout(new GridLayout(0, 1));

        //sets up panels and labels to be used on both panels of card layout
        JLabel userLabel = new JLabel(player.getName());
        moneyLabel.setText("You have $" + Integer.toString(player.getStack()));
        JLabel betLabel = new JLabel("Your bet: $" + bet);
        betButton.addActionListener(new ActionBet());
        dealerPanel.add(dealerLabel);
        userPanel.add(userLabel);
        moneyPanel.add(moneyLabel);
        moneyPanel.add(betLabel);

        //sets up panel without cards
        betText.setEditable(false);
        jPanelNoCards.add(betText);
        jPanelNoCards.add(moneyPanel);
        jPanelNoCards.add(betButton);
        jFrame.add(jPanelContainer);

        //sets up panel with cards
        jPanelWithCards.add(dealerPanel);
        jPanelWithCards.add(userPanel);
        jPanelWithCards.add(winLosePanel);
        hitButton.addActionListener(new ActionHit());
        stayButton.addActionListener(new ActionStay());
        continueButton.addActionListener(new ActionNextHand());
        jPanelWithCards.add(hitButton);
        jPanelWithCards.add(stayButton);

        jPanelContainer.add(jPanelNoCards, "1");
        jPanelContainer.add(jPanelWithCards, "2");
        cl.show(jPanelContainer, "1");

        jFrame.setVisible(true);

    }

    public class ActionBet implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            bet = Integer.valueOf(JOptionPane.showInputDialog(null, "How much you want to bet?"));
            underTwentyOne = true;
            hand.dealCards();
            player.setHand(hand.getPlayerCards());
            dealer.setHand(hand.getDealerCards());
            dealerCards.setText(dealer.getHand().get(0).showCard());
            userCards.setText(hand.showHand(player));
            dealerPanel.add(dealerCards);
            userPanel.add(userCards);
            if(player.getHand().get(0).getValue() > 10 && player.getHand().get(1).getValue() > 10
                    && hand.sum(player.getHand()) == 21) {
                youWin("Blackjack! You win!");
            } else cl.show(jPanelContainer, "2");
        }
    }

    public class ActionHit implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            if(hand.sum(player.getHand()) < 21) {
                hand.hit(player.getHand());
                userPanel.remove(1);
                JLabel userCards = new JLabel(hand.showHand(player));
                userPanel.add(userCards);
                jPanelWithCards.revalidate();
                if (hand.sum(player.getHand()) > 21) {
                    youLose();
                }
                cl.show(jPanelContainer, "2");
            }
        }
    }

    public class ActionNextHand implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            player.clearHand();
            dealer.clearHand();
            winLoseLabel.setText("");
            userPanel.remove(1);
            dealerPanel.remove(1);
            jPanelWithCards.revalidate();
            cl.show(jPanelContainer, "1");
        }
    }

    public class ActionStay implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e ) {
            while (hand.sum(dealer.getHand()) < 17 && stay) {
                hand.hit(dealer.getHand());
            }
            dealerCards.setText(hand.showHand(dealer));
            jPanelWithCards.revalidate();
            if(hand.sum(dealer.getHand()) > 21 || hand.sum(dealer.getHand()) < hand.sum(player.getHand())) {
                youWin("You win!");
                stay = false;
            } else if (hand.sum(dealer.getHand()) > hand.sum(player.getHand())) {
                youLose();
                stay = false;
            } else {
                push();
                stay = false;
            }
        }
    }

    public void youWin (String string) {
        winLoseLabel.setText(string);
        winLosePanel.add(winLoseLabel);
        jPanelWithCards.add(continueButton);
        jPanelNoCards.revalidate();
        player.setStack(player.getStack() + bet);
        moneyLabel.setText("You have $" + Integer.toString(player.getStack()));
        cl.show(jPanelContainer, "2");
    }

    public void youLose() {
        winLoseLabel.setText("You lose");
        winLosePanel.add(winLoseLabel);
        player.setStack(player.getStack() - bet);
        moneyLabel.setText("You have $" + Integer.toString(player.getStack()));
        jPanelNoCards.revalidate();
        jPanelWithCards.add(continueButton);
        jPanelWithCards.revalidate();
    }

    public void push() {
        winLoseLabel.setText("Push");
        jPanelWithCards.add(continueButton);
        jPanelWithCards.revalidate();
        winLosePanel.add(winLoseLabel);
    }
}
