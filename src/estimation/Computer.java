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
        Boolean b = true;
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

        int aceCounter = 0;

        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> temp = handSuits.get(i);

            if (temp.size() > 0 && (temp.get(0) % 13 == 0 && temp.get(temp.size() - 1) % 13 == 0)) {
                aceCounter++;
            }

            //Rule 1: If a suit in the hand has two cards,
            //        and one of them is a master card (A/K/Q/J),
            //        dash call cannot be called
            if ((temp.size() == 2 && temp.size() > 0)
                    && ((temp.get(temp.size() - 1) % 13) > 9 || (temp.get(0) % 13) == 0
                    || (temp.get(0) % 13) > 9)) {
                System.out.println("\t\t\tRule 1");
                b = false;
            }

            //Rule 2: If a suit in the hand has less than 4 cards,
            //        and one of them is a master card higher than a queen (A/K),
            //        dash call cannot be called
            if ((temp.size() <= 3 && temp.size() > 0)
                    && (((temp.get(0) % 13 == 12) || (temp.get(0) % 13) == 0))) {
                System.out.println("\t\t\tRule 2");
                b = false;
            }

            //Rule 3: If a suit in the hand has less than 5 cards,
            //        and one of them is an Ace,
            //        dash call cannot be called
            if ((temp.size() <= 4 && temp.size() > 0)
                    && (temp.get(0) % 13 == 0)) {
                System.out.println("\t\t\tRule 3");
                b = false;
            }

            //Rule 4: If a suit in the hand 1 card,
            //        and it is a master card (A/K/Q/J),
            //        dash call cannot be called
            if (((temp.size() == 1) && (((temp.get(0) % 13) > 9)
                    || (temp.get(0) % 13) == 0))) {
                System.out.println("\t\t\tRule 4");
                b = false;
            }

            //Rule 5: If a suit in the hand has 3 cards,
            //        and it two of them are master cards (A/K/Q/J),
            //        dash call cannot be called
            if ((temp.size() == 3) && (((temp.get(1) % 13) > 9))) {
                System.out.println("\t\t\tRule 5");
                b = false;
            }

            //Rule 6: If the hand contains more than 2 aces,
            //        dash call cannot be called
            if (aceCounter > 2) {
                System.out.println("\t\t\tRule 6");
                b = false;
            }

            //Rule 7: If the smallest card in a suit in the hand is greater than 5
            //        dash call cannot be called
            if (temp.size() > 0 && temp.size() < 4) {
                if (temp.get(temp.size() - 1) > 5) {
                    System.out.println("\t\t\tRule 7");
                    b = false;
                }
            }
        }

        if (b == true) {
            System.out.println("\t DASHCALL!");
        }
        return b;
    }
}
