/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Ahmad
 */
public class Estimation {

    public static void translate(ArrayList<Integer> list) {
        Collections.sort(list);
        Collections.reverse(list);

        ArrayList<Integer> spades = new ArrayList();
        ArrayList<Integer> hearts = new ArrayList();
        ArrayList<Integer> diamonds = new ArrayList();
        ArrayList<Integer> clubs = new ArrayList();
        ArrayList<ArrayList<Integer>> handSuits = new ArrayList<>(Arrays.asList(spades, hearts, diamonds, clubs));

        for (int i = 0; i < list.size(); i++) { //Organize hand into suits
            int card = list.get(i); //Get next card

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

        for (ArrayList<Integer> suit : handSuits) {
            System.out.println();
            for (int i : suit) {
                int card = i;
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
                        System.out.println(((card % 13) + 1) + " of Clubs");
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
                        System.out.println(((card % 13) + 1) + " of Diamonds");
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
                        System.out.println(((card % 13) + 1) + " of Hearts");
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
                        System.out.println(((card % 13) + 1) + " of Spades");
                    }
                }

            }
        }

    }

    public static String translate(int card) {
        String x = "";

        if ((card - 1) / 13 == 0) { //Check if card is in the first 13 cards of the deck (Clubs)
            if (card % 13 == 0) {
                x = "Ace of Clubs"; //The ace is the 13th card in the suit
            } else if (card % 13 == 12) {
                x = "King of Clubs"; //The king is the 12th card in the suit
            } else if (card % 13 == 11) {
                x = "Queen of Clubs"; //The queen is the 11th card in the suit
            } else if (card % 13 == 10) {
                x = "Jack of Clubs"; //The jack is the 10th card in the suit 
            } else {
                x = ((card % 13) + 1) + " of Clubs";
            }
        }
        if ((card - 1) / 13 == 1) {
            if (card % 13 == 0) {
                x = "Ace of Diamonds";
            } else if (card % 13 == 12) {
                x = "King of Diamonds";
            } else if (card % 13 == 11) {
                x = "Queen of Diamonds";
            } else if (card % 13 == 10) {
                x = "Jack of Diamonds";
            } else {
                x = ((card % 13) + 1) + " of Diamonds";
            }
        }
        if ((card - 1) / 13 == 2) {
            if (card % 13 == 0) {
                x = "Ace of Hearts";
            } else if (card % 13 == 12) {
                x = "King of Hearts";
            } else if (card % 13 == 11) {
                x = "Queen of Hearts";
            } else if (card % 13 == 10) {
                x = "Jack of Hearts";
            } else {
                x = ((card % 13) + 1) + " of Hearts";
            }
        }
        if ((card - 1) / 13 == 3) {
            if (card % 13 == 0) {
                x = "Ace of Spades";
            } else if (card % 13 == 12) {
                x = "King of Spades";
            } else if (card % 13 == 11) {
                x = "Queen of Spades";
            } else if (card % 13 == 10) {
                x = "Jack of Spades";
            } else {
                x = ((card % 13) + 1) + " of Spades";
            }
        }

        return x;
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

    public static void deal(ArrayList<Computer> computers) {
        ArrayList<Integer> cards = new ArrayList(52);
        for (int i = 1; i < 53; i++) {
            cards.add(i);
        }

        Collections.shuffle(cards);

        for (int i = 0; i < 4; i++) {
            for (int j = 13 * i; j < 13 * (i + 1); j++) {
                computers.get(i).addCardToHand(cards.get(j));
            }

            computers.get(i).sortHand();
        }
    }

    public static int numberOfMasters(ArrayList<Integer> suit) {
        int masterCounter = 0;

        for (int i = 0; i < suit.size(); i++) {
            if (suit.get(i) % 13 > 9 || suit.get(i) % 13 == 0) {
                masterCounter++;
            }
        }
        return masterCounter;
    }

    public static int suitPoints(ArrayList<Integer> suit) {
        int points = 0;
        for (int i = 0; i < suit.size(); i++) {
            if (suit.get(i) % 13 == 0) {
                points += 13;
            } else {
                points += (suit.get(i) % 13);
            }
        }
        return points;
    }

    public static int determineMaxSuitTricks(ArrayList<Integer> suit, int Case) {
        int tricks, suitSize;
        tricks = 0;
        suitSize = suit.size();
        int masters = numberOfMasters(suit);
        Boolean ace, king, queen, jack;
        ace = king = queen = jack = false;

        for (int i = 0; i < suit.size(); i++) {
            if (suit.get(i) % 13 == 0) {
                ace = true;
            }
            if (suit.get(i) % 13 == 12) {
                king = true;
            }
            if (suit.get(i) % 13 == 11) {
                queen = true;
            }
            if (suit.get(i) % 13 == 10) {
                jack = true;
            }
        }

        if (Case == 3) {
            tricks += suit.size();
        } else if (Case < 5 && Case != 3) {
            if (jack && suit.size() >= 4) {
                tricks++;
            }
            if (queen && suit.size() >= 3) {
                tricks++;
            }
            if (king && suit.size() >= 2) {
                tricks++;
            }
            if (ace) {
                tricks++;
            }
        } else if (Case == 5) {
            if (masters == 2 && suitSize == 5) {
                if (ace && king) {
                    tricks += (suitSize - 1);
                } else {
                    tricks += (suitSize - 2);
                }
            } else if (masters > 2) {
                if (suitSize < 7) {
                    tricks += (suitSize - 1);
                } else {
                    tricks += suitSize;
                }
            }
        }

        return tricks;
    }

    public static int determineSuitTricks(ArrayList<Integer> suit, int Case) {
        int tricks, suitSize;
        tricks = 0;
        suitSize = suit.size();
        int masters = numberOfMasters(suit);
        Boolean ace, king, queen, jack;
        ace = king = queen = jack = false;

        for (int i = 0; i < suit.size(); i++) {
            if (suit.get(i) % 13 == 0) {
                ace = true;
            }
            if (suit.get(i) % 13 == 12) {
                king = true;
            }
            if (suit.get(i) % 13 == 11) {
                queen = true;
            }
            if (suit.get(i) % 13 == 10) {
                jack = true;
            }
        }

        if (Case == 3) {
            tricks += suit.size();
        } else if (Case < 5 && Case != 3) {
            if (queen && suit.size() >= 3) {
                tricks++;
            }
            if (king && suit.size() >= 2) {
                tricks++;
            }
            if (ace) {
                tricks++;
            }
        } else if (Case == 5) {
            if (masters == 2 && suitSize == 5) {
                tricks += (suitSize - 2);
            } else if (masters > 2) {
                if (suitSize < 7) {
                    tricks += (suitSize - 2);
                } else {
                    if (ace && king) {
                        tricks += suitSize;
                    } else {
                        tricks += (suitSize - 1);
                    }
                }
            }
        }

        return tricks;
    }

    public static Call maxOpenBidding(ArrayList<Integer> hand) {
        Call c = new Call(true);

        ArrayList<Integer> h = hand;
        ArrayList<Integer> masters = new ArrayList();
        int aceCounter = 0;
        int tricks = 0;
        int highestTricks = 0;
        int greatestMaster = 0;
        int totalMasterPoints = 0;
        String callSuit = "";
        ArrayList<Integer> spades = new ArrayList();
        ArrayList<Integer> spadeMasters = new ArrayList();
        ArrayList<Integer> hearts = new ArrayList();
        ArrayList<Integer> heartMasters = new ArrayList();
        ArrayList<Integer> diamonds = new ArrayList();
        ArrayList<Integer> diamondMasters = new ArrayList();
        ArrayList<Integer> clubs = new ArrayList();
        ArrayList<Integer> clubMasters = new ArrayList();
        ArrayList<ArrayList> handSuits = new ArrayList<>(Arrays.asList(spades, hearts, diamonds, clubs));

        for (int i = 0; i < h.size(); i++) { //Organize hand into suits
            int card = h.get(i); //Get next card

            if ((card - 1) / 13 == 0) { //Check if card is in the first 13 cards of the deck (Clubs)
                clubs.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    masters.add(card);
                    clubMasters.add(card);
                }
            }
            if ((card - 1) / 13 == 1) {
                diamonds.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    masters.add(card);
                    diamondMasters.add(card);
                }
            }
            if ((card - 1) / 13 == 2) {
                hearts.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    masters.add(card);
                    heartMasters.add(card);
                }
            }
            if ((card - 1) / 13 == 3) {
                spades.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    masters.add(card);
                    spadeMasters.add(card);
                }
            }
        }

        for (int i = 0; i < masters.size(); i++) {
            if (masters.get(i) % 13 == 0) {
                greatestMaster = 13;
                aceCounter++;
                totalMasterPoints += 13;
            } else if ((masters.get(i) % 13) > greatestMaster) {
                greatestMaster = (masters.get(i) % 13);
            }
            totalMasterPoints += (masters.get(i) % 13);
        }

        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> temp = handSuits.get(i);
            int suitTricks = 0;

            //Rule 1: If the total amount of masters is greater than 4,
            //        and the sum of the master points is greater than 42,
            //        and the greatest master is higher than a Queen
            if (masters.size() > 4 && totalMasterPoints > 42 && greatestMaster > 11) {
//                System.out.println("\t\t\tRule 1");
//                System.out.println(determineMaxSuitTricks(temp, 1));
                tricks += determineMaxSuitTricks(temp, 1);
                suitTricks = determineMaxSuitTricks(temp, 1);
                if (determineMaxSuitTricks(temp, 1) > highestTricks) {
                    highestTricks = determineMaxSuitTricks(temp, 1);
                    callSuit = "Suns";
                }
//                continue;
            }

            //Rule 2: If the hand contains 4 Aces
            if (aceCounter == 4 && masters.size() == 4) {
//                System.out.println("\t\t\tRule 2");
//                System.out.println((determineMaxSuitTricks(temp, 2)));
                tricks += determineMaxSuitTricks(temp, 2);
                suitTricks = determineMaxSuitTricks(temp, 2);
                if (determineMaxSuitTricks(temp, 2) > highestTricks) {
                    highestTricks = determineMaxSuitTricks(temp, 2);
                    callSuit = "Suns";
                }
//                continue;
            }

            //Rule 3: If the suit contains a full house (Ace, King, Queen and Jack)
            if (temp.equals(spades)) {
                if (spadeMasters.size() == 4) {
//                    System.out.println("\t\t\tRule 3");
//                    System.out.println((determineMaxSuitTricks(temp, 3)));
                    if (determineMaxSuitTricks(temp, 3) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 3);
                        suitTricks = determineMaxSuitTricks(temp, 3);
                        if (determineMaxSuitTricks(temp, 3) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 3);
                            callSuit = "Spades";
                        }
                    }
//                    continue;
                }
            } else if (temp.equals(hearts)) {
                if (heartMasters.size() == 4) {
//                    System.out.println("\t\t\tRule 3");
//                    System.out.println((determineMaxSuitTricks(temp, 3)));
                    if (determineMaxSuitTricks(temp, 3) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 3);
                        suitTricks = determineMaxSuitTricks(temp, 3);
                        if (determineMaxSuitTricks(temp, 3) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 3);
                            callSuit = "Hearts";
                        }
                    }
//                    continue;
                }
            } else if (temp.equals(diamonds)) {
                if (diamondMasters.size() == 4) {
//                    System.out.println("\t\t\tRule 3");
//                    System.out.println((determineMaxSuitTricks(temp, 3)));
                    if (determineMaxSuitTricks(temp, 3) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 3);
                        suitTricks = determineMaxSuitTricks(temp, 3);
                        if (determineMaxSuitTricks(temp, 3) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 3);
                            callSuit = "Diamonds";
                        }
                    }
//                    continue;
                }
            } else {
                if (clubMasters.size() == 4) {
//                    System.out.println("\t\t\tRule 3");
//                    System.out.println((determineMaxSuitTricks(temp, 3)));
                    if (determineMaxSuitTricks(temp, 3) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 3);
                        suitTricks = determineMaxSuitTricks(temp, 3);
                        if (determineMaxSuitTricks(temp, 3) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 3);
                            callSuit = "Clubs";
                        }
                    }
//                    continue;
                }
            }

            //Rule 4: If a suit has more than 2 master cards,
            //        and more than 4 cards,
            //        and the total number of master cards in the hand is greater than 4
            if (temp.equals(spades)) {
                if (spadeMasters.size() > 2 && temp.size() > 4 && masters.size() > 4) {
//                    System.out.println("\t\t\tRule 4");
//                    System.out.println((determineMaxSuitTricks(temp, 4)));
                    if (determineMaxSuitTricks(temp, 4) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 4);
                        suitTricks = determineMaxSuitTricks(temp, 4);
                        if (determineMaxSuitTricks(temp, 4) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 4);
                            callSuit = "Spades";
                        }
                    }
//                    continue;
                }
            } else if (temp.equals(hearts)) {
                if (heartMasters.size() > 2 && temp.size() > 4 && masters.size() > 4) {
//                    System.out.println("\t\t\tRule 4");
//                    System.out.println((determineMaxSuitTricks(temp, 4)));
                    if (determineMaxSuitTricks(temp, 4) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 4);
                        suitTricks = determineMaxSuitTricks(temp, 4);
                        if (determineMaxSuitTricks(temp, 4) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 4);
                            callSuit = "Hearts";
                        }
                    }
//                    continue;
                }
            } else if (temp.equals(diamonds)) {
                if (diamondMasters.size() > 2 && temp.size() > 4 && masters.size() > 4) {
//                    System.out.println("\t\t\tRule 4");
//                    System.out.println((determineMaxSuitTricks(temp, 4)));
                    if (determineMaxSuitTricks(temp, 4) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 4);
                        suitTricks = determineMaxSuitTricks(temp, 4);
                        if (determineMaxSuitTricks(temp, 4) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 4);
                            callSuit = "Diamonds";
                        }
                    }
//                    continue;
                }
            } else {
                if (clubMasters.size() > 2 && temp.size() > 4 && masters.size() > 4) {
//                    System.out.println("\t\t\tRule 4");
//                    System.out.println((determineMaxSuitTricks(temp, 4)));
                    if (determineMaxSuitTricks(temp, 4) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 4);
                        suitTricks = determineMaxSuitTricks(temp, 4);
                        if (determineMaxSuitTricks(temp, 4) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 4);
                            callSuit = "Clubs";
                        }
                    }
//                    continue;
                }
            }

            //Rule 5: If a suit has two or more master cards,
            //        and five or more cards
            if (temp.equals(spades)) {
                if (spadeMasters.size() >= 2 && temp.size() >= 5) {
//                    System.out.println("\t\t\tRule 5");
//                    System.out.println((determineMaxSuitTricks(temp, 5)));
                    if (determineMaxSuitTricks(temp, 5) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 5);
                        if (determineMaxSuitTricks(temp, 5) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 5);
                            callSuit = "Spades";
                        }
                    }
                }
            } else if (temp.equals(hearts)) {
                if (heartMasters.size() >= 2 && temp.size() >= 5) {
//                    System.out.println("\t\t\tRule 5");
//                    System.out.println((determineMaxSuitTricks(temp, 5)));
                    if (determineMaxSuitTricks(temp, 5) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 5);
                        if (determineMaxSuitTricks(temp, 5) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 5);
                            callSuit = "Hearts";
                        }
                    }
                }
            } else if (temp.equals(diamonds)) {
                if (diamondMasters.size() >= 2 && temp.size() >= 5) {
//                    System.out.println("\t\t\tRule 5");
//                    System.out.println((determineMaxSuitTricks(temp, 5)));
                    if (determineMaxSuitTricks(temp, 5) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 5);
                        if (determineMaxSuitTricks(temp, 5) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 5);
                            callSuit = "Diamonds";
                        }
                    }
                }
            } else {
                if (clubMasters.size() >= 2 && temp.size() >= 5) {
//                    System.out.println("\t\t\tRule 5");
//                    System.out.println((determineMaxSuitTricks(temp, 5)));
                    if (determineMaxSuitTricks(temp, 5) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineMaxSuitTricks(temp, 5);
                        if (determineMaxSuitTricks(temp, 5) > highestTricks) {
                            highestTricks = determineMaxSuitTricks(temp, 5);
                            callSuit = "Clubs";
                        }
                    }
                }
            }
        }
//        System.out.println(tricks + " " + callSuit);

//        if (tricks > 3) {
//            c = new Call(tricks, callSuit, false, new Player());
//        }
        return c;
    }

    public static Call openBidding(ArrayList<Integer> hand) {
        Call c = new Call(true);
//        setCall(c);
//        return c;

        ArrayList<Integer> h = hand;
        ArrayList<Integer> masters = new ArrayList();
        int aceCounter = 0;
        int tricks = 0;
        int highestTricks = 0;
        int greatestMaster = 0;
        int totalMasterPoints = 0;
        String callSuit = "";
        ArrayList<Integer> spades = new ArrayList();
        ArrayList<Integer> spadeMasters = new ArrayList();
        ArrayList<Integer> hearts = new ArrayList();
        ArrayList<Integer> heartMasters = new ArrayList();
        ArrayList<Integer> diamonds = new ArrayList();
        ArrayList<Integer> diamondMasters = new ArrayList();
        ArrayList<Integer> clubs = new ArrayList();
        ArrayList<Integer> clubMasters = new ArrayList();
        ArrayList<ArrayList> handSuits = new ArrayList<>(Arrays.asList(spades, hearts, diamonds, clubs));

        for (int i = 0; i < h.size(); i++) { //Organize hand into suits
            int card = h.get(i); //Get next card

            if ((card - 1) / 13 == 0) { //Check if card is in the first 13 cards of the deck (Clubs)
                clubs.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    masters.add(card);
                    clubMasters.add(card);
                }
            }
            if ((card - 1) / 13 == 1) {
                diamonds.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    masters.add(card);
                    diamondMasters.add(card);
                }
            }
            if ((card - 1) / 13 == 2) {
                hearts.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    masters.add(card);
                    heartMasters.add(card);
                }
            }
            if ((card - 1) / 13 == 3) {
                spades.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    masters.add(card);
                    spadeMasters.add(card);
                }
            }
        }

        for (int i = 0; i < masters.size(); i++) {
            if (masters.get(i) % 13 == 0) {
                greatestMaster = 13;
                aceCounter++;
                totalMasterPoints += 13;
            } else if ((masters.get(i) % 13) > greatestMaster) {
                greatestMaster = (masters.get(i) % 13);
            }
            totalMasterPoints += (masters.get(i) % 13);
        }

        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> temp = handSuits.get(i);
            int suitTricks = 0;

            //Rule 1: If the total amount of masters is greater than 4,
            //        and the sum of the master points is greater than 42,
            //        and the greatest master is higher than a Queen
            if (masters.size() > 4 && totalMasterPoints > 42 && greatestMaster > 11) {
//                System.out.println("\t\t\tRule 1");
//                System.out.println(determineSuitTricks(temp, 1));
                tricks += determineSuitTricks(temp, 1);
                suitTricks = determineSuitTricks(temp, 1);
                if (determineSuitTricks(temp, 1) > highestTricks) {
                    highestTricks = determineSuitTricks(temp, 1);
                    callSuit = "Suns";
                }
//                continue;
            }

            //Rule 2: If the hand contains 4 Aces
            if (aceCounter == 4 && masters.size() == 4) {
//                System.out.println("\t\t\tRule 2");
//                System.out.println((determineSuitTricks(temp, 2)));
                tricks += determineSuitTricks(temp, 2);
                suitTricks = determineSuitTricks(temp, 2);
                if (determineSuitTricks(temp, 2) > highestTricks) {
                    highestTricks = determineSuitTricks(temp, 2);
                    callSuit = "Suns";
                }
//                continue;
            }

            //Rule 3: If the suit contains a full house (Ace, King, Queen and Jack)
            if (temp.equals(spades)) {
                if (spadeMasters.size() == 4) {
//                    System.out.println("\t\t\tRule 3");
//                    System.out.println((determineSuitTricks(temp, 3)));
                    if (determineSuitTricks(temp, 3) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 3);
                        suitTricks = determineSuitTricks(temp, 3);
                        if (determineSuitTricks(temp, 3) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 3);
                            callSuit = "Spades";
                        }
                    }
//                    continue;
                }
            } else if (temp.equals(hearts)) {
                if (heartMasters.size() == 4) {
//                    System.out.println("\t\t\tRule 3");
//                    System.out.println((determineSuitTricks(temp, 3)));
                    if (determineSuitTricks(temp, 3) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 3);
                        suitTricks = determineSuitTricks(temp, 3);
                        if (determineSuitTricks(temp, 3) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 3);
                            callSuit = "Hearts";
                        }
                    }
//                    continue;
                }
            } else if (temp.equals(diamonds)) {
                if (diamondMasters.size() == 4) {
//                    System.out.println("\t\t\tRule 3");
//                    System.out.println((determineSuitTricks(temp, 3)));
                    if (determineSuitTricks(temp, 3) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 3);
                        suitTricks = determineSuitTricks(temp, 3);
                        if (determineSuitTricks(temp, 3) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 3);
                            callSuit = "Diamonds";
                        }
                    }
//                    continue;
                }
            } else {
                if (clubMasters.size() == 4) {
//                    System.out.println("\t\t\tRule 3");
//                    System.out.println((determineSuitTricks(temp, 3)));
                    if (determineSuitTricks(temp, 3) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 3);
                        suitTricks = determineSuitTricks(temp, 3);
                        if (determineSuitTricks(temp, 3) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 3);
                            callSuit = "Clubs";
                        }
                    }
//                    continue;
                }
            }

            //Rule 4: If a suit has more than 2 master cards,
            //        and more than 4 cards,
            //        and the total number of master cards in the hand is greater than 4
            if (temp.equals(spades)) {
                if (spadeMasters.size() > 2 && temp.size() > 4 && masters.size() > 4) {
//                    System.out.println("\t\t\tRule 4");
//                    System.out.println((determineSuitTricks(temp, 4)));
                    if (determineSuitTricks(temp, 4) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 4);
                        suitTricks = determineSuitTricks(temp, 4);
                        if (determineSuitTricks(temp, 4) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 4);
                            callSuit = "Spades";
                        }
                    }
//                    continue;
                }
            } else if (temp.equals(hearts)) {
                if (heartMasters.size() > 2 && temp.size() > 4 && masters.size() > 4) {
//                    System.out.println("\t\t\tRule 4");
//                    System.out.println((determineSuitTricks(temp, 4)));
                    if (determineSuitTricks(temp, 4) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 4);
                        suitTricks = determineSuitTricks(temp, 4);
                        if (determineSuitTricks(temp, 4) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 4);
                            callSuit = "Hearts";
                        }
                    }
//                    continue;
                }
            } else if (temp.equals(diamonds)) {
                if (diamondMasters.size() > 2 && temp.size() > 4 && masters.size() > 4) {
//                    System.out.println("\t\t\tRule 4");
//                    System.out.println((determineSuitTricks(temp, 4)));
                    if (determineSuitTricks(temp, 4) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 4);
                        suitTricks = determineSuitTricks(temp, 4);
                        if (determineSuitTricks(temp, 4) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 4);
                            callSuit = "Diamonds";
                        }
                    }
//                    continue;
                }
            } else {
                if (clubMasters.size() > 2 && temp.size() > 4 && masters.size() > 4) {
//                    System.out.println("\t\t\tRule 4");
//                    System.out.println((determineSuitTricks(temp, 4)));
                    if (determineSuitTricks(temp, 4) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 4);
                        suitTricks = determineSuitTricks(temp, 4);
                        if (determineSuitTricks(temp, 4) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 4);
                            callSuit = "Clubs";
                        }
                    }
//                    continue;
                }
            }

            //Rule 5: If a suit has two or more master cards,
            //        and five or more cards
            if (temp.equals(spades)) {
                if (spadeMasters.size() >= 2 && temp.size() >= 5) {
//                    System.out.println("\t\t\tRule 5");
//                    System.out.println((determineSuitTricks(temp, 5)));
                    if (determineSuitTricks(temp, 5) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 5);
                        if (determineSuitTricks(temp, 5) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 5);
                            callSuit = "Spades";
                        }
                    }
                }
            } else if (temp.equals(hearts)) {
                if (heartMasters.size() >= 2 && temp.size() >= 5) {
//                    System.out.println("\t\t\tRule 5");
//                    System.out.println((determineSuitTricks(temp, 5)));
                    if (determineSuitTricks(temp, 5) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 5);
                        if (determineSuitTricks(temp, 5) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 5);
                            callSuit = "Hearts";
                        }
                    }
                }
            } else if (temp.equals(diamonds)) {
                if (diamondMasters.size() >= 2 && temp.size() >= 5) {
//                    System.out.println("\t\t\tRule 5");
//                    System.out.println((determineSuitTricks(temp, 5)));
                    if (determineSuitTricks(temp, 5) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 5);
                        if (determineSuitTricks(temp, 5) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 5);
                            callSuit = "Diamonds";
                        }
                    }
                }
            } else {
                if (clubMasters.size() >= 2 && temp.size() >= 5) {
//                    System.out.println("\t\t\tRule 5");
//                    System.out.println((determineSuitTricks(temp, 5)));
                    if (determineSuitTricks(temp, 5) > suitTricks) {
                        tricks -= suitTricks;
                        tricks += determineSuitTricks(temp, 5);
                        if (determineSuitTricks(temp, 5) > highestTricks) {
                            highestTricks = determineSuitTricks(temp, 5);
                            callSuit = "Clubs";
                        }
                    }
                }
            }
        }
//        System.out.println(tricks + " " + callSuit);

        if (tricks > 3) {
            c = new Call(tricks, callSuit, false, new User("s"));

            System.out.println("-------------------------------");
            translate(hand);
            System.out.println("\tCall: " + tricks + " " + callSuit);
            System.out.println("-------------------------------");
        }

        return c;
    }

    public static void calculatePositions(ArrayList<Player> players) {
        //bubble sort
        Player[] playerArray = new Player[4];

        for (int i = 0; i < 4; i++) {
            playerArray[i] = players.get(i);
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (playerArray[i].getScore() > playerArray[j].getScore()) {
                    if (i > j) {
                        Player temp = playerArray[j];
                        playerArray[j] = playerArray[i];
                        playerArray[i] = temp;
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            playerArray[i].setPosition(i);
        }
//        if two players have the same score, this makes it so that they don't have the same position
//        for (HashMap.Entry key1 : positionMap.entrySet()) {
//            for (HashMap.Entry key2 : positionMap.entrySet()) {
//                if (!key1.equals(key2)) {
//                    if (key1.getValue().equals(key2.getValue())) {
//                        if ((int) key1.getValue() <= 2) {
//                            key2.setValue((int) key2.getValue() + 1);
//                            break;
//                        } else if ((int) key1.getValue() > 2) {
//                            key2.setValue((int) key2.getValue() - 1);
//                            break;
//                        }
//                    }
//                }
//            }
//            Player p = (Player) key1.getKey();
//            p.setPosition((int) key1.getValue());
//        }

        for (int i = 0; i < 4; i++) {
            for (Player player : players) {
                if (player.getPosition() == i) {
                    System.out.println("Position " + (i + 1) + ") " + player.getName() + " - " + player.getScore());
                }
            }
        }

//        return positionMap;
    }

    public static Boolean dashCall(ArrayList<Integer> h) {
        Boolean b = true;
//        System.out.println(name);
//        translate();
//        System.out.println();

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

        int aceCounter = 0;

        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> temp = handSuits.get(i);
            int masterCount = 0;

            if (temp.size() > 0 && (temp.get(0) % 13 == 0 && temp.get(temp.size() - 1) % 13 == 0)) {
                aceCounter++;
            }

            for (Integer card : temp) {
                if ((card % 13 == 0) || (card % 13 > 9)) {
                    masterCount++;
                }
            }

            //Rule 1: If a suit in the hand has master cards,
            //        and half or more of the suit is master cards,
            //        dash call cannot be called
            if ((temp.size() > 0)
                    && (masterCount >= (float) (temp.size() / 2))) {
//                System.out.println("\t\t\tRule 1");
                b = false;
            }

            //Rule 2: If a suit in the hand has less than 4 cards,
            //        and one of them is an Ace,
            //        dash call cannot be called
            if ((temp.size() <= 3 && temp.size() > 0)
                    && (temp.get(0) % 13 == 0)) {
//                System.out.println("\t\t\tRule 2");
                b = false;
            }

            //Rule 3: If a suit in the hand has 1 card,
            //        and greater than a 5,
            //        dash call cannot be called
            if (((temp.size() == 1) && (((temp.get(0) % 13) > 5)
                    || (temp.get(0) % 13) == 0))) {
//                System.out.println("\t\t\tRule 3");
                b = false;
            }

            //Rule 4: If the hand contains more than 2 aces,
            //        dash call cannot be called
            if (aceCounter > 2) {
//                System.out.println("\t\t\tRule 4");
                b = false;
            }

            //Rule 5: If the smallest card in a suit in the hand is greater than 5
            //        dash call cannot be called
            if (temp.size() > 0 && temp.size() < 3) {
                if (temp.get(temp.size() - 1) > 5) {
//                  System.out.println("\t\t\tRule 5");
                    b = false;
                }
            }
        }

        if (b == true) {
//            System.out.println("\t DASHCALL!");
            System.out.println("\n\n");
            translate(h);
            System.out.println("\n\n");
        }
        return b;
    }

    public static int testDashCall() {
        int dashCounter = 0;
        for (int i = 0; i < 500; i++) {
            ArrayList<ArrayList<Integer>> lists = Deal();

            for (ArrayList<Integer> list : lists) {
                if (dashCall(list)) {
                    dashCounter++;
                }
            }
        }
        return dashCounter;
    }

    public static void testOpenBidding(ArrayList<Integer> hand) {
        for (int i = 0; i < 100; i++) {
//            ArrayList<ArrayList<Integer>> lists = Deal();
            ArrayList<ArrayList<Integer>> lists = new ArrayList();

            for (ArrayList<Integer> list : lists) {
                openBidding(list);
            }
        }
    }

    public static void testDetermineTrickPaths(ArrayList<Computer> computers) {
        deal(computers);
        for (int i = 0; i < computers.size(); i++) {
            Computer computer = computers.get(i);
            System.out.println("----------------------------------------");
            translate(computer.getHand());
            computer.getCall().setSuit("Spades");
            computer.setCaller(false);
            computers.get(3).setCaller(true);
            determineTrickPaths(computer);
        }
    }

    public static void determineTrickPaths(Computer c1) {
        ArrayList<Trick> tricks = new ArrayList();
        ArrayList<Integer> hand = c1.getHand();
        ArrayList<Integer> spades = new ArrayList();
        ArrayList<Integer> spadeMasters = new ArrayList();
        ArrayList<Integer> hearts = new ArrayList();
        ArrayList<Integer> heartMasters = new ArrayList();
        ArrayList<Integer> diamonds = new ArrayList();
        ArrayList<Integer> diamondMasters = new ArrayList();
        ArrayList<Integer> clubs = new ArrayList();
        ArrayList<Integer> clubMasters = new ArrayList();
        ArrayList<ArrayList> handSuits = new ArrayList<>(Arrays.asList(spades, hearts, diamonds, clubs));
        HashMap<ArrayList<Integer>, ArrayList<Integer>> suitMap = new HashMap();

        for (int i = 0; i < hand.size(); i++) { //Organize hand into suits
            int card = hand.get(i); //Get next card

            if ((card - 1) / 13 == 0) { //Check if card is in the first 13 cards of the deck (Clubs)
                clubs.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    clubMasters.add(card);
                }
            }
            if ((card - 1) / 13 == 1) {
                diamonds.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    diamondMasters.add(card);
                }
            }
            if ((card - 1) / 13 == 2) {
                hearts.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    heartMasters.add(card);
                }
            }
            if ((card - 1) / 13 == 3) {
                spades.add(card);
                if (card % 13 == 0 || card % 13 == 12 || card % 13 == 11 || card % 13 == 10) {
                    spadeMasters.add(card);
                }
            }
        }

        suitMap.put(spades, spadeMasters);
        suitMap.put(hearts, heartMasters);
        suitMap.put(diamonds, diamondMasters);
        suitMap.put(clubs, clubMasters);

        for (ArrayList<Integer> suit : handSuits) {
            if (c1.isCaller()) {
                if (suit.size() > 0) {
                    if (Suit.returnSuitByName(c1.getCall().getSuit()).trumpCheck(suit.get(0)) && !c1.getCall().getSuit().equals("Suns")) {
                        if (suitMap.get(suit).size() == 4) {
                            for (int i : suit) {
                                tricks.add(new Trick(i, new ArrayList(), "Regular"));
                            }
                        } else if (suitMap.get(suit).size() == 3) {
                            for (int i = 0; i < suit.size() - 1; i++) {
                                tricks.add(new Trick(suit.get(i), new ArrayList(), "Regular"));
                            }
                        } else {
                            for (int i = 0; i < suitMap.get(suit).size(); i++) {
                                if (suit.get(i) % 13 == 0) {
                                    tricks.add(new Trick(suit.get(i), new ArrayList(), "Regular"));
                                }
                                if (suit.get(i) % 13 == 12 && suit.size() > 1) {
                                    tricks.add(new Trick(suit.get(i), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1))), "Regular"));
                                }
                                if (suit.get(i) % 13 == 11 && suit.size() > 2) {
                                    tricks.add(new Trick(suit.get(i), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2), suit.get(suit.size() - 3))), "Regular"));
                                }
                                if (suit.get(i) % 13 == 10 && suit.size() > 3) {
                                    tricks.add(new Trick(suit.get(i), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2), suit.get(suit.size() - 3))), "Regular"));
                                }
                            }
                            for (int i = 2; i < suitMap.get(suit).size() - 2; i++) {
                                tricks.add(new Trick(suit.get(i), new ArrayList(), "Regular"));
                            }
                        }
                    } else {
                        if (suit.size() == 1) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                            }
                        }

                        if (suit.size() == 2) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1))), "Regular"));
                            }
                        }
                        if (suit.size() == 3) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1), suit.get(2))), "Regular"));
                                if (suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList<>(Arrays.asList(suit.get(2))), "Regular"));
                                }
                            }
                            if (suit.get(0) % 13 == 11) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1), suit.get(2))), "Regular"));
                            }
                        }
                        if (suit.size() == 4) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                                if (suit.get(2) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(2), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                                if (suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                                }
                            }
                            if (suit.get(0) % 13 == 11) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                            }
                            if (suit.get(0) % 13 == 10) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1), suit.get(2), suit.get(3))), "Regular"));
                            }
                        }
                        if (suit.size() >= 5 && !c1.getCall().getSuit().equals("Suns")) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12) {
                                    tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                                }
                            }
                        }
                        if (suit.size() >= 5 && c1.getCall().getSuit().equals("Suns")) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                            } else if (suit.get(0) % 13 == 11) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                            } else if (suit.get(0) % 13 == 10) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2), suit.get(suit.size() - 3))), "Regular"));
                            }
                        }
                    }
                }
            } else {
                if (suit.size() > 0) {
                    if (Suit.returnSuitByName(c1.getCall().getSuit()).trumpCheck(suit.get(0)) && !c1.getCall().getSuit().equals("Suns")) {
                        if (suit.size() == 1) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                            }
                        }
                        if (suit.size() == 2) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1))), "Regular"));
                            }
                        }
                        if (suit.size() == 3) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1), suit.get(2))), "Regular"));
                                if (suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList<>(Arrays.asList(suit.get(2))), "Regular"));
                                }
                            }
                            if (suit.get(0) % 13 == 11) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1), suit.get(2))), "Regular"));
                            }
                        }
                        if (suit.size() == 4) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                                if (suit.get(2) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(2), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                                if (suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                                }
                            }
                            if (suit.get(0) % 13 == 11) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                            }
                            if (suit.get(0) % 13 == 10) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1), suit.get(2), suit.get(3))), "Regular"));
                            }
                        }
                        if (suit.size() >= 5) {
                            for (int i = 0; i < suitMap.get(suit).size(); i++) {
                                if (suit.get(i) % 13 == 0) {
                                    tricks.add(new Trick(suit.get(i), new ArrayList(), "Regular"));
                                }
                                if (suit.get(i) % 13 == 12) {
                                    tricks.add(new Trick(suit.get(i), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                                }
                                if (suit.get(i) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(i), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2), suit.get(suit.size() - 3))), "Regular"));
                                }
                                if (suit.get(i) % 13 == 10) {
                                    tricks.add(new Trick(suit.get(i), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2), suit.get(suit.size() - 3))), "Regular"));
                                }
                            }
                        }

                    } else {
                        if (suit.size() == 1) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                            }
                        }

                        if (suit.size() == 2) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1))), "Regular"));
                            }
                        }
                        if (suit.size() == 3) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1), suit.get(2))), "Regular"));
                                if (suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList<>(Arrays.asList(suit.get(2))), "Regular"));
                                }
                            }
                            if (suit.get(0) % 13 == 11) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1), suit.get(2))), "Regular"));
                            }
                        }
                        if (suit.size() == 4) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                                if (suit.get(2) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(2), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                                if (suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                                }
                            }
                            if (suit.get(0) % 13 == 11) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                            }
                            if (suit.get(0) % 13 == 10) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(1), suit.get(2), suit.get(3))), "Regular"));
                            }
                        }
                        if (suit.size() >= 5 && !c1.getCall().getSuit().equals("Suns")) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12) {
                                    tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                }
                            }
                        }
                        if (suit.size() >= 5 && c1.getCall().getSuit().equals("Suns")) {
                            if (suit.get(0) % 13 == 0) {
                                tricks.add(new Trick(suit.get(0), new ArrayList(), "Regular"));
                                if (suit.get(1) % 13 == 12 || suit.get(1) % 13 == 11) {
                                    tricks.add(new Trick(suit.get(1), new ArrayList(), "Regular"));
                                }
                            } else if (suit.get(0) % 13 == 12) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                            } else if (suit.get(0) % 13 == 11) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2))), "Regular"));
                            } else if (suit.get(0) % 13 == 10) {
                                tricks.add(new Trick(suit.get(0), new ArrayList<>(Arrays.asList(suit.get(suit.size() - 1), suit.get(suit.size() - 2), suit.get(suit.size() - 3))), "Regular"));
                            }
                        }
                    }
                }
            }
        }

        for (Trick trick1 : tricks) {
            for (Trick trick2 : tricks) {
                if (!trick1.equals(trick2)) {
                    if (trick1.getSacrifices().contains(trick2.getCard())) {
                        ArrayList<Integer> s = trick1.getSacrifices();
                        s.remove(s.indexOf(trick2.getCard()));
                        trick1.setSacrifices(s);
                    }
                }
            }
            trick1.sortSacrifices();
        }

        for (int i = 0; i < tricks.size(); i++) {
            for (int j = 0; j < tricks.size(); j++) {
                if (!tricks.get(i).equals(tricks.get(j))) {
                    if (tricks.get(i).getCard() % 13 > (tricks.get(j).getCard() % 13) && tricks.get(j).getCard() % 13 != 0) {
                        Trick temp = tricks.get(j);
                        tricks.set(j, tricks.get(i));
                        tricks.set(i, temp);
                    }
                }
            }
        }

        System.out.println("-------------\n" + tricks.size() + " trick(s)");
        for (Trick trick : tricks) {
            System.out.println("-------------");
            System.out.println(translate(trick.getCard()) + " Sacrifices: ");
            for (int i : trick.getSacrifices()) {
                System.out.println(translate(i));
            }
            System.out.println("-------------");
        }
        if (c1.getCall().getTricks() > tricks.size()) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
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
//        ArrayList<Integer> suit = new ArrayList<>(Arrays.asList(2, 15, 16, 17, 33, 34, 36, 39, 46, 45, 49, 50, 52));
//        for (int j = 0; j < 20; j++) {
//            System.out.println("\n\n\n");
//            ArrayList<Integer> suit = new ArrayList();
//
//            ArrayList<Integer> cards = new ArrayList(52);
//            for (int i = 1; i < 53; i++) {
//                cards.add(i);
//            }
//
//            Collections.shuffle(cards);
//
//            for (int i = 0; i < 13; i++) {
//                suit.add(cards.get(i));
//            }
//
//            Collections.sort(suit);
//            Translate(suit);
//            Call c = openBidding(suit);
//            if (!c.isPassed()) {
//                System.out.println("\t\t\t"+c.getTricks() + " " + c.getSuit());
//            }
//            c = maxOpenBidding(suit);
//            if (!c.isPassed()) {
//                System.out.println("\t\t\t"+c.getTricks() + " " + c.getSuit());
//            }
//        }
//        System.out.println(suitPoints(suit));
//        int i;
        User u1 = new User("Ahmad");
        User u2 = new User("Negm");
        User u3 = new User("Ramy");
        User u4 = new User("Helal");
//
        Computer c1 = new Computer("Computer1");
        Computer c2 = new Computer("Computer2");
        Computer c3 = new Computer("Computer3");
        Computer c4 = new Computer("Computer4");

        ArrayList<Computer> computers = new ArrayList<>(Arrays.asList(c1, c2, c3, c4));
//        testDetermineTrickPaths(computers);

//        u1.incrementScore(21);
//        u2.incrementScore(-1);
//        u3.incrementScore(-1);
//        u4.incrementScore(20);
//        ArrayList<Player> players = new ArrayList<>(Arrays.asList(u1, u2, u3, u4));
//        Estimation.calculatePositions(players);
        Session s1 = new Session(u1, c2, c3, c4);
//        Session s1 = new Session(u1, u2, u3, u4);
        s1.startSession();
    }

}
