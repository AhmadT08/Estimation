/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Ahmad
 */
public class Computer extends Player {

    public Computer(String n) {
        name = n;
    }

    public void openBidding() {

    }

    public Boolean dashCall() {
        Boolean b = false;
        System.out.println(name);
        translate();
        System.out.println();

        ArrayList<Integer> h = getHand();
        ArrayList<Integer> spades = new ArrayList();
        ArrayList<Integer> hearts = new ArrayList();
        ArrayList<Integer> diamonds = new ArrayList();
        ArrayList<Integer> clubs = new ArrayList();
        ArrayList<ArrayList> handSuits = new ArrayList<>(Arrays.asList(spades, hearts, diamonds, clubs));

        for (int i = 0; i < h.size(); i++) { //Organize hand into suits
            int card = h.get(i); //Get next card

            if ((card - 1) / 13 == 0) { //Check if card is in the first 13 cards of the deck (Clubs)
                clubs.add(card);
            }
            if ((card - 1) / 13 == 1) {
                diamonds.add(card);
            }
            if ((card - 1) / 13 == 2) {
                hearts.add(card);
            }
            if ((card - 1) / 13 == 3) {
                spades.add(card);
            }
        }

        Integer[] prog1 = {2, 3, 4, 5};
        Integer[] prog2 = {6, 7, 8, 9};
        Integer[] prog3 = {10, 11, 12, 13};
        ArrayList<Integer[]> cardPlay = new ArrayList<>(Arrays.asList(prog1, prog2, prog3));

        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> temp = handSuits.get(i);

            //Rule 1: If a suit in the hand has less than 3 cards,
            //        and one of them is a master card (A/K/Q/J),
            //        dash call cannot be called
            if ((temp.size() <= 2 && temp.size() > 0) && ((temp.get(0) % 13) > 5 || (temp.get(0) % 13) == 0)) {
                System.out.println("\t\t\tRule 1");
                b = false;
            }

            //Rule 2: If a suit in the hand has less than 5 cards,
            //        and one of them is an Ace,
            //        dash call cannot be called
            if ((temp.size() < 4 && temp.size() > 0) && (temp.get(temp.size() - 1) % 13 == 0)) {
                System.out.println("\t\t\tRule 2");
                b = false;
            }

            //Rule 3: If a suit in the hand 1 card,
            //        and it is a master card (A/K/Q/J),
            //        dash call cannot be called
            if ((temp.size() == 1) && ((temp.get(0) % 13) > 9 || (temp.get(0) % 13) == 0)) {
                System.out.println("\t\t\tRule 3");
                b = false;
            }
        }

        System.out.println();
        return b;
    }
}
