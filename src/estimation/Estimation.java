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
    
    public static int determineSuitTricks(ArrayList<Integer> suit, int Case){
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
                    if(ace && king){
                        tricks += suitSize;
                    }
                    else{
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
                totalMasterPoints+=13;
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

        if (tricks > 3) {
            c = new Call(tricks, callSuit, false, new Player());
        }

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
                totalMasterPoints+=13;
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
            c = new Call(tricks, callSuit, false, new Player());
        }

        return c;
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
        User u1 = new User("Ahmad");
        User u2 = new User("Negm");
        User u3 = new User("Ramy");
        User u4 = new User("Helal");
//
//        Computer c1 = new Computer("Computer1");
//        Computer c2 = new Computer("Computer2");
//        Computer c3 = new Computer("Computer3");
//        Computer c4 = new Computer("Computer4");

        Session s1 = new Session(u1, u2, u3, u4, "Easy");
    }

}
