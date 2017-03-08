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
public class Clubs extends Suit {

    Clubs() {
        cards = new ArrayList();
        for (int i = 1; i < 14; i++) {
            cards.add(i);
        }

    }

    @Override
    public String getName() {
        return "Clubs";
    }

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
