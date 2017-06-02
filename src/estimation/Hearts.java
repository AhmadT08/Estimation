/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimation;

import java.util.ArrayList;

/**
 *
 * @author Ahmad
 */
public class Hearts extends Suit {

    Hearts() {
        cards = new ArrayList();
        for (int i = 27; i < 40; i++) {
            cards.add(i);
        }

    }

    @Override
    public String getName() {
        return "Hearts";
    }

}
