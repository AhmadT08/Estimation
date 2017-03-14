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

}
