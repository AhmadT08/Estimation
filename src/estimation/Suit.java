/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimation;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author Ahmad
 */
public abstract class Suit {

    public ArrayList<Integer> cards;

    public abstract String getName();

    public Boolean trumpCheck(int x) {
        //checks if card is a trump card
        Boolean b = false;

        if (cards.contains(x)) {
            b = true;
        }

        return b;
    }

    public ArrayList<Integer> rearrangeHand(ArrayList<Integer> hand) {
        ArrayList<Integer> result = new ArrayList();

        int a = hand.get(0);
        int b = hand.get(1);
        int c = hand.get(2);
        int d = hand.get(3);

        int[] alpha = {a, b, c, d};

        for (int i = 0; i < 4; i++) {
            if (trumpCheck(alpha[i])) {
                result.add(alpha[i]);
            }
        }

        for (int i = 0; i < 4; i++) {
            if (!result.contains(alpha[i])) {
                result.add(alpha[i]);
            }
        }

        return result;
    }

    public int compareWeight(ArrayList<Integer> hand) {
        //if x and y are trump cards, they are compared in size
        //if x is a trump card and y is not, x is larger
        //if x and y are not trump cards, 
        int result = 0;

        Collections.sort(hand);
        hand = rearrangeHand(hand);

        int a = hand.get(0);
        int b = hand.get(1);
        int c = hand.get(2);
        int d = hand.get(3);

        int[] alpha = {a, b, c, d};

        if (trumpCheck(a)) {

            if (trumpCheck(b)) {

                if (trumpCheck(c)) {

                    if (trumpCheck(d)) { //if all cards are trump cards, the highest card wins

                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (alpha[j] > alpha[i] && alpha[j] > result) {
                                    result = alpha[j];
                                }
                            }
                        }
                    }
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (alpha[j] > alpha[i] && alpha[j] > result) {
                                result = alpha[j];
                            }
                        }
                    }
                }
                if (b > a) {
                    if (b > result) {
                        result = b;
                    }
                }
            }
            if (a > result) {
                result = a;
            }
        }

//        if (suit.getClass() != this.getClass()) {
//            result = suit.compareWeight(hand, suit);
//        }
        return result;
    }

    public static Suit returnSuitByCard(int card) {
        Suit suit = null;
        
        if (card > 0 && card < 14) {
            suit = new Clubs();
        } else if (card > 13 && card < 27) {
            suit = new Diamonds();
        } else if (card > 26 && card < 40) {
            suit = new Hearts();
        } else if (card > 39 && card < 53) {
            suit = new Spades();
        } else {
            JOptionPane.showMessageDialog(null, card);
        }
        return suit;
    }

    public static Suit returnSuitByName(String name) {
        Suit suit = null;
        switch (name) {
            case "Clubs":
                suit = new Clubs();
                break;
            case "Diamonds":
                suit = new Diamonds();
                break;
            case "Hearts":
                suit = new Hearts();
                break;
            case "Spades":
                suit = new Spades();
                break;
            case "Suns":
                suit = new Suns();
                break;
            default:
                JOptionPane.showMessageDialog(null, name);
        }
        return suit;
    }

}
