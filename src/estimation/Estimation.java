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
import java.util.Scanner;

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

    public static ArrayList Deal() {
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
//        User u1 = new User("Ahmad");
//        u1.addCardToHand(5); u1.addCardToHand(12); u1.addCardToHand(42); u1.addCardToHand(51);
//        u1.addCardToHand(16); u1.addCardToHand(9); u1.addCardToHand(41); u1.addCardToHand(21);
//        u1.addCardToHand(1); u1.addCardToHand(11); u1.addCardToHand(31); u1.addCardToHand(50);
//        u1.addCardToHand(52);
//        System.out.println(u1.getHand());
//        Scanner sc = new Scanner(System.in);

//        System.out.println(u1.translate(sc.nextInt()));

//        ArrayList<Integer> x = new ArrayList();
//        Spades s = new Spades();
//        Diamonds d = new Diamonds();
//        Hearts h = new Hearts();
//        Clubs c = new Clubs();
//        
//        x.add(38);
//        x.add(12);
//        x.add(26);
//        x.add(11);
//        Collections.sort(x);
//        System.out.println(s.compareWeight(x, c));
        
        
        User u1 = new User("Ahmad");
        User u2 = new User("Negm");
        User u3 = new User("Ramy");
        User u4 = new User("Helal");

        Computer c1 = new Computer("Computer1");
        Computer c2 = new Computer("Computer2");
        Computer c3 = new Computer("Computer3");
        Computer c4 = new Computer("Computer4");

        Session s1 = new Session(u1, u2, u3, u4, "Easy");
    }

}
