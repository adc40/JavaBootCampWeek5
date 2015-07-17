package javabootcampweek5.GameSuite;

import javabootcampweek4.pokergame.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Abram on 7/15/2015.
 */
public class GamesFrame {

    Player player = new Player();

    public GamesFrame (Player player) {

        this.player = player;
        JFrame jFrame = new JFrame("Card Games");
        jFrame.setSize(300, 300);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jPanel = new JPanel();
        JButton pokerButton = new JButton("Poker");
        pokerButton.addActionListener(new ActionPoker());
        JButton blackjackButton = new JButton("BlackjackGame");
        blackjackButton.addActionListener(new ActionBlackjack());
        JLabel jLabel = new JLabel("Pick a game");
        jPanel.add(jLabel, BorderLayout.PAGE_START);
        jPanel.add(pokerButton, BorderLayout.LINE_START);
        jPanel.add(blackjackButton, BorderLayout.LINE_END);
        jFrame.add(jPanel);
        jFrame.setVisible(true);
    }

    public class ActionBlackjack implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            player.setName(JOptionPane.showInputDialog(null, "Welcome! What's your name?"));
            player.setStack(Integer.valueOf(JOptionPane.showInputDialog(null, "Hi " + player.getName()
                    + "! How much money are you playing with?")));
            BlackjackGame blackjack = new BlackjackGame(player);
        }
    }

    public class ActionPoker implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            player.setStack(Integer.valueOf(JOptionPane.showInputDialog(null, "How much money are you playing with?")));
            JFrame jFrame = new JFrame("Poker");
            jFrame.setSize(500, 500);

            jFrame.setVisible(true);
        }
    }

}
