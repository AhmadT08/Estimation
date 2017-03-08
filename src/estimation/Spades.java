/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimation;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Ahmad
 */
public class Spades extends Suit {

    Spades() {
        cards = new ArrayList();
        for (int i = 40; i < 53; i++) {
            cards.add(i);
        }
    }

    @Override
    public String getName() {
        return "Spades";
    }

//    @Override
//    public int compareWeight(ArrayList<Integer> hand, Suit suit) {
//        //if x and y are trump cards, they are compared in size
//        //if x is a trump card and y is not, x is larger
//        //if x and y are not trump cards, 
//        int result = 0;
//
//        Collections.sort(hand);
//        hand = rearrangeHand(hand);
//
//        int a = hand.get(0);
//        int b = hand.get(1);
//        int c = hand.get(2);
//        int d = hand.get(3);
//
//        int[] alpha = {a, b, c, d};
//
//        if (trumpCheck(a)) {
//
//            if (trumpCheck(b)) {
//
//                if (trumpCheck(c)) {
//
//                    if (trumpCheck(d)) { //if all cards are trump cards, the highest card wins
//
//                        for (int i = 0; i < 4; i++) {
//                            for (int j = 0; j < 4; j++) {
//                                if (alpha[j] > alpha[i] && alpha[j] > result) {
//                                    result = alpha[j];
//                                }
//                            }
//                        }
//                    }
//                    for (int i = 0; i < 3; i++) {
//                        for (int j = 0; j < 3; j++) {
//                            if (alpha[j] > alpha[i] && alpha[j] > result) {
//                                result = alpha[j];
//                            }
//                        }
//                    }
//                }
//                if (b > a) {
//                    if (b > result) {
//                        result = b;
//                    }
//                }
//            }
//            if (a > result) {
//                result = a;
//            }
//        }
//
//        if (suit.getClass() != this.getClass()) {
//            result = suit.compareWeight(hand, suit);
//        }
//
//        return result;
//    }

    @Override
    public Boolean trumpCheck(int x) {
        //checks if card is a trump card
        Boolean b = false;

        if (cards.contains(x)) {
            b = true;
        }

        return b;
    }

    @Override
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

}
