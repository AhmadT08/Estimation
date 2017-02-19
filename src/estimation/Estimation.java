/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Ahmad
 */
public class Estimation {

    public static void Translate(ArrayList<Integer> list) {
        Collections.sort(list);
        Collections.reverse(list);
        
        for (int j = 0; j < 13; j++) {
            int card = list.get(j);
            if ((card - 1) / 13 == 0) {
                if (card % 13 == 0) {
                    System.out.println("Ace of Clubs");
                } else if (card % 13 == 12) {
                    System.out.println("King of Clubs");
                } else if (card % 13 == 11) {
                    System.out.println("Queen of Clubs");
                } else if (card % 13 == 10) {
                    System.out.println("Jack of Clubs");
                } else {
                    System.out.println(card % 13 + " of Clubs");
                }
            }
            if ((card - 1) / 13 == 1) {
                if (card % 13 == 0) {
                    System.out.println("Ace of Diamonds");
                } else if (card % 13 == 12) {
                    System.out.println("King of Diamonds");
                } else if (card % 13 == 11) {
                    System.out.println("Queen of Diamonds");
                } else if (card % 13 == 10) {
                    System.out.println("Jack of Diamonds");
                } else {
                    System.out.println(card % 13 + " of Diamonds");
                }
            }
            if ((card - 1) / 13 == 2) {
                if (card % 13 == 0) {
                    System.out.println("Ace of Hearts");
                } else if (card % 13 == 12) {
                    System.out.println("King of Hearts");
                } else if (card % 13 == 11) {
                    System.out.println("Queen of Hearts");
                } else if (card % 13 == 10) {
                    System.out.println("Jack of Hearts");
                } else {
                    System.out.println(card % 13 + " of Hearts");
                }
            }
            if ((card - 1) / 13 == 3) {
                if (card % 13 == 0) {
                    System.out.println("Ace of Spades");
                } else if (card % 13 == 12) {
                    System.out.println("King of Spades");
                } else if (card % 13 == 11) {
                    System.out.println("Queen of Spades");
                } else if (card % 13 == 10) {
                    System.out.println("Jack of Spades");
                } else {
                    System.out.println(card % 13 + " of Spades");
                }
            }

        }
    }
    
    public static ArrayList Deal(){
        ArrayList<Integer> cards = new ArrayList(52);
        for (int i = 1; i < 53; i++) {
            cards.add(i);
        }
        Collections.shuffle(cards);

        ArrayList<Integer> p1cards = new ArrayList(13);
        ArrayList<Integer> p2cards = new ArrayList(13);
        ArrayList<Integer> p3cards = new ArrayList(13);
        ArrayList<Integer> p4cards = new ArrayList(13);
        ArrayList<ArrayList> cardLists = new ArrayList<>(Arrays.asList(p1cards, p2cards, p3cards, p4cards));

        for (int i = 0; i < 4; i++) {
            for (int j = 13 * i; j < 13 * (i + 1); j++) {
                cardLists.get(i).add(cards.get(j));
            }
        }
        return cardLists;
    }

    public static void main(String[] args) {
        User u1 = new User("Ahmad");
        Computer c1 = new Computer("Computer1");
        Computer c2 = new Computer("Computer2");
        Computer c3 = new Computer("Computer3");
        
        Session s1 = new Session(u1,c1,c2,c3,"Easy");
        
        
        
    }

}
